package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ProductData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * ProductService
 */
public class ProductService extends AbstractSagiaService<ProductData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves Products
     * @param productData productData
     * @return String
     */
    public String saveProducts(Collection<ProductData> productData){
        String refId = investorMappingService.getApplicantReferenceId(null);
        for(ProductData shareholderData : productData){
            shareholderData.setQ2refid(refId);
            super.save(shareholderData);
        }
        return null;
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService)
    {
        this.investorMappingService = investorMappingService;
    }
}
