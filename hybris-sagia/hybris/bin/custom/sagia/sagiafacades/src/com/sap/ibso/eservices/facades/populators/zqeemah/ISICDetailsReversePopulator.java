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
public class ISICDetailsReversePopulator implements Populator<ISICDetails, ISICDetailsData> {

    @Override
    public void populate(ISICDetails isicDetails, ISICDetailsData isicDetailsData) throws ConversionException {
		isicDetailsData.setQ2sectionno(isicDetails.getSectionNumber());
		isicDetailsData.setQ2sectiondesc(isicDetails.getSectionDescription());
		isicDetailsData.setQ2divisionno(isicDetails.getDivisionNumber());
		isicDetailsData.setQ2divisiondesc(isicDetails.getDivisionDescription());
		isicDetailsData.setQ2activityno(isicDetails.getActivityNumber());
		isicDetailsData.setQ2activitydesc(isicDetails.getActivityDescription());
		isicDetailsData.setQ2complimentaryno(isicDetails.getComplimentaryNumber());
		isicDetailsData.setQ2complimentarydesc(isicDetails.getComplimentaryDescription());
		isicDetailsData.setQ2licno(isicDetails.getLicenseNumber());
		isicDetailsData.setQ2licdesc(isicDetails.getLicenseDescription());
    }
}
