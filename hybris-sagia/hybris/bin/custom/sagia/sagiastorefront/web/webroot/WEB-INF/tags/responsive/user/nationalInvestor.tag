<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="panelTabs-head" id="register-national-investor">
    <icon:invest/>
    <span class="panelTabs-label"><spring:theme code="register.national.investor.heading.title"/></span>
</div>
<div class="panelTabs-body panelModule panelModule_halfRadius panelModule_transparent90">
    <div class="js-panelTabs_nested panelTabs_nested">
        <div class="panelTabs_nested-head">
            <span class="panelTabs_nested-link"><spring:theme code="register.national.investor.withcrnumber"/></span>
        </div>
        <div class="panelTabs_nested-body">
            <div class="row">
                <div class="col-md-4">
                    <div id="sagia-cms-help-national-investor-cr"></div>
                </div>

                <div class="col-md-8">
                    <div class="accountLogin-content-slot accountLogin-content-slot_right">
                        <div class="contentModule">
                            <c:if test="${not empty processingTime}">
                                <div class="serviceTime">
                                    <div class="serviceTime-label"><spring:theme code="average.service.time" /></div>
                                    <div class="serviceTime-detail">
                                        <c:choose>
                                            <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                                <span class="serviceTime-highlight">${processingTime.days}</span>
                                                <spring:theme code="average.processingTime.days"/>
                                                <span class="serviceTime-highlight">${processingTime.hours}</span>
                                                <spring:theme code="average.processingTime.hours"/>
                                            </c:when>
                                            <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                                <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                                <spring:theme code="average.processingTime.minutes"/>
                                                <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                                <spring:theme code="average.processingTime.seconds"/>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:if>
                            <div class="contentModule-headline contentModule-headline_larger">
                                <spring:theme code="register.national.investor.title"/>
                            </div>
                            <div class="contentModule-section">
                                <div class="contentModule-headline"><spring:theme code="register.national.investor.crnumber"/></div>
                                <div class="row">
                                    <div class="col">
                                        <div class="formInputBox">
                                            <div class="form-group cr-validation">
                                                <input id="inputCRNumber" name="crNumber" class="form-control" placeholder="." type="text" value="">
                                                <label class="control-label" for="crNumber"><spring:theme code="register.national.investor.crnumber.label"/></label>
                                                <div class="help-block" id="inputCRNumber-error"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="formSelectBox" id="registerNationalInvestorSelectLanguageDiv">
                                <div class="form-group">
                                    <select class="js-select2-oneColumn" id="registerNationalInvestorSelect" title="registerNationalInvestorSelect" name="language">
                                        <c:if test="${currentLanguage.isocode == 'en'}">
                                            <option value="en" selected="selected">English</option>
                                            <option value="ar">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
                                        </c:if>
                                        <c:if test="${currentLanguage.isocode == 'ar'}">
                                            <option value="en">English</option>
                                            <option value="ar" selected="selected">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
                                        </c:if>
                                    </select>
                                    <label class="control-label" for="registerNationalInvestorSelectLanguageSelect"><spring:theme code="general.language"/></label>
                                </div>
                            </div>

                            <div class="contentModule-actions contentModule-actions_centered accountLogin-content-formSubmitSection">
                                <div>
                                    <button type="button" class="btn load-investor"><spring:theme code="register.national.investor.validatecr"/></button>
                                    <div class="accountLogin-content-formSubmitSection-formSwitchText text-center">
                                        <spring:theme code="register.national.investor.alreadyhaveaccount"/><br />
                                        <a class="accountLogin-content-toggleBtn"><spring:theme code="register.national.investor.login"/></a>&nbsp;<spring:theme code="register.national.investor.here"/>
                                    </div>
                                </div>
                            </div>
                            <!-- empty section only to visually separate from content above, should be removed when correct one is in placed -->
                            <div class="contentModule-section"></div>
                            <!-- end empty section placeholder -->
                            <div class="valid-cr-fields" style="display: none">
                                <spring:url value="/register/national-investor-cr" var="registerNationalInvestorCr" htmlEscape="false"/>
                                <form:form method="post" modelAttribute="nationalInvestorForm" action="${registerNationalInvestorCr}" enctype="multipart/form-data">
                                    <form:hidden path="crNumber"/>
                                    <ol class="enumList enumList_validatable enumList_collapsible js-stepsList" data-keyClass="enumList">
                                        <li class="organization-info enumList-item">
                                            <div id="organizationInfo" class="enumList-item-head js-stepsList-toggle">
                                                <div class="contentModule-headline"><spring:theme code="register.national.investor.organizationinfo"/></div>
                                            </div>
                                            <div class="enumList-item-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="nameArabic" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="nameArabic">
                                                                    <spring:theme code="register.national.investor.companyname.arabic"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="nameEnglish" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="nameEnglish">
                                                                    <spring:theme code="register.national.investor.companyname.english"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="capital" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="capital">
                                                                    <spring:theme code="register.national.investor.companyname.capital"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="legalStatus" cssClass="js-select2 form-control">
                                                                    <form:option value=""/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="legalStatus">
                                                                    <spring:theme code="register.national.investor.companyname.legalstatus"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-4">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="compNatType" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                    <form:option value="SAUDI"><spring:theme code="register.national.investor.saudientity"/></form:option>
                                                                    <form:option value="GCC"><spring:theme code="register.national.investor.gccentity"/></form:option>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="compNatType">
                                                                    <spring:theme code="register.national.investor.type"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-8">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="compNationality" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="compNationality">
                                                                    <spring:theme code="text.specialservices.nationality"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="country" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                    <form:options items="${nipGCCCountries}"
                                                                                  itemValue="${not empty itemValue ? itemValue :'code'}"
                                                                                  itemLabel="${not empty itemLabel ? itemLabel :'name'}"
                                                                                  htmlEscape="true"/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="country">
                                                                    <spring:theme code="general.country"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-12">
                                                        <div class="formSelectBox jqRegion">
                                                            <div class="form-group">
                                                                <input type="text" class="form-control form-control_labeled jqTxtRegion" placeholder="." value="" style="display: none"/>
                                                                <select class="js-select2-oneColumn form-control jqSelRegion" id="region" name="region">
                                                                    <option></option>
                                                                </select>
                                                                <label class="control-label control-label_mandatory" for="region">
                                                                    <spring:theme code="general.regionorstate"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formSelectBox jqCity">
                                                            <div class="form-group">
                                                                <input type="text" class="form-control form-control_labeled jqTxtCity" placeholder="." value="" style="display: none"/>
                                                                <select class="js-select2-oneColumn form-control jqSelCity" id="city" name="city">
                                                                    <option></option>
                                                                </select>
                                                                <label class="control-label control-label_mandatory" for="city">
                                                                    <spring:theme code="general.city"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox-split">
                                                            <div class="formInputBox">
                                                                <div class="form-group">
                                                                    <input id="mobilePrefix" class="form-control form-control_preNumber" placeholder="." type="text" value="" readonly="readonly">
                                                                    <label class="control-label" for="mobilePrefix">
                                                                        <spring:theme code="general.countrycode"/>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="formInputBox formInputBox_big">
                                                                <div class="form-group">
                                                                    <form:input path="mobile" cssClass="form-control" placeholder="."/>
                                                                    <label class="control-label control-label_mandatory" for="mobile">
                                                                        <spring:theme code="general.mobilenumber"/>
                                                                    </label>
                                                                    <div class="help-block" style="margin-top: 6px;"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input id="email1" path="email" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="email">
                                                                    <spring:theme code="register.email"/>
                                                                </label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_right accountLogin-content-formSubmitSubSection">
                                                    <div><button type="button" class="btn js-stepsList-next"><spring:theme code="general.continue.button"/></button></div>
                                                </div>
                                            </div>
                                        </li>

                                        <li class="organization-details enumList-item">
                                            <div class="enumList-item-head js-stepsList-toggle">
                                                <div class="contentModule-headline"><spring:theme code="general.additionaldetails"/></div>
                                            </div>
                                            <div class="enumList-item-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="isicSection" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                    <form:options items="${nipISICSections}"
                                                                                  itemValue="${not empty itemValue ? itemValue :'code'}"
                                                                                  itemLabel="${not empty itemLabel ? itemLabel :'name'}"
                                                                                  htmlEscape="true"/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="isicSection"><spring:theme code="profile.company.register.ISICSection"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="isicDivision" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="isicDivision"><spring:theme code="profile.company.register.ISICDivision"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="number700" cssClass="form-control form-control_preNumber" placeholder="."/>
                                                                <label class="control-label" for="number700"><spring:theme code="govDocs.700number"/></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="zakatNumber" cssClass="form-control form-control_labeled" placeholder="."/>
                                                                <label class="control-label" for="zakatNumber"><spring:theme code="govDocs.zakatNo"/></label>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="col-md-6">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="molNumber" cssClass="form-control form-control_preNumber" placeholder="."/>
                                                                <label class="control-label" for="molNumber"><spring:theme code="govDocs.molNo"/></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="formInputBox formInputBox_big">
                                                            <div class="form-group">
                                                                <form:input path="gosiNumber" cssClass="form-control form-control_labeled" placeholder="."/>
                                                                <label class="control-label" for="gosiNumber"><spring:theme code="govDocs.gosiNo"/></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                                                    <div><button type="button" class="btn btn-secondary js-stepsList-prev"><spring:theme code="general.back"/></button></div>
                                                    <div><button type="button" class="btn js-stepsList-next"><spring:theme code="general.continue.button"/></button></div>
                                                </div>
                                            </div>
                                        </li>

                                        <li class="contact-details enumList-item">
                                            <div class="enumList-item-head js-stepsList-toggle">
                                                <div class="contentModule-headline"><spring:theme code="text.specialservices.contact.details"/></div>
                                            </div>
                                            <div class="enumList-item-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="contactName" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="contactName"><spring:theme code="nationalInvestor.contact.person.name"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="contactCountry" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                    <form:options items="${nipGCCCountries}"
                                                                                  itemValue="${not empty itemValue ? itemValue :'code'}"
                                                                                  itemLabel="${not empty itemLabel ? itemLabel :'name'}"
                                                                                  htmlEscape="true"/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="contactCountry"><spring:theme code="nationalInvestor.contact.person.countryOfResidence"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formSelectBox">
                                                            <div class="form-group">
                                                                <form:select path="contactNationality" cssClass="js-select2-oneColumn form-control">
                                                                    <form:option value=""/>
                                                                    <form:options items="${nipGCCCountries}"
                                                                                  itemValue="${not empty itemValue ? itemValue :'code'}"
                                                                                  itemLabel="${not empty itemLabel ? itemLabel :'nationalityText'}"
                                                                                  htmlEscape="true"/>
                                                                </form:select>
                                                                <label class="control-label control-label_mandatory" for="contactNationality"><spring:theme code="text.account.profile.license.shareholders.nationality"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="formSelectBox jqContactRegion">
                                                            <div class="form-group">
                                                                <input type="text" class="form-control form-control_labeled jqTxtContactRegion" placeholder="." value="" style="display: none"/>
                                                                <select class="js-select2-oneColumn form-control jqSelContactRegion" id="contactRegion" name="contactRegion">
                                                                    <option></option>
                                                                </select>
                                                                <label class="control-label control-label_mandatory" for="contactRegion"><spring:theme code="general.regionorstate"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="formSelectBox jqContactCity">
                                                            <div class="form-group">
                                                                <input type="text" class="form-control form-control_labeled jqTxtContactCity" placeholder="." value="" style="display: none"/>
                                                                <select class="js-select2-oneColumn form-control jqSelContactCity" id="contactCity" name="contactCity" title="contactCity">
                                                                    <option></option>
                                                                </select>
                                                                <label class="control-label control-label_mandatory" for="contactCity"><spring:theme code="general.city"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox-split">
                                                            <div class="formInputBox">
                                                                <div class="form-group">
                                                                    <input id="contactMobilePrefix" class="form-control form-control_preNumber" placeholder="." type="text" value="" readonly="readonly">
                                                                    <label class="control-label" for="contactMobilePrefix"><spring:theme code="general.countrycode"/></label>
                                                                </div>
                                                            </div>
                                                            <div class="formInputBox formInputBox_big">
                                                                <div class="form-group">
                                                                    <form:input path="contactMobile" cssClass="form-control form-control_labeled" placeholder="."/>
                                                                    <label class="control-label control-label_mandatory" for="contactMobile"><spring:theme code="general.mobilenumber"/></label>
                                                                    <div class="help-block"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputBox">
                                                            <div class="form-group">
                                                                <form:input path="contactEmail" cssClass="form-control" placeholder="."/>
                                                                <label class="control-label control-label_mandatory" for="contactEmail"><spring:theme code="guest.email"/></label>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                                                    <div><button type="button" class="btn btn-secondary js-stepsList-prev"><spring:theme code="general.back"/></button></div>
                                                    <div><button type="button" class="btn js-stepsList-next"><spring:theme code="general.continue.button"/></button></div>
                                                </div>
                                            </div>
                                        </li>

                                        <li class="attachments enumList-item">
                                            <div class="enumList-item-head js-stepsList-toggle">
                                                <div class="contentModule-headline"><spring:theme code="nationalInvestor.attachments.title"/></div>
                                            </div>
                                            <div class="enumList-item-body">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="formInputFile">
                                                            <div class="form-group">
                                                                <input name="files[0]" class="form-control js-inputFile" type="file" accept="application/pdf">
                                                                <input class="form-control" type="text" value="" placeholder="" readonly="readonly" tabindex="-1">
                                                                <label class="control-label "><spring:theme code="nationalInvestor.attachment1.description"/></label>
                                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputFile">
                                                            <div class="form-group">
                                                                <input name="files[1]" class="form-control js-inputFile" type="file" accept="application/pdf">
                                                                <input class="form-control" type="text" value="" placeholder="" readonly="readonly" tabindex="-1">
                                                                <label class="control-label "><spring:theme code="nationalInvestor.attachment2.description"/></label>
                                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputFile">
                                                            <div class="form-group">
                                                                <input name="files[2]" class="form-control js-inputFile" type="file" accept="application/pdf">
                                                                <input class="form-control" type="text" value="" placeholder="" readonly="readonly" tabindex="-1">
                                                                <label class="control-label "><spring:theme code="nationalInvestor.attachment3.description"/></label>
                                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputFile">
                                                            <div class="form-group">
                                                                <input name="files[3]" class="form-control js-inputFile" type="file" accept="application/pdf">
                                                                <input class="form-control" type="text" value="" placeholder="" readonly="readonly" tabindex="-1">
                                                                <label class="control-label"><spring:theme code="nationalInvestor.attachment4.description"/></label>
                                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="formInputFile">
                                                            <div class="form-group">
                                                                <input name="files[4]" class="form-control js-inputFile" type="file" accept="application/pdf">
                                                                <input class="form-control" type="text" value="" placeholder="" readonly="readonly" tabindex="-1">
                                                                <label class="control-label "><spring:theme code="nationalInvestor.attachment5.description"/></label>
                                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                                                <div class="help-block"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="contentModule-actions contentModule-actions_noPadding contentModule-actions_spaceBetween accountLogin-content-formSubmitSubSection">
                                                    <div><button type="button" class="btn btn-secondary js-stepsList-prev"><spring:theme code="nationalInvestor.back.button.text"/></button></div>
                                                </div>
                                            </div>
                                        </li>
                                    </ol>

                                    <div class="formCheckBox formCheckBox_block formCheckBox_belowPanel">
                                        <div class="form-group">
                                            <div class="form-group">
                                                <formElement:termsAndConditionsCheckbox event="REGISTRATION" id="termsAndConditions" path="termsAndConditionsChecked"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="contentModule-actions contentModule-actions_centered accountLogin-content-formSubmitSection">
                                        <div><button type="submit" class="btn"><spring:theme code="nationalInvestor.submit.button.text"/></button></div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panelTabs_nested-head" id="noCrNumberPanel">
            <span class="panelTabs_nested-link"><spring:theme code="register.national.investor.nocrnumber"/> </span>
        </div>
        <div class="panelTabs_nested-body">
            <div class="row">
                <div class="col-md-4">
                    <div id="sagia-cms-help-national-investor-no-cr"></div>
                </div>

                <div class="col-md-8">
                    <div class="accountLogin-content-slot accountLogin-content-slot_right">
                        <div class="contentModule">
                            <spring:url value="/register/national-investor" var="registerNationalInvestor" htmlEscape="false"/>
                            <form:form method="post" modelAttribute="nationalInvestorAppointment" action="${registerNationalInvestor}">
                                <c:if test="${not empty processingTime}">
                                    <div class="serviceTime">
                                        <div class="serviceTime-label"><spring:theme code="average.service.time" /></div>
                                        <div class="serviceTime-detail">
                                            <c:choose>
                                                <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                                    <span class="serviceTime-highlight">${processingTime.days}</span>
                                                    <spring:theme code="average.processingTime.days"/>
                                                    <span class="serviceTime-highlight">${processingTime.hours}</span>
                                                    <spring:theme code="average.processingTime.hours"/>
                                                </c:when>
                                                <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                                    <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                                    <spring:theme code="average.processingTime.minutes"/>
                                                    <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                                    <spring:theme code="average.processingTime.seconds"/>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="contentModule-headline contentModule-headline_larger">
                                    <spring:theme code="register.national.investor.title"/></div>
                                <div class="contentModule-section">
                                    <div class="contentModule-headline"><spring:theme code="register.national.investor.basicinfo"/></div>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:hidden path="department"/>
                                                    <label class="control-label" for="departmentDescription"><spring:theme code="register.national.investor.department"/></label>
                                                    <form:input id="departmentDescription" path="departmentDescription" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="formSelectBox">
                                                <div class="form-group">
                                                    <form:select path="branch" cssClass="js-select2-oneColumn form-control">
                                                        <form:option value=""/>
                                                        <form:options items="${branches}"
                                                                      itemValue="${not empty itemValue ? itemValue :'FieldKey'}"
                                                                      itemLabel="${not empty itemLabel ? itemLabel :'Description'}"
                                                                      htmlEscape="true"/>
                                                    </form:select>
                                                    <label class="control-label control-label_mandatory" for="branch"><spring:theme code="register.national.investor.branch"/></label>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:input path="investorId" cssClass="form-control" placeholder="."/>
                                                    <label class="control-label control-label_mandatory" for="investorId">
                                                        <spring:theme code="register.national.investor.gccid"/>*
                                                    </label>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:input id="email2" path="email" cssClass="form-control" placeholder="."/>
                                                    <label class="control-label control-label_mandatory" for="email2"><spring:theme code="register.national.investor.email"/></label>
                                                </div>
                                                <div class="help-block"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="contentModule-section" style="display: none;">
                                    <div class="contentModule-headline"><spring:theme code="register.national.investor.services"/></div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:hidden path="serviceType1"/>
                                                    <input id="serviceType1Description" class="form-control"
                                                           placeholder="." type="text"
                                                           value="${serviceType.description}"
                                                           readonly="readonly"/>
                                                    <label class="control-label" for="serviceType1Description">
                                                        <spring:theme code="register.national.investor.servicetype"/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:hidden path="service1"/>
                                                    <input id="service1Description" class="form-control"
                                                           placeholder="." type="text" value="${service.description}"
                                                           readonly="readonly"/>
                                                    <label class="control-label " for="service1Description"><spring:theme code="register.national.investor.services"/></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="contentModule-section">
                                    <div class="contentModule-headline"><spring:theme code="nationalInvestor.slots.available"/></div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="formInputBox formInputBox_group">
                                                <div class="form-group">
                                                    <form:input path="dateString" cssClass="form-control js-form-control_date"/>
                                                    <label class="control-label control-label_mandatory" for="dateString"><spring:theme code="nationalInvestor.label.date"/></label>
                                                    <div class="formInputBox-append" id="calendar-icon-pos">
                                                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                    </div>
                                                </div>
                                                <div class="help-block"></div>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <form:input path="timeFromString"
                                                                cssClass="form-control js-form-control_timeslot"
                                                                data-dates-enabled="09:00#16:00"/>
                                                    <label class="control-label control-label_mandatory" for="timeFromString"><spring:theme code="nationalInvestor.label.start"/></label>
                                                    <%--<div class="formInputBox-append" id="calendar-icon-pos"><icon:calendar-gray/></div>--%>
                                                    <div class="help-block"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="formCheckBox formCheckBox_block formCheckBox_belowPanel">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <formElement:termsAndConditionsCheckbox event="REGISTRATION" id="termsAndConditionsNationalInvestor" path="termsAndConditionsChecked"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="contentModule-actions contentModule-actions_centered accountLogin-content-formSubmitSection">
                                    <div><button type="submit" class="btn register-national-investor"><spring:theme code="nationalInvestor.submit.button.text"/></button></div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="nipSuccessModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" style="z-index: 220000;">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="nationalInvestor.request.submit"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:modal02/></div>
                <div class="modal-description">
                    <spring:theme code="nationalInvestor.request.submit.message"/><br />
                    <spring:theme code="nationalInvestor.request.service.request.id"/>: <span id="requestNumberPlaceholder"></span>
                </div>
            </div>
            <div class="modal-footer">
                <c:url value="/login" var="loginUrl"/>
                <a href="${loginUrl}" class="btn btn_slim"><spring:theme code="nationalInvestor.login.return"/></a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="nipFailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" style="z-index: 220000;">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="register.appointment.already.exists.title"/></div>
            </div>
            <div class="modal-body modal-body-center">
                <div class="modal-heroImage image-medium"><icon:status-cancelled/></div>
            </div>
            <div class="modal-body">
                <div class="modal-description">
                    <spring:theme code="register.appointment.already.exists"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="general.close"/></button>
            </div>
        </div>
    </div>
</div>
