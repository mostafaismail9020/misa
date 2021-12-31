package com.sap.ibso.eservices.sagiaservices.services.isicdetails;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ISICDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto.ISICDetailsRequestParameters;
import com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto.ISICDetailsParametersBuilder;

import java.util.Collection;

/**
 * ISICDetailsService
 */
public class ISICDetailsService extends AbstractSagiaService<ISICDetailsData> {

    /**
     * retrieves ISICDetailsList
     * @param functionParameters functionParameters
     * @return Collection of ISICDetailsData
     */
    public final Collection<ISICDetailsData> getISICDetailsList(ISICDetailsRequestParameters functionParameters) {

        String[] parameters = new ISICDetailsParametersBuilder()
                .language(functionParameters.getLanguage())
                .flag(functionParameters.getFlag())
                .lictype(functionParameters.getLictype())
                .section(functionParameters.getSection())
                .division(functionParameters.getDivision())
                .complimentary(functionParameters.getComplimentary())
                .activity(functionParameters.getActivity())
                .build();
        return getCollection(ISICDetailsData.class, parameters);
    }
}
