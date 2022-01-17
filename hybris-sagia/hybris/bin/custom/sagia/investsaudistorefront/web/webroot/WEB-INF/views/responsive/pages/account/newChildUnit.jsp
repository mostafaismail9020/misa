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
<div class="newchildUser_page">
<spring:htmlEscape defaultHtmlEscape="true"/>
<c:url value = "/my-account/child-unit" var="formAction" />
<div id="global-alerts" class="global-alerts">
   <c:if test="${not empty errorMessageKey}">
      <div class="alert alert-danger alert-dismissable getAccAlert">
         <img src="/investsaudistorefront/_ui/responsive/common/images/danger.png">
         <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
            <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
         </button>Error while creating new user.
      </div>
   </c:if>
   <c:if test="${createSuccess}">
      <div class="alert alert-info alert-dismissable getAccAlert">
         <img src="/investsaudistorefront/_ui/responsive/common/images/success.png">
         <button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
            <img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
         </button>User created successfully
      </div>
   </c:if>
</div>
<div class="headline header-account ">Add New Child Unit</div>
<form:form method="post" action="${formAction}" modelAttribute="newChildUnitForm" >
   <div class="row">
      <div class="w-50ncenter">
         <div class="col-md-12">
            <div class="form-group">
               <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.addChildUnit.id" path="uid" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
               <div id="NotEmpty-AddChildUnit-uid" class="help-block" style="display: none;">
                  <span id="uid-errors"></span>
               </div>
            </div>
         </div>
         <div class="col-md-12">
            <div class="form-group">
               <formElement:formInputBox idKey="createTicket-subject" labelKey="text.account.addChildUnit.name" path="name" labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text" mandatory="true"  />
               <div id="NotEmpty-AddChildUnit-name" class="help-block" style="display: none;">
                  <span id="name-errors"></span>
               </div>
            </div>
         </div>
         <div class="col-md-12">
            <div class="form-group">
               <label class="control-label label-ticket-tight control-label_mandatory" for="createTicket-subject">
                  <spring:theme code="text.account.addChildUnit.parent"/>
               </label>
               <input id="createTicket-subject" name="name" class="text form-control" type="text" value="${currentB2bUnt.id} - ${currentB2bUnt.name}"  disabled="disabled">
            </div>
         </div> 
        <div class="col-md-12 pt-5">
            <div class="form-group text-center addNewUser_group">
                <button class="btn btn-sagia btn-sagia-green cancel" type="button" id="cancelAddChild">
                Cancel
                </button>
                <button class="btn btn-sagia btn-sagia-green Submit" type="button" id="addChildUnit">
                    <spring:theme code="text.account.supporttickets.createTicket.submit" text="Submit"/>
                </button>
            </div>
        </div>
       </div>
    </div>
</form:form>
</div>