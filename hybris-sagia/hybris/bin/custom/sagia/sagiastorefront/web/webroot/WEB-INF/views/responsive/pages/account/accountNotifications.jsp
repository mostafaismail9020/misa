<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script src = "${MIGS_Session_JS}"></script>
<script>
    var notificationJs = ${notificationJson};
</script>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="account.notifications.title"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification p-0">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png" style="margin-top: -3px;margin-left: -3px;"/>
                            </button>
                        </c:if>
                    </div>
                    <!--<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.png"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                    </div>-->
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
<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup" style="top:55%;right:10%">
    <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
    <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
    <div class="sagiaNavigation-subPane-actions">
        <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
    </div>
</div>

<!--
<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/Header-banner-image1.jpg" alt='${imageIcon.altText}' title='${imageIcon.altText}' style="">
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="account.notifications.title"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <div class="calendar"><img alt="" class=" profile-icon-images" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Calender.png" /> </div>
                <div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                    <c:if test="${hasLicense or hasAwaitingPayment}">
                        <button class="sagiaNavigation-btn js-sagiaNavigationToggle btnNotifications messages" title="<spring:message code='account.notifications.yourMessages'/>">
                            <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span><img alt="" class="" src="${commonResourcePath}/images/dashboard-media/Profile-bar/message.png" />
                        </button>
                    </c:if>
                    <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                    <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible">
                        <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                        <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                        <div class="sagiaNavigation-subPane-actions">
                            <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                        </div>
                    </div>
                </div>
                <div class="profile"><img alt="" src="${commonResourcePath}/images/dashboard-media/Profile-bar/Account-User-icon.png" /> </div>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_noPaddingTop mt-5">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row">
                <div class="searchInputBox searchInputBox_inline searchInputBox_inline_aside">
                    <input class="searchInputBox-input  text-right" onkeyup="filterHistory(this)" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_noPaddingTop">
    <div class="container contentModule d-flex">
        <hr class="contentModule-separator contentModule-separator_green"/>
        <div class="expandableContent expanded col-4 p-0">
            <div class="expandableContent-aside d-block">
                <div class="panelModule sagia-subPane">
                    <div class="contentModule m--30">
                        <div class="contentModule-section_noDivider contentModule-section_noMargin m-0">
                            <h2 class=" contentModule-headline_noMargin clr_gld text-uppercase px-5 pb-3"><spring:theme code="account.notifications.yourMessages"/></div>
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_right contentModule-actions_noMargin px-5">
                                <a id="readAllNotificationsButton" class=" btn_link_text"><spring:theme code="account.notifications.markAll"/></a>
                            </div>

                            <ul id="history-list" class="notificationList notificationList_smallMarginTop m-0">
                                <c:forEach items="${notifications}" var="notifItem" varStatus="item">
                                    <c:choose>
                                        <c:when test="${empty notifItem.readDate}">
                                            <li style="cursor: pointer;" class="notificationList-item notificationList-item_unread" data-transaction-id="${notifItem.transactionId}">
                                        </c:when>
                                        <c:otherwise>
                                            <li style="cursor: pointer;" class="notificationList-item" data-transaction-id="${notifItem.transactionId}">
                                        </c:otherwise>
                                    </c:choose>
                                    <a class="notificationList-link">
                                        <div class="notificationList-thumb">
                                            <c:choose>
                                                <c:when test="${notifItem.notificationType eq 'WL' ||  notifItem.notificationType eq 'NO'}">
                                                    <span class="iconElement iconElement_expertProfile_green"><icon:expertProfile/></span>
                                                </c:when>
                                                <c:when test="${notifItem.notificationType eq 'SY' || notifItem.notificationType eq 'Rest'}">
                                                    <span class="iconElement"> <img alt="" src="${commonResourcePath}/images/Survey-list-icon.png"/></span>
                                                </c:when>
                                                <c:when test="${notifItem.notificationType eq 'VI'}">
                                                    <span class="iconElement"><img alt="" src="${commonResourcePath}/images/Appointment-list-icon.png"/></span>
                                                </c:when>
                                                <c:when test="${notifItem.notificationType eq 'PY'}">
                                                    <span class="iconElement"><icon:payments/></span>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="notificationList-note">
                                            <div class="notificationList-details">
                                                <h5 class="notificationList-name">${notifItem.createdBy}</h5>
                                                <div class="notificationList-date">
                                                    <c:if test="${not empty notifItem.createdOn}">
                                                        ${notifItem.createdOn.dateFormatted}
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="notificationList-details">
                                                <span class="notificationList-title font-14">
                                                  Request ID:&nbsp; <span class="clr_gld font-14">${notifItem.notificationText.replaceAll("\\D+","")}</span>
                                                </span>
                                                Service Name: &nbsp;<span class="notificationList-status text-uppercase clr_gld font-14"> ${notifItem.notificationText.replaceAll("\\d","")}</span>

                                                </span>
                                            </div>
                                        </div>
                                        <img class="message-right-arrow ml-3" alt="" src="${commonResourcePath}/images/arrow-left.png"/>
                                    </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="expandableContent-main col-8 p-0">
                <div class="panelModule sagia-subPane panelModule_smallMargin notification-body">
                    <div class="contentModule">
                        <div class="contentModule-section_noDivider contentModule-section_noMargin">
                            <ul class="messageList">
                                <li class="messageList-item">
                                    <div class="messageList-header">
                                        <div class="messageList-header-details">
                                            <div class="messageList-img messageList-img_large">
                                                <!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
                                                <span class="notificationIconSpan iconElement iconElement_expertProfile_green">
                                                   <c:choose>
                                                       <c:when test="${not empty notification}">
                                                           <c:choose>
                                                               <c:when test = "${notification.notificationType eq 'WL' || notification.notificationType eq 'NO'}">
                                                                   <span class="notificationIconSpan iconElement iconElement_expertProfile_green">
                                                                       <icon:expertProfile/>
                                                                   </span>
                                                               </c:when>

                                                               <c:when test = "${notification.notificationType eq 'SY' || notification.notificationType eq 'Rest'}">
                                                                   <span class="notificationIconSpan iconElement">
                                                                       <icon:user_system/>
                                                                   </span>
                                                               </c:when>

                                                               <c:when test = "${notification.notificationType eq 'VI'}">
                                                                   <span class="notificationIconSpan iconElement">
                                                                       <icon:user_calendar/>
                                                                   </span>
                                                               </c:when>

                                                               <c:when test = "${notification.notificationType eq 'PY'}">
                                                                   <span class="notificationIconSpan iconElement">
                                                                       <%-- TODO: fit the icon into the background frame --%>
                                                                       <icon:payments/>
                                                                   </span>
                                                               </c:when>
                                                           </c:choose>
                                                       </c:when>
                                                   </c:choose>
                                               </span>
                                            </div>
                                            <div class="messageList-header-details-content">
                                                <h2 class="contentModule-headline_noMargin clr_gld " id="createdByDiv">
                                                    <c:choose>
                                                        <c:when test="${not empty notification}">${notification.createdBy}</c:when>
                                                    </c:choose>
                                                </h2>
                                                <div class="contentModule-subheadline contentModule-subheadline_small" id="createdOnDiv">
                                                    <c:choose>
                                                        <c:when test="${not empty notification}">
                                                            ${notification.createdOn.dateFormatted}
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                        <a style="display: none" id="printButton"><spring:theme code="account.notifications.print.label" /> <span
                                                class="iconElement iconElement_print"><icon:print /></span></a>
                                        <a style="display: none" id="participateButton"><spring:theme code="account.notifications.participate.label" /></a>
                                        <a style="display: none" id="payButton"><spring:theme code="payment.pay" /></a>
                                        <c:choose>
                                            <c:when test = "${notification.notificationType eq 'WL'}">
                                                <c:url value="/my-sagia/notifications/print/${firstNotification.transactionId}" var="printNotif"/>
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a href="${printNotif}" class="print-not-link  btn btn_round btn-dashboard btn_slim"><spring:theme code="account.notifications.print.label"/> <span class="iconElement iconElement_print"><icon:print/></span></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'NO'}">
                                                <c:url value="/my-sagia/notifications/print" var="printNotif"/>
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a href="${printNotif}" class="print-not-link  btn btn_round btn-dashboard btn_slim"><spring:theme code="account.notifications.print.label"/> <span class="iconElement iconElement_print"><icon:print/></span></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'SY'}">
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a href="${encodedContextPath}/my-sagia/questionnaires/${notification.transactionId}" class="print-not-link btn btn_round btn-dashboard btn_slim"><spring:theme code="account.notifications.participate.label" /></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'PY'}">
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a onclick="SAGIA.payment.requestPayment(notificationJs)" class="print-not-link btn btn_round btn-dashboard btn_slim"><spring:theme code="payment.pay" /></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="print-not messageList-header-actions hidden">
                                                    <div>
                                                        <a href="#" class="print-not-link  btn btn_round btn-dashboard btn_slim"></a>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="pay-not messageList-header-actions hidden">
                                            <div>
                                                <a class="pay-link btn btn_outline btn_round btn_slim"><spring:theme code="payment.pay" /></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="messageList-content messageList-content_standalone">
                                        <div class="messageList-message">
                                            <p id="notificationTextParagraph">
                                                <c:choose>
                                                <c:when test="${not empty notification}">
                                            <p>${notification.notificationText}</p><br>

                                            <c:if test="${notification.notificationType eq 'PY'}">
                                                <c:forEach var="entry" items="${notification.paymentItems}">
                                                    <p style="font-weight: 700">${entry.key}</p>
                                                    <p>Items</p>
                                                    <c:forEach var="item" items="${entry.value}">
                                                        <p style="margin-left:5em">${item.description} : ${item.currency}&nbsp;${item.value}</p>
                                                    </c:forEach>
                                                </c:forEach>
                                            </c:if>
                                            </c:when>
                                            </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<payment:paymentModal/>
 