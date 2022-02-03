package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;
import com.sap.ibso.eservices.facades.data.license.amendment.Address;
import com.sap.ibso.eservices.facades.data.license.amendment.Branch;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class FinancialSurveyBranchPopulator implements Populator<FinancialSurveyBranchModel, Branch> {


    @Resource
    private FinancialSurveyAddressPopulator financialSurveyAddressPopulator;

    @Override
    public void populate(FinancialSurveyBranchModel financialSurveyBranchModel, Branch branch) throws ConversionException {
        branch.setSrId(financialSurveyBranchModel.getPk().getLongValueAsString());
        branch.setAddrNumber(financialSurveyBranchModel.getAddrNumber());
        branch.setName(financialSurveyBranchModel.getName());
        branch.setType(financialSurveyBranchModel.getType());
        branch.setVolumeWeight(financialSurveyBranchModel.getWeight());
        branch.setTypeDescription(financialSurveyBranchModel.getTypeDescription());
        branch.setMain(financialSurveyBranchModel.getMain());
        Address address = new  Address();
        financialSurveyAddressPopulator.populate(financialSurveyBranchModel.getAddress(),address);
        branch.setAddress(address);
    }
}
