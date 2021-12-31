<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<body>
	<div class="newsletterDiv" >
		<div style="display: none;">
			<div id="newsLetterSuccess"
				class="information_message bg-success text-white">
				<i class="icon-success"></i> <span class="c-global-message__text"><spring:theme
						code="blog.newsLetter.save.success" /></span>
			</div>
	
			<div id="newsLetterError"
				class="information_message bg-danger text-white">
				<i class="icon-warning"></i> <span class="c-global-message__text"><spring:theme
						code="blog.newsLetter.save.error" /></span>
			</div>
		</div>
		
		<form class="simplefixSignUpFrom" id="simplefixSignUpFrom">
			<div class="newsletterHeading">
				<p><spring:theme code="blog.simplefix.signup.title"/></p>
			</div>

			<div class="newsletterContainer">
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.title"/>" name="title" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.firstName"/>" name="firstName" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.lastName"/>" name="lastName" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.email"/>" name="email" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.company"/>" name="company" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.phoneNumber"/>" name="phoneNumber" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.manufacturer"/>" name="manufacturer" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.model"/>" name="model" required>
				<input type="text" placeholder="<spring:theme code="blog.simplefix.signup.describeYourSimpleFix"/>" name="describeYourSimpleFix" required>
			</div>

			<div class="newsletterContainer">
				<input type="submit" value="<spring:theme code="blog.simplefix.signup.contactUs"/>">
			</div>
		</form>
	</div>
</body>
