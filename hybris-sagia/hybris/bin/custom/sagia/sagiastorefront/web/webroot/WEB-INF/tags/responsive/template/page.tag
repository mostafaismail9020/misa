<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<template:master pageTitle="${pageTitle}">
	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss" />
	</jsp:attribute>

	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts" />
	</jsp:attribute>

	<jsp:body>
		<%@ include file="/WEB-INF/tags/responsive/common/sessionExpiresModal.tag" %>
		<spring:theme code="text.skipToContent" var="skipToContent" />
		<a href="#skip-to-content" class="skiptocontent" data-role="none">${fn:escapeXml(skipToContent)}</a>
		<spring:theme code="text.skipToNavigation" var="skipToNavigation" />
		<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${fn:escapeXml(skipToNavigation)}</a>

		<header:header hideHeaderLinks="${hideHeaderLinks}" />
		<a id="skip-to-content"></a>

		<main>
			<common:globalMessages />
			<jsp:doBody />
		</main>

		<footer:footer />
	</jsp:body>
</template:master>
