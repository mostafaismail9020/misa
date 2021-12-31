    <%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
        <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
        <c:set var="hasNextPage"
               value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>



        <template:portalpage pageTitle="${pageTitle}">
            <jsp:body>
                <header:portalPageTitle />

                <cms:pageSlot position="PortalPageTop" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>

                <main>
                    <div class="container mb-4">
                        <div class="row">
                            <c:forEach items="${searchPageData.results}" var="newsComponent">
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
                        <div class="row wow fadeIn" data-wow-delay="0.3s">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <ul class="pagination pg-darkgrey justify-content-center mt-4">
                                    <c:set var="urlWithoutParameter" value="${fn:escapeXml(requestURI)}?page="/>
                                    <c:set var="urlWithParameter" value="${fn:escapeXml(requestURI)}?${fn:escapeXml(queryString)}&page="/>
                                    <c:set var="url" value="${empty fn:escapeXml(queryString) ? urlWithoutParameter : urlWithParameter}"/>
                                    <c:if test="${hasPreviousPage}">
                                        <li class="page-item"><a class="page-link waves-effect waves-light"
                                            href="${url}${searchPageData.pagination.currentPage-1}"><i
                                            class="fa blue-text fa-long-arrow-left" aria-hidden="true"></i></a></li>
                                     </c:if>
                                <c:set var="count" value="1"/>
                                <c:forEach begin="${count}" end="${searchPageData.pagination.numberOfPages}" var="currentPage">
                                    <c:choose>
                                        <c:when test="${currentPage eq param.page + 1}">
                                            <li class="page-item active"><a class="page-link waves-effect waves-light"
                                                    href=${url}${currentPage-1}>${currentPage}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link waves-effect waves-light"
                                                    href=${url}${currentPage-1}>${currentPage}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${hasNextPage}">
                                    <li class="page-item"><a class="page-link waves-effect waves-light"
                                        href="${url}${searchPageData.pagination.numberOfPages-1}"><i
                                            class="fa blue-text fa-long-arrow-right" aria-hidden="true"></i></a></li>
                                </c:if>
                            </ul>

                            </div>
                        </div>
                    </div>
                </main>

                <cms:pageSlot position="PortalPageBottom" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>

            </jsp:body>
        </template:portalpage>