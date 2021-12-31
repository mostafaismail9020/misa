package com.sap.ibso.eservices.sagiaservices.services.notifications.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationCount;

public class SagiaPopupNotificationData {

	@JsonProperty("popupNotifs")
	List<SagiaInvestorNotificationDTO> popupNotifications;
	@JsonProperty("allNotifsCount")
	InvestorNotificationCount allNotificationsCount;
	
	public List<SagiaInvestorNotificationDTO> getPopupNotifications() {
		return popupNotifications;
	}
	public SagiaPopupNotificationData setPopupNotifications(List<SagiaInvestorNotificationDTO> popupNotifications) {
		this.popupNotifications = popupNotifications;
		return this;
	}
	public InvestorNotificationCount getAllNotificationsCount() {
		return allNotificationsCount;
	}
	public SagiaPopupNotificationData setAllNotificationsCount(InvestorNotificationCount allNotificationsCount) {
		this.allNotificationsCount = allNotificationsCount;
		return this;
	}
}
