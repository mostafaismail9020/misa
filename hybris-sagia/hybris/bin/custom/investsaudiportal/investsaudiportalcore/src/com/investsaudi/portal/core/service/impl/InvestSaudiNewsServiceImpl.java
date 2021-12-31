package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiNewsComponentDao;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiNewsService;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import javax.annotation.Resource;
import java.util.Collection;

public class InvestSaudiNewsServiceImpl implements InvestSaudiNewsService {

    @Resource
    private InvestSaudiNewsComponentDao investSaudiNewsComponentDao;


    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData) {
        return investSaudiNewsComponentDao.getAllNews(paginationData);
    }

    @Override
    public Collection<InvestSaudiNewsComponentModel> getNews(int number) {
        return investSaudiNewsComponentDao.getNews(number);
    }
}
