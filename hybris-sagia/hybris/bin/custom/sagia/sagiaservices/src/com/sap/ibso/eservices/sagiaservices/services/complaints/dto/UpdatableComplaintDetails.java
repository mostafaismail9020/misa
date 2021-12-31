package com.sap.ibso.eservices.sagiaservices.services.complaints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class UpdatableComplaintDetails {

    @JsonProperty("TextMsg")
    private String textMsg = "";

    /**
     * @return
     */
    public String getTextMsg() {
        return textMsg;
    }

    /**
     * @param textMsg
     * @return
     */
    public UpdatableComplaintDetails setTextMsg(String textMsg) {
        this.textMsg = textMsg;
        return this;
    }
}
