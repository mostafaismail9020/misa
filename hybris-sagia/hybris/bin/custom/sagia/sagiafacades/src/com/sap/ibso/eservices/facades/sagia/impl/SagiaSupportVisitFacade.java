package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.event.SupportVisitEmailGenerationEvent;
import com.sap.ibso.eservices.facades.data.SupportVisit;
import com.sap.ibso.eservices.facades.populators.SupportVisitPopulator;
import com.sap.ibso.eservices.facades.populators.SupportVisitReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.impl.SupportVisitSetService;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SagiaSupportVisitFacade
 */
public class SagiaSupportVisitFacade {


    private static final Logger LOG = LoggerFactory.getLogger(SagiaSupportVisitFacade.class);

    private SupportVisitSetService supportVisitSetService;
    private SupportVisitPopulator sagiaSupportVisitPopulator;
    private SupportVisitReversePopulator supportVisitReversePopulator;
    private EventService eventService;
    private BaseStoreService baseStoreService;
    private BaseSiteService baseSiteService;
    private CommonI18NService commonI18NService;
    private UserService userService;

    /**
     * Loads existing support Visits.
     *
     * @return List of SupportVisit
     */
    public List<SupportVisit> getSupportVisits() {
        List<SupportVisit> result = new ArrayList<>();
        try {
            Collection<SupportVisitData> supportVisitDataCollection = supportVisitSetService.getCollection();
            for (SupportVisitData item : supportVisitDataCollection) {
                SupportVisit supportVisitItem = new SupportVisit();
                sagiaSupportVisitPopulator.populate(item, supportVisitItem);
                result.add(supportVisitItem);
            }
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * Loads support Visit with this id.
     *
     * @param id id
     * @return SupportVisit
     */
    public SupportVisit getSupportVisit(String id) {
        SupportVisitData supportVisitData = supportVisitSetService.get(id);
        SupportVisit supportVisitItem = new SupportVisit();
        sagiaSupportVisitPopulator.populate(supportVisitData, supportVisitItem);
        return supportVisitItem;
    }

    /**
     * Saves an support visit and sends an email confirmation
     *
     * @param supportVisit supportVisit
     * @return String
     */
    public void saveSupportVisit(SupportVisit supportVisit) {
        SupportVisitData supportVisitData = new SupportVisitData();
        supportVisitReversePopulator.populate(supportVisit, supportVisitData);
        supportVisitSetService.create(supportVisitData);
        getEventService().publishEvent(initializeEvent(new SupportVisitEmailGenerationEvent(), supportVisitData));
    }

    /**
     * gets SupportVisitSetService
     *
     * @return SupportVisitSetService
     */
    public SupportVisitSetService getSupportVisitSetService() {
        return supportVisitSetService;
    }

    /**
     * sets SupportVisitSetService
     *
     * @param supportVisitSetService supportVisitSetService
     */
    public void setSupportVisitSetService(SupportVisitSetService supportVisitSetService) {
        this.supportVisitSetService = supportVisitSetService;
    }

    /**
     * gets SagiaSupportVisitPopulator
     *
     * @return SupportVisitPopulator
     */
    public SupportVisitPopulator getSagiaSupportVisitPopulator() {
        return sagiaSupportVisitPopulator;
    }

    /**
     * sets SagiaSupportVisitPopulator
     *
     * @param supportVisitPopulator supportVisitPopulator
     */
    public void setSagiaSupportVisitPopulator(SupportVisitPopulator supportVisitPopulator) {
        this.sagiaSupportVisitPopulator = supportVisitPopulator;
    }

    /**
     * gets EventService
     *
     * @return EventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * sets EventService
     *
     * @param eventService eventService
     */
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * @return SupportVisitReversePopulator
     */
    public SupportVisitReversePopulator getSupportVisitReversePopulator() {
        return supportVisitReversePopulator;
    }

    /**
     * @param supportVisitReversePopulator supportVisitReversePopulator
     */
    public void setSupportVisitReversePopulator(SupportVisitReversePopulator supportVisitReversePopulator) {
        this.supportVisitReversePopulator = supportVisitReversePopulator;
    }

    /**
     * @return
     */
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * @param baseStoreService baseStoreService
     */
    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    /**
     * @return
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * @param baseSiteService
     */
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /**
     * @return
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    /**
     * sets CommonI18NService
     *
     * @param commonI18NService commonI18NService
     */
    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    /**
     * gets UserService
     *
     * @return UserService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * sets UserService
     *
     * @param userService userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected AbstractCommerceUserEvent initializeEvent(final SupportVisitEmailGenerationEvent event, final SupportVisitData supportVisitData) {
        event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
        event.setSite(getBaseSiteService().getCurrentBaseSite());
        event.setSupportVisitData(supportVisitData);
        event.setCustomer((CustomerModel) getUserService().getCurrentUser());
        event.setLanguage(getCommonI18NService().getCurrentLanguage());
        event.setCurrency(getCommonI18NService().getCurrentCurrency());
        return event;
    }
}

