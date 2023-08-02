<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${component.visible}">
    <section class="invVisaApplySection invVisaApplyEnquiry" id="investor-visa-contact-form">
        <div class="container-fluid" >
         <!-- <div class="col-md-6 abt-odr-2">
            <div  class=" aboutImageGrid">
               <img class="aboutLogo"src="${component.backgroundImage.url}">
             </div>
         </div> -->
       
          <div class="row align-items-center">
            <div class="col-lg-6">

                <div class="invVisaApplyEnquirySecTitle">
                    <div class="investorVisaTitle">
                      
                        <h2 class="" ><spring:theme code="portal.investor.visa.contact.expert.name.label"/></h2>
                    </div>
                    <!-- <h3 class="wow fadeInUp  animated" data-wow-delay="500ms" data-wow-duration="2s">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</h3> -->
                    <p class="wow fadeInUp  animated" data-wow-delay="700ms" data-wow-duration="2s"> <spring:theme code="portal.investor.visa.contact.us.description"/></p>
                    </div>


        
             </div>
             <div class="col-lg-6">
                <div class="invVisaApplyEnquiryFromWrap  wow slideInRight  animated" data-wow-delay="300ms" data-wow-duration="1s" style="visibility: visible; animation-duration: 1s; animation-delay: 300ms; animation-name: slideInRight;">
                    <div class="invVisaApplyEnquiryFromTitle">
                        <h4> <span class="clr_gld"><spring:theme code="portal.investor.visa.form.heading.label"/></span></h4>
                    
                    </div>
                <div class="contactSuccess d-none">
                    <p class="font-bold">Thank you for your interest in the Invest Saudi.</p>
                    <p>We received your inquiry, and we will get back to you shortly.</p>
                    <p class="contact-sucess-ticket"></p>
                </div>

                

                <div class=" wow fadeIn mizaEnquiryFromWrap stscontactForm-sec" data-wow-delay="0.2s" id="investorVisaContactForm">
                    
                    
                    <div class="row">
                           <div class=" col-md-12 ">
                            <div class="form-group">
                                 
                                   <input type="text" class="form-control required validate-name" data-val="true"
                                        data-val-regex="Numbers and Special Characters are not allowed"
                                   data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                   data-val-required="Required" id="investorVisaName" name="Name"/>
                                   <label class="" for="investorVisaName">
                                    <spring:theme code="portal.sector.contact.expert.name.label"/>
                                    <span class="mandatory">* </span>
                                   </label>
                                   <div class="error-msg"></div>
                                   
                                </div>
                            </div>

                   
                  
                    <div class=" col-md-12 ">
                            <div class="form-group  ">
                               
                                <input type="text" class="form-control validate-email required" data-val="true"
                                    data-val-regex="Invalid email address"
                                    data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                    data-val-required="Required" id="investorVisaEmail" name="Email"/>
                                    <label class="" for="investorVisaEmail">
                                        <spring:theme code="portal.sector.contact.expert.email.label"/><span class="mandatory">* </span>
                                    </label>
                                <div class="error-msg"></div>
                                
                            </div>
                        </div>
                        
                  
                        <div class=" col-md-12 ">
                            <div class="form-group ">
                               
                                <div class="country-code-mobile">
                                    <input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">
                                    <div class="input-wrapper">
                                        <input type="text" style="padding-left: 50px;margin-bottom: 0;" class="form-control validate-mobile required mobile-number" id="investorVisaMobile" name="MobileNumber"  type="number"/>
                                        <label class="" for="investorVisaMobile">
                                            <spring:theme code="portal.sector.contact.expert.phoneNumber.label"/>
                                            <span class="mandatory">* </span>
                                        </label>
                                        <div class="error-msg"></div>
                                        
                                    </div>
                                </div>
                               
                            </div>
                        </div>
                  
                    <span class="field-validation-valid" data-valmsg-for="Subject"
                          data-valmsg-replace="true"></span>
                        
                    <div class=" col-md-12 ">
                        <div class="form-group">
                       
                        <textarea class="form-control required" cols="10" data-val="true"
                                  data-val-length="Name length should be between 2 &amp; 1000" data-val-length-max="1000"
                                  data-val-length-min="2" data-val-required="Required" id="investorVisaMessage" name="Enquiry"
                                  placeholder="${messageLabel}" rows="5" style="padding: 20px; padding-left: 20px; padding-top: 15px; margin-top:35px">
                         </textarea>
                          <label class="" for="crJobTitle">
                                                    <spring:theme code="portal.sector.contact.expert.enquiry.label"/>
                                                    <span class="mandatory">* </span>
                                                </label>
                           <div class="error-msg"></div>
                        </div>
                    </div>
               

                        <div class="col-md-12 ">
                            <div class="form-group captchaWrapper" style="display: flex;  align-items: center; justify-content: center; margin-top: 20px;">
                            <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
                               <div class="form_field-elements control-group js-recaptcha-captchaaddon sector-page-captcha"></div>
                                  <span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
                                  </div>
                         </div>
                        </div>
                    <div class="row form-action-btn" style="padding-left: 15px; padding-right: 15px;justify-content: center;margin-top: 40px;">
                       <div class="col-md-4">
                                <button type="button" id="contact-clear" class="btn btn-primary-fill btn-form-outline w-100">
                                    <spring:theme code="portal.sector.download.clear.button"/></button></div>
                            <div class="col-md-4"><button id="btn-investor-visa-contact" type="submit" class="responsive-contact-btn btn btn-primary-fill btn-form-fill  w-100" onclick="onInvestorVisaContactload()">
                                    <spring:theme code="portal.sector.download.submit.button"/></button></div>
                                </div>
                         
                        </div>
                    </div>
                ${successMessage}
            </div>
            </div>
          </div>
       
      </div>
      </section>


<script src="/_ui/responsive/common/js/wow.js"></script>


<script>
    // Floating label

$('input,textarea').val("");
$('.form-group input, .form-group textarea').focusout(function() {
  var text_val = $(this).val();
  if (text_val === "") {
	console.log("empty!");
	$(this).removeClass('has-value');
  } else {
	$(this).addClass('has-value');
  }
});

new WOW().init();
</script>











      
</c:if>
