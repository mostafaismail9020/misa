package com.sap.ibso.eservices.facades.populators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.facades.data.EntityInformationData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.SagiaLicenseData;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseTypeFacade;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;


public class LicenseEntityInformationPopulator implements Populator<EntityInformationModel, EntityInformationData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	
	@Resource
	private Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter;
	
	@Resource
	private Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter;

	@Resource
	private Converter<IsicMasterModel, IsicActivity> isicActivityConverter;
	
    @Resource(name = "sagiaLicenseTypeFacade")
    private SagiaLicenseTypeFacade sagiaLicenseTypeFacade;

	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private SagiaRegionDAO sagiaRegionDAO;

	@Override
	public void populate(EntityInformationModel source, EntityInformationData target) throws ConversionException {

		if (source.getLicense() != null) {
//			target.setLicense(getSagiaLicenseConverter().convert(source.getLicense()));
		}

		target.setLicenseType(source.getLicenseType().getCode());
//		target.setLegalStatus(source.getLegalStatus().getName());
		target.setHasAdvanceLicenseNr(source.isHasAdvanceLicenseNr());
		target.setAdvanceLicenseNr(source.getAdvanceLicenseNr());
		target.setIsEntrepreneur(source.isIsEntrepreneur());
		
		//RHQ Requirement START
		
		target.setIsMoreThan2Branch(source.isIsMoreThan2Branch());
		target.setIsMoreThan6Branch(source.isIsMoreThan6Branch());
		target.setIsEntityAssetMoreThanThreshold(source.isIsEntityAssetMoreThanThreshold());
		target.setIsEntityListedInStockMarket(source.isIsEntityListedInStockMarket());
		target.setIsEntityRevenueMoreThanThreshold(source.isIsEntityRevenueMoreThanThreshold());
		if (source.getMainBranchCR() != null) {
			target.setMainBranchCR(getMediaConverter().convert(source.getMainBranchCR()));
		}
		if (source.getOtherBranchCR1() != null) {
			target.setOtherBranchCR1(getMediaConverter().convert(source.getOtherBranchCR1()));
		}
		if (source.getOtherBranchCR2() != null) {
			target.setOtherBranchCR2(getMediaConverter().convert(source.getOtherBranchCR2()));
		}
		if (source.getRhqStockMarketAttachment() != null) {
			target.setRhqStockMarketAttachment(getMediaConverter().convert(source.getRhqStockMarketAttachment()));
		}
		if (source.getRhqEntityAssetAttachment() != null) {
			target.setRhqEntityAssetAttachment(getMediaConverter().convert(source.getRhqEntityAssetAttachment()));
		}
		if (source.getRhqEntityRevenueAttachment() != null) {
			target.setRhqEntityRevenueAttachment(getMediaConverter().convert(source.getRhqEntityRevenueAttachment()));
		}
		if (source.getRhqCR1() != null) {
			target.setRhqCR1(getMediaConverter().convert(source.getRhqCR1()));
		}
		if (source.getRhqCR2() != null) {
			target.setRhqCR2(getMediaConverter().convert(source.getRhqCR2()));
		}
		if (source.getRhqCR3() != null) {
			target.setRhqCR3(getMediaConverter().convert(source.getRhqCR3()));
		}
		if (source.getRhqCR4() != null) {
			target.setRhqCR4(getMediaConverter().convert(source.getRhqCR4()));
		}

		if(!source.getListOfRhqCountries().isEmpty())
		{
			final List<String> listOfRhqCountries = new ArrayList<String>();
			for (final String country : source.getListOfRhqCountries())
			{
				listOfRhqCountries.add(sagiaCountryDAO.getCountryCodeForName(country).getCode());
			}
		
		target.setListOfRhqCountries(listOfRhqCountries);
		}
		if(!source.getListOfRhqRegions().isEmpty())
		{
			final List<String> listOfRhqRegions = new ArrayList<String>();
			for (final String region : source.getListOfRhqRegions())
			{
				listOfRhqRegions.add(sagiaRegionDAO.getRhqRegionCodeForName(region).getCode());
			}
		target.setListOfRhqRegions(listOfRhqRegions);
		}
			
		//RHQ Requirement END
	
		if (source.getBoardResolutionFile() != null) {
			target.setBoardResolutionFile(getMediaConverter().convert(source.getBoardResolutionFile()));
		}
		if (source.getLetterOfSupportFile() != null) {
			target.setLetterOfSupportFile(getMediaConverter().convert(source.getLetterOfSupportFile()));
		}
		target.setLicenseDuration(source.getLicenseDuration());
		target.setEntityName(source.getEntityName());
		target.setEntityNameArabic(source.getEntityNameArabic());
		target.setLegalStatus(source.getLegalStatus().getCode());
		target.setLegalStatusText(source.getLegalStatus().getName());
		target.setBasicInfoExtendedMultinationalCompany(source.getBasicInfoExtendedMultinationalCompany());
		target.setCapital(source.getCapital());
		target.setEmail(source.getEmail());
		target.setTelephone(source.getTelephone());
		target.setMobilePhone(source.getMobilePhone());
		target.setCountryCodeForMobilePhone(source.getCountryCodeForMobilePhone());
		target.setCountryCodeForTelephone(source.getCountryCodeForTelephone());
		target.setWebsite(source.getWebsite());
		target.setCountry(source.getCountry().getCode());
		target.setCountryText(source.getCountry().getName());
		target.setRegion(source.getRegion().getCode());
		target.setRegionText(source.getRegion().getName());
		target.setCity(source.getCity().getCode());
		target.setCityText(source.getCity().getName());
		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setInvestment(source.getInvestment());
		target.setTemporaryLicenseText(source.getTemporaryLicenseText());

		LicenseType licenseTypeForCode = sagiaLicenseTypeFacade.getLicenseTypeForCode(source.getLicenseType().getCode());
		target.setLicenseTypeText(licenseTypeForCode.getLicenseTypeText());

		List<IsicMasterModel> isicActivityList = source.getIsicActivities();
		if(isicActivityList != null)
		{
			List<IsicActivity> isicActivities = isicActivityConverter.convertAll(isicActivityList);
			target.setIsicActivities(isicActivities);
		}
		
		target.setProfessionalLicenseCr(source.getProfessionalLicenseCr());
		target.setHasProfessionalLicenseCr(source.isHasProfessionalLicenseCr());
		target.setProfessionalLicenseCrVerified(source.isProfessionalLicenseCrVerified());
		
		target.setIsPreApprovalNumber(source.isIsPreApprovalNumber());
		target.setPreApprovalNumber(source.getPreApprovalNumber());
		if (source.getFinancialStatementFile() != null) {
			target.setFinancialStatementFile(getMediaConverter().convert(source.getFinancialStatementFile()));
		}
		if (source.getIqamaFile() != null) {
			target.setIqamaFile(getMediaConverter().convert(source.getIqamaFile()));
		}
		if (source.getCrCertificateFile() != null) {
			target.setCrCertificateFile(getMediaConverter().convert(source.getCrCertificateFile()));
		}
		if (source.getGosiCertificateFile() != null) {
			target.setGosiCertificateFile(getMediaConverter().convert(source.getGosiCertificateFile()));
		}
		if (source.getNoObjectionCertificateFile() != null) {
			target.setNoObjectionCertificateFile(getMediaConverter().convert(source.getNoObjectionCertificateFile()));
		}
	}

	public Converter<MediaModel, MediaData> getMediaConverter() {
		return mediaConverter;
	}

	public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
		this.mediaConverter = mediaConverter;
	}

	public Converter<SagiaCountryModel, SagiaCountryData> getSagiaCountryConverter() {
		return sagiaCountryConverter;
	}

	public void setSagiaCountryConverter(Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter) {
		this.sagiaCountryConverter = sagiaCountryConverter;
	}

	public Converter<SagiaLicenseModel, SagiaLicenseData> getSagiaLicenseConverter() {
		return sagiaLicenseConverter;
	}

	public void setSagiaLicenseConverter(Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter) {
		this.sagiaLicenseConverter = sagiaLicenseConverter;
	}
}
