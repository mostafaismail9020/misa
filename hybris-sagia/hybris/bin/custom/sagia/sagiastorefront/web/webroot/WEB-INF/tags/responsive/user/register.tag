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
<!-- container, possibly to be elsewhere -->
<div class="panelTabs-head" id="register-quick">
	<icon:register/><span class="panelTabs-label"><spring:theme code="quickRegistration.label"/></span>
</div>

<c:url value="/register/ajax" var="registerAction" />
<c:url value="/register/apply" var="applyAction" />
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

					<%--<formElement:formSelectBox idKey="register.country" labelKey="register.country" selectCSSClass="form-control" path="SagiaCountryData"--%>
											   <%--items="${countries}" itemLabel="code"/>--%>

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

					<%--<c:if test="${ not empty consentTemplateData }">--%>
						<%--<form:hidden path="consentForm.consentTemplateId" value="${consentTemplateData.id}" />--%>
						<%--<form:hidden path="consentForm.consentTemplateVersion" value="${consentTemplateData.version}" />--l%>
						<%--<div class="checkbox">--%>
							<%--<label class="control-label uncased">--%>
								<%--<form:checkbox path="quickregistration.consentForm.consentGiven" disabled="true"/>--%>
								<%--<c:out value="${consentTemplateData.description}" />--%>
							<%--</label>--%>
						<%--</div>--%>
						<%--<div class="help-block">--%>
							<%--<spring:theme code="registration.consent.link" />--%>
						<%--</div>--%>
					<%--</c:if>--%>

					<div class="accountLogin-content-formSubmitSection" >
						<div class="accountLogin-content-formSubmitSection-checkbox formCheckBox">
							<formElement:termsAndConditionsCheckbox id="termsAndConditionsRegister" path="termsAndConditionsChecked" event="REGISTRATION"/>
						</div>
						<input type="hidden" id="recaptchaChallangeAnswered"
							value="${requestScope.recaptchaChallangeAnswered}" />
						<div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
						<span id="lblErrorCaptcha" class="mandatory"></span>
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
				<!-- end effective content -->
				<!-- container, possibly to be elsewhere -->
			</div>
		</div>
	</div>
</div>

<!-- TAB 2 -->
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