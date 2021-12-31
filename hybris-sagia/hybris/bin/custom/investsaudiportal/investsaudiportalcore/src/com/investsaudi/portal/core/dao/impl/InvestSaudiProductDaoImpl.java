package com.investsaudi.portal.core.dao.impl;

import com.investsaudi.portal.core.dao.InvestSaudiProductDao;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.Collections;
import java.util.Optional;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class InvestSaudiProductDaoImpl extends DefaultGenericDao<ProductModel> implements InvestSaudiProductDao
{
    public InvestSaudiProductDaoImpl(String typecode) {
        super(typecode);
    }

    @Override
    public String getProductType(String code) {
        validateParameterNotNull(code, "Product code must not be null!");

        final Optional<ProductModel> productModel = find(Collections.singletonMap(ProductModel.CODE, (Object) code)).stream().findFirst();

        if(productModel.isPresent()){
            if(productModel.get() instanceof OpportunityProductModel ){
                return OpportunityProductModel._TYPECODE;
            }
            else{
                return SuccessStoryProductModel._TYPECODE;
            }
        }
        return null;
    }
}
