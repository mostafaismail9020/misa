package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.AmanahData;
import com.sap.ibso.eservices.facades.data.WasselCheckData;
import com.sap.ibso.eservices.facades.populators.AmanahPopulator;
import com.sap.ibso.eservices.facades.populators.WasselCheckPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaGovernmentDocumentsFacade;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovDocWasselCheck;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtDropdown;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaGovernmentDocumentsFacade
 */
public class DefaultSagiaGovernmentDocumentsFacade implements SagiaGovernmentDocumentsFacade {

    @Autowired
    private AmanahPopulator amanahPopulator;

    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;
    private WasselCheckPopulator wasselCheckPopulator;

    /**
     * @return - Retrieves the values for the Amanah dropdown
     */
    @Override
    public List<AmanahData> getAmanahList()  {
        Collection<GovtDropdown> govtDropdowns = zui5SagiaFacade.getGovtDropdowns();

        List<AmanahData> amanahDatas = new ArrayList();

        for(GovtDropdown govtDropdown : govtDropdowns)
        {
            AmanahData amanahData = new AmanahData();
            amanahPopulator.populate(govtDropdown,amanahData);
            amanahDatas.add(amanahData);
        }
        return amanahDatas;
    }


    /**
     * Interogates the CRM about the status of a Government Header
     *
     * @return - The response mapped on a WasselCheckData DTO
     */
    @Override
    public WasselCheckData requestWasselCheck() {
        SagiaGovDocWasselCheck sagiaGovDocWasselCheck = zui5SagiaFacade.requestWasselCheck();
        WasselCheckData wasselCheckData = new WasselCheckData();
        getWasselCheckPopulator().populate(sagiaGovDocWasselCheck,wasselCheckData);

        return wasselCheckData;
    }

    /**
     * @return
     */
    public ZUI5SagiaFacade getZui5SagiaFacade() {
        return zui5SagiaFacade;
    }

    /**
     * @param zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }

    /**
     * @return
     */
    public AmanahPopulator getAmanahPopulator() {
        return amanahPopulator;
    }

    /**
     * @param amanahPopulator
     */
    public void setAmanahPopulator(AmanahPopulator amanahPopulator) {
        this.amanahPopulator = amanahPopulator;
    }

    /**
     * @param wasselCheckPopulator
     */
    public void setWasselCheckPopulator(WasselCheckPopulator wasselCheckPopulator) {
        this.wasselCheckPopulator = wasselCheckPopulator;
    }

    /**
     * @return
     */
    public WasselCheckPopulator getWasselCheckPopulator() {
        return wasselCheckPopulator;
    }
}
