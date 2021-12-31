<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>







<%-- TODO: SAH-550 --%>






<div class="mainSection mainSection_white mainSection_narrow">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Amend License</h1>
			<div class="serviceTime">
				<div class="serviceTime-label">Avg. Service Time</div>
				<div class="serviceTime-detail">
				<span class="serviceTime-highlight">02</span> Days <span class="serviceTime-highlight">15</span> Hours
				</div>
			</div>            
        </div>
    </div>
</div>



<div class="js-panelTabs panelTabs panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_none  panelTabs_noPanelInBody panelTabs_white panelTabs_lightNavigation">
	<div class="panelTabs-head done" id="tab1">
		<icon:registerinTab/>
		<span class="panelTabs-label">Entity</span>
	</div>
	<div class="panelTabs-body">

		<div class="row">
			<div class="col-sm-6">
				<div class="formInputBox formInputBox_group">
					<div class="form-group">
						<input id="" class="form-control" placeholder="." value="" type="text">
						<label class="control-label" for="">
							Capital
						</label>
						<div class="formInputBox-append">
							<span class="formInputBox-text">SAH</span>
						</div>				
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="formInputBox">
					<div class="form-group">
						<input id="" class="form-control" placeholder="." value="" type="text">
						<label class="control-label" for="">
							Labour
						</label>				
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="formInputBox">
					<div class="form-group">
						<input id="" class="form-control" placeholder="." value="" type="text">
						<label class="control-label" for="">
							Entity name
						</label>				
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="formSelectBox">
				    <div class="form-group">
				        <select id="" name="" class="js-select2-oneColumn form-control">
				        	<option></option>
							<option value="1">hello</option>
							<option value="2">world</option>
						</select>
				        <label class="control-label" for="">Legal status</label>
				    </div>
				</div>
			</div>
		</div>

		<hr class="mainSection-separator">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <button type="button" class="btn btn-secondary">
                Cancel
            </button>
            <button type="submit" class="btn btn-primary"  data-toggle="modal" data-target="#unsavedChanges3">
                Submit
            </button>
        </div>
		

	</div>
	<div class="panelTabs-head" id="tab2">
		<icon:shareholder/>
		<span class="panelTabs-label">Shareholders</span>
	</div>
	<div class="panelTabs-body">
		<div class="contentModule">
			<div class="contentModule-section">
				<div class="tableModule tableModule_noOverflow">
					<table class="tableModule-table">
						<thead class="tableModule-head">
							<tr>
								<th>Name</th>
								<th>Type</th>
								<th>Percentage</th>
								<th>Nationality</th>
								<th>Legal status</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="tableModule-body">
							<tr>
								<td>Joshua Bryant</td>
								<td>Person</td>
								<td>15%</td>
								<td>USA</td>
								<td>-</td>
								<td class="tableModule-bodyItem-action">
									<div class="deleteDropdown js-deleteDropdown">
										<button class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn" >
											<icon:remove/>
										</button>
										<div class="deleteDropdown-drop">
											<div class="deleteDropdown-text">
												Delete the shareholder?
											</div>
											<div class="deleteDropdown-actions">
												<button class="btn btn_outline btn_slim js-deleteDropdown-cancel" >
													Cancel
												</button>
												<button class="btn btn_slim" >
													Delete
												</button>
											</div>
										</div>
									</div>
									<button class="btn btn_link" data-toggle="modal" data-target="#mo1">
										<icon:edit/>
									</button>
								</td>
							</tr>
							<tr>
								<td>Lilly Mendez</td>
								<td>Person</td>
								<td>15%</td>
								<td>USA</td>
								<td>-</td>
								<td class="tableModule-bodyItem-action">
									<div class="deleteDropdown js-deleteDropdown">
										<button class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn" >
											<icon:remove/>
										</button>
										<div class="deleteDropdown-drop">
											<div class="deleteDropdown-text">
												Delete the shareholder?
											</div>
											<div class="deleteDropdown-actions">
												<button class="btn btn_outline btn_slim js-deleteDropdown-cancel" >
													Cancel
												</button>
												<button class="btn btn_slim" >
													Delete
												</button>
											</div>
										</div>
									</div>
									<button class="btn btn_link" data-toggle="modal" data-target="#mo1">
										<icon:edit/>
									</button>
								</td>
							</tr>
							<tr>
								<td>Franklin Ltd. & Co.</td>
								<td>Entity</td>
								<td>55%</td>
								<td>USA</td>
								<td>Individual LLC</td>
								<td class="tableModule-bodyItem-action">
									<div class="deleteDropdown js-deleteDropdown">
										<button class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn" >
											<icon:remove/>
										</button>
										<div class="deleteDropdown-drop">
											<div class="deleteDropdown-text">
												Delete the shareholder?
											</div>
											<div class="deleteDropdown-actions">
												<button class="btn btn_outline btn_slim js-deleteDropdown-cancel" >
													Cancel
												</button>
												<button class="btn btn_slim" >
													Delete
												</button>
											</div>
										</div>
									</div>
									<button class="btn btn_link" data-toggle="modal" data-target="#mo1">
										<icon:edit/>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
	                <button class="btn btn-primary">+ New shareholder</button>
	            </div>



	            <div class="row">
	               <div class="col-md-6">
	            		<div class="formRadioBox">
	            			<div class="form-group">
	            				<div class="formRadioBox-label">* Shareholder type</div>
	            				<div class="form-item">
	            					<input id="a011" name="radioBox01" class="form-control" value="false" type="radio">
	            					<label for="a011" class="control-label">
	            						Person
	            					</label>
	            				</div>
	            				<div class="form-item">
	            					<input id="a021" name="radioBox01" class="form-control" value="false" type="radio">
	            					<label for="a021" class="control-label">
	            						Entity
	            					</label>
	            				</div>
	            			</div>
	            		</div>                    
	               	</div>
	            </div>
	            
	            
	            <div class="contentModule-separator"></div>
	            
	            
	            <div class="contentModule-headline">
	            	Basic Information
	            </div>
	            
	            <div class="row">
	               <div class="col-md-6">
	            		<div class="formRadioBox">
	            			<div class="form-group">
	            				<div class="formRadioBox-label">* Title</div>
	            				<div class="form-item">
	            					<input id="a0111" name="radioBox011" class="form-control" value="false" type="radio">
	            					<label for="a0111" class="control-label">
	            						Mrs
	            					</label>
	            				</div>
	            				<div class="form-item">
	            					<input id="a0211" name="radioBox011" class="form-control" value="false" type="radio">
	            					<label for="a0211" class="control-label">
	            						Mr
	            					</label>
	            				</div>
	            			</div>
	            		</div>                    
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">Academic title</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* First name
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* Last name
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">Academic title</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox formInputBox_group ">
	            				                <div class="form-group">
	            				                    <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date flatpickr-input" placeholder="." value="" readonly="readonly" type="text">
	            				                    <label class="control-label" for="profile.enquiry.type">
	            				                        Default
	            				                    </label>
	            				                    <div class="formInputBox-append">
	            				                        <span class="formInputBox-text">
	            				                        	<icon:calendar-gray/>
	            				                    	</span>
	            				                    </div>              
	            				                </div>
	            				            </div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">Current Nationality</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">Previous Nationality</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-sm-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* Shares percentage
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-sm-6">
	               		<div class="formRangeSlider formRangeSlider_slim js-formRangeSlider">
	            			<div class="form-group">
	            				<input type="range">
	            			</div>	
	            		</div> 
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formCheckBox">
	            			<div class="form-group">
	            				<div class="form-item">
	            					<input id="checkbox0166666" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="">
	            					<label class="control-label " for="checkbox0166666">
	            						<span>
	            							<icon:check/>
	            						</span> This is an inherited property.
	            					</label>
	            				</div>	
	            			</div>
	            		</div>
	               	</div>
	            </div>
	            
	            
	            
	            <div class="contentModule-separator"></div>
	            
	            
	            <div class="contentModule-headline">
	            	Contact information
	            </div>
	            
	            
	            
	            
	            <div class="row">
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">* Country</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox-split">
	            			<div class="formInputBox formInputBox_big">
	            				<div class="form-group">
	            					<input class="form-control form-control_labeled" placeholder="." value="" type="text">
	            					<label class="control-label" for="">
	            					Street</label>
	            				</div>
	            			</div>
	            			<div class="formInputBox">
	            				<div class="form-group">
	            					<input class="form-control form-control_preNumber" placeholder="." value="" type="text">
	            					<label class="control-label" for="">
	            					No.</label>
	            				</div>
	            			</div>
	            		</div>
	            	</div>
	               	<div class="col-md-6">
	               		<div class="formSelectBox">
	            		    <div class="form-group">
	            		        <select id="" name="" class="js-select2-oneColumn form-control">
	            		        	<option></option>
	            					<option value="1"></option>
	            					<option value="2"></option>
	            				</select>
	            		        <label class="control-label" for="">City</label>
	            		    </div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* ZIP code
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* Telephone number
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* Email Address
	            				</label>				
	            			</div>
	            		</div>
	               	</div>
	               	<div class="col-md-6">
	               		<div class="formInputBox formInputBox_group">
	            			<div class="form-group">
	            				<input id="" class="form-control" placeholder="." value="" type="text">
	            				<label class="control-label" for="">
	            					* Website URL
	            				</label>
	            				<div class="formInputBox-append">
	            					<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
	            					</span>
	            				</div>		
	            			</div>
	            		</div>
	            	</div>	
	            </div>



				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
	                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
	                    Cancel
	                </button>
	                <button type="submit" class="btn btn-slim">
	                    Save
	                </button>
	            </div>
		            
	            
			</div>
			<div class="contentModule-actions contentModule-actions_spaceBetween">
                <button type="button" class="btn btn-secondary">
                    Cancel
                </button>
                <button type="submit" class="btn">
                    Submit
                </button>
            </div>
		</div>

		




	</div>
	<div class="panelTabs-head" id="tab3">
		<icon:branch/>
		<span class="panelTabs-label">Branch</span>
	</div>
	<div class="panelTabs-body">
		<div class="contentModule">
			<div class="contentModule-section">
				<div class="tableModule">
					<table class="tableModule-table">
						<thead class="tableModule-head">
							<tr>
								<th>Branch type</th>
								<th>Branch name</th>
								<th>City</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="tableModule-body">
							<tr>
								<td></td>
								<td>google.com</td>
								<td>Provo</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link"  data-toggle="modal" data-target="#mo2">
										<icon:edit/>
									</button>
								</td>
							</tr>
							<tr>
								<td><strong>Factory Branch</strong></td>
								<td>Branch name</td>
								<td>Hangzhou</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
								</td>
							</tr>
							<tr>
								<td><strong>Commercial Center</strong></td>
								<td>Branch name</td>
								<td>Sacramento</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
								</td>
							</tr>							
						</tbody>
					</table>
				</div>

				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
	                <button class="btn btn-primary">+ New branch</button>
	            </div>


	            <div class="contentModule-headline">
					Branch details
				</div>

				<div class="row">
				   	<div class="col-md-6">
				   		<div class="formSelectBox">
						    <div class="form-group">
						        <select id="" name="" class="js-select2-oneColumn form-control">
						        	<option></option>
									<option value="1"></option>
									<option value="2"></option>
								</select>
						        <label class="control-label" for="">* Branch type</label>
						    </div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									Branch name
								</label>				
							</div>
						</div>
				   	</div>
				</div>



				<div class="contentModule-separator"></div>


				<div class="contentModule-headline">
					Contact information
				</div>




				<div class="row">
				   	<div class="col-md-6">
				   		<div class="formSelectBox">
						    <div class="form-group">
						        <select id="" name="" class="js-select2-oneColumn form-control">
						        	<option></option>
									<option value="1"></option>
									<option value="2"></option>
								</select>
						        <label class="control-label" for="">* Country</label>
						    </div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox-split">
							<div class="formInputBox formInputBox_big">
								<div class="form-group">
									<input class="form-control form-control_labeled" placeholder="." value="" type="text">
									<label class="control-label" for="">
									Street</label>
								</div>
							</div>
							<div class="formInputBox">
								<div class="form-group">
									<input class="form-control form-control_preNumber" placeholder="." value="" type="text">
									<label class="control-label" for="">
									No.</label>
								</div>
							</div>
						</div>
					</div>
				   	<div class="col-md-6">
				   		<div class="formSelectBox">
						    <div class="form-group">
						        <select id="" name="" class="js-select2-oneColumn form-control">
						        	<option></option>
									<option value="1"></option>
									<option value="2"></option>
								</select>
						        <label class="control-label" for="">City</label>
						    </div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									* ZIP code
								</label>				
							</div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									* Telephone number
								</label>				
							</div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									* Email Address
								</label>				
							</div>
						</div>
				   	</div>
				   	<div class="col-md-6">
				   		<div class="formInputBox formInputBox_group">
							<div class="form-group">
								<input id="" class="form-control" placeholder="." value="" type="text">
								<label class="control-label" for="">
									* Website URL
								</label>
								<div class="formInputBox-append">
									<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
									</span>
								</div>		
							</div>
						</div>
					</div>	
				</div>


				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
	                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
	                    Cancel
	                </button>
	                <button type="submit" class="btn btn-slim">
	                    Save
	                </button>
	            </div>


			</div>
			<div class="contentModule-actions contentModule-actions_spaceBetween">
                <button type="button" class="btn btn-secondary">
                    Cancel
                </button>
                <button type="submit" class="btn">
                    Submit
                </button>
            </div>
		</div>
	</div>
	<div class="panelTabs-head" id="tab4">
		<icon:productinTab/>
		<span class="panelTabs-label">Product</span>
	</div>
	<div class="panelTabs-body">
		<div class="contentModule">
			<div class="contentModule-section">
				<div class="tableModule">
					<table class="tableModule-table">
						<thead class="tableModule-head">
							<tr>
								<th>Product ID</th>
								<th>Product Description</th>
								<th>Qty</th>
								<th>Unit</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="tableModule-body">
							<tr>
								<td><strong>39369094</strong></td>
								<td>Product Description</td>
								<td>1000.000</td>
								<td>each</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
									<button class="btn btn_link">
										<icon:edit/>
									</button>
								</td>
							</tr>	
							<tr>
								<td><strong>39369094</strong></td>
								<td>Product Description</td>
								<td>1500.000</td>
								<td>Legal status</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
									<button class="btn btn_link">
										<icon:edit/>
									</button>
								</td>
							</tr>	
							<tr>
								<td><strong>39369094</strong></td>
								<td>Product Description</td>
								<td>2000.000</td>
								<td>each</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
									<button class="btn btn_link">
										<icon:edit/>
									</button>
								</td>
							</tr>	
							<tr>
								<td><strong>25020000</strong></td>
								<td>Unroasted iron pyrites.</td>
								<td>100.000</td>
								<td>each</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
									<button class="btn btn_link">
										<icon:edit/>
									</button>
								</td>
							</tr>	
							<tr>
								<td><strong>39204900</strong></td>
								<td>Product Description</td>
								<td>20.000</td>
								<td>each</td>
								<td class="tableModule-bodyItem-action">
									<button class="btn btn_link">
										<icon:remove/>
									</button>
									<button class="btn btn_link">
										<icon:edit/>
									</button>
								</td>
							</tr>	
						</tbody>
					</table>
				</div>

				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
	                <button class="btn btn-primary">+ New product</button>
	            </div>


	            <div class="row">
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								Product ID
							</label>				
						</div>
					</div>
			   	</div>
			</div>

			<div class="formTextArea">
				<div class="form-group">
					<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
					<label class="control-label" for="">
						Textarea
					</label>
				</div>
			</div>

			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* QTY
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* UNIT
							</label>				
						</div>
					</div>
			   	</div>
			</div>



			<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
                    Cancel
                </button>
                <button type="submit" class="btn btn-slim">
                    Save
                </button>
            </div>

			</div>
			<div class="contentModule-actions contentModule-actions_spaceBetween">
                <button type="button" class="btn btn-secondary">
                    Cancel
                </button>
                <button type="submit" class="btn"   data-toggle="modal" data-target="#docsmodal">
                    Submit
                </button>
            </div>
		</div>
	</div>
