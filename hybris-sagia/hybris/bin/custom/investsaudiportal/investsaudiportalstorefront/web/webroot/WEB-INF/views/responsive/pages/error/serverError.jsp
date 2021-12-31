<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="container page-contents pb-3">
    <div class="heading text-left mb-2 mt-4">
        <spring:message code="system.error.server.error"/>
    </div>
    <spring:message code="system.error.server.error.assigned.code"/>
    <p><b>${assignedErrorCode}</b></p>
    <p>&nbsp;</p>
    <c:if test="${not empty stacktrace}">
        <pre>${stacktrace}</pre>
    </c:if>
</section>
    