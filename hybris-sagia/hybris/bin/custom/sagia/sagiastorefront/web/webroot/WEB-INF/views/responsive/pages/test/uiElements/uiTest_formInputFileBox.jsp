<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formInputFileBOX</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->

	
<form action="" class="js-formInputFileBox">
	<div class="formInputFileBox">
		<div class="form-group">
			<div class="form-icon form-icon_browse">
				<icon:upload/>
			</div>		
			<input class="form-control js-inputFile" type="file" name="files[]" id="fileBox"/>
			<label class="control-label" for="fileBox">Choose a file<span class="formInputFileBox-dragndrop"> or drag it here</span>.</label>			
		</div>
		<div class="formInputFileBox-uploading">Uploading&hellip;</div>
		<div class="formInputFileBox-success">Done!</div>
		<div class="formInputFileBox-error">Error! <span></span>.</div>
	</div>	
</form>


	
</div>

