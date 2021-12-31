package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.DashboardSectionModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaConfigurationService;
import com.sap.ibso.eservices.core.sagia.services.SagiaDashboardSectionsService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Default implementation of Sagia Dashboard Sections
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaDashboardSectionsService extends AbstractBusinessService implements SagiaDashboardSectionsService {
    @Autowired
    private transient UserService userService;

    @Autowired
    private transient ModelService modelService;//NOSONAR

    @Autowired
    private transient SagiaConfigurationService sagiaConfigurationService;

    String[] sections = { "dashboardImage", "myLicense", "salaryAndEmployment", "servicesRequest","savedDrafts", "payments", "yourTickets", "support"};

    @Override
    public void updateUserDashboardSections(Map<String, ArrayList<String>> map) {
        if(map == null || map.isEmpty()) {
            return;
        }
        List<DashboardSectionModel> sectionsList = new ArrayList<>();
        UserModel user = userService.getCurrentUser();
        if(user != null && user.getDashboardSection() != null && !user.getDashboardSection().isEmpty()) {
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                DashboardSectionModel dashboardSectionModel = new DashboardSectionModel();
                dashboardSectionModel.setSectionCode(entry.getKey());
                dashboardSectionModel.setIndex(Integer.parseInt(entry.getValue().get(0)));
                dashboardSectionModel.setVisible(Boolean.parseBoolean(entry.getValue().get(1)));
                modelService.save(dashboardSectionModel);
                sectionsList.add(dashboardSectionModel);
            }
            user.setDashboardSection(sectionsList);
            modelService.save(user);
        }
    }

    @Override
    public Map<String, ArrayList<String>> getUserDashboardSections() {
        Map<String, ArrayList<String>> map = new HashMap<>();
        UserModel user = userService.getCurrentUser();
        if(user != null && user.getDashboardSection() != null && !user.getDashboardSection().isEmpty()) {
            for (DashboardSectionModel section : user.getDashboardSection()) {
                ArrayList<String> values = new ArrayList<>();
                values.add(String.valueOf(section.getIndex()));

                if ( "salaryAndEmployment".equals(section.getSectionCode()) && !Boolean.valueOf(sagiaConfigurationService.get("enableSalaryAndEmploymentOnDashboard"))) {
                    values.add(String.valueOf(Boolean.valueOf("false")));
                } else {
                    values.add(String.valueOf(section.getVisible()));
                }
                map.put(section.getSectionCode(), values);
            }
        }
        return map;
    }

    @Override
    public void initializeDashboardSections() {
        List<DashboardSectionModel> sectionsList = new ArrayList<>();
        UserModel user = userService.getCurrentUser();
        if (user != null && user.getDashboardSection() != null && user.getDashboardSection().isEmpty()) {
            for (int i = 0; i < sections.length; i++) {
                DashboardSectionModel dashboardSectionModel = new DashboardSectionModel();
                dashboardSectionModel.setSectionCode(sections[i]);
                dashboardSectionModel.setIndex(i);

                if ( "salaryAndEmployment".equals(sections[i]) && !Boolean.valueOf(sagiaConfigurationService.get("enableSalaryAndEmploymentOnDashboard"))) {
                    dashboardSectionModel.setVisible(false);
                } else {
                    dashboardSectionModel.setVisible(true);
                }

                modelService.save(dashboardSectionModel);
                sectionsList.add(dashboardSectionModel);
            }

            user.setDashboardSection(sectionsList);
            modelService.save(user);
        }
    }

}




