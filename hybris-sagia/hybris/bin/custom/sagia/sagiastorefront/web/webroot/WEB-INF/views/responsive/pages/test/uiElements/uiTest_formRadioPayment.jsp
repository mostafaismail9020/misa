<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formRadioPayment</h1>
	<p class="uiTest-description">RadioButtons in Box Style with fixed label above.</p>
</div>
<!-- End of Module description-->

<div class="row">
  
   <div class="col">

		<div class="formRadioPayment">

			<div class="form-group">

				<div class="form-item">
					<input id="a-pay01" name="a-radioPay01" class="form-control" type="radio" value="">
					<label for="a-pay01" class="control-label"><icon:creditCard_visa/></label>
				</div>

				<div class="form-item">
					<input id="a-pay02" name="a-radioPay01" class="form-control" type="radio" value="">
					<label for="a-pay02" class="control-label"><icon:creditCard_master/></label>
				</div>
				
				<div class="form-item">
					<input id="a-pay03" name="a-radioPay01" class="form-control" type="radio" value="">
					<label for="a-pay03" class="control-label"><icon:creditCard_dc/></label>
				</div>
				
				<div class="form-item">
					<input id="a-pay04" name="a-radioPay01" class="form-control" type="radio" value="">
					<label for="a-pay04" class="control-label"><icon:creditCard_ae/></label>
				</div>
				
				<div class="form-item">
					<input id="a-pay05" name="a-radioPay01" class="form-control" type="radio" value="">
					<label for="a-pay05" class="control-label"><icon:creditCard_jcb/></label>
				</div>			

			</div>
		</div>                    

   </div>

</div>


</div>

