package com.investsaudi.portal.facades.solrfacetsearch.populators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class SagiaExtSearchResultProductPopulator extends SearchResultProductPopulator {

    private static final Logger LOG = Logger.getLogger(SagiaExtSearchResultProductPopulator.class);
    private static final String FORMAT_TO_REMOVE = "<em class=\"search-results-highlight\">";
    private static final String FORMAT_TO_REMOVE_2 = "</em>";

    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        List superCategories = this.getValue(source, "category");
        if (CollectionUtils.isNotEmpty(superCategories)) {
            target.setParentCategory(superCategories.get(0).toString());
        }

        List sagiaRegions = this.<List>getValue(source, "sagiaRegion");
        if (CollectionUtils.isNotEmpty(sagiaRegions)) {
            target.setSagiaRegion(sagiaRegions.get(0).toString());
        }

        if(StringUtils.isNotBlank(target.getName())){
            target.setName(target.getName().replaceAll(FORMAT_TO_REMOVE, "").replaceAll(FORMAT_TO_REMOVE_2,""));
        }
        target.setResource(this.getValue(source, "resource"));
        target.setImageUrl(this.getValue(source, "picture"));
        target.setExpectedIRR(this.getValue(source, "expectedIRR"));
        if (target.getResource().equals("Event")) {
        	SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            target.setEventLocation(this.getValue(source, "eventLocation"));
            target.setEventTiming(this.getValue(source, "eventTiming"));	
            try {
				target.setEventDate(eventDateFormat.parse(this.getValue(source, "creationTime")));
			} catch (ParseException e) {
				LOG.error("Error while parsing event date for " + target.getCode() ,e);
			}
		}
        else if (target.getResource().equals("News")) {
        	SimpleDateFormat newsDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
				target.setNewsDate(newsDateFormat.parse(this.getValue(source, "creationTime")));
			} catch (ParseException e) {
				LOG.error("Error while parsing news date for " + target.getCode() ,e);
			}	
		}
        else if (target.getResource().equals("Report")) {
            SimpleDateFormat reportDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            target.setReportType(this.getValue(source, "reportType"));
            try {
                target.setReportDate(reportDateFormat.parse(this.getValue(source, "creationTime")));
            } catch (ParseException e) {
                LOG.error("Error while parsing report date for " + target.getCode() ,e);
            }
        }
        else if (target.getResource().equals("Article")) {
            SimpleDateFormat articleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                target.setArticleDate(articleDateFormat.parse(this.getValue(source, "creationTime")));
            } catch (ParseException e) {
                LOG.error("Error while parsing report date for " + target.getCode() ,e);
            }
        }
    }
}
