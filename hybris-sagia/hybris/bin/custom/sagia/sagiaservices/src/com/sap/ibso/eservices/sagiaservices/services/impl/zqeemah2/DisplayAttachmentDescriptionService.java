package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DisplayAttachmentDescriptionData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * DisplayAttachmentDescriptionService
 */
public class DisplayAttachmentDescriptionService extends AbstractSagiaService<DisplayAttachmentDescriptionData> {

    private InvestorMappingService investorMappingService;

    /**
     * retrieves AttachmentsDescription
     * @return Collection of DisplayAttachmentDescriptionData
     * @throws SagiaODataException exception
     */
    public Collection<DisplayAttachmentDescriptionData> getAttachmentsDescription() throws SagiaODataException {
        QueryOptionsBuilder queryOptionsBuilder = new QueryOptionsBuilder()
                .filter("Refid eq '" + investorMappingService.getApplicantReferenceId(null) + "'");
        return super.getCollection(DisplayAttachmentDescriptionData.class, queryOptionsBuilder.build());
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
