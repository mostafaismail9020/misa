package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah2.ISICDetails;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ISICDetailsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ISICDetailsPopulator  implements Populator<ISICDetailsData, ISICDetails> {

    @Override
    public void populate(ISICDetailsData isicDetailsData, ISICDetails isicDetails) throws ConversionException {
        isicDetails.setSectionNumber(isicDetailsData.getQ2sectionno());
		isicDetails.setSectionDescription(isicDetailsData.getQ2sectiondesc());
		isicDetails.setDivisionNumber(isicDetailsData.getQ2divisionno());
		isicDetails.setDivisionDescription(isicDetailsData.getQ2divisiondesc());
		isicDetails.setActivityNumber(isicDetailsData.getQ2activityno());
		isicDetails.setActivityDescription(isicDetailsData.getQ2activitydesc());
		isicDetails.setComplimentaryNumber(isicDetailsData.getQ2complimentaryno());
		isicDetails.setComplimentaryDescription(isicDetailsData.getQ2complimentarydesc());
		isicDetails.setLicenseNumber(isicDetailsData.getQ2licno());
		isicDetails.setLicenseDescription(isicDetailsData.getQ2licdesc());
    }
}
