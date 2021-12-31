package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.account.RemovePopupALRResponse;
import com.sap.ibso.eservices.sagiaservices.data.RemovePopupALR;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RemovePopupALRPopulator implements Populator<RemovePopupALR,RemovePopupALRResponse> {
    public static final String CRM_BOOLEAN = "X";

    @Override
    public void populate(RemovePopupALR removePopupALR, RemovePopupALRResponse removePopupALRResponse) throws ConversionException {
        removePopupALRResponse.setPartner(removePopupALR.getPartner());
        removePopupALRResponse.setErrorMessage(removePopupALR.getErrorMsg());
        removePopupALRResponse.setSuccess(CRM_BOOLEAN.equals(removePopupALR.getSucess()));
    }
}
