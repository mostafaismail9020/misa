<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/responsive/template/cms" %>

<c:set var="version" value="${not empty staticResourceVersion ? '?v=': ''}${not empty staticResourceVersion != '' ? staticResourceVersion: ''}" scope="request"/>

<c:choose>
	<c:when test="${false}"> <%-- TODO: enable wro4j ${wro4jEnabled} --%>
		<!--<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(contextPath)}/wro/all_responsive.css" />-->
		<!--<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(contextPath)}/wro/${fn:escapeXml(themeName)}_responsive.css" />-->
		<!--<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(contextPath)}/wro/addons_responsive.css" />-->
	</c:when>
	<c:otherwise>
		<%-- Theme CSS files --%>
<%--		<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/style.css"/>--%>
		<%--  AddOn Common CSS files --%>
		<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
			<link media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(addOnCommonCss)}"/>
		</c:forEach>
	</c:otherwise>
</c:choose>

<%--  AddOn Theme CSS files --%>
<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
	<link media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(addOnThemeCss)}"/>
</c:forEach>

<cms:previewCSS cmsPageRequestContextData="${cmsPageRequestContextData}" />
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/aos.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/style-${currentLanguage.isocode eq 'ar' ? 'ar' : 'en'}.css" />




<!--<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/bootstrap.${currentLanguage.isocode eq 'ar' ? 'ar' : 'en'}.min.css"/>-->
<!--<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/style.css"/>-->
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/main-${currentLanguage.isocode eq 'ar' ? 'ar' : 'en'}.css" />
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/font-awesome.min.css"/>


<%-- Theme CSS files --%>
		<!--<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css${version}"/>-->
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/particles.css${version}"/>
		
<!--<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale,'ar')}">
        <link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/ar.css"/>
    </c:when>
</c:choose>-->

<c:if test="${cmsPage.masterTemplate.uid == 'portalWebinarTemplate'}">
     <link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/webinarjune.css"/>
</c:if>

<%-- performance improvement --%>

<!-- async non-critical CSS -->

<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(themeResourcePath)}/css/owlcarousel/owl.carousel.min.css" >
<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(themeResourcePath)}/css/owlcarousel/owl.theme.default.min.css" >
<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');"href="${fn:escapeXml(themeResourcePath)}/css/popup-multiselect.css">


<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(themeResourcePath)}/css/swiper.min.css">
<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(commonResourcePath)}/intlTelInput/css/intlTelInput.css">
<link rel="stylesheet" media="print" onload="this.onload=null;this.removeAttribute('media');" href="${fn:escapeXml(commonResourcePath)}/yearPicker/dist/yearpicker.css">

<!-- no-JS fallback for non-critical CSS -->
<noscript>
<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
			<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(addOnCommonCss)}"/>
		</c:forEach>
		
<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
	<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(addOnThemeCss)}"/>
</c:forEach>
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/owlcarousel/owl.carousel.min.css" />
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/owlcarousel/owl.theme.default.min.css" />
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/popup-multiselect.css"/>

<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(themeResourcePath)}/css/swiper.min.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(commonResourcePath)}/intlTelInput/css/intlTelInput.css"/>
<link rel="stylesheet" type="text/css" media="all" href="${fn:escapeXml(commonResourcePath)}/yearPicker/dist/yearpicker.css"/>
</noscript>


<%-- performance improvement End --%>