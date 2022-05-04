<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="shareholderType" required="false" type="java.lang.String" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="organizationData" required="false" type="com.sap.ibso.eservices.facades.data.OrganizationShareholderData" %>
<%@ attribute name="personData" required="false" type="com.sap.ibso.eservices.facades.data.PersonShareholderData" %>

<c:choose>
    <c:when test="${not empty organizationData}">
        <c:set var="data" value="${organizationData}"/>
    </c:when>
    <c:when test="${not empty personData}">
        <c:set var="data" value="${personData}"/>
    </c:when>
</c:choose>

<div class="contentModule-section">
    <div class="contentModule contentModule-wrap">
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
            <span class="contentModule-headline"><spring:theme code="license.apply.shareholder.delegate" /></span>
            <div class="contentModule-headline-border"></div>
        </div>
    </div>
    <div class="row">
    <div class="col-md-12 justify-content-center">
    <a class="btn btn_link js-tip d-block w-100 delegate-entity" style="padding-top: 10px;" data-container="body" data-tip-id="delegateToolTip"
           data-tip-class="delegateToolTip" data-trigger="click"><spring:theme
                code="text.account.profile.license.shareholders.tooltip.heading"/><icon:tipInfo/></a>
        <div class="tooltip_content" id="delegateToolTip">
            <h2><span><spring:theme code="text.account.profile.license.shareholders.tooltip.heading"/></span></h2>
            <p style="margin: 10px">
                <spring:theme code="text.account.profile.license.shareholders.tooltip.body"/>
            </p>
        </div>
    </div>
    </div>
    <div class="formRadioBox-wrapper" id="delegateSectionQuestion">
       <div class="row">
            <div class="col-md-6">
                <span><spring:theme code="text.account.profile.license.shareholders.que.wantdelegate"/></span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group" <c:if test="${not empty data.delegateInfo}"> data-delegate="${data.delegateInfo.delegate eq true}"</c:if>>
                        <div class="form-item">
                            <input type="radio" name="delegateInfo.delegate" id="${shareholderType.concat('DelegateYES')}" class="form-control delegateYES" value="true" ${not empty data.delegateInfo && data.delegateInfo.delegate eq true ? 'checked="checked"' : ''}/>
                            <label for="${shareholderType.concat('DelegateYES')}" class="btn-ctrl btn-outline btn_bold control-label" id="${shareholderType.concat('DelegateYESLable')}"><spring:theme code="text.account.profile.license.shareholders.delegate.yes"/></label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="delegateInfo.delegate" id="${shareholderType.concat('DelegateNO')}" value="false" class="form-control delegateNO" ${not empty data.delegateInfo && data.delegateInfo.delegate eq false ? 'checked="checked"' : ''}/>
                            <label for="${shareholderType.concat('DelegateNO')}" id="${shareholderType.concat('DelegateNOLable')}" class="btn-ctrl btn-bg btn_bold control-label"><spring:theme code="text.account.profile.license.shareholders.delegate.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="delegateSection">
        <div class="formRadioBox-wrapper" id="showDelegateQuestion" >
            <div class="row">
                <div class="col-md-6">
					<span><spring:theme code="text.account.profile.license.shareholders.isDelegate"/></span>
                </div>
                <div class="col-md-6">
                    <div class="formRadioBox">
                        <div class="form-group">
                            <div class="form-item">
                                <input type="radio" name="delegateInfo.delegateYourself" id="hasDelegateYES" value="true" class="form-control" ${not empty data.delegateInfo  && data.delegateInfo.delegateYourself eq true ? 'checked="checked"' : ''}/>
                                <label for="hasDelegateYES" id="hasDelegateYESLabel" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="text.account.profile.license.shareholders.hasDelegate.yes"/></label>
                            </div>
                            <div class="form-item">
                                <input type="radio" name="delegateInfo.delegateYourself" id="hasDelegateNO" value="false" class="form-control" ${not empty data.delegateInfo  && data.delegateInfo.delegateYourself eq false ? 'checked="checked"' : ''}/>
                                <label for="hasDelegateNO" id="hasDelegateNOLabel" class="btn btn-ctrl btn_bold control-label"><spring:theme code="text.account.profile.license.shareholders.hasDelegate.no"/></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="delegate">
            <div class="row" id="delegateDetails" >
                <div class="col-md-6 pt-2">
					<formElement:formSelectBoxCustom idKey="idType" labelKey="license.apply.shareholder.idType"
													 path="delegateInfo.delegateIdentityType"
													 selectCSSClass="js-select2-oneColumn validate__delegate-mandatory" labelCSS="control-label_mandatory"
                                                     selectedDataValue="${data.delegateInfo.delegateIdentityType}" />
