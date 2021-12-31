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
<%--<form id="shareholdersForm" onsubmit="javascript:return false;">--%>
<!--Shareholders -->
<style>
    span.formInputBox-text.formInputBox-text_tip.js-tip {
        padding: 0;
    }
</style>

<div class="panelTabs-head" id="shareholdersTab">
    <icon:shareholder/>
    <span class="panelTabs-label"><spring:theme code="license.apply.shareholders"/></span>
    <span class="panelTabs_formFlow-bar"></span>
    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
</div>
<div class="panelTabs-body" id="shareholdersTabData">
    <div class="globalMessage alert alert-warning getAccAlert" style="display: none;">
        <span class="globalMessage-msg">
            <div class="globalMessage-icon globalMessage-icon_warning">
                <icon:warning/>
            </div>
            <spring:theme code="license.apply.qeemah.determination.warning"/>
        </span>
    </div>
    <license:newLicenseApplyShareholders-QM1/>
<%--    <license:licenseApplyShareholders-QM2/>--%>
    <div class="contentModule-actions contentModule-actions_spaceBetween">
        <c:url value="/my-sagia/license/entity" var="backUrl"/>
        <span>
            <a id="shareholdersBackButton" type="button" class="btn btn-secondary" href="${backUrl}"><spring:theme code="general.back"/></a>
<%--            <button id="shareholdersCancelButton" type="button" class="btn btn-secondary btn_link btn_bold"><spring:theme code="general.cancel"/></button>--%>
        </span>
        <c:url value="/my-sagia/license/contactperson" var="nextUrl"/>
        <span>
            <button id="shareholdersNextButton" type="button" class="btn" data-url="${nextUrl}"><spring:theme code="general.next"/></button>
        </span>
    </div>
</div>
<%--</form>--%>
