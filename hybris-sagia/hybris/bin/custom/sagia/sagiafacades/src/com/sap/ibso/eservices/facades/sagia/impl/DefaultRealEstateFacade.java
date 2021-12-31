
package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.facades.data.RegionCity;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.RealEstateFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentList;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentListData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSet;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSetData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaRealEstateAttachmentSetService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaRealEstateDeedInfoDtlsSetService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 * DefaultRealEstateFacade
 */
public class DefaultRealEstateFacade implements RealEstateFacade {
    private ZUI5SagiaFacade zui5SagiaFacade;
    private RegionCityPopulator regionCityPopulator;
    private RealEstatePopulator realEstatePopulator;
    private RealEstateAttachmentSetPopulator realEstateAttachmentSetPopulator;
    private RealEstateReversePopulator realEstateReversePopulator;
    private SagiaRealEstateAttachmentSetService sagiaRealEstateAttachmentSetService;
    private RealEstateEntityDetailsSetPopulator realEstateEntityDetailsSetPopulator;
    private SagiaRealEstateDeedInfoDtlsSetService sagiaRealEstateDeedInfoDtlsSetService;

 

	public void setSagiaRealEstateDeedInfoDtlsSetService(
			SagiaRealEstateDeedInfoDtlsSetService sagiaRealEstateDeedInfoDtlsSetService) {
		this.sagiaRealEstateDeedInfoDtlsSetService = sagiaRealEstateDeedInfoDtlsSetService;
	}

	/**
     * Gets the Real Estate objects.
     * @return list with the Real Estate objects.
     */
    @Override
    public List<RealEstate> getRealEstateHistory() {
        Collection<RealEstateData> realEstateHistoryData = zui5SagiaFacade.getRealEstateHistoryData();
        List<RealEstate> realEstateHistory = new ArrayList<>();

        if(realEstateHistoryData!=null) {
            realEstateHistoryData.forEach(realEstateData -> {
                RealEstate rE = new RealEstate();
                realEstatePopulator.populate(realEstateData, rE);
                realEstateHistory.add(rE);
            });
        }
        return realEstateHistory;
    }

    /**
     * Get a Real Estate object based on a given id.
     * @param realEstateId
     * @return Real Estate object based on a given id.
     */
    @Override
    public RealEstate getRealEstateById(String realEstateId) {
        RealEstateData realEstateData = zui5SagiaFacade.getRealEstateById(realEstateId);
        RealEstate realEstate = new RealEstate();
        realEstatePopulator.populate(realEstateData, realEstate);
        return realEstate;
    }

    /**
     * Get list of RegionCity.
     * @return list of RegionCity.
     */
    @Override
    public List<RegionCity> getRegionCitySet() {
        Collection<RegionCityData> regionCityData = zui5SagiaFacade.getRegionCityData();
        List<RegionCity> regionCities = new ArrayList<>();

        regionCityData.forEach(region -> {
            RegionCity rc = new RegionCity();
            regionCityPopulator.populate(region, rc);
            regionCities.add(rc);
        });

        return regionCities;
    }

    /**
     * Save Real Estate object to CRM.
     * @param realEstate
     * @return true / false based on the success of the saving operation.
     */
    @Override
    public boolean saveRealEstate(RealEstate realEstate) {
        RealEstateData realEstateData = new RealEstateData();
        realEstateReversePopulator.populate(realEstate, realEstateData);
        return zui5SagiaFacade.saveRealEstate(realEstateData);
    }

    /**
     * @param requestType
     * @param realEstateType
     * @return
     */
    @Override
    public Collection<RealEstateAttachmentList> getAttachmentSet(String requestType, String realEstateType) {
        Collection<RealEstateAttachmentListData> realEstateAttachmentListData = sagiaRealEstateAttachmentSetService.getAttachmentSet(requestType, realEstateType);
        Collection<RealEstateAttachmentList> realEstateAttachmentList = new ArrayList<>();

        int index = 0;
        for(RealEstateAttachmentListData item : realEstateAttachmentListData){
            RealEstateAttachmentList attachment = new RealEstateAttachmentList();
            realEstateAttachmentSetPopulator.populate(item, attachment);
            attachment.setLocalId(index++);
            realEstateAttachmentList.add(attachment);
        }
        return realEstateAttachmentList;
    }

