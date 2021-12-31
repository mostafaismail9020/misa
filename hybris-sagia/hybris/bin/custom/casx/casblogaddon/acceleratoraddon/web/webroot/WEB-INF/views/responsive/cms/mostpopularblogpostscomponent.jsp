<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="most-popular-area">
	<h2 class="popularPostHeading"><spring:theme code="blog.page.lable.mostpopuar"/></h2>
	<ul class="list-unstyled">
		<c:forEach items="${popularPosts}" begin="0"
			end="${numberOfPostToShow}" var="post" varStatus="index">
			<c:if test="${post.visible}">
				<c:url var="linkUrl" value="${post.postUrl}" />
				<li>
					<p class="blogDate">
						<span class="blogDate">
							<fmt:formatDate type="date" dateStyle="long" value="${post.startDate}" /> - 
							<fmt:formatDate type="date" dateStyle="long" value="${post.endDate}" />
						</span> <span>${post.tag}</span>
					</p>
					<p class="blogPostTitle">
						<a href="${linkUrl}">${post.title}</a>
					</p>
					<p class="blogExtract">
						<c:set var="extractedContent" value="${fn:substring(post.extractedContent, 0, 80)}" />
						${extractedContent}...
						<a href="${linkUrl}" class="blogReadMore"><spring:theme code="blog.page.readMore"/></a>
					</p>
				</li>
				<hr>
			</c:if>
		</c:forEach>
	</ul>
</div>
