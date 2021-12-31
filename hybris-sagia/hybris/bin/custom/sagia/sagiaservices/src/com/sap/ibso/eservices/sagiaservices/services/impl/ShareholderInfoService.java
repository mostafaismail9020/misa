package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderInfoData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * ShareholderInfoService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ShareholderInfoService extends AbstractSagiaService<ShareholderInfoData> {

    /**
     * retrieves ShareholdersInfo
     * @param refId refId
     * @param fileType fileType
     * @param entityNo entityNo
     * @return Collection of ShareholderInfoData
     */
    public Collection<ShareholderInfoData> getShareholdersInfo(String refId, String fileType, String entityNo) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.put("RefID", refId == null ? "''" : ("'" + refId + "'"));
        queryOptions.put("FileType", fileType == null ? "''" : (fileType + "'"));
        queryOptions.put("EntityNo", entityNo == null ? "''" : (entityNo + "'"));

        return getCollection(ShareholderInfoData.class, queryOptions);
    }

    /**
     * creates Shareholders
     * @param shareholderInfoData shareholderInfoData
     */
    public void createShareholders(List<ShareholderInfoData> shareholderInfoData) {
        shareholderInfoData.forEach(super::save);
    }

    /**
     * uploads Files
     * @param shareholderAttachments shareholderAttachments
     */
    public void uploadFiles(Collection<ShareholderAttachmentData> shareholderAttachments){
        shareholderAttachments.forEach(attachment -> {
            byte[] fileContent = Base64.getDecoder().decode(attachment.getFileContent());
            InputStream contentStream = new ByteArrayInputStream(fileContent);
            Map<String,String> queryData = new HashMap<>();
            queryData.put("RefID", attachment.getRefId());
            queryData.put("EntityNo", attachment.getEntityNumber());
            queryData.put("FileType", attachment.getFileType());
            super.upload(attachment.getFileName(), contentStream, queryData, "/InfoToFile");
        });
    }
}