</div>   











<div class="modal fade" id="mo1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content panelModule panelModule_halfRadius">
			<div class="contentModule-headline">
				Edit bla bla
			</div>
			<div class="row">
			   <div class="col-md-6">
					<div class="formRadioBox">
						<div class="form-group">
							<div class="formRadioBox-label">* Shareholder type</div>
							<div class="form-item">
								<input id="a011" name="radioBox01" class="form-control" value="false" type="radio">
								<label for="a011" class="control-label">
									Person
								</label>
							</div>
							<div class="form-item">
								<input id="a021" name="radioBox01" class="form-control" value="false" type="radio">
								<label for="a021" class="control-label">
									Entity
								</label>
							</div>
						</div>
					</div>                    
			   	</div>
			</div>

			
			<div class="contentModule-separator"></div>


			<div class="contentModule-headline">
				Basic Information
			</div>

			<div class="row">
			   <div class="col-md-6">
					<div class="formRadioBox">
						<div class="form-group">
							<div class="formRadioBox-label">* Title</div>
							<div class="form-item">
								<input id="a0111" name="radioBox011" class="form-control" value="false" type="radio">
								<label for="a0111" class="control-label">
									Mrs
								</label>
							</div>
							<div class="form-item">
								<input id="a0211" name="radioBox011" class="form-control" value="false" type="radio">
								<label for="a0211" class="control-label">
									Mr
								</label>
							</div>
						</div>
					</div>                    
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">Academic title</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* First name
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Last name
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">Academic title</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox formInputBox_group ">
		                <div class="form-group">
		                    <input id="profile.enquiry.type" name="enquiryType" class="form-control js-form-control_date flatpickr-input" placeholder="." value="" readonly="readonly" type="text">
		                    <label class="control-label" for="profile.enquiry.type">
		                        Default
		                    </label>
		                    <div class="formInputBox-append">
		                        <span class="formInputBox-text">
		                        	<icon:calendar-gray/>
		                    	</span>
		                    </div>              
		                </div>
		            </div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">Current Nationality</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">Previous Nationality</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-sm-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Shares percentage
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-sm-6">
			   		<div class="formRangeSlider formRangeSlider_slim js-formRangeSlider">
						<div class="form-group">
							<input type="range">
						</div>	
					</div> 
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formCheckBox">
						<div class="form-group">
							<div class="form-item">
								<input id="checkbox0166666" name="checkbox01name" class="form-control" placeholder="." type="checkbox" value="">
								<label class="control-label " for="checkbox0166666">
									<span>
										<icon:check/>
									</span> This is an inherited property.
								</label>
							</div>	
						</div>
					</div>
			   	</div>
			</div>



			<div class="contentModule-separator"></div>


			<div class="contentModule-headline">
				Contact information
			</div>




			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">* Country</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox-split">
						<div class="formInputBox formInputBox_big">
							<div class="form-group">
								<input class="form-control form-control_labeled" placeholder="." value="" type="text">
								<label class="control-label" for="">
								Street</label>
							</div>
						</div>
						<div class="formInputBox">
							<div class="form-group">
								<input class="form-control form-control_preNumber" placeholder="." value="" type="text">
								<label class="control-label" for="">
								No.</label>
							</div>
						</div>
					</div>
				</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">City</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* ZIP code
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Telephone number
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Email Address
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox formInputBox_group">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Website URL
							</label>
							<div class="formInputBox-append">
								<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
								</span>
							</div>		
						</div>
					</div>
				</div>	
			</div>



			<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
                    Cancel
                </button>
                <button type="submit" class="btn btn-slim">
                    Save
                </button>
            </div>


		</div>
	</div>
