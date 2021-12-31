package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.HashMap;
import java.util.Map;

/**
 * LicenseAmendmentShareholderService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class LicenseAmendmentShareholderService extends AbstractSagiaService<ShareholderData> {

    /**
     * retrieves LicenseAmendmentShareholder
     * @param bpId bpId
     * @return ShareholderData
     */
    public ShareholderData getLicenseAmendmentShareholder(String bpId) {
        Map<String, Object> ids = new HashMap<>();
        ids.put("SrID", "");
        ids.put("BpID", bpId);
        return get(ShareholderData.class, ids);
    }
}
