package com.investsaudi.portal.facades.contact.populator;

import com.investsaudi.portal.core.model.MizaServiceTypeForFormModel;
import com.investsaudi.portal.core.model.StrategicInvestorServiceTypeModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.MizaServiceTypeForFormData;
import de.hybris.platform.customerticketingfacades.data.StrategicInvestorServiceTypeData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class StrategicInvestorServiceTypePopulator implements Populator<StrategicInvestorServiceTypeModel, StrategicInvestorServiceTypeData> {

    @Override
    public void populate(final StrategicInvestorServiceTypeModel strategicServiceTypeForForm,
                         final StrategicInvestorServiceTypeData strategicServiceTypeForFormData) throws ConversionException {
        strategicServiceTypeForFormData.setCode(strategicServiceTypeForForm.getCode());
        strategicServiceTypeForFormData.setLabel(strategicServiceTypeForForm.getLabel());
    }
}
