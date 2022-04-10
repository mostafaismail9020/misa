
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
 <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.title"/></div>
<hr class="hr">
<div class="mncBranchTableJsonInputs"></div>
<div class="mncBrandTableJsonInputs"></div>
<div class="mncCostTableJsonInputs"></div>

<!--Optional/Functions/Corporate  Activities start-->
<div class="formSelectBox" style="margin-top:40px">
		<div class="form-group optionalActivity">
			<select id="rhqCheckbox" name="listOfCorporateActivities"
				class="form-control"
				multiple  data-value="${sagiaApplyEntityInfoForm.listOfCorporateActivities}">
			</select>
			<label class="control-label control-label_mandatory" for="rhqCheckbox"><spring:theme code="rhq.investor.activities.corporate.label"/></label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Optional/Functions/Corporate  Activities End-->

<!--Strategic direction includes start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqStrategicCheckbox" name="listOfStrategicActivities"
				class="form-control"
				multiple tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfStrategicActivities}">
			</select>
			<label class="control-label control-label_mandatory" for="rhqStrategicCheckbox"><spring:theme code="rhq.investor.activities.strategic.label"/></label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Strategic direction includes End-->

<!--Management functions include start-->
<div class="formSelectBox">
		<div class="form-group optionalActivity">
			<select id="rhqManagementFunCheckbox" name="listOfManagementActivities"
				class="form-control"
				multiple data-value="${sagiaApplyEntityInfoForm.listOfManagementActivities}">
			</select>
			<label class="control-label control-label_mandatory" for="rhqManagementFunCheckbox"><spring:theme code="rhq.investor.activities.management.label"/></label>
		</div>
		<div class="help-block"></div>
	</div>
<!--Management functions include End-->

<!--Center of Administrative start-->


	<div class="formSelectBox">
			<div class="form-group">
				<select id="rhqCenterAdmin" name="rhqCenterAdmin" class="form-control" multiple data-value="">
					 <option value="GCC">GCC</option>
                     <option value="MENA">MENA</option>
                     <option value="Middle_East_ME">Middle East (ME)</option>
				</select>
				<label class="control-label control-label_mandatory" for="rhqCenterAdmin"><spring:theme code="rhq.investor.activities.center.of.administrative.label"/></label>
				<div id="rhqCenterAdmin-error" class="help-block"></div>
			</div>
		</div>

<%--         <div class="formSelectBox">
                <div class="form-group">
                    <select id="branchInformationRhqCountry" name="listOfRhqCountries"
                        class="form-control"
                        multiple data-value="${sagiaApplyEntityInfoForm.listOfRhqCountries}">
                    </select> <label class="control-label" for="branchInformationRhqCountry"><spring:theme code="profile.rhq.countries" /></label>
                </div>
                <div class="help-block"></div>
            </div>

		<div class="formSelectBox" id="rhqCountryRegion" >
		<div class="form-group">
			<select id="branchInformationRhqRegionsSection" name="listOfRhqRegions"
				class="form-control"
				multiple data-value="${sagiaApplyEntityInfoForm.listOfRhqRegions}">
			</select> <label class="control-label control-label_mandatory" for="branchInformationRhqRegionsSection"><spring:theme code="profile.rhq.regions" /></label>
		</div>
		<div class="help-block"></div>
	</div> --%>

     <div class="formRadioButton rhqSubsidiaryPresence-formRadioButtonDiv">
         <div class="form-group optionalActivity ">
		 <label class="control-label rhqSubsidiaryPresence-label control-label_mandatory" for="rhqSubsidiaryPresence"><spring:theme code="rhq.mnc.subsidiaries.presence" /></label>
		 <div id="rhqSubsidiaryPresence">

		  </div>
        </div>
		<div class="help-block"></div>
     </div>


<!--Center of Administrative End-->
</div>

<!--Edit Branch Table Model-->

