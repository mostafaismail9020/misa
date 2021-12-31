package com.sap.ibso.eservices.sagiaservices.services.notifications;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaHybrisInvestorNotificationService;
import com.sap.ibso.eservices.sagiaservices.converters.notifications.InvestorNotificationConverter;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * InvestorNotificationsService
 */
public class InvestorNotificationsService {

    private static final Logger LOGGER = Logger.getLogger(InvestorNotificationsService.class);

    private InvestorMappingService investorMappingService;
    private InvestorNotificationConverter investorNotificationConverter;
    private SagiaHybrisInvestorNotificationService sagiaHybrisInvestorNotificationService;
    private SagiaCRMInvestorNotificationsService sagiaCRMInvestorNotificationsService;

    /**
     * retrieves SyncedHybrisNotifications
     *
     * @param currentInvestorId currentInvestorId
     * @param crmNotifications  crmNotifications
     * @return List of SagiaInvestorNotificationModel
     */
    private List<SagiaInvestorNotificationModel> getSyncedHybrisNotifications(String currentInvestorId,
                                                                              List<InvestorNotificationData> crmNotifications) {
        return sagiaHybrisInvestorNotificationService.syncAndGetHybrisNotifications(currentInvestorId,
                crmNotifications);
    }

    /**
     * retrieves AllExpandedNotifications
     *
     * @return List of SagiaInvestorNotificationDTO
     */
    public List<SagiaInvestorNotificationDTO> getAllExpandedNotifications() {
        try {
            String currentInvestorId = investorMappingService.getEntityId();
            validateInvestorId(currentInvestorId);
            //STEP 1: read all notifications from CRM
            List<InvestorNotificationData> crmNotifications = getCrmNotificationsFor(currentInvestorId);

            //STEP 2: synchronize notifications in hybris and retrieve them
            List<SagiaInvestorNotificationModel> syncedHybrisNotifications = getSyncedHybrisNotifications(currentInvestorId, crmNotifications);

            //STEP 3: create expanded notifications from CRM Notifications + hybris Notifications
            return investorNotificationConverter.expandNotificationsWith(crmNotifications, syncedHybrisNotifications);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * retrieves SagiaInvestorNotificationDTO by id
     *
     * @param transactionId transactionId
     * @return SagiaInvestorNotificationDTO
     */
    public SagiaInvestorNotificationDTO getNotificationById(String transactionId) {
        try {
            SagiaInvestorNotificationModel notificationFromHybris = getHybrisNotificationBy(transactionId);
            InvestorNotificationData crmNotification = getCRMNotificationBy(transactionId);
            return investorNotificationConverter.from(crmNotification, notificationFromHybris.getRead());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * retrieves CRMNotificationBy By Type
     *
     * @param notificationType notificationType
     * @return List of InvestorNotificationData
     */
    public List<InvestorNotificationData> getCRMNotificationsByType(String notificationType) {
        try {
            String currentInvestorId = investorMappingService.getEntityId();
            validateInvestorId(currentInvestorId);
            return getCRMNotificationsByType(currentInvestorId, notificationType);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * retrieves CRMNotificationBy transactionId
     *
     * @param transactionId transactionId
     * @return InvestorNotificationData
     */
    public InvestorNotificationData getCRMNotificationBy(String transactionId) {
        String currentInvestorId = investorMappingService.getEntityId();
        validateInvestorId(currentInvestorId);
        validateTransactionId(transactionId);
        return sagiaCRMInvestorNotificationsService.getNotificationBy(currentInvestorId, transactionId);
    }

    /**
     * retrieves CRMNotificationsByType
     *
     * @param currentInvestorId currentInvestorId
     * @param notificationType  notificationType
     * @return List of InvestorNotificationData
     */
    public List<InvestorNotificationData> getCRMNotificationsByType(String currentInvestorId, String notificationType) {
        return sagiaCRMInvestorNotificationsService.getNotificationsByType(currentInvestorId, notificationType);
    }

    /**
     * retrieves HybrisNotificationsForCurrentUser
     *
     * @return List of SagiaInvestorNotificationModel
     */
    public List<SagiaInvestorNotificationModel> getHybrisNotificationsForCurrentUser() {
        String currentInvestorId = investorMappingService.getEntityId();
        validateInvestorId(currentInvestorId);
        return sagiaHybrisInvestorNotificationService.getNotificationsFor(currentInvestorId);
    }

    /**
     * retrieves HybrisNotificationBy
     *
     * @param transactionId transactionId
     * @return SagiaInvestorNotificationModel
     */
    public SagiaInvestorNotificationModel getHybrisNotificationBy(String transactionId) {
        String currentInvestorId = investorMappingService.getEntityId();
        validateTransactionId(transactionId);
        validateInvestorId(currentInvestorId);
        return sagiaHybrisInvestorNotificationService.getNotificationBy(currentInvestorId, transactionId);
    }

    /**
     * sets NotificationAsRead
     *
     * @param notificationToUpdate notificationToUpdate
     */
    public void setNotificationAsRead(SagiaInvestorNotificationDTO notificationToUpdate) {
        String currentInvestorId = investorMappingService.getEntityId();
        validateInvestorId(currentInvestorId);
        if (Objects.isNull(notificationToUpdate)) {
            throw new IllegalArgumentException("notification to update must not be null");
        }
        SagiaInvestorNotificationModel notificationModel = investorNotificationConverter.toModel(
                notificationToUpdate);
        notificationModel.setInvestorId(currentInvestorId);
        sagiaHybrisInvestorNotificationService.updateReadDateFor(notificationModel);
    }


    /**
     * marks AllNotificationsAsRead
     *
     * @param readDate readDate
     */
    public void markAllNotificationsAsRead(String readDate) {
        String currentInvestorId = investorMappingService.getEntityId();
        validateInvestorId(currentInvestorId);
        sagiaHybrisInvestorNotificationService.markAllNotificationsAsReadFor(currentInvestorId, readDate);
    }

    /**
     * retrieves CrmNotificationsFor
     *
     * @param investorId investorId
     * @return List of InvestorNotificationData
     */
    private List<InvestorNotificationData> getCrmNotificationsFor(String investorId) {
        return sagiaCRMInvestorNotificationsService.getNotificationsFor(investorId);
    }

    /**
     * @param investorId
     */
    private void validateInvestorId(String investorId) {
        Assert.hasLength(investorId, "Investor identifier must not be null");
    }

    /**
     * @param transactionId
     */
    private void validateTransactionId(String transactionId) {
        Assert.hasLength(transactionId, "Transaction identifier must not be null");
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }

    /**
     * @return
     */
    public SagiaHybrisInvestorNotificationService getSagiaHybrisInvestorNotificationService() {
        return sagiaHybrisInvestorNotificationService;
    }

    /**
     * @param sagiaHybrisInvestorNotificationService
     */
    @Required
    public void setSagiaHybrisInvestorNotificationService(
            SagiaHybrisInvestorNotificationService sagiaHybrisInvestorNotificationService) {
        this.sagiaHybrisInvestorNotificationService = sagiaHybrisInvestorNotificationService;
    }

    /**
     * @return
     */
    public SagiaCRMInvestorNotificationsService getSagiaCRMInvestorNotificationsService() {
        return sagiaCRMInvestorNotificationsService;
    }

    /**
     * @param sagiaCRMInvestorNotificationsService
     */
    @Required
    public void setSagiaCRMInvestorNotificationsService(SagiaCRMInvestorNotificationsService sagiaCRMInvestorNotificationsService) {
        this.sagiaCRMInvestorNotificationsService = sagiaCRMInvestorNotificationsService;
    }

    /**
     * @return
     */
    public InvestorNotificationConverter getInvestorNotificationConverter() {
        return investorNotificationConverter;
    }

    /**
     * @param investorNotificationConverter
     */
    @Required
    public void setInvestorNotificationConverter(InvestorNotificationConverter investorNotificationConverter) {
        this.investorNotificationConverter = investorNotificationConverter;
    }
}
