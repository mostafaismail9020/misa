<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/responsive/template/cms" %>

<c:set var="version" value="${not empty staticResourceVersion ? '?v=': ''}${not empty staticResourceVersion != '' ? staticResourceVersion: ''}" scope="request"/>

<c:choose>
	<c:when test="${wro4jEnabled}">
		<link rel="stylesheet" type="text/css" media="all" href="${contextPath}/wro/all_responsive.css${version}" />
		<link rel="stylesheet" type="text/css" media="all" href="${contextPath}/wro/${themeName}_responsive.css${version}" />
		<link rel="stylesheet" type="text/css" media="all" href="${contextPath}/wro/addons_responsive.css${version}" />
		
	</c:when>
	<c:otherwise>
		<%-- Theme CSS files --%>
		<!--<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css${version}"/>-->
		<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/particles.css${version}"/>
		<%--  AddOn Common CSS files --%>
		<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
			<link rel="stylesheet" type="text/css" media="all" href="${addOnCommonCss}${version}"/>
		</c:forEach>
		
		<%--  AddOn Theme CSS files --%>
		<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
			<link rel="stylesheet" type="text/css" media="all" href="${addOnThemeCss}${version}"/>
		</c:forEach>
		
	</c:otherwise>
</c:choose>



<%-- <link rel="stylesheet" href="${commonResourcePath}/blueprint/print.css" type="text/css" media="print" /> 
<style type="text/css" media="print">
	@IMPORT url("${commonResourcePath}/blueprint/print.css");
</style>
 --%>

<cms:previewCSS cmsPageRequestContextData="${cmsPageRequestContextData}" />
