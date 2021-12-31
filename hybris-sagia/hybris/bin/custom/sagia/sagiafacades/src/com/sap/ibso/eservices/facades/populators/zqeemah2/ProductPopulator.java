package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ProductData;
import de.hybris.platform.converters.Populator;
import com.sap.ibso.eservices.facades.data.zqeemah2.Product;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ProductPopulator implements Populator<ProductData, Product> {
    @Override
    public void populate(ProductData productData, Product product) throws ConversionException {
         product.setRefId(productData.getQ2refid());
         product.setSerialNumber(productData.getQ2sno());
         product.setProductId(productData.getQ2prdcode());
         product.setDescription(productData.getQ2descr());
         product.setQuantity(productData.getQ2qty());
         product.setUnitOfMeasurement(productData.getQ2uom());
         product.setUnitOfMeasurementText(productData.getQ2uomtxt());
         product.setReturnProperty(productData.getQ2return());
    }
}
