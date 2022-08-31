/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaIndicatorTermModel;

import java.util.List;


/**
 * @author anouarbadri
 *
 */
public interface SagiaIndicatorTermDAO
{
	public List<SagiaIndicatorTermModel> getActiveIndicatorTerms();

}
