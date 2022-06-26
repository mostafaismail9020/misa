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
        branch.setAction("2");
        branch.setAddrNumber(financialSurveyBranchModel.getNumber());
        branch.setName(financialSurveyBranchModel.getName());
        branch.setType(financialSurveyBranchModel.getType());
        branch.setVolumeWeight(financialSurveyBranchModel.getWeight());
        branch.setTypeDescription(financialSurveyBranchModel.getTypeDescription());
        branch.setMain(financialSurveyBranchModel.getMain());
        Address address = new  Address();
        address.setCity(financialSurveyBranchModel.getCity()!=null?financialSurveyBranchModel.getCity().getCode():null);
        //address.setCityDescription(sagiaSurveyAddressModel.getCityDescription());
        address.setCountry(financialSurveyBranchModel.getCountry()!=null?financialSurveyBranchModel.getCountry().getCode():null);
        // address.setCountryDescription(sagiaSurveyAddressModel.getCountryDescription());
        address.setRegion(financialSurveyBranchModel.getRegion()!=null?financialSurveyBranchModel.getRegion().getCode():null);
        //   address.setRegionDescription(sagiaSurveyAddressModel.getRegionDescription());
        address.setStreet(financialSurveyBranchModel.getStreet());
        address.setWebsite(financialSurveyBranchModel.getWebsite());
        address.setNumber(financialSurveyBranchModel.getNumber());
        address.setZipCode(financialSurveyBranchModel.getZipCode());
        address.setTelephone(financialSurveyBranchModel.getTelephone());
        address.setEmail(financialSurveyBranchModel.getEmail());
       // financialSurveyAddressPopulator.populate(financialSurveyBranchModel.getAddress(),address);
        branch.setAddress(address);
    }
}
