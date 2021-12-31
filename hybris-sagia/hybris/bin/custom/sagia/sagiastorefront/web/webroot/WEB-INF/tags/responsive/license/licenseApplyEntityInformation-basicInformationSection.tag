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

<!-- Basic information -->
<form:form action="" method="post" modelAttribute="basicOrganizationInformation">
    <div class="contentModule-section" id="basicInformationSection" style="display: none">
        <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.basicInformationSection.basicInformation"/></div>
        <div class="row">
            <div class="col-md-6">
                <formElement:formInputBox idKey="basicInformationEntityName"
                                          labelKey="profile.company.entityName" path="entityName"
                                          inputCSS="text" mandatory="true" labelCSS="control-label_mandatory"/>
            </div>
            <div class="col-md-6">
                <formElement:formInputBox idKey="basicInformationEntityNameArabic"
                                          labelKey="profile.company.entityNameArabic"
                                          path="entityNameArabic" inputCSS="text"/>
            </div>
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="basicInformationLegalStatus" name="legalStatus" class="js-select2-oneColumn form-control" placeholder="."></select>
                        <label class="control-label control control-label_mandatory" for="basicInformationLegalStatus"><spring:theme code="licenseApplyEntityInformation.basicInformationSection.legalStatus"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="basicInformationLabourSize" name="labourSize" class="form-control" placeholder="." value="" type="text"/>
                        <label class="control-label" for="basicInformationLabourSize"><spring:theme code="licenseApplyEntityInformation.basicInformationSection.labourSize"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox formInputBox_group">
                    <div class="form-group">
                        <input id="basicInformationCapital" name="capital" class="form-control" placeholder="." value="" type="text"/>
                        <label class="control-label control-label_mandatory" for="basicInformationCapital"><spring:theme code="licenseApplyEntityInformation.basicInformationSection.capital"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text"><spring:theme code="licenseApplyEntityInformation.basicInformationSection.sar"/></span>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="basicInformationRegion" name="region" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="basicInformationRegion"><spring:theme code="profile.company.region"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="basicInformationCity" name="city" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="basicInformationCity"><spring:theme code="profile.company.city"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <%--<div class="row">--%>
            <%--<div class="col-md-6">--%>
                <%--<div class="formSelectBox">--%>
                    <%--<div class="form-group">--%>
                        <%--<select id="basicInformationInvestment" name="investment" class="js-select2-oneColumn form-control"></select>--%>
                        <%--<label class="control-label control-label_mandatory" for="basicInformationInvestment"><spring:theme code="license.apply.expectedinvestment"/></label>--%>
                    <%--</div>--%>
                    <%--<div class="help-block"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</form:form>
