package com.sap.ibso.eservices.storefront.controllers.cms;


import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.InvestmentSaudiLastReportCategoryComponentModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;
import com.investsaudi.portal.core.service.InvestSaudiResourceService;
import de.hybris.platform.core.model.media.MediaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*
*   This controller is used to display the last n news present in the system
*
*/

@Controller("InvestmentSaudiLastReportCategoryComponentController")
@RequestMapping("/view/InvestmentSaudiLastReportCategoryComponentController")
public class InvestmentSaudiLastReportCategoryComponentController extends AbstractAcceleratorCMSComponentController<InvestmentSaudiLastReportCategoryComponentModel> {




    @Resource
    InvestSaudiResourceService investSaudiResourceService ;


    @Override
    protected void fillModel(HttpServletRequest request, Model model, InvestmentSaudiLastReportCategoryComponentModel component) {


        InvestmentHighlightsReportModel lastInvestmentHighlightsReportModel = investSaudiResourceService.getLastInvestmentHighlightsReport() ;
        EconomicAndInvestmentReportsAndStudiesModel lastEconomicAndInvestmentReportsAndStudies = investSaudiResourceService.getLastEconomicAndInvestmentReportsAndStudies();
        MonthlyBulletinReportModel lastMonthlyBulletinReport = investSaudiResourceService.getLastMonthlyBulletinReport();
        EconomicAndInvestmentMonitorModel lastEconomicAndInvestmentMonitor = investSaudiResourceService.getLastEconomicAndInvestmentMonitor();

        if (lastEconomicAndInvestmentMonitor != null ){
            model.addAttribute("lastReportsBox1", new ReportDto(component.getBox1Name(), lastEconomicAndInvestmentMonitor.getResourceShortInformation(), lastEconomicAndInvestmentMonitor.getResourceDate(), "/economic/investmentReports/economicAndInvestmentMonitor", lastEconomicAndInvestmentMonitor.getResourceThumbnailImage())); //Economic and Investment Monitor
        }
        if (lastInvestmentHighlightsReportModel != null) {
            model.addAttribute("lastReportsBox2", new ReportDto(component.getBox2Name(), lastInvestmentHighlightsReportModel.getResourceShortInformation(), lastInvestmentHighlightsReportModel.getResourceDate(), "/economic/investmentReports/investmentHighlightsReport", lastInvestmentHighlightsReportModel.getResourceThumbnailImage())); //Investment Highlights
        }
        if(lastMonthlyBulletinReport != null){
            model.addAttribute("lastReportsBox3", new ReportDto(component.getBox3Name(), lastMonthlyBulletinReport.getResourceShortInformation(), lastMonthlyBulletinReport.getResourceDate(), "/economic/investmentReports/monthlyBulletinReport", lastMonthlyBulletinReport.getResourceThumbnailImage())); //Monthly Bulletin
        }
        if(lastEconomicAndInvestmentReportsAndStudies != null ){
            model.addAttribute("lastReportsBox4", new ReportDto(component.getBox4Name(), lastEconomicAndInvestmentReportsAndStudies.getResourceShortInformation(), lastEconomicAndInvestmentReportsAndStudies.getResourceDate(), "/economic/investmentReports/economicAndInvestmentReportsAndStudies", lastEconomicAndInvestmentReportsAndStudies.getResourceThumbnailImage())); //Economic and Investment Reports & Studies
        }
           }

    public static class ReportDto {
        String boxTitle;
        String reportShortInformation;
        Date reportDate;
        String reportUrl;
        MediaModel reportImage;

        public ReportDto(String boxTitle, String reportShortInformation, Date reportDate, String reportUrl, MediaModel reportImage) {
            this.boxTitle = boxTitle;
            this.reportShortInformation = reportShortInformation;
            this.reportDate = reportDate;
            this.reportUrl = reportUrl;
            this.reportImage = reportImage;
        }

        public String getBoxTitle() {
            return boxTitle;
        }

        public void setBoxTitle(String boxTitle) {
            this.boxTitle = boxTitle;
        }

        public String getReportShortInformation() {
            return reportShortInformation;
        }

        public void setReportShortInformation(String reportShortInformation) {
            this.reportShortInformation = reportShortInformation;
        }

        public Date getReportDate() {
            return reportDate;
        }

        public void setReportDate(Date reportDate) {
            this.reportDate = reportDate;
        }

        public String getReportUrl() {
            return reportUrl;
        }

        public void setReportUrl(String reportUrl) {
            this.reportUrl = reportUrl;
        }

        public MediaModel getReportImage() {
            return reportImage;
        }

        public void setReportImage(MediaModel reportImage) {
            this.reportImage = reportImage;
        }
    }
}
