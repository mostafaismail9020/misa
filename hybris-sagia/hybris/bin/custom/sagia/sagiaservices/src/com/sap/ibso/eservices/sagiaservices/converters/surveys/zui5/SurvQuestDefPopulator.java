package com.sap.ibso.eservices.sagiaservices.converters.surveys.zui5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurvQuestDefData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurvQustDDLBData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SurvQuestDefPopulator extends ODataPopulator<SurvQuestDefData> {
    private SurvQustDDLBPopulator survQustDDLBPopulator;

    @Override
    public void populate(ODataModel model, SurvQuestDefData survQuestDefData) throws ConversionException {
        super.populate(model, survQuestDefData);

        Map<String, Object> map = model.get();
        survQuestDefData.setMandquest((Boolean)map.get("Mandquest"));
        ODataFeed surveyHdrToSurvQuestAnsNav = (ODataFeed) map.get("SurveyQuestDefToSurvQuestDDLBNav");
        if(surveyHdrToSurvQuestAnsNav != null && surveyHdrToSurvQuestAnsNav.getEntries() != null && !surveyHdrToSurvQuestAnsNav.getEntries().isEmpty()) {
            List<SurvQustDDLBData> survQustDDLBDataSet = new ArrayList<>();
            for(ODataEntry oDataEntry : surveyHdrToSurvQuestAnsNav.getEntries()) {
                SurvQustDDLBData survQustDDLBData = new SurvQustDDLBData();
                survQustDDLBPopulator.populate(new ODataModel(oDataEntry), survQustDDLBData);
                survQustDDLBData.setSurvQuestDefData(survQuestDefData);
                survQustDDLBDataSet.add(survQustDDLBData);
            }
            survQuestDefData.setSurvQustDDLBDataSet(survQustDDLBDataSet);
        }
    }

    /**
     * @return
     */
    public SurvQustDDLBPopulator getSurvQustDDLBPopulator() {
        return survQustDDLBPopulator;
    }

    /**
     * @param survQustDDLBPopulator
     */
    public void setSurvQustDDLBPopulator(SurvQustDDLBPopulator survQustDDLBPopulator) {
        this.survQustDDLBPopulator = survQustDDLBPopulator;
    }
}
