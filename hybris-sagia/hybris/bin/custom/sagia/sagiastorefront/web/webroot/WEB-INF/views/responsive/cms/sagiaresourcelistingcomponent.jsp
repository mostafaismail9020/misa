<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/search/global/autocomplete/sagia-search-box-component" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage" value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<main>
<div class="container">
    <div class="row justify-content-center">
        <c:if test="${not empty searchPageData.results}">
            <div class="col-lg-6 col-md-8 opp-filter-container opportunity-card <c:if test="${language eq 'ar' }"> text-right</c:if> <c:if test="${language eq 'en' }"> text-left</c:if>">
                <div class="sector-search mb-5">
                    <form name="search_form_${fn:escapeXml(component.uid)}" method="get" class="position-relative" action="${searchUrl}">
                        <spring:theme code="portal.opportunity.searchby.placeholder" var="searchPlaceholder"/>
                        <ycommerce:testId code="header_search_input">
                            <input type="text" id="js-site-search-input"
                                data-test="asdfg"
                                class="js-site-search-input" name="q" value="${fn:containsIgnoreCase(request.getParameter("q"), ':') ? '' : request.getParameter("q")}"
                                maxlength="100" placeholder="${searchPlaceholder}"
                                data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "3","waitTimeBeforeRequest" : "500","displayProductImages" : true}'>
                            <a class="a-search">
                                <img class="img-fluid search-icon" width="20" src="${commonResourcePath}/images/Icon-awesome-search.png" alt=""/>
                            </a>
                        </ycommerce:testId>
                        <div class="opportunity-card total-results">
                            <spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>
</main>

