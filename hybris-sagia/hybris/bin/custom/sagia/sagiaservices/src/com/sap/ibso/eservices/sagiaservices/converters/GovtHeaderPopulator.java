package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtBranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.*;

import static com.sap.ibso.eservices.sagiaservices.services.impl.GovtHeaderService.GOVT_BRANCH_SET;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GovtHeaderPopulator extends ODataPopulator<GovtHeaderData>
{
    private GovtBranchPopulator govtBranchPopulator;

    @Override
    public void populate(ODataModel model, GovtHeaderData govtHeaderData) throws ConversionException
    {
        super.populate(model, govtHeaderData);

        Map<String, Object> map = model.get();
        ODataFeed govtBranchFeed = (ODataFeed) map.get(GOVT_BRANCH_SET);
        if (govtBranchFeed != null && govtBranchFeed.getEntries() != null && !govtBranchFeed.getEntries().isEmpty())
        {
            List<GovtBranchData> govtBranchDataSet = new ArrayList<>();
            for(ODataEntry entry : govtBranchFeed.getEntries())
            {
                GovtBranchData branchData = new GovtBranchData();

                govtBranchPopulator.populate(new ODataModel(entry), branchData);
                branchData.setGovtHeader(govtHeaderData);
                govtBranchDataSet.add(branchData);
            }
            govtHeaderData.setGovtBranchSet(govtBranchDataSet);
        }
    }

    /**
     * @return
     */
    public GovtBranchPopulator getGovtBranchPopulator() {
        return govtBranchPopulator;
    }

    /**
     * @param govtBranchPopulator
     */
    public void setGovtBranchPopulator(GovtBranchPopulator govtBranchPopulator) {
        this.govtBranchPopulator = govtBranchPopulator;
    }
}
