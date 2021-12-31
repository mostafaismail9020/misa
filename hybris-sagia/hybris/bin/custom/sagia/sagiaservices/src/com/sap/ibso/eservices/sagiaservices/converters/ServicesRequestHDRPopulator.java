package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestHDRPopulator extends ODataPopulator<ServiceRequestHDRsData>{

    private ContentHDRPopulator contentHDRPopulator;
    private ServicesRequestAddressPopulator addressPopulator;
    private ServicesRequestAttachmentPopulator attachmentPopulator;
    private ServicesRequestTextPopulator textPopulator;

    @Override
    public void populate(ODataModel model, ServiceRequestHDRsData serviceRequestData) throws ConversionException {
        super.populate(model, serviceRequestData);

        Map<String, Object> map = model.get();
        serviceRequestData.setSrvReqToContentNav(getSrvReqToContentNav(map));
        setSrvReqToAddressNav(serviceRequestData, map);
        setSrvReqToAttachNav(serviceRequestData, map);
        setSrvReqToTextNav(serviceRequestData, map);
    }

    private List<ContentHDRData> getSrvReqToContentNav(Map<String, Object> map) {
        return contentHDRPopulator.readAttachmentByOdataProperty(map, "SrvReqToContentNav");
    }

    private void setSrvReqToAddressNav(ServiceRequestHDRsData serviceRequestData, Map<String, Object> map) {
        ODataEntry srvReqToAddressNav = (ODataEntry) map.get("SrvReqToAddressNav");
        if(srvReqToAddressNav != null) {
            AddressHDRData addressHDRData = new AddressHDRData();
            addressPopulator.populate(new ODataModel(srvReqToAddressNav), addressHDRData);
            serviceRequestData.setSrvReqToAddressNav(addressHDRData);
        }
    }

    private void setSrvReqToAttachNav(ServiceRequestHDRsData serviceRequestData, Map<String, Object> map) {
        ODataFeed srvReqToAttachNav = (ODataFeed) map.get("SrvReqToAttachNav");
        if(srvReqToAttachNav != null && srvReqToAttachNav.getEntries() != null && !srvReqToAttachNav.getEntries().isEmpty()){
            List<AttachmentHDRData> attachments = new ArrayList<>();
            for(ODataEntry oDataEntry : srvReqToAttachNav.getEntries()) {
                AttachmentHDRData attachmentHDRData = new AttachmentHDRData();
                attachmentPopulator.populate(new ODataModel(oDataEntry), attachmentHDRData);
                attachments.add(attachmentHDRData);
            }
            serviceRequestData.setSrvReqToAttachNav(attachments);
        }
    }

    private void setSrvReqToTextNav(ServiceRequestHDRsData serviceRequestData, Map<String, Object> map) {
        ODataFeed srvReqToTextNav = (ODataFeed) map.get("SrvReqToTextNav");
        if(srvReqToTextNav != null && srvReqToTextNav.getEntries() != null && !srvReqToTextNav.getEntries().isEmpty()) {
            List<TextHDRData> textHDRDataList = new ArrayList<>();
            for(ODataEntry oDataEntry : srvReqToTextNav.getEntries()) {
                TextHDRData textHDRData = new TextHDRData();
                textPopulator.populate(new ODataModel(oDataEntry), textHDRData);
                textHDRDataList.add(textHDRData);
            }
            serviceRequestData.setSrvReqToTextNav(textHDRDataList);
        }
    }

    public ServicesRequestAddressPopulator getAddressPopulator() {
        return addressPopulator;
    }

    public void setAddressPopulator(ServicesRequestAddressPopulator addressPopulator) {
        this.addressPopulator = addressPopulator;
    }

    public ServicesRequestAttachmentPopulator getAttachmentPopulator() {

        return attachmentPopulator;
    }

    public void setAttachmentPopulator(ServicesRequestAttachmentPopulator attachmentPopulator) {
        this.attachmentPopulator = attachmentPopulator;
    }

    public void setTextPopulator(ServicesRequestTextPopulator textPopulator) {
        this.textPopulator = textPopulator;
    }
    
	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	/**
	 * @param contentHDRPopulator
	 */
	public void setContentHDRPopulator(final ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}
}
