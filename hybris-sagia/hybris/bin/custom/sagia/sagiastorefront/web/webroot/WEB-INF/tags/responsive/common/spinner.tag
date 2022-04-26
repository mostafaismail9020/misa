<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ attribute name="spinnerId" required="false" type="java.lang.String"%>
<%@ attribute name="useSVG" required ="false" type="java.lang.Boolean"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:theme code="img.spinner" text="/" var="spinnerPath" />
<c:choose>
	<c:when test="${originalContextPath ne null}">
		<c:url value="${spinnerPath}" context="${originalContextPath}" var="spinnerUrl" />
	</c:when>
	<c:otherwise>
		<c:url value="${spinnerPath}" var="spinnerUrl" />
	</c:otherwise>
</c:choose>

<c:if test="${empty spinnerId}">
	<c:set var="spinnerId" value="spinner"/>
</c:if>

<div id="spinnerMainDiv" class="spinnerContainer hidden">
	<div id="spinnerOverlay" class="spinnerOverlay"></div>
	<div id="${spinnerId}" class="spinner">
		<c:choose>
			<c:when test="${useSVG}">
				<icon:loading-spinner />
			</c:when>
			<c:otherwise>
				<img src="${spinnerUrl}" class="spinner-icon" alt=""/>
			</c:otherwise>
		</c:choose>
		<span id="spinnerMessage" class="spinner-message"><spring:theme code="text.loadingMessage"/></span>
	</div>
</div>