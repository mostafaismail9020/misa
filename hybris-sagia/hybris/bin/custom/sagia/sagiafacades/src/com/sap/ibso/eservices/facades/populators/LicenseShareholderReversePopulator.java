package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.microsoft.sqlserver.jdbc.StringUtils;
import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.model.ExistingShareholderModel;
import com.sap.ibso.eservices.core.model.OrganizationShareholderModel;
import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.facades.data.ExistingShareholderData;
import com.sap.ibso.eservices.facades.data.OrganizationShareholderData;
import com.sap.ibso.eservices.facades.data.PersonShareholderData;
import com.sap.ibso.eservices.facades.data.ShareHoldersData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;


public class LicenseShareholderReversePopulator implements Populator<ShareHoldersData, ShareHolderModel> {

	@Resource
	private LicenseDelegateInformationReversePopulator licenseDelegateInformationReversePopulator;
	
	@Resource
	private LicenseExistingShareholderReversePopulator licenseExistingShareholderReversePopulator;
	
	@Resource
	private LicensePersonShareholderReversePopulator licensePersonShareholderReversePopulator;
	
	@Resource
	private LicenseOrganizationShareholderReversePopulator licenseOrganizationShareholderReversePopulator;

	@Resource
	private ModelService modelService;

	private KeyGenerator keyGenerator;
	
	@Override
	public void populate(ShareHoldersData source, ShareHolderModel target) throws ConversionException {
		
		
		if(StringUtils.isEmpty(source.getCode())){
			target.setCode(generateShareHolderCode());
		}

		target.setShareHolderType(source.getShareHolderType());
		target.setShareHolderIdType(source.getShareHolderIdType());
		if (source instanceof ExistingShareholderData) {
			ExistingShareholderModel model = (ExistingShareholderModel) target;

			ExistingShareholderData data = (ExistingShareholderData) source;
			getLicenseExistingShareholderReversePopulator().populate(data, model);
			target = model;
		} 
		else if (source instanceof PersonShareholderData) {
			PersonShareholderModel model = (PersonShareholderModel) target;

			PersonShareholderData data = (PersonShareholderData) source;
			getLicensePersonShareholderReversePopulator().populate(data, model);
			target = model;
		} 
		else if (source instanceof OrganizationShareholderData) {
			OrganizationShareholderModel model = (OrganizationShareholderModel) target;

			OrganizationShareholderData data = (OrganizationShareholderData) source;
			getLicenseOrganizationShareholderReversePopulator().populate(data, model);
			target = model;
		}
		
		if(source.getDelegateInfo()!=null) {
			DelegateInfoModel delegateInfoModel = target.getDelegateInfo();
			
			
			
			if(delegateInfoModel == null)
			{
				delegateInfoModel = getModelService().create(DelegateInfoModel.class);
			}
			if(!"4".equals(source.getShareHolderIdType()) && source instanceof PersonShareholderData)  {
				delegateInfoModel.setDelegateYourself(true);
			}
			
			delegateInfoModel.setShareholder(target);
			DelegateInformationData data = source.getDelegateInfo();
			getLicenseDelegateInformationReversePopulator().populate(data, delegateInfoModel);
			target.setDelegateInfo(delegateInfoModel);
			if(delegateInfoModel.getPk() != null)
			{
				if(delegateInfoModel.isDelegateYourself() || ( source instanceof OrganizationShareholderData && !delegateInfoModel.isDelegate() ) ){
					clearDelegateFields(delegateInfoModel);
				}

				//In case of edit scenario, otherwise it will save together with shareholder model
				modelService.save(delegateInfoModel);
			}
		}
	}

	private void clearDelegateFields(DelegateInfoModel delegateInfoModel) {
		delegateInfoModel.setDelegateIdentityNumber(null);
		delegateInfoModel.setDelegateDateOfBirth(null);
		delegateInfoModel.setDelegateIdentityType(null);
		delegateInfoModel.setIdIssueDate(null);
		delegateInfoModel.setIdExpiryDate(null);
		delegateInfoModel.setDelegateCountry(null);
		delegateInfoModel.setDelegateNationality(null);
		delegateInfoModel.setDelegateFullName(null);
		delegateInfoModel.setDelegateFirstNameArabic(null);
		delegateInfoModel.setDelegateLastNameArabic(null);
		delegateInfoModel.setDelegateTelephoneNumber(null);
		delegateInfoModel.setDelegateMobileNumber(null);
		delegateInfoModel.setDelegatePoBox(null);
		delegateInfoModel.setDelegatePostalCode(null);
		delegateInfoModel.setDelegateCountryCodeMobile(null);
		delegateInfoModel.setDelegateCountryCodeTel(null);
		delegateInfoModel.setGender(null);
		delegateInfoModel.setDelegateEmail(null);
		delegateInfoModel.setAuthorisationLetter(null);
		delegateInfoModel.setSaudiIdCopy(null);
	}

	private String generateShareHolderCode(){
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

	public LicenseDelegateInformationReversePopulator getLicenseDelegateInformationReversePopulator() {
		return licenseDelegateInformationReversePopulator;
	}

	public void setLicenseDelegateInformationReversePopulator(
			LicenseDelegateInformationReversePopulator licenseDelegateInformationReversePopulator) {
		this.licenseDelegateInformationReversePopulator = licenseDelegateInformationReversePopulator;
	}

	public LicenseExistingShareholderReversePopulator getLicenseExistingShareholderReversePopulator() {
		return licenseExistingShareholderReversePopulator;
	}

	public void setLicenseExistingShareholderReversePopulator(
			LicenseExistingShareholderReversePopulator licenseExistingShareholderReversePopulator) {
		this.licenseExistingShareholderReversePopulator = licenseExistingShareholderReversePopulator;
	}

	public LicensePersonShareholderReversePopulator getLicensePersonShareholderReversePopulator() {
		return licensePersonShareholderReversePopulator;
	}

	public void setLicensePersonShareholderReversePopulator(
			LicensePersonShareholderReversePopulator licensePersonShareholderReversePopulator) {
		this.licensePersonShareholderReversePopulator = licensePersonShareholderReversePopulator;
	}

	public LicenseOrganizationShareholderReversePopulator getLicenseOrganizationShareholderReversePopulator() {
		return licenseOrganizationShareholderReversePopulator;
	}

	public void setLicenseOrganizationShareholderReversePopulator(
			LicenseOrganizationShareholderReversePopulator licenseOrganizationShareholderReversePopulator) {
		this.licenseOrganizationShareholderReversePopulator = licenseOrganizationShareholderReversePopulator;
	}

	public ModelService getModelService() {
		return modelService;
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
}
