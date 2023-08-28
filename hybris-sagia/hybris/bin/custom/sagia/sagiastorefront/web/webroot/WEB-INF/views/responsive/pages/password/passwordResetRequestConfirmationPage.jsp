<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<template:page pageTitle="${pageTitle}">
<%--
	<div class="item_container_holder">
		<div class="title_holder">
			<h2>
				<spring:theme code="forgottenPwd.title"/>
			</h2>
		</div>
		<div class="item_container">
			<spring:theme code="account.confirmation.forgotten.password.link.sent"/>
		</div>
	</div>
--%>
	
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

            <div class="panelModule panelModule_halfRadius accountLogin-content accountLogin-content_small reset-pw-wrapper-dt">

                <h1 class="login-register-text pt-4 mb-4"><spring:theme code="forgottenPwd.title"/></h1>

                <div class="accountLogin-copyWrapper">
                    <p><spring:theme code="account.confirmation.forgotten.password.link.sent"/></p>
                </div>


                <div class="accountLogin-buttonWrapper">
                    <c:url value="/login" var="loginUrl"></c:url>
                    <a href="${loginUrl}" class="btn btn_wide"><spring:theme code="register.back.login"/>
                        <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M4.58331 11H16.5M11.9166 5.5L16.7685 10.3518C17.1264 10.7098 17.1264 11.2902 16.7685 11.6482L11.9166 16.5" stroke="#F8F9FB" stroke-width="1.5" stroke-linecap="round"/>
                            </svg>
                            </button>
                    </a>
                </div>
                <div class="accountLogin-linkWrapper">
                    <a href="https://investsaudi.sa/en/contact/" class=" btn_link btn_link_text ">
                        <spring:theme code="general.contactus"/></a>
                </div>                


            </div>   
        </div>    
    </div>    
</div>	
	
	
	
	
	
	
	
</template:page>
 