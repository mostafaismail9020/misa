<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="userTags" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

	<div class="contentModule-headline contentModule-headline_small">
	<c:if test="${empty isEnableTwoFactorAuthService}">
		<spring:theme code="text.authenticate.code.description"/>
	</c:if>
	</div>
		<div class="messageList-message">
			<c:if test="${isEnableTwoFactorAuthService}">
				<spring:theme code="text.twofactor.authentication.code.description" arguments="${maskedPhoneNumber},${maskedEmailID}"/>
			</c:if>
		</div>
	<form:form modelAttribute="sagiaAuthenticateCodeForm" action="${action}" id="sagiaAuthenticationForm" method="POST">
	    <c:if test="${not empty sagiaAuthenticateCodeFormError}">
	        <div class="formError">
	            <icon:messageError/><spring:theme code="${sagiaAuthenticateCodeFormError}" arguments=""/>
	        </div>
	    </c:if>
	    <c:if test="${not empty sagiaAuthenticateCodeIncorrectError}">
	        <div class="formError">
	            <icon:messageError/><spring:theme code="${sagiaAuthenticateCodeIncorrectError}" arguments=""/>
	        </div>
	    </c:if>
	    <formElement:formInputBox idKey="authenticate.code"
	                              labelKey="text.authenticate.code.input" path="code" inputCSS="form-control"
	                              mandatory="true" autocomplete="off"/>
	    <div class="accountLogin-content-formSubmitSection">
	        <div class="contentModule-actions contentModule-actions_centered">
	            <ycommerce:testId code="authenticate_Code_button">
	                <button type="submit" class="btn btn-default btn-block">
		               	<c:choose>
						    <c:when test="${isVerificationPage}">
						    	Confirm
						    </c:when>    
						    <c:otherwise>
						        Login
						    </c:otherwise>
						</c:choose>
	                    <%-- <spring:theme code='${actionNameKey}'/> --%>
	                </button>
	            </ycommerce:testId>
	            <c:choose>
				    <c:when test="${isVerificationPage}">
				    	<c:url value="/verification/resend" var="resendCodeUrl"/>
				    </c:when>
				    <c:when test="${isEnableTwoFactorAuthService}">
				    	<c:url value="/verification/twoFactorAuth" var="resendCodeUrl"/>
				    </c:when>
				    <c:otherwise>
				        <c:url value="/login-second-step/resend" var="resendCodeUrl"/>
				    </c:otherwise>
				</c:choose>
                <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}"/>
                 <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
	            <a id="resendBtn" class="btn btn-secondary" href="${resendCodeUrl}"><spring:theme code="text.resend.code.button"/></a>
	        </div>
	        <spring:theme code="text.otp.expiry.message.description"/>
	        	<br/> <p><spring:theme code="register.login.problem"/> &nbsp<a href="https://misa.gov.sa/ar/contact-us/" ><spring:theme code="register.login.problem.contact"/></a></p>
	    </div>
	</form:form>
	
