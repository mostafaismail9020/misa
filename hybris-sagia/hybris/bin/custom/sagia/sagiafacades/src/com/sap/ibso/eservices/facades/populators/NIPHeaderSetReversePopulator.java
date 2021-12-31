package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NationalInvestorAtachment;
import com.sap.ibso.eservices.facades.data.NationalInvestorHeaderSet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPHeaderSetData;
import com.sap.ibso.eservices.sagiaservices.data.nip.NationalInvestorAtachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NIPHeaderSetReversePopulator implements Populator<NationalInvestorHeaderSet, NIPHeaderSetData> {
    NipAttachmentReversePopulator nipAttachmentReversePopulator;

    @Override
    public void populate(NationalInvestorHeaderSet nationalInvestorHeaderSet, NIPHeaderSetData nipHeaderSetData) throws ConversionException {
        nipHeaderSetData.setCR_NUMBER(nationalInvestorHeaderSet.getCrNumber());
        nipHeaderSetData.setSRQ_GUID(nationalInvestorHeaderSet.getSrqGuid());
        nipHeaderSetData.setSRQ_ID(nationalInvestorHeaderSet.getSrqId());
        nipHeaderSetData.setPARTNER(nationalInvestorHeaderSet.getPartner());
        nipHeaderSetData.setNAME_ARABIC(nationalInvestorHeaderSet.getNameArabic());
        nipHeaderSetData.setNAME_ENGLISH(nationalInvestorHeaderSet.getNameEnglish());
        nipHeaderSetData.setCAPITAL(nationalInvestorHeaderSet.getCapital());
        nipHeaderSetData.setLEGAL_STATUS(nationalInvestorHeaderSet.getLegalStatus());
        nipHeaderSetData.setCOMP_NAT_TYPE(nationalInvestorHeaderSet.getCompNationality());
        nipHeaderSetData.setCOMP_NATIONALITY(nationalInvestorHeaderSet.getCompNationality());
        nipHeaderSetData.setCOUNTRY(nationalInvestorHeaderSet.getCountry());
        nipHeaderSetData.setREGION(nationalInvestorHeaderSet.getRegion());
        nipHeaderSetData.setCITY(nationalInvestorHeaderSet.getCity());
        nipHeaderSetData.setMOBILE(nationalInvestorHeaderSet.getMobile());
        nipHeaderSetData.setEMAIL(nationalInvestorHeaderSet.getEmail());
        nipHeaderSetData.setLIC_TYPE(nationalInvestorHeaderSet.getLicenceType());
        nipHeaderSetData.setISIC_SECTION(nationalInvestorHeaderSet.getIsicSection());
        nipHeaderSetData.setISIC_DIVISION(nationalInvestorHeaderSet.getIsicDivision());
        nipHeaderSetData.setNUMBER_700(nationalInvestorHeaderSet.getNumber700());
        nipHeaderSetData.setZAKAT_NUMBER(nationalInvestorHeaderSet.getZakatNumber());
        nipHeaderSetData.setMOL_NUMBER(nationalInvestorHeaderSet.getMolNumber());
        nipHeaderSetData.setGOSI_NUMBER(nationalInvestorHeaderSet.getGosiNumber());
        nipHeaderSetData.setCONTACT_NAME(nationalInvestorHeaderSet.getContactName());
        nipHeaderSetData.setCONTACT_NATIONALITY(nationalInvestorHeaderSet.getContactNationality());
        nipHeaderSetData.setCONTACT_COUNTRY(nationalInvestorHeaderSet.getContactCountry());
        nipHeaderSetData.setCONTACT_REGION(nationalInvestorHeaderSet.getContactRegion());
        nipHeaderSetData.setCONTACT_CITY(nationalInvestorHeaderSet.getContactCity());
        nipHeaderSetData.setCONTACT_MOBILE(nationalInvestorHeaderSet.getContactMobile());
        nipHeaderSetData.setCONTACT_EMAIL(nationalInvestorHeaderSet.getContactEmail());
        nipHeaderSetData.setCR_ISSUE_DATE(nationalInvestorHeaderSet.getCrIssueDate());
        nipHeaderSetData.setCR_EXPIRY_DATE(nationalInvestorHeaderSet.getCrExpiryDate());
        nipHeaderSetData.setCR_ACTIVITY(nationalInvestorHeaderSet.getCrActivity());
        nipHeaderSetData.setCR_EXPIRY_CHECK(nationalInvestorHeaderSet.getCrExpiryCheck());
        nipHeaderSetData.setCR_VALID_CHECK(nationalInvestorHeaderSet.getCrValidCheck());
        nipHeaderSetData.setSUCCESS(nationalInvestorHeaderSet.getSuccess());
        nipHeaderSetData.setERROR(nationalInvestorHeaderSet.getError());
        List<NationalInvestorAtachmentData> attachmentsData = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(nationalInvestorHeaderSet.getAttachmentsSet())   ) {
            for(NationalInvestorAtachment item : nationalInvestorHeaderSet.getAttachmentsSet()){
                NationalInvestorAtachmentData attachment = new NationalInvestorAtachmentData();
                nipAttachmentReversePopulator.populate(item, attachment);
                attachmentsData.add(attachment);
            }
        }
        nipHeaderSetData.setToAttachments(attachmentsData);
    }

    public void setNipAttachmentReversePopulator(NipAttachmentReversePopulator nipAttachmentReversePopulator) {
        this.nipAttachmentReversePopulator = nipAttachmentReversePopulator;
    }
}
