package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.sagia.services.SagiaAccountCompanyService;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

/**
 * Default implementation of Account Company Service
 * Created by i335541 on 2/12/18.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaAccountCompanyService extends AbstractBusinessService implements SagiaAccountCompanyService {

    @Override
    public ProfileCompanyData getCompanyForCode(String code) {
        return getMockCompanyData();
    }

    private ProfileCompanyData getMockCompanyData(){
        ProfileCompanyData companyData = new ProfileCompanyData();
        companyData.setCapital("10000");
        companyData.setCity("Riad");
        companyData.setCountry("Saudi Arabia");
        companyData.setEntityName("entity name");
        companyData.setEntityNameArabic("arabic entiry name");
        companyData.setISICDivision("isic div");
        companyData.setISICSection("isic sec");
        companyData.setISINCode("isin code");
        companyData.setLegalStatus("status");
        companyData.setRegion("region");

        return companyData;
    }
}
