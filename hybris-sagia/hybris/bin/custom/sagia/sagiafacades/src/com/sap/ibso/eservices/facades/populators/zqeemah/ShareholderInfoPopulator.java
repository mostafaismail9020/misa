package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderInfoData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ShareholderInfoPopulator implements Populator<ShareholderInfoData, ShareholderInfo> {

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ShareholderInfoData shareholderInfoData, ShareholderInfo shareholderInfo) throws ConversionException {
        shareholderInfo.setType(shareholderInfoData.getShldrType());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(shareholderInfoData.getDob(), formatter);
        shareholderInfo.setDateOfBirth(sagiaFormatProvider.getLocalizedDateData(birthDate).toString());
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
