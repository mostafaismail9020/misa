package com.sap.ibso.eservices.facade.actions;

import com.sap.ibso.eservices.core.model.BrandPresenceModel;
import com.sap.ibso.eservices.core.model.EntitiesManagedByRhqModel;
import com.sap.ibso.eservices.core.model.OperatingCostForRhqModel;
import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;
import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class SagiaSendRhqTables extends AbstractAction<SagiaPublishLicenseProcessProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SagiaSendRhqTables.class);

    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    @Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK","BYPASS");
    }

    @Override
    public String execute(SagiaPublishLicenseProcessProcessModel arg0) throws RetryLaterException, Exception {
            baseSiteService.setCurrentBaseSite(arg0.getSite(), true);
            if (!arg0.getSagiaLicense().getEntityInformation().getLicenseType().getCode().equals("11"))
            {
                return ("BYPASS");
            }
            else
                {
                    for (EntitiesManagedByRhqModel entityModel : arg0.getSagiaLicense().getEntityInformation().getListOfEntitiesManagedByRhq()) {
                        try
                        {
                            sagiaODataFacade.saveEntityManagedByRhqOData(entityModel, arg0.getSagiaLicense().getEntityInformation());
                        }
                        catch (Exception e)
                        {
                            LOG.error("Entity Managed by RHQ Data sending failed for License " + arg0.getSagiaLicense().getCode() + " With the following Error", e);
                            return ("NOK");
                        }
                    }
                    for (BrandPresenceModel brandPresence : arg0.getSagiaLicense().getEntityInformation().getListOfBrandPresenceInMENARegion()) {
                        try
                        {
                           sagiaODataFacade.saveBrandPresenceInMENARegion(brandPresence, arg0.getSagiaLicense().getEntityInformation());
                        }
                        catch (Exception e)
                        {
                            LOG.error("Brand Presence in MENA Region Data sending failed for License " + arg0.getSagiaLicense().getCode() + " With the following Error", e);
                            return ("NOK");
                        }
                    }
                    for (OperatingCostForRhqModel opeartingCost : arg0.getSagiaLicense().getEntityInformation().getListOfEstimatedOperatingCostForRhq()) {
                        try
                        {
                            sagiaODataFacade.saveEstimatedOperatingCostForRhq(opeartingCost, arg0.getSagiaLicense().getEntityInformation());
                        }
                        catch (Exception e)
                        {
                            LOG.error("Estimated Operating Cost for RHQ Data sending failed for License " + arg0.getSagiaLicense().getCode() + " With the following Error", e);
                            return ("NOK");
                        }
                    }
                    return ("OK");
            }
    }
}