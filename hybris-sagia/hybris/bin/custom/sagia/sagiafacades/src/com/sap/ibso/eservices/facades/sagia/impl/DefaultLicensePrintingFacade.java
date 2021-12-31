package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.sagia.LicensePrintingFacade;
import com.sap.ibso.eservices.sagiaservices.document.DocumentService;

/**
 * DefaultLicensePrintingFacade
 */
public class DefaultLicensePrintingFacade implements LicensePrintingFacade{
    private DocumentService sagiaEServicesDefaultDocumentService;

    /**
     * @return
     */
    public DocumentService getSagiaEServicesDefaultDocumentService() {
        return sagiaEServicesDefaultDocumentService;
    }

    /**
     * @param sagiaEServicesDefaultDocumentService
     */
    public void setSagiaEServicesDefaultDocumentService(DocumentService sagiaEServicesDefaultDocumentService) {
        this.sagiaEServicesDefaultDocumentService = sagiaEServicesDefaultDocumentService;
    }

    /**
     * @return
     */
    @Override
    public byte[] getLicense() {
        return sagiaEServicesDefaultDocumentService.getDigitalLicense();
    }

    /**
     * @param transactionId
     * @return
     */
    @Override
    public byte[] getWarningLetter(String transactionId) {
        return sagiaEServicesDefaultDocumentService.getWarningLetter(transactionId);
    }

    /**
     * @return
     */
    @Override
    public byte[] getNotificationNotes() {
        return sagiaEServicesDefaultDocumentService.getNotificationNotes();
    }


    @Override
    public byte[] getDocument(String documentNumber) {
        return sagiaEServicesDefaultDocumentService.getSadadServiceRequestInvoice(documentNumber);
    }

}
