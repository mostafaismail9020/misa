package com.sap.ibso.eservices.sagiaservices.converters.complaints;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GeneralInquirySet;

public class ContactUsEnquiriesPopulator extends ODataPopulator<GeneralInquirySet> {
	
	private ContentHDRPopulator contentHDRPopulator;
    private SagiaFormatProvider sagiaFormatProvider;
    
    @Override
	public void populate(ODataModel model, GeneralInquirySet generalInquirySetData) {

    	generalInquirySetData.setOBJECT_ID(String.valueOf(model.get("OBJECT_ID")));
		super.populate(model, generalInquirySetData);
		
		//generalInquirySetData.setCompAndEnqHdrToDetailNav(readComplaintDetailsFrom(model.get()));
		//generalInquirySetData.setCompAndEnqHdrToContentNav(readComplaintAttachmentsFrom(model.get()));
		//generalInquirySetData.setCompAndEnqHdrToTextNav(readComplaintMessagesFrom(model.get()));
	}

    /**
	 * @param sagiaFormatProvider
	 */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
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
