package com.sap.ibso.eservices.sagiaservices.document.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.document.BackendDocumentType;
import com.sap.ibso.eservices.bol.document.DocumentBackendService;
import com.sap.ibso.eservices.bol.document.data.DocumentBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.document.DocumentService;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Implements access to e-services related documents.
 */
public class DefaultDocumentService extends BackendAwareService implements DocumentService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultDocumentService.class);

    private InvestorMappingService investorMappingService;

    /**
     * Creates the default document service.
     *
     * @param documentBackendServiceBeanName the bean name (or alias) of the document backend service
     * @param investorMappingService         the investor mapping service to retrieve the entity identifier associated with the current user
     */
    public DefaultDocumentService(String documentBackendServiceBeanName, InvestorMappingService investorMappingService)
    {
        super(documentBackendServiceBeanName);
        this.investorMappingService = investorMappingService;
    }

    @Override
    public byte[] getDocumentAsPdf(String documentNumber, DocumentType documentType)
    {
        // Basic parameter checks
        Assert.notNull(documentType, "Document type must not be null.");

        // Backend service access
        DocumentBackendService backendService = getBackendService();
        // Retrieve the document in the corresponding backend system
        DocumentBackendData documentBackendData = backendService.getDocumentAsPdf(documentNumber, BackendDocumentType.valueOf(documentType.name()), investorMappingService.getEntityId());

        // Log potential backend messages
        MessageUtil.logBackendMessages(documentBackendData.getMessages(), LOGGER);

        return documentBackendData.getDocument();
    }
}
