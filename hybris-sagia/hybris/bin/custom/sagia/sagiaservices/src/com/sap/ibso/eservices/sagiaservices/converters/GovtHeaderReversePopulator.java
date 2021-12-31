package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtBranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GovtHeaderReversePopulator extends ODataReversePopulator<GovtHeaderData> {

    private GovtBranchReversePopulator govtBranchReversePopulator;

    @Override
    public void populate(GovtHeaderData govtHeaderData, ODataModel model) throws ConversionException {
        super.populate(govtHeaderData, model);

        if(govtHeaderData.getGovtBranchSet() != null && govtHeaderData.getGovtBranchSet().isEmpty()){
            List<GovtBranchData> branches = govtHeaderData.getGovtBranchSet();
            Set<Map> branchesSet = new HashSet<>();

            for(GovtBranchData branch : branches){
                ODataModel oDataModel = new ODataModel();
                govtBranchReversePopulator.populate(branch,oDataModel);
                branchesSet.add(oDataModel.get());
            }
            model.get().put("GovtBranchSet",branchesSet);
        }
    }

    /**
     * @return
     */
    public GovtBranchReversePopulator getGovtBranchReversePopulator() {
        return govtBranchReversePopulator;
    }

    /**
     * @param govtBranchReversePopulator
     */
    public void setGovtBranchReversePopulator(GovtBranchReversePopulator govtBranchReversePopulator) {
        this.govtBranchReversePopulator = govtBranchReversePopulator;
    }
}
