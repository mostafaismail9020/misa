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

<div id="hasSagiaLicenseSection">
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span><spring:theme code="text.account.profile.license.isGCCNationality"/></span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="hasGCCNationality" id="hasGCCNationalityYES" value="yes" class="form-control"/>
                            <label for="hasGCCNationalityYES" id="hasGCCNationalityYESLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.hasLicenseSection.yes"/></label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="hasGCCNationality" id="hasGCCNationalityNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType ? 'checked="checked"' : ''}/>
                            <label for="hasGCCNationalityNO" id="hasGCCNationalityNOLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.hasLicenseSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!--       <div class="formRadioBox-wrapper">
        <div class="row">
     <div class="col-md-6" id="hasSagiaLicenseSpan" style="display: none">
                <span><spring:theme code="text.account.profile.license.hasSAGIALicense"/></span>
         </div>
            <div class="col-md-6" id="hasSagiaLicenseRadioDiv" style="display: none">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="hasSagiaLicense" id="hasSagiaLicenseYES" value="yes" class="form-control"/>
                            <label for="hasSagiaLicenseYES" id="hasSagiaLicenseYESLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.hasLicenseSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="hasSagiaLicense" id="hasSagiaLicenseNO" value="no" class="form-control"/>
                            <label for="hasSagiaLicenseNO" id="hasSagiaLicenseNOLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.hasLicenseSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </div> --> 
</div>
