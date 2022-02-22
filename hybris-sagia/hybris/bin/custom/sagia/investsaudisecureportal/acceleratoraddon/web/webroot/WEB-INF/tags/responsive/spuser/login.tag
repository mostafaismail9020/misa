<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<!--<div class="headline">
   &nbsp; <%-- <spring:theme code="login.title"/> --%>
</div>-->

<script>
function validateLoginForm(){
    var username = $("#j_username");
    var password = $("#j_password");
    var recaptcha = document.forms["loginForm"]["g-recaptcha-response"].value;
    
    username.removeClass('hasError');
    password.removeClass('hasError');
    var valid = true;
 
    if (username.val() === "" ){       
        username.addClass('hasError');
        valid = false;
    }
    if(password.val() === "" ){
        password.addClass('hasError');
        valid = false;
    }
    if(recaptcha === ""){
        $("#lblErrorCaptcha").text("Please fill reCAPTCHA");
        valid = false;
       } 
    return valid;
}

function recaptchaCallback(){
    $(".js-recaptcha-captchaaddon").siblings('span#lblErrorCaptcha').text('');           
}

</script>
<style>
.mandatory{
color:#ff0000;
font-size:12px;
}
.hasError{
background:#fec3c3;
border-color:#fd7b7b;
}
</style>

<form:form action="${action}" method="post" modelAttribute="loginForm" name="loginForm" onsubmit="return validateLoginForm()" >
    <c:if test="${not empty message}">
		<span class="has-error"> <spring:theme code="${message}"/></span>
    </c:if>

 <div class="row col-lg-12 col-xl-12 col-12 login-screen r-sn">
        <div class="login-right-wrapper">
            <div class="login-register-text">LOGIN</div>
            <div class="register-role-selection">
                <img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid">
            </div>


        <div class="row register-user-info ">
            <div class="col-md-12 register-form">
                <formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" mandatory="true" inputCSS="register-user-details" labelCSS="register-user-info-label"/>
            </div>
            <div class="col-md-12 register-form">
                <formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="form-control register-user-details" labelCSS="register-user-info-label" mandatory="true"/>
                    <%--<i toggle="#password-field" class="fa fa-eye toggle-password" aria-hidden="true"  id="togglePassword"></i>--%>
            </div>
            	
            	<div class="col-md-12 register-form">
						<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
		        <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
		        <span id="lblErrorCaptcha" class="mandatory d-flex"></span>
		     </div>
        </div>	

    <div class="login-buttons accountLogin-content-formSubmitSection">
        <c:set var="loginBtnClasses" value="col-sm-12 col-md-6 col-md-push-6"/>

        <c:if test="${enableRegistration}">
            <c:set var="loginBtnClasses" value="col-sm-6 col-sm-push-6"/>
        </c:if>

        <div class="${loginBtnClasses}">
            <ycommerce:testId code="login_Login_button">
                <button type="submit" class="login-btn login-btn-next active"  >
                    <spring:theme code="${actionNameKey}"/>
                </button>
            </ycommerce:testId>
        </div>

        <div class="col-sm-6 col-sm-pull-6">
            <c:if test="${enableRegistration}">
                <a href="<c:url value='/register'/>">
                    <button type="button" class="login-btn login-entry-register">
                        <spring:theme code="text.secureportal.link.createAccount"/>
                    </button>
                </a>
            </c:if>
        </div>
    </div>

    <div class="forgotten-password login-forgot">
        <ycommerce:testId code="login_forgotPassword_link">
            <a href="#" data-link="<c:url value='/login/pw/request'/>" class="js-password-forgotten"
               data-cbox-title="<spring:theme code="forgottenPwd.title"/>">
                <spring:theme code="login.link.forgottenPwd"/>
            </a>
        </ycommerce:testId>
    </div>
</form:form>

