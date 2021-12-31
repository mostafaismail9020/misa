package com.sap.ibso.eservices.facades.populators.zqeemah;

import org.springframework.core.convert.ConversionException;

import de.hybris.platform.converters.Populator;

import com.sap.ibso.eservices.facades.data.zqeemah.IsicDetails;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetData;

public class ISICDetailsObjectsPopulator implements Populator<IsicDetData, IsicDetails> {
    @Override
    public void populate(IsicDetData isicDetData, IsicDetails isicDetails) throws ConversionException {
        isicDetails.setFlag(isicDetData.getFlag());
        isicDetails.setLang(isicDetData.getLang());
        isicDetails.setIsicSection(isicDetData.getIsicSection());
        isicDetails.setIsicDivision(isicDetData.getIsicDivision());
        isicDetails.setIsicGroup(isicDetData.getIsicGroup());
        isicDetails.setIsicClass(isicDetData.getIsicClass());
        isicDetails.setAct(isicDetData.getAct());
        isicDetails.setIsicDescription(isicDetData.getIsicDescription());
        isicDetails.setActivity(isicDetData.getActivity());
        isicDetails.setSurveyId(isicDetData.getSurveyID());
        isicDetails.setAttachments(isicDetData.getAttachments());
        isicDetails.setQuestion(isicDetails.getQuestion());
    }
}