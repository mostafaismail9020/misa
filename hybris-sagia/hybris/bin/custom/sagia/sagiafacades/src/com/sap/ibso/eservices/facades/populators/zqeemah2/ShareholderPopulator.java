package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ShareholderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ShareholderPopulator implements Populator<ShareholderData, Shareholder> {
    @Override
    public void populate(ShareholderData shareholderData, Shareholder shareholder) throws ConversionException {
        shareholder.setId(shareholderData.getQ2refid());
		shareholder.setType(shareholderData.getQ2sharetype());
		shareholder.setName(shareholderData.getQ2name());
		shareholder.setNationality(shareholderData.getQ2nationaity());
		shareholder.setSharesPercentage(shareholderData.getQ2shareper());
		shareholder.setLegalStatus(shareholderData.getQ2legalstatus());
		shareholder.setQeemah(shareholderData.getQ2qeemah());
    }
}
