<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

<div class="mainSection mainSection">
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
                    <div class="calendar notification">
                        <div class="count-notification">123</div>
                        <a href="${encodedContextPath}/my-sagia/notifications">
                            <span></span>
                        </a>
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
            <h1 class="mainSection-headline"><spring:theme code="warningletter.extension"/></h1>
            
        </div>
    </div>
</div> -->

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="d-flex row renewal-services w-100">
            <div class="col-md-4">
                <a href="./" class="btn btn_leftIconLink btn_darkLink  w-100">
                    <span class="iconElement iconElement_closeBack"><icon:close/></span>
                    <spring:theme code="text.account.followup.backWarningLetters"/>
                </a>
            </div>
            <div class="col-md-8 d-flex btn-drafts_list amend-service-link">
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${formName}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.savedraft"/><span class="iconElement iconElement_save"><icon:save/></span>
                </button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${formName}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/><span class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <form:form action="" id="${formName}" class="js-followup-form" modelAttribute="createWarningLetterExtensionFormData">
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline headline-text">
                                <icon:info/>
                                <spring:theme code="general.info"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                    <div class="form-group">
                                        <div class="formRadioButton-label">
                                            <spring:theme code="text.account.followup.warningLetterNumber"/>
                                        </div>

                                        <c:forEach items="${warningLetters}" var="letter" varStatus="indexStatus">
                                            <div class="form-item">
                                                <input id="${letter.number}-${indexStatus.index}" name="letterNumber" class="form-control js-wl-number" type="radio"
                                                       data-end-date="${letter.endDate.dateFormatted}" value="${letter.number}"
                                                       <c:if test="${indexStatus.first}">checked="checked"</c:if>>
                                                <label for="${letter.number}-${indexStatus.index}" class="control-label">
                                                    <span></span> ${letter.number}
                                                </label>
                                            </div>
                                        </c:forEach>
                                        <input type="hidden" id="serviceId"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="contentModule-section js-warning-letter-extension-time">
                        <div class="row">
                            <div class="col-sm-3">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.followup.warningLetterEndDate"/></dt>
                                    <dd>${selectedWarningLetter.endDate.dateFormatted}</dd>
                                </dl>
                            </div>
                            <div class="col-sm-6">
                                <div class="formRangeSlider js-formRangeSlider">
                                    <div class="form-group">
                                        <input required name="daysExtension" type="range" min="0" max="100" value="0" step="1"
                                               class="js-days-range js-form-element" data-date="${selectedWarningLetter.endDate.dateFormatted}"/>
                                        <div class="formRangeSlider-label control-label_mandatory">
                                           <spring:theme code="text.account.followup.warningLetterExtension"/>
                                        </div>
                                        <div class="formRangeSlider-value">
                                            <span>0</span> <spring:theme code="text.account.followup.days"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <dl class="dlList dlList_separated">
                                    <dt><spring:theme code="text.account.followup.warningLetterExtendedDate"/></dt>
                                    <dd class="js-end-date"></dd>
                                </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <div class="contentModule-headline headline-text">
                            <icon:enquiry2/>
                            <spring:theme code="text.account.followup.reason"/>
                        </div>
                        <div class="formTextArea">
                            <div class="form-group">
                                <textarea required id="extensionReason" name="extensionReason" class="form-control js-form-element" placeholder="."></textarea>
                                <label class="control-label control-label_mandatory" for="extensionReason">
                                    <spring:theme code="text.account.followup.warningLetterReason"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <div class="contentModule-headline headline-text">
                            <icon:documents/>
                            <spring:theme code="text.account.followup.supportDocuments"/>
                        </div>
                        <formElement:uploadDocuments name="docs"/>
                    </div>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">                
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox  event="GOVERNMENT_DOCUMENTS" id="termsAndConditions" path="termsAndConditionsChecked" name="termsAndConditionsChecked"   containerCssClass="js-terms-agree"/>
                    </div>
                </div>
                <button type="button" class="btn btn_leftIconLink btn_outline" onclick="window.history.back()">Cancel</button>
                <button type="submit" class="btn js-followup-wl-create">
                    <spring:theme code="text.account.followup.submit"/>
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
                <div class="modal-title"><spring:theme code="warningLetterExtensionsCreate.reqSubmitted.text"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:modal02/></div>
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
