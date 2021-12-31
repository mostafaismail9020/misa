<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
    <c:set var="pageLabel" value="${cmsPage.itemtype eq 'ContentPage' ? cmsPage.label : ''}"/>
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top ${pageLabel ne '/homepage' ? 'fixed-header' : ''}"
         id="insvest-navbar">
        <div class="container px-0">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="">
                <span class="navbar-toggler-icon"></span>
            </button>

            <c:set var="componentLogoLink" value="${portal.cmsLinkUrl(logoLink)}"/>
            <div class="nav-item logo hiddenlarge d-md-none">
                <a class="nav-link" href="${componentLogoLink}">
                    <img src="${component.logoImage.url}" alt="${component.logoImage.altText}"/>
                </a>
            </div>

            <ul class="navbar-nav collapse navbar-collapse" id="navbarResponsive">
                <li class="nav-item text-center img-roya mynav-logo">
                    <a class="nav-link pl-0" href="${componentLogoLink}">
                        <img src="${fn:escapeXml(component.logoImage.url)}"
                             alt="${fn:escapeXml(component.logoImage.altText)}"/>
                    </a>
                </li>
                <c:forEach items="${component.navigationNode.children}" var="childLevel1" varStatus="childLevel1index">
                    <c:set var="linkUrl" value="${not empty childLevel1.entries && not empty childLevel1.entries[0] && not empty childLevel1.entries[0].item ? portal.cmsLinkUrl(childLevel1.entries[0].item) : ''}" />
                    <c:set var="requestUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />
                    <li class="nav-item ${not empty childLevel1.children ? 'text-center list-about ': ''} ${fn:contains(requestUrl, linkUrl) ? 'active' : ''}">
                        <c:forEach items="${childLevel1.entries}" var="childlink1">
                            <c:choose>
                                <c:when test="${not empty childlink1.item && fn:contains(childlink1.item.uid,'language-switch-link')}">
                                    <span class="yCmsComponent nav-link">
                                    <c:choose>
                                        <c:when test="${currentLanguage.isocode eq 'ar'}">
                                            <a href="${fn:replace(requestUrl,'/ar','/en')}"
                                               title="${childlink1.item.linkName}">${childlink1.item.linkName}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${fn:replace(requestUrl,'/en','/ar')}"
                                               title="${childlink1.item.linkName}">${childlink1.item.linkName}</a>
                                        </c:otherwise>
                                    </c:choose>
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <cms:component component="${childlink1.item}" evaluateRestriction="true" element="span" class="nav-link"/>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${not empty childLevel1.children}">
                            <ul class="dropdown-list ">
                                <c:forEach items="${childLevel1.children}" var="childLevel2"
                                           varStatus="childLevel2index">
                                    <li>
                                        <c:forEach items="${childLevel2.entries}" var="childlink2">
                                            <cms:component component="${childlink2.item}" evaluateRestriction="true"
                                                           element="span"
                                                           class="a-nav-item"/>
                                        </c:forEach>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>

                </c:forEach>
            </ul>

            <ul class="navbar-nav float-right">
                <li class="nav-item text-center img-roya left">
                    <a class="nav-link" href="${component.visionLink.url}" target="_blank">
                        <img src="${component.visionImage.url}" alt="${component.visionImage.altText}" width="97"
                             class="roya-height"></a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>
