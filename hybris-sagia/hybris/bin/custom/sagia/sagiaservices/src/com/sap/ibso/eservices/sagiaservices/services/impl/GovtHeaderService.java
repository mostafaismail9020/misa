package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;


/**
 * GovtHeaderService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class GovtHeaderService extends AbstractSagiaService<GovtHeaderData> {
    public static final String GOVT_BRANCH_SET = "GovtBranchSet";

    /**
     * retrieves GovtHeader
     * @param id id
     * @return GovtHeaderData
     */
    public final GovtHeaderData getGovtHeader(String id) {
        return super.get(GovtHeaderData.class,id);
    }

    /**
     * retrieves GovtHeaderWithBranchSet
     * @param id id
     * @return GovtHeaderData
     */
    public final GovtHeaderData getGovtHeaderWithBranchSet(String id) {
        return super.get(GovtHeaderData.class, (Object) id, REQUEST_PARAMETER_EXPAND, GOVT_BRANCH_SET);
    }
}
