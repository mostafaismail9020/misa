package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.*;
import com.sap.ibso.eservices.sagiaservices.services.impl.DiffQeemahService;
import com.sap.ibso.eservices.sagiaservices.services.impl.InvsIdPostService;
import com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2.BasicContactInformationService;
import com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2.LegalStatusService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.DropdownValueService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.QeemahGeneralQstService;

import java.util.Collection;

/**
 * Zqeemah2SagiaFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class Zqeemah2SagiaFacade {
    private InvsIdPostService invsIdPostService;
    private DiffQeemahService diffQeemahService;
    private DropdownValueService dropdownValueService;
    private QeemahGeneralQstService qeemahGeneralQstService;
    private BasicContactInformationService basicContactInformationService;
    private LegalStatusService legalStatusService;

    /**
     * getDiffQeemahData
     * @return DiffQeemahData
     */
    public DiffQeemahData getDiffQeemahData() {
        return diffQeemahService.get();
    }

    /**
     * saves DiffQeemahData
     * @param diff diff
     */
    public void saveDiffQeemahData(DiffQeemahData diff){
        diffQeemahService.saveDiff(diff);
    }

    /**
     * saveInvsIdPostData
     * @param invsIdPostData invsIdPostData
     * @return String
     */
    public String saveInvsIdPostData(InvsIdPostData invsIdPostData) {
        return invsIdPostService.save(invsIdPostData);
    }

    /**
     * getDropdownValues
     * @param language language
     * @param flag flag
     * @return Collection of DropdownValueData
     */
    public Collection<DropdownValueData> getDropdownValues(String language, String flag) {
        return dropdownValueService.getDropdownValuesData(language, flag, "");
    }

    /**
     * getQeemahGeneralQstData
     * @param language language
     * @param refId refId
     * @param questionId questionId
     * @return Collection of QeemahGeneralQstData
     */
    public Collection<QeemahGeneralQstData> getQeemahGeneralQstData(String language, String refId, String questionId) {
        return qeemahGeneralQstService.getCollection(language, refId, questionId);
    }

    /**
     * saveContactInformation
     * @param basicContactInfoData basicContactInfoData
     * @return String
     */
    public String saveContactInformation(BasicContactInfoData basicContactInfoData) {
        return basicContactInformationService.saveContact(basicContactInfoData);
    }

    /**
     * getLegalStatuses
     * @return Collection of LegalStatusData
     */
    public Collection<LegalStatusData> getLegalStatuses() {
        return legalStatusService.getLegalStatuses();
    }

    /**
     * setInvsIdPostService
     * @param invsIdPostService invsIdPostService
     */
    public void setInvsIdPostService(InvsIdPostService invsIdPostService) {
        this.invsIdPostService = invsIdPostService;
    }

    /**
     * setDiffQeemahService
     * @param diffQeemahService diffQeemahService
     */
    public void setDiffQeemahService(DiffQeemahService diffQeemahService) {
        this.diffQeemahService = diffQeemahService;
    }

    /**
     * setDropdownValueService
     * @param dropdownValueService dropdownValueService
     */
    public void setDropdownValueService(DropdownValueService dropdownValueService) {
        this.dropdownValueService = dropdownValueService;
    }

    /**
     * setQeemahGeneralQstService
     * @param qeemahGeneralQstService qeemahGeneralQstService
     */
    public void setQeemahGeneralQstService(QeemahGeneralQstService qeemahGeneralQstService) {
        this.qeemahGeneralQstService = qeemahGeneralQstService;
    }

    /**
     * setBasicContactInformationService
     * @param basicContactInformationService basicContactInformationService
     */
    public void setBasicContactInformationService(BasicContactInformationService basicContactInformationService) {
        this.basicContactInformationService = basicContactInformationService;
    }

    /**
     * setLegalStatusService
     * @param legalStatusService legalStatusService
     */
    public void setLegalStatusService(LegalStatusService legalStatusService) {
        this.legalStatusService = legalStatusService;
    }
}
