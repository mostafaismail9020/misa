<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/potentialOpportunity/${ticketId}/serviceRequest" var="submitServiceRequestAction" htmlEscape="false"/>

<form:form class="contact-form pt-3" action="${submitServiceRequestAction}" method="post" modelAttribute="sagiaServiceRquestForm">
<div class="form-row">
<div class="form-group col-md-6 form-normal-item">
			<label class="control-label" for="subject"> Subject <span
				class="mandatory">* </span></label> <input type="text" class="form-control"
				name="subject" id="subject" maxlength="300"
				onkeypress="return onlyAlphabets(event)" required />
		</div>
		<div class="form-group form-floating col-md-6 form-normal-item">
			<label class="control-label" for="description"> Description <span
				class="mandatory">* </span></label> <input type="text" class="form-control"
				name="description" id="description" maxlength="300"
				onkeypress="return onlyAlphabets(event)" required />
		</div>
		<div class="form-group col-md-6 form-normal-item-select">
			<label class="control-label" for="incidentCategory"> Incident Category <span
				class="mandatory">* </span></label>
			<form:select path="incidentCategory" id="incidentCategory">
				<c:forEach var="incidentCategoryValue" items="${incidentCategories}">
					<form:option value="${incidentCategoryValue}">${incidentCategoryValue}</form:option>
				</c:forEach>
			</form:select>
			<i class="caret"></i>
		</div>
		
		<div class="form-group col-md-6 form-normal-item-select">
			<label class="control-label" for="serviceCategory"> Service Category <span
				class="mandatory">* </span></label>
				<%-- <select id="serviceCategory" title="Service Category" disabled="true">
				      <option value="${sagiaServiceRquestForm.serviceCategory}">${sagiaServiceRquestForm.serviceCategory} </option>
				</select> --%>
			<form:select path="serviceCategory" id="serviceCategory" disabled="true">
				<c:forEach var="serviceCategoryValue" items="${serviceCategories}">
					<form:option value="${serviceCategoryValue}">${serviceCategoryValue}</form:option>
				</c:forEach>
			</form:select> 
			<i class="caret"></i>
		</div>
		
		<div class="form-group col-md-6 form-normal-item-select">
			<label class="control-label" for="priority"> Priority <span
				class="mandatory">* </span></label>
			<form:select path="priority" id="priority">
				<c:forEach var="priorityValue" items="${priorities}">
					<form:option value="${priorityValue}">${priorityValue}</form:option>
				</c:forEach>
			</form:select>
			<i class="caret"></i>
		</div>

      <button class="btn btn-block btn-primary positive adv_search_button" type="submit">Submit</button>
	</div>
</form:form>