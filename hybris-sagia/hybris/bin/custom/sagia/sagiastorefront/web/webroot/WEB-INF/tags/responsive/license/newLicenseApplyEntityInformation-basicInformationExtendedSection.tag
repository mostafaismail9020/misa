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

<!--Basic information extended-->
<div class="contentModule-section" id="basicInformationExtendedSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.basicInformation"/></div>
   <hr class="hr">
    <div class="row mt-5 pt-3">
        <div class="col-md-6 mb-4">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEntityName"
                                      labelKey="profile.company.entityName" path="entityName"
                                      labelCSS="control-label_mandatory"
                                      inputCSS="text validate__no-special-chars" mandatory="true" maxlength="100" />
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEntityNameArabic"
                                      labelKey="profile.company.entityNameArabic"
                                      path="entityNameArabic" inputCSS="text validate__arabic-only" maxlength="80" />
        </div>
        <div class="col-md-6 mb-4">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedLegalStatus"
                                       labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.legalStatus"
                                       path="legalStatus" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.legalStatus}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedLegalStatus" name="legalStatus" class="js-select2-oneColumn form-control" placeholder="."></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedLegalStatus"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.legalStatus"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedMultinationalCompany"
                                       labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.multinationalCompany"
                                       path="basicInfoExtendedMultinationalCompany" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.basicInfoExtendedMultinationalCompany}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedMultinationalCompany" name="basicInfoExtendedMultinationalCompany" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedMultinationalCompany"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.multinationalCompany"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pt-3 pb-5">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedCapital"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.capital" path="capital"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__float-numbers-only" mandatory="true" inputBoxCSS="formInputBox_group"
                                      inputBoxAppendKey="licenseApplyEntityInformation.basicInformationExtendedSection.sar" maxlength="20"/>
<%--            <div class="formInputBox formInputBox_group">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedCapital" name="capital" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.capital}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCapital"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.capital"/></label>--%>
<%--                    <div class="formInputBox-append">--%>
<%--                        <span class="formInputBox-text"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.sar"/></span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6  pt-3 pb-5">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEmail"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.email" path="email"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__email" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedEmail" name="email" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.email}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedEmail"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.email"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-5">
            <div class="formInputBox-split">
                <formElement:formInputBoxCustom idKey="basicInformationExtendedCountryCodeForTelephone" maxlength="5"
                                                labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"
                                                path="countryCodeForTelephone" mandatory="true" labelCSS="control-label"
                                                inputCSS="text form-control_preNumber validate__numbers-only"
                                                dataValue="${sagiaApplyEntityInfoForm.countryCodeForTelephone}"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedCountryCodeForTelephone" name="countryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.countryCodeForTelephone}"/>--%>
<%--                        <label class="control-label" for="basicInformationExtendedCountryCodeForTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
                <formElement:formInputBoxCustom idKey="basicInformationExtendedTelephone"
                                          labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.telephone"
                                          labelCSS="control-label control-label_mandatory" path="telephone"
                                          inputCSS="text form-control_labeled validate__numbers-only" maxlength="30" mandatory="true"
                                          inputBoxCSS="formInputBox_big"/>
<%--                <div class="formInputBox formInputBox_big">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedTelephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.telephone}"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="basicInformationExtendedTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.telephone"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
        </div>
        <div class="col-md-6 pb-4">
            <div class="formInputBox-split">
                <formElement:formInputBoxCustom idKey="basicInformationExtendedCountryCodeForMobilePhone" maxlength="5"
                                                labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"
                                                path="countryCodeForMobilePhone" mandatory="true" labelCSS="control-label"
                                                inputCSS="text form-control_preNumber validate__numbers-only"
                                                dataValue="${sagiaApplyEntityInfoForm.countryCodeForMobilePhone}"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedCountryCodeForMobilePhone" name="countryCodeForMobilePhone" class="form-control form-control_preNumber" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.countryCodeForMobilePhone}"/>--%>
<%--                        <label class="control-label" for="basicInformationExtendedCountryCodeForMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
                <formElement:formInputBoxCustom idKey="basicInformationExtendedMobilePhone"
                                          labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone" path="mobilePhone"
                                          labelCSS="control-label control-label_mandatory"
                                          inputCSS="text form-control_labeled validate__numbers-only" maxlength="30" mandatory="true"
                                          inputBoxCSS="formInputBox_big"/>
<%--                <div class="formInputBox formInputBox_big">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedMobilePhone" name="mobilePhone" class="form-control form-control_labeled" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.mobilePhone}"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="basicInformationExtendedMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
        </div>
        <div class="col-md-6 pb-4">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedWebsite"
                                            labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.website" path="website"
                                            labelCSS="control-label control-label_mandatory"
                                            inputCSS="text validate__website" mandatory="true" maxlength="132" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedWebsite" class="form-control" name="website" placeholder="." value="" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedWebsite"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.website"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedCountry"
                                       labelKey="profile.company.country"
                                       path="country" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.country}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedCountry" name="country" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCountry"><spring:theme code="profile.company.country"/></label>--%>
<%--                    <input type="hidden" name="country">--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
            <input type="hidden" name="country">
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 pb-5">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedRegion"
                                       labelKey="profile.company.region"
                                       path="region" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.region}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedRegion" name="region" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedRegion"><spring:theme code="profile.company.region"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-4">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedCity"
                                       labelKey="profile.company.city"
                                       path="city" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.city}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedCity" name="city" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCity"><spring:theme code="profile.company.city"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-5">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedAddress"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.address"
                                      labelCSS="control-label control-label_mandatory" maxlength="60" path="address"
                                      inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedAddress" name="address" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.address}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedAddress"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.address"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6 pb-3">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedPOBox"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.poBox" path="poBox"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__numbers-only" maxlength="5" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedPOBox" name="poBox" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.poBox}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPOBox"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.poBox"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedPostalCode"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.postalCode" path="postalCode"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__numbers-only" maxlength="5" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedPostalCode" name="postalCode" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.postalCode}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPostalCode"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.postalCode"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>

        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedInvestment"
                                       labelKey="license.apply.expectedinvestment"
                                       path="investment" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.investment}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedInvestment" name="investment" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedInvestment"><spring:theme code="license.apply.expectedinvestment"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
    </div>
</div>
