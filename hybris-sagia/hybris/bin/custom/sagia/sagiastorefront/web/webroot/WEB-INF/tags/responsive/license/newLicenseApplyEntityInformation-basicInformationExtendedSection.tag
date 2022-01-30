<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<!--Basic information extended-->
<div>
<div class="rhqSelectBoxes" style="display:none">
<!--Optional/Functions/Corporate  Activities start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqCheckbox" name="listOfCorporateActivities"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfCorporateActivities}">

			</select>
			<label class="control-label" for="rhqCheckbox">* Optional/Functions/Corporate Activities</label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Optional/Functions/Corporate  Activities End-->

<!--Strategic direction includes start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqStrategicCheckbox" name="listOfStrategicActivities"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfStrategicActivities}">

			</select>
			<label class="control-label" for="rhqStrategicCheckbox">* Strategic direction includes</label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Strategic direction includes End-->

<!--Management functions include start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqManagementFunCheckbox" name="listOfManagementActivities"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfManagementActivities}">
			</select>
			<label class="control-label" for="rhqManagementFunCheckbox">* Management functions include</label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Management functions include End-->

<!--Center of Administrative start-->


	<div class="formSelectBox">
			<div class="form-group">
				<select id="rhqCenterAdmin" name="rhqCenterAdmin" class="js-select2-search form-control" >
					<option></option>
					 <option value="GCC">GCC</option>
                     <option value="MENA">MENA</option>
                     <option value="Middle East (ME)">Middle East (ME)</option>
				</select>
				<label class="control-label" for="rhqCenterAdmin">* Center of Administrative</label>
				<div id="rhqCenterAdmin-error" class="help-block"></div>
			</div>
		</div>


		<div class="formSelectBox" id="rhqCountryRegion" style="display:none">
		<div class="form-group">
			<select id="centerAdminRhqRegionsSection" name="centerAdminRhqRegionsSection"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="">
			</select> <label class="control-label" for="centerAdminRhqRegionsSection">* RHQ Regions</label>
		</div>
		<div class="help-block"></div>
	</div>

	<!--MNC Subsidiaries start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqSubsidiaryPresence" name="rhqSubsidiaryPresence"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="">
			</select>
			<label class="control-label" for="rhqSubsidiaryPresence">* MNC subsidiaries or branch presence</label>
		</div>
		<div class="help-block"></div>
	</div>
<!--MNC Subsidiaries End-->


<!--Center of Administrative End-->
</div>

<!--Edit Branch Table Model-->

<div class="modal fade" id="EditBranchTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Edit Branch</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form action="">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBranchName" name="editBranchName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Company
			                    </label>
                                <div class="help-block"></div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBranchCountry" name="editBranchCountry" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
                    <option value="Kingdom of Saudi Arabia">Kingdom of Saudi Arabia</option>
					<option value="Romania">Romania</option>
                    <option value="Germany">Germany</option>
                    <option value="India">India</option>
                    <option value="Andorra">Andorra</option>
                    <option value=">United Arab Emirates">United Arab Emirates</option>
                    <option value="Afghanistan">Afghanistan</option>
				</select>
				<label class="control-label" for="">* Country</label>
				<div id="editBranchCountry-error" class="help-block"></div>
			</div>
		</div>
							</div>

						</div>
                        <div class="row">
                        	<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBranchBuz" name="editBranchBuz" class="js-select2-search form-control" >
					<option></option>
					 <option value="Joint Venture">Joint Venture</option>
                     <option value="Affiliate">Affiliate</option>
                     <option value="Distributor">Distributor</option>
                     <option value="Others">Others</option>
				</select>
				<label class="control-label" for="">* Business Relationship Type</label>
				<div id="editBranchBuz-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBranchIndustry" name="editBranchIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Industry
			                    </label>
								<div id="editBranchIndustry-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBranchOperation" name="editBranchOperation" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
					 <option value="Manufacturing">Manufacturing</option>
                    <option value="Assembly">Assembly</option>
                    <option value="Others">Others</option>
				</select>
				<label class="control-label" for="">* Operations</label>
				<div id="editBranchOperation-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBranchRhqActivity" name="editBranchRhqActivity" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				               * RHQ Activity
			                    </label>
								<div id="editBranchRhqActivity-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                        </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityEditBranch">Update</button>
			</div>
		</div>
	</div>
