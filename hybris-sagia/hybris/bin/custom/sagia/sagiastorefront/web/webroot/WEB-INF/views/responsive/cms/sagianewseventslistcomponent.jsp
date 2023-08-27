<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>
<div class="container-fluid">
    <div class="row p-2">
    
         <div class="col-md-9 col-sm-12 page-main-content">
            <c:choose>
                <c:when test="${not empty searchPageData.results}">
                <h2>Latest News</h2>
                    <div class="row">
                        <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
	                        <tags:news-card result="${result}" loopCount="${status.index}"/>
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
        
            <div class="col-md-9 col-sm-12 page-main-content">
            <c:choose>
                <c:when test="${not empty eventSearchPageData.results}">
                <h2>Upcoming Events</h2>
                    <div class="row">
                        <c:forEach var="result" items="${eventSearchPageData.results}" varStatus="status">
	                        <tags:events-card result="${result}" loopCount="${status.index}"/>
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
    </div>
</div>
