package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.TelecodeData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * Provides access to CRM_ID_STATUS_ENT collection of the ZQEEMAH service.
 */
public class TelecodeService extends AbstractSagiaService<TelecodeData> {
    /**
     * retrieves TelecodeData
     * @param countryCode countryCode
     * @return TelecodeData
     */
    public TelecodeData get(String countryCode) {
        return super.getByProperty(TelecodeData.class, "CounKey", countryCode);
    }
}