<%--                    <div class="formSelectBox">--%>
<%--                        <div class="form-group">--%>
<%--                            <select id="idType" name="delegateInfo.delegateIdentityType"--%>
<%--                                    class="js-select2-oneColumn form-control">--%>
<%--                            </select>--%>
<%--                            <label class="control-label control-label_mandatory" for="idType"><spring:theme--%>
<%--                                    code="license.apply.shareholder.idType"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                </div>
                <div class="col-md-6">
					<formElement:formInputBoxCustom path="delegateInfo.delegateIdentityNumber" labelKey="license.apply.shareholder.idNumber"
											  idKey="idNumber" labelCSS="control-label_mandatory" inputCSS="validate__delegate-mandatory"/>
<%--                    <div class="formInputBox">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="idNumber" name="delegateInfo.delegateIdentityNumber" class="form-control"--%>
<%--                                   placeholder="." value="" type="text"/> <label--%>
<%--                                class="control-label control-label_mandatory" for="idNumber"><spring:theme--%>
<%--                                code="license.apply.shareholder.idNumber"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                </div>
                <div class="col-md-6">
                    <c:set var="calendar">
                        <icon:calendar-gray/>
                    </c:set>
                    <formElement:formInputBoxCustom idKey="delegateDateofBirth"
                                                    labelKey="license.apply.shareholder.dateOfBirth" path="delegateInfo.delegateDateOfBirth"
                                                    labelCSS="control-label_mandatory" inputBoxCode="${calendar}"
                                                    inputBoxCSS="formInputBox_group" inputCSS="validate__delegate-mandatory"/>
<%--                    <div class="formInputBox formInputBox_group ">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="delegateDateofBirth" name="delegateInfo.delegateDateOfBirth" class="form-control " placeholder="." value="" type="text"/>--%>
<%--                            <label class="control-label control-label_mandatory" for="delegateDateofBirth"><spring:theme code="license.apply.shareholder.dateOfBirth"/></label>--%>
<%--                            <div class="formInputBox-append">--%>
<%--                                <span class="formInputBox-text"><icon:calendar-gray/></span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                </div>
                <div class="col-md-6" id="nicVerifyBtnSection">
                    <a class="btn btn_bold pt-2 mt-4" id="verifyDetailsShow" data-nic-verified="false"><spring:theme code="license.apply.shareholder.verify"/></a>
                    <input type="checkbox" id="isNicVerified" name="delegateInfo.nicVerified" value="true" class="hidden" ${data.delegateInfo.nicVerified ? "checked=checked" : 0}>
                    <div class="inputValidationError" style="display: none;color: #ff4c4a;line-height: 1.2;margin-top: 10px;">
                        <spring:theme code="validation.licenseApply.shareholder.invalidInputValidation"
                                      text="Please click on 'Input Validation' to save Shareholder"/>
                    </div>
                </div>
            </div>
            <div id="verifyDelegateDetails" ${not empty data.delegateInfo  && data.delegateInfo.delegateYourself eq false ? '' : 'style="display: none;"'}>
                <div class="row">
                    <div class="col-md-6">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="license.apply.shareholder.delegateDetails.title"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <formElement:formInputBox path="delegateInfo.delegateFirstNameArabic"
                                                  labelKey="general.firstname.arabic" maxlength="40"
                                                  idKey="delegateFirstNameArabic" labelCSS="control-label_mandatory"
                                                  inputCSS="validate__delegate-mandatory validate__no-special-chars-with-arabic"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateFirstNameArabic" name="delegateInfo.delegateFirstNameArabic" class="form-control" placeholder="." value="" type="text"/> --%>
