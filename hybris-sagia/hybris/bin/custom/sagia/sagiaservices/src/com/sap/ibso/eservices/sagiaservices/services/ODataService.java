/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.event.HttpDestinationPotentialUpdateEvent;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionService;
import com.sap.ibso.eservices.sagiaservices.utils.ODataEdmConvertor;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.sap.core.configuration.http.HTTPDestinationService;
import de.hybris.platform.sap.core.configuration.http.impl.HTTPDestinationServiceImpl;
import de.hybris.platform.sap.core.configuration.model.SAPConfigurationModel;
import de.hybris.platform.sap.core.configuration.model.SAPHTTPDestinationModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import org.apache.olingo.odata2.api.edm.*;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.core.edm.provider.EdmImplProv;
import org.apache.olingo.odata2.core.edm.provider.EdmxProvider;
import org.fest.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/**
 * This bean shall be instantiated for each OData service as separate $metadata are used by OData services.
 */
public class ODataService {
    private class TokenCache {
        private List<String> setCookies;
        private List<String> xcsrfTokens;
    }

    private class HttpDestination {
        private String targetUrl;
        private String basicAuthorization;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ODataService.class);

    private static final String X_CSRF_TOKEN = "x-csrf-token";

    private volatile ThreadLocal<Edm> edm; // Each thread must have its own instance of Edm.
    private HttpURLConnectionService httpURLConnectionService;
    private ODataEdmConvertor oDataEdmConvertor;

    private String rootUrl;
    private String basicAuth;
    private String password;
    private String user;
    private Proxy proxy = Proxy.NO_PROXY;
    private String sapClient;

    private String serviceName;
    private String destinationSystem = "NETVIEWVER";

    private volatile TokenCache token; // Multi-thread shared cookie(http session) & CSRF token

    private BaseStoreService baseStoreService;
    private UserService userService;
    private EventService eventService;
    private CommonI18NService commonI18NService;

    private volatile HttpDestination httpDestination;
    
    private HttpDestinationEventListener httpDestinationEventListener;

    private static String sagiaCRMDestinationSystem = Config.getString("sagia.crm.destination.system", "CRM");

    @Resource(name="sapCoreHTTPDestinationService")
    HTTPDestinationServiceImpl httpDestinationService;
    
    
    /**
     * Read {@link InputStream} and return a byte array.
     *
     * @param stream {@link InputStream} to read.
     * @return All bytes that could be read from the {@link InputStream}
     * @throws IOException from {@link InputStream#read(byte[])}
     * @see HttpURLConnectionService#bufferStream(InputStream)
     */
    @Nonnull
    public byte[] bufferStream(final InputStream stream) throws IOException {
        return this.httpURLConnectionService.bufferStream(stream);
    }

