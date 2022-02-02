<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<style type="text/css">
    .investsaudiHeader,
    .sagiaNavigation,
    .investsaudiFooter,
    .sagiaHead-paddingHelper,
    .sagiaHead {
        display: none;
    }
</style>



<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small">
                <div class="login-section">
                    <h1 class="accountLogin-title headline login-page__headline text-center"><spring:theme code="password.forgottenpassword"/></h1>

                    <form:form method="post" modelAttribute="forgottenPwdForm">
                        <ycommerce:testId code="login_forgotPasswordEmail_input">
                            <formElement:formInputBox idKey="forgottenPwd.email" labelKey="forgottenPwd.email" path="email" mandatory="true"/>
                        </ycommerce:testId>
                        <div class="accountLogin-copyWrapper">
                            <p><spring:theme code="forgottenPwd.description"/></p>
                        </div>


                        <div class="accountLogin-buttonWrapper">
                            <ycommerce:testId code="login_forgotPasswordSubmit_button">
                                <button class="login-btn login-btn-next active" type="submit">
                                    <spring:theme code="forgottenPwd.title"/>
                                </button>
                            </ycommerce:testId>
                        </div>
                        <div class="accountLogin-linkWrapper">
                            <a href="https://investsaudi.sa/en/contact/" class="btn_link btn_link_text">
                                <spring:theme code="support.contactus"/></a>
                        </div>                
                    </form:form>
                </div>
            </div>   
        </div>    
    </div>    
</div>