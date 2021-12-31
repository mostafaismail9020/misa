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

<div class="contentModule-section" id="technicalAndFinancialCriteriaSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.technicalAndFInancialCriteriaSection.title"/></div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <!-- the link inside the spring needs a tooltip-->
                    <spring:theme code="text.account.profile.license.hasRevenueOver70MilSR"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="revenueOver70" id="revenueOver70Yes" value="yes" class="form-control"/>
                            <label for="revenueOver70Yes" class="control-label">Yes</label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="revenueOver70" id="revenueOver70No" value="no" class="form-control"/>
                            <label for="revenueOver70No" class="control-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <!-- the link inside the spring needs a tooltip-->
                    <spring:theme code="text.account.profile.license.hasAssetsOver100MilSR"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="assetsOver100" id="assetsOver100Yes" value="yes" class="form-control"/>
                            <label for="assetsOver100Yes" class="control-label">Yes</label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="assetsOver100" id="assetsOver100No" value="no" class="form-control"/>
                            <label for="assetsOver100No" class="control-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <!-- the link inside the spring needs a tooltip-->
                    <spring:theme code="text.account.profile.license.setHasNetProfitOver50MilSR"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="profitOver50" id="profitOver50Yes" value="yes" class="form-control"/>
                            <label for="profitOver50Yes" class="control-label">Yes</label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="profitOver50" id="profitOver50No" value="no" class="form-control"/>
                            <label for="profitOver50No" class="control-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <spring:theme code="text.account.profile.license.setHas3RegionalBranches"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="regionalBranches" id="regionalBranchesYes" value="yes" class="form-control"/>
                            <label for="regionalBranchesYes" class="control-label">Yes</label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="regionalBranches" id="regionalBranchesNo" value="no" class="form-control"/>
                            <label for="regionalBranchesNo" class="control-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <spring:theme code="text.account.profile.license.has50PercentExport"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="exportOver50" id="exportOver50Yes" value="yes" class="form-control"/>
                            <label for="exportOver50Yes" class="control-label">Yes</label>
                        </div>
                        <div class="form-item">
                            <input type="radio" name="exportOver50" id="exportOver50No" value="no" class="form-control"/>
                            <label for="exportOver50No" class="control-label">No</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="formRadioBox-wrapper" id="technicalAndFinancialCriteriaQuestionTemplate" style="display: none;">
    <div class="row">
        <div class="col-md-6">
            <span class="questionDescription"></span>
        </div>
        <div class="col-md-6">
            <div class="formRadioBox">
                <div class="form-group">
                    <div class="form-item">
                        <input type="radio" name="radioYes" value="yes" class="form-control questionDescriptionRadioYes"/>
                        <label class="control-label">Yes</label>
                    </div>
                    <div class="form-item">
                        <input type="radio" name="radioNo" value="no" class="form-control questionDescriptionRadioNo"/>
                        <label class="control-label">No</label>
                    </div>
                </div>
                <div class="help-block" style="text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
