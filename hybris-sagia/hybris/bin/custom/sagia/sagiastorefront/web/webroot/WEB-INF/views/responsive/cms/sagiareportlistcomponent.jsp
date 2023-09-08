<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />


<section class="page-link-and-title">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:if test="${language eq 'en'}">
                    <a href="/${language}/">
                        <div class="page-breadcrumb-container breadcrumb-container">
                            <span class="breadcrumb-left-icon"></span>
                            <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                        </div>
                    </a>
                </c:if>
                <c:if test="${language eq 'ar'}">
                    <a href="/${language}/">
                        <div class="page-breadcrumb-container breadcrumb-container">
                            <span class="breadcrumb-page-info"><spring:theme code="text.link.home.label"/></span>
                            <span class="breadcrumb-left-icon"></span>
                        </div>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</section>


<div class="container">
    <div class="row p-2">
        <div class="col-md-12 col-sm-12 page-main-content">
            <c:choose>
                <c:when test="${not empty searchPageData.results}">
                <div class="report-container">
                     <h2 class="misa-text-title"><spring:theme code="portal.media.reportTitle" /></h2>
                    <div class="col-md-12">
                    <div class="row">
                        <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                            <tags:report-card result="${result}" loopCount="${status.index}"/>
                        </c:forEach>
                    </div>
                    </div>
                    </div>
                    <div class="col-md-12">
                        <div class="showMoreLessButtonContainer">
                            <button id="loadReportMore" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme
                                    code="review.show.more"/></button>
                            <button id="showReportLess" class="loadNewsEventShowLessButton misa-btn-special"><spring:theme
                                    code="review.show.less"/></button>
                        </div>
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


