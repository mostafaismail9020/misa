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
public class NIPHeaderSetPopulator implements Populator<NIPHeaderSetData, NationalInvestorHeaderSet> {
    NipAttachmentPopulator nipAttachmentPopulator;

    /**
     * @param nipAttachmentPopulator
     */
    public void setNipAttachmentPopulator(NipAttachmentPopulator nipAttachmentPopulator) {
        this.nipAttachmentPopulator = nipAttachmentPopulator;
    }

    @Override
    public void populate(NIPHeaderSetData nipHeaderSetData, NationalInvestorHeaderSet nationalInvestorHeaderSet) throws ConversionException {
        nationalInvestorHeaderSet.setCrNumber(nipHeaderSetData.getCR_NUMBER());
        nationalInvestorHeaderSet.setSrqGuid(nipHeaderSetData.getSRQ_GUID());
        nationalInvestorHeaderSet.setSrqId(nipHeaderSetData.getSRQ_ID());
        nationalInvestorHeaderSet.setPartner(nipHeaderSetData.getPARTNER());
        nationalInvestorHeaderSet.setNameArabic(nipHeaderSetData.getNAME_ARABIC());
        nationalInvestorHeaderSet.setNameEnglish(nipHeaderSetData.getNAME_ENGLISH());
        nationalInvestorHeaderSet.setCapital(nipHeaderSetData.getCAPITAL());
        nationalInvestorHeaderSet.setLegalStatus(nipHeaderSetData.getLEGAL_STATUS());
        nationalInvestorHeaderSet.setCompNationality(nipHeaderSetData.getCOMP_NAT_TYPE());
        nationalInvestorHeaderSet.setCompNationality(nipHeaderSetData.getCOMP_NATIONALITY());
        nationalInvestorHeaderSet.setCountry(nipHeaderSetData.getCOUNTRY());
        nationalInvestorHeaderSet.setRegion(nipHeaderSetData.getREGION());
        nationalInvestorHeaderSet.setCity(nipHeaderSetData.getCITY());
        nationalInvestorHeaderSet.setMobile(nipHeaderSetData.getMOBILE());
        nationalInvestorHeaderSet.setEmail(nipHeaderSetData.getEMAIL());
        nationalInvestorHeaderSet.setLicenceType(nipHeaderSetData.getLIC_TYPE());
        nationalInvestorHeaderSet.setIsicSection(nipHeaderSetData.getISIC_SECTION());
        nationalInvestorHeaderSet.setIsicDivision(nipHeaderSetData.getISIC_DIVISION());
        nationalInvestorHeaderSet.setNumber700(nipHeaderSetData.getNUMBER_700());
        nationalInvestorHeaderSet.setZakatNumber(nipHeaderSetData.getZAKAT_NUMBER());
        nationalInvestorHeaderSet.setMolNumber(nipHeaderSetData.getMOL_NUMBER());
        nationalInvestorHeaderSet.setGosiNumber(nipHeaderSetData.getGOSI_NUMBER());
        nationalInvestorHeaderSet.setContactName(nipHeaderSetData.getCONTACT_NAME());
        nationalInvestorHeaderSet.setContactNationality(nipHeaderSetData.getCONTACT_NATIONALITY());
        nationalInvestorHeaderSet.setContactCountry(nipHeaderSetData.getCONTACT_COUNTRY());
        nationalInvestorHeaderSet.setContactRegion(nipHeaderSetData.getCONTACT_REGION());
        nationalInvestorHeaderSet.setContactCity(nipHeaderSetData.getCONTACT_CITY());
        nationalInvestorHeaderSet.setContactMobile(nipHeaderSetData.getCONTACT_MOBILE());
        nationalInvestorHeaderSet.setContactEmail(nipHeaderSetData.getCONTACT_EMAIL());
        nationalInvestorHeaderSet.setCrIssueDate(nipHeaderSetData.getCR_ISSUE_DATE());
        nationalInvestorHeaderSet.setCrExpiryDate(nipHeaderSetData.getCR_EXPIRY_DATE());
        nationalInvestorHeaderSet.setCrActivity(nipHeaderSetData.getCR_ACTIVITY());
        nationalInvestorHeaderSet.setCrExpiryCheck(nipHeaderSetData.getCR_EXPIRY_CHECK());
        nationalInvestorHeaderSet.setCrValidCheck(nipHeaderSetData.getCR_VALID_CHECK());
        nationalInvestorHeaderSet.setSuccess(nipHeaderSetData.getSUCCESS());
        nationalInvestorHeaderSet.setError(nipHeaderSetData.getERROR());
        List<NationalInvestorAtachment> attachments = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(nipHeaderSetData.getToAttachments())) {
            for(NationalInvestorAtachmentData item : nipHeaderSetData.getToAttachments()){
                NationalInvestorAtachment attachment = new NationalInvestorAtachment();
                nipAttachmentPopulator.populate(item, attachment);
                attachments.add(attachment);
            }
        }
        nationalInvestorHeaderSet.setAttachmentsSet(attachments);
    }
}
