<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<div class="newUser_page">
  <div id="global-alerts" class="global-alerts">
    <c:if test="${not empty errorMessageKey}">
        <div class="alert alert-danger alert-dismissable getAccAlert">
          <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
            <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
          </button>
          <div class="alert-wrapper">
            <img src="/investsaudistorefront/_ui/responsive/common/images/danger.png">
            <spring:theme code="${errorMessageKey}"/>
          </div>
        </div>
    </c:if>
    <c:if test="${createSuccess}">
        <div class="alert alert-info alert-dismissable getAccAlert">
          <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
            <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
          </button>
          <div class="alert-wrapper">
            <img src="/investsaudistorefront/_ui/responsive/common/images/success.png">
            <span>User created successfully</span>
          </div>
        </div>
    </c:if>
  </div>
  <div class="headline header-account "  >Create New User Page</div>
  <c:url value = "/my-account/new-user" var="userFormAction" />
  <div id="global-alerts" class="global-alerts"></div>
  <form:form method="post" modelAttribute="newUserForm" action="${userFormAction}" >
    <div class="row">
        <div class="col-md-6">
          <div class="form-group">
              <label class="control-label control-label_mandatory label-color" for="text.account.newUser">
                <spring:theme code="text.account.newUser" text="Title"/>
              </label>
              <form:select path="title" cssClass="form-control">
                <c:forEach var="title" items="${titles}">
                    <form:option value="${title.code}">${title.name}</form:option>
                </c:forEach>
              </form:select>
              <div id="NotEmpty-AddNewUserForm-title" class="help-block" style="display: none;">
                <span id="title-errors"></span>
              </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
              <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.newUser.firstName" path="firstName" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
              <div id="NotEmpty-AddNewUserForm-firstName" class="help-block" style="display: none;">
                <span id="firstName-errors"></span>
              </div>
          </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
          <div class="form-group">
              <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.newUser.lastName" path="lastName" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
              <div id="NotEmpty-AddNewUserForm-lastName" class="help-block" style="display: none;">
                <span id="lastName-errors"></span>
              </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
              <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.newUser.email" path="email" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
              <div id="NotEmpty-AddNewUserForm-email" class="help-block" style="display: none;">
                <span id="email-errors"></span>
              </div>
          </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
          <div class="form-group">
              <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.newUser.mobile" path="mobileNumber" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
              <div id="NotEmpty-AddNewUserForm-mobileNumber" class="help-block" style="display: none;">
                <span id="mobileNumber-errors"></span>
              </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
              <label class="control-label control-label_mandatory label-color" for="text.account.newUser">
                <spring:theme code="text.account.newUser" text="Parent Unit"/>
              </label>
              <form:select path="parentUnit" cssClass="form-control">
                <c:forEach var="parentUnit" items="${parentUnits}">
                    <form:option value="${parentUnit.id}">${parentUnit.name}</form:option>
                </c:forEach>
              </form:select>
              <div id="NotEmpty-AddNewUserForm-parentUnit" class="help-block" style="display: none;">
                <span id="parentUnit-errors"></span>
              </div>
          </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
          <div class="form-group">
              <label class="control-label control-label_mandatory label-color" for="text.account.newUser">
                <spring:theme code="text.account.newUser" text="Role"/>
              </label>
              <br/>
              <c:forEach var="userGroup" items="${userGroups}">
                <c:choose>
                    <c:when test="${userGroup eq 'b2bCustomerGroup'}">
                      <input type="radio" name="role" value="${userGroup}" checked>
                      <span>
                          <spring:message code="text.account.role.${userGroup}"/>
                      </span>
                      &nbsp;&nbsp;&nbsp;
                    </c:when>
                    <c:otherwise>
                      <input type="radio" name="role" value="${userGroup}" class="userRole">
                      <spring:message code="text.account.role.${userGroup}"/>
                      &nbsp;&nbsp;&nbsp;
                    </c:otherwise>
                </c:choose>
              </c:forEach>
              <div id="NotEmpty-AddNewUserForm-userGroups" class="help-block" style="display: none;">
                <span id="userGroups-errors"></span>
              </div>
          </div>
        </div>
    </div>
    <div class="row" style="padding-bottom:20px">
        <div class="col-md-6"></div>
        <div class="col-md-12 pt-5 text-center">
          <div class="form-group addNewUser_group">
              <button class="btn btn-sagia btn-sagia-green cancel" type="reset" id="addNewUserCancel">
              Cancel
              </button>
              <button class="btn btn-sagia btn-sagia-green Submit" type="button" id="addNewUserSubmit">
                <spring:theme code="text.account.supporttickets.createTicket.submit" text="Submit"/>
              </button>
          </div>
        </div>
    </div>
  </form:form>
  <common:globalMessagesTemplates/>
</div> 

<script>
  $("button:reset").on("click", function() {
    this.form.reset(); 
    $('#parentUnit').attr('disabled', false); 
});
</script>