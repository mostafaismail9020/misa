/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.services.notifications.SagiaCRMInvestorNotificationsService;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import java.util.Collection;

@IntegrationTest
public class SagiaCRMInvestorNotificationsServiceTest extends ServicelayerTransactionalTest {

    private static SagiaCRMInvestorNotificationsService investorNotificationService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        investorNotificationService = appCtx.getBean("sagiaCRMInvestorNotificationsService", SagiaCRMInvestorNotificationsService.class);
        investorNotificationService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateInvestorNotificationData() {
        Collection<InvestorNotificationData> investorNotifications = investorNotificationService.getNotificationsFor("614140");
        Assert.assertTrue(investorNotifications.size() > 0);
    }

    @Test
    public void shouldPopulateInvestorNotificationDataByTransactionId() {
        InvestorNotificationData investorNotification = investorNotificationService.getNotificationBy("614140", "0000526439");
        Assert.assertEquals("614140", investorNotification.getInvestorId());
        Assert.assertEquals("0000526439", investorNotification.getTransactionId());
    }
}
