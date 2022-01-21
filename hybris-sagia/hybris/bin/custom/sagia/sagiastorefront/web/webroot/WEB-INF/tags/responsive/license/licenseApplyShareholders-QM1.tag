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
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<!-- QM1 -->
<div class="contentModule" id="shareholdersQM1" style="display: none">
    <div class="contentModule-section" id="addShareholderQM1NoShareholderSection" style="display: none">
        <span class="iconElement iconElement_shareholderProfile text-center"><icon:shareholderProfile/></span>
        <p class="text-center"><spring:theme code="licence.apply.noshareholder"/></p>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
    <button type="button" class="addExistingButton btn btn-bg btn_bold btn-normal"><span class="icon-bg icon-ele"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="existing" transform="translate(-222.936 -1127.936)">
    <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"/>
    <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"/>
  </g>
</svg>
</span> <spring:theme code="license.apply.review.existing.shareholder"/></button>
    <button type="button" class="addNewButton btn btn_bold btn-outline taggle-button btn-normal"><span class="icon-bg"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="add-new" transform="translate(-222.936 -1127.936)">
    <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"/>
    <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
      <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"/>
      <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"/>
    </g>
  </g>
</svg>
</span><spring:theme code="license.apply.review.new.shareholder"/></button>
        </div>
    </div>

    <!--        This section should be shown if you click on Save shareholer                -->
    <div class="contentModule-section" id="shareholderQM1TableSection">
        <div class="tableModule">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="license.apply.review.name"/></th>
                        <th><spring:theme code="license.apply.review.type"/></th>
                        <th><spring:theme code="license.apply.percentage"/></th>
                        <th><spring:theme code="license.apply.review.nationality"/></th>
                        <th><spring:theme code="license.apply.review.legalstatus"/></th>
                        <th><spring:theme code="license.apply.review.delegate"/></th>
                        <th><spring:theme code="license.apply.review.delegateId"/></th>
                        <th class="tableModule-headItem tableModule-headItem_actionsCount_2"></th>
                    </tr>
                </thead>
                <tbody class="tableModule-body"></tbody>
            </table>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="btn btn-bg btn_bold addExistingButton btn-normal"><span class="icon-bg icon-ele"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="ORGANIZATION" transform="translate(-222.936 -1127.936)">
    <g id="Ellipse_35" data-name="Ellipse 35" transform="translate(222.936 1127.936)" fill="#1ac4dc" stroke="#00a6be" stroke-width="1">
      <circle cx="16" cy="16" r="16" stroke="none"/>
      <circle cx="16" cy="16" r="15.5" fill="none"/>
    </g>
    <path id="Path_2180" data-name="Path 2180" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.768 294.961)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"/>
  </g>
</svg></span>  <spring:theme code="license.apply.review.existing.shareholder"/></button>
            <button type="button" class="btn btn-outline btn_bold addNewButton taggle-button btn-normal"><span class="icon-bg"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="add-new" transform="translate(-222.936 -1127.936)">
    <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
    <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
      <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
      <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
    </g>
  </g>
