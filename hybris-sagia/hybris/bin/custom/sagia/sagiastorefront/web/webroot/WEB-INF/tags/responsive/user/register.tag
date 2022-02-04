<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ attribute name="registerFormError" required="false" type="java.lang.String"%>
<%@ attribute name="titles" required="false" type="java.util.List<de.hybris.platform.commercefacades.user.data.TitleData>"%>
<%@ attribute name="sectors" required="false" type="java.util.List<com.sap.ibso.eservices.facades.data.SagiaSectorData>"%>
<%@ attribute name="countries" required="false" type="java.util.List<com.sap.ibso.eservices.facades.data.SagiaCountryData>"%>
<%@ attribute name="currentLanguage" required="false" type="de.hybris.platform.core.model.c2l.LanguageModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="userTags" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<%--@elvariable id="sagiaRegisterForm" type="de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm"--%>
<%--<spring:url value="/termsAndConditions" var="getTermsAndConditionsUrl"/>--%>
<%-- container, possibly to be elsewhere --%>


<div class="panelTabs-head" id="register-quick">
	<icon:register/><span class="panelTabs-label"><spring:theme code="quickRegistration.label"/></span> 
</div>
 
<style>
.right_arrowdd .select2.select2-container .select2-selection:after{
	right: -8px;
}
[dir='rtl'] .right_arrowdd .select2.select2-container .select2-selection:after{
	left: 0 !important;
	right: auto;
}
[dir='rtl'] .select2.select2-container .select2-selection:after { left: 0 !important;right: auto; }
</style>

