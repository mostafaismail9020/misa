package com.sap.ibso.eservices.sagiaservices.services.email;

import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailGenerationService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.processengine.model.BusinessProcessModel;

import java.util.ArrayList;
import java.util.List;

/**
 * SagiaEmailGenerationService
 */
public class SagiaEmailGenerationService extends DefaultEmailGenerationService {

    private static final String OLD_EMAIL = "oldEmail";
    //This attribute can be used if generated email have to send multiple recipients

    @Override
    protected EmailMessageModel createEmailMessage(String emailSubject, String emailBody, AbstractEmailContext<BusinessProcessModel> emailContext) {
        final List<EmailAddressModel> toEmails = new ArrayList<>();
        final EmailAddressModel toAddress = getEmailService().getOrCreateEmailAddressForEmail(emailContext.getToEmail(),
                emailContext.getToDisplayName());
        if(emailContext.get(OLD_EMAIL) != null){
            String oldEmail = (String) emailContext.get(OLD_EMAIL);
            EmailAddressModel toOldAddress = getEmailService().getOrCreateEmailAddressForEmail(oldEmail,
                    emailContext.getToDisplayName());
            toEmails.add(toOldAddress);
        }
        if(emailContext.get(SagiaCoreConstants.TO_EMAIL_LIST) != null &&  !((List<String>) emailContext.get(SagiaCoreConstants.TO_EMAIL_LIST)).isEmpty()){
            List<String> approverList = (List<String>) emailContext.get(SagiaCoreConstants.TO_EMAIL_LIST);
            for(String emailAddress:approverList) {
                EmailAddressModel emailAddressModel = getEmailService().getOrCreateEmailAddressForEmail(emailAddress,
                        emailAddress);
                toEmails.add(emailAddressModel);
            }
        }
        toEmails.add(toAddress);
        final EmailAddressModel fromAddress = getEmailService().getOrCreateEmailAddressForEmail(emailContext.getFromEmail(),
                emailContext.getFromDisplayName());
        return getEmailService().createEmailMessage(toEmails, new ArrayList<>(),
                new ArrayList<>(), fromAddress, emailContext.getFromEmail(), emailSubject, emailBody, null);
    }
}
