package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;
import com.sap.ibso.eservices.facades.data.license.amendment.Subsidiary;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FinancialSurveySubsidiaryPopulator implements Populator<SagiaSubsidiaryModel, Subsidiary> {


    @Override
    public void populate(SagiaSubsidiaryModel sagiaSubsidiaryModel, Subsidiary subsidiary) throws ConversionException {

        subsidiary.setSubsidiaryName(sagiaSubsidiaryModel.getName());
        subsidiary.setAction("2");
        subsidiary.setSrId(sagiaSubsidiaryModel.getPk().getLong().toString());
        subsidiary.setContribution(sagiaSubsidiaryModel.getContribution());
        subsidiary.setDataIncludedInHeadOffice(sagiaSubsidiaryModel.getIsDataIncludedInHeadOffice());
        subsidiary.setRegistrationName(sagiaSubsidiaryModel.getCommercialRegistrationNo());
        subsidiary.setUnifiedNo(sagiaSubsidiaryModel.getUnifiedNo700());
    }
}