<%--                                <label class="control-label control-label_mandatory" for="delegateFirstNameArabic"><spring:theme code="general.firstname.arabic"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>

                    <div class="col-md-6">
                        <formElement:formInputBox path="delegateInfo.delegateLastNameArabic" disabled="disabled"
                                                  labelKey="general.lastname.arabic" maxlength="40"
                                                  idKey="delegateLastNameArabic" labelCSS="control-label_mandatory"
                                                  inputCSS="validate__delegate-mandatory validate__no-special-chars-with-arabic"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateLastNameArabic" name="delegateInfo.delegateLastNameArabic" class="form-control" placeholder="." value="" type="text"/> --%>
<%--                                <label class="control-label control-label_mandatory" for="delegateLastNameArabic"><spring:theme code="general.lastname.arabic"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>

                    <div class="col-md-6">
                        <formElement:formInputBox  path="delegateInfo.delegateFullName" maxlength="100" disabled="disabled"
                                                  labelKey="general.fullname.english"
                                                  idKey="delegateFullNameEnglish" labelCSS="control-label_mandatory"
                                                  inputCSS="validate__delegate-mandatory validate__no-special-chars-with-arabic"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateFullNameEnglish" name="delegateInfo.delegateFullName" class="form-control" placeholder="." value="" type="text"/> --%>
<%--                                <label class="control-label control-label_mandatory" for="delegateFullNameEnglish"><spring:theme code="general.fullname.english"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6">
                        <formElement:formSelectBoxCustom idKey="delegateGender" disabled="disabled"
                                                         labelKey="license.apply.shareholder.delegateDetails.gender"
                                                         path="delegateInfo.gender" selectCSSClass="js-select2-oneColumn validate__delegate-mandatory"
                                                         selectedDataValue="${data.delegateInfo.gender}"/>
<%--                        <div class="formSelectBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <select id="delegateGender" name="delegateInfo.gender" class="js-select2-oneColumn form-control"></select>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegateGender"><spring:theme code="license.apply.shareholder.delegateDetails.gender"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6">
                        <formElement:formInputBoxCustom idKey="delegateIssueDate"
                                                        labelKey="license.apply.shareholder.issueDate" path="delegateInfo.idIssueDate"
                                                        labelCSS="control-label_mandatory" inputBoxCode="${calendar}"
                                                        inputBoxCSS="formInputBox_group" inputCSS="validate__delegate-mandatory"/>
<%--                        <div class="formInputBox formInputBox_group ">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateIssueDate" name="delegateInfo.idIssueDate" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegateIssueDate"><spring:theme code="license.apply.shareholder.issueDate"/></label>--%>
<%--                                <div class="formInputBox-append">--%>
<%--                                    <span class="formInputBox-text"><icon:calendar-gray/></span>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6">
                        <formElement:formInputBoxCustom idKey="delegateExpiryDate" disabled="disabled"
                                                        labelKey="license.apply.shareholder.expiryDate" path="delegateInfo.idExpiryDate"
                                                        labelCSS="control-label_mandatory" inputBoxCode="${calendar}"
                                                        inputBoxCSS="formInputBox_group" inputCSS="validate__delegate-mandatory"/>
<%--                        <div class="formInputBox formInputBox_group ">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateExpiryDate" name="delegateInfo.idExpiryDate" class="form-control " placeholder="." value="" type="text"/> --%>
<%--                                <label class="control-label control-label_mandatory" for="delegateExpiryDate"><spring:theme code="license.apply.shareholder.expiryDate"/></label>--%>
<%--                                <div class="formInputBox-append">--%>
<%--                                    <span class="formInputBox-text"><icon:calendar-gray/></span>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6" id="delegateCountryDiv">
                        <formElement:formSelectBoxCustom idKey="delegateCountry"
                                                         labelKey="general.country"
                                                         path="delegateInfo.delegateCountry" selectCSSClass="js-select2-oneColumn validate__delegate-mandatory"
                                                         selectedDataValue="${data.delegateInfo.delegateCountry}"/>
