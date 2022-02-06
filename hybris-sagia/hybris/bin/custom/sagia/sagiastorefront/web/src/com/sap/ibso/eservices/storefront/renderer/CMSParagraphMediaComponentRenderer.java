package com.investsaudi.portal.facades.renderer.impl;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;
import de.hybris.platform.acceleratorservices.util.HtmlSanitizerPolicyProvider;
import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;
import de.hybris.platform.util.Config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Value;

public class CMSParagraphMediaComponentRenderer implements CMSComponentRenderer<SagiaCMSParagraphMediaComponentModel>
{

   private static final String UNSAFE_JAVASCRIPT_ALLOWED = "cms.components.allowUnsafeJavaScript";
	
	@Override
	public void renderComponent(final PageContext pageContext, final SagiaCMSParagraphMediaComponentModel component)
			throws ServletException, IOException
	{
		// <div class="content">${content}</div>
		final JspWriter out = pageContext.getOut();

		out.write("<div class=\"content\">");
		
		final String content = component.getContent() == null ? StringUtils.EMPTY : component.getContent();
		
		if(isUnsafeJavaScriptAllowed())
		{
				out.write(content);
		}
		else
		{
				out.write(HtmlSanitizerPolicyProvider.defaultPolicy().sanitize(content));
		}
		out.write("</div>");
	}
	
	protected boolean isUnsafeJavaScriptAllowed()
	{
		return Config.getBoolean(UNSAFE_JAVASCRIPT_ALLOWED, false);
	}
	
}
