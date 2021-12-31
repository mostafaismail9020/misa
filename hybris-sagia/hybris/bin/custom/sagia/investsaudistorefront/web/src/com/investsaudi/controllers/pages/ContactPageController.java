package com.investsaudi.controllers.pages;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.investsaudi.data.InvestSaudiContactData;
import com.investsaudi.data.InvestSaudiContactListData;
import com.investsaudi.facades.InvestSaudiContactFacade;
import com.investsaudi.services.InvestsaudiContactService;
import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;


@Controller
@Scope("tenant")
@RequestMapping("/contact")
public class ContactPageController extends AbstractPageController
{
	private static final String CONTACT_CMS_PAGE = "contact";
	public static final String CONTACT_PAGE_LINK= "text.contact.contact";
	
	@Resource(name = "defaultInvestSaudiContactConverter")
	private Converter<InvestSaudiContactModel, InvestSaudiContactData> investSaudiContactConverter;

	@Resource
	private InvestSaudiContactFacade investSaudiContactFacade ;
	
	@Resource
	private InvestsaudiContactService investsaudiContactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getContacts(final Model model) throws CMSItemNotFoundException
	{
		 final ContentPageModel offersCMSPage = getContentPageForLabelOrId(CONTACT_CMS_PAGE);
		  storeCmsPageInModel(model, offersCMSPage);
		  setUpMetaDataForContentPage(model, offersCMSPage);
		  
		  final Breadcrumb registrationBreadcrumbEntry = new Breadcrumb("#",
					getMessageSource().getMessage(CONTACT_PAGE_LINK, null, getI18nService().getCurrentLocale()), null);
			model.addAttribute("breadcrumbs", Collections.singletonList(registrationBreadcrumbEntry));
			
		return getViewForPage(model);
	}
	
	/**
	 * Spring MVC Model attribute that holds the list of contacts used to populate the contacts.
	 */
	@ModelAttribute("contacts")
	public InvestSaudiContactListData getContacts()
	{	
		return investSaudiContactFacade.getContactDataList();
	}
	
	
	
	
	
}