<c:url value="/register/ajax" var="registerAction" />
<c:url value="/register/apply" var="applyAction" />
<div class="row">
	<div class="col-md-6">
		<div class="login-logo">
			<a href="https://eservices.sagia.gov.sa/en">
				<img src="${commonResourcePath}/images/B2C/Login-logo.svg" alt="logo" class="img-fluid w-100" />
			</a>
		</div>
		<div id="sagia-cms-help-quick-registration-helper"></div>
	</div>
	
	<div class="col-md-6">
		<form:form method="post" id="sagiaRegisterFormQuickRegistration" name="sagiaRegisterFormQuickRegistration"  modelAttribute="sagiaRegisterForm" action="${registerAction}#register-quick" onsubmit="return validateRegisterForm()">
			<div class="row col-lg-12 col-xl-12 col-12 register-account-screen r-sn ">
				<div class="login-right-wrapper">
					<div class="login-register-text"><spring:theme code="investor.registration.your.account.label"/></div>
					<div class="register-progress">
						<span class="circle register-progress-selection">1</span>
						<hr />
						<span class="circle">2</span>
						<hr />
						<span class="circle">3</span>
						<%--
						<hr />
						<span class="circle">4</span>
						--%>
					</div>
					<div class="login-register-role"><spring:theme code="investor.registration.select.role.label"/></div>
					<div class="login-role-selection">
						<div class="login-role-selection-box role-investor active">							
							<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid img-ivestor-inactive d-none" />
							<img src="${commonResourcePath}/images/B2C/Investor-icons.png" alt="Investor" class="img-fluid img-ivestor-active" />
							<p class="role-text"><spring:theme code="investor.select.role.label"/></p>							
						</div>
						<div class="login-role-selection-box role-partner">
							<img src="${commonResourcePath}/images/B2C/Partner.png" alt="Partner" class="img-fluid img-partner-inactive" />
							<img src="${commonResourcePath}/images/B2C/Partner-icon.png" alt="Investor" class="img-fluid img-partner-active d-none" />
							<p class="role-text"><spring:theme code="partner.select.role.label"/></p>
						</div>
					</div>
					<div class="login-role-selection role-selected-text d-none">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse imperdiet sit
					</div>
					<spring:theme code="${registerFormError}" arguments=""/>
					<div class="login-buttons">
						<div class="col-md-6 col-12">
							<a class="login-btn login-btn-cancel padding-top-10"><spring:theme code="investor.registration.cancel.button"/></a>
						</div>
						<div class="col-md-6 col-12">													
							<a class="login-btn login-btn-next active padding-top-10" ><spring:theme code="investor.registration.next.button"/>&nbsp;
								<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
									<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
										d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
										transform="translate(-7.875 -11.252)" fill="#fff">
									</path>
								</svg>
							</a>
						</div>
					</div>
				</div>
			</div>

			<div class="row col-lg-12 col-xl-12 col-12 register-account-investor-screen2 r-sn next-hide">
				<div class="login-right-wrapper">
					<div class="login-register-text"><spring:theme code="investor.registration.your.account.label"/></div>
					<div class="register-role-selection">
						<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid" />
						<img src="" class="img-fluid bussiness-sector-selected" alt=""/>
					</div>
					<div class="register-progress">
						<span class="circle progress-completion">&#10003;</span>								
						<hr />
						<span class="circle">2</span>								
						<span class="circle register-progress-selection">2</span>								
						<hr />
						<span class="circle">3</span>								
						<hr />
						<span class="circle">4</span>							
					</div>
					<div class="login-register-role">SELECT YOUR BUSINESS AREA / SECTOR</div>
					<div class="login-role-selection">
						<div class="bussiness-sector">					
							<div class="bussiness-sector-btn" data-sectorname="Energy" data-sectorid="energy" data-selected="no">
								<img class="bussiness-sector-item-icon deselected-img" src="/_ui/responsive/common/images/B2C/sector/Icons-blue/Energy.png" data-norm="/_ui/responsive/common/images/B2C/sector/Icons-blue/Energy.png" data-alt="/_ui/responsive/common/images/B2C/sector/Icons-blue/Energy.png" data-norm="/_ui/responsive/common/images/B2C/sector/Icons-blue/Energy.png" alt="Energy">
								<img style="display: none;" class="bussiness-sector-item-icon selected-img" src="/_ui/responsive/common/images/B2C/sector/Icons-white/Energy.png" data-norm="/_ui/responsive/common/images/B2C/sector/Icons-white/Energy.png" data-norm="/_ui/responsive/common/images/B2C/sector/Icons-white/Energy.png" data-alt="/_ui/responsive/common/images/B2C/sector/Icons-white/Energy.png" alt="Energy">
								<span class="bussiness-sector-list-text ">Energy</span>
								<img class="img-fluid bussiness-sector-close-icon" src="/_ui/responsive/common/images/close_icon.png" alt="">
							</div>
							<%--
							<formElement:formSelectBox idKey="quickregistration.register.sector" labelKey="register.sector" selectCSSClass="form-control jqSector js-select-required" path="sector" items="{sectors}" itemLabel="name"/>
							--%>
						</div>
					</div>
					<div class="login-buttons">
						<div class="col-md-6 col-12">
							<a class="login-btn register-investor-screen2-btn-back padding-top-10">BACK</a>
						</div>
						<div class="col-md-6 col-12">
							<a class="login-btn register-investor-screen2-btn-next padding-top-10">NEXT&nbsp;
								<svg xmlns="http://www.w3.org/2000/svg" width="15.835"
										height="10.561" viewBox="0 0 15.835 10.561" class="next-hide">
									<path id="Icon_ionic-ios-arrow-round-forward"
										data-name="Icon ionic-ios-arrow-round-forward"
										d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
										transform="translate(-7.875 -11.252)" fill="#fff">
									</path>
								</svg>
							</a>
						</div>
					</div>
				</div>
			</div>

			<div class="row col-lg-12 col-xl-12 col-12 register-account-investor-screen3 r-sn next-hide">
				<div class="login-right-wrapper">
					<div class="login-register-text"><spring:theme code="investor.registration.your.account.label"/></div>
					<div class="register-role-selection">
						<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid" />
						<img src="" class="img-fluid bussiness-sector-selected" alt=""/>
					</div>
					<div class="register-progress">
						<span class="circle progress-completion register-progress-selection">&#10003;</span>
						<hr />
						<span class="circle register-progress-selection">2</span>								
						<hr />
						<span class="circle">3</span>								
						<%--
						<hr />
						<span class="circle">4</span>
						--%>
					</div>
					<div class="login-register-role"><spring:theme code="investor.registration.enter.details.label"/></div>
					<div class="row register-user-info">
						<%--
						<div class="col-md-12 register-form focus-on-change register-forms-select">
							<formElement:formSelectBox idKey="quickregistration.register.title"
									labelKey="register.title" selectCSSClass="form-control jqTitle js-select-required register-user-details"
									path="titleCode" mandatory="true" skipBlank="false" labelCSS="register-user-info-label"
									skipBlankMessageKey="form.select.empty" items="${titles}"/>						
						</div>
						--%>
						<div class="col-md-12 register-form register-form-names ">
							<div class="col-xl-2 col-md-12 col-12 register-form focus-on-change register-forms-select px-0 right_arrowdd">
								<formElement:formSelectBox idKey="quickregistration.register.title"
										labelKey="register.title" selectCSSClass="form-control jqTitle js-select-required register-user-details"
										path="titleCode" mandatory="true" skipBlank="false" labelCSS="register-user-info-label select-label-mandatory"
										skipBlankMessageKey="form.select.empty" items="${titles}"/>	
							</div>
							<div class="col-xl-5 col-md-12 col-12 focus-on-change ">
								<%--
								<label class="register-user-info-label" for="reg-fName">First Name<span class="mandatory">*</span></label>
								<input type="text" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-fName" name="FName" />
								--%>
								<formElement:formInputBox idKey="quickregistration.register.firstName"
										labelKey="register.firstName" path="firstName" inputCSS="js-quickregister-firstname register-user-details" labelCSS="control-label_mandatory register-user-info-label"
										mandatory="true"/>
							</div>
							<div class="col-xl-5 col-md-12 col-12 focus-on-change">
								<%--
								<label class="register-user-info-label" for="reg-lName">Last Name<span class="mandatory">*</span></label>
								<input type="text" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-lName" name="LName" />
								--%>
								<formElement:formInputBox idKey="quickregistration.register.lastName"
										labelKey="register.lastName" path="lastName" inputCSS="js-quickregister-lastname register-user-details" labelCSS="control-label_mandatory register-user-info-label"
										mandatory="true"/>
							</div>
							<div class="error-msg"></div>
						</div>
						<div class="col-md-12 register-form focus-on-change">
							<%--
							<label class="register-user-info-label" for="reg-Company">Company<span class="mandatory">*</span></label>
							<input type="text" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-Company" name="Company" />
							--%>
							<formElement:formInputBox idKey="quickregistration.register.company"
										labelKey="register.company" path="company" inputCSS="js-quickregister-company register-user-details" labelCSS="control-label_mandatory register-user-info-label"
										mandatory="true" />
							<div class="error-msg"></div>
						</div>
						<div class="col-md-12 register-form focus-on-change register-forms-select">
							<formElement:formSelectBox idKey="quickregistration.register.sector" 
										labelKey="register.sector" selectCSSClass="form-control jqSector js-select-required register-user-details" path="sector"
									   	items="${sectors}" itemLabel="name"  labelCSS="register-user-info-label select-label-mandatory"/>	
						</div>
						<div class="col-md-12 register-form focus-on-change">
							<%--
							<label class="register-user-info-label" for="reg-EmailId">Email Id<span class="mandatory">*</span></label>
							<input type="text" class="register-user-details js-register-quick-email" data-val="true" data-val-required="Required"	id="reg-EmailId" name="EmailId" />
							--%>
							<formElement:formInputBox idKey="quickregistrationEmail"
										labelKey="register.email" path="email"
										inputCSS="register-user-details js-register-quick-email" helpBlockSuccessCSS="js-help-block-success" labelCSS="control-label_mandatory register-user-info-label"
										mandatory="true" />
							<div class="error-msg"></div>
						</div>
						<div class="col-md-12 register-form focus-on-change register-forms-select">
							<formElement:formSelectBox idKey="quickregistration.register.country"
									   labelKey="register.country" selectCSSClass="form-control countriesselect jqCountry js-select2-searchBegining js-select2-sortAlphabetically register-user-details" 
									   labelCSS="control-label_mandatory register-user-info-label"
									   path="countryCode" mandatory="true" skipBlank="false"
									   skipBlankMessageKey="form.select.empty" items="${countries}" itemValue="code"/>	
						</div>
						<div class="col-md-12 register-form form-floating country-code-mobile">
							<div class="col-xl-8 col-12 ml-0 pl-0 d-flex">
								<%--
								<input type="text" class="ddl-countryCode register-user-details register-contry-info" placeholder=" " autocomplete="off" id="reg-country">
								<div class="input-wrapper focus-on-change">
									<label class="register-user-info-label" for="reg-mobile">Contact No<span class="mandatory">*</span></label>
									<input type="text" class="register-user-details validate-mobile mobile-number" id="reg-mobile" name="MobileNumber"  type="number"/>
									<div class="error-msg"></div>
								</div> 
								--%>
								<formElement:formInputBox idKey="quickregistration.register.mobileCountryCode"
											labelKey="register.mobileCountryCode" path="mobileCountryCode" labelCSS="ddl-countrycode-label"
											inputCSS="js-quickregister-countrycode form-control_preNumber js-mobile-coutry-code ddl-countryCode register-user-details register-contry-info" readonly = "true" />
								<div class="input-wrapper focus-on-change">
									<formElement:formInputBox idKey="quickregistration.register.mobileNumber"
												labelKey="register.mobileNumber" path="mobileNumber"
												helpBlockSuccessCSS="js-help-block-success"
												inputCSS="js-quick-mobile-number register-user-details validate-mobile mobile-number" labelCSS="control-label_mandatory register-user-info-label" inputBoxCSS="formInputBox_big"
												mandatory="true"/>
								</div>								
							</div>
							<div class="col-xl-4 col-12 mx-0 px-0 register-language" id="registerQuickSelectLanguageDiv">
								<span><spring:theme code="general.language"/></span>
								<label class="switch">
									<input type="checkbox" id="registerQuickSelectLanguageSelect">
									<div class="slider round"></div>
								</label>
							</div>
						</div>
					</div>
					<div class="login-buttons user-info-buttons">
						<div class="col-md-6 col-12">
							<a class="login-btn register-investor-screen3-btn-back padding-top-10"><spring:theme code="investor.registration.back.button"/></a>
						</div>
						<div class="col-md-6 col-12">
							<a class="login-btn register-investor-screen3-btn-next padding-top-10"><spring:theme code="investor.registration.next.button"/>&nbsp;
								<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561" class="next-hide">
									<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
										d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
										transform="translate(-7.875 -11.252)" fill="#fff">
									</path>
								</svg>
							</a>
						</div>
					</div>
				</div>
			</div>

			<div class="row col-lg-12 col-xl-12 col-12 register-account-investor-screen4 r-sn next-hide">
				<div class="login-right-wrapper">
					<div class="login-register-text"><spring:theme code="investor.registration.your.account.label"/></div>
					<div class="register-role-selection">
						<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid" />
						<img src="" class="img-fluid bussiness-sector-selected" alt=""/>
					</div>
					<div class="register-progress">
						<span class="circle progress-completion register-progress-selection 1">&#10003;</span>
						<hr />
						<span class="circle progress-completion register-progress-selection 2">&#10003;</span>
						<hr />
						<span class="circle register-progress-selection 3">3</span>						
						<%--
						<hr />
						<span class="circle register-progress-selection 4">4</span>
						--%>
					</div>
					<div class="login-register-role"><spring:theme code="investor.registration.create.username.label"/></div>
					<div class="row register-user-info">
						<div class="col-md-12 register-form focus-on-change">
							<%--
							<label class="register-user-info-label" for="reg-username">User Name<span class="mandatory">*</span></label>
							<input type="text" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-username" name="username" />
							--%>
							<formElement:formInputBox idKey="quickregistrationUsername"
										labelKey="register.userName" path="userName"
										inputCSS="js-register-quick-user-name register-user-details" labelCSS="control-label_mandatory register-user-info-label"
										mandatory="true"
										helpBlockSuccessCSS="js-help-block-success"/>
						</div>
						<div class="col-md-12 register-form focus-on-change password-field-height">
							<%--
							<label class="register-user-info-label" for="reg-password">Password<span class="mandatory">*</span></label>
							<input type="password" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-password" name="password" maxlength="30" />
							<i class="far fa-eye" id="togglePassword"></i>
							--%>
							<%-- <i class="far fa-info-circle" aria-hidden="true"></i> --%>
							<formElement:formPasswordBox idKey="quickregistration.password"
										labelKey="register.pwd" path="pwd"
										inputCSS=" quickregistrationPwd register-user-details" labelCSS="control-label_mandatory register-user-info-label"																
										mandatory="true"/>
							<i toggle="#password-field" class="fa fa-eye toggle-password1" aria-hidden="true"  id="togglePassword"></i>
							<meter max="4" id="password-strength-meter" value="0" class=""></meter>
							<div class="error-msg"></div>
						</div>
						<div class="col-md-12 register-form focus-on-change">
							<%--
							<label class="register-user-info-label" for="reg-cpassword">Confirm Password<span class="mandatory">*</span></label>
							<input type="password" class="register-user-details" data-val="true" data-val-required="Required"	id="reg-cpassword" name="cpassword" maxlength="30" />
							<i class="far fa-eye" id="toggleCPassword"></i>
							--%>
							<formElement:formPasswordBox idKey="quickregistration.register.checkPwd" 
										labelKey="register.checkPwd" path="checkPwd" inputCSS="quickregistrationCheckPwd register-user-details" labelCSS="control-label_mandatory register-user-info-label" 
										mandatory="true" />
							<i toggle="#password-field" class="fa fa-eye toggle-password2" aria-hidden="true"  id="toggleCPassword"></i>
							<div class="error-msg"></div>
						</div>
						<div class="col-md-12 register-form focus-on-change">
							<div class="form-item register-form-terms-condition">
								<input id="termsAndConditionsRegister" name="termsAndConditionsChecked" placeholder="." class="reg-terms-checkbox " type="checkbox" value="true">
								<input type="hidden" name="_termsAndConditionsChecked" value="on">
								<div class="col-md-1 reg-terms-checkbox-span">
									<span for="termsAndConditionsRegister">
										<svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
											<path fill="#fff" d="M4.477 14.003l-4.178-4.102 1.402-1.427 2.823 2.773 9.8-8.984 1.352 1.474z"></path>
										</svg>
									</span>
								</div>
								<div class="col-md-9 mx-0 px-0">															
									<label class="register-terms uncased mb-0" for="termsAndConditionsRegister">
										<spring:theme code="investor.registration.read.agree.label"/>
										<a class="register-terms-link" target="_blank" href="/en/cms/sagia-cms-TandC-registration"><spring:theme code="investor.registration.tearm.label"/></a>
									</label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
						<div class="col-md-12 ">
							<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
							<div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
							<span id="lblErrorCaptchareg" class="mandatory"></span>
						</div>
					</div>
					<div class="login-buttons user-info-buttons">
						<div class="col-md-6 col-12">
							<a class="login-btn register-investor-screen4-btn-back padding-top-10"><spring:theme code="investor.registration.back.button"/></a>
						</div>
						<div class="col-md-6 col-12">
							<button class="login-btn register-investor-screen4-btn-next"><spring:theme code="investor.registration.submit.button"/> &nbsp;
								<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561" class="next-hide">
									<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
										d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
										transform="translate(-7.875 -11.252)" fill="#fff">
									</path>
								</svg>
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row col-lg-12 col-xl-12 col-12 register-account-investor-screen5 r-sn next-hide" id="requestSubmittedApply" >
				<div class="login-right-wrapper">
					<div class="login-register-text"><spring:theme code="investor.registration.your.account.label"/></div>
					<div class="register-role-selection">
						<img src="${commonResourcePath}/images/B2C/Investor-icon.png" alt="Investor" class="img-fluid" />
						<img src="" class="img-fluid bussiness-sector-selected" alt=""/>
					</div>
					<div class="login-register-role reg-sucess"><spring:theme code="investor.registration.success.label"/></div>
					<div class="row register-user-info">
						<span class="circle success-circle"><span class="sucess-checkmark">&#10003;</span></span>
						<!-- 
						<div class="col-md-12 register-form register-success-msg" dir="ltr">
							<span>Thank you for registering with us. Your registration is Under review. One of our export will be in touch with you as soon as possible.</span>
						</div>		
						 -->											
					</div>
					<div class="login-buttons user-info-buttons">
						<div class="col-md-6 col-12">
							<a href="/en/" class="login-btn register-investor-screen5-btn-back website-from-register padding-top-10">
								<spring:theme code="investor.registration.invest.saudi.button"/>
							</a>
						</div>
						<div class="col-md-6 col-12">
							<a href="/en/login" class="login-btn register-investor-screen5-btn-next active login-from-registration padding-top-10">
								<spring:theme code="investor.registration.login.button"/>&nbsp;
								<svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561" >
									<path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
										d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
										transform="translate(-7.875 -11.252)" fill="#fff">
									</path>
								</svg>
							</a>
						</div>
					</div>
				</div>
			</div>					
		</form:form>
	</div>
