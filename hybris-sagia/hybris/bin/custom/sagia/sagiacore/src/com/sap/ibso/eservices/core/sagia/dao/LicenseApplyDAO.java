package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import de.hybris.platform.core.model.user.CustomerModel;

public interface LicenseApplyDAO {

	EntityInformationModel getLicenseApplyData(SagiaLicenseModel sagiaLicenseModel);

	ShareHolderModel getShareHolderData(SagiaLicenseModel sagiaLicenseModel);

	ContactPersonModel getContactPersonData(SagiaLicenseModel sagiaLicenseModel);

	SagiaLicenseModel getLicenseDataByCustomer(CustomerModel customer);


	ShareHolderModel getShareHolder(String code);

	List<SagiaLicenseModel> getPendingLicenses();
	
}
