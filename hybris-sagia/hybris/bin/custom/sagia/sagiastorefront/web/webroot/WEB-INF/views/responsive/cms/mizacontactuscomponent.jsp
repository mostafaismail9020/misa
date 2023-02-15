<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <section class="mizaSection mizaEnquiry p100 company-mgr-quote" id="miza-contact-form">
    
            <div class="container-fluid">
               <h2 class="mizaTitle  wow fadeInUp    animated" data-wow-delay="300ms" data-wow-duration="1s"><span class="clr_gld"><spring:theme code="portal.miza.contact.expert.name.label"/></span></h2>



     
       
            <!--<div class="accountLogin-headline">
                <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                    <img src="https://investsaudi.sa/investsaudistorefront/_ui/responsive/common/images/logo-en.svg">
                </a>
            </div> -->
         
            <div class="contactSuccess d-none">
                <p class="font-bold">Thank you for your interest in the Invest Saudi.</p>
                <p>We received your inquiry, and we will get back to you shortly.</p>
                <p class="contact-sucess-ticket"></p>
            </div>
          
             
                    <div class=" wow fadeIn mizaEnquiryFromWrap " data-wow-delay="0.2s" id="mizaContactForm">

                        <div class="row">
                               <div class=" col-md-6 ">
                                <div class="form-group">
                                       <label class="" for="mizacrName">
                                        <spring:theme code="portal.sector.contact.expert.name.label"/>
                                        <span class="mandatory">* </span>
                                       </label>
                                       <input type="text" class="form-control required validate-name" data-val="true"
                                            data-val-regex="Numbers and Special Characters are not allowed"
                                       data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                       data-val-required="Required" id="mizacrName" name="Name"/>
                                       <div class="error-msg"></div>
                                    </div>
                                </div>

                                <div class=" col-md-6 ">
                                         <div class="form-group ">
                                            <label class="" for="mizacrCompany">
                                                <spring:theme code="portal.sector.contact.expert.companyName.label"/>
                                                <span class="mandatory">* </span>
                                            </label>
                                            <input type="text" class="form-control required validate-name" data-val="true"
                                            data-val-regex="Numbers and Special Characters are not allowed"
                                            data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                            id="mizacrCompany" name="Company"/>
                                            <div class="error-msg"></div>
                                        </div>
                                    </div>
                        </div>
                        <div class="row">
                            <div class=" col-md-6 ">
                                <div class="form-group ">
                                    <label class="" for="mizacrMobile">
                                        <spring:theme code="portal.sector.contact.expert.phoneNumber.label"/>
                                        <span class="mandatory">* </span>
                                    </label>
                                    <div class="country-code-mobile">
                                        <input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">
                                        <div class="input-wrapper">
                                           
                                            <input type="text" style="padding-left: 50px;margin-bottom: 0;" class="form-control validate-mobile required mobile-number" id="mizacrMobile" name="MobileNumber"  type="number"/>
                                            <div class="error-msg"></div>
                                        </div>
                                    </div>

                                  

                                   
                                </div>
                            </div>
                            <div class=" col-md-6 ">
                                <div class="form-group  ">
                                    <label class="" for="mizacrPosition">
                                        <spring:theme code="portal.sector.contact.expert.position.label"/>
                                        <span class="mandatory">* </span>
                                    </label>
                                    <input type="text" class="form-control required validate-name" data-val="true"
                                    data-val-regex="Numbers and Special Characters are not allowed"
                                    data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                    id="mizacrPosition" name="Position"/>
                                    <div class="error-msg"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class=" col-md-6 ">
                                <div class="form-group  ">
                                    <label class="" for="mizacrEmail">
                                        <spring:theme code="portal.sector.contact.expert.email.label"/><span class="mandatory">* </span>
                                    </label>
                                    <input type="text" class="form-control validate-email required" data-val="true"
                                        data-val-regex="Invalid email address"
                                        data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                        data-val-required="Required" id="mizacrEmail" name="Email"/>
                                    <div class="error-msg"></div>
                                </div>
                            </div>
                            <div class=" col-md-6 ">
                                <div class="form-group ">
                                   <label class="" for="mizacrSubjectID"><spring:theme code="portal.sector.contact.expert.service.type.label"/><span class="mandatory">* </span></label>
                                   <select id="mizacrSubjectID" class="form-control required drop-item">
                                        <c:forEach var="contactSubject" items="${contactSubjects}">
                                            <option value="${contactSubject.code}">${contactSubject.label}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input id="mizacrSubjectID" name="SubjectID" type="hidden" value=""/>
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
                                      data-val-length-min="2" data-val-required="Required" id="mizacrMessage" name="Enquiry"
                                      placeholder="${messageLabel}" rows="5" style="padding: 20px; padding-left: 35px; padding-top: 25px;">
                             </textarea>
                               <div class="error-msg"></div>
                            </div>
                        </div>
                    </div>

                            <div class="col-md-12 ">
                                <div class="form-group captchaWrapper" style="    display: flex;  align-items: center; justify-content: center; margin-top: 20px;">
                                <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
                                   <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
                                      <span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
                                      </div>
                             </div>
                        <div class="row form-action-btn" style="padding-left: 15px; padding-right: 15px;justify-content: center;margin-top: 40px;">
                           <div class="col-md-3">
                                    <button type="button" id="contact-clear" class="btn btn-primary-fill btn-form-outline w-100">
                                        <spring:theme code="portal.sector.download.clear.button"/></button></div>
                                <div class="col-md-3"><button id="btn-miza-contact" type="submit" class="responsive-contact-btn btn btn-primary-fill btn-form-fill  w-100" onclick="onMizaContactload()">
                                        <spring:theme code="portal.sector.download.submit.button"/></button></div>
                              </div>
                        </div>
                    ${successMessage}
                    <!--<input type="hidden" id="hfSectorID" value="${categoryCode}"/>
                    <input type="hidden" id="hfOpportunity" value="${productName}"/>
                    <input type="hidden" id="hfPageTitle" value="${sectorOrProduct}"/>
                    <input type="hidden" id="hfProductCode" value="${productCode}"/> -->
              

            
        </div>
       
    </section>
</c:if>
