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

<h1 class="accountLogin-title headline login-page__headline text-center">
   <spring:theme code="support.eservices" />
</h1>


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
			    	<i toggle="#password-field" class="fa fa-eye toggle-password" aria-hidden="true"  style="width: 0px;"></i>
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
                <%-- <span class="accountLogin-content-inlineDelimiter">|</span>
                <spring:theme code="register.donthaveaccount"/>&#1564; --%>
<%--                 <a class="accountLogin-content-toggleBtn"> <spring:theme code="register.register"/>&nbsp<spring:theme code="register.here"/></a> --%>
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
