<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/search/autocomplete/sagia-search-box-component" var="autocompleteUrl" htmlEscape="false">
	<spring:param name="componentuid"  value="${component.uid}"/>
</spring:url>
<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
<c:set var="hasNextPage"
       value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>
<div class="container">
    <div class="row p-2">
        <c:if test="${not empty searchPageData.results}">
            <div class="col-md-3 col-sm-12 my-4 d-none d-md-block opp-filter-container opportunity-card <c:if test="${language eq 'ar' }"> text-right</c:if> <c:if test="${language eq 'en' }"> text-left</c:if>">
				<form name="search_form_${fn:escapeXml(component.uid)}" method="get" action="${searchUrl}">
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
					<div class="opportunity-card total-results mt-2">
						<spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
					</div>
				</form>
                <div id="product-facet" style="height: inherit" class="hidden-sm hidden-xs product__facet js-product-facet">
                    <!-- <nav:facetNavAppliedFilters pageData="${solrSearchPageData}"/> -->
                    <nav:facetNavRefinements pageData="${solrSearchPageData}"/>
				</div>
            </div>
        </c:if>
        <div class="col-md-9 col-sm-12 page-main-content">
            <c:if test="${not empty searchPageData.results}">
            <div class="opp-mobile-show text-center">
            	<spring:theme code="portal.opportunity.search.modal.btn" var="btnModalTxt"/>
				<button id="opp-open-modal-filter" class="btn btn-secondary-fill">${btnModalTxt}</button>
				<div class="opportunity-card total-results mt-2 mb-3">
					<spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
				</div>
            </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 col-6 opportunity-card opp-filter opp-mobile-show">
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
                                        <div class="opp-filter-container" style="padding: 20px;">
                                        	<nav:facetNavRefinements pageData="${solrSearchPageData}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal End -->
                    </div>

                    <div class="opportunity-card opp-sort">
                        <div class="dashboardWidget-headline js-dashboardWidget-headline">
                            <form id="sortForm1" name="sortForm1" method="get" action="#">
                                <div class="sorting">
                                    <div class="sortby">
                                        <label for="opportunity-search" class="full"><spring:theme code="sagia.sort.sort.by"/></label>
                                        <select id="sortOptions1" name="sort" class="form-control--plp-sorting browser-default custom-select form-control">
                                            <c:forEach items="${solrSearchPageData.sorts}" var="sort">
                                                <option value="${fn:escapeXml(sort.code)}" ${sort.selected? 'selected="selected"' : ''}>
                                                    <c:choose>
                                                        <c:when test="${not empty sort.name}">
                                                            ${fn:escapeXml(sort.name)}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <spring:theme code="${themeMsgKey}.sort.${sort.code}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </option>
                                            </c:forEach>
                                        </select>                                         
                                    </div>
                                    <span class="item-count">
                                        <spring:message code="portal.opportunity.search.opportunities.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}"/>
                                    </span>
                                </div>
                                <c:catch var="errorException">
                                    <spring:eval expression="solrSearchPageData.currentQuery.query"
                                                 var="dummyVar"/><%-- This will throw an exception is it is not supported --%>
                                    <input type="hidden" name="q" value="${solrSearchPageData.currentQuery.query.value}"/>
                                </c:catch>

                                <c:if test="${supportShowAll}">
                                    <ycommerce:testId code="searchResults_showAll_link">
                                        <input type="hidden" name="show" value="Page"/>
                                    </ycommerce:testId>
                                </c:if>
                                <c:if test="${supportShowPaged}">
                                    <ycommerce:testId code="searchResults_showPage_link">
                                        <input type="hidden" name="show" value="All"/>
                                    </ycommerce:testId>
                                </c:if>
                                <c:if test="${not empty additionalParams}">
                                    <c:forEach items="${additionalParams}" var="entry">
                                        <input type="hidden" name="${fn:escapeXml(entry.key)}" value="${fn:escapeXml(entry.value)}"/>
                                    </c:forEach>
                                </c:if>
                            </form>
                        </div>
                    </div>

            </c:if>

            <c:choose>
                <c:when test="${ not empty searchPageData.results}">
                    <div class="row">
                        <c:forEach var="result" items="${searchPageData.results}" varStatus="status">
                            <tags:opportunity-card result="${result}" loopCount="${status.index}"/>
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
</div>

<div class="container">
    <div class="row wow fadeIn all-opportunity">

        <div class="col-lg-12 col-md-12 col-sm-12">
            <ul class="pagination pg-darkgrey justify-content-center mt-4">

                <c:if test="${hasPreviousPage}">
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','sectors-opportunities/opportunities')}" var="previousPageUrl" htmlEscape="true">
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
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','sectors-opportunities/opportunities')}" var="pageNumberUrl" htmlEscape="true">
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
                    <spring:url value="${fn:replace(solrSearchPageData.currentQuery.url,'search','sectors-opportunities/opportunities')}" var="nextPageUrl" htmlEscape="true">
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
