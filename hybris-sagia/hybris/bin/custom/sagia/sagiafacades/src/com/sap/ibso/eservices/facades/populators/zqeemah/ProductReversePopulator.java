package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.Product;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ProductData;
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
        productData.setInvestorid(product.getInvestorId());
        productData.setSNo(product.getSNo());
        productData.setPrdCode(product.getProductCode());
        productData.setDescr(product.getDescription());
        productData.setQty(product.getQuantity());
        productData.setUom(product.getUom());
        productData.setUomTxt(product.getUomText());
        productData.setReturnProperty(product.getReturnProperty());
    }
}