</div>
<!--Edit Branch Table Model End-->



<!--Add Branch Table Model-->

<div class="modal fade" id="addBranchTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Add New Branch</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form id="addBranchForm" name="addBranchForm" action="">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBranchName" name="addBranchName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Company
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBranchCountry" name="addBranchCountry" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
                    <option value="Kingdom of Saudi Arabia">Kingdom of Saudi Arabia</option>
					<option value="Romania">Romania</option>
                    <option value="Germany">Germany</option>
                    <option value="India">India</option>
                    <option value="Andorra">Andorra</option>
                    <option value="United Arab Emirates">United Arab Emirates</option>
                    <option value="Afghanistan">Afghanistan</option>
				</select>
				<label class="control-label" for="">* Country</label>
				<div id="addBranchCountry-error" class="help-block"></div>
			</div>
		</div>
							</div>

						</div>
                        <div class="row">
                        	<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBranchBuz" name="addBranchBuz" class="js-select2-search form-control" >
					<option></option>
					 <option value="Joint Venture">Joint Venture</option>
                     <option value="Affiliate">Affiliate</option>
                     <option value="Distributor">Distributor</option>
                     <option value="Others">Others</option>
				</select>
				<label class="control-label" for="">* Business Relationship Type</label>
				<div id="addBranchBuz-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBranchIndustry" name="addBranchIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Industry
			                    </label>
								<div id="addBranchIndustry-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBranchOperation" name="addBranchOperation" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
					 <option value="Manufacturing">Manufacturing</option>
                    <option value="Assembly">Assembly</option>
                    <option value="Others">Others</option>
				</select>
				<label class="control-label" for="">* Operations</label>
				<div id="addBranchOperation-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBranchRhqActivity" name="addBranchRhqActivity" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				               * RHQ Activity
			                    </label>
								<div id="addBranchRhqActivity-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                        </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityAddBranch">Add</button>
			</div>
		</div>
	</div>
</div>
<!--Add Branch Table Model End-->



<!--Add Brand Table Model-->

<div class="modal fade" id="addBrandTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Add New Brand</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form id="addBrandForm" method ="post" name="addBrandForm" onsubmit="return false">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addBrandName" name="addBrandName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Brand Name
			                    </label>
                                <div class="help-block">
					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBrandCountry" name="addBrandCountry" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
                    <option value="Kingdom of Saudi Arabia">Kingdom of Saudi Arabia</option>
					<option value="Romania">Romania</option>
                    <option value="Germany">Germany</option>
                    <option value="India">India</option>
                    <option value="Andorra">Andorra</option>
                    <option value="United Arab Emirates">United Arab Emirates</option>
                    <option value="Afghanistan">Afghanistan</option>
				</select>
				<label class="control-label" for="">* Country</label>
				<div id="addBrandCountry-error" class="help-block"></div>
			</div>
		</div>
							</div>

						</div>
                        <div class="row">
                         <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBrandIndustry" name="addBrandIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Industry
			                    </label>
								<div id="addBrandIndustry-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBrandMena" name="addBrandMena"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                 Company owning the brand in MENA
			                    </label>
								<div id="addBrandMena-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>


                        </div>
                           <div class="row">
                         <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBrandProvider" name="addBrandProvider"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                 RHQ activity Provided
			                    </label>
								<div id="addBrandProvider-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                            </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityAddBrand">Add</button>
			</div>
		</div>
	</div>
</div>
<!--Add Brand Table Model End-->



<!--Edit Brand Table Model-->