</div>


<%--
<div class="panelTabs-body panelModule panelModule_halfRadius panelModule_transparent90">
	<div class="row">
		<div class="col-md-4">
			<div id="sagia-cms-help-quick-registration-helper"></div>
		</div>
		<div class="col-md-8">
			<div class="accountLogin-content-slot accountLogin-content-slot_right">
				<form:form method="post" id="sagiaRegisterFormQuickRegistration" modelAttribute="sagiaRegisterForm" action="${registerAction}#register-quick">
					<c:if test="${not empty registerFormError}">
						<div class="formError">
							<icon:messageError/><spring:theme code="${registerFormError}" arguments=""/>
						</div>
					</c:if>
					<formElement:formSelectBox idKey="quickregistration.register.title"
						labelKey="register.title" selectCSSClass="form-control jqTitle js-select-required"
						path="titleCode" mandatory="true" skipBlank="false"
						skipBlankMessageKey="form.select.empty" items="${titles}"/>

					<formElement:formInputBox idKey="quickregistration.register.firstName"
						labelKey="register.firstName" path="firstName" inputCSS="form-control js-quickregister-firstname" labelCSS="control-label_mandatory"
						mandatory="true"/>
					<formElement:formInputBox idKey="quickregistration.register.lastName"
						labelKey="register.lastName" path="lastName" inputCSS="form-control js-quickregister-lastname" labelCSS="control-label_mandatory"
						mandatory="true"/>

					<formElement:formInputBox idKey="quickregistration.register.company"
											  labelKey="register.company" path="company" inputCSS="form-control" labelCSS="control-label_mandatory"
											  mandatory="true" />
											  
					<formElement:formSelectBox idKey="quickregistration.register.sector" labelKey="register.sector" selectCSSClass="form-control jqSector js-select-required" path="sector"
											   items="${sectors}" itemLabel="name"/>											  
					<formElement:formInputBox idKey="quickregistrationEmail"
											  labelKey="register.email" path="email"
											  inputCSS="form-control js-register-quick-email" helpBlockSuccessCSS="js-help-block-success" labelCSS="control-label_mandatory"
											  mandatory="true" />
											  
					<formElement:formSelectBox idKey="quickregistration.register.country"
											   labelKey="register.country" selectCSSClass="form-control countriesselect jqCountry js-select2-searchBegining js-select2-sortAlphabetically" labelCSS="control-label_mandatory"
											   path="countryCode" mandatory="true" skipBlank="false"
											   skipBlankMessageKey="form.select.empty" items="${countries}" itemValue="code"/>											  											 

					<div class="formInputBox-split">
						<formElement:formInputBox idKey="quickregistration.register.mobileCountryCode"
												  labelKey="register.mobileCountryCode" path="mobileCountryCode"
												  inputCSS="form-control_preNumber js-mobile-coutry-code"/>
						<formElement:formInputBox idKey="quickregistration.register.mobileNumber"
												  labelKey="register.mobileNumber" path="mobileNumber"
												  helpBlockSuccessCSS="js-help-block-success"
												  inputCSS="form-control js-quick-mobile-number" labelCSS="control-label_mandatory" inputBoxCSS="formInputBox_big"
												  mandatory="true"/>
					</div>

					<formElement:formInputBox idKey="quickregistrationUsername"
											  labelKey="register.userName" path="userName"
											  inputCSS="form-control js-register-quick-user-name" labelCSS="control-label_mandatory"
											  mandatory="true"
											  helpBlockSuccessCSS="js-help-block-success"/>
					<formElement:formPasswordBox idKey="quickregistration.password"
												 labelKey="register.pwd" path="pwd"
												 inputCSS="form-control quickregistrationPwd" labelCSS="control-label_mandatory"
												 mandatory="true"/>
					<formElement:formPasswordBox idKey="quickregistration.register.checkPwd" labelKey="register.checkPwd" path="checkPwd" inputCSS="form-control quickregistrationCheckPwd" labelCSS="control-label_mandatory" mandatory="true" />

					<div class="formSelectBox" id="registerQuickSelectLanguageDiv">
						<div class="form-group">
							<select class="js-select2-oneColumn" id="registerQuickSelectLanguageSelect" name="language">
								<c:if test="${currentLanguage.isocode == 'en'}">
									<option value="en" selected="selected">English</option>
									<option value="ar">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
								</c:if>
								<c:if test="${currentLanguage.isocode == 'ar'}">
									<option value="en">English</option>
									<option value="ar" selected="selected">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
								</c:if>
							</select>
							<label class="control-label" for="registerQuickSelectLanguageSelect"><spring:theme code="general.language"/></label>
						</div>
					</div>

					<div class="accountLogin-content-formSubmitSection" >
						<div class="accountLogin-content-formSubmitSection-checkbox formCheckBox">
							<formElement:termsAndConditionsCheckbox id="termsAndConditionsRegister" path="termsAndConditionsChecked" event="REGISTRATION"/>
						</div>
						<input type="hidden" id="recaptchaChallangeAnswered"
							value="${requestScope.recaptchaChallangeAnswered}" />
						<div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
						<div class="form-actions clearfix">
							<ycommerce:testId code="register_Register_button">
								<button type="submit" class="btn btn-default quick-register js-quick-register-btn font-weight-bold"><spring:theme code='${actionNameKey}'/></button>
							</ycommerce:testId>
						</div>
						<div class="accountLogin-content-formSubmitSection-formSwitchText text-center">
							<spring:theme code="account.existing"/>&nbsp;<a class="accountLogin-content-toggleBtn" style="color: #3cdc0f;font-weight:600"><spring:theme code="account.existing.login"/>&nbsp;<spring:theme code="register.text.here"/></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

