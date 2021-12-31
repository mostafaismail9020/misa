package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.data.CRMGovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtCRMUploadPopulator implements Populator<GovtServiceUploadData, CRMGovtServiceUploadData> {
	/**
	 * Populate from GovtServiceUploadData to CRMGovtServiceUploadData.
	 * @param source the source object
	 * @param target the target to fill
	 * @throws ConversionException
	 */
	@Override
    public void populate(GovtServiceUploadData source, CRMGovtServiceUploadData target) throws ConversionException {
        target.setScenario(source.getScenario());
		target.setFieldname(source.getFieldname());
		target.setFieldkey(source.getFieldkey());
		target.setFieldSubtype(source.getFieldSubtype());
		target.setDescription(source.getDescription());
		target.setDockeyID(source.getDockeyID());
		target.setLongDescr(source.getLongDescr());
		target.setOptionalAttach(source.getOptionalAttach());
    }
}
