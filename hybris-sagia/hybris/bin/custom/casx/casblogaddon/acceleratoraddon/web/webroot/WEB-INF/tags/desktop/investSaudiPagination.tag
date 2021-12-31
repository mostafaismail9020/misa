<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="searchUrl" required="true" %>
<%@ attribute name="searchPageData" required="true"
              type="de.hybris.platform.core.servicelayer.data.SearchPageData" %>
<%@ attribute name="top" required="true" type="java.lang.Boolean" %>
<%@ attribute name="showTopTotals" required="false" type="java.lang.Boolean" %>
<%@ attribute name="supportShowAll" required="true" type="java.lang.Boolean" %>
<%@ attribute name="supportShowPaged" required="true" type="java.lang.Boolean" %>
<%@ attribute name="additionalParams" required="false" type="java.util.HashMap" %>
<%@ attribute name="msgKey" required="false" %>
<%@ attribute name="showCurrentPageInfo" required="false" type="java.lang.Boolean" %>
<%@ attribute name="hideRefineButton" required="false" type="java.lang.Boolean" %>
<%@ attribute name="numberPagesShown" required="false" type="java.lang.Integer" %>
<%@ taglib prefix="blog" tagdir="/WEB-INF/tags/addons/casblogaddon/desktop"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/responsive/nav/pagination" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="themeMsgKey" value="${not empty msgKey ? msgKey : 'search.page'}"/>
<c:set var="showCurrPage" value="${not empty showCurrentPageInfo ? showCurrentPageInfo : false}"/>
<c:set var="hideRefBtn" value="${hideRefineButton ? true : false}"/>
<c:set var="showTotals" value="${empty showTopTotals ? true : showTopTotals}"/>

<c:if test="${searchPageData.pagination.totalNumberOfResults == 0 && top && showTotals}">
    <div class="paginationBar top clearfix">
        <ycommerce:testId code="searchResults_productsFound_label">
            <div class="totalResults"><spring:theme code="${themeMsgKey}.totalResults"
                                                    arguments="${searchPageData.pagination.totalNumberOfResults}"/></div>
        </ycommerce:testId>
    </div>
</c:if>

<c:if test="${searchPageData.pagination.totalNumberOfResults > 0}">
    <div class="pagination-bar ${(top)?"top":"bottom"}" style="margin: 0">
        <div class="pagination-toolbar" style="border:0">
            <c:if test="${true}">
                <div class="helper clearfix hidden-md hidden-lg"></div>
                <div class="sort-refine-bar">
                    <div class="row">
                        <div class="col-xs-12 pagination-wrap">
                            <blog:investSaudiPageSelectionPagination searchUrl="${searchUrl}" searchPageData="${searchPageData}"
                                                                     numberPagesShown="${numberPagesShown}"
                                                                     themeMsgKey="${themeMsgKey}"/>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${top && showTotals}">
            <div class="row">
                <div class="col-xs-12">
                    <div class="pagination-bar-results">
                        <ycommerce:testId code="searchResults_productsFound_label">
                            <c:choose>
                                <c:when test="${showCurrPage}">
                                    <c:choose>
                                        <c:when test="${searchPageData.pagination.totalNumberOfResults == 1}">
                                            <spring:theme code="${themeMsgKey}.totalResultsSingleOrder"/>
                                        </c:when>
                                        <c:when test="${searchPageData.pagination.numberOfPages <= 1}">
                                            <spring:theme code="${themeMsgKey}.totalResultsSinglePag"
                                                          arguments="${searchPageData.pagination.totalNumberOfResults}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="currentPageItems"
                                                   value="${(searchPageData.pagination.currentPage + 1) * searchPageData.pagination.pageSize}"/>
                                            <c:set var="upTo"
                                                   value="${(currentPageItems > searchPageData.pagination.totalNumberOfResults ? searchPageData.pagination.totalNumberOfResults : currentPageItems)}"/>
                                            <c:set var="currentPage"
                                                   value="${searchPageData.pagination.currentPage * searchPageData.pagination.pageSize + 1} - ${upTo}"/>
                                            <spring:theme code="${themeMsgKey}.totalResultsCurrPage"
                                                          arguments="${currentPage},${searchPageData.pagination.totalNumberOfResults}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="${themeMsgKey}.totalResults"
                                                  arguments="${searchPageData.pagination.totalNumberOfResults}"/>
                                </c:otherwise>
                            </c:choose>
                        </ycommerce:testId>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</c:if>