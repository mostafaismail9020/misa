<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="mainSection mainSection_grey">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="payments.page.title"/></h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_grey mainSection_noPaddingTop js-dashboard">
    <div class="container">
        <div class="row">
            <ul id="draggableComponentsList" class="dashboardWidgetList">
                <li class="dashboardWidgetListComponent js-component">
                    <div class="col col-12" id="payments">
                        <div class="dashboardWidget js-dashboardWidget">
                            <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                <div class="dashboardWidget-filter">
                                    <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control" onchange="sortPayments()">
                                        <option value="null" disabled><spring:theme code="sagia.sort.sort.by"/></option>
                                        <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.ascending"/> </option>
                                        <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme code="sagia.sort.descending"/> </option>
                                        <option value="amount_asc"><spring:theme code="sagia.sort.lowest"/> </option>
                                        <option value="amount_desc"><spring:theme code="sagia.sort.highest"/> </option>
                                        <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.oldest"/></option>
                                        <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.latest"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="dashboardWidget-body">
                                <div class="dashboardWidgetPayments">
                                    <div class="tableModule tableModule_slim tableModule_striped">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                                <th><spring:theme code="dashboard.payments.date"/></th>
                                                <th><spring:theme code="dashboard.payments.name"/></th>
                                                <th><spring:theme code="dashboard.payments.status"/></th>
                                                <th class="dashboardWidgetPayments-lastCol"><spring:theme code="dashboard.payments.amount"/></th>
                                            </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="paymentsTable"></tbody>
                                        </table>
                                    </div>
                                    <div class="paginationModule paginationModule_loading">
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
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<table class="paymentTemplateWrapper" style="display:none;">
    <tr>
        <td>
            <div class="dashboardWidgetPayments-date dashboardWidgetPayments-date_advanced">
                <div class="paymentDate"></div>
            </div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-title dashboardWidgetPayments-title_advanced">
                <a href=""><span class="paymentName"></span></a>
            </div>
            <div class="dashboardWidgetPayments-eid dashboardWidgetPayments-eid_advanced"></div>
        </td>
        <td>
            <span class="statusDescription"></span>
            <div class="dashboardWidgetPayments-status-icon"><icon:status-complete/></div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-amount"></div>
            <div class="dashboardWidgetPayments-currency"></div>
        </td>
    </tr>
</table>