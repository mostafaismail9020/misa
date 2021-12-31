package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.enums.AvailabilityTypeEnum;
import com.sap.ibso.eservices.core.enums.DayOfWeekEnum;
import com.sap.ibso.eservices.core.model.SagiaAvailabilityModel;

import java.util.List;

public interface SagiaAvailabilityDAO {

    List<SagiaAvailabilityModel> getAvailabilities();

    SagiaAvailabilityModel getAvailabilityForTypeAndDay(AvailabilityTypeEnum type, DayOfWeekEnum day);
}
