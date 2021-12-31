/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.breadcrumb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSContentPageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Breadcrumb builder that uses page title in breadcrumb or page name as fallback when title is missing.
 */
public class PortalContentPageBreadcrumbBuilder extends ContentPageBreadcrumbBuilder {
    private static final String LAST_LINK_CLASS = "active";

    private static final Logger log = LoggerFactory.getLogger(PortalContentPageBreadcrumbBuilder.class);

    @Autowired
    CMSContentPageService cmsContentPageService;

    /**
     * @param page - page to build up breadcrumb for
     * @return breadcrumb for given page
     */
    @Override
    public List<Breadcrumb> getBreadcrumbs(final ContentPageModel page) {
        String pageLabel = page.getLabel();
        
        if (StringUtils.isNotBlank(pageLabel)) {

            String[] labels = StringUtils.split(pageLabel, "/");
            String currentLabel = labels[labels.length - 1];
            List<Breadcrumb> breadcrumbs = new ArrayList<>();

            for (String label : labels) {

                String url = StringUtils.substringBefore(pageLabel, "/" + label) + "/" + label;

                Breadcrumb breadcrumb;
                try {
                    ContentPageModel pageForLabel = cmsContentPageService.getPageForLabelOrIdAndMatchType(url, true);
                    breadcrumb = getBreadcrumb(pageForLabel);
                } catch (CMSItemNotFoundException e) {
                    log.warn("Content page with label [{}] not found", url);
                    breadcrumb = new Breadcrumb("#", label, LAST_LINK_CLASS);
                }

                if (!label.equals(currentLabel)) {
                    breadcrumb.setUrl(url);
                }

                breadcrumbs.add(breadcrumb);
            }

            return breadcrumbs;
        }

        return Collections.singletonList(getBreadcrumb(page));
    }

    private Breadcrumb getBreadcrumb(final ContentPageModel page) {
        String title = page.getTitle();
        if (title == null) {
            title = page.getName();
        }
        Breadcrumb breadcrumb = new Breadcrumb("#", title, LAST_LINK_CLASS);
        return breadcrumb;
    }

}
