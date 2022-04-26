<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="resetPwdForm" type="com.sap.ibso.eservices.storefront.forms.SagiaResetPwdForm"--%>

<script type="text/javascript" src="${themeResourcePath}/js/jquery-3.3.1.min.js"></script>


<spring:htmlEscape defaultHtmlEscape="true"/>
<c:url var="action" value="/login/pw/change"/>
<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png" alt="logo">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small">
                <div class="login-section">
                    <h1 class="accountLogin-title headline login-page__headline text-center"><spring:theme code="text.account.profile.resetPassword"/></h1>
                    <form:form method="post" id="sagiaResetPwdForm" modelAttribute="sagiaResetPwdForm" action="${action}">
                        <formElement:formPasswordBox idKey="password" labelKey="updatePwd.pwd" path="pwd"
                                                     inputCSS="form-control resetPwdForm" labelCSS="control-label_mandatory" mandatory="true"/>
                        <formElement:formPasswordBox idKey="updatePwd.checkPwd" labelKey="updatePwd.checkPwd" path="checkPwd"
                                                     inputCSS="form-control resetPwdCheckForm" labelCSS="control-label_mandatory" mandatory="true"/>
                        <input type="hidden" value="${sagiaResetPwdForm.token}" name="token"/>
                        <div class="accountLogin-buttonWrapper">
                            <button type="submit" class="btn btn-primary btn-block">
                                <spring:theme code="updatePwd.submit"/>
                            </button>
                            <button type="button" class="btn btn-default btn-block backToHome" onclick="window.location.href='${pageContext.request.contextPath}/login'">
                                <spring:theme code="text.button.cancel"/>
                            </button>
                        </div>
                        <div class="accountLogin-linkWrapper">
                            <a href="https://investsaudi.sa/en/contact/" class="btn btn_link btn_link_text"><spring:theme code="support.contactus"/></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var backendRegex = '${backendRegex}';
    var backendRegexErrorMessage = '${backendRegexErrorMessage}';
</script>

<script type="text/javascript" src="${themeResourcePath}/js/sagia.resetPassword.js"/>