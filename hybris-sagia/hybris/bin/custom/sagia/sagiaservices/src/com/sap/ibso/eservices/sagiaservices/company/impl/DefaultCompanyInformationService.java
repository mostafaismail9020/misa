package com.sap.ibso.eservices.sagiaservices.company.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.company.CompanyInformationBackendService;
import com.sap.ibso.eservices.bol.company.data.BasicCompanyBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.company.CompanyInformationService;
import com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Implements access to e-services related investor company data.
 */
public class DefaultCompanyInformationService extends BackendAwareService implements CompanyInformationService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultCompanyInformationService.class);

    private CommonI18NService commonI18NService;
    private InvestorMappingService investorMappingService;

    /**
     * Creates the default company information service.
     *
     * @param companyInformationBackendServiceBeanName the company information backend service bean name
     * @param commonI18NService                        the session service to retrieve the currently used session language
     * @param investorMappingService                   the investor mapping service to retrieve the entity identifier associated with the current user
     */
    public DefaultCompanyInformationService(String companyInformationBackendServiceBeanName, CommonI18NService commonI18NService, InvestorMappingService investorMappingService)
    {
        super(companyInformationBackendServiceBeanName);
        this.commonI18NService = commonI18NService;
        this.investorMappingService = investorMappingService;
    }

    @Override
    public BasicCompanyData getBasicInformation()
    {
        // Backend service access
        CompanyInformationBackendService backendService = getBackendService();
        // Retrieve basic company data in the corresponding backend system
        BasicCompanyBackendData basicCompanyBackendData = backendService.getBasicInformation(investorMappingService.getEntityId(), getSessionLanguage());

        // Log potential backend messages
        MessageUtil.logBackendMessages(basicCompanyBackendData.getMessages(), LOGGER);

        return convert(basicCompanyBackendData);
    }

    /**
     * Gets the ISO code of the current session language.
     *
     * @return the session language
     */
    private String getSessionLanguage()
    {
        LanguageModel language = commonI18NService.getCurrentLanguage();
        Assert.notNull(language, "Current session language must not be null");

        return language.getIsocode();
    }

    /**
     * Converts a {@link BasicCompanyBackendData} instance into a {@link BasicCompanyData} instance.
     *
     * @param backendData the basic company backend data
     * @return the basic company data
     */
    private BasicCompanyData convert(BasicCompanyBackendData backendData)
    {
        BasicCompanyData basicCompanyData = new BasicCompanyData();

        basicCompanyData.setBusinessEmailAddress(backendData.getBusinessEmailAddress());
        basicCompanyData.setCapitalValue(backendData.getCapitalValue());
        basicCompanyData.setCity(backendData.getCity());
        basicCompanyData.setCompanyNameInArabic(backendData.getCompanyNameInArabic());
        basicCompanyData.setCompanyNameInEnglish(backendData.getCompanyNameInEnglish());
        basicCompanyData.setCountry(backendData.getCountry());
        basicCompanyData.setCurrencyIso(backendData.getCurrencyIso());
        basicCompanyData.setIsicDevision(backendData.getIsicDevision());
        basicCompanyData.setIsicSection(backendData.getIsicSection());
        basicCompanyData.setIsinCode(backendData.getIsinCode());
        basicCompanyData.setLegalStatus(backendData.getLegalStatus());
        basicCompanyData.setRegion(backendData.getRegion());

        return basicCompanyData;
    }
}
