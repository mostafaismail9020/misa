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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="account.notifications.title"/></h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row">
                <div class="searchInputBox searchInputBox_inline searchInputBox_inline_aside">
                    <input class="searchInputBox-input" onkeyup="filterHistory(this)" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container contentModule">
        <hr class="contentModule-separator contentModule-separator_green"/>
        <div class="expandableContent expanded">
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline contentModule-headline_noMargin"><spring:theme code="account.notifications.yourMessages"/></div>
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_right contentModule-actions_noMargin">
                                <button id="readAllNotificationsButton" class="btn btn_slim btn-secondary btn_link btn_link_text"><spring:theme code="account.notifications.markAll"/></button>
                            </div>

                            <ul id="history-list" class="notificationList notificationList_smallMarginTop">
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
                                                    <span class="iconElement"><icon:user_system/></span>
                                                </c:when>
                                                <c:when test="${notifItem.notificationType eq 'VI'}">
                                                    <span class="iconElement"><icon:user_calendar/></span>
                                                </c:when>
                                                <c:when test="${notifItem.notificationType eq 'PY'}">
                                                    <span class="iconElement"><icon:payments/></span>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="notificationList-note">
                                            <div class="notificationList-details">
                                                <div class="notificationList-name">${notifItem.createdBy}</div>
                                                <div class="notificationList-date">
                                                    <c:if test="${not empty notifItem.createdOn}">
                                                        ${notifItem.createdOn.dateFormatted}
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="notificationList-details">
                                                <div class="notificationList-title">
                                                        ${notifItem.notificationText}
                                                </div>
                                                <div class="notificationList-status"></div>
                                            </div>
                                        </div>
                                    </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
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
                                                <div class="contentModule-headline contentModule-headline_noMargin" id="createdByDiv">
                                                    <c:choose>
                                                        <c:when test="${not empty notification}">${notification.createdBy}</c:when>
                                                    </c:choose>
                                                </div>
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
                                                        <a href="${printNotif}" class="print-not-link btn btn_outline btn_round btn_slim"><spring:theme code="account.notifications.print.label"/> <span class="iconElement iconElement_print"><icon:print/></span></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'NO'}">
                                                <c:url value="/my-sagia/notifications/print" var="printNotif"/>
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a href="${printNotif}" class="print-not-link btn btn_outline btn_round btn_slim"><spring:theme code="account.notifications.print.label"/> <span class="iconElement iconElement_print"><icon:print/></span></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'SY'}">
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a href="${encodedContextPath}/my-sagia/questionnaires/${notification.transactionId}" class="print-not-link btn btn_outline btn_round btn_slim"><spring:theme code="account.notifications.participate.label" /></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test = "${notification.notificationType eq 'PY'}">
                                                <div class="print-not messageList-header-actions">
                                                    <div>
                                                        <a onclick="SAGIA.payment.requestPayment(notificationJs)" class="print-not-link btn btn_outline btn_round btn_slim"><spring:theme code="payment.pay" /></a>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="print-not messageList-header-actions hidden">
                                                    <div>
                                                        <a href="#" class="print-not-link btn btn_outline btn_round btn_slim"></a>
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