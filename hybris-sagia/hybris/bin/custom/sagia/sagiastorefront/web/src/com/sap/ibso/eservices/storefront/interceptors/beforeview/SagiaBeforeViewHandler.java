package com.sap.ibso.eservices.storefront.interceptors.beforeview;

import com.sap.ibso.eservices.facades.data.MandatorySurveyIndicators;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaSurveyFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.ProductPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.i18n.L10NService;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * Handler designed for injecting the required javascript files for each page.
 */
public class SagiaBeforeViewHandler implements BeforeViewHandler {

	private static final Logger LOG = Logger.getLogger(SagiaBeforeViewHandler.class);

    private static final String SAGIA_I18N_LIST = "sagia.I18n.js";
    private static final String SAGIA_DASHBOARD_JS = "sagia.dashboardsCommon.js";
    private static final String SAGIA_LICENSE_REPLACEMENT_JS = "sagia.license.replacement.js";
    private static final String SAGIA_LICENSE_CANCELLATION_JS = "sagia.license.cancellation.js";
    private static final String SAGIA_FOLLOW_UP_JS = "sagia.followUp.js";
    private static final String SAGIA_DRAFT_JS = "sagia.draft.js";
    private static final String SAGIASTOREFRONT_MESSAGES_JS = "sagiastorefront.messages.js";
    private static final String SAGIA_JS_TEXTS = "sagiaJsTexts";
    private static final String SAGIA_JS_TEXTS_LOCALE = "sagiaJsTextsLocale";
    private static final String SAGIA_JAVASCRIPTS = "sagiaJavascripts";
    private static final String SAGIA_ETOUR_JS = "sagia.eServiceTour.js";
    private static final String SAGIA_MESSAGES_JS = "sagia.messages.js";
    private static final String SAGIA_REGISTER_JS = "sagia.register.js";
    private static final String SAGIA_SPECIALSERVICES_JS = "sagia.specialservices.js";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource(name = "sagiaSurveyFacade")
    private SagiaSurveyFacade sagiaSurveyFacade;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

	@Resource(name = "userService")
	private UserService userService;

    private L10NService l10NService;

    private I18NService i18NService;

