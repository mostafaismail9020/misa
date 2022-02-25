<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<c:set value="/" var="existingShareholderFormAction"/>

<form:form id="existingShareholderForm" action="${existingShareholderFormAction}" method="post" modelAttribute="sagiaApplyExistingShareholderForm" enctype="multipart/form-data">
    <input type="hidden" name="code" value="${sagiaApplyExistingShareholderForm.code}">
    <div class="row" id="existing-Shareholder-Form">
        <div class="col-md-6 pb-0 pb-md-4">
            <formElement:formInputBox idKey="existingShareholderEntityNumber"
                                      labelKey="licence.apply.entershareholderentitynumber"
                                      path="shareHolderEntityNumber" inputCSS="validate__mandatory validate__numbers-only"/>
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="existingShareholderEntityNumber" class="form-control" placeholder="." value="" type="text" name="shareHolderEntityNumber"/>--%>
<%--                    <label class="control-label" for="existingShareholderEntityNumber"><spring:theme code="licence.apply.entershareholderentitynumber"/></label>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-2 pb-md-4">
            <formElement:formInputBox idKey="existingShareholderName" labelKey="license.apply.review.name"
                                      path="shareHolderName" disabled="true" inputCSS="validate__mandatory" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="existingShareholderName" class="form-control" placeholder="." value="" type="text" disabled="disabled" name="shareHolderName"/>--%>
<%--                    <label class="control-label" for="existingShareholderName"><spring:theme code="license.apply.review.name"/></label>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="existingShareholderParentCompanyCountry"
                                             labelKey="licence.apply.parentcompanycountry"
                                             path="parentCompanyCountry" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                             disabled="true"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="existingShareholderParentCompanyCountry" name="parentCompanyCountry" class="js-select2-oneColumn form-control" disabled="disabled"></select>--%>
<%--                    <label class="control-label" for="existingShareholderParentCompanyCountry"><spring:theme code="licence.apply.parentcompanycountry"/></label>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-4 pb-md-5">
            <formElement:formInputBoxCustom idKey="existingShareholderSharesPercentage"
                                            labelKey="license.apply.review.shares.percentage" path="sharesPercentage"
                                            inputBoxAppendKey="%" inputBoxCSS="formInputBox_group" inputCSS="validate__mandatory validate__float-numbers-only-sharespercentage"/>
<%--            <div class="formInputBox formInputBox_group mt-3">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="existingShareholderSharesPercentage" name = "sharesPercentage" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                    <label class="control-label" for="existingShareholderSharesPercentage"><spring:theme code="license.apply.review.shares.percentage"/></label>--%>
<%--                    <div class="formInputBox-append">--%>
<%--                        <span class="formInputBox-text">%</span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-1 pb-md-5">
            <formElement:formSelectBoxCustom idKey="existingProfessionalLicense" labelKey="license.professionalLicense"
                                             path="professionalLicense" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                             labelCSS="control-label_mandatory"
                                             selectedDataValue="${sagiaApplyExistingShareholderForm.professionalLicense}"/>
        </div>
       <%--  <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyExistingShareholderForm.commercialRegistration ? "active" : ""}">
                <c:set var="commercialRegistrationErrors">
                    <form:errors path="commercialRegistration"/>
                </c:set>
                <div class="form-group ${not empty commercialRegistrationErrors ? 'has-error' : ''}">
                    <input id="existingShareholderCommercialRegistrationFile" name="existingShareholderCommercialRegistrationFile" class="form-control js-inputFile validate__file" data-maxsize="5" type="file" accept="application/pdf" value=""/>
                    <input id="existingShareholderCommercialRegistrationFileName" name="existingShareholderCommercialRegistrationFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyExistingShareholderForm.commercialRegistration.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="existingShareholderCommercialRegistrationFileName"><spring:theme code="licence.apply.commercial.registration"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    <div class="help-block">${commercialRegistrationErrors}</div>
                </div>
            </div>
        </div> --%>
        <%-- <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyExistingShareholderForm.lastBudget ? "active" : ""}">
                <c:set var="lastBudgetErrors">
                    <form:errors path="lastBudget"/>
                </c:set>
                <div class="form-group  ${not empty lastBudgetErrors ? 'has-error' : ''}">
                    <input id="existingShareholderLastBudgetFile" name="existingShareholderLastBudgetFile" class="form-control js-inputFile validate__file" type="file" data-maxsize="5" accept="application/pdf" value=""/>
                    <input id="existingShareholderLastBudgetFileName" name="existingShareholderLastBudgetFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyExistingShareholderForm.lastBudget.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="existingShareholderLastBudgetFileName"><spring:theme code="licence.apply.commercial.existing.investment"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    <div class="help-block">${lastBudgetErrors}</div>
                </div>
            </div>
        </div> --%>
        <div class="col-md-6 " id="existingProfessionalLicenseFileAttachment" style="display: none">
             <div class="formInputFile ${not empty sagiaApplyExistingShareholderForm.professionalLicenseCertificate ? "active" : ""}">
                 <c:set var="existingProfessionalLicenseErrors">
                     <form:errors path="professionalLicenseCertificate"/>
                 </c:set>
                 <div class="form-group ${not empty existingProfessionalLicenseErrors ? 'has-error' : ''}">
                     <input id="existingProfessionalLicenseFile" name="existingProfessionalLicenseFile" class="form-control js-inputFile" type="file" data-maxsize="5" accept="application/pdf" value=""/>
                     <input id="existingProfessionalLicenseFileName" name="existingProfessionalLicenseFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyExistingShareholderForm.professionalLicenseCertificate.fileName}" readonly tabindex="-1"/>
                     <label class="control-label control-label_mandatory" for="existingProfessionalLicenseFileName"><spring:theme code="licence.apply.commercial.professionalLicense"/> </label>
                     <div class="form-icon form-icon_browse"><icon:upload/></div>
                     <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                 </div>
                 <div class="help-block">${existingProfessionalLicenseErrors}</div>
             </div>
         </div>
    </div>
</form:form>