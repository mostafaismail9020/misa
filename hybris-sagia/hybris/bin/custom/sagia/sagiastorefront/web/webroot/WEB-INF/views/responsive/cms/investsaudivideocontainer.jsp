<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="row d-flex align-items-stretch">
        <c:forEach var="currentComponent" items="${components}" varStatus="status">
            <div class="col-md-6 mb-4">
                <div class="embed-responsive embed-responsive-16by9">
                    <c:choose>
                        <c:when test="${not empty currentComponent.videoLink}">
                            <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" src="${fn:escapeXml(currentComponent.videoLink.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise>
                            <video class="video-fluid z-depth-1 vplayer embed-responsive-item" controls poster="${fn:escapeXml(currentComponent.poster.url)}" preload="none">
                              <source src="${fn:escapeXml(currentComponent.video.url)}" type="${fn:escapeXml(currentComponent.video.mime)}">
                              <spring:theme code="video.browser.does.not.support.HTML5"/>
                            </video>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="text-capitalize mt-2">${currentComponent.descriptionText}</div>
            </div>
        </c:forEach>
    </div>
</div>

