/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.beans.factory.annotation.Required;

/**
 * OrgInfoService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

public class OrgInfoService extends AbstractSagiaService<OrgInfoData> {

    private InvestorMappingService investorMappingService;

    /**
     * retrieves OrgInfoData
     * @return OrgInfoData
     */
    public final OrgInfoData get() {
        return super.get(OrgInfoData.class, (Object) "");
    }

    /**
     * updates OrgInfoData
     * TODO: we need to see if this can be replaced by the updateOrganizationInformtion
     * @param basicCompanyFormData basicCompanyFormData
     */
    public void updateOrgInfoData(final BasicCompanyData basicCompanyFormData) {
        final OrgInfoData data = new OrgInfoData();
        data.setOrgName(basicCompanyFormData.getEntityName());
        data.setOrgName1(basicCompanyFormData.getEntityNameArabic());
        data.setCapital(basicCompanyFormData.getCapital());
        data.setRegion(basicCompanyFormData.getRegion());
        data.setCity(basicCompanyFormData.getCity());
        data.setLegalStatus(basicCompanyFormData.getLegalStatus());
        data.setRefID(investorMappingService.getApplicantReferenceId(null));
        super.save(data, investorMappingService.getApplicantReferenceId(null));
    }

    /**
     * updates OrganizationInformtion
     * @param orgInfoData orgInfoData
     * @return OrgInfoData
     */
    public OrgInfoData updateOrganizationInformtion(OrgInfoData orgInfoData,CustomerModel customer){
        orgInfoData.setRefID(investorMappingService.getApplicantReferenceId(customer));
        super.save(orgInfoData);
        return null;
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService)
    {
        this.investorMappingService = investorMappingService;
    }
}
