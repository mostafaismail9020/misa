package com.sap.ibso.eservices.bol.document;

/**
 * Provides the types of documents which are retrievable as PDFs in SAP backend system.
 */
public enum BackendDocumentType
{
    /**
     * Indicates that a document is a digital license.
     */
    DIGITAL_LICENSE("ZESRV_DLIC"),
    /**
     * Indicates that a document is a warning letter.
     */
    WARNING_LETTER("ZESRV_WRNL"),
    /**
     * Indicates that a document contains notification notes.
     */
    NOTES("ZESRV_NOTE"),
    /**
     * Indicates that a document is a SADAD invoice for a sales order.
     */
    SADAD_SALES_ORDER_INVOICE("ZESRV_SADA"),
    /**
     * Indicates that a document is a SADAD invoice for a service request.
     */
    SADAD_SERVICE_REQUEST_INVOICE("ZESRV_SRSD");

    private final String name;

    /**
     * Create a document type enumeration value.
     *
     * @param name the ABAP document type name
     */
    BackendDocumentType(String name)
    {
        this.name = name;
    }

    /**
     * Gets the ABAP name of a document type.
     *
     * @return the ABAP document type name
     */
    @Override
    public String toString()
    {
        return name;
    }
}
