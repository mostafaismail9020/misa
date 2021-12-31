package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.RemovePopupALR;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * RemovePopupALRService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class RemovePopupALRService extends AbstractSagiaService<RemovePopupALR> {
    /**
     * Removes the License renewal popup from dashboard
     * @return Response object
     */
    public RemovePopupALR removePopupALR(){
        return super.get(RemovePopupALR.class,(Object)"");
    }
}