</div>	





















<div class="modal fade" id="mo2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content panelModule panelModule_halfRadius">
			<div class="contentModule-headline">
				Edit bla bla
			</div>
			


			<div class="contentModule-headline">
				Branch details
			</div>

			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">* Branch type</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								Branch name
							</label>				
						</div>
					</div>
			   	</div>
			</div>



			<div class="contentModule-separator"></div>


			<div class="contentModule-headline">
				Contact information
			</div>




			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">* Country</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox-split">
						<div class="formInputBox formInputBox_big">
							<div class="form-group">
								<input class="form-control form-control_labeled" placeholder="." value="" type="text">
								<label class="control-label" for="">
								Street</label>
							</div>
						</div>
						<div class="formInputBox">
							<div class="form-group">
								<input class="form-control form-control_preNumber" placeholder="." value="" type="text">
								<label class="control-label" for="">
								No.</label>
							</div>
						</div>
					</div>
				</div>
			   	<div class="col-md-6">
			   		<div class="formSelectBox">
					    <div class="form-group">
					        <select id="" name="" class="js-select2-oneColumn form-control">
					        	<option></option>
								<option value="1"></option>
								<option value="2"></option>
							</select>
					        <label class="control-label" for="">City</label>
					    </div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* ZIP code
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Telephone number
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Email Address
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox formInputBox_group">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* Website URL
							</label>
							<div class="formInputBox-append">
								<span class="formInputBox-text formInputBox-text_tip js-tip" style="position: relative;z-index: 1000;" data-tip-title="Tooltip Information to be shown to the user." data-original-title="" title=""><icon:tipInfo/>
								</span>
							</div>		
						</div>
					</div>
				</div>	
			</div>



			<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
                    Cancel
                </button>
                <button type="submit" class="btn btn-slim">
                    Save
                </button>
            </div>


		</div>
	</div>