<div class="modal fade" id="EditBranchTable"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"><spring:theme code="rhq.edit.branch.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
				                <spring:theme code="rhq.company.label" />
			                    </label>
                                <div class="help-block"></div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBranchCountry" name="editBranchCountry" class="js-select2-search form-control" data-search-placeholder="Select a country">

				</select>
				<label class="control-label" for=""><spring:theme code="rhq.country.label" /></label>
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
				<label class="control-label" for=""><spring:theme code="rhq.business.relationship.type.label" /></label>
				<div id="editBranchBuz-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBranchIndustry" name="editBranchIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				                <spring:theme code="rhq.industry.label" />
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
				<select id="editBranchOperation" name="editBranchOperation" class="js-select2-search form-control" data-search-placeholder="">
					<option></option>
					 <option value="Manufacturing">Manufacturing</option>
                    <option value="Assembly">Assembly</option>
                    <option value="Others">Others</option>
				</select>
				<label class="control-label" for=""><spring:theme code="rhq.operation.label" /></label>
				<div id="editBranchOperation-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="editBranchRhqActivity" name="editBranchRhqActivity" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
				               <spring:theme code="rhq.rhq.activity.label" />
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
				<button type="button" class="btn btn_slim" data-dismiss="modal" id="entityEditBranch"><spring:theme code="rhq.update.label" /></button>
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
				<div class="modal-title"><spring:theme code="rhq.add.new.branch.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
			                    <spring:theme code="rhq.company.label" />
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBranchCountry" name="addBranchCountry" class="js-select2-search form-control" data-search-placeholder="">

				</select>
				<label class="control-label" for=""><spring:theme code="rhq.country.label" /></label>
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
				<label class="control-label" for=""><spring:theme code="rhq.business.relationship.type.label" /></label>
				<div id="addBranchBuz-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBranchIndustry" name="addBranchIndustry"  class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
			                    <spring:theme code="rhq.industry.label" />
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
				<select id="addBranchOperation" name="addBranchOperation" class="js-select2-search form-control" data-search-placeholder="">
					<option></option>
					 <option value="Manufacturing">Manufacturing</option>
                    <option value="Assembly">Assembly</option>
                    <option value="Others">Others</option>
				</select>
				<label class="control-label" for=""><spring:theme code="rhq.operation.label" /></label>
				<div id="addBranchOperation-error" class="help-block"></div>
			</div>
		</div>
							</div>
                            <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group">
			                    <input id="addBranchRhqActivity" name="addBranchRhqActivity" class="form-control" placeholder="." value="" type="text" autocomplete="off">
			                    <label class="control-label" for="">
			                    <spring:theme code="rhq.rhq.activity.label" />
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
				<div class="modal-title"><spring:theme code="rhq.add.new.brand.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
				                <spring:theme code="rhq.brand.name.label" />
			                    </label>
                                <div class="help-block">
					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="addBrandCountry" name="addBrandCountry" class="js-select2-search form-control" data-search-placeholder="">
				</select>
				<label class="control-label" for=""><spring:theme code="rhq.country.label" /></label>
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
			                    <spring:theme code="rhq.industry.label" />
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
				                 <spring:theme code="rhq.company.brand.in.mena.region.label" />
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
				                 <spring:theme code="rhq.rhq.activity.label" />
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
				<div class="modal-title"><spring:theme code="rhq.edit.brand.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
				                <spring:theme code="rhq.brand.name.label" />
			                    </label>
                                <div class="help-block">

					</div>
		                        </div>
	                    </div>
							</div>
							<div class="col-md-6">
							<div class="formSelectBox">
			<div class="form-group">
				<select id="editBrandCountry" name="editBrandCountry" class="js-select2-search form-control" data-search-placeholder="">
				</select>
				<label class="control-label" for=""><spring:theme code="rhq.country.label" /></label>
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
				                <spring:theme code="rhq.industry.label" />
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
				                 <spring:theme code="rhq.company.brand.in.mena.region.label" />
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
				                 <spring:theme code="rhq.rhq.activity.label" />
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
				<div class="modal-title"><spring:theme code="rhq.add.estimated.cost.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
				                <spring:theme code="rhq.item.name.label" />
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
				                <spring:theme code="rhq.unit.cost.label" />
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
				                <spring:theme code="rhq.no.of.units.label" />
			                    </label>

		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addCostFreq" name="addCostFreq" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                <spring:theme code="rhq.cost.frequency.label" />
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
				                <spring:theme code="rhq.year.2022.label" />
			                    </label>

		                        </div>
	                    </div>
							</div>
                              <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="addYear2" name="addYear2" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                <spring:theme code="rhq.year.2023.label" />
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
				                <spring:theme code="rhq.year.2024.label" />
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
				<div class="modal-title"><spring:theme code="rhq.edit.estimated.cost.label" /></div>
				  <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <svg version="1" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path stroke="#000" stroke-width="2" stroke-miterlimit="10" fill="none" d="M1 .922l14 14M1 14.922l14-14"/></svg>
                        </button>
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
				                <spring:theme code="rhq.item.name.label" />
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
				                <spring:theme code="rhq.unit.cost.label" />
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
				               <spring:theme code="rhq.no.of.units.label" />
			                    </label>

		                        </div>
	                    </div>
							</div>
                             <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editCostFreq" name="editCostFreq" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                <spring:theme code="rhq.cost.frequency.label" />
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
				                <spring:theme code="rhq.year.2022.label" />
			                    </label>

		                        </div>
	                    </div>
							</div>
                              <div class="col-md-6">
								<div class="formInputBox">
		                        <div class="form-group ">
			                    <input id="editYear2" name="editYear2" class="form-control" placeholder="." value="" type="number" autocomplete="off">
			                    <label class="control-label" for="">
				                <spring:theme code="rhq.year.2023.label" />
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
				                <spring:theme code="rhq.year.2024.label" />
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

