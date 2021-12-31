package com.sap.ibso.eservices.sagiaservices.services.reopenfacility;

import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.log4j.Logger;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.beans.factory.annotation.Required;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.APPLICATION_JSON;
import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * ReopenFacilityService
 */
public class ReopenFacilityService extends AbstractSagiaService<ReopenFacilityDetailsData>
{
    private InvestorMappingService investorMappingService;

    private static final Logger LOG = Logger.getLogger( ReopenFacilityService.class.getName() );
    /**
     * Loads facility re-open service request for a given serviceRequestId
     *
     * @param serviceRequestId serviceRequestId
     * @return ReopenFacilityDetailsData
     */
    public final ReopenFacilityDetailsData get(String serviceRequestId) {
        Map<String, String> properties = new HashMap<>();
        properties.put("SrID", serviceRequestId);
        return super.getByProperties(ReopenFacilityDetailsData.class, properties, REQUEST_PARAMETER_EXPAND, "DecExecVisitToDocAttach");
    }

    /**
     * get the facility re-open requests collection
     *
     * @return Collection of ReopenFacilityDetailsData
     */
    public final Collection<ReopenFacilityDetailsData> getCollection() {
        return super.getCollection(ReopenFacilityDetailsData.class);
    }

    /**
     * saves ReopenFacilityDetailsData Entity
     * @param reopenFacilityData reopenFacilityData
     * @return ReopenFacilityDetailsData
     * @throws ODataException exception
     */
    public ReopenFacilityDetailsData saveEntity(ReopenFacilityDetailsData reopenFacilityData) throws ODataException {
        ReopenFacilityDetailsData savedEntity = new ReopenFacilityDetailsData();
        reopenFacilityData.setBPID(investorMappingService.getEntityId());
        try {
            String response = super.save(reopenFacilityData);
            InputStream inputStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));

            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer()
                    .getEntitySet(getEntitySetName());

            final EntityProviderReadProperties readerProperties = EntityProviderReadProperties.init()
                    .mergeSemantic(false).build();
            ODataEntry odataEntry = EntityProvider.readEntry(APPLICATION_JSON, entitySet, inputStream, readerProperties);
            ODataModel model = new ODataModel(odataEntry);
            getoDataPopulator().populate(model, savedEntity);
            if("".equals(savedEntity.getSrID())){
                throw new ODataException("Service request save error!");
            }
        } catch (EntityProviderException|EdmException|IOException e) {
            LOG.error(e.getMessage(),e);
        }
        return savedEntity;
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
