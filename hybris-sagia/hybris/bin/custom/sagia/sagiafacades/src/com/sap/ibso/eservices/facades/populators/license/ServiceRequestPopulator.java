package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Populates service request data (indented to be displayed in overview components).
 */
public class ServiceRequestPopulator
{
    @Autowired
    private SagiaFormatProvider sagiaFormatProvider;

    /**
     * Populates service request data received from service layer.
     *
     * @param serviceRequest the service layer service request data transfer object
     * @param serviceRequestData the facade layer service request data transfer object
     * @param servicesByCodes the map of service codes to Sagia services (to get URL path information)
     */
    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity"})
    public void populate(com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestData serviceRequest,
                         ServiceRequestData serviceRequestData,
                         Map<String, SagiaServiceModel> servicesByCodes)
    {
        // Populate data except URL path
        serviceRequestData.setCategoryLevelOne(serviceRequest.getCategoryLevelOneName());
        serviceRequestData.setCategoryLevelTwo(serviceRequest.getCategoryLevelTwoName());
        serviceRequestData.setCategoryLevelThree(serviceRequest.getCategoryLevelThreeName());
        serviceRequestData.setCategoryLevelFour(serviceRequest.getCategoryLevelFourName());
        serviceRequestData.setRequestDate(sagiaFormatProvider.getLocalizedDateData(serviceRequest.getServiceRequestCreationDate()));
        serviceRequestData.setRequestNumber(serviceRequest.getServiceRequestNumber());
        serviceRequestData.setStatus(serviceRequest.getServiceRequestStatusCode());
        serviceRequestData.setServiceType(serviceRequest.getServiceTypeName());
        serviceRequestData.setStatusDescription(serviceRequest.getServiceRequestStatusDescription());

        //Populate a field after which the sort by name is made
        if(Strings.isNotEmpty(serviceRequestData.getCategoryLevelFour())){
            serviceRequestData.setSortByNameValue(serviceRequestData.getCategoryLevelFour());
        }
        else if(Strings.isNotEmpty(serviceRequestData.getCategoryLevelThree())){
            serviceRequestData.setSortByNameValue(serviceRequestData.getCategoryLevelThree());
        }
        else if(Strings.isNotEmpty(serviceRequestData.getCategoryLevelTwo())){
            serviceRequestData.setSortByNameValue(serviceRequestData.getCategoryLevelTwo());
        }
        else if(Strings.isNotEmpty(serviceRequestData.getCategoryLevelOne())){
            serviceRequestData.setSortByNameValue(serviceRequestData.getCategoryLevelOne());
        }
        else if(Strings.isNotEmpty(serviceRequestData.getServiceType())){
            serviceRequestData.setSortByNameValue(serviceRequestData.getServiceType());
        }
        else{
            serviceRequestData.setSortByNameValue(Strings.EMPTY);
        }

        // Now handle URL path

        // Check whether the service request is associated to service type category 3 or 4
        if (StringUtils.hasLength(serviceRequest.getCategoryLevelThreeCode()) || StringUtils.hasLength(serviceRequest.getCategoryLevelFourCode()))
        {
            return; // Links are not supported for service requests associated to service type categories 3 or 4
        }

        // Check whether the service request is associated to service type category 2
        SagiaServiceModel serviceModel = servicesByCodes.get(serviceRequest.getCategoryLevelTwoCode());
        if (serviceModel == null)
        {
            // Check whether the service request is associated to service type category 1
            serviceModel = servicesByCodes.get(serviceRequest.getCategoryLevelOneCode());
            if (serviceModel == null)
            {
                // A service request shall always be associated to a service type
                serviceModel = servicesByCodes.get(serviceRequest.getServiceTypeCode());
                if (serviceModel == null)
                {
                    return; // Auto-fix by not providing a service request link
                }
            }
        }

        //set government services url
        if ("ZS10".equals(serviceRequest.getServiceTypeCode())) {
            String urlPath = "services/government/";
            String categoryCode = getCategoryCode(serviceRequest.getCategoryLevelOneCode());
            if (!StringUtils.isEmpty(categoryCode) && !StringUtils.isEmpty(serviceRequest.getCategoryLevelTwoCode())) {
                urlPath += getCategoryCode(serviceRequest.getCategoryLevelOneCode()) + "/";
                urlPath += serviceRequest.getCategoryLevelTwoCode();
                if(serviceRequest.getServiceRequestNumber() != null) {
                    urlPath += "?srID=" + serviceRequest.getServiceRequestNumber();
                }

                serviceRequestData.setUrlPath(urlPath);
            }
            return;
        }

        // An URL path shall be maintained for each service model
        String urlPath = serviceModel.getUrl();
        if (!StringUtils.hasLength(urlPath))
        {
            return; // Auto-fix by not providing a service request link
        }

        String serviceTypeCode = serviceRequest.getServiceTypeCode();
        if ("ZSR6".equals(serviceTypeCode) || "ZSR9".equals(serviceTypeCode) ||
                ("ZS21".equals(serviceTypeCode) && "Z_ONLINE".equals(serviceRequest.getCategoryLevelOneCode())))
        {
            /* For license cancellation (ZSR6) and cancellation support letter (ZSR9) there is no service request
             * history component. Therefore the URL path does not require a service request number. The URL path shall
             * just lead the user to the "right" page without selecting a specific service request.
             *
             * The same holds for re-open facility (ZS21, Z_ONLINE) service requests.
             */
            serviceRequestData.setUrlPath(urlPath);
            return;
        }

        // Add request number to URL path
        if (urlPath.endsWith("/"))
        {
            urlPath = urlPath + "display/" + serviceRequest.getServiceRequestNumber();
        }
        else
        {
            urlPath = urlPath + "/display/" + serviceRequest.getServiceRequestNumber();
        }

        serviceRequestData.setUrlPath(urlPath);
    }

    private static String getCategoryCode(String categoryLevelOneCode) {
        switch (categoryLevelOneCode){
            case "ZMOIP":
                return "interiorAndPassport";
            case "ZMOL":
                return "labour";
            case "ZMOIR":
                return "interiorAndRecruitment";
            case "ZMOCI":
                return "commerceAndIndustry";
            default:
                return null;
        }
    }
}
