package com.investsaudi.dao;

import java.util.List;

import de.hybris.platform.b2b.model.B2BUnitModel;

/**
 * DAO with B2B unit specific methods
 */
public interface SagiaB2BUnitDao {

	/**
	 * 
	 * @return the list of B2B units that should be displayed to the customers. 
	 */
	List<B2BUnitModel> getDisplayedB2BUnit();

}
