<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce"
	uri="/WEB-INF/common/tld/ycommercetags.tld"%>
<%@ taglib prefix="blog" tagdir="/WEB-INF/tags/addons/casblogaddon/desktop"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
<input type="hidden" id="startDateVal" value="${startDate}"/>
<input type="hidden" id="endDateVal" value="${endDate}"/>
<input type="hidden" id="rangeVal" value="${range}"/>
<input type="hidden" id="multipleMonth" value="${multipleMonth}"/>

<c:url var="blogIndexURL" value="/events?${ not empty params? params : 'startDate='}${startDate}&endDate=${endDate}"/>
<c:set var="paginationParams">
	<c:choose>
		<c:when test="${endDate ne 0}">
			?startDate=${startDate}&endDate=${endDate}
		</c:when>
	</c:choose>
</c:set>
<c:set var="paginationURL" value="/events${paginationParams}"/>

<div class="blogContainerDiv calendarContainerDiv">
<%--	<div class="text-right" style="font-size: 10px;color: #808080;padding-right: 5px;">* Double-click month to select all days</div>--%>
	<div class="wrapper"><input type="checkbox" id="monthRange" ${multipleMonth eq true ? 'checked="checked"' : ''}><label for="monthRange">Select multiple months:</label></div>
	<div id="calendar"></div>
	<form:form id="selectDateForm" name="selectDateForm" modelAttribute="selectDateForm" action="${blogIndexURL}">
		<div class="casblogaddon-filter">
			<input type="button" id="reset" name="reset" onclick="javascript:resetFilter()" class="btn btn-sagia reset-btn" value="Reset"/>&nbsp;&nbsp;
			<input type="button" id="filter" name="filter" onclick="javascript:filterBlogs()" class="btn btn-sagia btn-sagia-green filter-btn" value="Filter"/>
		</div>
		<div class="clearfix"></div>
	</form:form>
</div>
<div class="containerBorder">
	<c:forEach items="${blogData.results}" var="blog" varStatus="status">
		<blog:blogContanier blog="${blog}" />
	</c:forEach>
</div>
<div class="toolbar-bottom" style="border-right: 1px solid rgba(0, 0, 0, 0.1)">
	<blog:investSaudiPagination top="false" numberPagesShown="5" supportShowPaged="false" supportShowAll="false" searchPageData="${blogData}" searchUrl="${paginationURL}"/>
</div>
