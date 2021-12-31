package com.sap.ibso.eservices.storefront.util;

import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class SagiaUtils {
    private static final Logger LOG = LoggerFactory.getLogger(SagiaUtils.class);
    private static final String LEGAL_STATUS = "legalStatus";

    private SagiaUtils() {}

    public static void writeByteArray(final HttpServletResponse response, byte[] pdf) {
        if (pdf == null || pdf.length == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setHeader("Content-Disposition", "inline;filename=myLicense.pdf");
        response.setContentType("application/pdf");
        response.setDateHeader("Expires", -1);
        try {
            response.getOutputStream().write(pdf);
            response.getOutputStream().flush();
        } catch (final IOException e) {
            LOG.error("Unable to write invoice PDF on output stream",e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static Collection<Shareholder> loadQeemah2ShareholderData(Collection<Map> qeemah2Shareholders) {
        Collection<Shareholder> shareholders = new ArrayList<>();
        int index = 0;
        for (Map shareholderData : qeemah2Shareholders) {
            Shareholder shareholder = new Shareholder();
            shareholder.setId(String.valueOf(index));
            shareholder.setType(shareholderData.get("type").toString());
            shareholder.setName(shareholderData.get("name").toString());
            shareholder.setNationality((shareholderData).get("nationality").toString());
            shareholder.setSharesPercentage(shareholderData.get("sharesPercentage").toString());
            if (shareholderData.get(LEGAL_STATUS) != null) {
                shareholder.setLegalStatus(shareholderData.get(LEGAL_STATUS).toString());
            }
            shareholders.add(shareholder);
            index++;
        }
        return shareholders;
    }
}
