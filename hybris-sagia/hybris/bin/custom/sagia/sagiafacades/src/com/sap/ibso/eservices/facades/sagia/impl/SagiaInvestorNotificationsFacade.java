package com.sap.ibso.eservices.facades.sagia.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentService;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.PaymentItem;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Required;

import com.sap.ibso.eservices.sagiaservices.converters.notifications.InvestorNotificationConverter;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationCount;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationUtils;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationsCountBuilder;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationsService;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaPopupNotificationData;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * SagiaInvestorNotificationsFacade
 */
public class SagiaInvestorNotificationsFacade {

    private static final Logger LOG = Logger.getLogger(SagiaInvestorNotificationsFacade.class);
    private InvestorNotificationsService investorNotificationsService;
    private InvestorNotificationConverter investorNotificationConverter;
    private static final int POPUP_NOTIFICATIONS_TO_DISPLAY = 10;
    private static final String NOTIFICATION_TYPE_PAYMENT = "PY";
    private static final String PAYMENT_TRANSACTION_DELIMITER = "|";

    private I18NService i18NService;

    private MessageSource messageSource;

    private SagiaPaymentService sagiaSalesOrderPaymentService;

    /**
     * Retrieves All Notifications
     *
     * @return List of SagiaInvestorNotificationDTO
     */
    public List<SagiaInvestorNotificationDTO> getAllNotifications() {
        try {
            List<SagiaInvestorNotificationDTO> notifications = investorNotificationsService.getAllExpandedNotifications();
            Iterator<SagiaInvestorNotificationDTO> iterator = notifications.iterator();

            while(iterator.hasNext()){
                SagiaInvestorNotificationDTO notification = iterator.next();

                if(NOTIFICATION_TYPE_PAYMENT.equals(notification.getNotificationType())){
                    populatePaymentDetails(notification);
                    populateNotificationText(notification);
                    String[] salesOrders = splitTransactionsFromTransactionId(notification.getTransactionId());
                    //remove the notification if it is marked as already payed in Hybris.
                    if(salesOrders.length > 1) {
                    	if(sagiaSalesOrderPaymentService.isAtLeastOneSalesOrderSaved(salesOrders[0],salesOrders[1])){
                            iterator.remove();
                        }
                    }else {
                    	if(sagiaSalesOrderPaymentService.isSalesOrderSaved(salesOrders[0])){
                            iterator.remove();
                        }
                    }
                }
            }
            return notifications;
        } catch(SagiaODataException e) {
            LOG.warn(e.getMessage(),e);
            return Collections.emptyList();
        }
    }

