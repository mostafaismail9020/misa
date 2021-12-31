package com.sap.ibso.eservices.sagiaservices.services.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class InvestorNotificationCount {

    @JsonProperty("all")
    private Integer allNotificationsSize;
    @JsonProperty("unread")
    private Integer unreadNotificationsSize;
    @JsonProperty("mandatory")
    private Integer mandatoryNotificationsSize;

    /**
     * @return
     */
    public Integer getAllNotificationsSize() {
        return allNotificationsSize;
    }

    /**
     * @param allNotificationsSize
     * @return
     */
    public InvestorNotificationCount setAllNotificationsSize(Integer allNotificationsSize) {
        this.allNotificationsSize = allNotificationsSize;
        return this;
    }

    /**
     * @return
     */
    public Integer getUnreadNotificationsSize() {
        return unreadNotificationsSize;
    }

    /**
     * @param unreadNotificationsSize
     * @return
     */
    public InvestorNotificationCount setUnreadNotificationsSize(Integer unreadNotificationsSize) {
        this.unreadNotificationsSize = unreadNotificationsSize;
        return this;
    }

    /**
     * @return
     */
    public Integer getMandatoryNotificationsSize() {
        return mandatoryNotificationsSize;
    }

    public InvestorNotificationCount setMandatoryNotificationsSize(Integer mandatoryNotificationsSize) {
        this.mandatoryNotificationsSize = mandatoryNotificationsSize;
        return this;
    }

}
