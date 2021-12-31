package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SalAndEmpData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SalAndEmpPopulator extends ODataPopulator<SalAndEmpData> {
    @Override
    public void populate(ODataModel model, SalAndEmpData salAndEmpData) throws ConversionException {
        super.populate(model, salAndEmpData);
        Map<String, Object> map = model.get();
        salAndEmpData.setExpatSal(new BigDecimal(map.get("ExpatSal").toString()));
        salAndEmpData.setFemales_emp(map.get("females_emp").toString());
        salAndEmpData.setMale_emp((map.get("male_emp").toString()));
    }
}
