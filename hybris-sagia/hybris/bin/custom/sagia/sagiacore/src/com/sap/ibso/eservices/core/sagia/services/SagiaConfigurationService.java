package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;

import java.util.List;

/**
 * Provides access to the Configuration Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaConfigurationService {

    /**
     * Get all configurations
     * @return - List containing all configurations
     */
    List<SagiaConfigurationModel> get();

    /**
     * Get a configuration by its unique key
     * @param key - The key for which the configuration is retrieved
     * @return - The configuration with that specific key
     */
    String get(String key);

    /**
     * Get the password rule.
     * @return - Sagia password rule
     */
    String getPasswordRule();

    /**
     * Get the error message by the language
     * @param lang - The language for which the password error message is retrieved.
     * @return - The password error message for that specific language.
     */
    String getPasswordErrorMessageByLang(String lang);
}
