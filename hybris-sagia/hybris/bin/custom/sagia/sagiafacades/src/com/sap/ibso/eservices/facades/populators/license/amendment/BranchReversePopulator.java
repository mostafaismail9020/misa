package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.Branch;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.BranchData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class BranchReversePopulator implements Populator<Branch, BranchData> {

    private static final String COUNTRY = "SA"; // Country has identical value for arabic and english in fiori sagia; Hardcoded here

    @Override
    public void populate(Branch branch, BranchData branchData) {
        branchData.setBpID(branch.getBpId());
        branchData.setBranchType(branch.getType());
        branchData.setBranTypDesc(branch.getTypeDescription());
        branchData.setBranchDesc(branch.getName());
        branchData.setCountry(COUNTRY);
        branchData.setMainBranch(branch.getMain());
        branchData.setAction(branch.getAction());
        branchData.setBrAddrNum(branch.getAddrNumber());
        branchData.setStreet(branch.getAddress().getStreet());
        branchData.setHouseNo(branch.getAddress().getNumber());
        branchData.setZipCode(branch.getAddress().getZipCode());
        branchData.setRegion(branch.getAddress().getRegion());
        branchData.setRegionDesc(branch.getAddress().getRegionDescription());
        branchData.setCityCode(branch.getAddress().getCity());
        branchData.setCity(branch.getAddress().getCityDescription());
        branchData.setTelephone(branch.getAddress().getTelephone());
        branchData.setEmail(branch.getAddress().getEmail());
        branchData.setWebsite(branch.getAddress().getWebsite());
    }
}