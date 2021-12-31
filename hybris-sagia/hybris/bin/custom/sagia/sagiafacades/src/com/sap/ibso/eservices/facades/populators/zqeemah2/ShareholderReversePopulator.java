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
public class ShareholderReversePopulator implements Populator<Shareholder, ShareholderData> {
	private static final String SHAREHOLDER_PERSON = "Person";

    @Override
    public void populate(Shareholder shareholder, ShareholderData shareholderData) throws ConversionException {
        shareholderData.setQ2refid(shareholder.getId());
        if(SHAREHOLDER_PERSON.equalsIgnoreCase(shareholder.getType())) {
			shareholderData.setQ2sharetype("01");
		} else {
			shareholderData.setQ2sharetype("02");
		}
		shareholderData.setQ2name(shareholder.getName());
		shareholderData.setQ2nationaity(shareholder.getNationality());
		shareholderData.setQ2shareper(shareholder.getSharesPercentage());
		shareholderData.setQ2legalstatus(shareholder.getLegalStatus());
		shareholderData.setQ2qeemah(shareholder.getQeemah());
    }
}
