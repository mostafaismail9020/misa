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

<!--  License Information  -->
<div class="contentModule-section" id="branchInformationSection" style="display: none">
    <div class="contentModule contentModule-wrap">
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
            <span class="contentModule-headline"><spring:theme code="licenseApplyEntityInformation.branchInformationSection.title" /></span>
            <div class="contentModule-headline-border"></div>
        </div>
    </div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.branchInformationSection.more.than.2.branch.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isMoreThan2Branch" id="isMoreThan2BranchYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isMoreThan2Branch == true ? 'checked' : ''}/>
                            <label for="isMoreThan2BranchYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isMoreThan2Branch" id="isMoreThan2BranchNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isMoreThan2Branch == false ? 'checked' : ''}/>
                            <label for="isMoreThan2BranchNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.branchInformationSection.listed.in.stock.market.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isEntityListedInStockMarket" id="isEntityListedInStockMarketYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityListedInStockMarket == true ? 'checked' : ''}/>
                            <label for="isEntityListedInStockMarketYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isEntityListedInStockMarket" id="isEntityListedInStockMarketNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityListedInStockMarket == false ? 'checked' : ''}/>
                            <label for="isEntityListedInStockMarketNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.branchInformationSection.entity.revenue.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isEntityRevenueMoreThanThreshold" id="isEntityRevenueMoreThanThresholdYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityRevenueMoreThanThreshold == true ? 'checked' : ''}/>
                            <label for="isEntityRevenueMoreThanThresholdYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isEntityRevenueMoreThanThreshold" id="isEntityRevenueMoreThanThresholdNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityRevenueMoreThanThreshold == false ? 'checked' : ''}/>
                            <label for="isEntityRevenueMoreThanThresholdNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.branchInformationSection.entity.asset.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isEntityAssetMoreThanThreshold" id="isEntityAssetMoreThanThresholdYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityAssetMoreThanThreshold == true ? 'checked' : ''}/>
                            <label for="isEntityAssetMoreThanThresholdYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isEntityAssetMoreThanThreshold" id="isEntityAssetMoreThanThresholdNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isEntityAssetMoreThanThreshold == false ? 'checked' : ''}/>
                            <label for="isEntityAssetMoreThanThresholdNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6"><span><spring:theme code="licenseApplyEntityInformation.branchInformationSection.entity.6.countries.text"/></span></div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="isMoreThan6Branch" id="isMoreThan6BranchYES" value="yes" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isMoreThan6Branch == true ? 'checked' : ''}/>
                            <label for="isMoreThan6BranchYES" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="isMoreThan6Branch" id="isMoreThan6BranchNO" value="no" class="form-control" ${not empty sagiaApplyEntityInfoForm.licenseType && sagiaApplyEntityInfoForm.isMoreThan6Branch == false ? 'checked' : ''}/>
                            <label for="isMoreThan6BranchNO" class="btn btn-ctrl btn-outline btn_bold control-label"><spring:theme code="licenseApplyEntityInformation.licenseInformationSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 

	<div class="formSelectBox">
		<div class="form-group">
			<select id="branchInformationRhqRegionsSection" name="listOfRhqRegions"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfRhqRegions}">
			</select> <label class="control-label" for="branchInformationRhqRegionsSection"><spring:theme code="profile.rhq.regions" /></label>
		</div>
		<div class="help-block"></div>
	</div>

	<div class="formSelectBox">
		<div class="form-group">
			<select id="branchInformationRhqCountry" name="listOfRhqCountries"
				class="js-select2-multi form-control select2-hidden-accessible"
				multiple="" tabindex="-1" aria-hidden="true" data-value="${sagiaApplyEntityInfoForm.listOfRhqCountries}">
			</select> <label class="control-label" for="branchInformationRhqCountry"><spring:theme code="profile.rhq.countries" /></label>
		</div>
		<div class="help-block"></div>
	</div>
		
</div>
<script>
    var listOfRhqCountriesInJS = '${sagiaApplyEntityInfoForm.listOfRhqCountries}';
</script>