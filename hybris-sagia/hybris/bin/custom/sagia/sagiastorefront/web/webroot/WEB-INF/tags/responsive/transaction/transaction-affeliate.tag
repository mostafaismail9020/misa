<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="modals" tagdir="/WEB-INF/tags/responsive/common" %>


<div id="accordionTransactionAffeliate" class="services-container-tabcontent">
    <div class="accordion-item" style="width: 100%">
        <h5 class="mb-0">
            <button class="accordion-button collapsed" type="button" data-toggle="collapse" data-target="#collapseTransactionAffeliate1"
                    aria-expanded="false" aria-controls="collapseThree">
                <h5 class="mb-0"> <spring:theme code="financial.survey.transaction.title.assets"/>
                </h5>
                <div class="plus-minus-icon"></div>
            </button>
        </h5>

        <div id="collapseTransactionAffeliate1" class="accordion-collapse collapse" aria-labelledby="headingThree" data-parent="#accordionTransactionAffeliate">

            <div class="accordion-body serviceModule-detail border-top-0">


                <div id="assetsTransactionId" class="row">

                    <%-- Start trade Debit--%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.tradeDebit"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="tradeDebitCurrentQuarterId" name="tradeDebitCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="tradeDebitCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="tradeDebitPreviousQuarterId" name="tradeDebitPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="tradeDebitPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End trade Debit--%>


                    <%-- Start Short and Long term loans--%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.shortAndLongTermLoans"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="loansAssetsCurrentQuarterId" name="loansAssetsCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="loansAssetsCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="loansAssetsPreviousQuarterId" name="loansAssetsPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="loansAssetsPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Short and Long term loans--%>


                    <%-- Start interest Received --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.interestReceived"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="interestReceivedCurrentQuarterId"
                                       name="interestReceivedCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="interestReceivedCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="interestReceivedPreviousQuarterId"
                                       name="interestReceivedPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="interestReceivedPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Short and Long term loans--%>


                    <%-- Start dividends Received  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.dividendsReceived"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="dividendsReceivedCurrentQuarterId"
                                       name="dividendsReceivedCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="dividendsReceivedCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="dividendsReceivedPreviousQuarterId"
                                       name="dividendsReceivedPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="dividendsReceivedPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End dividends Received  --%>


                    <%-- Start expensesReceived  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.expensesReceived"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesReceivedCurrentQuarterId"
                                       name="expensesReceivedCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesReceivedCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesReceivedPreviousQuarterId"
                                       name="expensesReceivedPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesReceivedPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End expensesReceived --%>


                    <%-- Start productionSupplies  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.productionSupplies"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="sellProductionSuppliesCurrentQuarterId"
                                       name="sellProductionSuppliesCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="sellProductionSuppliesCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="sellProductionSuppliesPreviousQuarterId"
                                       name="sellProductionSuppliesPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="sellProductionSuppliesPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End productionSupplies --%>


                    <%-- Start machinery  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.machinery"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="sellMachineryCurrentQuarterId"
                                       name="sellMachineryCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="sellMachineryCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="sellMachineryPreviousQuarterId"
                                       name="sellMachineryPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="sellMachineryPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End machinery --%>


                    <%-- Start currentDebitAccount  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.currentDebitAccount"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="currentDebitAccountCurrentQuarterId"
                                       name="currentDebitAccountCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="currentDebitAccountCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="currentDebitAccountPreviousQuarterId"
                                       name="currentDebitAccountPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="currentDebitAccountPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End currentDebitAccount --%>


                    <%-- Start expensesReceived  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.assets.expensesReceived"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesReceivableCurrentQuarterId"
                                       name="expensesReceivableCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesReceivableCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesReceivablePreviousQuarterId"
                                       name="expensesReceivablePreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesReceivablePreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End expensesReceived --%>


                    <%-- Start insuranceCommissionReceivable  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.assets.insuranceCommissionReceivable"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="insuranceCommissionReceivableCurrentQuarterId"
                                       name="insuranceCommissionReceivableCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="insuranceCommissionReceivableCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="insuranceCommissionReceivablePreviousQuarterId"
                                       name="insuranceCommissionReceivablePreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="insuranceCommissionReceivablePreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End insuranceCommissionReceivable --%>


                    <%-- Start other  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.assets.other"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="otherDebitCurrentQuarterId"
                                       name="otherDebitCurrentQuarter" onchange="caluculateTotalTansactionDebit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="otherDebitCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="otherDebitPreviousQuarterId"
                                       name="otherDebitPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="otherDebitPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End other --%>


                    <%-- Start Total  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.assets.total"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="totalDebitCurrentQuarterId"
                                       name="totalDebitCurrentQuarter"
                                       class="form-control" placeholder="." value="" type="text" disabled="true" >
                                <label class="control-label "
                                       for="totalDebitCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="totalDebitPreviousQuarterId"
                                       name="totalDebitPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="totalDebitPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Total --%>


                </div>
            </div>
        </div>
    </div>

    <div class="accordion-item" style="width: 100%">
        <h5 class="mb-0">
            <button class="accordion-button collapsed" type="button" data-toggle="collapse" data-target="#collapseTransactionAffeliate2"
                    aria-expanded="false" aria-controls="collapseThree">
                <h5 class="mb-0"> <spring:theme code="financial.survey.transaction.title.liabilities"/>
                </h5>
                <div class="plus-minus-icon"></div>
            </button>
        </h5>

        <div id="collapseTransactionAffeliate2" class="accordion-collapse collapse" aria-labelledby="headingThree"
             data-parent="#accordionTransactionAffeliate">
            <div class="accordion-body serviceModule-detail border-top-0">

                <div id="transactionId" class="row">

                    <%-- Start trade Credit--%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme code="financial.survey.transaction.liabilities.tradeCredit"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="tradeCreditCurrentQuarterId" name="tradeCreditCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="tradeCreditCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="tradeCreditPreviousQuarterId" name="tradeCreditPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="tradeCreditPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End trade Debit--%>


                    <%-- Start loans Liabilities --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.shortAndLongTermLoans"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="loansLiabilitiesCurrentQuarterId"
                                       name="loansLiabilitiesCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="loansLiabilitiesCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="loansLiabilitiesPreviousQuarterId"
                                       name="loansLiabilitiesPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="loansLiabilitiesPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End loans Liabilities --%>


                    <%-- Start  Interest Payable  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.interestPayables"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="interestPayableCurrentQuarterId"
                                       name="interestPayableCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="interestPayableCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="interestPayablePreviousQuarterId"
                                       name="interestPayablePreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="interestPayablePreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End  Interest Payable --%>


                    <%-- Start  dividends Paid   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.dividendsPaid"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="dividendsPaidCurrentQuarterId"
                                       name="dividendsPaidCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="dividendsPaidCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="dividendsPaidPreviousQuarterId"
                                       name="dividendsPaidPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="dividendsPaidPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End  dividends Paid --%>


                    <%-- Start  Expenses paid   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.expensesPaid"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesPaidCurrentQuarterId"
                                       name="expensesPaidCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesPaidCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesPaidPreviousQuarterId"
                                       name="expensesPaidPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesPaidPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End  Expenses Paid --%>


                    <%-- Start  Purchase of production supplies   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.productionSupplies"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="purchaseProductionSuppliesCurrentQuarterId"
                                       name="purchaseProductionSuppliesCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesPaidCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="purchaseProductionSuppliesPreviousQuarterId"
                                       name="purchaseProductionSuppliesPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="purchaseProductionSuppliesPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End  Purchase of production supplies --%>


                    <%-- Start Purchase of machinery and equipment   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.machinery"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="purchaseMachineryCurrentQuarterId"
                                       name="purchaseMachineryCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="purchaseMachineryCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="purchaseMachineryPreviousQuarterId"
                                       name="purchaseMachineryPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="purchaseMachineryPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Purchase of machinery and equipment --%>


                    <%-- Start  Current credit account   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.currentCreditAccount"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="currentCreditAccountCurrentQuarterId"
                                       name="currentCreditAccountCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="currentCreditAccountCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="currentCreditAccountPreviousQuarterId"
                                       name="currentCreditAccountPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="currentCreditAccountPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Current credit account --%>


                    <%-- Start Expenses paid   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.expensesPaid"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesPayableCurrentQuarterId"
                                       name="expensesPayableCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesPayableCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="expensesPayablePreviousQuarterId"
                                       name="expensesPayablePreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="expensesPayablePreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Expenses paid --%>


                    <%-- Start insurance Commission Payable   --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.liabilities.insuranceCommissionPayable"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="insuranceCommissionPayableCurrentQuarterId"
                                       name="insuranceCommissionPayableCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="insuranceCommissionPayableCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="insuranceCommissionPayablePreviousQuarterId"
                                       name="insuranceCommissionPayablePreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="insuranceCommissionPayablePreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End insurance Commission Payable --%>


                    <%-- Start Other - please specify  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.assets.other"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="otherCreditCurrentQuarterId"
                                       name="otherCreditCurrentQuarter" onchange="calculateTotalTansactionCredit()"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="otherCreditCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="otherCreditPreviousQuarterId"
                                       name="otherCreditPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="otherCreditPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Other - please specify  --%>


                    <%-- Start Total  --%>
                    <div class="col-md-12">
                        <div class="contentModule-headline_smallMargin">
                            <spring:theme
                                    code="financial.survey.transaction.assets.total"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="totalCreditCurrentQuarterId"
                                       name="totalCreditCurrentQuarter"
                                       class="form-control" placeholder="." value="" type="text" disabled="true">
                                <label class="control-label "
                                       for="totalCreditCurrentQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.currentQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <%--<div class="col-md-6">
                        <div class="formInputBox">
                            <div class="form-group">
                                <input id="totalCreditPreviousQuarterId"
                                       name="totalCreditPreviousQuarter"
                                       class="form-control" placeholder="." value="" type="text">
                                <label class="control-label "
                                       for="totalCreditPreviousQuarterId">
                                    <spring:theme code="financial.survey.transaction.assets.previousQuarter"/>
                                </label>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>--%>
                    <%-- End Total  --%>

                </div>


            </div>



            </div>
        </div>
    </div>
</div>


<div class="contentModule-separator"></div>

