<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="customicon" tagdir="/WEB-INF/tags/responsive/customIcons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- requires sagia.payment.js and onclick="displayPaymentModel()" on a button to be displayed -->
<!-- APPLY CLICK-JACKING STYLING AND HIDE CONTENTS OF THE PAGE -->
<%--<style id="antiClickjack">body{display:none !important;}</style>--%>

<!--Modal: Use (data-toggle="modal" data-target="#creditCardPayment") on link or button to call it-->
<div class="modal fade" id="creditCardPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header"><div class="modal-title"><spring:theme code="payment.creditcardpayment"/></div></div>
            <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onclick="hide()">
                <icon:close/>
            </button>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:payment02/></div>
                <div class="modal-description">
                    <div class="row">
                        <div class="col">
                            <div class="formRadioPayment">

                                <div class="form-group">

                                    <div class="form-item">
                                        <input id="pay01" name="radioPay01" class="form-control" type="radio" value="">
                                        <label for="pay01" class="control-label"><icon:creditCard_visa/></label>
                                    </div>

                                    <div class="form-item">
                                        <input id="pay02" name="radioPay01" class="form-control" type="radio" value="">
                                        <label for="pay02" class="control-label"><icon:creditCard_master/></label>
                                    </div>

                                <!--    <div class="form-item">
                                        <input id="pay03" name="radioPay01" class="form-control" type="radio" value="">
                                        <label for="pay03" class="control-label"><icon:creditCard_dc/></label>
                                    </div>

                                    <div class="form-item">
                                        <input id="pay04" name="radioPay01" class="form-control" type="radio" value="">
                                        <label for="pay04" class="control-label"><icon:creditCard_ae/></label>
                                    </div>  -->

                                    <div class="form-item">
                                        <input id="pay05" name="radioPay01" class="form-control" type="radio" value="">
                                        <label for="pay05" class="control-label"><customicon:creditCard_mada/></label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="formError" id="payment-formError" style="display:none;">
                            <icon:messageError/>
                            <span></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="card-number" class="input-field form-control" placeholder="" value="" type="text" readonly>
                                    <label class="control-label" for="card-number"><spring:theme code="payment.cardno"/></label>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <!--                            <div class="formInputBox formInputBox_group">-->
                            <div class="formInputBox">
                                <div class="form-group">
                                    <input id="security-code" class="input-field form-control" placeholder="" value="" type="text" readonly>
                                    <label class="control-label" for="security-code"><spring:theme code="payment.cvv"/></label>
                                    <!--
                                    <div class="formInputBox-append">
                                    <span class="formInputBox-text formInputBox-text_tip js-tip"
                                          style="position: relative;z-index: 1000;"
                                          data-tip-title="Tooltip Information to be shown to the user."
                                          data-original-title="" title=""><icon:tipInfo/>
                                    </span>
                                    </div>
-->
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="formInputBox formInputBox_group ">
                                <div class="form-group">
                                    <input id="expiry-month" class="input-field form-control" placeholder="MM" value="" type="text">
                                    <label class="control-label" for="expiry-month"><spring:theme code="payment.expirymonth"/></label>
                                    <div class="formInputBox-append">
                                        <span class="formInputBox-text"><spring:theme code="payment.mm"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="formInputBox formInputBox_group ">
                                <div class="form-group">
                                    <input id="expiry-year" class="input-field form-control" placeholder="YY" value="" type="text">
                                    <label class="control-label" for="expiry-year"><spring:theme code="payment.expiryyear"/></label>
                                    <div class="formInputBox-append">
                                        <span class="formInputBox-text"><spring:theme code="payment.yy"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <div class="modal-footer modal-footer_centered">
                    <button id="PayButton" type="button" class="btn btn_slim"  onclick="SAGIA.payment.pay()"><spring:theme code="payment.pay"/></button>
                </div>
            </div>
        </div>
    </div>
</div>


<!--Modal: Use (data-toggle="modal" data-target="#licenseApplicationPayment") on link or button to call it-->
<div class="modal fade" id="licenseApplicationPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header"><div class="modal-title"><spring:theme code="license.apply.applicationpayment"/></div></div>
            <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onclick="hide()">
                <icon:close/>
            </button>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:payment01/></div>
                <div class="modal-description">
                    <div class="tableModule tableModule_footer">
                        <table class="tableModule-table" id="paymentModal">
                            <tbody class="tableModule-body">

                            </tbody>
                            <tfoot class="tableModule-footer">
                            <tr>
                                <td><spring:theme code="license.apply.total"/></td>
                                <td><span id="total"></span></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent pay-buttons" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:creditCard/></span>
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="SAGIA.payment.displayPaymentModel()"><spring:theme code="license.apply.paywithcc"/></button>
                    </div>
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:sadad/></span>
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="SAGIA.payment.displayPayWithSadadMessage()"><spring:theme code="license.apply.paywithsadad"/></button>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent apply" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <button type="button" class="btn btn_slim btn-apply" data-dismiss="modal"><spring:theme code="register.submit"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="entityBillPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header"><div class="modal-title"><spring:theme code="license.apply.applicationpayment"/></div></div>
            <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onclick="hide()">
                <icon:close/>
            </button>
            <div class="modal-body">
                <div class="modal-heroImage"><icon:payment01/></div>
                <div class="modal-description">
                    <div class="tableModule tableModule_footer">
                        <table class="tableModule-table" id="entityBillPaymentModal">
                            <tbody class="tableModule-body">

                            </tbody>
                            <tfoot class="tableModule-footer">
                            <tr>
                                <td><spring:theme code="license.apply.total"/></td>
                                <td><span id="entityBillTotal"></span></td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent pay-buttons" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <span class="iconElement iconElement_iconButton"><icon:creditCard/></span>
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="SAGIA.payment.displayPaymentModel()"><spring:theme code="license.apply.paywithcc"/></button>
                    </div>
                </div>
            </div>
            <div class="modal-secondaryContent apply" style="display: none">
                <div class="modal-footer modal-footer_centered modal-footer_iconButton">
                    <div class="text-center">
                        <button type="button" class="btn btn_slim btn-apply" data-dismiss="modal"><spring:theme code="register.submit"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>