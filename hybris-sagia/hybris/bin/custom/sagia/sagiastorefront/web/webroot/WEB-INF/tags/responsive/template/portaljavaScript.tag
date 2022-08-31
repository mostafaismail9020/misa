<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/responsive/template/cms" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>

<c:set var="jsPath" value="${not empty environment && environment != 'local' && environment != 'development' ?'js':'js'}" scope="request"/>
<c:set var="version" value="${not empty staticResourceVersion ? '?v=': ''}${not empty staticResourceVersion != '' ? staticResourceVersion: ''}" scope="request"/>
<template:javaScriptVariables/>

<c:set var="commonResourcePathHtml" value="${fn:escapeXml(commonResourcePath)}"/>
<c:choose>
	<c:when test="${false}"> <%-- TODO: enable wro4j ${wro4jEnabled} --%>
	  	<script src="${fn:escapeXml(contextPath)}/wro/all_responsive.js"></script>
	  	<script src="${fn:escapeXml(contextPath)}/wro/addons_responsive.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${commonResourcePathHtml}/js/owlcarousel/jquery.min.js"></script>
        <script src="${commonResourcePathHtml}/js/popper.min.js"></script>
        <script src="${commonResourcePathHtml}/js/bootstrap.min.js"></script>
		<script src="${commonResourcePathHtml}/js/aos.js"></script>
		<script src="${commonResourcePathHtml}/js/owlcarousel/owl.carousel.js"></script>
        <script src="${commonResourcePathHtml}/js/script.js"></script>
		<script src="${commonResourcePathHtml}/js/contact.js"></script>
		<script type="text/javascript" src="${themeResourcePath}/js/sagia.I18n.js${version}"></script>

        <script type="text/javascript" src="${commonResourcePath}/js/owl.carousel.custom.js${version}"></script>
		<%-- jquery --%>


		<%-- plugins --%>
		<script src="${commonResourcePathHtml}/js/enquire.min.js"></script>
		<script src="${commonResourcePathHtml}/js/Imager.min.js"></script>
		<!--<script src="${commonResourcePathHtml}/js/purify.min.js"></script>-->
		<script src="${commonResourcePathHtml}/js/jquery.blockUI-2.66.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.colorbox-min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.form.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.hoverIntent.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.pstrength.custom-1.2.0.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.syncheight.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.tabs.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery-ui-1.12.1.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.zoom.custom.js"></script>
		<!--<script src="${commonResourcePathHtml}/js/owl.carousel.custom.js"></script>-->
		<script src="${commonResourcePathHtml}/js/jquery.tmpl-1.0.0pre.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.currencies.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.waitforimages.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.slideviewer.custom.1.2.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.autocomplete.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.sanitizer.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.paginationsort.js"></script>

		<%-- Custom ACC JS --%>

		<!--
		<script src="${commonResourcePathHtml}/js/acc.address.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.autocomplete.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.carousel.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.cart.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.cartitem.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.checkout.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.checkoutsteps.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.cms.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.colorbox.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.common.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.forgottenpassword.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.global.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.hopdebug.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.imagegallery.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.langcurrencyselector.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.minicart.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.navigation.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.order.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.paginationsort.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.payment.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.paymentDetails.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.pickupinstore.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.product.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.productDetail.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.quickview.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.ratingstars.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.refinements.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.sanitizer.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.silentorderpost.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.tabs.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.termsandconditions.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.track.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.storefinder.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.futurelink.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.productorderform.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.savedcarts.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.multidgrid.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.quickorder.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.quote.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.consent.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.cookienotification.js"></script>
		<script src="${commonResourcePathHtml}/js/acc.closeaccount.js"></script>

		<script src="${commonResourcePathHtml}/js/acc.csv-import.js"></script>-->

		<script src="${commonResourcePathHtml}/js/_autoload.js"></script>

		<%-- Cms Action JavaScript files --%>
		<c:forEach items="${cmsActionsJsFiles}" var="actionJsFile">
		    <script src="${commonResourcePathHtml}/js/cms/${fn:escapeXml(actionJsFile)}"></script>
		</c:forEach>

		<%-- AddOn JavaScript files --%>
		<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
		    <script src="${fn:escapeXml(addOnJavaScript)}"></script>
		</c:forEach>

	</c:otherwise>
</c:choose>
<!--<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/vendor/swiper.min.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/uadetect.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/main.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/fontsize.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-homepage.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-whypage.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-brandpolicy.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/contact.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/registration.js"></script>-->
<script src="${fn:escapeXml(commonResourcePath)}/intlTelInput/js/intlTelInput.js" defer></script>
<script src="${fn:escapeXml(commonResourcePath)}/yearPicker/dist/yearpicker.js" defer></script>

<%-- performance improvement - Home page --%>

<c:if test = "${pageTitle != 'Homepage' &&
pageTitle != 'About Invest Saudi' &&
pageTitle!='Living in Saudi' &&
pageTitle != 'About The Kingdom' &&
pageTitle != 'Regions Overview' &&
pageTitle != 'Province' &&
pageTitle !=  'Regional Head Quarters' &&
pageTitle != 'Sectors & Opportunities' &&
pageTitle != 'Sector Details' &&
pageTitle != 'Investment Guide' &&
pageTitle != 'Incentives for Investor' &&
pageTitle != 'Media Center' &&
pageTitle != 'News' &&
pageTitle != 'Events' &&
pageTitle != 'Resources' &&
pageTitle != 'Videos' &&
pageTitle != 'About Invest Saudi' &&
pageTitle != 'Contact Us' &&
pageTitle != 'Economic Highlights' &&
pageTitle != 'News Details' &&
pageTitle != 'Event Details' &&
pageTitle != 'Resource Details'
}">
<script src="${fn:escapeXml(commonResourcePath)}/amCharts/core.js" defer></script>
<script src="${fn:escapeXml(commonResourcePath)}/amCharts/charts.js" defer></script>

</c:if>
<%-- performance improvement - Home page  - End --%>

<c:if test="${includeMapJavascripts}">
<!--	<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/offices.js"></script> -->
	<script src="https://maps.googleapis.com/maps/api/js?key=${googleMapsAPIKey}&libraries=places&language=en&callback=initMap&region=SA"></script>
	<c:set var="includeMapJavascripts" value="false" scope="session" />
</c:if>

<c:forEach var="sagiaJavascript" items="${sagiaJavascripts}">
    <script type="text/javascript" src="${themeResourcePath}/${jsPath}/${sagiaJavascript}${version}"></script>
</c:forEach>

<analytics:googleTagManagerDatalayer />

<cms:previewJS cmsPageRequestContextData="${cmsPageRequestContextData}" />
