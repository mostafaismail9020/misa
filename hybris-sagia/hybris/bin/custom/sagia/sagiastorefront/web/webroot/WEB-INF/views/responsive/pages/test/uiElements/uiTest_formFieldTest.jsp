<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">Testing formfields</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->

<button class="btn" data-toggle="modal" data-target="#formInModal">ModalFormTest</button>
<div class="row">
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group has-error">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Error
				</label>
				<div class="help-block">
						<span id="titleCode.errors">Some Error Message</span></div>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
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
	</div>
	<div class="col-md-6">
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
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputFile">
			<div class="form-group">
				<input id="aafile08" name="aafile08" class="form-control js-inputFile" type="file" value="">
				<input id="aatext08" name="taaext08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
				<label class="control-label " for="">Label for file</label>
				<div class="form-icon form-icon_browse">
					<icon:upload/>
				</div>
				<div class="form-icon form-icon_reset js-inputFile-reset">
					<icon:cross/>
				</div>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formRadioBox">

			<div class="form-group">

				<div class="formRadioBox-label">* Label for Radiogroup</div>

				<div class="form-item">
					<input id="aa01" name="aradioBox01" class="form-control" type="radio" value="false">
					<label for="aa01" class="control-label">
						Yes
					</label>
				</div>

				<div class="form-item">
					<input id="aa02" name="aradioBox01" class="form-control" type="radio" value="false">
					<label for="aa02" class="control-label">
						No
					</label>
				</div>

			</div>
		</div> 		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formRadioButton">
			<div class="form-group">

				<div class="form-item">
					<input id="aahasMomra1" name="aaradioButtonName1" class="form-control" type="radio" value="true" checked="checked">
					<label for="aahasMomra1" class="control-label">
						<span></span> I have Momra</label>
				</div>

				<div class="form-item">
					<input id="aahasMomra2" name="aaradioButtonName1" class="form-control" type="radio" value="false">
					<label for="aahasMomra2" class="control-label">
						<span></span> I don't have Momra</label>
				</div>
							
			</div>
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formCheckBox">
			<div class="form-group">
				<div class="form-item">
					<input id="aacheckbox01" name="aacheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="aacheckbox01">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>
				<div class="form-item">
					<input id="aacheckbox02" name="aacheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="aacheckbox02">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>	
			</div>
		</div>
		<div class="formCheckBox formCheckBox_switch">
			<div class="form-group">
				<div class="form-item">
					<input id="aacheckbox03" name="aacheckbox03name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="aacheckbox03">
						<span><icon:check/>
						</span> Label
					</label>
				</div>
				<div class="form-item">
					<input id="aacheckbox04" name="aacheckbox04name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="aacheckbox04">
						<span><icon:check/>
						</span> Label
					</label>
				</div>
			</div>
		</div>	
	</div>		
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
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
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formTextArea">
			<div class="form-group">
				<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
				<label class="control-label" for="">
					Textarea
				</label>
			</div>
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
				<select id="" name="" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
					<option value="1">Lorem</option>
					<option value="2">ipsum</option>
					<option value="3">dolor</option>
					<option value="4">sit</option>
					<option value="5">amet</option>
					<option value="6">consectetur</option>
					<option value="7">adipisicing</option>
					<option value="8">elit</option>
				</select>
				<label class="control-label" for="">Select with search</label>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
				<select id="" name="" class="js-select2-oneColumn form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
					<option value="1">Lorem</option>
					<option value="2">ipsum</option>
					<option value="3">dolor</option>
					<option value="4">sit</option>
					<option value="5">amet</option>
					<option value="6">consectetur</option>
					<option value="7">adipisicing</option>
					<option value="8">elit</option>
					<option value="9">elit</option>
					<option value="10">elit</option>
					<option value="11">elit</option>
					<option value="12">elit</option>
				</select>
				<label class="control-label" for="">Select with search</label>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formRangeSlider js-formRangeSlider">
			<div class="form-group">
				<input type="range">
				<div class="formRangeSlider-label">Range Slider</div>
				<div class="formRangeSlider-value">0</div>
			</div>	
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formRangeSlider formRangeSlider_slim js-formRangeSlider">
			<div class="form-group">
				<input type="range">
			</div>	
		</div> 	
	</div>						
</div>



<button class="btn" data-toggle="modal" data-target="#formInModal">ModalFormTest</button>


