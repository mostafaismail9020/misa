package com.sap.ibso.eservices.storefront.controllers.pages;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.facades.data.financial.FinancialData;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialFacade;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Test Class for FinancialController
 */
@UnitTest
public class FinancialControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private FinancialController financialController;

    @Mock
    private SagiaFinancialFacade sagiaFinancialFacade;

    @Mock
    private ContentDetailsService contentDetailsService;

    @Mock
    private List<FinancialData> financialDataList;

    @Mock
    private FinancialData financialData;

    /**
     * Default constructor, initialize spied mocks.
     */
    public FinancialControllerTest() {
        financialController = new FinancialController();
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
    public void getFinancialPageTest(){
        try{

            financialDataList = new ArrayList<>();
            financialDataList.add(financialData);
            given(financialData.getSrId()).willReturn("srId");
            given(sagiaFinancialFacade.getFinancialEntities()).willReturn(financialDataList);
            given(sagiaFinancialFacade.getFinancialData("srId")).willReturn(financialData);

            financialController.getFinancialPage(model,"2333");

            verify(model).addAttribute("financialData",financialDataList);
            verify(model).addAttribute("first",financialData);
        }catch(Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getFinancialPageExceptionTest(){

        try {
            given(sagiaFinancialFacade.getFinancialEntities()).willThrow(new RuntimeException("Testing Runtime Exception..."));
            financialController.getFinancialPage(model,"2333");

        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getFinanceHDRTest(){
        try {
            FinancialData financialData = new FinancialData();
            given(sagiaFinancialFacade.getFinancialData("id")).willReturn(financialData);
            Assert.assertEquals(new GsonBuilder().serializeNulls().create().toJson(financialData),financialController.getFinanceHDR("id"));
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
