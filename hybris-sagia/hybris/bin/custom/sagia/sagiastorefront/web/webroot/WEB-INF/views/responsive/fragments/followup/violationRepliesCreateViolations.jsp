<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>

<c:forEach items="${warningLetter.violations}" var="violation" varStatus="violationStatus">
    <div class="form-item">
        <input id="violations[${violationStatus.index}]" name="violations[${violationStatus.index}]" class="form-control" placeholder="."
               type="checkbox" value="${violation.violationKey}">
        <label class="control-label " for="violations[${violationStatus.index}]">
            <span><icon:check/></span>${violation.violationText}
        </label>
        <div class="formCheckBox-detail formCheckBox-detail_textarea">
            <div class="formTextArea">
                <div class="form-group">
                    <textarea id="violationsTexts[${violationStatus.index}]" data-violation="${violation.violationKey}" name="violationsTexts[${violationStatus.index}]"
                              class="form-control form-control_slim" placeholder="."></textarea>
                    <label class="control-label" for="violationsTexts[${violationStatus.index}]">
                        <spring:theme code="text.account.followup.comments"/>
                    </label>
                </div>
            </div>
        </div>
    </div>
</c:forEach>