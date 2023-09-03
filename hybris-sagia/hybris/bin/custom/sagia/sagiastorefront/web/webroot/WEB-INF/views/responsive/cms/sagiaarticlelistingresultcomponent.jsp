<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>


<div class="article-listing-page-banner">
    <div class="article-listing-page-banner-container" data-aos="fade-up">
        <div class="container">
            <div class="row">
                <a href="/${language}/">
                    <div class="col-md-12 breadcrumb-container">
                        <span class="breadcrumb-left-icon"></span>
                        <span class="breadcrumb-page-info">Home</span>
                    </div>
                </a>
                <div class="col-md-12">
                    <h1 class="article-detail-page-general-title">Article Listing</h1>
                </div>
            </div>
        </div>
    </div>
</div>

<cms:pageSlot position="PortalPageMainTop" var="slotComponent">
    <cms:component component="${slotComponent}"/>
</cms:pageSlot>

<div class="container">
    <div class="row">
            <c:if test="${not empty searchPageData.results}">
            <div class="opp-mobile-show text-center col-md-12 d-none">
            	<spring:theme code="portal.opportunity.search.modal.btn" var="btnModalTxt"/>
				<button id="opp-open-modal-filter" class="btn btn-secondary-fill">${btnModalTxt}</button>
				<div class="opportunity-card total-results" style="text-align: center; margin: 10px 0 5px 0px;">
					<spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
				</div>
            </div>
                    <div class="col-md-12 opportunity-card opp-filter opp-mobile-show">
                        <!-- Modal -->
                        <div class="modal fade" id="facetFilterModal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog opportunity-modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class='section-headline my-5 all-opportunity-filter'>
                                            <spring:theme code="portal.opportunity.search.filter"/>
                                        </h1>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <i class="fa fa-close"></i>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal End -->


                </div>
            </c:if>
        <c:choose>
            <c:when test="${ not empty searchPageData.results}">
                <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index % 2 == 0}">
                            <tags:article-card result="${result}" loopCount="${status.index}" className="evenColor"/>
                        </c:when>
                        <c:otherwise>
                            <tags:article-card result="${result}" loopCount="${status.index}" className="oddColor"/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-md-12 mt-4 text-center">
                    <spring:theme code="text.label.notFound"/>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="row wow fadeIn all-opportunity">

        <div class="col-lg-12 col-md-12 col-sm-12">
            <ul class="pagination pg-darkgrey justify-content-center mt-4">

                <c:if test="${hasPreviousPage}">
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','articlesList')}" var="previousPageUrl" htmlEscape="true">
                        <spring:param name="page" value="${solrSearchPageData.pagination.currentPage - 1}"/>
                    </spring:url>
                    <li class="page-item previous-page">
                        <a class="page-link waves-effect waves-light" href="${previousPageUrl}">
                            <img class="img-fluid previous-left-blue-icon" width="20" src="${commonResourcePath}/images/previous-arrow-left.png" alt=""/>
                            <img class="img-fluid previous-left-white-icon" style="display: none;" width="20"
                                 src="${commonResourcePath}/images/previous-white-arrow-left.png" alt=""/>
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
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','articlesList')}" var="pageNumberUrl" htmlEscape="true">
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
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','articlesList')}" var="nextPageUrl" htmlEscape="true">
                        <spring:param name="page" value="${solrSearchPageData.pagination.currentPage + 1}"/>
                    </spring:url>
                    <li class="page-item next-page">
                        <a class="page-link waves-effect waves-light" href="${nextPageUrl}"  style="padding-top: 10px;">
                            <img class="img-fluid arrow-left-blue-icon" width="20" src="${commonResourcePath}/images/Icon-feather-arrow-left.png" alt=""/>
                            <img class="img-fluid arrow-left-white-icon" style="display: none;" width="20"
                                 src="${commonResourcePath}/images/Icon-white-arrow-left.png" alt=""/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>