package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCheckHistory;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SPCheckHistoryPopulator extends ODataPopulator<SpCheckHistory> {
    @Override
    public void populate(ODataModel model, SpCheckHistory spCheckHistory) throws ConversionException {
        super.populate(model, spCheckHistory, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
        Map<String, Object> map = model.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String postingDate = map.get("POSTING_DATE").toString();
        LocalDate postingDateFormatted = LocalDate.now();
        if(!"".equals(postingDate)){
            postingDateFormatted = LocalDate.parse(postingDate, formatter);
        }
        spCheckHistory.setPOSTING_DATE_FORMATTED(postingDateFormatted);
    }
}
