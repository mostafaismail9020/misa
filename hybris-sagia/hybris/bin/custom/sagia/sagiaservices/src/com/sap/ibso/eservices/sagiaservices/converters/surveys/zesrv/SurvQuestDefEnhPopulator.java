package com.sap.ibso.eservices.sagiaservices.converters.surveys.zesrv;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurvQuestDefEnhData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurvQustDDLBEnhData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

public class SurvQuestDefEnhPopulator extends ODataPopulator<SurvQuestDefEnhData> {

    private SurvQustDDLBEnhPopulator survQustDDLBEnhPopulator;

	@Override
	public void populate(ODataModel model, SurvQuestDefEnhData survQuestDefData) {
		super.populate(model, survQuestDefData);
		Map<String, Object> map = model.get();
		
		ODataFeed surveyHdrToSurvQuestAnsNav = (ODataFeed) map.get("SurveyQuestDefToSurvQuestDDLBNav");
		if (Objects.nonNull(surveyHdrToSurvQuestAnsNav)) {
			survQuestDefData.setSurvQustDDLBDataSet(createQustDDLBList(surveyHdrToSurvQuestAnsNav));
		}
	}

	private List<SurvQustDDLBEnhData> createQustDDLBList(ODataFeed surveyHdrToSurvQuestAnsNav) {
		List<ODataEntry> surveyHdrToSurvQuestEntries = surveyHdrToSurvQuestAnsNav.getEntries();
		return surveyHdrToSurvQuestEntries
				.stream()
				.map(this::createDDLBEnhDataFrom)
				.collect(Collectors.toList());
	}
	
	private SurvQustDDLBEnhData createDDLBEnhDataFrom(ODataEntry oDataEntry) {
		SurvQustDDLBEnhData survQustDDLBData = new SurvQustDDLBEnhData();
        survQustDDLBEnhPopulator.populate(new ODataModel(oDataEntry), survQustDDLBData);
        return survQustDDLBData;
	}

	public SurvQustDDLBEnhPopulator getSurvQustDDLBEnhPopulator() {
		return survQustDDLBEnhPopulator;
	}

	public void setSurvQustDDLBEnhPopulator(SurvQustDDLBEnhPopulator survQustDDLBEnhPopulator) {
		this.survQustDDLBEnhPopulator = survQustDDLBEnhPopulator;
	}

}
