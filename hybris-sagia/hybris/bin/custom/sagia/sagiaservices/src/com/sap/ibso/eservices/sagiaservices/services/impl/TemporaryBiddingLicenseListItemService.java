package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseListItemData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * TemporaryBiddingLicenseListItemService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class TemporaryBiddingLicenseListItemService extends AbstractSagiaService<TemporaryBiddingLicenseListItemData> {
    /**
     * retrieves a Collection of TemporaryBiddingLicenseListItemData by type and language
     * @param type type
     * @param language language
     * @return Collection of TemporaryBiddingLicenseListItemData
     */
    public Collection<TemporaryBiddingLicenseListItemData> getListItems(String type, String language) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.put("$skip", "0");
        queryOptions.put("$top", "100");
        queryOptions.put("$inlinecount", "allpages");
        queryOptions.put("Type", "\'" + type + "\'");
        queryOptions.put("Lang", "\'" + language + "\'");
        queryOptions.put("$format", "json");
        return getCollection(TemporaryBiddingLicenseListItemData.class, queryOptions);
    }
}