<div class="modal fade" id="EditBrandTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Edit Brand</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form action="">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBrandName" name="editBrandName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Brand Name
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBrandCountry" name="editBrandCountry" class="js-select2-search form-control" data-search-placeholder="Filter by Product ID / Product Description">
					<option></option>
                    <option value="Kingdom of Saudi Arabia">Kingdom of Saudi Arabia</option>
					<option value="Romania">Romania</option>
                    <option value="Germany">Germany</option>
                    <option value="India">India</option>
                    <option value="Andorra">Andorra</option>
                    <option value=">United Arab Emirates">United Arab Emirates</option>
                    <option value="Afghanistan">Afghanistan</option>
				</select>
				<label class="control-label" for="">* Country</label>
				<div id="editBrandCountry-error" class="help-block"></div>
			</div>
		</div>
							</div>

						</div>
                        <div class="row">
                         <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBrandIndustry" name="editBrandIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Industry
			                    </label>
								<div id="editBrandIndustry-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBrandMena" name="editBrandMena"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                 Company owning the brand in MENA
			                    </label>
								<div id="editBrandMena-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>


                        </div>
                           <div class="row">
                         <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBrandProvider" name="editBrandProvider"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                 RHQ activity Provided
			                    </label>
								<div id="editBrandProvider-error" class="help-block"></div>
		                        </div>
	                    </div>
							</div>
                            </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityEditBrand">Update</button>
			</div>
		</div>
	</div>
</div>
<!--Edit Brand Table Model End-->

<!--Add Estimated operating costs for the RHQ Table Model-->

<div class="modal fade" id="addrhqCostTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Add Estimated operating costs for the RHQ</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form id="addItemForm" name="addItemForm" action="">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addItemName" name="addItemName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Item Name
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
						<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addUnitCost" name="addUnitCost" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Unit Cost
			                    </label>

		                        </div>
	                    </div>
							</div>

						</div>
                        <div class="row">
                        	<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addNoUnits" name="addNoUnits" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Number of Units
			                    </label>

		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addCostFreq" name="addCostFreq" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Cost frequency
			                    </label>

		                        </div>
	                    </div>
							</div>


                        </div>
                           <div class="row">
                          <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addYear1" name="addYear1" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2022
			                    </label>

		                        </div>
	                    </div>
							</div>
                              <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addYear2" name="addYear2" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2023
			                    </label>

		                        </div>
	                    </div>
							</div>
                            </div>
                             <div class="row">
                          <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addYear3" name="addYear3" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2023
			                    </label>

		                        </div>
	                    </div>
							</div>

                            </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityAddItem">Add</button>
			</div>
		</div>
	</div>
</div>
<!--Add Estimated operating costs for the RHQ Table Model End-->


<!--Edit Estimated operating costs for the RHQ Table Model-->

<div class="modal fade" id="EditrhqCostTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">Edit Estimated operating costs for the RHQ</div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">


					<form action="">
						<div class="row">
							<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editItemName" name="editItemName" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                * Item Name
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
						<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editUnitCost" name="editUnitCost" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Unit Cost
			                    </label>

		                        </div>
	                    </div>
							</div>

						</div>
                        <div class="row">
                        	<div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editNoUnits" name="editNoUnits" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Number of Units
			                    </label>

		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editCostFreq" name="editCostFreq" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Cost frequency
			                    </label>

		                        </div>
	                    </div>
							</div>


                        </div>
                           <div class="row">
                          <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editYear1" name="editYear1" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2022
			                    </label>

		                        </div>
	                    </div>
							</div>
                              <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editYear2" name="editYear2" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2023
			                    </label>

		                        </div>
	                    </div>
							</div>
                            </div>
                             <div class="row">
                          <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editYear3" name="editYear3" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                * Year 2023
			                    </label>

		                        </div>
	                    </div>
							</div>

                            </div>
					</form>
				</div>
			</div>
			<div class="modal-footer modal-footer_centered">
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityEditItem">Update</button>
			</div>
		</div>
	</div>
</div>
<!--Edit Estimated operating costs for the RHQ Table Model End-->

