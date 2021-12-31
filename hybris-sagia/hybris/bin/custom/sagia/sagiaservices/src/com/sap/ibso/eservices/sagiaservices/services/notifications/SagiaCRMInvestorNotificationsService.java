package com.sap.ibso.eservices.sagiaservices.services.notifications;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SagiaCRMInvestorNotificationsService
 */
public class SagiaCRMInvestorNotificationsService extends AbstractSagiaService<InvestorNotificationData> {

	public static final String INVESTOR_ID_EQ = "InvestorId eq ";

	/**
	 * retrieves InvestorNotificationData For investorId
	 * @param investorId investorId
	 * @return List of InvestorNotificationData
	 */
	public final List<InvestorNotificationData> getNotificationsFor(String investorId) {
		String filterExpression = INVESTOR_ID_EQ + "'" + investorId + "'";
		Map<String, String> queryOptions = new QueryOptionsBuilder()
										.filter(filterExpression)
										.build();
		return getCollection(InvestorNotificationData.class, queryOptions)
				.stream()
				.collect(Collectors.toList());
	}

	/**
	 * retrieves InvestorNotificationData by investorId and transactionId
	 * @param investorId investorId
	 * @param transactionId transactionId
	 * @return InvestorNotificationData
	 */
	public final InvestorNotificationData getNotificationBy(String investorId, String transactionId ) {

		String filterExpression = INVESTOR_ID_EQ + "'" + investorId    + "'"
								+ " and " +
								 "TransactionId eq " + "'" + transactionId + "'";
		Map<String, String> queryOptions = new QueryOptionsBuilder()
										.filter(filterExpression)
										.build();
		return getCollection(InvestorNotificationData.class, queryOptions)
					.stream()
					.findFirst()
					.orElse(null);
	}

	/**
	 * retrieves InvestorNotificationData by type
	 * @param investorId investorId
	 * @param notificationType notificationType
	 * @return List of InvestorNotificationData
	 */
	public final List<InvestorNotificationData> getNotificationsByType(String investorId, String notificationType ) {

		String filterExpression = INVESTOR_ID_EQ + "'" + investorId    + "'"
				+ " and " +
				"NotificationType eq " + "'" + notificationType + "'";
		Map<String, String> queryOptions = new QueryOptionsBuilder()
				.filter(filterExpression)
				.build();
		return getCollection(InvestorNotificationData.class, queryOptions)
				.stream()
				.collect(Collectors.toList());
	}

}
