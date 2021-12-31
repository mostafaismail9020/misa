package com.sap.ibso.eservices.sagiaservices.services.notifications;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class InvestorNotificationsCountBuilder {

	private static final String ALL = "all";
	private static final String UNREAD = "unread";
	private static final String MANDATORY = "mandatory";
	private Map<String, Integer> countOptions = new HashMap<>();

	/**
	 * @param allNotificationsSize
	 * @return
	 */
	public InvestorNotificationsCountBuilder all(Integer allNotificationsSize) {
		this.countOptions.put(ALL, allNotificationsSize);
		return this;
	}

	/**
	 * @param unreadNotificationsSize
	 * @return
	 */
	public InvestorNotificationsCountBuilder unread(Integer unreadNotificationsSize) {
		this.countOptions.put(UNREAD, unreadNotificationsSize);
		return this;
	}

	/**
	 * @param mandatoryNotificationsSize
	 * @return
	 */
	public InvestorNotificationsCountBuilder mandatory(Integer mandatoryNotificationsSize) {
		this.countOptions.put(MANDATORY, mandatoryNotificationsSize);
		return this;
	}

	/**
	 * @return
	 */
	public InvestorNotificationCount build() {
		return new InvestorNotificationCount()
				.setAllNotificationsSize(this.countOptions.get(ALL))
				.setUnreadNotificationsSize(this.countOptions.get(UNREAD))
				.setMandatoryNotificationsSize(this.countOptions.get(MANDATORY));
	}
}

