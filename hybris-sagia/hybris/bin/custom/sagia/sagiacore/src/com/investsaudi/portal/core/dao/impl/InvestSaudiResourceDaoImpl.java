package com.investsaudi.portal.core.dao.impl;

import com.investsaudi.portal.core.dao.InvestSaudiResourceDao;
import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

public class InvestSaudiResourceDaoImpl implements InvestSaudiResourceDao {



    private static final Logger LOG = Logger.getLogger(InvestSaudiMediaCenterDaoImpl.class);

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private PaginatedFlexibleSearchService paginatedFlexibleSearchService;

    @Resource
    private CatalogVersionService catalogVersionService;

    private static final String CATALOG_ID = "sagiaContentCatalog";
    private static final String ONLINE = "Online";

    private static final String QUERY_LAST_ECONOMIC_AND_INVESTMENT_MONITOR = "SELECT {p:pk} FROM {EconomicAndInvestmentMonitor AS p} "
            + "WHERE  {p.catalogVersion} = ?catalogVersion ORDER BY {p.resourceDate} DESC LIMIT 1";

    private static final String QUERY_LAST_INVESTMENT_HIGHLIGHTS_REPORT = "SELECT {p:pk} FROM {InvestmentHighlightsReport AS p} "
            + "WHERE  {p.catalogVersion} = ?catalogVersion ORDER BY {p.resourceDate} DESC LIMIT 1";

    private static final String QUERY_LAST_MONTHLY_BULLETIN_REPORT = "SELECT {p:pk} FROM {MonthlyBulletinReport AS p} "
            + "WHERE  {p.catalogVersion} = ?catalogVersion ORDER BY {p.resourceDate} DESC LIMIT 1";


    private static final String QUERY_LAST_ECONOMIC_AND_INVESTMENT_REPORTS_AND_STUDIES = "SELECT {p:pk} FROM {EconomicAndInvestmentReportsAndStudies AS p} "
            + "WHERE  {p.catalogVersion} = ?catalogVersion ORDER BY {p.resourceDate} DESC LIMIT 1";



    @Override
    public EconomicAndInvestmentMonitorModel getLastEconomicAndInvestmentMonitor() {


        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_LAST_ECONOMIC_AND_INVESTMENT_MONITOR);

        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        final SearchResult<EconomicAndInvestmentMonitorModel> resultList = flexibleSearchService.search(searchQuery);

        return resultList.getResult().get(0);
    }

    @Override
    public InvestmentHighlightsReportModel getLastInvestmentHighlightsReport() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_LAST_INVESTMENT_HIGHLIGHTS_REPORT);

        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        final SearchResult<InvestmentHighlightsReportModel> resultList = flexibleSearchService.search(searchQuery);

        return resultList.getResult().get(0);
    }

    @Override
    public MonthlyBulletinReportModel getLastMonthlyBulletinReport() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_LAST_ECONOMIC_AND_INVESTMENT_MONITOR);

        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        final SearchResult<MonthlyBulletinReportModel> resultList = flexibleSearchService.search(searchQuery);

        return resultList.getResult().get(0);
    }

    @Override
    public EconomicAndInvestmentReportsAndStudiesModel getLastEconomicAndInvestmentReportsAndStudies() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_LAST_ECONOMIC_AND_INVESTMENT_REPORTS_AND_STUDIES);

        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        final SearchResult<EconomicAndInvestmentReportsAndStudiesModel> resultList = flexibleSearchService.search(searchQuery);

        return resultList.getResult().get(0);
    }
}