<%--                        <div class="formSelectBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <select id="delegateCountry" name="delegate.country" class="js-select2-oneColumn form-control"></select>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegateCountry"><spring:theme code="general.country"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6" id="delegateNationalityDiv">
                        <formElement:formSelectBoxCustom idKey="delegateNationality"
                                                         labelKey="license.apply.shareholder.nationality"
                                                         path="delegateInfo.delegateNationality" selectCSSClass="js-select2-oneColumn validate__delegate-mandatory"
                                                         selectedDataValue="${data.delegateInfo.delegateNationality}"/>
<%--                        <div class="formSelectBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <select id="delegateNationality" name="delegateInfo.nationality" class="js-select2-oneColumn form-control"></select>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegateNationality"><spring:theme code="license.apply.shareholder.nationality"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6" id="postalCodeDiv" style="display: none;">
                        <formElement:formInputBox path="delegateInfo.delegatePostalCode" labelKey="general.postalcode"
                                                  idKey="delegatePostalCode" labelCSS="control-label_mandatory" inputCSS="validate__numbers-only" maxlength="5"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegatePostalCode" name="delegateInfo.postalCode" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegatePostalCode"><spring:theme code="general.postalcode"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6" id="poBoxDiv" style="display: none;">
                        <formElement:formInputBox path="delegateInfo.delegatePoBox" labelKey="general.pobox"
                                                  idKey="delegatePOBox" labelCSS="control-label_mandatory" inputCSS="validate__numbers-only" maxlength="5"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegatePOBox" name="delegateInfo.delegatePoBox" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegatePOBox"><spring:theme code="general.pobox"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox-split">
                            <formElement:formInputBoxCustom idKey="delegateCountryCodeForTelephone" labelKey="general.country.code"
                                                            path="delegateInfo.delegateCountryCodeTel" labelCSS="control-label_mandatory"
                                                            inputCSS="form-control_preNumber validate__delegate-mandatory validate__numbers-only"
                                                            maxlength="5" dataValue="${data.delegateInfo.delegateCountryCodeTel}"/>
<%--                            <div class="formInputBox">--%>
<%--                                <div class="form-group">--%>
<%--                                    <input id="delegateCountryCodeForTelephone" name="delegateInfo.delegateCountryCodeTel" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
<%--                                    <label class="control-label control-label_mandatory" for="delegateCountryCodeForTelephone"><spring:theme code="general.country.code"/></label>--%>
<%--                                </div>--%>
<%--                                <div class="help-block"></div>--%>
<%--                            </div>--%>
                            <formElement:formInputBox idKey="delegateTelephone" labelKey="general.telephone" path="delegateInfo.delegateTelephoneNumber"
                                                      labelCSS="control-label_mandatory" inputCSS="form-control_labeled validate__delegate-mandatory validate__numbers-only"
                                                      inputBoxCSS="formInputBox_big" maxlength="30"/>
<%--                            <div class="formInputBox formInputBox_big">--%>
<%--                                <div class="form-group">--%>
<%--                                    <input id="delegateTelephone" name="delegateInfo.delegateTelephoneNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
<%--                                    <label class="control-label control-label_mandatory" for="delegateTelephone"><spring:theme code="general.telephone"/></label>--%>
<%--                                </div>--%>
<%--                                <div class="help-block"></div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox-split">
                            <formElement:formInputBoxCustom idKey="delegateCountryCodeForMobile" labelKey="general.country.code"
                                                      path="delegateInfo.delegateCountryCodeMobile" labelCSS="control-label_mandatory"
                                                      inputCSS="form-control_preNumber validate__delegate-mandatory validate__numbers-only"
                                                      maxlength="5" dataValue="${data.delegateInfo.delegateCountryCodeMobile}"/>
