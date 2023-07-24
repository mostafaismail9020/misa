package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaConfigurationService;
import com.sap.ibso.eservices.sagiaservices.data.SagiaConfigurationData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * DefaultSagiaConfigurationFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaConfigurationFacade implements SagiaConfigurationFacade {

    private SagiaConfigurationService sagiaConfigurationService;
    private Converter<SagiaConfigurationModel, SagiaConfigurationData> sagiaConfigurationConverter;

    /**
     * @return
     */
    public SagiaConfigurationService getSagiaConfigurationService() {
        return sagiaConfigurationService;
    }

    /**
     * @param sagiaConfigurationService
     */
    public void setSagiaConfigurationService(SagiaConfigurationService sagiaConfigurationService) {
        this.sagiaConfigurationService = sagiaConfigurationService;
    }

    /**
     * @return
     */
    public Converter<SagiaConfigurationModel, SagiaConfigurationData> getSagiaConfigurationConverter() {
        return sagiaConfigurationConverter;
    }

    /**
     * @param sagiaConfigurationConverter
     */
    public void setSagiaConfigurationConverter(Converter<SagiaConfigurationModel, SagiaConfigurationData> sagiaConfigurationConverter) {
        this.sagiaConfigurationConverter = sagiaConfigurationConverter;
    }

    /**
     * @param key
     * @return
     */
    private String get(String key) {
        return getSagiaConfigurationService().get(key);
    }

    @Override
    public String getCrmProxy() {
        return get("crmProxy");
    }

    @Override
    public boolean isEnableSmsService() {
        return Boolean.valueOf(get("enableSmsService"));
    }

    @Override
    public String getSmsServiceUrl() {
        return get("smsServiceUrl");
    }

    @Override
    public String getSmsServiceUser() {
        return get("smsServiceUser");
    }

    @Override
    public String getSmsServicePassword() {
        return get("smsServicePassword");
    }

    @Override
    public String getPasswordRegex() {
        return sagiaConfigurationService.getPasswordRule();
    }
    
    @Override
    public String getPasswordErrorMessage(String lang) {
        return sagiaConfigurationService.getPasswordErrorMessageByLang(lang);
    }

    @Override
    public String getUnifiedLicenseUrl() {
        return get("unifiedLicenseUrl");
    }

    @Override
    public String getItemsPerPage() {
        return get("itemsPerPage");
    }

    @Override
    public String getShowItemsPerPage(){
        return get("showitemsPerPage");
    }
    @Override
    public String getRealtimeEmailUs() {
        return get("realtimeEmailUs");
    }

    @Override
    public String getRealtimeCallLocal() {
        return get("realtimeCallLocal");
    }

    @Override
    public String getRealtimeCallInternational() {
        return get("realtimeCallInternational");
    }

    @Override
    public String getGoogleMapKey() {
        return get("googleMapKey");
    }

    @Override
    public String getChatUrl() {
        return get("chatUrl");
    }

    @Override
    public String getDashboardBannerUrl() {
        return get("dashboardBannerUrl");
    }

    @Override
    public String getWebcallbackUrl() {
        return get("webcallbackSoapUrl");
    }

    @Override
    public String getWebcall_En_Queue() {
        return get("webcall_En_Queue");
    }
    @Override
    public String getWebcall_Ar_Queue() {
        return get("webcall_Ar_Queue");
    }

    @Override
    public Boolean isEnabledSalaryAndEmploymentOnDashboard() {
        return Boolean.valueOf(get("enableSalaryAndEmploymentOnDashboard"));
    }

    @Override
    public String getMandatorySurvey() {return get("mandatorySurvey");}

    @Override
    public String getTechnicalUsername() {
        return get("technicalUsername");
    }

	@Override
	public boolean isVerificationEnabled() {
		// TODO Auto-generated method stub
		return Boolean.valueOf(get("enableVerification"));
	}

	@Override
	public boolean isMobileVerificationEnabled() {
		// TODO Auto-generated method stub
		return Boolean.valueOf(get("enableMobileVerification"));
	}

	@Override
	public boolean isEmailVerificationEnabled() {
		// TODO Auto-generated method stub
		return Boolean.valueOf(get("enableEmailVerification"));
	}

	@Override
	public long getEmailValidityInSeconds() {
		// TODO Auto-generated method stub
		return Long.valueOf(get("emailValidityInSeconds"));
	}
	
    @Override
    public boolean isEnableTwoFactorAuthService() {
        return Boolean.valueOf(get("enableTwoFactorAuthService"));
    }
    
    @Override
    public boolean isEnableInvestSaudi2FactorAuthService() {
        return Boolean.valueOf(get("enableInvestSaudi2FactorAuthService"));
    }

    @Override
    public String getSagiaPartnerList() {
        return get("sagiaPartnerList");
    }

    @Override
    public String getMizaEmailId() {
        return get("mizaEmailId");
    }

    @Override
    public String getMizaEmailUserName() {
        return get("mizaEmailUserName");
    }

    @Override
    public String getStrategicInvestorEmailId() {
        return get("strategicInvestorEmailId");
    }

    @Override
    public String getStrategicInvestorEmailUserName() {
        return get("strategicInvestorEmailUserName");
    }
    @Override
    public String getInvestorVisaEmailId() {
        return get("investorVisaEmailId");
    }

    @Override
    public String getInvestorVisaEmailUserName() {
        return get("investorVisaEmailUserName");
    }


}
