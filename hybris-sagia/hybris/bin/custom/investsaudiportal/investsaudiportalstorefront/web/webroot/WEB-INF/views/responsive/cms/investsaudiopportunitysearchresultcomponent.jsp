<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<div class="container page-main-content">
    <c:choose>
        <c:when test="${ not empty searchPageData.results}">
            <div class="row">
                <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                    <div class="col-lg-4 col-md-6 col-sm-12 my-4">
                        <a href="${encodedContextPath}${result.opportunity.url}">
                            <div class="content-box fearured-opp">
                                <h2 class="h1 font-bold fearured-opp-headtitle">${result.opportunity.name}</h2>
                                <h3 class="fearured-opp-type font-bold"><span class="sector-icon-mini"><img
                                        src="${result.parentCategory.picture.url}"
                                        alt="${result.parentCategory.picture.altText}"></span>${result.parentCategory.name}
                                </h3>
                                <div class="button btn org-button btn-green"><spring:theme code="text.button.learnMore"/></div>
                            </div>
                        </a>
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

<div class="row wow fadeIn">

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
                <li class="page-item"><a class="page-link waves-effect waves-light"
                                         href="${url}${searchPageData.pagination.currentPage + 1}"><i
                        class="fa blue-text fa-long-arrow-right" aria-hidden="true"></i></a></li>
            </c:if>
        </ul>

    </div>
</div>
