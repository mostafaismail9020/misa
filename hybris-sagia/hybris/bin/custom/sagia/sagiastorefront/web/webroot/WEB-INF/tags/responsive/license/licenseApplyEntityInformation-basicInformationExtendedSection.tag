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
<form:form action="" method="post" modelAttribute="organizationInformationExtended" onsubmit="javascript:return false;">
<div class="contentModule-section" id="basicInformationExtendedSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.basicInformation"/></div>
    <div class="row">
        <div class="col-md-6">
            <formElement:formInputBox idKey="basicInformationExtendedEntityName"
                                      labelKey="profile.company.entityName" path="entityName"
                                      labelCSS="control-label_mandatory"
                                      inputCSS="text" mandatory="true" maxlength="100" />
        </div>
        <div class="col-md-6">
            <formElement:formInputBox idKey="basicInformationExtendedEntityNameArabic"
                                      labelKey="profile.company.entityNameArabic"
                                      path="entityNameArabic" inputCSS="text" maxlength="80" />
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedLegalStatus" name="legalStatus" class="js-select2-oneColumn form-control" placeholder="."></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedLegalStatus"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.legalStatus"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedMultinationalCompany" name="basicInformationExtendedMultinationalCompany" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedMultinationalCompany"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.multinationalCompany"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox formInputBox_group">
                <div class="form-group">
                    <input id="basicInformationExtendedCapital" name="capital" class="form-control" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCapital"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.capital"/></label>
                    <div class="formInputBox-append">
                        <span class="formInputBox-text"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.sar"/></span>
                    </div>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="basicInformationExtendedEmail" name="email" class="form-control" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedEmail"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.email"/></label>
                </div>
                <div class="help-block" id="emailValidation"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="basicInformationExtendedCountryCodeForTelephone" name="countryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                        <label class="control-label" for="basicInformationExtendedCountryCodeForTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
                <div class="formInputBox formInputBox_big">
                    <div class="form-group">
                        <input id="basicInformationExtendedTelephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="basicInformationExtendedTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.telephone"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="basicInformationExtendedCountryCodeForMobilePhone" name="countryCodeForMobilePhone" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                        <label class="control-label" for="basicInformationExtendedCountryCodeForMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
                <div class="formInputBox formInputBox_big">
                    <div class="form-group">
                        <input id="basicInformationExtendedMobilePhone" name="mobilePhone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="basicInformationExtendedMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="basicInformationExtendedWebsite" class="form-control" name="website" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedWebsite"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.website"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedCountry" name="country" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCountry"><spring:theme code="profile.company.country"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedRegion" name="region" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedRegion"><spring:theme code="profile.company.region"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedCity" name="city" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCity"><spring:theme code="profile.company.city"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="basicInformationExtendedAddress" name="address" class="form-control" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedAddress"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.address"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="basicInformationExtendedPOBox" name="poBox" class="form-control" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPOBox"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.poBox"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="basicInformationExtendedPostalCode" name="postalCode" class="form-control" placeholder="." value="" type="text"/>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPostalCode"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.postalCode"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="basicInformationExtendedInvestment" name="investment" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="basicInformationExtendedInvestment"><spring:theme code="license.apply.expectedinvestment"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
</div>
</form:form>
