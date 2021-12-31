package com.sap.ibso.eservices.sagiaservices.services.reopenfacility;

import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddressData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * ReopenFacilityHistoryService
 */
public class ReopenFacilityHistoryService extends AbstractSagiaService<ReopenFacilityAddressData>
{
    private InvestorMappingService investorMappingService;

    private static final String BP_ID = "Bpid";
    private static final String EXPAND_DATA = "BPAddrToDepndtSrvReq";

    /**
     * Loads facility re-open service request history for a given bpId
     *
     * @return ReopenFacilityAddressData
     */
    public final ReopenFacilityAddressData get() {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(BP_ID, investorMappingService.getEntityId());
        return super.getByProperties(ReopenFacilityAddressData.class, parameters, REQUEST_PARAMETER_EXPAND, EXPAND_DATA);
    }

    /**
     * get the facility re-open requests collection
     * @param bpId bpId
     * @return Collection of ReopenFacilityAddressData
     */
    public final Collection<ReopenFacilityAddressData> getCollection(String bpId) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(BP_ID, bpId);
        return super.getCollectionByProperties(ReopenFacilityAddressData.class, parameters, REQUEST_PARAMETER_EXPAND, EXPAND_DATA);
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
