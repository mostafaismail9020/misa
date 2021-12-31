package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.moj.MOJInheritSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

public class MojInheritService extends AbstractSagiaService<MOJInheritSetData> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MojInheritService.class);
	private static final String DECEASEDID = "DeceasedId eq '";
    private static final String DEEDNUMBER = "DeedNumber eq '";
    
    /**
     * retrieves the Collection of MOJInheritSetData by deceasedId and deedNumber
     * @param deceasedId deceasedId
     * @param deedNumber deedNumber
     * @return Collection of MOJInheritSetData
     */
    public final Collection<MOJInheritSetData> get(String deceasedId, String deedNumber){
        StringBuilder filter = new StringBuilder();
        filter.append(DECEASEDID);
        filter.append(deceasedId);
        filter.append("' and ");
        filter.append(DEEDNUMBER);
        filter.append(deedNumber);
        filter.append("'");
        return super.getCollection(MOJInheritSetData.class, REQUEST_PARAMETER_FILTER, filter.toString());
    }
}
