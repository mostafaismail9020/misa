package com.sap.ibso.eservices.sagiaservices.auth.impl.odata;

import com.sap.ibso.eservices.sagiaservices.auth.impl.exception.CredentialVerificationException;
import com.sap.ibso.eservices.sagiaservices.services.ODataService;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_TYPE_GET;

/**
 * Implements a credential verification against an external system by a user-specific OData service call.
 */
public class CredentialVerificationODataService extends ODataService
{
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Executes an user-authenticated OData service call which takes the provided user name and password as authentication
     * credentials. The external system is implicitly determined by the target URL of HTTP(S) destination from the
     * SAP configuration assigned to the current base store.
     * A successful response (HTTP code 200) indicates valid credentials.
     *
     * @param userName the user name
     * @param password the password
     * @return {@code true} if the OData service call response returns HTTP code 200, {@code false} otherwise
     * @throws CredentialVerificationException if a technical error occurs
     */
    public boolean isValid(String userName, String password)
    {
        try {
            String targetURL = getSAPHTTPDestinationModel().getTargetURL();
            setRootUrl(targetURL);

            final URL url = createURL("");
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
            request.setProxy(getProxy());

            request.getRequestProperties().put("sap-user", userName.toUpperCase(Locale.ENGLISH));
            request.getRequestProperties().put("sap-password", password);

            final HttpURLConnectionResponse response = getHttpURLConnectionService().execute(request);

            int httpCode = response.getResponseCode();
            LOGGER.info("HTTP code: " + httpCode + ", user: " + userName);
            return httpCode == 200;
        }
        catch (Exception e)
        {
            throw new CredentialVerificationException(e.getMessage(), e);
        }
    }

    /**
     * Checks whether an HTTP URL connection response contains an {@code IOException}.
     *
     * @param response the HTTP URL connection response
     * @throws IOException of the response if present
     */
    @Override
    protected void checkStatus(final HttpURLConnectionResponse response) throws IOException
    {
        if (response.getIOException() != null)
        {
            throw response.getIOException();
        }
    }

    /**
     * Spring bean lifecycle method: post-construction callback.
     */
    @Override
    public void init()
    {
        // "Empty" method implementation disables HTTP destination configuration change listener functionality of super-class
    }

    /**
     * Spring bean lifecycle method: pre-destruction callback.
     */
    @Override
    public void destroy()
    {
        // "Empty" method implementation disables HTTP destination configuration change listener functionality of super-class
    }
}
