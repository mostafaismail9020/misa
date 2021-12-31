package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaInvestorNotificationDAO;
import com.sap.ibso.eservices.core.sagia.exception.SagiaInconsistentHybrisNotificationData;
import com.sap.ibso.eservices.core.sagia.exception.SagiaItemNotFoundException;
import com.sap.ibso.eservices.core.sagia.services.SagiaHybrisInvestorNotificationService;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of Sagia Hybris Investor Notification
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaHybrisInvestorNotificationService extends AbstractBusinessService implements SagiaHybrisInvestorNotificationService {

    private transient SagiaInvestorNotificationDAO sagiaInvestorNotificationDAO;
	private transient ModelService modelService;//NOSONAR

	public SagiaInvestorNotificationDAO getSagiaInvestorNotificationDAO() {
        return sagiaInvestorNotificationDAO;
    }

    public void setSagiaInvestorNotificationDAO(SagiaInvestorNotificationDAO sagiaInvestorNotificationDAO) {
        this.sagiaInvestorNotificationDAO = sagiaInvestorNotificationDAO;
    }
    
    @Override
    public ModelService getModelService() {
		return modelService;
	}
	
	@Override
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	@Override
	public List<SagiaInvestorNotificationModel> getNotificationsFor(String investorId) {
        return sagiaInvestorNotificationDAO.getNotificationsFor(investorId);
	}
	
	@Override
	public SagiaInvestorNotificationModel getNotificationBy(String investorId, String transactionId) {
		return sagiaInvestorNotificationDAO.getNotificationBy(investorId, transactionId)
										  .orElse(null);
	}
	
	@Override
	public List<SagiaInvestorNotificationModel> syncAndGetHybrisNotifications(String investorId, Collection<InvestorNotificationData> crmNotificationList) {

		final List<SagiaInvestorNotificationModel> hybrisNotifications = sagiaInvestorNotificationDAO.getNotificationsFor(investorId);
		final List<InvestorNotificationData> crmNotifications = new ArrayList<>(crmNotificationList);
		//get notifications that are in CRM but not in hybris
		List<InvestorNotificationData> newNotifications = filter(investorId, crmNotifications, hybrisNotifications);
		//add in hybris the new notifications
		newNotifications.stream()
		 				.map(this::createInvestorModelFrom )
		 			    .forEach(this::addNotificationInHybrisTable);
		//remove notifications from hybris table that are not in CRM
		deleteExtraNotificationsFromHybrisTable(investorId, crmNotifications);
		
		final List<SagiaInvestorNotificationModel> finalHybrisNotifList = sagiaInvestorNotificationDAO.getNotificationsFor(investorId);
		//last check that the hybris notifications are 1:1 with CRM notifications
		validateSynchronizationOfNotifications(investorId, crmNotifications, finalHybrisNotifList);
		return finalHybrisNotifList;
	}

	private void validateSynchronizationOfNotifications(String currentInvestorId, List<InvestorNotificationData> crmNotifications, List<SagiaInvestorNotificationModel> finalHybrisNotifList) {

		boolean investorIdInconsistency = crmNotifications
				.stream()
				.anyMatch(crmNotif -> !crmNotif.getInvestorId().equals(currentInvestorId));
		if(investorIdInconsistency) {
			throw new SagiaInconsistentHybrisNotificationData(
					"Notifications retrieved from CRM for " + currentInvestorId + " have invalid investorId values");
		}
		if(finalHybrisNotifList.size() != crmNotifications.size()) {
			throw new SagiaInconsistentHybrisNotificationData(
					"The CRM notifications for " + currentInvestorId + " do not match the hybris notifications for the same investor");
		}
	}

	private void deleteExtraNotificationsFromHybrisTable(String investorId,
			final List<InvestorNotificationData> crmNotifications) {
		final List<SagiaInvestorNotificationModel> updatedHybrisNotifs = sagiaInvestorNotificationDAO.getNotificationsFor(investorId);
		Set<String> crmTransactionIds = crmNotifications
				.stream()
                .map(InvestorNotificationData::getTransactionId)
                .collect(Collectors.toSet());
		ArrayList<SagiaInvestorNotificationModel> extraInHybris = new ArrayList<>(updatedHybrisNotifs);
		extraInHybris.removeIf(notif -> crmTransactionIds.contains(notif.getTransactionId()));
		extraInHybris.stream().forEach(this::deleteNotificationFromHybrisTable);
	}

	private void deleteNotificationFromHybrisTable(SagiaInvestorNotificationModel extraNotif) {
		getModelService().remove(extraNotif);
	}

	private void addNotificationInHybrisTable(SagiaInvestorNotificationModel newModel) {
		getModelService().save(newModel);
	}
	
	private SagiaInvestorNotificationModel createInvestorModelFrom(InvestorNotificationData crmNotification) {
		SagiaInvestorNotificationModel newModel = modelService.create(SagiaInvestorNotificationModel.class);
		newModel.setInvestorId(crmNotification.getInvestorId());
		newModel.setTransactionId(crmNotification.getTransactionId());
		return newModel;
	}

	/**
	 * hybris table has as primary key investorId + transactionId
	 * make sure that the notifications that come from CRM are for the current investorId
	 * @param crmNotifications
	 * @param hybrisNotifications
	 * @return notifications that are in CRM but not in hybris for the current investorId
	 */
	private List<InvestorNotificationData> filter(String currentInvestorId,
			List<InvestorNotificationData> crmNotifications, List<SagiaInvestorNotificationModel> hybrisNotifications) {
		Set<String> hybrisTransactionIds = hybrisNotifications.stream()
				.map(SagiaInvestorNotificationModel::getTransactionId).collect(Collectors.toSet());

		return crmNotifications.stream()
				.filter(crmNotification -> crmNotification.getInvestorId().equals(currentInvestorId))
				.filter(crmNotification -> !hybrisTransactionIds.contains(crmNotification.getTransactionId()))
				.collect(Collectors.toList());
	}


	@Override
	public void updateReadDateFor(SagiaInvestorNotificationModel notificationToUpdate) {
		
		SagiaInvestorNotificationModel hybrisNotification = sagiaInvestorNotificationDAO
				.getNotificationBy(
						notificationToUpdate.getInvestorId(), notificationToUpdate.getTransactionId())
				.orElse(null);
		if(Objects.isNull(hybrisNotification)) {
			throw new SagiaItemNotFoundException("Notification " +  notificationToUpdate.getTransactionId() + " was not found in hybris!");
		}
		 updateNotification(hybrisNotification, notificationToUpdate.getRead());
	}
	
	@Override
	public void markAllNotificationsAsReadFor(String investorId, String readDate) {
		List<SagiaInvestorNotificationModel> hybrisNotifications = sagiaInvestorNotificationDAO
				.getNotificationsFor(investorId);
		hybrisNotifications.stream()
						   .forEach(hybrisNotif -> hybrisNotif.setRead(readDate));
		getModelService().saveAll(hybrisNotifications);
	}
	
	private void updateNotification(SagiaInvestorNotificationModel notification, String readDate) {
		getModelService().refresh(notification);
		notification.setRead(readDate);
		addNotificationInHybrisTable(notification);
	}

}
