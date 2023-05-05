<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <section class="mizaSection mizaEnquiry stsContact-section secDark company-mgr-quote" id="strategic-contact-form">
        <div class="can-help-background" >
        <div class="container-fluid">
          <div class="row align-items-center">
            <div class="col-xl-6">
                <div class="strategicTitle wow zoomIn  animated" data-wow-duration="1s">
                      <!-- <h5 class="strategicSubTitle">STRATEGIC</h5> -->
              <h1><spring:theme code="portal.strategic.contact.expert.name.label"/></h1>
              <p class="contactcnt-text">Submit your queries here and we will get back to you as soon as possible.</p>
          </div>

             </div>

            <div class="col-xl-6">
                <div class="contactSuccess d-none">
                    <p class="font-bold">Thank you for your interest in the Invest Saudi.</p>
                    <p>We received your inquiry, and we will get back to you shortly.</p>
                    <p class="contact-sucess-ticket"></p>
                </div>

                <div class=" wow fadeIn mizaEnquiryFromWrap stscontactForm-sec" data-wow-delay="0.2s" id="strategicContactForm">
                    <div class="stscontactForm-header">
                        <h6 class="stscontactForm-subtitle "> <spring:theme code="portal.strategic.contact.expert.name.label"/></h6>

                    </div>
                    <div class="row">
                           <div class=" col-md-6 ">
                            <div class="form-group">
                                   <label class="" for="strategiccrName">
                                    <spring:theme code="portal.sector.contact.expert.name.label"/>
                                    <span class="mandatory">* </span>
                                   </label>
                                   <input type="text" class="form-control required validate-name" data-val="true"
                                        data-val-regex="Numbers and Special Characters are not allowed"
                                   data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                   data-val-required="Required" id="strategiccrName" name="Name"/>
                                   <div class="error-msg"></div>
                                </div>
                            </div>

                            <div class=" col-md-6 ">
                                     <div class="form-group ">
                                        <label class="" for="strategiccrCompany">
                                            <spring:theme code="portal.sector.contact.expert.companyName.label"/>
                                            <span class="mandatory">* </span>
                                        </label>
                                        <input type="text" class="form-control required validate-name" data-val="true"
                                        data-val-regex="Numbers and Special Characters are not allowed"
                                        data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                        id="strategiccrCompany" name="Company"/>
                                        <div class="error-msg"></div>
                                    </div>
                                </div>
                    </div>
                    <div class="row">
                        <div class=" col-md-6 ">
                            <div class="form-group ">
                                <label class="" for="strategiccrMobile">
                                    <spring:theme code="portal.sector.contact.expert.phoneNumber.label"/>
                                    <span class="mandatory">* </span>
                                </label>
                                <div class="country-code-mobile">
                                    <input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">
                                    <div class="input-wrapper">
                                        <input type="text" style="padding-left: 50px;margin-bottom: 0;" class="form-control validate-mobile required mobile-number" id="strategiccrMobile" name="MobileNumber"  type="number"/>
                                        <div class="error-msg"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class=" col-md-6 ">
                            <div class="form-group  ">
                                <label class="" for="strategiccrPosition">
                                    <spring:theme code="portal.sector.contact.expert.position.label"/>
                                    <span class="mandatory">* </span>
                                </label>
                                <input type="text" class="form-control required validate-name" data-val="true"
                                data-val-regex="Numbers and Special Characters are not allowed"
                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                id="strategiccrPosition" name="Position"/>
                                <div class="error-msg"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class=" col-md-6 ">
                            <div class="form-group  ">
                                <label class="" for="strategiccrEmail">
                                    <spring:theme code="portal.sector.contact.expert.email.label"/><span class="mandatory">* </span>
                                </label>
                                <input type="text" class="form-control validate-email required" data-val="true"
                                    data-val-regex="Invalid email address"
                                    data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                    data-val-required="Required" id="strategiccrEmail" name="Email"/>
                                <div class="error-msg"></div>
                            </div>
                        </div>
                        <div class=" col-md-6 ">
                            <div class="form-group ">
                               <label class="" for="strategiccrSubjectID"><spring:theme code="portal.sector.contact.expert.service.type.label"/><span class="mandatory">* </span></label>
                               <select id="strategiccrSubjectID" class="form-control required drop-item">
                                    <c:forEach var="contactSubject" items="${contactSubjects}">
                                        <option value="${contactSubject.code}">${contactSubject.label}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input id="strategiccrSubjectID" name="SubjectID" type="hidden" value=""/>
                    <span class="field-validation-valid" data-valmsg-for="Subject"
                          data-valmsg-replace="true"></span>
                          <div class="row ">
                    <div class=" col-md-12 ">
                        <div class="form-group">
                        <label class="" for="crJobTitle">
                                                    <spring:theme code="portal.sector.contact.expert.enquiry.label"/>
                                                    <span class="mandatory">* </span>
                                                </label>
                        <textarea class="form-control required" cols="10" data-val="true"
                                  data-val-length="Name length should be between 2 &amp; 1000" data-val-length-max="1000"
                                  data-val-length-min="2" data-val-required="Required" id="strategiccrMessage" name="Enquiry"
                                  placeholder="${messageLabel}" rows="5" style="padding: 20px; padding-left: 35px; padding-top: 25px;">
                         </textarea>
                           <div class="error-msg"></div>
                        </div>
                    </div>
                </div>

                        <div class="col-md-12 ">
                            <div class="form-group captchaWrapper" style="display: flex;  align-items: center; justify-content: center; margin-top: 20px;">
                            <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
                               <div class="form_field-elements control-group js-recaptcha-captchaaddon sector-page-captcha"></div>
                                  <span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
                                  </div>
                         </div>
                    <div class="row form-action-btn" style="padding-left: 15px; padding-right: 15px;justify-content: center;margin-top: 40px;">
                       <div class="col-md-3">
                                <button type="button" id="contact-clear" class="btn btn-primary-fill btn-form-outline w-100">
                                    <spring:theme code="portal.sector.download.clear.button"/></button></div>
                            <div class="col-md-3"><button id="btn-strategic-contact" type="submit" class="responsive-contact-btn btn btn-primary-fill btn-form-fill  w-100" onclick="onStrategicContactload()">
                                    <spring:theme code="portal.sector.download.submit.button"/></button></div>
                          </div>
                    </div>
                ${successMessage}

            </div>
          </div>
        </div>
      </div>
      </section>
</c:if>
