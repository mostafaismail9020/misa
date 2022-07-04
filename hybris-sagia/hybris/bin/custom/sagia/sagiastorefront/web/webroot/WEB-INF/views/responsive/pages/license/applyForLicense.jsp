<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/infoModal.tag" %>
<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/responsive/modals" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<%--@elvariable id="MIGS_Session_JS" type="java.lang.String"--%>
<%--@elvariable id="processingTime" type="com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData"--%>
<script>
    var controllerUrl = '${controllerUrl}';
</script>
<script src="${MIGS_Session_JS}"></script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="license.apply.sagialicenseapplication"/>
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

<div class="mainSection mainSection_narrow mainSection_white">
    <div class="container">
        <div class="mainSection-header">
            <!-- <h1 class="mainSection-headline"><spring:theme code="license.apply.sagialicenseapplication"/></h1> -->
            <div>
                <c:if test="${'/simulator' ne controllerUrl}">
                    <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                        <button id="saveDraftButton" class="btn btn_round btn_slim"><spring:theme code="general.savedraft"/>
                            <span class="iconElement iconElement_save"><icon:save/></span>
                        </button>

                        <button id="loadDraftButton" class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>>
                            <spring:theme code="general.loaddraft"/><span class="iconElement iconElement_save"><icon:upload/></span>
                        </button>
                    </sec:authorize>
                </c:if>
            </div>
        </div>
        <c:if test="${not empty processingTime}">
            <div class="serviceTime">
                <div class="serviceTime-label"><spring:theme code="average.service.time" /></div>
                <div class="serviceTime-detail">
                    <c:choose>
                        <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                            <span class="serviceTime-highlight">${processingTime.days}</span>
                            <spring:theme code="average.processingTime.days"/>
                            <span class="serviceTime-highlight">${processingTime.hours}</span>
                            <spring:theme code="average.processingTime.hours"/>
                        </c:when>
                        <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                            <span class="serviceTime-highlight">${processingTime.minutes}</span>
                            <spring:theme code="average.processingTime.minutes"/>
                            <span class="serviceTime-highlight">${processingTime.seconds}</span>
                            <spring:theme code="average.processingTime.seconds"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:if>
    </div>
    <c:if test="${'/simulator' eq controllerUrl}">
        <div class="container">
            <div class="globalMessage-holder">
                <div class="globalMessage">
                    <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                        <div class="globalMessage-action">
                            <a href="${encodedContextPath}/login#register-quick" class="btn btn_round btn-warning"><spring:theme code="license.simulation.register"/></a>
                        </div>
                    </sec:authorize>
                    <div class="globalMessage-msg">
                        <div class="globalMessage-icon"><icon:warning/></div>
                        <spring:theme code="license.simulation.warning"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

<div class="js-panelTabs panelTabs panelTabs_white panelTabs_lightNavigation panelTabs_noPanelInBody panelTabs_iconsAndLabel panelTabs_separated panelTabs_formFlow panelTabs_tip_none">
    <license:licenseApplyEntityInformation/>
    <license:licenseApplyShareholders/>
    <license:licenseApplyContactPerson/>
    <license:licenseApplyReview/>
    <modal:ratingWithComments/>
</div>

