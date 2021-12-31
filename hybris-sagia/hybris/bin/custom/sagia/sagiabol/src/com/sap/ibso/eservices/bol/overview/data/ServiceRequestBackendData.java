package com.sap.ibso.eservices.bol.overview.data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Provides a data transfer object for service request data from SAP backend system.
 */
public class ServiceRequestBackendData implements Serializable
{
    private String serviceRequestId;
    private LocalDate serviceRequestCreationDate;
    private String serviceRequestStatusCode;
    private String serviceTypeCode;
    private String categoryLevelOneCode;
    private String categoryLevelTwoCode;
    private String categoryLevelThreeCode;
    private String categoryLevelFourCode;

    /**
     * Gets the service request identifier.
     *
     * @return the service request identifier
     */
    public String getServiceRequestId()
    {
        return serviceRequestId;
    }

    /**
     * Sets the service request identifier.
     *
     * @param serviceRequestId the service request identifier
     */
    public void setServiceRequestId(String serviceRequestId)
    {
        this.serviceRequestId = serviceRequestId;
    }

    /**
     * Gets the service request creation date.
     *
     * @return the service request creation date
     */
    public LocalDate getServiceRequestCreationDate()
    {
        return serviceRequestCreationDate;
    }

    /**
     * Sets the service request creation date.
     *
     * @param serviceRequestCreationDate the service request creation date
     */
    public void setServiceRequestCreationDate(LocalDate serviceRequestCreationDate)
    {
        this.serviceRequestCreationDate = serviceRequestCreationDate;
    }

    /**
     * Gets the service request status code.
     *
     * @return the service request status code
     */
    public String getServiceRequestStatusCode()
    {
        return serviceRequestStatusCode;
    }

    /**
     * Sets the service request status code.
     *
     * @param serviceRequestStatusCode the service request status code
     */
    public void setServiceRequestStatusCode(String serviceRequestStatusCode)
    {
        this.serviceRequestStatusCode = serviceRequestStatusCode;
    }

    /**
     * Gets the type code of the requested service.
     *
     * @return the service type code
     */
    public String getServiceTypeCode()
    {
        return serviceTypeCode;
    }

    /**
     * Sets the type code of the requested service.
     *
     * @param serviceTypeCode the service type code
     */
    public void setServiceTypeCode(String serviceTypeCode)
    {
        this.serviceTypeCode = serviceTypeCode;
    }

    /**
     * Gets the first level service type category code of the category hierarchy associated to the service request
     * if present, otherwise {@code null} is returned.
     *
     * @return the first level category code
     */
    public String getCategoryLevelOneCode()
    {
        return categoryLevelOneCode;
    }

    /**
     * Sets the first level service type category code of the category hierarchy associated to the service request.
     *
     * @param categoryLevelOneCode the first level category code
     */
    public void setCategoryLevelOneCode(String categoryLevelOneCode)
    {
        this.categoryLevelOneCode = categoryLevelOneCode;
    }

    /**
     * Gets the second level service type category code of the category hierarchy associated to the service request
     * if present, otherwise {@code null} is returned.
     *
     * @return the second level category code
     */
    public String getCategoryLevelTwoCode()
    {
        return categoryLevelTwoCode;
    }

    /**
     * Sets the second level service type category code of the category hierarchy associated to the service request.
     *
     * @param categoryLevelTwoCode the second level category code
     */
    public void setCategoryLevelTwoCode(String categoryLevelTwoCode)
    {
        this.categoryLevelTwoCode = categoryLevelTwoCode;
    }

    /**
     * Gets the third level service type category code of the category hierarchy associated to the service request
     * if present, otherwise {@code null} is returned.
     *
     * @return the third level category code
     */
    public String getCategoryLevelThreeCode()
    {
        return categoryLevelThreeCode;
    }

    /**
     * Sets the third level service type category code of the category hierarchy associated to the service request.
     *
     * @param categoryLevelThreeCode the third level category code
     */
    public void setCategoryLevelThreeCode(String categoryLevelThreeCode)
    {
        this.categoryLevelThreeCode = categoryLevelThreeCode;
    }

    /**
     * Gets the fourth level service type category code of the category hierarchy associated to the service request
     * if present, otherwise {@code null} is returned.
     *
     * @return the fourth level category code
     */
    public String getCategoryLevelFourCode()
    {
        return categoryLevelFourCode;
    }

    /**
     * Sets the fourth level service type category code of the category hierarchy associated to the service request.
     *
     * @param categoryLevelFourCode the fourth level category code
     */
    public void setCategoryLevelFourCode(String categoryLevelFourCode)
    {
        this.categoryLevelFourCode = categoryLevelFourCode;
    }
}
