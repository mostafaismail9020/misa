package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.data.zqeemah.DelegateInfo;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ShareholderData;
import de.hybris.platform.converters.Populator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.time.chrono.HijrahChronology;
import java.util.Date;

/**
 *
 */
public class ShareholderReversePopulator implements Populator<Shareholder, ShareholderData> {

	private static final Logger LOG = Logger.getLogger(ShareholderReversePopulator.class);

	private static final String SHAREHOLDER_PERSON = "1";
	public static final String DATE_FORMAT = "yyyyMMdd";
	private static final String SHAREHOLDER_CREATE_ACTION = "01";
	private static final String SHAREHOLDER_TYPE = "2";

	private SagiaFormatProvider sagiaFormatProvider;

	/*
	 * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not
	 * be too complex The structure of this method is not difficult to understand in
	 * the given context.
	 */
	@SuppressWarnings({ "squid:MethodCyclomaticComplexity" })
	@Override
	public void populate(Shareholder shareholder, ShareholderData shareholderData) {
		shareholderData.setBpID(shareholder.getBpId());
		shareholderData.setShBpID(shareholder.getShBpId());
		shareholderData.setBpType(shareholder.getBpType());
		shareholderData.setFirstname(shareholder.getFirstName());
		shareholderData.setNameEng(shareholder.getEnglishName());
		shareholderData.setSecondname(shareholder.getSecondName());
		shareholderData.setMultiNatComp(shareholder.getMultinationalCompany());
		shareholderData.setIndustry(shareholder.getIndustry());
		shareholderData.setIndDesc(shareholder.getIndustryDescription());
		shareholderData.setSubsector(shareholder.getSubsector());
		shareholderData.setLegalStatus(shareholder.getLegalStatus());
		shareholderData.setLegalStat_desc(shareholder.getLegalStatusDescription());
		shareholderData.setLaboursize(shareholder.getLabourSize());
		shareholderData.setCapitals(shareholder.getCapital());
		shareholderData.setGender(shareholder.getGender());
		shareholderData.setGenderDesc(shareholder.getGenderDescription());
		shareholderData.setMaritalStatus(shareholder.getMaritalStatus());
		shareholderData.setMaritalDesc(shareholder.getMaritalStatus());
		shareholderData.setAcademicTitle(shareholder.getAcademicTitle());
		shareholderData.setOrgCountry(shareholder.getCompanyCountry());
		shareholderData.setOrgCRNumber(shareholder.getInputCRNumber());

		shareholderData.setOrgMCVer(odataBoolean((shareholder.getIsCrVerified())));

		
		
		
		try {

			if (StringUtils.isNotBlank(shareholder.getIdType()) && "1".equals(shareholder.getIdType())) {
				shareholderData.setBirthdate(shareholder.getBirthDateString() != null
						? sagiaFormatProvider.getLocalDateTimeFromuiUAQDateString(shareholder.getBirthDateString(),HijrahChronology.INSTANCE)
								: null);
			} else {
				shareholderData.setBirthdate(shareholder.getBirthDateString() != null
						? sagiaFormatProvider.getDateTimeFromUIDateString(shareholder.getBirthDateString())
								: null);
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		shareholderData.setNationality(shareholder.getNationalityCurrent());
		shareholderData.setNatioDesc(shareholder.getNationalityCurrentDescription());
		shareholderData.setPrevNational(shareholder.getNationalityPrevious());
		shareholderData.setPreNatDesc(shareholder.getNationalityPreviousDescription());
		shareholderData.setPercentage(shareholder.getPercentage());
		shareholderData.setShInherProp(shareholder.getInheritedProperty());
		shareholderData.setShDocId(shareholder.getDocumentId());
		shareholderData.setAction(shareholder.getAction());
		shareholderData.setStreet(shareholder.getAddress().getStreet());
		shareholderData.setHouseNo(shareholder.getAddress().getNumber());
		shareholderData.setCountry(shareholder.getAddress().getCountry());
		shareholderData.setZipCode(shareholder.getAddress().getZipCode());
		shareholderData.setCity(shareholder.getAddress().getCity());
		shareholderData.setTelephone(shareholder.getAddress().getTelephone());
		shareholderData.setEmail(shareholder.getAddress().getEmail());
		shareholderData.setWebsite(shareholder.getAddress().getWebsite());
		shareholderData.setPremiumResident(shareholder.getPremiumResident());
		shareholderData.setPremiumResidentDesc(shareholder.getPremiumResidentDescription());
		
		shareholderData.setDeedNumber(shareholder.getDeedNumber());
		shareholderData.setDeceasedId(shareholder.getDeceasedId());
		shareholderData.setDeceasedName(shareholder.getDeceasedName());
		shareholderData.setIsMojVerified(shareholder.getIsMojVerified());
		LOG.info("####ShareholderReversePopulator: deedNo: "+shareholder.getDeedNumber()+" **DeceasedId: "+shareholder.getDeceasedId()+" ** DeceasedName: "+shareholder.getDeceasedName()+" **IsMojVerified: "+shareholder.getIsMojVerified());

		String idVerified = "true";
		if ("4".equals(shareholder.getIdType())) {
			idVerified = "false";
			shareholderData.setSelfDelegate("T");
		}
		if(SHAREHOLDER_TYPE.equals(shareholder.getBpType()) || !SHAREHOLDER_CREATE_ACTION.equals(shareholder.getAction())) {
			idVerified = "false";
		}
		shareholderData.setPerNICVerified(odataBoolean(idVerified));

		shareholderData.setPerIDNum(shareholder.getIdNumber());
		shareholderData.setPerIDType(shareholder.getIdType());

		
		// TODO - verify if is needed to check if (SelfDelegate == false) before start
		// populating the delegate
		if (shareholder.getDelegate() != null && shareholder.getDelegate().getFullNameEnglish() != null
				&& !"".equals(shareholder.getDelegate().getFullNameEnglish())) {
			DelegateInfo delegate = shareholder.getDelegate();

			shareholderData.setSelfDelegate("F");
			// shareholderData.setIsPerDelegateNICVerified(shareholder.getIsPerDelegateNICVerified());

			String delVerified = "true";
			if ("4".equals(delegate.getIdType())) {
				delVerified = "false";
			}
			shareholderData.setIsPerDelegateNICVerified(odataBoolean(delVerified));

			shareholderData.setDeleIDType(delegate.getIdType());
			shareholderData.setDeleIDNumber(delegate.getIdNumber());

			shareholderData.setDeleFirstNameArabic(delegate.getFirstNameArabic());
			shareholderData.setDeleLastNameArabic(delegate.getLastNameArabic());
			shareholderData.setDeleNameEnglish(delegate.getFullNameEnglish());
			shareholderData.setDeleGender(delegate.getGender());
			try {

				if (StringUtils.isNotBlank(delegate.getIdType()) && "1".equals(delegate.getIdType())) {
					shareholderData.setDeleDOB(delegate.getDateofBirth() != null
							? sagiaFormatProvider.getLocalDateTimeFromuiUAQDateString(delegate.getDateofBirth(),HijrahChronology.INSTANCE)
									: null);
				} else {
					shareholderData.setDeleDOB(delegate.getDateofBirth() != null
							? sagiaFormatProvider.getDateTimeFromUIDateString(delegate.getDateofBirth())
									: null);
				}
				
				shareholderData.setDeleIDIssueDate(delegate.getIssueDate() != null
						? sagiaFormatProvider.getDateTimeFromUIDateString(delegate.getIssueDate())
						: null);
				shareholderData.setDeleIDExpiryDate(delegate.getExpiryDate() != null
						? sagiaFormatProvider.getDateTimeFromUIDateString(delegate.getExpiryDate())
						: null);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}

			shareholderData.setDeleCountry(delegate.getCountry());
			shareholderData.setDeleNationality(delegate.getNationality());
			shareholderData.setDeleTeleCCode(delegate.getCountryCodeForTelephone());
			shareholderData.setDeleTeleNumber(delegate.getTelephone());
			shareholderData.setDeleMobCcode(delegate.getCountryCodeForMobile());
			shareholderData.setDeleMobNumber(delegate.getMobile());
			shareholderData.setDeleEmail(delegate.getEmail());
			shareholderData.setDelePostalCode(delegate.getPostalCode());
			shareholderData.setDelePoBox(delegate.getPoBox());
		}
	}

	/**
	 * @return
	 */
	public SagiaFormatProvider getSagiaFormatProvider() {
		return sagiaFormatProvider;
	}

	private String odataBoolean(String value) {
		if (null != value)
			return sagiaFormatProvider.formatBooleanForODATA(Boolean.valueOf(value));
		else
			return "";
	}

	/**
	 * @param sagiaFormatProvider
	 */
	public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
	}
}
