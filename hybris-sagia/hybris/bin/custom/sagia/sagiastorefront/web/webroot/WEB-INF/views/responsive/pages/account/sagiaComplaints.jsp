<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="row">
	<div class="container-lg col-md-6">
		<div class="account-section-content">
			<div class="account-section-form">
				<h1><spring:theme code="complaints.complaints"/></h1>
				<form:form method="POST"
					style="display: flex; flex-direction:  column;"
					action="${encodedContextPath}/complaints/create-complaint"
					modelAttribute="complaintFormData" 
					enctype="multipart/form-data">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />					
					<form:select path="details.EnquiryType">
						<option value="0"><spring:theme code="complaints.enquirytype"/></option>
						<form:options items="${complaintFormData.enquiryTypes}" itemValue="catDesc" itemLabel="catDesc" htmlEscape="true" />
					</form:select>
					<br />
					<form:select path="details.Category1">
						<option value="0"><spring:theme code="complaints.category1"/></option>
						<form:options items="${complaintFormData.categoriesOne}" itemValue="catDesc" itemLabel="catDesc" htmlEscape="true" />
					</form:select>
					<br />
					<form:select path="details.Category2">
						<option value="0"><spring:theme code="complaints.category2"/></option>
						<form:options items="${complaintFormData.categoriesTwo}" itemValue="catDesc" itemLabel="catDesc" htmlEscape="true" />
					</form:select>
					<br />
					<form:select path="details.Branch">
						<option value="0"><spring:theme code="complaints.branch"/></option>
						<form:options items="${complaintFormData.branches}" itemValue="description" itemLabel="description" htmlEscape="true" />
					</form:select>
					<br />
					<br />
					<spring:theme code="complaints.commentmessage"/> <textarea id="details.textMsg" name="details.textMsg" height="80px"> </textarea>
					<input type="submit" value="SUBMIT" style="align-self: center; width: 50%" />
					<br />
					<p><spring:theme code="complaints.filestoupload"/></p>
					<spring:theme code="complaints.uploadfile1"/> <input type="file" name="file" accept="image/jpeg,application/pdf">
					<br />
					<spring:theme code="complaints.uploadfile2"/> <input type="file" name="file" accept="image/jpeg,application/pdf">
					<br />
					<spring:theme code="complaints.uploadfile3"/> <input type="file" name="file" accept="image/jpeg,application/pdf">
					<br />
					<spring:theme code="complaints.uploadfile4"/> <input type="file" name="file" accept="image/jpeg,application/pdf">
					<br />
				</form:form>
				<br />
				<table border="1" cellpadding="5" width="300">
					<tr>
						<th><spring:theme code="complaints.complaintid"/></th>
						<th><spring:theme code="complaints.status"/></th>
						<th><spring:theme code="complaints.created"/></th>
					</tr>
					<c:forEach items="${list}" var="item">
						<tr>
							<td>${item.srStCode}</td>
							<td>${item.srStDesc}</td>
							<td>${item.srCrDate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
