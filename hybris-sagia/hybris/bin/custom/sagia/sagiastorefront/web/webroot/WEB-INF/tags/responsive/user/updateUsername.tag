<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<form id="changeUsername">
 <div class="user-status pt-3 pb-5">
                <spring:theme code="updateUsername.current"/>&nbsp;
                <span id="currentUsername"></span>
            </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="username" name="username" class="form-control" type="text" value=""/>
                    <label class="control-label control-label_mandatory" for="username">
                        <spring:theme code="profile.change.username"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="checkUsername" name="checkUsername" class="form-control" type="text" value=""/>
                    <label class="control-label control-label_mandatory" for="checkUsername">
                        <spring:theme code="profile.change.checkUsername"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="passwordForChangeUsername" name="passwordForChangeUsername" class="form-control" type="password" value=""/>
                    <label class="control-label control-label_mandatory" for="passwordForChangeUsername">
                        <spring:theme code="profile.password"/>
                    </label>
                </div>
            </div>
        </div>
    </div>

    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
        <button id="cancelUpdateUsernameButton" type="button" class="btn btn_bold btn-outline pt-2 pb-2 w-25 text-uppercase"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
        <button type="button" id="updateUsername" class="btn btn-bg pt-2 pb-2 btn_bold w-25 text-uppercase"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
    </div>
</form>


