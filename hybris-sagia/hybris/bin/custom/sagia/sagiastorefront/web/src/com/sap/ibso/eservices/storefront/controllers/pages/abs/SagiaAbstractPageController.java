package com.sap.ibso.eservices.storefront.controllers.pages.abs;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialData;
import com.sap.ibso.eservices.facades.sagia.SagiaTutorialFacade;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SagiaAbstractPageController extends AbstractPageController {
	public static final String ENTITY_STATUS = "entityStatus";
	public static final String ENTITY_STATUS_DESCRIPTION = "entityStatusDescription";
	public static final String CURRENT_ENTITY_STATUS = "currentEntityStatus";
	public static final String IS_ENTITY_STATUS_VALID = "isEntityStatusValid";
	public static final String ENTITY_STATUS_REVOKED = "REVOKED";
	public static final String ENTITY_STATUS_EXPIRED = "EXPIRED";
	public static final String ENTITY_STATUS_ACTIVE = "ACTIVE";
	public static final String ENTITY_STATUS_SUSPENDED = "SUSPENDED";
	public static final String ENTITY_STATUS_NATIONAL = "NATIONAL";
	public static final String ENTITY_STATUS_CANCEL = "CANCEL";
	public static final String SERVICE_NO_DESCRIPTION = "service.no.description";
	public final Map<String, String> entityStatuses = new HashMap<>();

	@Resource(name = "sagiaFormatProvider")
	private SagiaFormatProvider provider;

	@Resource(name = "ZUI5SagiaFacade")
	private ZUI5SagiaFacade zui5SagiaFacade;

	@Resource
	private MessageSource messageSource;

	@Resource(name = "i18nService")
	private I18NService i18nService;

	@Resource(name = "sagiaTutorialFacade")
	private SagiaTutorialFacade sagiaTutorialFacade;

	@Resource(name = "sagiaCustomerFacade")
	private SagiaCustomerFacade sagiaCustomerFacade;

	public SagiaAbstractPageController(){
		entityStatuses.put("ACTIV", "Active");
		entityStatuses.put("LICIN", "Expired");
		entityStatuses.put("SUSFL", "Suspended");
		entityStatuses.put("DEACT", "Revoked");
		entityStatuses.put("COCAN", "Cancelled");
		entityStatuses.put("CONAT", "Converted to National");
		entityStatuses.put("NMREG", "Name Registration");
		entityStatuses.put("INREQ", "License Request Under Approval");
		entityStatuses.put("COREJ", "License Request Rejected");
		entityStatuses.put("TBCER", "Temporary Bidding Certificate");
		entityStatuses.put("CRACT", "CR Active");
		entityStatuses.put("CREXP", "CR Expired");
		entityStatuses.put("CRUNA", "CR Unavailable");
	}
	
	@ModelAttribute("uiDateFormat")
	public String getUiDateFormat()
	{
		return provider.getUIDateFormat();
	}

	@ModelAttribute("uiTimeFormat")
	public String getUiTimeFormat()
	{
		return provider.getUITimeFormat();
	}

	@ModelAttribute("uiDateFormatUAQ")
	public String getUiDateFormatUAQ()
	{
		return provider.getUIDateFormatUAQ();
	}

	@ModelAttribute("uiTimeFormatUAQ")
	public String getUiTimeFormatUAQ()
	{
		return provider.getUITimeFormatUAQ();
	}
	
	@ModelAttribute("displayTutorial")
	public Boolean getDismissedTutorial(){
		return sagiaCustomerFacade.displayDashboardTutorial();
	}


	@ModelAttribute("tutorialJson")
	public String getTutorial(HttpServletRequest request){
	    String tutorialUrl = StringUtils.stripStart(request.getServletPath(), "/");
        if(StringUtils.countMatches(tutorialUrl, "/") > 1){
            int endIndex = tutorialUrl.lastIndexOf("/");
            if (endIndex != -1)
            {
                tutorialUrl = tutorialUrl.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
            }
        }
		SagiaTutorialData tutorial = sagiaTutorialFacade.getTutorial(tutorialUrl);
		if(tutorial != null){
			return new Gson().toJson(tutorial);
		}
		return null;
	}

	protected String getCurrentEntityStatus() {
		HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
		if (homeHDR != null && homeHDR.getLicenseInfoData() != null) {
			return homeHDR.getLicenseInfoData().getLicStDesc();
		}
		return null;
	}

	protected String getEntityStatus() {
		String entityStatus = getSessionService().getAttribute(ENTITY_STATUS);
		if(StringUtils.isEmpty(entityStatus)) {
			HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
			if (homeHDR != null && homeHDR.getLicenseInfoData() != null) {
				entityStatus = entityStatuses.get(homeHDR.getLicenseInfoData().getLicStatus());
				getSessionService().getCurrentSession().setAttribute(ENTITY_STATUS, entityStatus);
			}
		}
		return entityStatus;
	}

	protected String getEntityStatusDescription() {
		String entityStatusDescription = getSessionService().getAttribute(ENTITY_STATUS_DESCRIPTION);
		if(StringUtils.isEmpty(entityStatusDescription)) {
			HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
			if (homeHDR != null && homeHDR.getLicenseInfoData() != null) {
				entityStatusDescription = homeHDR.getLicenseInfoData().getLicStDesc();
				getSessionService().getCurrentSession().setAttribute(ENTITY_STATUS_DESCRIPTION, entityStatusDescription);
			}
		}
		return entityStatusDescription;
	}

	protected boolean checkEntityStatusRealEstate(RedirectAttributes redirectModel) {
		String sessionEntityStatus = getEntityStatus();
		if(StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_ACTIVE) ||
				StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_EXPIRED) ||
				StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_REVOKED)) {
			return false;
		}
		redirectModel.addFlashAttribute(IS_ENTITY_STATUS_VALID, false);
		redirectModel.addFlashAttribute(ENTITY_STATUS, sessionEntityStatus);
		return true;
	}

	protected boolean allowBuyOption() {
		return !((StringUtils.containsIgnoreCase(getEntityStatus(), ENTITY_STATUS_EXPIRED)) || (StringUtils.containsIgnoreCase(getEntityStatus(), ENTITY_STATUS_REVOKED)));
	}

	/*
	 * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
	 * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
	 * The structure of this method is not difficult to understand in the given context.
	 */
	@SuppressWarnings({ "squid:S3776","squid:S134"})
	protected String getServiceDescription(String serviceCode) {
		String serviceCodeParsed = serviceCode;
		if(serviceCode.indexOf('/') == 0){
			serviceCodeParsed = serviceCode.substring(1);
		}

		if(StringUtils.isNotEmpty(serviceCode)) {
			Map<String, Object> sagiaMenuData = getSessionService().getAttribute("sagiaMenuData");
			if (sagiaMenuData != null) {
				Map<String, List<SagiaServiceData>> navservices = (Map<String, List<SagiaServiceData>>) sagiaMenuData.get("navservices");
				if (MapUtils.isNotEmpty(navservices)) {
					for(Map.Entry<String, List<SagiaServiceData>> navService : navservices.entrySet()){
						for(SagiaServiceData service : navService.getValue()){
							if(serviceCodeParsed.equals(service.getUrl())){
								return service.getDescription();
							}
						}
					}
				}
			}
		}
		return messageSource.getMessage(SERVICE_NO_DESCRIPTION, null, i18nService.getCurrentLocale());
	}

	/*
	 * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
	 * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
	 * The structure of this method is not difficult to understand in the given context.
	 */
	@SuppressWarnings({ "squid:S3776","squid:S134"})
	protected String getServiceName(String serviceName){
		if(StringUtils.isNotEmpty(serviceName)) {
			Map<String, Object> sagiaMenuData = getSessionService().getAttribute("sagiaMenuData");
			if (sagiaMenuData != null) {
				Map<String, List<SagiaServiceData>> navservices = (Map<String, List<SagiaServiceData>>) sagiaMenuData.get("navservices");
				if (MapUtils.isNotEmpty(navservices)) {
					for(Map.Entry<String, List<SagiaServiceData>> navService : navservices.entrySet()){
						for(SagiaServiceData service : navService.getValue()){
							if(serviceName.equals(service.getUrl())){
								return service.getName();
							}
						}
					}
				}
			}
		}
		return messageSource.getMessage(SERVICE_NO_DESCRIPTION, null, i18nService.getCurrentLocale());
	}

	public SagiaFormatProvider getProvider()
	{
		return provider;
	}

	public void setProvider(final SagiaFormatProvider provider)
	{
		this.provider = provider;
	}
}
