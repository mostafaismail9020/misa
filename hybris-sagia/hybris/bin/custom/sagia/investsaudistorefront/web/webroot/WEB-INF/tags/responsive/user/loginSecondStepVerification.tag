<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user"%>

<spring:htmlEscape defaultHtmlEscape="true"/>
<c:url value="/login-second-step/authenticate" var="authenticateActionUrl" />


<h1 class="accountLogin-title text-center"><spring:theme code="text.account.profile.sagiaeservices"/></h1>

<user:authenticate actionNameKey="authenticate.submit" action="${authenticateActionUrl}" />
