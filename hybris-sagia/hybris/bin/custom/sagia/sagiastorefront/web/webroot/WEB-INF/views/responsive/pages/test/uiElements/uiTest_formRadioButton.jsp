<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formRadioButton</h1>
	<p class="uiTest-description">RadioButtons can be inline or displayed as block</p>
</div>
<!-- End of Module description-->

<div class="row">
	<div class="col-md-8">
		<!-- Inline Radiobuttons -->
		<div class="formRadioButton">
			<div class="form-group">

				<div class="form-item">
					<input id="hasMomra1" name="radioButtonName1" class="form-control" type="radio" value="true" checked="checked">
					<label for="hasMomra1" class="control-label">
						<span></span> I have Momra</label>
				</div>

				<div class="form-item">
					<input id="hasMomra2" name="radioButtonName1" class="form-control" type="radio" value="false">
					<label for="hasMomra2" class="control-label">
						<span></span> I don't have Momra</label>
				</div>
				
				<div class="form-item">
					<input id="hasMomra3" name="radioButtonName1" class="form-control" type="radio" value="false" disabled>
					<label for="hasMomra3" class="control-label">
						<span></span> I don't have Momra</label>
				</div>				
			</div>
		</div>
				
	</div>
	
	<div class="col-md-4">
	
		<div class="formRadioButton formRadioButton_block">
			<div class="form-group">

				<div class="form-item">
					<input id="hasMomra4" name="radioButtonName2" class="form-control" type="radio" value="true" checked="checked">
					<label for="hasMomra4" class="control-label">
						<span></span> I have Momra</label>
				</div>

				<div class="form-item">
					<input id="hasMomra5" name="radioButtonName2" class="form-control" type="radio" value="false">
					<label for="hasMomra5" class="control-label">
						<span></span> I don't have Momra</label>
				</div>
				
				<div class="form-item">
					<input id="hasMomra6" name="radioButtonName2" class="form-control" type="radio" value="false" disabled>
					<label for="hasMomra6" class="control-label">
						<span></span> I don't have Momra</label>
				</div>				
			</div>
		</div>
				
	</div>
	
	<div class="col-md-6">
	
		<div class="formRadioButton formRadioButton_block formRadioButton_slim">
			<div class="form-group">
				<div class="formRadioButton-label">Label for RadioButton with Blockmodifier (can be omitted)</div>
				
				<div class="form-item">
					<input id="hasMomra4" name="radioButtonName2" class="form-control" type="radio" value="true" checked="checked">
					<label for="hasMomra4" class="control-label">
						<span></span> I have Momra</label>
				</div>

				<div class="form-item">
					<input id="hasMomra5" name="radioButtonName2" class="form-control" type="radio" value="false">
					<label for="hasMomra5" class="control-label">
						<span></span> I don't have Momra</label>
				</div>
				
				<div class="form-item">
					<input id="hasMomra6" name="radioButtonName2" class="form-control" type="radio" value="false" disabled>
					<label for="hasMomra6" class="control-label">
						<span></span> I don't have Momra</label>
				</div>				
			</div>
		</div>
		
	</div>
	

	
</div>


</div>

