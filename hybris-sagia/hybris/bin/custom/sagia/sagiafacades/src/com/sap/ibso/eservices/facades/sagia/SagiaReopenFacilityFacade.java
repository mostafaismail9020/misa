package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddress;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.util.Collection;

/**
 * SagiaReopenFacilityFacade
 */
public interface SagiaReopenFacilityFacade {
    /**
     * loads DecisionExecutionVisit
     * @param srId srId
     * @return ReopenFacility
     */
    ReopenFacility loadDecisionExecutionVisit(String srId);

    /**
     * loads DecisionExecutionVisit Collection
     * @return Collection of ReopenFacility
     */
    Collection<ReopenFacility> loadDecisionExecutionVisitCollection();

    /**
     * saves DecisionExecutionVisit
     * @param reopenFacility reopenFacility
     * @return String
     * @throws ODataException exception
     */
    String saveDecisionExecutionVisit(ReopenFacility reopenFacility) throws ODataException;

    /**
     * loads ReopenHistory
     * @return ReopenFacilityAddress
     */
    ReopenFacilityAddress loadReopenHistory();
}
