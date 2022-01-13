<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

            <!-- Contact form section  -->
            <section id="contactpage-form" class="contactpage-form">
              <div class="container">
                <div class="row">
                  <ul class="nav nav-pills mb-3 m-auto contactpage-form-tab" id="pills-tab" role="tablist">
                    <li class="nav-item">
                      <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home1"
                        role="tab" aria-controls="pills-home" aria-selected="true">
                        <svg xmlns="http://www.w3.org/2000/svg" width="45.589" height="45.599"
                          viewBox="0 0 45.589 45.599">
                          <path id="Icon_awesome-wpforms" data-name="Icon awesome-wpforms"
                            d="M44.589,6.55v36a4.251,4.251,0,0,1-4.3,4.3H4.3A4.265,4.265,0,0,1,0,42.539V6.55a4.251,4.251,0,0,1,4.3-4.3h36A4.248,4.248,0,0,1,44.589,6.55ZM40.876,42.539V6.55a.591.591,0,0,0-.577-.577h-.926L28.4,13.4l-6.1-4.966L16.2,13.4,5.225,5.962H4.3a.591.591,0,0,0-.577.577v36a.591.591,0,0,0,.577.577h36a.578.578,0,0,0,.577-.577ZM14.949,17.577V21.26H7.634V17.577Zm0,7.4v3.712H7.634V24.982Zm1.1-14.661,5.375-4.349H9.634l6.42,4.349Zm20.9,7.256V21.26H17.447V17.577Zm0,7.4v3.712H17.447V24.982Zm-8.42-14.661,6.42-4.349H23.17l5.365,4.349Zm8.42,22.085v3.712H27.062V32.407Z"
                            transform="translate(0.5 -1.75)" fill="none" stroke="#bf9b2e" stroke-width="1" />
                        </svg>
                        <!-- <span>Contact Form</span>  -->
                        <span>
                          <spring:theme code="portal.contact.us.contact.misa" />
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile2"
                        role="tab" aria-controls="pills-profile" aria-selected="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="55.487" height="45.599"
                          viewBox="0 0 55.487 43.604">
                          <g id="Group_422" data-name="Group 422" transform="translate(-696.757 -1063.5)">
                            <path id="Icon_zocial-email" data-name="Icon zocial-email"
                              d="M.072,26.564V6.254q0-.035.106-.67L12.131,15.81.213,27.269a2.988,2.988,0,0,1-.141-.705ZM1.659,4.174a1.52,1.52,0,0,1,.6-.106H34.451a2,2,0,0,1,.635.106L23.1,14.435,21.51,15.7l-3.138,2.574L15.234,15.7l-1.587-1.269Zm.035,24.471,12.024-11.53,4.654,3.773,4.654-3.773L35.05,28.644a1.693,1.693,0,0,1-.6.106H2.258a1.6,1.6,0,0,1-.564-.106ZM24.613,15.81,36.531,5.584a2.1,2.1,0,0,1,.106.67v20.31a2.7,2.7,0,0,1-.106.705Z"
                              transform="translate(715.106 1059.932)" fill="none" stroke="#bf9b2e"
                              stroke-width="1" />
                            <path id="Icon_ionic-md-call" data-name="Icon ionic-md-call"
                              d="M38.352,29.391A23.371,23.371,0,0,1,31.283,28.3a1.863,1.863,0,0,0-1.991.5l-4.381,4.38A29.944,29.944,0,0,1,11.768,20.032l4.381-4.38a2.075,2.075,0,0,0,.5-1.991,21.656,21.656,0,0,1-1.195-7.169A2,2,0,0,0,13.461,4.5H6.491A2,2,0,0,0,4.5,6.491,33.824,33.824,0,0,0,38.352,40.343a2,2,0,0,0,1.991-1.991v-6.97A2,2,0,0,0,38.352,29.391Z"
                              transform="translate(692.757 1066.261)" fill="none" stroke="#bf9b2e"
                              stroke-width="1" />
                          </g>
                        </svg>
                        <span>
                          <spring:theme code="portal.contact.us.email.call" text="Email & Call" />
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact3"
                        role="tab" aria-controls="pills-contact" aria-selected="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24.494" height="45.599"
                          viewBox="0 0 24.494 36.241">
                          <path id="Icon_ionic-md-help" data-name="Icon ionic-md-help"
                            d="M23.775,39.741H17.718V33.775h6.057Zm-.092-8.994H17.81c0-9.269,8.81-8.728,8.81-14.592a5.844,5.844,0,0,0-5.873-5.818,6,6,0,0,0-5.873,5.91H9a11.747,11.747,0,0,1,23.494-.092C32.494,23.488,23.684,24.323,23.684,30.747Z"
                            transform="translate(-8.5 -4)" fill="none" stroke="#bf9b2e" stroke-width="1" />
                        </svg>
                        <span>
                          <spring:theme code="portal.contact.us.faqs" text="FAQs" />
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" id="pills-appt-tab" href="https://investsaudi.sa/appt"
                        target="_blank" role="tab" aria-controls="pills-home" aria-selected="true">
                        <svg xmlns="http://www.w3.org/2000/svg" width="45.589" height="45.599"
                          viewBox="0 0 45.589 45.599">
                          <path id="Icon_awesome-wpforms" data-name="Icon awesome-wpforms"
                            d="M44.589,6.55v36a4.251,4.251,0,0,1-4.3,4.3H4.3A4.265,4.265,0,0,1,0,42.539V6.55a4.251,4.251,0,0,1,4.3-4.3h36A4.248,4.248,0,0,1,44.589,6.55ZM40.876,42.539V6.55a.591.591,0,0,0-.577-.577h-.926L28.4,13.4l-6.1-4.966L16.2,13.4,5.225,5.962H4.3a.591.591,0,0,0-.577.577v36a.591.591,0,0,0,.577.577h36a.578.578,0,0,0,.577-.577ZM14.949,17.577V21.26H7.634V17.577Zm0,7.4v3.712H7.634V24.982Zm1.1-14.661,5.375-4.349H9.634l6.42,4.349Zm20.9,7.256V21.26H17.447V17.577Zm0,7.4v3.712H17.447V24.982Zm-8.42-14.661,6.42-4.349H23.17l5.365,4.349Zm8.42,22.085v3.712H27.062V32.407Z"
                            transform="translate(0.5 -1.75)" fill="none" stroke="#bf9b2e" stroke-width="1" />
                        </svg>
                        <!-- <span>Contact Form Contact MISA</span>  -->
                        <span>
                          <spring:theme code="portal.contact.us.appointment" />
                        </span>
                      </a>
                    </li>
                  </ul>
                  <div class="tab-content w-100" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-home1" role="tabpanel"
                      aria-labelledby="pills-home-tab">
                      <%-- 
                      <h3 class="col-md-8 m-auto pb-5" dir="ltr">
                        <spring:theme code="portal.contact.us.contact.misa.link" />
                      </h3>
                      --%>
                   
                       <h3 class="col-md-8 m-auto pb-5"><spring:theme code="portal.contactus.form.text"/></h3>
                            <form:form class="contact-form pt-3" id="contact-us-page-contact-us-form" name="contact-us-form" action = "${encodedContextPath}/about/contactUs" modelAttribute="contactUsFormData" enctype="multipart/form-data" onsubmit="return validateFormContactUs()">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                              <div class="form-row">
                                <div class="form-group col-md-6 form-normal-item">
                                    <label class="control-label" for="firstName"><spring:theme code="portal.contactus.form.firstname.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="firstName" id="firstName" maxlength="300" onkeypress="return onlyAlphabets(event)" required/>
                                </div>
                                <div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="lastName"><spring:theme code="portal.contactus.form.lastname.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="lastName" id="lastName" maxlength="300" onkeypress="return onlyAlphabets(event)" required/>
                                </div>
                                <div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="phoneNumber"><spring:theme code="portal.contactus.form.phoneno.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" maxlength="20" onkeypress="return isNumber(event)" required/>
                                </div>
                                <div class="form-group form-floating col-md-6 form-normal-item">
                                    <label class="control-label" for="email"><spring:theme code="portal.contactus.form.email.label"/> <span class="mandatory">* </span></label>
                                    <input type="text" class="form-control" name="email" id="email" maxlength="500" onblur="return validateEmailReg(event)" required/>
                                    <span id="lblError" class="mandatory"></span>
                                </div>
                                                                                  
                                <div class="form-group col-md-6 form-normal-item-select">
                                    <label class="control-label" for="selectedEnquiryType"><spring:theme code="portal.contactus.form.enquirytype.label"/><span class="mandatory">* </span></label>
                                    <form:select path="selectedEnquiryType" class="form-control required error contact-us-enquirytype" onchange="OnChangeEnquiryType()" aria-required="true" required="true">
                                        <option value=""><spring:theme code="portal.contactus.form.enquirytype.label"/></option>                       	
                                        <form:options items="${contactUsFormData.enquiryTypes}" itemValue="catID" itemLabel="catDesc" htmlEscape="true" id="enquiryList" />                    	
                                    </form:select>
                                  <i class="caret"></i>
                                </div>     
                                <div class="form-group col-md-6 form-normal-item-select">
                                  <label class="control-label" for="selectedCategoryOne"><spring:theme code="portal.contactus.form.category.label"/><span class="mandatory">* </span></label>
                                  <form:select path="selectedCategoryOne" class="form-control required error contact-us-enquiry-category" aria-required="true" required="true">
                                      <option value=""><spring:theme code="portal.contactus.form.category.label"/></option>
                                      <form:options items="${contactUsFormData.categoryOne}" itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                  </form:select>
                                  <i class="caret"></i>
                                </div>     
                                 
					 
                          <%-- <form:select path="categoryTwo">
                            <option value="0"><spring:theme code="complaints.category2"/></option>
                            <form:options items="${contactUsFormData.categoryTwo}" itemValue="catDesc" itemLabel="catDesc" htmlEscape="true" />
                          </form:select> --%>
			  
			 			 
                          <br />             							  
                                <div class="form-group col-md-12 form-normal-item-textarea">
                                  <label class="control-label" for="message"><spring:theme code="portal.contactus.form.your.message.label"/></label>
                                  <textarea class="form-control" id="message" name="message" style="height: 190px" ></textarea>
                                </div>
                                <div class="form-group col-md-12">
                                  <div class="custom-file mb-3">
                                    <input type="file" class="custom-file-input" id="contactfile" name="contactfile">
                                    <!-- <label class="custom-file-label" for="contactfile"><spring:theme code="portal.contactus.form.drag.file.label"/>  -->
                                      <label class="custom-file-label" for="contactfile" id="contact-us-upload-file"><spring:theme code="portal.contactus.form.drag.file.label"/> 
                                    <span class="pvcy-policy"><spring:theme code="portal.contactus.form.browse.label"/></span></label>
                                  </div>
                                </div>
                                <div class="form-group col-md-12">
                                  <label for="floatingInput " class="mand-field-text"><span class="mandatory">* </span>
                                  <spring:theme code="portal.contactus.form.fill.fields.label"/></label>
                                  <div class="form-check p-0 d-flex align-items-center mb-3">
                                  
                                    <div class="invalid-feedback"><spring:theme code="portal.contactus.form.agree.submit.label"/></div>
                                  </div>
                                  <div class="form-check p-0 d-flex align-items-center mb-3">
                                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                                    <label class="form-check-label mand-field-text" for="invalidCheck">
                                      <spring:theme code="portal.contactus.form.terms.service.label"/> 
                                      <a class="pvcy-policy" href="/${language}/privacy-policy"><spring:theme code="portal.contactus.form.privacy.policy.label"/></a>
                                    </label>
                                    <div class="invalid-feedback">
                                      <spring:theme code="portal.contactus.form.agree.submit.label"/>
                                    </div>
                                  </div>
                                </div>
                                
                                <div class="form-group col-md-12">
                                  <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
		                              <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
                                  <span id="lblErrorCaptcha" class="mandatory"></span>
                                </div>
                              </div>
                              <div class="row form-action-btn">
                                <div class="col-md-3"><button type="submit" class="btn btn-primary-fill btn-form-outline w-100" id="contact-us-form-cancel"><spring:theme code="portal.contactus.form.cancel.button"/></button></div>
                                <div class="col-md-3"><button type="submit" class="btn btn-primary-fill btn-form-fill  w-100"><spring:theme code="portal.contactus.form.submit.button"/></button></div>
                              </div>              
                              <div class="formSuccess d-none">
                          <p class="font-bold"><spring:theme code="portal.contactus.form.thank.message.label"/></p>
                          <p><spring:theme code="portal.contactus.form.receive.enquiry.label"/></p>
                      </div>   
                      <input type="hidden" id="CRMResponse" value="${CRMResponse}"/>
                            <input type="hidden" id="CRMObjectId" value="${CRMObjectId}"/>                        
                            </form:form>             
                            <div id="contact-us-form-success" class="d-none" dir="ltr">
                              <p>We received your enquiry, and we will get back to you shortly.</p>
                              <p class="contact-us-form-ticket"></p>                            
                            </div>
                    </div>


                    <div class="tab-pane fade" id="pills-profile2" role="tabpanel"
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