<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>


<c:choose>
    <c:when test="${responseCommitted}">
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
    </c:when>
    <c:otherwise>
        <template:portalpage pageTitle="${pageTitle}">

            <jsp:body>
                <section class="page-title">
                    <div class="container main-content-area pb-3">
                        &nbsp;
                    </div>
                </section>

                <cms:pageSlot position="PortalPageTop" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>

                <main>

                    <cms:pageSlot position="PortalPageMain" var="slotComponent">
                        <cms:component component="${slotComponent}"/>
                    </cms:pageSlot>

                </main>

                <cms:pageSlot position="PortalPageBottom" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>
            </jsp:body>
        </template:portalpage>
    </c:otherwise>
</c:choose>
