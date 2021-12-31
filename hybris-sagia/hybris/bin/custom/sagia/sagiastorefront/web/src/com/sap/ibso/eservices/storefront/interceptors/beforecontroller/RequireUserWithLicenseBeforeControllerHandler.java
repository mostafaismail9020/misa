package com.sap.ibso.eservices.storefront.interceptors.beforecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.servicelayer.i18n.I18NService;
import com.sap.ibso.eservices.storefront.exceptions.SagiaUserWithoutLicenseException;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;

@Component
public class RequireUserWithLicenseBeforeControllerHandler implements HandlerInterceptor {
	private static final Logger LOG = Logger.getLogger(RequireUserWithLicenseBeforeControllerHandler.class);

    @Autowired
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private I18NService i18nService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String currentUser = request.getRemoteUser();
		LicenseRequired licenseRequired = handlerMethod.getMethod().getAnnotation(LicenseRequired.class);
		if (licenseRequired == null) {
			return true;
		}
		if (!sagiaDashboardWithoutLicenseFacade.hasLicense()) {
			LOG.debug(currentUser + " has to be licensed to access: " + request.getRequestURI());
			throw new SagiaUserWithoutLicenseException(getLocalizedError("general.license.required"));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//Do nothing
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//Do nothing
	}
	
    private String getLocalizedError(String key) {
        return messageSource.getMessage(key, null, i18nService.getCurrentLocale());
    }

}
