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

<c:set var="editCss">
    ${(saveStatus eq false || editOrError eq true) &&
        sagiaApplyOrganizationShareholderForm.delegateInfo.delegate eq true ? "edit-initial" : ""}
</c:set>

<form:form action="/" id="organizationShareholderForm" modelAttribute="sagiaApplyOrganizationShareholderForm" method="POST" cssClass="${editCss}">
    <input type="hidden" name="code" value="${sagiaApplyOrganizationShareholderForm.code}">
    <div class="contentModule-section">
        <div class="contentModule-headline"><spring:theme code="licence.apply.shareholder.details"/></div>
<hr class="hr">
		<div class="row mt-5" style= ${not empty sagiaApplyOrganizationShareholderForm.division ? "display:none" : ""} >
		  <div class="col-md-6">
                <%--TODO: create companyCountryData--%>
                <formElement:formSelectBoxCustom idKey="companyCountry" labelKey="general.country"
                                                 path="companyCountry" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.companyCountry}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="companyCountry" name="companyCountry" class="js-select2-oneColumn form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyCountry"><spring:theme code="general.country"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>

            <div class="col-md-6">
            </div>


			<div class="col-md-6">
		        <div class="formInputBox">
	                <div class="form-group cr-validation">
	                	<%-- <formElement:formInputBoxCustom idKey="inputCRNumber"
                             labelKey="licenseApplyEntityInformation.licenseInformationSection.crnumber" path="professionalLicenseCr"
                             labelCSS="control-label control-label_mandatory"
                             inputCSS="text validate__numbers-only" maxlength="20" />
                             <div class="help-block" id="inputCRNumber-error"></div> --%>
	                    <input id="inputCRNumber" name="professionalLicenseCr" disabled="disabled" class="form-control validate__numbers-only" placeholder="." type="text" value="${not empty sagiaApplyOrganizationShareholderForm.professionalLicenseCr ? sagiaApplyOrganizationShareholderForm.professionalLicenseCr : ''}">
	                    <label class="control-label" for="professionalLicenseCr"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.crnumber"/></label>
	                    <div class="help-block" id="inputCRNumber-error"></div>
	                </div>
	            </div>
		    </div>
		    <div class="col-md-6">
		        <div class="formInputBox mt-4">
		            <div class="form-group">
		                <div class="form-item">
		                    <button id="load-investor" type="button" class="btn btn-bg btn-normal btn_bold btn-ctrl w-50 load-investor" disabled="disabled" ><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.validatecr" /></button>
		                    <input type="hidden" id="professionalLicenseCrVerified" name="professionalLicenseCrVerified" value="${sagiaApplyOrganizationShareholderForm.professionalLicenseCrVerified}" />
		                    <%-- <input type="checkbox" id="professionalLicenseCrVerified" name="professionalLicenseCrVerified" value="true" class="hidden" ${sagiaApplyEntityInfoForm.professionalLicenseCrVerified ? "checked=checked" : 0}> --%>
		                </div>
		            </div>
		        </div>
		    </div>
	    </div>



        <div class="row" id="dataSectionCompany" style= ${not empty sagiaApplyOrganizationShareholderForm.division ? "display:display" : "display:none"}  >
            <div class="col-md-6">
                <formElement:formInputBox idKey="organizationNameEnglish" labelKey="general.organization.name.english"
                                          path="organizationName" labelCSS="control-label_mandatory"
                                          inputCSS="validate__mandatory validate__no-special-chars" maxlength="100" />
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="organizationNameArabic" labelKey="general.organization.name.arabic"
                                          path="organizationNameArabic" labelCSS="control-label_mandatory" inputCSS="validate__mandatory validate__arabic-only" maxlength="40" />
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="organizationNameArabic" name="organizationNameArabic" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label" for="organizationNameArabic"><spring:theme code="general.organization.name.arabic"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="organizationLegalStatus" labelKey="general.legalstatus"
                                                 path="legalStatus" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.legalStatus}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="organizationLegalStatus" name="legalStatus" class="js-select2-oneColumn form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="organizationLegalStatus"><spring:theme code="general.legalstatus"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="multinationalCompany" labelKey="general.multinational.company"
                                                 path="multinationalCompany" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.multinationalCompany}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="multinationalCompany" name="multinationalCompany" class="js-select2-oneColumn form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="multinationalCompany"><spring:theme code="general.multinational.company"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyRegistrationNumber" labelKey="general.company.registration.number"
                                          path="companyRegNumber" labelCSS="control-label_mandatory"
                                          inputCSS="validate__mandatory validate__no-special-chars" maxlength="60"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyRegistrationNumber" name="companyRegNumber" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyRegistrationNumber"><spring:theme code="general.company.registration.number"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="companyProfessionalLicense" labelKey="license.professionalLicense"
                                                 path="professionalLicense" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory"
                                                 selectedDataValue="${sagiaApplyOrganizationShareholderForm.professionalLicense}"/>
            </div>
        </div>
        <div class="row" id="dataSectionCapital" style="display: none">
            <div class="col-md-6">
                <formElement:formInputBoxCustom idKey="companyCapital" labelKey="general.capital" path="capital"
                                                labelCSS="control-label_mandatory" inputBoxAppendKey="SAR"
                                                inputBoxCSS="formInputBox_group" maxlength="20"
                                                inputCSS="validate__mandatory validate__float-numbers-only"/>
