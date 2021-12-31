<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="searchUrl" required="true" %>
<%@ attribute name="searchPageData" required="true" type="de.hybris.platform.core.servicelayer.data.SearchPageData" %>
<%@ attribute name="top" required="true" type="java.lang.Boolean" %>
<%@ attribute name="supportShowAll" required="true" type="java.lang.Boolean" %>
<%@ attribute name="supportShowPaged" required="true" type="java.lang.Boolean" %>
<%@ attribute name="sortQueryParams" required="false" %>
<%@ attribute name="partSearchText" required="false" %>
<%@ attribute name="orderSearchText" required="false" %>
<%@ attribute name="companyOrders" required="false" %>
<%@ attribute name="msgKey" required="false" %>
<%@ attribute name="displaySortBy"  required="false" type="java.lang.Boolean" %>
<%@ attribute name="classes"  required="false" type="java.lang.String" %>
<%@ attribute name="sortByClasses"  required="false" type="java.lang.String" %>
<%@ attribute name="showShowingOf" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="themeMsgKey" value="${not empty msgKey ? msgKey : 'search.page'}"/>
<c:set var="displaySortBy" value="${not empty displaySortBy ? displaySortBy : true}"/>
<c:set var="showShowingOf" value="${not empty showShowingOf ? showShowingOf : true}"/>

