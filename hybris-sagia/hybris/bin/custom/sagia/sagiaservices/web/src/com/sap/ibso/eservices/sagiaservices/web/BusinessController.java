package com.sap.ibso.eservices.sagiaservices.web;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;

    @RequestMapping(method = RequestMethod.GET, path = "/homehdrs")
    public String getHomeHDRs(final HttpServletRequest request, Model model) {
        try {
            List<HomeHDRData> homeHDRModelList = new ArrayList<>();
            homeHDRModelList.add(zui5SagiaFacade.getHomeHDRData());
            model.addAttribute("homehdrs", homeHDRModelList);
        } catch(Exception e) {

        }
        return "homehdrs";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/surveyhdrs")
    public String getSurveyHDRs(final HttpServletRequest request, Model model) {
        try {
            List<SurveyHDRData> homeHDRModelList = new ArrayList<>();
            homeHDRModelList.add(zui5SagiaFacade.getSurveyHDRData("1234"));
            model.addAttribute("surveyhdrs", homeHDRModelList);
        } catch(Exception e) {

        }
        return "surveyhdrs";
    }
}
