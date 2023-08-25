<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container forgotPassword-container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small forgotPassword-wrapper">
                <div class="login-section">
                    <div class="login-register-text pt-4 mb-5"><spring:theme code="password.forgottenpassword"/></div>

                    <form:form method="post" modelAttribute="forgottenPwdForm">
                        <ycommerce:testId code="login_forgotPasswordEmail_input">
                            <formElement:formInputBox idKey="forgottenPwd.email" labelKey="forgottenPwd.email" path="email" mandatory="true"/>
                        </ycommerce:testId>
                        <div class="accountLogin-copyWrapper">
                            <p class="mt-5"><spring:theme code="forgottenPwd.description"/></p>
                        </div>


                        <div class="accountLogin-buttonWrapper">
                            <ycommerce:testId code="login_forgotPasswordSubmit_button">
                                <button class="login-btn login-btn-next active" type="submit">
                                    <spring:theme code="forgottenPwd.title"/>
                                    <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M4.58331 11H16.5M11.9166 5.5L16.7685 10.3518C17.1264 10.7098 17.1264 11.2902 16.7685 11.6482L11.9166 16.5" stroke="#F8F9FB" stroke-width="1.5" stroke-linecap="round"/>
</svg>
</button>
                                </button>
                            </ycommerce:testId>
                        </div>
                        <div class="accountLogin-linkWrapper">
                            <a href="https://misa.gov.sa/${language}/contact-us/" class="btn_link btn_link_text">
                                <spring:theme code="support.contactus"/></a>
                        </div>                
                    </form:form>
                </div>
            </div>   
        </div>    
    </div>    
</div>