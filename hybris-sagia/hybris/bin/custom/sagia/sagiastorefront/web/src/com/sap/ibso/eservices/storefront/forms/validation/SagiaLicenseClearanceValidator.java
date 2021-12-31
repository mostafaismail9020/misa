package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceFormData;
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
import org.apache.commons.lang.StringUtils;

@Component("licenseClearanceValidator")
public class SagiaLicenseClearanceValidator implements Validator {
    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return LicenseClearanceFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LicenseClearanceFormData formData = (LicenseClearanceFormData) o;

        if (StringUtils.isBlank(formData.getCrNo())) {
            errors.rejectValue("crNo", "general.entercrno");
        }
        if (StringUtils.isBlank(formData.getZakathNo())) {
            errors.rejectValue("zakathNo", "general.enterzakathno");
        }
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
                    final String fileCode = fileData.get(index);
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