<div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="rhq.table.header.label" /></div>
 <hr class="hr">
<div class="tableModule" >
<div class="control-label rhqSubsidiaryPresence-label control-label_mandatory"><spring:theme code="rhq.mnc.branches.label" /></div>

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
	</tbody>
  </table>
  <input type="hidden" id="rowToDelete" name="rowToDelete" value="0">
  	<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin w-100">

					<button type="button" class="btn w-25" data-toggle="modal" data-target="#addBranchTable" style="margin-top: 23px;"><spring:theme code="rhq.add.new.label" /></button>
				</div>


</div>

</div>

<!--MNC Brand Start-->
<div class="rqh-tables" style="display:none">
 <hr class="hr">
<div class="tableModule" >
<div class="control-label rhqSubsidiaryPresence-label control-label_mandatory"><spring:theme code="rhq.mnc.brand.label" /></div>
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
				<button type="button" class="btn w-25" data-toggle="modal" data-target="#addBrandTable" style="margin-top: 23px;"><spring:theme code="rhq.add.new.label" /></button>
				</div>
</div>
</div>
<!--MNC Brand End-->


<!--rhqCostTable-->
<div class="rqh-tables" style="display:none;border-bottom: 1px solid #ccd0d4;margin-bottom: 48px;padding-bottom: 24px;">
<hr class="hr">
<div class="tableModule" >
<div class="control-label rhqSubsidiaryPresence-label control-label_mandatory"><spring:theme code="rhq.estimated.cost.label" /></div>

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
    <th id="rhqCostTable-totalText">Total</th>
    <th id="rhqCostTable-sum1"></th>
	<th id="rhqCostTable-sum2"></th>
	<th id="rhqCostTable-sum3"></th>
     <th></th>
    </tr>
    </tfoot>
  </table>
  <input type="hidden" id="rowToDelete" name="rowToDelete" value="0">
  	<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin w-100">
			<button type="button" class="btn w-25" data-toggle="modal" data-target="#addrhqCostTable" style="margin-top: 23px;"><spring:theme code="rhq.add.new.label" /></button>
				</div>
</div>
</div>
<!--rhqCostTable-->


<div>
<div class="contentModule-section" id="basicInformationExtendedSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.basicInformationExtendedSection.basicInformation"/></div>
   <hr class="hr">
    <div class="row mt-5 pt-3">
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

#rhqCostTable-sum1,#rhqCostTable-sum2,#rhqCostTable-sum3,#rhqCostTable-totalText{
	font-weight: 600;
    font-size: 14px;
    color: #1c242c;
	padding: 15px 10px 15px;
}

