package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.Address;
import com.sap.ibso.eservices.facades.data.license.amendment.Branch;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.BranchData;
import de.hybris.platform.converters.Populator;

public class BranchPopulator implements Populator<BranchData, Branch> {
    @Override
    public void populate(BranchData branchData, Branch branch) {
        branch.setType(branchData.getBranchType());
        branch.setTypeDescription(branchData.getBranTypDesc());
        branch.setName(branchData.getBranchDesc());
        branch.setMain(branchData.getMainBranch());
        branch.setBpId(branchData.getBpID());
        branch.setAction(branchData.getAction());
        branch.setAddrNumber(branchData.getBrAddrNum());

        Address address = new Address();
        address.setStreet(branchData.getStreet());
        address.setNumber(branchData.getHouseNo());
        address.setCountry(branchData.getCountry());
        address.setZipCode(branchData.getZipCode());
        address.setCity(branchData.getCityCode());
        address.setCityDescription(branchData.getCity());
        address.setRegion(branchData.getRegion());
        address.setRegionDescription(branchData.getRegionDesc());
        address.setTelephone(branchData.getTelephone());
        address.setEmail(branchData.getEmail());
        address.setWebsite(branchData.getWebsite());
        branch.setAddress(address);
    }
}