</svg></span> <spring:theme code="license.apply.review.new.shareholder"/></button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1ExistingSection" style="display: none">
        <div class="contentModule-headline"><spring:theme code="license.apply.review.existing.shareholder"/></div>
     <hr class="hr"/>
        <div class="row">
            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="existingShareholderEntityNumber" class="form-control" placeholder="." value="" type="text"/>
                        <label class="control-label" for="existingShareholderEntityNumber"><spring:theme code="licence.apply.entershareholderentitynumber"/></label>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="existingShareholderName" class="form-control" placeholder="." value="" type="text" disabled="disabled"/>
                        <label class="control-label" for="existingShareholderName"><spring:theme code="license.apply.review.name"/></label>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="existingShareholderParentCompanyCountry" name="existingShareholderParentCompanyCountry" class="js-select2-oneColumn form-control" disabled="disabled"></select>
                        <label class="control-label" for="existingShareholderParentCompanyCountry"><spring:theme code="licence.apply.parentcompanycountry"/></label>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox formInputBox_group">
                    <div class="form-group">
                        <input id="existingShareholderSharesPercentage" name = "existingShareholderSharesPercentage" class="form-control" placeholder="." value="" type="text"/>
                        <label class="control-label" for="existingShareholderSharesPercentage"><spring:theme code="license.apply.review.shares.percentage"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text">%</span>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputFile">
                    <div class="form-group">
                        <input id="existingShareholderCommercialRegistrationFile" name="existingShareholderCommercialRegistrationFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                        <input id="existingShareholderCommercialRegistrationFileName" name="existingShareholderCommercialRegistrationFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="existingShareholderCommercialRegistrationFileName"><spring:theme code="licence.apply.commercial.registration"/></label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputFile">
                    <div class="form-group">
                        <input id="existingShareholderLastBudgetFile" name="existingShareholderLastBudgetFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                        <input id="existingShareholderLastBudgetFileName" name="existingShareholderLastBudgetFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="existingShareholderLastBudgetFileName"><spring:theme code="licence.apply.commercial.existing.investment"/></label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="cancelButton btn btn-normal btn_bold btn-outline w-25"><spring:theme code="general.cancel"/></button>
            <button id="validateAddShareholder" type="button" class="addButton btn btn-normal btn-bg btn-bg btn_bold w-25"><spring:theme code="licence.apply.validate.add"/></button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1NewSection">
        <div class="contentModule-headline"><spring:theme code="license.apply.review.new.shareholder"/></div>
    <hr class="hr"/>
        <div class="row">
            <div class="col-md-12">
                <div class="formRadioBox pt-3" id="shareholderType">
                    <div class="form-group justify-content-center">
                        <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licence.apply.shareholder.type"/></div>
                        <div class="form-item">
                            <input id="personType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Person"/>
                            <label for="personType" class="btn btn-bg btn-normal btn_bold person-type control-label"><span class="icon-bg icon-ele"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="ORGANIZATION" transform="translate(-222.936 -1127.936)">
    <g id="Ellipse_35" data-name="Ellipse 35" transform="translate(222.936 1127.936)" fill="#1ac4dc" stroke="#00a6be" stroke-width="1">
      <circle cx="16" cy="16" r="16" stroke="none"/>
      <circle cx="16" cy="16" r="15.5" fill="none"/>
    </g>
    <path id="Path_2180" data-name="Path 2180" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.768 294.961)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"/>
  </g>
</svg>
</span><span><spring:theme code="license.apply.shareholder.person"/></span></label>
                        </div>

                        <div class="form-item">
                            <input id="organizationType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Organization"/>
                            <label for="organizationType" class="btn btn-outline btn-normal btn_bold control-label organization-type"><span class="icon-bg icon-ele"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
  <g id="Person-inactive" transform="translate(-222.936 -1127.936)">
    <g id="Ellipse_35" data-name="Ellipse 35" transform="translate(222.936 1127.936)" fill="none" stroke="#00a6be" stroke-width="1">
      <circle cx="16" cy="16" r="16" stroke="none"/>
      <circle cx="16" cy="16" r="15.5" fill="none"/>
    </g>
    <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"/>
  </g>
