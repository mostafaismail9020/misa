package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.dao.NafathDAO;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.time.TimeService;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DefaultNafathDAO implements NafathDAO {

    private FlexibleSearchService flexibleSearchService;
    private TimeService timeService;

    @Override
    public List<NafathLoginModel> getOldLoginRecords(Integer daysOld) {
        Date tillDate = new DateTime(getTimeService().getCurrentTime()).minusDays(daysOld).toDate();

        final StringBuilder query = new StringBuilder();
        query.append(" SELECT {").append(NafathLoginModel.PK).append("} FROM {").append(NafathLoginModel._TYPECODE)
                .append("} WHERE {").append(NafathLoginModel.MODIFIEDTIME).append("} <= ?tillDate");

        final HashMap<String, Date> queryParams = new HashMap<>();
        queryParams.put("tillDate", tillDate);

        final SearchResult<NafathLoginModel> result = getFlexibleSearchService().search(query.toString(), queryParams);
        return result.getResult();

    }

    @Override
    public NafathLoginModel getLoginFromTransactionId(String transactionID, String nationalId, String randomText) {

        NafathLoginModel nafathLoginModel = new NafathLoginModel();
        nafathLoginModel.setTransactionId(transactionID);
        nafathLoginModel.setNationalId(nationalId);
        nafathLoginModel.setRandom(randomText);

        List<NafathLoginModel> modelsByExample = getFlexibleSearchService().getModelsByExample(nafathLoginModel);
        return modelsByExample != null ? modelsByExample.get(0) : null;
    }

    @Override
    public SagiaLicenseModel getLicense(String license) {
        try {
            SagiaLicenseModel sagiaLicenseModel = new SagiaLicenseModel();
            sagiaLicenseModel.setCode(license);
            final List<SagiaLicenseModel> licenseModelList = getFlexibleSearchService().getModelsByExample(sagiaLicenseModel);
            return licenseModelList != null ? licenseModelList.get(0) : null;
        } catch (Exception e) {
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

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }
}
