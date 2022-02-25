<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user"%>
<style type="text/css">
	.navigation__overflow,
	.page-homepage,
	.footer,
	.footer__top,
	.footer__bottom,
	.search-area,
	.yCmsContentSlot,
	.container-fluid,
	.navigation,
	.navigation--bottom,
	.js_navigation--bottom,
	.js-enquire-offcanvas-navigation,
    .investsaudiHeader,
    .sagiaNavigation,
    .investsaudiFooter,
    .sagiaHead-paddingHelper,
    .sagiaHead {
        display: none;       
    }
    
   .page-login header, .page-updatePassword header, .page-login footer, .page-updatePassword footer {
    display: none;
}

.page-login footer {
	padding-top: 0px;
    margin-top: 0px;
    background: #ffffff;
    color: #ffffff;
    font-weight: 300;
}

    
.login-section {
    max-width: 320px;
}
[dir] .login-section {
    margin: 0 auto;
}    
[dir] .accountLogin {
    text-align: center;
}  

.accountLogin-background {
    overflow: auto;
    min-height: 100vh;
}
[dir] .accountLogin-background {
    background-color: rgba(29, 36, 44, 0.12);
    background-position: bottom center;
    background-repeat: no-repeat;
    background-size: cover;
    background-attachment: fixed;
    background-image: url(../investsaudistorefront/_ui/responsive/common/images/login-background.jpg);
}

.accountLogin-headline {
    width: 100%;
}
[dir] .accountLogin-headline {
    text-align: center;
}
.accountLogin-headline-logo {
    display: inline-block;
}
[dir] .accountLogin-headline-logo {
    margin: 30px auto;
}
.accountLogin-headline-logo img,
img.accountLogin-headline-logo {
    height: 140px;
    width: auto;
}
.accountLogin-content {
    max-width: 560px;
    position: relative;
}
[dir] .accountLogin-content {
    margin-bottom: 30px;
    padding-top: 48px;
}
[dir="ltr"] .accountLogin-content {
    text-align: left;
    margin-left: auto;
    margin-right: auto;
}

.panelModule {
    width: 100%;
}
[dir] .panelModule {
    -webkit-box-shadow: 0 2px 30px 0 rgba(28, 36, 44, 0.12);
    box-shadow: 0 2px 30px 0 rgba(28, 36, 44, 0.12);
    padding: 32px 15px;
    padding-bottom: 16px !important;
    margin-bottom: 15px;
    background-color: #fff;
}
@media (min-width: 576px) {
    [dir] .panelModule {
        padding: 32px 20px;
    }
}
@media (min-width: 768px) {
    [dir] .panelModule {
        padding: 32px 60px;
        margin-bottom: 50px;
    }
}
@media (min-width: 992px) {
    [dir] .panelModule {
        padding: 32px 90px;
        margin-bottom: 50px;
    }
}


[dir="ltr"] .panelModule_halfRadius {
    border-radius: 0 40px;
}

.accountLogin-content-formSubmitSection {
    font-size: 12px;
}
[dir] .accountLogin-content-formSubmitSection {
    text-align: center;
    margin-top: 30px;
    margin-bottom: 10px;
}

[dir] .accountLogin-title {
    margin-bottom: 32px;
}
[dir] .text-center {
    text-align: center;
}

.accountLogin-content-formSubmitSection a,
.accountLogin-content-formSubmitSection a.accountLogin-content-toggleBtn {
    color: #5cc83b;
}
.accountLogin-content-formSubmitSection a.accountLogin-content-toggleBtn:hover,
.accountLogin-content-formSubmitSection a:hover {
    color: #3cdc0f;
}
[dir] .accountLogin-content-formSubmitSection .accountLogin-content-toggleBtn {
    cursor: pointer;
}



[dir] .contentModule-actions>*, [dir] .contentModule-actions>.btn {
    margin: 0 8px 8px 8px;
}
[dir=ltr] .contentModule-actions {
    margin-left: 45px;
    margin-right: 45px;
}

[dir] .contentModule-actions {
    margin-bottom: 2px;
}
.contentModule-actions_centered {
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
}
.contentModule-actions {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    position: relative;
    overflow: visible;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
}


[dir] .btn-secondary {
    background-color: #32465a;
    border-color: #32465a;
}
[dir] .btn {
    margin: 0;
    text-align: center;
    padding: 9px 24px 7px;
    border-radius: .25rem;
    border: 0 solid transparent;
    border-radius: 0;
    cursor: pointer;
    background-color: #5cc83b;
    border-color: #5cc83b;
}

[dir=ltr] .btn {
    border-radius: 0 1em;
}

.btn {
    display: inline-block;
    font-weight: 700;
    white-space: nowrap;
    vertical-align: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    font-size: 13px;
    line-height: 20px;
    -webkit-appearance: none;
    -webkit-font-smoothing: antialiased;
    color: #fff;
}
[type=reset], [type=submit], button, html [type=button] {
    -webkit-appearance: button;
}
.accountLogin-content-formSubmitSection a, .accountLogin-content-formSubmitSection a.accountLogin-content-toggleBtn {
    color: #5cc83b;
}

.accountLogin-title {
    font-size: 28px;
    font-weight: 400;
    line-height: 1;
    color: #1c242c;
}
  
.accountLogin-content, accountLogin-content_small {
    z-index: auto;
}

.accountLogin-content_small {
    max-width: 560px;
}
 
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("footer").hide();
	$("body").css({"padding-top": "0px"});
	$(".page-login").css({"padding-top": "0px"});
	$(".page-login").attr('style', 'padding-top: 0px !important');
	$(".btn").attr('style', 'border-radius: 0 1em !important');
	$(".btn-secondary").css({"background-color": "#32465a", "border-color": "#32465a"});
	$(".btn").css({"font-size": "13px", "line-height": "20px"});
	$("#resendBtn").attr("disabled", true);
    setTimeout(function(){$("#resendBtn").attr("disabled", false)}, 420000);
});
</script>

<template:page pageTitle="${pageTitle}">

<div class="accountLogin page-login">
    <div class="accountLogin-background">
        <div class="container">
            <div class="login-logo">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="/investsaudistorefront/_ui/responsive/common/images/logo-en.svg">
                </a>
            </div>

            <div class="panelModule_halfRadius accountLogin-content second-step-auth">
                <div>
					<div class="login-section">
					    <user:loginSecondStepVerification/>
					</div>
                </div>
            </div>   
        </div>    
    </div>    
</div>

<%-- <div class="page-login" style="padding-top: 0px;">
<div class="main__inner-wrapper">
            <div class="login-logo">
                <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                    <cms:component component="${logo}"/>
                </cms:pageSlot>
            </div>
            
            <div class="login-area-wrapper">
                <div class="paragraph-wrapper">
                    <cms:pageSlot position="LeftContentSlot-login-secondstep" var="feature">
                        <cms:component component="${feature}"/>
                    </cms:pageSlot>
                </div>
	            <div class="login-wrapper">
					<div class="login-section">
					    <user:loginSecondStepVerification/>
					</div>
				</div>
			</div>
</div>
</div> --%>

</template:page>