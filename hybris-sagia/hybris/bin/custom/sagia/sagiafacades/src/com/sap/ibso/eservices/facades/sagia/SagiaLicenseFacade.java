package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;

import java.util.List;

/**
 * SagiaLicenseFacade
 */
public interface SagiaLicenseFacade {
    /**
     * Retrieves a license DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Licence DTO split from Home HDR
     */
    LicenseData getLicense(HomeHDRData homeHDRData);

    /**
     * Retrieves a shareholders DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Shareholders DTO split from Home HDR
     */
    List<ShareholderData> getShareholders(HomeHDRData homeHDRData);


    /**
     * Get all the branches
     * @param entityId entityId
     * @return The list of all branches
     */
    List<BranchData> getBranches(String entityId);

    /**
     * Get the main branch(Government Header) from the CRM
     *
     * @param entityId - The entity id for which the branches are retrieved
     * @return - Branch data for the main branch
     */
    BranchData getGovtHeader(String entityId);

    /**
     * Retrieves a general manager DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - General Manager DTO split from Home HDR
     */
    GeneralManagerData getGeneralManager(HomeHDRData homeHDRData);

    /**
     * Retrieves contact person DTO list from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - List<Contact person DTO> split from Home HDR
     */
    List<ContactPersonData>  getContactPerson(HomeHDRData homeHDRData);

    /**
     * Retrieves a profile company DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Profile company DTO split from Home HDR
     */
    ProfileCompanyData getProfileCompany(HomeHDRData homeHDRData);

    /***
     * Post the main branch among with its sub-branches to the CRM
     * @param branchData - Main branch DTO containing the subset of its branches
     * @return GovtHeaderData
     */
    GovtHeaderData saveGovernmentHeader(BranchData branchData);

    /**
     * retireves EntityStatus
     * @param homeHDRData homeHDRData
     * @return String
     */
	String getEntityStatus(HomeHDRData homeHDRData);

	/**
	 * check if current user had valid license
	 * @param homeHDRData homeHDRData
	 * @return boolean
	 */
	boolean hasInvalidLicense(HomeHDRData homeHDRData);
}
