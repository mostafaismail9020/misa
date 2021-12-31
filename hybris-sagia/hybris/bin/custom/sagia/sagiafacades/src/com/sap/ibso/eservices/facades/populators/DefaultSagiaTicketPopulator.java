package com.sap.ibso.eservices.facades.populators;

import com.casblogaddon.data.BlogPostComponentData;
import com.casblogaddon.model.BlogPostComponentModel;
import com.casblogaddon.model.CsBlogTicketModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.converters.populators.DefaultTicketPopulator;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.apache.commons.lang.StringUtils;

public class DefaultSagiaTicketPopulator<SOURCE extends CsTicketModel, TARGET extends TicketData>
		extends DefaultTicketPopulator<CsTicketModel, TicketData>
{

	private Populator<BlogPostComponentModel, BlogPostComponentData> blogPostContainerComponentPopulator;

	@Override
	public void populate(final CsTicketModel source, final TicketData target) throws ConversionException
	{
		super.populate(source, target);
		target.setLocation(source.getLocation());
		if(source.getConfiguration() != null)
		{
			target.setSector(source.getConfiguration().getName());
		}

		if(source instanceof CsBlogTicketModel){
			CsBlogTicketModel csBlogTicketModel = (CsBlogTicketModel) source;

			if(csBlogTicketModel.getBlogPostComponent() != null){

				BlogPostComponentModel blogPostComponentModel = csBlogTicketModel.getBlogPostComponent();

				BlogPostComponentData blogPostComponentData = new BlogPostComponentData();
				blogPostContainerComponentPopulator.populate(blogPostComponentModel, blogPostComponentData);

				target.setBlogInfo(blogPostComponentData);

			}

		}
		if(StringUtils.isNotEmpty(source.getOpportunityMessage())) {
			target.setOpportunityMessage(source.getOpportunityMessage());
		}
		if(StringUtils.isNotEmpty(source.getCustomer().getName())) {
			target.setCustomerId(source.getCustomer().getName());
		}
	}

	public void setBlogPostContainerComponentPopulator(Populator<BlogPostComponentModel, BlogPostComponentData> blogPostContainerComponentPopulator) {
		this.blogPostContainerComponentPopulator = blogPostContainerComponentPopulator;
	}
}