package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaTermsAndConditionsModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTermsAndConditionsDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUserTermsAndConditionsDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaTermsAndConditionsService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;

/**
 * Default implementation of Terms And Conditions Service.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultTermsAndConditionsService extends AbstractBusinessService implements SagiaTermsAndConditionsService {
    private transient SagiaTermsAndConditionsDAO sagiaTermsAndConditionsDAO;
    private transient SagiaUserTermsAndConditionsDAO sagiaUserTermsAndConditionsDAO;
    private transient BaseSiteService baseSiteService;
    private static final int DELETION_PERIOD_SECONDS = 900;

    /**
     * @return - All terms and conditions stored in DB
     */
    @Override
    public List<SagiaTermsAndConditionsModel> getAll() {
        return sagiaTermsAndConditionsDAO.getAll();
    }

    /**
     * Retrieves the most recent saved terms and conditions.
     * Period : now - seconds
     * @param seconds - Period of seconds
     * @param event - Acceptance event
     * @return - List of recently saved terms and conditions
     */
    @Override
    public List<SagiaTermsAndConditionsModel> getRecentlySaved(int seconds,TermsAndConditionsAcceptanceEventEnum event) {
        return  getSagiaTermsAndConditionsDAO().getRecentlySaved(seconds,event);
    }

    /**
     * Get the active terms and conditions for an event
     * @param event - The event for which T&C are retrieved
     * @return - Terms and Conditions Model
     */
    public SagiaTermsAndConditionsModel getActive(TermsAndConditionsAcceptanceEventEnum event){
        return getSagiaTermsAndConditionsDAO().getActive(event);
    }

    /**
     * Method that is called from {@link com.sap.ibso.eservices.core.event.SagiaAfterSaveListener}.
     * It creates a new version of a 'Terms and Conditions' entity while keeping hisotry.
     * It removes all the entities associated with that event that were saved less than 15 min ago.
     * This duration period can be changed from
     * {@link com.sap.ibso.eservices.core.sagia.services.impl.DefaultTermsAndConditionsService#DELETION_PERIOD_SECONDS}.
     * @param content - The HTML content of the Terms And Conditions page
     * @param event - The event for which T&C are updated (ex. REGISTRATION, SPECIAL_SERVICES etc)
     */
    public void handleTermsAndConditionsUpdate(String content,TermsAndConditionsAcceptanceEventEnum event){
        boolean activeFound = false;

        SagiaTermsAndConditionsModel sagiaTermsAndConditionsModel = new SagiaTermsAndConditionsModel();
        sagiaTermsAndConditionsModel.setContent(content);
        sagiaTermsAndConditionsModel.setAcceptanceEvent(event);
        sagiaTermsAndConditionsModel.setActive(true);

        List<SagiaTermsAndConditionsModel> modelList = getRecentlySaved(DELETION_PERIOD_SECONDS,event);

        if(modelList!= null && !modelList.isEmpty()) {
            for(SagiaTermsAndConditionsModel dbModel : modelList) {
                if(dbModel.getActive()){
                    activeFound = true;
                    sagiaTermsAndConditionsModel.setVersion(dbModel.getVersion());
                }
                getModelService().remove(dbModel);
            }
        }

        if(!activeFound) {
            SagiaTermsAndConditionsModel activeTC = getActive(event);
            if(activeTC != null) {
                activeTC.setActive(false);
                getModelService().save(activeTC);
                sagiaTermsAndConditionsModel.setVersion(activeTC.getVersion()+1);
            } else {
                sagiaTermsAndConditionsModel.setVersion(1);
            }
        }
        getModelService().save(sagiaTermsAndConditionsModel);
    }

    public void setSagiaTermsAndConditionsDAO(SagiaTermsAndConditionsDAO sagiaTermsAndConditionsDAO) {
        this.sagiaTermsAndConditionsDAO = sagiaTermsAndConditionsDAO;
    }

    public SagiaTermsAndConditionsDAO getSagiaTermsAndConditionsDAO() {
        return sagiaTermsAndConditionsDAO;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public SagiaUserTermsAndConditionsDAO getSagiaUserTermsAndConditionsDAO() {
        return sagiaUserTermsAndConditionsDAO;
    }

    public void setSagiaUserTermsAndConditionsDAO(SagiaUserTermsAndConditionsDAO sagiaUserTermsAndConditionsDAO) {
        this.sagiaUserTermsAndConditionsDAO = sagiaUserTermsAndConditionsDAO;
    }
}
