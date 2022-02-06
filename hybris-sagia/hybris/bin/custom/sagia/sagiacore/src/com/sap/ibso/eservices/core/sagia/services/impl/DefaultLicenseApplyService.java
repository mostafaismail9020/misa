package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.enums.LicenseStatus;
import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.LicenseApplyDAO;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DefaultLicenseApplyService implements LicenseApplyService {

	private final static Logger LOG = Logger.getLogger(DefaultLicenseApplyService.class.getName());
	private final static String NEW_VERSION_MESSAGE = "This License was rejected and the customer reapplied with a new license";
	@Resource
	private UserService userService;

	@Resource
	private ModelService modelService;

	@Resource
	private CMSSiteService cmsSiteService;

	@Resource
	private CatalogService catalogService;

	@Resource
	private MediaService mediaService;
	
	@Resource
	private LicenseApplyDAO licenseApplyDAO;

	private KeyGenerator keyGenerator;
	
	@Resource(name="shareHolderCodeGenerator")
	private KeyGenerator shareHolderCodeGenerator ;

	@Override
	public SagiaLicenseModel getLicense(LicenseStatus licenseStatus) {
		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		List<SagiaLicenseModel> licenses = customer.getLicenses();
		if(licenses != null)
		return licenses.stream()
				.filter(license -> (license.getStatus() != null && license.getStatus().equals(licenseStatus)))
				.findFirst().orElse(null);

		return null;
	}

	@Override
	public SagiaLicenseModel getCurrentProcessedLicense() {
		
		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		String applicationServiceRequestID = customer.getApplicationServiceRequestID();
		if(StringUtils.isNotBlank(applicationServiceRequestID))
		{
			List<SagiaLicenseModel> licenses = customer.getLicenses();
			if(licenses != null)
				return licenses.stream()
						.filter(license -> (license.getApplicationServiceRequestID() != null && license.getApplicationServiceRequestID().equals(applicationServiceRequestID) && license.getStatus() != null && license.getStatus().equals(LicenseStatus.PROCESSED)))
						.findFirst().orElse(null);
		}

		return null;
	}
	
	@Override
	public SagiaLicenseModel getDraftLicense() {
		return getLicense(LicenseStatus.DRAFT);
	}
	
	@Override
	public EntityInformationModel getEntityInformation() {
		SagiaLicenseModel license = getDraftLicense();
		if (license != null) {
			return license.getEntityInformation();
		}
		return null;
	}

	@Override
	public List<ShareHolderModel> getShareHolders() {
		SagiaLicenseModel license = getDraftLicense();
		if (license != null) {
			return license.getShareHolders();
		}
		return null;
	}

	@Override
	public ShareHolderModel getShareHolder(String code) {
		List<ShareHolderModel> shareHolderModels = getShareHolders();

		for (ShareHolderModel shareHolderModelFound : shareHolderModels){
			if(shareHolderModelFound.getCode().equals(code)){
				return shareHolderModelFound;
			}
		}
		return null;
	}

	@Override
	public void removeShareholder(String code) {
		List<ShareHolderModel> shareHoldersList = getShareHolders();
		if(shareHoldersList != null)
		{
			ShareHolderModel tobeRemoved = shareHoldersList.stream()
					.filter(shareHolderModel -> shareHolderModel.getCode().equals(code))
					.findFirst().orElse(null);
			if(tobeRemoved != null)
			{
				modelService.remove(tobeRemoved);
				modelService.refresh(getDraftLicense()); //Update the license list so that it reflect on the UI
			}
		}
	}

	@Override
	public ContactPersonModel getContactPerson() {
		SagiaLicenseModel license = getDraftLicense();
		if (license != null) {
			return license.getContactPerson();
		}
		return null;
	}

	public void saveEntityInformation(EntityInformationModel entityInformationModel) {

		SagiaLicenseModel license = getDraftLicense();

		if (license == null) {
			license = generateLicense();
		}

		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		license.setCustomerApplicantReferenceID(customer.getApplicantReferenceID());

		license.setEntityInformation(entityInformationModel);
		entityInformationModel.setLicense(license);
		modelService.saveAll(entityInformationModel,license);
	}

	private SagiaLicenseModel generateLicense() {
		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		SagiaLicenseModel license = modelService.create(SagiaLicenseModel.class);

		license.setCode(generateLicenseCode());

		license.setCustomer(customer);
		license.setStatus(LicenseStatus.DRAFT);
		modelService.save(license);
		modelService.refresh(customer);
		return license;
	}
	
	public SagiaLicenseModel cloneLicense(SagiaLicenseModel originalLicense) {

		
		SagiaLicenseModel draft = modelService.clone(originalLicense) ;
		

		// update the license.
		
		  if(draft != null) { 
			  draft.setStatus(LicenseStatus.DRAFT);
			  draft.setApplicantReferenceID("");
			  draft.setApplicationServiceRequestID("");
			  draft.setCode(generateLicenseCode());
			  
				
				//update shareholders code:
				List<ShareHolderModel> listShareholder = draft.getShareHolders();
				for (ShareHolderModel shareHolderModel : listShareholder) {
					shareHolderModel.setCode(generateShareHolderCodeCode());
					modelService.save(shareHolderModel);
					modelService.refresh(shareHolderModel);
				}
				
		      modelService.save(draft); 
		      modelService.refresh(draft);
		    
		   }	  
		
		
		
			originalLicense.setVersioning(NEW_VERSION_MESSAGE);
			modelService.save(originalLicense);
			
			
		
		return draft;
	
	}

	@Override
	public List<RhqActivitiesModel> getCorporateActivities() {
		return licenseApplyDAO.getCorporateActivities();
	}

	@Override
	public List<RhqActivitiesModel> getStrategicActivities() {
		return licenseApplyDAO.getStrategicActivities();
	}

	@Override
	public List<RhqActivitiesModel> getManagementActivities() {
		return licenseApplyDAO.getManagementActivities();
	}

	@Override
	public String getSelectedCorporateActivities(String activity) {
		return licenseApplyDAO.getActivityDetailsForCode(activity).getDetails();
	}

	@Override
	public String getSelectedStrategicActivities(String activity) {
		return licenseApplyDAO.getActivityDetailsForCode(activity).getDetails();
	}

	@Override
	public String getSelectedManagementActivities(String activity) {
		return licenseApplyDAO.getActivityDetailsForCode(activity).getDetails();
	}

	private String generateLicenseCode(){
		final Object generatedValue = keyGenerator.generate();
		if (generatedValue instanceof String)
		{
			return (String) generatedValue;
		}
		else
		{
			return String.valueOf(generatedValue);
		}
	}
	
	private String generateShareHolderCodeCode(){
		final Object generatedValue = shareHolderCodeGenerator.generate();
		if (generatedValue instanceof String)
		{
			return (String) generatedValue;
		}
		else
		{
			return String.valueOf(generatedValue);
		}
	}

	@Override
	public void saveShareHolderAndLicense(ShareHolderModel shareHolderModel, SagiaLicenseModel sagiaLicenseModel) {
		if (sagiaLicenseModel != null && shareHolderModel != null) {
			modelService.saveAll(shareHolderModel, sagiaLicenseModel);
		}
	}

	@Override
	public void saveContactPerson(ContactPersonModel contactPersonModel) {
		SagiaLicenseModel license = getDraftLicense();
		contactPersonModel.setLicense(license);
		license.setContactPerson(contactPersonModel);
		modelService.save(license);
	}

	@Override
	public void saveShareHolderData(ShareHolderModel shareHolderModel) {
		modelService.save(shareHolderModel);
	}

	@Override
	public MediaModel uploadFile(final InputStream in, final String fileName, String realFileName)
	{
		final String currentCatalogVersion = cmsSiteService.getCurrentSite().getContentCatalogs().get(0).getId();
		final CatalogVersionModel catalogVersionModel = catalogService.getCatalogVersion(currentCatalogVersion, "Online");
		final MediaModel mediaModel = new MediaModel();
		mediaModel.setCode(fileName);
		mediaModel.setCatalogVersion(catalogVersionModel);
		mediaModel.setRealFileName(realFileName);
		modelService.saveAll(mediaModel);

		mediaService.setStreamForMedia(mediaModel, in);
		try
		{
			in.close();
		}
		catch (final IOException ex)
		{
			LOG.warn(ex.getMessage());
			ex.printStackTrace();
		}

		return mediaModel;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}


	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	@Override
	public List<SagiaLicenseModel> getPendingLicenses() {
		
		return licenseApplyDAO.getPendingLicenses();
	}

	

}