<div class="sorter ${classes}">
	<c:if test="${not empty searchPageData.sorts && displaySortBy}">
		<form id="sort_form${top ? '1' : '2'}" name="sort_form${top ? '1' : '2'}" method="get" action="#" class="form-inline ${sortByClasses}">
			<c:if test="${not empty sortQueryParams}">
				<c:forEach var="queryParam" items="${fn:split(sortQueryParams, '&')}">
					<c:set var="keyValue" value="${fn:split(queryParam, '=')}"/>
					<c:if test="${not empty keyValue[1]}">
						<input type="hidden" name="${keyValue[0]}" value="${keyValue[1]}"/>
					</c:if>
				</c:forEach>
			</c:if>

			<label for="sortOptions${top ? '1' : '2'}"><spring:theme code="${themeMsgKey}.sortTitle"/></label>
			<div class="select-wrapper">
				<select id="sortOptions${top ? '1' : '2'}" name="sort" class="sortOptions form-control" ${fn:length(searchPageData.results) eq 0 ? 'disabled=disabled' : ''}>
					<c:forEach items="${searchPageData.sorts}" var="sort">
						<option value="${sort.code}" ${sort.selected ? 'selected="selected"' : ''}>
						<c:choose>
							<c:when test="${not empty sort.name}">
								${sort.name}
							</c:when>
							<c:otherwise>
								<spring:theme code="${themeMsgKey}.sort.${sort.code}"/>
							</c:otherwise>
						</c:choose>
						</option>
					</c:forEach>
				</select>
			</div>

			<c:catch var="errorException">
				<spring:eval expression="searchPageData.currentQuery.query" var="dummyVar"/><%-- This will throw an exception is it is not supported --%>
				<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
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
			<c:if test="${not empty partSearchText}">
				<input type="hidden" name="text" value="${partSearchText}"/>
			</c:if>
			<c:if test="${not empty orderSearchText}">
				<input type="hidden" name="text" value="${orderSearchText}"/>
			</c:if>
			<c:if test="${not empty companyOrders}">
				<input type="hidden" name="companyOrders" value="${companyOrders}"/>
			</c:if>
		</form>
	</c:if>

		<c:if test="${supportShowPaged}">
			<spring:url value="${searchUrl}" var="showPageUrl" htmlEscape="true">
				<spring:param name="show" value="Page" />
			</spring:url>
			<ycommerce:testId code="searchResults_showPage_link">
				<a href="${showPageUrl}"><spring:theme code="${themeMsgKey}.showPageResults" /></a>
			</ycommerce:testId>
		</c:if>

	<c:if test="${(searchPageData.pagination.numberOfPages > 1)}">
		<div class="pagination_page">

			<spring:url value="${searchUrl}" var="pageUrl" htmlEscape="true"/>
			<ul class="c-pagination">
				<c:if test="${showShowingOf}">
				<li class="page-item c-pagination__page-showing-total">
					Showing ${searchPageData.pagination.currentPage * 21 + 1} - ${searchPageData.pagination.currentPage * 21 + fn:length(searchPageData.results)} of ${searchPageData.pagination.totalNumberOfResults}
				</li>
				</c:if>
				<li class="page-item c-pagination__page-item">
				<c:if test="${searchPageData.pagination.numberOfPages > 9}">
					<c:set var="start" scope="session" value="${searchPageData.pagination.currentPage - (searchPageData.pagination.currentPage % 10) }"/>
					<c:choose>
						<c:when test="${(searchPageData.pagination.currentPage + 1) gt (searchPageData.pagination.numberOfPages - (searchPageData.pagination.numberOfPages % 10)) }">
							<c:set var="end" scope="session" value="${searchPageData.pagination.numberOfPages-1}"/>
						</c:when>
						<c:otherwise>
							<c:set var="end" scope="session" value="${searchPageData.pagination.currentPage + 9 - (searchPageData.pagination.currentPage % 10) }"/>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${start}" end="${end}">
						<c:if test="${searchPageData.pagination.currentPage eq i}">
				<li class="page-item c-pagination__page-item active"><span class="c-pagination__page-link active"> ${i+1}</span></li>
						</c:if>
						<c:if test="${searchPageData.pagination.currentPage != i}">
							<li class="page-item c-pagination__page-item"><a class="c-pagination__page-link" href="${pageUrl}&page=${i}">${i+1}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${searchPageData.pagination.numberOfPages <= 9 }">
					<c:forEach var="i" begin="0" end="${searchPageData.pagination.numberOfPages - 1}">
						<c:if test="${searchPageData.pagination.currentPage eq i}">
							<li class="page-item c-pagination__page-item active"><span class="c-pagination__page-link active"> ${i+1}</span></li>
						</c:if>
						<c:if test="${searchPageData.pagination.currentPage != i}">
							<li class="page-item c-pagination__page-item"><a href="${pageUrl}&page=${i}" class="c-pagination__page-link">${i+1}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				</li>
				<li class="page-item c-pagination__prev previous_page">
					<c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}"/>
					<c:if test="${hasPreviousPage}">
						<spring:url value="${searchUrl}" var="previousPageUrl" htmlEscape="true">
							<spring:param name="page" value="${searchPageData.pagination.currentPage - 1}"/>
						</spring:url>
						<ycommerce:testId code="searchResults_previousPage_link">
							<a href="${previousPageUrl}">
								<span><spring:theme code="${themeMsgKey}.linkPreviousPage"/></span></a>
						</ycommerce:testId>
					</c:if>
					<c:if test="${not hasPreviousPage}">
						<span class="not-allowed">
							<spring:theme code="${themeMsgKey}.linkPreviousPage"/>
						</span>
					</c:if>
				</li>
				<li class="c-pagination__pipe-line"> | </li>
				<li class="page-item c-pagination__next">
					<c:set var="hasNextPage" value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}"/>
					<c:if test="${hasNextPage}">
						<spring:url value="${searchUrl}" var="nextPageUrl" htmlEscape="true">
							<spring:param name="page" value="${searchPageData.pagination.currentPage + 1}"/>
						</spring:url>
						<ycommerce:testId code="searchResults_nextPage_link">
							<a href="${nextPageUrl}" >
								<span><spring:theme code="${themeMsgKey}.linkNextPage"/></span>
							</a>
						</ycommerce:testId>
					</c:if>
					<c:if test="${not hasNextPage}">
						<span class="not-allowed">
							<spring:theme code="${themeMsgKey}.linkNextPage"/>
						</span>
					</c:if>
				</li>
			</ul>

		</div>
	</c:if>
</div>
