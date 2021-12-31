<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>

<div class="account-section-header">
	<div class="row">
		<div class="container-lg col-md-6">
			<spring:theme code="a" text="My Account"/>
		</div>
	</div>
</div>
<div class="row">
	<div class="container-lg col-md-6">
		<div class="account-section-content">
			<div class="account-section-form">
				<div class="row">
					<div class="col-md-12">
						<a href="<c:url value="/my-account/update-password"/>">Password</a>
					</div>
					<div class="col-md-12">
						<a href="<c:url value="/my-account/support-tickets"/>">Opportunity Requests</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>