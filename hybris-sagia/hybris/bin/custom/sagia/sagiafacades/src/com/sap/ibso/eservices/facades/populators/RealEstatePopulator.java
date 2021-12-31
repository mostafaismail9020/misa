package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealEstatePopulator implements Populator<RealEstateData, RealEstate> {

    private RealEstateAttachmentPopulator realEstateAttachmentPopulator;
    private SagiaFormatProvider sagiaFormatProvider;
    private static final Logger LOG = Logger.getLogger(RealEstatePopulator.class);

    /**
     * Populate from RealEstateData to RealEstate.
     *
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(RealEstateData source, RealEstate target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setObjectGuid(source.getObjectGuid());
        target.setRequestType(source.getREType());
        target.setPurchaseType(source.getPurchaseType());
        String plotNo = source.getPlotNo();
        if (StringUtils.isNotEmpty(plotNo)) {
            setPlotNo(target, plotNo);
        }

        target.setPlotArea(source.getPlotArea());
        target.setDeedNo(source.getDeedNo());
        if (source.getOutsideMakkah() != null) {
            target.setOutsideMakkah("1".equals(source.getOutsideMakkah()) ? Boolean.TRUE : Boolean.FALSE);
        }
        if (StringUtils.isNotEmpty(source.getApprovedIndustrial())) {
            target.setApprovedIndustrial("1".equals(source.getApprovedIndustrial()) ? Boolean.TRUE : Boolean.FALSE);
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
        target.setThirtyMore("1".equals(source.getThirtyMore()) ? Boolean.TRUE : Boolean.FALSE);
        target.setBpID(source.getBpID());
        target.setStatus(source.getStatus());
        
        //if(StringUtils.isNotBlank(source.getIdNo())) {
        	target.setIdNo(source.getIdNo());
        	target.setMojRegion(source.getRegion());
            target.setMojCity(source.getCity());
            target.setMojDeedDate(source.getPurchaseDate());
            
            target.setRegion(source.getRegion());
            target.setCity(source.getCity());
            target.setIssuerCourtName(source.getIssuerCourtName());
            
            //target.setSagiaPurchaseDate(source.getPurchaseDate());
        //}else {
        //	setPurchaseAndPostingDates(source, target);
       // }

        setPurchaseAndPostingDates(source, target);

        setAttachments(source, target);

    }

    private void setPurchaseAndPostingDates(RealEstateData source, RealEstate target) {
        String purchaseDate = source.getPurchaseDate();
        if(StringUtils.isNotBlank(source.getIdNo())) {
        	target.setPurchaseDate(purchaseDate);
        	target.setMojDeedDate(purchaseDate);
        }else {
        	if(StringUtils.isNotBlank(purchaseDate) && !"00000000".equals(purchaseDate)) {
	            LocalDateTime formattedDate = formatDate(purchaseDate);
	            if(formattedDate != null) {
	                target.setSagiaPurchaseDate(sagiaFormatProvider.getLocalizedDateTimeData(formattedDate));
	            }
        	}
        }
        String postingDate = source.getPostingDate();
        if(StringUtils.isNotBlank(postingDate) && !"00000000".equals(postingDate)) {
            LocalDateTime formattedDate = formatDate(postingDate);
            if(formattedDate != null) {
                target.setSagiaPostingDate(sagiaFormatProvider.getLocalizedDateTimeData(formattedDate));
            }
        }
    }

    private void setAttachments(RealEstateData source, RealEstate target) {
        Set<RealEstateAttachmentData> sourceAttachmentsSet = source.getAttachmentsSet();
        Set<RealEstateAttachment> attachmentSet = new HashSet<>();
        if (CollectionUtils.isNotEmpty(sourceAttachmentsSet)) {
            for (RealEstateAttachmentData item : sourceAttachmentsSet) {
                if(StringUtils.isNotEmpty(item.getFilename())) {
                    RealEstateAttachment serviceAttachment = new RealEstateAttachment();
                    realEstateAttachmentPopulator.populate(item, serviceAttachment);
                    attachmentSet.add(serviceAttachment);
                }
            }
        }
        target.setAttachmentsSet(attachmentSet);
    }

    private LocalDateTime formatDate(String date) {
        LocalDateTime dateTime = null;
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("uuuuMMdd")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
            dateTime = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException dateException) {
            LOG.error("Date object could not be parsed!", dateException);
        }
        return dateTime;
    }


    private void setPlotNo(RealEstate target, String plotNo) {
        String[] plotNos = plotNo.split("_");
        int index = 0;
        for (String nr : plotNos) {
            ++index;
            if (index == 1) {
                target.setPlotNo(nr);
            }
            if (index == 2) {
                target.setPlotNo2(nr);
            }
            if (index == 3) {
                target.setPlotNo3(nr);
            }
            if (index == 4) {
                target.setPlotNo4(nr);
            }
            if (index == 5) {
                target.setPlotNo5(nr);
            }
        }
    }

    /**
     * @param realEstateAttachmentPopulator
     */
    public void setRealEstateAttachmentPopulator(RealEstateAttachmentPopulator realEstateAttachmentPopulator) {
        this.realEstateAttachmentPopulator = realEstateAttachmentPopulator;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
