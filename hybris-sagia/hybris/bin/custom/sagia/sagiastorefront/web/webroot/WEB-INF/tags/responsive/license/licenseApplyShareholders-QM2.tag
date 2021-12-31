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
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<!-- QM2 -->
<div class="contentModule" id="shareholdersQM2" style="display: none">
    <div class="contentModule-section" id="addShareholderQM2NoShareholderSection" style="display: none;">
        <span class="iconElement iconElement_shareholderProfile text-center"><icon:shareholderProfile/></span>
        <p class="text-center"><spring:theme code="licence.apply.noshareholder"/></p>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="addNewButton btn">
              <spring:theme code="text.account.profile.license.shareholders.newShareholder"/>
            </button>
        </div>
    </div>

    <!--        This section should be shown if you click on Save shareholer                -->
    <div class="contentModule-section" id="shareholderQM2TableSection" style="display: none;">
        <div class="tableModule">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="license.apply.review.name"/></th>
                        <th><spring:theme code="license.apply.review.type"/></th>
                        <th><spring:theme code="license.apply.review.shares.percentage"/></th>
                        <th><spring:theme code="license.apply.review.nationality"/></th>
                        <th><spring:theme code="license.apply.review.legalstatus"/></th>
                        <th class="tableModule-headItem tableModule-headItem_actionsCount_2"></th>
                    </tr>
                </thead>
                <tbody class="tableModule-body"></tbody>
            </table>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="addNewButton btn">+ <spring:theme code="license.apply.newshareholder"/></button>
        </div>
    </div>

    <!--        This section should be shown if you click on "+ New shareholder"-->
    <div class="contentModule-section" id="addShareholderQM2NewSection" style="display: none">
        <div class="row">
            <div class="col-md-6">
                <div class="formRadioBox" id="shareholderType">
                    <div class="form-group">
                        <div class="formRadioBox-label">Shareholder type</div>
                        <div class="form-item">
                            <input id="qm2PersonType" name="type" class="form-control" type="radio" value="Person"/>
                            <label for="qm2PersonType" class="control-label"><spring:theme code="license.apply.review.person"/></label>
                            <div class="help-block"></div>
                        </div>

                        <div class="form-item">
                            <input id="qm2EntityType" name="type" class="form-control" type="radio" value="Entity"/>
                            <label for="qm2EntityType" class="control-label"><spring:theme code="license.apply.review.entity"/></label>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="name" name="name" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="name"><spring:theme code="license.apply.review.name"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="nationality" name="nationality" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="nationality"><spring:theme code="license.apply.review.nationality"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formInputBox formInputBox_group">
                    <div class="form-group">
                        <input id="sharesPercentage" name="sharesPercentage" class="form-control" placeholder="." value="" type="text"/>
                        <label class="control-label control-label_mandatory" for="sharesPercentage"><spring:theme code="license.apply.review.shares.percentage"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text">%</span>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group" style="display: none;">
                        <select id="legalStatus" name="legalStatus" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="legalStatus"><spring:theme code="license.apply.review.legalstatus"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="cancelButton btn btn_outline"><spring:theme code="general.cancel"/></button>
            <button type="button" class="addButton btn"><spring:theme code="license.apply.review.save.shareholder"/></button>
        </div>
    </div>
</div>
