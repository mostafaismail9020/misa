<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header" %>

<li class="sagiaNavigation-list-hasSub">
    <a href="#"><span><spring:theme code="header.services.text"/></span></a>
    <!-- populates categories under their label and populate data-target-submenu to be used when services are populated -->
    <div class="sagiaNavigation-subPane js-sagiaNavigation-subNav">
        <ul class="sagiaNavigation-subNav">
            <li class="sagiaNavigation-subNav-title">
                <a href="${encodedContextPath}/service-search"><icon:your-services-requests-overview/>Overview</a>
            </li>
            <c:if test="${not empty navcategories['FIRST']}">
                <c:forEach items="${navcategories['FIRST']}" var="category">
                    <li class="sagiaNavigation-subNav-icon sagiaNavigation-subNav-icon">
                        <div class="serviceModule-icon">
                            <a href="" data-target-submenu="${category.name}">
                                <c:if test="${not empty category.menuIcon.url}">
                                    <img src="${category.menuIcon.url}" width="25" height="25"/>
                                </c:if>
                                <c:choose>
                                    <c:when test="${fn:length(category.name) gt 0}">
                                        ${category.name}
                                    </c:when>
                                    <c:otherwise>
                                        ${category.code}
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                    </li>
                </c:forEach>
            </c:if>
            <c:if test="${not empty navcategories}">
                <c:forEach items="${navcategories}" var="label">
                    <c:if test="${label.key ne 'FIRST'}">
                    <li class="sagiaNavigation-subNav-subtitle">${label.key}</li>
                    <c:forEach items="${label.value}" var="category">
                        <li class="sagiaNavigation-subNav-icon sagiaNavigation-subNav-icon">
                            <div class="serviceModule-icon">
                                <a href="" data-target-submenu="${category.name}">
                                    <c:if test="${not empty category.menuIcon.url}">
                                        <img src="${category.menuIcon.url}" width="25" height="25"/>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${fn:length(category.name) gt 0}">
                                            ${category.name}
                                        </c:when>
                                        <c:otherwise>
                                            ${category.code}
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                    </c:if>
                </c:forEach>
            </c:if>
        </ul>

    <!-- populates services on categories using data-submenu value -->
        <div class="sagiaNavigation-subRight">
            <c:if test="${not empty navservices}">
                <c:forEach items="${navservices}" var="category" >
                    <div class="sagiaNavigation-subRight-pane js-sagiaNavigation-subRight-pane" data-submenu="${category.key}">
                        <c:forEach items="${category.value}" var="service" varStatus="serviceLoop">
                            <a href="${encodedContextPath}/${service.menuUrl}"><span>${service.name}</span></a>
                        </c:forEach>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</li>