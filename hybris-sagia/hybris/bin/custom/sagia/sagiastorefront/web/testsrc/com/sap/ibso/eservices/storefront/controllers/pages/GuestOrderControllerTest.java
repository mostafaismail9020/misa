package com.sap.ibso.eservices.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for GuestOrderController
 */
public class GuestOrderControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private GuestOrderController guestOrderController;

    @Mock
    private OrderFacade orderFacade;

    @Mock
    private OrderData orderDetails;

    /**
     * Default constructor, initialize spied mocks.
     */
    public GuestOrderControllerTest() {
        guestOrderController = new GuestOrderController();
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
    public void orderTest(){
        try {
            given(orderFacade.getOrderDetailsForGUID("orderGUID")).willReturn(orderDetails);
            String order = guestOrderController.order("orderGUID",model,response);
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
            verify(model).addAttribute("orderData", orderDetails);
            Assert.assertEquals("pages/guest/guestOrderPage",order);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void orderUnknownIdentifierExceptionTest(){
        try {
            given(orderFacade.getOrderDetailsForGUID("orderGUID")).willThrow(new UnknownIdentifierException("Testing UnknownIdentifierException.."));
            String order = guestOrderController.order("orderGUID",model,response);
            Assert.assertEquals("pages/error/errorNotFoundPage",order);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void orderIllegalArgumentExceptionTest(){
        try {
            given(orderFacade.getOrderDetailsForGUID("orderGUID")).willThrow(new IllegalArgumentException("Testing IllegalArgumentException.."));
            String order = guestOrderController.order("orderGUID",model,response);
            Assert.assertEquals("redirect:/orderExpired",order);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

}
