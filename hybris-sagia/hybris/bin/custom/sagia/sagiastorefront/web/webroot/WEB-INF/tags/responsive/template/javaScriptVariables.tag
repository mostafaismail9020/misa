<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%-- JS configuration --%>
<script type="text/javascript">
    /*<![CDATA[*/
    <%-- Define a javascript variable to hold the content path --%>
    var SAGIA = {config: {}};
    SAGIA.config.hasLicense = ${hasLicense ? true : false};
    SAGIA.config.hasAwaitingPayment = ${hasAwaitingPayment ? true : false};
    SAGIA.config.sessionTimeout = ${sagiaSessionTimeout};
    var ACC = {config: {}};
    ACC.config.contextPath = "${contextPath}";
    ACC.config.encodedContextPath = "${encodedContextPath}";
    ACC.config.commonResourcePath = "${commonResourcePath}";
    ACC.config.themeResourcePath = "${themeResourcePath}";
    ACC.config.siteResourcePath = "${siteResourcePath}";
    ACC.config.rootPath = "${siteRootUrl}";
    ACC.config.CSRFToken = "${CSRFToken.token}";
    ACC.config.environment = "${environment}";
    ACC.pwdStrengthVeryWeak = '<spring:theme code="password.strength.veryweak" />';
    ACC.pwdStrengthWeak = '<spring:theme code="password.strength.weak" />';
    ACC.pwdStrengthMedium = '<spring:theme code="password.strength.medium" />';
    ACC.pwdStrengthStrong = '<spring:theme code="password.strength.strong" />';
    ACC.pwdStrengthVeryStrong = '<spring:theme code="password.strength.verystrong" />';
    ACC.pwdStrengthUnsafePwd = '<spring:theme code="password.strength.unsafepwd" />';
    ACC.pwdStrengthTooShortPwd = '<spring:theme code="password.strength.tooshortpwd" />';
    ACC.pwdStrengthMinCharText = '<spring:theme code="password.strength.minchartext"/>';
    ACC.accessibilityLoading = '<spring:theme code="aria.pickupinstore.loading"/>';
    ACC.accessibilityStoresLoaded = '<spring:theme code="aria.pickupinstore.storesloaded"/>';
    ACC.config.googleApiKey = "${googleApiKey}";
    ACC.config.googleApiVersion = "${googleApiVersion}";

    <c:if test="${request.secure}"><c:url value="/search/autocompleteSecure" var="autocompleteUrl"/></c:if>
    <c:if test="${not request.secure}"><c:url value="/search/autocomplete" var="autocompleteUrl"/></c:if>
    ACC.autocompleteUrl = '${autocompleteUrl}';

    ACC.formatUITime = '${uiTimeFormat}';
    ACC.formatUIDate = '${uiDateFormat}';

    ACC.formatUITimeUAQ = '${uiTimeFormatUAQ}';
    ACC.formatUIDateUAQ = '${uiDateFormatUAQ}';

    ACC.text = ACC.text || {};
    ACC.text.followUp = '<spring:theme code="text.account.followup.error"/>';

    <c:url value="/login" var="loginUrl"/>
    ACC.config.loginUrl = '${loginUrl}';

    <c:url value="/authentication/status" var="authenticationStatusUrl"/>
    ACC.config.authenticationStatusUrl = '${authenticationStatusUrl}';

    <c:forEach var="jsVar" items="${jsVariables}">
        <c:if test="${not empty jsVar.qualifier}" >
            ACC.${jsVar.qualifier} = '${jsVar.value}';
        </c:if>
    </c:forEach>
    SAGIA.config.temporaryLicenseConstant = "${temporaryLicenseConstant}";
    SAGIA.config.regularQeemahChannel = "${regularQeemahChannel}";
    SAGIA.config.immediateQeemahChannel = "${immediateQeemahChannel}";
    /*]]>*/
</script>
<template:javaScriptAddOnsVariables/>
<template:sagia.I18n.texts/>

<%-- generated variables from commonVariables.properties --%>
<script type="text/javascript" src="${sharedResourcePath}/js/generatedVariables.js${version}"></script>
