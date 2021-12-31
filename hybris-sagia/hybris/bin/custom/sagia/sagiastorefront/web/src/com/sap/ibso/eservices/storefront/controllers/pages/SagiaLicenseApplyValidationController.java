package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.BasicContactInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.ExistingShareholderInfo;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.facades.data.zqeemah2.BasicContactInfo;
import com.sap.ibso.eservices.facades.data.zqeemah2.BasicOrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.license.*;
import com.sap.ibso.eservices.storefront.util.JsonResponseWrapper;
import com.sap.ibso.eservices.storefront.util.SagiaUtils;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.i18n.L10NService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sap.ibso.eservices.facades.sagia.SagiaCountryFacade;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/my-sagia/license/validation")
public class SagiaLicenseApplyValidationController extends SagiaAbstractPageController {
    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "i18NService")
    private I18NService i18NService;

    @Resource(name = "l10nService")
    private L10NService l10nService;

    @Resource(name = "basicOrganizationInformationValidator")
    BasicOrganizationInformationValidator basicOrganizationInformationValidator;

    @Resource(name = "organizationInformationValidator")
    OrganizationInformationValidator organizationInformationValidator;

    @Resource(name = "newShareholderEntityQeemah1Validator")
    NewShareholderEntityQeemah1Validator newShareholderEntityQeemah1Validator;

    @Resource(name = "basicContactInfoValidatorQ2")
    ContactPersonValidatorQeemah2 contactPersonValidatorQeemah2;

    @Resource(name = "basicContactInfoValidatorQ1")
    ContactPersonValidatorQeemah1 contactPersonValidatorQ1;

    @Resource(name = "newShareholderPersonQeemah1Validator")
    NewShareholderPersonQeemah1Validator newShareholderPersonQeemah1Validator;

    @Resource(name = "shareholderEntityValidatorQm2")
    ShareholderEntityValidatorQm2 shareholderEntityValidatorQm2;

    @Resource(name = "shareholderPersonValidatorQm2")
    ShareholderPersonValidatorQm2 shareholderPersonValidatorQm2;

    @Resource(name = "sharesPercentageValidator")
    SharesPercentageValidator sharesPercentageValidator;

    @Resource (name = "existingShareholderValidator")
    ExistingShareholderValidator existingShareholderValidator;
    
    @Resource(name = "sagiaCountryFacade")
    private SagiaCountryFacade sagiaCountryFacade;

    private static final String NEW_SHAREHOLDERS = "newShareholders";
    private static final String EXISTING_SHAREHOLDERS = "existingShareholders";

    @RequestMapping(value = "/contact-person-q2-validation", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateBasicContactInfoQ2(@RequestBody BasicContactInfo basicContactInfo, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        contactPersonValidatorQeemah2.validate(basicContactInfo, bindingResult);
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/contact-person-q1-validation", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateBasicContactInfoQ1(@RequestBody BasicContactInformation basicContactInformation, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        contactPersonValidatorQ1.validate(basicContactInformation, bindingResult);
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/basic-organization-information", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateBasicInformation(@RequestBody BasicOrganizationInformation basicOrganizationInformation, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        basicOrganizationInformationValidator.validate(basicOrganizationInformation, bindingResult);
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/organization-information", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateOrganizationInformation(@RequestBody OrganizationInformation organizationInformation, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        organizationInformationValidator.validate(organizationInformation, bindingResult);
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/new-shareholder-qeemah1-entity", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateShareholderQeemah1Entity(HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        HashMap<String, String> errors = new HashMap<>();
        List<ShareholderInfo> newShareholders = new ArrayList<>();
        List<ExistingShareholderInfo> existingShareholders = new ArrayList<>();

        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), "UTF-8"), Map.class); //NOSONAR
        Map<String, Object> qeemah1Map = (Map) map.get("qeemah1Data");

        OrganizationInformation organizationInformation = new Gson().fromJson(new Gson().toJson(map.get("basicInformationExtended")), OrganizationInformation.class);

        fillShareholdersLists(qeemah1Map, newShareholders, existingShareholders);
        
        List<SagiaCountryData> shareHolderCheck = sagiaCountryFacade.getShareHolderCheckCountries();
        

         if(!newShareholders.isEmpty()) {
             newShareholderEntityQeemah1Validator.validateNewShareholderEntity(newShareholders.get(newShareholders.size()-1), organizationInformation, errors, shareHolderCheck);
        }

        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }


    }

    @RequestMapping(value = "/new-shareholder-qeemah1-person", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateShareholderQeemah1Person(@RequestBody ShareholderInfo shareholderInfo, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        newShareholderPersonQeemah1Validator.validate(shareholderInfo, bindingResult);        
        
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/new-shareholder-qeemah2-person", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateShareholderQeemah2Person(@RequestBody Shareholder shareholder, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        shareholderPersonValidatorQm2.validate(shareholder, bindingResult);
        if (!bindingResult.hasErrors()) {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return getResponseEntity(bindingResult, response);
        }
    }

    @RequestMapping(value = "/new-shareholder-qeemah2-entity", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateShareholderQeemah2Entity(HttpServletRequest request) throws IOException {

        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), "UTF-8"), Map.class); //NOSONAR
        JsonResponseWrapper response = new JsonResponseWrapper();
        HashMap<String, String> errors = new HashMap<>();

        BasicOrganizationInformation basicOrganizationInformation = new Gson().fromJson(new Gson().toJson(map.get("basicInformation")), BasicOrganizationInformation.class);



        Collection<Map> shareholdersData = new Gson().fromJson(new Gson().toJson(((Map<String, Object>) map.get("qeemah2Data")).get("shareholders")), ArrayList.class);
        List<Shareholder> shareholders = new ArrayList (SagiaUtils.loadQeemah2ShareholderData(shareholdersData));

        if(shareholders!= null  && !shareholders.isEmpty()) {
            shareholderEntityValidatorQm2.validateEntityShareholder(shareholders.get(shareholders.size()-1), basicOrganizationInformation, errors);
        }

        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/new-shareholder-percentage-QM2", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateShareholderPercentageQM2(@RequestBody Map<Integer, List<Shareholder>> shareholderMap) {
        JsonResponseWrapper response = new JsonResponseWrapper();
        Shareholder shareholder = (shareholderMap.get(0) != null ? shareholderMap.get(0).get(0) : new Shareholder());
        List<Shareholder> existingShareholdersList = (shareholderMap.get(1) != null ? shareholderMap.get(1) : new ArrayList<>());
        HashMap<String, String> errors = new HashMap<>();

        sharesPercentageValidator.validateNewShareholderPercentageQM2(shareholder, errors, existingShareholdersList);

        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/new-shareholder-percentage-QM1", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateNewShareholderPercentageQM1(HttpServletRequest request) throws IOException {
        final Map<String, Object> qeemah1Map = getData(request);

        List<ShareholderInfo> newShareholders = new ArrayList<>();
        List<ExistingShareholderInfo> existingShareholders = new ArrayList<>();

        fillShareholdersLists(qeemah1Map, newShareholders, existingShareholders);

        JsonResponseWrapper response = new JsonResponseWrapper();

        HashMap<String, String> errors = new HashMap<>();
        if (!newShareholders.isEmpty()) {
            ShareholderInfo newShareholder = newShareholders.get(newShareholders.size() - 1);
            String property = ("Organization".equals(newShareholder.getType()) ? "companySharesPercentage" : "sharesPercentage");

            sharesPercentageValidator.validateShareholderPercentageQM1(existingShareholders, newShareholders, errors, property);
        }
        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/existing-shareholder-percentage-QM1", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateExistingShareholderInfoPercentageQM1(HttpServletRequest request) throws IOException {
        final Map<String, Object> qeemah1Map = getData(request);

        List<ShareholderInfo> newShareholders = new ArrayList<>();
        List<ExistingShareholderInfo> existingShareholders = new ArrayList<>();

        fillShareholdersLists(qeemah1Map, newShareholders, existingShareholders);

        JsonResponseWrapper response = new JsonResponseWrapper();

        HashMap<String, String> errors = new HashMap<>();

        sharesPercentageValidator.validateShareholderPercentageQM1(existingShareholders, newShareholders, errors, "existingShareholderSharesPercentage");

        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/existing-shareholder-qeemah1", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateExistingShareholderQM1(HttpServletRequest request) throws IOException {

        JsonResponseWrapper response = new JsonResponseWrapper();
        HashMap<String, String> errors = new HashMap<>();
        List<ShareholderInfo> newShareholders = new ArrayList<>();
        List<ExistingShareholderInfo> existingShareholders = new ArrayList<>();

        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), "UTF-8"), Map.class); //NOSONAR
        Map<String, Object> qeemah1Map = (Map) map.get("qeemah1Data");

        OrganizationInformation organizationInformation = new Gson().fromJson(new Gson().toJson(map.get("basicInformationExtended")), OrganizationInformation.class);

        fillShareholdersLists(qeemah1Map, newShareholders, existingShareholders);


        if(!existingShareholders.isEmpty()) {
            existingShareholderValidator.validateExistingShareholder(existingShareholders.get(existingShareholders.size()-1), organizationInformation, errors);
        }


        if (!errors.isEmpty()) {
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/shareholders-total-percentage-QM2", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validShareholdersPercentage(@RequestBody List<Shareholder> shareholderMap) {
        JsonResponseWrapper response = new JsonResponseWrapper();
        if (!sharesPercentageValidator.validTotalShareholdersPercentageQM2(shareholderMap)) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("errorPercentageAmount", messageSource.getMessage("validation.shareholder.sharesPercentage.totalAmount", null, i18NService.getCurrentLocale()));
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/shareholders-total-percentage-QM1", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validShareholdersPercentage(HttpServletRequest request) throws IOException {
        final Map<String, Object> qeemah1Map = getData(request);

        List<ShareholderInfo> newShareholders = new ArrayList<>();
        List<ExistingShareholderInfo> existingShareholders = new ArrayList<>();

        fillShareholdersLists(qeemah1Map, newShareholders, existingShareholders);

        JsonResponseWrapper response = new JsonResponseWrapper();

        if (!sharesPercentageValidator.validTotalShareholdersPercentageQM1(existingShareholders, newShareholders)) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("errorPercentageAmount", messageSource.getMessage("validation.shareholder.sharesPercentage.totalAmount", null, i18NService.getCurrentLocale()));
            response.setFormErrors(errors);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setSuccess(true);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/financial-questions", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateFinancialQuestions(HttpServletRequest request) throws IOException {
        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), "UTF-8"), Map.class); //NOSONAR
        JsonResponseWrapper response = new JsonResponseWrapper();
        response.setSuccess(true);
        HashMap<String, String> errors = new HashMap<>();
        for(Map.Entry item : map.entrySet()) {
            if ("".equals(((Map) item.getValue()).get("answer"))) {
                errors.put(item.getKey().toString(), messageSource.getMessage("validation.financialquestion", null, i18NService.getCurrentLocale()));
            }
        }
        response.setFormErrors(errors);
        if (!errors.isEmpty()) {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/has-stock-listed-info", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity validateHasStockListedInfo(HttpServletRequest request) throws IOException {
        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), "UTF-8"), Map.class); //NOSONAR
        JsonResponseWrapper response = new JsonResponseWrapper();
        response.setSuccess(true);
        HashMap<String, String> errors = new HashMap<>();

            if (map.get("country") ==null || "".equals(map.get("country"))) {
                errors.put("countries", messageSource.getMessage("validation.stockcountry", null, i18NService.getCurrentLocale()));
            }

            if (map.get("isinCode") == null ||"". equals(map.get("isinCode"))){
                errors.put("iSINCode", messageSource.getMessage("validation.stockIsinCode", null, i18NService.getCurrentLocale()));
        }

        response.setFormErrors(errors);
        if (!errors.isEmpty()) {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }



    protected ResponseEntity getResponseEntity(BindingResult bindingResult, JsonResponseWrapper response) {
        response.setSuccess(false);
        HashMap<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(e -> {
            String message = messageSource.getMessage(e, getI18nService().getCurrentLocale());
            errors.put(e.getField(), message);
        });
        response.setFormErrors(errors);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    protected void fillShareholdersLists(Map<String, Object> qeemah1Map, List<ShareholderInfo> newShareholders, List<ExistingShareholderInfo> existingShareholders) {
        if (MapUtils.isNotEmpty(qeemah1Map)) {
            List<Map> qeemah1NewShareholders = (List) qeemah1Map.get(NEW_SHAREHOLDERS);
            List<Map> qeemah1ExistingShareholders = (List) qeemah1Map.get(EXISTING_SHAREHOLDERS);
            qeemah1NewShareholders.forEach(newShareholder -> {
                ShareholderInfo shareholderInfo = new Gson().fromJson(new Gson().toJson(newShareholder), ShareholderInfo.class);
                newShareholders.add(shareholderInfo);

            });
            qeemah1ExistingShareholders.forEach(existingShareholder -> {
                ExistingShareholderInfo existingShareholderInfo = new Gson().fromJson(new Gson().toJson(existingShareholder), ExistingShareholderInfo.class);
                existingShareholders.add(existingShareholderInfo);
            });
        }
    }

    protected Map<String, Object> getData(HttpServletRequest request) throws IOException{
        String data = IOUtils.toString(request.getInputStream(), "UTF-8");
        data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        data = data.replaceAll("\\+", "%2B");
        return new Gson().fromJson(URLDecoder.decode(data, "UTF-8"), Map.class);
    }
}
