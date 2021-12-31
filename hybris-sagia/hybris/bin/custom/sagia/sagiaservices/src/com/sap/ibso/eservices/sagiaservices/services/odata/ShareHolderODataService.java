package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;


/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ShareHolderODataService extends AbstractSagiaService<ShareholderData>{

	    /**
	     * saves Entity
	     *
	     * @param basicContactInfoData entityInformationData
	     * @return String
	     */
	    public ShareholderData saveShareholder(ShareholderData shareholderData,ShareHolderModel shareHolderModel) {
	    	shareholderData.setRefid(shareHolderModel.getLicense().getApplicantReferenceID());
	        return super.saveAndParseResponse(shareholderData, ShareholderData.class);
	    }

	    

}
