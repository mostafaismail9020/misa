package com.sap.ibso.eservices.facades.populators.tickets;

import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.i18n.I18NMessageTranslatorService;

import de.hybris.platform.converters.Populator;

public class TicketsDataPopulator implements Populator<ComplaintsAndEnquiryHdrsData, TicketData> {

	private SagiaFormatProvider sagiaFormatProvider;
	private I18NMessageTranslatorService i18NMessageTranslatorService;

	@Override
	public void populate(ComplaintsAndEnquiryHdrsData complaint, TicketData ticketData) {
		ticketData.setLastUpdate(complaint.getSrCrDate());
		ticketData.setTicketNumber(complaint.getSrID());
		ticketData.setEnquiryType(complaint.getTransType());
		ticketData.setStatus(complaint.getSrStDesc());
		ticketData.setStatusKey(getStatusKeyBy(complaint.getSrStDesc()));
		SagiaDateData lastUpdateData = sagiaFormatProvider.getLocalizedDateData(complaint.getSrCrDate());
		ticketData.setLastUpdateData(lastUpdateData);
	}

	private String getStatusKeyBy(String currentStatus) {
		String openStatusKey = "Open";
		String inProcessStatusKey = "In Process";
		String closedStatusKey = "Closed";
		String resolvedStatusKey = "Resolved";

		if (StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("tickets.status.open"))) {
			return openStatusKey;
		}
		if (StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("tickets.status.inprocess"))) {
			return inProcessStatusKey;
		}
		if (StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("tickets.status.closed"))) {
			return closedStatusKey;
		}
		if (StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("tickets.status.resolved"))) {
			return resolvedStatusKey;
		}
		return currentStatus;

	}

	private String getMessageValue(String messageKey) {
		return i18NMessageTranslatorService.getLocalizedMessageValue(messageKey);
	}

	public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
	}

	public void setI18NMessageTranslatorService(I18NMessageTranslatorService i18NMessageTranslatorService) {
		this.i18NMessageTranslatorService = i18NMessageTranslatorService;
	}
}