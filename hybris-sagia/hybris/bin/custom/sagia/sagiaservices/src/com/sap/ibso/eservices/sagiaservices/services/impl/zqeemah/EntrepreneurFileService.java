package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.EntrepreneurFileData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * EntrepreneurFileService
 */
public class EntrepreneurFileService extends AbstractSagiaService<EntrepreneurFileData> {

    /**
     * uploads Files
     * @param shareholderAttachments shareholderAttachments
     */
    public void uploadFiles(Collection<EntrepreneurFileData> shareholderAttachments){
        shareholderAttachments.forEach(attachment -> {
            byte[] fileContent = Base64.getDecoder().decode(attachment.getFileContent());
            InputStream contentStream = new ByteArrayInputStream(fileContent);
            Map<String,String> queryData = new HashMap<>();
            queryData.put("RefID", attachment.getRefId());
            queryData.put("FileType", attachment.getFileType());
            super.upload(attachment.getFileName(), contentStream, queryData, "/InfoToPoa");
        });
    }

}
