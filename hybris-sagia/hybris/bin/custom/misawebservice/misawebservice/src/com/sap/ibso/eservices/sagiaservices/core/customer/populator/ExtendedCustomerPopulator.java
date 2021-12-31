/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.core.customer.populator;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * Extended populator implementation for {@link de.hybris.platform.core.model.user.CustomerModel} as source and
 * {@link de.hybris.platform.commercefacades.user.data.CustomerData} as target type.
 */
public class ExtendedCustomerPopulator implements Populator<CustomerModel, CustomerData>
{
    private Converter<AddressModel, AddressData> addressConverter;

    protected Converter<AddressModel, AddressData> getAddressConverter()
    {
        return addressConverter;
    }

    @Required
    public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
    {
        this.addressConverter = addressConverter;
    }

    @Override
    public void populate(final CustomerModel source, final CustomerData target) throws ConversionException //NOSONAR
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        if (source.getTitle() != null)
        {
            target.setTitle(source.getTitle().getName());
        }


        if (source.getDefaultPaymentAddress() != null)
        {
            target.setDefaultBillingAddress(getAddressConverter().convert(source.getDefaultPaymentAddress()));
        }

        if (source.getDefaultShipmentAddress() != null)
        {
            target.setDefaultShippingAddress(getAddressConverter().convert(source.getDefaultShipmentAddress()));
        }

        if (source.getApplicantReferenceID() != null)
        {
            target.setApplicantReferenceID(source.getApplicantReferenceID());
        }

        if (source.getCustomerID() != null)
        {
            target.setCustomerId(source.getCustomerID());
        }

        if (source.getMobileStatus() != null)
        {
            target.setMobileStatus(source.getMobileStatus());
        }

        if (source.getRegEmailStatus() != null)
        {
            target.setRegEmailStatus(source.getRegEmailStatus());
        }

        if (source.getEntityID() != null)
        {
            target.setEntityID(source.getEntityID());
        }

        if (source.getIsOutstandingFee() != null)
        {
            target.setIsOutstandingFee(source.getIsOutstandingFee());
        }

        if (source.getUserNameEmail() != null)
        {
            target.setUserNameEmail(source.getUserNameEmail());
        }

        if (source.getQeemahEmail() != null)
        {
            target.setQeemahEmail(source.getQeemahEmail());
        }

        if (source.getQeemahEmailStatus() != null)
        {
            target.setQeemahEmailStatus(source.getQeemahEmailStatus());
        }

        if (source.getInternetUserID() != null)
        {
            target.setInternetUserID(source.getInternetUserID());
        }

        if (source.getApplicationServiceRequestID() != null)
        {
            target.setApplicationServiceRequestID(source.getApplicationServiceRequestID());
        }

        if (source.getDepartment() != null)
        {
            target.setDepartment(source.getDepartment());
        }

        if (source.getOtherUserEntity() != null)
        {
            target.setOtherUserEntity(source.getOtherUserEntity());
        }
    }
}
