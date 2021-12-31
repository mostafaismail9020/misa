<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
    <section class="company-mgr-quote mt-4">
        <div class="container">
            <div class="row">
                <h2 class="heading text-left mb-5 col-md-12">${componentTitle}</h2>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">

                    <div class="mb-3 my-form pos-rel wow fadeIn" data-wow-delay="0.2s" id="corForm">

                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form">
                                    <input class="form-control required validate-name" data-val="true"
                                           data-val-regex="Numbers and Special Characters are not allowed"
                                           data-val-regex-pattern="^[A-Za-z\u0600-\u065F\u066A-\u06EF\u06FA-\u06FF\s]*$"
                                           data-val-required="Required" id="crName" name="Name"
                                           placeholder="${nameLabel}"
                                           type="text" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form">
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
                                <div class="md-form my-mobile">
                                    <input type="text" class="ddl-countryCode form-control" placeholder=""/>
                                    <input class="form-control mobile-number required" id="crMobile" name="MobileNumber"
                                           placeholder="${mobileLabel}" type="number" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form">
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
                                <div class="md-form">
                                    <input class="form-control required validate-email" data-val="true"
                                           data-val-regex="Invalid email address"
                                           data-val-regex-pattern="[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+(?:\.[A-Za-z0-9!#$%&amp;&#39;*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?"
                                           data-val-required="Required" id="crEmail" name="Email"
                                           placeholder="${emailLabel}"
                                           type="text" value=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-12">
                                <div class="md-form">
                                    <select id="contactSubjectList" class="form-control required"
                                            onchange="updateSubjectId(event)">
                                        <option disabled selected>${contactSubjectListLabel}</option>
                                        <c:forEach var="contactSubject" items="${contactSubjects}">
                                            <option value="${contactSubject.code}">${contactSubject.label}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input id="crSubjectID" name="SubjectID" type="hidden" value=""/>
                        <span class="field-validation-valid" data-valmsg-for="Subject"
                              data-valmsg-replace="true"></span>
                        <div class="md-form">
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
                                <button id="btn-contact" type="button"
                                        class="button btn btn-contact btn-green pl-5 pr-5 m-0 text-uppercase"
                                        onclick="onContactload()">${submitButtonLabel} <i
                                        class="fa fa-long-arrow-${currentLanguage.isocode eq 'ar' ? 'left' :'right'}"></i>
                                </button>
                            </div>
                        </div>
                    </div>

                        ${successMessage}


                    <input type="hidden" id="hfSectorID" value="${categoryCode}"/>
                    <input type="hidden" id="hfOpportunity" value="${productName}"/>
                    <input type="hidden" id="hfPageTitle" value="${sectorOrProduct}"/>
                </div>

            </div>
        </div>
    </section>
</c:if>
