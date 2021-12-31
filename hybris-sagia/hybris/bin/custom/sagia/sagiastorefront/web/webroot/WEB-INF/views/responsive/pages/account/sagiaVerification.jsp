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
<c:url value="https://investsaudi.sa/en/contact/" var="contactUs" />

<div class="accountLogin">
    <div class="accountLogin-background">
        <div class="container">
            <div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="${themeResourcePath}/img/${currentLanguage.isocode}/logo.png">
                </a>
            </div>

            <div class="panelModule panelModule_halfRadius accountLogin-content">
                <div>
                    <h1 class="accountLogin-title headline login-page__headline text-center"><spring:theme code="text.verification.verify"/></h1>
					<form:form method="post" modelAttribute="sagiaVerificationForm">
					<c:if test="${isMobileVerificationEnabled}">
					<div class="row">
						<div class="col-6 col-md-2">
					        <div class="formInputBox formInputBox_group ">
					            <div class="form-group">
					                <form:input path="countryCode"
					                            cssClass="form-control js-mobile-coutry-code"
					                            placeholder="." maxlength="5" id="countryCode" disabled="true"/>
					                <label class="control-label" for="countryCode"><spring:theme code="text.verification.countrycode"/></label>
					                <div class="formInputBox-append" id="editCountryCode">
<%-- 					                    <span class="formInputBox-text"><icon:edit/></span> --%>
					                </div>
					            </div>
					            <div class="help-block"></div>
					        </div>
					    </div>

					    <div class="col-xs-7 col-md-4">
					        <div class="formInputBox formInputBox_group ">
					            <div class="form-group">
					                <form:input path="mobile"
					                            cssClass="form-control js-quick-mobile-number"
					                            placeholder="." maxlength="15" disabled="true" id="mobile" />
					                <label class="control-label" for="mobile"><spring:theme code="text.verification.mobile"/></label>
					                <div class="formInputBox-append" id="editMobile">
<%-- 					                    <span class="formInputBox-text"><icon:edit/></span> --%>
					                </div>
					            </div>
					            <div class="help-block"></div>
					        </div>
					    </div>

					  <!--    <div class="col-6 col-md-2"> -->
					    <div class="col-6 col-md-2" id = "align" style="padding-top: 38px;padding-left: 0px;font-size:13px;line-height:0.5">
				    		<c:choose>
							    <c:when test='${mobileStatus == "VERIFIED"}'>
							    	<span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified"/></span>
							    </c:when>
							    <c:when test='${mobileStatus == "NOT_VERIFIED"}'>
							    	<span style="color:red;font-weight:bold"><spring:theme code="text.verification.status.notverified"/></span>
							    </c:when>
							    <c:otherwise>
							        <span style="color:black;font-weight:bold">Not Verified</span>
							    </c:otherwise>
							</c:choose>
		                </div>
						<!--  </div> -->

					    <div class="col-6 col-md-2" id = "align" style="padding:15px">
							<button class="btn btn_round" name="smsverify" id="mobileBtn"><spring:theme code="text.verification.verify.mobile"/></button>
						</div>
					</div>
					<div class="row">
					  <div class="col-xs-8 col-md-6">
					    <label class="control-label"><spring:theme code="text.verification.mobile.adaptive"/></label>
					   </div>
					</div>
					</c:if>
					<c:if test="${isEmailVerificationEnabled}">
					<div class="row">
					    <div class="col-xs-8 col-md-6">
					        <div class="formInputBox formInputBox_group ">
					            <div class="form-group">
					                <form:input path="regEmail"
					                            cssClass="form-control js-register-quick-email"
					                            placeholder="." maxlength="60" disabled="true" id="regEmail"/>
					                <label class="control-label" for="regEmail"><spring:theme code="text.verification.email.register"/></label>
					                <div class="formInputBox-append" id="editEmail">