<!--Modal for terms and conditions. Use (data-toggle="modal" data-target="#termsConditions") on link or button to call it-->
<div class="modal fade" id="termsConditions" tabindex="-1" role="dialog" aria-labelledby="termsConditions"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="general.termsandconditions"/></div>
                <!--        Close Button is optional-->
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close"><icon:close/></button>
            </div>
            <div class="modal-body">
                <div class="scrollWrapper">
                    <div class="scrollWrapper-inner">
                        <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor
                            invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam
                            et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est
                            Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed
                            diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam
                            voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
                            gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit
                            amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
                            dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores
                            et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit
                            amet. </p>

                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel
                            illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui
                            blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem
                            ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt
                            ut laoreet dolore magna aliquam erat volutpat.</p>

                        <p>Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl
                            ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in
                            vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero
                            eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue
                            duis dolore te feugait nulla facilisi. </p>

                        <p>Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim
                            placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed
                            diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi
                            enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut
                            aliquip ex ea commodo consequat. </p>

                        <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel
                            illum dolore eu feugiat nulla facilisis. </p>

                        <p>At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea
                            takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur
                            sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam
                            erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita
                            kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor
                            sit amet, consetetur sadipscing elitr, At accusam aliquyam diam diam dolore dolores duo
                            eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et
                            gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum
                            dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                            eirmod tempor invidunt ut labore et dolore magna aliquyam erat. </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt><spring:theme code="general.name"/></dt>
                            <dd>Rollo Ragnvaldsson</dd>
                            <dt><spring:theme code="general.role"/></dt>
                            <dd>Senior Project Manager</dd>
                            <dt><spring:theme code="general.date"/></dt>
                            <dd>12. March 2018</dd>
                        </dl>
                    </div>

                    <div class="col-md-6">
                        <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                            <div class="form-group">
                                <div class="form-item">
                                    <input id="TermsCheckbox01" name="TermsCheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name"/>
                                    <label class="control-label " for="TermsCheckbox01">
										<span><icon:check/></span><spring:theme code="general.readtermsandconditions"/>
                                    </label>
                                </div>
                                <div class="form-item">
                                    <input id="TermsCheckbox02" name="TermsCheckbox02name" class="form-control" placeholder="." type="checkbox" value="entity name"/>
                                    <label class="control-label " for="TermsCheckbox02">
										<span><icon:check/></span><spring:theme code="license.apply.declarethatalldocumentsarevalid"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer modal-footer_centered">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="general.submit"/></button>
            </div>
        </div>
    </div>
</div>
<c:if test='${controllerUrl eq "/simulator"}'>
    <div class="modal fade" id="simulatorWarning" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"><spring:theme code="license.simulation.modal.warning.title"/></div>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        <spring:theme code="license.simulation.modal.warning.content"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim showHistoryBtn" data-dismiss="modal"><spring:theme
                            code="license.simulation.modal.close"/></button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!--Modal: Use (data-toggle="modal" data-target="#licenseApplicationPayment") on link or button to call it-->
<div class="modal fade" id="licenseApplicationPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header"><div class="modal-title"><spring:theme code="license.apply.applicationpayment"/></div></div>
            <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onclick="hide()">
                <icon:close/>
            </button>
            <div class="modal-body">
                <!-- <div class="modal-heroImage"><icon:payment01/></div> -->
                <div class="modal-description" style="text-align: initial !important;">
					<table class="table table-striped" id="tblGrid" style="width:100%">
						<tbody>
						</tbody>
			        </table>

					<%-- <h2 style="color: red;"><spring:theme code="license.apply.payment.entrepreneur.certificate.title"/></h2>
					<ul>
					  <li><spring:theme code="license.apply.payment.entrepreneur.line1"/></li>
					  <li><spring:theme code="license.apply.payment.entrepreneur.line2"/></li>
					</ul>

					<h2 style="color: red;"><spring:theme code="license.apply.payment.other.certificate.title"/></h2>
					<ul>
					  <li><spring:theme code="license.apply.payment.other.line1"/></li>
					  <li><spring:theme code="license.apply.payment.other.line2"/></li>
					</ul> --%>

                    <div class="tableModule tableModule_footer" style="display:none;">
                        <table class="tableModule-table" id="paymentModal">
                            <tbody class="tableModule-body">

                            </tbody>
                            <tfoot class="tableModule-footer">
                                <tr>
                                    <td><spring:theme code="license.apply.total"/></td>
                                    <td><span id="total"></span></td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent pay-buttons" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:creditCard/></span>
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="displayPaymentModel()"><spring:theme code="license.apply.paywithcc"/></button>
                    </div>
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:sadad/></span>
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="SAGIA.payment.displayPayWithSadadMessage()"><spring:theme code="license.apply.paywithsadad"/></button>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent apply" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:licensing/></span>
                        <button type="button" class="btn btn_slim btn-apply" data-dismiss="modal"><spring:theme code="register.submit"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<payment:paymentModal/>



