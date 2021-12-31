package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.facades.data.ContactPersonData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeContactDetailData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ContactPersonPopulator implements Populator<HomeContactDetailData,ContactPersonData> {
    @Override
    public void populate(HomeContactDetailData contactPerson, ContactPersonData contactPersonData)  {
        contactPersonData.setEmail(contactPerson.getEmail());
        contactPersonData.setNationalId(contactPerson.getBpID());
        contactPersonData.setFirstName(contactPerson.getFirstName());
        contactPersonData.setLastName(contactPerson.getLastName());
        contactPersonData.setMobileNumber(contactPerson.getMobileNumber());
    }
}
