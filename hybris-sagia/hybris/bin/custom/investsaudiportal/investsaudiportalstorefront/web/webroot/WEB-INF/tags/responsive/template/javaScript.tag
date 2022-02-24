<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/responsive/template/cms" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:url value="/" var="siteRootUrl"/>
<c:url value="${requestScope['javax.servlet.forward.request_uri']}" var="requestURL" />

<!-- TOOD: load min-js for qa & prod environment -->
<c:set var="jsPath" value="${not empty environment && environment != 'local' && environment != 'development' ?'js':'js'}" scope="request"/>
<c:set var="version" value="${not empty staticResourceVersion ? '?v=': ''}${not empty staticResourceVersion != '' ? staticResourceVersion: ''}" scope="request"/>
<template:javaScriptVariables/>


<c:choose>
	<c:when test="${wro4jEnabled}">
	  	<script type="text/javascript" src="${contextPath}/wro/all_responsive.js${version}"></script>
	  	<script type="text/javascript" src="${contextPath}/wro/addons_responsive.js${version}"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="${themeResourcePath}/js/polyfill.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/formDataPolyfill.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/jquery-3.3.1.min.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/jquery.tabs.min.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/jquery.menu-aim.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia-jquery.select2.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/popper.min.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/bootstrap/util.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/bootstrap/modal.js${version}"></script>
		<%-- plugins --%>
		<script type="text/javascript" src="${commonResourcePath}/js/Imager.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.blockUI-2.66.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.validate.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/additional-methods.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox-min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.form.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.hoverIntent.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.pstrength.custom-1.2.0.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.syncheight.custom.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.tabs.custom.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.12.1.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.zoom.custom.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/owl.carousel.custom.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.tmpl-1.0.0pre.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.currencies.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.waitforimages.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jquery.slideviewer.custom.1.2.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/jsrender.min.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/popup-multiselect.js"></script>
		<%-- Custom ACC JS --%>
		<script type="text/javascript" src="${commonResourcePath}/js/main.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.autocomplete.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.colorbox.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.common.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/_autoload.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/enquire.min.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/Chart.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/bootstrap/tooltip.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/js-calendar/js-calendar.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/flatpickr.min.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/i10n/ar.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/jquery.qrcode.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/qrcode.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/picturefill.min.js${version}"></script>

        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.min.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.plus.min.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.plugin.min.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.picker.min.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.ummalqura.min.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.picker-ar.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/jquery_calendars/jquery.calendars.ummalqura-ar.js${version}"></script>

		<%-- common SAGIA javascript --%>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.common.js${version}"></script>
        <script type="text/javascript" src="${themeResourcePath}/js/sagia.ratings.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.statusUpdate.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.globalAlerts.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.formElements.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.jquery.search.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.sagiaNavigation.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.tabComponent.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.mainSections.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.panelTabs.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.eServiceTour.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.expandTables.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.expandContent.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.expandService.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.stepsList.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.showTarget.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.fileInput.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.fileInputBox.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.formRangeSlider.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.deleteDropdown.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.realTimeOnlineSupport.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.serviceMenu.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.toggleView.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.pagination.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.fileListUpload.js${version}"></script>
		<%--<script type="text/javascript" src="${themeResourcePath}/js/sagia.appointments.js${version}"></script>--%>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.termsAndConditions.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.messages.js${version}"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.I18n.js${version}"></script>

		<script type="text/javascript" src="${themeResourcePath}/js/acc.calendarcommons.js${version}"></script>
		<script type="text/javascript" src="${commonResourcePathHtml}/js/acc.administration.js"></script>

		<%-- AddOn JavaScript files --%>
		<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
		    <script type="text/javascript" src="${addOnJavaScript}${version}"></script>
		</c:forEach>

		<%-- Cms Action JavaScript files --%>
		<c:forEach items="${cmsActionsJsFiles}" var="actionJsFile">
		    <script type="text/javascript" src="${commonResourcePath}/js/cms/${actionJsFile}${version}"></script>
		</c:forEach>

	</c:otherwise>
</c:choose>


 <c:if test="${not fn:containsIgnoreCase(requestURL, '/login')}">
     <script type="text/javascript" src="${themeResourcePath}/${jsPath}/sagia.investsaudiHeader.js${version}"></script>
<%--<script type="text/javascript" src="${themeResourcePath}/js/particles/particles-load.js"></script>--%>
 </c:if>

<c:if test="${cmsPage.label == 'documents-branches'}">
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${googleMapKey}&callback=initMap"></script>
</c:if>

<c:forEach var="sagiaJavascript" items="${sagiaJavascripts}">
			<script type="text/javascript" src="${themeResourcePath}/${jsPath}/${sagiaJavascript}${version}"></script>
</c:forEach>


<cms:previewJS cmsPageRequestContextData="${cmsPageRequestContextData}" />

