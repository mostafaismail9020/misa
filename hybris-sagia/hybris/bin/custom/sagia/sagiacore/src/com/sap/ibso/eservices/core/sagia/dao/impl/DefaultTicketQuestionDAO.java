/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketQuestionModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketQuestionDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;


/**
 * @author anouarbadri
 *
 */
public class DefaultTicketQuestionDAO extends DefaultGenericDao<TicketQuestionModel>  implements TicketQuestionDAO
{

	public DefaultTicketQuestionDAO(String typecode) {
		super(typecode);
	}

	@Override
	public List<TicketQuestionModel> getQuestionForConfigurationID(final String configurationID)
	{

		//SELECT {tq:pk}	FROM	{	TicketConfiguration AS tc join TicketConfigurationRelation as tcr ON {tcr.source} = {tc.pk} join TicketQuestion as tq  on {tq:pk} = {tcr:target}  AND {tq:isActive} = 1}	WHERE  {tc:code} = 'Chemicals - Polymers'  Order By {tq:ranking}
		
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {tq:" + TicketQuestionModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as tc join TicketConfigurationRelation AS tcr ON {tcr.source} = {tc.pk} join "+TicketQuestionModel._TYPECODE+" as tq ON {tq.pk} = {tcr.target} AND {tq.isActive} = 1");
		sql.append("}	");
		sql.append("WHERE {tc." + TicketQuestionModel.CODE + "} = ?configuration ");
		sql.append("Order By {tq." + TicketQuestionModel.RANKING + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		searchQuery.addQueryParameter("configuration", configurationID);
		final SearchResult<TicketQuestionModel> result = getFlexibleSearchService().search(searchQuery);

		return result.getResult();
	}
	
    @Override
    public TicketQuestionModel getQuestion(String code) {
        Map<String,Object> map = new HashMap<>();
        map.put(TicketQuestionModel.CODE,code);

        List<TicketQuestionModel> results = super.find(map);

        if(results == null || results.isEmpty()){
            return null;
        }
        return results.get(0);
    }

}
