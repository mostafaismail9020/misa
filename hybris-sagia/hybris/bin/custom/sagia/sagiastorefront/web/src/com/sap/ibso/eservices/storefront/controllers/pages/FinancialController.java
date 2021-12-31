package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.facades.data.financial.FinancialData;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/financial")
public class FinancialController extends SagiaAbstractPageController {

    private static final String CURRENCY_SAR = "SAR";
    @Resource(name = "sagiaFinancialFacade")
    private SagiaFinancialFacade sagiaFinancialFacade;
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    private static final String ENTITY_NAME = "FinanceHDRS";
    private static final String SAGIA_FINANCIAL_CMS_PAGE = "financial";

    /**
     * The page that contains all the financial data and history
     *
     * @param model
     * @return - financial page url
     * @throws CMSItemNotFoundException exception
     * @throws IOException exception
     */
    @RequestMapping(path= {"","/display/{srId}"},method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getFinancialPage(final Model model, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException {
        List<FinancialData> financialEntities = sagiaFinancialFacade.getFinancialEntities();

        if(CollectionUtils.isNotEmpty(financialEntities)) {
            FinancialData financialData;
            if(Strings.isNotEmpty(srId)){
                Optional<FinancialData> selectedFinancial = financialEntities.stream().filter(financial -> srId.equals(financial.getSrId())).findFirst();
                if(selectedFinancial.isPresent()){
                    financialData = sagiaFinancialFacade.getFinancialData(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else{
                    financialData =  sagiaFinancialFacade.getFinancialData(financialEntities.stream().findFirst().get().getSrId());
                }
            }
            else{
                financialData =  sagiaFinancialFacade.getFinancialData(financialEntities.stream().findFirst().get().getSrId());
            }
            model.addAttribute("first", financialData);
        }
        model.addAttribute("currency", CURRENCY_SAR); // hardcoded as currency is not recieved from CRM
        model.addAttribute("financialEntities", financialEntities);
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Ajax request to get a specific financial data. The request is made when the entity is changed
     * from the history list in the page.
     *
     * @param id - The id of the financial entity
     * @return - The financial entity with respective id in JSON format
     */
    @RequestMapping(path = "/changeFinanceHDR/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public String getFinanceHDR(@PathVariable String id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();

        FinancialData data = sagiaFinancialFacade.getFinancialData(id);
        return gson.toJson(data);
    }
}
