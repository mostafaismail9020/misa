<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ taglib prefix="payment" tagdir="/WEB-INF/tags/responsive/payment" %>

<%-- TODO: SAH-890: Payments Detail - Creditcard --%>
<script src = "${MIGS_Session_JS}"></script>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="text.account.paymentDetails"/></h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <c:url value="/dashboard" var="dashboardUrl"></c:url>
            <a href="${dashboardUrl}" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="payments.details.back"/></a>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-headline contentModule-headline_big"><spring:theme code="payments.details.subscriptionFee"/>&nbsp;<c:out value="${paymentData.serviceDescription}"/></div>
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:questionaires/>
                       </span>
                        <spring:theme code="payments.details.generalData"/>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="payments.details.serviceId"/></dt>
                                <dd><c:out value="${paymentData.serviceId}"/></dd>
                            </dl>
                        </div>
                        <c:if test="${paymentData.status == 'E0003' && paymentData.hybrisStatusDescription != 'Paid'}">
	                        <div class="col-md-6">
	                            <div style="text-align: right;">
			                        <a onclick="SAGIA.payment.requestCreditBillPayment(${paymentData.serviceId},'${paymentData.serviceDescription}',${paymentData.amount},'${paymentData.currency}')" target="_blank" class="btn btn_round btn_outline">
			                            <spring:theme code="license.apply.paywithcc" />
			                        </a>
	                			</div>
	                        </div>
                        </c:if>
                    </div>
                </div>
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:documents/>
                       </span>
                        <spring:theme code="payments.details.processingData"/>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="payments.details.serviceStatus"/></dt>
                                <dd>
                                	<c:choose>
	                                	<c:when test="${paymentData.status == 'E0003' && paymentData.hybrisStatusDescription == 'Paid'}">
	                                		<c:out value="${paymentData.hybrisStatusDescription}"/>
	                                	</c:when>
	                                	<c:otherwise>
	                                		<c:out value="${paymentData.statusDescription}"/>
	                                	</c:otherwise>
                                	</c:choose>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="payments.details.dateOfCreation"/></dt>
                                <dd><c:out value="${paymentData.sagiaPaymentDate.dateFormatted}"/></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:payment03/>
                       </span>
                        <spring:theme code="payments.details.value"/>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <fmt:formatNumber var="ammount" value="${paymentData.amount}" maxFractionDigits="2" />


                                <dt><spring:theme code="payments.details.netValue"/></dt>
                                <dd><c:out value="${ammount}"/>&nbsp;<c:out value="${paymentData.currency}"/></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:info/>
                       </span>
                        SADAD Payment Information
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="contentModule-headline contentModule-headline_small">
                                SADAD Bill Payment Information
                            </div>
                            <dl class="dlList dlList_separated">
                                <dt>SADAD Bill Status</dt>
                                <dd>${paymentData.sadadBillStatus}</dd>
                                <dt>SADAD Bill Number</dt>
                                <dd>${paymentData.sadadBillNum}</dd>
                                <dt>SADAD Bill Account</dt>
                                <dd>${paymentData.sadadBillAccount}</dd>
                                <dt>SADAD Payment Number</dt>
                                <dd>${paymentData.sadadPaymentNum}</dd>
                                <dt>SADAD Investor ID</dt>
                                <dd>${paymentData.sadadInvestorId}</dd>
                                <dt>SADAD Amount Paid</dt>
                                <dd>${paymentData.sadadAmountPaid}</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <div class="contentModule-headline contentModule-headline_small">
                                SADAD Bill Bank Information
                            </div>
                            <dl class="dlList dlList_separated">
                                <dt>SADAD Transaction Date</dt>
                                <dd>${paymentData.sadadTransDate}</dd>
                                <dt>SADAD Transaction Number</dt>
                                <dd>${paymentData.sadadTransNum}</dd>
                                <dt>SADAD Bank ID</dt>
                                <dd>${paymentData.sadadBankId}</dd>
                                <dt>SADAD Payment Method</dt>
                                <dd>${paymentData.sadadPaymentMethod}</dd>
                                <dt>SADAD Payment Channel</dt>
                                <dd>${paymentData.sadadPaymentChannel}</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="contentModule-actions contentModule-actions_centered">
                    <c:if test="${hasInvoice}">
                        <a href="${encodedContextPath}/payment/pdf/${paymentData.serviceId}" target="_blank" class="btn btn_round btn_outline">
                            Print SADAD invoice<span class="iconElement iconElement_print"><icon:print/></span>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<payment:paymentModal/>
