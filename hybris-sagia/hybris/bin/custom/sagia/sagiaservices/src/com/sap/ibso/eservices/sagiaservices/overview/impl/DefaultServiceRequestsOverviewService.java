package com.sap.ibso.eservices.sagiaservices.overview.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.overview.ServiceRequestsOverviewBackendService;
import com.sap.ibso.eservices.bol.overview.data.ServiceRequestBackendData;
import com.sap.ibso.eservices.bol.overview.data.ServiceRequestsBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.model.StatusModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeCategoryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeService;
import com.sap.ibso.eservices.core.sagia.services.SagiaStatusService;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.overview.ServiceRequestsOverviewService;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestsOverviewData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.util.*;


/**
 * Implements access to an overview of e-service requests.
 */
public class DefaultServiceRequestsOverviewService extends BackendAwareService implements ServiceRequestsOverviewService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultServiceRequestsOverviewService.class);

    private CommonI18NService commonI18NService;
    private InvestorMappingService investorMappingService;
    private SagiaServiceTypeService serviceTypeService;
    private SagiaServiceTypeCategoryService serviceTypeCategoryService;
    private SagiaStatusService statusService;

    /**
     * Creates the default service requests overview service.
     *
     * @param serviceRequestsOverviewBackendServiceBeanName the service requests overview backend service bean name
     * @param commonI18NService                             the session service to retrieve the currently used session language
     * @param investorMappingService                        the investor mapping service to retrieve the entity identifier associated with the current user
     * @param serviceTypeService                            the service to retrieve service type information (e.g. overview relevance, language dependent names)
     * @param serviceTypeCategoryService                    the service to retrieve service type category information (e.g. language dependent names)
     * @param statusService                                 the service to retrieve status information (e.g. language dependent names)
     */
    public DefaultServiceRequestsOverviewService(String serviceRequestsOverviewBackendServiceBeanName, CommonI18NService commonI18NService,
                                                 InvestorMappingService investorMappingService, SagiaServiceTypeService serviceTypeService,
                                                 SagiaServiceTypeCategoryService serviceTypeCategoryService, SagiaStatusService statusService)
    {
        super(serviceRequestsOverviewBackendServiceBeanName);
        this.commonI18NService = commonI18NService;
        this.investorMappingService = investorMappingService;
        this.serviceTypeService = serviceTypeService;
        this.serviceTypeCategoryService = serviceTypeCategoryService;
        this.statusService = statusService;
    }

    @Override
    public ServiceRequestsOverviewData getServiceRequests()
    {
        // Retrieve overview relevant service types
        Map<String, ServiceTypeModel> serviceTypesByCodes = serviceTypeService.getOverviewRelevantServiceTypesByCodes();
        // Retrieve service type categories for overview relevant service types only
        Map<String, ServiceTypeCategoryModel> serviceTypeCategoriesByCodes = serviceTypeCategoryService.getOverviewRelevantServiceTypeCategoriesByCodes();

        // Backend service access
        ServiceRequestsOverviewBackendService backendService = getBackendService();
        // Retrieve overview relevant service requests for the current user in the corresponding backend system
        ServiceRequestsBackendData serviceRequestsBackendData =
                backendService.getServiceRequests(investorMappingService.getEntityId(), serviceTypesByCodes, serviceTypeCategoriesByCodes.keySet());

        // Log potential backend messages
        MessageUtil.logBackendMessages(serviceRequestsBackendData.getMessages(), LOGGER);

        return convert(serviceRequestsBackendData.getServiceRequests(), serviceTypesByCodes, serviceTypeCategoriesByCodes);
    }

    @Override
    public ServiceRequestsOverviewData translate(ServiceRequestsOverviewData overviewData, String language)
    {
        Assert.notNull(overviewData, "Service requests overview data instance must not be null");
        Assert.notNull(overviewData.getServiceRequests(), "List of service requests must not be null");
        Assert.notNull(language, "Language ISO code must not be null");

        // Retrieve overview relevant service types
        Map<String, ServiceTypeModel> serviceTypesByCodes = serviceTypeService.getOverviewRelevantServiceTypesByCodes();
        // Retrieve service type categories for overview relevant service types only
        Map<String, ServiceTypeCategoryModel> serviceTypeCategoriesByCodes = serviceTypeCategoryService.getOverviewRelevantServiceTypeCategoriesByCodes();

        ServiceRequestsOverviewData result = new ServiceRequestsOverviewData();
        result.setLanguageIsoCode(language);

        List<ServiceRequestData> resultServiceRequests = new ArrayList<>(overviewData.getServiceRequests().size());
        result.setServiceRequests(resultServiceRequests);

        // Retrieve statuses
        Map<String, StatusModel> statusesByCodes = statusService.getStatusesByCodes();

        for (ServiceRequestData source : overviewData.getServiceRequests())
        {
            ServiceRequestData target = new ServiceRequestData();

            target.setServiceRequestNumber(source.getServiceRequestNumber());
            target.setServiceRequestCreationDate(source.getServiceRequestCreationDate());

            // Set status code, service type code and categories codes
            target.setServiceRequestStatusCode(source.getServiceRequestStatusCode());
            target.setServiceTypeCode(source.getServiceTypeCode());
            target.setCategoryLevelOneCode(source.getCategoryLevelOneCode());
            target.setCategoryLevelTwoCode(source.getCategoryLevelTwoCode());
            target.setCategoryLevelThreeCode(source.getCategoryLevelThreeCode());
            target.setCategoryLevelFourCode(source.getCategoryLevelFourCode());

            // Set language dependent names and descriptions
            populateLanguageDependentData(source, target, language, serviceTypesByCodes, serviceTypeCategoriesByCodes, statusesByCodes);

            resultServiceRequests.add(target);
        }

        return result;
    }

    /**
     * Converts a list of {@link ServiceRequestBackendData} instances into a list of {@link ServiceRequestData} instances.
     *
     * @param backendDataList              the list of service request backend data
     * @param serviceTypesByCodes          the overview relevant service types
     * @param serviceTypeCategoriesByCodes the service type categories for overview relevant service types
     * @return the overview data of service requests
     */
    private ServiceRequestsOverviewData convert(List<ServiceRequestBackendData> backendDataList,
                                                Map<String, ServiceTypeModel> serviceTypesByCodes,
                                                Map<String, ServiceTypeCategoryModel> serviceTypeCategoriesByCodes)
    {
        ServiceRequestsOverviewData overviewData = new ServiceRequestsOverviewData();

        // ISO code of current session language
        String language = getSessionLanguage();
        overviewData.setLanguageIsoCode(language);

        if (backendDataList.isEmpty())
        {
            overviewData.setServiceRequests(Collections.emptyList());
            return overviewData;
        }

        List<ServiceRequestData> serviceRequests = new ArrayList<>(backendDataList.size());
        overviewData.setServiceRequests(serviceRequests);

        // Retrieve statuses
        Map<String, StatusModel> statusesByCodes = statusService.getStatusesByCodes();

        for (ServiceRequestBackendData backendData : backendDataList)
        {
            ServiceRequestData serviceRequest = new ServiceRequestData();

            serviceRequest.setServiceRequestNumber(backendData.getServiceRequestId());
            serviceRequest.setServiceRequestCreationDate(backendData.getServiceRequestCreationDate());

            // Set status code, service type code and categories codes
            serviceRequest.setServiceRequestStatusCode(backendData.getServiceRequestStatusCode());
            serviceRequest.setServiceTypeCode(backendData.getServiceTypeCode());
            serviceRequest.setCategoryLevelOneCode(backendData.getCategoryLevelOneCode());
            serviceRequest.setCategoryLevelTwoCode(backendData.getCategoryLevelTwoCode());
            serviceRequest.setCategoryLevelThreeCode(backendData.getCategoryLevelThreeCode());
            serviceRequest.setCategoryLevelFourCode(backendData.getCategoryLevelFourCode());

            // Set default names: codes for status and service type, empty strings for categories
            serviceRequest.setServiceRequestStatusDescription(serviceRequest.getServiceRequestStatusCode());
            serviceRequest.setServiceTypeName(serviceRequest.getServiceTypeCode());
            serviceRequest.setCategoryLevelOneName("");
            serviceRequest.setCategoryLevelTwoName("");
            serviceRequest.setCategoryLevelThreeName("");
            serviceRequest.setCategoryLevelFourName("");

            // Set language dependent names and descriptions
            populateLanguageDependentData(serviceRequest, serviceRequest, language, serviceTypesByCodes, serviceTypeCategoriesByCodes, statusesByCodes);

            // Add to list
            serviceRequests.add(serviceRequest);
        }

        return overviewData;
    }

    /**
     * Populates language dependent names and descriptions for a target language into a target service request data instance.
     * A source service request data instance provides access codes to retrieve the language dependent names and descriptions
     * as well as default names in case a name does not exists in target language.
     *
     * @param source                       the source service request
     * @param target                       the target service request
     * @param language                     the target language
     * @param serviceTypesByCodes          the map provides access to language dependent names and descriptions of service types
     * @param serviceTypeCategoriesByCodes the map provides access to language dependent names and descriptions of service type categories
     * @param statusesByCodes              the map provides access to language dependent names and descriptions of statuses
     */
    /* Suppress Sonar warning: the cyclomatic complexity of methods should not exceed a defined threshold.
     * Reason: the cyclomatic complexity of this method seems still manageable in the given context.
     */
    @SuppressWarnings("squid:MethodCyclomaticComplexity")
    private void populateLanguageDependentData(ServiceRequestData source, ServiceRequestData target, String language,
                                               Map<String, ServiceTypeModel> serviceTypesByCodes,
                                               Map<String, ServiceTypeCategoryModel> serviceTypeCategoriesByCodes,
                                               Map<String, StatusModel> statusesByCodes)
    {
        Locale locale = new Locale(language);

        // Status
        StatusModel status = statusesByCodes.get(source.getServiceRequestStatusCode());
        String statusName = null;
        if (status != null)
        {
            statusName = status.getName(locale);
        }
        // Leave status name unchanged if name does not exist in target language
        target.setServiceRequestStatusDescription(statusName != null ? statusName : source.getServiceRequestStatusDescription());

        // Service type
        ServiceTypeModel serviceType = serviceTypesByCodes.get(source.getServiceTypeCode());
        String serviceTypeName = null;
        if (serviceType != null)
        {
            serviceTypeName = serviceType.getName(locale);
        }
        // Leave service type name unchanged if name does not exist in target language
        target.setServiceTypeName(serviceTypeName != null ? serviceTypeName : source.getServiceTypeName());

        // Category level 1
        ServiceTypeCategoryModel category = serviceTypeCategoriesByCodes.get(source.getCategoryLevelOneCode());
        String categoryName = null;
        if (category != null)
        {
            categoryName = category.getName(locale);
        }
        // Leave service type category name unchanged if name does not exist in target language
        target.setCategoryLevelOneName(categoryName != null ? categoryName : source.getCategoryLevelOneName());

        // Category level 2
        category = serviceTypeCategoriesByCodes.get(source.getCategoryLevelTwoCode());
        categoryName = null;
        if (category != null)
        {
            categoryName = category.getName(locale);
        }
        // Leave service type category name unchanged if name does not exist in target language
        target.setCategoryLevelTwoName(categoryName != null ? categoryName : source.getCategoryLevelTwoName());

        // Category level 3
        category = serviceTypeCategoriesByCodes.get(source.getCategoryLevelThreeCode());
        categoryName = null;
        if (category != null)
        {
            categoryName = category.getName(locale);
        }
        // Leave service type category name unchanged if name does not exist in target language
        target.setCategoryLevelThreeName(categoryName != null ? categoryName : source.getCategoryLevelThreeName());

        // Category level 4
        category = serviceTypeCategoriesByCodes.get(source.getCategoryLevelFourCode());
        categoryName = null;
        if (category != null)
        {
            categoryName = category.getName(locale);
        }
        // Leave service type category name unchanged if name does not exist in target language
        target.setCategoryLevelFourName(categoryName != null ? categoryName : source.getCategoryLevelFourName());
    }

    /**
     * Gets the ISO code of the current session language.
     *
     * @return the session language
     */
    private String getSessionLanguage()
    {
        LanguageModel language = commonI18NService.getCurrentLanguage();
        Assert.notNull(language, "Current session language must not be null");

        return language.getIsocode();
    }
}
