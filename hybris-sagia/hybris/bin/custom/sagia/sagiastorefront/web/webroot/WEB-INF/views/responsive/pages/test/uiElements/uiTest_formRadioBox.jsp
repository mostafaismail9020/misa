<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formRadioBox</h1>
	<p class="uiTest-description">RadioButtons in Box Style with fixed label above.</p>
</div>
<!-- End of Module description-->

<div class="row">
   <div class="col-md-6">

		<div class="formRadioBox">

			<div class="form-group">

				<div class="formRadioBox-label">* Label for Radiogroup</div>

				<div class="form-item">
					<input id="a01" name="radioBox01" class="form-control" type="radio" value="false">
					<label for="a01" class="control-label">
						Yes
					</label>
				</div>

				<div class="form-item">
					<input id="a02" name="radioBox01" class="form-control" type="radio" value="false">
					<label for="a02" class="control-label">
						No
					</label>
				</div>

			</div>
		</div>                    

   </div>
   
   <div class="col-md-6">
   	
		<div class="formRadioBox">

			<div class="form-group">

				<div class="formRadioBox-label">* Disabled Radiobox</div>

				<div class="form-item">
					<input id="a03" name="radioBox02" class="form-control" type="radio" value="false" disabled>
					<label for="a03" class="control-label">
						Yes
					</label>
				</div>

				<div class="form-item">
					<input id="a04" name="radioBox02" class="form-control" type="radio" value="false" disabled>
					<label for="a04" class="control-label">
						No
					</label>
				</div>

			</div>
		</div>    	
   	
   </div>

</div>


</div>