    /**
     * If the notification is of type payment, the notification text is retrieved from CRM
     * under the form of a string containing several tokens split by several delimiters.
     * This method find the payment items,currency and the total amount and set them on the
     * object.
     * @param notification - processed notification
     * @return - notification containing payment details including items,currency and total amount.
     */
    public void populatePaymentDetails(SagiaInvestorNotificationDTO notification) {
        Map<String,List<PaymentItem>> paymentDetails = new HashMap();
        //Pattern to exclude the 'Items -' syntax from the items of the sales order.
        Pattern excludeItemsWordPattern = Pattern.compile("(?:\\s*Items - )(.+)");
        //Pattern to split the attributes of an item.
        Pattern splitItemPattern = Pattern.compile("(.+?)(?:\\s*:\\s*)(\\w{3})(?:\\s*)([0-9]+\\.?[0-9]+)");
        Matcher matcher;

        // '#' delimits the sales orders. They are two.
        StringTokenizer ordersTokenizer = new StringTokenizer(notification.getNotificationText(), "#");
        // two sales orders.
        for (int i = 0; i < 2; i++) {
            if (ordersTokenizer.hasMoreTokens()) {
                String salesOrder = ordersTokenizer.nextToken();
                String salesOrderDescription = Strings.EMPTY;
                salesOrder = salesOrder.trim();

                // '|' delimits the attributes of a sale order. They should be two: sale order description and its items.
                StringTokenizer orderAttributesTokenizer = new StringTokenizer(salesOrder, "|");
                // two sale order attributes
                for (int j = 0; j < 2; j++) {

                    // on the first position stays the description
                    if (j == 0 && orderAttributesTokenizer.hasMoreTokens()) {
                        salesOrderDescription = orderAttributesTokenizer.nextToken().trim();
                    // on the second position stay the items
                    } else if (j == 1 && orderAttributesTokenizer.hasMoreTokens()) {
                        String salesOrderItems = orderAttributesTokenizer.nextToken().trim();
                        matcher = excludeItemsWordPattern.matcher(salesOrderItems);
                        // exclude the useless syntax.
                        matcher.find();
                        // '*' delimits the items of a sales order.
                        StringTokenizer itemsTokenizer = new StringTokenizer(matcher.group(1).trim(), "*");

                        // for every sales order item.
                        List<PaymentItem> paymentItems= new ArrayList();
                        while (itemsTokenizer.hasMoreTokens()) {
                            // take its attributes: description, currency and amount(different groups of the pattern).
                            matcher = splitItemPattern.matcher(itemsTokenizer.nextToken().trim());
                            matcher.find();
                            PaymentItem paymentItem = new PaymentItem(matcher.group(1),new BigDecimal(matcher.group(3)),matcher.group(2));
                            paymentItems.add(paymentItem);
                        }
                        paymentDetails.put(salesOrderDescription,paymentItems);
                    }
                }
            }
        }
        notification.setPaymentItems(paymentDetails);
    }

    /**
     * If the notification is of type payment, the notification text is retrieved from CRM
     * under the form of a string consisting of several tokens which are split by a delimiter
     * and contain payment details.This is stored in notification text. After the payment details
     * are parsed, the notification text is changed by this method into something more user friendly.
     * @param notification - processed notification
     * @return - notification containing new notification text.
     */
    public void populateNotificationText(SagiaInvestorNotificationDTO notification){
		String[] salesOrders = splitTransactionsFromTransactionId(notification.getTransactionId());
		if(salesOrders.length > 1) {
        	notification.setNotificationText(messageSource.getMessage("account.notifications.paymentTitle.nonEntrepreneur", salesOrders, i18NService.getCurrentLocale()));
        }else {
        	notification.setNotificationText(messageSource.getMessage("account.notifications.paymentTitle.entrepreneur", salesOrders, i18NService.getCurrentLocale()));
        }
        //notification.setNotificationText(messageSource.getMessage("account.notifications.paymentTitle", splitTransactionsFromTransactionId(notification.getTransactionId()), i18NService.getCurrentLocale()));
    }

    /**
     * Transaction id field for a payment notification contains two transactions in it,
     * split by '|' delimiter. This method divides the text and splits the two
     * transactions.
     * @param transactionId - transaction id containing the two transactions.
     * @return  the two transactions contained in a <code>String</code> array.
     */
    public String[] splitTransactionsFromTransactionId(String transactionId){
        StringTokenizer tokenizer = new StringTokenizer(transactionId, PAYMENT_TRANSACTION_DELIMITER);
        //Assert.isTrue(tokenizer.countTokens() == 2,"Payment notification must contain 2 sales order transactions");
         if(tokenizer.countTokens() == 2) {
        	return new String[]{tokenizer.nextToken(),tokenizer.nextToken()};
        }else {
        	return new String[]{tokenizer.nextToken()};
        }
        //return new String[]{tokenizer.nextToken(),tokenizer.nextToken()};
    }

    /**
     * retrieves the notifications to be displayed in the popup
     * retrieves count will all notifications
     * @return SagiaPopupNotificationData
     */
    public SagiaPopupNotificationData getPopupNotificationData() {
    		List<SagiaInvestorNotificationDTO> allNotifications = getAllNotifications();
    		return new SagiaPopupNotificationData()
    				.setPopupNotifications(getPopupNotifications(allNotifications))
    				.setAllNotificationsCount(getNotificationCountFrom(allNotifications));
    }
    
