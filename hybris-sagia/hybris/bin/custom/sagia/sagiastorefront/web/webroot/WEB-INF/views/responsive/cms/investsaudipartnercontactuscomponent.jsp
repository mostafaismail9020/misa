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
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                    <input class="form-control required validate-name" data-val="true"
                                           data-val-regex="Numbers and Special Characters are not allowed"
                                           data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                           data-val-required="Required" id="crName" name="Name"
                                           placeholder="${nameLabel}"
                                           type="text" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                    <input class="form-control required validate-name" data-val="true"
                                           data-val-regex="Numbers and Special Characters are not allowed"
                                           data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                           id="crCompany" name="Company" placeholder="${companyLabel}" type="text"
                                           value=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form my-mobile show-interest-form-normal-item">
                                    <input type="text" class="ddl-countryCode form-control" placeholder=""/>
                                    <input class="form-control mobile-number required" id="crMobile" name="MobileNumber"
                                           placeholder="${mobileLabel}" type="number" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                    <input class="form-control required validate-name" data-val="true"
                                           data-val-regex="Numbers and Special Characters are not allowed"
                                           data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                           id="crJobTitle" name="JobTitle" placeholder="${jobTitleLabel}" type="text"
                                           value=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                    <input class="form-control required validate-email" data-val="true"
                                           data-val-regex="Invalid email address"
                                           data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                           data-val-required="Required" id="crEmail" name="Email"
                                           placeholder="${emailLabel}"
                                           type="text" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                   <select id="contactSubjectList" class="form-control required drop-item"
                                            onchange="updateSubjectId(event)">
                                        <option disabled selected>${contactSubjectListLabel}</option>
                                        <c:forEach var="contactSubject" items="${contactSubjects}">
                                            <option value="${contactSubject.code}">${contactSubject.label}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form show-interest-form-normal-item">
                                    <select id="partnerSectors" class="form-control required drop-item"
                                            onchange="updateInterestSectorId(event)">
                                 			<option disabled selected>${sectorsLabel}</option>
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
        <textarea class="md-textarea form-control required" cols="10" data-val="true"
                  data-val-length="Name length should be between 2 &amp; 1000" data-val-length-max="1000"
                  data-val-length-min="2" data-val-required="Required" id="crMessage" name="Message"
                  placeholder="${messageLabel}" rows="5">
</textarea>
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
