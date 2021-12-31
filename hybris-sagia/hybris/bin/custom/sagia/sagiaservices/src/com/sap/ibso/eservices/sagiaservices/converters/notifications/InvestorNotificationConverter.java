package com.sap.ibso.eservices.sagiaservices.converters.notifications;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Required;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;

/**
 *
 */
public class InvestorNotificationConverter {

	private SagiaFormatProvider sagiaFormatProvider;

	/**
	 * @param investorNotificationData
	 * @param readDate
	 * @return
	 */
	public SagiaInvestorNotificationDTO from(InvestorNotificationData investorNotificationData, String readDate) {
		return new SagiaInvestorNotificationDTO()
				.setInvestorId(investorNotificationData.getInvestorId())
				.setTransactionId(investorNotificationData.getTransactionId())
				.setCreatedBy(investorNotificationData.getCreatedBy())
				.setCreatedOn(sagiaFormatProvider.getLocalizedDateData(investorNotificationData.getCreatedOn()))
				.setExpiryDate(sagiaFormatProvider.getLocalizedDateData(investorNotificationData.getExpiryDate()))
				.setNotificationPriority(investorNotificationData.getNotificationPriority())
				.setNotificationText(investorNotificationData.getNotificationText())
				.setNotificationType(investorNotificationData.getNotificationType())
				.setAmountPayable(investorNotificationData.getAmountPayable())
				.setReadDate(readDate);
	}

	/**
	 * @param investorNotificationData
	 * @return
	 */
	public SagiaInvestorNotificationModel toModel(SagiaInvestorNotificationDTO investorNotificationData) {
		SagiaInvestorNotificationModel notificationModel = new SagiaInvestorNotificationModel();
		notificationModel.setInvestorId(investorNotificationData.getInvestorId());
		notificationModel.setTransactionId(investorNotificationData.getTransactionId());
		notificationModel.setRead(investorNotificationData.getReadDate());
		return notificationModel;
	}

	/**
	 * @param crmNotifications
	 * @param notificationsFromHybris
	 * @return
	 */
	public List<SagiaInvestorNotificationDTO> expandNotificationsWith(
			Collection<InvestorNotificationData> crmNotifications,
			List<SagiaInvestorNotificationModel> notificationsFromHybris) {

		Map<InvestorNotificationData, String> map = new HashMap<>();
		crmNotifications.stream().forEach(crmNotif -> 
			map.put(crmNotif, getReadDateFrom(notificationsFromHybris, crmNotif))
		);
		return map.entrySet().stream()
				.map(entry -> from(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	private String getReadDateFrom(List<SagiaInvestorNotificationModel> notificationsFromHybris,
			InvestorNotificationData crmNotif) {
		return notificationsFromHybris
				.stream()
				.filter(notif -> notif.getTransactionId().equals(crmNotif.getTransactionId()))
				.findFirst()
				.map(SagiaInvestorNotificationModel::getRead)
				.orElse(null);
	}

	/**
	 * @return
	 */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

	/**
	 * @param sagiaFormatProvider
	 */
    @Required
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
