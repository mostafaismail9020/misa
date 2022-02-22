<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="modal" tagdir="/WEB-INF/tags/responsive/modals" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>


<a href="#" class="realTimeOnlineSupport-btn active js-realTimeOnlineSupport-btn js-realTimeOnlineSupport">
    <div class="realTimeOnlineSupport-btn-icon"><icon:chat/></div>
    <div class="realTimeOnlineSupport-btn-text"><spring:theme code="realTimeOnlineSupport.contactUs"/></div>
    <div class="realTimeOnlineSupport-btn-online" style="display: none;"><span><spring:theme
            code="realTimeOnlineSupport.time"/></span> <spring:theme code="realTimeOnlineSupport.online"/> <em></em>
    </div>
</a>

<div class="realTimeOnlineSupport js-realTimeOnlineSupport">
    <div class="realTimeOnlineSupport-headline"><spring:theme code="realTimeOnlineSupport.help"/></div>

    <div class="realTimeOnlineSupport-row">
        <div class="realTimeOnlineSupport-col">
            <a href="#" class="realTimeOnlineSupport-item js-realTimeOnlineSupportCall-open">
                <div class="realTimeOnlineSupport-item-icon"><icon:call/></div>
                <div class="realTimeOnlineSupport-item-title"><spring:theme
                        code="realTimeOnlineSupportCall.title"/></div>
                <div class="realTimeOnlineSupport-item-text"><spring:theme
                        code="realTimeOnlineSupportCall.text.firstLine"/><br><spring:theme
                        code="realTimeOnlineSupportCall.text.secondLine"/></div>
            </a>
        </div>
        <div class="realTimeOnlineSupport-col">
            <a href="#" class="realTimeOnlineSupport-item j-realTimeOnlineSupport-enquiry">
                <div class="realTimeOnlineSupport-item-icon"><icon:contactUs-file/></div>
                <div class="realTimeOnlineSupport-item-title"><spring:theme code="realTimeOnlineSupport.enquiry"/></div>
                <div class="realTimeOnlineSupport-item-text"><spring:theme
                        code="realTimeOnlineSupport.enquiry.question"/><br><spring:theme
                        code="realTimeOnlineSupport.enquiry.help"/></div>
            </a>
        </div>
        <div class="realTimeOnlineSupport-col">
            <a href="#" class="realTimeOnlineSupport-item js-realTimeOnlineSupportChatList-open">
                <div class="realTimeOnlineSupport-item-icon"><icon:chat/></div>
                <div class="realTimeOnlineSupport-item-title"><spring:theme
                        code="realTimeOnlineSupportChatList.title"/></div>
                <div class="realTimeOnlineSupport-item-text"><spring:theme
                        code="realTimeOnlineSupportChatList.text"/></div>
            </a>
        </div>
        <div class="realTimeOnlineSupport-col">
            <a href="#" class="realTimeOnlineSupport-item js-realtimeOnlineSupportEmailUs">
                <div class="realTimeOnlineSupport-item-icon"><icon:contactUs-message/></div>
                <div class="realTimeOnlineSupport-item-title"><spring:theme
                        code="realtimeOnlineSupportEmailUs.title"/></div>
                <div class="realTimeOnlineSupport-item-text"><spring:theme
                        code="realtimeOnlineSupportEmailUs.text"/></div>
            </a>
        </div>
    </div>
</div>

