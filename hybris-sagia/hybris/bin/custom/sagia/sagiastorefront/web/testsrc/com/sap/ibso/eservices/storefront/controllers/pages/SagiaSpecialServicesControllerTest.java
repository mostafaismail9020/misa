package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.facades.data.specialservices.ServiceApplicant;
import com.sap.ibso.eservices.facades.data.specialservices.SpecialService;
import com.sap.ibso.eservices.facades.data.specialservices.SpecialServiceHeader;
import com.sap.ibso.eservices.facades.sagia.SagiaSpecialServiceFacade;
import com.sap.ibso.eservices.sagiaservices.services.impl.SpecialServiceAttachmentService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaSpecialServicesController
 */
@UnitTest
public class SagiaSpecialServicesControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    public static final String PDF = "application/pdf";
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";



    @Mock
    private InputStream inputStream;

    @Mock
    private SagiaSpecialServiceFacade sagiaSpecialServiceFacade;

    @Mock
    private SpecialServiceAttachmentService specialServiceAttachmentService;

    @Mock
    private Collection<SpecialService> specialServices;

    @Mock
    private SessionService sessionService;

    @Mock
    private ServiceApplicant serviceApplicant;

    @Mock
    private SpecialServiceHeader specialServiceHeader;


    @Spy
    @InjectMocks
    private SagiaSpecialServicesController sagiaSpecialServicesController;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaSpecialServicesControllerTest() {

        sagiaSpecialServicesController = new SagiaSpecialServicesController();
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
    public void saveAppointmentTest(){
        try{
            SpecialServiceHeader specialServiceHeader = new SpecialServiceHeader();
            assertEquals("redirect:/special-services/serviceType",sagiaSpecialServicesController.saveAppointment(specialServiceHeader,null,"serviceType",Optional.of("id"),model));
        }catch(Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getPdfAttachmentTest(){
        try {
            given(sagiaSpecialServiceFacade.getAttachements("objectId", "documentId")).willReturn(inputStream);

            assertEquals(HttpStatus.OK,sagiaSpecialServicesController.getPdfAttachment("entitySetName","objectId","documentId",request,model).getStatusCode());
            assertEquals(MediaType.parseMediaType(PDF),sagiaSpecialServicesController.getPdfAttachment("entitySetName","objectId","documentId",request,model).getHeaders().getContentType());
            assertEquals(inputStream,sagiaSpecialServicesController.getPdfAttachment("entitySetName","objectId","documentId",request,model).getBody().getInputStream());
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getSpecialServicesTest(){
        try{
            given(sagiaSpecialServiceFacade.getServices("ZSPLJAWA01")).willReturn(specialServices);

            Assert.assertNull(sagiaSpecialServicesController.getSpecialServices("exit-re-entry-visa",
                    1,model));

            verify(model).addAttribute("specialServices", specialServices);
            verify(model).addAttribute("serviceType", "exit-re-entry-visa");
            Assert.assertTrue(model.containsAttribute("specialServiceHeader"));

        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void editAppointmentTest(){
        try{
            ServiceApplicant applicant = new ServiceApplicant();
            Set<ServiceApplicant> applicants = new HashSet<>();
            SpecialServiceHeader specialServiceHeader = new SpecialServiceHeader();

            applicants.add(applicant);

            specialServiceHeader.setApplicants(applicants);
            given(sagiaSpecialServiceFacade.getService(1)).willReturn(specialServiceHeader);

            Assert.assertNull(sagiaSpecialServicesController.editAppointment("serviceType", Optional.of("1"), model, new RedirectAttributes() {
                @Override
                public Object getAttribute(String s) {
                    return null;
                }

                @Override
                public RedirectAttributes addAttribute(String s, Object o) {
                    return null;
                }

                @Override
                public RedirectAttributes addAttribute(Object o) {
                    return null;
                }

                @Override
                public RedirectAttributes addAllAttributes(Collection<?> collection) {
                    return null;
                }

                @Override
                public RedirectAttributes mergeAttributes(Map<String, ?> map) {
                    return null;
                }

                @Override
                public RedirectAttributes addFlashAttribute(String s, Object o) {
                    return null;
                }

                @Override
                public RedirectAttributes addFlashAttribute(Object o) {
                    return null;
                }

                @Override
                public Map<String, ?> getFlashAttributes() {
                    return null;
                }

                @Override
                public Model addAllAttributes(Map<String, ?> map) {
                    return null;
                }

                @Override
                public boolean containsAttribute(String s) {
                    return false;
                }

                @Override
                public Map<String, Object> asMap() {
                    return null;
                }
            }));

            verify(model).addAttribute("serviceType", "serviceType");
            Assert.assertTrue(model.containsAttribute("specialServiceHeaderJson"));
            Assert.assertTrue(model.containsAttribute("specialServiceHeader"));
            Assert.assertTrue(model.containsAttribute("serviceApplicant"));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getApplicantsTest(){

        Collection<ServiceApplicant> collection =new ArrayList<>();
        given(sessionService.getAttribute("ServiceApplicants" + "exit-re-entry-visa")).willReturn(collection);
        assertEquals(HttpStatus.OK, sagiaSpecialServicesController.getApplicants("exit-re-entry-visa").getStatusCode());
    }

    @Test
    public void addApplicantTest(){

        Collection<ServiceApplicant> collection =new ArrayList<>();
        given(sessionService.getAttribute("ServiceApplicants" + "exit-re-entry-visa")).willReturn(collection);
        assertEquals(HttpStatus.OK, sagiaSpecialServicesController.addApplicant(serviceApplicant, "exit-re-entry-visa").getStatusCode());
    }

    @Test
    public void removeApplicantTest(){
        Collection<ServiceApplicant> collection =new ArrayList<>();
        given(sessionService.getAttribute("ServiceApplicants" + "exit-re-entry-visa")).willReturn(collection);
        assertEquals(HttpStatus.OK, sagiaSpecialServicesController.removeApplicant("guid", "exit-re-entry-visa").getStatusCode());
    }


}
