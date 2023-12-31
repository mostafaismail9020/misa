<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
    <section class="show-interest-wrapper">
    <div class="accountLogin-background">
        <div class="container">
         <div class="accountLogin-headline">
                        <a href="${encodedContextPath}/" class="accountLogin-headline-logo">
                            <img src="https://investsaudi.sa/investsaudistorefront/_ui/responsive/common/images/logo-en.svg">
                        </a>
                    </div>
            <div class="row">
                <h2 class="heading text-center mb-5 col-md-12" style="color: rgb(255, 255, 255);">${componentTitle}</h2>
            </div>
            <b><p class="text-left mb-12 col-md-12" style="color: rgb(255, 255, 255);"> <spring:theme code="text.interest.in.saudi.arabia"/></p></b>
            <b><p class="text-left mb-12 col-md-12" style="color: rgb(255, 255, 255);"> <spring:theme code="text.interest.representative.message"/></p></b><br><br>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="mb-3 my-form pos-rel wow fadeIn" data-wow-delay="0.2s" id="corForm">

                        <div class="row">
                               <div class="form-group form-floating col-md-6 form-normal-item">
                                       <label class="control-label" for="crName">
                                        <spring:theme code="portal.sector.contact.expert.name.label"/>
                                        <span class="mandatory">* </span>
                                       </label>
                                       <input type="text" class="form-control required validate-name" data-val="true"
                                            data-val-regex="Numbers and Special Characters are not allowed"
                                       data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                       data-val-required="Required" id="crName" name="Name"/>
                                       <div class="error-msg"></div>
                                </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                         <div class="form-group form-floating form-normal-item">
                                            <label class="control-label" for="crCompany">
                                                <spring:theme code="portal.sector.contact.expert.companyName.label"/>
                                                <span class="mandatory">* </span>
                                            </label>
                                            <input type="text" class="form-control required validate-name" data-val="true"
                                            data-val-regex="Numbers and Special Characters are not allowed"
                                            data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                            id="crCompany" name="Company"/>
                                            <div class="error-msg"></div>
                                        </div>
                                    </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="form-group form-floating form-normal-item country-code-mobile">
                                    <input type="text" class="ddl-countryCode form-control" placeholder="+966" autocomplete="off">

                                    <div class="input-wrapper">
                                        <label class="control-label" for="crMobile">
                                            <spring:theme code="portal.sector.contact.expert.phoneNumber.label"/>
                                            <span class="mandatory">* </span>
                                        </label>
                                        <input type="text" style="padding-left: 50px;" class="form-control validate-mobile required mobile-number" id="crMobile" name="MobileNumber"  type="number"/>
                                        <div class="error-msg"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="form-group form-floating form-normal-item">
                                    <label class="control-label" for="crJobTitle">
                                        <spring:theme code="portal.sector.contact.expert.jobTitle.label"/>
                                        <span class="mandatory">* </span>
                                    </label>
                                    <input type="text" class="form-control required validate-name" data-val="true"
                                    data-val-regex="Numbers and Special Characters are not allowed"
                                    data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                    id="crJobTitle" name="JobTitle"/>
                                    <div class="error-msg"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="form-group form-floating form-normal-item">
                                    <label class="control-label" for="crEmail">
                                        <spring:theme code="portal.sector.contact.expert.email.label"/><span class="mandatory">* </span>
                                    </label>
                                    <input type="text" class="form-control validate-email required" data-val="true"
                                        data-val-regex="Invalid email address"
                                        data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                        data-val-required="Required" id="crEmail" name="Email"/>
                                    <div class="error-msg"></div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="form-group form-normal-item-select">
                                   <label class="control-label" for="contactSubjectList"><spring:theme code="portal.sector.contact.expert.purpose.label"/><span class="mandatory">* </span></label>
                                   <select id="contactSubjectList" class="form-control required drop-item"
                                            onchange="updateSubjectId(event)">
                                        <c:forEach var="contactSubject" items="${contactSubjects}">
                                            <option value="${contactSubject.code}">${contactSubject.label}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="form-group form-normal-item-select">
                                    <label class="control-label" for="contactSubjectList"><spring:theme code="portal.sector.explore.name.label"/><span class="mandatory">* </span></label>
                                    <select id="partnerSectors" class="form-control required drop-item"
                                            onchange="updateInterestSectorId(event)">
                                        <c:forEach var="sector" items="${sectors}">
                                            <option value="${sector.sectorCode}">${sector.name}</option>
                                        </c:forEach>
                                  </select>
                                </div>
                            </div>
                        </div>
                        <input id="crSectorID" name="SectorID" type="hidden" value=""/>
                        <span class="field-validation-valid" data-valmsg-for="Subject"
                              data-valmsg-replace="true"></span>
                        <input id="crSubjectID" name="SubjectID" type="hidden" value=""/>
                        <span class="field-validation-valid" data-valmsg-for="Subject"
                              data-valmsg-replace="true"></span>
                        <div class="md-form show-interest-form-normal-item-textarea">
                         <label class="control-label" for="crJobTitle">
                                                        <spring:theme code="portal.sector.contact.expert.message.label"/>
                                                        <span class="mandatory">* </span>
                                                    </label>
                            <textarea class="md-textarea form-control required" cols="10" data-val="true"
                                      data-val-length="Name length should be between 2 &amp; 1000" data-val-length-max="1000"
                                      data-val-length-min="2" data-val-required="Required" id="crMessage" name="Message"
                                      placeholder="${messageLabel}" rows="5">
                             </textarea>
                        </div>
                            <div class="form-group form-floating col-md-12 form-normal-item">
                                <input type="hidden" id="recaptchaChallangeAnswered" value="${requestScope.recaptchaChallangeAnswered}" />
                                   <div class="form_field-elements control-group js-recaptcha-captchaaddon"></div>
                                      <span id="lblSectorPageErrorCaptcha" class="mandatory"></span>
                             </div>
                        <div class="row d-flex align-items-center mb-4">
                            <div class="col-md-7 d-flex justify-content-start">
                                <label class="lbError d-none"></label>
                            </div>
                            <div class="col-md-5 d-flex justify-content-end">
                                <button id="btn-contact responsive-contact-btn" type="button"
                                        class="btn btn-primary-fill btn-knowmore text-uppercase"
                                        onclick="onContactload()">${submitButtonLabel} 
                                </button>
                            </div>
                        </div>
                    </div>
                        ${successMessage}
                    <input type="hidden" id="hfSectorID" value="${categoryCode}"/>
                    <input type="hidden" id="hfOpportunity" value="${productName}"/>
                    <input type="hidden" id="hfPageTitle" value="${sectorOrProduct}"/>
                    <input type="hidden" id="hfProductCode" value="${productCode}"/>
                </div>

            </div>
        </div>
        </div>
    </section>
</c:if>
