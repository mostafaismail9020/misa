package com.sap.ibso.eservices.sagiaservices.converters.surveys.zui5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurvQuestAnsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurvQuestDefData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

/**
 * SurveyHDRPopulator
 */
public class SurveyHDRPopulator extends ODataPopulator<SurveyHDRData> {
    private SurvQuestDefPopulator survQuestDefPopulator;
    private SurvQuestAnsPopulator survQuestAnsPopulator;

    @Override
    public void populate(ODataModel model, SurveyHDRData surveyHDRData) {
        super.populate(model, surveyHDRData);
        Map<String, Object> map = model.get();

        if(Objects.isNull(surveyHDRData.getSurveyversion())) {
            Map<String, Object> insensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            insensitiveMap.putAll(map);
            Object surveyVersion = insensitiveMap.get("SurveyVersion");
            surveyHDRData.setSurveyversion(String.valueOf(surveyVersion));
        }

        setSurvQuestDefDataSet(surveyHDRData, map);
        setSurvQuestAnsDataSet(surveyHDRData, map); //info: CRM does not support reading this navigation property...
    }

    private void setSurvQuestDefDataSet(SurveyHDRData surveyHDRData, Map<String, Object> map) {
        ODataFeed surveyHdrToSurvQuestDefNav = (ODataFeed) map.get("SurveyHdrToSurvQuestDefNav");
        if(surveyHdrToSurvQuestDefNav != null && surveyHdrToSurvQuestDefNav.getEntries() != null && !surveyHdrToSurvQuestDefNav.getEntries().isEmpty()) {
            List<SurvQuestDefData> survQuestDefDataSet = new ArrayList<>();
            for(ODataEntry oDataEntry : surveyHdrToSurvQuestDefNav.getEntries()) {
                SurvQuestDefData survQuestDefData = new SurvQuestDefData();
                survQuestDefPopulator.populate(new ODataModel(oDataEntry), survQuestDefData);
                survQuestDefData.setSurveyHDRData(surveyHDRData);
                survQuestDefDataSet.add(survQuestDefData);
            }
            surveyHDRData.setSurvQuestDefDataSet(survQuestDefDataSet);
        }
    }

    private void setSurvQuestAnsDataSet(SurveyHDRData surveyHDRData, Map<String, Object> map) {
        ODataFeed surveyHdrToSurvQuestAnsNav = (ODataFeed) map.get("SurveyHdrToSurvQuestAnsNav");
        if(surveyHdrToSurvQuestAnsNav != null && surveyHdrToSurvQuestAnsNav.getEntries() != null && !surveyHdrToSurvQuestAnsNav.getEntries().isEmpty()) {
            List<SurvQuestAnsData> survQuestAnsDataSet = new ArrayList<>();
            for(ODataEntry oDataEntry : surveyHdrToSurvQuestAnsNav.getEntries()) {
                SurvQuestAnsData survQuestAnsData = new SurvQuestAnsData();
                survQuestAnsPopulator.populate(new ODataModel(oDataEntry), survQuestAnsData);
                survQuestAnsData.setSurveyHDRData(surveyHDRData);
                survQuestAnsDataSet.add(survQuestAnsData);
            }
            surveyHDRData.setSurvQuestAnsDataSet(survQuestAnsDataSet);
        }
    }

    /**
     * @return
     */
    public SurvQuestDefPopulator getSurvQuestDefPopulator() {
        return survQuestDefPopulator;
    }

    /**
     * @param survQuestDefPopulator
     */
    public void setSurvQuestDefPopulator(SurvQuestDefPopulator survQuestDefPopulator) {
        this.survQuestDefPopulator = survQuestDefPopulator;
    }

    /**
     * @return
     */
    public SurvQuestAnsPopulator getSurvQuestAnsPopulator() {
        return survQuestAnsPopulator;
    }

    /**
     * @param survQuestAnsPopulator
     */
    public void setSurvQuestAnsPopulator(SurvQuestAnsPopulator survQuestAnsPopulator) {
        this.survQuestAnsPopulator = survQuestAnsPopulator;
    }
}
