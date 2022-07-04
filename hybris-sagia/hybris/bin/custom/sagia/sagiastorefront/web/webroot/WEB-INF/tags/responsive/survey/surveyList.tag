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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="contentModule">
    <div id="newSurveysSection" class="contentModule-section" style="display: none;">
        <div class="contentModule-headline">
            <span class="iconElement iconElement_group"><icon:group/></span>
            <spring:theme code="general.participate.latest"/>
        </div>
    </div>

    <div id="surveysSection" class="contentModule-section">
        <div class="contentModule-headline mw2">
            <!--<span class="iconElement iconElement_questionaires"><icon:questionaires/></span>--><spring:theme code="general.all.questionnaires"/>
        </div>
         <hr class="hr"/>
    </div>

    <div class="myAccount-questionnaire newQuestionnaireTemplate" style="display: none">
        <div class="myAccount-questionnaire-action">
            <a class="newSurveyLink btn btn_outline btn_slim"><spring:theme code="general.participate"/></a>
        </div>
        <div class="myAccount-questionnaire-title">
            <span class="myAccount-questionnaire-new"><spring:theme code="general.participate.new"/></span>&nbsp;<span class="newSurveyTitle"></span>
        </div>
    </div>

    <div class="myAccount-questionnaire questionnaireTemplate" style="display: none">
        <div class="myAccount-questionnaire-action">
            <a class="surveyLink btn btn_outline btn_slim"><spring:theme code="general.participate"/></a>
        </div>
        <div class="myAccount-questionnaire-title">
            <span class="surveyTitle"></span>
        </div>
    </div>
</div>

