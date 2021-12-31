package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.enums.AvailabilityTypeEnum;
import com.sap.ibso.eservices.core.enums.DayOfWeekEnum;
import com.sap.ibso.eservices.core.model.SagiaAvailabilityModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaAvailabilityDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaAvailabilityService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of Default Availability Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaAvailabilityService extends AbstractBusinessService implements SagiaAvailabilityService {

    private transient SagiaAvailabilityDAO sagiaAvailabilityDAO;


    @Override
    public List<SagiaAvailabilityModel> getAvailabilities() {
        return sagiaAvailabilityDAO.getAvailabilities();
    }

    @Override
    public SagiaAvailabilityModel getAvailabilityForTypeAndDay(AvailabilityTypeEnum type, DayOfWeekEnum day) {
        return sagiaAvailabilityDAO.getAvailabilityForTypeAndDay(type,day);
    }

    public List<String> getTimeSlotAvailabilities() {
        return getAvailabilities().stream()
                .filter(x -> SagiaCoreConstants.CALLBACK.equals(x.getType().getCode()))
                .map(x -> x.getDayOfWeek().getCode() + "-" + x.getStartTime() + "#" + x.getEndTime())
                .collect(Collectors.toList());

    }

    /**
     * checks if is Available
     * @param type type
     * @return boolean
     */
    public  boolean isAvailable(String type){
        SagiaAvailabilityModel availability = null;
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(type.equals(SagiaCoreConstants.CALLBACK)) {
            availability = getAvailabilityForTypeAndDay(AvailabilityTypeEnum.CALLBACK, getDayNameCode(c.get(Calendar.DAY_OF_WEEK)));
        }
        if(type.equals(SagiaCoreConstants.LIVECHAT)) {
            availability = getAvailabilityForTypeAndDay(AvailabilityTypeEnum.LIVECHAT, getDayNameCode(c.get(Calendar.DAY_OF_WEEK)));
        }
        if (availability != null) {
            int from = Integer.parseInt(availability.getStartTime().replace(":", ""));
            int to = Integer.parseInt(availability.getEndTime().replace(":", ""));
            int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);

            boolean toGtFrom = (to > from) && (t >= from) && (t <= to);
            boolean toLtFrom = (to < from) && (t >= from || t <= to);
            return toGtFrom || toLtFrom;
        }
        return false;
    }

    private DayOfWeekEnum getDayNameCode(int day) {
        switch (day) {
            case 1:
                return DayOfWeekEnum.SUNDAY;
            case 2:
                return DayOfWeekEnum.MONDAY;
            case 3:
                return DayOfWeekEnum.TUESDAY;
            case 4:
                return DayOfWeekEnum.WEDNESDAY;
            case 5:
                return DayOfWeekEnum.THURSDAY;
            case 6:
                return DayOfWeekEnum.FRIDAY;
            case 7:
                return DayOfWeekEnum.SATURDAY;
            default:
                break;
        }
        return null;
    }

    public SagiaAvailabilityDAO getSagiaAvailabilityDAO() {
        return sagiaAvailabilityDAO;
    }

    public void setSagiaAvailabilityDAO(SagiaAvailabilityDAO sagiaAvailabilityDAO) {
        this.sagiaAvailabilityDAO = sagiaAvailabilityDAO;
    }
}
