/*
 * package com.investsaudi.portal.core.service.impl;
 * 
 * import com.investsaudi.portal.core.dao.InvestSaudiWebinarDao; import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarModel; import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationModel; import
 * com.investsaudi.portal.core.service.InvestSaudiWebinarService; import
 * de.hybris.platform.core.model.media.MediaModel; import
 * de.hybris.platform.servicelayer.model.ModelService; import
 * org.apache.commons.collections4.CollectionUtils;
 * 
 * import javax.annotation.Resource; import java.util.ArrayList; import
 * java.util.Collection; import java.util.List; import java.util.Optional;
 * 
 * import static org.apache.commons.collections4.CollectionUtils.*;
 * 
 * public class InvestSaudiWebinarServiceImpl implements
 * InvestSaudiWebinarService {
 * 
 * @Resource private InvestSaudiWebinarDao investSaudiWebinarDao;
 * 
 * @Resource private ModelService modelService;
 * 
 * @Override public InvestSaudiWebinarModel findWebinarByCode(String
 * webinarCode) { Optional<InvestSaudiWebinarModel>
 * optionalInvestSaudiWebinarModel =
 * investSaudiWebinarDao.findWebinarByCode(webinarCode);
 * 
 * return optionalInvestSaudiWebinarModel.orElse(null); }
 * 
 * public InvestSaudiWebinarModel
 * addNewRegistrationToWebinar(InvestSaudiWebinarRegistrationModel
 * webinarRegistrationModel) {
 * 
 * InvestSaudiWebinarModel investSaudiWebinarModel =
 * findWebinarByCode(webinarRegistrationModel.getWebinarCode());
 * 
 * if (investSaudiWebinarModel != null) {
 * List<InvestSaudiWebinarRegistrationModel> webinarRegistrationModelList = new
 * ArrayList<>(emptyIfNull(investSaudiWebinarModel.getPortalWebinarRegistration(
 * ))); webinarRegistrationModelList.add(webinarRegistrationModel);
 * investSaudiWebinarModel.setPortalWebinarRegistration(
 * webinarRegistrationModelList);
 * 
 * modelService.save(investSaudiWebinarModel); }
 * 
 * return investSaudiWebinarModel; }
 * 
 * @Override public Collection<MediaModel> getEmailMedias() { return
 * emptyIfNull(investSaudiWebinarDao.getAllEmailMedias().getResult()); } }
 */