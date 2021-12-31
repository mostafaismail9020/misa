package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.Product;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ProductReversePopulator implements Populator<Product, ProductData> {

    @Override
    public void populate(Product product, ProductData productData) {
        productData.setBpID(product.getBpId());
        productData.setAction(product.getAction());
        productData.setProductID(product.getId());
        productData.setProdDesc(product.getDescription());
        productData.setQuantity(product.getQuantity());
        productData.setUnit(product.getUnit());
    }
}
