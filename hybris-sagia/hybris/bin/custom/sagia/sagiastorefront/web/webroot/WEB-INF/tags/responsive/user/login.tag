<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />


<spring:htmlEscape defaultHtmlEscape="true"/>
<c:set var="hideDescription" value="checkout.login.loginAndCheckout"/>

<div class="row m_login_container">
	<div class="col-md-5 m_login_title">
		<div class="login-logo">
			<a href="/${language}">
				<img src="${commonResourcePath}/images/B2C/Login-logo.svg" alt="logo" class="img-fluid w-100" />
			</a>
		</div>
		<div id="sagia-cms-help-quick-login-helper"></div>
	</div>
    <div class="col-md-7 m_login_section">
        <form:form action="${action}" method="post" modelAttribute="loginForm" name="sagiaLoginForm" id="sagiaLoginForm" onsubmit="return validateLoginForm()">
            <c:if test="${not empty message}">
                <span class="has-error"><spring:theme code="${message}"/></span>
            </c:if>

            <div class="row col-lg-12 col-xl-12 col-12 login-screen r-sn">
                <div class="login-right-wrapper">
                    <div class="login-register-text"><spring:theme code="dashboard.login.label"/></div>
                    <div class="register-role-selection"> 
                        <img src="${commonResourcePath}/images/B2C/Partner.png" alt="Investor" class="img-fluid img-partner-active login-role" />
                    </div>	 
                    
                    
                    <div class="row register-user-info ">
                        <div class="col-md-12 register-form">
                            <!-- <label class="register-user-info-label" for="j_username">User Name<span class="mandatory">*</span></label>
                        <input type="text" class="register-user-details" data-val="true" data-val-required="Required" id="j_username" name="username" />-->
                            <formElement:formInputBox idKey="j_username" labelKey="login.username" labelCSS="control-label_mandatory register-user-info-label" inputCSS ="register-user-details" path="j_username" mandatory="true"/>
                            <div class="error-msg"></div>
                        </div>
                        
                        <div class="col-md-12 register-form">
                        <!-- <label class="register-user-info-label" for="j_password">Password<span class="mandatory">*</span></label>
                            <input type="password" class="register-user-details" data-val="true" data-val-required="Required"	id="j_password" name="password" maxlength="30" /> -->
                            <formElement:formPasswordBox idKey="j_password" labelKey="login.password" labelCSS="control-label_mandatory register-user-info-label " path="j_password" inputCSS="register-user-details" mandatory="true" />
                            <!--<i class="far fa-eye" id="togglePassword"></i>-->
                            <i toggle="#password-field" class="fa fa-eye-slash toggle-password" aria-hidden="true"  id="togglePassword"></i>
                            <div class="error-msg"></div>
                        </div>
                    
                        <div class="col-md-12 register-form form-floating country-code-mobile">														
                            <div class="col-xl-5 col-12 mx-0 px-0 register-language" id="loginSelectLanguageDiv">
                                <span ><spring:theme code="general.language"/></span>
                                <label class="switch">
                                    <input type="checkbox" id="loginSelectLanguageSelect">
                                    <div class="slider round"></div>
                                </label>
                            </div>
                        </div>

                        <div class="captcha-pos">
                            <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
                            <div class="form_field-elements control-group js-recaptcha-captchaaddon" style=""></div>
                            <span id="lblErrorCaptcha" class="mandatory"></span>
                        </div>
                    </div>	
                <%-- <span id="lblError" class="mandatory"></span>	--%>
                    <div class="row login-error-form">
                        <div class="formError">
                            <c:if test="${not empty loginFormError}">
                            <icon:messageError/>
                            <c:choose>
                                <c:when test="${disabledUser}">
                                    <spring:theme code="login.error.disabled.user" text="Password is disabled kindly click on forget my password to renew your password." arguments=""/>
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="${loginFormError}" arguments=""/>
                                </c:otherwise>
                            </c:choose>
                            </c:if>										
                        </div>
                    </div>
                    <div class="login-buttons accountLogin-content-formSubmitSection">
                        <div class="col-md-6 col-12">
                            <button class="login-btn login-entry-cancel cancel-disabled"><spring:theme code="investor.registration.cancel.button"/></button>
                        </div>
                        <div class="col-md-6 col-12">													
                            <!--<button class="login-btn login-btn-next" >LOGIN</button>-->
                            <ycommerce:testId code="loginAndCheckoutButton">
                                <button type="submit" class="login-btn login-btn-next active" id="sagiaLoginBtn"><spring:theme code="${actionNameKey}"/></button>
                            </ycommerce:testId>
                        </div>
                    </div>
                    <div class="col-md-12 login-register">
                        <span><spring:theme code="login.if.new.user.label"/></span>
                        <!--<span id="e-service-register">Register Here.</span>-->
                        
                        <span id="sagiaRegisterBtn">
                        <a class="accountLogin-content-toggleBtn">
                            <ycommerce:testId code="registerButton">
                                <spring:theme code="register.register"/>&nbsp;<spring:theme code="register.here"/> 
                                <!-- <button type="button" id="sagiaRegisterBtn"><spring:theme code="register.register"/>&nbsp<spring:theme code="register.here"/></button> -->                      
                            </ycommerce:testId>
                        </a>  
                        </span>  
                    </div>
                    <div class="col-md-12 login-forgot accountLogin-content-formSubmitSection">
                        <%-- <button type="button" data-toggle="modal" data-target="#forgotPassword"><span>Forgot Password ?</span></button>|<span>
                        <a href="https://misa.gov.sa/ar/contact-us/" target="_blank">Trouble Logging in Contact Us</a></span> --%>
                        <%--<span data-toggle="modal" data-target="#forgotPassword">Forgot Password ?</span> --%>
                        <span class="accountLogin-content-formSubmitSection-forgottenPassword forgotten-password">
                            <ycommerce:testId code="login_forgotPassword_link">
                                <div class="forgotten_pwd_inner">
                                    <c:url value='/login/pw/request/external' var="forgot"/>
                                    <a href="${forgot}" class="js-password-forgotten" data-cbox-title="<spring:theme code="forgottenPwd.title"/>">
                                        <spring:theme code="login.link.forgottenPwd"/>
                                    </a><br> 
                                    <!--<p><spring:theme code="register.login.problem"/> &nbsp<a href="https://misa.gov.sa/ar/contact-us/" class="contact_link">
                                    <spring:theme code="register.login.problem.contact"/></a></p>-->
                                </div>
                            </ycommerce:testId>
                        </span>
                        <span>|</span>
                        <span class="accountLogin-content-formSubmitSection-forgottenPassword">
                            <spring:theme code="register.login.problem"/> &nbsp; 
                            <a href="https://misa.gov.sa/ar/contact-us/" target="_blank"><spring:theme code="register.login.problem.contact"/></a>
                        </span>
                        <c:if test="${expressCheckoutAllowed}">
                            <button type="submit" class="btn btn-default btn-block expressCheckoutButton">
                                <spring:theme code="text.expresscheckout.header"/>
                            </button>
                            <input id="expressCheckoutCheckbox" name="expressCheckoutEnabled" type="checkbox" class="form left doExpressCheckout display-none"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </form:form>   
    </div>
