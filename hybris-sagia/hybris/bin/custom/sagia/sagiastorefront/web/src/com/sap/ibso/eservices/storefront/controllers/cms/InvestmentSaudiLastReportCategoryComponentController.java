package com.sap.ibso.eservices.storefront.controllers.cms;


import com.investsaudi.portal.core.model.InvestmentSaudiLastReportCategoryComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*
*   This controller is used to display the last n news present in the system
*
*/

@Controller("InvestmentSaudiLastReportCategoryComponentController")
@RequestMapping("/view/InvestmentSaudiLastReportCategoryComponentController")
public class InvestmentSaudiLastReportCategoryComponentController extends AbstractAcceleratorCMSComponentController<InvestmentSaudiLastReportCategoryComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, InvestmentSaudiLastReportCategoryComponentModel component) {
        model.addAttribute("lastReportsBox1", new ReportDto(new Date(), component.getBox1Name(), "/economic/investmentReports/categoryName1", "/medias/news-national-center-for-waste-management-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzU0MTN8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaGQ3L2gyOS84ODEwOTU4MjI1NDM4LmpwZ3w5YjYzYmEwZTEzYTJiOGY0ZjM1MTBmMTc0NmJlODEyNDE1NjYwNjhiOTFmNjZlZWI4MGFiYmEzN2UwNDI0ZWYy")); //Economic and Investment Monitor
        model.addAttribute("lastReportsBox2", new ReportDto(new Date(), component.getBox2Name(), "/economic/investmentReports/categoryName1", "/medias/news-ministers-of-Misa-and-Malaysia-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzIxMjV8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDBhL2g1Ni84ODEwOTU3OTk2MDYyLmpwZ3xlOTE0ZjY5ODgyMGE5MDQxOWE4MDAxOTgzNDBhMDNiNzYwMjE3ZGNmMGJlMjkzYTI3MjIzMzEyNDYxYTc4ODU0")); //Investment Highlights
        model.addAttribute("lastReportsBox3", new ReportDto(new Date(), component.getBox3Name(), "/economic/investmentReports/categoryName1", "/medias/news-Roche-and-misa-partnership-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMzM2MTN8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaDE5L2hhYS84ODExNzI0NzM0NDk0LmpwZ3xmYTYxNmE5YmRjODQ2MTI1NDZkOGMwZjZhNzJlZjI0ZGFkM2IwNDM5M2VhZTdjOGJhMDQ5YjRjNDhlNzQ5ZTQz")); //Monthly Bulletin
        model.addAttribute("lastReportsBox4", new ReportDto(new Date(), component.getBox4Name(), "/economic/investmentReports/categoryName1", "/medias/news-saudi-uzbek-thumbnail.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXwxMTMxMzV8aW1hZ2UvanBlZ3xwb3J0YWwtbWVkaWEvaGJhL2gxYS84ODExMTk5Mzk3OTE4LmpwZ3xkMmJlOTdlNjMwYTMzZmI5OGExMDk4ZDYzMTU1MjliMzBmZGJkNzczODZkMTE2MGNlNzQ2Y2Y3OGZkYWMwYzBj")); //Economic and Investment Reports & Studies
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
