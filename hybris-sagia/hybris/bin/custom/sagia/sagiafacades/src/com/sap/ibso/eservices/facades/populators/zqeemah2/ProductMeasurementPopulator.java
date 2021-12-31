package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.ProductMeasurement;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ProductIdData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ProductMeasurementPopulator implements Populator<ProductIdData, ProductMeasurement> {
    @Override
    public void populate(ProductIdData productData, ProductMeasurement product) throws ConversionException {
         product.setProductId(productData.getProductid());
         product.setUnitOfMeasurement(productData.getUom());
         product.setUnitOfMeasurementText(productData.getUomtext());
    }
}