<div class="container mt-4">
    <div class="row no-gutters">
        <div class="col-md-12 col-sm-12">
            <c:choose>
                <c:when test="${ not empty searchPageData.results}">
                    <div class="row">
                        <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
	                        <c:choose>
		                        <c:when test="${result.opportunity.resource eq 'Opportunity'}">
		                        	<tags:opportunity-tile result="${result}" loopCount="${status.index}"/>
		                        </c:when>
		                        <c:when test="${result.opportunity.resource eq 'Article'}">
		                        	<tags:article-tile result="${result.opportunity}" loopCount="${status.index}"/>
		                        </c:when>
		                        <c:when test="${result.opportunity.resource eq 'Event'}">
		                        	<tags:event-tile result="${result.opportunity}" loopCount="${status.index}"/>
		                        </c:when>
		                        <c:otherwise>
		                        	<tags:news-tile result="${result.opportunity}" loopCount="${status.index}"/>
		                        </c:otherwise>
	                        </c:choose>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12 col-md-12 mt-4 text-center">
                        <spring:theme code="text.label.notFound"/>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="row wow fadeIn all-opportunity">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <ul class="pagination pg-darkgrey justify-content-center mt-4">

                        <c:if test="${hasPreviousPage}">
                            <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','resourcesList')}" var="previousPageUrl" htmlEscape="true">
                                <spring:param name="page" value="${solrSearchPageData.pagination.currentPage - 1}"/>
                            </spring:url>
                            <li class="page-item previous-page">
                                <a href="${previousPageUrl}">
                                    <div class="prev-next-link">
                                        <svg width="25" height="25" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M10.5625 11.1875C10.75 11.375 10.8438 11.6042 10.8438 11.875C10.8438 12.1042 10.75 12.3333 10.5625 12.5625C10.375 12.75 10.1458 12.8438 9.875 12.8438C9.64583 12.8438 9.41667 12.75 9.1875 12.5625L5.3125 8.6875C5.125 8.45833 5.03125 8.22917 5.03125 8C5.03125 7.72917 5.125 7.5 5.3125 7.3125L9.1875 3.4375C9.41667 3.25 9.64583 3.15625 9.875 3.15625C10.1458 3.15625 10.375 3.25 10.5625 3.4375C10.75 3.625 10.8438 3.85417 10.8438 4.125C10.8438 4.35417 10.75 4.58333 10.5625 4.8125L7.34375 8L10.5625 11.1875Z" fill="#BF9B2E"/>
                                            </svg>
                                    </div>
                                </a>
                            </li>
                        </c:if>

                        <c:set var="limit" value="${numberPagesShown}"/>
                        <c:set var="halfLimit"><fmt:formatNumber value="${limit/1}" maxFractionDigits="0"/></c:set>
                        <c:set var="beginPage">
                            <c:choose>
                                <%-- Limit is higher than number of pages --%>
                                <c:when test="${limit gt searchPageData.pagination.numberOfPages}">1</c:when>
                                <%-- Start shifting page numbers once currentPage reaches halfway point--%>
                                <c:when test="${searchPageData.pagination.currentPage + halfLimit ge limit}">
                                    <c:choose>
                                        <c:when test="${searchPageData.pagination.currentPage + halfLimit lt searchPageData.pagination.numberOfPages}">
                                            <%-- Avoid rounding issue--%>
                                            <c:choose>
                                                <c:when test="${searchPageData.pagination.currentPage + 1 - halfLimit gt 0}">
                                                    ${searchPageData.pagination.currentPage + 1 - halfLimit}
                                                </c:when>
                                                <c:otherwise>1</c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>${searchPageData.pagination.numberOfPages + 1 - limit}</c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>1</c:otherwise>
                            </c:choose>
                        </c:set>
                        <c:set var="endPage">
                            <c:choose>
                                <c:when test="${limit gt searchPageData.pagination.numberOfPages}">
                                    ${searchPageData.pagination.numberOfPages}
                                </c:when>
                                <c:when test="${hasNextPage}">
                                    ${beginPage + limit - 1}
                                </c:when>
                                <c:otherwise>
                                    ${searchPageData.pagination.numberOfPages}
                                </c:otherwise>
                            </c:choose>
                        </c:set>

                        <c:set var="startPagination" value="${beginPage}"/>
                        <c:set var="endPagination" value="${endPage}"/>
                        <c:forEach begin="${startPagination}" end="${endPagination}" var="currentPage">
                            <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','resourcesList')}" var="pageNumberUrl" htmlEscape="true">
                                <spring:param name="page" value="${currentPage - 1}"/>
                            </spring:url>

                            <c:choose>
                                <c:when test="${currentPage eq param.page + 1}">
                                    <li class="page-item active"><a class="page-link waves-effect waves-light"
                                                                    href=${pageNumberUrl}>${currentPage}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link waves-effect waves-light"
                                                             href=${pageNumberUrl}>${currentPage}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${hasNextPage}">
                            <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','resourcesList')}" var="nextPageUrl" htmlEscape="true">
                                <spring:param name="page" value="${solrSearchPageData.pagination.currentPage + 1}"/>
                            </spring:url>
                            <li class="page-item next-page">
                                <a href="${nextPageUrl}">
                                    <div class="prev-next-link">
                                    <svg width="25" height="25" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M5.3125 4.8125C5.125 4.58333 5.03125 4.35417 5.03125 4.125C5.03125 3.85417 5.125 3.625 5.3125 3.4375C5.54167 3.25 5.77083 3.15625 6 3.15625C6.27083 3.15625 6.5 3.25 6.6875 3.4375L10.5625 7.3125C10.75 7.5 10.8438 7.72917 10.8438 8C10.8438 8.22917 10.75 8.45833 10.5625 8.6875L6.6875 12.5625C6.5 12.75 6.27083 12.8438 6 12.8438C5.77083 12.8438 5.54167 12.75 5.3125 12.5625C5.125 12.3333 5.03125 12.1042 5.03125 11.875C5.03125 11.6042 5.125 11.375 5.3125 11.1875L8.53125 8L5.3125 4.8125Z" fill="#BF9B2E"/>
                                        </svg>
                                    </div>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="resource-title no-border">
                <h2>Upcoming Events</h2>
                <div class="action">
                    <a href="/" class="see-more">See More</a>
                </div>
            </div>
        </div>
        <div class="col-sm-12">Events List (needs backend changes)</div>
    <div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="resource-title">
                <h2>Opportunities</h2>
            </div>
        </div>
        <div class="col-sm-12">Opportunities List (needs backend changes)</div>
    <div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="resource-title">
                <h2>News</h2>
            </div>
        </div>
        <div class="col-sm-12">News List (needs backend changes)</div>
        <div class="col-sm-12">
            <a href="/" class="button-large mt-5">See more updates</a>
        </div>
    <div>
</div>


<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="resource-title">
                <h2>Articles</h2>
                <div class="action">
                    <a href="/" class="see-more">See More</a>
                </div>
            </div>
        </div>
        <div class="col-sm-12">Articles  List (needs backend changes)</div>
    <div>
</div>
