<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user"%>

<spring:htmlEscape defaultHtmlEscape="true"/>

<c:choose>
    <c:when test="${isVerificationPage}">
        <c:url value="/verification/authenticate" var="authenticateActionUrl" />
    </c:when>    
    <c:otherwise>
        <c:url value="/login-second-step/authenticate" var="authenticateActionUrl" />
    </c:otherwise>
</c:choose>

<h1 class="accountLogin-title headline login-page__headline text-center"><spring:theme code="text.account.profile.sagiaeservices"/></h1>

<user:authenticate actionNameKey="authenticate.submit" action="${authenticateActionUrl}" />