<div class="realTimeOnlineSupportCall  js-realTimeOnlineSupportCall">
    <button class="btn btn_link realTimeOnlineSupportCall-close js-realTimeOnlineSupportCall-close">
        <icon:close/></button>
    <div class="realTimeOnlineSupportCall-page js-realTimeOnlineSupportCall-page1 active">
        <div class="realTimeOnlineSupportCall-icon"><icon:call/></div>
        <div class="realTimeOnlineSupportCall-title"><spring:theme code="realTimeOnlineSupportCall.title"/></div>
        <div class="realTimeOnlineSupportCall-text"><spring:theme
                code="realTimeOnlineSupportCall.text.firstLine"/><br><spring:theme
                code="realTimeOnlineSupportCall.text.secondLine"/></div>

        <div class="realTimeOnlineSupportCall-tel">
            <spring:theme code="realTimeOnlineSupportCall.tel.local"/> <strong><a href="#"
                                                                                  class="js-realTimeOnlineSupportCallLocal"></a></strong>
            <br>
            <spring:theme code="realTimeOnlineSupportCall.tel.international"/> <strong><a href="#"
                                                                                          class="js-realTimeOnlineSupportCallInternational"></a></strong>
        </div>

        <div class="realTimeOnlineSupportCall-actions">
            <a href="#" class="js-realTimeOnlineSupportCallNow btn btn_round"><spring:theme code="realTimeOnlineSupportCall.now"/></a>
            <c:if test="${user.uid ne 'anonymous'}">
                <button class="btn btn_round js-realTimeOnlineSupportCall-page2-open"><spring:theme
                        code="realTimeOnlineSupportCall.schedule"/></button>
            </c:if>
        </div>

        <%--<div class="realTimeOnlineSupportCall-queue">2 Customers are waiting on queue</div>--%>
    </div>

    <div class="realTimeOnlineSupportCall-page js-realTimeOnlineSupportCall-page2">
        <form:form method="post" id="formAvailableSlot" modelAttribute="realTimeScheduleForm"
                   action="${encodedContextPath}/realtime/create">
            <div class="realTimeOnlineSupportCall-icon"><icon:call/></div>
            <div class="realTimeOnlineSupportCall-title"><spring:theme
                    code="realTimeOnlineSupportCall.available.slot"/></div>
            <div class="formInputBox formInputBox_group">
                <div class="form-group">
                    <form:input path="dateStart" cssClass="form-control js-form-control_date flatpickr-input"
                                placeholder="."/>
                        <%--<input class="form-control js-form-control_date" placeholder="." value="" type="text">--%>
                    <label class="control-label" for="dateStart"><spring:theme
                            code="realTimeOnlineSupportCall.start"/></label>
                    <div class="formInputBox-append" id="calendar-icon-pos"><span class="formInputBox-text"><icon:calendar-gray/></span></div>
                </div>
            </div>
            <div class="formInputBox formInputBox_group">
                <div class="form-group">
                    <form:input path="timeStart" cssClass="form-control js-form-control_timeslot" placeholder="."/>
                        <%--<input class="form-control js-form-control_timeslot" placeholder="." value="" type="text">--%>
                    <label class="control-label" for="timeStart"><spring:theme
                            code="realTimeOnlineSupportCall.available.slot.v2"/></label>
                    <div class="formInputBox-append" id="calendar-icon-pos">
                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                    </div>
                </div>
            </div>

            <formElement:formSelectBox idKey="realtime.callback.country"
                                       labelKey="register.country"
                                       selectCSSClass="form-control countriesselect.realtime jqCountry js-select2-searchBegining js-select2-sortAlphabetically"
                                       labelCSS="control-label_mandatory"
                                       path="countryCode" mandatory="true" skipBlank="false"
                                       skipBlankMessageKey="form.select.empty" items="${countries}" itemValue="code"/>

            <div class="formInputBox-split">
                <formElement:formInputBox idKey="realtime.callback.mobileCountryCode"
                                          labelKey="register.mobileCountryCode" path="mobileCountryCode"
                                          inputCSS="form-control_preNumber js-mobile-coutry-code"/>
                <formElement:formInputBox idKey="realtime.callback.mobileNumber"
                                          labelKey="register.mobileNumber" path="mobileNumber"
                                          helpBlockSuccessCSS="js-help-block-success"
                                          inputCSS="form-control js-quick-mobile-number"
                                          labelCSS="control-label_mandatory" inputBoxCSS="formInputBox_big"
                                          mandatory="true"/>
            </div>

            <c:if test="${user.uid ne 'anonymous'}">
                <div class="realTimeOnlineSupportCall-actions">
                    <button type="submit" class="btn btn_round js-realTimeOnlineSupportCall-page3-open"><spring:theme
                            code="realTimeOnlineSupportCall.schedule"/></button>
                </div>
            </c:if>
        </form:form>
    </div>

    <div class="realTimeOnlineSupportCall-page js-realTimeOnlineSupportCall-page3">
        <div class="realTimeOnlineSupportCall-icon">
            <icon:contactUs-schedule-a-call/>
        </div>
        <div class="realTimeOnlineSupportCall-title"><spring:theme
                code="realTimeOnlineSupportCall.schedule.success"/></div>

        <div class="realTimeOnlineSupportCall-slot">
            <div class="realTimeOnlineSupportCall-slot-name"><spring:theme
                    code="realTimeOnlineSupportCall.timeSlot"/></div>
            <div class="realTimeOnlineSupportCall-slot-value js-timeslot-value">9:00 - 9:30</div>
        </div>

        <div class="realTimeOnlineSupportCall-slot">
            <div class="realTimeOnlineSupportCall-slot-name"><spring:theme code="realTimeOnlineSupportCall.date"/></div>
            <div class="realTimeOnlineSupportCall-slot-value js-date-value">04.04.2018</div>
        </div>
    </div>
