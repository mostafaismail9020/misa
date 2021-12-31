package com.sap.ibso.eservices.storefront.controllers.pages.test;

import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.email.actions.SendEmailAction;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ui")
public class UiTestController extends AbstractPageController {
    private static final String SAGIA_UI_TEST = "ui-test";

    @Resource(name = "sendEmail")
    private SendEmailAction sendEmailAction;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_UI_TEST));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_UI_TEST));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public void sendEmail(@RequestBody Map<String, String> payload) {
        BusinessProcessModel model = new BusinessProcessModel();
        model.setCode("MCM_EMAIL_PROCESS");
        List<EmailMessageModel> emails = new ArrayList<>();
        EmailMessageModel emailModel = new EmailMessageModel();

        EmailAddressModel fromAddress = new EmailAddressModel();
        fromAddress.setEmailAddress(payload.get("from"));
        emailModel.setFromAddress(fromAddress);

        List<EmailAddressModel> toEmails = new ArrayList<>();
        EmailAddressModel toEmail = new EmailAddressModel();
        toEmail.setEmailAddress(payload.get("to"));
        toEmails.add(toEmail);
        emailModel.setToAddresses(toEmails);

        emailModel.setSubject(payload.get("subject"));
        emailModel.setBody(payload.get("message"));
        emailModel.setProcess(model);
        emails.add(emailModel);
        model.setEmails(emails);
        sendEmailAction.executeAction(model);
        if(!emailModel.isSent()) {
            throw new SagiaRuntimeException("failed to send email");
        }
    }
}
