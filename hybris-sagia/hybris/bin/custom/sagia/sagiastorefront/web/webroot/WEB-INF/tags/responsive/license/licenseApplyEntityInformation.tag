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

<!--Entity information-->
<div class="panelTabs-head" id="entityInformationTab">
    <icon:entityInformation/>
    <span class="panelTabs-label"><spring:theme code="licenseApplyEntityInformation.entityInformation.title"/></span>
    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
</div>
<div class="panelTabs-body" id="entityInformationTabData">
    <%--<form:form action="" method="post" commandName="sagiaLicenseApplyForm">--%>
        <div class="contentModule">
            <div class="contentModule-section">
                <license:licenseApplyEntityInformation-hasLicenseSection/>
                <license:licenseApplyEntityInformation-hasShareholdersTypePerson/>
                <license:licenseApplyEntityInformation-advanceLicenseNumberSection/>
            </div>
            
            <license:licenseApplyEntityInformation-licenseTypeSection/>
            <license:licenseApplyEntityInformation-licenseInformationSection/>
            <license:licenseApplyEntityInformation-attachmentSection/>
            <license:licenseApplyEntityInformation-licenseYearsSection/>
            <license:licenseApplyEntityInformation-stockMarketSection/>
            <license:licenseApplyEntityInformation-notOnStockMarketSection/>
            <license:licenseApplyEntityInformation-onStockMarketSection/>
            <license:licenseApplyEntityInformation-technicalAndFinancialCriteriaSection/>
            <license:licenseApplyEntityInformation-basicInformationSection/>
            <license:licenseApplyEntityInformation-basicInformationExtendedSection/>
            <license:licenseBusinessActivitiesSection/>
            <license:licenseApplyEntityInformation-productsSection/>
        </div>
    <%--</form:form>--%>
</div>

