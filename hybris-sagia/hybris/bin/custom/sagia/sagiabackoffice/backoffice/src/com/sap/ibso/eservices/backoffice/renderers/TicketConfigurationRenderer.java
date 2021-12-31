package com.sap.ibso.eservices.backoffice.renderers;

import com.hybris.cockpitng.editors.EditorContext;
import com.hybris.cockpitng.editors.EditorListener;
import com.hybris.cockpitng.editors.impl.AbstractCockpitEditorRenderer;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;

import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;

public class TicketConfigurationRenderer extends AbstractCockpitEditorRenderer<TicketConfigurationModel> {

	@Override
	public void render(Component parent, EditorContext<TicketConfigurationModel> context, EditorListener<TicketConfigurationModel> listener) {
		
		final String initialValue = context.getInitialValue().getCode();
        final Text text = new Text();
        text.setValue(initialValue);
        parent.appendChild(text);
        parent.setAttribute("configuration", text);
       // text.setParent(parent);
		
	}

}
