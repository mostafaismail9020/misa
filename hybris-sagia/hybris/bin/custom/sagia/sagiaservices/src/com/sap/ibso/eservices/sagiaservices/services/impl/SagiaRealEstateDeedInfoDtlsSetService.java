package com.sap.ibso.eservices.sagiaservices.services.impl;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;
import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentListData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import de.hybris.platform.servicelayer.i18n.I18NService;

public class SagiaRealEstateDeedInfoDtlsSetService extends AbstractSagiaService<RealEstateData> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SagiaRealEstateDeedInfoDtlsSetService.class);
	private static final String IDNUMBER = "IdNo eq '";
    private static final String IDTYPE = "IdType eq '";
    private static final String DEEDNUMBER = "DeedNo eq '";

	 /**
     * Get all Real Estate Objects.
     * @return collection of Real Estate objects.
     */
    public Collection<RealEstateData> getDeedInfoList(String identityNumber, String identityType, String deedNumber) {
    	
    	try {
    	if(deedNumber.equals("1")) {
    		return super.getCollection(RealEstateData.class, REQUEST_PARAMETER_FILTER, IDNUMBER + identityNumber+"'" + " and " + IDTYPE + identityType+"'");
    		//return super.getCollection(RealEstateData.class, REQUEST_PARAMETER_FILTER, IDNUMBER + identityNumber+"'");
    	}else {
    		return super.getCollection(RealEstateData.class, REQUEST_PARAMETER_FILTER, IDNUMBER + identityNumber+"'" + " and " + IDTYPE + identityType+"'" + " and " + DEEDNUMBER + deedNumber+"'");
    		//return super.getCollection(RealEstateData.class, REQUEST_PARAMETER_FILTER, IDNUMBER + identityNumber+"'");
    	}
    	} catch(Exception e) {
            LOGGER.error(e.getMessage(),e);
            return Collections.emptyList();
        }
    }
    
    /**
     * Gets a Real Estate object based on a given ids.
     * @param params params
     * @return Real Estate object.
     */
//    public RealEstateData getDeedInfoDetail(String params) {
//        return super.get(RealEstateData.class, (Object)params, REQUEST_PARAMETER_EXPAND, "AttachmentsSet");
//    }
}
