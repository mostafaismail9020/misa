/*
 * package com.investsaudi.portal.facades.webinar.impl;
 * 
 * import com.investsaudi.portal.core.model.InvestSaudiWebinarModel; import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationModel; import
 * com.investsaudi.portal.core.model.InvestSaudiWebinarRegistrationProcessModel;
 * import
 * com.investsaudi.portal.core.service.InvestSaudiWebinarRegistrationService;
 * import com.investsaudi.portal.core.service.InvestSaudiWebinarService; import
 * com.investsaudi.portal.facades.webinar.InvestSaudiWebinarRegistrationFacade;
 * import de.hybris.platform.core.model.media.MediaModel; import
 * de.hybris.platform.processengine.BusinessProcessService; import
 * de.hybris.platform.servicelayer.config.ConfigurationService; import
 * de.hybris.platform.site.BaseSiteService; import
 * de.hybris.platform.store.services.BaseStoreService; import
 * de.hybris.platform.util.Config; import
 * org.apache.commons.collections4.CollectionUtils; import
 * org.apache.commons.lang3.StringUtils;
 * 
 * import javax.annotation.Resource; import
 * javax.print.attribute.standard.Media; import java.util.Collection; import
 * java.util.Optional; import java.util.stream.Collectors;
 * 
 * import static org.apache.commons.collections4.CollectionUtils.*;
 * 
 * public class InvestSaudiWebinarRegistrationFacadeImpl implements
 * InvestSaudiWebinarRegistrationFacade {
 * 
 * private static final String LINKEDIN = "linkedin"; private static final
 * String YOUTUBE = "youtube"; private static final String LOGO = "logo";
 * private static final String TWITTER = "twitter"; private static final String
 * ONLINE = "Online";
 * 
 * @Resource private InvestSaudiWebinarRegistrationService
 * investSaudiWebinarRegistrationService;
 * 
 * @Resource private InvestSaudiWebinarService investSaudiWebinarService;
 * 
 * @Resource private BusinessProcessService businessProcessService;
 * 
 * @Resource private BaseSiteService baseSiteService;
 * 
 * @Resource private BaseStoreService baseStoreService;
 * 
 * @Resource(name = "configurationService") private ConfigurationService
 * configurationService;
 * 
 * @Override public void sendEmailRegistration(String userEmail, String
 * webinarCode) {
 * 
 * InvestSaudiWebinarRegistrationModel investSaudiWebinarRegistrationModel =
 * investSaudiWebinarRegistrationService.createNewWebinarRegistration(userEmail,
 * webinarCode); InvestSaudiWebinarModel investSaudiWebinarModel =
 * investSaudiWebinarService.addNewRegistrationToWebinar(
 * investSaudiWebinarRegistrationModel);
 * 
 * final InvestSaudiWebinarRegistrationProcessModel
 * investSaudiWebinarRegistrationProcessModel =
 * businessProcessService.createProcess( "InvestSaudiWebinarRegistrationProcess"
 * + System.currentTimeMillis(), "InvestSaudiWebinarRegistrationProcess");
 * 
 * populate(investSaudiWebinarRegistrationProcessModel, userEmail,
 * investSaudiWebinarModel); businessProcessService.startProcess(
 * investSaudiWebinarRegistrationProcessModel);
 * 
 * }
 * 
 * private void populate(InvestSaudiWebinarRegistrationProcessModel target,
 * String userEmail, InvestSaudiWebinarModel investSaudiWebinarModel) {
 * 
 * final String fromEmail = Config.getString("from.email",
 * "live@investsaudi.sa"); final String baseUrl = getBaseUrl();
 * 
 * Collection<MediaModel> mediaModelCollection =
 * getOnlineCatalogMedias(investSaudiWebinarService.getEmailMedias());
 * 
 * target.setSite(baseSiteService.getCurrentBaseSite());
 * target.setStore(baseStoreService.getCurrentBaseStore());
 * target.setLanguage(baseStoreService.getCurrentBaseStore().getDefaultLanguage(
 * )); target.setUserEmail(userEmail);
 * target.setLinkUrl(investSaudiWebinarModel.getUrl());
 * target.setImageUrl(baseUrl +
 * extractMediaUrl(investSaudiWebinarModel.getImage()));
 * target.setFromEmail(fromEmail); target.setLinkedinImageUrl(baseUrl +
 * extractMediaUrl(extractMediasForEmail(mediaModelCollection, LINKEDIN)));
 * target.setYoutubeImageUrl(baseUrl +
 * extractMediaUrl(extractMediasForEmail(mediaModelCollection, YOUTUBE)));
 * target.setLogoImageUrl(baseUrl +
 * extractMediaUrl(extractMediasForEmail(mediaModelCollection, LOGO)));
 * target.setTwitterImageUrl(baseUrl +
 * extractMediaUrl(extractMediasForEmail(mediaModelCollection, TWITTER)));
 * 
 * }
 * 
 * private String extractMediaUrl(MediaModel mediaModel) { return mediaModel !=
 * null ? mediaModel.getURL() : StringUtils.EMPTY; }
 * 
 * private String getBaseUrl() { return
 * configurationService.getConfiguration().getString(
 * "media.investsaudiportal.https"); }
 * 
 * private MediaModel extractMediasForEmail(Collection<MediaModel> collection,
 * String mediaName) {
 * 
 * Optional<MediaModel> optionalMediaModel =
 * collection.stream().filter(mediaModel ->
 * mediaModel.getCode().contains(mediaName)).findFirst();
 * 
 * return optionalMediaModel.orElse(null); }
 * 
 * private Collection<MediaModel> getOnlineCatalogMedias(Collection<MediaModel>
 * collection) {
 * 
 * return emptyIfNull(collection).stream().filter(mediaModel ->
 * mediaModel.getCatalogVersion().getVersion().equalsIgnoreCase(ONLINE)).collect
 * (Collectors.toList()); }
 * 
 * 
 * }
 */