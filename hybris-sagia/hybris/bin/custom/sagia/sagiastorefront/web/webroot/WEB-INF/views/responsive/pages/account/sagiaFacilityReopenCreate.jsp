<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsApplyModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>


<div class="mainSection mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="facilityReopenCreate.title"/></h1>

            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${formId}"
                        data-service-id="${serviceId}"><spring:theme code="general.savedraft"/><span
                        class="iconElement iconElement_save"><icon:save/></span></button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${formId}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/><span
                        class="iconElement iconElement_save"><icon:upload/></span>
                </button>
            </div>
        </div>
    </div>
</div>
<div class="mainSection mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div>
                <a href="${encodedContextPath}/facility-reopen" class="btn btn_leftIconLink btn_darkLink appointmentControl-backBtn">
                    <span>&times;</span><span class="appointmentControl-backBtn-label"><spring:theme code="facilityReopenCreate.backToOverview.text"/></span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="mainSection mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <form:form method="post" id="${formId}" modelAttribute="reopenFacility">
            <div class="panelModule panelModule_halfRadius appointmentDetails">
                <div class="contentModule-actions contentModule-actions_noMargin contentModule-actions_wrap">
                    <div class="contentModule-headline contentModule-headline_smallMargin">
                        <icon:enquiry2/><spring:theme code="facilityReopenCreate.comment.title"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="formTextArea">
                            <div class="form-group">
                                <form:textarea path="text" class="form-control" placeholder="."/>
                                <label class="control-label" for="text"><spring:theme code="facilityReopenCreate.comment.description"/></label>
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
                                <spring:theme code="facilityReopenCreate.attachments"/>
                            </div>
                        </div>
                    </div>
                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">
                        <formElement:uploadDocuments name="attachmentIds"/>
                    </div>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                <a href="${encodedContextPath}/facility-reopen" class="btn btn-secondary"><spring:theme code="general.cancel"/></a>
                <div class="formCheckBox formCheckBox_belowPanel">
                    <div class="form-group">
                        <formElement:termsAndConditionsCheckbox event="FOLLOW_UP" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition"/>
                    </div>
                </div>
                <button type="submit" class="btn">
                    <spring:theme code="text.account.followup.submit"/>
                </button>
            </div>
        </form:form>
    </div>
</div>
<div class="errorTemlateWrapper">
    <span class="help-block"></span>
</div>
<common:errorModal/>
