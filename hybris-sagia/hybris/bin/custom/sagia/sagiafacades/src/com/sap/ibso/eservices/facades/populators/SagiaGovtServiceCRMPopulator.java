package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtService;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtServiceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtServiceCRMPopulator implements Populator<SagiaCRMGovtService, SagiaCRMGovtServiceData> {

    private SagiaFormatProvider sagiaFormatProvider;

    /**
     * Populate from SagiaCRMGovtService to SagiaCRMGovtServiceData.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException exception
     */
    @Override
    public void populate(SagiaCRMGovtService source, SagiaCRMGovtServiceData target) throws ConversionException {
        target.setLicID(source.getLicID());
        target.setLicType(source.getLicType());
        target.setSrStCode(source.getSrStCode());
        target.setSrGuid(source.getSrGuid());
        target.setAction(source.getAction());
        target.setBpChangeID(source.getBpChangeID());
        target.setSalesrepCount(source.getSalesrepCount());
        target.setLicExDate(source.getLicExDate());
        target.setTransType(source.getTransType());
        target.setTermCond(source.getTermCond());
        target.setLicTypeDesc(source.getLicTypeDesc());
        target.setLicGuid(source.getLicGuid());
        target.setBpID(source.getBpID());
        target.setMinistryType(source.getMinistryType());
        target.setServiceType(source.getServiceType());
        target.setBpName(source.getBpName());
        target.setValidFrom(source.getValidFrom());
        target.setLicRenFlag(source.getLicRenFlag());
        target.setBpGuid(source.getBpGuid());
        target.setSrStDesc(source.getSrStDesc());
        target.setContactChInd(source.getContactChInd());
        target.setSrID(source.getSrID());
        target.setSrCrDate(source.getSrCrDate());
        target.setSrCrDateData(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(source.getSrCrDate())));
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider()
    {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)
    {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
