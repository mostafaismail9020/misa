package com.sap.ibso.eservices.core.sagia.services;

import java.util.Collection;
import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;

/**
 * Provides access to Hybris Investor Notification Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaHybrisInvestorNotificationService {

   /**
    * Get the notifications for an investor.
    * @param investorId - The investor id for which the notifications are retrivied.
    * @return -
    */
   List<SagiaInvestorNotificationModel> getNotificationsFor(String investorId);

   /**
    * Get a notification by the investor and transaction.
    * @param investorId - The investor id
    * @param transactionId - The transaction id
    * @return - The notification with that specific investor id and transaction id.
    */
   SagiaInvestorNotificationModel getNotificationBy(String investorId, String transactionId);

   /**
    * Synchronizes the notification from CRM with Hybries and then retrieves them.
    * @param investorId - The investor id
    * @param crmNotifications - The collection of notifications from CRM
    * @return - The collection of notifications synchronized in Hybris.
    */
   List<SagiaInvestorNotificationModel> syncAndGetHybrisNotifications(String investorId, Collection<InvestorNotificationData> crmNotifications);

   /**
    * Updates the read date for a notification.
    * @param notificationModel - The notification for which the date is updated.
    */
   void updateReadDateFor(SagiaInvestorNotificationModel notificationModel);

   /**
    * Marks all notifications as read for an investor.
    * @param investorId - The investor id
    * @param readDate - The date in which the notification has been read.
    */
   void markAllNotificationsAsReadFor(String investorId, String readDate);

}
