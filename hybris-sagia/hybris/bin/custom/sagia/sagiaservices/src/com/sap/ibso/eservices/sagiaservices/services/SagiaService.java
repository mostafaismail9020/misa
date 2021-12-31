package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.attachments.AttachmentFile;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * SagiaService
 * @param <DTO> dto
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaService<DTO> {

    /**
     * retrieves DTO
     * @param clazz clazz
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param queryOptions queryOptions
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param id id
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, Object id) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param id id
     * @param queryOptions queryOptions
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param id id
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, Map<String, Object> id) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param ids ids
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, Collection<String> ids) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param ids ids
     * @param queryOptions queryOptions
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO get(Class<DTO> clazz, Collection<String> ids, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves DTO
     * @param clazz clazz
     * @param id id
     * @param queryOptions queryOptions
     * @return DTO
     */
    DTO get(Class<DTO> clazz, Object id, Map<String, String> queryOptions);

    /**
     * getByProperty
     * @param clazz clazz
     * @param propertyName propertyName
     * @param propertyValue propertyValue
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO getByProperty(Class<DTO> clazz, String propertyName, String propertyValue) throws SagiaODataException;

    /**
     * retrieves DTO By Properties
     * @param clazz clazz
     * @param properties properties
     * @param queryOptions queryOptions
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO getByProperties(Class<DTO> clazz, Map<String, String> properties, String... queryOptions) throws SagiaODataException;
    
    /**
     * retrieves DTO By Properties
     * @param clazz clazz
     * @param queryOptions queryOptions
     * @return DTO
     * @throws SagiaODataException exception
     */
    DTO getDtoObject(Class<DTO> clazz, Map<String, String> queryOptions) throws SagiaODataException;

    /**
     * retrieves Collection of DTO By Properties
     * @param clazz clazz
     * @param properties properties
     * @param queryOptions queryOptions
     * @return Collection
     * @throws SagiaODataException exception
     */
    Collection<DTO> getCollectionByProperties(Class<DTO> clazz, Map<String, String> properties, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves Collection of DTO
     * @param clazz clazz
     * @return Collection
     * @throws SagiaODataException exception
     */
    Collection<DTO> getCollection(Class<DTO> clazz) throws SagiaODataException;

    /**
     * retrieves Collection of DTO by id
     * @param clazz clazz
     * @param id id
     * @return Collection
     * @throws SagiaODataException exception
     */
    Collection<DTO> getCollection(Class<DTO> clazz, Object id) throws SagiaODataException;

    /**
     * retrieves Collection of DTO
     * @param clazz clazz
     * @param queryOptions queryOptions
     * @return Collection
     * @throws SagiaODataException exception
     */
    Collection<DTO> getCollection(Class<DTO> clazz, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves Collection of DTO
     * @param clazz clazz
     * @param id id
     * @param queryOptions queryOptions
     * @return Collection
     * @throws SagiaODataException exception
     */
    Collection<DTO> getCollection(Class<DTO> clazz, Object id, String... queryOptions) throws SagiaODataException;

    /**
     * retrieves Collection of DTO
     * @param clazz clazz
     * @param queryOptions queryOptions
     * @return Collection
     */
    Collection<DTO> getCollection(Class<DTO> clazz, Map<String, String> queryOptions);

    /**
     * retrieves Collection of DTO From Batch
     * @param clazz clazz
     * @param entitySetNames entitySetNames
     * @return Collection
     */
    Collection<DTO> getCollectionFromBatch(Class<DTO> clazz, Collection<String> entitySetNames);

    /**
     * retrieves MediaEntity
     * @param requestedEntity requestedEntity
     * @param ids ids
     * @return InputStream
     */
    InputStream getMediaEntity(String requestedEntity, Collection<String> ids);

    /**
     * retrieves MediaEntity
     * @param requestedEntity requestedEntity
     * @param method method
     * @param ids ids
     * @return InputStream
     */
    InputStream getMediaEntity(String requestedEntity, String method, Collection<String> ids);

    /**
     * downloads File
     * @param requestedEntity requestedEntity
     * @param ids ids
     * @return AttachmentFile
     */
    AttachmentFile downloadFile(String requestedEntity, Collection<String> ids);

    /**
     * saves DTO
     * @param entity entity
     * @return String
     */
    String save(DTO entity);

    /**
     * saves And Parses Response
     * @param model model
     * @param clazz clazz
     * @return DTO
     */
    public DTO saveAndParseResponse(DTO model, Class<DTO> clazz);

    /**
     * saves And Parses Response
     * @param model model
     * @param id id
     * @param clazz clazz
     * @return DTO
     */
    public DTO saveAndParseResponse(DTO model, Object id, Class<DTO> clazz);
    /**
     * saves DTO
     * @param model model
     * @param id id
     * @return String
     */
    String save(DTO model, Object id);

    /**
     * deletes DTO
     * @param entity entity
     * @return String
     */
    String delete(DTO entity);

    /**
     * deletes DTO
     * @param parameters parameters
     * @return String
     */
    String delete(Map<String, String> parameters);

    /**
     * creates DTO
     * @param payloadObject payloadObject
     * @return String
     */
    String create(Object payloadObject);

    /**
     * createWithParameters
     * @param data data
     * @param parameters parameters
     * @return String
     */
    String createWithParameters(final Object data, Map<String, String> parameters);

    /**
     * creates WithErrorHandling
     * @param payloadObject payloadObject
     * @return String
     */
    String createWithErrorHandling(Object payloadObject);


    /**
     * uploads InputStream
     * @param slug slug
     * @param content content
     * @param fieldsMap ids
     * @param uploadUrl uploadUrl
     * @return String
     */
    String upload(String slug, InputStream content, Map<String, String> fieldsMap, String uploadUrl);

    /**
     * checks ValidResponseCode
     * @param id id
     * @param queryOptions queryOptions
     */
    void checkValidResponseCode(Object id, String... queryOptions);

    /**
     * retrieves EntityUrl
     * @return String
     */
    String getEntityUrl();

    /**
     * retrieves EntitySetName
     * @return String
     */
    String getEntitySetName();

    /**
     * retrieves oDataService
     * @return ODataService
     */
    ODataService getoDataService();

    /**
     * retrieves oDataPopulator
     * @return ODataPopulator
     */
    ODataPopulator<DTO> getoDataPopulator();

    /**
     * retrieves oDataReversePopulator
     * @return ODataReversePopulator
     */
    ODataReversePopulator<DTO> getoDataReversePopulator();
}
