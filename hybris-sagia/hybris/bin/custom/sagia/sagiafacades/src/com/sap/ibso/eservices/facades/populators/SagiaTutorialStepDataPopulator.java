package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaTutorialStepModel;
import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialStepData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SagiaTutorialStepDataPopulator implements Populator<SagiaTutorialStepModel, SagiaTutorialStepData> {

    @Override
    public void populate(SagiaTutorialStepModel sagiaTutorialStepModel, SagiaTutorialStepData sagiaTutorialStepData) throws ConversionException {
        sagiaTutorialStepData.setName(sagiaTutorialStepModel.getName());
        sagiaTutorialStepData.setSelector(sagiaTutorialStepModel.getSelector());
        sagiaTutorialStepData.setSortOrder(sagiaTutorialStepModel.getSortOrder());
        sagiaTutorialStepData.setPosition(sagiaTutorialStepModel.getPosition().getCode());
        sagiaTutorialStepData.setOffset(sagiaTutorialStepModel.getOffset());
        sagiaTutorialStepData.setBorderRadius(sagiaTutorialStepModel.getBorderRadius());
        sagiaTutorialStepData.setBorderRadiusSmall(sagiaTutorialStepModel.getBorderRadiusSmall());
        sagiaTutorialStepData.setBeforeChangeHandler(sagiaTutorialStepModel.getBeforeChangeHandler());
        sagiaTutorialStepData.setTitle(sagiaTutorialStepModel.getTitle());
        sagiaTutorialStepData.setDescription(sagiaTutorialStepModel.getDescription());
    }

}
