package com.sap.ibso.eservices.storefront.controllers.pages.economic;

import com.sap.ibso.eservices.facades.data.ListSagiaIndicatorTermData;
import com.sap.ibso.eservices.facades.data.SagiaIndicatorTermData;
import com.sap.ibso.eservices.facades.sagia.SagiaIndicatorTermFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@Controller
@RequestMapping(value = "/economic")
public class EconomicInvestmentsTermsController extends SagiaAbstractPageController {

	private static final String INVESTMENTS_TERMS_PAGE = "/economic/investmentsTerms";
	private static final String SAGIA_FIRST_PAGE_INDEX = "1";

	@Resource
	SagiaIndicatorTermFacade sagiaIndicatorTermFacade;


	@Resource(name = "sagiaConfigurationFacade")
	private SagiaConfigurationFacade sagiaConfigurationFacade;


	@Resource(name = "sagiaPaginationFacade")
	private SagiaPaginationFacade sagiaPaginationFacade;

	@RequestMapping(value = "/investmentsTerms", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getInvestmentsTerms(final Model model) throws CMSItemNotFoundException {

		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INVESTMENTS_TERMS_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);
		return getViewForPage(model);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/investmentsTermsData")
	@ResponseBody
	public ListSagiaIndicatorTermData getInvestmentsTerms() {
		ListSagiaIndicatorTermData listSagiaIndicatorTermData = new ListSagiaIndicatorTermData();
		List<SagiaIndicatorTermData> listTerms = sagiaIndicatorTermFacade.getAllActiveIndicatorTerms();
		listSagiaIndicatorTermData.setSgiaIndicatorTerms(listTerms);
		return listSagiaIndicatorTermData;
	}

}
