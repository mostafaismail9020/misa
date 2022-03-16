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


<div class="row col-12 login-screen2 r-sn">
	<div class="login-right-wrapper auth-screen-height">
        <div class="login-register-text">AUTHENTICATE</div>
        <div class="register-role-selection">
            <img src="${commonResourcePath}/images/Partner.svg" alt="Partner" class="img-fluid" />
        </div>
         <div class="login-register-role">Mobile Number VERFICATION</div>
        <div class="row register-user-info ">
            <div class="contentModule-headline contentModule-headline_small">
                <spring:theme code="text.authenticate.code.description" arguments="${maskedPhoneNumber},${maskedEmailID}"/>
            </div>
            <%-- <div class="messageList-message">
                    <spring:theme code="text.authenticate.code.description" arguments="${maskedPhoneNumber},${maskedEmailID}"/>
            </div> --%>

            <form:form modelAttribute="sagiaAuthenticateCodeForm" action="${action}" method="POST" class="w-100">
                <c:if test="${not empty sagiaAuthenticateCodeFormError}">
                    <div class="formError formError-auth">
                        <icon:messageError/><spring:theme code="${sagiaAuthenticateCodeFormError}" arguments=""/>
                    </div>
                </c:if>
                <c:if test="${not empty sagiaAuthenticateCodeIncorrectError}">
                    <div class="formError formError-auth">
                        <icon:messageError/><spring:theme code="${sagiaAuthenticateCodeIncorrectError}" arguments=""/>
                    </div>
                </c:if>
                <div class="mobile-code-wrapper">
                    <formElement:formInputBox idKey="authenticate.code"
                                            labelKey="text.authenticate.code.input" path="code" inputCSS="mobile-code-text validate-mobile mobile-number" labelCSS="mobile-code-label"
                                            mandatory="true" autocomplete="off"/>
                </div>
                <div class="accountLogin-content-formSubmitSection">
                    <div class="contentModule-actions contentModule-actions_centered">
                        <c:url value="/login-second-step/resend" var="resendCodeUrl"/>
                        <a id="resendBtn" class="login-btn login-btn-cancel a-button" href="${resendCodeUrl}"><spring:theme code="text.resend.code.button"/></a>
                        <ycommerce:testId code="authenticate_Code_button">
                            <button type="submit" class="login-btn login-btn-next active btn-block">
                                <%-- <c:choose>
                                    <c:when test="${isVerificationPage}">
                                        Confirm
                                    </c:when>    
                                    <c:otherwise>
                                        Login
                                    </c:otherwise>
                                </c:choose> --%>
                                <spring:theme code='${actionNameKey}'/>
                            </button>
                        </ycommerce:testId>
                        
                    </div>
                    
                    <spring:theme code="text.otp.expiry.message.description"/>
                            <br/> <p class="login-forgot trouble-contact-us mt-3"><spring:theme code="register.login.problem"/> &nbsp<a href="https://misa.gov.sa/ar/contact-us/" ><spring:theme code="register.login.problem.contact"/></a></p>
                </div>
            </form:form>
        </div>
	</div>
</div>
