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

<div class="contentModule-section" id="stockMarketSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.stockMarket"/></div>
    <div class="formRadioBox-wrapper">
        <div class="row">
            <div class="col-md-6">
                <span>
                    <spring:theme code="text.account.profile.license.hasStockListing"/>
                </span>
            </div>
            <div class="col-md-6">
                <div class="formRadioBox">
                    <div class="form-group">
                        <div class="form-item">
                            <input type="radio" name="hasStockListing" id="hasStockListingYES" value="yes" class="form-control"/>
                            <label for="hasStockListingYES" id = "hasStockListingYESLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.yes"/></label>
                        </div>

                        <div class="form-item">
                            <input type="radio" name="hasStockListing" id="hasStockListingNO" value="no" class="form-control"/>
                            <label for="hasStockListingNO" id="hasStockListingNOLabel" class="control-label"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.no"/></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