</div>	




















<div class="modal fade" id="mo3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content panelModule panelModule_halfRadius">
			<div class="contentModule-headline">
				Edit
			</div>
			
			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								Product ID
							</label>				
						</div>
					</div>
			   	</div>
			</div>

			<div class="formTextArea">
				<div class="form-group">
					<textarea id=""  class="form-control form-control_slim" placeholder="."></textarea>
					<label class="control-label" for="">
						Textarea
					</label>
				</div>
			</div>

			<div class="row">
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* QTY
							</label>				
						</div>
					</div>
			   	</div>
			   	<div class="col-md-6">
			   		<div class="formInputBox">
						<div class="form-group">
							<input id="" class="form-control" placeholder="." value="" type="text">
							<label class="control-label" for="">
								* UNIT
							</label>				
						</div>
					</div>
			   	</div>
			</div>



			<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                <button type="button" class="btn btn-slim btn_outline"  data-dismiss="modal">
                    Cancel
                </button>
                <button type="submit" class="btn btn-slim">
                    Save
                </button>
            </div>


		</div>
	</div>
</div>	





































<div class="modal fade" id="unsavedChanges3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<form action="" class="js-formInputFileBox">
				<div class="modal-header modal-header_smallPDB">
					<div class="modal-title">You have unsaved changes</div>
					<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
						<icon:close/>
					</button>	
				</div>
				<div class="modal-body">
					<div class="modal-description modal-description_largeMargin modal-description_smallText">
						There are still changes in the form that were not yet submited. In order to amend those informations you need to either submit those changes or dismiss them. 
					</div>
				</div>
				<div class="modal-footer modal-footer_spaceBetween">
					<button type="button" type="submit" class="btn btn-warning btn_round btn_slim" data-dismiss="modal">Dismiss Changes</button>
					<button type="button" type="submit" class="btn btn_round btn_slim">Submit Changes</button>
				</div>
			</form>	
		</div>
	</div>