--%>

<%-- TAB 2 --%>
<%-- <div class="panelTabs-head" id="register-apply">
	<icon:apply/>
	<!-- iconElement-colorBackground_stroke // stroke="#fff" -->
	<span class="panelTabs-label"><spring:theme code="general.apply"/></span>
</div>
<div class="panelTabs-body panelModule panelModule_halfRadius panelModule_transparent90">
	<div class="js-panelTabs_nested panelTabs_nested">
		<div class="panelTabs_nested-head" data-show="#register-apply_side">
			<span class="panelTabs_nested-link"><spring:theme code="license.label"/></span>
		</div>
		<div class="panelTabs_nested-body">
			<div class="row">
				<div class="col-md-4">
					<div id="sagia-cms-help-apply-sagia-license"></div>
				</div>

				<div class="col-md-8">
					<div class="accountLogin-content-slot accountLogin-content-slot_right">
						<form:form method="post" acceptCharset="UTF-8"
								   data-redirect="/my-sagia/license/entity"
								   id="sagiaRegisterFormApplyLicense" modelAttribute="sagiaRegisterForm" action="${applyAction}#register-apply">
							<formElement:formSelectBox idKey="applylicense.register.title"
													   labelKey="register.title" selectCSSClass="form-control js-select-required"
													   path="titleCode" mandatory="true" skipBlank="false"
													   skipBlankMessageKey="form.select.empty" items="${titles}" />
							<formElement:formInputBox idKey="applylicense.register.firstName"
													  labelCSS="control-label_mandatory" labelKey="register.firstName"
													  path="firstName" inputCSS="form-control js-applylicense-lastname"
													  mandatory="true" />
							<formElement:formInputBox idKey="applylicense.register.lastName" labelKey="register.lastName"
													  labelCSS="control-label_mandatory" path="lastName"
													  inputCSS="form-control js-applylicense-firstname"
													  mandatory="true" />
							<formElement:formInputBox idKey="applylicense.register.company" labelKey="register.company"
													  labelCSS="control-label_mandatory" path="company" inputCSS="form-control"
													  mandatory="true" />
													  
