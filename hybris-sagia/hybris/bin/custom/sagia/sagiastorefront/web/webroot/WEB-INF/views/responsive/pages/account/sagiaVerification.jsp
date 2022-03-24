<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<style type="text/css">
	.investsaudiHeader,
	.sagiaNavigation,
	.investsaudiFooter,
	.sagiaHead-paddingHelper,
	.sagiaHead ,#topbar ,#header,.footer,.copyright{
		display: none !important;
	}
</style>

<template:page pageTitle="${pageTitle}">

	<c:url value="/verification/backtologin" var="backToLoginActionUrl" />
	<c:url value="https://investsaudi.sa/en/contact/" var="contactUs" />

	<div class="accountLogin">
		<div class="accountLogin-background">
			<div class="container login-screen2 r-sn">

				<div class="panelModule panelModule_halfRadius login-right-wrapper">
					<div class="">
						<h1 class="accountLogin-title headline login-page__headline text-center login-register-text">
							<spring:theme code="text.verification.verify" />
						</h1>
						<div class="register-role-selection">
							<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid login-role" />
						</div>
						<form:form method="post" modelAttribute="sagiaVerificationForm">
							<div class="login-verification-processs">
								<c:if test="${isMobileVerificationEnabled}">
									<div class="row register-user-info">
										<div class="col-md-12 register-form form-floating country-code-mobile">
											<div class="col-md-10 col-12">
												<div class="formInputBox formInputBox_group ">
													<div class="form-group">
														<form:input path="countryCode" cssClass=" ddl-countryCode register-user-details register-contry-info form-control"
															placeholder="." maxlength="5" id="countryCode" disabled="true" />
														<label
															class="control-label register-user-info-label focused"
															for="countryCode">
															<spring:theme code="text.verification.countrycode" />
														</label>
														<div class="formInputBox-append" id="editCountryCode">
															<form:input path="mobile"
																cssClass="register-user-details validate-mobile mobile-number form-control"
																placeholder="."
																maxlength="15"
																disabled="true"
																id="mobile" />
															<label class="control-label register-user-info-label validate-mobile-label focused" for="mobile">
																<spring:theme code="text.verification.mobile" />
															</label>
															<div class="formInputBox-append" id="editMobile">
																<%-- <span class="formInputBox-text"><icon:edit/></span> --%>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-2 col-12 verification-info mx-0 px-0">
												<i class="fas fa-info-circle"></i>
												<c:choose>
													<c:when test='${mobileStatus == "VERIFIED"}'>
														<!-- <span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified"/></span> -->
														<!-- <img src="${commonResourcePath}/images/B2C/Icon ionic-ios-checkmark.png" alt="verified" /> -->
														<div class="verified" id="mobile-verified"></div>
													</c:when>
													<c:when test='${mobileStatus == "NOT_VERIFIED"}'>
														<span class="mobile-not-verified">
															<spring:theme code="text.verification.status.notverified" />
														</span>
													</c:when>
													<c:otherwise>
														<span class="mobile-not-verified">Not Verified</span>
													</c:otherwise>
												</c:choose>
											</div>

											<!-- <div class="col-6 col-md-2" id = "align" style="padding:15px">
													<button class="btn btn_round" name="smsverify" id="mobileBtn"><spring:theme code="text.verification.verify.mobile"/></button>
												</div> -->
										</div>
										<div class="row d-block">
											<label class="control-label mobile-verify-text">
												<spring:theme code="text.verification.mobile.adaptive" />
											</label>
										</div>
										<div class="help-block has-error"></div>
										<div class="row d-block login-buttons verify-mobile m-auto">
											<div class="col-md-12 col-12">
												<button class="login-btn login-btn-next active" name="smsverify" id="mobileBtn">
													<spring:theme code="text.verification.verify.mobile" />
												</button>
											</div>
										</div>
									</div>
									<hr class="dotted-lines" />
								</c:if>
								<c:if test="${isEmailVerificationEnabled}">
									<div class="row register-user-info">
										<div class="col-md-12 register-form focus-on-change verify-email-box ">
											<div class="col-12 col-md-10">
												<div class="formInputBox formInputBox_group ">
													<div class="form-group">
														<form:input path="regEmail" cssClass="register-user-details validate-email form-control" placeholder="." maxlength="60" disabled="true" id="regEmail" />
														<label class="control-label register-user-info-label focused" for="regEmail">
															<spring:theme code="text.verification.email.register" />
														</label>
														<div class="formInputBox-append" id="editEmail">
															<%-- <span class="formInputBox-text"> <icon:edit /></span> --%>
														</div>
													</div>
													<div class="help-block has-error" id="emailR"></div>
												</div>
											</div>
											<div class="col-6 col-md-2 verification-info mx-0 px-0" id="align">
												<c:choose>
													<c:when test='${regEmailStatus == "VERIFIED"}'>
														<!-- <span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified" /></span> -->
														<div class="verified" id="email-verified"></div>
													</c:when>
													<c:when test='${regEmailStatus == "NOT_VERIFIED"}'>
														<span  class="email-not-verified"><spring:theme code="text.verification.status.notverified" /></span>
													</c:when>
													<c:when test='${regEmailStatus == "PENDING"}'>
														<span  class="email-not-verified"><spring:theme code="text.verification.status.pending" /></span>
													</c:when>
													<c:otherwise>
														<span class="email-not-verified">Not Verified</span>
													</c:otherwise>
												</c:choose>
											</div>										
										</div>
										<div class="row d-block login-buttons verify-mobile m-auto">
											<div class="col-md-12 col-12">
												<button class="login-btn login-btn-next active" name="regemailverify" id="regEmailBtn">
													<c:choose>
														<c:when test='${regEmailStatus == "PENDING"}'>
															<spring:theme code="text.verification.verify.pending" />
														</c:when>
														<c:otherwise>
															<spring:theme code="text.verification.verify.email" />
														</c:otherwise>
													</c:choose>
												</button>
											</div>
										</div>
									</div>

									<c:if test="${not empty qeemahEmail}">
										<div class="row user-email-verify-sec register-user-info"> 
											<div class="col-xs-8 col-md-10">
												<div
													class="formInputBox formInputBox_group ">
													<div class="form-group">
														<form:input path="qeemahEmail"
															cssClass="form-control"
															placeholder="." maxlength="60"
															disabled="true"
															id="qeemahEmail" />
														<label class="control-label" for="qeemahEmail">
															<spring:theme code="text.verification.email.qeemah" />
														</label>
														<div class="formInputBox-append" id="editQEmail">
															<%-- <span class="formInputBox-text"><icon:edit /></span> --%>
														</div>
													</div>
													<div class="help-block has-error" id="emailQ">
													</div>
												</div>
											</div>
											<!--    <div class="col-6 col-md-2"> -->
											<div class="col-6 col-md-2 verification-info" id="align">
												<i class="fas fa-info-circle"></i>
												<c:choose>
													<c:when test='${qeemahEmailStatus == "VERIFIED"}'>
														<!-- <span style="color:green;font-weight:bold"><spring:theme code="text.verification.status.verified" /></span> -->
														<div class="verified" id="email-verified"></div>
													</c:when>
													<c:when test='${qeemahEmailStatus == "NOT_VERIFIED"}'>
														<span class="email-not-verified"><spring:theme code="text.verification.status.notverified" /></span>
													</c:when>
													<c:when test='${qeemahEmailStatus == "PENDING"}'>
														<span class="email-not-verified"><spring:theme code="text.verification.status.pending" /></span>
													</c:when>
													<c:otherwise>
														<span class="email-not-verified">Not Verified</span>
													</c:otherwise>
												</c:choose>
											</div>
											<!--	</div> -->
											<div class="row d-block login-buttons verify-mobile m-auto">
												<div class="col-md-12 col-12">
													<button class="login-btn login-btn-next active"
														name="qeemahemailverify"
														id="qeemahEmailBtn" <c:if
														test='${qeemahEmailStatus == "VERIFIED"}'>
														disabled="true"
														</c:if>
													>
														<c:choose>
															<c:when test='${qeemahEmailStatus == "PENDING"}'>
																<spring:theme code="text.verification.verify.pending" />
															</c:when>
															<c:otherwise>
																<spring:theme code="text.verification.verify.email" />
															</c:otherwise>
														</c:choose>
													</button>
												</div>
											</div>
										</div>
									</c:if>
								</c:if>
								<div class="col-md-12 login-forgot trouble-contact-us">
									<a href="${contactUs}" class=""><spring:theme code="support.contactus" /></a>
								</div>
								<div class="login-buttons margin-top-30">
									<a class="login-btn login-btn-next back-to-login padding-top-10" href="${backToLoginActionUrl}"><spring:theme code="text.verification.backtologin" /></a>
								</div>
							</div>

							<!-- <div class=""> -->
								
								
							<!-- </div> -->
				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>

</template:page>