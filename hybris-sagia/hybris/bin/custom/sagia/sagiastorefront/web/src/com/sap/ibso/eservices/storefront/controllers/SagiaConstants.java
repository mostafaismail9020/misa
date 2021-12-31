package com.sap.ibso.eservices.storefront.controllers;

import java.util.Arrays;
import java.util.List;

public interface SagiaConstants {
    public static final String MIGS_SESSION_URL = "migs.sessionjs.url";

    interface TemporaryBiddingLicenseConstants {
        // Attachments

        // MimeType
        String MIME_TYPE = "application/pdf";

        // Names
        String EXTENSION = ".pdf";
        String DOC0 = "CR_Copy" + EXTENSION;
        String DOC1 = "Classification_Letter from the ultimate country" + EXTENSION;
        String DOC2 = "Article_of Association stamped from Saudi embassy" + EXTENSION; // not mandatory
        String DOC3 = "Classification_Letter from the other country" + EXTENSION; // not mandatory
        String DOC4 = "Last_5 years Audited Budget" + EXTENSION;
        String DOC5 = "ISO_Certificate 9001" + EXTENSION;
        String DOC6 = "Three_project Certificates of achievement" + EXTENSION;
        String DOC7 = "ISO_Certificate 14001 or other valid Certificates" + EXTENSION; // not mandatory
        String DOC8 = "Three_project Letters of award as Main Contractor" + EXTENSION;
        String DOC9 = "Safety_Certificate OSHAS 18001" + EXTENSION;
        String DOC10 = "Certificates_of completion for 3 projects as Main Contractor" + EXTENSION;
        String DOC11 = "Provide_acknowledgement of information authenticity" + EXTENSION;

        static List<String> getAttachmentsNames() {
            return Arrays.asList(DOC0, DOC1, DOC2, DOC3, DOC4, DOC5, DOC6, DOC7, DOC8, DOC9, DOC10, DOC11);
        }

    }
}
