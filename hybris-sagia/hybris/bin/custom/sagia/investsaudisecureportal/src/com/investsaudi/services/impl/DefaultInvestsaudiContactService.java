package com.investsaudi.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.investsaudi.dao.InvestSaudiContactDao;
import com.investsaudi.dao.InvestSaudiContactDepartmentDao;
import com.investsaudi.services.InvestsaudiContactService;
import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

public class DefaultInvestsaudiContactService implements InvestsaudiContactService {

	@Resource
	private InvestSaudiContactDao investSaudiContactDao;
	
	@Resource
	private InvestSaudiContactDepartmentDao investSaudiContactDepartmentDao;
	
	@Override
	public List<InvestSaudiContactModel> getActiveContat() {
		return investSaudiContactDao.getActiveContact();
	}
	
	@Override
	public List<InvestSaudiContactModel> getActiveContatByDepartment(InvestSaudiSectorModel department) {
		return investSaudiContactDao.getActiveContactByDepartment(department);
	}

	@Override
	public List<InvestSaudiSectorModel> getContactDepartment() {
		return investSaudiContactDepartmentDao.getContactDepartment();
	}
	
	
	
}
