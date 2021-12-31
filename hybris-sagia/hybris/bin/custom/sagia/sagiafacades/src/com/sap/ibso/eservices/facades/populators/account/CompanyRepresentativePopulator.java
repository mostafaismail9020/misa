package com.sap.ibso.eservices.facades.populators.account;

import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SrvBPHdrContactData;
import de.hybris.platform.converters.Populator;

public class CompanyRepresentativePopulator implements Populator<SrvBPHdrContactData,ProfileCompanyRepresentativeData> {
    @Override
    public void populate(SrvBPHdrContactData srvBPHdrContactData, ProfileCompanyRepresentativeData profileCompanyRepresentativeData) {
        profileCompanyRepresentativeData.setFirstName(srvBPHdrContactData.getFirstName());
        profileCompanyRepresentativeData.setLastName(srvBPHdrContactData.getLastName());
        profileCompanyRepresentativeData.setMiddleName(srvBPHdrContactData.getMiddleName());
        profileCompanyRepresentativeData.setMobileNumber(srvBPHdrContactData.getMobileNumber());
        profileCompanyRepresentativeData.setEmail(srvBPHdrContactData.getEmail());
        profileCompanyRepresentativeData.setNationality(srvBPHdrContactData.getNationalID());
        profileCompanyRepresentativeData.setDocId(srvBPHdrContactData.getDocumentID());
        profileCompanyRepresentativeData.setContactType(srvBPHdrContactData.getContactType());
    }

}