<%--                <div class="formInputBox formInputBox_group">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyCapital" name="capital" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyCapital"><spring:theme code="general.capital"/></label>--%>
<%--                        <div class="formInputBox-append">--%>
<%--                            <span class="formInputBox-text">SAR</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBoxCustom idKey="companySharesPercentage" labelKey="general.shares.percentage" path="sharesPercentage"
                                                labelCSS="control-label_mandatory" inputBoxAppendKey="%"
                                                inputBoxCSS="formInputBox_group" inputCSS="validate__mandatory validate__float-numbers-only-sharespercentage"/>
<%--                <div class="formInputBox formInputBox_group">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companySharesPercentage" name="sharesPercentage" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companySharesPercentage"><spring:theme code="general.shares.percentage"/></label>--%>
<%--                        <div class="formInputBox-append">--%>
<%--                            <span class="formInputBox-text">%</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="companySection" labelKey="general.section"
                                                 path="section" selectCSSClass="js-select2-oneColumn js-select2-sortAlphabetically validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.section}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="companySection" name="section" class="js-select2-oneColumn js-select2-sortAlphabetically form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="companySection"><spring:theme code="general.section"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="companyDivision" labelKey="general.division"
                                                 path="division" selectCSSClass="js-select2-oneColumn js-select2-sortAlphabetically validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.division}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="companyDivision" name="division" class="js-select2-oneColumn js-select2-sortAlphabetically form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyDivision"><spring:theme code="general.division"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <c:set var="parentCompanyNameTooltip">
                    <span class="formInputBox-text formInputBox-text_tip js-tip"
                          style="position: relative;z-index: 1000;"
                          data-tip-title="Tooltip Information to be shown to the user."
                          data-original-title="" title=""><icon:tipInfo/>
                    </span>
                </c:set>
                <formElement:formInputBoxCustom idKey="parentCompanyName" labelKey="general.company.parent"
                                                path="parentCompanyName" labelCSS="control-label_mandatory"
                                                inputBoxCode="${parentCompanyNameTooltip}" inputBoxCSS="formInputBox_group"
                                                inputCSS="validate__mandatory validate__no-special-chars-with-arabic" maxlength="60"/>
