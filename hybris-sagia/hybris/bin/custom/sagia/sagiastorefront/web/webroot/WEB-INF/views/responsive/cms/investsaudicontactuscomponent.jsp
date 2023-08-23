<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <section class="company-mgr-quote mt-4 opportunity-contact" id="opp-contact-form">
        <div class="container">
            <div class="row">
                <h2 class="heading mb-5 col-md-12">${componentTitle}</h2>
            </div>
            <div class="contactSuccess d-none diff-color">
                <p class="font-bold"><spring:theme code="portal.contact.received.thankyou"/></p>
                <p><spring:theme code="portal.contact.received.message"/></p>
                <p class="contact-sucess-ticket"></p>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="mb-3 pos-rel wow fadeIn" data-wow-delay="0.2s" id="corForm">
						<div class="row">
							<div class="form-group col-md-6 col-12 form-normal-item">
								<label for="crName">
									<spring:theme code="portal.sector.contact.expert.name.label"/> *
								</label>
								<input type="text" class="form-control required validate-name" data-val="true" 
                               		data-val-regex="Numbers and Special Characters are not allowed"
	                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
	                                data-val-required="Required" id="crName" name="Name"/>
								<small class="error-msg"></small>
							</div>
							<div class="form-group col-md-6 col-12 form-normal-item">
								<label for="crCompany">
									<spring:theme code="portal.sector.contact.expert.companyName.label"/> *
								</label>
								<input type="text" class="form-control required validate-name" data-val="true"
	                                data-val-regex="Numbers and Special Characters are not allowed"
	                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
	                                id="crCompany" name="Company"/>
								<small class="error-msg"></small>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6 col-12 form-normal-item">
								<label for="crMobile">
									<spring:theme code="portal.sector.contact.expert.phoneNumber.label"/> *
								</label>
								<div class="row no-gutters">
									<div class="col-md-4 col-4">
										<input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off"/>
									</div>
	                               <div class="col-md-8 col-8">
	                                   <input type="text" class="form-control validate-mobile required mobile-number" id="crMobile" name="MobileNumber" type="number"/>
	                                   <small class="error-msg"></small>
	                               </div> 
								</div>                               
                           </div>
							<div class="form-group col-md-6 col-12 form-normal-item">
								<label for="crEmail">
									<spring:theme code="portal.sector.contact.expert.email.label"/> *
								</label>
								<input type="text" class="form-control validate-email required" data-val="true"
	                                data-val-regex="Invalid email address"
	                                data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
	                                data-val-required="Required" id="crEmail" name="Email"/>
								<small class="error-msg"></small>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6 col-12 form-normal-item">
								<label for="crJobTitle">
									<spring:theme code="portal.sector.contact.expert.jobTitle.label"/> *
								</label>
								<input type="text" class="form-control required validate-name" data-val="true"
	                                data-val-regex="Numbers and Special Characters are not allowed"
	                                data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
	                                id="crJobTitle" name="JobTitle"/>
								<small class="error-msg"></small>
							</div>
							<div class="form-group col-md-6 col-12 form-normal-item-select">
								<label for="contactSubjectList">
									<spring:theme code="portal.sector.contact.expert.purpose.label"/> *
								</label>
                                <select type="text" id="contactSubjectList" class="form-control required error"
                               			onchange="updateSubjectId(event)">
                                    <option disabled selected value=""><spring:theme code="portal.sector.contact.expert.purpose.label"/></option>
                                    <c:forEach var="contactSubject" items="${contactSubjects}">
                                        <option value="${contactSubject.code}">${contactSubject.label}</option>
                                    </c:forEach>
                                </select>
                                <div class="error-msg"></div>
                            </div>
						</div>
						<div class="row">
							<div class="form-group col-md-12">
								<label for="crMessage">
									<spring:theme code="portal.sector.contact.expert.message.label"/> *
								</label>
								<textarea class="form-control required" cols="10" 
		                            data-val="true" data-val-length="Name length should be between 2 &amp; 1000" 
		                            data-val-length-max="1000" data-val-length-min="2" data-val-required="Required" 
		                            id="crMessage" name="Message" rows="5">
	                            </textarea>
							</div>
						</div>
                        <div class="row">
                        	<div class="form-group col-md-3 col-6 align-self-center mx-auto mb-4 mt-2">
	                        	<input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
	                         	<div class="form_field-elements control-group js-recaptcha-captchaaddon sector-page-captcha"></div>
	                         	<span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
	                        </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 col-6 align-self-center mx-auto">
	                            <button id="btn-contact" type="submit" class="responsive-contact-btn btn btn-primary-fill btn-form-fill w-100">
	                            	<spring:theme code="portal.sector.download.submit.button"/>
	                           	</button>
                            </div>
                        </div>
                       
                        <input id="crSubjectID" name="SubjectID" type="hidden" value=""/>
                        <span class="field-validation-valid" data-valmsg-for="Subject"
                        	  data-valmsg-replace="true">
                        </span>                      
                    </div>

                    ${successMessage}

                    <input type="hidden" id="hfSectorID" value="${categoryCode}"/>
                    <input type="hidden" id="hfOpportunity" value="${productName}"/>
                    <input type="hidden" id="hfPageTitle" value="${sectorOrProduct}"/>
					<input type="hidden" id="hfProductCode" value="${productCode}"/>
                </div>
            </div>
        </div>
    </section>
</c:if>
