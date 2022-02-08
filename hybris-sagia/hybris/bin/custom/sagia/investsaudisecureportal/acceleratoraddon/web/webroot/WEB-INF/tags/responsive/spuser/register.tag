<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ attribute name="userGroup" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<%--
<div class="headline">
    <spring:theme code="text.secureportal.register.new.customer"/>
</div>
--%>

<div class="row register-user-info">
    <form:form method="post" id="registerForm-${userGroup}" modelAttribute="registrationForm" action="${action}" cssClass="registerForm">

        <formElement:formInputBox idKey="text.secureportal.${userGroup}.register.firstAndLastName"
                                  labelKey="text.secureportal.register.firstAndLastName" path="name"
                                  inputCSS="form-control register-user-details" mandatory="true" labelCSS="control-label_mandatory register-user-info-label"
                                  maxlength="60"/>

        <formElement:formSelectBox idKey="user.${userGroup}.entity_del" labelKey="user.entity"
                                   path="userEntity" mandatory="false"
                                   skipBlank="false" skipBlankMessageKey="user.selectUserEntity" items="${b2bUnit}"
                                   itemValue="id" selectCSSClass="form-control js-secureportal-userEntity register-user-details"
                                   labelCSS="control-label_mandatory register-user-info-label focused"/>
        <div class="form-group js-secureportal-otherUserEntity" style="display:none">
            <formElement:formInputBox idKey="text.secureportal.${userGroup}.register.otherUserEntity"
                                      labelKey="text.secureportal.register.otherUserEntity" path="otherUserEntity"
                                      inputCSS="form-control register-user-details"
                                      mandatory="true" labelCSS="control-label_mandatory register-user-info-label" maxlength="60"/>
        </div>
        <formElement:formInputBox idKey="text.secureportal.${userGroup}.register.department"
                                  labelKey="text.secureportal.register.department" path="department"
                                  inputCSS="form-control register-user-details"
                                  mandatory="true" labelCSS="control-label_mandatory register-user-info-label" maxlength="60"/>
        <%-- <formElement:formInputBox idKey="text.secureportal.register.companyName"
                                  labelKey="text.secureportal.register.companyName" path="companyName"
                                  inputCSS="form-control" mandatory="true"/> --%>
        <%--  <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="companyAddressStreet"
                                   inputCSS="form-control" mandatory="true"/>
         <formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="companyAddressStreetLine2"
                                   inputCSS="form-control register-user-details" mandatory="false"/>
         <formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="companyAddressCity"
                                   inputCSS="form-control register-user-details " mandatory="true"/> --%>
        <%--  <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="companyAddressPostalCode"
                                   inputCSS="form-control" mandatory="true"/> --%>
        
                <formElement:formInputBox idKey="text.secureportal.${userGroup}.register.position"
                                          labelKey="text.secureportal.register.position" path="position"
                                          inputCSS="form-control register-user-details"
                                          mandatory="true" labelCSS="control-label_mandatory register-user-info-label" maxlength="60"/>

                <input id="lob" name="lob" type="hidden" value="${userGroup}"/>
            
        <%-- <formElement:formSelectBox idKey="user.entity_del" labelKey="user.entity"
                                    path="userEntity" mandatory="false"
                                    skipBlank="false" skipBlankMessageKey="user.selectUserEntity" items="${b2bUnits}"
                                    itemValue="id" selectCSSClass="form-control" labelCSS="control-label_mandatory register-user-info-label" /> --%>
        <div class="row">
            <div class="col-sm-3 extension">
                <formElement:formInputBox idKey="text.secureportal.${userGroup}.register.extension" placeholder="966"
                                          labelKey="text.secureportal.register.extension" path="telephoneExtension"
                                          labelCSS="register-user-info-label focused focused-country"
                                          inputCSS="form-control js-secureportal-telephoneExtension register-user-details" maxlength="5"/>
            </div>
            <div class="col-sm-9 phone">
                <formElement:formInputBox idKey="storeDetails.${userGroup}.table.telephone" labelKey="storeDetails.table.telephone"
                                          path="telephone" inputCSS="form-control  js-secureportal-telephone register-user-details"
                                          mandatory="true" labelCSS="control-label_mandatory register-user-info-label" maxlength="15"/>
            </div>

        </div>


        <formElement:formInputBox idKey="register.${userGroup}.email" labelKey="register.email" path="email"
                                  inputCSS="form-control js-secureportal-orignal-register-email register-user-details" mandatory="true"
                                  labelCSS="control-label_mandatory register-user-info-label" maxlength="60"/>

        <div class="form-group">
            <label class="control-label control-label_mandatory register-user-info-label" for="register.${userGroup}.confirm.email"> <spring:theme
                    code="guest.confirm.email"/></label>
            <input class="form-control js-secureportal-confirm-register-email register-user-details" id="register.${userGroup}.confirm.email" labelCSS="register-user-info-label"
                   maxlength="60"/>

            <div class="js-secureportal-email-not-match-message has-error" style="display:none">
                <span class="help-block">
                    <spring:theme code="text.secureportal.register.email.not.match"/>
                </span>
            </div>
        </div>

        <%--      <formElement:formTextArea idKey="text.secureportal.register.message"
                                       labelKey="text.secureportal.register.message" path="message"
                                       areaCSS="textarea form-control" mandatory="false"/>
      --%>
        


        <div class="register-form-action row">
            <div class="col-xs-12">
                <div class="accountLogin-content-formSubmitSection-checkbox formCheckBox js-secureportal-terms">
                    <formElement:termsAndConditionsCheckbox id="termsAndConditionsRegister-${userGroup}"
                                                            cssClass="termsAndConditionsRegister"
                                                            path="termsAndConditionsChecked" event="REGISTRATION"/>
                </div>
            <div class="col-xs-12">
                 <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}"/>

        <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
                 </div>
            </div>
            <div class="col-xs-12 col-md-4 col-md-offset-4">
           
                <ycommerce:testId code="register_Register_button">
                    <button data-theme="b"
                            class="js-secureportal-register-button login-btn login-btn-next active btn-block">
                        <spring:theme code='${actionNameKey}'/>
                    </button>
                </ycommerce:testId>
            </div>
        </div>
    </form:form>
    <div style="padding-top: 14 px;" class="text-center">
      
       <spring:theme code="login.back"/> <a href="./login" class="login-back">
            <spring:theme code="login.link.back"/>
        </a>
    </div>
</div>
