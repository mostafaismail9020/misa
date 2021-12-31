package com.investsaudi.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

public interface InvestsaudiContactService  {

	
	/**
	 * Gets the list of contacts
	 * 
	 * @return list of contacts
	 */
	public List<InvestSaudiContactModel> getActiveContat();

	/**
	 * Gets the list of departments
	 * 
	 * @return list of departments
	 */
	public List<InvestSaudiSectorModel> getContactDepartment();

	public List<InvestSaudiContactModel> getActiveContatByDepartment(InvestSaudiSectorModel department);
	
}