    protected void checkStatus(final HttpURLConnectionResponse response) throws IOException {
        if (response.getIOException() != null) {
            throw new IOException("IOException occured", response.getIOException());
        }
        final int responseCode = response.getResponseCode();
        if (400 <= responseCode && responseCode <= 599) {
            final byte[] payloadError = response.getPayloadError();
            String errorMessage;
            final String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(payloadError));
            if ("application/xml".equals(contentType)) {
                errorMessage = this.prettyPrintXML(payloadError);
            } else {
                errorMessage = new String(payloadError, StandardCharsets.UTF_8);
            }

             throw new IOException("HTTP error (" + responseCode + ") : " + errorMessage);
        }
    }

    /**
     * Create an absolute URL accessing the OData service at the resource path provided.
     *
     * @param resourcePath Resource path of the OData service.
     * @return {@link URL}
     */
    @Nonnull
    public URL createURL(final String resourcePath) {
        return this.createURL(resourcePath, Collections.emptyMap());
    }

    /**
     * Create an absolute URL accessing the OData service at the resource path provided.
     *
     * @param resourcePath Resource path of the OData service.
     * @param id id
     * @return {@link URL}
     */
    @Nonnull
    public URL createURLSingleEntry(final String resourcePath, final Object id) {
        final String path;
        if (id instanceof String && !Strings.isEmpty(id.toString())) {
            path = resourcePath + "('" + id.toString() + "')";
        } else if(id instanceof Integer) {
            path = resourcePath + "(" + id.toString() + ")";
        } else {
            path = resourcePath + "('')";
        }
        return this.createURL(path, Collections.emptyMap());
    }

    /**
     * Create an absolute URL accessing the OData service at the resourcePath provided using OData query options.
     *
     * @param resourcePath Resource path of the OData service.
     * @param queryOptions OData Query String Options.
     * @return {@link URL}
     */
    @Nonnull
    public URL createURL(final String resourcePath, final Map<String, String> queryOptions) {
        StringBuilder query = null;
        try {
            query = buildQuery(queryOptions);
            return new URL(this.getTargetUrl() + resourcePath + query);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new IllegalStateException("Invalid URL built using rootUrl=" + this.getTargetUrl() + " resourcePath=" + resourcePath
                    + " query=" + query + " queryOptions=" + queryOptions, e);
        }
    }

    /**
     * Create an absolute URL accessing the OData service at the resourcePath provided using OData query options.
     *
     * @param resourcePath Resource path of the OData service.
     * @param queryString  Prepared query string.
     * @param queryOptions OData Query String Options.
     * @return {@link URL}
     */
    @Nonnull
    public URL createURL(final String resourcePath, final Object queryString, final Map<String, String> queryOptions) {
        StringBuilder query = null;
        try {
            query = buildQuery(queryOptions);
            query.append("&");
            query.append(queryString);
            return new URL(this.getTargetUrl() + resourcePath + query);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new IllegalStateException("Invalid URL built using rootUrl=" + this.getTargetUrl() + " resourcePath=" + resourcePath
                    + " query=" + query + " queryOptions=" + queryOptions, e);
        }
    }

    /**
     * Creates a {@link StringBuilder} instance via {@link ODataService#getCustomQueryOptions()} and appends the given
     * query options URL encoded to this builder.
     *
     * @param queryOptions name-value pairs
     * @return the created {@link StringBuilder}
     * @throws UnsupportedEncodingException if URL encoding fails
     */
    private StringBuilder buildQuery(Map<String, String> queryOptions) throws UnsupportedEncodingException {
        StringBuilder query = this.getCustomQueryOptions();

        for (final Entry<String, String> e : queryOptions.entrySet()) {
            query.append('&');
            query.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(e.getValue(), "UTF-8"));
        }

        return query;
    }

    /**
     * Create an absolute URL accessing the OData service at the resourcePath provided using OData query options.
     *
     * @param resourcePath Resource path of the OData service.
     * @param queryOptions OData Query String Options.
     * @return {@link URL}
     */
    @Nonnull
    public URL createURL(final String resourcePath, final String... queryOptions) {
        final Map<String, String> queryOptionsMap = new LinkedHashMap<>();
        for (int i = 0; i < queryOptions.length; i += 2) {
            queryOptionsMap.put(queryOptions[i], queryOptions[i + 1]);
        }
        return this.createURL(resourcePath, queryOptionsMap);
    }

    @Nonnull
    private byte[] decompressIfGZIP(final byte[] payload) throws IOException {
        if (payload.length < 2 || payload[0] != 31 || payload[1] != -117) {
            return payload;
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(payload)) {
            try (GZIPInputStream gis = new GZIPInputStream(bais)) {
                return this.bufferStream(gis);
            }
        }
    }

    /**
     * Provides direct HTTP URL connection service access for sub-classes.
     *
     * @return the HTTP URL connection service
     */
    protected HttpURLConnectionService getHttpURLConnectionService()
    {
        return httpURLConnectionService;
    }

    /**
     * Provides proxy access for sub-classes.
     *
     * @return the proxy
     */
    protected Proxy getProxy()
    {
        return proxy;
    }

    /**
     * Execute the request with Authorization : "Basic BASE64user:pass".<br>
     * Using this method will create a new http session (unnecessary cost) on the yMKT side.<br>
     * It is preferable to use {@link #executeWithRetry(HttpURLConnectionRequest)} for most execution scenarios.
     *
     * @param request {@link HttpURLConnectionRequest} to execute.
     * @return {@link HttpURLConnectionResponse} executed.
     * @throws IOException If the HTTP request was not successful.
     * @see URLConnection#setRequestProperty
     */
    @Nonnull
    protected HttpURLConnectionResponse executeWithBasicAuth(final HttpURLConnectionRequest request) throws IOException {
        if (this.basicAuth != null) {
            request.getRequestProperties().put("Authorization", this.basicAuth);
        } else {
            request.getRequestProperties().put("Authorization", getHttpDestination().basicAuthorization);
        }

        request.setProxy(this.proxy);
        final HttpURLConnectionResponse response = this.httpURLConnectionService.execute(request);

        this.checkStatus(response);

        return response;
    }

    /**
     * Execute the request with "cookie" & "x-csrf-token".<br>
     * If the existing session and/or token expired, those are refreshed and the request is re-tried.
     *
     * @param request {@link HttpURLConnectionRequest} to execute.
     * @return {@link HttpURLConnectionResponse} executed.
     * @throws IOException If the HTTP request was not successful.
     */
    @Nonnull
    public HttpURLConnectionResponse executeWithRetry(final HttpURLConnectionRequest request) throws IOException {
        // Double-check idiom for lazy initialization of volatile instance fields
        TokenCache tokenCache = token;

        try {
            if (tokenCache == null) { // First check without locking
                synchronized (this) {
                    tokenCache = token;
                    if (tokenCache == null) { // Second check with locking
                        tokenCache = token = refreshToken();
                    }
                }
            }
            request.getRequestProperties().put(X_CSRF_TOKEN, String.join("; ", tokenCache.xcsrfTokens));
            request.getRequestProperties().put("cookie", String.join("; ", tokenCache.setCookies));
        } catch (Exception e) {
            LOG.warn("Failed to retrieve token "+ e.getMessage(),e);
        }

        // All OData call accept gzip encoding.
        request.getRequestProperties().put("Accept-Encoding", "gzip");

        //Adds the backend gateway user name which shall be associated with this request
        addInternetUserIdentifier(request);
        // Adds the language which shall be associated with this request
        addSessionLanguage(request);

        request.setProxy(this.proxy);
        HttpURLConnectionResponse response = this.httpURLConnectionService.execute(request);

        // Expired http session is reported as error 401
        // Invalid csrf token is reported as error 403
        // HCI convert both 401 & 403 as error 500
        if (response.getResponseCode() == 401 || //
                response.getResponseCode() == 403 || //
                response.getResponseCode() == 500) {
            try {
                TokenCache retryTokenCache;

                synchronized (this) {
                    /*
                     * Refresh:
                     * - if token is null
                     * - if token is not null and both (token and tokenCache) are equal (which did not work in first place due to the potential errors mentioned above)
                     */
                    if (token == null || Objects.equals(tokenCache, token)) {
                        retryTokenCache = token = refreshToken();
                    } else {
                        /*
                         * Token is not null and tokenCache differs from token:
                         * indicates that refreshing was already performed by another thread
                         */
                        retryTokenCache = token;
                    }
                }

                request.getRequestProperties().put(X_CSRF_TOKEN, String.join("; ", retryTokenCache.xcsrfTokens));
                request.getRequestProperties().put("cookie", String.join("; ", retryTokenCache.setCookies));
            } catch (Exception e) {
                LOG.warn("Failed to refresh token", e);
            }

            response = this.httpURLConnectionService.execute(request);
        }

        response.setPayload(this.decompressIfGZIP(response.getPayload()));
        response.setPayloadError(this.decompressIfGZIP(response.getPayloadError()));

        this.checkStatus(response);

        return response;
    }

    /**
     * Create an {@link ODataFilterBuilder} starting on the entitySetName provided.
     *
     * @param entitySetName &lt;EntitySet <b>Name</b>&gt value found in the DefaultEntityContainer of the OData service.
     * @return {@link ODataFilterBuilder}
     * @throws IOException if an I/O error occurs.
     */
    public ODataFilterBuilder filter(final String entitySetName) throws IOException {
        final EdmEntitySet entitySet = this.getEntitySet(entitySetName);
        return ODataFilterBuilder.of(entitySet, oDataEdmConvertor);
    }

    @Nonnull
    private StringBuilder getCustomQueryOptions() {
        final StringBuilder customQueryOptions = new StringBuilder("?saml2=disabled");
        if (this.sapClient != null && !this.sapClient.isEmpty()) {
            customQueryOptions.append("&sap-client=").append(this.sapClient);
        }
        return customQueryOptions;
    }

    /**
     * Provide buffered olingo's {@link Edm} of the OData service.
     *
     * @return And instance of {@link Edm} <code>$metadata</code> for the service end point set by
     * {@link #setRootUrl(String)}.<br>
     * This resource is requested once for the entire lifecycle of the bean. This resource is also synchronized.
     * <br>
     * Each Thread has its own instance of Edm.
     * @throws IOException If Edm could not be returned.
     */
    @Nonnull
    public Edm getEdm() throws IOException {
        if (this.edm != null) {
            return this.edm.get();
        }
        synchronized (this) {
            if (this.edm != null) {
                return this.edm.get();
            }

            final HttpURLConnectionRequest request = new HttpURLConnectionRequest("GET", this.createURL("$metadata"));
            request.getRequestProperties().put("Accept", "application/xml");

            final HttpURLConnectionResponse response = this.executeWithRetry(request);

            try (InputStream in = new ByteArrayInputStream(response.getPayload())) {
                final EdmProvider provider = new EdmxProvider().parse(in, false);
                this.edm = ThreadLocal.withInitial(() -> new EdmImplProv(provider));
                return this.edm.get();
            } catch (final EntityProviderException e) {
                throw new IllegalStateException("Invalid Edm $metadata at " + request.getURL(), e);
            }
        }
    }

    /**
     * Simple wrapping with improved error handling of :<br>
     * <code>this.getEdm().getDefaultEntityContainer().getEntitySet(entitySetName);</code>
     *
     * @param entitySetName &lt;EntitySet <b>Name</b>&gt value found in the DefaultEntityContainer of the OData service.
     * @return {@link EdmEntitySet}
     * @throws IOException In case of communication errors.
     */
    public EdmEntitySet getEntitySet(final String entitySetName) throws IOException {
        try {
            final EdmEntitySet entitySet = this.getEdm().getDefaultEntityContainer().getEntitySet(entitySetName);
            if (entitySet == null) {
                final List<String> entitySetNames = new ArrayList<>();
                final List<EdmEntitySet> entitySets = this.getEdm().getDefaultEntityContainer().getEntitySets();
                for (final EdmEntitySet es : entitySets) {
                    entitySetNames.add(es.getName());
                }

                throw new IllegalStateException(
                        "EntitySetName=" + entitySetName + " does not exists in " + entitySetNames + " at " + this.getTargetUrl());
            }
            return entitySet;
        } catch (final EdmException e) {
            throw new IllegalStateException("Error read entitySetName=" + entitySetName, e);
        }
    }

    /**
     * @param array the XML.
     * @return A String containing the XML pretty printed indented by 1 space.<br>
     * Or a String containing the original array if any exception occurs.
     */
    @Nonnull
    private static String prettyPrintXML(final byte[] array) {
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            final Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
            final StreamResult result = new StreamResult(new StringWriter());
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            transformer.transform(new DOMSource(builder.parse(new ByteArrayInputStream(array))), result);
            try (Writer writer = result.getWriter()) {
                return writer.toString();
            }
        } catch (final Exception e) {
            final String string = new String(array, StandardCharsets.UTF_8);
            LOG.warn("Transformer error xml='" + string + "'", e);
            return string;
        }
    }

    @Nonnull
    private TokenCache refreshToken() throws IOException {
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest("GET", this.createURL(""));
        request.getRequestProperties().put(X_CSRF_TOKEN, "Fetch");

        final HttpURLConnectionResponse response = this.executeWithBasicAuth(request);

        final TokenCache newToken = new TokenCache();

        List<String> cookies = new ArrayList<>();
        for (String cookie : response.getHeaderField("set-cookie")) {
            int endIndex = cookie.indexOf(';');
            if (endIndex >= 0) {
                // Only cookie-name and cookie-value are considered, no other cookie directives (like e.g. path, domain, etc.)
                cookies.add(cookie.substring(0, endIndex));
            }
        }
        newToken.setCookies = cookies;
        newToken.xcsrfTokens = response.getHeaderField(X_CSRF_TOKEN);

        /*
         * Note: empty lists for setCookies and xcsrfTokens are allowed (e.g. to support public accessible OData services
         * which don't required authentication and user session)
         */

        return newToken;
    }

    @Required
    public void setHttpURLConnectionService(final HttpURLConnectionService httpURLConnectionService) {
        this.httpURLConnectionService = httpURLConnectionService;
    }

    @Required
    public void setODataEdmConvertor(final ODataEdmConvertor oDataEdmConvertor) {
        this.oDataEdmConvertor = oDataEdmConvertor;
    }

    /**
     * @param user Username used to login at the {@link #setRootUrl(String)} address.
     */
    public void setUser(final String user) {
        this.user = user;
        this.updateBasicAuth();
    }

    /**
     * @param password Password used to login with {@link #setUser(String)} at the {@link #setRootUrl(String)} address.
     */
    public void setPassword(final String password) {
        this.password = password;
        this.updateBasicAuth();
    }

    private void updateBasicAuth() {

        if (this.user == null || this.password == null) {
            return;
        }

        final byte[] userAndPassword = (this.user + ':' + this.password).getBytes(StandardCharsets.UTF_8);
        basicAuth = "Basic " + Base64.getEncoder().encodeToString(userAndPassword);

        // Remove user & password from memory.
        this.user = null;
        this.password = null;
    }

    public void setProxy(final String proxy) throws MalformedURLException {
        LOG.debug("proxy={}", proxy);
        if (proxy == null || proxy.isEmpty()) {
            return;
        }
        final URL proxyURL = new URL(proxy);
        final String hostname = proxyURL.getHost();
        final int port = proxyURL.getPort() == -1 ? 8080 : proxyURL.getPort();
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
    }

    /**
     * @param sapClient the sapClient to set
     */
    public void setSapClient(final String sapClient) {
        LOG.debug("sapClient={}", sapClient);
        this.sapClient = sapClient;
    }

    /**
     * @param rootUrl OData service root URL.<br>
     *                Sample : <code>https://localhost:50100/sap/opu/odata/sap/SOME_SRV/</code> <br>
     *                <code>localhost</code> should be replaced with the proper SAP hybris Marketing server host name.
     */
    public void setRootUrl(final String rootUrl) {
        LOG.debug("rootUrl={}", rootUrl);
        this.rootUrl = rootUrl;
    }

    public String getRootUrl(){
        return this.rootUrl;
    }

    private String getTargetUrl() {
        // Check rootUrl based on back office configuration, only if not manually set in configuration (xml)
        if (rootUrl != null && !rootUrl.trim().isEmpty()) {
            /*
             * Note: As the root URL is based on spring xml, the user/password have to be based on spring xml as well
             *       Spring xml based configuration are for development only, never for production!
             */
            return getNormalizedTargetUrl(rootUrl); //configuration is based on spring xml
        }

        return getHttpDestination().targetUrl;
    }

    /**
     * Creates a normalized targetUrl which includes the corresponding service name (ending with '/').
     *
     * @param rootUrl the root URL without service name
     * @return the normalized target URL
     */
    private String getNormalizedTargetUrl(String rootUrl) {
        // Create a normalized targetUrl which includes the corresponding service name
        String normalizedRootUrl = rootUrl.endsWith("/") ? rootUrl : rootUrl.concat("/"); // Always append '/'

        String normalizedServiceName = serviceName;
        if (normalizedServiceName.startsWith("/")) {
            normalizedServiceName = normalizedServiceName.substring(1);
        }
        if (!normalizedServiceName.endsWith("/")) {
            normalizedServiceName = normalizedServiceName.concat("/");
        }

        return normalizedRootUrl + normalizedServiceName;
    }

    @Required
    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDestinationSystem() {
		return destinationSystem;
	}

	public void setDestinationSystem(String destinationSystem) {
		this.destinationSystem = destinationSystem;
	}

	@Required
    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Required
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Required
    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    /**
     * Adds the backend internet user name associated with the currently logged in user as HTTP request header attribute.
     *
     * @param request the HTTP request
     */
    private void addInternetUserIdentifier(HttpURLConnectionRequest request) {
    	
    	if(userService.getCurrentUser() != null && userService.getCurrentUser() instanceof EmployeeModel)
    	{
    		request.getRequestProperties().put("entityID", "ADMIN");
    	}
    	else
    	{
    		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
    		String internetUserId = customer.getInternetUserID();
    		
    		// Note: the value might be null which is a valid case as well
    		if (internetUserId != null) {
    			// Note: the parameter on the CRM side for internet user identifier is currently named entityID
    			request.getRequestProperties().put("entityID", internetUserId.toUpperCase(Locale.ENGLISH));
    		}
    	}
    }

    /**
     * Adds the ISO code of the current (user) session language as HTTP request header attribute.
     *
     * @param request the HTTP request
     */
    private void addSessionLanguage(HttpURLConnectionRequest request) {
        LanguageModel language = commonI18NService.getCurrentLanguage();
        Assert.notNull(language, "Current session language must not be null");
        Assert.notNull(language.getIsocode(), "Current session language ISO code must not be null");

        request.getRequestProperties().put("Accept-Language", language.getIsocode().toUpperCase(Locale.ENGLISH));
    }

    /**
     * Usage of double-check idiom to retrieve the HTTP destination (cf. Effective Java, 2nd Edition, Item 71)
     *
     * @return the http destination (which is configured in back office)
     */
    private HttpDestination getHttpDestination() {
        // Double-check idiom for lazy initialization of volatile instance fields
        HttpDestination result = httpDestination;

        if (result == null) { // First check without locking
            synchronized (this) {
                result = httpDestination;
                if (result == null) { // Second check with locking
                    httpDestination = result = createHttpDestination();
                }
            }
        }

        return result;
    }

    /**
     * Creates the HTTP destination for this OData service which consists of the target URL and the basic authorization string.
     * Required data are retrieved from the SAP Configuration of the current base store. The OData service name must already be
     * known.
     *
     * @return the HTTP destination
     */
    private HttpDestination createHttpDestination() {
        SAPHTTPDestinationModel model = getSAPHTTPDestinationModel();
        HttpDestination destination = new HttpDestination();
        // Create a normalized targetUrl which includes the corresponding service name
        destination.targetUrl = getNormalizedTargetUrl(model.getTargetURL());
        final byte[] credentials = (model.getUserid() + ':' + model.getPassword()).getBytes(StandardCharsets.UTF_8);
        destination.basicAuthorization = "Basic " + Base64.getEncoder().encodeToString(credentials);

        return destination;
    }

    /**
     * Retrieves the SAP HTTP destination model from the SAP configuration assigned to the current base store.
     *
     * @return the SAP HTTP destination model
     * @throws IllegalStateException if a configuration failure is detected
     */
    public SAPHTTPDestinationModel getSAPHTTPDestinationModel() {
    	
    	//TODO: Currently there is a defect, httpDestination object is not singleton. In future if we make httpDestination singleton then this class should have two different httpDestination object 1. CRM, 2. NetViewer gateway calls.  
    	SAPHTTPDestinationModel sapHttpDestination;
    	if(destinationSystem.equalsIgnoreCase(sagiaCRMDestinationSystem))
    	{
    		sapHttpDestination = httpDestinationService.getHttpDestinationsByName(sagiaCRMDestinationSystem);
    	}
    	else
    	{
	        BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
	        if (currentBaseStore == null) {
	            throw new IllegalStateException("No base store found to retrieve SAP configuration");
	        }
	
	        SAPConfigurationModel sapConfiguration = currentBaseStore.getSAPConfiguration();
	        if (sapConfiguration == null) {
	            throw new IllegalStateException("No SAP configuration found to retrieve HTTP destination");
	        }
	
	        sapHttpDestination = sapConfiguration.getSAPHTTPDestination();
	        if (sapHttpDestination == null) {
	            throw new IllegalStateException("No HTTP destination found for SAP Configuration");
	        }
    	}

        return sapHttpDestination;
    }

    /**
     * Listener for HTTP destination update events.
     */
    public class HttpDestinationEventListener extends AbstractEventListener<HttpDestinationPotentialUpdateEvent> {
        @Override
        protected void onEvent(HttpDestinationPotentialUpdateEvent event) {
            LOG.info("Event received that HTTP destination attributes have potentially changed, trigger read at next access (" + serviceName + ")");

            synchronized (ODataService.this) { // Acquire lock on outer class instance
                // Resets the HTTP destination and token
                httpDestination = null;
                token = null;
            }
        }
    }

    /**
     * Spring bean lifecycle method: post-construction callback.
     */
    public void init() {
        // Register listener to be aware of HTTP destination configuration changes (e.g. user / password)
        httpDestinationEventListener = new HttpDestinationEventListener();
        eventService.registerEventListener(httpDestinationEventListener);
        LOG.info("HTTP destination event listener registered for service (" + serviceName + ")");
    }

    /**
     * Spring bean lifecycle method: pre-destruction callback.
     */
    public void destroy() {
        if (httpDestinationEventListener != null) {
            eventService.unregisterEventListener(httpDestinationEventListener);
            LOG.info("HTTP destination event listener unregistered for service (" + serviceName + ")");
        }
    }
}
