package com.sap.ibso.eservices.bol.document;

import com.sap.ibso.eservices.bol.document.data.DocumentBackendData;

/**
 * Provides synchronous access to e-services related documents in SAP backend system.
 */
public interface DocumentBackendService
{
    /**
     * Retrieves a document in portable document format (PDF) for a document number, a backend document type and an entity.
     * An empty byte array is returned (within {@link DocumentBackendData}) if no document was found matching
     * the document number, backend document type and entity identifier.
     *
     * @param documentNumber the unique identifier for a document of certain document type
     * @param documentType the backend document type
     * @param entityId the identifier of the entity for which the document shall be retrieved
     * @return the document backend data
     */
    DocumentBackendData getDocumentAsPdf(String documentNumber, BackendDocumentType documentType, String entityId);
}
