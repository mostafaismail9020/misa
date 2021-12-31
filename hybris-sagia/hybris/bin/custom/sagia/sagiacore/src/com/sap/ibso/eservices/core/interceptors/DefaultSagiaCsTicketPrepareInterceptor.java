package com.sap.ibso.eservices.core.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.ticket.interceptors.CsTicketPrepareInterceptor;
import de.hybris.platform.ticket.model.CsTicketModel;

public class DefaultSagiaCsTicketPrepareInterceptor extends CsTicketPrepareInterceptor {

	private String SERVICE_REQUEST_PREFIX = "8"; 
	private String OPPORTUNITY_PREFIX = "3"; 
	
	@Override
	public void onPrepare(Object model, InterceptorContext ctx) throws InterceptorException {
		
		if (model instanceof CsTicketModel) {
			CsTicketModel ticket = (CsTicketModel)model;
			if (ticket.getTicketID() == null) {
			super.onPrepare(model, ctx);
			
			if (ticket.getConfiguration() != null) {
			if(ticket.getConfiguration().isIsServiceRequest()) {			
				ticket.setTicketID(SERVICE_REQUEST_PREFIX+ticket.getTicketID());
			}else {
				ticket.setTicketID(OPPORTUNITY_PREFIX+ticket.getTicketID());
			}
			}
			
			}else if (ticket.getState() == null){
				super.onPrepare(model, ctx);
			}
		}
		
	}

	
	
	
}