<!-- 								probably needs backend adjustments  -->
							<formElement:formSelectBox idKey="applylicense.register.sector"
														labelKey="register.sector" 
														selectCSSClass="form-control js-select-required"
										   				path="sector"
											   			items="${sectors}" 
											   			itemLabel="name"/>													  
													  
							<formElement:formInputBox idKey="applylicense.register.email"
													  labelCSS="control-label_mandatory"
													  labelKey="register.email" path="email" inputCSS="form-control js-apply-email"
													  helpBlockSuccessCSS="js-help-block-success"
													  mandatory="true" />
							

<!-- 								probably needs backend adjustments 						   -->
							<formElement:formSelectBox idKey="applylicense.register.country"
													   labelCSS="control-label_mandatory"
													   labelKey="register.country" selectCSSClass="form-control countriesselect.apply js-select2-searchBegining js-select2-sortAlphabetically"
											   path="countryCode" mandatory="true" skipBlank="false"
											   skipBlankMessageKey="form.select.empty" items="${countries}" itemValue="code"/>														  
													  
							<div class="formInputBox-split">
								<formElement:formInputBox idKey="applylicense.register.mobileCountryCode"
													  labelKey="register.mobileCountryCode" path="mobileCountryCode"
													  inputCSS="form-control_preNumber js-mobile-coutry-code"
													  mandatory="false" />
								<formElement:formInputBox idKey="applylicense.register.mobileNumber"
														  labelCSS="control-label_mandatory"
														  labelKey="register.mobileNumber" path="mobileNumber"
														  inputCSS="form-control_labeled js-apply-mobile-number" inputBoxCSS="formInputBox_big"
														  helpBlockSuccessCSS="js-help-block-success"
														  mandatory="true" />
							</div>
							
