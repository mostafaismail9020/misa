package com.sap.ibso.eservices.sagiaservices.converters.attachment;

public class ContentHDRUtils {

    private static final String PDF_FILE_MIME_TYPE = "application/pdf";
    private static final String PDF_FILE_EXTENSION = ".pdf";

    private ContentHDRUtils() {
    }

    public static String createFullFileNameFrom(String fileName, String mimeType) {

        if (fileName == null) {
            return "";
        }
        String fileExtension = getFileExtenstionOrNull(mimeType);

        if (fileExtension == null) {
            return fileName;
        }
        return fileName + fileExtension;
    }

    private static String getFileExtenstionOrNull(String mimeType) {
        if (mimeType == null) {
            return null;
        }
        if (mimeType.equalsIgnoreCase(PDF_FILE_MIME_TYPE)) {
            return PDF_FILE_EXTENSION;
        }
        if (mimeType.toLowerCase().contains("pdf")) {
            return PDF_FILE_EXTENSION;
        }
        return mimeType;
    }
}
