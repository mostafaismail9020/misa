/*
 * package com.investsaudi.portal.core.service.impl;
 * 
 * import com.investsaudi.portal.core.model.InvestSaudiWebinarModel; import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationModel; import
 * com.investsaudi.portal.core.service.InvestSaudiWebinarRegistrationService;
 * import de.hybris.platform.servicelayer.i18n.I18NService; import
 * de.hybris.platform.servicelayer.model.ModelService;
 * 
 * import javax.annotation.Resource;
 * 
 * public class InvestSaudiWebinarRegistrationServiceImpl implements
 * InvestSaudiWebinarRegistrationService {
 * 
 * @Resource private ModelService modelService;
 * 
 * @Resource private I18NService i18NService;
 * 
 * @Override public InvestSaudiWebinarRegistrationModel
 * createNewWebinarRegistration(String userEmail, String webinarCode) {
 * 
 * InvestSaudiWebinarRegistrationModel investSaudiWebinarRegistrationModel =
 * modelService.create(InvestSaudiWebinarRegistrationModel.class);
 * 
 * investSaudiWebinarRegistrationModel.setUserEmail(userEmail);
 * investSaudiWebinarRegistrationModel.setWebinarCode(webinarCode);
 * investSaudiWebinarRegistrationModel.setLanguageIso(i18NService.
 * getCurrentLocale().getLanguage());
 * 
 * modelService.save(investSaudiWebinarRegistrationModel);
 * 
 * return investSaudiWebinarRegistrationModel; }
 * 
 * @Override public void sendWebinarRegistrationEmail(InvestSaudiWebinarModel
 * investSaudiWebinarModel, String userEmail) {
 * 
 * } }
 */