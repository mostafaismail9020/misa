package com.sap.ibso.eservices.sagiaservices.document;

import org.springframework.util.Assert;

/**
 * Provides access to e-services related documents.
 */
public interface DocumentService
{
    /**
     * Provides the types of documents which are retrievable as PDFs.
     */
    enum DocumentType
    {
        /**
         * Indicates that a document is a digital license.
         */
        DIGITAL_LICENSE,
        /**
         * Indicates that a document is a warning letter.
         */
        WARNING_LETTER,
        /**
         * Indicates that a document contains notification notes.
         */
        NOTES,
        /**
         * Indicates that a document is a SADAD invoice for a sales order.
         */
        SADAD_SALES_ORDER_INVOICE,
        /**
         * Indicates that a document is a SADAD invoice for a service request.
         */
        SADAD_SERVICE_REQUEST_INVOICE
    }

    /**
     * Retrieves a document in portable document format (PDF) for a document number, a document type and
     * always implicitly for the currently logged in user. Note that a document number is not required for
     * {@link DocumentType#DIGITAL_LICENSE}. An empty byte array is returned if no matching document was found.
     * <p>
     * The following basic parameter check is performed: document type must not be null.
     * </p>
     *
     * @param documentNumber the unique identifier for a document of certain document type
     * @param documentType the document type
     * @return the document as PDF byte array
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    byte[] getDocumentAsPdf(String documentNumber, DocumentType documentType);

    /**
     * Retrieves a digital license in portable document format (PDF) for the currently logged in user.
     * An empty byte array is returned if no digital license was found.
     **
     * @return the digital license as PDF byte array
     */
    default byte[] getDigitalLicense()
    {
        return getDocumentAsPdf(null, DocumentType.DIGITAL_LICENSE);
    }

    /**
     * Retrieves a warning letter in portable document format (PDF) for a document number and the currently logged in user.
     * An empty byte array is returned if no warning letter was found.
     * <p>
     * The following basic parameter check is performed: document number must not be null.
     * </p>
     *
     * @param documentNumber the document number
     * @return the warning letter as PDF byte array
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    default byte[] getWarningLetter(String documentNumber)
    {
        // Basic parameter check
        Assert.notNull(documentNumber, "Document number must not be null.");
        return getDocumentAsPdf(documentNumber, DocumentType.WARNING_LETTER);
    }

    /**
     * Retrieves notification notes in portable document format (PDF) for the currently logged in user.
     * An empty byte array is returned if no notification notes were found.
     **
     * @return the notification notes as PDF byte array
     */
    default byte[] getNotificationNotes()
    {
        return getDocumentAsPdf(null, DocumentType.NOTES);
    }

    /**
     * Retrieves a SADAD sales order invoice in portable document format (PDF) for a document number and the currently logged in user.
     * An empty byte array is returned if no SADAD sales order invoice was found.
     * <p>
     * The following basic parameter check is performed: document number must not be null.
     * </p>
     *
     * @param documentNumber the document number which is actually a sales order number
     * @return the SADAD sales order invoice as PDF byte array
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    default byte[] getSadadSalesOrderInvoice(String documentNumber)
    {
        // Basic parameter check
        Assert.notNull(documentNumber, "Document number must not be null.");
        return getDocumentAsPdf(documentNumber, DocumentType.SADAD_SALES_ORDER_INVOICE);
    }

    /**
     * Retrieves a SADAD service request invoice in portable document format (PDF) for a document number and the currently logged in user.
     * An empty byte array is returned if no SADAD service request invoice was found.
     * <p>
     * The following basic parameter check is performed: document number must not be null.
     * </p>
     *
     * @param documentNumber the document number which is actually a service request number
     * @return the SADAD service request invoice as PDF byte array
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    default byte[] getSadadServiceRequestInvoice(String documentNumber)
    {
        // Basic parameter check
        Assert.notNull(documentNumber, "Document number must not be null.");
        return getDocumentAsPdf(documentNumber, DocumentType.SADAD_SERVICE_REQUEST_INVOICE);
    }
}
