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

<!--  License Information  -->
<div class="contentModule-section" id="licenseInformationSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.title"/></div>
<hr class="hr">
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isEntrepreneur" id="isEntrepreneurYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntrepreneur == true ? 'checked' : ''}/>
                            <label for="isEntrepreneurYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isEntrepreneur" id="isEntrepreneurNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntrepreneur == false ? 'checked' : ''}/>
                            <label for="isEntrepreneurNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="formRadioBox-wrapper pb-5">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.preApprovalNumber.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isPreApprovalNumber" id="isPreApprovalNumberYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isPreApprovalNumber == true ? 'checked' : ''}/>
                            <label for="isPreApprovalNumberYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isPreApprovalNumber" id="isPreApprovalNumberNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isPreApprovalNumber == false ? 'checked' : ''}/>
                            <label for="isPreApprovalNumberNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div class="row" id="isPreApprovalNumberSection" style="display: none">
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="preApprovalNumberInput"
                                            labelKey="licenseApplyEntityInformation.licenseInformationSection.preApprovalNumber" path="preApprovalNumber"
                                            labelCSS="control-label control-label_mandatory"
                                            inputCSS="text validate__numbers-only" mandatory="true" maxlength="15" />
        </div>
    </div>

    <div class="formRadioBox-wrapper"  id="hasProfessionalLicenseSection" style="display: none">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.hasProfessionalLicense.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="hasProfessionalLicenseCr" id="hasProfessionalLicenseCrYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.hasProfessionalLicenseCr == true ? 'checked' : ''}/>
                            <label for="hasProfessionalLicenseCrYES" class="control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.hasProfessionalLicense.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="hasProfessionalLicenseCr" id="hasProfessionalLicenseCrNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.hasProfessionalLicenseCr == false ? 'checked' : ''}/>
                            <label for="hasProfessionalLicenseCrNO" class="control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.hasProfessionalLicense.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="contentModule-section" id="professionalLicenseCrSection" style="display: none">
		<div class="row">
			<div class="col-md-6">
		        <div class="formInputBox">
	                <div class="form-group cr-validation">
	                	<%-- <formElement:formInputBoxCustom idKey="inputCRNumber"
                             labelKey="licenseApplyEntityInformation.licenseInformationSection.crnumber" path="professionalLicenseCr"
                             labelCSS="control-label control-label_mandatory"
                             inputCSS="text validate__numbers-only" maxlength="20" />
                             <div class="help-block" id="inputCRNumber-error"></div> --%>
	                    <input id="inputCRNumber" name="professionalLicenseCr" class="form-control validate__numbers-only" placeholder="." type="text" value="${not empty sagiaApplyEntityInfoForm.professionalLicenseCr ? sagiaApplyEntityInfoForm.professionalLicenseCr : ''}">
	                    <label class="control-label" for="professionalLicenseCr"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.crnumber"/></label>
	                    <div class="help-block" id="inputCRNumber-error"></div>
	                </div>
	            </div>
		    </div>
		    <div class="col-md-6">
		        <div class="formInputBox">
		            <div class="form-group">
		                <div class="form-item">
		                    <button id="load-investor" type="button" class="btn load-investor" disabled><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.validatecr"/></button>
		                    <input type="hidden" id="professionalLicenseCrVerified" name="professionalLicenseCrVerified" value="${sagiaApplyEntityInfoForm.professionalLicenseCrVerified}" />
		                    <%-- <input type="checkbox" id="professionalLicenseCrVerified" name="professionalLicenseCrVerified" value="true" class="hidden" ${sagiaApplyEntityInfoForm.professionalLicenseCrVerified ? "checked=checked" : 0}> --%>
		                </div>
		            </div>
		        </div>
		    </div>
	    </div>
    </div>

</div>
