package com.sap.ibso.eservices.storefront.controllers.cms;


import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestmentHighlightsReportModel;
import com.investsaudi.portal.core.model.InvestmentSaudiLastReportCategoryComponentModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;
import com.investsaudi.portal.core.service.InvestSaudiResourceService;
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
            model.addAttribute("lastReportsBox1", new ReportDto(lastEconomicAndInvestmentMonitor.getResourceDate(), component.getBox1Name(), "/economic/investmentReports/categoryName1", "/medias/news-national-center-for-waste-management-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyNDczOHxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oNzIvaGIzLzg4Njk1MzQzMzUwMDYuanBnfDg5OWUwOGY5ZmRmNzA5NjY4ZDY5NmNiMGNmNGI0NjYzM2RhNTQ2YmI2YWMyODhkNmNhM2JmNzhiMjUxZGI5M2U")); //Economic and Investment Monitor
        }
        if (lastInvestmentHighlightsReportModel != null) {
            model.addAttribute("lastReportsBox2", new ReportDto(lastInvestmentHighlightsReportModel.getResourceDate(), component.getBox2Name(), "/economic/investmentReports/categoryName1", "/medias/news-ministers-of-Misa-and-Malaysia-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyMTM3M3xpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oYjIvaDg2Lzg4Njk1MzQ1NjQzODIuanBnfGQwZWY2ZGY0YWExMWVkMzQ3NzU4ZTU2NWVhYzhkYWUwNGRmNTNhMzEwZDE3YzExODgxOGNkOTYzM2NkMGI0NDU")); //Investment Highlights
        }
        if(lastMonthlyBulletinReport != null){
            model.addAttribute("lastReportsBox3", new ReportDto(lastEconomicAndInvestmentReportsAndStudies.getResourceDate(), component.getBox3Name(), "/economic/investmentReports/categoryName1", "/medias/news-Roche-and-misa-partnership-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyNDEzOHxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oNzcvaDc2Lzg4Njk1MzQ3MjgyMjIuanBnfDU0ODMwYmY0MDM0M2VhODJkMTlhZWI2ZTMxNzM1NjUxNTgyMDRhMGY3YTkxY2IwY2E2OGMzOTAxNGY1YTY3ZGM")); //Monthly Bulletin
        }
        if(lastEconomicAndInvestmentReportsAndStudies != null ){
            model.addAttribute("lastReportsBox4", new ReportDto(lastEconomicAndInvestmentReportsAndStudies.getResourceDate(), component.getBox4Name(), "/economic/investmentReports/categoryName1", "/medias/news-saudi-uzbek-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwyMDM3N3xpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oNjAvaDU3Lzg4Njk1MzQzMDIyMzguanBnfGI4NDVlMjFmYWIxMDZjNDlhY2ZmOTRkZmY3ZjMyZDY4Yjc0MmE1NTYxNjVjMzU2NGYwYTE0MWYzMTFkYzZhNWE")); //Economic and Investment Reports & Studies
        }
           }

    public static class ReportDto {
        Date date;
        String title;
        String url;
        String image;

        public ReportDto(Date date, String title, String url, String image) {
            this.date = date;
            this.title = title;
            this.url = url;
            this.image = image;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
