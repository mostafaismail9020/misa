package com.sap.ibso.eservices.sagiaservices.services.attachments;

import java.io.ByteArrayInputStream;

/**
 * AttachmentFile
 */
public class AttachmentFile {
    private String mimeType;
    private ByteArrayInputStream content;

    /**
     * getMimeType
     * @return String
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * setMimeType
     * @param mimeType mimeType
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * getContent
     * @return ByteArrayInputStream
     */
    public ByteArrayInputStream getContent() {
        return content;
    }

    /**
     * setContent
     * @param content content
     */
    public void setContent(ByteArrayInputStream content) {
        this.content = content;
    }
}