<%--                            <div class="formInputBox">--%>
<%--                                <div class="form-group">--%>
<%--                                    <input id="delegateCountryCodeForMobile" name="delegateInfo.delegateCountryCodeMobile" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
<%--                                    <label class="control-label control-label_mandatory" for="delegateCountryCodeForMobile"><spring:theme code="general.country.code"/></label>--%>
<%--                                </div>--%>
<%--                                <div class="help-block"></div>--%>
<%--                            </div>--%>
                            <formElement:formInputBox idKey="delegateMobile" labelKey="general.mobilenumber" path="delegateInfo.delegateMobileNumber"
                                                      labelCSS="control-label_mandatory" inputCSS="form-control_labeled validate__delegate-mandatory validate__numbers-only"
                                                      inputBoxCSS="formInputBox_big" maxlength="30"/>
<%--                            <div class="formInputBox formInputBox_big">--%>
<%--                                <div class="form-group">--%>
<%--                                    <input id="delegateMobile" name="delegateInfo.delegateMobileNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
<%--                                    <label class="control-label control-label_mandatory" for="delegateMobile"><spring:theme code="general.mobilenumber"/></label>--%>
<%--                                </div>--%>
<%--                                <div class="help-block"></div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <formElement:formInputBox path="delegateInfo.delegateEmail" labelKey="general.license.email"
                                                  idKey="delegateEmail" labelCSS="control-label_mandatory"
                                                  inputCSS="validate__delegate-email"/>
<%--                        <div class="formInputBox">--%>
<%--                            <div class="form-group">--%>
<%--                                <input id="delegateEmail" name="delegateInfo.delegateEmail" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                                <label class="control-label control-label_mandatory" for="delegateEmail"><spring:theme code="general.license.email"/></label>--%>
<%--                            </div>--%>
<%--                            <div class="help-block"></div>--%>
<%--                        </div>--%>
                    </div>
                </div>
                <div class="row" id="delegateAttachments">
                    <div class="col-md-6">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="licence.apply.attachments"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6" id="loaFileDiv" style="display: none;">
                        <div class="formInputFile ${not empty data.delegateInfo.authorisationLetter ? "active" : ""}">
                            <c:set var="authorisationLetterErrors">
                                <form:errors path="delegateInfo.authorisationLetter"/>
                            </c:set>
                            <div class="form-group ${not empty authorisationLetterErrors ? 'has-error' : ''}">
                                <input id="authorizationLetterFile" name="authorizationLetterFile" class="form-control js-inputFile " data-maxsize="5" type="file" accept="application/pdf" value=""/>
                                <input id="authorizationLetterFileName" name="authorizationLetterFileName" class="form-control" type="text" value="" placeholder="${data.delegateInfo.authorisationLetter.fileName}" readonly tabindex="-1"/>
                                <label class="control-label control-label_mandatory" for="authorizationLetterName"><spring:theme code="license.apply.shareholder.authorizationLetter"/></label>
                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                            </div>
                            <div class="help-block">${authorisationLetterErrors}</div>
                        </div>
                    </div>
                    <div class="col-md-6" style="display: none;" id="idCopyFileDiv">
                        <div class="formInputFile ${not empty data.delegateInfo.saudiIdCopy ? "active" : ""}">
                            <c:set var="saudiIdCopyErrors">
                                <form:errors path="delegateInfo.saudiIdCopy"/>
                            </c:set>
                            <div class="form-group ${not empty saudiIdCopyErrors ? 'has-error' : ''}">
                                <input id="saudiIdCopy" name="saudiIdCopy" class="form-control js-inputFile" type="file" accept="application/pdf" data-maxsize="5" value=""/>
                                <input id="idCopyFileName" name="idCopyFileName" class="form-control" type="text" value="" placeholder="${data.delegateInfo.saudiIdCopy.fileName}" readonly tabindex="-1"/>
                                <label class="control-label control-label_mandatory saudiIdCopy" for="idCopyFileName"><spring:theme code="license.apply.shareholder.idCopyFile"/> </label>
                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                            </div>
                            <div class="help-block">${saudiIdCopyErrors}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
