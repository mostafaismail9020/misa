package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import com.sap.ibso.eservices.storefront.forms.SupportVisitForm;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Component("sagiaSupportVisitValidator")
public class SagiaSupportVisitValidator implements Validator {

    @Resource
    private MediaService mediaService;

    @Override
    public boolean supports(Class<?> aClass) {

        return SupportVisitForm.class.equals(aClass);

    }

    @Override
    public void validate(Object o, Errors errors) {

        SupportVisitForm supportVisitForm = (SupportVisitForm) o;

        if (StringUtils.isEmpty(supportVisitForm.getDateString())) {
            errors.rejectValue("dateString", "validation.supportVisit.dateString");
        }

        if (StringUtils.isEmpty(supportVisitForm.getMessage())) {
            errors.rejectValue("message", "validation.supportVisit.message");
        }

        if (CollectionUtils.isNotEmpty(supportVisitForm.getFiles()) && supportVisitForm.getFiles().get(0).getSize() <= 0) {
            List<String> fileData = supportVisitForm.getFileData();
            if (CollectionUtils.isNotEmpty(fileData) && StringUtils.isNotEmpty(fileData.get(0))) {
                final String fileCode = supportVisitForm.getFileData().get(0);
                final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                final MultipartFile customMultipartFile = new CustomMultipartFile(
                        mediaService.getDataFromMedia(mediaFile),
                        mediaFile.getRealFileName(),
                        mediaFile.getMime());
                supportVisitForm.getFiles().set(0, customMultipartFile);
            }
        }

    }
}
