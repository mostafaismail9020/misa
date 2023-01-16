/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.portal.core.populator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ContactTicketPurposeModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.ticketsystem.data.ContactTicketParameter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactTicketPopulator<SOURCE extends ContactTicketParameter, TARGET extends ContactTicketModel>
    implements Populator<SOURCE, TARGET> {

    private static final Logger log = LoggerFactory.getLogger(ContactTicketPopulator.class);

    @Autowired
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    private static String CONTACT_DEFAULT_EMAIL_TO = "contact.default.email.to";

    @Autowired
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource(name = "sessionService")
    private SessionService sessionService;


    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException {

        if(null!=sessionService.getAttribute("isMizaContactUsFlow"))
        {
            target.setSendEmailTo("miza@misa.gov.sa");
        }
        else {
            List<String> sendEmailsTo = new ArrayList<>();
            sendEmailsTo.add(configurationService.getConfiguration().getString(CONTACT_DEFAULT_EMAIL_TO));
            try {
                CategoryModel sector = categoryService.getCategoryForCode(source.getCategoryCode());
                if (sector != null && StringUtils.isNotBlank(sector.getEmailList())) {
                    sendEmailsTo.addAll(Arrays.asList(StringUtils.split(sector.getEmailList(), ",")));
                }

                String opportunity = StringUtils.isNoneEmpty(source.getOpportunity()) ?
                        " - Opportunity: " + source.getOpportunity() : "";
                target.setSectorCategory((sector != null ? sector.getName() :
                        source.getCategoryCode()) + opportunity);
                target.setSectorCategoryCode(source.getCategoryCode());
                target.setOpportunityCode(StringUtils.isNoneEmpty(source.getProductCode()) ? source.getProductCode() : "");
                target.setOpportunityName(StringUtils.isNoneEmpty(source.getOpportunity()) ? source.getOpportunity() : "");
                target.setSectorCategoryName(sector != null ? sector.getName() : source.getCategoryCode());
                target.setC4CAccountID(StringUtils.isNoneEmpty(source.getC4CAccountID()) ? source.getC4CAccountID() : "");
                target.setCommerceUserID(StringUtils.isNoneEmpty(source.getCommerceUserID()) ? source.getCommerceUserID() : "");

            } catch (UnknownIdentifierException e) {
                log.warn("Category with code [{}]  not found", source.getCategoryCode(), e);
                target.setSectorCategory(source.getOpportunity());
            }

            String queryString = "Select {pk} FROM {ContactTicketPurpose} WHERE {code}=?code";
            final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
            query.addQueryParameter("code", StringUtils.isNotBlank(source.getContactSubject()) ? source.getContactSubject() : "interest-invest");
            var contactSubjects = flexibleSearchService.<ContactTicketPurposeModel>search(query).getResult();
            if (CollectionUtils.isNotEmpty(contactSubjects)) {
                if (StringUtils.isNoneEmpty(contactSubjects.get(0).getEmailList())) {
                    sendEmailsTo.addAll(Arrays.asList(StringUtils.split(contactSubjects.get(0).getEmailList(), ",")));
                }
                target.setHeadline(contactSubjects.get(0).getLabel());
                target.setContactSubject(contactSubjects.get(0).getLabel());
            }
            target.setSendEmailTo(StringUtils.join(sendEmailsTo, ","));

        }
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMobile(source.getMobile());
        target.setCompany(source.getCompany());
        target.setJobTitle(source.getJobTitle());
        target.setMessage(StringUtils.substring(source.getMessage(), 0, 254));
		target.setPartner(source.getPartnerSystem());
        if(null!=source.getPartnerSystem() && "MCIT".equalsIgnoreCase(source.getPartnerSystem()))
        {
            target.setSourceCode("ZMC");
        }
        else
        {
            target.setSourceCode("Z01");
        }
    }
}
