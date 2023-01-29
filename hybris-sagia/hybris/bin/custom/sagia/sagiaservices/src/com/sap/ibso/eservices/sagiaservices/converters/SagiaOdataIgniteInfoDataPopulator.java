package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.data.SagiaIgniteServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaOdataIgniteInfoDataPopulator extends ODataPopulator<SagiaIgniteServiceInfoDataCRM> {
    @Override
    public void populate(ODataModel model, SagiaIgniteServiceInfoDataCRM sagiaIgniteServiceInfoDataCRM) throws ConversionException {
        super.populate(model, sagiaIgniteServiceInfoDataCRM);
        if (model.get("700Number") != null){
            sagiaIgniteServiceInfoDataCRM.setSevenHundredNumber(model.get("700Number").toString());
        }
    }
}
