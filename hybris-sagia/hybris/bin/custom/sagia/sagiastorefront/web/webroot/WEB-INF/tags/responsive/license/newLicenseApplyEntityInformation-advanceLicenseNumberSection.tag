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

<div id="advanceLicenseNumberSection" style="display: none">
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.title"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="hasAdvanceLicenseNr" id="hasAdvanceLicenseNrYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.hasAdvanceLicenseNr == true ? 'checked' : ''}/>
                            <label for="hasAdvanceLicenseNrYES" id="hasAdvanceLicenseNrYESLabel" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="hasAdvanceLicenseNr" id="hasAdvanceLicenseNrNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.hasAdvanceLicenseNr == false ? 'checked' : ''}/>
                            <label for="hasAdvanceLicenseNrNO" id="hasAdvanceLicenseNrNOLabel" class="btn btn-ctrl btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="hasAdvanceLicenseNumberSection" style="display: none">
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="advanceLicenseNumberInput"
                                            labelKey="licenseApplyEntityInformation.advanceLicenseNumberSection.text" path="advanceLicenseNr"
                                            labelCSS="control-label control-label_mandatory"
                                            inputCSS="text validate__numbers-only" mandatory="true" maxlength="10" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="advanceLicenseNumberInput" class="form-control" placeholder="." value="" type="text" name="advanceLicenseNr" value="${sagiaApplyEntityInfoForm.advanceLicenseNr}"/>--%>
<%--                    <label class="control-label" for="advanceLicenseNumberInput"><spring:theme code="licenseApplyEntityInformation.advanceLicenseNumberSection.text"/></label>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>
</div>
