package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.facades.data.GeneralManagerData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeContactDetailData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class GeneralManagerPopulator implements Populator<HomeContactDetailData,GeneralManagerData> {
    @Override
    public void populate(HomeContactDetailData generalManager, GeneralManagerData generalManagerData) {
        generalManagerData.setEmail(generalManager.getEmail());
        generalManagerData.setMobileNumber(generalManager.getMobileNumber());
        generalManagerData.setFirstName(generalManager.getFirstName());
        generalManagerData.setLastName(generalManager.getLastName());
    }
}
