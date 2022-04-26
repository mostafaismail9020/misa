<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formInputFile</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->


	<div class="formInputFile">
		<div class="form-group">
			<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
			<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
			<label class="control-label " for="">Label for file</label>
			<div class="form-icon form-icon_browse">
				<icon:upload/>
			</div>
			<div class="form-icon form-icon_reset js-inputFile-reset">
				<icon:cross/>
			</div>
		</div>
	</div>
	
	
	<div class="formInputFile">
		<div class="form-group has-error">
			<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
			<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
			<label class="control-label " for="">Label for file</label>
			<div class="form-icon form-icon_browse">
				<icon:upload/>
			</div>
			<div class="form-icon form-icon_reset js-inputFile-reset">
				<icon:cross/>
			</div>
		</div>
	</div>	


	<div class="formInputFile">
		<div class="form-group">
			<span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;float: right;right: 80px;top:20px;" data-tip-trigger="click click-outside" data-tip-title='Tooltip Lorem Ipsum' data-original-title="" title=""><icon:tipInfo/></span>
			<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
			<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
			<label class="control-label " for="">Label for file</label>
			<div class="form-icon form-icon_browse">
				<icon:upload/>
			</div>
			<div class="form-icon form-icon_reset js-inputFile-reset">
				<icon:cross/>
			</div>
		</div>
	</div>	
	
	
	<!-- <div class="formInputFile formInputFile_group">
		<div class="form-group">
			<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
			<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
			<label class="control-label " for="">Label for file</label>
			<div class="form-icon form-icon_browse">
				<icon:upload/>
			</div>
			<div class="form-icon form-icon_reset js-inputFile-reset">
				<icon:cross/>
			</div>
			<div class="formInputFile-append">
			  <span class="formInputFile-text formInputFile-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-trigger="click click-outside" data-tip-title='Tooltip Lorem Ipsum' data-original-title="" title=""><icon:tipInfo/>
			  </span>
			</div>			
		</div>
	</div>	 -->
	
</div>

