package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiResourceDao;
import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;
import com.investsaudi.portal.core.service.InvestSaudiResourceService;

import javax.annotation.Resource;

public class InvestSaudiResourceServiceImpl implements InvestSaudiResourceService {

    @Resource
    InvestSaudiResourceDao investSaudiResourceDao ;

    @Override
    public EconomicAndInvestmentMonitorModel getLastEconomicAndInvestmentMonitor() {
        return investSaudiResourceDao.getLastEconomicAndInvestmentMonitor();
    }

    @Override
    public InvestmentHighlightsReportModel getLastInvestmentHighlightsReport() {
        return investSaudiResourceDao.getLastInvestmentHighlightsReport();
    }

    @Override
    public MonthlyBulletinReportModel getLastMonthlyBulletinReport() {
        return investSaudiResourceDao.getLastMonthlyBulletinReport();
    }

    @Override
    public EconomicAndInvestmentReportsAndStudiesModel getLastEconomicAndInvestmentReportsAndStudies() {
        return investSaudiResourceDao.getLastEconomicAndInvestmentReportsAndStudies();
    }
}
