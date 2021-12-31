package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancelFormData;
import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Component("sagialicenseCancelValidator")
public class SagiaLicenseCancelValidator implements Validator {
    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return LicenseCancelFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LicenseCancelFormData formData = (LicenseCancelFormData) o;

        if (!formData.getTermsAndConditionsChecked()) {
            errors.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
        }
        if (CollectionUtils.isEmpty(formData.getFiles())) {
            errors.rejectValue("attachments", "general.pleaseuploadfiles");
            return;
        }
        int index = 0;
        List<String> fileData = formData.getFileData();
        for (MultipartFile file : formData.getFiles()) {
            if (file.getSize() <= 0) {
                if (fileData != null && fileData.get(index) != null) {
                    final String fileCode = formData.getFileData().get(index);
                    final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                    final MultipartFile customMultipartFile = new CustomMultipartFile(
                            mediaService.getDataFromMedia(mediaFile),
                            mediaFile.getRealFileName(),
                            mediaFile.getMime());
                    formData.getFiles().set(index, customMultipartFile);
                } else {
                    errors.rejectValue("attachments", "general.pleaseuploadfiles");
                    break;
                }
            }
            index++;
        }
    }
}