	/**
	 * Retrieves Notifications to display on popup display first 10 new and
	 * mandatory notifications if there are no new notifications, display first 10
	 * mandatory ones if there are no mandatory notifications, display first 10 new
	 * ones if there are no mandatory and no new notifications, display first 10
	 * notifications complete each case until 10 notifications are displayed on popup
	 *
     * @param allNotifications allNotifications
	 * @return List of SagiaInvestorNotificationDTO
	 */
	private List<SagiaInvestorNotificationDTO> getPopupNotifications(List<SagiaInvestorNotificationDTO> allNotifications) {

		List<SagiaInvestorNotificationDTO> mandatoryNotifications = getMandatoryNotificationsFrom(allNotifications);
		List<SagiaInvestorNotificationDTO> newNotifications = InvestorNotificationUtils
				.getUnreadNotificationsFrom(allNotifications);
		List<SagiaInvestorNotificationDTO> newAndMandatoryNotifications = InvestorNotificationUtils
				.getUnreadNotificationsFrom(mandatoryNotifications);

		// no notification at all, return
		if (allNotifications.isEmpty()) {
			return allNotifications;
		}

		// display first 10 mandatory and new notifications
		if (newAndMandatoryNotifications.size() >= POPUP_NOTIFICATIONS_TO_DISPLAY) {
			return newAndMandatoryNotifications.subList(0, POPUP_NOTIFICATIONS_TO_DISPLAY);
		}
		// if there are less than 10, display the mandatory ones to complete
		else {
			List<SagiaInvestorNotificationDTO> notificationstoDisplay = new ArrayList<>();
			notificationstoDisplay.addAll(newAndMandatoryNotifications);
			notificationstoDisplay.addAll(newNotifications);
			notificationstoDisplay.addAll(mandatoryNotifications);
			if (notificationstoDisplay.size() >= POPUP_NOTIFICATIONS_TO_DISPLAY) {
				return notificationstoDisplay.subList(0, POPUP_NOTIFICATIONS_TO_DISPLAY);
			} else {
				// if mandatory + new notifications are less than 10, complete with rest of
				// notifications
				// remove duplicates
				notificationstoDisplay.addAll(allNotifications);
				Set<String> notifAlreadyPresent = new HashSet<>();
				notificationstoDisplay.removeIf(p -> !notifAlreadyPresent.add(p.getTransactionId()));
				// display maximum 10
				if (notificationstoDisplay.size() >= POPUP_NOTIFICATIONS_TO_DISPLAY) {
					return notificationstoDisplay.subList(0, POPUP_NOTIFICATIONS_TO_DISPLAY);
				} else {
					return notificationstoDisplay.subList(0, notificationstoDisplay.size());
				}
			}
		}
	}
    

    /**
     * retrieves NotificationBy transactionId
     *
     * @param transactionId transactionId
     * @return SagiaInvestorNotificationDTO
     */
    public SagiaInvestorNotificationDTO getNotificationBy(String transactionId) {
        SagiaInvestorNotificationDTO notificationDTO = investorNotificationsService.getNotificationById(transactionId);
        if(notificationDTO.getNotificationType().equals(NOTIFICATION_TYPE_PAYMENT))
        {
            populatePaymentDetails(notificationDTO);
            populateNotificationText(notificationDTO);
        }
        return notificationDTO;
    }

    /**
     * retrieves CRMNotificationsByType by notificationType
     *
     * @param notificationType notificationType
     * @return List of InvestorNotificationData
     */
    public List<InvestorNotificationData> getCRMNotificationsByType(String notificationType) {
        return investorNotificationsService.getCRMNotificationsByType(notificationType);
    }

