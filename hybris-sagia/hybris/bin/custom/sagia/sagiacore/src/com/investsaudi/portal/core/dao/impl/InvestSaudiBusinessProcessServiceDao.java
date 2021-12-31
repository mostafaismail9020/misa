package com.investsaudi.portal.core.dao.impl;

import de.hybris.platform.jalo.order.delivery.DeliveryMode;
import de.hybris.platform.processengine.impl.DefaultBusinessProcessServiceDao;
import de.hybris.platform.processengine.jalo.BusinessProcess;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Invest saudi business process service dao.
 */
public class InvestSaudiBusinessProcessServiceDao extends DefaultBusinessProcessServiceDao {

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public BusinessProcessModel findProcessByName(@Nonnull String processName) {
        Objects.requireNonNull(processName, "processName is required");
        FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {"+ BusinessProcessModel.PK+"} FROM {"+
                BusinessProcessModel._TYPECODE+"} WHERE {"+BusinessProcessModel.CODE+"} like ?code");
        Map<String, String> params = new HashMap<>();
        params.put("code", processName);
        query.addQueryParameter("code", processName);
        SearchResult<BusinessProcessModel> result = this.flexibleSearchService.search(query);
        return result.getCount() == 1 ? (BusinessProcessModel)result.getResult().get(0) : null;
    }
}
