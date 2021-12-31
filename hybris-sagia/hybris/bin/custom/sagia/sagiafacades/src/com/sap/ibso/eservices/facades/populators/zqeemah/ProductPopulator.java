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
public class ProductPopulator implements Populator<ProductData, Product> {
    @Override
    public void populate(ProductData productData, Product product) throws ConversionException {
         product.setInvestorId(productData.getInvestorid());
         product.setSNo(productData.getSNo());
         product.setProductCode(productData.getPrdCode());
         product.setDescription(productData.getDescr());
         product.setQuantity(productData.getQty());
         product.setUom(productData.getUom());
         product.setUomText(productData.getUomTxt());
         product.setReturnProperty(productData.getReturnProperty());
    }
}
