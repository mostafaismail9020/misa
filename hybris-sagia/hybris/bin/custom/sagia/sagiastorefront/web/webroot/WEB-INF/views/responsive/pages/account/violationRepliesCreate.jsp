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


<div class="mainSection mainSection_dark">
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
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="./" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme
                    code="text.account.followup.backViolationReplies"/></a>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">

        <form:form action="" id="${formName}" class="js-followup-form" modelAttribute="violationReplyForm">
          <input type="hidden" id="serviceId"/>
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_info"><icon:info/></span>
                                <spring:theme code="text.account.followup.info"/>
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
                        <div class="contentModule-actions contentModule-actions_spaceBetween">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_loading"><icon:loading/></span>
                                <spring:theme code="violation.violationstatus"/>
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
                        <div class="contentModule-actions contentModule-actions_spaceBetween">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_chat"><icon:chat/></span>
                                <spring:theme code="text.account.followup.comments"/>
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
                        <div class="contentModule-actions contentModule-actions_spaceBetween">
                            <div class="contentModule-headline">
                                <icon:documents/>
                                <spring:theme code="text.account.followup.supportDocuments"/>
                            </div>
                        </div>
                    </div>
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <formElement:uploadDocuments name="docs"/>
                    </div>
                </div>
            </div>


            <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                <button type="button" class="btn btn-secondary" onclick="window.history.back()">
                    <spring:theme code="general.cancel"/>
                </button>
                <div class="formCheckBox formCheckBox_belowPanel">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="FOLLOW_UP" id="termsAndConditions" path="termsAndConditionsChecked"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary js-followup-vr-create">
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