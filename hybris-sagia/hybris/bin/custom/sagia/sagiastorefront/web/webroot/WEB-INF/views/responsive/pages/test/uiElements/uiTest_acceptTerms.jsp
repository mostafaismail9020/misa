<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">Accept Terms Checkbox</h1>
	<p class="uiTest-description">Special Checkbox on my account page</p>
</div>
<!-- End of Module description-->

<div class="acceptTerms">
	<div class="row">
		<div class="col-md-6">
			<div class="formCheckBox">
				<div class="form-group">
					<div class="form-item">
						<input id="checkbox01" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
						<label class="control-label " for="checkbox01">
							<span><icon:check/></span> Please Accept terms and conditions to proceed for submission
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<a href="#" class="link" target="_blank">COC Authorized Letter template</a>
		</div>
	</div>
</div>


</div>