<%--                <div class="formInputBox formInputBox_group">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="parentCompanyName" name="parentCompanyName" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="parentCompanyName"><spring:theme code="general.company.parent"/></label>--%>
<%--                        <div class="formInputBox-append">--%>
<%--                                ${parentCompanyNameTooltip}--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="parentCompanyCountry" labelKey="general.country.parent.company"
                                                 path="parentCompanyCountry" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.parentCompanyCountry}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="parentCompanyCountry" name="parentCompanyCountry" class="js-select2-oneColumn form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="parentCompanyCountry"><spring:theme code="general.country.parent.company"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
           <%--  <div class="col-md-6">
                TODO: create companyCountryData
                <formElement:formSelectBoxCustom idKey="companyCountry" labelKey="general.country"
                                                 path="companyCountry" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.companyCountry}"/>
               <div class="formSelectBox">
                   <div class="form-group">
                       <select id="companyCountry" name="companyCountry" class="js-select2-oneColumn form-control"></select>
                       <label class="control-label control-label_mandatory" for="companyCountry"><spring:theme code="general.country"/></label>
                   </div>
                   <div class="help-block"></div>
               </div>
            </div> --%>
            <div class="col-md-6">
                <formElement:formSelectBoxCustom idKey="companyCountryOfRegistration" labelKey="general.country.registration"
                                                 path="countryOfReg" selectCSSClass="js-select2-oneColumn validate__mandatory"
                                                 labelCSS="control-label_mandatory" selectedDataValue="${sagiaApplyOrganizationShareholderForm.countryOfReg}"/>
<%--                <div class="formSelectBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <select id="companyCountryOfRegistration" name="countryOfReg" class="js-select2-oneColumn form-control"></select>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyCountryOfRegistration"><spring:theme code="general.country.registration"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyCity" labelKey="general.city" path="city" maxlength="40"
                                          labelCSS="control-label_mandatory" inputCSS="validate__mandatory validate__no-special-chars-with-arabic"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyCity" name="city" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyCity"><spring:theme code="general.city"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyAddress" labelKey="general.address" path="address" maxlength="60"
                                          labelCSS="control-label_mandatory"
                                          inputCSS="validate__mandatory validate__no-special-chars-with-arabic"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyAddress" name="address" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyAddress"><spring:theme code="general.address"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyPOBox" labelKey="general.pobox" path="poBox"
                                          labelCSS="control-label_mandatory" inputCSS="validate__mandatory validate__numbers-only" maxlength="5"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyPOBox" name="poBox" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyPOBox"><spring:theme code="general.pobox"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyPostalCode" labelKey="general.postalcode" path="postalCode"
                                          labelCSS="control-label_mandatory" inputCSS="validate__mandatory validate__numbers-only" maxlength="5"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyPostalCode" name="postalCode" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyPostalCode"><spring:theme code="general.postalcode"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>

            <div class="col-md-6">
                <div class="formInputBox-split">
                    <formElement:formInputBoxCustom idKey="companyCountryCodeForTelephone" labelKey="general.country.code"
                                              path="countryCodeTelephone" labelCSS="control-label_mandatory"
                                              inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
                                              maxlength="5" dataValue="${sagiaApplyOrganizationShareholderForm.countryCodeTelephone}"/>
<%--                    <div class="formInputBox">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="companyCountryCodeForTelephone" name="countryCodeTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
<%--                            <label class="control-label control-label_mandatory" for="companyCountryCodeForTelephone"><spring:theme code="general.country.code"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                    <formElement:formInputBox idKey="companyTelephone" labelKey="general.telephone" path="telephoneNumber"
                                              labelCSS="control-label_mandatory" inputCSS="form-control_labeled validate__mandatory validate__numbers-only"
                                              inputBoxCSS="formInputBox_big" maxlength="30"/>
<%--                    <div class="formInputBox formInputBox_big">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="companyTelephone" name="telephoneNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
<%--                            <label class="control-label control-label_mandatory" for="companyTelephone"><spring:theme code="general.telephone"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox-split">
                    <formElement:formInputBoxCustom idKey="companyCountryCodeForMobile" labelKey="general.country.code"
                                              path="countryCodeMobile" labelCSS="control-label_mandatory"
                                              inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
                                              maxlength="5" dataValue="${sagiaApplyOrganizationShareholderForm.countryCodeMobile}"/>
