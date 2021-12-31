/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.TicketQuestionModel;


/**
 * @author anouarbadri
 *
 */
public interface TicketQuestionDAO
{
	public List<TicketQuestionModel> getQuestionForConfigurationID(String ConfigurationID);

	TicketQuestionModel getQuestion(String code);
}
