package com.sap.ibso.eservices.sagiaservices.services;

import com.google.gson.Gson;
import org.apache.commons.lang3.Validate;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ODataModel
 */
public class ODataModel {
    private final Map<String, Object> map = new HashMap<>();

    /**
     *
     */
    public ODataModel() {
    }

    /**
     * @param oDataEntry
     */
    public ODataModel(ODataEntry oDataEntry) {
        Validate.notNull(oDataEntry, "oDataEntry cannot be null");
        Validate.notEmpty(oDataEntry.getProperties(), "properties cannot be empty");
        this.map.clear();
        this.map.putAll(oDataEntry.getProperties());
    }

    /**
     * @param key
     * @return
     */
    public Object get(String key) {
        return map.get(key);
    }

    /**
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        Validate.notEmpty(key, "key cannot be empty");
        Validate.notNull(value, "value cannot be null");
        map.put(key, value);
    }

    /**
     * @param map
     */
    public void set(Map<String, Object> map) {
        Validate.notEmpty(map,"map cannot be empty");
        this.map.clear();
        this.map.putAll(map);
    }

    /**
     * @return
     */
    public Map<String, Object> get() {
        return Collections.unmodifiableMap(map);
    }

    /**
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        map.put(key, value);
    }

    /**
     * @return
     */
    public byte[] getAsPayload(){
        return new Gson().toJson(map).getBytes();
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
