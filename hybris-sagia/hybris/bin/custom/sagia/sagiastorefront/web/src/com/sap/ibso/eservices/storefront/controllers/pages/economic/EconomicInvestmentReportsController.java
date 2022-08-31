package com.sap.ibso.eservices.storefront.controllers.pages.economic;

import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/economic/investmentReports")
public class EconomicInvestmentReportsController extends SagiaAbstractPageController {

	private static final String INVESTMENTS_REPORTS_MAIN_PAGE = "/economic/investmentsReports";
	private static final String INVESTMENTS_REPORTS_CATEGORY_PAGE = "/economic/investmentsReports/category";

	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getInvestmentReports(final Model model) throws CMSItemNotFoundException {

		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INVESTMENTS_REPORTS_MAIN_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}
}
