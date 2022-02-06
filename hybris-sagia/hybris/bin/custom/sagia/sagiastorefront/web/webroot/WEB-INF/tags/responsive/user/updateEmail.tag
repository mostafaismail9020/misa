<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<spring:url value="/my-sagia/update-email" var="updateEmailURL"/>

<form id="changeEmail" name="changeEmail">
<div class="user-status pt-3 pb-5">
  <spring:theme code="updateEmail.current"/>&nbsp;
                 <span id="currentEmail"></span>
            </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="email" name="email" class="form-control" type="text" value="" required="required" placeholder="."/>
                    <label class="control-label control-label_mandatory" for="email">
                        <spring:theme code="profile.change.email"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="chkEmail" name="chkEmail" class="form-control" type="text" value="" placeholder="."/>
                    <label class="control-label control-label_mandatory" for="chkEmail">
                        <spring:theme code="profile.change.chkEmail"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="passwordForChangeEmail" name="passwordForChangeEmail" class="form-control" type="password" value="" placeholder="."/>
                    <label class="control-label control-label_mandatory" for="passwordForChangeEmail">
                        <spring:theme code="profile.password"/>
                    </label>
                </div>
            </div>
        </div>
    </div>

    <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
        <button id="cancelUpdateEmailButton" type="button" class="btn btn_bold btn-outline pt-2 pb-2 w-25 text-uppercase"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
        <button id="updateEmail" type="button" class="btn btn-bg pt-2 pb-2 btn_bold w-25 text-uppercase"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
    </div>
</form>



