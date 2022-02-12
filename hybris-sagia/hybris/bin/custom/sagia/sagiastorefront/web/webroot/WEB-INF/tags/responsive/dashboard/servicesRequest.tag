<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="editable" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageIsDashboard" value="${fn:containsIgnoreCase(requestScope['javax.servlet.forward.request_uri'], 'dashboard')}"/>

<div class="dashboardWidget js-dashboardWidget no-border">
    <c:if test="${editable}">
        <dashboard:addAndRemoveComponent checkboxIndex="3"/>
    </c:if>
    <div class="dashboardWidget-headline js-dashboardWidget-headline  d-none d-sm-block">
        <a href="" data-redirect="service-requests-overview" class="service-request-header js-page-redirect" style="text-decoration: inherit;color: inherit">
            <spring:theme code="dashboard.servicesRequest.title"/>
        </a>
        <div class="dashboardWidget-headline-icon">
            <a href="" data-redirect="service-requests-overview" class="js-page-redirect">
                <div class="dashboardWidget-headline-icon">
                    <icon:your-services-requests-overview/>
                </div>
            </a>
        </div>

        <div class="dashboardWidget-filter">
        <c:if test="${!editable}">
            <select id="serviceSort" title="Services" class="js-select2-oneColumn form-control" onchange="sortServices()">
                <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.asc"/> </option>
                <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.desc"/> </option>
                <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
            </select>
        </c:if>
        </div>
    </div>
    <div class="dashboardWidget-body">     
        <div class="dashboardWidgetRequests">
            <div class="tableModule tableModule_slim tableModule_striped">
                <table class="tableModule-table">
                    <thead class="tableModule-head">
                        <tr>
                            <th><spring:theme code="dashboard.servicesRequest.name"/></th>
                            <th><spring:theme code="dashboard.servicesRequest.number"/></th>
                            <th><spring:theme code="dashboard.servicesRequest.date"/></th>
                            <th><spring:theme code="dashboard.servicesRequest.status"/></th>
                            <th><spring:theme code="general.print"/></th>
                        </tr>
                    </thead>
                    <tbody class="tableModule-body" id="serviceRequestsTable"></tbody>
                </table>
            </div>
            <div class="paginationModule paginationModule_loading">
                <c:if test="${!pageIsDashboard}">
                    <div class="dashboardWidget-filter">
                        <div style="width: 150px; position: absolute">
                            <select class="paginationPicker js-select2-oneColumn form-control"></select>
                        </div>
                    </div>
                </c:if>
                <div class="paginationModule-wrapper">
                    <button class="paginationModule-control paginationModule-control_left" disabled>
                        <img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive">
                    </button>
                    <div class="paginationModule-items">
                        <div class="loadingModule">
                            <div class="loadingModule-icon"><icon:loading-spinner /></div>
                            <div class="loadingModule-msg">Loading content ...</div>
                        </div>
                    </div>
                    <button class="paginationModule-control paginationModule-control_right">
                        <img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive" >
                    </button>
                </div>
                <c:if test="${pageIsDashboard}">
                    <div class="tableModule-headline">
                        <a href="" data-redirect="service-requests-overview" class=" btn-dashboard btn-view-all js-page-redirect ">
                            <spring:theme code="dashboard.viewall" text="View all"/>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<table class="serviceTemplateWrapper" style="display: none">
    <tr>
        <td>
            <a id="serviceRequestLink" href=""><div class="tableModule-headline"></div></a>
            <div class="tableModule-subHeadline"></div>
            <div class="tableModule-description" id="description1"></div>
            <div class="tableModule-description" id="description2"></div>
            <div class="tableModule-description" id="description3"></div>

            <div class="dashboardWidgetRequests-title"></div>
        </td>
        <td class="requestNumber"></td>
        <td class="requestDate"></td>
        <td>
            <span class="serviceStatus"></span>
            <div class="dashboardWidgetRequests-status-icon">
                <icon:in-process/>
            </div>
        </td>
        <td>
            <a id="printLink" href="" taget="_blank" download><icon:print/></a>
        </td>
    </tr>
</table>
