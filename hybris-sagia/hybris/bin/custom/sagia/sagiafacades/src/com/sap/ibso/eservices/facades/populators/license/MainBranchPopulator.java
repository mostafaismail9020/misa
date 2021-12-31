package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class MainBranchPopulator implements Populator<GovtHeaderData,BranchData> {
    @Override
    public void populate(GovtHeaderData branch, BranchData branchData) {
        branchData.setZakatNumber(branch.getZakatNo());
        branchData.setCommercialRegisterNumber(branch.getCrNo());
        branchData.setGosiNumber(branch.getGosiNo());
        branchData.setMolNumber(branch.getMolNo());
        branchData.setShopLicenseNumber(branch.getShopLicNo());
        branchData.setAmanah(branch.getAmanah());
        branchData.setStatus(branch.getStatus());
        branchData.setStatusCode(branch.getStatusCode());
    }
}
