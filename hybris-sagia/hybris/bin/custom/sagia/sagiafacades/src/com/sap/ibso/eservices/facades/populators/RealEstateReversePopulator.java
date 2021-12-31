package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealEstateReversePopulator implements Populator<RealEstate, RealEstateData> {

    private static final Logger LOG = LoggerFactory.getLogger(RealEstateReversePopulator.class);
    private static final String DATE_PATTERN = "yyyyMMdd";
    private RealEstateAttachmentReversePopulator realEstateAttachmentReversePopulator;
    private I18NService i18NService;

    /**
     * Populate from RealEstate to RealEstateData.
     *
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(RealEstate source, RealEstateData target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setObjectGuid(source.getObjectGuid());
        target.setREType(source.getRequestType());
        target.setPurchaseType(source.getPurchaseType());

        setPlotNo(source, target);

        target.setPlotArea(source.getPlotArea());
        target.setDeedNo(source.getDeedNo());
        if("TRUE".equalsIgnoreCase(source.getMojVerified())) {
        	target.setIsVerified("1");
        	//target.setIssuerCourtName(source.getIssuerCourtName());
        }else {
        	target.setIsVerified("0");
        	//target.setIssuerCourtName("");
        }
        target.setIssuerCourtName(source.getIssuerCourtName());
        if (source.getPurchaseDate() != null) {
            LocalDate purchaseDate = null;
            LOG.info("############# populate1 source.getPurchaseDate(): "+source.getPurchaseDate()+" **getIdNo : "+source.getIdNo()+" **getMojDeedDate : "+source.getMojDeedDate()+" **getCity : "+source.getCity()+" **getRegion : "+source.getRegion()+" **getMojVerified : "+source.getMojVerified());
            if(!source.getPurchaseDate().contains(", ")) {
            	target.setPurchaseDate(source.getPurchaseDate());
            }else {
            	try {
                    purchaseDate = LocalDate.from(Instant.ofEpochMilli(new SimpleDateFormat("MMM d, yyyy", i18NService.getCurrentLocale()).parse(source.getPurchaseDate()).getTime()).atZone(ZoneId.systemDefault()));
            	} catch (ParseException e) {
                    LOG.warn(e.getMessage(), e);
                }
                if (purchaseDate != null) {
                    target.setPurchaseDate(purchaseDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
                }
                
            }
        }
        if (source.getOutsideMakkah() != null) {
            target.setOutsideMakkah(Boolean.TRUE.equals(source.getOutsideMakkah()) ? "1" : "0");
        }
        if (source.getApprovedIndustrial() != null) {
            target.setApprovedIndustrial(Boolean.TRUE.equals(source.getApprovedIndustrial()) ? "1" : "0");
        }
        target.setProjectValue(source.getProjectValue());
        target.setPrice(source.getPrice());
        target.setRegion(source.getRegion());
        target.setCity(source.getCity());
        target.setHousingType(source.getHousingType());
        target.setDistrict(source.getDistrict());
        target.setUnitNo(source.getUnitNo());
        target.setBlockNo(source.getBlockNo());
        target.setRemarks(source.getRemarks());
        target.setThirtyMore(Boolean.TRUE.equals(source.getThirtyMore()) ? "1" : "0");
        target.setBpID(source.getBpID());
        target.setPostingDate(source.getPostingDate());
        target.setStatus(source.getStatus());

        setAttachmentsSet(source, target);
    }

    private void setAttachmentsSet(RealEstate source, RealEstateData target) {
        Set<RealEstateAttachment> sourceAttachmentSet = source.getAttachmentsSet();
        Set<RealEstateAttachmentData> attachmentSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(sourceAttachmentSet)) {
            for (RealEstateAttachment item : sourceAttachmentSet) {
                RealEstateAttachmentData serviceAttachment = new RealEstateAttachmentData();
                realEstateAttachmentReversePopulator.populate(item, serviceAttachment);
                attachmentSet.add(serviceAttachment);
            }
        }
        target.setAttachmentsSet(attachmentSet);
    }

    private void setPlotNo(RealEstate source, RealEstateData target) {
        String plotNo = source.getPlotNo();
        if (StringUtils.isNotBlank(plotNo)) {
            if (StringUtils.isNotEmpty(source.getPlotNo2())) {
                plotNo += "_" + source.getPlotNo2();
            }
            if (StringUtils.isNotEmpty(source.getPlotNo3())) {
                plotNo += "_" + source.getPlotNo3();
            }
            if (StringUtils.isNotEmpty(source.getPlotNo4())) {
                plotNo += "_" + source.getPlotNo4();
            }
            if (StringUtils.isNotEmpty(source.getPlotNo5())) {
                plotNo += "_" + source.getPlotNo5();
            }
            target.setPlotNo(plotNo);
        }
    }

    /**
     * @return
     */
    public RealEstateAttachmentReversePopulator getRealEstateAttachmentReversePopulator() {
        return realEstateAttachmentReversePopulator;
    }

    /**
     * @param realEstateAttachmentReversePopulator
     */
    public void setRealEstateAttachmentReversePopulator(RealEstateAttachmentReversePopulator realEstateAttachmentReversePopulator) {
        this.realEstateAttachmentReversePopulator = realEstateAttachmentReversePopulator;
    }

    /**
     * @param i18NService
     */
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
