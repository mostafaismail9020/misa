package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.specialservices.SpecialService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCheckHistory;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SpecialServicePopulator implements Populator<SpCheckHistory, SpecialService> {
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(SpCheckHistory spCheckHistory, SpecialService specialService) throws ConversionException {
        specialService.setId(spCheckHistory.getOBJECT_ID());
        specialService.setType(spCheckHistory.getPROCESS_TYPE());
        specialService.setCategoryCode1(spCheckHistory.getCAT_CODE1());
        specialService.setCategory1(spCheckHistory.getCATEGORY1());
        specialService.setCategoryCode2(spCheckHistory.getCAT_CODE2());
        specialService.setCategory2(spCheckHistory.getCATEGORY2());
        specialService.setCategoryCode3(spCheckHistory.getCAT_CODE3());
        specialService.setCategory3(spCheckHistory.getCATEGORY3());
        specialService.setCategoryCode4(spCheckHistory.getCAT_CODE4());
        specialService.setCategory4(spCheckHistory.getCATEGORY4());
        specialService.setDescription(spCheckHistory.getDESCRIPTION());
        specialService.setDate(spCheckHistory.getPOSTING_DATE_FORMATTED());
        specialService.setDateData(sagiaFormatProvider.getLocalizedDateData(spCheckHistory.getPOSTING_DATE_FORMATTED()));
        specialService.setStatus(spCheckHistory.getSTATUS());
        specialService.setComments(spCheckHistory.getCOMMENTS());
        specialService.setLanguage(spCheckHistory.getLANGUAGE());
        specialService.setCreationAllowed(spCheckHistory.getCREATION_ALLOWED());
        specialService.setErrorMessage(spCheckHistory.getERROR_MSG());
    }


    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
