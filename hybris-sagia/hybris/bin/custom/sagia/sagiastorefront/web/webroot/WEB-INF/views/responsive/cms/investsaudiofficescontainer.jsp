<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

            <!-- Contact form section  -->
            <section id="contactpage-form" class="contactpage-form">
              <div class="container">
                <div class="row">

                  <div class="tab-content w-100" id="pills-tabContent">
                    <div class="" id="pills-home1" role="tabpanel" aria-labelledby="pills-home-tab">                   
                       
                            <form:form class="contact-form pt-3" id="contact-us-page-contact-us-form" name="contact-us-form" action = "${encodedContextPath}/about/contactUs" modelAttribute="contactUsFormData" enctype="multipart/form-data" onsubmit="return validateFormContactUs()">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                              <div>
                                <div class="col-sm-12"><h2 class="contact-form-title mb-4"><spring:theme code="portal.contactus.form.text"/></h2></div>
                                <div class="form-group col-md-6 form-normal-item">
                                    <label class="control-label" for="contactUsName"><spring:theme code="portal.contactus.form.name.label"/></label>
                                    <input type="text" class="form-control" name="contactUsName" id="contactUsName" maxlength="300" onkeypress="return onlyAlphabetsWithSpace(event)" onCopy="return false" onpaste="return false;"/>
                                    <em><span id="lblErrorContactUsName" class="error-msg"></span></em>
                                </div>
                                 <!--<div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="lastName"><spring:theme code="portal.contactus.form.lastname.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="lastName" id="lastName" maxlength="300" onkeypress="return onlyAlphabets(event)" onCopy="return false" onpaste="return false;"/>
                                    <em><span id="lblErrorlastName" class="error-msg"></span></em>
                                </div> -->
                                <!-- <div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="phoneNumber"><spring:theme code="portal.contactus.form.phoneno.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" maxlength="20" onkeypress="return isNumber(event)" required/>
                                </div> -->
                                <div class="form-group form-floating col-md-6 form-normal-item country-code-mobile">
                                  <label class="control-label mob-control-label" for="phoneNumber"><spring:theme code="portal.contactus.form.phoneno.label"/></label>
                                  <div>
                                    <input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">
                                    <div class="input-wrapper">
                                      <input type="text"  class="form-control r phonenumber" id="phoneNumber" name="phoneNumber"  type="number" maxlength="20" onkeypress="return isNumber(event)" onCopy="return false" onpaste="return false;"/>
                                      <em><span id="lblErrorPhoneNumber" class="error-msg"></span></em>
                                    </div>   
                                  </div> 
                                </div>
                                <div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="email"><spring:theme code="portal.contactus.form.email.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="email" id="email" maxlength="500" onblur="return validateEmailReg(event)" />
                                    <em><span id="lblError" class="error-msg"></span></em>
                                </div>
                                                                                  
                                 <%--<div class="form-group col-md-6 form-normal-item-select">
                                    <label class="control-label" for="selectedEnquiryType"><spring:theme code="portal.contactus.form.enquirytype.label"/><span class="mandatory">* </span></label>
                                    <form:select path="selectedEnquiryType" id="selectedEnquiryType" class="form-control required error contact-us-enquirytype" onchange="OnChangeEnquiryType()" aria-required="true" >
                                        <option value=""><spring:theme code="portal.contactus.form.enquirytype.label"/></option>                       	
                                        <form:options items="${contactUsFormData.enquiryTypes}" itemValue="catID" itemLabel="catDesc" htmlEscape="true" id="enquiryList" />                    	
                                    </form:select>
                                  <i class="caret"></i>
                                  <em><span id="lblErrorselectedEnquiryType" class="error-msg"></span></em>
                                </div>     
                                <div class="form-group col-md-6 form-normal-item-select">
                                  <label class="control-label" for="selectedCategoryOne"><spring:theme code="portal.contactus.form.category.label"/><span class="mandatory">* </span></label>
                                  <form:select path="selectedCategoryOne" id="selectedCategoryOne" class="form-control required error contact-us-enquiry-category" aria-required="true" >
                                      <option value=""><spring:theme code="portal.contactus.form.category.label"/></option>
                                      <form:options items="${contactUsFormData.categoryOne}" itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                  </form:select>
                                  <i class="caret"></i>
                                  <em><span id="lblErrorselectedCategoryOne" class="error-msg"></span></em>
                                </div>     --%>
                                 
					 
                          <%-- <form:select path="categoryTwo">
                            <option value="0"><spring:theme code="complaints.category2"/></option>
                            <form:options items="${contactUsFormData.categoryTwo}" itemValue="catDesc" itemLabel="catDesc" htmlEscape="true" />
                          </form:select> --%>
			  
			 			 
                          <br />             							  
                                <div class="form-group col-md-12 form-normal-item-textarea">
                                  <label class="control-label" for="message"><spring:theme code="portal.contactus.form.your.message.label"/><span class="mandatory">* </span></label>
                                  <textarea class="form-control" id="message" name="message" style="height: 190px" ></textarea>
                                  <em><span id="lblErrorMessage" class="error-msg"></span></em>
                                </div>
                                <div class="form-group col-md-6">
                                  <div class="custom-file mb-3">
                                    <input type="file" class="custom-file-input" id="contactfile" name="contactfile">
                                    <!-- <label class="custom-file-label" for="contactfile"><spring:theme code="portal.contactus.form.drag.file.label"/>  -->
                                      <label class="custom-file-label" for="contactfile" id="contact-us-upload-file"><!--<spring:theme code="portal.contactus.form.drag.file.label"/> -->
                                    <span class="pvcy-policy"><spring:theme code="portal.contactus.form.browse.label"/></span></label>
                                  <div class="form-icon form-icon_browse" style="">
                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                      <path d="M19.0104 12.5466L13.3536 18.2034C10.5257 21.0312 7.6967 19.6176 6.63604 18.557C5.57538 17.4963 4.15918 14.6699 6.98959 11.8395C6.98959 11.8395 13.1995 5.6296 14.0607 4.7684C15.1213 3.70774 17.2426 3.70774 18.3033 4.7684C19.364 5.82906 19.364 7.95038 18.3033 9.01104L12.6464 14.6679C11.2322 16.0821 10.1716 15.0214 10.1716 15.0214C10.1716 15.0214 9.11091 13.9608 10.5251 12.5466L14.7678 8.30393" stroke="#BF9B2E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                      </svg>                                      
                                  </div>

                                  <div class="form-icon form-icon_reset js-inputFile-reset" style="display:none">
                                  <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">
                                      <path d="M9,17.5c-4.687,0-8.5-3.813-8.5-8.5c0-4.687,3.813-8.5,8.5-8.5c4.687,0,8.5,3.813,8.5,8.5C17.5,13.687,13.687,17.5,9,17.5z M9,1.5C4.864,1.5,1.5,4.864,1.5,9s3.364,7.5,7.5,7.5s7.5-3.364,7.5-7.5S13.136,1.5,9,1.5z M11.813,12.812c-0.268,0-0.519-0.104-0.707-0.292l-1.949-1.949l-1.95,1.949c-0.378,0.377-1.037,0.377-1.414,0c-0.189-0.188-0.293-0.439-0.293-0.707s0.104-0.519,0.293-0.707l1.949-1.949l-1.95-1.95c-0.39-0.39-0.39-1.024,0-1.414c0.377-0.377,1.037-0.377,1.414,0l1.95,1.95l1.949-1.95c0.377-0.377,1.039-0.377,1.414,0c0.389,0.39,0.389,1.023,0,1.413l-1.95,1.95l1.95,1.949c0.39,0.392,0.389,1.025,0,1.414C12.332,12.709,12.081,12.812,11.813,12.812z"></path>
                                  </svg>
                                  </div>
                                  </div>
                                </div>
							   </div>
                                <%--<div class="form-group col-md-12">
                                  <!-- <label for="floatingInput " class="mand-field-text"><span class="mandatory">* </span>
                                  <spring:theme code="portal.contactus.form.fill.fields.label"/></label> -->
                                  <div class="form-check p-0 d-flex align-items-center mb-3">
                                  
                                    <div class="invalid-feedback"><spring:theme code="portal.contactus.form.agree.submit.label"/></div>
                                  </div>
                                  <div class="form-check p-0 d-flex align-items-center mb-3">
                                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck">
                                    <label class="form-check-label mand-field-text" for="invalidCheck">
                                      <spring:theme code="portal.contactus.form.terms.service.label"/> 
                                      <a class="pvcy-policy" href="/${language}/privacy-policy"><spring:theme code="portal.contactus.form.privacy.policy.label"/></a>
                                    </label>
                                    <div class="invalid-feedback">
                                      <spring:theme code="portal.contactus.form.agree.submit.label"/>
                                    </div>
                                  </div>
                                  <em><span id="lblErrorinvalidCheck" class="error-msg"></span></em>
                                </div>--%>
                                
                                <div class="form-group col-md-12">
                                  <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
		                              <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
                                  <em><span id="lblErrorCaptcha" class="error-msg"></span></em>
                                </div>

                                                           <!--<div class="col-md-3"><button type="submit" class="btn btn-primary-fill btn-form-outline w-100" id="contact-us-form-cancel"><spring:theme code="portal.contactus.form.cancel.button"/></button></div>-->
                                                           <div class="col-sm-12"><button type="submit" class="btn btn-primary-fill btn-form-fill w-100 submit-button"><spring:theme code="portal.contactus.form.submit.button"/>
                                                            <svg width="23" height="22" viewBox="0 0 23 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                              <path d="M5.08331 11H17M12.4166 5.5L17.2685 10.3518C17.6264 10.7098 17.6264 11.2902 17.2685 11.6482L12.4166 16.5" stroke="#F8F9FB" stroke-width="1.5" stroke-linecap="round"/>
                                                              </svg>
                                                          </button></div>
                              </div>          
                              <div class="formSuccess d-none">
                          <p class="font-bold"><spring:theme code="portal.contactus.form.thank.message.label"/></p>
                          <p><spring:theme code="portal.contactus.form.receive.enquiry.label"/></p>
                      </div>   
                      <input type="hidden" id="CRMResponse" value="${CRMResponse}"/>
                            <input type="hidden" id="CRMObjectId" value="${CRMObjectId}"/>                        
                            </form:form>             
                            <!-- <div id="contact-us-form-success" class="d-none" dir="ltr">
                              <p>We received your enquiry, and we will get back to you shortly.</p>
                              <p class="contact-us-form-ticket"></p>                            
                            </div> -->
                    </div>


                    <%--<div class="tab-pane fade" id="pills-profile2" role="tabpanel"
                      aria-labelledby="pills-profile-tab">
                      <div class="email-call">
                        <h2>
                          <spring:theme code="portal.contact.us.contact.us.today" text="CONTACT US TODAY" />
                        </h2>
                        <h3 class="col-md-8 m-auto">
                          <spring:theme code="portal.contact.us.contact.us.today.description1"
                            text="INVEST SAUDI is ready to assist you 24/7 ." /><br />
                          <spring:theme code="portal.contact.us.contact.us.today.description2"
                            text="We are happy to answer your questions in the following languages ( English, German, Japanese, French, Spanish, Korean , and Chinese)" />
                        </h3>
                        <div class="col-md-6 m-auto py-4">
                          <div class="row">
                            <div class="col-md-6">
                              <div class="contact-num">
                                <img src="${commonResourcePath}/images/Contact-us/local.png" alt="local number"
                                  class="img-fluid" />
                                <div class="contact-text">

                                  <h3 class="cntct-number">
                                    <spring:theme code="portal.contact.us.local.number" text="Local: 8002449990" />
                                  </h3>
                                </div>
                              </div>
                            </div>
                            <div class="col-md-6">
                              <div class="contact-num">
                                <img src="${commonResourcePath}/images/Contact-us/International.png"
                                  alt="local number" class="img-fluid" />
                                <div class="contact-text">

                                  <h3 class="cntct-number">
                                    <spring:theme code="portal.contact.us.international.number"
                                      text="International: 00966112035777" />
                                  </h3>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="seperator"></div>
                      <div class="email-call">
                        <h2>
                          <spring:theme code="portal.contact.us.email.us" text="email us !" />
                        </h2>
                        <h3 class="col-md-8 m-auto">
                          <spring:theme code="portal.contact.us.description"
                            text="You can also mail us and will be pleased to assist you. We are available for you 24 hrs, 7 days a week" />
                        </h3>
                        <div class="col-md-4 m-auto py-4">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="contact-num">
                                <!-- <img src="${commonResourcePath}/images/Contact-us/local.png" alt="local number" class="img-fluid"/> -->
                                <div class="contact-text">
                                  <h3 class="cntct-number mt-2">
                                    <spring:theme code="portal.contact.us.mail.id"
                                      text="InvestorCare@misa.gov.sa" />
                                  </h3>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="tab-pane fade" id="pills-contact3" role="tabpanel"
                      aria-labelledby="pills-contact-tab">
                      <div id="accordion" class="contact-accordion">
                        <div class="card">
                          <div class="card-header" id="headingOne">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <spring:theme code="portal.contact.us.faqs.question1"
                                  text="What are the requirements for obtaining an investment license?" />
                              </button>
                            </h5>
                          </div>

                          <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer1"
                                text="The procedure for obtaining a license is determined by the type of activity in which you wish to invest. The Investment Manual for your chosen investment activity can be found in following link." />
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header" id="headingTwo">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <spring:theme code="portal.contact.us.faqs.question2"
                                  text="How many years of operational experience does a foreign company need to have to be able to obtain an investment license?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                            data-parent="#accordion">
                            <div class="card-body">
                              <div class="ml-3">
                                <spring:theme code="portal.contact.us.faqs.answer2"
                                  text="To apply for an investment license, a foreign company must have at least one year of experience in the same business field." />

                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header" id="headingThree">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <spring:theme code="portal.contact.us.faqs.question3"
                                  text="Is foreign investment permitted in all activities?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseThree" class="collapse" aria-labelledby="headingThree"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer3.description"
                                text="All activities are permitted for foreign investment except the following activities:" />
                              <br />
                              <spring:theme code="portal.contact.us.faqs.answer3.1"
                                text="- Subsistence insurance services for the military sectors" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer3.2"
                                text="- Intelligence services and security" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer3.3"
                                text="- Real estate investments in Makkah and Medina" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer3.4"
                                text="- Tourism guide services related to Hajj" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer3.5"
                                text="- Civil employment services" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer3.6"
                                text="- Commercial agents with commission internationally classified as No. (621)" />
                              <br />
                              <spring:theme code="portal.contact.us.faqs.answer3.7"
                                text="- Fishing for living aquatic resources" />

                            </div>
                          </div>
                        </div>

                        <div class="card">
                          <div class="card-header" id="headingFour">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                <spring:theme code="portal.contact.us.faqs.question4"
                                  text="What legal entities can be established and obtain an investment license?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseFour" class="collapse" aria-labelledby="headingFour"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer4.1"
                                text="ï¿½  Limited liability company" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer4.2"
                                text="ï¿½  One-person company with limited liability" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer4.3"
                                text="ï¿½  A joint stock company" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer4.4"
                                text="ï¿½  Branch of a foreign company" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer4.5"
                                text="ï¿½  A joint venture company" /><br />

                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header" id="headingFive">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                <spring:theme code="portal.contact.us.faqs.question5"
                                  text="How do I apply for a visa to visit Saudi Arabia?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseFive" class="collapse" aria-labelledby="headingFive"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer5"
                                text="If you are owner of a stake in a licensed establishment, or hold the position of general manager, or have a special iqama, you can apply for a visit visa to enter Saudi Arabia through MISA's online portal or through the Ministry of Foreign Affairs' website at the following link." />
                            </div>
                          </div>
                        </div>
                        <div class="card">
                          <div class="card-header" id="headingSix">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                <spring:theme code="portal.contact.us.faqs.question6"
                                  text="Can an investor living within the Kingdom obtain a license?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseSix" class="collapse" aria-labelledby="headingSix"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer6"
                                text="By obtaining a letter of support from one of the authorized incubators or investment funds, an investor resident in the Kingdom can apply for an investment license." />
                            </div>
                          </div>
                        </div>

                        <div class="card">
                          <div class="card-header" id="headingSeven">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                <spring:theme code="portal.contact.us.faqs.question7"
                                  text="How do I get access to other Saudi government agencies' services?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer7"
                                text="To apply for government services the investor can use the company's MISA digital account or contact a MISA employee. To do so, make an appointment with a representative of the relevant government agency through MISA's reservation service and meet with them in MISA's business center to process your service inquiry." />
                            </div>
                          </div>
                        </div>

                        <div class="card">
                          <div class="card-header" id="headingEight">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                                <spring:theme code="portal.contact.us.faqs.question8"
                                  text="Do investors have access to specialized centers?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseEight" class="collapse" aria-labelledby="headingEight"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer8"
                                text="Yes, the Ministry of Investment of Saudi Arabia (MISA) has numerous branches to assist investors in Riyadh, Jeddah, Dammam, Medina ." />
                            </div>
                          </div>
                        </div>

                        <div class="card">
                          <div class="card-header" id="headingNine">
                            <h5 class="mb-0">
                              <button class="btn btn-link collapsed" data-toggle="collapse"
                                data-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
                                <spring:theme code="portal.contact.us.faqs.question9"
                                  text="What is the best way for me to contact the Ministry of Investment of Saudi Arabia (MISA)?" />
                              </button>
                            </h5>
                          </div>
                          <div id="collapseNine" class="collapse" aria-labelledby="headingNine"
                            data-parent="#accordion">
                            <div class="card-body">
                              <spring:theme code="portal.contact.us.faqs.answer9.inside.kingdom.number"
                                text="8002449990 (from inside the kingdom)" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer9.outside.kingdom.number"
                                text="+966112035777 (from outside the kingdom)" /><br />
                              <spring:theme code="portal.contact.us.faqs.answer9.email"
                                text="email :  InvestorCare@misa.gov.sa" />
                              <spring:theme code="portal.contact.us.faqs.answer9.twitter ="
                                text="or follow us on Twitter: @SAGIACARE" />
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>--%>
                  </div>
                </div>
              </div>
              <div id="contactusformModal" class="modal fade bootstrap-modal" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-body">
                      <p>We received your enquiry, and we will get back to you shortly.</p>
                      <p class="contact-us-form-ticket"></p>    
                    </div>
                    <div class="modal-body-footer text-center" style="padding: 0 20px 20px 20px;">
                      <button type="button" id="btnContactModalClose" class="btn btn-sagia" data-dismiss="modal">Close</button>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <!-- *End of Contact form section  -->

            <!-- Old code  -->
            <!-- <div class="container">
<div class="row">
    <h2 class="col-lg-12 col-md-12 col-sm-12 mb-4">Saudi Offices</h2>
    <c:forEach var="currentComponent" items="${components}">
        <cms:component component="${currentComponent}" element="div" class="col-lg-6 col-md-6 col-sm-12 d-flex alignment-stretch"/>
    </c:forEach>
</div>
</div> -->