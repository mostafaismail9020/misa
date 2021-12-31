<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>

<div class="mx-5 page-main-content">
    <c:choose>
        <c:when test="${ not empty searchPageData.results}">
            <div class="row">
                <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                    <div class="col-lg-4 col-md-6 col-sm-12 my-4 opportunity-card text-center">
                        
                            <div class="content-box">
                                <h2 class="h1 font-bold opp-headtitle" title="${result.opportunity.name}">${result.opportunity.name}</h2>
                                <h3 class="opp-type font-bold">${result.parentCategory.name}</h3>
                                <a href="${encodedContextPath}${result.opportunity.url}">
                                    <div class="button btn know-more-btn"> 
                                        <spring:theme code="portal.opportunity.know.more.button"/>&nbsp;
                                        <img class="img-fluid arrow-icon" src="${commonResourcePath}/images/know-more.png" alt=""/>
                                    </div>
                                </a>
                                <br/>
                                <a href="${encodedContextPath}${result.opportunity.url}/?scrollTo=contact">
                                    <div class="button btn interest-btn"> 
                                        <spring:theme code="portal.opportunity.iam.interested.button"/>&nbsp;
                                        <img class="img-fluid arrow-icon" src="${commonResourcePath}/images/arrow_blue.png" alt=""/>
                                    </div>
                                </a>
                                
                            </div>
                        <img class="img-fluid opportunity-thumbimg" src="${result.opportunity.normal.url}" alt=""/>
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
