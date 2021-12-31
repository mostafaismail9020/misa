package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaTutorialModel;
import com.sap.ibso.eservices.core.model.SagiaTutorialStepModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaTutorialService;
import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialData;
import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialStepData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SagiaTutorialDataPopulator implements Populator<SagiaTutorialModel, SagiaTutorialData> {
    SagiaTutorialStepDataPopulator sagiaTutorialStepDataPopulator;

    @Override
    public void populate(SagiaTutorialModel sagiaTutorialModel, SagiaTutorialData sagiaTutorialData) throws ConversionException {
        sagiaTutorialData.setName(sagiaTutorialModel.getName());
        sagiaTutorialData.setPageUrl(sagiaTutorialModel.getPageUrl());
        sagiaTutorialData.setTitle(sagiaTutorialModel.getTitle());
        sagiaTutorialData.setDescription(sagiaTutorialModel.getDescription());
        Collection<SagiaTutorialStepData> sagiaTutorialStepData = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(sagiaTutorialModel.getTutorialStep())){
            Collection<SagiaTutorialStepModel> sortedSteps = sagiaTutorialModel.getTutorialStep().stream()
                    .sorted(Comparator.comparing(SagiaTutorialStepModel::getSortOrder)).collect(Collectors.toList());
            for(SagiaTutorialStepModel item : sortedSteps){
                SagiaTutorialStepData tutorialStepData = new SagiaTutorialStepData();
                sagiaTutorialStepDataPopulator.populate(item, tutorialStepData);
                sagiaTutorialStepData.add(tutorialStepData);
            }
        }
        sagiaTutorialData.setSteps(sagiaTutorialStepData);
    }

    public void setSagiaTutorialStepDataPopulator(SagiaTutorialStepDataPopulator sagiaTutorialStepDataPopulator) {
        this.sagiaTutorialStepDataPopulator = sagiaTutorialStepDataPopulator;
    }
}
