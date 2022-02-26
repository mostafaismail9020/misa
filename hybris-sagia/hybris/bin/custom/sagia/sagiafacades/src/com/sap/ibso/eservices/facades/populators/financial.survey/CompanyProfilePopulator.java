package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import de.hybris.platform.commercefacades.user.data.CompanyProfileData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class CompanyProfilePopulator implements Populator<SagiaCompanyProfileModel, CompanyProfileData> {


    @Resource
    private SagiaFormatProvider sagiaFormatProvider ;



    @Override
    public void populate(SagiaCompanyProfileModel companyProfileModel, CompanyProfileData companyProfile) throws ConversionException {

        companyProfile.setCompanyName(companyProfileModel.getCompanyName());
        companyProfile.setCommercialRegistrationNo(companyProfileModel.getCommercialRegistrationNo());
        companyProfile.setUnifiedNo700(companyProfileModel.getUnifiedNo700());
        companyProfile.setCrIssueDate(sagiaFormatProvider.formatBackEndDateToUIStr(companyProfileModel.getCrIssueDate()));
        companyProfile.setIncorporationDate(sagiaFormatProvider.formatBackEndDateToUIStr(companyProfileModel.getIncorporationDate()));
        companyProfile.setFinanceManagerEmail(companyProfileModel.getFinancialManagerEmail());
        companyProfile.setFinanceManagerName(companyProfileModel.getFinancialManagerName());
        companyProfile.setFinanceManagerTelephone(companyProfileModel.getFinancialManagerTelephone());
        companyProfile.setLegalStatus(companyProfileModel.getLegalStatus()==null?null:companyProfileModel.getLegalStatus().getCode());
    }
}
