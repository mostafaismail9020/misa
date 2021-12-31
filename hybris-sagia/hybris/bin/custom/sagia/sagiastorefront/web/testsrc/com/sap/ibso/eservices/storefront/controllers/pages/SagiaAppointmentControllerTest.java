package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.AppointmentListItem;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.facades.sagia.SagiaAppointmentFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaSpecialServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaAppointmentController
 */
@UnitTest
public class SagiaAppointmentControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    private SagiaAppointmentFacade sagiaAppointmentFacade;

    @Mock
    private Appointment appointment;

    @Mock
    private AppointmentData appointmentData;

    @Mock
    private SagiaSpecialServiceFacade sagiaSpecialServiceFacade;

    @Spy
    @InjectMocks
    private SagiaAppointmentController sagiaAppointmentController;

    @Mock
    private Collection<ListItem> departments;

    @Mock
    private Collection<ListItem> branches;

    @Mock
    private Collection<ListItem> ministries;

    @Mock
    private Collection<ListItem> serviceTypes;


    @Mock
    private Collection<ListItem> services;

    @Mock
    private SessionService sessionService;


    @Mock
    private HttpSessionRequestCache httpSessionRequestCache;

    @Mock
    private AppointmentListItem appointmentListItem;

    @Mock
    private ListItem listItem;

    @Mock
    private SagiaPaginationFacade sagiaPaginationFacade;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaAppointmentControllerTest() {
        sagiaAppointmentController = new SagiaAppointmentController();
        }

    /**
     * Initialize mocks
     */
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        setupCmsContentPage();
    }

    @Test
    public void editAppointmentTest() {
        Collection<Appointment> appointments = new ArrayList<>();
        given(sagiaAppointmentFacade.getAppointments()).willReturn(appointments);
        try {
            assertNull(sagiaAppointmentController.editAppointment(Optional.of("id"), model));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
   public void saveAppointmentTest(){
       doNothing().when(sagiaAppointmentFacade).saveAppointment(appointment);
       try {
           assertEquals("redirect:/appointments", sagiaAppointmentController.saveAppointment( Optional.of("id"),appointment, model));
       } catch (Exception e) {
           Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
       }
   }

   @Test
    public void appointmentDetailsTest(){
        try {
            Appointment app = new Appointment();
            given(sagiaAppointmentFacade.getAppointment(0)).willReturn(app);
            assertNull(sagiaAppointmentController.appointmentDetails(0, model));
            verify(model).addAttribute("appointmentData", app);
            Assert.assertTrue(model.containsAttribute("appointmentDataJSON"));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getAppointmentsTest(){
        try {
            Collection<ListItem> list = new ArrayList<>();
            list.add(appointmentListItem);
            given(appointmentListItem.getFieldName()).willReturn("DEPARTMENT");
            given(sagiaAppointmentFacade.getAppointmentOptions()).willReturn(list);

            Collection<Appointment> appointments = new ArrayList<>();

            given(sagiaAppointmentFacade.getDepartments(list)).willReturn(departments);
            given(sagiaAppointmentFacade.getBranches(list)).willReturn(branches);
            given(sagiaAppointmentFacade.getMinistries(list)).willReturn(ministries);
            given(sagiaAppointmentFacade.getServiceTypes(list)).willReturn(serviceTypes);
            given(sagiaAppointmentFacade.getService(list)).willReturn(services);
            given(sagiaAppointmentFacade.getAppointments()).willReturn(appointments);

            assertNull(sagiaAppointmentController.getAppointments(model, null));
            verify(model).addAttribute("appointmentsPageNumber", 0);
            verify(model).addAttribute("departments", departments);
            verify(model).addAttribute("branches", branches);
            verify(model).addAttribute("ministries", ministries);
            verify(model).addAttribute("serviceTypes", serviceTypes);
            verify(model).addAttribute("services", services);
            verify(model).addAttribute("appointments", appointments);

            Assert.assertTrue(model.containsAttribute("appointmentModel"));
            Assert.assertTrue(model.containsAttribute("appointmentsJson"));
            Assert.assertTrue(model.containsAttribute("formOptionsJSON"));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void appointmentInfoQRTest(){
        try{
            Collection<ListItem> formOptions = new ArrayList<>();

            Collection<ListItem> departaments = new ArrayList<>();
            ListItem department = new ListItem();
            department.setFieldKey("1");
            departaments.add(department);

            Collection<ListItem> branches = new ArrayList<>();
            ListItem branch = new ListItem();
            branch.setFieldKey("1");
            branches.add(branch);

            String date = "2009-02-12T10:30:00";

            Collection<ListItem> list = new ArrayList<>();
            ListItem listItem = new ListItem();
            listItem.setFieldKey("0");
            list.add(listItem);

            listItem = new ListItem();
            listItem.setFieldKey("1");
            list.add(listItem);

            listItem = new ListItem();
            listItem.setFieldKey("2");
            list.add(listItem);

            String keys = "0:1:2";

            given(sagiaAppointmentFacade.getAppointmentOptions()).willReturn(formOptions);
            given(sagiaAppointmentFacade.getBranches(formOptions)).willReturn(branches);
            given(sagiaAppointmentFacade.getDepartments(formOptions)).willReturn(departaments);
            given(sagiaAppointmentFacade.getMinistries(formOptions)).willReturn(list);
            given(sagiaAppointmentFacade.getServiceTypes(formOptions)).willReturn(list);
            given(sagiaAppointmentFacade.getService(formOptions)).willReturn(list);

            Assert.assertNull(sagiaAppointmentController.appointmentInfoQR(Optional.of("1"),Optional.of("1"),
                    Optional.of(date), Optional.of(keys),Optional.of(keys),Optional.of(keys),model));

        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
