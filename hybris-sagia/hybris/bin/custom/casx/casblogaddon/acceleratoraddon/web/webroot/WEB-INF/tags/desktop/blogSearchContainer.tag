<%@attribute name="blog"
	type="com.casblogaddon.model.BlogPostComponentModel"
	required="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${featuredPost.startDate}" var="startDate" />
<c:set var="startDate" value="${fn:split(startDate, '-')}"/>
<fmt:formatDate type="date" pattern="dd-MMMM-Y" value="${featuredPost.endDate}" var="endDate"/>
<c:set var="endDate" value="${fn:split(endDate, '-')}"/>

<div class="blogContainerDiv">
	<c:url var="linkUrl" value="${blog.postUrl}" />
	<%--<c:if test="${blog.thumbnailImageSrc ne null}">--%>
		<%--<c:url var="imgUrl" value="${blog.thumbnailImageSrc}" />--%>
		<%--<a href="${linkUrl}" style="color: black;"><img src="${imgUrl}" /></a>--%>
	<%--</c:if>--%>
	<div class="blogContainerBackGround">
		<p class="blogDate">
			<span class="blogDate">
				<fmt:formatDate type="date" dateStyle="long" value="${blog.startDate}" />
				<fmt:formatDate type="date" dateStyle="long" value="${blog.endDate}" />
			</span>
			<span>${blog.tag}</span>
		</p>
		<p class="blogPostTitle">
			<a href="${linkUrl}" style="color: black;">${blog.title}</a>
		</p>
		<p class="blogExtract">
					<c:set var="extractedContent" value="${fn:substring(blog.extractedContent, 0, 90)}" />
					${extractedContent}...
					<a href="${linkUrl}" class="blogReadMore"><spring:theme code="blog.page.readMore"/></a>
		</p>
	</div>
</div>
