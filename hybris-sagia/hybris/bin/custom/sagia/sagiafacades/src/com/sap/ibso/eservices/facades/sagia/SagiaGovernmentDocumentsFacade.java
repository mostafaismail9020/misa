package com.sap.ibso.eservices.facades.sagia;


import com.sap.ibso.eservices.facades.data.AmanahData;
import com.sap.ibso.eservices.facades.data.WasselCheckData;

import java.util.List;

/**
 * SagiaGovernmentDocumentsFacade
 */
public interface SagiaGovernmentDocumentsFacade {

    /**
     * retrieves AmanahList
     * @return - Retrieves the values for the Amanah dropdown
     */
    List<AmanahData> getAmanahList();


    /**
     * Interogates the CRM about the status of a Government Header
     *
     * @return - The response mapped on a WasselCheckData DTO
     */
    WasselCheckData requestWasselCheck();
}
