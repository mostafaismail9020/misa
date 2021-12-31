<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce"
	uri="/WEB-INF/common/tld/ycommercetags.tld"%>
<!--  Featured Content display -->

<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${featuredPost.startDate}" var="startDate" />
<c:set var="startDate" value="${fn:split(startDate, '-')}"/>
<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${featuredPost.endDate}" var="endDate"/>
<c:set var="endDate" value="${fn:split(endDate, '-')}"/>

<c:if test="${featuredPost ne null && featuredPost.visible}">
<c:url var="linkUrl" value="${featuredPost.postUrl}" />
<div class="featureDiv">
	<div>
		<c:if test="${featuredPost.featuredPostImageSrc ne null}">
			<a href="${linkUrl}" class="blog-post-link">
				<img src="${featuredPost.featuredPostImageSrc}" class="round-borders">
				<div class="floating-date">
					<div class="start-date">
						<div class="day text-center">${startDate[0]}</div>
						<div class="month text-center">${startDate[1]}</div>
						<div class="year text-center">${startDate[2]}</div>
					</div>
					<div>-</div>
					<div class="end-date">
						<div class="day text-center">${endDate[0]}</div>
						<div class="month text-center">${endDate[1]}</div>
						<div class="year text-center">${endDate[2]}</div>
					</div>
				</div>
			</a>
		</c:if>
		<div style="padding: 10px" class="featureBack">
			<p class="blogDate">
<%--					<span class="blogDate">--%>
<%--						<fmt:formatDate type="date" dateStyle="long" value="${featuredPost.startDate}" /> ---%>
<%--						<fmt:formatDate type="date" dateStyle="long" value="${featuredPost.endDate}" />--%>
<%--					</span> --%>
				<span>${featuredPost.tag}</span>
			</p>
			<p class="blogPostTitle">
				<a href="${linkUrl}">${featuredPost.title}</a>
			</p>
			<p class="blogExtract">
				<c:set var="shortExtractedContent" value="${fn:substring(featuredPost.extractedContent, 0, 200)}" />
				${shortExtractedContent}...
				<a href="${linkUrl}" class="blogReadMore"><spring:theme code="blog.page.readMore"/></a>
			</p>
		</div>
	</div>
</div>
</c:if>