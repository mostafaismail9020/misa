<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<c:set var="hasShareholder" value="${fn:length(shareHoldersDataList) > 0}"/>

<!-- QM1 -->
<div class="contentModule" id="shareholdersQM1" data-legalstatus="${entityInformationData.legalStatus}" data-licensetype="${entityInformationData.licenseType}">
    <div class="contentModule-section" id="addShareholderQM1NoShareholderSection" ${hasShareholder ? "style='display: none'" : ""}>
        <span class="iconElement iconElement_shareholderProfile text-center"><icon:shareholderProfile/></span>
        <p class="text-center"><spring:theme code="licence.apply.noshareholder"/></p>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
           <button type="button" class="addExistingButton btn" data-url="<c:url value="/my-sagia/license/existing-shareholder-form"/>">+ <spring:theme code="license.apply.review.existing.shareholder"/></button>
           <button type="button" class="addNewButton btn">+ <spring:theme code="license.apply.review.new.shareholder"/></button>
        </div>
    </div>

    <!--        This section should be shown if you click on Save shareholer                -->
    <div class="contentModule-section" id="shareholderQM1TableSection" ${hasShareholder ? "" : "style='display: none'"}>
        <div class="tableModule">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="license.apply.review.name"/></th>
                        <th><spring:theme code="license.apply.review.type"/></th>
                        <th><spring:theme code="license.apply.percentage"/></th>
                        <th><spring:theme code="license.apply.review.nationality"/></th>
                        <th><spring:theme code="license.apply.review.legalstatus"/></th>
                        <th><spring:theme code="license.apply.review.delegate"/></th>
                        <th><spring:theme code="license.apply.review.delegateId"/></th>
                        <th class="tableModule-headItem tableModule-headItem_actionsCount_2"></th>
                    </tr>
                </thead>
                <tbody class="tableModule-body">
                    <license:newLicenseApplyRegisteredShareholdersTable/>
                </tbody>
            </table>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
           	<button type="button" class="addExistingButton btn">+ <spring:theme code="license.apply.review.existing.shareholder"/></button>
            <button type="button" class="addNewButton btn">+ <spring:theme code="license.apply.review.new.shareholder"/></button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1ExistingSection" style="display: none">
        <div id="shareholderExistingGlobalMessage" class="globalMessage alert alert-warning getAccAlert" style="display: none;">
        <span class="globalMessage-msg">
            <div class="globalMessage-icon globalMessage-icon_warning">
                <icon:warning/>
            </div>
            <span class="text">
                <spring:theme code="license.apply.qeemah.determination.warning"/>
            </span>
        </span>
        </div>

        <div class="contentModule-headline"><spring:theme code="license.apply.review.existing.shareholder"/></div>

        <div class="existingShareholdersForm-wrapper">
<%--            <license:newLicenseApplyExistingShareholderForm-QM1/>--%>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="cancelButton btn btn_outline"><spring:theme code="general.cancel"/></button>
            <button id="validateAddShareholder" type="button" class="addButton btn"><spring:theme code="licence.apply.validate.add"/></button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1NewSection" style="display: none">
        <div class="contentModule-headline"><spring:theme code="license.apply.review.new.shareholder"/></div>

        <div class="row">
            <div class="col-md-6">
                <div class="formRadioBox" id="shareholderType">
                    <div class="form-group">
                        <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licence.apply.shareholder.type"/></div>
                        <div class="form-item">
                            <input id="personType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Person"/>
                            <label for="personType" class="control-label"><spring:theme code="license.apply.shareholder.person"/></label>
                        </div>

                        <div class="form-item">
                            <input id="organizationType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Organization"/>
                            <label for="organizationType" class="control-label"><spring:theme code="general.organization"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button class="cancelButton btn btn_outline"><spring:theme code="general.cancel"/></button>
        </div>


    </div>

     <div id="shareholderNewGlobalMessage" class="globalMessage alert alert-warning getAccAlert" style="display: none;">
                <span class="globalMessage-msg">
                    <div class="globalMessage-icon globalMessage-icon_warning">
                        <icon:warning/>
                    </div>
                    <spring:theme code="validation.review.form"/>
                    <%--<span class="text"></span>--%>
                </span>
     </div>


    <!-- section for type Person -->
    <div id="addShareholderQM1NewPersonSection" style="display: none">

    </div>

    <!-- section for type organization -->
    <div id="addShareholderQM1NewOrganizationSection" style="display: none">

	</div>
</div>
