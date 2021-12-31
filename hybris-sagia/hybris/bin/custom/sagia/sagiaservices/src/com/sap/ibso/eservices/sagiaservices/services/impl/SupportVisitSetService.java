package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Arrays;
import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * SupportVisitSetService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SupportVisitSetService extends AbstractSagiaService<SupportVisitData> {

    private static final String SUPP_VISIT_TO_TEXT = "SuppVisitToText";
    private static final String SUPPORT_VST_TO_ATTACH_NAV = "SupportVstToAttachNav";

    /**
     * retrieves Collection of SupportVisitData
     *
     * @return Collection of SupportVisitData
     */
    public final Collection<SupportVisitData> getCollection() {
        return super.getCollection(SupportVisitData.class, REQUEST_PARAMETER_EXPAND, SUPP_VISIT_TO_TEXT + "," + SUPPORT_VST_TO_ATTACH_NAV);
    }

    /**
     * retrieves SupportVisitData by id
     *
     * @param id id
     * @return SupportVisitData
     */
    public final SupportVisitData get(String id) {
        String objectId = "'" + id + "'";
        return super.get(SupportVisitData.class, Arrays.asList(objectId), REQUEST_PARAMETER_EXPAND, SUPP_VISIT_TO_TEXT + "," + SUPPORT_VST_TO_ATTACH_NAV);
    }

    /**
     * creates a SupportVisitData
     *
     * @param supportVisitData supportVisitData
     * @return String
     */
    public final void create(SupportVisitData supportVisitData) {
        super.save(supportVisitData);
    }
}
