<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="metaDescription" required="false" %>
<%@ attribute name="metaKeywords" required="false" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>
<%@ taglib prefix="addonScripts" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="generatedVariables" tagdir="/WEB-INF/tags/shared/variables" %>
<%@ taglib prefix="debug" tagdir="/WEB-INF/tags/shared/debug" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="htmlmeta" uri="http://hybris.com/tld/htmlmeta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<!DOCTYPE html>
<html lang="${fn:escapeXml(currentLanguage.isocode)}" dir="${currentLanguage.isocode eq 'ar' ? 'rtl' : 'ltr'}">
<head>
	<title>
		${not empty pageTitle ? pageTitle : not empty cmsPage.title ? fn:escapeXml(cmsPage.title) : 'Accelerator Title'}
	</title>

	<%-- Meta Content --%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<%-- Additional meta tags --%>
	<htmlmeta:meta items="${metatags}"/>

	<%-- Favourite Icon --%>
	<spring:theme code="img.favIcon" text="/" var="favIconPath"/>

	<link rel="canonical" href="${portal.getConfiguration('website.investsaudiportal.https')}${requestScope['javax.servlet.forward.request_uri']}" />
	
	<c:choose>
		<%-- if empty webroot, skip originalContextPath, simply use favIconPath --%>
		<c:when test="${fn:length(originalContextPath) eq 1}" >
			<link rel="shortcut icon" type="image/x-icon" media="all" href="${favIconPath}" />
		</c:when>
		<c:otherwise>
			<link rel="shortcut icon" type="image/x-icon" media="all" href="${originalContextPath}${favIconPath}" />
		</c:otherwise>
	</c:choose>

	<c:url var="requestUrl" value="${portal.getConfiguration('website.investsaudiportal.https')}${requestScope['javax.servlet.forward.request_uri']}" />
	<c:set var="metaDescription" value="${portal.getMetaDescription(metatags)}" />
	<%-- Open Graph tags --%>
	<meta property="og:title" content="${pageTitle}">
	<meta property="og:description" content="${metaDescription}">
	<meta property="og:url" content="${requestUrl}">
	<meta property="og:locale" content="${currentLanguage.isocode}">
	<meta property="og:image" content="${portal.getMetaImage(cmsPage,"url")}">
	<meta property="og:image:type" content="${portal.getMetaImage(cmsPage,"type")}">
	<meta property="og:type" content="website">
	<meta property="og:site_name" content="Invest Saudi">
	<%-- Twitter tags --%>
	<meta name="twitter:card" content="summary_large_image">
	<meta name="twitter:site" content="Invest Saudi">
	<meta name="twitter:creator" content="Invest Saudi">
	<meta name="twitter:title" content="${pageTitle}">
	<meta name="twitter:description" content="${metaDescription}">
	<meta name="twitter:image" content="${portal.getMetaImage(cmsPage,"url")}">
	<meta name="twitter:url" content="">

	<%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
	<template:portalstyleSheets/>

	<%-- Inject any additional CSS required by the page --%>
	<jsp:invoke fragment="pageCss"/>
	<analytics:portalAnalytics/>
	<generatedVariables:generatedVariables/>
</head>
<body class="${pageBodyCssClasses} ${cmsPageRequestContextData.liveEdit ? ' yCmsLiveEdit' : ''} language-${fn:escapeXml(currentLanguage.isocode)}">

<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=${gtmContainerId}"
				  height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->

	<%-- Inject the page body here --%>
	<jsp:doBody/>

	<form name="accessiblityForm">
		<input type="hidden" id="accesibility_refreshScreenReaderBufferField" name="accesibility_refreshScreenReaderBufferField" value=""/>
	</form>
	<div id="ariaStatusMsg" class="skip" role="status" aria-relevant="text" aria-live="polite"></div>

	<%-- Load JavaScript required by the site --%>
	<template:portaljavaScript/>
	
	<%-- Inject any additional JavaScript required by the page --%>
	<jsp:invoke fragment="pageScripts"/>

	<%-- Inject CMS Components from addons using the placeholder slot--%>
	<addonScripts:addonScripts/>


</body>

<debug:debugFooter/>

</html>
