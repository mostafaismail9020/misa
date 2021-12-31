package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaClassificationService;
import com.sap.ibso.eservices.facades.data.LicenseData;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import com.sap.ibso.eservices.facades.data.SagiaClassificationData;
import com.sap.ibso.eservices.facades.sagia.SagiaClassificationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCREATEData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeZCreateDataService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.fest.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DefaultSagiaClassificationFacade
 */
public class DefaultSagiaClassificationFacade implements SagiaClassificationFacade {

    private SagiaClassificationService sagiaClassificationService;
    private Converter<SagiaClassificationModel,SagiaClassificationData> sagiaClassificationConverter;
    @Autowired
    private ClassificationUpgradeZCreateDataService classificationUpgradeZCreateDataService;
    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;
    @Autowired
    private SagiaLicenseFacade licenseFacade;

    /**
     * @return
     */
    public SagiaClassificationService getSagiaClassificationService() {
        return sagiaClassificationService;
    }

    /**
     * @return
     */
    public Converter<SagiaClassificationModel, SagiaClassificationData> getSagiaClassificationConverter() {
        return sagiaClassificationConverter;
    }

    /**
     * @param sagiaClassificationService
     */
    public void setSagiaClassificationService(SagiaClassificationService sagiaClassificationService) {
        this.sagiaClassificationService = sagiaClassificationService;
    }

    /**
     * @param sagiaClassificationConverter
     */
    public void setSagiaClassificationConverter(Converter<SagiaClassificationModel, SagiaClassificationData> sagiaClassificationConverter) {
        this.sagiaClassificationConverter = sagiaClassificationConverter;
    }

    /**
     * gets the classifications list, filters the list after code and sorts the classifications list for an upgrade
     *
     * @return
     */
    @Override
    public List<SagiaClassificationData> getClassificationsList() {
        final List<SagiaClassificationModel> classifications = getSagiaClassificationService().getClassifications();
        final List<SagiaClassificationData> classificationsData = new ArrayList<>();
        final ZCREATEData zcreateData = classificationUpgradeZCreateDataService.getZCreateData();
        String currentClass = zcreateData.getClassProperty();

        if (currentClass == null || currentClass.isEmpty()){
            return Collections.emptyList();
        }

        Map<String, Integer> map =  classifications.stream()
                .collect(Collectors.toMap(
                        SagiaClassificationModel::getName,
                        SagiaClassificationModel::getCode));
        Integer currentClassCode = map.get(currentClass);

        for (SagiaClassificationModel classification: classifications) {
            SagiaClassificationData classificationData = getSagiaClassificationConverter().convert(classification);
            classificationsData.add(classificationData);
        }
        final List<SagiaClassificationData> classificationsDataFiltered = classificationsData.stream()
                .filter(classificationData -> classificationData.getCode() > currentClassCode)
                .collect(Collectors.toList());


        classificationsDataFiltered.sort(Comparator.comparing(SagiaClassificationData::getCode).reversed());

        return classificationsDataFiltered;
    }
    
    @Override
	public String getCurrentClassification() {

		HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
		LicenseData licenseData = licenseFacade.getLicense(homeHDR);
		if (licenseData != null) {
			ProfileCompanyData profileCompanyData = licenseData.getProfileCompanyData();
			return profileCompanyData.getClassLevel();
		} else {
			return "";
		}
	}
    
    /**
     * if there are no classifications at all, the CRM will respond an entity with ObjectId null 
     * remove all classifications that do have primary key empty
     */
    @Override
    public ZListData getLatestClassificationUpgradeRequest(Collection<ZListData> classificationUpgradeList) {
    		return classificationUpgradeList
    				.stream()
    				.filter(classification -> !Strings.isEmpty(classification.getOBJECT_ID()))
    				.findFirst()
    				.orElse(null);
    }
}
