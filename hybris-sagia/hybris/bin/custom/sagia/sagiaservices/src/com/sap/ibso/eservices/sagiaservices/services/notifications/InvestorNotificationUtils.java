package com.sap.ibso.eservices.sagiaservices.services.notifications;

import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * InvestorNotificationUtils
 */
public class InvestorNotificationUtils {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractSagiaService.class);
	private static final String PRIORITY_MANDATORY = "M";

	private InvestorNotificationUtils() {}

	/**
	 * checks if isMandatory
	 * @param notification notification
	 * @return boolean
	 */
	public static boolean isMandatory(SagiaInvestorNotificationDTO notification) {
		if(notification == null) {
			return false;
		}
		String notificationPriority = notification.getNotificationPriority();
		return Objects.nonNull(notificationPriority) && PRIORITY_MANDATORY.equalsIgnoreCase(notification.getNotificationPriority());
	}

	/**
	 * retrieves MandatoryNotificationsFrom
	 * @param notifications notifications
	 * @return List of SagiaInvestorNotificationDTO
	 */
	public static List<SagiaInvestorNotificationDTO> getMandatoryNotificationsFrom(
			List<SagiaInvestorNotificationDTO> notifications) {
		try {
			return notifications
					.stream()
					.filter(Objects::nonNull)
					.filter(InvestorNotificationUtils::isMandatory)
					.collect(Collectors.toList());
		} catch(Exception e) {
			LOG.warn(e.getMessage(),e);
			return Collections.emptyList();
		}
	}

	/**
	 * retrieves UnreadNotificationsFrom
	 * @param notifications notifications
	 * @return List of SagiaInvestorNotificationDTO
	 */
	public static List<SagiaInvestorNotificationDTO> getUnreadNotificationsFrom(
			List<SagiaInvestorNotificationDTO> notifications) {
		try {
			return notifications
					.stream()
					.filter(notif -> StringUtils.isBlank(notif.getReadDate()))
					.collect(Collectors.toList());
		} catch(Exception e) {
			LOG.warn(e.getMessage(),e);
			return Collections.emptyList();
		}
	}
}
