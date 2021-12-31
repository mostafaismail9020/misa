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

<!-- section if on stockmarket -->
<div class="contentModule-section" id="onStockMarketSection" style="display: none">
    <div class="row">
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="countries" name="countries" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label" for="countries"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.country"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
<%--            <formElement:formInputBox idKey="isinCode" labelKey="profile.company.ISINCode" path="iSINCode" inputCSS="text" mandatory="true"/>--%>
        </div>
        <%--<div class="col-md-6">--%>
            <%--<div class="formSelectBox">--%>
                <%--<div class="form-group">--%>
                    <%--<select id="isicSection" name="isicSection" class="js-select2-wide_left form-control"></select>--%>
                    <%--<label class="control-label" for="isicSection"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.section"/></label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="col-md-6">--%>
            <%--<div class="formSelectBox">--%>
                <%--<div class="form-group">--%>
                    <%--<select id="isicDivision" name="isicDivision" class="js-select2-wide_right form-control"></select>--%>
                    <%--<label class="control-label" for="isicDivision"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.division"/></label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="col-12">--%>
            <%--<div class="formTextArea">--%>
                <%--<div class="form-group">--%>
                    <%--<textarea id="licenseCondition" class="form-control form-control_slim" placeholder="."></textarea>--%>
                    <%--<label class="control-label" for="licenseCondition"><spring:theme code="licenseApplyEntityInformation.stockMarketSection.condition"/></label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
