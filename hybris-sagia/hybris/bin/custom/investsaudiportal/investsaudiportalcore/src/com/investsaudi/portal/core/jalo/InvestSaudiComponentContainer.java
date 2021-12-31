package com.investsaudi.portal.core.jalo;

import java.util.List;

import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import org.apache.log4j.Logger;

public class InvestSaudiComponentContainer extends GeneratedInvestSaudiComponentContainer
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger( InvestSaudiComponentContainer.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	@Override
	public List<SimpleCMSComponent> getCurrentCMSComponents(final SessionContext ctx)
	{
		return getSimpleCMSComponents(ctx);
	}
}
