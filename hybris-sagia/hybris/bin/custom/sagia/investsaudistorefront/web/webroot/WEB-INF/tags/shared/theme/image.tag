<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="code" required="true" type="java.lang.String"%>
<%@ attribute name="alt" required="false" type="java.lang.String"%>
<%@ attribute name="title" required="false" type="java.lang.String"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:theme code="${code}" text="/" var="imagePath" />
<c:choose>
	<c:when test="${originalContextPath ne null}">
		<c:choose>
			<c:when test='${fn:startsWith(imagePath, originalContextPath)}'>
				<c:url value="${imagePath}" var="imageUrl" context="/" />
			</c:when>
			<c:otherwise>
				<c:url value="${imagePath}" var="imageUrl"
					context="${originalContextPath}" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:url value="${imagePath}" var="imageUrl" />
	</c:otherwise>
</c:choose>

<img src="${imageUrl}" alt="${alt}" title="${title}" />