</svg>
</span><span><spring:theme code="general.organization"/></span></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button class="cancelButton btn btn-normal btn_bold btn-outline w-25"><spring:theme code="general.cancel"/></button>
        </div>
    </div>

    <!-- section for type Person -->
    <div id="addShareholderQM1NewPersonSection" style="display: none">
        <div class="contentModule-section">
            <div class="contentModule-headline"><spring:theme code="licence.apply.shareholder.details"/></div>
          <hr class="mb-5 hr"/>
            <div class="row">
                <div class="col-md-6">
                    <div id="shareholderTitle" class="formRadioBox">
                        <div class="form-group">
                            <div class="formRadioBox-label"><spring:theme code="general.license.title"/></div>
                            <div class="form-item mt-2 mb-4">
                                <input id="personTitle" name="title" class="form-control" type="radio" value="Mr."/>
                                <label for="personTitle" class="btn-ctrl btn-bg btn_bold control-label"><spring:theme code="general.mr"/></label>
                            </div>

                            <div class="form-item mt-2 mb-4">
                                <input id="organizationTitle" name="title" class="form-control" type="radio" value="Mrs."/>
                                <label for="organizationTitle" class="btn-ctrl btn-bg btn_bold control-label"><spring:theme code="general.mrs"/></label>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="academicTitle" name="academicTitle" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="academicTitle"><spring:theme code="general.academic.title"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="firstNameArabic" name="firstNameArabic" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="firstNameArabic"><spring:theme code="general.firstname.arabic"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="lastNameArabic" name="lastNameArabic" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="lastNameArabic"><spring:theme code="general.lastname.arabic"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="fullNameEnglish" name="fullNameEnglish" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="fullNameEnglish"><spring:theme code="general.fullname.english"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox formInputBox_group">
                        <div class="form-group">
                            <input id="personSharesPercentage" name="sharesPercentage" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="personSharesPercentage"><spring:theme code="license.apply.review.shares.percentage"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text">%</span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox formInputBox_group ">
                        <div class="form-group">
                            <input id="dateOfBirth" name="dateOfBirth" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="dateOfBirth"><spring:theme code="general.dateofbirth"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text"><icon:calendar-gray/></span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="passportNumber" name="passportNumber" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="passportNumber"><spring:theme code="general.passport.number"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6 mb-4">
                    <div class="formInputBox formInputBox_group ">
                        <div class="form-group">
                            <input id="passportIssueDate" name="passportIssueDate" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="passportIssueDate"><spring:theme code="general.passport.issuedate"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text"><icon:calendar-gray/></span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6 mb-4">
                    <div class="formInputBox formInputBox_group ">
                        <div class="form-group">
                            <input id="passportExpiryDate" name="passportExpiryDate" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="passportExpiryDate"><spring:theme code="general.passport.expirydate"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text"><icon:calendar-gray/></span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6 mb-3">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="currentNationality" name="currentNationality" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="currentNationality"><spring:theme code="general.nationality.current"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6 mb-3">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="previousNationality" name="previousNationality" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="previousNationality"><spring:theme code="general.nationality.previous"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="country" name="country" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="country"><spring:theme code="general.country"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="premiumResident" name="premiumResident" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="premiumResident"><spring:theme code="license.premiumstatus"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="city" name="city" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="city"><spring:theme code="general.city"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="poBox" name="poBox" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="poBox"><spring:theme code="general.pobox"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="postalCode" name="postalCode" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="postalCode"><spring:theme code="general.postalcode"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="formInputBox-split">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="countryCodeForTelephone" name="countryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                                <label class="control-label control-label_mandatory" for="countryCodeForTelephone"><spring:theme code="general.country.code"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                        <div class="formInputBox formInputBox_big">
                            <div class="form-group">
                                <input id="telephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                                <label class="control-label control-label_mandatory" for="telephone"><spring:theme code="general.telephone"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox-split">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="countryCodeForMobile" name="countryCodeForMobile" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                                <label class="control-label control-label_mandatory" for="countryCodeForMobile"><spring:theme code="general.country.code"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                        <div class="formInputBox formInputBox_big">
                            <div class="form-group">
                                <input id="mobile" name="mobile" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                                <label class="control-label control-label_mandatory" for="mobile"><spring:theme code="general.mobilenumber"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="email" name="email" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="email"><spring:theme code="general.email"/></label>
                        </div>
                        <div class="help-block"  id="shareholderEmailValidation"></div>
                    </div>
                </div>
            </div>
        </div>

        <!--  Attachment  -->
        <div class="contentModule-section">
            <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="general.attachments"/></div>
           <hr class="hr"/>
            <div class="row">
                <div class="col-md-6">
                    <div class="formInputFile">
                        <div class="form-group">
                            <input id="passportFile" name="passportFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                            <input id="passportFileName" name="passportFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                            <label class="control-label control-label_mandatory" for="passportFileName"><spring:theme code="licence.apply.passport"/></label>
                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputFile">
                        <div class="form-group">
                            <input id="otherFile" name="otherFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                            <input id="otherFileName" name="otherFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                            <label class="control-label control-label_mandatory" for="otherFileName"><spring:theme code="licence.apply.other"/></label>
                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
        </div>

        <license:licenseApplyShareholders-delegate shareholderType="Person"/>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection pb-4">
                <button type="button" class="cancelButton btn  btn-normal btn_outline btn_bold w-25"><spring:theme code="general.cancel"/></button>
                <button type="button" class="saveNew btn btn-bg btn-normal btn_bold w-25"><spring:theme code="licence.apply.savenewshareholder"/></button>
       </div>
    </div>

    <!-- section for type organization -->
    <div id="addShareholderQM1NewOrganizationSection" style="display: none">
        <div class="contentModule-section">
            <div class="contentModule-headline"><spring:theme code="licence.apply.shareholder.details"/></div>
            <hr class="hr"/>
            <div class="row">
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="organizationNameEnglish" name="organizationNameEnglish" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="organizationNameEnglish"><spring:theme code="general.organization.name.english"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="organizationNameArabic" name="organizationNameArabic" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label" for="organizationNameArabic"><spring:theme code="general.organization.name.arabic"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="organizationLegalStatus" name="organizationLegalStatus" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="organizationLegalStatus"><spring:theme code="general.legalstatus"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="multinationalCompany" name="multinationalCompany" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="multinationalCompany"><spring:theme code="general.multinational.company"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyRegistrationNumber" name="companyRegistrationNumber" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyRegistrationNumber"><spring:theme code="general.company.registration.number"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="formInputBox formInputBox_group">
                        <div class="form-group">
                            <input id="companyCapital" name="companyCapital" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyCapital"><spring:theme code="general.capital"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text">SAR</span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox formInputBox_group">
                        <div class="form-group">
                            <input id="companySharesPercentage" name="companySharesPercentage" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companySharesPercentage"><spring:theme code="general.shares.percentage"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text">%</span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="companySection" name="companySection" class="js-select2-oneColumn js-select2-sortAlphabetically form-control"></select>
                            <label class="control-label control-label_mandatory" for="companySection"><spring:theme code="general.section"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="companyDivision" name="companyDivision" class="js-select2-oneColumn js-select2-sortAlphabetically form-control"></select>
                            <label class="control-label control-label_mandatory" for="companyDivision"><spring:theme code="general.division"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox formInputBox_group">
                        <div class="form-group">
                            <input id="parentCompanyName" name="parentCompanyName" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="parentCompanyName"><spring:theme code="general.company.parent"/></label>
                            <div class="formInputBox-append">
                                <span class="formInputBox-text formInputBox-text_tip js-tip"
                                      style="position: relative;z-index: 1000;"
                                      data-tip-title="Tooltip Information to be shown to the user."
                                      data-original-title="" title=""><icon:tipInfo/>
                                </span>
                            </div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="parentCompanyCountry" name="parentCompanyCountry" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="parentCompanyCountry"><spring:theme code="general.country.parent.company"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="companyCountry" name="companyCountry" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="companyCountry"><spring:theme code="general.country"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formSelectBox">
                        <div class="form-group">
                            <select id="companyCountryOfRegistration" name="companyCountryOfRegistration" class="js-select2-oneColumn form-control"></select>
                            <label class="control-label control-label_mandatory" for="companyCountryOfRegistration"><spring:theme code="general.country.registration"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyCity" name="companyCity" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyCity"><spring:theme code="general.city"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyAddress" name="companyAddress" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyAddress"><spring:theme code="general.address"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyPOBox" name="companyPOBox" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyPOBox"><spring:theme code="general.pobox"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyPostalCode" name="companyPostalCode" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyPostalCode"><spring:theme code="general.postalcode"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="formInputBox-split">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="companyCountryCodeForTelephone" name="companyCountryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                                <label class="control-label control-label_mandatory" for="companyCountryCodeForTelephone"><spring:theme code="general.country.code"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                        <div class="formInputBox formInputBox_big">
                            <div class="form-group">
                                <input id="companyTelephone" name="companyTelephone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                                <label class="control-label control-label_mandatory" for="companyTelephone"><spring:theme code="general.telephone"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox-split">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="companyCountryCodeForMobile" name="companyCountryCodeForMobile" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                                <label class="control-label control-label_mandatory" for="companyCountryCodeForMobile"><spring:theme code="general.country.code"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                        <div class="formInputBox formInputBox_big">
                            <div class="form-group">
                                <input id="companyMobile" name="companyMobile" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                                <label class="control-label control-label_mandatory" for="companyMobile"><spring:theme code="general.mobilenumber"/></label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyEmail" name="companyEmail" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyEmail"><spring:theme code="general.license.email"/></label>
                        </div>
                        <div class="help-block" id="companyEmailValidation"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="companyWebsite" name="companyWebsite" class="form-control" placeholder="." value="" type="text"/>
                            <label class="control-label control-label_mandatory" for="companyWebsite"><spring:theme code="licence.apply.website"/></label>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>
        </div>
       <!--  Attachment  -->
        <div class="contentModule-section">
            <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="general.attachments"/></div>
          <hr class="hr"/>
            <div class="row">
                <div class="col-md-6">
                    <div class="formInputFile">
                        <div class="form-group">
                            <input id="companyRegistrationFile" name="" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                            <input id="companyRegistrationFileName" name="companyRegistrationFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                            <label class="control-label control-label_mandatory" for="companyRegistrationFileName"><spring:theme code="licence.apply.commercial.registration.copy"/></label>
                            <div class="form-icon form-icon_browse"><svg id="Upload-icon" xmlns="http://www.w3.org/2000/svg" width="30.222" height="31.179" viewBox="0 0 30.222 31.179">
  <path id="Path_1958" data-name="Path 1958" d="M680.545,1506.881a1,1,0,0,0-1,1v13.074H653.323v-13.074a1,1,0,0,0-2,0v15.074h30.222v-15.074A1,1,0,0,0,680.545,1506.881Z" transform="translate(-651.323 -1491.776)" fill="#fff"/>
  <path id="Path_1959" data-name="Path 1959" d="M660.183,1500.854l5.251-5.251v17.622a1,1,0,0,0,2,0v-17.618l5.247,5.247a1,1,0,0,0,1.414-1.414l-7.663-7.664-7.663,7.664a1,1,0,0,0,1.414,1.414Z" transform="translate(-651.323 -1491.776)" fill="#fff"/>