<!-- 								probably needs backend adjustments 	 -->
							<formElement:formInputBox idKey="applyForLicenseUsername"
													  labelCSS="control-label_mandatory"
													  labelKey="register.userName" path="userName" inputCSS="form-control js-apply-user-name"
													  helpBlockSuccessCSS="js-help-block-success"
													  mandatory="true" />							

							<formElement:formPasswordBox idKey="applylicense.password" labelKey="register.pwd"
														 labelCSS="control-label_mandatory" path="pwd" inputCSS="form-control" mandatory="true" />
							<formElement:formPasswordBox idKey="applylicense.password.checkPwd" labelKey="register.checkPwd"
														 labelCSS="control-label_mandatory" path="checkPwd" inputCSS="form-control" mandatory="true" />

							<div class="formSelectBox" id="registerApplySelectLanguageDiv">
								<div class="form-group">
									<select class="js-select2-oneColumn" id="registerApplySelectLanguageSelect" name="language">
										<c:if test="${currentLanguage.isocode == 'en'}">
											<option value="en" selected="selected">English</option>
											<option value="ar">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
										</c:if>
										<c:if test="${currentLanguage.isocode == 'ar'}">
											<option value="en">English</option>
											<option value="ar" selected="selected">&#1575;&#1604;&#1593;&#1585;&#1576;&#1610;&#1577;</option>
										</c:if>
									</select>
									<label class="control-label" for="registerApplySelectLanguageSelect"><spring:theme code="general.language"/></label>
								</div>
							</div>

							<c:if test="${ not empty consentTemplateData }">
								<form:hidden path="consentForm.consentTemplateId" value="${consentTemplateData.id}" />
								<form:hidden path="consentForm.consentTemplateVersion" value="${consentTemplateData.version}" />
								<div class="checkbox">
									<label class="control-label uncased">
										<form:checkbox path="consentForm.consentGiven" disabled="true"/>
										<c:out value="${consentTemplateData.description}" />
									</label>
								</div>
								<div class="help-block">
									<spring:theme code="registration.consent.link" />
								</div>
							</c:if>

							<div class="accountLogin-content-formSubmitSection">
								<div class="accountLogin-content-formSubmitSection-checkbox formCheckBox">
									<formElement:termsAndConditionsCheckbox id="termsAndConditionsApplyLicense" path="termsAndConditionsChecked" event="REGISTRATION"/>
								</div>

								<input type="hidden" id="applylicense.recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
								<div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
								<div class="form-actions clearfix">
									<ycommerce:testId code="register_Register_button">
										<button type="submit" class="btn btn-default js-apply-license-btn">
											<spring:theme code='${actionNameKey}'/>
										</button>
									</ycommerce:testId>
								</div>
								<div class="accountLogin-content-formSubmitSection-formSwitchText text-center"><spring:theme code="account.existing"/> <a class="accountLogin-content-toggleBtn"><spring:theme code="account.existing.login"/>&nbsp;<spring:theme code="account.existing.login.here"/>.</a></div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<div class="panelTabs_nested-head" >
			<span class="panelTabs_nested-link"><spring:theme code="register.unified.licence.title"/></span>
		</div>
		<div class="panelTabs_nested-body">
			<div class="row">
				<div class="col-md-4">
					<div id="sagia-cms-help-apply-unified-license"></div>
				</div>

				<div class="col-md-8">
					<div class="accountLogin-content-slot accountLogin-content-slot_right">
						<div class="contentModule">
							<div class="contentModule-headline contentModule-headline_larger text-center"><spring:theme code="register.apply.unified.licence"/><!-- <spring:theme code="register.new.customer" /> --></div>
							<div class="contentModule-actions contentModule-actions_centered">
								<a id="unifiedLicenseUrl" target="_blank" href="#" type="reset" class="btn"><spring:theme code="register.button.apply.text"/></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> --%>

<!-- TAB 3 -->
<%-- <userTags:nationalInvestor/> --%>
<!-- END TAB 3 -->

<%--
<div class="modal fade" id="requestSubmittedApply"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"><spring:theme code="general.successregistration"/></div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:modal02/>
				</div>
			</div>
			<div class="modal-description">
				<spring:theme code="general.thankforfillingform"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn" data-dismiss="modal"><spring:theme code="general.login"/></button>
			</div>
		</div>
	</div>
</div>
--%>

<div class="modal fade" id="failInformationModal"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title js-message"><spring:theme code="register.applyforlicense.error"/></div>
			</div>
			<div class="modal-body modal-body-center">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="register.applyforlicense.close"/></button>
			</div>
		</div>
	</div>
</div>
