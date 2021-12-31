/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.user.impl;

import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import com.sap.ibso.eservices.facades.user.SagiaUserFacade;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation for SagiaUserFacade
 *
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.user.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaUserFacade implements SagiaUserFacade {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaUserFacade.class);
    private SagiaUserService sagiaUserService;
    private Converter<B2BCustomerModel, CustomerData> b2BCustomerConverter;
    @Resource
    private SearchRestrictionService searchRestrictionService;
    @Resource
    private SessionService sessionService;

    /**
     * validates UniqueValue
     *
     * @param userName          userName
     * @param email             email
     * @param mobileNumber      mobileNumber
     * @param mobileCountryCode mobileCountryCode
     * @return boolean
     */
    public boolean validateUniqueValue(final String userName, final String email, final String mobileNumber, final String mobileCountryCode) {
        return sagiaUserService.validateUniqueness(userName, email, mobileNumber, mobileCountryCode);
    }

    @Override
    public List<CustomerData> getAllChildB2BCustomers() {
        List<CustomerData> childB2BCustomerDataList = Collections.EMPTY_LIST;

        Set<B2BCustomerModel> childB2BCustomerModels = sessionService.executeInLocalView(new SessionExecutionBody()
        {
            @Override
            public Set<B2BCustomerModel> execute()
            {
                searchRestrictionService.disableSearchRestrictions();
                Set<B2BCustomerModel> childB2BCustomerModels = sagiaUserService.getAllChildB2BCustomers();
                searchRestrictionService.enableSearchRestrictions();
                return childB2BCustomerModels;
            }
        });

        Set<B2BCustomerModel> childs = sagiaUserService.getAllChildB2BCustomers();

        if (CollectionUtils.isEmpty(childB2BCustomerModels)) {
            LOG.error("No Child B2B Customer found..");
        } else {
            childB2BCustomerDataList = getB2BCustomerConverter().convertAll(childB2BCustomerModels);
        }

        return childB2BCustomerDataList;
    }

    /**
     * Gets sagia user service.
     *
     * @return the sagia user service
     */
    public SagiaUserService getSagiaUserService() {
        return sagiaUserService;
    }

    /**
     * Sets sagia user service.
     *
     * @param sagiaUserService the sagia user service
     */
    public void setSagiaUserService(final SagiaUserService sagiaUserService) {
        this.sagiaUserService = sagiaUserService;
    }

    protected Converter<B2BCustomerModel, CustomerData> getB2BCustomerConverter()
    {
        return b2BCustomerConverter;
    }

    @Required
    public void setB2BCustomerConverter(final Converter<B2BCustomerModel, CustomerData> b2bCustomerConverter)
    {
        b2BCustomerConverter = b2bCustomerConverter;
    }
}
