<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formInputBox</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->



	<div class="formInputBox">
		<div class="form-group">
			<input id="" class="form-control" placeholder="." value="" type="text">
			<label class="control-label" for="">
				Normal
			</label>				
		</div>
	</div>


	<div class="formInputBox">
		<div class="form-group has-error">
			<input id="" class="form-control" placeholder="." value="" type="text">
			<label class="control-label" for="">
				Error
			</label>
			<div class="help-block"><span id="titleCode.errors">Some Error Message</span></div>				
		</div>
	</div>


	<div class="formInputBox-split">
		<div class="formInputBox">
			<div class="form-group">
				<input class="form-control form-control_preNumber" placeholder="." type="text" value="">
				<label class="control-label" for="">
				* Country Code</label>
			</div>
		</div>
		<div class="formInputBox formInputBox_big">
			<div class="form-group">
				<input class="form-control form-control_labeled" placeholder="." type="text" value="">
				<label class="control-label" for="">
				* Mobile Number</label>
			</div>
		</div>
	</div>


    <div class="formInputBox-split">
		<div class="formInputBox formInputBox_big">
			<div class="form-group">
				<input class="form-control form-control_labeled" placeholder="." type="text" value="">
				<label class="control-label" for="">
				* Street</label>
			</div>
		</div>
		<div class="formInputBox">
			<div class="form-group">
				<input class="form-control form-control_preNumber" placeholder="." type="text" value="">
				<label class="control-label" for="">
				* No</label>
			</div>
		</div>
	</div>


    <div class="formInputBox formInputBox_group ">
		<div class="form-group">
			<input id="" class="form-control js-form-control_date" placeholder="." value="" type="text">
			<label class="control-label" for="">
				Available Slot
			</label>
			<div class="formInputBox-append">
				<span class="formInputBox-text"><icon:calendar-gray/></span>
			</div>		
		</div>
		<div class="help-block"><span id="titleCode.errors">Some Error Message</span></div>		
	</div>


    



	<div class="formInputBox formInputBox_group">
		<div class="form-group">
			<input id="" class="form-control" placeholder="." value="" type="text">
			<label class="control-label" for="">
				Curency
			</label>
			<div class="formInputBox-append">
				<span class="formInputBox-text">SAR</span>
			</div>				
		</div>
	</div>

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
	</div>


	<div class="formInputBox formInputBox_group">
		<div class="form-group">
			<input id="" class="form-control" placeholder="." value="" type="text">
			<label class="control-label" for="">
				Input with Tooltip
			</label>
			<div class="formInputBox-append">
				<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
				</span>
			</div>		
		</div>
	</div>
	




</div>