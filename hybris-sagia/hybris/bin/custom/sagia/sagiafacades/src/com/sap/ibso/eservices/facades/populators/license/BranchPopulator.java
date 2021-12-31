package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtBranchData;
import de.hybris.platform.converters.Populator;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.YES;

public class BranchPopulator implements Populator<GovtBranchData,BranchData> {
    @Override
    public void populate(GovtBranchData govtBranchData, BranchData branchData) {
        branchData.setCity(govtBranchData.getBranchCity());
        branchData.setName(govtBranchData.getBranchName());
        branchData.setType(govtBranchData.getBranchType());
        branchData.setHasMomra(YES.equalsIgnoreCase(govtBranchData.getMomraYesNo()) ? Boolean.TRUE : Boolean.FALSE);
        branchData.setHasCr(YES.equalsIgnoreCase(govtBranchData.getCrYesNo()) ? Boolean.TRUE : Boolean.FALSE);
    }
}
