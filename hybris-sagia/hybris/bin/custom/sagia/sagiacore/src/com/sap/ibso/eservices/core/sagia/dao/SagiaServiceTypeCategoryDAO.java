package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;

import java.util.List;

/**
 * Provides data access object for service type categories.
 */
public interface SagiaServiceTypeCategoryDAO extends GenericDao<ServiceTypeCategoryModel>
{
    /**
     * Finds all service type category models for overview relevant service types.
     * A service type is overview relevant if {@link ServiceTypeModel#getOverviewRelevant()} returns {@link Boolean#TRUE}.
     *
     * @return the service type category models for overview relevant service types
     */
    List<ServiceTypeCategoryModel> findForOverviewRelevantServiceTypes();
}
