package com.sap.ibso.eservices.facades.populators.zqeemah;

import org.apache.commons.lang3.StringUtils;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.DelegateInfo;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.DelegateInfoData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DelegateInfoReversePopulator  implements Populator<DelegateInfo, DelegateInfoData> {

	public static final String DATE_FORMAT_RECEIVED = "MMM d, yyyy";
	public static final String DATE_FORMAT_RETURNED = "YYYYMMDD";
	private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(DelegateInfo source, DelegateInfoData target) throws ConversionException {
    	
    	  if("true".equalsIgnoreCase(source.getNicVerified()))
    	  {
    		  target.setNicVerified("X");
    	  }
    	  target.setEntityNo(source.getEntityNo());
		  target.setFirstname(source.getFirstNameArabic());
		  target.setLastname(source.getLastNameArabic());
		  target.setFullname(source.getFullNameEnglish());
		  target.setNationality(source.getNationality());
		  target.setGender(source.getGender());
		  target.setCcodeTele(source.getCountryCodeForTelephone());
		  target.setCcodeMobile(source.getCountryCodeForMobile());
		  target.setTelephone(source.getTelephone());
		  target.setMobile(source.getMobile());
		  target.setEmail(source.getEmail());
		  target.setCountry(source.getCountry());
		  target.setPoBox(source.getPoBox());
		  target.setPostalCode(source.getPostalCode());
		  target.setIdNumber(source.getIdNumber());
		  target.setIdType(source.getIdType());
		  populateDateFields(source, target); 
    }

	private void populateDateFields(DelegateInfo source, DelegateInfoData target) {
		if(StringUtils.isNotBlank(source.getIdType()) && "1".equals(source.getIdType()))
		{
			target.setIdIssueDate(source.getIssueDate()!=null ? sagiaFormatProvider.convertuiUAQStrToBackEndGreStr(source.getIssueDate()) : null);
			target.setBirthdate(source.getDateofBirth()!=null ? sagiaFormatProvider.convertuiUAQStrToBackEndGreStr(source.getDateofBirth()) : null);
		}
		else
		{
			target.setIdIssueDate(source.getIssueDate()!=null ? sagiaFormatProvider.formatUIStrToBackStr(source.getIssueDate()) : null);
			target.setBirthdate(source.getDateofBirth()!=null ? sagiaFormatProvider.formatUIStrToBackStr(source.getDateofBirth()) : null);
		}
	}

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
