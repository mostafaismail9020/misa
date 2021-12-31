package com.sap.ibso.eservices.storefront.forms.translator;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import de.hybris.platform.servicelayer.i18n.I18NService;

/**
 * StorefrontLozalizedMessageTranslator
 */
@Component("storefrontLozalizedMessageTranslator")
public class StorefrontLozalizedMessageTranslator {

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;
    @Resource(name = "i18NService")
    private I18NService i18NService;
	private static final Logger LOG = LoggerFactory.getLogger(StorefrontLozalizedMessageTranslator.class);

	/**
	 * retrieves LocalizedMessageValue
	 * @param messageKey  messageKey
	 * @return String
	 */
	public String getLocalizedMessageValue(String messageKey) {
		Locale currentLocale = i18NService.getCurrentLocale();
		return getMessageValueFor(messageKey, currentLocale);
	}

	/**
	 * retrieves MessageValueFor
	 * 
	 * @param messageKey messageKey
	 * @param locale locale
	 * @return String
	 */
	public String getMessageValueFor(String messageKey, Locale locale) {
		try {
			return messageSource.getMessage(messageKey, null, locale);
		} catch (Exception ex) {
			LOG.debug("Could not read translation for key: " + messageKey, ex);
			return messageKey;
		}
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setI18NService(I18NService i18NService) {
		this.i18NService = i18NService;
	}
}
