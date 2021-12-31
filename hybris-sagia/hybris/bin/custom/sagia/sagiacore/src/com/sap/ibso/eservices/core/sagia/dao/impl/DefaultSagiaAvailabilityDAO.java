package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.enums.AvailabilityTypeEnum;
import com.sap.ibso.eservices.core.enums.DayOfWeekEnum;
import com.sap.ibso.eservices.core.model.SagiaAvailabilityModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaAvailabilityDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaAvailabilityDAO extends DefaultGenericDao<SagiaAvailabilityModel> implements SagiaAvailabilityDAO {

    private static final Logger LOGGER = Logger.getLogger(DefaultSagiaAvailabilityDAO.class);

    public DefaultSagiaAvailabilityDAO(String typecode) {
        super(typecode);
    }

    public List<SagiaAvailabilityModel> getAvailabilities() {
        return find();
    }

    public SagiaAvailabilityModel getAvailabilityForTypeAndDay(AvailabilityTypeEnum type, DayOfWeekEnum day) {
        validateParameterNotNull(type, "SagiaAvailability type must not be null!");
        validateParameterNotNull(day, "SagiaAvailability dayOfWeek must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaAvailabilityModel.TYPE, type);
        parameters.put(SagiaAvailabilityModel.DAYOFWEEK, day);
        List parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            try {
                return (SagiaAvailabilityModel) parameterList.get(0);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(),ex);
                return null;
            }
        } else {
            return null;
        }
    }
}
