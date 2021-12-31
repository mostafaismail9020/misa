package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.Product;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ProductPopulator implements Populator<ProductData, Product> {

    @Override
    public void populate(ProductData productData, Product product) {
        product.setBpId(productData.getBpID());
        product.setId(productData.getProductID());
        product.setDescription(productData.getProdDesc());
        product.setQuantity(productData.getQuantity());
        product.setUnit(productData.getUnit());
        product.setUnitDescription(productData.getUnitDesc());
        product.setAction(productData.getAction());
    }
}
