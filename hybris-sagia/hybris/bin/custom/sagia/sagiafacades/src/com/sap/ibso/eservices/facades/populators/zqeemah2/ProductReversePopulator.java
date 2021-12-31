package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.Product;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ProductReversePopulator implements Populator<Product, ProductData> {
    @Override
    public void populate(Product product, ProductData productData) throws ConversionException {
         productData.setQ2refid(product.getRefId());
         productData.setQ2sno(product.getSerialNumber());
         productData.setQ2prdcode(product.getProductId());
         productData.setQ2descr(product.getDescription());
         productData.setQ2qty(product.getQuantity());
         productData.setQ2uom(product.getUnitOfMeasurement());
         productData.setQ2uomtxt(product.getUnitOfMeasurementText());
         productData.setQ2return(product.getReturnProperty());
    }
}
