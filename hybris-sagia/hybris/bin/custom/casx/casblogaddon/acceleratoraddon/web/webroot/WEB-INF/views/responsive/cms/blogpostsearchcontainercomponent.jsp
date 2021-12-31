<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce"
	uri="/WEB-INF/common/tld/ycommercetags.tld"%>
<%@ taglib prefix="blog" tagdir="/WEB-INF/tags/addons/casblogaddon/desktop"%>

<c:set var="blogIndexURL" value="/events"/>

<div class="containerBorder blog--search-container">
	<c:forEach items="${blogSearchData.results}" var="blog" varStatus="status">
		<blog:blogSearchContainer blog="${blog}" />
	</c:forEach>
</div>

<div class="toolbar-bottom">
	<blog:investSaudiPagination top="false"  numberPagesShown="5" supportShowPaged="false" supportShowAll="false" searchPageData="${blogSearchData}" searchUrl="${blogSearchIndexUrl}"/>
</div>