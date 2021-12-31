<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>

<div class="b2b-password-section">
	<div class="account-section-header">
		<div class="row">
			<div class="container-lg col-md-6">
				<spring:theme code="text.account.profile.updatePasswordForm"/>
			</div>
		</div>
	</div>
	<div class="password-container">
		<div class="row">
			<div class="container-lg col-md-12">
				<div class="account-section-content">
					<div class="account-section-form">
						<form:form action="${action}" method="post" modelAttribute="updatePasswordForm">
		
							<formElement:formPasswordBox idKey="currentPassword"
														 labelKey="profile.currentPassword" path="currentPassword" inputCSS="form-control"
														 mandatory="true" />
							<formElement:formPasswordBox idKey="newPassword"
														 labelKey="profile.newPassword" path="newPassword" inputCSS="form-control"
														 mandatory="true" />
							<formElement:formPasswordBox idKey="checkNewPassword"
														 labelKey="profile.checkNewPassword" path="checkNewPassword" inputCSS="form-control"
														 mandatory="true" />
		
		
							<div class="row">
								<div class="col-sm-12">
									<div class="accountActions">
										<button type="button" class="btn btn-sagia btn-block backToHome">
											<spring:theme code="text.button.cancel" text="Cancel" />
										</button>
										<button type="submit" class="btn btn-sagia btn-sagia-green btn-block">
											<spring:theme code="updatePwd.submit" text="Update Password" />
										</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
