<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="eventsIndexPage" value="/events" />
<c:url var="newsletterUrl" value="/newsletter/saveTicket" />
<c:url var="newsletterSubscribeUrl" value="/newsletter/subscribe" />
<c:url var="newsletterUnSubscribeUrl" value="/newsletter/unsubscribe" />

<input type="hidden" id="eventsIndexPage" value="${eventsIndexPage}"/>
<input type="hidden" id="newsletterUrl" value="${newsletterUrl}"/>
<input type="hidden" id="newsletterSubscribeUrl" value="${newsletterSubscribeUrl}"/>
<input type="hidden" id="newsletterUnSubscribeUrl" value="${newsletterUnSubscribeUrl}"/>

<div class="newsletterDiv" >
	<div style="display: none;">
		<div id="newsLetterSubscribeSuccess"
			class="information_message bg-success text-white" style="padding: 20px">
			<i class="icon-success"></i> <span class="c-global-message__text"><spring:theme
					code="blog.newsLetter.subscribe.save.success" /></span>
		</div>

		<div id="newsLetterUnsubscribeSuccess"
			class="information_message bg-success text-white" style="padding: 20px">
			<i class="icon-success"></i> <span class="c-global-message__text"><spring:theme
					code="blog.newsLetter.unsubscribe.save.success" /></span>
		</div>

		<div id="newsLetterSubscribeError"
			 class="information_message bg-danger text-white" style="padding: 20px">
			<i class="icon-warning"></i> <span class="c-global-message__text"><spring:theme
				code="blog.newsLetter.subscribe.save.error" /></span>
		</div>

		<div id="newsLetterSuccess"
			 class="information_message bg-success text-white" style="padding: 20px">
			<i class="icon-success"></i> <span class="c-global-message__text"><spring:theme
				code="blog.newsLetter.save.success" /></span>
		</div>

		<div id="newsLetterError"
			 class="information_message bg-danger text-white" style="padding: 20px">
			<i class="icon-warning"></i> <span class="c-global-message__text"><spring:theme
				code="blog.newsLetter.save.error" /></span>
		</div>
	</div>

	<div id="newsletterStatusModal" class="modal fade bootstrap-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body" style="padding: 20px">

				</div>
				<div class="modal-body-footer text-right" style="padding: 0 20px 20px 20px;">
					<button type="button" class="btn btn-sagia" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

    <form class="newsletterFrom" id="newsletterFrom">
			<div class="newsletterHeading">
				<p><spring:theme code="blog.ticket.title"/></p>
			</div>

			<div class="newsletterContainer">
			    <textarea name="comments" class="form-control text-hint" rows="5" required id="comments"></textarea>
			</div>

			<div class="newsletterContainer">
				<input type="submit" class="btn btn-sagia btn-block btn-sagia-green" value="<spring:theme code="blog.ticket.submit"/>">
			</div>
	</form>




</div>
<div class="goBackLink">
	<br>
	<br>
	<a href="${blogHomePageUrl}" >&laquo; <spring:theme code="blog.page.goBack"/> </a>
</div>

