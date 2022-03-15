<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<style type="text/css">
    .investsaudiHeader,
    .sagiaNavigation,
    .investsaudiFooter,
    .sagiaHead-paddingHelper,
    .sagiaHead {
        display: none;       
    }
    
   
}

</style>

<template:page pageTitle="${pageTitle}">

<c:url value="/verification/backtologin" var="backToLoginActionUrl" />
<c:url value="/verification/emailsubscriptionfee" var="emailSubscriptionFeeUrl" />
<c:url value="/verification/billPayment" var="billPaymentUrl" />
<c:url value="https://sagia.gov.sa/ar/contact-us/" var="contactUs" />

<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content verification-div-sub" style="max-width: 600px;margin-top: 200px;">
                <div>
                    <%-- <h4 class="accountLogin-title headline login-page__headline text-center"><spring:theme code="text.subscriptionfeecheck.title"/></h4> --%>
                    <p><spring:theme code="text.subscriptionfeecheck.title"/></p>
                </div>
                <div style="text-align: justify;">
                	<!-- <p>Your E-Services are suspended. Please pay the bill 0021012345 in order to restart the E-Services.</p> -->
                	<p><spring:theme code="text.subscriptionfeecheck.message1"/></p>
                </div>
                <div>
                    <p><spring:theme code="text.subscriptionfeecheck.message2"/></p>
                </div>
                <div>
                    <p><spring:theme code="text.subscriptionfeecheck.message3"/></p>
                </div>
                <div>
                    <p><spring:theme code="text.subscriptionfeecheck.message4"/></p>
                </div>
                
                	<div class="row" style="justify-content: center;">
	                   <div class="accountLogin-buttonWrapper">
	                       <a class="btn" style="margin-left: 35px;" href="${emailSubscriptionFeeUrl}"><spring:theme code="text.subscriptionfeecheck.sendemail"/></a>
	                   </div>
	                   <div class="accountLogin-buttonWrapper">
	                       <a class="btn" style="margin-left: 30px;" href="${billPaymentUrl}"><spring:theme code="license.apply.paywithcc"/></a>
	                   </div>
                   </div>
                   <div class="accountLogin-linkWrapper" style="margin-top: 6px;margin-bottom: 11px;">
                     <a href="${backToLoginActionUrl}" class="btn_link_text">
                         <spring:theme code="text.verification.backtologin"/></a>
                   </div>
                   <div class="accountLogin-linkWrapper" style="margin-top: 6px;margin-bottom: 11px;">
                     <a href="${contactUs}" class="btn_link_text">
                         <spring:theme code="support.contactus"/></a>
                   </div>      
				
            </div>   
        </div>    
    </div>    
</div>

</template:page>
	
