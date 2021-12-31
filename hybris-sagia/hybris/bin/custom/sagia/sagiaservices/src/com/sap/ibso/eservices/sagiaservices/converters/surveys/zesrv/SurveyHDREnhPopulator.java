package com.sap.ibso.eservices.sagiaservices.converters.surveys.zesrv;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurvQuestDefEnhData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurveyHDREnhData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SurveyHDREnhPopulator extends ODataPopulator<SurveyHDREnhData> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSagiaService.class);
    private SurvQuestDefEnhPopulator survQuestDefEnhPopulator;
    private SurvQuestAnsEnhPopulator survQuestAnsEnhPopulator;
    
	@Override
	public void populate(ODataModel model, SurveyHDREnhData surveyHDRData) {
		super.populate(model, surveyHDRData);
        Map<String, Object> map = model.get();
        surveyHDRData.setSurvQuestDefDataSet(getSurvQuestDefDataSet(map));
	}
	
	private List<SurvQuestDefEnhData> getSurvQuestDefDataSet( Map<String, Object> map) {
		ODataFeed surveyHdrToSurvQuestDefNav = (ODataFeed) map.get("SurveyHdrToSurvQuestDefNav");
		if (Objects.nonNull(surveyHdrToSurvQuestDefNav)) {
			return surveyHdrToSurvQuestDefNav
					.getEntries()
					.stream()
					.map(this::createSurvQuestDefData)
					.collect(Collectors.toList());
		} else {
			LOG.error("Property SurveyHdrToSurvQuestDefNav was not found for SurveyHDREnh entity!");
			return Collections.emptyList();
		}
	}
	
	private SurvQuestDefEnhData createSurvQuestDefData(ODataEntry oDataEntry) {
		SurvQuestDefEnhData survQuestDefData = new SurvQuestDefEnhData();
		survQuestDefEnhPopulator.populate(new ODataModel(oDataEntry), survQuestDefData);
		return survQuestDefData;
	}

	public SurvQuestDefEnhPopulator getSurvQuestDefEnhPopulator() {
		return survQuestDefEnhPopulator;
	}

	public void setSurvQuestDefEnhPopulator(SurvQuestDefEnhPopulator survQuestDefEnhPopulator) {
		this.survQuestDefEnhPopulator = survQuestDefEnhPopulator;
	}

	public SurvQuestAnsEnhPopulator getSurvQuestAnsEnhPopulator() {
		return survQuestAnsEnhPopulator;
	}

	public void setSurvQuestAnsEnhPopulator(SurvQuestAnsEnhPopulator survQuestAnsEnhPopulator) {
		this.survQuestAnsEnhPopulator = survQuestAnsEnhPopulator;
	}

}
