package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * BasicContactInformationServiceQeemah1
 */
public class BasicContactInformationServiceQeemah1 extends AbstractSagiaService<BasicContactInformationData> {
    /**
     * saves Contact
     * @param basicContactInformationData basicContactInformationData
     * @return String
     */
    public String saveContact(BasicContactInformationData basicContactInformationData){
        return super.save(basicContactInformationData);
    }

    public void uploadFile(ShareholderAttachmentData attachment){
        byte[] fileContent = Base64.getDecoder().decode(attachment.getFileContent());

        InputStream contentStream = new ByteArrayInputStream(fileContent);
        Map<String,String> queryData = new HashMap<>();
        queryData.put("RefID", attachment.getRefId());
        queryData.put("FileType", attachment.getFileType());
        super.upload(attachment.getFileName(), contentStream, queryData, "/InfoToPoa");
    }
}
