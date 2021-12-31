package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.specialservices.ServiceApplicant;
import com.sap.ibso.eservices.facades.data.specialservices.ServiceAttachment;
import com.sap.ibso.eservices.facades.data.specialservices.SpecialServiceHeader;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpecialServiceHeaderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SpecialServiceHeaderPopulator implements Populator<SpecialServiceHeaderData, SpecialServiceHeader> {

    ServiceAttachmentPopulator serviceAttachmentPopulator;
    ServiceApplicantPopulator serviceApplicantPopulator;
    SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(SpecialServiceHeaderData specialServiceHeaderData, SpecialServiceHeader specialServiceHeader) throws ConversionException {
        specialServiceHeader.setId(specialServiceHeaderData.getOBJECT_ID());
        specialServiceHeader.setType(specialServiceHeaderData.getPROCESS_TYPE());
        specialServiceHeader.setServiceRegion(specialServiceHeaderData.getZZSER_REGION());
        specialServiceHeader.setCategory1(specialServiceHeaderData.getCATEGORY1());
        specialServiceHeader.setCategoryCode1(specialServiceHeaderData.getCAT_CODE1());
        specialServiceHeader.setCategory2(specialServiceHeaderData.getCATEGORY2());
        specialServiceHeader.setCategoryCode2(specialServiceHeaderData.getCAT_CODE2());
        specialServiceHeader.setDescription(specialServiceHeaderData.getDESCRIPTION());
        specialServiceHeader.setDate(specialServiceHeaderData.getPOSTING_DATE_FORMATTED());
        specialServiceHeader.setDateData(sagiaFormatProvider.getLocalizedDateData(specialServiceHeaderData.getPOSTING_DATE_FORMATTED()));

        specialServiceHeader.setStatus(specialServiceHeaderData.getSTATUS());
        specialServiceHeader.setComments(specialServiceHeaderData.getCOMMENTS());
        specialServiceHeader.setErrorMessage(specialServiceHeaderData.getERROR_MSG());

        specialServiceHeader.setEmail(specialServiceHeaderData.getZZEMAIL_ADD());
        specialServiceHeader.setPhoneNumber(specialServiceHeaderData.getZZPHONE_NUM());

        specialServiceHeader.setCompany(specialServiceHeaderData.getZZNEW_COMP());
        specialServiceHeader.setCRNumber(specialServiceHeaderData.getZZ_CR_NUMBER());
        specialServiceHeader.setProfession(specialServiceHeaderData.getZZNEW_PROF());
        specialServiceHeader.setApplicationReason(specialServiceHeaderData.getZZAPP_REASON());

        Set<ServiceApplicant> serviceApplicantSet = new HashSet<>();

        setAttachments(specialServiceHeaderData, specialServiceHeader, serviceApplicantSet);
    }

    private void setAttachments(SpecialServiceHeaderData specialServiceHeaderData, SpecialServiceHeader specialServiceHeader, Set<ServiceApplicant> serviceApplicantSet) {
        if (specialServiceHeaderData.getTOAPPLICANTS() != null && !specialServiceHeaderData.getTOAPPLICANTS().isEmpty()) {
            for(ServiceApplicantData item : specialServiceHeaderData.getTOAPPLICANTS()){
                ServiceApplicant serviceApplicant = new ServiceApplicant();
                serviceApplicantPopulator.populate(item, serviceApplicant);
                serviceApplicantSet.add(serviceApplicant);
            }
        }
        specialServiceHeader.setApplicants(serviceApplicantSet);

        Set<ServiceAttachment> serviceAttachmentSet = new HashSet<>();

        if (specialServiceHeaderData.getTOATTACHMENTS() != null  && !specialServiceHeaderData.getTOATTACHMENTS().isEmpty()) {
            for(ServiceAttachmentData item : specialServiceHeaderData.getTOATTACHMENTS()){
                if(StringUtils.isNotEmpty(item.getOBJECT_ID()) && StringUtils.isNotEmpty(item.getDOC_GUID())) {
                    ServiceAttachment serviceAttachment = new ServiceAttachment();
                    serviceAttachmentPopulator.populate(item, serviceAttachment);
                    serviceAttachmentSet.add(serviceAttachment);
                }
            }
        }
        specialServiceHeader.setAttachedDocuments(serviceAttachmentSet);
    }

    /**
     * @param serviceAttachmentPopulator serviceAttachmentPopulator
     */
    public void setServiceAttachmentPopulator(ServiceAttachmentPopulator serviceAttachmentPopulator) {
        this.serviceAttachmentPopulator = serviceAttachmentPopulator;
    }

    /**
     * @param serviceApplicantPopulator serviceApplicantPopulator
     */
    public void setServiceApplicantPopulator(ServiceApplicantPopulator serviceApplicantPopulator) {
        this.serviceApplicantPopulator = serviceApplicantPopulator;
    }

    /**
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