</div>

<!-- NOT IMPLEMENTED -->
<div class="realTimeOnlineSupportChatList js-realTimeOnlineSupportChatList">
    <button class="btn btn_link realTimeOnlineSupportChatList-close js-realTimeOnlineSupportChatList-close">
        <icon:close/></button>
    <div class="realTimeOnlineSupportChatList-head"><icon:chat/><spring:theme
            code="realTimeOnlineSupportChat.liveChat"/></div>

    <div class="realTimeOnlineSupportChatList-list">
        <a href="#" class="realTimeOnlineSupportChatList-list-item">
            <div class="realTimeOnlineSupportChatList-list-arrow"><icon:contactUs-arrow/></div>
            <div class="realTimeOnlineSupportChatList-list-image"><icon:contact-person/></div>
            <div class="realTimeOnlineSupportChatList-list-body">
                <div class="realTimeOnlineSupportChatList-list-name">
                    <div class="realTimeOnlineSupportChatList-list-status online"><spring:theme
                            code="realTimeOnlineSupport.online"/></div>
                    Steve Rodriguez
                </div>
                <div class="realTimeOnlineSupportChatList-list-msg">
                    <div class="realTimeOnlineSupportChatList-list-msg-icon"><icon:read/></div>
                    Lorem ipsum dolor sit amet.
                </div>
            </div>
        </a>
        <a href="#" class="realTimeOnlineSupportChatList-list-item">
            <div class="realTimeOnlineSupportChatList-list-arrow"><icon:contactUs-arrow/></div>
            <div class="realTimeOnlineSupportChatList-list-image"><icon:contact-person/></div>
            <div class="realTimeOnlineSupportChatList-list-body">
                <div class="realTimeOnlineSupportChatList-list-name">
                    <div class="realTimeOnlineSupportChatList-list-status">8h ago</div>
                    Miles Thompson
                </div>
                <div class="realTimeOnlineSupportChatList-list-msg">
                    <div class="realTimeOnlineSupportChatList-list-msg-icon">
                        <icon:read/>
                    </div>
                    Lorem ipsum dolor sit amet, <br>conseter disedple eseo aleh…
                </div>
            </div>
        </a>
        <a href="#" class="realTimeOnlineSupportChatList-list-item">
            <div class="realTimeOnlineSupportChatList-list-arrow"><icon:contactUs-arrow/></div>
            <div class="realTimeOnlineSupportChatList-list-image"><icon:contact-person/></div>
            <div class="realTimeOnlineSupportChatList-list-body">
                <div class="realTimeOnlineSupportChatList-list-name">
                    <div class="realTimeOnlineSupportChatList-list-status">8h ago</div>
                    Holly Bethford
                </div>
                <div class="realTimeOnlineSupportChatList-list-msg">
                    <div class="realTimeOnlineSupportChatList-list-msg-icon"><icon:read/></div>
                    Lorem ipsum
                </div>
            </div>
        </a>

        <a href="#" class="realTimeOnlineSupportChatList-list-item">
            <div class="realTimeOnlineSupportChatList-list-arrow"><icon:contactUs-arrow/></div>
            <div class="realTimeOnlineSupportChatList-list-image"><icon:contact-person/></div>
            <div class="realTimeOnlineSupportChatList-list-body">
                <div class="realTimeOnlineSupportChatList-list-name">
                    <div class="realTimeOnlineSupportChatList-list-status">8h ago</div>
                    Holly Bethford
                </div>
                <div class="realTimeOnlineSupportChatList-list-msg">
                    <div class="realTimeOnlineSupportChatList-list-msg-icon"><icon:read/></div>
                    Lorem ipsum
                </div>
            </div>
        </a>
    </div>

    <div class="realTimeOnlineSupportChatList-actions">
        <button class="btn btn_round js-realTimeOnlineSupportChatList-openChat"><spring:theme
                code="realTimeOnlineSupportChat.title"/></button>
    </div>

    <div class="realTimeOnlineSupportChatList-queue">2<spring:theme
            code="realTimeOnlineSupportChat.waiting.queue"/></div>
