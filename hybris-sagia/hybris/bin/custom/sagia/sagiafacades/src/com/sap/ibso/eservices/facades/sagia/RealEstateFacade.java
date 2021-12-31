package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RegionCity;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentList;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSet;

import java.util.Collection;
import java.util.List;

/**
 * Provides access to RealEstateFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface RealEstateFacade {
    /**
     * Retrieves RegionCitySet
     * @return List of RegionCity
     */
    List<RegionCity> getRegionCitySet();

    /**
     * Retrieves Cities
     * @param regionId regionId
     * @return Collection of RegionCityData
     */
    Collection<RegionCityData> getCities(String regionId);

    /**
     * Retrieves RealEstateHistory
     * @return Collection of RealEstate
     */
    Collection<RealEstate> getRealEstateHistory();

    /**
     * Retrieves RealEstate By Id
     * @param realEstateId realEstateId
     * @return RealEstate
     */
    RealEstate getRealEstateById(String realEstateId);

    /**
     * Retrieves Latest RealEstate
     * @return RealEstate
     */
    RealEstate getLatestRealEstate();

    /**
     * saves RealEstate
     * @param realEstate realEstate
     * @return boolean
     */
    boolean saveRealEstate(RealEstate realEstate);

    /**
     * Retrieves AttachmentSet
     * @param requestType requestType
     * @param realEstateType realEstateType
     * @return Collection of RealEstateAttachmentList
     */
    Collection<RealEstateAttachmentList> getAttachmentSet(String requestType, String realEstateType);

    /**
     * retrieves the RealEstateEntityDetailsSet in order to check the allowed request type
     * @return RealEstateEntityDetailsSet
     */
    RealEstateEntityDetailsSet getRealEstateEntityDetailsSet();
    
    Collection<RealEstate> getDeedInfoList(String identityNumber, String identityType, String deedNumber);
    
    RealEstate getDeedDetails(String identityNumber, String identityType, String deedNumber);
}
