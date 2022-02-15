<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="warningletter.extension"/>
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

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="warningletter.extension"/></h1> -->
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


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row renewal-services w-100">
                <div class="col-md-3 col-12 px-0">
                    <a href="/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
                <div class="col-xl-3 col-12">
                    <div class="mainSection-linkActions mainSection-linkActions_right">
                        <a href="warning-letters/create" type="submit" class="btn btn_slim js-create-warning-letter back_to_service">
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
<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
    <c:if test="${fn:length(warningLetters) > 1}">
        <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
            <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
            <div><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
        </button>
    </c:if>
        <div class="expandableContent expanded" id="expand01">
            <c:if test="${fn:length(warningLetters) > 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline headline-text">
                                    <span class="iconElement iconElement_history"><icon:history/></span>
                                    <spring:theme code="text.account.followup.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>" onkeypress="filterHistory(this)"/>
                                </div>
                                <ul class="historyList" id="history-list">
                                    <c:forEach items="${warningLetters}" var="item" varStatus="letterStatus">
                                        <li class="historyList-item <c:if test="${item.current}">historyList-item_current</c:if>">
                                            <a href="#"
                                               class="historyList-link js-show-letters-details <c:if test="${item.current}">js-item-current</c:if>"
                                               data-id="${item.srId}"
                                               data-type="warning-letters">
                                                <div class="historyList-id">${item.srId}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${item.srCrDate.dateFormatted}</div>
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
            <div class="expandableContent-main js-warning-letters">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule contentModule-wrap">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">

                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline">
                                    <spring:theme code="text.account.followup.info"/>: ${selectedItem.srId}
                                </span>
                                
<!--
                                <a href="warning-letters/create" type="submit" class="btn btn_slim js-create-warning-letter">
                                    <spring:theme code="text.account.followup.create"/>
                                </a>
-->
                                <c:if test="${fn:length(warningLetters) > 0}">
                                    <div class="statusIndicator
                                    <c:if test="${selectedItem.srStCode == 'E0001'}">statusIndicator_process</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0002'}">statusIndicator_accepted</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0003'}">statusIndicator_rejected</c:if>
                                ">
                                        <spring:theme code="text.account.followup.status"/>: <span>${selectedItem.srStDesc}</span>
                                    </div>
                                </c:if>
                                <div class="contentModule-headline-border"></div>
                            </div>


                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt class="headline-golden">
                                            <spring:theme code="text.account.followup.requestedDaysNumber"/>
                                        </dt>
                                        <dd>${selectedItem.noOfDaysExtension}</dd>
                                        <dt class="headline-golden">
                                            <spring:theme code="text.account.followup.extendedDate"/>
                                        </dt>
                                        <dd>${selectedItem.extWlDate.dateFormatted}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <dt class="headline-golden">
                                            <spring:theme code="text.account.followup.approvedDaysNumber"/>
                                        </dt>
                                        <dd>${selectedItem.noOfApprDaysExtn}</dd>
                                        <dt class="headline-golden">
                                            <spring:theme code="text.account.followup.extendedEndDate"/>
                                        </dt>
                                        <dd>${selectedItem.extWlEndDate.dateFormatted}</dd>
                                    </dl>
                                </div>
                            </div>
                        </div>

                        <c:if test="${fn:length(warningLetters) > 0}">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline_small">
                                    <spring:theme code="text.account.followup.comments"/>
                                </div>
                                <div class="commentModule">
                                    <div class="commentModule-window">
                                        <ul class="messageList">
                                            <c:forEach items="${selectedItem.followText}" var="comment">
                                                <li class="messageList-item">
                                                    <div class="messageList-img">
                                                        <!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
                                                        <span class="iconElement iconElement_expertProfile_white">
													<icon:expertProfile/>
												</span>
                                                    </div>
                                                    <div class="messageList-content">
                                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                                        <h3 class="messageList-date">${comment.commentDate}</h3>
                                                        <div class="messageList-message">
                                                            <p>${comment.comments}</p>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>

                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                            <!-- <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                                <div class="contentModule-headline contentModule-headline_bordered headline-text">
                                    <spring:theme code="text.account.followup.supportDocuments"/>
                                </div>
                            </div> -->
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>

                            <ul class="downloadList downloadList_maxHeight">
                                <c:forEach items="${selectedItem.contentHdr}" var="element">
                                    <li class="downloadList-item">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                ${element.fileName}
                                        </div>
                                        <div class="downloadList-actions">
                                                <%--<a href="#" class="link link_nowrap" data-attachments-object="a" data-attachments-file="b">--%>
                                            <a href="${encodedContextPath}/attachment/pdf/${element.objectId}/${element.documentID}" class="link link_nowrap"
                                            			download="${element.fullFileName}">
                                                <span class="iconElement iconElement_cloud"><icon:download/></span>
                                                <spring:theme code="text.account.followup.download"/>
                                            </a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
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
            </div>
            <div class="modal-body modal-body-center">
                <div class="modal-heroImage image-medium">
                    <!-- <icon:status-cancelled/> -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="text.account.followup.close"/></button>
            </div>
        </div>
    </div>
</div>
