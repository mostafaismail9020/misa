<%@attribute name="blog"
	type="com.casblogaddon.model.BlogPostComponentModel"
	required="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${blog.startDate}" var="startDate" />
<c:set var="startDate" value="${fn:split(startDate, '-')}"/>
<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${blog.endDate}" var="endDate"/>
<c:set var="endDate" value="${fn:split(endDate, '-')}"/>

<div class="blogContainerDiv">
	<c:url var="linkUrl" value="${blog.postUrl}" />
	<c:if test="${blog.thumbnailImageSrc ne null}">
		<a href="${linkUrl}" style="color: black;" class="blog-post-link">
			<img src="${blog.thumbnailImageSrc}" class="round-borders" />
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
	<div class="blogContainerBackGround">
		<p class="blogDate">
<%--			<span>--%>
<%--				<fmt:formatDate type="date" dateStyle="long" value="${blog.startDate}" /> - --%>
<%--				<fmt:formatDate type="date" dateStyle="long" value="${blog.endDate}" />--%>
<%--			</span> --%>
			<span>${blog.tag}</span>
		</p>
		<p class="blogPostTitle">
			<a href="${linkUrl}">${blog.title}</a>
		</p>
		<p class="blogExtract">
					<c:set var="extractedContent" value="${fn:substring(blog.extractedContent, 0, 90)}" />
					${extractedContent}...
					<a href="${linkUrl}" class="blogReadMore"><spring:theme code="blog.page.readMore"/></a>
		</p>
	</div>
</div>