<%-- 					                    <span class="formInputBox-text"><icon:edit/></span> --%>
					                </div>
					            </div>
					            <div class="help-block has-error"  id="emailR"></div>
					        </div>
					    </div>
					 <!--   <div class="col-6 col-md-2">  -->
					    	<div class="col-6 col-md-2" id = "align" style="padding-top: 38px;padding-left: 0px;font-size:13px;line-height:0.5" >
					    		<c:choose>
								    <c:when test='${regEmailStatus == "VERIFIED"}'>
								    	<span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified"/></span>
								    </c:when>
								    <c:when test='${regEmailStatus == "NOT_VERIFIED"}'>
								    	<span style="color:red;font-weight:bold"><spring:theme code="text.verification.status.notverified"/></span>
								    </c:when>
								    <c:when test='${regEmailStatus == "PENDING"}'>
								    	<span style="color:black;font-weight:bold"><spring:theme code="text.verification.status.pending"/></span>
								    </c:when>
								    <c:otherwise>
								        <span style="color:black;font-weight:bold">Not Verified</span>
								    </c:otherwise>
								</c:choose>
					        </div>
					<!--	</div> -->
					    <div class="col-6 col-md-2" id = "align" style="padding:15px">
							<button class="btn btn_round" name="regemailverify" id="regEmailBtn" >
								<c:choose>
									<c:when test='${regEmailStatus == "PENDING"}'>
										<spring:theme code="text.verification.verify.pending"/>
									</c:when>
									<c:otherwise>
										<spring:theme code="text.verification.verify.email"/>
									</c:otherwise>
								</c:choose>
							</button>
						</div>
					</div>
					<c:if test="${not empty qeemahEmail}">
					<div class="row">
					    <div class="col-xs-8 col-md-6">
					        <div class="formInputBox formInputBox_group ">
					            <div class="form-group">
					                <form:input path="qeemahEmail"
					                            cssClass="form-control"
					                            placeholder="." maxlength="60" disabled="true" id="qeemahEmail" />
					                <label class="control-label" for="qeemahEmail"><spring:theme code="text.verification.email.qeemah"/></label>
					                <div class="formInputBox-append" id="editQEmail">
<%-- 					                    <span class="formInputBox-text"><icon:edit/></span> --%>
					                </div>
					            </div>
					            <div class="help-block" id="emailQ"></div>
					        </div>
					    </div>
					 <!--    <div class="col-6 col-md-2"> --> 
					    	<div class="col-6 col-md-2" id="align" style="padding-top: 38px;padding-left: 0px;font-size:13px;line-height:0.5">
					            <c:choose>
								    <c:when test='${qeemahEmailStatus == "VERIFIED"}'>
								    	<span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified"/></span>
								    </c:when>
								    <c:when test='${qeemahEmailStatus == "NOT_VERIFIED"}'>
								    	<span style="color:red;font-weight:bold"><spring:theme code="text.verification.status.notverified"/></span>
								    </c:when>
								    <c:when test='${qeemahEmailStatus == "PENDING"}'>
								    	<span style="color:black;font-weight:bold"><spring:theme code="text.verification.status.pending"/></span>
								    </c:when>    
								    <c:otherwise>
								        <span style="color:black;font-weight:bold">Not Verified</span>
								    </c:otherwise>
								</c:choose>
					        </div>
					<!--	</div> --> 
					    <div class="col-6 col-md-2" id="align" style="padding:15px">
							<button class="btn btn_round"  name="qeemahemailverify" id="qeemahEmailBtn" <c:if test='${qeemahEmailStatus == "VERIFIED"}'> disabled="true"</c:if>>
								<c:choose>
									<c:when test='${qeemahEmailStatus == "PENDING"}'>
										<spring:theme code="text.verification.verify.pending"/>
									</c:when>    
									<c:otherwise>
										<spring:theme code="text.verification.verify.email"/>
									</c:otherwise>
								</c:choose>
							</button>
						</div>	
					</div>
					</c:if>
					</c:if>
					<div>
                      <div class="accountLogin-buttonWrapper">
                          <a class="btn" href="${backToLoginActionUrl}"><spring:theme code="text.verification.backtologin"/></a>
                      </div>
                      <div class="accountLogin-linkWrapper">
                        <a href="${contactUs}" class="btn btn_link btn_link_text">
                            <spring:theme code="support.contactus"/></a>
                      </div>      
					</div>                     
					
					</form:form>
                </div>
            </div>   
        </div>    
    </div>    
</div>

</template:page>
