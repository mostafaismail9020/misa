package com.sap.ibso.eservices.sagiaservices.services;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.attachments.AttachmentFile;
import com.sap.ibso.eservices.sagiaservices.services.error.ErrorResponse;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.olingo.odata2.api.client.batch.*;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.*;

/**
 * AbstractSagiaService
 * @param <DTO> dto
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public abstract class AbstractSagiaService<DTO> implements SagiaService<DTO> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSagiaService.class);

    private static final String HTTP_METHOD_POST = "POST";

    protected static final EntityProviderReadProperties NO_READ_PROPERTIES = EntityProviderReadProperties.init().build();
    public static final String VALUE = "$value";
    public static final String RESPONSE = "Response={}";
    public static final String ERROR_AFTER_PERFORM_UPDATE = "Error after perform update : {}";
    public static final String ERROR_ON_SAVING_DATA_TO_CRM = "Error on saving data to CRM : ";

    private ODataService oDataService;
    private String entitySetName;
    private String entityUrl;
    private ODataPopulator<DTO> oDataPopulator = new ODataPopulator<>();
    private ODataReversePopulator<DTO> oDataReversePopulator = new ODataReversePopulator<>();

    /**
     * setoDataPopulator
     * @param oDataPopulator oDataPopulator
     */
    public void setoDataPopulator(ODataPopulator<DTO> oDataPopulator) {
        this.oDataPopulator = oDataPopulator;
    }

    @Override
    public ODataPopulator<DTO> getoDataPopulator() {
        return oDataPopulator;
    }

    /**
     * setoDataReversePopulator
     * @param oDataReversePopulator oDataReversePopulator
     */
    public void setoDataReversePopulator(ODataReversePopulator<DTO> oDataReversePopulator) {
        this.oDataReversePopulator = oDataReversePopulator;
    }

    @Override
    public ODataReversePopulator<DTO> getoDataReversePopulator() {
        return oDataReversePopulator;
    }

    @Override
    public DTO get(Class<DTO> clazz) throws SagiaODataException {
        return getDTO(clazz, null, null);
    }

    @Override
    public DTO get(Class<DTO> clazz, String... queryOptions) throws SagiaODataException {
        return getDTO(clazz, null, queryOptions);
    }

    @Override
    public DTO get(Class<DTO> clazz, Object id) throws SagiaODataException {
        return getDTO(clazz, id, null);
    }

    @Override
    public DTO get(Class<DTO> clazz, Map<String, Object> id) throws SagiaODataException {
        return getDTO(clazz, id, null);
    }

    @Override
    public DTO get(Class<DTO> clazz, Collection<String> ids) throws SagiaODataException {
        return getDTO(clazz, ids, null);
    }

    @Override
    public DTO get(Class<DTO> clazz, Collection<String> ids, String... queryOptions) throws SagiaODataException {
        return getDTO(clazz, ids, queryOptions);
    }

    @Override
    public DTO get(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException {
        return getDTO(clazz, id, queryOptions);
    }
   
    @Override
    public DTO getDtoObject(Class<DTO> clazz, Map<String, String> queryOptions) {
    	LOG.info("Entity Url "+getEntityUrl());
    	final URL url = getoDataService().createURL(getEntityUrl(), queryOptions);
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
                    .getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init()
                    .mergeSemantic(false).build();
            ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, content, readerProperties);
            ODataModel model = new ODataModel(odataEntry);
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }
    
    @Override
    public DTO get(Class<DTO> clazz, Object id, Map<String, String> queryOptions) {
        final URL url = getoDataService().createURL(getEntityRequestUrl(id), queryOptions);
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
                    .getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init()
                    .mergeSemantic(false).build();
            ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, content, readerProperties);
            ODataModel model = new ODataModel(odataEntry);
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public DTO getByProperty(Class<DTO> clazz, String propertyName, String propertyValue) throws SagiaODataException {
        final URL url = getoDataService().createURL(getEntityByPropertyRequestUrl(propertyName, propertyValue));
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
                    .getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init()
                    .mergeSemantic(false).build();
            ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, content, readerProperties);
            ODataModel model = new ODataModel(odataEntry);
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public DTO getByProperties(Class<DTO> clazz, Map<String, String> properties, String... queryOptions) throws SagiaODataException {
        final URL url = getoDataService().createURL(getEntityRequestUrlByProperties(properties), queryOptions);
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init().mergeSemantic(false).build();
            ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, content, readerProperties);
            ODataModel model = new ODataModel(odataEntry);
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public Collection<DTO> getCollectionByProperties(Class<DTO> clazz, Map<String, String> properties, String... queryOptions) throws SagiaODataException {
        final URL url = getoDataService().createURL(getEntityRequestUrlByProperties(properties), queryOptions);
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            return getCollectionFromFeed(clazz, EntityProvider.readFeed(APPLICATION_JSON, entitySet, content, NO_READ_PROPERTIES));
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public Collection<DTO> getCollection(Class<DTO> clazz, Map<String, String> queryOptions) {
        final URL url = getoDataService().createURL(getEntityUrl(), queryOptions);
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, url);
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        try {
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            final InputStream content = new ByteArrayInputStream(response.getPayload());
            return getCollectionFromFeed(clazz, EntityProvider.readFeed(APPLICATION_JSON, entitySet, content, NO_READ_PROPERTIES));
        } catch (IOException | ODataMessageException | IllegalAccessException | InstantiationException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public InputStream getMediaEntity(String requestedEntity, Collection<String> ids) {
        try {
            final URL readEntityURL = getoDataService().createURL(createEntityNameWithMultipleIds(requestedEntity, ids) + "/" + VALUE);
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, readEntityURL);
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] mediaPayload = response.getPayload();
            return new ByteArrayInputStream(mediaPayload);
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public AttachmentFile downloadFile(String requestedEntity, Collection<String> ids) {
        try {
            AttachmentFile attachmentFile = new AttachmentFile();
            final URL readEntityURL = getoDataService().createURL(createEntityNameWithMultipleIds(requestedEntity, ids) + "/" + VALUE);
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, readEntityURL);
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] mediaPayload = response.getPayload();
            attachmentFile.setContent(new ByteArrayInputStream(mediaPayload));
            //response.getHeaderField("Content-Type")
            attachmentFile.setMimeType(response.getHeaderField("Content-Type").get(0));
            return attachmentFile;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public InputStream getMediaEntity(String requestedEntity, String method, Collection<String> ids) {
        try {
            final URL readEntityURL = this.oDataService.createURL(createEntityNameWithMultipleIds(requestedEntity + "/" + method, ids) + "/" + VALUE);
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, readEntityURL);
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            final HttpURLConnectionResponse response = this.oDataService.executeWithRetry(request);
            byte[] mediaPayload = response.getPayload();
            return new ByteArrayInputStream(mediaPayload);
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    protected byte[] compressGZIP(final byte[] payload) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzos = new GZIPOutputStream(baos)) {
                gzos.write(payload);
            }
            return baos.toByteArray();
        }
    }


    private String createEntityNameWithMultipleIds(String entityName, Collection<String> ids) {
        String returnedEntityName;
        if (ids != null) {
            String multipleIDs = ids.stream()
                    .map(id -> id)
                    .collect(Collectors.joining(","));
            returnedEntityName = entityName + "(" + multipleIDs + ")";
        } else {
            returnedEntityName = entityName;
        }
        return returnedEntityName;
    }

    private InputStream getContent(String entityUrl, String... queryOptions) throws IOException {
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, getoDataService().createURL(entityUrl, queryOptions));
        request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
        return new ByteArrayInputStream(getoDataService().executeWithRetry(request).getPayload());
    }

    private String getEntityRequestUrl(Object id) {
        StringBuilder entityRequestUrl = new StringBuilder(getEntityUrl());
        if (id == null) {
            return entityRequestUrl.toString();
        }
        entityRequestUrl.append("(");
        if (id instanceof String) {
            entityRequestUrl.append("'").append(id).append("'");
        } else if (id instanceof Map) {
            for (Object key : ((Map) id).keySet()) {
                entityRequestUrl.append(key).append("=");
                Object value = ((Map) id).get(key);
                if (value instanceof String) {
                    entityRequestUrl.append("'").append(value).append("'");
                } else {
                    entityRequestUrl.append(value);
                }
                entityRequestUrl.append(",");
            }
            entityRequestUrl = new StringBuilder(entityRequestUrl.substring(0, entityRequestUrl.length() - 1));
        } else {
            entityRequestUrl.append(id);
        }
        entityRequestUrl.append(")");

        return entityRequestUrl.toString();
    }

    private String getEntityByPropertyRequestUrl(String propertyName, String propertyValue) {
        StringBuilder entityRequestUrl = new StringBuilder(getEntityUrl());
        if (propertyName != null) {
            entityRequestUrl.append("(");
            entityRequestUrl.append(propertyName);
            entityRequestUrl.append("='");
            entityRequestUrl.append(propertyValue);
            entityRequestUrl.append("'");
            entityRequestUrl.append(")");
        }
        return entityRequestUrl.toString();
    }

    private String getEntityRequestUrlByProperties(Map<String, String> properties) {
        StringBuilder requestParams = new StringBuilder();
        boolean first = true;
        if(!MapUtils.isEmpty(properties) ) {
            requestParams.append("(");
            for (Map.Entry<String, String> entry : properties.entrySet())
            {
                if (!first) {
                    requestParams.append(",");
                }
                else {
                    first = false;
                }
                requestParams.append(entry.getKey());
                requestParams.append("='");
                requestParams.append(entry.getValue());
                requestParams.append("'");
            }
            requestParams.append(")");
        }
        return getEntityUrl() + requestParams.toString();
    }

    private List<String> getQueryOptions(String... queryOptions) {
        List<String> localQueryOptions = new ArrayList<>();
        if (queryOptions != null && queryOptions.length > 0) {
            localQueryOptions.addAll(Arrays.asList(queryOptions));
        }
        localQueryOptions.add(REQUEST_PARAMETER_FORMAT);
        localQueryOptions.add(FORMAT_JSON);
        return localQueryOptions;
    }

    @Override
    public Collection<DTO> getCollection(Class<DTO> clazz) throws SagiaODataException {
        return getDataAsCollection(clazz, null, null);
    }

    @Override
    public Collection<DTO> getCollection(Class<DTO> clazz, String... queryOptions) throws SagiaODataException {
        return getDataAsCollection(clazz, null, queryOptions);
    }

    @Override
    public Collection<DTO> getCollection(Class<DTO> clazz, Object id) throws SagiaODataException {
        return getDataAsCollection(clazz, id, null);
    }

    @Override
    public Collection<DTO> getCollection(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException {
        return getDataAsCollection(clazz, id, queryOptions);
    }

    private DTO getDTO(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException {
        try {
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            ODataModel model = new ODataModel(EntityProvider.readEntry(APPLICATION_JSON, entitySet,
                    getContent(getEntityRequestUrl(id), getQueryOptions(queryOptions).toArray(new String[0])), NO_READ_PROPERTIES));
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (ODataException | IllegalAccessException | InstantiationException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage());
        }
    }

    private DTO getDTO(Class<DTO> clazz, Collection<String> ids, String... queryOptions) throws SagiaODataException {
        try {
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            ODataModel model = new ODataModel(EntityProvider.readEntry(APPLICATION_JSON, entitySet,
                    getContent(createEntityNameWithMultipleIds(getEntitySetName(), ids), getQueryOptions(queryOptions).toArray(new String[0])), NO_READ_PROPERTIES));
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            return dto;
        } catch (ODataException | IllegalAccessException | InstantiationException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage());
        }
    }

    private List<BatchPart> buildBatchParts(Collection<String> entitySetNames) {
        List<BatchPart> batchParts = new ArrayList<>();
        Map headers = buildHeadersForBatchRequest();
        BatchQueryPart.BatchQueryPartBuilder builder = BatchQueryPart.method("GET").headers(headers);
        if (CollectionUtils.isNotEmpty(entitySetNames)) {
            entitySetNames.forEach(entity -> batchParts.add(builder.uri(entity).build()));
        }
        return batchParts;
    }

    /**
     * Get collection of objects of a given type using a batch request.
     *
     * @param clazz clazz
     * @param entitySetNames entitySetNames
     * @return collection of objects of a given type using a batch request.
     */
    @Override
    public Collection<DTO> getCollectionFromBatch(Class<DTO> clazz, Collection<String> entitySetNames) {
        try {
            final String boundary = "batch";
            final URL url = getoDataService().createURL("$batch");
            final HttpURLConnectionRequest request = getHttpURLConnectionRequestPost(boundary, url);

            try (InputStream writeBatchRequest = EntityProvider.writeBatchRequest(buildBatchParts(entitySetNames), boundary)) {
                byte[] payload = this.oDataService.bufferStream(writeBatchRequest);
                LOG.debug("{}", new String(payload, StandardCharsets.UTF_8));
                addPayloadOnRequest(request, payload);
            }

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            LOG.debug("{}", new String(response.getPayload(), StandardCharsets.UTF_8));
            LOG.debug("{}", response.getHeaderFields());

            try (ByteArrayInputStream content = new ByteArrayInputStream(response.getPayload())) {
                final String contentType = response.getHeaderField("content-type").get(0);
                List<BatchSingleResponse> responses = EntityProvider.parseBatchResponse(content, contentType);
                if (CollectionUtils.isEmpty(responses)
                        || responses.get(1) == null
                        || StringUtils.isEmpty(responses.get(1).getBody())) {
                    return Collections.emptyList();
                }
                final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
                        .getEntitySet(getEntitySetName());
                return getCollectionFromFeed(clazz,
                        EntityProvider.readFeed(APPLICATION_JSON, entitySet, new ByteArrayInputStream(responses.get(1).getBody().getBytes()), NO_READ_PROPERTIES));
            }
        } catch (Exception e) {
            LOG.error("Error sending $batch request to get RealEstate.", e);
            throw new SagiaODataException(new StringBuilder("Error sending $batch request to get RealEstate.").append(getEntitySetName()).toString());
        }
    }

    /**
     * Save object in CRM using a batch post request.
     *
     * @param object object
     * @param uri uri
     * @return true / false based on the success of the saving operation.
     */
    public boolean createObjectWithBatchPost(Object object, String uri) {
        List<BatchPart> batchParts = new ArrayList<>();
        BatchChangeSetPart changeSetPart = BatchChangeSetPart.method(HTTP_METHOD_POST)
                .headers(buildHeadersForBatchRequest())
                .body(new Gson().toJson(object))
                .uri(uri)
                .build();
        BatchChangeSet changeSet = BatchChangeSet.newBuilder().build();
        changeSet.add(changeSetPart);
        batchParts.add(changeSet);
        return postRequest(batchParts);
    }

    /**
     * creates ObjectsWithBatchPost
     * @param objects objects
     * @param uri uri
     * @return boolean
     */
    public boolean createObjectsWithBatchPost(List<?> objects, String uri) {
        return createObjectsWithBatchPost(objects, uri, FieldNamingPolicy.IDENTITY);
    }

    /**
     * Save object in CRM using a batch post request.
     *
     * @param objects list of objects
     * @param uri uri
     * @param fieldNamingPolicy fieldNamingPolicy
     * @return true / false based on the success of the saving operation.
     */
    public boolean createObjectsWithBatchPost(List<?> objects, String uri, FieldNamingPolicy fieldNamingPolicy) {
        List<BatchPart> batchParts = new ArrayList<>();
        BatchChangeSet changeSet = BatchChangeSet.newBuilder().build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicy)
                .create();
        objects.forEach(object ->{
            BatchChangeSetPart changeSetPart = BatchChangeSetPart.method(HTTP_METHOD_POST)
                    .headers(buildHeadersForBatchRequest())
                    .body(gson.toJson(object))
                    .uri(uri)
                    .build();
            changeSet.add(changeSetPart);
        });
        batchParts.add(changeSet);
        return postRequest(batchParts);
    }

    private boolean postRequest(List<BatchPart> batchParts) {
        final String boundary = "batch";
        final URL url = getoDataService().createURL("$batch");
        final HttpURLConnectionRequest request = getHttpURLConnectionRequestPost(boundary, url);

        InputStream writeBatchRequest = EntityProvider.writeBatchRequest(batchParts, boundary);
        byte[] payload = new byte[0];
        try {
            payload = this.oDataService.bufferStream(writeBatchRequest);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.debug("{}", new String(payload, StandardCharsets.UTF_8));
        try {
            addPayloadOnRequest(request, payload);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        final HttpURLConnectionResponse response;
        try {
            response = getoDataService().executeWithRetry(request);
            return "202".equals(String.valueOf(response.getResponseCode()));
        } catch (Exception e) {
            LOG.error("Error sending $batch request to CREATE Object. ", e);
            throw new SagiaODataException("Error sending $batch request to CREATE Object. ", e);
        }
    }

    private HttpURLConnectionRequest getHttpURLConnectionRequestPost(String boundary, URL url) {
        final HttpURLConnectionRequest request = new HttpURLConnectionRequest(HTTP_METHOD_POST, url);
        request.getRequestProperties().put("Accept", APPLICATION_JSON + "," + MULTIPART_MIXED);
        request.getRequestProperties().put("Content-Type", MULTIPART_MIXED + ";boundary=" + boundary);
        return request;
    }

    private void addPayloadOnRequest(HttpURLConnectionRequest request, byte[] payload) throws IOException {
        final byte[] payloadGZIP = compressGZIP(payload);
        request.getRequestProperties().put("Content-Encoding", "gzip");
        request.setPayload(payloadGZIP);
        LOG.debug("GZIP compression {} Bytes -> {} Bytes, {}% reduction.", payload.length, payloadGZIP.length,
                (float) 100.0 * (payload.length - payloadGZIP.length) / payload.length);
    }

    private Map buildHeadersForBatchRequest() {
        Map headers = new HashMap();
        headers.put("sap-contextid-accept", "header");
        headers.put("Accept", APPLICATION_JSON);
        headers.put("Accept-Language", "en");
        headers.put("DataServiceVersion", "2.0");
        headers.put("MaxDataServiceVersion", "2.0");
        headers.put("sap-cancel-on-close", "true");
        headers.put("content-type", "application/json;odata=verbose");
        return headers;
    }

    private Collection<DTO> getCollectionFromFeed(Class<DTO> clazz, ODataFeed feed) throws IllegalAccessException, InstantiationException {
        if (feed == null) {
            return Collections.emptyList();
        }
        List<ODataEntry> feedEntries = feed.getEntries();
        if (CollectionUtils.isEmpty(feedEntries)) {
            return Collections.emptyList();
        }
        Collection<DTO> collection = new ArrayList<>();
        for (ODataEntry entry : feedEntries) {
            ODataModel model = new ODataModel(entry);
            DTO dto = clazz.newInstance();
            getoDataPopulator().populate(model, dto);
            collection.add(dto);
        }
        return collection;
    }

    private Collection<DTO> getDataAsCollection(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException {
        try {
            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            return getCollectionFromFeed(clazz, EntityProvider.readFeed(APPLICATION_JSON, entitySet,
                    getContent(getEntityRequestUrl(id), getQueryOptions(queryOptions).toArray(new String[0])), NO_READ_PROPERTIES));
        } catch (ODataException | IllegalAccessException | InstantiationException | IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage());
        }
    }

    /**
     * Saves DTO model
     * @param model model
     * @return payload
     * @throws SagiaODataException exception
     */
    public String save(DTO model) {
        try {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST,
                    getoDataService().createURL(getEntitySetName()));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);
            ODataModel oDataModel = new ODataModel();
            getoDataReversePopulator().populate(model, oDataModel);
            request.setPayload(oDataModel.getAsPayload());
			if(LOG.isDebugEnabled())
			{
				oDataModel.getMap().forEach((k,v)-> LOG.debug(k+", "+v));
			}
            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String payload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, payload);
            return payload;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage(), "Could not save data to CRM");
        }
    }

    /**
     * saveAndParseResponse
     *
     * @param model model
     * @param clazz clazz
     * @return DTO
     * @throws SagiaODataException exception
     */
	public DTO saveAndParseResponse(DTO model, Class<DTO> clazz) throws SagiaODataException {
		try {
			final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST,
					getoDataService().createURL(getEntitySetName()));
			request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
			request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
			request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);
			ODataModel oDataModel = new ODataModel();
			getoDataReversePopulator().populate(model, oDataModel);
			LOG.debug(new Gson().toJson(oDataModel.getMap()));
			request.setPayload(oDataModel.getAsPayload());
			if(LOG.isDebugEnabled())
			{
				oDataModel.getMap().forEach((k,v)-> LOG.debug(k+", "+v));
			}
			final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
			byte[] payloadAsByte = response.getPayload();
			if (payloadAsByte == null || payloadAsByte.length == 0) {
				return null;
			}
            String payload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, payload);
			return createDTOFromPayload(clazz, payloadAsByte);
		} catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage());
		}
	}

    /**
     * Saves DTO
     * @param model model
     * @param id id
     * @return payload
     * @throws SagiaODataException exception
     */
    @Override
    public DTO saveAndParseResponse(DTO model, Object id, Class<DTO> clazz) throws SagiaODataException {
        ODataModel oDataModel = new ODataModel();
        getoDataReversePopulator().populate(model, oDataModel);

        try {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_PUT, getoDataService().createURLSingleEntry(getEntitySetName(), id));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);
            request.setPayload(oDataModel.getAsPayload());

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            LOG.debug("Response payload : {}", new String(payloadAsByte, StandardCharsets.UTF_8));
            return createDTOFromPayload(clazz, payloadAsByte);
        } catch (IOException e) {
            LOG.debug(ERROR_AFTER_PERFORM_UPDATE, e.getMessage(),e);
            throw new SagiaCRMException(e.getMessage());
        }
    }

	private DTO createDTOFromPayload(Class<DTO> clazz, byte[] payloadAsByte) {
		try {
			final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
					.getEntitySet(getEntitySetName());
			final InputStream content = new ByteArrayInputStream(payloadAsByte);
			final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init()
					.mergeSemantic(false).build();
			ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, content, readerProperties);
			ODataModel modelResponse = new ODataModel(odataEntry);
			DTO dto = clazz.newInstance();
			getoDataPopulator().populate(modelResponse, dto);
			return dto;
		} catch (IOException | EdmException | EntityProviderException | IllegalAccessException | InstantiationException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaODataException(e.getMessage(), "could not read response from saving request");
		}
	}

    /**
     * Saves DTO
     * @param model model
     * @param id id
     * @return payload
     * @throws SagiaODataException exception
     */
    @Override
    public String save(DTO model, Object id) throws SagiaODataException {
        ODataModel oDataModel = new ODataModel();
        getoDataReversePopulator().populate(model, oDataModel);

        try {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_PUT, getoDataService().createURLSingleEntry(getEntitySetName(), id));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);
            request.setPayload(oDataModel.getAsPayload());

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String payload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug("Response payload : {}", new String(response.getPayload(), StandardCharsets.UTF_8));
            return payload;
        } catch (IOException e) {
            LOG.debug(ERROR_AFTER_PERFORM_UPDATE, e.getMessage(),e);
            throw new SagiaODataException(ERROR_ON_SAVING_DATA_TO_CRM + e.getMessage());
        }
    }

    /**
     * Saves DTO
     * @param model model
     * @param ids ids
     * @return payload
     * @throws SagiaODataException exception
     */
    public String save(DTO model, Collection<String> ids) throws SagiaODataException {
        ODataModel oDataModel = new ODataModel();
        getoDataReversePopulator().populate(model, oDataModel);
        try {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_PUT, getoDataService().createURL(createEntityNameWithMultipleIds(getEntitySetName(), ids), Collections.emptyMap()));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);
            request.setPayload(oDataModel.getAsPayload());

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String payload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug("Response payload : {}", new String(response.getPayload(), StandardCharsets.UTF_8));
            return payload;
        } catch (IOException e) {
            LOG.debug(ERROR_AFTER_PERFORM_UPDATE, e.getMessage(),e);
            throw new SagiaODataException(ERROR_ON_SAVING_DATA_TO_CRM + e.getMessage());
        }
    }

    @Override
    public String create(final Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            byte[] payload = mapper.writeValueAsBytes(data);
            LOG.debug(mapper.writeValueAsString(data));

            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST,
                    getoDataService().createURL(getEntitySetName())
            );
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.setPayload(payload);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String responsePayload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, responsePayload);
            return responsePayload;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new SagiaODataException("Could not save data to CRM!");
        }
    }

    @Override
    public String createWithParameters(final Object data, Map<String, String> parameters) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            byte[] payload = mapper.writeValueAsBytes(data);
            LOG.debug(mapper.writeValueAsString(data));

            final URL url = getoDataService().createURL(getEntityUrl());
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST, url);

            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.setPayload(payload);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if(payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String responsePayload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, responsePayload);
            return responsePayload;
        } catch (IOException e) {
            LOG.error("Error creating entity: " + e.getMessage(), e);
            throw new SagiaODataException("Error creating entity: " + e.getMessage(), e);
        }
    }

    @Override
    public String upload(String slug, InputStream content, Map<String, String> fieldsMap, String uploadUrl) throws SagiaODataException {
        try {
            final URL url = getoDataService().createURL(getEntityRequestUrlByProperties(fieldsMap) + uploadUrl);
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST, url);
            request.getRequestProperties().put("slug", slug);
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, "application/atom+xml");
            request.getRequestProperties().put("X-Requested-With", "X");
            request.getRequestProperties().put("Content-Disposition", "attachment; filename=\"filename.pdf\"");

            byte[] payloadBytes = IOUtils.toByteArray(content);
            request.setPayload(payloadBytes);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if(payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String responsePayload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, responsePayload);
            return responsePayload;
        } catch (IOException e) {
            LOG.error("Error on create data to CRM", e);
            throw new SagiaODataException("Error on create data to CRM : " + e.getMessage());
        }
    }

    @Override
    public String createWithErrorHandling(final Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            byte[] payload = mapper.writeValueAsBytes(data);
            LOG.debug(mapper.writeValueAsString(data));

            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST, getoDataService().createURL(getEntitySetName()));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.setPayload(payload);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String responsePayload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, responsePayload);
            /* It means that there were no errors*/
            return null;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            final ErrorResponse errorResponse = getMessageFromException(e.getMessage());
            if(errorResponse != null) {
                return errorResponse.getError().getMessage().getValue();
            }
            throw new SagiaODataException("Could not save data to CRM! ");
        }
    }

    private ErrorResponse getMessageFromException(final String message) {
        try {
            final String jsonString = message.substring(message.indexOf('{'));
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString, new TypeReference<ErrorResponse>() {});
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            ErrorResponse errorResponse = new ErrorResponse();
            com.sap.ibso.eservices.sagiaservices.services.error.Error error = new com.sap.ibso.eservices.sagiaservices.services.error.Error();
            com.sap.ibso.eservices.sagiaservices.services.error.Message messageObject = new com.sap.ibso.eservices.sagiaservices.services.error.Message();
            messageObject.setLang("en");
            messageObject.setValue(message);
            error.setMessage(messageObject);
            error.setCode("error");
            errorResponse.setError(error);
            return errorResponse;
        }
    }

    @Override
    public void checkValidResponseCode(Object id, String... queryOptions) {
        try {
            HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_GET, getoDataService().createURL(entityUrl, queryOptions));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            getoDataService().executeWithRetry(request);
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SagiaCRMException(ex.getMessage());
        }
    }

    @Override
    public String delete(DTO entity) {
        throw new UnsupportedOperationException();
        // some code that removes an entity from CRM
    }

    @Override
    public String delete(final Map<String, String> parameters) {
        try {
            final URL url = getoDataService().createURL(getEntityRequestUrlByProperties(parameters));
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_DELETE, url);
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            byte[] payloadAsByte = response.getPayload();
            if (payloadAsByte == null || payloadAsByte.length == 0) {
                return null;
            }
            String responsePayload = new String(payloadAsByte, StandardCharsets.UTF_8);
            LOG.debug(RESPONSE, responsePayload);
            return null;
        }
        catch (IOException ex) {
            LOG.debug(ERROR_AFTER_PERFORM_UPDATE, ex.getMessage(),ex);
            throw new SagiaODataException("Error on delete data from CRM : " + ex.getMessage());
        }
    }

    @Override
    public ODataService getoDataService() {
        return oDataService;
    }

    /**
     * setoDataService
     * @param oDataService oDataService
     */
    public void setoDataService(ODataService oDataService) {
        this.oDataService = oDataService;
    }

    @Override
    public String getEntitySetName() {
        return entitySetName;
    }

    /**
     * setEntitySetName
     * @param entitySetName entitySetName
     */
    public void setEntitySetName(String entitySetName) {
        this.entitySetName = entitySetName;
    }

    @Override
    public String getEntityUrl() {
        return entityUrl;
    }

    /**
     * setEntityUrl
     * @param entityUrl entityUrl
     */
    public void setEntityUrl(String entityUrl) {
        this.entityUrl = entityUrl;
    }
}
