package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiOpportunityDao;
import com.investsaudi.portal.core.dao.InvestSaudiProductDao;
import com.investsaudi.portal.core.dao.InvestSaudiSuccessStoryDao;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import com.investsaudi.portal.core.service.InvestSaudiProductService;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.investsaudi.portal.core.service.utils.PaginationUtils.createPaginationData;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiProductServiceImpl implements InvestSaudiProductService
{

    @Resource
    private InvestSaudiOpportunityDao investSaudiOpportunityDao;

    @Resource
    private InvestSaudiSuccessStoryDao investSaudiSuccessStoryDao;

    @Resource
    private InvestSaudiProductDao investSaudiProductDao;

    @Override
    public OpportunityProductModel getOpportunityForCode(String code) {

        OpportunityProductModel opportunityProductModel = null;
        Optional<OpportunityProductModel> opportunityProductModelOptional = emptyIfNull(investSaudiOpportunityDao.findProductsByCode(code)).stream().findFirst();

        if (opportunityProductModelOptional.isPresent()) {
            opportunityProductModel = opportunityProductModelOptional.get();
        }
        return opportunityProductModel;
    }

    @Override
    public SuccessStoryProductModel getSuccessStoryForCode(String code){
        SuccessStoryProductModel successStoryProductModel = null;
        Optional<SuccessStoryProductModel> successStoryProductModelOptional = emptyIfNull(investSaudiSuccessStoryDao.findProductsByCode(code)).stream().findFirst();

        if (successStoryProductModelOptional.isPresent()) {
            successStoryProductModel = successStoryProductModelOptional.get();
        }
        return successStoryProductModel;
    }

    @Override
    public String getProductTypeForCode(String code)
    {
        return investSaudiProductDao.getProductType(code);
    }

    @Override
    public List<OpportunityProductModel> getFeaturedOpportunities(int limit) {
        return emptyIfNull(investSaudiOpportunityDao.getFeaturedOpportunities())
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpportunityProductModel> getOpportunitiesByCategory(int limit, String categoryCode){
        return emptyIfNull(investSaudiOpportunityDao.getFeaturedOpportunitiesByCategoryCode(categoryCode))
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<SuccessStoryProductModel> getSuccessStories(int limit){
        return emptyIfNull(investSaudiSuccessStoryDao.getSuccessStories())
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<SuccessStoryProductModel> getSuccessStoriesByCategory(int limit, String categoryCode){
        return emptyIfNull(investSaudiSuccessStoryDao.getSuccessStoriesByCategoryCode(categoryCode))
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public SearchPageData<OpportunityProductModel> searchOpportunityByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize) {
        PaginationData paginationData = createPaginationData(currentPage, pageSize);

        return investSaudiOpportunityDao.searchOpportunityByNameAndSectors(text, categories, paginationData);
    }

    @Override
    public SearchPageData<SuccessStoryProductModel> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize) {
        PaginationData paginationData = createPaginationData(currentPage, pageSize);

        return investSaudiSuccessStoryDao.searchSuccessStoriesByNameAndSectors(text, categories, paginationData);
    }
    
    @Override
    public SearchPageData<OpportunityProductModel> searchOpportunityByRegion(PaginationData paginationData, String regionId) {

        return investSaudiOpportunityDao.searchOpportunityByRegion(paginationData, regionId);
    }
}
