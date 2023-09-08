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

<main>
<div class="container">
    <div class="row justify-content-center">
        <c:if test="${not empty opportunitySearchPageData.results}">
            <div class="col-lg-6 col-md-8 opp-filter-container opportunity-card <c:if test="${language eq 'ar' }"> text-right</c:if> <c:if test="${language eq 'en' }"> text-left</c:if>">
                <div class="sector-search mb-3">
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
                            <spring:message code="portal.resources.search.totalResults" arguments="${fn:length(opportunitySearchPageData.results) + fn:length(eventSearchPageData.results) + fn:length(newsSearchPageData.results) + fn:length(articleSearchPageData.results)}"/>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>
</main>

<div class="container">
    <c:choose>
        <c:when test="${ not empty opportunitySearchPageData.results}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="resource-title">
                        <h2>Opportunities</h2>
                    </div>
                </div>
                <c:forEach var="result" items="${opportunitySearchPageData.results}" varStatus="status">
                    <tags:opportunity-tile result="${result}" loopCount="${status.index}"/>
                </c:forEach>  
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="action">
                        <a href="/sectors-opportunities/opportunities" class="submit-button mt-5"><spring:theme code="review.show.more"/></a>
                    </div>                    
                </div>
            </div>   
            <div class="section-break mt-5 mb-5"></div>  
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${not empty newsSearchPageData.results}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="resource-title mb-4">
                        <h2><spring:theme code="text.newsevents.listing.page.latest.news"/></h2>
                    </div>
                </div>
                <c:forEach var="result" items="${newsSearchPageData.results}" varStatus="status">
                    <tags:news-tile result="${result}" loopCount="${status.index}"/>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="action">
                        <a href="/mediaCenter/news" class="submit-button mt-0"><spring:theme code="review.show.more"/></a>
                    </div>                    
                </div>
            </div>
            <div class="section-break mt-5 mb-5"></div>          
        </c:when>
    </c:choose>    

    <c:choose>
        <c:when test="${not empty eventSearchPageData.results}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="resource-title mb-5">
                        <h2><spring:theme code="text.newsevents.listing.page.upcoming.events"/></h2>
                    </div>
                </div>
                <c:forEach var="result" items="${eventSearchPageData.results}" varStatus="status">
                    <tags:events-card result="${result}" loopCount="${status.index}"/>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="action">
                        <a href="/mediaCenter/events" class="submit-button mt-0"><spring:theme code="review.show.more"/></a>
                    </div>                    
                </div>
            </div>
            <div class="section-break mt-5 mb-5"></div>
        </c:when>
    </c:choose>        

    <c:choose>
        <c:when test="${not empty articleSearchPageData.results}">
            <div class="row">
                <div class="col-sm-12">
                    <div class="resource-title mb-4">
                        <h2>Articles</h2>
                    </div>
                </div>
                <c:forEach var="result" items="${articleSearchPageData.results}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index % 2 == 0}">
                            <tags:article-tile result="${result}" loopCount="${status.index}" className="evenColor"/>
                        </c:when>
                        <c:otherwise>
                            <tags:article-tile result="${result}" loopCount="${status.index}" className="oddColor"/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="action">
                        <a href="articlesList" class="submit-button mt-4"><spring:theme code="review.show.more"/></a>
                    </div>                    
                </div>
            </div>                
        </c:when>
    </c:choose>
</div>
