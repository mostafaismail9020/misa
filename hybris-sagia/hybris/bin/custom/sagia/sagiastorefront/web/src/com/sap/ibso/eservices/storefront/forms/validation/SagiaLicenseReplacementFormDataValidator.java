package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementFormData;
import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Component("licenseReplacementFormDataValidator")
public class SagiaLicenseReplacementFormDataValidator implements Validator {

    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return LicenseReplacementFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LicenseReplacementFormData licenseData = (LicenseReplacementFormData) o;

        if (!licenseData.getTermsAndConditionsChecked()) {
            errors.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
        }

        if (CollectionUtils.isNotEmpty(licenseData.getFiles()) && licenseData.getFiles().get(0).getSize() <= 0) {
            List<String> fileData = licenseData.getFileData();
            if (CollectionUtils.isNotEmpty(fileData) && StringUtils.isNotEmpty(fileData.get(0))) {
                final String fileCode = licenseData.getFileData().get(0);
                final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                final MultipartFile customMultipartFile = new CustomMultipartFile(
                        mediaService.getDataFromMedia(mediaFile),
                        mediaFile.getRealFileName(),
                        mediaFile.getMime());
                licenseData.getFiles().set(0, customMultipartFile);
            }
        }
    }
}