<div class="rqh-tables" style="display:none">

<div class="contentModule-headline contentModule-headline_smallMargin">* MNC Branches</div>
<div class="tableModule" >

<div class="formInputBox">
 <div class="form-group ">
 <div id="mncBranchTable-error" class="help-block"></div>
 </div>
</div>

  <table class="tableModule-table" id="mncBranchTable">
	<thead class="tableModule-head">
	  <tr>
		<th>Company Name</th>
		<th>Country</th>
		<th>Business</th>
		<th>Industry</th>
		<th>Operations</th>
        <th>HRQ Activity Provided</th>
        <th>Action</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">
<!--	<tr>
    <td>Lorem Ipsum is simply dummy text </td>
    <td>Lorem Ipsum </td>
    <td>Lorem Ipsum </td>
    <td>Lorem Ipsum </td>
    <td>Lorem Ipsum </td>
    <td>Lorem Ipsum</td>
    <td><a  class="btn btn_link iconElement iconElement_edit02 edit_btn_click" data-toggle="modal" data-target="#EditBranchTable">
	<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18"><path fill="#5CC83B" d="M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z"></path></svg>
    </a>
<button type="" class="btn btn_link iconElement iconElement_edit02 delete_btn_click">
      <svg class="icon icon-delete" height="18" viewBox="0 0 24 24" width="18" xmlns="http://www.w3.org/2000/svg">
      <path fill="#d0021b" d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
    </svg>
      </button>
    </td>
    </tr>

	-->



	</tbody>
  </table>
  <input type="hidden" id="rowToDelete" name="rowToDelete" value="0">
  	<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin w-100">
				<a href="#" data-toggle="modal" data-target="#addBranchTable" style="margin-top: 16px; color: #73c859;display:flex;align-items:center;">
				<svg class="icon icon-add" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
      <path  fill="#5CC83B" d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
    </svg> Add New</a>
				</div>
</div>

</div>

<!--MNC Brand Start-->
<div class="rqh-tables" style="display:none">
<div class="contentModule-headline contentModule-headline_smallMargin">* MNC Brand</div>
<div class="tableModule" >

<div class="formInputBox">
 <div class="form-group ">
 <div id="mncBrandTable-error" class="help-block"></div>
 </div>
</div>

  <table class="tableModule-table" id="mncBrandTable">
	<thead class="tableModule-head">
	  <tr>
		<th>Brand Name</th>
		<th>Country</th>
		<th>Industry</th>
		<th>Company owning the brand in MENA</th>
		<th>RHQ activity Provided</th>
        <th>Action</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">




	</tbody>
  </table>
  <input type="hidden" id="rowToDelete" name="rowToDelete" value="0">
  	<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin w-100">
				<a href="#" data-toggle="modal" data-target="#addBrandTable" style="margin-top: 16px; color: #73c859;display:flex;align-items:center;">
				<svg class="icon icon-add" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
      <path  fill="#5CC83B" d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
    </svg>Add New</a>
				</div>
</div>
</div>
<!--MNC Brand End-->


<!--rhqCostTable-->
<div class="rqh-tables" style="display:none">
<div class="contentModule-headline contentModule-headline_smallMargin">* Estimated operating costs for the RHQ</div>
<div class="tableModule" >

<div class="formInputBox">
 <div class="form-group ">
 <div id="rhqCostTable-error" class="help-block"></div>
 </div>
</div>

  <table class="tableModule-table" id="rhqCostTable">
	<thead class="tableModule-head">
	  <tr>
		<th>Item</th>
		<th>Unit Cost</th>
		<th>Number of units</th>
		<th>Cost frequency</th>
		<th>Year 2022</th>
        <th>Year 2023</th>
        <th>Year 2024</th>
        <th>Action</th>
	  </tr>
	</thead>
	<tbody class="tableModule-body">



	</tbody>
    <tfoot>
    <tr>
    <th></th>
    <th></th>
    <th></th>
    <th></th>
    <th id="rhqCostTable-sum1"></th>
	<th id="rhqCostTable-sum2"></th>
	<th id="rhqCostTable-sum3"></th>
     <th></th>
    </tr>
    </tfoot>
  </table>
  <input type="hidden" id="rowToDelete" name="rowToDelete" value="0">
  	<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin w-100">
			 <a href="#" data-toggle="modal" data-target="#addrhqCostTable" style="margin-top: 16px; color: #73c859;display:flex;align-items:center;">
				<svg class="icon icon-add" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
      <path  fill="#5CC83B" d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/>
    </svg>Add New</a>
				</div>
