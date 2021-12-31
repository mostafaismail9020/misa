package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SagiaClassificationData;
import com.sap.ibso.eservices.facades.sagia.SagiaClassificationFacade;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZAPPEALData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCREATEData;
import com.sap.ibso.eservices.sagiaservices.services.classification.*;
import com.sap.ibso.eservices.sagiaservices.services.classification.dto.ClassificationUpgradeFormData;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaClassificationUpgradeController
 */
@UnitTest
public class SagiaClassificationUpgradeControllerTest extends  AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    private ClassificationUpgradeService classificationUpgradeService;
    @Mock
    private ClassificationUpgradeDetailService classificationUpgradeDetailService;
    @Mock
    private ClassificationUpgradeAppealService classificationUpgradeAppealService;
    @Mock
    private ClassificationUpgradeAttachmentListService classificationUpgradeAttachmentListService;
    @Mock
    private ClassificationUpgradeZCreateDataService classificationUpgradeZCreateDataService;


    @Mock
    private ZATT_LISTData zatt_listData;

    @Mock
    private ZAPPEALData zappealData;

    @Mock
    private ZCREATEData zcreateData;

    @Mock
    private ZCLASS_DETSETData zclass_detsetData;

    @Mock
    private ClassificationUpgradeFormData classificationUpgradeFormData;

    @Mock
    private BindingResult bindingResult;


    @Spy
    @InjectMocks
    private SagiaClassificationUpgradeController sagiaClassificationUpgradeController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaClassificationUpgradeControllerTest() {
        sagiaClassificationUpgradeController = new SagiaClassificationUpgradeController();
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
    public void getClassificationUpgradeAppealListTest(){
        List<ZAPPEALData> zappealDataList = new ArrayList<>();
        zappealDataList.add(zappealData);
        given(classificationUpgradeAppealService.getClassificationUpgradeAppealList()).willReturn(zappealDataList);
        try {
            assertEquals("pages/classification/classificationList",sagiaClassificationUpgradeController.getClassificationUpgradeAppealList(model));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
            verify(model).addAttribute("classificationUpgrade_appeal", zappealDataList);

    }


    @Test
    public void getClassificationUpgradeAppealListExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(classificationUpgradeAppealService).getClassificationUpgradeAppealList();
        try {
            assertEquals("pages/classification/classificationList",sagiaClassificationUpgradeController.getClassificationUpgradeAppealList(model));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

    }

    @Test
    public void getClassificationUpgradeAttachmentListTest(){
        List<ZATT_LISTData> zatt_list = new ArrayList<>();
        zatt_list.add(zatt_listData);
        given(classificationUpgradeAttachmentListService.getClassificationUpgradeAttachmentList()).willReturn(zatt_list);
        try {
            assertEquals("pages/classification/classificationList",sagiaClassificationUpgradeController.getClassificationUpgradeAttachmentList(model));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("classificationUpgrade_attachment", zatt_list);
    }


    @Test
    public void getClassificationUpgradeAttachmentListExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(classificationUpgradeAttachmentListService).getClassificationUpgradeAttachmentList();
        try {
            assertEquals("pages/classification/classificationList",sagiaClassificationUpgradeController.getClassificationUpgradeAttachmentList(model));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        }

    @Test
    public void getClassificationUpgradeCreateByTest(){
        given(classificationUpgradeZCreateDataService.getZCreateData()).willReturn(zcreateData);
        assertEquals("pages/classification/classificationList", sagiaClassificationUpgradeController.getClassificationUpgradeCreateBy(request,model));
        verify(model).addAttribute("classificationUpgrade_create", zcreateData);

    }

    @Test
    public void getClassificationUpgradeCreateByExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(classificationUpgradeZCreateDataService).getZCreateData();
        assertEquals("pages/classification/classificationList", sagiaClassificationUpgradeController.getClassificationUpgradeCreateBy(request,model));

    }

    @Test
    public void getClassificationUpgradeDetailByTest(){
        given(classificationUpgradeDetailService.getClassificationUpgradeBy("objectID")).willReturn(zclass_detsetData);
        assertEquals("pages/classification/classificationList", sagiaClassificationUpgradeController.getClassificationUpgradeDetailBy("objectID", request,model));
        verify(model).addAttribute("classificationUpgrade_detail", zclass_detsetData);


    }

    @Test
    public void getClassificationUpgradeDetailByExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(classificationUpgradeDetailService).getClassificationUpgradeBy("objectID");
        assertEquals("pages/classification/classificationList", sagiaClassificationUpgradeController.getClassificationUpgradeDetailBy("objectID", request,model));


    }

    @Test
    public void submitTest(){
        List<ZATT_LISTData> zatt_list= new ArrayList<>();
        zatt_list.add(zatt_listData);
        given(classificationUpgradeAttachmentListService.getClassificationUpgradeAttachmentList()).willReturn(zatt_list);
        given(classificationUpgradeZCreateDataService.getZCreateData()).willReturn(zcreateData);
        doNothing().when(classificationUpgradeDetailService).createClassificationUpgrade(classificationUpgradeFormData, zatt_list);
        assertEquals("redirect:/my-sagia/license/classifications", sagiaClassificationUpgradeController.submit(model,classificationUpgradeFormData,bindingResult));
    }


    @Test
    public void submitExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(classificationUpgradeAttachmentListService).getClassificationUpgradeAttachmentList();
        given(classificationUpgradeZCreateDataService.getZCreateData()).willReturn(zcreateData);
        assertEquals("redirect:/my-sagia/license/classifications", sagiaClassificationUpgradeController.submit(model,classificationUpgradeFormData,bindingResult));

    }

    @Test
    public void getClassificationUpgradeFormDataTest(){

        assertNotNull(sagiaClassificationUpgradeController.getClassificationUpgradeFormData());

    }



}
