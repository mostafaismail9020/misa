/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * Moscow, Russian Federation
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.facades.data.ProfileGeneralManagerData;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceBpHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.contacts.ContactsConverter;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.ContactUpdateDTO;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import org.fest.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.*;

/**
 * ServiceBpHDRService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ServiceBpHDRService extends AbstractSagiaService<ServiceBpHDRData> {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceBpHDRService.class);

    private static final String SERVICE_BP_HDR_EXPAND = "SrvBPHdrToBPContactNav";
    private static final String SERVICE_CONTACT_UPDATE_HISTORY_EXPAND = "SrvBPHdrToAttachNav,SrvBPHdrToBPContactNav,SrvBPHdrToTextNav";
    private static final String SR_ST_CODE_APPROVED = "Approved";
    private static final String GENERAL_MANAGER_CODE = "GM";
    private static final String POST_METHOD = "POST";

    /**
     * creates UpdateRequest
     * @param contactUpdateForm contactUpdateForm
     */
    public void createUpdateRequest(final ContactUpdateForm contactUpdateForm) {
        performRequest(ContactsConverter.convert(contactUpdateForm));
    }

    /**
     * performs Request
     *
     * @param data data
     */
    private void performRequest(final ContactUpdateDTO data) {
        try {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(POST_METHOD,
                    getoDataService().createURL(getEntitySetName()));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put("Content-Type", APPLICATION_JSON);
            ObjectMapper mapper = new ObjectMapper();

            byte[] payload = mapper.writeValueAsBytes(data);
            LOG.debug(mapper.writeValueAsString(data));
            request.setPayload(payload);

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);
            LOG.debug("Response payload : {}", new String(response.getPayload(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOG.debug("Error after perform update : {}", e.getMessage(), e);
            throw new SagiaCRMException(e.getMessage());
        }
    }

    /**
     * fills DocumentsFromServiceBPHDR
     * @param generalManagerData generalManagerData
     * @param companyRepresentativesData the companyRepresentativesData
     */
    public final void fillDocumentsFromServiceBPHDR(final ProfileGeneralManagerData generalManagerData, final List<ProfileCompanyRepresentativeData> companyRepresentativesData) {
        final Collection<ServiceBpHDRData> serviceBpHDRData = getCollection();
        if(serviceBpHDRData == null || serviceBpHDRData.isEmpty()) {
            return;
        }

        final Map<String, ProfileCompanyRepresentativeData> representativesIds = new HashMap<>();
        companyRepresentativesData.forEach(companyRepresentativeData ->
                representativesIds.put(companyRepresentativeData.getContactType(), companyRepresentativeData));

        updateRepresentativeData(generalManagerData, serviceBpHDRData, representativesIds);

        setDocuments(generalManagerData, companyRepresentativesData);
    }

    /*
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S134","squid:S3776"})
    private void updateRepresentativeData(ProfileGeneralManagerData generalManagerData, Collection<ServiceBpHDRData> serviceBpHDRData, Map<String, ProfileCompanyRepresentativeData> representativesIds) {
        int changedCount = 0;
        int existedCompanyRepresentatives = representativesIds.size();
        for (final ServiceBpHDRData data : serviceBpHDRData) {
            if (data.getBpChangeID() != null && SR_ST_CODE_APPROVED.equals(data.getSrStDesc())) {
                if (data.getBpChangeID().contains("GM")) {
                    generalManagerData.setSrId(data.getSrID());
                }

                for (final String type : data.getBpChangeID().split(",")) {
                    if (representativesIds.containsKey(type)) {
                        final ProfileCompanyRepresentativeData representativeData = representativesIds.get(type);
                        if (Strings.isEmpty(representativeData.getSrId())) {
                            changedCount++;
                            representativeData.setSrId(data.getSrID());
                        }
                    }
                }
            }

            if (changedCount == existedCompanyRepresentatives) {
                break;
            }
        }
    }

    private void setDocuments(ProfileGeneralManagerData generalManagerData, List<ProfileCompanyRepresentativeData> companyRepresentativesData) {
        final Map<String, ServiceBpHDRData> alreadyRequestedData = new HashMap<>();
        if (!Strings.isEmpty(generalManagerData.getSrId())) {
            final ServiceBpHDRData data = get(generalManagerData.getSrId());
            alreadyRequestedData.put(generalManagerData.getSrId(), data);

            data.getSrvBPHdrToBPContactDataSet().forEach(dataElement -> {
                if (GENERAL_MANAGER_CODE.equals(dataElement.getContactType())) {
                    generalManagerData.setDocId(dataElement.getDocumentID());
                }
            });
        }

        companyRepresentativesData.forEach(representativeData -> {
            if (!Strings.isEmpty(representativeData.getSrId())) {
                final ServiceBpHDRData requestedData;
                if (alreadyRequestedData.containsKey(representativeData.getSrId())) {
                    requestedData = alreadyRequestedData.get(representativeData.getSrId());
                } else {
                    requestedData = get(representativeData.getSrId());
                }

                requestedData.getSrvBPHdrToBPContactDataSet().forEach(dataElement -> {
                   if (representativeData.getContactType().equals(dataElement.getContactType())) {
                       representativeData.setDocId(dataElement.getDocumentID());
                       representativeData.setDocGovId(dataElement.getGosiID());
                    }
                });
            }
        });
    }

    /**
     * retrieves Collection of ServiceBpHDRData
     * @return Collection of ServiceBpHDRData
     */
    public Collection<ServiceBpHDRData> getCollection() {
        return super.getCollection(ServiceBpHDRData.class, REQUEST_PARAMETER_EXPAND, SERVICE_BP_HDR_EXPAND);
    }

    /**
     * retrieves ServiceBpHDRData by id
     * @param id id
     * @return ServiceBpHDRData
     */
    public ServiceBpHDRData get(final String id) {
        return super.get(ServiceBpHDRData.class, (Object) id, REQUEST_PARAMETER_EXPAND, SERVICE_BP_HDR_EXPAND);
    }

    /**
     * Return a contact update history entry.
     * @param id - The id for which the history entry is retrieved.
     * @return - The history entry with that specific id.
     */
    public ServiceBpHDRData getHistoryEntry(String id){
        return super.get(ServiceBpHDRData.class, (Object) id, REQUEST_PARAMETER_EXPAND, SERVICE_CONTACT_UPDATE_HISTORY_EXPAND);
    }

    /**
     * Retrieves all the entities without any expand.
     *
     * @return - List of entities
     */
    public Collection<ServiceBpHDRData> getAll(){
        return super.getCollection(ServiceBpHDRData.class);
    }
}