.page-new-license-apply .select.modal.in.fade.show{z-index:9999999999 !important;}
.page-new-license-apply .fade.show{}
.page-new-license-apply .modal-dialog.modal-sm{min-width:500px;}
.page-new-license-apply label[for=rhqCheckbox],.page-new-license-apply label[for=rhqStrategicCheckbox],.page-new-license-apply label[for=rhqManagementFunCheckbox],
.page-new-license-apply label[for=rhqCenterAdmin],.page-new-license-apply label[for=branchInformationRhqCountry],.page-new-license-apply label[for=branchInformationRhqRegionsSection]{
	top: -17px;
}

.page-new-license-apply .selectWrap{
	padding-top: 5px;
    border-radius: 113px!important;
    background: #fff;
    -webkit-box-shadow: 0 19px 47px #00000029;
    box-shadow: 0 19px 47px #00000029;
}
.page-new-license-apply .selectWrap > .select-content > .addedOption{color: white;
    background-color: #ffffff;
    border:1px solid #00a6be4f !important;
    padding: 3px 5px;
    border-radius: 5px;
    margin: 2px;
    float: left;
    cursor: pointer;}
.page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption,
.page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption >span,
.page-new-license-apply .select.modal .modal-body > .option.selected{    
	color: #00a6be !important;
    font-size: 15px !important;}

.page-new-license-apply .select .modal-dialog .modal-body{padding: 0 22px;}
.page-new-license-apply .tooltip-listItem_expanded .tooltip-listItem-body {color:#000;}
.page-new-license-apply  .tooltip.show {
       z-index: 999999999;
}



.page-new-license-apply .selectWrap > .open-options > span.icon{
	color:#00a6be !important;
}

.page-new-license-apply .selectWrap > .select-content > .addedOption > .text {
    color: #707070 !important;
    font-size: 15px !important;
}

.page-new-license-apply #rhqSubsidiaryPresence .form-item .control-label span {
	top:0 !important;

}

.page-new-license-apply .rhqSubsidiaryPresence-label{
	font-size: 14px !important;
    text-transform: uppercase;
    margin-bottom: 0.5rem;
    line-height: 17px;
    left: 20px;
}
.page-new-license-apply .formRadioButton .form-group.has-error .control-label.rhqSubsidiaryPresence-label {color: #707070;}
.page-new-license-apply #rhqSubsidiaryPresence .control-label {
	color: #707070 !important;
    font-size: 15px !important;
}

[dir=ltr] .page-new-license-apply  .rhqSubsidiaryPresence-formRadioButtonDiv .help-block {
        text-align: left;
	    margin-bottom: 19px !important;
		margin-top: 0p !important;
	   font-size: var(--label-font);
}

[dir=rtl] .page-new-license-apply  .rhqSubsidiaryPresence-formRadioButtonDiv .help-block {
        text-align: right !important;
	    margin-bottom: 19px !important;
		margin-top: 0px !important;
	   font-size: var(--label-font);
}

.page-new-license-apply .rhqSelectBoxes .formSelectBox .form-group {
	    padding-bottom: 8px !important;
}

.page-new-license-apply .rhqSelectBoxes .formRadioButton .form-group {
	    padding-top: 0px;
}



#addBranchTable .modal-dialog .modal-body,
#EditBranchTable .modal-dialog .modal-body,
#addBrandTable .modal-dialog .modal-body,
#EditBrandTable .modal-dialog .modal-body,
#addrhqCostTable .modal-dialog .modal-body,
#EditrhqCostTable .modal-dialog .modal-body{
padding-top:15px !important;
max-height:unset !important;
}

#addBranchTable .modal-dialog .modal-body .formInputBox,
#EditBranchTable .modal-dialog .modal-body .formInputBox,
#addBrandTable .modal-dialog .modal-body .formInputBox,
#EditBrandTable .modal-dialog .modal-body .formInputBox,
#addrhqCostTable .modal-dialog .modal-body .formInputBox,
#EditrhqCostTable .modal-dialog .modal-body .formInputBox{
margin-bottom:20px !important
}


