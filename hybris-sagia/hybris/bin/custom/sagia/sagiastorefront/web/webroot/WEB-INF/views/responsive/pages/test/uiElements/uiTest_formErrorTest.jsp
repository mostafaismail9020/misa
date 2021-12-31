<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">form errors</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->


<div class="row">

	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group has-error">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Error
				</label>
				<div class="help-block">
					<span>Some Error Message</span>
				</div>				
			</div>
		</div>		
	</div>
	
</div>
<div class="row">
	
	<div class="col-md-6">
		<div class="formInputBox-split">
			<div class="formInputBox">
				<div class="form-group has-error">
					<input class="form-control form-control_preNumber" placeholder="." type="text" value="">
					<label class="control-label" for="">
					* Country Code</label>
					<div class="help-block">
						<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo quibusdam tempore, voluptatum eius! Nulla ab labore explicabo ipsam nostrum aut sint totam maiores, ipsum fugiat officia, unde, dignissimos. A, consequuntur.</span>
					</div>	
				</div>
			</div>
			<div class="formInputBox formInputBox_big">
				<div class="form-group has-error">
					<input class="form-control form-control_labeled" placeholder="." type="text" value="">
					<label class="control-label" for="">
					* Mobile Number</label>
					<div class="help-block">
						<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloribus omnis, et! Dolorum officia error mollitia reprehenderit deleniti quod dolores harum, minus eos, quasi quos eligendi corrupti asperiores provident, cumque facere.</span>
					</div>					
				</div>
			</div>			
		</div>		
	</div>
	
	<div class="col-md-6">
		<div class="formInputBox-split">
			<div class="formInputBox formInputBox_big">
				<div class="form-group has-error">
					<input class="form-control form-control_labeled" placeholder="." type="text" value="">
					<label class="control-label" for="">
					* Street</label>
					<div class="help-block">
						<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo quibusdam tempore, voluptatum eius! Nulla ab labore explicabo ipsam nostrum aut sint totam maiores, ipsum fugiat officia, unde, dignissimos. A, consequuntur.</span>
					</div>						
				</div>
			</div>
			<div class="formInputBox">
				<div class="form-group has-error">
					<input class="form-control form-control_preNumber" placeholder="." type="text" value="">
					<label class="control-label" for="">
					* No</label>
					<div class="help-block">
						<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illo quibusdam tempore, voluptatum eius! Nulla ab labore explicabo ipsam nostrum aut sint totam maiores, ipsum fugiat officia, unde, dignissimos. A, consequuntur.</span>
					</div>										
				</div>
			</div>		
		</div>		
	</div>
	
</div>
<div class="row">
		
	<div class="col-md-6">
		<div class="formInputBox formInputBox_group ">
			<div class="form-group has-error">
				<input id="" class="form-control js-form-control_date" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Available Slot
				</label>
				<div class="formInputBox-append">
					<span class="formInputBox-text"><icon:calendar-gray/></span>
				</div>		
			</div>
			<div class="help-block">
				<span>Some Error Message</span>
			</div>		
		</div>		
	</div>
	
	<div class="col-md-6">
		<div class="formInputBox formInputBox_group">
			<div class="form-group has-error">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Curency
				</label>
				<div class="formInputBox-append">
					<span class="formInputBox-text">SAR</span>
				</div>				
			</div>
			<div class="help-block">
				<span>Some Error Message</span>
			</div>				
		</div>		
	</div>
	
	<div class="col-md-6">
		<div class="formInputBox formInputBox_group">
			<div class="form-group has-error">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Input with Tooltip
				</label>
				<div class="formInputBox-append">
					<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
					</span>
				</div>		
			</div>
			<div class="help-block">
				<span>Some Error Message</span>
			</div>				
		</div>		
	</div>
</div>


<div class="row">
	<div class="col-md-12">
		<div class="formTextArea">
			<div class="form-group has-error">
				<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
				<label class="control-label" for="">
					Textarea
				</label>
				<div class="help-block">
					<span>Some Error Message</span>
				</div>				
			</div>
		</div>		
	</div>
</div>


<div class="row">
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group has-error">
				<select id="" name="" class="js-select2 form-control">
					<option></option>
					<option value="1">hello</option>
					<option value="2">world</option>
					<option value="3">hello</option>
					<option value="4">world</option>
					<option value="5">hello</option>
					<option value="6">world</option>
					<option value="7">hello</option>
					<option value="8">world</option>
				</select>
				<label class="control-label" for="">Select</label>
				<div class="help-block">
					<span>Some Error Message</span>
				</div>					
			</div>
		</div>		
	</div>
</div>



</div>