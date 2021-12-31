package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.data.GovtDocCheck;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovernmentDocumentsCheck;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class GovernmentDocumentsCheckPopulator implements Populator<GovtDocCheck,GovernmentDocumentsCheck> {
    public static final String CRM_BOOLEAN = "X";
    @Override
    public void populate(GovtDocCheck govtDocCheck, GovernmentDocumentsCheck governmentDocumentsCheck) throws ConversionException {
        governmentDocumentsCheck.setAutoRenewalEligible(CRM_BOOLEAN.equals(govtDocCheck.getAutorenEligible()));
        governmentDocumentsCheck.setAutoRenewalForClearanceEligible(CRM_BOOLEAN.equals(govtDocCheck.getAutorrfcEligible()));
    }
}
