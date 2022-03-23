<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:url value="${fn:escapeXml(urlLink)}" var="encodedUrl" />

<div class="banner__component simple-banner">
	<c:choose>
		<c:when test="${empty encodedUrl || encodedUrl eq '#' || encodedUrl eq 'login'}">
			<!-- <img title="${fn:escapeXml(media.altText)}" alt="${fn:escapeXml(media.altText)}"
				src="${media.url}"> -->
				
					<img title="${fn:escapeXml(media.altText)}" alt="${fn:escapeXml(media.altText)}"
					src="/investsaudistorefront/_ui/responsive/common/images/INV-logo-white-en.svg">
				
		</c:when>
		<c:otherwise>
			 <a href="${encodedUrl}"><!--<img title="${fn:escapeXml(media.altText)}"
				alt="${fn:escapeXml(media.altText)}" src="${media.url}"> -->
				<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
					<div class=" display-xs display-sm js-logged_in">
						<img title="${fn:escapeXml(media.altText)}" alt="${fn:escapeXml(media.altText)}"
						src="/investsaudistorefront/_ui/responsive/common/images/logo_green.png">
					</div>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
						<div class=" display-xs display-sm">
						<img title="${fn:escapeXml(media.altText)}" alt="${fn:escapeXml(media.altText)}"
							src="/investsaudistorefront/_ui/responsive/common/images/INV-logo-white-en.svg">
						</div>
					</sec:authorize>
					<div class="register-page-logo display-md">
						<img title="${fn:escapeXml(media.altText)}" alt="${fn:escapeXml(media.altText)}"
						src="/investsaudistorefront/_ui/responsive/common/images/INV-logo-white-en.svg">
					</div>
			
			</a>
		</c:otherwise>
	</c:choose>
</div>