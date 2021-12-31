<%@ tag body-content="tagdependent" deferredSyntaxAllowedAsLiteral="true"  trimDirectiveWhitespaces="true"%>
<%@ attribute name="activeTabName" required="true" type="java.lang.String"%>
<%@ attribute name="tabs" required="true" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

<ul class="js-tabComponent">
    <c:forEach var="tabItem" items="${tabs}">
        <c:if test="${activeTabName == tabItem}">
            <li class='active'>
                <a href="#<c:out value='${tabItem}' />" data-toggle="tab"><c:out value="${tabItem}" /></a>
            </li>
        </c:if>
        <c:if test="${not (activeTabName == tabItem)}">
            <li>
                <a href="#<c:out value='${tabItem}' />" data-toggle="tab"><c:out value="${tabItem}" /></a>
            </li>
        </c:if>
    </c:forEach>
</ul>