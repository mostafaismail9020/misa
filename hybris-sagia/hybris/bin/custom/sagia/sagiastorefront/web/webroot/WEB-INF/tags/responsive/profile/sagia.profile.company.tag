<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--@elvariable id="hasLicense" type="java.lang.Boolean"--%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>

<c:if test="${hasLicense}">
    <div class="panelTabs-head" id="companyTab"><spring:theme code="general.company"/></div>
    <div class="panelTabs-body" id="companyTabData">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">

                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                        <div class="contentModule-headline mw0 ml-0">
                            <%--<span class="iconElement iconElement_info"><icon:info/></span>--%>
                            <spring:theme code="general.basicinformation"/>
                        </div>
                             <hr class="hr w-100"/>

                    </div>


                    <div class="js-myAccount-edit-toggle">
                        <div class="row">
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.entityName"/></dt>
                                    <dd id="sagiaCompanyFormEntityName"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.entityNameArabic"/></dt>
                                    <dd id="sagiaCompanyFormEntityNameArabic"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.email"/></dt>
                                    <dd id="sagiaCompanyFormEmail"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.legalstatus"/></dt>
                                    <dd id="sagiaCompanyFormLegalStatusDisplay"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.region"/></dt>
                                    <dd id="sagiaCompanyFormRegionDisplay"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.capital"/></dt>
                                    <dd id="sagiaCompanyFormCapital"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.city"/></dt>
                                    <dd id="sagiaCompanyFormCityDisplay"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.country"/></dt>
                                    <dd id="sagiaCompanyFormCountry"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.isicSection"/></dt>
                                    <dd id="sagiaCompanyFormIsicSection"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.isinCode"/></dt>
                                    <dd id="sagiaCompanyFormIsicCode"></dd>
                                </dl>
                            </div>

                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.profile.basic.isicDivision"/></dt>
                                    <dd id="sagiaCompanyFormIsicDivision"></dd>
                                </dl>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="contentModule-section">
                    <div class="contentModule-headline mw1">
                        <!--<span class="iconElement iconElement_governmentDocuments"><icon:governmentDocuments/></span>-->
                        <spring:theme code="general.governmentdocuments"/>
                    </div>
                    <hr class="hr"/>
                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <a href="${encodedContextPath}/governmentDocuments" class="btn-normal btn btn-bg btn_bold btn_slim back_to_service">
                            <spring:theme code="general.governmentdocuments"/>
                        </a>
                    </div>
                </div>

                <div class="contentModule-section" id="branchesSection">
                    <div class="contentModule-headline mw0">
                        <c:set var="countBranches" value="${fn:length(branches)}"/>
                       <!-- <span class="iconElement iconElement_branches"><icon:branches/></span>--> <spring:theme code="general.branches"/>(${countBranches})
                    </div>
                    <hr class="hr"/>

                    <div class="tableModule tableModule_slim">
                        <table class="tableModule-table" id="branchesTable">
                            <thead class="tableModule-head">
                                <tr>
                                    <th><spring:theme code="text.account.profile.license.branches.name"/></th>
                                    <th><spring:theme code="text.account.profile.license.branches.type"/></th>
                                    <th><spring:theme code="text.account.profile.license.branches.city"/></th>
                                </tr>
                            </thead>
                            <tbody class="tableModule-body">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- General Manager and Company Representative-->
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <form id="generalManagerForm">
                    <!-- general manager -->
                    <div class="contentModule-section">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap mb-0">
                            <div class="contentModule-headline mw2">
                                <!--<span class="iconElement iconElement_generalManager"><icon:generalManager/></span>--><spring:theme code="profileCompany.generalManager.title"/>
                            </div>
                            <div>
                                <div class="js-myAccount loading"><spring:theme code="profileCompany.button.loading.text"/>
                                    <span class="iconElement iconElement_edit02"><icon:loading-spinner/></span>
                                </div>
                                <button class="js-contactUpdate-edit btn btn_link btn_edit">
                                    <div class="js-myAccount-edit-text edit hidden"><spring:theme code="profileCompany.button.edit.text"/>
                                        <span class="iconElement iconElement_edit02"><icon:edit/></span>
                                    </div>
                                    <div class="js-myAccount-edit-text close-edit hidden">
                                        <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span>
                                        <spring:theme code="profileCompany.button.close.edit.text"/>
                                    </div>
                                </button>
                            </div>
                        </div>
                        <hr class="hr"/>
                        <div class="js-myAccount-edit-toggle">
                            <div class="row mt-5 pt-4">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.firstname"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormFirstName"></dd>
                                    </dl>
                                </div>

                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.middlename"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormMiddleName"></dd>
                                    </dl>
                                </div>

                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.lastname"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormLastName"></dd>
                                    </dl>
                                </div>

                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.mobilenumber"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormMobileNumber"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.email"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormEmail"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.nationalid"/></dt>
                                        <dd id="sagiaProfileGeneralManagerFormNationalId"></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>

                        <div class="js-myAccount-edit-toggle" style="display: none;">
                            <div class="row mt-5 pt-4 pb-2">
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormFirstNameInput" name="firstName" class="form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.firstname"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormMiddleNameInput" name="middleName" class="form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.middlename"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormLastNameInput" name="lastName" class="form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.lastname"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormMobileNumberInput" name="mobileNumber" class=" form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.mobilenumber"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormEmailInput" name="email" class="form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.email"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sagiaProfileGeneralManagerFormNationalIdInput" name="nationalId" class="form-control" placeholder="." type="text"/>
                                            <label class="control-label"><spring:theme code="general.nationalid"/></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="fileNationalIdContent" name="fileNationalIdContent" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                            <input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                            <label class="control-label " for=""><spring:theme code="company.representativenationalid"/></label>
                                            <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="applicationSignedFile" name="applicationSignedFile" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                            <input id="text06" name="text06" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                            <label class="control-label"><spring:theme code="company.applicationsigned"/></label>
                                            <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="formInputFile">
                                        <div class="form-group">
                                            <input id="gosiCertificateIdContent" name="gosiCertificateIdContent" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                            <input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                            <label class="control-label " for=""><spring:theme code="company.gosicertificate"/></label>
                                            <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div class="contentModule-separator"></div>

                <!-- company representative-->
                <div class="contentModule-section" id="companyRepresentativeSection">
                   <!--<div class="contentModule-headline">
                        <span class="iconElement iconElement_generalManager"><icon:person/></span>
                        <spring:theme code="profileCompany.companyRepresentative.title"/>
                    </div>-->
                    <div class="js-myAccount-edit-toggle">
                        <div class="tableModule tableModule_slim">
                            <table class="tableModule-table" id="representativeTable">
                                <thead class="tableModule-head">
                                <tr>
                                    <th><spring:theme code="general.firstname"/></th>
                                    <th><spring:theme code="general.lastname"/></th>
                                    <th><spring:theme code="general.mobilenumber"/></th>
                                    <th><spring:theme code="general.email"/></th>
                                    <th class="tableModule-headItem_actionsCount_1"><spring:theme code="general.details"/></th>
                                </tr>
                                </thead>
                                <tbody class="tableModule-body"></tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- primary contact-->
                <div class="contentModule-section primary-contact">
                    <div class="contentModule-headline mw0">
                        <!--<span class="iconElement iconElement_generalManager"><icon:login-quick/></span>--><spring:theme code="general.primary.contact"/>
                    </div>
                    <hr class="hr"/>
                    <div class="js-myAccount-edit-toggle">
                        <div class="row">
                            <div class="col-md-6">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="general.current.primary.contact"/></dt>
                                    <dd id="primaryContactLabel"></dd>
                                </dl>
                            </div>
                        </div>
                    </div>

                    <div class="js-myAccount-edit-toggle" style="display: none;">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="primaryContactId" name="primaryContactId" class="js-select2-oneColumn form-control">
                                            <option></option>
                                        </select>
                                        <label class="control-label btn_bold pl-4" for="primaryContactId"><spring:theme code="general.current.primary.contact"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="js-myAccount-edit-toggle">
                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                        <c:url var="historyUrl" value="/contacts/history"/>
                        <a href="${historyUrl}" class="btn btn-ctrl btn_slim btn-bg btn_bold btn-normal w-25"><spring:theme code="profileCompany.contactUpdate.history"/></a>
                    </div>
                </div>

                <div class="js-myAccount-edit-toggle" style="display: none;">
                    <div class="acceptTerms acceptTerms_outsideSection">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="formCheckBox">
                                    <div class="form-group">
                                        <div class="form-item">
                                            <input id="termsAndConditionsId" name="termsAndConditions" class="form-control" placeholder="." type="checkbox" value="entity name"/>
                                            <label class="control-label" for="termsAndConditionsId">
                                                <c:url var="termsUrl" value="/cms/sagia-cms-TandC-licenseServices"/>
                                                <input class="form-control"  type="checkbox" >
                                                <label class="control-label" for="termsAndConditionsId">
                                                    <span><icon:check/></span>
                                                    <div class="termsDesc"><spring:theme code="register.termsConditions" arguments="${termsUrl}"/></div>
                                                </label>
                                            </label>
                                            <span class="help-block"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <a href="${contactUpdateTemplateUrl}" class="d-block link tc-download-file"><spring:theme code="company.applicationsigned.template"/></a>
                            </div>
                        </div>
                    </div>

                    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection mt-5">
                        <button id="updateContactsCancel" type="button" class="btn btn-normal btn-outline btn_bold text-uppercase"><spring:theme code="general.cancel"/></button>
                        <button id="updateContactsButton" type="button" class="btn btn-normal btn-bg btn_bold text-uppercase"><spring:theme code="general.update"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="companyRepresentativeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="contentModule">
                    <div class="modal-header">
                        <div class="modal-title">
                            <!--<span class="iconElement iconElement_generalManager"><icon:person/></span>--><spring:theme code="profileCompany.companyRepresentative.title"/>
                        </div>
                        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <icon:close/>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="contentModule-section">
                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.firstname"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormFirstName"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.middlename"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormMiddleName"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.lastname"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormLastName"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.mobilenumber"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormMobileNumber"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.email"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormEmail"></dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt><spring:theme code="general.nationalid"/></dt>
                                        <dd class="sagiaProfileRepresentativeFormNationalId"></dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer modal-footer_right"></div>
                </div>
            </div>
        </div>
    </div>

    <form id="companyRepresentativeTemplate" style="display:none">
        <div class="js-myAccount-edit-toggle" style="display: none;">
            <div class="contentModule-headline w-50"></div>
            <hr class="hr"/>
            <input name="bpId" class="bpId form-control" placeholder="." type="hidden" value=""/>
            <div class="row mt-5 pt-4">
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input name="firstName" class="companyRepresentativeFirstName form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.firstname"/></label>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input name="middleName" class="companyRepresentativeMiddleName form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.middlename"/></label>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input name="lastName" class="companyRepresentativeLastName form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.lastname"/></label>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input name="mobileNumber" class="companyRepresentativeMobileNumber form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.mobilenumber"/></label>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input name="email" class="companyRepresentativeEmail form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.email"/></label>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyRepNationalId" name="nationalId" class="companyRepresentativeNationalId form-control" placeholder="." type="text" value=""/>
                            <label class="control-label"><spring:theme code="general.nationalid"/></label>
                        </div>
                    </div>
                </div>

                <input name="contactType" title="contactType" class="companyRepresentativeContactType hidden" value="" type="text"/>
            </div>
            <div class="contentModule-separator"></div>
        </div>
    </form>

    <div class="modal fade" id="unsavedChangesModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <form action="" class="js-formInputFileBox">
                    <div class="modal-header modal-header_smallPDB">
                        <div class="modal-title">You have unsaved changes</div>
                        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <icon:close/>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="modal-description modal-description_largeMargin modal-description_smallText">
                            There are still changes in the form that were not yet submitted. By dismissing the changes all data will be lost.
                        </div>
                    </div>
                    <div class="modal-footer modal-footer_spaceBetween">
                        <button type="submit" class="btn btn-warning btn-bg m-0 mb-3" id="dismissChanges">Dismiss Changes</button>
                        <button type="submit" class="btn btn_round btn_slim m-0 mb-3" data-dismiss="modal">Continue editing</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