</div>















<div class="modal fade" id="docsmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content  panelModule panelModule_halfRadius">
			<div class="contentModule">
				<div class="contentModule-headline">
					Documents
				</div>
				<div class="contentModule-section">

					<div class="contentModule-headline contentModule-headline_marginBottom">Amendment types</div>

					<div class="row">
						<div class="col-sm-6">
							<ul class="dottedList dottedList_green dottedList_big">
							    <li class="dottedList-item">Shareholder Exit</li>
							    <li class="dottedList-item">New Company</li>
							</ul>	
						</div>
						<div class="col-sm-6">
							<ul class="dottedList dottedList_green dottedList_big">
							    <li class="dottedList-item">Capital increase</li>
							    <li class="dottedList-item">Increase workforce</li>
							</ul>	
						</div>

					</div>


					<div class="contentModule-separator"></div>


					<div class="contentModule-headline">Supporting documents</div>


					<div class="row">
						<div class="col-sm-6">
							<div class="formInputFile">
								<div class="form-group">
									<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
									<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
									<label class="control-label " for="">Board Resoulution</label>
									<div class="form-icon form-icon_browse">
										<icon:upload/>
									</div>
									<div class="form-icon form-icon_reset js-inputFile-reset">
										<icon:cross/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="formInputFile">
								<div class="form-group">
									<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
									<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
									<label class="control-label " for="">Contract of Sale</label>
									<div class="form-icon form-icon_browse">
										<icon:upload/>
									</div>
									<div class="form-icon form-icon_reset js-inputFile-reset">
										<icon:cross/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="formInputFile">
								<div class="form-group">
									<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
									<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
									<label class="control-label " for="">sidf approval </label>
									<div class="form-icon form-icon_browse">
										<icon:upload/>
									</div>
									<div class="form-icon form-icon_reset js-inputFile-reset">
										<icon:cross/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="formInputFile">
								<div class="form-group">
									<input id="file08" name="file08" class="form-control js-inputFile" type="file" value="">
									<input id="text08" name="text08" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
									<label class="control-label " for="">Document of the new Sahreholders</label>
									<div class="form-icon form-icon_browse">
										<icon:upload/>
									</div>
									<div class="form-icon form-icon_reset js-inputFile-reset">
										<icon:cross/>
									</div>
								</div>
							</div>
						</div>

					</div>


					<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
		                <button type="button" class="btn btn-slim btn_outline">
		                    Cancel
		                </button>
		                <button type="submit" class="btn btn-slim">
		                    Save
		                </button>
		            </div>
					
				</div>

			</div>
		</div>
	</div>
</div>