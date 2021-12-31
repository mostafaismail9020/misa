<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <div class="container pb-4">
        <h2 class="heading text-left mb-2">${component.title}</h2>
        <div class="row">
            <c:forEach items="${lastNews}" var="newsComponent">
                <div class="col-sm-4 my-4 news">
                    <a href="${portal.cmsLinkUrl(newsComponent.newsUrl)}">
                        <img class="corner-borders" src="${fn:escapeXml(newsComponent.newsThumbnailImage.url)}" alt="${newsComponent.newsTitle}">
                        <div class="news-date text-center">
                            <div class="day"><fmt:formatDate value="${newsComponent.newsDate}" pattern="d" /></div>
                            <div class="month"><fmt:formatDate value="${newsComponent.newsDate}" pattern="MMMM" /></div>
                        </div>
                        <h2 class="h1 font-bold news-header">${newsComponent.newsTitle}</h2>
                        <p>${newsComponent.newsShortDescription}</p>
                    </a>
                </div>
            </c:forEach>
        </div>

        <div class="row">
            <div class="col-md-12 float-rigt text-right explore-more mb-2">
                <a href="${portal.cmsLinkUrl(component.exploreAllUrl)}">${component.exploreAllUrlName}</a>
            </div>
        </div>
    </div>
</c:if>