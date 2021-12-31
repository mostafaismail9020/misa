package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.LicenseAmendmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.HashMap;
import java.util.Map;

/**
 * LicenseAmendmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class LicenseAmendmentService extends AbstractSagiaService<LicenseAmendmentData> {

    private static Map<String, String> queryOptions = new HashMap<>();
    static {
        queryOptions.put("$expand", "AmendHeaderToAmendShareHoldNav,AmendHeaderToAmendBranchNav,AmendHeaderToAmendEntityNav,AmendHeaderToAmendProductNav,AmendHeaderToTextNav");
    }

    /**
     * retrieves LicenseAmendment
     * @param srId srId
     * @return LicenseAmendmentData
     */
    public LicenseAmendmentData getLicenseAmendment(String srId) {
        Map<String, Object> ids = new HashMap<>();
        ids.put("SrID", srId);
        return get(LicenseAmendmentData.class, ids, queryOptions);
    }

    /**
     * checks of isInstantAmendment
     * @param licenseAmendmentData licenseAmendmentData
     * @return boolean
     */
    public boolean isInstantAmendment(LicenseAmendmentData licenseAmendmentData) {
        LicenseAmendmentData result = saveAndParseResponse(licenseAmendmentData, LicenseAmendmentData.class);
        return result.getIsInstant();
    }
}