<%--                    <div class="formInputBox">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="companyCountryCodeForMobile" name="countryCodeMobile" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
<%--                            <label class="control-label control-label_mandatory" for="companyCountryCodeForMobile"><spring:theme code="general.country.code"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                    <formElement:formInputBox idKey="companyMobile" labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone" path="mobileNumber"
                                              labelCSS="control-label_mandatory" inputCSS="form-control_labeled validate__mandatory validate__numbers-only"
                                              inputBoxCSS="formInputBox_big" maxlength="30"/>
<%--                    <div class="formInputBox formInputBox_big">--%>
<%--                        <div class="form-group">--%>
<%--                            <input id="companyMobile" name="mobileNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
<%--                            <label class="control-label control-label_mandatory" for="companyMobile"><spring:theme code="general.mobilenumber"/></label>--%>
<%--                        </div>--%>
<%--                        <div class="help-block"></div>--%>
<%--                    </div>--%>
                </div>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyEmail" labelKey="general.license.email" path="email"
                                          labelCSS="control-label_mandatory" inputCSS="validate__email"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyEmail" name="email" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyEmail"><spring:theme code="general.license.email"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="companyWebsite" labelKey="licence.apply.website" path="website"
                                          labelCSS="control-label_mandatory" inputCSS="validate__website" maxlength="132"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="companyWebsite" name="website" class="form-control" placeholder="." value="" type="text"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="companyWebsite"><spring:theme code="licence.apply.website"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
            <div class="col-md-6" id="mofaNumberSection" style="display: none">
            	<c:set var="mofaNumberTooltip">
                    <span class="formInputBox-text formInputBox-text_tip js-tip"
                          style="position: relative;z-index: 1000;"
                          data-tip-title="MOFA-Request/ Tracking Number"
                          data-original-title="<spring:theme code="license.apply.shareholder.mofaNumber"/>" title=""><icon:tipInfo/>
                    </span>
                </c:set>
                <formElement:formInputBoxCustom idKey="mofaNumber" labelKey="license.apply.shareholder.mofaNumber"
                                                path="mofaNumber" inputBoxCode="${mofaNumberTooltip}" inputBoxCSS="formInputBox_group"
                                                inputCSS="validate__numbers-only" maxlength="13"/>
                <%-- <formElement:formInputBox idKey="mofaNumber" labelKey="license.apply.shareholder.mofaNumber"
                                          path="mofaNumber" inputCSS="validate__numbers-only" maxlength="12"/>
                <div class="help-block" id="mofaNumber-error"></div> --%>
                <input type="hidden" id="isMofaVerified" name="mofaNumberVerified" value="${sagiaApplyOrganizationShareholderForm.mofaNumberVerified}">
                <%-- <input type="hidden" id="isMofaVerifiedOrg" name="mofaNumberVerified" value="${sagiaApplyOrganizationShareholderForm.mofaNumberVerified}"> --%>
            </div>
        </div>
    </div>
    <!--  Attachment  -->
    <div class="contentModule-section" id="dataSectionAttachment" style="display: none">
        <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="general.attachments"/></div>
        <!--<div class="contentModule-headline contentModule-headline_smallMargin" id="orgAttachmentTitle"><spring:theme code="general.attachments"/></div>-->
        <hr class="hr">
        <div class="row">
            <div class="col-md-6" id="companyRegistrationFileSection"  style="display:none">
                <div class="formInputFile ${not empty sagiaApplyOrganizationShareholderForm.commercialRegCopy ? "active" : ""}">
                    <c:set var="commercialRegCopyErrors">
                        <form:errors path="commercialRegCopy"/>
                    </c:set>
                    <div class="form-group ${not empty commercialRegCopyErrors ? 'has-error' : ''}">
                        <input id="companyRegistrationFile" name="companyRegistrationFile" class="form-control js-inputFile validate__file" data-maxsize="5" type="file" accept="application/pdf" value=""/>
                        <input id="companyRegistrationFileName" name="companyRegistrationFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyOrganizationShareholderForm.commercialRegCopy.fileName}" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="companyRegistrationFileName"><spring:theme code="licence.apply.commercial.registration.copy"/></label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    </div>
                    <div class="help-block">${commercialRegCopyErrors}</div>
                </div>
            </div>
            <div class="col-md-6" id="companyFinancialStatementFileSection"  style="display:none">
                <div class="formInputFile ${not empty sagiaApplyOrganizationShareholderForm.lastYearFinStatement ? "active" : ""}">
                    <c:set var="lastYearFinStatementErrors">
                        <form:errors path="lastYearFinStatement"/>
                    </c:set>
                    <div class="form-group ${not empty lastYearFinStatementErrors ? 'has-error' : ''}">
                        <input id="companyFinancialStatementFile" name="companyFinancialStatementFile" class="form-control js-inputFile  validate__file" data-maxsize="5" type="file" accept="application/pdf" value=""/>
                        <input id="companyFinancialStatementFileName" name="companyFinancialStatementFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyOrganizationShareholderForm.lastYearFinStatement.fileName}" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="companyFinancialStatementFileName" id="labelForCompanyFinancialStatementFileName"><spring:theme code="licence.apply.commercial.financialstatement"/> </label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    </div>
                    <div class="help-block">${lastYearFinStatementErrors}</div>
                </div>
            </div>
            <div class="col-md-6" id="companyCheckFileAttachment" style="display: none">
                <div class="formInputFile ${not empty sagiaApplyOrganizationShareholderForm.companyMemoAssociation ? "active" : ""}">
                    <c:set var="companyMemoAssociationErrors">
                        <form:errors path="companyMemoAssociation"/>
                    </c:set>
                    <div class="form-group ${not empty companyMemoAssociationErrors ? 'has-error' : ''}">
                        <input id="companyMemoAssociationFile" name="companyMemoAssociationFile" class="form-control js-inputFile" type="file" data-maxsize="5" accept="application/pdf" value=""/>
                        <input id="companyMemoAssociationFileName" name="companyMemoAssociationFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyOrganizationShareholderForm.companyMemoAssociation.fileName}" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="companyMemoAssociationFileName"><spring:theme code="licence.apply.commercial.memoassociation"/> </label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    </div>
                    <div class="help-block">${companyMemoAssociationErrors}</div>
                </div>
            </div>
            <div class="col-md-6" id="companyProfessionalLicenseFileAttachment" style="display: none">
                <div class="formInputFile ${not empty sagiaApplyOrganizationShareholderForm.professionalLicenseCertificate ? "active" : ""}">
                    <c:set var="companyProfessionalLicenseErrors">
                        <form:errors path="professionalLicenseCertificate"/>
                    </c:set>
                    <div class="form-group ${not empty companyProfessionalLicenseErrors ? 'has-error' : ''}">
                        <input id="companyProfessionalLicenseFile" name="companyProfessionalLicenseFile" class="form-control js-inputFile" type="file" data-maxsize="5" accept="application/pdf" value=""/>
                        <input id="companyProfessionalLicenseFileName" name="companyProfessionalLicenseFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyOrganizationShareholderForm.professionalLicenseCertificate.fileName}" readonly tabindex="-1"/>
                        <label class="control-label control-label_mandatory" for="companyProfessionalLicenseFileName"><spring:theme code="licence.apply.commercial.professionalLicense"/> </label>
                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                    </div>
                    <div class="help-block">${companyProfessionalLicenseErrors}</div>
                </div>
            </div>
        </div>
        <span class="spl-notes">Note - The Maximum File Size should not exceed 2 MB</span>
    </div>
    <div id="dataSectionDelegate" style="display: none">
    <license:newLicenseApplyShareholders-delegate shareholderType="Organization" organizationData="${sagiaApplyOrganizationShareholderForm}"/>
    </div>

    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
        <button class="cancelButton btn btn-outline btn-normal btn_bold w-25" type="button"><spring:theme code="general.cancel"/></button>
        <a class="addButton btn btn-ctrl btn-bg btn-normal btn_bold w-25"><spring:theme code="licence.apply.savenewshareholder"/></a>
    </div>
</form:form>