</div> 

<%--
<h1 class="accountLogin-title headline login-page__headline text-center">
   <spring:theme code="support.eservices" />
</h1>
--%>
<%--
<form:form action="${action}" method="post" modelAttribute="loginForm" id="sagiaLoginForm">
    <c:if test="${not empty message}">
        <span class="has-error"> <spring:theme code="${message}"/></span>
    </c:if>
    
    <c:if test="${not empty loginFormError}">
        <div class="formError">
            <icon:messageError/>
            <c:choose>
                <c:when test="${disabledUser}">
                    <spring:theme code="login.error.disabled.user" text="Password is disabled kindly click on forget my password to renew your password." arguments=""/>
                </c:when>
                <c:otherwise>
                    <spring:theme code="${loginFormError}" arguments=""/>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
    
    <table>
	    <tbody>
		    <tr>
			    <td style="width: 150%;">
			    	<formElement:formInputBox idKey="j_username" labelKey="login.username" labelCSS="control-label_mandatory" path="j_username" mandatory="true"/>
			    </td></tr><tr><td>
			    	<formElement:formPasswordBox idKey="j_password" labelKey="login.password" labelCSS="control-label_mandatory" path="j_password" inputCSS="form-control" mandatory="true" />
			    </td>
			    <td>
			    	<i toggle="#password-field" class="fa fa-eye-slash toggle-password" aria-hidden="true"  style="width: 0px;"></i>
			    	<!-- <span toggle="#password-field" class="fa fa-fw fa-eye field_icon toggle-password" style="width: 0px;"> --></span>
			    </td>
			</tr>
			<tr>
				<td>
				    <div class="formSelectBox" id="loginSelectLanguageDiv">
				        <div class="form-group">
				            <select class="js-select2-oneColumn" id="loginSelectLanguageSelect" name="language">
				                <div style="text-align: center;">
				                    <c:if test="${currentLanguage.isocode == 'en'}">
				                        <option value="en" selected="selected">English</option>
				                        <option value="ar">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
				                    </c:if>
				                    <c:if test="${currentLanguage.isocode == 'ar'}">
				                        <option value="en">English</option>
				                        <option value="ar" selected="selected">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
				                    </c:if>
				                </div>
				            </select>
				            <label class="control-label" for="loginSelectLanguageSelect"><spring:theme code="general.language"/></label>
				        </div>
				    </div>
				</td>
			</tr>
		</tbody>
	</table>
    <div class="accountLogin-content-formSubmitSection">
      
      <div class="login_button_outer">      
      <input type="hidden" id="recaptchaChallangeAnswered"
							value="${requestScope.recaptchaChallangeAnswered}" />
      <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
        
        <ycommerce:testId code="loginAndCheckoutButton">
            <button type="submit" class="btn btn_wide font-weight-bold" id="sagiaLoginBtn"><spring:theme code="${actionNameKey}"/></button>
        </ycommerce:testId>                
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
        <a class="accountLogin-content-toggleBtn">
        <ycommerce:testId code="registerButton">
            <button type="button" class="btn btn_outline font-weight-bold" id="sagiaRegisterBtn"><spring:theme code="register.register"/>&nbsp<spring:theme code="register.here"/></button>
        </ycommerce:testId></a>                  
		</div>

        <div class="accountLogin-content-formSubmitSection-forgottenPassword forgotten-password">
            <ycommerce:testId code="login_forgotPassword_link">
            <div class="forgotten_pwd_inner">
                <c:url value='/login/pw/request/external' var="forgot"/>
                <a href="${forgot}" class="js-password-forgotten" data-cbox-title="<spring:theme code="forgottenPwd.title"/>">
                    <spring:theme code="login.link.forgottenPwd"/>
                </a><br> 
                <p><spring:theme code="register.login.problem"/> &nbsp<a href="https://misa.gov.sa/ar/contact-us/" class="contact_link"><spring:theme code="register.login.problem.contact"/></a></p>
           </div>
            </ycommerce:testId>
        </div>
        
        <c:if test="${expressCheckoutAllowed}">
            <button type="submit" class="btn btn-default btn-block expressCheckoutButton"><spring:theme code="text.expresscheckout.header"/></button>
            <input id="expressCheckoutCheckbox" name="expressCheckoutEnabled" type="checkbox" class="form left doExpressCheckout display-none"/>
        </c:if>
    </div>
</form:form>

--%>