<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ attribute name="loopIndex" required="false" type="java.lang.String" %>
<%@ attribute name="startMap" required="true" type="java.util.Map" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<c:forEach var="entry" items="${startMap}" varStatus="parentLoop">
    <c:url var="index" value="${loopIndex}_${parentLoop.index}"/>
    
    <c:choose>
        <c:when test="${empty loopIndex or loopIndex eq '0'}">
            <c:url value="${entry.key.url}" var="pageUrl"/>
            <h5><a href="${pageUrl}">${fn:escapeXml(entry.key.name)}</a></h5>
        </c:when>
        <c:otherwise>
            <li>
                <c:url value="${entry.key.url}" var="pageUrl"/>
                <a href="${pageUrl}">${fn:escapeXml(entry.key.name)}</a>
            </li>
        </c:otherwise>
    </c:choose>

    <c:if test="${fn:length(entry.value) > 0}">
        <c:choose>
            <c:when test="${portal.countCharactersOccurence(index) < 2}">
                <ul class="list-unstyled my-3">
                    <nav:siteMapHierarchy startMap="${entry.value}" loopIndex="${index}"/>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="site-map-align-left">
                <nav:siteMapHierarchy startMap="${entry.value}" loopIndex="${index}"/>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:if>

</c:forEach>