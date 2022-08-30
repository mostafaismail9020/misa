package com.investsaudi.portal.core.dao;

import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;

public interface InvestSaudiResourceDao {


    EconomicAndInvestmentMonitorModel getLastEconomicAndInvestmentMonitor();

    InvestmentHighlightsReportModel getLastInvestmentHighlightsReport();

    MonthlyBulletinReportModel getLastMonthlyBulletinReport();

    EconomicAndInvestmentReportsAndStudiesModel getLastEconomicAndInvestmentReportsAndStudies();

}
