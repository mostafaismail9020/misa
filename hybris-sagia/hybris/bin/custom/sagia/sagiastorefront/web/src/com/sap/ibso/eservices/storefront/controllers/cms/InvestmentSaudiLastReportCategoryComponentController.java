package com.sap.ibso.eservices.storefront.controllers.cms;


import com.investsaudi.portal.core.model.InvestmentSaudiLastReportCategoryComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;
import de.hybris.bootstrap.annotations.Accessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/*
*   This controller is used to display the last n news present in the system
*
*/

@Controller("InvestmentSaudiLastReportCategoryComponentController")
@RequestMapping("/view/InvestmentSaudiLastReportCategoryComponentController")
public class InvestmentSaudiLastReportCategoryComponentController extends AbstractAcceleratorCMSComponentController<InvestmentSaudiLastReportCategoryComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, InvestmentSaudiLastReportCategoryComponentModel component) {
        Collection<ReportDto> lastReports = new LinkedList<>();
        lastReports.add(new ReportDto(new Date(), "title1", "/economic/investmentReports/categoryName1", "imageUrl"));
        lastReports.add(new ReportDto(new Date(), "title2", "/economic/investmentReports/categoryName2", "imageUrl"));
        lastReports.add(new ReportDto(new Date(), "title3", "/economic/investmentReports/categoryName3", "imageUrl"));
        lastReports.add(new ReportDto(new Date(), "title4", "/economic/investmentReports/categoryName4", "imageUrl"));

        model.addAttribute("lastReports", lastReports);
    }

    private static class ReportDto {
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
