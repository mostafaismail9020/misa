<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ attribute name="editable" required="false" type="java.lang.Boolean"%>

<div class="dashboardWidget js-dashboardWidget no-border">
    <c:if test="${editable}">
        <dashboard:addAndRemoveComponent checkboxIndex="7"/>
    </c:if>
    <div class="dashboardWidget-headline js-dashboardWidget-headline">
        <a href="" data-redirect="service-requests-overview" class="incomplete-request-header js-page-redirect" style="text-decoration: inherit;color: inherit">
            <spring:theme code="dashboard.savedDrafts.title"/>
        </a>
        <div class="dashboardWidget-headline-icon">
            <a href="" data-redirect="service-requests-overview" class="js-page-redirect"><icon:your-services-requests-overview/></a>
        </div>
    </div>
    <div class="dashboardWidget-body">
        <div class="dashboardWidgetDrafts">
            <div class="tableModule tableModule_slim tableModule_striped">
                <table class="tableModule-table">
                    <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="dashboard.savedDrafts.name"/><br></th>
                        <th><spring:theme code="dashboard.savedDrafts.temporaryNumber"/><br></th>
                        <th><spring:theme code="dashboard.savedDrafts.updateDate"/></th>
                        <th style="width: 200px"><spring:theme code="dashboard.savedDrafts.action"/></th>
                    </tr>
                    </thead>
                    <tbody id="draftsTable" class="tableModule-body"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<table class="draftTemplateWrapper" style="display: none">
    <tr>
        <td class="draftName">
            <div class="tableModule-headline js-category-1"></div>
            <div class="tableModule-subHeadline js-category-2"></div>
            <div class="tableModule-description js-category-3"></div>
            <div class="tableModule-description js-category-4"></div>
            <div class="dashboardWidgetRequests-title"></div>
        </td>
        <td class="draftTemporaryNumber"></td>
        <td class="draftDate"></td>
        <td class="action">
            <div>
                <a role="button" class="btn btn_slim draftContinueBtn">
                    <spring:theme code="drafts.continue"/>
                </a>
            </div>
        </td>
    </tr>
</table>
