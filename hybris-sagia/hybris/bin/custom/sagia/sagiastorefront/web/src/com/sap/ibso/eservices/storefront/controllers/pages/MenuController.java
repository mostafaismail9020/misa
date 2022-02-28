package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaNavigationMenuFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    private static final String MENU_DATA = "sagiaMenuData";
    private static final String NAVCATEGORIES = "navcategories";
    private static final String NAVSERVICES = "navservices";

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Autowired
    private SagiaNavigationMenuFacade sagiaNavigationMenuFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/cached")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getMenuData() {
        Object menuData = getSessionService().getAttribute(MENU_DATA);
        if(menuData == null) {
            menuData = Collections.emptyMap();
            getSessionService().setAttribute(MENU_DATA, menuData);
        }
        return (Map<String, Object>)menuData;
    }

    private void updateMenuData() {
        Map<String, Object> menuData = new HashMap<>();
        boolean hasLicense = sagiaDashboardWithoutLicenseFacade.hasLicense();
        if(hasLicense) {
            menuData.put(NAVCATEGORIES, sagiaNavigationMenuFacade.getNavigationMenuCategories());
            menuData.put(NAVSERVICES, sagiaNavigationMenuFacade.getNavigationMenuServices());
        }
        getSessionService().setAttribute(MENU_DATA, menuData);
    }

    private boolean areEqual(SagiaCategoryData oldCategoryData, SagiaCategoryData newCategoryData) {
    	if (null != oldCategoryData && null != newCategoryData) {
	        if(!oldCategoryData.getCategoryUrl().equals(newCategoryData.getCategoryUrl())
	                //|| !oldCategoryData.getDescription().equals(newCategoryData.getDescription())
	                || !oldCategoryData.getLabel().equals(newCategoryData.getLabel())
	                || !oldCategoryData.getName().equals(newCategoryData.getName())) {
	            return false;
	        }
    	}
        return true;
    }

    private boolean hasMenuDataChanged(Map<String, Object> oldMenuData, Map<String, Object> newMenuData) {
        if (oldMenuData == null || newMenuData == null) {
            return true;
        }

        if (checkCategories((Map<String, List<SagiaCategoryData>>) oldMenuData.get(NAVCATEGORIES), (Map<String, List<SagiaCategoryData>>) newMenuData.get(NAVCATEGORIES))) {
            return true;
        }

        if (checkServices((Map<String, List<SagiaServiceData>>) oldMenuData.get(NAVSERVICES), (Map<String, List<SagiaServiceData>>) newMenuData.get(NAVSERVICES))) {
            return true;
        }

        return false;

    }


    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S2864 | "entrySet()" should be iterated when both the key and value are needed
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776", "squid:MethodCyclomaticComplexity", "squid:S2864","squid:S134"})
    private boolean checkServices(Map<String, List<SagiaServiceData>> oldServices, Map<String, List<SagiaServiceData>> newServices) {
        if (oldServices == null || newServices == null) {
            return true;
        }
        if (oldServices.keySet() == null || newServices.keySet() == null) {
            return true;
        }
        if (!oldServices.keySet().equals(newServices.keySet())) {
            return true;
        }
        for (String key : oldServices.keySet()) {
            List<SagiaServiceData> oldServiceDataList = oldServices.get(key);
            List<SagiaServiceData> newServiceDataList = newServices.get(key);
            if(oldServiceDataList.size() != newServiceDataList.size()) {
                return true;
            }
            for(SagiaServiceData oldServiceData : oldServiceDataList) {
                boolean found = false;
                for(SagiaServiceData newServiceData : oldServiceDataList) {
                    if(oldServiceData.getName().equals(newServiceData.getName())) {
                        found = true;
                        if (!oldServiceData.getMenuUrl().equals(newServiceData.getMenuUrl())) {
                            return true;
                        }
                        break;
                    }
                }
                if(!found) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S2864 | "entrySet()" should be iterated when both the key and value are needed
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:S3776", "squid:MethodCyclomaticComplexity", "squid:S2864","squid:S134"})
    private boolean checkCategories(Map<String, List<SagiaCategoryData>> oldCategories, Map<String, List<SagiaCategoryData>> newCategories) {
        if (oldCategories == null || newCategories == null) {
            return true;
        }
        if (oldCategories.keySet() == null || newCategories.keySet() == null) {
            return true;
        }
        if (!oldCategories.keySet().equals(newCategories.keySet())) {
            return true;
        }
        for (String key : oldCategories.keySet()) {
            List<SagiaCategoryData> oldCategoryDataList = oldCategories.get(key);
            List<SagiaCategoryData> newCategoryDataList = newCategories.get(key);
            if (oldCategoryDataList.size() != newCategoryDataList.size()) {
                return true;
            }
            for (SagiaCategoryData oldCategoryData : oldCategoryDataList) {
                boolean found = false;
                for (SagiaCategoryData newCategoryData : oldCategoryDataList) {
                    if (oldCategoryData.getCode().equals(newCategoryData.getCode())) {
                        found = true;
                        if (!areEqual(oldCategoryData, newCategoryData)) {
                            return true;
                        }
                        break;
                    }
                }
                if (!found) {
                    return true;
                }
            }
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getMenuDataIfChanged() {
        Map<String, Object> currentMenuData = getMenuData();
        updateMenuData();
        Map<String, Object> newMenuData = getMenuData();
        if(hasMenuDataChanged(currentMenuData, newMenuData)) {
            return newMenuData;
        } else {
            return Collections.emptyMap();
        }
    }

    public SessionService getSessionService() {
        return sessionService;
    }
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
