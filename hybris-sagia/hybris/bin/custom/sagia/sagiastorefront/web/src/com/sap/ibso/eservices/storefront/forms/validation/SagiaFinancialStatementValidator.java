package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.FinancialStatementForm;
//import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

import com.sap.ibso.eservices.sagiaservices.utils.CustomMultipartFile;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;


@Component("financialStatementValidator")
public class SagiaFinancialStatementValidator implements Validator {

    @Resource
    private MediaService mediaService;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return FinancialStatementForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	FinancialStatementForm financialStatementForm = (FinancialStatementForm) o ;

        if (!Boolean.TRUE.equals(financialStatementForm.isTermsAndConditionsChecked())) {
            errors.rejectValue("termsAndConditionsChecked", "legal.consulation.validation.termsAndConditions");
        }
        
//        if (CollectionUtils.isNotEmpty(financialStatementForm.getFiles()) && financialStatementForm.getFiles().get(0).getSize() <= 0) {
//            List<String> fileData = financialStatementForm.getFileData();
//            if (CollectionUtils.isNotEmpty(fileData) && StringUtils.isNotEmpty(fileData.get(0))) {
//                final String fileCode = financialStatementForm.getFileData().get(0);
//                final CatalogUnawareMediaModel mediaFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
//                final MultipartFile customMultipartFile = new CustomMultipartFile(
//                        mediaService.getDataFromMedia(mediaFile),
//                        mediaFile.getRealFileName(),
//                        mediaFile.getMime());
//                financialStatementForm.getFiles().set(0, customMultipartFile);
//            }
//        }
    }
}
