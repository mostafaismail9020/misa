package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CategorizationSchemaGetListData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;

public class CategorizationSchemaContactUsListService extends AbstractSagiaService<CategorizationSchemaGetListData> {

	/**
	 * reads CategorizationSchema
	 * @return Collection of CategorizationSchemaGetListData
	 */
	public Collection<CategorizationSchemaGetListData> readCategorizationSchema() {
		return getCollection(CategorizationSchemaGetListData.class, new QueryOptionsBuilder().skip("0")
				.top("500")
				.filter("ScenarioID eq " + "'" + "ZSR2" + "'")
				.inLineCount("allpages")
				.build());
	}
}
