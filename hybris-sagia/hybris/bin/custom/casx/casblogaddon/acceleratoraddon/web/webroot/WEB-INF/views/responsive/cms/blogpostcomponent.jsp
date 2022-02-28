<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="blogPostDiv">
	<c:url var="blogHomePageUrl" value="/events" />

	<c:if test="${postImage ne null}">
		<img src="${postImage.url}" class="round-borders">
	</c:if>

	<div class="fb-share-button" data-href="" data-layout="button" data-size="large">
		<a onclick="window.open('http://www.facebook.com/share.php?u='+escape(window.location.href))" class="fb-xfbml-parse-ignore">Share</a>
	</div>
	<p class="blogDate">
		<span class="blogPostDateFormat">
			<fmt:formatDate type="date" dateStyle="long" value="${startDate}" /> -
			<fmt:formatDate type="date" dateStyle="long" value="${endDate}" />
		</span> <span>${tag}</span>
	</p>
	<%-- <h3 class="blogPostTitle">${title}</h3> --%>
	<div class="blogContent">
		${content}
	</div>
	<br>
	<div class="newsletterFormDiv row">
		<div class="goBackLinkNews col-sm-6"> <a href="${blogHomePageUrl}" >&laquo; <spring:theme code="blog.page.goBack"/> </a></div>

		<form id="subscribeNewsletter" class="col-sm-6">
			<input type="hidden" id="eventDetailForNewsletter" name="eventDetailForNewsletter" value="${uniqueCode}" />
			<input type="hidden" id="subscriptionFlag" name="subscriptionFlag" value="${newsletterSubscriptionFlag}" />
			<div id="postNewsletterDiv">
				<input type="submit" id="subscribe2NewsletterButton" class="btn btn-sagia btn-sagia-green" style="display:${newsletterSubscriptionFlag?'none':'block'}" value="<spring:theme code="blog.newsLetter.subsrcibe"/>">
				<input type="submit" id="unSubscribe2NewsletterButton" class="btn btn-sagia btn-sagia-green" style="display:${newsletterSubscriptionFlag?'block':'none'}"  value="<spring:theme code="blog.newsLetter.unsubsrcibe"/>">
			</div>
		</form>	</div>
	<!--<div class="goBackLinkNews">
	<br><br>
	
	</div>-->
</div> 