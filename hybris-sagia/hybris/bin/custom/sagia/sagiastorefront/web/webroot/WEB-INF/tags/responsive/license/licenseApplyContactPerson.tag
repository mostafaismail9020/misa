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

<!--Contact person -->
<div class="panelTabs-head" id="contactPersonTab">
    <icon:contactPerson/>
    <span class="panelTabs-label"><spring:theme code="licenseApply.contactPerson.text"/></span>
    <span class="panelTabs_formFlow-bar"></span>
    <span class="panelTabs_formFlow-mark iconElement"><icon:status-complete/></span>
</div>
<div class="panelTabs-body" id="contactPersonTabData">
    <div class="globalMessage alert alert-warning getAccAlert" style="display: none;">
        <span class="globalMessage-msg">
            <div class="globalMessage-icon globalMessage-icon_warning">
                <icon:warning/>
            </div>
            <spring:theme code="license.apply.qeemah.determination.warning"/>
        </span>
    </div>
    <div class="contentModule">
        <license:licenseApplyContactPerson-QM2></license:licenseApplyContactPerson-QM2>

        <license:licenseApplyContactPerson-QM1></license:licenseApplyContactPerson-QM1>

        <div class="contentModule-actions contentModule-actions_spaceBetween">
            <span>
                <button id="contactPersonBackButton" type="button" class="btn btn-secondary"><spring:theme code="licenseApply.contactPerson.button.back.text"/></button>
                <button id="contactPersonCancelButton" type="button" class="btn btn-secondary btn_link btn_bold"><spring:theme code="licenseApply.contactPerson.button.cancel.text"/></button>
            </span>
            <span>
                <%--<button type="button" class="btn btn-secondary btn_link btn_bold">Skip</button>--%>
                <button id="contactPersonNextButton" type="button" class="btn"><spring:theme code="licenseApply.contactPerson.button.next.text"/></button>
            </span>
        </div>
    </div>
</div>
