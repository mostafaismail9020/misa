package com.sap.ibso.eservices.sagiaservices.price.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.price.AmendmentBackendParam;
import com.sap.ibso.eservices.bol.price.PriceSimulationBackendService;
import com.sap.ibso.eservices.bol.price.RenewalBackendParam;
import com.sap.ibso.eservices.bol.price.data.PriceSimulationBackendData;
import com.sap.ibso.eservices.bol.price.data.PriceSimulationItemBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationItemData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.price.AmendmentParam;
import com.sap.ibso.eservices.sagiaservices.price.PriceSimulationService;
import com.sap.ibso.eservices.sagiaservices.price.RenewalParam;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements access to e-services related price simulations.
 */
public class DefaultPriceSimulationService extends BackendAwareService implements PriceSimulationService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultPriceSimulationService.class);

    private CommonI18NService commonI18NService;
    private InvestorMappingService investorMappingService;

    /**
     * Creates the default price simulation service.
     *
     * @param priceSimulationBackendServiceBeanName the bean name (or alias) of the price simulation backend service
     * @param commonI18NService                     the session service to retrieve the currently used session language
     * @param investorMappingService                the investor mapping service to retrieve the entity identifier associated with the current user
     */
    public DefaultPriceSimulationService(String priceSimulationBackendServiceBeanName, CommonI18NService commonI18NService, InvestorMappingService investorMappingService)
    {
        super(priceSimulationBackendServiceBeanName);
        this.commonI18NService = commonI18NService;
        this.investorMappingService = investorMappingService;
    }

    @Override
    public PriceSimulationData getPriceSimulationData(String serviceType, AmendmentParam amendmentParam, RenewalParam renewalParam)
    {
        Assert.notNull(serviceType, "Service type must not be null");

        // Backend service access
        PriceSimulationBackendService backendService = getBackendService();
        // Retrieve priced e-services in the corresponding backend system
        PriceSimulationBackendData priceSimulationBackendData = backendService.getPriceSimulationData(serviceType, investorMappingService.getEntityId(), convert(amendmentParam), convert(renewalParam), getSessionLanguage());

        // Log potential backend messages
        MessageUtil.logBackendMessages(priceSimulationBackendData.getMessages(), LOGGER);

        return convert(priceSimulationBackendData);
    }

    @Override
    public PriceSimulationData getPriceSimulationData(String serviceType, String qeemah) {
        Assert.notNull(serviceType, "Service type must not be null");
        Assert.notNull(qeemah, "Qeemah must not be null");

        // Backend service access
        PriceSimulationBackendService backendService = getBackendService();
        // Retrieve priced e-services in the corresponding backend system
        PriceSimulationBackendData priceSimulationBackendData = backendService.getPriceSimulationData(serviceType, qeemah, getSessionLanguage());

        // Log potential backend messages
        MessageUtil.logBackendMessages(priceSimulationBackendData.getMessages(), LOGGER);

        return convert(priceSimulationBackendData);
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
     * Converts a {@link PriceSimulationBackendData} instance into a {@link PriceSimulationData} instance.
     *
     * @param backendData the price simulation backend data
     * @return the price simulation data
     */
    private PriceSimulationData convert(PriceSimulationBackendData backendData)
    {
        PriceSimulationData data = new PriceSimulationData();
        List<PriceSimulationItemBackendData> backendItems = backendData.getItems();

        if (CollectionUtils.isEmpty(backendItems))
        {
            data.setPriceSimulationItems(Collections.emptyList());
            return data;
        }
        else
        {
            List<PriceSimulationItemData> items = new ArrayList<>(backendItems.size());
            for (PriceSimulationItemBackendData backendItem : backendItems)
            {
                PriceSimulationItemData item = new PriceSimulationItemData();

                item.setServiceName(backendItem.getServiceName());
                item.setNetValue(backendItem.getNetValue());
                item.setCurrencyIso(backendItem.getCurrency());

                items.add(item);
            }

            data.setPriceSimulationItems(items);
            return data;
        }
    }

    /**
     * Converts a {@link AmendmentParam} instance into a {@link AmendmentBackendParam} instance.
     *
     * @param amendmentParam the amendment parameters
     * @return the amendment backend parameters
     */
    private AmendmentBackendParam convert(AmendmentParam amendmentParam)
    {
        if (amendmentParam == null)
        {
            return null;
        }

        AmendmentBackendParam backendParam = new AmendmentBackendParam();

        backendParam.setBranchNumberOfNewBranches(amendmentParam.getBranchNumberOfNewBranches());
        backendParam.setBranchClosing(amendmentParam.isBranchClosing());
        backendParam.setBranchOpening(amendmentParam.isBranchOpening());
        backendParam.setEntityActivityChange(amendmentParam.isEntityActivityChange());
        backendParam.setEntityCapitalIncreasing(amendmentParam.isEntityCapitalIncreasing());
        backendParam.setEntityCapitalReduction(amendmentParam.isEntityCapitalReduction());
        backendParam.setEntityConversionToIndividualLimitedLiabilityCompany(amendmentParam.isEntityConversionToIndividualLimitedLiabilityCompany());
        backendParam.setEntityConversionToLimitedLiabilityCompany(amendmentParam.isEntityConversionToLimitedLiabilityCompany());
        backendParam.setEntityLaborIncreasing(amendmentParam.isEntityLaborIncreasing());
        backendParam.setEntityMainBranchLocationChange(amendmentParam.isEntityMainBranchLocationChange());
        backendParam.setEntityNameChange(amendmentParam.isEntityNameChange());
        backendParam.setProductChange(amendmentParam.isProductChange());
        backendParam.setShareholderAddition(amendmentParam.isShareholderAddition());
        backendParam.setShareholderNameChange(amendmentParam.isShareholderNameChange());
        backendParam.setShareholderPropertyInheritance(amendmentParam.isShareholderPropertyInheritance());
        backendParam.setShareholderRemoval(amendmentParam.isShareholderRemoval());
        backendParam.setShareholderShareRedistribution(amendmentParam.isShareholderShareRedistribution());

        return backendParam;
    }

    /**
     * Converts a {@link RenewalParam} instance into a {@link RenewalBackendParam} instance.
     *
     * @param renewalParam the renewal parameters
     * @return the renewal backend parameters
     */
    private RenewalBackendParam convert(RenewalParam renewalParam)
    {
        if (renewalParam == null)
        {
            return null;
        }

        RenewalBackendParam backendParam = new RenewalBackendParam();

        backendParam.setRenewalPeriods(renewalParam.getRenewalPeriods());

        return backendParam;
    }
}