</div>
</div>
<!--rhqCostTable-->


<div>
<div class="contentModule-section" id="basicInformationExtendedSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.basicInformation"/></div>
    <div class="row">
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEntityName"
                                      labelKey="profile.company.entityName" path="entityName"
                                      labelCSS="control-label_mandatory"
                                      inputCSS="text validate__no-special-chars" mandatory="true" maxlength="100" />
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEntityNameArabic"
                                      labelKey="profile.company.entityNameArabic"
                                      path="entityNameArabic" inputCSS="text validate__arabic-only" maxlength="80" />
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedLegalStatus"
                                       labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.legalStatus"
                                       path="legalStatus" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.legalStatus}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedLegalStatus" name="legalStatus" class="js-select2-oneColumn form-control" placeholder="."></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedLegalStatus"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.legalStatus"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedMultinationalCompany"
                                       labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.multinationalCompany"
                                       path="basicInfoExtendedMultinationalCompany" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.basicInfoExtendedMultinationalCompany}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedMultinationalCompany" name="basicInfoExtendedMultinationalCompany" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedMultinationalCompany"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.multinationalCompany"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedCapital"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.capital" path="capital"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__float-numbers-only" mandatory="true" inputBoxCSS="formInputBox_group"
                                      inputBoxAppendKey="licenseApplyEntityInformation.basicInformationExtendedSection.sar" maxlength="20"/>
<%--            <div class="formInputBox formInputBox_group">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedCapital" name="capital" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.capital}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCapital"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.capital"/></label>--%>
<%--                    <div class="formInputBox-append">--%>
<%--                        <span class="formInputBox-text"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.sar"/></span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedEmail"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.email" path="email"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__email" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedEmail" name="email" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.email}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedEmail"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.email"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <formElement:formInputBoxCustom idKey="basicInformationExtendedCountryCodeForTelephone" maxlength="5"
                                                labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"
                                                path="countryCodeForTelephone" mandatory="true" labelCSS="control-label"
                                                inputCSS="text form-control_preNumber validate__numbers-only"
                                                dataValue="${sagiaApplyEntityInfoForm.countryCodeForTelephone}"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedCountryCodeForTelephone" name="countryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.countryCodeForTelephone}"/>--%>
<%--                        <label class="control-label" for="basicInformationExtendedCountryCodeForTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
                <formElement:formInputBoxCustom idKey="basicInformationExtendedTelephone"
                                          labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.telephone"
                                          labelCSS="control-label control-label_mandatory" path="telephone"
                                          inputCSS="text form-control_labeled validate__numbers-only" maxlength="30" mandatory="true"
                                          inputBoxCSS="formInputBox_big"/>
<%--                <div class="formInputBox formInputBox_big">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedTelephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.telephone}"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="basicInformationExtendedTelephone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.telephone"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <formElement:formInputBoxCustom idKey="basicInformationExtendedCountryCodeForMobilePhone" maxlength="5"
                                                labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"
                                                path="countryCodeForMobilePhone" mandatory="true" labelCSS="control-label"
                                                inputCSS="text form-control_preNumber validate__numbers-only"
                                                dataValue="${sagiaApplyEntityInfoForm.countryCodeForMobilePhone}"/>
