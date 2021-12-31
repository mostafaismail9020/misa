<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/responsive/template/cms" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>

<template:javaScriptVariables/>

<c:set var="commonResourcePathHtml" value="${fn:escapeXml(commonResourcePath)}"/>
<c:choose>
	<c:when test="${false}"> <%-- TODO: enable wro4j ${wro4jEnabled} --%>
	  	<script src="${fn:escapeXml(contextPath)}/wro/all_responsive.js"></script>
	  	<script src="${fn:escapeXml(contextPath)}/wro/addons_responsive.js"></script>
	</c:when>
	<c:otherwise>
		<%-- jquery --%>
		<script src="${commonResourcePathHtml}/js/jquery-3.4.1.min.js"></script>
		
		<%-- plugins --%>
		 <%-- portal jquery --%>
		<script src="${commonResourcePathHtml}/js/jquery-3.2.1.slim.min.js"></script>
		<script src="${commonResourcePathHtml}/js/popper.min.js"></script>
		<script src="${commonResourcePathHtml}/js/bootstrap.min.js"></script>
		<script src="${commonResourcePathHtml}/js/script.js"></script>
		
		<script src="${commonResourcePathHtml}/js/enquire.min.js"></script>
		<script src="${commonResourcePathHtml}/js/Imager.min.js"></script>
		<script src="${commonResourcePathHtml}/js/purify.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.blockUI-2.66.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.colorbox-min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.form.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.hoverIntent.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.pstrength.custom-1.2.0.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.syncheight.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.tabs.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery-ui-1.12.1.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.zoom.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/owl.carousel.custom.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.tmpl-1.0.0pre.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.currencies.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.waitforimages.min.js"></script>
		<script src="${commonResourcePathHtml}/js/jquery.slideviewer.custom.1.2.js"></script>
		
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
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/vendor/swiper.min.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/uadetect.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/main.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/fontsize.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-homepage.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-whypage.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/invest-saudi-brandpolicy.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/contact.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/registration.js"></script>
<script src="${fn:escapeXml(commonResourcePath)}/intlTelInput/js/intlTelInput.js"></script>

<c:if test="${includeMapJavascripts}">
	<script src="${fn:escapeXml(commonResourcePath)}/js/invest-saudi/offices.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=${googleMapsAPIKey}&libraries=places&language=en&callback=initMap&region=SA"></script>
	<c:set var="includeMapJavascripts" value="false" scope="session" />
</c:if>

<analytics:googleTagManagerDatalayer />

<cms:previewJS cmsPageRequestContextData="${cmsPageRequestContextData}" />
