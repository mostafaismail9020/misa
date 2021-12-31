package com.sap.ibso.eservices.core.populator;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.SagiaSectorData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class CustomProfilePopulator implements Populator<CustomerModel,CustomerData> {

    private Converter<MediaModel, MediaData> mediaConverter;
    private Converter<SagiaCountryModel,SagiaCountryData> sagiaCountryConverter;
    private Converter<SagiaSectorModel,SagiaSectorData> sagiaSectorConverter;

    public Converter<MediaModel, MediaData> getMediaConverter() {
        return mediaConverter;
    }

    public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
        this.mediaConverter = mediaConverter;
    }

    @Override
    public void populate(CustomerModel customerModel, CustomerData customerData)  {

        customerData.setUid(customerModel.getUid());
        customerData.setEmail(customerModel.getUserNameEmail());
        customerData.setMobileCountryCode(customerModel.getMobileCountryCode());
        customerData.setMobileNumber(customerModel.getMobileNumber());
        customerData.setCompany(customerModel.getCompany());

        if (customerModel.getDashboardMedia() != null) {
            customerData.setDashboardMedia(getMediaConverter().convert(customerModel.getDashboardMedia()));
        }
        if(customerModel.getSector() != null){
            customerData.setSector(sagiaSectorConverter.convert(customerModel.getSector()));
        }
        if(customerModel.getCountry() != null){
            customerData.setCountry(sagiaCountryConverter.convert(customerModel.getCountry()));
        }

        //default is true
        Boolean displaySetNowCompanyPhotoOption = customerModel.getShouldDisplaySetCompanyPhotoOption();
        customerData.setShouldDisplaySetCompanyPhotoOption(
                displaySetNowCompanyPhotoOption == null ? Boolean.TRUE : displaySetNowCompanyPhotoOption);

        Boolean dashboardTutorialDismissedOption = customerModel.getDashboardTutorialDismissed();
        customerData.setDashboardTutorialDismissed(
                dashboardTutorialDismissedOption == null ? Boolean.FALSE : dashboardTutorialDismissedOption);

        Boolean dashboardNoLicenseTutorialDismissedOption = customerModel.getDashboardNoLicenseTutorialDismissed();
        customerData.setDashboardNoLicenseTutorialDismissed(
                dashboardNoLicenseTutorialDismissedOption == null ? Boolean.FALSE : dashboardNoLicenseTutorialDismissedOption);

        customerData.setLicenseApplicationServiceRequestID(customerModel.getApplicationServiceRequestID());

    }

    public void setSagiaCountryConverter(Converter<SagiaCountryModel,SagiaCountryData> sagiaCountryConverter) {
        this.sagiaCountryConverter = sagiaCountryConverter;
    }

    public Converter<SagiaCountryModel,SagiaCountryData> getSagiaCountryConverter() {
        return sagiaCountryConverter;
    }

    public void setSagiaSectorConverter(Converter<SagiaSectorModel,SagiaSectorData> sagiaSectorConverter) {
        this.sagiaSectorConverter = sagiaSectorConverter;
    }

    public Converter<SagiaSectorModel,SagiaSectorData> getSagiaSectorConverter() {
        return sagiaSectorConverter;
    }
}
