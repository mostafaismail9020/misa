package com.sap.ibso.eservices.storefront.forms;

import javax.validation.constraints.NotNull;

public class SagiaUpdateUsernameForm {

    @NotNull
    private String username;
    private String checkUsername;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCheckUsername() {
        return checkUsername;
    }

    public void setCheckUsername(String checkUsername) {
        this.checkUsername = checkUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
