package com.investsaudi.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

/**
 * DAO with InvestSaudiContactDepartment specific methods
 */
public interface InvestSaudiContactDepartmentDao {

	
	/**
	 * 
	 * @return the list of InvestSaudiContact Department that should  be displayed to the customers. 
	 */
	List<InvestSaudiSectorModel> getContactDepartment();

	
	
}
