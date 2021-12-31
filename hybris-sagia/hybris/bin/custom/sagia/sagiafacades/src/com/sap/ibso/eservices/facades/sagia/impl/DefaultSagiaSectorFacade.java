package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaSectorService;
import com.sap.ibso.eservices.facades.data.SagiaSectorData;
import com.sap.ibso.eservices.facades.sagia.SagiaSectorFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultSagiaSectorFacade
 */
public class DefaultSagiaSectorFacade  implements SagiaSectorFacade {

    private SagiaSectorService sagiaSectorService;
    private Converter<SagiaSectorModel,SagiaSectorData> sagiaSectorConverter;

    /**
     * @return
     */
    public SagiaSectorService getSagiaSectorService() {
        return sagiaSectorService;
    }

    /**
     * @return
     */
    public Converter<SagiaSectorModel, SagiaSectorData> getSagiaSectorConverter() {
        return sagiaSectorConverter;
    }

    /**
     * @param sagiaSectorService
     */
    public void setSagiaSectorService(SagiaSectorService sagiaSectorService) {
        this.sagiaSectorService = sagiaSectorService;
    }

    /**
     * @param sagiaSectorConverter
     */
    public void setSagiaSectorConverter(Converter<SagiaSectorModel, SagiaSectorData> sagiaSectorConverter) {
        this.sagiaSectorConverter = sagiaSectorConverter;
    }

    /**
     * retrieves sectors List from DB
     * @return
     */
    @Override
    public List<SagiaSectorData> getSectorsList() {
        final List<SagiaSectorModel> sectors = getSagiaSectorService().getSectors();
        final List<SagiaSectorData> sectorsData = new ArrayList<>();
        for (SagiaSectorModel sector:
                sectors) {
        	if(sector.getCode().startsWith("1"))
        	{
        		SagiaSectorData sectorData = getSagiaSectorConverter().convert(sector);
        		sectorsData.add(sectorData);
        	}
        }
        return sectorsData;
    }

}
