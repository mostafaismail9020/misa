package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import com.sap.ibso.eservices.storefront.forms.CreateGovtServiceForm;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Component("createGovtServiceValidator")
public class SagiaCreateGovtServiceValidator implements Validator {

    private static final String FORM_FILES = "formFiles";
    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateGovtServiceForm.class.equals(aClass);
    }

    /**
     * Validator for CreateGovtServiceForm.
     *
     * @param object
     * @param errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        CreateGovtServiceForm govtServiceForm = (CreateGovtServiceForm) object;

        if (!govtServiceForm.getTermsAndConditionsChecked()) {
            errors.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
        }

        if (CollectionUtils.isEmpty(govtServiceForm.getFiles())) {
            errors.rejectValue(FORM_FILES, "create.govtServices.emptyList");
            return;
        }
        int index = 0;
        List<String> fileData = govtServiceForm.getFileData();
        for (MultipartFile file : govtServiceForm.getFiles()) {
            if (file.getSize() <= 0) {
                if (fileData != null && index < fileData.size() && fileData.get(index) != null) {
                    final String fileCode = govtServiceForm.getFileData().get(index);
                    final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                    final MultipartFile customMultipartFile = new CustomMultipartFile(
                            mediaService.getDataFromMedia(mediaFile),
                            mediaFile.getRealFileName(),
                            mediaFile.getMime());
                    govtServiceForm.getFiles().set(index, customMultipartFile);
                } else {
                    errors.rejectValue(FORM_FILES, "create.govtServices.uploadAllDocs");
                    break;
                }
            }
            index++;
        }
    }
}
