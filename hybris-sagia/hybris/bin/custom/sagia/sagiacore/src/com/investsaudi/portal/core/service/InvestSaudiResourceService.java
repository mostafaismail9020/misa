package com.investsaudi.portal.core.service;

import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;

public interface InvestSaudiResourceService {

    EconomicAndInvestmentMonitorModel getLastEconomicAndInvestmentMonitor();


    InvestmentHighlightsReportModel getLastInvestmentHighlightsReport();


    MonthlyBulletinReportModel getLastMonthlyBulletinReport();


    EconomicAndInvestmentReportsAndStudiesModel getLastEconomicAndInvestmentReportsAndStudies();
}
