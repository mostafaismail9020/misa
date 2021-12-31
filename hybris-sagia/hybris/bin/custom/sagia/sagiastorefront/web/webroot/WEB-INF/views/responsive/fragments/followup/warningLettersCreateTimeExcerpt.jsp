<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

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
                       class="js-days-range js-form-element" data-date="${selectedWarningLetter.endDate.dateFormatted}">
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