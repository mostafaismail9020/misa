package com.investsaudi.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.investsaudi.dao.SagiaB2BUnitDao;
import com.investsaudi.services.SagiaB2BUnitService;

import de.hybris.platform.b2b.model.B2BUnitModel;

public class DefaultSagiaB2BUnitService implements SagiaB2BUnitService {

	@Resource
	private SagiaB2BUnitDao sagiaB2BUnitDao;
	
	@Override
	public List<B2BUnitModel> getDisplayedB2BUnit() {
	
		return sagiaB2BUnitDao.getDisplayedB2BUnit();
	}

}
