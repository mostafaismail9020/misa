package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of the Sagia Country Service.
 * Service for the Countries.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaCountryService extends AbstractBusinessService implements SagiaCountryService {

    private transient SagiaCountryDAO sagiaCountryDAO;

    public SagiaCountryDAO getSagiaCountryDAO() {
        return sagiaCountryDAO;
    }

    public void setSagiaCountryDAO(SagiaCountryDAO sagiaCountryDAO) {
        this.sagiaCountryDAO = sagiaCountryDAO;
    }

    @Override
    public List<SagiaCountryModel> getCountries() {
        return getSagiaCountryDAO().getAllCountries();
    }

    @Override
    public SagiaCountryModel getCountryForCode(String code) {
        return sagiaCountryDAO.getCountryForCode(code);
    }
    
    @Override
    public List<SagiaCountryModel> getShareHolderCheckCountries() {
        return getSagiaCountryDAO().getShareHolderCheckCountries();
    }

	@Override
	public SagiaCountryModel getCountryForPhonePrefix(String phonePrefix) {
		return sagiaCountryDAO.getCountryForPhonePrefix(phonePrefix);
	}
}
