package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ClassificationUpgradeDetailReversePopulator extends ODataReversePopulator<ZCLASS_DETSETData> {
    @Override
    public void populate(ZCLASS_DETSETData zClassDetail, ODataModel model)
            throws ConversionException {

        ObjectMapper mapper = new ObjectMapper();
        // use mixins
        mapper.addMixInAnnotations(ZCLASS_DETSETData.class, ClassificationUpgradeMixIns.class);
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        Map<String, Object> map = mapper.convertValue(zClassDetail, Map.class);
        map.remove("ClassProperty");
        model.set(map);
    }


    private abstract class ClassificationUpgradeMixIns {

        @JsonIgnore
        String classProperty;

        ClassificationUpgradeMixIns(@JsonProperty("Class") String classProperty) {
        }

        @JsonProperty("Class")
        abstract String getClassProperty(); // rename property

    }

}
