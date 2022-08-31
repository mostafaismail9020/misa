<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<div class="mx-5 page-main-content">

    <hr/>

    <c:choose>
        <c:when test="${ not empty searchPageData.results}">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 opportunity-card">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <div class="form-group form-inline">
                            <label for="opportunity-search"><spring:theme code="sagia.sort.sort.by"/>:</label>&nbsp;
                            <select id="opportunity-search" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                <option value="null" disabled></option>
                                <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.ascending"/> </option>
                                <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.descending"/> </option>
                                <option value="amount_asc"><spring:theme code="sagia.sort.lowest"/> </option>
                                <option value="amount_desc"><spring:theme code="sagia.sort.highest"/> </option>
                                <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.oldest"/></option>
                                <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.latest"/></option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 opportunity-card text-right">
                    <spring:message code="portal.opportunity.search.opportunities.totalResults"
                           arguments="${searchPageData.pagination.totalNumberOfResults}"/>
                </div>
            </div>

            <div class="row" style="margin-top: 75px;">
                <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                    <div class="col-12 col-lg-4 mb-5">
                        <div class="news-card">
                            <div class="news-date text-center">
                                <div class="day"><fmt:formatDate value="${result.resourceDate}" pattern="d" /></div>
                                <div class="month"><fmt:formatDate value="${result.resourceDate}" pattern="MMMM" /></div>
                            </div>
                            <img class="img-fluid w-100 news-card-img" src="${fn:escapeXml(lastReportsBox2.resourceShortInformation)}" alt="${result.resourceTitle}"/>
                            <div class="news-card-inner">
                                <h3 title="${result.resourceTitle}">${result.resourceTitle}</h3>
                                <p>${result.resourceShortInformation}</p>
                                <a class="btn btn-primary-fill btn-knowmore" href="${result.resourceUrl}">
                                    <span class="arow-icon"><img class="img-fluid" src="${commonResourcePath}/images/news_page/download.png" alt=""/></span>&nbsp;
                                    <spring:theme code="portal.media.resourcedetails.download" text="Download"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="col-lg-12 col-md-12 mt-4 text-center">
                <spring:theme code="text.label.notFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<div class="row wow fadeIn all-opportunity">

    <div class="col-lg-12 col-md-12 col-sm-12">
        <ul class="pagination pg-darkgrey justify-content-center mt-4">

            <c:set var="urlWithoutParameter" value="${fn:escapeXml(requestURI)}?page="/>
            <c:set var="urlWithParameter" value="${fn:escapeXml(requestURI)}?${fn:escapeXml(queryString)}&page="/>
            <c:set var="url" value="${empty fn:escapeXml(queryString) ? urlWithoutParameter : urlWithParameter}"/>

            <c:if test="${hasPreviousPage}">
                <li class="page-item previous-page">
                    <a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.currentPage-1}">
                        <img class="img-fluid previous-left-blue-icon" width="20" src="${commonResourcePath}/images/previous-arrow-left.png" alt=""/>
                        <img class="img-fluid previous-left-white-icon" style="display: none;" width="20"
                        		src="${commonResourcePath}/images/previous-white-arrow-left.png" alt=""/>
                    </a>
                </li>
            </c:if>

            <c:set var="startPagination" value="${not empty startPagination ? startPagination : 1}"/>
            <c:set var="endPagination" value="${not empty endPagination ? endPagination : searchPageData.pagination.numberOfPages}"/>
            <c:forEach begin="${startPagination}" end="${endPagination}" var="currentPage">
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
                <li class="page-item next-page">
                    <a class="page-link waves-effect waves-light" href="${url}${searchPageData.pagination.currentPage + 1}"  style="padding-top: 5px;">
                        <img class="img-fluid arrow-left-blue-icon" width="20" src="${commonResourcePath}/images/Icon-feather-arrow-left.png" alt=""/>
                        <img class="img-fluid arrow-left-white-icon" style="display: none;" width="20"
                        		src="${commonResourcePath}/images/Icon-white-arrow-left.png" alt=""/>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
