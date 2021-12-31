package com.sap.ibso.eservices.sagiaservices.converters.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zesrv.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.FinancialStatementHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.util.*;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils.extractDateFrom;

public class FinancialStatementHDRPopulator extends ODataPopulator<FinancialStatementHDRData> {
	
	private ContentHDRPopulator contentHDRPopulator;
    @Override
    public void populate(ODataModel model, FinancialStatementHDRData financialStatementHDRData) {
        super.populate(model, financialStatementHDRData);

        financialStatementHDRData.setContentHDRSet(readLegalInquiryAttachmentsFrom(model.get()));
    }

    private List<ContentHDRData> readLegalInquiryAttachmentsFrom(Map<String, Object> map) {
        return contentHDRPopulator.readAttachmentByOdataProperty(map, "ContentHDRSet");
    }

	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	public void setContentHDRPopulator(ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}

}
