<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">formCheckBox</h1>
	<p class="uiTest-description">Checkbox can be inline or displayed as block</p>
</div> 
<!-- End of Module description-->

<div class="row">
	<div class="col-md-6">
		<!-- Inline Checkbox -->
		<div class="formCheckBox">
			<div class="form-group">
			
				<div class="form-item">
					<input id="checkbox01" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="checkbox01">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox02" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="checkbox02">
						<span>
							<icon:check/>
						</span> Inline Label
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox03" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="entity name" disabled>
					<label class="control-label " for="checkbox03">
						<span>
							<icon:check/>
						</span> Disabled
					</label>
				</div>				
				
			</div>
		</div>

	</div>
	
	<div class="col-md-6">
	
		<!-- Checkbox Block -->
		<div class="formCheckBox formCheckBox_block">
			<div class="form-group">
			
				<div class="form-item">
					<input id="checkbox04" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="checkbox04">
						<span>
							<icon:check/>
						</span> Checkbox Block
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox05" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="checkbox05">
						<span>
							<icon:check/>
						</span> Checkbox Block
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox06" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name" disabled>
					<label class="control-label " for="checkbox06">
						<span>
							<icon:check/>
						</span> Disabled Checkbox Block
					</label>
				</div>				
				
			</div>
		</div>
			
				
	</div>
	
	
	
	
	
	
	<div class="col-md-6">
	
		<!-- Checkbox Block -->
		<div class="formCheckBox formCheckBox_block formCheckBox_slim">
			<div class="form-group">
				<div class="formCheckBox-label">Label for Checkbox with Blockmodifier (can be omitted)</div>
				<div class="form-item">
					<input id="checkbox04" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name" checked>
					<label class="control-label " for="checkbox04">
						<span>
							<icon:check/>
						</span> Checkbox Block
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox05" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name">
					<label class="control-label " for="checkbox05">
						<span>
							<icon:check/>
						</span> Checkbox Block
					</label>
				</div>
				
				<div class="form-item">
					<input id="checkbox06" name="checkbox02name" class="form-control" placeholder="." type="checkbox" value="entity name" disabled>
					<label class="control-label " for="checkbox06">
						<span>
							<icon:check/>
						</span> Disabled Checkbox Block
					</label>
				</div>				
				
			</div>
		</div>
			
				
	</div>	
	
	
	
	
</div>

<div class="row">
	<div class="col-12">
		<div class="formCheckBox formCheckBox_block formCheckBox_slim">
			<div class="form-group">
				<div class="form-item">
					<input id="expandableCheckbox-1" name="expandableCheckbox" class="form-control" placeholder="." type="checkbox" value="">
					<label class="control-label " for="expandableCheckbox-1">
						<span><icon:check/></span>
						Checkbox Items with expandable Textarea
					</label>
					<div class="formCheckBox-detail formCheckBox-detail_textarea">
						<div class="formTextArea">
							<div class="form-group">
								<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
								<label class="control-label" for="">
									Comments
								</label>
							</div>
						</div>                                            
					</div>
				</div>
				<div class="form-item">
					<input id="expandableCheckbox-2" name="expandableCheckbox" class="form-control" placeholder="." type="checkbox" value="">
					<label class="control-label " for="expandableCheckbox-2">
						<span><icon:check/></span>
						Checkbox Items with expandable Textarea
					</label>
					<div class="formCheckBox-detail formCheckBox-detail_textarea">
						<div class="formTextArea">
							<div class="form-group">
								<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
								<label class="control-label" for="">
									Comments
								</label>
							</div>
						</div>                                            
					</div>                                        
				</div>								
			</div>
		</div>		
	</div>
</div>

</div>
