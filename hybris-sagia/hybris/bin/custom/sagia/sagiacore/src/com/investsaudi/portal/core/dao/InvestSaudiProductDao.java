package com.investsaudi.portal.core.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

public interface InvestSaudiProductDao extends Dao {

    /**
     * returns  List<OpportunityProductModel> for product code both catalog versions
     *
     * @param code the productCode
     * @return List<OpportunityProductModel> list class model with both catalog versions
     */
    String getProductType(final String code);

}
