package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCREATEData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeAppealService;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeAttachmentListService;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeDetailService;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeZCreateDataService;
import com.sap.ibso.eservices.sagiaservices.services.classification.dto.ClassificationUpgradeFormData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/classifications")
public class SagiaClassificationUpgradeController extends SagiaAbstractPageController {
    private static final String SAGIA_CLASSIFICATION_UPGRADE_HOME_PAGE = "/my-sagia/license/classifications";
    private static final String CLASSIFICATION_LIST = "pages/classification/classificationList";

    @Resource(name = "classificationUpgradeDetailService")
    private ClassificationUpgradeDetailService classificationUpgradeDetailService;
    @Resource(name = "classificationUpgradeAppealService")
    private ClassificationUpgradeAppealService classificationUpgradeAppealService;
    @Resource(name = "classificationUpgradeAttachmentListService")
    private ClassificationUpgradeAttachmentListService classificationUpgradeAttachmentListService;
    @Resource(name = "classificationUpgradeZCreateDataService")
    private ClassificationUpgradeZCreateDataService classificationUpgradeZCreateDataService;
    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;
    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    /**
     * read appeals for a classification upgrade
     * displays appeals for Classification upgrade
     *
     * @param model model
     */
    @RequestMapping(value = "/appeal", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getClassificationUpgradeAppealList(final Model model) {
        model.addAttribute("classificationUpgrade_appeal", classificationUpgradeAppealService.getClassificationUpgradeAppealList());
        return CLASSIFICATION_LIST;
    }

    /**
     * read attachments for a classification upgrade
     * displays attachments for Classification upgrade
     *
     * @param model model
     */
    @RequestMapping(value = "/attachment", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getClassificationUpgradeAttachmentList(final Model model) {
        model.addAttribute("classificationUpgrade_attachment",
                classificationUpgradeAttachmentListService.getClassificationUpgradeAttachmentList());
        return CLASSIFICATION_LIST;
    }

    /**
     * read entity for a classification upgrade
     * displays entity for a classification upgrade
     *
     * @param model
     * @param request
     */
    @RequestMapping(method = RequestMethod.GET, path = "/entity/{entity}")
    @RequireHardLogIn
    public String getClassificationUpgradeCreateBy(HttpServletRequest request, Model model) {
        model.addAttribute("classificationUpgrade_create", classificationUpgradeZCreateDataService.getZCreateData());
        return CLASSIFICATION_LIST;
    }

    /**
     * read Classification Upgrade by objectID
     * displays details for a specific Classification upgrade entity
     *
     * @param model
     * @param objectID
     * @param request
     */
	@RequestMapping(method = RequestMethod.GET, path = "/{objectID}")
	@RequireHardLogIn
	public ResponseEntity<ZCLASS_DETSETData> getClassificationUpgradeDetailBy(@PathVariable("objectID") String objectID,
			HttpServletRequest request, Model model) {
		ZCLASS_DETSETData requestedEntity = classificationUpgradeDetailService.getClassificationUpgradeBy(objectID);
		return new ResponseEntity<>(requestedEntity, HttpStatus.OK);
	}

    /**
     * create a new request in order to upgrade the classification
     *
     * @param model
     * @param classificationUpgradeFormData
     * @param result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String submit(final Model model, @ModelAttribute("classificationUpgradeFormData") final ClassificationUpgradeFormData classificationUpgradeFormData,
                         final BindingResult result) {
        if (licenseFacade.hasInvalidLicense(zui5SagiaFacade.getHomeHDRData())) {
            throw new SagiaRuntimeException("Upgrade classification is not allowed. Entity status is invalid!");
        }
        ZCREATEData zcreateData = classificationUpgradeZCreateDataService.getZCreateData();
        classificationUpgradeFormData.setEntity(zcreateData.getEntity());
        classificationUpgradeFormData.setClassProperty(zcreateData.getClassProperty());
        classificationUpgradeDetailService.createClassificationUpgrade(
                classificationUpgradeFormData, classificationUpgradeAttachmentListService.getClassificationUpgradeAttachmentList());
        return REDIRECT_PREFIX + SAGIA_CLASSIFICATION_UPGRADE_HOME_PAGE;
    }

    @ModelAttribute("classificationUpgradeFormData")
    public ClassificationUpgradeFormData getClassificationUpgradeFormData() {
        return new ClassificationUpgradeFormData();
    }
}