    /**
     * updates Notification
     *
     * @param transactionId        transactionId
     * @param notificationToUpdate notificationToUpdate
     */
    public void updateNotification(String transactionId, SagiaInvestorNotificationDTO notificationToUpdate) {
        if(notificationToUpdate == null) {
            return;
        }
        notificationToUpdate.setTransactionId(transactionId);
        investorNotificationsService.setNotificationAsRead(notificationToUpdate);
    }

    /**
     * marks AllNotificationsAsRead
     * @param readDate readDate
     */
    public void markAllNotificationsAsRead(String readDate) {
        investorNotificationsService.markAllNotificationsAsRead(readDate);
    }

    /**
     * Retrieves NotificationCount
     *
     * @return InvestorNotificationCount
     */
    public InvestorNotificationCount getNotificationCount() {
        List<SagiaInvestorNotificationDTO> allNotifications = getAllNotifications();
        List<SagiaInvestorNotificationDTO> unreadNotifications = InvestorNotificationUtils.getUnreadNotificationsFrom(allNotifications);
        List<SagiaInvestorNotificationDTO> mandatoryNotifications = InvestorNotificationUtils.getMandatoryNotificationsFrom(allNotifications);
        return new InvestorNotificationsCountBuilder()
                .all(allNotifications.size())
                .unread(unreadNotifications.size())
                .mandatory(mandatoryNotifications.size())
                .build();
    }

    /**
     * retrieves NotificationCountFrom
     * @param allNotifications allNotifications
     * @return InvestorNotificationCount
     */
    public InvestorNotificationCount getNotificationCountFrom(List<SagiaInvestorNotificationDTO> allNotifications) {
        List<SagiaInvestorNotificationDTO> unreadNotifications = InvestorNotificationUtils.getUnreadNotificationsFrom(allNotifications);
        List<SagiaInvestorNotificationDTO> mandatoryNotifications = InvestorNotificationUtils.getMandatoryNotificationsFrom(allNotifications);
        return new InvestorNotificationsCountBuilder()
                .all(allNotifications.size())
                .unread(unreadNotifications.size())
                .mandatory(mandatoryNotifications.size())
                .build();
    }

    /**
     * @return List of SagiaInvestorNotificationDTO
     */
    public List<SagiaInvestorNotificationDTO> getMandatoryNotifications() {
        return InvestorNotificationUtils.getMandatoryNotificationsFrom(getAllNotifications());
    }
    
    /**
     * retrieves MandatoryNotificationsFrom
     * @param allNotifs allNotifs
     * @return List of SagiaInvestorNotificationDTO
     */
    public List<SagiaInvestorNotificationDTO> getMandatoryNotificationsFrom(List<SagiaInvestorNotificationDTO> allNotifs) {
        return InvestorNotificationUtils.getMandatoryNotificationsFrom(allNotifs);
    }

    /**
     * check if has Mandatory Notifications
     *
     * @return boolean
     */
    public boolean hasMandatoryNotifications() {
        return CollectionUtils.isNotEmpty(getMandatoryNotifications());
    }

    /**
     * @return InvestorNotificationsService
     */
    public InvestorNotificationsService getInvestorNotificationsService() {
        return investorNotificationsService;
    }

    /**
     * @param investorNotificationsService investorNotificationsService
     */
    @Required
    public void setInvestorNotificationsService(InvestorNotificationsService investorNotificationsService) {
        this.investorNotificationsService = investorNotificationsService;
    }

    /**
     * @return InvestorNotificationConverter
     */
    public InvestorNotificationConverter getInvestorNotificationConverter() {
        return investorNotificationConverter;
    }

    /**
     * @param investorNotificationConverter investorNotificationConverter
     */
    @Required
    public void setInvestorNotificationConverter(InvestorNotificationConverter investorNotificationConverter) {
        this.investorNotificationConverter = investorNotificationConverter;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setSagiaSalesOrderPaymentService(SagiaPaymentService sagiaPaymentService) {
        this.sagiaSalesOrderPaymentService = sagiaPaymentService;
    }
}