.page-new-license-apply .select.modal .modal-body > .option.selected > .option-tick .icon-ok,
.page-new-license-apply .select.modal .modal-body > .option.selected >.option-text{
	color: #00a6be !important;
}

.page-new-license-apply .rhqSelectBoxes .formSelectBox .form-group label {
    top: -20px !important;
}

    
      @media (min-width: 1200px) and (max-width:1500px) {
	  
	  .page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption, .page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption >span, .page-new-license-apply .select.modal .modal-body > .option.selected {
    color: #00a6be !important;
    font-size: 12px !important;
}
.page-new-license-apply .selectWrap > .select-content > .addedOption > .text {
    color: #707070 !important;
    font-size: 12px !important;
}
}

@media (min-width: 320px) and (max-width:991.98px)  { 
	  .page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption, .page-new-license-apply .selectWrap > .select-content > .addedOption > .removeOption >span, .page-new-license-apply .select.modal .modal-body > .option.selected {
    color: #00a6be !important;
    font-size: 12px !important;
}
.page-new-license-apply .selectWrap > .select-content > .addedOption > .text {
    color: #707070 !important;
    font-size: 12px !important;
}
.page-new-license-apply .selectWrap{
border-radius: 0px!important;
}
 }

 .page-new-license-apply .rqh-tables .help-block,
.page-new-license-apply .rhqSelectBoxes .help-block	 {
	margin-top: 6px;
}

.page-new-license-apply  .select.modal .modal-header > .close{
color: #00a6be !important;
    opacity: 1;
    width: 35px;
    height: 35px;
    line-height: 35px;
    border: 2px solid #00a6be !important;
    border-radius: 35px;
    display: inline-block;
    padding: 0 !important;
    text-align: center;
    font-weight: bold;
}

.page-new-license-apply  .select.modal .modal-header{    
	display: flex;
    justify-content: center;
    align-items: center}

html[dir="rtl"] .page-new-license-apply .select.modal .modal-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

html[dir="rtl"] .page-new-license-apply .select.modal .float-right.clickable.close{
margin-left: unset;
}

html[dir="rtl"]  .page-new-license-apply .select.modal .modal-body > .option.selected {
    color: #5cc83b;
    display: flex;
    justify-content: space-between;
}


</style>

<script>
	  objectBranchesString =  ('${entitiesManagedByRhq}');
      objectBranchesString1 = objectBranchesString.replace(/("{)/g, '{');
	  objectBranchesString2 = objectBranchesString1.replace(/(}")/g, '}');

  	  console.log(objectBranchesString2);
	  objectBranches = [];
      objectBranches =  JSON.parse(objectBranchesString2);

	  objectBrandString =  ('${brandPresenceInMENARegion}');
      objectBrandString1 = objectBrandString.replace(/("{)/g, '{');
	  objectBrandString2 = objectBrandString1.replace(/(}")/g, '}');

	  console.log(objectBrandString2);
	  objectBrands = [];
      objectBrands =  JSON.parse(objectBrandString2);

	  objectCostsString =  ('${estimatedOperatingCostForRhq}');
      objectCostsString1 = objectCostsString.replace(/("{)/g, '{');
	  objectCostsString2 = objectCostsString1.replace(/(}")/g, '}');

	  console.log(objectCostsString2);
	  objectCost = [];
	  if(objectCostsString2!= "" &&  objectCostsString2 != undefined){
 	 objectCost =  JSON.parse(objectCostsString2);
	  }


	var listOfRhqCountriesInJS =  ('${sagiaApplyEntityInfoForm.listOfRhqCountries}');
	var rhqCenterAdminInJS =  ('${sagiaApplyEntityInfoForm.rhqCenterAdmin}');
	var rhqSubsidiaryPresenceInJS =  ('${sagiaApplyEntityInfoForm.rhqSubsidiaryPresence}');
	var listOfManagementActivitiesInJS = ('${sagiaApplyEntityInfoForm.listOfManagementActivities}');
	console.log('rhqSubsidiaryPresenceInJS'+('${sagiaApplyEntityInfoForm.rhqSubsidiaryPresence}'));



</script>