<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>
 
                                                        <div class="mainSection mainSection">
                                                            <div class="achievement_header">
                                                                <img class="achievement_header_icon  page-header-image" src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
                                                                <div class="container">
                                                                    <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                                                                        <h1 data-aos="fade-up">
                                                                            <spring:theme code="appointments.makeanappointment" />
                                                                        </h1>
                                                                    </div>
                                                                    <div class="profile-icons float-right">
                                                                        <c:if test="${hasLicense or hasAwaitingPayment}">
                                                                            <div class="calendar">
                                                                                <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                                                                                    <span></span>
                                                                                </a>
                                                                            </div>
                                                                            <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                                                                                <c:if test="${hasLicense or hasAwaitingPayment}">
                                                                                    <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                                                                        <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                                                                        <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img"/>
                                                                                    </button>
                                                                                </c:if>
                                                                                <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                                                                                <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
                                                                                    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                                                                                    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                                                                                    <div class="sagiaNavigation-subPane-actions">
                                                                                        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <div class="profile">
                                                                            <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
                                                                                <span></span>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!-- <div class="mainSection mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="appointments.makeanappointment"/></h1>
        </div>
    </div>
</div> -->

                                                        <div class="mainSection mainSection mainSection_dark mainSection_noPadding">
                                                            <div class="container">
                                                                <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
                                                                    <div class="w-25 appointments_create">
                                                                        <a href="${encodedContextPath}/appointments" class="w-100 btn-outline  btn_darkLink btn_leftIconLink  appointmentControl-backBtn appoint-btn">
                                                                            <!--<span class="iconElement iconElement_closeBack"><icon:close/></span>-->
                                                                            <svg class="potential_svg_arrow3" xmlns="http://www.w3.org/2000/svg" width="10" height="17.116" viewBox="0 0 10 17.116">
                                                                                <path id="Icon_ionic-ios-arrow-back" data-name="Icon ionic-ios-arrow-back" d="M14.265,14.749l6.618-6.471a1.2,1.2,0,0,0,0-1.727,1.275,1.275,0,0,0-1.77,0l-7.5,7.332a1.2,1.2,0,0,0-.036,1.687l7.53,7.383a1.277,1.277,0,0,0,1.77,0,1.2,1.2,0,0,0,0-1.727Z" transform="translate(-11.251 -6.194)" fill="#00a6be"></path>
                                                                            </svg>
                                                                            <span class="w-75 appointmentControl-backBtn-label"><spring:theme code="appointments.backToOverview"/></span>
                                                                        </a>
                                                                    </div>
                                                                    <%--<div>
                                                                            <a class="btn btn--primary btn--half-radius btn--small" href="#"><spring:theme code="appointments.makeanappointment"/></a>
                                                                        </div>--%>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="mainSection mainSection mainSection_dark mainSection_pdt16">
                                                            <div class="container">
                                                                <div class="panelModule panelModule_halfRadius appointmentDetails">
                                                                    <form:form method="post" modelAttribute="appointmentModel">
                                                                        <form:hidden path="appointmentID" />
                                                                        <div class="appointmentDetails appointmentDetails_forms">
                                                                            <div class="contentModule-headline">
                                                                                <!--<icon:calendarText/>-->
                                                                                <spring:theme code="appointments.appointmentdetails" />
                                                                            </div>
                                                                            <hr class="hr" />
                                                                            <div class="row mt-5 mb-5">
                                                                                <div class="col-xs-8 col-md-6">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="department" cssClass="js-select2-oneColumn form-control">
                                                                                                <option></option>
                                                                                                <form:options items="${departments}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="department"><spring:theme code="appointments.department"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-8 col-md-6">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="branch" cssClass="js-select2-oneColumn form-control">
                                                                                                <option></option>
                                                                                                <form:options items="${branches}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="branch"><spring:theme code="appointments.branch"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="contentModule-headline mw0">
                                                                                <spring:theme code="appointments.services" />
                                                                            </div>
                                                                            <hr class="hr" />
                                                                            <div class="row service-selection mt-5">
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="serviceType1" cssClass="js-select2-oneColumn service-type servicetype-group">
                                                                                                <option></option>
                                                                                                <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="serviceType1"><spring:theme code="appointments.servicetype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="ministry1" cssClass="js-select2-oneColumn ministry ministry-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="ministry1"><spring:theme code="appointments.ministry"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="service1" cssClass="js-select2-oneColumn service service-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="service1"><spring:theme code="appointments.enquirytype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <hr class="contentModule-section-rowDelimiter" />
                                                                            <div class="row service-selection mt-5">
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="serviceType2" cssClass="js-select2-oneColumn service-type servicetype-group">
                                                                                                <option></option>
                                                                                                <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="serviceType2"><spring:theme code="appointments.servicetype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="ministry2" cssClass="js-select2-oneColumn ministry ministry-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="ministry2"><spring:theme code="appointments.ministry"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="service2" cssClass="js-select2-oneColumn service service-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="service2"><spring:theme code="appointments.enquirytype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <hr class="contentModule-section-rowDelimiter" />
                                                                            <div class="row service-selection mt-5">
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="serviceType3" cssClass="js-select2-oneColumn service-type servicetype-group">
                                                                                                <option></option>
                                                                                                <form:options items="${serviceTypes}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="serviceType3"><spring:theme code="appointments.servicetype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="ministry3" cssClass="js-select2-oneColumn ministry ministry-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${ministries}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="ministry3"><spring:theme code="appointments.ministry"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-6 col-sm-6 col-md-4">
                                                                                    <div class="formSelectBox">
                                                                                        <div class="form-group">
                                                                                            <form:select path="service3" cssClass="js-select2-oneColumn service service-group" disabled="true">
                                                                                                <option></option>
                                                                                                <form:options items="${services}" itemValue="${not empty itemValue ? itemValue :'FieldKey'}" itemLabel="${not empty itemLabel ? itemLabel :'Description'}" htmlEscape="true" />
                                                                                            </form:select>
                                                                                            <label class="control-label control-label_mandatory" for="service3"><spring:theme code="appointments.enquirytype"/></label>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <%--<div class="appointmentDetails-row_add row text-center">
                        <button class="btn btn_plain">+ Add Row</button>
                    </div>--%>

                                                                                <div class="contentModule-headline mw0 mt-5">
                                                                                    <spring:theme code="appointments.availableslots" />
                                                                                </div>
                                                                                <hr class="hr" />
                                                                                <div class="row mt-5">
                                                                                    <div class="col-xs-8 col-md-6 mb-4">
                                                                                        <div class="formInputBox formInputBox_group ">
                                                                                            <div class="form-group">
                                                                                                <form:input path="dateString" cssClass="form-control js-form-control_date flatpickr-input" placeholder="." />
                                                                                                <label class="control-label control-label_mandatory" for="dateString"><spring:theme code="appointments.date"/></label>
                                                                                                <div class="formInputBox-append" id="calendar-icon-pos">
                                                                                                    <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-xs-8 col-md-6 mb-4">
                                                                                        <div class="formInputBox formInputBox_group ">
                                                                                            <div class="form-group">
                                                                                                <form:input path="timeStartString" cssClass="form-control js-form-control_timeslot" placeholder="." data-dates-enabled="10:00#17:30" />
                                                                                                <%--<input  id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_timeslot" placeholder="." value="" type="text"  data-dates-enabled="10:00#17:30">--%>
                                                                                                    <label class="control-label control-label_mandatory" for="timeStartString"><spring:theme code="appointments.start"/></label>
                                                                                                    <div class="formInputBox-append" id="calendar-icon-pos">
                                                                                                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                                                                    </div>
                                                                                            </div>
                                                                                            <div class="help-block"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                                                                        <div class="formSelectBox">
                                                                                            <div class="form-group">
                                                                                                <form:select path="contactPerson" cssClass="js-select2-oneColumn contact-person">
                                                                                                    <option></option>
                                                                                                    <form:options items="${contactPersons}" itemValue="${not empty itemValue ? itemValue :'dockeyID'}" itemLabel="${not empty itemLabel ? itemLabel :'longDescription'}" htmlEscape="true" />
                                                                                                </form:select>
                                                                                                <label class="control-label control-label_mandatory" for="ministry1"><spring:theme code="appointments.visitorname"/></label>
                                                                                                <div class="help-block"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col">
                                                                                        <div class="formTextArea">
                                                                                            <div class="form-group">
                                                                                                <%-- <form:textArea cssClass="form-control" idKey="notes"
                                              labelKey="govDocs.comments"
                                              path="notes" mandatory="false" /> --%>
                                                                                                    <textarea id="notes" class="form-control mt-5" placeholder="." name="notes"></textarea>
                                                                                                    <label class="control-label control-label_mandatory" for="notes"><spring:theme code="govDocs.comments"/></label>
                                                                                                    <div class="help-block"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="mt-5 contentModule-actions contentModule-actions_centered contentModule_spaceTop contentModule-actions_noMargin">
                                                                                    <%-- <button type="submit" class="btn bnt_bold btn_slim btn-normal w-25"><spring:theme code="appointments.saveappointment"/></button>  --%>
																					<button type="submit" class="btn bnt_bold btn_slim btn-normal w-25" disabled><spring:theme code="appointments.saveappointment"/></button>
                                                                                </div>
                                                                    </form:form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal fade" id="requestSubmittedApply" tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <div class="modal-title">
                                                                            <spring:theme code="general.requestsubmitted" />
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="modal-heroImage">
                                                                            <icon:modal02/>
                                                                        </div>
                                                                        <div class="modal-description js-description-text">
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <a class="btn js-close-btn" href="${encodedContextPath}/appointments">Close</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal fade" id="appointmentDisclaimer" tabindex="-1" role="dialog" aria-labelledby="appointmentDisclaimer" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <%-- <div class="modal-title"><spring:theme code="services.government.create.disclaimer.title"/></div> --%>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="modal-description js-description-text">
                                                                            <label class="control-label" for="department">
	                <spring:theme code="services.government.create.disclaimer.title"/>
	                <br>
	                <spring:theme code="services.government.create.disclaimer.message"/>
	                <br>
	                <spring:theme code="services.government.create.disclaimer.greet"/>
                </label>
                                                                        </div>
                                                                    </div>
                                                                    <!-- <div class="modal-footer">
	            <button type="button" class="btn js-close-btn" data-dismiss="modal" aria-label="Close">Close
	            </button>
            </div> -->
                                                                    <div class="modal-footer">
                                                                        <a class="btn js-close-btn" href="${encodedContextPath}/appointments">Close</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <script>
                                                            var appointmentsJson = '${appointmentsJson}';
                                                            var appointmentJson = '${appointmentJson}';
                                                            var formOptionsJSON = '${formOptionsJSON}';
                                                            var appointmentId = '${appointmentId}';
                                                        </script>
                                                        <script> 
                                                            $(document).ready(function(e) {
                                                                $('#requestSubmittedApply').on('hidden.bs.modal', function(e) {
                                                                    window.location.href = '${encodedContextPath}/appointments'
                                                                });
                                                            });
                                                        </script>