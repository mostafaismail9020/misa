package com.sap.ibso.eservices.sagiaservices.services.odata;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ShareHolderInfoAttService extends AbstractSagiaService<ShareholderAttachmentData>{

	    public String uploadAttachmentFile(ShareholderAttachmentData attachment){
	       
	        InputStream contentStream = attachment.getContent();
	        Map<String,String> queryData = new HashMap<>();
	        queryData.put("Refid", attachment.getRefid());
	        queryData.put("Objectid", attachment.getObjectid());
	        queryData.put("Guid", attachment.getGuid());
	        queryData.put("Shhldcode", attachment.getShhldcode());	        
	        queryData.put("FileType", attachment.getFileType());
	       
	     //   queryData.put("Delegate", attachment.getDelegate());     
	      //  queryData.put("FileName", attachment.getFileName());
	       // queryData.put("FileMtype", " ");
	           
	        return super.upload(attachment.getFileName(), contentStream, queryData, "/ShareHolderInfoAttSet");
	    }

	    

}
