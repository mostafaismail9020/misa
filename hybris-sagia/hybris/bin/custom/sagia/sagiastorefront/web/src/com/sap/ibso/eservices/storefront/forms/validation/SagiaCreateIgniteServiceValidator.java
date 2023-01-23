package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import com.sap.ibso.eservices.storefront.forms.CreateIgniteServiceForm;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Component("createIgniteServiceValidator")
public class SagiaCreateIgniteServiceValidator implements Validator {

    private static final String FORM_FILES = "formFiles";
    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateIgniteServiceForm.class.equals(aClass);
    }

    /**
     * Validator for CreateIgniteServiceForm.
     *
     * @param object
     * @param errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        CreateIgniteServiceForm igniteServiceForm = (CreateIgniteServiceForm) object;

        if (!igniteServiceForm.getTermsAndConditionsChecked()) {
            errors.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
        }

        if (CollectionUtils.isEmpty(igniteServiceForm.getFiles())) {
            errors.rejectValue(FORM_FILES, "create.igniteServices.emptyList");
            return;
        }
        int index = 0;
        List<String> fileData = igniteServiceForm.getFileData();
        for (MultipartFile file : igniteServiceForm.getFiles()) {
            if (file.getSize() <= 0) {
                if (fileData != null && index < fileData.size() && fileData.get(index) != null) {
                    final String fileCode = igniteServiceForm.getFileData().get(index);
                    final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                    final MultipartFile customMultipartFile = new CustomMultipartFile(
                            mediaService.getDataFromMedia(mediaFile),
                            mediaFile.getRealFileName(),
                            mediaFile.getMime());
                    igniteServiceForm.getFiles().set(index, customMultipartFile);
                } else {
                    errors.rejectValue(FORM_FILES, "create.igniteServices.uploadAllDocs");
                    break;
                }
            }
            index++;
        }
    }
}
