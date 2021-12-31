package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.db.jdbcext.wrapper.Array;
import com.sap.ibso.eservices.core.enums.PublishStatus;
import com.sap.ibso.eservices.core.jalo.PersonShareholder;
import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.LicenseApplyDAO;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DefaultLicenseApplyDAO implements LicenseApplyDAO {

	private FlexibleSearchService flexibleSearchService;
	private EnumerationService enumerationService ;

	@Override
	public EntityInformationModel getLicenseApplyData(SagiaLicenseModel sagiaLicenseModel) {
		final String queryString = "SELECT {" + EntityInformationModel.PK + "} FROM {" + EntityInformationModel._TYPECODE +"}";
		//final HashMap<String, String> queryParams = new HashMap()<String, String>();
		//queryParams.put("",);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		SearchResult<EntityInformationModel> searchResult = flexibleSearchService.search(query);
		EntityInformationModel entityInformationModel = (EntityInformationModel) searchResult.getResult();
		return entityInformationModel;
	}

	@Override
	public SagiaLicenseModel getLicenseDataByCustomer(CustomerModel customer) {

		final String queryString = "SELECT {" + SagiaLicenseModel.PK + "} FROM {" + SagiaLicenseModel._TYPECODE +" as sl JOIN "+ CustomerModel._TYPECODE +" as c on {c.pk} = {sl.customer} } where {c.uid} = ?customerCode";
		final HashMap<String, String> queryParams = new HashMap<>();
		queryParams.put("customerCode", customer.getUid());
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameters(queryParams);
		SearchResult<SagiaLicenseModel> searchResult = flexibleSearchService.search(query);
		SagiaLicenseModel SagiaLicenseModel = (SagiaLicenseModel) searchResult.getResult().get(0);
		return SagiaLicenseModel;
	}

	@Override
	public ShareHolderModel getShareHolderData(SagiaLicenseModel sagiaLicenseModel) {
		final String queryString = "SELECT {" + ShareHolderModel.PK + "} FROM {" + ShareHolderModel._TYPECODE +"}";
		//final HashMap<String, String> queryParams = new HashMap()<String, String>();
		//queryParams.put("",);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		SearchResult<EntityInformationModel> searchResult = flexibleSearchService.search(query);
		ShareHolderModel shareHolderModel = (ShareHolderModel) searchResult.getResult();
		return shareHolderModel;
	}

	@Override
	public ContactPersonModel getContactPersonData(SagiaLicenseModel sagiaLicenseModel) {
		final String queryString = "SELECT {" + ContactPersonModel.PK + "} FROM {" + ContactPersonModel._TYPECODE +"}";
		//final HashMap<String, String> queryParams = new HashMap()<String, String>();
		//queryParams.put("",);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		SearchResult<EntityInformationModel> searchResult = flexibleSearchService.search(query);
		ContactPersonModel contactPersonModel = (ContactPersonModel) searchResult.getResult();
		return contactPersonModel;
	}
	
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public List<SagiaLicenseModel> getPendingLicenses() {
		
		final String queryString = "SELECT {" + SagiaLicenseModel.PK + "} " +
				"FROM {" + SagiaLicenseModel._TYPECODE +"} "; //+
			//	"WHERE {"+ SagiaLicenseModel.PUBLISHSTATUS+"} = ?status or {"+ SagiaLicenseModel.PUBLISHSTATUS+"} = ?status2";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameter("status",PublishStatus.PENDING);
		query.addQueryParameter("status2",PublishStatus.FAILED);
		
		SearchResult<SagiaLicenseModel> searchResult = flexibleSearchService.search(query);
		List<SagiaLicenseModel> listSagiaLicense = searchResult.getResult();

		return listSagiaLicense ;
	}

	@Override
	public ShareHolderModel getShareHolder(String code) {
		final String queryString = "SELECT {" + ShareHolderModel.PK + "} " +
				"FROM {" + ShareHolderModel._TYPECODE +"} " +
				"WHERE {"+ ShareHolderModel.CODE+"} = ?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameter("code",code);

		SearchResult<ShareHolderModel> searchResult = flexibleSearchService.search(query);
		List<ShareHolderModel> listShareHolders = searchResult.getResult();

		if(listShareHolders!= null && listShareHolders.size() > 0){
			return listShareHolders.get(0);
		}
		return null;
	}
}
