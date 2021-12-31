package com.sap.ibso.eservices.facades.sagia;

/**
 * Provides access to LicensePrintingFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface LicensePrintingFacade {
    /**
     * @return licence bite array
     */
    byte[] getLicense();

    /**
     * Retrieves WarningLetter
     * @param transactionId transactionId
     * @return warningLetter bite array
     */
    byte[] getWarningLetter(String transactionId);

    /**
     * Retrieves NotificationNotes
     * @return notificationNotes bite array
     */
    byte[] getNotificationNotes();

    /**
     * retrieves document by documentNumber
     * @param documentNumber documentNumber
     * @return document bite array
     */
    byte[] getDocument(String documentNumber);
}
