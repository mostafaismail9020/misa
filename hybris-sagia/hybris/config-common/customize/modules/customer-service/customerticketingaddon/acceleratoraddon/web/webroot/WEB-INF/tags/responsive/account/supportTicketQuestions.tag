<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<div class="row">
    <c:forEach items="${questions}" var="question">
        <div class="col-md-6">
            <div class="form-group">
                <c:set var="isMandatory" value="${question.isMandatory != null && question.isMandatory eq true}"/>
                <label class="label-ticket ${isMandatory ? "control-label_mandatory" : ""}" for="${question.id}">
                        ${question.label}
                </label>

                <textarea name="${question.id}" id="${question.id}" class="ticket-questions text"
                          placeholder="${question.placeholder}" ${isMandatory ? "required" : ""}></textarea>

                <div id="${question.id}-subjects" class="help-block" style="display: none;">
                    <span id="subject-errors"></span>
                </div>

                <div id="${question.id}-error" class="help-block" style="display: none;"></div>
            </div>
        </div>
    </c:forEach>
</div>