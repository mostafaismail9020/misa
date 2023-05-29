<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<template:portalpage pageTitle="${pageTitle}">
    <jsp:body>
	<!--
	<script>
    document.querySelector("html").style.cssText = 'background:#000!important';
    document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
    window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
    window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
    window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
    (function(s,e,ss,i,o,n){
    if(s.console && s.console.log) { s.console.log(i);};
    o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
    n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
    })(window,document,'script','SessionForward Loaded.');
    </script>
	-->

        <header:portalPageTitle />
               
        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>
            <cms:pageSlot position="PortalPageMain" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>
        
        
        
                                     <section class="rhq-contact-us">
                                <div class="container-fluid p-0">
                                    <div class="row position-relative">
                                    <div class="col-md-12 p-0 mb-5 contactus-content">
                                        
                                        <h5 class="position-absolute"><spring:theme code="portal.regional.hq.contactus.main.header"/></h5>
                                    </div>  
                                    </div>  
                                </div>
                            </section>
                            <section class="RHQ_contact_bg">
                                <div class="container">
                                    <div class="row position-relative">
                                    <div class="m-auto pb-5">
                                        <ul class="nav nav-pills  m-auto RHQ_contactpage-form-tab" id="pills-tab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home1" role="tab" aria-controls="pills-home" aria-selected="true">
                                                    <span><spring:theme code="portal.regional.hq.contactus.label"/></span>
                                                </a>
                                            </li> 
                                            <li class="nav-item">
                                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact3" role="tab" aria-controls="pills-contact" aria-selected="false">
                                                    <span><spring:theme code="portal.regional.hq.faqs.label"/></span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="tab-content w-100" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-home1" role="tabpanel" aria-labelledby="pills-home-tab">
                                            <div class="rhq-contant">
                                                <div class="email-call">
                                                    <h2><span><spring:theme code="portal.rigional.hq.contact.us.today"/></span></h2>
                                                    <div class="col-md-8 m-auto">
                                                        <h3><spring:theme code="portal.regional.hq.contact.us.today.description1"/><br>
                                                            <spring:theme code="portal.regional.hq.contact.us.today.description2"/></h3>
                                                    </div>
                                                    <div class="col-md-6 m-auto py-4">
                                                      <div class="row">
                                                        <div class="col-md-6">
                                                          <div class="contact-num">
                                                            <img src="${commonResourcePath}/images/Contact-us/local.png" alt="local number" class="img-fluid">
                                                            <div class="contact-text">
                                                              <h3><spring:theme code="portal.regional.hq.local.number"/></h3>
                                                              <h3 class="cntct-number">8002449990</h3>
                                                            </div>
                                                          </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                          <div class="contact-num">
                                                            <img src="${commonResourcePath}/images/Contact-us/International.png" alt="local number" class="img-fluid">
                                                            <div class="contact-text">
                                                              <h3><spring:theme code="portal.regional.hq.international.number"/></h3>
                                                              <h3 class="cntct-number">00966112035777</h3>
                                                            </div>
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                  </div>
                                                  <div class="seperator"></div>
                                                  <div class="email-call">
                                                    <h2><spring:theme code="portal.regional.hq.email.us"/></h2>
                                                    <h3 class="col-md-8 m-auto"><spring:theme code="portal.regional.hq..email.description"/></h3>
                                                    <div class="col-md-4 m-auto py-4">
                                                      <div class="row">
                                                        <div class="col-md-12">
                                                          <div class="contact-num">
                                                            <!-- <img src="/sagiastorefront/_ui/responsive/common/images/Contact-us/local.png" alt="local number" class="img-fluid"/> -->
                                                            <div class="contact-text">
                                                              <h3 class="cntct-number mt-2"><a href="mailto:Outreach-RHQ@misa.gov.sa"><spring:theme code="portal.regional.hq.mail.id"/></a></h3>
                                                            </div>
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>
                                                  </div>
                                            </div>
                                            
                                           
                                            <!-- <div class="col-md-6 m-auto pb-5 contact-us-padding-top8rem">  
                                                <form:form class="contact-form pt-3" action = "${encodedContextPath}/about/contactUs" modelAttribute="contactUsForm" enctype="multipart/form-data">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                                                <div class="form-row">
                                                    <div class="form-group col-md-6 form-normal-item">
                                                        <label class="control-label" for="firstName">First Name <span class="mandatory">* </span></label>
                                                        <input type="text" class="form-control" name="firstName" id="firstName"/>
                                                    </div>
                                                    <div class="form-group form-floating col-md-6 form-normal-item">
                                                        <label class="control-label" for="lastName">Last Name <span class="mandatory">* </span></label>
                                                        <input type="text" class="form-control" name="lastName" id="lastName"/>
                                                    </div>
                                                    <div class="form-group form-floating col-md-6 form-normal-item">
                                                        <label class="control-label" for="phoneNumber">Phone No <span class="mandatory">* </span></label>
                                                        <input type="text" class="form-control" name="phoneNumber" id="phoneNumber"/>
                                                    </div>
                                                    <div class="form-group form-floating col-md-6 form-normal-item">
                                                        <label class="control-label" for="email">email <span class="mandatory">* </span></label>
                                                        <input type="text" class="form-control" name="email" id="email"/>
                                                    </div>                
                                                    <div class="form-group col-md-12 form-normal-item-textarea">
                                                    <label class="control-label" for="message">Your message (500 words)</label>
                                                    <textarea class="form-control" id="message" name="message" style="height: 190px" ></textarea>
                                                    </div> 
                                                    <div class="form-group col-md-12">
                                                    <label for="floatingInput " class="mand-field-text"><span class="mandatory">* </span>Please fill all the mandatory fields</label>
                                                    <div class="form-check p-0 d-flex align-items-center mb-3">
                                                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                                                        <label class="form-check-label mand-field-text" for="invalidCheck">
                                                            I would like my profile to be created
                                                        </label>
                                                        <div class="invalid-feedback">
                                                        You must agree before submitting.
                                                        </div>
                                                    </div>
                                                    <div class="form-check p-0 d-flex align-items-center mb-3">
                                                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                                                        <label class="form-check-label mand-field-text" for="invalidCheck">
                                                            Yes, I have read and agreed to the Data <a class="pvcy-policy" href="">Privacy Policy.</a>
                                                        </label>
                                                        <div class="invalid-feedback">
                                                        You must agree before submitting.
                                                        </div>
                                                    </div>
                                                    </div>
                                                </div>
                                                <div class="row form-action-btn">
                                                    <div class="col-md-3"><button type="submit" class="btn btn-primary-fill btn-form-outline w-100">CLEAR ALL</button></div>
                                                    <div class="col-md-3"><button type="submit" class="btn btn-primary-fill btn-form-fill  w-100">SEND</button></div>
                                                </div>              
                                                <div class="formSuccess d-none">
                                                    <p class="font-bold">Thank you for your interest in the Invest Saudi.</p>
                                                    <p>We received your inquiry, and we will get back to you shortly.</p>
                                                </div>                          
                                                </form:form>
                
                                            </div> -->
                                        </div>
                                        <div class="tab-pane fade" id="pills-contact3" role="tabpanel" aria-labelledby="pills-contact-tab">
                                            <div class="col-md-10 m-auto pb-5 contact-us-padding-top8rem">  
                                            <div id="accordion" class="contact-accordion">
                                            <h3 class="rhq-Faq-header clr_gld my-4"><spring:theme code="portal.regional.hq.faqs.heading"/></h3>
                                            <div class="card">
                                              <div class="card-header" id="headingOne">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                    <spring:theme code="portal.regional.hq.faqs.question1"/>
                                                  </button>
                                                </h5>
                                              </div>
                                          
                                              <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.answer1"/>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="card">
                                              <div class="card-header" id="headingTwo">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                    <spring:theme code="portal.regional.hq.faqs.question2"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                                <div class="card-body"> 
													<spring:theme code="portal.regional.hq.faqs.answer2"/>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="card">
                                              <div class="card-header" id="headingThree">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                    <spring:theme code="portal.regional.hq.faqs.question3"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                                <div class="card-body">
                                                     <spring:theme code="portal.regional.hq.faqs.answer3"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="headingFour">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                                    <spring:theme code="portal.regional.hq.faqs.question4"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                                                <div class="card-body">
                                                   <spring:theme code="portal.regional.hq.faqs.answer4"/>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="card">
                                              <div class="card-header" id="headingFive">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                                    <spring:theme code="portal.regional.hq.faqs.question5"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.answer5"/>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="card">
                                              <div class="card-header" id="headingSix">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                                    <spring:theme code="portal.regional.hq.faqs.question6"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.answer6"/>
                                                </div>
                                              </div>
                                            </div>
                                            <div class="card">
                                              <div class="card-header" id="headingSeven">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                                    <spring:theme code="portal.regional.hq.faqs.question7"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven" data-parent="#accordion">
                                                <div class="card-body">
                                                   <spring:theme code="portal.regional.hq.faqs.answer7"/>
                                                </div>
                                              </div>
                                            </div>
											
											
											<div class="card">
                                              <div class="card-header" id="headingEight">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                                                    <spring:theme code="portal.regional.hq.faqs.question8"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordion">
                                                <div class="card-body">                                                   												   
													<p><spring:theme code="portal.regional.hq.faqs.answer8"/></p>
													<p>&nbsp;</p>
													<ol>
													<li><spring:theme code="portal.regional.hq.faqs.answer8.1"/></li>
													</ol>
													<ol start="2">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.2"/></li>
													</ol>
													<ol start="3">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.3"/></li>
													</ol>
													<ol start="4">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.4"/></li>
													</ol>
													<ol start="5">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.5"/></li>
													</ol>
													<ol start="6">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.6"/></li>
													</ol>
													<ol start="7">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.7"/></li>
													</ol>
													<ol start="8">
													<li><spring:theme code="portal.regional.hq.faqs.answer8.8"/></li>
													</ol>
													<p>&nbsp;</p>
													<p><u><spring:theme code="portal.regional.hq.faqs.answer8.9"/></u></p>
													<p>&nbsp;</p>
													<p><u><spring:theme code="portal.regional.hq.faqs.answer8.10"/></u></p>
													<p>&nbsp;</p>
													<p><em><u><spring:theme code="portal.regional.hq.faqs.answer8.11"/></u></em></p>												   
                                                </div>
                                              </div>
                                            </div>
											

                                            <h3 class="rhq-Faq-header clr_gld my-4"><spring:theme code="portal.regional.hq.license.heading"/></h3>
                                            <div class="card">
                                              <div class="card-header" id="heading8">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse8" aria-expanded="false" aria-controls="collapse8">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question1"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse8" class="collapse" aria-labelledby="heading8" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer1"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading9">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse9" aria-expanded="false" aria-controls="collapse9">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question2"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse9" class="collapse" aria-labelledby="heading9" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer2"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading10">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse10" aria-expanded="false" aria-controls="collapse10">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question3"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse10" class="collapse" aria-labelledby="heading10" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer3"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading11">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse11" aria-expanded="false" aria-controls="collapse11">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question4"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse11" class="collapse" aria-labelledby="heading11" data-parent="#accordion">
                                                <div class="card-body">
                                                      <spring:theme code="portal.regional.hq.faqs.license.answer4"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading12">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse12" aria-expanded="false" aria-controls="collapse12">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question5"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse12" class="collapse" aria-labelledby="heading12" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer5"/>
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading13">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse13" aria-expanded="false" aria-controls="collapse13">
                                                        <spring:theme code="portal.regional.hq.faqs.license.question6"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse13" class="collapse" aria-labelledby="heading13" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer6"/> 
                                                </div>
                                              </div>
                                            </div>
                                  
                                            <div class="card">
                                              <div class="card-header" id="heading14">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse14" aria-expanded="false" aria-controls="collapse14">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question7"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse14" class="collapse" aria-labelledby="heading14" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer7"/>
                                                </div>
                                              </div>
                                            </div>                                                                                                             
                                            <div class="card">
                                              <div class="card-header" id="heading16">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse16" aria-expanded="false" aria-controls="collapse16">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question8"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse16" class="collapse" aria-labelledby="heading16" data-parent="#accordion">
                                                <div class="card-body">
                                                    <spring:theme code="portal.regional.hq.faqs.license.answer8"/>
                                                </div>
                                              </div>
                                            </div>                                                                   
                                            <div class="card">
                                              <div class="card-header" id="heading22">
                                                <h5 class="mb-0">
                                                  <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse22" aria-expanded="false" aria-controls="collapse22">
                                                    <spring:theme code="portal.regional.hq.faqs.license.question9"/>
                                                  </button>
                                                </h5>
                                              </div>
                                              <div id="collapse22" class="collapse" aria-labelledby="heading22" data-parent="#accordion">
                                                <div class="card-body">
                                                    <div class=""><spring:theme code="portal.regional.hq.faqs.license.answer9.description1"/></div>                                                    
                                                    <div class="table-responsive px-3 py-4">
                                                      <table class="table  mb-0 rhq-Faq-table">
                                                        <thead>
                                                          <tr>
                                                            <th colspan="4"><spring:theme code="portal.regional.hq.faqs.license.table.heading.answer9"/></th>
                                                          </tr>
                                                        </thead>
                                                        <tbody>
                                                          <tr>
                                                            <td>1</td>
                                                            <td><spring:theme code="portal.regional.hq.faqs.license.table.answer9.1"/></td>
                                                          </tr>
                                                          <tr>
                                                            <td>2</td>
                                                            <td><spring:theme code="portal.regional.hq.faqs.license.table.answer9.2"/></td>
                                                          </tr>
														  <tr>
                                                            <td>3</td>
                                                            <td>
																<spring:theme code="portal.regional.hq.faqs.license.table.answer9.3.description3"/>
																<!-- <ul>
																	<li><spring:theme code="portal.regional.hq.faqs.license.table.answer9.3.description3.1"/></li>
																	<li><spring:theme code="portal.regional.hq.faqs.license.table.answer9.3.description3.2"/></li>
																	<li><spring:theme code="portal.regional.hq.faqs.license.table.answer9.3.description3.3"/></li>
																</ul> -->
															</td>
                                                          </tr>
														  <tr>
                                                            <td>4</td>
                                                            <td><spring:theme code="portal.regional.hq.faqs.license.table.answer9.3.description4"/></td>
                                                          </tr>
                                                        </tbody>
                                                      </table>
													  <spring:theme code="portal.regional.hq.faqs.license.answer9.4"/>
                                                    </div>
                                                </div>
                                              </div>
                                            </div>


                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            </section>
              
    </jsp:body>
</template:portalpage>