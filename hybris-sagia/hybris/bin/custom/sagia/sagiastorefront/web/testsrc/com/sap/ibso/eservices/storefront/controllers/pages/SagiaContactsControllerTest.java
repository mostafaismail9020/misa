package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.CompanyRepresentativeFormData;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.GeneralManagerFormData;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * Unit Test Class for SagiaContactsController
 */
@UnitTest
public class SagiaContactsControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    GeneralManagerFormData generalManagerFormData;

    @Mock
    private SagiaAccountFacade sagiaAccountFacade;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private CompanyRepresentativeFormData companyRepresentativeFormData;

    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;

    @Spy
    @InjectMocks
    private SagiaContactsController sagiaContactsController;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaContactsControllerTest() {
        sagiaContactsController = new SagiaContactsController();
        redirectAttributes = new RedirectAttributesModelMap();
    }

    /**
     * Initialize mocks
     */
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        setupCmsContentPage();
    }

//    TODO : Test the update of all the contacts in one request
}
