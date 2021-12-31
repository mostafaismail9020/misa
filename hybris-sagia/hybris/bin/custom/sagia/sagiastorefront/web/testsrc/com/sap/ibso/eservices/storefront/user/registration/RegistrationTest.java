package com.sap.ibso.eservices.storefront.user.registration;

import com.sap.ibso.eservices.facades.user.data.SagiaRegisterData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

@UnitTest
public class RegistrationTest
{

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCustomerRegistration() throws DuplicateUidException
    {
        SagiaRegisterData customerData = new SagiaRegisterData();
        customerData.setFirstName("Leonardo");
        customerData.setLastName("Della-Sap");
        customerData.setMobileNumber("+1330993324422");
        customerData.setSectorCode("A");
        customerData.setPassword("myLeonardoPass123!");
    }
}
