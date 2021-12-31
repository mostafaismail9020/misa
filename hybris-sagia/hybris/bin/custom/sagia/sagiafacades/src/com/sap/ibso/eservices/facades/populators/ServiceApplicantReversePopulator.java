package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.specialservices.ServiceApplicant;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDate;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceApplicantReversePopulator implements Populator<ServiceApplicant, ServiceApplicantData> {
    SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ServiceApplicant serviceApplicant, ServiceApplicantData serviceApplicantData) throws ConversionException {
        serviceApplicantData.setOBJECT_GUID(serviceApplicant.getObjectGuid());
        serviceApplicantData.setOBJECT_ID(serviceApplicant.getObjectId());
        serviceApplicantData.setERROR_MSG(serviceApplicant.getErrorMessage());
        serviceApplicantData.setSUCCESS(serviceApplicant.getSuccess());
        serviceApplicantData.setZZAPP_NAME(serviceApplicant.getApplicantName());
        serviceApplicantData.setZZIQAMA_NUM(serviceApplicant.getIqmaNumber());
        LocalDate iqamaExpiryDate = sagiaFormatProvider.getDateFromUIDateStringOrNull(serviceApplicant.getIqmaExpiryDate());
        serviceApplicantData.setZZIQAMA_EXPIRY(ObjectUtils.getCRMDateFormat(iqamaExpiryDate));
        serviceApplicantData.setZZAPP_NATION(serviceApplicant.getNationality());
        serviceApplicantData.setZZNATION_NOTE(serviceApplicant.getNationalityNote());
        serviceApplicantData.setZZAPP_PROF(serviceApplicant.getApplicantProfession());
        serviceApplicantData.setZZSAGIA_BP(serviceApplicant.getInvestorNumber());
        serviceApplicantData.setZZAPPLY_CAT(serviceApplicant.getApplicationCategory());
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
