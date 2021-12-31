package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ClassificData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ClassificPopulator extends ODataPopulator<ClassificData> {
    @Override
    public void populate(ODataModel model, ClassificData classificData) throws ConversionException {
        super.populate(model, classificData);
        Map<String, Object> map = model.get();
        classificData.setFees(new BigDecimal(map.get("Fees").toString()));
    }
}
