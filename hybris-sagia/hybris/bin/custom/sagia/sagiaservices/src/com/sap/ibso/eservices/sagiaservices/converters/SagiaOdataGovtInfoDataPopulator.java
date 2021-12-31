package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaOdataGovtInfoDataPopulator extends ODataPopulator<SagiaGovtServiceInfoDataCRM> {
    @Override
    public void populate(ODataModel model, SagiaGovtServiceInfoDataCRM sagiaGovtServiceInfoDataCRM) throws ConversionException {
        super.populate(model, sagiaGovtServiceInfoDataCRM);
        if (model.get("700Number") != null){
            sagiaGovtServiceInfoDataCRM.setSevenHundredNumber(model.get("700Number").toString());
        }
    }
}
