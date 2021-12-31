package com.sap.ibso.eservices.storefront.controllers.pages;


import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.FinancialStatementHDR;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialStatementFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.FinancialStatementHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.FinancialStatementForm;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaFinancialStatementValidator;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.util.localization.Localization;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;



@Controller
@RequestMapping(value = "/financial-statement")
public class FinancialStatementController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(FinancialStatementController.class);

    private static final String CURRENCY_SAR = "SAR";
    private static final String ENTITY_NAME = "FinancialStatementHDRSet";
    private static final String SAGIA_FINANCIAL_STATEMENT_CREATE_CMS_PAGE = "financial-statement-create";
    private static final String SAGIA_FINANCIAL_STATEMENT_CMS_PAGE = "financial-statement";
    private static final String SERVICE_ID = "ZS11";
    private static final String CREATE_FINANCE_SERVICE = "createFinanceService";
    
    private static final String FORM_GLOBAL_ERROR = "form.global.error";
    
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "financialStatementHDRService")
    private FinancialStatementHDRService financialStatementHDRService;

    @Resource(name = "defaultSagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
	
	@Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;	
    
    @Resource(name = "sagiaFinancialStatementFacade")
    private SagiaFinancialStatementFacade sagiaFinancialStatementFacade;
 	
    @Resource(name = "financialStatementValidator")
    SagiaFinancialStatementValidator financialStatementValidator;
    

    
    /**
     * read  Financial Statement by srID
     * @return details for a specific Financial Statement entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{srId}/details")
    @RequireHardLogIn
    @LicenseRequired
    public ResponseEntity<FinancialStatementHDR> getFinancialStatementDetails(@PathVariable("srId") String srId,
                                                                       HttpServletRequest request, Model model) {
    	FinancialStatementHDR financialStatementHDR = sagiaFinancialStatementFacade.getFinancialStatementHDR(srId);
    	if(null != financialStatementHDR.getContentHDRSet()) {
    		LOG.info("@@@@ ContentHDRSet size: "+financialStatementHDR.getContentHDRSet().size());
    	}
        return new ResponseEntity<>(financialStatementHDR, HttpStatus.OK);
    }
    
    /**
     * read Financial Statement list
     * @param model
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(path= {"","/display/{srId}"},method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getFinancialStatementList(final Model model, final HttpServletRequest request, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException{
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));

        List<FinancialStatementHDR> financialStatements = sagiaFinancialStatementFacade.getFinancialStatementHDR();
        getSessionService().setAttribute("financialStatements", financialStatements);
        model.addAttribute("financialStatements", financialStatements);

        if (!financialStatements.isEmpty()) {
            String selectedFinancialStatementId = financialStatements.get(0).getSrId();
            if (StringUtils.isNotEmpty(srId) && financialStatements.stream().anyMatch(l -> srId.equals(l.getSrId()))) {
                selectedFinancialStatementId = srId;
            }

            FinancialStatementHDR latestFinancialStatement = sagiaFinancialStatementFacade.getFinancialStatementHDR(selectedFinancialStatementId);
            model.addAttribute("latestFinancialStatement", latestFinancialStatement);
        }

        model.addAttribute("serviceDescription", getServiceDescription(request.getServletPath()));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_STATEMENT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_STATEMENT_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }
    
    /**
     * @return page for creating a new Financial Statement request
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String newFinancialStatement(final Model model) throws CMSItemNotFoundException {
        try {
			SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SERVICE_ID);
        	model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }

        boolean draftExists = sagiaDraftFacade.isDraftExists(SERVICE_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SERVICE_ID);

        model.addAttribute("financialStatementForm", new FinancialStatementForm());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_STATEMENT_CREATE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_STATEMENT_CREATE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }
    
    /**
     * Create a Financial Statement request
     * @param model
     * @param financialStatementForm request payload
     * @param result
     */
    @RequestMapping(value="/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    @LicenseRequired
    public String submit(final Model model, @ModelAttribute("financialStatementForm") final FinancialStatementForm financialStatementForm,
                         final BindingResult result, RedirectAttributes redirectModel)  throws JSONException {
    	financialStatementValidator.validate(financialStatementForm, result);
    	if (financialStatementForm.getFiles().size() <= 0) {
    		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString(FORM_GLOBAL_ERROR));
            //throw new IllegalArgumentException("Please upload only the requested files");
    		getSessionService().setAttribute("financialStatementForm", financialStatementForm);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.financialStatementForm", result);
            return REDIRECT_PREFIX + "/financial-statement";
        }
        if (result.hasErrors()) {
            getSessionService().setAttribute("financialStatementForm", financialStatementForm);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.financialStatementForm", result);
            return REDIRECT_PREFIX + "/financial-statement";
        }

        try {
        	financialStatementHDRService.createFinancialStatement(financialStatementForm);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.FINANCIAL_STATEMENT);
            redirectModel.addFlashAttribute("requestFeedback", "true");
        } catch (SagiaCRMException e) {
            LOG.warn(e.getMessage(),e);
            int status = 0;
            if (e.getMessage().indexOf('{') >= 0) {
                status = Integer.valueOf(e.getMessage().substring(0, e.getMessage().indexOf('{')).replaceAll("[^0-9]", ""));
                LOG.info("Financial statement status creation status: " + status);
                String jsonError = new JSONObject(e.getMessage().substring(e.getMessage().indexOf('{'))).getString("error");
                if (StringUtils.isNotEmpty(jsonError)) {
                    String jsonMessage = new JSONObject(jsonError).getString("message");
                    if (StringUtils.isNotEmpty(jsonMessage)) {
                        redirectModel.addFlashAttribute("errorMessage", new JSONObject(jsonMessage).getString("value"));
                    }
                }
            }
        }
        return REDIRECT_PREFIX + "/financial-statement";
    }
    
    /**
     * Displays a Financial Statement based on its id
     * @param srId
     * @param model
     * @param request
     */

    @RequestMapping(method = RequestMethod.GET, path = "/{srId}")
    @RequireHardLogIn
    @LicenseRequired
    public String getFinancialStatementBy(@PathVariable("srId") String srId, HttpServletRequest request, Model model) {
        try {
            model.addAttribute("financialStatement_detail", sagiaFinancialStatementFacade.getFinancialStatementHDR(srId));
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
        return "fragments/legalConsultation/expandedFinancialStatement";
    }
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/attachment/{objectId}/{documentID}")
    @RequireHardLogIn
    @LicenseRequired
    public ResponseEntity<InputStreamResource> getComplaintAttachment(@PathVariable("objectId") String objectId,
                                                                      @PathVariable("documentID") String documentId) {
        try {
            InputStream attachmentStream = sagiaFinancialStatementFacade.readAttachment(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(attachmentStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @ModelAttribute("financialStatementForm")
    public FinancialStatementForm getFinancialStatementForm() {
        return new FinancialStatementForm();
    }
    
}