</div>

<!-- NOT IMPLEMENTED -->
<div class="realTimeOnlineSupportChat js-realTimeOnlineSupportChat">
    <button class="btn btn_link realTimeOnlineSupportChat-close js-realTimeOnlineSupportChat-close">
        <icon:close/></button>
    <div class="realTimeOnlineSupportChat-entry-wrapper">
        <div class="realTimeOnlineSupportChat-entry">
            <div class="realTimeOnlineSupportChat-entry-name">
                <div class="realTimeOnlineSupportChat-entry-status online">Online</div>
                Holly Bethford
            </div>
            <div class="realTimeOnlineSupportChat-entry-text">
                <spring:theme code="realTimeOnlineSupportChat.salutation"/> <br>
                <spring:theme code="realTimeOnlineSupportChat.helpMessage"/> <br>
                <spring:theme code="realTimeOnlineSupportChat.support"/>
            </div>
        </div>
    </div>

    <div class="realTimeOnlineSupportChat-input	">
        <div class="realTimeOnlineSupportChat-input-actions">
            <button class="btn btn_link">
                <icon:contactUs-trigger-attach/>
            </button>
            <button class="btn btn_link">
                <icon:contactUs-trigger-camera/>
            </button>
            <button class="btn btn_link">
                <icon:contactUs-trigger-face/>
            </button>
        </div>
        <textarea></textarea>
    </div>
</div>

<!-- NOT IMPLEMENTED -->
<div class="realTimeOnlineSupportChatDone js-realTimeOnlineSupportChatDone">
    <button class="btn btn_link realTimeOnlineSupportChatDone-close js-realTimeOnlineSupportChatDone-close">
        <icon:close/></button>
    <div class="realTimeOnlineSupportChatDone-head"><icon:contactUs-rating/></div>
    <div class="realTimeOnlineSupportChatDone-title"><spring:theme
            code="realTimeOnlineSupportChatDone.enjoy.live.chat"/></div>
    <div class="realTimeOnlineSupportChatDone-text"><spring:theme
            code="realTimeOnlineSupportChatDone.rate.service"/></div>
    <div class="ratingModule">
        <div class="ratingModule-star active"><icon:rating-star/></div>
        <div class="ratingModule-star active"><icon:rating-star/></div>
        <div class="ratingModule-star active"><icon:rating-star/></div>
        <div class="ratingModule-star active"><icon:rating-star/></div>
        <div class="ratingModule-star"><icon:rating-star-empty/></div>
    </div>
</div>

<div class="realTimeOnlineSupport-wrapper js-realTimeOnlineSupport js-realTimeOnlineSupport-btn"></div>