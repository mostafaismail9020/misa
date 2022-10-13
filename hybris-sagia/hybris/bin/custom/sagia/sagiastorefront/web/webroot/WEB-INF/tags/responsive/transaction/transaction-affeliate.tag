<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>
<%@ taglib prefix="modals" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:theme code="financial.survey.transaction.affiliate.header"/>
<div id="accordionTransactionAffeliate" class="services-container-tabcontent">
    <div class="accordion-item" style="width: 100%">
        <h5 class="mb-0">
            <button class="accordion-button collapsed" type="button" data-toggle="collapse" data-target="#collapseTransactionAffeliate1"
                    aria-expanded="false" aria-controls="collapseThree">
                <h5 class="mb-0"> <spring:theme code="financial.survey.transaction.affiliate.title.assets"/>
                </h5>
                <div class="plus-minus-icon"></div>
            </button>
        </h5>

        <div id="collapseTransactionAffeliate1" class="accordion-collapse collapse" aria-labelledby="headingThree" data-parent="#accordionTransactionAffeliate">

            <div class="accordion-body serviceModule-detail border-top-0">


                <div id="assetsTransactionId">

                    <%-- Start trade Debit--%>

                    <div class="tableModule tableModule_slim  overflow-y-hidden">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th></th>
                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.currentQuarter" arguments="${fn:escapeXml(currenQuarter)}"  /></th>
                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.previousQuarter"  arguments="${fn:escapeXml(previousQuarter)}"  /></th>
                            </tr>
                            </thead>

                            <tbody class="tableModule-body" id="shareholderEquityableTable">
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.tradeDebit"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="tradeDebitCurrentQuarterId" name="tradeDebitCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="tradeDebitPreviousQuarterId" name="tradeDebitPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>

                                </td>

                            </tr>

                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.shortAndLongTermLoans"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="loansAssetsCurrentQuarterId" name="loansAssetsCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="loansAssetsPreviousQuarterId" name="loansAssetsPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>

                                </td>

                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.interestReceived"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="interestReceivedCurrentQuarterId"
                                                   name="interestReceivedCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="interestReceivedPreviousQuarterId"
                                                   name="interestReceivedPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>

                                </td>

                            </tr>


                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.dividendsReceived"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="dividendsReceivedCurrentQuarterId"
                                                   name="dividendsReceivedCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="dividendsReceivedPreviousQuarterId"
                                                   name="dividendsReceivedPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.expensesReceived"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesReceivedCurrentQuarterId"
                                                   name="expensesReceivedCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesReceivedPreviousQuarterId"
                                                   name="expensesReceivedPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.productionSupplies"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sellProductionSuppliesCurrentQuarterId"
                                                   name="sellProductionSuppliesCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sellProductionSuppliesPreviousQuarterId"
                                                   name="sellProductionSuppliesPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.machinery"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sellMachineryCurrentQuarterId"
                                                   name="sellMachineryCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="sellMachineryPreviousQuarterId"
                                                   name="sellMachineryPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.currentDebitAccount"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="currentDebitAccountCurrentQuarterId"
                                                   name="currentDebitAccountCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="currentDebitAccountPreviousQuarterId"
                                                   name="currentDebitAccountPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.expensesReceived"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesReceivableCurrentQuarterId"
                                                   name="expensesReceivableCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesReceivablePreviousQuarterId"
                                                   name="expensesReceivablePreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.insuranceCommissionReceivable"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="insuranceCommissionReceivableCurrentQuarterId"
                                                   name="insuranceCommissionReceivableCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="insuranceCommissionReceivablePreviousQuarterId"
                                                   name="insuranceCommissionReceivablePreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.other"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="otherDebitCurrentQuarterId"
                                                   name="otherDebitCurrentQuarter" onchange="caluculateTotalAffeliateTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="otherDebitPreviousQuarterId"
                                                   name="otherDebitPreviousQuarter" onchange="caluculateTotalAffeliatePreviousTansactionDebit()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.total"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="totalDebitCurrentQuarterId"
                                                   name="totalDebitCurrentQuarter"
                                                   class="form-control" placeholder="." value="" type="text" disabled="true" >
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="totalDebitPreviousQuarterId"
                                                   name="totalDebitPreviousQuarter"
                                                   class="form-control" placeholder="." value="" type="text" disabled="true" >
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="accordion-item" style="width: 100%">
        <h5 class="mb-0">
            <button class="accordion-button collapsed" type="button" data-toggle="collapse" data-target="#collapseTransactionAffeliate2"
                    aria-expanded="false" aria-controls="collapseThree">
                <h5 class="mb-0"> <spring:theme code="financial.survey.transaction.affiliate.title.liabilities"/>
                </h5>
                <div class="plus-minus-icon"></div>
            </button>
        </h5>

        <div id="collapseTransactionAffeliate2" class="accordion-collapse collapse" aria-labelledby="headingThree"
             data-parent="#accordionTransactionAffeliate">
            <div class="accordion-body serviceModule-detail border-top-0">

                <div id="transactionId" >

                    <div class="tableModule tableModule_slim  overflow-y-hidden">
                        <table class="tableModule-table">
                            <thead class="tableModule-head">
                            <tr>
                                <th></th>
                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.currentQuarter" arguments="${fn:escapeXml(currenQuarter)}"  /></th>
                                <th style="text-align: center"><spring:theme code="financial.survey.transaction.assets.previousQuarter"  arguments="${fn:escapeXml(previousQuarter)}"  /></th>
                            </tr>
                            </thead>

                            <tbody class="tableModule-body" id="transactionPayableShareholderTable">
                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.tradeCredit"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="tradeCreditCurrentQuarterId" name="tradeCreditCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="tradeCreditPreviousQuarterId" name="tradeCreditPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.shortAndLongTermLoans"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="loansLiabilitiesCurrentQuarterId"
                                                   name="loansLiabilitiesCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="loansLiabilitiesPreviousQuarterId"
                                                   name="loansLiabilitiesPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.interestPayables"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="interestPayableCurrentQuarterId"
                                                   name="interestPayableCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="interestPayablePreviousQuarterId"
                                                   name="interestPayablePreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>




                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.dividendsPaid"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="dividendsPaidCurrentQuarterId"
                                                   name="dividendsPaidCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="dividendsPaidPreviousQuarterId"
                                                   name="dividendsPaidPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.expensesPaid"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesPaidCurrentQuarterId"
                                                   name="expensesPaidCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesPaidPreviousQuarterId"
                                                   name="expensesPaidPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.productionSupplies"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="purchaseProductionSuppliesCurrentQuarterId"
                                                   name="purchaseProductionSuppliesCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="purchaseProductionSuppliesPreviousQuarterId"
                                                   name="purchaseProductionSuppliesPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.machinery"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="purchaseMachineryCurrentQuarterId"
                                                   name="purchaseMachineryCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="purchaseMachineryPreviousQuarterId"
                                                   name="purchaseMachineryPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.currentCreditAccount"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="currentCreditAccountCurrentQuarterId"
                                                   name="currentCreditAccountCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="currentCreditAccountPreviousQuarterId"
                                                   name="currentCreditAccountPreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>




                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.expensesPaid"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesPayableCurrentQuarterId"
                                                   name="expensesPayableCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="expensesPayablePreviousQuarterId"
                                                   name="expensesPayablePreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.liabilities.insuranceCommissionPayable"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="insuranceCommissionPayableCurrentQuarterId"
                                                   name="insuranceCommissionPayableCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="insuranceCommissionPayablePreviousQuarterId"
                                                   name="insuranceCommissionPayablePreviousQuarter" onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.other"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="otherCreditCurrentQuarterId"
                                                   name="otherCreditCurrentQuarter" onchange="calculateTotalTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="otherCreditPreviousQuarterId"
                                                   name="otherCreditPreviousQuarter"  onchange="calculateTotalPrevQuarterTansactionCreditForAffeliate()"
                                                   class="form-control" placeholder="." value="" type="text">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>



                            <tr>
                                <td class="lastUpdate">
                                    <spring:theme
                                            code="financial.survey.transaction.assets.total"/>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="totalCreditCurrentQuarterId"
                                                   name="totalCreditCurrentQuarter"
                                                   class="form-control" placeholder="." value="" type="text" disabled="true">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                                <td>
                                    <div class="formInputBox">
                                        <div class="form-group">
                                            <input id="totalCreditPreviousQuarterId"
                                                   name="totalCreditPreviousQuarter"
                                                   class="form-control" placeholder="." value="" type="text" disabled="true">
                                        </div>
                                        <div class="help-block"></div>
                                    </div>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>


                </div>


            </div>



        </div>
    </div>
</div>
</div>


<div class="contentModule-separator"></div>

