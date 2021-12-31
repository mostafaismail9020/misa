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
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>

<script src = "${MIGS_Session_JS}"></script>

<c:set var="pageIsDashboard" value="${fn:containsIgnoreCase(requestScope['javax.servlet.forward.request_uri'], 'dashboard')}"/>
<%-- <div class="globalMessage-action">
    <a href="#" class="dashboardPrintButton btn btn_outline btn_round btn_slim" style="float: right;"><spring:theme code="payment.pay" /></a>
</div> --%>
<div class="mainSection mainSection_grey">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="payments.page.title"/></h1>

            <div class="col col-12" id="payments">
                <div class="dashboardWidget js-dashboardWidget"
                     data-eServiceTutorial-index="2"
                     data-eServiceTutorial-position='top'
                     data-eServiceTutorial-offset='<spring:theme code="dashboard.tutorial.step1.offset" />'
                     data-eServiceTutorial-borderradius='<spring:theme code="dashboard.tutorial.step1.border.radius" />'
                     data-eServiceTutorial-borderradius-sm='[0,13,0,13]'>
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="payments.page.title"/>
                        <div class="dashboardWidget-headline-icon">
                            <a href="" class="js-page-redirect" data-redirect="payments"><icon:payments/></a>
                        </div>
                        <div class="dashboardWidget-filter">
                            <select id="paymentSort" title="Payments" class="js-select2-oneColumn form-control"
                                    onchange="sortPayments()">
                                <option value="null"><spring:theme code="sagia.sort.sort.by"/></option>
                                <option value="name_asc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme
                                        code="sagia.sort.ascending"/></option>
                                <option value="name_desc"><spring:theme code="sagia.sort.name"/>&nbsp;<spring:theme
                                        code="sagia.sort.descending"/></option>
                                <option value="amount_asc"><spring:theme code="sagia.sort.lowest"/></option>
                                <option value="amount_desc"><spring:theme code="sagia.sort.highest"/></option>
                                <option value="date_asc" data-sort="asc"><spring:theme
                                        code="sagia.sort.oldest"/></option>
                                <option value="date_desc" data-sort="desc"><spring:theme
                                        code="sagia.sort.latest"/></option>
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
                                        <th class="dashboardWidgetPayments-lastCol"><spring:theme
                                                code="dashboard.payments.amount"/></th>
                                        <th><spring:theme code="payment.pay" /></th>
                                    </tr>
                                    </thead>
                                    <tbody class="tableModule-body" id="paymentsTable"></tbody>
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
                                        <icon:arrow_green_right/>
                                    </button>
                                    <div class="paginationModule-items">
                                        <div class="loadingModule">
                                            <div class="loadingModule-icon"><icon:loading-spinner/></div>
                                            <div class="loadingModule-msg">Loading content ...</div>
                                        </div>
                                    </div>
                                    <button class="paginationModule-control paginationModule-control_right">
                                        <icon:arrow_green_right/>
                                    </button>
                                </div>
                                <div class="tableModule-headline">
                                    <c:if test="${pageIsDashboard}">
                                        <a href="/${currentLanguage.isocode}/payments-overview">
                                            <spring:theme code="dashboard.viewall" text="View all"/>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

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
            <div class="dashboardWidgetPayments-eid dashboardWidgetPayments-eid_noMargin"></div>
        </td>
        <td>
            <span class="statusDescription"></span>
            <div class="dashboardWidgetPayments-status-icon"><icon:status-complete/></div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-amount"></div>
            <div class="dashboardWidgetPayments-currency"></div>
        </td>
        <td>
            <div class="dashboardWidgetPayments-pay">
            	<a onclick="" class="" style=""></a>
            </div>
        </td>
    </tr>
</table>

<payment:paymentModal/>