    @Override
    public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView) throws IOException {
        final ResourceBundle bundle = l10NService.getResourceBundle(SAGIASTOREFRONT_MESSAGES_JS);
        final String currentLocaleCode = i18NService.getCurrentLocale().getLanguage();
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        if(customer != null) {
        	LOG.info("########## IsOutstandingFee "+customer.getIsOutstandingFee()+" **** IsPendingFinancialStatement "+customer.getIsPendingFinancialStatement());
        }
        Map<String, String> jsTexts = convertResourceBundleToMap(bundle);
        modelAndView.addObject(SAGIA_JS_TEXTS_LOCALE, currentLocaleCode);
        modelAndView.addObject(SAGIA_JS_TEXTS, jsTexts);
        modelAndView.addObject("environment",Config.getString("sagia.environment", ""));
        modelAndView.addObject("staticResourceVersion",Config.getString("sagia.staticresource.buildversion", ""));

        String name = null;
        AbstractPageModel requestedPage = (AbstractPageModel) modelAndView.getModel().get(AbstractPageController.CMS_PAGE_MODEL);
        if (requestedPage != null) {
        	if (requestedPage instanceof ContentPageModel) {
        		name = ((ContentPageModel) requestedPage).getLabel();

	            List<String> javascripts = new ArrayList<>();
	            updateJavascripts(name, javascripts);
	            modelAndView.addObject(SAGIA_JAVASCRIPTS, javascripts);
	            LOG.info("******* name: "+name);
	            if (name.contains("login")
	                    || name.contains("logout") || name.contains("questionnaire")
	                    || name.contains("payments-overview") || name.contains("financial-statement")
	                    || name.contains("payments-success") || name.contains("payments-failure")
	                    || name.contains("sagia-generic-exception")) {
	                return;
	            }
	        }

            if(isURLAllowed(request))
            {
            	if(!sagiaDashboardWithoutLicenseFacade.hasLicense()){
            		return;
            	}else {
            		if(customer != null && Boolean.TRUE.equals(customer.getIsOutstandingFee())) {
            			//LOG.info("########## IsOutstandingFee "+customer.getIsOutstandingFee());
            			redirectStrategy.sendRedirect(request, response, "/payments-overview");
            		}
            		if(customer != null && Boolean.TRUE.equals(customer.getIsPendingFinancialStatement())) {
            			//LOG.info("^^^^^^ getIsPendingFinancialStatement: "+customer.getIsPendingFinancialStatement());
            			redirectStrategy.sendRedirect(request, response, "/financial-statement/new");
            		}
            	}
            }

            if (("true").equalsIgnoreCase(sagiaConfigurationFacade.getMandatorySurvey())) {
                HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
                MandatorySurveyIndicators mandatorySurveyIndicators = sagiaAccountFacade.getMandatorySurveyIndicators(homeHDR);
                if (mandatorySurveyIndicators.isActiveSurvey() && mandatorySurveyIndicators.isMandSurvey() && sagiaSurveyFacade.getMandatorySurvey() != null) {
                    redirectStrategy.sendRedirect(request, response, "/my-sagia/questionnaires/" + sagiaSurveyFacade.getMandatorySurvey().getSurveyid() + "/" + sagiaSurveyFacade.getMandatorySurvey().getSurveyversion());
                }
            }
        }
    }

	private boolean isURLAllowed(final HttpServletRequest request) {
		String header = request.getHeader("x-requested-with");
		boolean isAjaxRequest = header != null && header.equalsIgnoreCase("XMLHttpRequest");
		return !request.getRequestURI().contains("/login") && !request.getRequestURI().contains("/cms/") && !isAjaxRequest;
	}

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | The cyclomatic complexity of methods should not exceed a defined threshold.)
     * Suppress sonar warning (squid: S1479 | "switch" statements should not have too many "case" clauses)
     * Suppress sonar warning (squid: S1151 | Methods should not have too many lines )
     * Suppress sonar warning (squid: S138 | Methods should not have too many lines )
     * The structure of this switch case statement is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity","squid:S1479","squid:S1151","squid:S138"})
    private void updateJavascripts(String name, List<String> javascripts) {
        switch (name) {
            case "login":
            case "login-second-step":
                javascripts.addAll(Arrays.asList(SAGIA_REGISTER_JS, "sagia.login.js"));
                break;
            case "verification": {
                javascripts.addAll(Arrays.asList(SAGIA_REGISTER_JS, "sagia.login.js"));
                break;
            }

            case "sagia-profile": {
                javascripts.addAll(Arrays.asList("sagia.profile.js", "sagia.myAccount.js", "sagia.expandedComplaint.js"));
                break;
            }
            case "dashboard": {
                javascripts.add(SAGIA_DASHBOARD_JS);
                javascripts.add("sagia.dashboardWithLicense.js");
                javascripts.add("sagia.expandedComplaint.js");
                javascripts.add("sagia.payment.js");
                javascripts.add("sagia.payment.ratings.js");
                javascripts.add(SAGIA_ETOUR_JS);
                break;
            }
            case "dashboard-without-license": {
                javascripts.add(SAGIA_DASHBOARD_JS);
                javascripts.add("sagia.dashboardNoLicense.js");
                javascripts.add(SAGIA_ETOUR_JS);
                break;
            }
            case "payments-success": {
                javascripts.add("sagia.payment.ratings.js");
                break;
            }
            case "payments-failure":
                break;
            case "payments": {
                javascripts.add("sagia.payments.js");
                break;
            }
            case "payments-overview": {
                javascripts.add("sagia.payments.js");
                javascripts.add("sagia.payment.js");
                javascripts.add("sagia.payment.ratings.js");
                break;
            }
            case "service-requests-overview": {
                javascripts.add("sagia.serviceRequests.js");
                break;
            }
            case "appointments":
            case "appointment-create":
            case "appointment-details":
            case "appointment-info":
                javascripts.add("sagia.appointments.js");
                break;

            case "special-services": {
                javascripts.add(SAGIA_SPECIALSERVICES_JS);
                break;
            }

            case "special-services-create": {
                javascripts.add(SAGIA_SPECIALSERVICES_JS);
                break;
            }
            case "special-services-service-requests-overviewcreate": {
                javascripts.addAll(Arrays.asList(SAGIA_SPECIALSERVICES_JS));
                break;
            }
            case "complaints":
                break;

            case "government-documents": {
                javascripts.addAll(Arrays.asList("sagia.wasselCheck.js","sagia.govDocs.statusModal.js", "sagia.governmentDocuments.js"));
                break;
            }
            case "documents-branches": {
                javascripts.addAll(Arrays.asList("sagia.googleMap.js", "sagia.governmentDocuments.js"));
                break;
            }
            case "legal-consultation": {
                javascripts.add("sagia.legalConsultation.js");
                break;
            }
            case "legal-consultation-create": {
                javascripts.addAll(Arrays.asList("sagia.legalConsultationCreate.js", SAGIA_DRAFT_JS));
                break;
            }
            case "financial-statement": {
                javascripts.add("sagia.financialStatement.js");
                break;
            }
            case "financial-statement-create": {
                javascripts.addAll(Arrays.asList("sagia.financialStatementCreate.js", SAGIA_DRAFT_JS));
                break;
            }
            case "license-apply": {
                javascripts.addAll(Arrays.asList("sagia.license.apply.js", "sagia.license.apply.entityinformation.js", "sagia.license.apply.shareholders.js", "sagia.license.apply.shareholders.qeemah1.js",
                        "sagia.license.apply.shareholders.qeemah2.js", "sagia.license.apply.contactperson.js", "sagia.license.apply.review.js", "sagia.license.businessActivities.js", "sagia.payment.js", "sagia.license.simulator.js"));
                break;
            }
            case "new-license-apply": {
                javascripts.addAll(Arrays.asList("sagia.license.businessActivities.js", "sagia.licenseApplyEntityInfo.js", "sagia.licenseApply.js"));
                break;
            }
            case "shareholder-apply": {
                javascripts.addAll(Arrays.asList("sagia.licenseApplyShareholder.js", "sagia.licenseApply.js", "sagia.licenseApplyShareholderCommons.js"));
                break;
            }
            case "new-apply-contactperson": {
                javascripts.addAll(Arrays.asList("sagia.licenseApplyContactPerson.js", "sagia.licenseApply.js", "sagia.licenseApplyShareholderCommons.js"));
                break;
			}
            case "new-apply-review": {
                javascripts.addAll(Arrays.asList("sagia.licenseApplyReview.js", "sagia.licenseApply.js"));
                break;
			}
            case "license-amend": {
                javascripts.addAll(Arrays.asList(
                        "jquery.dataTables.min.js", "sagia.license.amendment.util.js", "sagia.license.businessActivities.js", "sagia.license.amendment.js", "sagia.license.amendment.shareholder.js", "sagia.license.amendment.branch.js", "sagia.license.amendment.product.js", "sagia.license.amendment.validation.js"));
                break;
            }
            case "financial-survey": {
                javascripts.addAll(Arrays.asList(
                        "jquery.dataTables.min.js", "sagia.license.amendment.util.js", "sagia.financial.survey.businessActivities.js", "sagia.financial.survey.js", "sagia.financial.survey.shareholder.js", "sagia.financial.survey.affiliate.js", "sagia.financial.survey.branch.js","sagia.financial.survey.subsidiary.js", "sagia.financial.survey.validation.js","sagia.financial.survey.attachement.js"));
                break;
            }
            case "license-replace": {
                javascripts.add(SAGIA_LICENSE_REPLACEMENT_JS);
                break;
            }
            case "license-new-replace": {
                javascripts.addAll(Arrays.asList(SAGIA_LICENSE_REPLACEMENT_JS, SAGIA_DRAFT_JS));
                break;
            }
            case "license-resubmit-replace": {
                javascripts.addAll(Arrays.asList(SAGIA_LICENSE_REPLACEMENT_JS, SAGIA_DRAFT_JS));
                break;
            }
            case "license-convert": {
                javascripts.add("sagia.convertToNationals.js");
                break;
            }
            case "license-new-convert": {
                javascripts.addAll(Arrays.asList("sagia.newConvertToNationals.js", SAGIA_DRAFT_JS));
                break;
            }
            case "license-renew": {
                javascripts.addAll(Arrays.asList("sagia.renewlicense.js"));
                break;
            }
            case "license-renew-edit": {
                javascripts.addAll(Arrays.asList("sagia.renewlicense.js", SAGIA_DRAFT_JS));
                break;
            }
            case "license-bidding": {
                javascripts.addAll(Arrays.asList("sagia.license.js", SAGIA_DRAFT_JS));
                break;
            }
            case "license-stakeholders":
                break;

            case "ui-test":
                break;

            case "financial":
            	javascripts.addAll(Arrays.asList("sagia.financialStatementCreate.js", "sagia.financialStatement.js", SAGIA_DRAFT_JS));
                break;

            case "realestate": {
                javascripts.add("sagia.realEstate.js");
                break;
            }
            case "realestate-create": {
                javascripts.addAll(Arrays.asList("sagia.realEstate.js", SAGIA_DRAFT_JS));
                break;
            }
            case "sagia-govtServices": {
                javascripts.add("sagia.services.js");
                break;
            }
            case "sagia-createGovtServices": {
                javascripts.addAll(Arrays.asList("sagia.services.js", SAGIA_DRAFT_JS));
                break;
            }
            case "license-cancellation":
                javascripts.add(SAGIA_LICENSE_CANCELLATION_JS);
                break;

            case "license-cancellation_1": {
                javascripts.addAll(Arrays.asList(SAGIA_LICENSE_CANCELLATION_JS, SAGIA_DRAFT_JS));
                break;
            }

            case "license-cancellation_2": {
                javascripts.add(SAGIA_LICENSE_CANCELLATION_JS);
                break;
            }
            case "license-cancellation_3": {
                javascripts.addAll(Arrays.asList(SAGIA_LICENSE_CANCELLATION_JS, SAGIA_DRAFT_JS));
                break;
            }
            case "license-cancellation_4": {
                javascripts.add(SAGIA_LICENSE_CANCELLATION_JS);
                break;
            }
            case "code-authentication": {
                javascripts.add(SAGIA_LICENSE_CANCELLATION_JS);
                break;
            }
            case "service-search": {
                javascripts.add("sagia.searchService.js");
                break;
            }
            case "license-classification-upgrade": {
                javascripts.add("sagia.classificationUpgrade.js");
                break;
            }
            case "license-new-classification-upgrade": {
                javascripts.add("sagia.newClassificationUpgrade.js");
                break;
            }
            case "dashboard-edit": {
                javascripts.add(SAGIA_DASHBOARD_JS);
                javascripts.add("sagia.dashboardWithLicense.js");
                break;
            }
            case "sagia-cms-dashboard-no-license": {
                javascripts.add(SAGIA_DASHBOARD_JS);
                break;
            }
            case "sagia-payment-details":{
            	javascripts.add("sagia.payment.js");
                javascripts.add("sagia.payment.ratings.js");
                break;
            }
            case "questionnaires": {
                javascripts.add("sagia.myAccount.js");
                break;
            }

            case "sagia-cms-help-and-informations":
                break;

            case "sagia-cms-help-quick-registration":
                break;

            case "sagia-cms-help-apply-sagia-license":
                break;

            case "sagia-cms-help-apply-unified-license":
                break;

            case "sagia-cms-help-national-investor-cr":
                break;

            case "sagia-cms-help-national-investor-no-cr":
                break;

            case "notifications": {
                javascripts.add("sagia.notifications.js");
                javascripts.add("sagia.payment.js");
                break;
            }
            case "empty-notifications": {
                javascripts.add("sagia.emptyNotifications.js");
                break;
            }
            case "warning-letters": {
                javascripts.add(SAGIA_FOLLOW_UP_JS);
                break;
            }
            case "warning-letters-create": {
                javascripts.add(SAGIA_FOLLOW_UP_JS);
                javascripts.add(SAGIA_DRAFT_JS);
                break;
            }
            case "violation-replies": {
                javascripts.add(SAGIA_FOLLOW_UP_JS);
                break;
            }
            case "violation-replies-create": {
                javascripts.add(SAGIA_FOLLOW_UP_JS);
                javascripts.add(SAGIA_DRAFT_JS);
                break;
            }
            case "support-visits": {
                javascripts.add(SAGIA_FOLLOW_UP_JS);
                javascripts.add("sagia.supportVisits.js");
                javascripts.add(SAGIA_REGISTER_JS);
                break;
            }
            case "support-visits-create": {
                javascripts.addAll(Arrays.asList(SAGIA_FOLLOW_UP_JS, SAGIA_REGISTER_JS, "sagia.supportVisits.js", SAGIA_DRAFT_JS));
                break;
            }
            case "single-questionnaire": {
                javascripts.add("sagia.questionnaire.js");
                break;
            }
            case "facility-reopen":
            case "facility-reopen-create": {
                javascripts.addAll(Arrays.asList("sagia.facility-reopen.js", SAGIA_DRAFT_JS));
                break;
            }
            case "contact-update-history": {
                javascripts.add("sagia.contactUpdateHistory.js");
                break;
            }
            default:
                break;
        }
    }

    public L10NService getL10NService() {
        return l10NService;
    }

    public void setL10NService(final L10NService l10NService) {
        this.l10NService = l10NService;
    }

    public I18NService getI18NService()
    {
        return i18NService;
    }

    public void setI18NService(final I18NService i18NService)
    {
        this.i18NService = i18NService;
    }

    private static Map<String, String> convertResourceBundleToMap(ResourceBundle resource) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
        }

        return map;
    }
}
