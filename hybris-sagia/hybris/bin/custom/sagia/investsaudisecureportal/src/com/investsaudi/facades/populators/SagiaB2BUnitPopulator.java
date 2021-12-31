package com.investsaudi.facades.populators;

import com.investsaudi.data.SagiaB2BUnitData;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.catalog.model.CompanyModel;
import de.hybris.platform.converters.Populator;

import javax.annotation.Resource;

/**
 *
 */
public class SagiaB2BUnitPopulator implements Populator<B2BUnitModel, SagiaB2BUnitData> {

    @Resource
    private B2BUnitService b2bUnitService ;
	
    @Override
    public void populate(B2BUnitModel source, SagiaB2BUnitData target) {
    	target.setId(source.getUid());
        target.setName(source.getName());
        CompanyModel parent = b2bUnitService.getParent(source);
        if(null != parent) {
            target.setParentUnitId(parent.getUid());
            target.setParentUnitName(parent.getName());
        }
    }
}