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
<form:form action="" method="post" modelAttribute="sagiaLicenseApplyForm">
<div class="contentModule-section" id="licenseTypeSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.licenseTypeSection.title"/></div>
    <div class="row">
        <div class="col-md-6">
            <formElement:formSelectBox idKey="licenseTypes"
                                       labelKey="license.apply.licenseType"
                                       path="activity"
                                       items="${licenseTypes}"
                                       itemLabel="licenseTypeText"
                                       itemValue="licenseType"
                                       skipBlank="false"
                                       skipBlankMessageKey="form.select.empty"
                                       selectCSSClass="form-control"/>
        </div>
        <div class="col-md-6">
            <a class="btn btn_link btn_formAligned js-tip" data-tip-id="demoTooltipListId" data-tip-width="auto" data-trigger="click"><spring:theme code="licenseApplyEntityInformation.licenseTypeSection.typeOfLicense"/></a>
            <div class="tooltip_content" id="demoTooltipListId">
                <ul class="tooltip-list tooltip-list_collapsible">
                    <spring:message code="license.apply.licenseType.help" var="licenseTypesHelp"/>
                    <c:forEach var="listValue" items="${licenseTypesHelp.split(',,,')}">
                        <li class="tooltip-listItem">
                            <div class="tooltip-listItem-header">
                                ${listValue.split("///")[0]}
                                <button class="btn btn_link tooltip-listItem-trigger"><icon:collapse/></button>
                            </div>
                            <div class="tooltip-listItem-body">${listValue.split("///")[1]}</div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</form:form>
