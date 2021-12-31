package com.sap.ibso.eservices.facades.populators;

import com.casblogaddon.model.CsBlogTicketModel;
import com.sap.ibso.eservices.core.model.TicketAnswerModel;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaTicketBusinessService;
import de.hybris.platform.customerticketingfacades.converters.populators.DefaultTicketEventPopulator;
import de.hybris.platform.customerticketingfacades.data.TicketEventData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import org.apache.commons.lang.StringUtils;
import org.fest.util.Collections;

import java.util.Set;

public class DefaultSagiaTicketEventPopulator<SOURCE extends CsTicketEventModel, TARGET extends TicketEventData>
		extends DefaultTicketEventPopulator<CsTicketEventModel, TicketEventData>
{

	@Override
	public void populate(final CsTicketEventModel source, final TicketEventData target) throws ConversionException
	{
		super.populate(source, target);

		if(source.getTicket() instanceof CsBlogTicketModel){
			CsBlogTicketModel csBlogTicketModel = (CsBlogTicketModel) source.getTicket();
			target.setText(csBlogTicketModel.getBlogComment());
		}
		else  if (StringUtils.isNotEmpty(source.getText()) && source.getText().equals(DefaultSagiaTicketBusinessService.DEFAULT_CREATION_NOTE))
		{
			target.setText(generateQueAnsText(source));
		}

	}

	private String generateQueAnsText(CsTicketEventModel source) {
		Set<TicketAnswerModel> answers = source.getTicket().getAnswers();
		if(!Collections.isEmpty(answers))
		{
            StringBuilder text = new StringBuilder("");
			for(TicketAnswerModel answer : answers)
			{
				text.append("Q: "+answer.getTicketQuestion().getQuestion()+"\n A: "+answer.getAnswer()+"\n\n");
			}
			return text.toString();
		}
		return "";
	}
}