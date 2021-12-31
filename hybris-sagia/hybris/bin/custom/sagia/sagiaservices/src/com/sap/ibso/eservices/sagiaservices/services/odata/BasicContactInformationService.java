package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.sagiaservices.data.odata.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class BasicContactInformationService extends AbstractSagiaService<BasicContactInformationData>{

	    /**
	     * saves Contact
	     *
	     * @param basicContactInfoData basicContactInfoData
	     * @return String
	     */
	    public BasicContactInformationData saveContact(BasicContactInformationData basicContactInfoData) {
	        return super.saveAndParseResponse(basicContactInfoData, BasicContactInformationData.class);
	    }

	    

}
