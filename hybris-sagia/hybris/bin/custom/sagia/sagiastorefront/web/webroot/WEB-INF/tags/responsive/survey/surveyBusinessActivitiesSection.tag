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

<div class="contentModule-section" id="businessActivitiesSection">
    <div class="contentModule contentModule-wrap">
        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
            <span class="contentModule-headline"><spring:theme code="financial.survey.business.activities"/></span>
            <div class="contentModule-headline-border"></div>
        </div>
    </div>
<%--     <div id="businessTypeSection" class="row">
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="businessType" name="businessType" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label" for="businessType"><spring:theme code="license.apply.business.type"/></label>
                </div>
            </div>
        </div>
    </div> --%>

    <div id="noBusinessActivitiesSelected" style="display: none;">
        <span class="iconElement iconElement_business text-center"><icon:business/></span>
        <p class="text-center"><spring:theme code="survey.activities.question"/></p>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="btn" data-toggle="modal" data-target="#businessActivitiesModal">+ <spring:theme code="financial.survey.business.activities"/></button>
        </div>
        <div class="help-block"></div>
    </div>

    <div id="businessActivitiesTable" style="display: none;">
        <div class="tableModule tableModule_striped">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th class="tableModule-headItem tableModule-headItem_isicCode"><spring:theme code="licence.apply.isicCode"/></th>
                        <th><spring:theme code="licence.apply.crbusinessactivity"/></th>
<%--                         <th id="supportDocumentsThId"><spring:theme code="licence.apply.doc.required"/></th> --%>
                        <th id="actionsThId" class="tableModule-headItem tableModule-headItem_actionsCount_1"></th>
                    </tr>
                </thead>
                <tbody class="tableModule-body"></tbody>
            </table>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="btn" data-toggle="modal" data-target="#businessActivitiesModal"><spring:theme code="licence.apply.addedit.activities"/></button>
        </div>
    </div>

    <!--Modal: Use (data-toggle="modal" data-target="#businessActivities") on link or button to call it-->
    <div class="modal fade" id="businessActivitiesModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="overflow: auto !important;">
        <div class="modal-dialog modal-dialog-centered modal-dialog_businessActivities" role="document">
            <div class="modal-content">
                <form action="" class="">
                    <div class="modal-header">
                        <div class="modal-title"><spring:theme code="financial.survey.business.activities"/></div>
                        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <icon:close/>
                        </button>
                        <ul class="baBreadcrumb">
                            <li id="sectionList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.section"/></a>
                            </li>
                            <li id="divisionList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.division"/></a>
                            </li>
                            <li id="groupList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.group"/></a>
                            </li>
                            <li id="classList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.class"/></a>
                            </li>
                            <li id="branchList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.branch"/></a>
                            </li>
                            <li id="activityList" class="baBreadcrumb-item">
                                <a href="#" class="baBreadcrumb-link active"><spring:theme code="license.apply.activity"/></a>
                            </li>
                        </ul>
                        <div class="baModule-headline"><spring:theme code="license.apply.license.type"/> <span id="licenseTypeSpan"><spring:theme code="license.apply.industrial"/></span></div>
                        <div class="baModule-headline" style="display: none"><spring:theme code="license.apply.business.type"/><span id="businessTypeSpan"></span></div>
                    </div>
                    <div class="modal-body">
                        <!-- markup for section selection. Place content inside baModule-->
                        <div id="sectionItems" class="baModule" style="display: none">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.section"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <ul class="baList baList_columns"></ul>
                        </div>

                        <!-- markup for division selection. Place content inside baModule-->
                        <div id="divisionItems" class="baModule" style="display: none;">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.division"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- markup for group selection. Place content inside baModule-->
                        <div id="groupItems" class="baModule" style="display: none">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.group"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- markup for classes selection. Place content inside baModule-->
                        <div id="classItems" class="baModule" style="display: none">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.class"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- markup for branches selection. Place content inside baModule-->
                        <div id="branchItems" class="baModule" style="display: none">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.branch"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- markup for activities selection. Place content inside baModule-->
                        <div id="activityItems" class="baModule" style="display: none">
                            <div class="baModule-header">
                                <div class="baModule-title"><spring:theme code="license.apply.choose.activities"/></div>
                                <div class="baModule-search">
                                    <div class="searchInputBox searchInputBox_dark">
                                        <input class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                    </div>
                                </div>
                            </div>
                            <div class="contentModule-headline contentModule-headline_small contentModule-headline_bordered"></div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="formCheckBox formCheckBox_block formCheckBox_slim">
                                        <div class="form-group"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer modal-footer_centered">
                        <button type="button" type="submit" class="btn btn_outline btn_slim" data-dismiss="modal"><spring:theme code="general.cancel"/></button>
                        <button id="nextButton" type="button" type="submit" class="btn btn_slim btn-bg"><spring:theme code="general.next"/></button>
                    </div>
                </form>
            </div>
        </div>

    </div>

      <!-- Requirement Modal -->
     <div class="modal fade" id="typeRequirementModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog_businessActivities" role="document">
            <div class="modal-content requirement-modal-content">

                     <div class="modal-header">
                        <div class="modal-title"><spring:theme code="licenseApply.contactPerson.typeRequirement"/></div>
                        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                            <icon:close/>
                        </button>
                           </div>

                <div class="modal-body requirement-body" ></div>

            </div>
        </div>
    </div>


</div>


<div class="contentModule-section" id="temporaryLicenseTextBox" style="display: none;">
<div class="contentModule-headline"><spring:theme code="license.apply.business.activities.temporary.title"/></div>
    <div class="formInputBox" >
      <div class="form-group">
<%--      	<div class="contentModule-headline"><spring:theme code="license.apply.business.activities.temporary"/></div> --%>

 	    <textarea id="temporaryLicenseTextBoxContent" class="form-control js-quote-entry-comments" rows="10" placeholder="." value=""
 		style="width: 100%;font-size: 14px;font-family: MyriadPro; height: 250px !important"></textarea>
		<label class="control-label control-label_mandatory" for="temporaryLicenseTextBoxContent"><spring:theme code="license.apply.business.activities.temporary"/></label>
	 </div>
	 <div class="help-block"></div>
	</div>
</div>

