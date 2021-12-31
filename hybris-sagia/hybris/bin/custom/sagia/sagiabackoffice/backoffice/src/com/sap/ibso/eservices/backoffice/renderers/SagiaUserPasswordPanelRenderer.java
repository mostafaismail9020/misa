package com.sap.ibso.eservices.backoffice.renderers;

import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.sap.ibso.eservices.core.sagia.services.SagiaConfigurationService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.platformbackoffice.renderers.UserPasswordPanelRenderer;
import de.hybris.platform.servicelayer.user.PasswordPolicyService;
import de.hybris.platform.servicelayer.user.PasswordPolicyViolation;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SagiaUserPasswordPanelRenderer extends  UserPasswordPanelRenderer{

    private static final String HMC_PASS_DONOTMATCH = "hmc.passwordsdonotmatch";
    private static final String HMC_PASS_NOTVALID = "hmc.password.notvalid";

    private SagiaConfigurationService sagiaConfigurationService;
    private UserService userService;
    private PasswordPolicyService passwordPolicyService;

    @Override
    protected EventListener<InputEvent> createPasswordChangeListener(UserModel user, Textbox passwordTextbox, Textbox passwordConfirmationTextbox, Combobox encryptionChooser, Label messageLabel, WidgetInstanceManager widgetInstanceManager) {
        return event -> {
            String password = passwordTextbox.getText();
            if (password != null && !this.isValidPassword(password)) {
                setMessageLabel(messageLabel, HMC_PASS_NOTVALID);
            } else {
                if (password.equals(passwordConfirmationTextbox.getText())) {
                    messageLabel.setVisible(false);
                    Comboitem selectedItem = encryptionChooser.getSelectedItem();
                    if (selectedItem == null) {
                        return;
                    }
                    setPass(user, messageLabel, widgetInstanceManager, password, selectedItem);
                } else {
                    setMessageLabel(messageLabel, HMC_PASS_DONOTMATCH);
                }
            }
        };
    }

    private void setPass(UserModel user, Label messageLabel, WidgetInstanceManager widgetInstanceManager, String password, Comboitem selectedItem) {
        List<PasswordPolicyViolation> passwordPolicyViolations = this.getPasswordPolicyService().verifyPassword(user, password, selectedItem.getValue());
        this.renderPasswordPolicyViolations(messageLabel, passwordPolicyViolations);
        UserModel refreshedCurrentUser = this.getRefreshedCurrentUser(user, widgetInstanceManager);
        if (passwordPolicyViolations.isEmpty() && refreshedCurrentUser != null) {
            this.userService.setPassword(refreshedCurrentUser, password, selectedItem.getValue());
            widgetInstanceManager.getModel().changed();
        }
    }

    private void setMessageLabel(Label messageLabel, String hmcPassNotvalid) {
        messageLabel.setValue(Labels.getLabel(hmcPassNotvalid));
        messageLabel.setVisible(true);
    }

    private UserModel getRefreshedCurrentUser(UserModel renderedUser, WidgetInstanceManager wim) {
        UserModel current = (UserModel)wim.getModel().getValue("currentObject", UserModel.class);
        if (current != null) {
            return Objects.equals(current, renderedUser) ? current : null;
        } else {
            return renderedUser;
        }
    }

    private void renderPasswordPolicyViolations(Label messageLabel, List<PasswordPolicyViolation> violations) {
        StringBuilder sb = new StringBuilder();
        Iterator violationsIterator = violations.iterator();

        while(violationsIterator.hasNext()) {
            PasswordPolicyViolation violation = (PasswordPolicyViolation)violationsIterator.next();
            sb.append(violation.getLocalizedMessage() + "\n");
        }

        messageLabel.setValue(sb.toString());
        messageLabel.setVisible(true);
    }

    private boolean isValidPassword(String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            return false;
        }
        String backendRegex = getSagiaConfigurationService().getPasswordRule();
        if (backendRegex != null && !backendRegex.trim().isEmpty()) {
            Pattern pattern = Pattern.compile(backendRegex);
            Matcher matcher = pattern.matcher(pwd);
            if (!matcher.matches()) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public SagiaConfigurationService getSagiaConfigurationService() {
        return sagiaConfigurationService;
    }

    public void setSagiaConfigurationService(SagiaConfigurationService sagiaConfigurationService) {
        this.sagiaConfigurationService = sagiaConfigurationService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public PasswordPolicyService getPasswordPolicyService() {
        return passwordPolicyService;
    }

    @Override
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
        this.passwordPolicyService = passwordPolicyService;
    }

}