</svg>
</div>
                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="formInputFile">
                        <div class="form-group">
                            <input id="companyFinancialStatementFile" name="" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                            <input id="companyFinancialStatementFileName" name="companyFinancialStatementFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                            <label class="control-label control-label_mandatory" for="companyFinancialStatementFileName"><spring:theme code="licence.apply.commercial.financialstatement"/> </label>
                            <div class="form-icon form-icon_browse"><svg id="Upload-icon" xmlns="http://www.w3.org/2000/svg" width="30.222" height="31.179" viewBox="0 0 30.222 31.179">
  <path id="Path_1958" data-name="Path 1958" d="M680.545,1506.881a1,1,0,0,0-1,1v13.074H653.323v-13.074a1,1,0,0,0-2,0v15.074h30.222v-15.074A1,1,0,0,0,680.545,1506.881Z" transform="translate(-651.323 -1491.776)" fill="#fff"/>
  <path id="Path_1959" data-name="Path 1959" d="M660.183,1500.854l5.251-5.251v17.622a1,1,0,0,0,2,0v-17.618l5.247,5.247a1,1,0,0,0,1.414-1.414l-7.663-7.664-7.663,7.664a1,1,0,0,0,1.414,1.414Z" transform="translate(-651.323 -1491.776)" fill="#fff"/>
</svg>
</div>
                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
                <div class="col-md-6" id="companyCheckFileAttachment" style="display: none">
                    <div class="formInputFile">
                        <div class="form-group">
                            <input id="companyMemoAssociationFile" name="" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                            <input id="companyMemoAssociationFileName" name="companyMemoAssociationFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                            <label class="control-label control-label_mandatory" for="companyMemoAssociationFileName"><spring:theme code="licence.apply.commercial.memoassociation"/> </label>
                            <div class="form-icon form-icon_browse"><icon:upload/></div>
                            <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                        </div>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>


        </div>
        <license:licenseApplyShareholders-delegate shareholderType="Organization"/>
         <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection pb-4">
                <button class="cancelButton btn btn-outline btn-normal btn_bold w-25"><spring:theme code="general.cancel"/></button>
                <button class="saveNew btn btn-bg btn-normal btn_bold w-25"><spring:theme code="licence.apply.savenewshareholder"/></button>
            </div>
	</div>
</div>
