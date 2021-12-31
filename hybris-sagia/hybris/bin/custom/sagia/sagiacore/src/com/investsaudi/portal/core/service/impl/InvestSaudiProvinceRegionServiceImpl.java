package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiProvinceRegionDao;
import com.investsaudi.portal.core.model.ProvinceComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiProvinceRegionService;

import javax.annotation.Resource;

public class InvestSaudiProvinceRegionServiceImpl implements InvestSaudiProvinceRegionService {
	
	@Resource
    private InvestSaudiProvinceRegionDao investSaudiProvinceRegionDao;
	
	@Override
    public ProvinceComponentModel getProvinceRegionDetails(String provinceId) {
    	return investSaudiProvinceRegionDao.getProvinceRegionDetails(provinceId);
    }

}
