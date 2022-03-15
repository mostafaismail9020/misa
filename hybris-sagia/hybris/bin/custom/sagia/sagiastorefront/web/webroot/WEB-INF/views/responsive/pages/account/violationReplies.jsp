<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>

<div class="mainSection mainSection">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="text.account.followup.violationReplies"/>
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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="text.account.followup.violationReplies"/></h1> -->
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
    </div>
</div>

<!-- <div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
            <div>
                <a href="violation-replies/create" class="btn btn_slim js-create-violation-reply">
                    <spring:theme code="text.account.followup.create"/>
                </a>
            </div>
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-xl-3 col-md-6 col-12 px-0">
                    <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-md-6 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                            <a href="violation-replies/create" class="btn btn_slim js-create-violation-reply back_to_service">
                                <spring:theme code="text.account.followup.create"/>
                            </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <div class="expandableContent" id="service-tab">
            
        </div>
    </div>
</div>
<c:if test="${not empty sagiaService.description || not empty sagiaService.serviceDocuments || not empty sagiaService.rulesRestrictions || not empty sagiaService.serviceFinancialFees || not empty sagiaService.serviceDuration}">  
    <div class="container">
        <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
            <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
            <div class=""><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
        </button>
    </div>
    <div class="container service-wrapper service-wrapper-info mb-5 expanded"  id="expand-03">
        <div class="serviceModule serviceModule_list mx-5 pt-4">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.description}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.service.overview"/> </span>	
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.description}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceDocuments}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.service.document"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDocuments}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.rulesRestrictions}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.rules.restrictions"/></span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.rulesRestrictions}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceFinancialFees}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.financial.fees"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceFinancialFees}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="serviceModule serviceModule_list mx-5 pb-4">
            <div class="serviceModule-section">
                <div class="serviceModule-content">
                    <div class="serviceModule-description">
                        <c:if test="${not empty sagiaService.serviceDuration}">
                            <span class="serviceModule-headline"> <spring:theme code="sagia.services.duration"/> </span>
                            <div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDuration}</p></div></div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <c:if test="${fn:length(replies) > 1}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div class=""><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent mt-3" id="expand01">
            <c:if test="${fn:length(replies) > 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline headline-text">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    <spring:theme code="text.account.followup.history"/></div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul class="historyList" id="history-list">
                                    <c:forEach items="${replies}" var="item" varStatus="letterStatus">
                                        <li class="historyList-item <c:if test="${item.current}">historyList-item_current</c:if>">
                                            <a href="#"
                                               class="historyList-link js-show-replies-details <c:if test="${item.current}">js-item-current</c:if>"
                                               data-id="${item.srId}"
                                               data-type="violation-replies">
                                                <div class="historyList-id">${item.srId}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">
                                                        ${item.srCrDate.dateFormatted}
                                                    </div>
                                                    <div class="historyList-status
                                                    <c:if test="${item.srStCode == 'E0001'}">historyList-status_process</c:if>
                                                    <c:if test="${item.srStCode == 'E0002'}">historyList-status_accepted</c:if>
                                                    <c:if test="${item.srStCode == 'E0003'}">historyList-status_rejected</c:if>
                                                ">${item.srStDesc}</div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="expandableContent-main js-violation-replies">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule contentModule-wrap">
                        <div class=" contentModule-section_noDivider contentModule-section_slimDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <!-- <icon:info/> -->
                                <!-- <div class="contentModule-headline headline-text">
                                    <spring:theme code="text.account.followup.info"/>:&nbsp;${selectedItem.srId}
                                </div> -->
                                <span class="contentModule-headline contentModule-headline_small "><spring:theme code="text.account.followup.info"/>:&nbsp;${selectedItem.srId}</span>
<!--
                                <a type="submit" href="violation-replies/create" class="btn btn_slim js-create-violation-reply">
                                    <spring:theme code="text.account.followup.create"/>
                                </a>
