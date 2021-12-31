package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.facades.data.specialservices.ServiceApplicant;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceApplicantPopulator implements Populator<ServiceApplicantData, ServiceApplicant> {

    @Override
    public void populate(ServiceApplicantData serviceApplicantData, ServiceApplicant serviceApplicant) throws ConversionException {
        serviceApplicant.setObjectGuid(serviceApplicantData.getOBJECT_GUID());
        serviceApplicant.setObjectId(serviceApplicantData.getOBJECT_ID());
        serviceApplicant.setErrorMessage(serviceApplicantData.getERROR_MSG());
        serviceApplicant.setSuccess(serviceApplicantData.getSUCCESS());

        serviceApplicant.setApplicantName(serviceApplicantData.getZZAPP_NAME());
        serviceApplicant.setIqmaNumber(serviceApplicantData.getZZIQAMA_NUM());
        serviceApplicant.setIqmaExpiryDate(ObjectUtils.modifyDateFormat(serviceApplicantData.getZZIQAMA_EXPIRY()));

        serviceApplicant.setNationality(serviceApplicantData.getZZAPP_NATION());
        serviceApplicant.setNationalityNote(serviceApplicantData.getZZNATION_NOTE());
        serviceApplicant.setApplicantProfession(serviceApplicantData.getZZAPP_PROF());
        serviceApplicant.setInvestorNumber(serviceApplicantData.getZZSAGIA_BP());
        serviceApplicant.setApplicationCategory(serviceApplicantData.getZZAPPLY_CAT());
    }
}