<!--Template for large modals. Use (data-toggle="modal" data-target="#formInModal") on link or button to call it-->
<div class="modal fade" id="formInModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Testing formfileds in modal</div>
				<!--        Close Button is optional-->
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
<div class="row">
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group has-error">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Error
				</label>
				<div class="help-block">
						<span id="titleCode.errors">Some Error Message</span></div>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
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
	</div>
	<div class="col-md-6">
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
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputFile">
			<div class="form-group">
				<input id="aafile08" name="aafile08" class="form-control js-inputFile" type="file" value="">
				<input id="aatext08" name="taaext08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
				<label class="control-label " for="">Label for file</label>
				<div class="form-icon form-icon_browse">
					<icon:upload/>
				</div>
				<div class="form-icon form-icon_reset js-inputFile-reset">
					<icon:cross/>
				</div>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formRadioBox">

			<div class="form-group">

				<div class="formRadioBox-label">* Label for Radiogroup</div>

				<div class="form-item">
					<input id="aa01" name="aradioBox01" class="form-control" type="radio" value="false">
					<label for="aa01" class="control-label">
						Yes
					</label>
				</div>

				<div class="form-item">
					<input id="aa02" name="aradioBox01" class="form-control" type="radio" value="false">
					<label for="aa02" class="control-label">
						No
					</label>
				</div>

			</div>
		</div> 		
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>			
	</div>
	<div class="col-md-6">
		<div class="formRadioButton">
			<div class="form-group">

				<div class="form-item">
					<input id="aahasMomra1" name="aaradioButtonName1" class="form-control" type="radio" value="true" checked="checked">
					<label for="aahasMomra1" class="control-label">
						<span></span> I have Momra</label>
				</div>

				<div class="form-item">
					<input id="aahasMomra2" name="aaradioButtonName1" class="form-control" type="radio" value="false">
					<label for="aahasMomra2" class="control-label">
						<span></span> I don't have Momra</label>
				</div>
							
			</div>
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formCheckBox">
			<div class="form-group">
				<div class="form-item">
					<input id="aacheckbox01" name="aacheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="aacheckbox01">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>
				<div class="form-item">
					<input id="aacheckbox02" name="aacheckbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="aacheckbox02">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>	
			</div>
		</div>
		<div class="formCheckBox formCheckBox_switch">
			<div class="form-group">
				<div class="form-item">
					<input id="aacheckbox03" name="aacheckbox03name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="aacheckbox03">
						<span><icon:check/>
						</span> Label
					</label>
				</div>
				<div class="form-item">
					<input id="aacheckbox04" name="aacheckbox04name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="aacheckbox04">
						<span><icon:check/>
						</span> Label
					</label>
				</div>
			</div>
		</div>	
	</div>		
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
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
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formTextArea">
			<div class="form-group">
				<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
				<label class="control-label" for="">
					Textarea
				</label>
			</div>
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
				<select id="selectSearchModal" name="" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description in Modal">
					<option></option>
					<option value="1">Lorem</option>
					<option value="2">ipsum</option>
					<option value="3">dolor</option>
					<option value="4">sit</option>
					<option value="5">amet</option>
					<option value="6">consectetur</option>
					<option value="7">adipisicing</option>
					<option value="8">elit</option>
				</select>
				<label class="control-label" for="">selectSearchModal</label>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formSelectBox">
			<div class="form-group">
				<select id="" name="" class="js-select2-oneColumn form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
					<option value="1">Lorem</option>
					<option value="2">ipsum</option>
					<option value="3">dolor</option>
					<option value="4">sit</option>
					<option value="5">amet</option>
					<option value="6">consectetur</option>
					<option value="7">adipisicing</option>
					<option value="8">elit</option>
					<option value="9">elit</option>
					<option value="10">elit</option>
					<option value="11">elit</option>
					<option value="12">elit</option>
				</select>
				<label class="control-label" for="">Select with search</label>
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formRangeSlider js-formRangeSlider">
			<div class="form-group">
				<input type="range">
				<div class="formRangeSlider-label">Range Slider</div>
				<div class="formRangeSlider-value">0</div>
			</div>	
		</div>	
	</div>
	<div class="col-md-6">
		<div class="formInputBox">
			<div class="form-group">
				<input id="" class="form-control" placeholder="." value="" type="text">
				<label class="control-label" for="">
					Normal
				</label>				
			</div>
		</div>		
	</div>
	<div class="col-md-6">
		<div class="formRangeSlider formRangeSlider_slim js-formRangeSlider">
			<div class="form-group">
				<input type="range">
			</div>	
		</div> 	
	</div>						
</div>
<%@ include file="uiTest_flatpickr.jsp" %>		
<%@ include file="uiTest_formSelectBox.jsp" %>		
			</div>
			<div class="modal-footer modal-footer_right">
				<button type="button" class="btn btn_slim" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>






</div>






