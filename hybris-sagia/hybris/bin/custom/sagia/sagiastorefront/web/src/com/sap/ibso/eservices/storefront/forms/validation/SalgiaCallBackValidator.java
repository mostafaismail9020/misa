package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.TimeSlot;
import java.text.SimpleDateFormat;
import  java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("salgiaCallBackValidator")
public class SalgiaCallBackValidator implements Validator {

    private static final String DATE = "dateStart";
    private static final String TIME_SLOT = "timeStart";
    private static final String COUNTRY_CODE = "mobileCountryCode";
    private static final String MOBILE_NUMBER = "mobileNumber";
    final private static String PATTERN="yyyy-MM-dd HH:mm";
    final private static String EMPTY = " ";

    @Override
    public boolean supports(final Class<?> aClass) {
        return TimeSlot.class.equals(aClass);
    }

    @Override
    public void validate(final Object object, final Errors errors) {
        final TimeSlot timeSlot = (TimeSlot) object;
        final String countryCode = timeSlot.getMobileCountryCode();
        final String mobileNumber = timeSlot.getMobileNumber();
        final String dateStart = timeSlot.getDateStart();
        final String timeStart = timeSlot.getTimeStart();
        final String scheduleDatetime = timeSlot.getDateStart() + EMPTY + timeSlot.getTimeStart();

        if (StringUtils.isEmpty(dateStart)) {
            errors.rejectValue(DATE, "profile.countryCode.invalid");
        }
        if (StringUtils.isEmpty(timeStart)) {
            errors.rejectValue(TIME_SLOT, "profile.countryCode.invalid");
        }

        if (StringUtils.isEmpty(countryCode)) {
            errors.rejectValue(COUNTRY_CODE, "profile.countryCode.invalid");
        }

        if (StringUtils.isEmpty(mobileNumber)) {
            errors.rejectValue(MOBILE_NUMBER, "profile.mobileNumber.invalid");
        }

        try{
            if (new SimpleDateFormat(PATTERN).parse(scheduleDatetime).before(new Date())){
                errors.rejectValue(DATE, "profile.countryCode.invalid");
                errors.rejectValue(TIME_SLOT, "profile.countryCode.invalid");
            }
        } catch (ParseException dateException) {
            errors.rejectValue(DATE, "profile.countryCode.invalid");
            errors.rejectValue(TIME_SLOT, "profile.countryCode.invalid");
        }
    }
}
