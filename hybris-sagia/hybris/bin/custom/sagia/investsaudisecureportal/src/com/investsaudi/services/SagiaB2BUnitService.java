package com.investsaudi.services;

import java.util.List;

import de.hybris.platform.b2b.model.B2BUnitModel;

/**
 * Service methods that are used by the Sagia B2B unit 
 * 
 * @author anouarbadri
 */

public interface SagiaB2BUnitService {
	
	/**
	 * Gets the B2BUnit that should be displayed to the customers. 
	 * 
	 * @return list of SagiaB2BUnit to be displayed.
	 */
	public List<B2BUnitModel> getDisplayedB2BUnit();

}
