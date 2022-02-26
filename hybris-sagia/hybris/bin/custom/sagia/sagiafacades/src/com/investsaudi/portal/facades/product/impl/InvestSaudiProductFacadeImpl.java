package com.investsaudi.portal.facades.product.impl;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import com.investsaudi.portal.core.service.InvestSaudiProductService;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.investsaudi.portal.facades.product.populator.InvestSaudiOpportunityPopulator;
import com.investsaudi.portal.facades.product.populator.InvestSaudiSuccessStoryPopulator;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.SuccessStoryData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import de.hybris.platform.core.servicelayer.data.PaginationData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiProductFacadeImpl implements InvestSaudiProductFacade
{

    @Resource
    private InvestSaudiProductService investSaudiProductService;

    @Resource
    private InvestSaudiOpportunityPopulator investSaudiOpportunityPopulator;

    @Resource
    private InvestSaudiSuccessStoryPopulator investSaudiSuccessStoryPopulator;


    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    @Override
    public ProductData getProductForCode(String code) {

        ProductData productData = new ProductData();

        final String productType = investSaudiProductService.getProductTypeForCode(code);
        
        if(productType != null) {
            if (productType.equals(OpportunityProductModel._TYPECODE)) {
                OpportunityProductModel opportunityProductModel = investSaudiProductService.getOpportunityForCode(code);
                if (opportunityProductModel != null) {
                    investSaudiOpportunityPopulator.populate(productData, opportunityProductModel);
                    return productData;
                }
            } else {
                SuccessStoryProductModel successStoryProductModel =
                    investSaudiProductService.getSuccessStoryForCode(code);
                if (successStoryProductModel != null) {
                    investSaudiSuccessStoryPopulator.populate(productData, successStoryProductModel);
                    return productData;
                }
            }
        }
        
        return null;
    }

    @Override
    public List<OpportunityData> getFeaturedOpportunities(int limit) {

        List<OpportunityProductModel> opportunityProductModelList = investSaudiProductService.getFeaturedOpportunities(limit);
        return populateOpportunities(opportunityProductModelList);
    }

    @Override
    public List<OpportunityData> getFeaturedOpportunitiesByCategory(int limit, String categoryCode) {

        List<OpportunityProductModel> opportunityProductModelList = investSaudiProductService.getOpportunitiesByCategory(limit, categoryCode);
        return populateOpportunities(opportunityProductModelList);
    }


    private List<OpportunityData> populateOpportunities(List<OpportunityProductModel> opportunityProductModelList){
        List<ProductData> productDataList = new ArrayList<>();
        for (OpportunityProductModel opportunityProductModel : emptyIfNull(opportunityProductModelList)) {
            ProductData productData = new ProductData();
            investSaudiOpportunityPopulator.populate(productData, opportunityProductModel);
            productDataList.add(productData);
        }

        List<OpportunityData> opportunitiesDataList = new ArrayList<>();
        productDataList.forEach(featuredOpportunity -> opportunitiesDataList.add(createOpportunityData(featuredOpportunity)));
        return opportunitiesDataList;
    }


    @Override
    public List<SuccessStoryData> getSuccessStories(int limit) {

        List<SuccessStoryProductModel> successStorProductModelList = investSaudiProductService.getSuccessStories(limit);
        return populateSuccesStories(successStorProductModelList);
    }

    @Override
    public List<SuccessStoryData> getSuccessStoriesByCategory(int limit, String categoryCode) {

        List<SuccessStoryProductModel> successStorProductModelList = investSaudiProductService.getSuccessStoriesByCategory(limit, categoryCode);
        return populateSuccesStories(successStorProductModelList);
    }

    private List<SuccessStoryData> populateSuccesStories(List<SuccessStoryProductModel> successStorProductModelList){
        List<ProductData> productDataList = new ArrayList<>();
        for (SuccessStoryProductModel successStoryProductModel : emptyIfNull(successStorProductModelList)) {
            ProductData productData = new ProductData();
            investSaudiSuccessStoryPopulator.populate(productData, successStoryProductModel);
            productDataList.add(productData);
        }

        List<SuccessStoryData> successStoryDataList = new ArrayList<>();
        productDataList.forEach(successStory -> successStoryDataList.add(createSuccessStoryData(successStory)));
        return successStoryDataList;
    }

    @Override
    public SearchPageData<OpportunityData> searchOpportunityByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize) {

        SearchPageData<OpportunityProductModel> opportunityProductModelSearchPageData = investSaudiProductService.searchOpportunityByNameAndSectors(text, categories, currentPage, pageSize);
        List<ProductData> productDataList = new ArrayList<>();
        List<OpportunityData> opportunityDataList = new ArrayList<>();

        for (OpportunityProductModel opportunityProductModel : emptyIfNull(opportunityProductModelSearchPageData.getResults())) {
            ProductData productData = new ProductData();
            investSaudiOpportunityPopulator.populate(productData, opportunityProductModel);
            productDataList.add(productData);
        }

        for (ProductData productData : productDataList) {
            opportunityDataList.add(createOpportunityData(productData));
        }

        return populateOpportunitySearchPageData(opportunityProductModelSearchPageData, opportunityDataList);
    }

    @Override
    public SearchPageData<SuccessStoryData> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize) {

        SearchPageData<SuccessStoryProductModel> successStoryProductModelSearchPageData = investSaudiProductService.searchSuccessStoriesByNameAndSectors(text, categories, currentPage, pageSize);
        List<ProductData> productDataList = new ArrayList<>();
        List<SuccessStoryData> successStoryDataList = new ArrayList<>();

        for (SuccessStoryProductModel successStoryProductModel : emptyIfNull(successStoryProductModelSearchPageData.getResults())) {
            ProductData productData = new ProductData();
            investSaudiSuccessStoryPopulator.populate(productData, successStoryProductModel);
            productDataList.add(productData);
        }

        for (ProductData productData : productDataList) {
            successStoryDataList.add(createSuccessStoryData(productData));
        }

        return populateSuccessStorySearchPageData(successStoryProductModelSearchPageData, successStoryDataList);
    }
    
    @Override
    public SearchPageData<OpportunityData> searchOpportunityByRegion(PaginationData paginationData, String regionId) {

        SearchPageData<OpportunityProductModel> opportunityProductModelSearchPageData = investSaudiProductService.searchOpportunityByRegion(paginationData, regionId);
        List<ProductData> productDataList = new ArrayList<>();
        List<OpportunityData> opportunityDataList = new ArrayList<>();

        for (OpportunityProductModel opportunityProductModel : emptyIfNull(opportunityProductModelSearchPageData.getResults())) {
            ProductData productData = new ProductData();
            investSaudiOpportunityPopulator.populate(productData, opportunityProductModel);
            productDataList.add(productData);
        }

        for (ProductData productData : productDataList) {
            opportunityDataList.add(createOpportunityData(productData));
        }

        return populateOpportunitySearchPageData(opportunityProductModelSearchPageData, opportunityDataList);
    }


    private OpportunityData createOpportunityData(ProductData productData) {
        OpportunityData opportunityData = new OpportunityData();
        opportunityData.setOpportunity(productData);
        if(productData.getParentCategory() != null)
        {
            opportunityData.setParentCategory(investSaudiCategoryFacade.getCategoryForCode(productData.getParentCategory()));
        }
        return opportunityData;
    }

    private SuccessStoryData createSuccessStoryData(ProductData productData) {
        SuccessStoryData successStoryData = new SuccessStoryData();
        successStoryData.setSuccessStory(productData);
        if(productData.getParentCategory() != null)
        {
            successStoryData.setParentCategory(investSaudiCategoryFacade.getCategoryForCode(productData.getParentCategory()));
        }
        return successStoryData;
    }


    private SearchPageData<OpportunityData> populateOpportunitySearchPageData(SearchPageData<OpportunityProductModel> opportunityProductModelSearchPageData, List<OpportunityData> list) {
        SearchPageData<OpportunityData> productDataSearchPageData = new SearchPageData<>();

        if (opportunityProductModelSearchPageData != null && opportunityProductModelSearchPageData.getSorts() != null) {
            productDataSearchPageData.setSorts(opportunityProductModelSearchPageData.getSorts());
        }
        if (opportunityProductModelSearchPageData != null && opportunityProductModelSearchPageData.getPagination() != null) {
            productDataSearchPageData.setPagination(opportunityProductModelSearchPageData.getPagination());
        }
        productDataSearchPageData.setResults(list);

        return productDataSearchPageData;
    }

    private SearchPageData<SuccessStoryData> populateSuccessStorySearchPageData(SearchPageData<SuccessStoryProductModel> successStoryProductModelSearchPageData, List<SuccessStoryData> list) {
        SearchPageData<SuccessStoryData> productDataSearchPageData = new SearchPageData<>();

        if (successStoryProductModelSearchPageData != null && successStoryProductModelSearchPageData.getSorts() != null) {
            productDataSearchPageData.setSorts(successStoryProductModelSearchPageData.getSorts());
        }
        if (successStoryProductModelSearchPageData != null && successStoryProductModelSearchPageData.getPagination() != null) {
            productDataSearchPageData.setPagination(successStoryProductModelSearchPageData.getPagination());
        }
        productDataSearchPageData.setResults(list);

        return productDataSearchPageData;
    }


}
