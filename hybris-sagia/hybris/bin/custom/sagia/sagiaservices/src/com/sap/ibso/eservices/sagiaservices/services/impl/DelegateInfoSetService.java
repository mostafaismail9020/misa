package com.sap.ibso.eservices.sagiaservices.services.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.DelegateAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.DelegateInfoData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * DelegateDocumentSetService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DelegateInfoSetService extends AbstractSagiaService<DelegateInfoData> {


    /**
     * creates Delegates
     * @param shareholderInfoData shareholderInfoData
     */
    public void createDelegates(List<DelegateInfoData> delegateInfoData) {
    	delegateInfoData.forEach(super::save);
    }
    
    
    /**
     * uploads Files
     * @param shareholderAttachments shareholderAttachments
     */
    public void uploadFiles(Collection<DelegateAttachmentData> delegateAttachments){
    	delegateAttachments.forEach(attachment -> {
            byte[] fileContent = Base64.getDecoder().decode(attachment.getFileContent());
            InputStream contentStream = new ByteArrayInputStream(fileContent);
            Map<String,String> queryData = new HashMap<>();
            queryData.put("RefID", attachment.getRefId());
            queryData.put("EntityNo", attachment.getEntityNumber());
            queryData.put("FileType", attachment.getFileType());
            super.upload(attachment.getFileName(), contentStream, queryData, "/DelegateDocumentSet");
        });
    }

    
}