    /**
     * Get all cities for a given region id.
     * @param regionId
     * @return all cities for a given region id.
     */
    @Override
    public Collection<RegionCityData> getCities(String regionId) {
        return zui5SagiaFacade.getCities(regionId);
    }

    /**
     * @param zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }

    /**
     * @param regionCityPopulator
     */
    public void setRegionCityPopulator(RegionCityPopulator regionCityPopulator) {
        this.regionCityPopulator = regionCityPopulator;
    }

    /**
     * @param realEstatePopulator
     */
    public void setRealEstatePopulator(RealEstatePopulator realEstatePopulator) {
        this.realEstatePopulator = realEstatePopulator;
    }

    /**
     * @param realEstateReversePopulator
     */
    public void setRealEstateReversePopulator(RealEstateReversePopulator realEstateReversePopulator) {
        this.realEstateReversePopulator = realEstateReversePopulator;
    }

    /**
     * @param sagiaRealEstateAttachmentSetService
     */
    public void setSagiaRealEstateAttachmentSetService(SagiaRealEstateAttachmentSetService sagiaRealEstateAttachmentSetService) {
        this.sagiaRealEstateAttachmentSetService = sagiaRealEstateAttachmentSetService;
    }

    /**
     * @param realEstateAttachmentSetPopulator
     */
    public void setRealEstateAttachmentSetPopulator(RealEstateAttachmentSetPopulator realEstateAttachmentSetPopulator) {
        this.realEstateAttachmentSetPopulator = realEstateAttachmentSetPopulator;
    }

	@Override
	public RealEstate getLatestRealEstate() {
        RealEstateData realEstateData = zui5SagiaFacade.getLatestRealEstate();
        RealEstate realEstate = new RealEstate();
        realEstatePopulator.populate(realEstateData, realEstate);
        return realEstate;
	}


    /**
     * @param realEstateEntityDetailsSetPopulator
     */

    public void setRealEstateEntityDetailsSetPopulator(RealEstateEntityDetailsSetPopulator realEstateEntityDetailsSetPopulator) {
        this.realEstateEntityDetailsSetPopulator = realEstateEntityDetailsSetPopulator;
    }


    /**
     * retrieves the RealEstateEntityDetailsSet
     * @return RealEstateEntityDetailsSet
     */

    @Override
    public RealEstateEntityDetailsSet getRealEstateEntityDetailsSet(){
        Collection<RealEstateEntityDetailsSetData> realEstateEntityDetailsSetDataCollection = zui5SagiaFacade.getRealEstateEntityDetailsSet();

        List<RealEstateEntityDetailsSet> realEstateEntityDetailsSetDataList = new ArrayList<>();

        realEstateEntityDetailsSetDataCollection.forEach(realEstateEntityDetailsSetData -> {
            RealEstateEntityDetailsSet realEstateEntityDetailsSet = new RealEstateEntityDetailsSet();
            realEstateEntityDetailsSetPopulator.populate(realEstateEntityDetailsSetData,realEstateEntityDetailsSet);
            realEstateEntityDetailsSetDataList.add(realEstateEntityDetailsSet);
        });

        return realEstateEntityDetailsSetDataList.get(0);

        }
    
    
    @Override
    public List<RealEstate> getDeedInfoList(String identityNumber, String identityType, String deedNumber) {
        Collection<RealEstateData> deedInfoData = sagiaRealEstateDeedInfoDtlsSetService.getDeedInfoList(identityNumber,identityType,deedNumber);
        List<RealEstate> deedInfoList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(deedInfoData)) {
        	deedInfoData.forEach(realEstateData -> {
                RealEstate deedData = new RealEstate();
                realEstatePopulator.populate(realEstateData, deedData);
                System.out.println("**** deedData deedno:  "+deedData.getDeedNo());
                deedInfoList.add(deedData);
            });
        }
        return deedInfoList;
    }
    
    @Override
	public RealEstate getDeedDetails(String identityNumber, String identityType, String deedNumber) {
    	Collection<RealEstateData> deedInfoData = sagiaRealEstateDeedInfoDtlsSetService.getDeedInfoList(identityNumber,identityType,deedNumber);
    	if (CollectionUtils.isNotEmpty(deedInfoData)) {
    		RealEstateData realEstateData = deedInfoData.stream().findFirst().get();
            RealEstate realEstate = new RealEstate();
            realEstatePopulator.populate(realEstateData, realEstate);
            return realEstate;
    	}else {
    		return null;
    	}
    	
	}

}

