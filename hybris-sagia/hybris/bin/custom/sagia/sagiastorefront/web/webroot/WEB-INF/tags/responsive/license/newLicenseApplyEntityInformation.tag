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
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--Entity information-->
<div class="panelTabs-head" id="entityInformationTab">
    <icon:entityInformation/>
    <span class="panelTabs-label"><spring:theme code="licenseApplyEntityInformation.entityInformation.title"/></span>
    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
</div>
<div class="panelTabs-body" id="entityInformationTabData">
    <div id="entityInformationGlobalMessage" class="globalMessage alert alert-warning getAccAlert" style="display: none;">
        <span class="globalMessage-msg">
            <div class="globalMessage-icon globalMessage-icon_warning">
                <icon:warning/>
            </div>
            <span class="text"></span>
        </span>
    </div>
    <c:url var="entityInfoFormAction" value="/my-sagia/license/entity"/>
    <c:if test="${isicRegisterError}">
        <spring:theme code="business.activities.mandatory" var="isicError"/>
    </c:if>
    <form:form id="entityForm" action="${entityInfoFormAction}" method="post" modelAttribute="sagiaApplyEntityInfoForm" enctype="multipart/form-data">
        <div class="contentModule">
            <c:if test="${isicRegisterError}">
                <input type="hidden" name="isicErrorMessage" value="<spring:theme code="business.activities.mandatory"/>">
            </c:if>

            <div class="contentModule-section">
                <license:newLicenseApplyEntityInformation-hasLicenseSection/>
                <license:licenseApplyEntityInformation-hasShareholdersTypePerson/>
                <license:newLicenseApplyEntityInformation-advanceLicenseNumberSection/>
            </div>
            <license:newLicenseApplyEntityInformation-licenseTypeSection/>
            <license:newLicenseApplyEntityInformation-licenseInformationSection/>
                <%--   <license:newLicenseApplyEntityInformation-branchInformationSection/> --%>
            <license:newLicenseApplyEntityInformation-attachmentSection/>
            <license:newLicenseApplyEntityInformation-licenseYearsSection/>
            <license:licenseApplyEntityInformation-stockMarketSection/>
            <license:licenseApplyEntityInformation-notOnStockMarketSection/>
            <license:newLicenseApplyEntityInformation-onStockMarketSection/>
            <license:licenseApplyEntityInformation-technicalAndFinancialCriteriaSection/>
           <%--            <license:newLicenseApplyEntityInformation-basicInformationSection/>--%>
            <license:newLicenseApplyEntityInformation-basicInformationExtendedSection/>
			<%--<license:newLicenseApplyEntityInformation-rhqAttachmentSection/>--%>
            <div class="businessActivitiesJsonInputs">
                <c:if test="${not empty isicActivities}">
                    <c:forEach items="${isicActivities}" var="isicActivity">
                        <input type="hidden" name="businessActivities" value="${fn:replace(isicActivity, "\"", "\'")}">
                    </c:forEach>
                </c:if>

            </div>
            <license:newLicenseBusinessActivitiesSection/>
            <license:newLicenseApplyEntityInformation-productsSection/>
        </div>
    </form:form>
</div>

