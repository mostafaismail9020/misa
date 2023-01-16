package com.investsaudi.portal.facades.contact.populator;

import com.investsaudi.portal.core.model.MizaServiceTypeForFormModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.MizaServiceTypeForFormData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class MizaServiceTypeForFormPopulator implements Populator<MizaServiceTypeForFormModel, MizaServiceTypeForFormData> {

    @Override
    public void populate(final MizaServiceTypeForFormModel mizaServiceTypeForForm,
                         final MizaServiceTypeForFormData mizaServiceTypeForFormData) throws ConversionException {
        mizaServiceTypeForFormData.setCode(mizaServiceTypeForForm.getCode());
        mizaServiceTypeForFormData.setLabel(mizaServiceTypeForForm.getLabel());
    }
}
