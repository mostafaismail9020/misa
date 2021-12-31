package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.format.DateTimeFormatter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NIPAppointmentSetReversePopulator extends ODataReversePopulator<NationalInvestorAppointmentData> {
    @Override
    public void populate(NationalInvestorAppointmentData nationalInvestorAppointmentData, ODataModel model) throws ConversionException {
        model.put("Branch", nationalInvestorAppointmentData.getBranch());
        model.put("ServiceType1", nationalInvestorAppointmentData.getServiceType1());
        model.put("Service1", nationalInvestorAppointmentData.getService1());
        model.put("Email", nationalInvestorAppointmentData.getEmail());
        model.put("IDNumber", nationalInvestorAppointmentData.getIDNumber());
        model.put("ApptPrint", nationalInvestorAppointmentData.getApptPrint());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        model.put("DateAppt", nationalInvestorAppointmentData.getDateAppt().format(dateFormatter));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("'PT'HH'H'mm'M'ss'S'");
        model.put("TimeFrom", nationalInvestorAppointmentData.getTimeFrom().format(timeFormatter));
        model.put("TimeTo", nationalInvestorAppointmentData.getTimeTo().format(timeFormatter));
        model.put("ApptID", nationalInvestorAppointmentData.getApptID());
        model.put("ApptStatus", nationalInvestorAppointmentData.getApptStatus());
        model.put("ApptStatDesc", nationalInvestorAppointmentData.getApptStatDesc());
        model.put("Department", nationalInvestorAppointmentData.getDepartment());
        model.put("DeptDesc", nationalInvestorAppointmentData.getDeptDesc());
        model.put("BpID", nationalInvestorAppointmentData.getBpID());
        model.put("Action", nationalInvestorAppointmentData.getAction());

    }
}
