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
<hr class="hr"/>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isEntrepreneur" id="isEntrepreneurYES" value="yes" class="form-control"/>
                            <label for="isEntrepreneurYES" class="btn btn-ctrl btn-bg btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isEntrepreneur" id="isEntrepreneurNO" value="no" class="form-control"/>
                            <label for="isEntrepreneurNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
