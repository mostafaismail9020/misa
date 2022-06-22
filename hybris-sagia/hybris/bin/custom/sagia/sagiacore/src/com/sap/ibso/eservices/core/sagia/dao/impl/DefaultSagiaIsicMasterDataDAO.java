package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaIsicMasterDataDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaIsicMasterDataDAO extends DefaultGenericDao<IsicMasterModel> implements SagiaIsicMasterDataDAO {

    public DefaultSagiaIsicMasterDataDAO(String typecode) {
        super(typecode);
    }

    @Override
    public Collection<IsicMasterModel> getAllIsicMasterByLicenseType(String licenseType) {

        validateParameterNotNull(licenseType, "LicenseType must not be null!");

        final Map parameters = new HashMap();
        parameters.put(IsicMasterModel.LICENSETYPE, licenseType);
        parameters.put(IsicMasterModel.ACTIVE, Boolean.TRUE);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return parameterList;
        } else {
            return null;
        }

    }

    @Override
    public Collection<IsicMasterModel> getAllIsicMasterForAllLicenseType() {

        final Map parameters = new HashMap();
        parameters.put(IsicMasterModel.ACTIVE, Boolean.TRUE);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return parameterList;
        } else {
            return null;
        }

    }

    @Override
    public List<IsicTextsModel> getTextsDataFromType(String type, List<String> parentIds) {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = ?type");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in (?parentIds) ");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("type", type);
        parameters.put("parentIds", parentIds);

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(), parameters);

        return result.getResult();
    }

	@Override
	public Collection<IsicMasterModel> getAllIsicMaster(List<String> isicActivityCodes) {
	    final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicMasterModel.PK + " }  ");
        query.append(" FROM {" + IsicMasterModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicMasterModel.ACTIVE + "} = ?active ");
        query.append(" AND {m: " + IsicMasterModel.ISICACTIVITY + "} in (?isicActivityCodes) ");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("active", Boolean.TRUE);
        parameters.put("isicActivityCodes", isicActivityCodes);

        final SearchResult<IsicMasterModel> result = getFlexibleSearchService().search(query.toString(), parameters);
        return result.getResult();
	}

	@Override
	public List<IsicTextsModel> getActiveISICSection() {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'SECTION'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICSECTION} from {IsicMaster} WHERE {ACTIVE} = 1 GROUP BY {ISICSECTION} }})");

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString());

        return result.getResult();
	}


	@Override
	public List<IsicTextsModel> getActiveISICDivision(String sectionID) {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'DIVISION'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICDIVISION} from {IsicMaster} WHERE {ACTIVE} = 1 AND {ISICSECTION} = ?sectionID GROUP BY {ISICDIVISION}  }})");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("sectionID", sectionID);

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        return result.getResult();
	}


    @Override
    public IsicTextsModel getIsicTextsByCode(String isicCode, String isicType) {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = ?isicType ");
        query.append(" AND {m: " + IsicTextsModel.CODE + "}  =  ?isicCode  ");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("isicCode", isicCode);
        parameters.put("isicType", isicType);

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        if(CollectionUtils.isNotEmpty(result.getResult())){
            return result.getResult().get(0);
        } else {
            return null;
        }
    }




    @Override
    public List<IsicTextsModel> getActiveISICGroup() {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'GROUP'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICGROUP} from {IsicMaster} WHERE {ACTIVE} = 1 GROUP BY {ISICGROUP}  }})");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        return result.getResult();
    }

    @Override
    public List<IsicTextsModel> getActiveISICClass() {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'CLASS'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICCLASS} from {IsicMaster} WHERE {ACTIVE} = 1 GROUP BY {ISICCLASS}  }})");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        return result.getResult();
    }

    @Override
    public List<IsicTextsModel> getActiveISICGroupByDivisionID(String divisionID) {
        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'GROUP'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICGROUP} from {IsicMaster} WHERE {ACTIVE} = 1 AND {ISICDIVISION} = ?divisionID GROUP BY {ISICGROUP}  }})");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("divisionID", divisionID);

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        return result.getResult();
    }


    @Override
    public List<IsicTextsModel> getActiveISICClassByGroupID(String groupID) {
        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {m: " + IsicTextsModel.PK + " }  ");
        query.append(" FROM {" + IsicTextsModel._TYPECODE + " as m }");
        query.append(" WHERE {m: " + IsicTextsModel.ISICCOLUMNTYPE + " } = 'CLASS'");
        query.append(" AND {m: " + IsicTextsModel.CODE + "} in ({{ select {ISICCLASS} from {IsicMaster} WHERE {ACTIVE} = 1 AND {ISICGROUP} = ?groupID GROUP BY {ISICCLASS}  }})");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("groupID", groupID);

        final SearchResult<IsicTextsModel> result = getFlexibleSearchService().search(query.toString(),parameters);

        return result.getResult();
    }

}
