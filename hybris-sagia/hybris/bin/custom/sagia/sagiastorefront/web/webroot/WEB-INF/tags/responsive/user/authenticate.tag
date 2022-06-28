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

<style type="text/css">
    .footer,.copyright {
        display: none;
    }
</style>	

<div class="contentModule-headline_1 contentModule-headline_small mobile-verify-text auth-mobile">
<!-- <c:if test="${empty isEnableTwoFactorAuthService}">
	<spring:theme code="text.authenticate.code.description"/>
</c:if> -->
</div>
<div class="messageList-message row reg-para-text">
	<c:if test="${isEnableTwoFactorAuthService}">
		<spring:theme code="text.twofactor.authentication.code.description" arguments="${maskedPhoneNumber},${maskedEmailID}"/>
	</c:if>
</div>
<form:form modelAttribute="sagiaAuthenticateCodeForm" action="${action}" method="POST">
	<c:if test="${not empty sagiaAuthenticateCodeFormError}">
		<div class="row"> 
			<div class="formError reg-form-error">
				<icon:messageError/><spring:theme code="${sagiaAuthenticateCodeFormError}" arguments=""/>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty sagiaAuthenticateCodeIncorrectError}">
		<div class="row"> 
			<div class="formError reg-form-error">
				<icon:messageError/><spring:theme code="${sagiaAuthenticateCodeIncorrectError}" arguments=""/>
			</div>
		</div>
	</c:if>
	
	<div class="mobile-code-wrapper">
		<formElement:formInputBox idKey="authenticate.code" path="code" labelKey="text.authenticate.code.input" labelCSS="mobile-code-label" inputCSS="form-control mobile-code-text validate-mobile mobile-number" mandatory="true" autocomplete="off"/>
	</div>	
	<div class="col-md-12 captcha-pos">
            	<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
            	<div class="form_field-elements control-group js-recaptcha-captchaaddon" style=""></div>
            	<span id="lblErrorCaptchareg" class="mandatory"></span>
            </div>
	<div class="accountLogin-content-formSubmitSection">
		<div class="contentModule-actions contentModule-actions_centered">
			<ycommerce:testId code="authenticate_Code_button">
				<button type="submit" class="login-btn login-btn-next active btn-block">
					<c:choose>
						<c:when test="${isVerificationPage}">
							<spring:theme code="authenticate.confirm.label"/>
						</c:when>    
						<c:otherwise>
							<%--Login--%>
							<spring:theme code="${actionNameKey}"/>
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
			
			<a id="resendBtn" class="login-btn login-entry-cancel padding-top-10" href="${resendCodeUrl}"><spring:theme code="text.resend.code.button"/></a>
		</div>
		<div class="trouble-contact-us"><spring:theme code="text.otp.expiry.message.description"/></div>
		<br/>
		<p class="trouble-contact-us"><spring:theme code="register.login.problem"/> &nbsp;
			<a href="https://misa.gov.sa/ar/contact-us/" class="login-forgot d-inline-block"><spring:theme code="register.login.problem.contact"/></a>
		</p>
		<div><a id="backtoLogin" class="login-btn login-entry-cancel padding-top-10" href="/en/login"><spring:theme code="register.back.login"/></a></div>
	</div>
</form:form>>
