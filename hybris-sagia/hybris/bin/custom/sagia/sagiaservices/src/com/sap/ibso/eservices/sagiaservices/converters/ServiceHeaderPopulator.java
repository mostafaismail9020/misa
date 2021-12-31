package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpecialServiceHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceHeaderPopulator extends ODataPopulator<SpecialServiceHeaderData> {
    private ServiceAttachmentDataPopulator serviceAttachmentDataPopulator;
    private ServiceApplicantDataPopulator serviceApplicantDataPopulator;

    /**
     * @param serviceAttachmentDataPopulator
     */
    public void setServiceAttachmentDataPopulator(ServiceAttachmentDataPopulator serviceAttachmentDataPopulator) {
        this.serviceAttachmentDataPopulator = serviceAttachmentDataPopulator;
    }

    /**
     * @param serviceApplicantDataPopulator
     */
    public void setServiceApplicantDataPopulator(ServiceApplicantDataPopulator serviceApplicantDataPopulator) {
        this.serviceApplicantDataPopulator = serviceApplicantDataPopulator;
    }

    @Override
    public void populate(ODataModel model, SpecialServiceHeaderData specialServiceHeaderData) throws ConversionException {
        super.populate(model, specialServiceHeaderData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);

        Map<String, Object> map = model.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String postingDate = map.get("POSTING_DATE").toString();
        LocalDate postingDateFormatted = LocalDate.parse(postingDate, formatter);
        specialServiceHeaderData.setPOSTING_DATE_FORMATTED(postingDateFormatted);

        ODataFeed attachments = (ODataFeed) map.get("TOATTACHMENTS");
        if (attachments != null && attachments.getEntries() != null && !attachments.getEntries().isEmpty()) {
            Set<ServiceAttachmentData> attachmentDataSet = new HashSet<>();
            for (ODataEntry oDataEntry : attachments.getEntries()) {
                ServiceAttachmentData serviceAttachmentData = new ServiceAttachmentData();
                ODataModel tempModel = new ODataModel(oDataEntry);
                serviceAttachmentDataPopulator.populate(tempModel, serviceAttachmentData);
                attachmentDataSet.add(serviceAttachmentData);
            }
            specialServiceHeaderData.setTOATTACHMENTS(attachmentDataSet);
        }

        ODataFeed applicants = (ODataFeed) map.get("TOAPPLICANTS");
        if (applicants != null && applicants.getEntries() != null && !applicants.getEntries().isEmpty()) {
            Set<ServiceApplicantData> serviceApplicantDataSet = new HashSet<>();
            for (ODataEntry oDataEntry : applicants.getEntries()) {
                ServiceApplicantData serviceApplicantData = new ServiceApplicantData();
                serviceApplicantDataPopulator.populate(new ODataModel(oDataEntry), serviceApplicantData);
                serviceApplicantDataSet.add(serviceApplicantData);
            }
            specialServiceHeaderData.setTOAPPLICANTS(serviceApplicantDataSet);
        }
    }
}
