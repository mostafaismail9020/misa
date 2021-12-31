<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:htmlEscape defaultHtmlEscape="true"/>

<form id="updatePwdForm">
    <div class="row">
        <div class="col-md-12">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="oldPwd" name="oldPwd" class="form-control" type="password" value=""/>
                    <label class="control-label control-label_mandatory" for="oldPwd">
                        <spring:theme code="updatePwd.pwd.old"/>
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="pwd" name="pwd" class="form-control" type="password" value=""/>
                    <label class="control-label control-label_mandatory" for="pwd">
                        <spring:theme code="updatePwd.pwd"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="checkPwd" name="checkPwd" class="form-control" type="password" value=""/>
                    <label class="control-label control-label_mandatory" for="checkPwd">
                        <spring:theme code="updatePwd.checkPwd"/>
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="myAccount-passwordInfo"></div>

    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
        <button type="reset" class="btn btn-secondary"><spring:theme code="text.button.cancel"/></button>
        <button type="button" id="updatePassword" class="btn"><spring:theme code="updatePwd.submit"/></button>
    </div>
</form>
