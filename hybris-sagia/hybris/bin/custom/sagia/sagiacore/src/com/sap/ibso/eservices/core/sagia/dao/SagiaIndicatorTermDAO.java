/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.jalo.SagiaIndicatorTerm;

import java.util.List;


/**
 * @author anouarbadri
 *
 */
public interface SagiaIndicatorTermDAO
{
	public List<SagiaIndicatorTerm> getActiveIndicatorTerms();

}
