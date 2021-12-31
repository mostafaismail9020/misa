package com.sap.ibso.eservices.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdatePwdForm;

import javax.validation.constraints.NotNull;

public class SagiaUpdatePwdForm extends UpdatePwdForm {
    private String oldPwd;

    @NotNull(message = "{updatePwd.oldPwd.invalid}")
    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