<%--                <div class="formInputBox">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedCountryCodeForMobilePhone" name="countryCodeForMobilePhone" class="form-control form-control_preNumber" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.countryCodeForMobilePhone}"/>--%>
<%--                        <label class="control-label" for="basicInformationExtendedCountryCodeForMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.countryCode"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
                <formElement:formInputBoxCustom idKey="basicInformationExtendedMobilePhone"
                                          labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone" path="mobilePhone"
                                          labelCSS="control-label control-label_mandatory"
                                          inputCSS="text form-control_labeled validate__numbers-only" maxlength="30" mandatory="true"
                                          inputBoxCSS="formInputBox_big"/>
<%--                <div class="formInputBox formInputBox_big">--%>
<%--                    <div class="form-group">--%>
<%--                        <input id="basicInformationExtendedMobilePhone" name="mobilePhone" class="form-control form-control_labeled" placeholder="." type="text" value="${sagiaApplyEntityInfoForm.mobilePhone}"/>--%>
<%--                        <label class="control-label control-label_mandatory" for="basicInformationExtendedMobilePhone"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.mobilePhone"/></label>--%>
<%--                    </div>--%>
<%--                    <div class="help-block"></div>--%>
<%--                </div>--%>
            </div>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedWebsite"
                                            labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.website" path="website"
                                            labelCSS="control-label control-label_mandatory"
                                            inputCSS="text validate__website" mandatory="true" maxlength="132" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedWebsite" class="form-control" name="website" placeholder="." value="" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedWebsite"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.website"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedCountry"
                                       labelKey="profile.company.country"
                                       path="country" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.country}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedCountry" name="country" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCountry"><spring:theme code="profile.company.country"/></label>--%>
<%--                    <input type="hidden" name="country">--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
            <input type="hidden" name="country">
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedRegion"
                                       labelKey="profile.company.region"
                                       path="region" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.region}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedRegion" name="region" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedRegion"><spring:theme code="profile.company.region"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedCity"
                                       labelKey="profile.company.city"
                                       path="city" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.city}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedCity" name="city" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedCity"><spring:theme code="profile.company.city"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedAddress"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.address"
                                      labelCSS="control-label control-label_mandatory" maxlength="60" path="address"
                                      inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedAddress" name="address" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.address}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedAddress"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.address"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedPOBox"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.poBox" path="poBox"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__numbers-only" maxlength="5" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedPOBox" name="poBox" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.poBox}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPOBox"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.poBox"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
        <div class="col-md-6">
            <formElement:formInputBoxCustom idKey="basicInformationExtendedPostalCode"
                                      labelKey="licenseApplyEntityInformation.basicInformationExtendedSection.postalCode" path="postalCode"
                                      labelCSS="control-label control-label_mandatory"
                                      inputCSS="text validate__numbers-only" maxlength="5" mandatory="true" />
<%--            <div class="formInputBox">--%>
<%--                <div class="form-group">--%>
<%--                    <input id="basicInformationExtendedPostalCode" name="postalCode" class="form-control" placeholder="." value="${sagiaApplyEntityInfoForm.postalCode}" type="text"/>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedPostalCode"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.postalCode"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>

        <div class="col-md-6">
            <formElement:formSelectBoxCustom idKey="basicInformationExtendedInvestment"
                                       labelKey="license.apply.expectedinvestment"
                                       path="investment" labelCSS="control-label control-label_mandatory"
                                       selectCSSClass="js-select2-oneColumn form-control"
                                       selectedDataValue="${sagiaApplyEntityInfoForm.investment}"/>
<%--            <div class="formSelectBox">--%>
<%--                <div class="form-group">--%>
<%--                    <select id="basicInformationExtendedInvestment" name="investment" class="js-select2-oneColumn form-control"></select>--%>
<%--                    <label class="control-label control-label_mandatory" for="basicInformationExtendedInvestment"><spring:theme code="license.apply.expectedinvestment"/></label>--%>
<%--                </div>--%>
<%--                <div class="help-block"></div>--%>
<%--            </div>--%>
        </div>
    </div>
</div>
<style>

.page-new-license-apply.modal-open .select2-container--open.select2-container
{
	z-index:999999;
}

</style>