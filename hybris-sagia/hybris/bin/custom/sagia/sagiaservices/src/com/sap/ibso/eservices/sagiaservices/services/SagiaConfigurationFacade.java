package com.sap.ibso.eservices.sagiaservices.services;

/**
 * SagiaConfigurationFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaConfigurationFacade {
    /**
     * getCrmProxy
     * @return
     */
    String getCrmProxy();

    /**
     * isEnableSmsService
     * @return
     */
    boolean isEnableSmsService();

    /**
     * getSmsServiceUrl
     * @return
     */
    String getSmsServiceUrl();

    /**
     * getSmsServiceUser
     * @return
     */
    String getSmsServiceUser();

    /**
     * getSmsServicePassword
     * @return
     */
    String getSmsServicePassword();

    /**
     * getPasswordRegex
     * @return
     */
    String getPasswordRegex();

    /**
     * getUnifiedLicenseUrl
     * @return
     */
    String getUnifiedLicenseUrl();

    /**
     * getItemsPerPage
     * @return
     */
    String getItemsPerPage();

    /**
     * getShowItemsPerPage
     * @return
     */
    String getShowItemsPerPage();

    /**
     * getRealtimeEmailUs
     * @return
     */
    String getRealtimeEmailUs();

    /**
     * getRealtimeCallLocal
     * @return
     */
    String getRealtimeCallLocal();

    /**
     * getRealtimeCallInternational
     * @return
     */
    String getRealtimeCallInternational();

    /**
     * getGoogleMapKey
     * @return
     */
    String getGoogleMapKey();

    /**
     * getChatUrl
     * @return
     */
    String getChatUrl();

    /**
     *
     * getPasswordErrorMessage
     * @param  lang lang
     * @return errorMessage
     */
    String getPasswordErrorMessage(String lang);

    /**
     * getDashboardBannerUrl
     * @return String configured dashboard URL for banner
     */
    String getDashboardBannerUrl();

    /**
     * getWebCallbackUrl
     * @return
     */
    String getWebcallbackUrl();

    /**
     * getWebcall_En_Queue
     * @return
     */
    String getWebcall_En_Queue();

    /**
     * getWebcall_Ar_Queue
     * @return
     */
    String getWebcall_Ar_Queue();

    /**
     * getEnableSalaryAndEmploymentOnDashboard
     * @return
     */
    Boolean isEnabledSalaryAndEmploymentOnDashboard();

    /**
     * getMandatorySurvey
     * @return
     */
    String getMandatorySurvey();

    /**
     * getTechnicalUsername
     * @return
     */
    String getTechnicalUsername();
    
    /**
     * isEnableVerificationService
     * @return
     */
    boolean isVerificationEnabled();
    
    /**
     * isEnableVerificationService
     * @return
     */
    boolean isMobileVerificationEnabled();
    
    /**
     * isEnableVerificationService
     * @return
     */
    boolean isEmailVerificationEnabled();
    
    /**
     * isEnableVerificationService
     * @return
     */
    long getEmailValidityInSeconds();
    
    /**
     * getEnableTwoFactorAuthentication
     * @return
     */
    boolean isEnableTwoFactorAuthService();
    
    /**
     * isEnableInvestSaudi2FactorAuthService
     * @return
     */
    boolean isEnableInvestSaudi2FactorAuthService();

    /**
     * getSagiaPartnerList
     * @return
     */
    String getSagiaPartnerList();

    /**
     * getMizaEmailId
     * @return
     */
    String getMizaEmailId();

    /**
     * getMizaEmailUserName
     * @return
     */
    String getMizaEmailUserName();

    /**
     * getStrategicInvestorEmailId
     * @return
     */
    String getStrategicInvestorEmailId();

    /**
     * getStrategicInvestorEmailUserName
     * @return
     */
    String getStrategicInvestorEmailUserName();
}
