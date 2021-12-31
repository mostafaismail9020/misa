package com.investsaudi.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

/**
 * DAO with InvestSaudiContact specific methods
 */
public interface InvestSaudiContactDao {
	
	/**
	 * 
	 * @return the list of InvestSaudiContact that should  be displayed to the customers. 
	 */
	List<InvestSaudiContactModel> getActiveContact();

	List<InvestSaudiContactModel> getActiveContactByDepartment(InvestSaudiSectorModel department);

}
