<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<%-- TODO: SAH-890: Payments Overview --%>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Payments</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-actions contentModule-actions_right contentModule-actions_wrap">
                        <div class="sortBySelect">
                            <div class="sortBySelect-label">Sort by:</div>
                            <div class="sortBySelect-input">
                                <select class="js-select2-oneColumn form-control">
                                    <option value="null"></option>
                                    <option value="null">Name</option>
                                    <option value="null">Status</option>
                                    <option value="null">Amount</option>
                                </select>
                            </div>
                        </div>                        
                    </div>
                    <div class="tableModule tableModule_slim tableModule_striped">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                                <tr>
                                    <th><spring:theme code="dashboard.payments.name"/></th>
                                    <th><spring:theme code="dashboard.payments.status"/></th>
                                    <th class="dashboardWidgetPayments-lastCol"><spring:theme code="dashboard.payments.amount"/></th>
                                </tr>
                            </thead>
                            <tbody class="tableModule-body">
                                <c:forEach items="${payments}" var="payment">
                                    <tr>
                                        <td>
                                            <div class="dashboardWidgetPayments-date">
                                                <div>
                                                    <c:out value="${payment.paymentMonth}"/>
                                                </div>
                                                <c:out value="${payment.paymentDay}"/>
                                            </div>
                                            <div class="dashboardWidgetPayments-title"><c:out value="${payment.name}"/></div>
                                            <div class="dashboardWidgetPayments-eid">EID 600048</div>
                                        </td>
                                        <td>
                                            <c:out value="${payment.statusDescription}"/>
                                            <div class="dashboardWidgetPayments-status-icon">
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${fn:contains(payment.status, 'E0001')}">--%>
                                                        <%--<div class="historyList-status historyList-status_process">--%>
                                                                <%--${financial.srStatusDescription}--%>
                                                        <%--</div>--%>
                                                    <%--</c:when>--%>
                                                <%--</c:choose>--%>
                                                <icon:status-complete/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="dashboardWidgetPayments-amount">
                                                <c:out value="${payment.amount}"/>
                                            </div>
                                            <div class="dashboardWidgetPayments-currency">
                                                SAR
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>                    
                    <div class="paginationModule">
                        <div class="paginationModule-wrapper">
                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                <icon:arrow_green_right/>
                            </button>

                            <div class="paginationModule-items">
                                <div class="paginationModule-item"><a href="" class="paginationModule-link active">1</a></div>
                                <div class="paginationModule-item"><a href="" class="paginationModule-link">2</a></div>
                                <div class="paginationModule-item"><a href="" class="paginationModule-link">3</a></div>
                                <div class="paginationModule-item"><a href="" class="paginationModule-link">4</a></div>
                                <div class="paginationModule-item">
                                    <div class="paginationModule-separator paginationModule-separator_right">&hellip;</div>
                                </div>
                                <div class="paginationModule-item"><a href="" class="paginationModule-link">10</a></div>
                            </div>

                            <button class="paginationModule-control paginationModule-control_right">
                                <icon:arrow_green_right/>
                            </button>

                        </div>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
</div>






<%-- TODO: SAH-890: Payments Detail - Sadad --%>


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Payment Details</h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Payments Overview</a>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-headline contentModule-headline_big">Subscription Fee: 21000707</div>
                               
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:questionaires/>
                       </span>                       
                        General Data
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Service ID</dt>
                                <dd>21000707</dd>
                                <dt>Entity Address</dt>
                                <dd>852 Tahalia Street, 1 / 22345 000018000046</dd>
                                <dt>Employee Response</dt>
                                <dd>Mr. 622421 Ilam S Sivaprakasam</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Entity Name</dt>
                                <dd></dd>
                                <dt>Contact</dt>
                                <dd>Contact</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                                
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:documents/>
                       </span>                       
                        Processing Data
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Service Status</dt>
                                <dd>Awaiting Payment</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Date of creation</dt>
                                <dd>27.09.2016</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                
                          
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:payment03/>
                       </span>                       
                        Value
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Net Value of the service</dt>
                                <dd>50.000,00 SAR</dd>
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
                                <dd>0,00 SAR</dd>
                                <dt>SADAD Bill NUmber</dt>
                                <dd></dd>
                                <dt>SADAD Bill Account</dt>
                                <dd></dd>
                                <dt>SADAD Payment Number</dt>
                                <dd></dd>
                                <dt>SADAD Investor ID</dt>
                                <dd></dd>
                                <dt>SADAD Amount Paid</dt>
                                <dd></dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <div class="contentModule-headline contentModule-headline_small">
                                SADAD Bill Bank Information
                            </div>
                            <dl class="dlList dlList_separated">
                                <dt>SADAD Transaction Date</dt>
                                <dd></dd>
                                <dt>SADAD Transaction Number</dt>
                                <dd></dd>
                                <dt>SADAD Bank ID</dt>
                                <dd></dd>
                                <dt>SADAD Payment Method</dt>
                                <dd></dd>
                                <dt>SADAD Payment Channel</dt>
                                <dd></dd>
                            </dl>
                        </div>                        
                    </div>
                </div>
                
                    

                <div class="contentModule-actions contentModule-actions_centered">
                    <button class="btn btn_outline btn_round btn_slim">
                          Print SADAD invoice<span class="iconElement iconElement_print"><icon:print/></span>
                    </button>
                </div>                    
            </div>            
        </div>        
    </div>
</div>









<%-- TODO: SAH-890: Payments Detail - Creditcard --%>


<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Payment Details</h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Payments Overview</a>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPaddingTop">
    <div class="container">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-headline contentModule-headline_big">Subscription Fee: 21000707</div>
                               
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:questionaires/>
                       </span>                       
                        General Data
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Service ID</dt>
                                <dd>21000707</dd>
                                <dt>Entity Address</dt>
                                <dd>852 Tahalia Street, 1 / 22345 000018000046</dd>
                                <dt>Employee Response</dt>
                                <dd>Mr. 622421 Ilam S Sivaprakasam</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Entity Name</dt>
                                <dd></dd>
                                <dt>Contact</dt>
                                <dd>Contact</dd>
                            </dl>
                        </div>
                    </div>
                </div>

                                
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:documents/>
                       </span>                       
                        Processing Data
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Service Status</dt>
                                <dd>Awaiting Payment</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Date of creation</dt>
                                <dd>27.09.2016</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                
                          
                <div class="contentModule-section">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:payment03/>
                       </span>                       
                        Value
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt>Net Value of the service</dt>
                                <dd>50.000,00 SAR</dd>
                            </dl>
                        </div>
                    </div>
                </div>
                
                           
                <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                    <div class="contentModule-headline">
                       <span class="iconElement iconElement_questionaires">
                           <icon:info/>
                       </span>                       
                        Credit Card Payment Information
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="contentModule-headline contentModule-headline_small">
                                SADAD Bill Payment Information
                            </div>
                            <dl class="dlList dlList_separated">
                                <dt>Credit card type</dt>
                                <dd>VISA</dd>
                                <dt>Card Number</dt>
                                <dd>1234-5678-9012-3456</dd>
                                <dt>Security code</dt>
                                <dd>234</dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <div class="contentModule-headline contentModule-headline_small">
                                SADAD Bill Bank Information
                            </div>
                            <dl class="dlList dlList_separated">
                                <dt>Name on card</dt>
                                <dd>Rollo Ragnvaldsson</dd>
                                <dt>Expires on</dt>
                                <dd>July, 2024</dd>
                            </dl>
                        </div>                        
                    </div>
                </div>
                  
            </div>            
        </div>        
    </div>
</div>


