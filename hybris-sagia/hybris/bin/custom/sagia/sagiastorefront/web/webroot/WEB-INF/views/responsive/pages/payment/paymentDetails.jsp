<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/successModal.tag" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>

<%-- TODO: SAH-890: Payments Detail - Creditcard --%>
<script src = "${MIGS_Session_JS}"></script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="text.account.paymentDetails"/>
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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="text.account.paymentDetails"/></h1>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <c:url value="/dashboard" var="dashboardUrl"></c:url>
            <a href="${dashboardUrl}" class="btn btn_leftIconLink btn_darkLink back_to_service payment-detail-overview-btn"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="payments.details.back"/></a>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 mt-5">
    <div class="container">
        <div class="panelModule panelModule_halfRadius mt-3 w-100">
            <div class="contentModule">
                <!-- <div class="contentModule-headline contentModule-headline_big"> -->
                <div class="contentModule-actions contentModule-actions_wrap w-100">
                    <span class="headline-text"><spring:theme code="payments.details.subscriptionFee"/>&nbsp;<c:out value="${paymentData.serviceDescription}"/></span>                    
                </div>
                    <!-- </div> -->
                <div class="contentModule-section">
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline"><spring:theme code="payments.details.generalData"/></span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>
                    <div class="commentModule_1">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt class="headline-golden"><spring:theme code="payments.details.serviceId"/></dt>
                                <dd><c:out value="${paymentData.serviceId}"/></dd>
                            </dl>
                        </div>
                        <c:if test="${paymentData.status == 'E0003' && paymentData.hybrisStatusDescription != 'Paid'}">
	                        <div class="col-md-6">
	                            <div style="text-align: right;">
			                        <a onclick="SAGIA.payment.requestCreditBillPayment(${paymentData.serviceId},'${paymentData.serviceDescription}',${paymentData.amount},'${paymentData.currency}')" target="_blank" class="btn-outline">
			                            <spring:theme code="license.apply.paywithcc.detail.page" />
			                        </a>
	                			</div>
	                        </div>
                        </c:if>
                    </div>
                </div>
                <div class="contentModule-section">
                    <!-- <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:documents/>
                       </span>
                        <spring:theme code="payments.details.processingData"/>
                    </div> -->
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline"><spring:theme code="payments.details.processingData"/></span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>
                    <div class="commentModule_1">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt class="headline-golden"><spring:theme code="payments.details.serviceStatus"/></dt>
                                <dd>
                                	<c:choose>
	                                	<c:when test="${paymentData.status == 'E0003' && paymentData.hybrisStatusDescription == 'Paid'}">
	                                		<c:out value="${paymentData.hybrisStatusDescription}"/>
	                                	</c:when>
	                                	<c:otherwise>
	                                		<c:out value="${paymentData.statusDescription}"/>
	                                	</c:otherwise>
                                	</c:choose>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt class="headline-golden"><spring:theme code="payments.details.dateOfCreation"/></dt>
                                <dd><c:out value="${paymentData.sagiaPaymentDate.dateFormatted}"/></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-section">
                    <!-- <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:payment03/>
                       </span>
                        <spring:theme code="payments.details.value"/>
                    </div> -->
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline"><spring:theme code="payments.details.value"/></span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>

                    <div class="commentModule_1">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <fmt:formatNumber var="ammount" value="${paymentData.amount}" maxFractionDigits="2" />


                                <dt class="headline-golden"><spring:theme code="payments.details.netValue"/></dt>
                                <dd><c:out value="${ammount}"/>&nbsp;<c:out value="${paymentData.currency}"/></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                    <!-- <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:info/>
                       </span>
                        SADAD Payment Information
                    </div> -->
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                            <span class="contentModule-headline">SADAD Payment Information</span>
                            <div class="contentModule-headline-border"></div>
                        </div>
                    </div>
                    <div class="row commentModule_1 mx-0">
                        <div class="col-md-6 mt-3">
                            <div class="contentModule contentModule-wrap">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                    <span class="contentModule-headline">SADAD Bill Payment Information</span>
                                    <div class="contentModule-headline-border"></div>
                                </div>
                            </div>
                            <dl class="dlList dlList_separated mt-3">
                                <dt class="headline-golden">SADAD Bill Status</dt>
                                <dd>${paymentData.sadadBillStatus}</dd>
                                <dt class="headline-golden">SADAD Bill Number</dt>
                                <dd>${paymentData.sadadBillNum}</dd>
                                <dt class="headline-golden">SADAD Bill Account</dt>
                                <dd>${paymentData.sadadBillAccount}</dd>
                                <dt class="headline-golden">SADAD Payment Number</dt>
                                <dd>${paymentData.sadadPaymentNum}</dd>
                                <dt class="headline-golden">SADAD Investor ID</dt>
                                <dd>${paymentData.sadadInvestorId}</dd>
                                <dt class="headline-golden">SADAD Amount Paid</dt>
                                <dd>${paymentData.sadadAmountPaid}</dd>
                            </dl>
                        </div>
                        <div class="col-md-6 mt-3">
                            <div class="contentModule contentModule-wrap">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                    <span class="contentModule-headline">SADAD Bill Bank Information</span>
                                    <div class="contentModule-headline-border"></div>
                                </div>
                            </div>
                            <dl class="dlList dlList_separated mt-3">
                                <dt class="headline-golden">SADAD Transaction Date</dt>
                                <dd>${paymentData.sadadTransDate}</dd>
                                <dt class="headline-golden">SADAD Transaction Number</dt>
                                <dd>${paymentData.sadadTransNum}</dd>
                                <dt class="headline-golden">SADAD Bank ID</dt>
                                <dd>${paymentData.sadadBankId}</dd>
                                <dt class="headline-golden">SADAD Payment Method</dt>
                                <dd>${paymentData.sadadPaymentMethod}</dd>
                                <dt class="headline-golden">SADAD Payment Channel</dt>
                                <dd>${paymentData.sadadPaymentChannel}</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-actions contentModule-actions_centered">
                    <c:if test="${hasInvoice}">
                        <a href="${encodedContextPath}/payment/pdf/${paymentData.serviceId}" target="_blank" class="btn btn_round btn_outline w-25">
                            Print SADAD invoice<span class="iconElement iconElement_print"><icon:print/></span>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<payment:paymentModal/>
