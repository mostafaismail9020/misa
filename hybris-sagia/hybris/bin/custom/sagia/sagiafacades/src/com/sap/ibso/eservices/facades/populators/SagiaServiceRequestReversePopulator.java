package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.SagiaServiceRequestFormData;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.converters.Populator;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaServiceRequestReversePopulator implements Populator<SagiaServiceRequestFormData, ServiceRequestModel> {
	
	private I18NService i18nService;

	/**
	 * @return the i18nService
	 */
	public I18NService getI18nService() {
		return i18nService;
	}

	/**
	 * @param i18nService the i18nService to set
	 */
	public void setI18nService(I18NService i18nService) {
		this.i18nService = i18nService;
	}

	@Override
	public void populate(SagiaServiceRequestFormData source, ServiceRequestModel target) throws ConversionException {

		if (null != source.getCode()) {
			target.setId(source.getCode());
		}
		if (null != source.getSubject()) {
			target.setSubject(source.getSubject(), getI18nService().getCurrentLocale());
		}
		if (null != source.getDescription()) {
			target.setDescription(source.getDescription(), getI18nService().getCurrentLocale());
		}
		if (null != source.getIncidentCategory()) {
			target.setIncidentCategory(source.getIncidentCategory());
		}
		if (null != source.getServiceCategory()) {
			target.setServiceCategory(source.getServiceCategory());
		}
		if (null != source.getPriority()) {
			target.setPriority(source.getPriority());
		}
	}
}
