
package com.sap.ibso.eservices.core.interceptors;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.investsaudi.portal.core.model.OpportunityProductModel;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;


public class SagiaOpportunityPrepareInterceptor implements PrepareInterceptor<OpportunityProductModel>
{
	private static final Logger LOG = Logger.getLogger(SagiaOpportunityPrepareInterceptor.class);

    @Override
    public void onPrepare(final OpportunityProductModel opportunityProductModel, final InterceptorContext ctx) throws InterceptorException
    {
    	try {
        	final List<Locale> locales = Arrays.asList(new Locale("ar"), new Locale("en"));
	    	for (Locale currentLocale : locales) {
		        if(ctx.isModified(opportunityProductModel, OpportunityProductModel.SEGMENT)){
		        	opportunityProductModel.setSegmentText(
		    				CollectionUtils.emptyIfNull(opportunityProductModel.getSagiaSegment()).stream()
		    				.map(seg -> seg.getSegmentName())
		    				.collect(Collectors.joining(", "))
		    				);
		        }
		        if(ctx.isModified(opportunityProductModel, OpportunityProductModel.LOCATION)){
		        	opportunityProductModel.setRegionText(
		        			CollectionUtils.emptyIfNull(opportunityProductModel.getLocation()).stream()
		    				.filter(loc -> null != loc.getRegion() && StringUtils.isNotEmpty(loc.getRegion().getName(currentLocale)))
		    				.map(loc -> loc.getRegion().getName(currentLocale))
		    				.collect(Collectors.joining(", "))
		    			);
		        	opportunityProductModel.setCityText(
		        			CollectionUtils.emptyIfNull(opportunityProductModel.getLocation()).stream()
		    				.filter(loc -> null != loc.getCity() && StringUtils.isNotEmpty(loc.getCity().getName(currentLocale)))
		    				.map(loc -> loc.getCity().getName(currentLocale))
		    				.collect(Collectors.joining(", "))
		    			);
		        }
			}
    	}
    	catch(Exception e) {
    		LOG.error("Error occurred in SagiaOpportunityPrepareInterceptor", e);
    	}
    }
}
