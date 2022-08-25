package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.dao.NafathDAO;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultNafathDAO implements NafathDAO {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<NafathLoginModel> getOldLoginRecords(Integer daysOld) {

        long tillDate = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(daysOld);
        final StringBuilder query = new StringBuilder();

        query.append(" SELECT ").append(NafathLoginModel.PK).append(" FROM ").append(NafathLoginModel._TYPECODE)
                .append("WHERE ").append(NafathLoginModel.MODIFIEDTIME).append(" < ?tillDate");

        final HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("tillDate", String.valueOf(tillDate));

        final SearchResult<NafathLoginModel> result = getFlexibleSearchService().search(query.toString(), queryParams);
        return result.getResult();

    }

    @Override
    public NafathLoginModel getLoginFromTransactionId(String transactionID) {
        final StringBuilder query = new StringBuilder();

        query.append(" SELECT ").append(NafathLoginModel.PK).append(" FROM ").append(NafathLoginModel._TYPECODE)
                .append("WHERE ").append(NafathLoginModel.TRANSACTIONID).append(" = ?transactionId");

        final HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("transactionId", transactionID);

        final SearchResult<NafathLoginModel> result = getFlexibleSearchService().search(query.toString(), queryParams);
        return result.getResult().get(0);
    }

    @Override
    public SagiaLicenseModel getLicense(String license) {
        try{
            final StringBuilder query = new StringBuilder();

            query.append(" SELECT ").append(SagiaLicenseModel.PK).append(" FROM ").append(SagiaLicenseModel._TYPECODE)
                    .append("WHERE ").append(SagiaLicenseModel.CODE).append(" = ?license");

            final HashMap<String, String> queryParams = new HashMap<>();
            queryParams.put("license", license);

            final SearchResult<SagiaLicenseModel> result = getFlexibleSearchService().search(query.toString(), queryParams);
            return result.getResult().get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
