<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags/responsive/survey" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>

<div class="contentModule">
    <form:form id="surveyForm" class="js-survey" action=""
               data-survey="${surveyData.surveyid}"
               data-survey-version="${surveyData.surveyversion}"
               data-application="${surveyData.applicationId}"
               data-transaction-id="${surveyData.transactionId}"
               data-isFetchedFromNotificationService="${surveyData.isFetchedFromNotificationService}">
                <div class="row">
                    
				<div class="col-md-3 col-12 px-0">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile#questionnairesTab" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="general.participate.backtoallquestionaires"/></a>
                </div>
            </div>
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap mt-3 ">
            <div class="contentModule-headline fdi ml-0">
                <!--<span class="iconElement iconElement_questionaires"><icon:questionaires/></span>-->${surveyData.surveytitle}
            </div>
              <hr class="hr w-100"/>


        </div>

        <c:forEach items="${surveyData.sections}" var="section" varStatus="sectionStatus">
            <div class="contentModule-section contentModule-section_slimDivider contentModule-section_noDivider">
                <div class="contentModule-headline_survey contentModule-headline_survey_operation">${section.header}</div>
                <c:forEach items="${section.sections}" var="subsection" varStatus="subsectionStatus">
                    <div class="contentModule-headline_survey_operation contentModule-subheadline border-0">${subsection.header}</div>
                    <div class="row">
                        <c:forEach items="${subsection.questions}" varStatus="questionStatus" var="question">
                            <c:choose>
                                <c:when test="${question.answType == '6'}">
                                    <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="${answer.questID}"
                                                           class="form-control <c:if test="${question.mandquest}">js-required</c:if>"
                                                           placeholder="." value=""
                                                           type="number"
                                                           data-answer="${answer.ansID}"
                                                           data-question-type="${question.answType}"
                                                           data-control="${answer.controlID}"
                                                           data-question="${answer.questID}">
                                                    <label class="control-label" for="${answer.questID}">
                                                            ${question.questTXT}
                                                            <c:if test="${question.mandquest}">*</c:if>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${question.answType == '4'}">
                                    <div class="col-md-6">
                                        <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                            <div class="formInputBox formInputBox_group ">
                                                <div class="form-group">
                                                    <input id="${answer.controlID}" name="enquiryType"
                                                           class="form-control js-form-control_date <c:if test="${question.mandquest}">js-required</c:if>"
                                                           placeholder="." value="" type="text"
                                                           <%--data-format="dd MM yyyy"--%>
                                                           data-answer="${answer.ansID}"
                                                           data-question-type="${question.answType}"
                                                           data-control="${answer.controlID}"
                                                           data-question="${answer.questID}"></input>
                                                    <label class="control-label" for="${answer.controlID}">
                                                        ${question.questTXT}<c:if test="${question.mandquest}">*</c:if>
                                                    </label>
                                                    <div class="formInputBox-append">
                                                        <span class="formInputBox-text"><icon:calendar-gray/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:when test="${question.answType == '10'}" >
                                    <div class="col-md-12">
                                        <div class="formRadioButton formRadioButton_block formRadioButton_slim">
                                            <div class="form-group">
                                                <div class="formRadioButton-label">${question.questTXT}<c:if test="${question.mandquest}">*</c:if></div>
                                                <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                                    <div class="form-item">
                                                        <input id="${answer.controlID}"
                                                               name="${question.questID}"
                                                               class="form-control" type="radio"
                                                               data-answer="${answer.ansID}"
                                                               data-question-type="${question.answType}"
                                                               data-control="${answer.controlID}"
                                                               data-question="${answer.questID}"
                                                               value="${answer.controlID}" >
                                                        <label for="${answer.controlID}" class="control-label">
                                                            <span></span> ${answer.ansText}
                                                        </label>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${question.answType == '1'}">
                                    <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                        <div class="col-md-6">
                                            <div class="formInputBox">
                                                <div class="form-group">
                                                    <input id="${answer.controlID}"
                                                           class="form-control <c:if test="${question.mandquest}">js-required</c:if>"
                                                           placeholder="." value=""
                                                           type="text"
                                                           data-answer="${answer.ansID}"
                                                           data-question-type="${question.answType}"
                                                           data-control="${answer.controlID}"
                                                           data-question="${answer.questID}">
                                                    <label class="control-label" for="${answer.controlID}">
                                                        ${answer.ansText}<c:if test="${question.mandquest}">*</c:if>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${question.answType == '101'}">
                                    <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                        <div class="col-12">
                                            <div class="formCheckBox">
                                                <div class="form-group">
                                                    <div class="form-item">
                                                        <input id="${answer.controlID}"
                                                               name="terms"
                                                               class="form-control <c:if test="${question.mandquest}">js-required</c:if>"
                                                               placeholder="." type="checkbox"
                                                               data-answer="${answer.ansID}"
                                                               data-question-type="${question.answType}"
                                                               data-control="${answer.controlID}"
                                                               data-question="${answer.questID}"
                                                               value="${answer.controlID}"></input>
                                                        <label class="control-label " for="${answer.controlID}">
                                                            <span><icon:check/></span>${answer.ansText}<c:if test="${question.mandquest}">*</c:if>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${question.answType == '3' || question.answType == '13' || question.answType == '12'}">
                                    <div class="col-md-12">
                                        <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                            <div class="form-group">
                                                <div class="formCheckBox-label">${question.questTXT}<c:if test="${question.mandquest}">*</c:if></div>
                                                <c:forEach items="${question.answers}" var="answer" varStatus="itemStatus">
                                                    <div class="form-item">
                                                        <input id="${answer.controlID}"
                                                               name="${answer.ansID}"
                                                               class="form-control <c:if test="${question.mandquest}">js-required</c:if>"
                                                               placeholder="." type="checkbox"
                                                               data-answer="${answer.ansID}"
                                                               data-question-type="${question.answType}"
                                                               data-control="${answer.controlID}"
                                                               data-question="${answer.questID}"
                                                               value="${answer.controlID}" >
                                                        <label class="control-label" for="${answer.controlID}">
                                                            <span><icon:check/></span>${answer.ansText}
                                                        </label>
                                                        <c:if test="${answer.subQuestion != null}">
                                                            <div class="formCheckBox-detail formCheckBox-detail_textarea">
                                                                <div class="formTextArea">
                                                                    <div class="form-group">
                                                                        <textarea id="${answer.subQuestion.questID}"
                                                                                  class="form-control form-control_slim <c:if test="${question.mandquest}">js-required</c:if>"
                                                                                  placeholder="."
                                                                                  data-answer="${answer.ansID}"
                                                                                  data-question-type="${question.answType}"
                                                                                  data-control="${answer.controlID}"
                                                                                  data-question="${answer.questID}"></textarea>
                                                                        <label class="control-label" for="${answer.subQuestion.questID}">Your answer</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>

                               <c:when test="${question.answType == '2'}">
                                    <c:forEach items="${question.answers}" var="answer" varStatus="answerStatus">
                                        <div class="col-md-12">
                                            <div class="formTextArea">
                                                <div class="form-group">
                                                    <textarea id="${answer.controlID}"
                                                           class="form-control form-control_slim <c:if test="${question.mandquest}">js-required</c:if>"
                                                           placeholder="."
                                                           data-answer="${answer.ansID}"
                                                           data-question-type="${question.answType}"
                                                           data-control="${answer.controlID}"
                                                           data-question="${answer.questID}"></textarea>
                                                    <label class="control-label" for="${answer.controlID}">
                                                        ${question.questTXT}<c:if test="${question.mandquest}">*</c:if>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>


                            </c:choose>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>

        <div class="contentModule-actions contentModule-actions_centered">
            <button class="btn btn_bold btn-bg btn-normal w-25 pl-2 pr-2"><spring:theme code="text.account.questionnaries.send.feedback"/></button>
        </div>
    </form:form>
</div>

<div class="modal fade" id="surveyResultFailModal" tabindex="-1" role="dialog" aria-labelledby="surveyResultFailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="text.account.questionnaries.thanks"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:modal02/></div>
                <div class="modal-description">
                    <spring:theme code="text.account.questionnaries.valueOpinion"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_slim" data-dismiss="modal">
                    <spring:theme code="text.account.questionnaries.questionnairesBack"/>
                </button>
            </div>
            <div class="modal-secondaryContent">
                <div class="modal-headline"><spring:theme code="text.account.questionnaries.experience"/></div>
                <div class="ratingModule">
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star active"><icon:rating-star/></div>
                    <div class="ratingModule-star"><icon:rating-star-empty/></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="surveyResultValidateModal" tabindex="-1" role="dialog" aria-labelledby="surveyResultFailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="text.account.questionnaries.mandatory"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-description"></div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:theme code="text.account.questionnaries.close"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="surveyResultIncorrectModal" tabindex="-1" role="dialog" aria-labelledby="surveyResultFailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="text.account.questionnaries.incorrectValues"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-description"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:theme code="text.account.questionnaries.close"/></button>
            </div>
        </div>
    </div>
</div>