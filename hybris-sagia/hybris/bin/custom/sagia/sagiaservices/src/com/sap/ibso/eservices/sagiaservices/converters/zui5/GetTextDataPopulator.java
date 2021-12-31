package com.sap.ibso.eservices.sagiaservices.converters.zui5;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

public class GetTextDataPopulator extends ODataPopulator<GetTextData> {
	
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(ODataModel model, GetTextData contentDetailsData) {
		super.populate(model, contentDetailsData);
		contentDetailsData.setSagiaCommentDate(sagiaFormatProvider.getLocalizedDateData(contentDetailsData.getCommentDate()));
	}
	
	public SagiaFormatProvider getSagiaFormatProvider() {
		return sagiaFormatProvider;
	}

	public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
	}
}
