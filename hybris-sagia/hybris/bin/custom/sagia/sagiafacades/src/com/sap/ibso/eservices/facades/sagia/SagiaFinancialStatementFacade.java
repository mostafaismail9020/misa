package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.AttachmantHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.FinancialStatementHDR;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;


public interface SagiaFinancialStatementFacade {

    /**
     * Loads the Financial Statementfrom the CRM
     *
     * @return List of FinancialStatementHDR
     * @throws IOException exception
     */
    List<FinancialStatementHDR> getFinancialStatementHDR();

    /**
     * Loads a Financial Statement by id
     *
     * @param id id
     * @return FinancialStatementHDR
     */
    FinancialStatementHDR getFinancialStatementHDR(String id);

    /**
     * retrieves FinancialStatementSupportingAttachments
     * @return Collection of AttachmantHDR
     */
    //Collection<AttachmantHDR> getFinancialStatementSupportingAttachments();

    /**
     * reads Attachment
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    InputStream readAttachment(String objectId, String documentId);
    
}
