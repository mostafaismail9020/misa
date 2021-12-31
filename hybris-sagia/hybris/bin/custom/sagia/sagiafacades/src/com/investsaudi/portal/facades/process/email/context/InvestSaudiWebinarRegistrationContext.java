/*
 * package com.investsaudi.portal.facades.process.email.context;
 * 
 * import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationProcessModel;
 * import
 * de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
 * import de.hybris.platform.acceleratorservices.process.email.context.
 * AbstractEmailContext; import
 * de.hybris.platform.basecommerce.model.site.BaseSiteModel; import
 * de.hybris.platform.commerceservices.model.process.
 * StoreFrontCustomerProcessModel; import
 * de.hybris.platform.commerceservices.model.process.StoreFrontProcessModel;
 * import de.hybris.platform.core.model.c2l.LanguageModel; import
 * de.hybris.platform.core.model.user.CustomerModel; import
 * de.hybris.platform.processengine.model.BusinessProcessModel;
 * 
 * public class InvestSaudiWebinarRegistrationContext extends
 * AbstractEmailContext {
 * 
 * private static final String IMAGEURL = "imageUrl"; private static final
 * String LINKURL = "linkUrl"; private static final String TWITTER = "twitter";
 * private static final String LINKEDIN = "linkedin"; private static final
 * String LOGO = "logo"; private static final String YOUTUBE = "youtube";
 * 
 * @Override public void init(final BusinessProcessModel businessProcessModel,
 * final EmailPageModel emailPageModel) { put(FROM_EMAIL,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getFromEmail()); put(FROM_DISPLAY_NAME,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getFromEmail()); put(EMAIL,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getUserEmail()); put(DISPLAY_NAME,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getUserEmail()); put(EMAIL_LANGUAGE,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getLanguage()); put(IMAGEURL,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getImageUrl()); put(LINKURL,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getLinkUrl()); put(TWITTER,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getTwitterImageUrl()); put(LINKEDIN,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getLinkedinImageUrl()); put(LOGO,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getLogoImageUrl()); put(YOUTUBE,
 * ((InvestSaudiWebinarRegistrationProcessModel)
 * businessProcessModel).getYoutubeImageUrl());
 * 
 * }
 * 
 * @Override protected BaseSiteModel getSite(final BusinessProcessModel
 * businessProcessModel) { return ((StoreFrontProcessModel)
 * businessProcessModel).getSite(); }
 * 
 * @Override protected CustomerModel getCustomer(final BusinessProcessModel
 * businessProcessModel) { return ((StoreFrontCustomerProcessModel)
 * businessProcessModel).getCustomer(); }
 * 
 * @Override protected LanguageModel getEmailLanguage(BusinessProcessModel
 * businessProcessModel) { return ((StoreFrontCustomerProcessModel)
 * businessProcessModel).getLanguage(); } }
 */