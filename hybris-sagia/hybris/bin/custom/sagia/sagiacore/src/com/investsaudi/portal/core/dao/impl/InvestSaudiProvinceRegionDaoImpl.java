package com.investsaudi.portal.core.dao.impl;

import com.investsaudi.portal.core.dao.InvestSaudiProvinceRegionDao;
import com.investsaudi.portal.core.model.ProvinceComponentModel;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.catalog.CatalogVersionService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class InvestSaudiProvinceRegionDaoImpl extends DefaultGenericDao<ProvinceComponentModel> implements InvestSaudiProvinceRegionDao {
	
	private static final Logger LOG = Logger.getLogger(InvestSaudiProvinceRegionDaoImpl.class);
	
	@Resource
    private FlexibleSearchService flexibleSearchService;
	
	@Resource
    private CatalogVersionService catalogVersionService;
	
	private static final String CATALOG_ID = "sagiaContentCatalog";
    private static final String ONLINE = "Online";
	
	private static final String QUERY_PROVINCE_DETAILS_COMPONENT = "SELECT {p:pk} FROM {ProvinceComponent AS p} "
    		+ "WHERE {p.uid}=?uid AND {p.catalogVersion} = ?catalogVersion" ;
	
	public InvestSaudiProvinceRegionDaoImpl(String typecode) {
        super(typecode);
    }
	
	@Override
    public ProvinceComponentModel getProvinceRegionDetails(String provinceId) {            
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", provinceId);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_PROVINCE_DETAILS_COMPONENT, params);        
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        
        final SearchResult<ProvinceComponentModel> resultList = flexibleSearchService.search(searchQuery);

        return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
    }

}