-->
                                <c:if test="${fn:length(replies) > 0}">
                                    <div class="statusIndicator
                                    <c:if test="${selectedItem.srStCode == 'E0001'}">statusIndicator_process</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0002'}">statusIndicator_accepted</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0003'}">statusIndicator_rejected</c:if>
                                ">
                                        <spring:theme code="text.account.followup.status"/> : <span>${selectedItem.srStDesc}</span>
                                    </div>
                                </c:if>
                                <div class="contentModule-headline-border"></div>
                            </div>

                            <div class="tableModule commentModule1">
                                <table class="tableModule-table">
                                    <thead class="tableModule-head">
                                    <tr>
                                        <th><spring:theme code="text.account.followup.description"/></th>
                                        <th><spring:theme code="text.account.followup.investorReply"/></th>
                                        <th><spring:theme code="text.account.followup.status"/></th>
                                    </tr>
                                    </thead>
                                    <tbody class="tableModule-body">
                                    <c:forEach items="${selectedItem.violation}" var="violation">
                                        <tr>
                                            <td class="tableModule-bodyItem-right">${violation.violationText}</td>
                                            <td>${violation.violationReplyText}</td>
                                            <td>${violation.violationStatusText}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>


                        </div>

                        <c:if test="${fn:length(replies) > 0}">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="commentModule commentModule-no-border">
                                    <c:forEach items="${selectedItem.followText}" var="comment">
                                        <div class="commentModule-window">
                                            <ul class="messageList">
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                        <!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
                                                        <span class="iconElement iconElement_expertProfile_white">
                                                            <icon:expertProfile/>
                                                        </span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                                        <h3 class="messageList-date">
                                                                ${comment.commentDate.dateFormatted}
                                                        </h3>
                                                        <div class="messageList-message">
                                                            <p>${comment.comments}</p>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <!-- <div class="contentModule-headline contentModule-headline_small">
                                <spring:theme code="text.account.followup.attachments"/>
                            </div> -->
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.attachments"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                            <div class="documentModule commentModule">
                                <c:forEach items="${selectedItem.contentHdr}" var="element">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="documentSection-item">
                                                <div class="documentSection-img">
                                                    <span class="iconElement iconElement_pdf02"><svg width="21" height="26"
                                                                                                     xmlns="http://www.w3.org/2000/svg"><g
                                                            fill-rule="nonzero" fill="none"><path
                                                            d="M6.533 15.579a.876.876 0 0 1-.453-.136c-.454-.362-.544-.724-.499-.996.09-.724.998-1.494 2.72-2.309.68-1.494 1.315-3.35 1.723-4.89-.453-.996-.907-2.263-.59-2.988a.855.855 0 0 1 .5-.543c.09-.045.362-.09.453-.09.226 0 .408.271.59.452.135.181.452.544-.182 3.079.59 1.268 1.496 2.535 2.312 3.44.59-.09 1.088-.18 1.541-.18.726 0 1.134.18 1.315.498.136.271.09.588-.181.996-.272.362-.59.543-.998.543-.544 0-1.178-.362-1.904-1.041-1.27.271-2.765.77-3.99 1.267-.362.815-.725 1.45-1.087 1.947-.454.68-.862.951-1.27.951zm1.224-2.309c-.952.543-1.36.996-1.405 1.222 0 .046 0 .136.181.317.09 0 .499-.18 1.224-1.539zm6.166-1.992c.363.272.453.407.68.407.09 0 .408 0 .544-.18.045-.091.09-.137.09-.182-.045-.045-.135-.09-.543-.09-.182-.046-.454 0-.771.045zm-3.4-2.988c-.318 1.132-.771 2.309-1.224 3.44.952-.362 1.994-.678 2.947-.905-.59-.77-1.18-1.63-1.723-2.535zM10.25 4.44c-.046 0-.59.815.045 1.45.453-.952 0-1.45-.045-1.45z"
                                                            fill="#5CC83B"></path><path
                                                            d="M19.998 5.89L14.739.638c-.09-.09-.181-.135-.272-.135H1.32c-.363 0-.68.316-.68.86v24.041c0 .135.271.452.634.452h18.18c.363 0 .635-.317.635-.452V6.388c.045-.317 0-.407-.091-.498zM1.546 18.16V1.408H14.15v5.07h5.077V18.16H1.547z"
                                                            fill="#5CC83B"></path><path
                                                            d="M5.581 24.498h-.725v-4.573H6.17c.182 0 .409.046.59.091s.363.136.499.272c.136.135.272.271.362.452.09.181.136.363.136.589 0 .226-.045.453-.136.634-.09.18-.181.362-.317.453-.136.09-.317.271-.544.316-.227.046-.408.091-.635.091h-.544v1.675zm0-3.984v1.811h.68c.09 0 .182 0 .272-.045.09-.046.182-.09.227-.136a.947.947 0 0 0 .181-.272c.046-.09.046-.271.046-.453 0-.09 0-.18-.046-.271 0-.09-.045-.181-.136-.272a.464.464 0 0 0-.272-.226c-.136-.045-.272-.09-.453-.09l-.499-.046zM12.29 22.099c0 .362-.044.679-.135.95-.09.272-.181.498-.317.68a1.73 1.73 0 0 1-.409.407c-.136.09-.317.181-.453.226-.136.046-.272.09-.408.09H8.573v-4.527h1.36c.363 0 .726.046.998.182.272.135.544.271.725.498.182.226.318.452.454.679.136.271.18.543.18.815zm-2.175 1.856c.498 0 .861-.181 1.088-.498.226-.317.317-.77.317-1.404 0-.18 0-.362-.045-.543a1.079 1.079 0 0 0-.272-.498 1.63 1.63 0 0 0-.544-.362c-.227-.09-.499-.136-.862-.136h-.453v3.44h.77zM14.15 20.514v1.449h1.904v.498H14.15v2.037h-.771v-4.573h2.856v.544H14.15z"
                                                            fill="#FFF"></path></g></svg></span>
                                                </div>
                                                <div class="documentSection-name js-download-attachments cursor-pointer"
                                                     data-attachments-object="${element.objectId}"
                                                     data-attachments-file="${element.documentID}">${element.fileName}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="failInformationModal"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title js-message"><spring:theme code="text.account.followup.error"/></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body modal-body-center">
                <!-- <div class="modal-heroImage image-medium">
                    <icon:status-cancelled/>
                </div> -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="text.account.followup.close"/></button>
            </div>
        </div>
    </div>
</div>
