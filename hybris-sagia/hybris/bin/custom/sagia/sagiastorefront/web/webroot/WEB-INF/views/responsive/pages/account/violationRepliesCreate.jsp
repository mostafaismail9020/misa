<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsApplyModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>

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

<!-- <div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="text.account.followup.violationReplies"/></h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${formName}"
                        data-service-id="${serviceId}"><spring:theme code="general.savedraft"/><span
                        class="iconElement iconElement_save"><icon:save/></span></button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${formName}"
                        data-service-id="${serviceId}"><spring:theme code="general.loaddraft"/><span
                        class="iconElement iconElement_save"><icon:upload/></span></button>
            </div>
        </div>
    </div>
</div> -->


<div class="container mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="./" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack"><span class="iconElement iconElement_closeBack " id="image-pos-arrow"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span></span><spring:theme
                    code="text.account.followup.backViolationReplies"/></a>
        </div>
    </div>
</div>


<div class="container mainSection mainSection_dark mainSection_pdt16">
    <div class="">

        <form:form action="" id="${formName}" class="js-followup-form" modelAttribute="violationReplyForm">
          <input type="hidden" id="serviceId"/>
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.info"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col">


                                <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                    <div class="form-group">
                                        <div class="formRadioButton-label"><spring:theme
                                                code="text.account.followup.warningLetterNumber"/></div>
                                        <c:forEach items="${warningLetters}" var="letter" varStatus="indexStatus">
                                            <div class="form-item">
                                                <input id="${letter.number}-${indexStatus.index}" name="letterNumber" class="form-control js-vr-number" type="radio"
                                                       value="${letter.number}"
                                                       <c:if test="${indexStatus.first}">checked="checked"</c:if>>
                                                <label for="${letter.number}-${indexStatus.index}" class="control-label">
                                                    <span></span> ${letter.number}</label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>


            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
            
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="violation.violationstatus"/></span>
                                <div class="contentModule-headline-border"></div>
                           </div>
                        </div>


                        <div class="row">
                            <div class="col">
                                <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                    <div class="form-group js-violations-list">


                                        <c:forEach items="${warningLetter.violations}" var="violation" varStatus="violationStatus">
                                            <div class="form-item">
                                                <input id="violations[${violationStatus.index}]" name="violations[${violationStatus.index}]" class="form-control" placeholder="."
                                                       type="checkbox" value="${violation.violationKey}">
                                                <label class="control-label " for="violations[${violationStatus.index}]">
                                                    <span><icon:check/></span>
                                                    ${violation.violationText}
                                                </label>
                                                <div class="formCheckBox-detail formCheckBox-detail_textarea">
                                                    <div class="formTextArea">
                                                        <div class="form-group">
                                                            <textarea id="violationsTexts[${violationStatus.index}]" data-violation="${violation.violationKey}" name="violationsTexts[${violationStatus.index}]"
                                                                      class="form-control form-control_slim"
                                                                      placeholder="."></textarea>
                                                            <label class="control-label" for="violationsTexts[${violationStatus.index}]">
                                                                <spring:theme code="text.account.followup.comments"/>
                                                            </label>
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


            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.comments"/></span>
                                <div class="contentModule-headline-border"></div>
                           </div>
                        </div>

                        <div class="formTextArea">
                            <div class="form-group">
                                <textarea id="comments" required name="comments"  class="form-control js-form-element" placeholder=""></textarea>
                                    <label class="control-label control-label_mandatory" for="comments">
                                        <spring:theme code="text.account.followup.comments"/>
                                    </label>

                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <div class="contentModule contentModule-wrap">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                                <span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
                                <div class="contentModule-headline-border"></div>
                            </div>
                        </div>
                    </div>
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <formElement:uploadDocuments name="docs"/>
                    </div>
                </div>
            </div>


            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="FOLLOW_UP" id="termsAndConditions" path="termsAndConditionsChecked"/>
                    </div>
                </div>  
                <button type="button" class="btn btn_outline" onclick="window.history.back()">
                   <spring:theme code="general.cancel"/>
                </button>
                <button type="submit" class="btn js-followup-vr-create full-width-responsive">
                    <spring:theme code="general.submit"/>
                </button>              
            </div>
        </form:form>
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
                <div class="modal-heroImage image-medium">
                    <icon:status-cancelled/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="text.account.followup.close"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="successInformationModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
                <div class="modal-description">
                    <spring:theme code="specialservices.wewillreviewmessage"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim js-return"><spring:theme code="general.return"/></button>
            </div>
        </div>
    </div>
</div>