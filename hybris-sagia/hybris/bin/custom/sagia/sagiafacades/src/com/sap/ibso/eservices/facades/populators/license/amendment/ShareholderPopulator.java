package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.amendment.Address;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ShareholderData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ShareholderPopulator implements Populator<ShareholderData, Shareholder> {

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ShareholderData shareholderData, Shareholder shareholder) {
        shareholder.setBpId(shareholderData.getBpID());
        shareholder.setShBpId(shareholderData.getShBpID());
        shareholder.setBpType(shareholderData.getBpType());
        shareholder.setFirstName(shareholderData.getFirstname());
        shareholder.setEnglishName(shareholderData.getNameEng());
        shareholder.setSecondName(shareholderData.getSecondname());
        shareholder.setMultinationalCompany(shareholderData.getMultiNatComp());
        shareholder.setIndustry(shareholderData.getIndustry());
        shareholder.setIndustryDescription(shareholderData.getIndDesc());
        shareholder.setSubsector(shareholderData.getSubsector());
        shareholder.setLegalStatus(shareholderData.getLegalStatus());
        shareholder.setLegalStatusDescription(shareholderData.getLegalStat_desc());
        shareholder.setLabourSize(shareholderData.getLaboursize());
        shareholder.setCapital(shareholderData.getCapitals());
        shareholder.setGender(shareholderData.getGender());
        shareholder.setGenderDescription(shareholderData.getGenderDesc());
        shareholder.setMaritalStatus(shareholderData.getMaritalStatus());
        shareholder.setMaritalStatusDescription(shareholderData.getMaritalDesc());
        shareholder.setAcademicTitle(shareholderData.getAcademicTitle());
        shareholder.setAcademicTitleDescription(shareholderData.getAcaTitleDesc());
        shareholder.setPremiumResident(shareholderData.getPremiumResident());
        shareholder.setPremiumResidentDescription(shareholderData.getPremiumResidentDesc());
        if (shareholderData.getBirthdate() != null) {
            shareholder.setBirthDate(sagiaFormatProvider.getLocalizedDateData(shareholderData.getBirthdate()));
        }
        shareholder.setNationalityCurrent(shareholderData.getNationality());
        shareholder.setNationalityCurrentDescription(shareholderData.getNatioDesc());
        shareholder.setNationalityPrevious(shareholderData.getPrevNational());
        shareholder.setNationalityPreviousDescription(shareholderData.getPreNatDesc());
        shareholder.setPercentage(shareholderData.getPercentage());
        shareholder.setInheritedProperty(shareholderData.getShInherProp());
        shareholder.setDocumentId(shareholderData.getShDocId());
        shareholder.setAction(shareholderData.getAction());
        
        shareholder.setDeedNumber(shareholderData.getDeedNumber());
        shareholder.setDeceasedId(shareholderData.getDeceasedId());
        shareholder.setDeceasedName(shareholderData.getDeceasedName());
        shareholder.setIsMojVerified(shareholderData.getIsMojVerified());

        Address address = new Address();
        address.setStreet(shareholderData.getStreet());
        address.setNumber(shareholderData.getHouseNo());
        address.setCountry(shareholderData.getCountry());
        address.setZipCode(shareholderData.getZipCode());
        address.setCity(shareholderData.getCity());
        address.setTelephone(shareholderData.getTelephone());
        address.setEmail(shareholderData.getEmail());
        address.setWebsite(shareholderData.getWebsite());
        shareholder.setAddress(address);
        
      
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

}
