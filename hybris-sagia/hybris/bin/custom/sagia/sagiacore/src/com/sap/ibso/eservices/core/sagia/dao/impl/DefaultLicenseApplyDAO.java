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
<<<<<<< HEAD
import org.apache.commons.collections4.CollectionUtils;
=======
import de.hybris.platform.catalog.CatalogVersionService;
import javax.annotation.Resource;
import java.util.Map;
import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;
>>>>>>> refs/heads/feature/ui-revamp-resolution-merge

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.CatalogVersionService;

import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;

public class DefaultLicenseApplyDAO implements LicenseApplyDAO {

	private FlexibleSearchService flexibleSearchService;
	private EnumerationService enumerationService ;
		
	@Resource
    private CatalogVersionService catalogVersionService;

	
	 private static final String CATALOG_ID = "sagiaContentCatalog";
	 private static final String ONLINE = "Online";
	
	private static final String QUERY_DOWNLOAD_MEDIA_DETAILS = "SELECT {r:pk} FROM {SagiaCMSParagraphMediaComponent AS r} "
    		+ "WHERE {r.uid}=?uid AND {r.catalogVersion} = ?catalogVersion" ;
      
	

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
	public List<RhqActivitiesModel> getCorporateActivities() {
		final String queryString = "SELECT {" + RhqActivitiesModel.PK + "} FROM {" + RhqActivitiesModel._TYPECODE +"} where {isCorporate} = ?isCorporate";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameter("isCorporate",true);
		SearchResult<RhqActivitiesModel> searchResult = flexibleSearchService.search(query);
		List<RhqActivitiesModel> rhqActivitiesModel = searchResult.getResult();
		return rhqActivitiesModel;
	}

	@Override
	public List<RhqActivitiesModel> getStrategicActivities() {
		final String queryString = "SELECT {" + RhqActivitiesModel.PK + "} FROM {" + RhqActivitiesModel._TYPECODE +"} where {isStrategic} = ?isStrategic";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameter("isStrategic",true);
		SearchResult<RhqActivitiesModel> searchResult = flexibleSearchService.search(query);
		List<RhqActivitiesModel> rhqActivitiesModel = searchResult.getResult();
		return rhqActivitiesModel;
	}

	@Override
	public List<RhqActivitiesModel> getManagementActivities() {
		final String queryString = "SELECT {" + RhqActivitiesModel.PK + "} FROM {" + RhqActivitiesModel._TYPECODE +"} where {isManagement} = ?isManagement";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.addQueryParameter("isManagement",true);
		SearchResult<RhqActivitiesModel> searchResult = flexibleSearchService.search(query);
		List<RhqActivitiesModel> rhqActivitiesModel = searchResult.getResult();
		return rhqActivitiesModel;
	}

	@Override
	public RhqActivitiesModel getActivityDetailsForCode(String code) {
		validateParameterNotNull(code, "Sagia Activity code must not be null!");

		final StringBuilder query = new StringBuilder();

		query.append(" SELECT {PK} FROM ");
		query.append(" { ");
		query.append("  RhqActivities");
		query.append(" } ");
		query.append(" WHERE {id} = ?code ");


		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", code);

		final SearchResult<RhqActivitiesModel> result = getFlexibleSearchService().search(query.toString(), parameters);
		if(CollectionUtils.isNotEmpty(result.getResult())){
			return (RhqActivitiesModel) result.getResult().get(0);
		} else {
			return null;
		}
	}

	@Override
	public RhqActivitiesModel getActivityDetailsCodeForName(String details) {
		validateParameterNotNull(details, "Sagia Activity code must not be null!");

		final StringBuilder query = new StringBuilder();

		query.append(" SELECT {PK} FROM ");
		query.append(" { ");
		query.append("  RhqActivities");
		query.append(" } ");
		query.append(" WHERE {details} = ?details ");


		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("details", details);

		final SearchResult<RhqActivitiesModel> result = getFlexibleSearchService().search(query.toString(), parameters);
		if(CollectionUtils.isNotEmpty(result.getResult())){
			return (RhqActivitiesModel) result.getResult().get(0);
		} else {
			return null;
		}
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
	
	@Override
    public SagiaCMSParagraphMediaComponentModel getParagraphLicenseMedia(String uid) {
    	
    	final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_DOWNLOAD_MEDIA_DETAILS, params);        
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        
        final SearchResult<SagiaCMSParagraphMediaComponentModel> resultList = flexibleSearchService.search(searchQuery);

        return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
    }
			
}
