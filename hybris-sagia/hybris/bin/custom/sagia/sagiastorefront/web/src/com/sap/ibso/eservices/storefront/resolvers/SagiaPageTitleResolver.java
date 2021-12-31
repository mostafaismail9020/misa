package web.src.com.sap.ibso.eservices.storefront.resolvers;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import java.lang.String;
import de.hybris.platform.cms2.model.site.CMSSiteModel;

import de.hybris.platform.acceleratorservices.storefront.util.PageTitleResolver;

public class SagiaPageTitleResolver extends PageTitleResolver {
	
	
	@Override
	public String resolveContentPageTitle(final String title)
	{
		final CMSSiteModel currentSite = getCmsSiteService().getCurrentSite();

		final StringBuilder builder = new StringBuilder();
		if (!StringUtils.isEmpty(title))
		{
			builder.append(title);
		}else {
			builder.append(currentSite.getName());
		}
		return StringEscapeUtils.escapeHtml(builder.toString());
	}

}
