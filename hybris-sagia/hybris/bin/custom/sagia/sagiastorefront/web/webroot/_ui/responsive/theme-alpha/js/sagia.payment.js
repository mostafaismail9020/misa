var SAGIA = SAGIA || {};
SAGIA.payment = SAGIA.payment || {};

SAGIA.payment = {
    salesOrders: [],
    requestPayment : function(notification){
        var currency;
        var paymentItems = notification.paymentItems;
        var payableAmount = notification.amountPayable;

        SAGIA.payment.salesOrders = notification.transactionId.split("|");
		if(SAGIA.payment.salesOrders.length == 1){
        	SAGIA.payment.salesOrders.push("");
        }

        $("#licenseApplicationPayment").find('.apply').hide();
        $("#licenseApplicationPayment").find('.pay-buttons').show();
        $("#paymentModal").find("tbody").html("");
        for(key in paymentItems){
            $("#paymentModal").find("tbody").append('<tr> <td style="font-weight: 600">'+ key +'</td><td></td></tr>');
            for(item in paymentItems[key]){
                if(!currency && paymentItems[key][item].currency){
                    currency = paymentItems[key][item].currency;
                }
                var amount = Math.round(paymentItems[key][item].value * 100) / 100;
                SAGIA.payment.salesOrders.push(amount);
                $("#paymentModal").find("tbody").append('<tr> <td>'+ paymentItems[key][item].description +'</td>'+'<td>'+ amount +' '+currency +'</td></tr>');
            }
        }
        $('#total').text(Math.round(payableAmount * 100) / 100 +' '+currency);
        $('#total').attr("total",Math.round(payableAmount * 100) / 100);
        $('#total').attr("currency",currency);
        $('#licenseApplicationPayment').modal('toggle');
    },
    requestCreditBillPayment : function(billId, billDesc, amount, payableCurrency){
    	var currency = payableCurrency;
    	var billDescription = billDesc;
        //var paymentItems = notification.paymentItems;
        var payableAmount = amount;

        SAGIA.payment.salesOrders.push(billId);
		if(SAGIA.payment.salesOrders.length == 1){
        	SAGIA.payment.salesOrders.push("");
        }

        $("#licenseApplicationPayment").find('.apply').hide();
        $("#licenseApplicationPayment").find('.pay-buttons').show();
        $("#paymentModal").find("tbody").html("");
        
        var amount = Math.round(payableAmount * 100) / 100;
        SAGIA.payment.salesOrders.push(amount);
        
        $("#paymentModal").find(".js-wrapper").append('<span>'+ billDescription +'</span>'+'<span>'+ currency +' '+ amount +'</span>');
        
        $('#total').text(currency+ ' '+ Math.round(payableAmount * 100) / 100 );
        $('#total').attr("total",Math.round(payableAmount * 100) / 100);
        $('#total').attr("currency",currency);
        $('#licenseApplicationPayment').modal('toggle');
    },
    requestPaymentDetails: function(reviewMode, serviceType, callback){
      SAGIA.payment.requestPaymentDetails(reviewMode, serviceType, null, callback);
    },
    requestPaymentDetails: function(reviewMode, serviceType, qeemah, callback){
        if(reviewMode){
            $("#licenseApplicationPayment").find('.apply').show();
			$("#tblGrid").find("tbody").html("");            
            if(SAGIA.license.apply.data.isEntrepreneur){
            $("#tblGrid").find("tbody").append("<tr>" +
                    "<th width='40%'>" + getI18nText("license.apply.payment.service") + "</th>" +
                    "<th width='20%'>" + getI18nText("license.apply.payment.duration") + "</th>" +
                    "<th width='40%'>" + getI18nText("license.apply.payment.price") + "</th>" +
                    "</tr>" + 
                    "<tr>" +
                    "<td width='40%'>" + getI18nText("license.apply.payment.license.fee") + "</td>" +
                    "<td width='20%'>" + getI18nText("license.apply.payment.license.fee.entrepreneur.duration."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                    "<td width='40%'>" + getI18nText("license.apply.payment.license.fee.entrepreneur.price."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                    "</tr>" + 
                    "<tr>" +
                    "<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee") + "</td>" +
                    "<td width='20%'>" + getI18nText("license.apply.payment.subscription.fee.entrepreneur.duration."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                    "<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee.entrepreneur.price."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                    "</tr>" + 
                    "<tr>" +
                    "<td width='60%'><span style='color:green;font-size:12px'>" + getI18nText("license.apply.payment.entrepreneur.description") + "</span></td>" +
                    "<td width='20%'>" + "</td>" +
                    "<td width='20%'>" + "</td>" +
                    "</tr>" + 
                    "<tr>" +
                    "<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total") + "</span></td>" +
                    "<td width='20%'>" +  "</td>" +
                    "<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.entrepreneur.total."+SAGIA.license.apply.data.licenseYear) + "</span></td>" +
                    "</tr>");
            
            }else{
            	$("#tblGrid").find("tbody").append("<tr>" +
                        "<th width='50%'>" + getI18nText("license.apply.payment.service") + "</th>" +
                        "<th width='20%'>" + getI18nText("license.apply.payment.duration") + "</th>" +
                        "<th width='30%'>" + getI18nText("license.apply.payment.price") + "</th>" +
                        "</tr>" + 
                        "<tr>" +
                        "<td width='40%' >" + getI18nText("license.apply.payment.license.fee") + "</td>" +
                        "<td width='20%' >" + getI18nText("license.apply.payment.license.fee.duration."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                        "<td width='40%' >" + getI18nText("license.apply.payment.license.fee.price."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                        "</tr>" + 
                        "<tr>" +
                        "<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee") + "</td>" +
                        "<td width='20%'>" + getI18nText("license.apply.payment.subscription.fee.duration."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                        "<td width='40%'>" + getI18nText("license.apply.payment.subscription.fee.price."+SAGIA.license.apply.data.licenseYear) + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total") + "</span></td>" +
                        "<td width='20%'>" +  "</td>" +
                        "<td width='40%'><span style='font-weight:bold'>" + getI18nText("license.apply.payment.total."+SAGIA.license.apply.data.licenseYear) + "</span></td>" +
                        "</tr>");
            }  
            $("#licenseApplicationPayment").find('.pay-buttons').hide();
        }
        else{
            $("#licenseApplicationPayment").find('.apply').hide();
            $("#licenseApplicationPayment").find('.pay-buttons').show();
        }

        var requestUrl = ACC.config.encodedContextPath + "/simulator/paymentDetails/"+serviceType;
        if(qeemah){
            requestUrl+= "/"+qeemah;
        }
        $.ajax(requestUrl, {
            type:"GET",
            contentType:"application/json;charset=utf-8",
            cache:false,
            success : function(response) {
                response = JSON.parse(response);
                var items = response.items;
                $("#paymentModal").find("tbody").html("");
                for (var key in items) {
                    $("#paymentModal").find("tbody").append('<tr> <td>'+ items[key].serviceName +'</td>'+'<td>'+ Math.round(items[key].netValue * 100) / 100 +'</td></tr>');
                }
                $('#total').text(Math.round(response.total * 100) / 100 +' '+response.currency);
                $('#total').attr("total",Math.round(response.total * 100) / 100);
                $('#total').attr("currency",response.currency);
                $('#licenseApplicationPayment').unbind().modal('toggle');

                if(callback)
                    $("#licenseApplicationPayment").find(".btn-apply").unbind().on('click', callback); //use unbind to avoid multiple request on close and reopen same model
            }
        });
    },
    displayPaymentError: function(error){
        $("#payment-formError").hide();
        $("#payment-formError span").html(getI18nText(error));
        $("#payment-formError").show();
    },
    pay: function () {
        // UPDATE THE SESSION WITH THE INPUT FROM HOSTED FIELDS
        PaymentSession.updateSessionFromForm('card');
    },
    displayPaymentModel: function() {
        $("#creditCardPayment").modal('toggle');
    },
    displayPayWithSadadMessage: function() {
        $("#licenseApplicationPayment").modal("hide");
        SAGIA.showError(getI18nText("payments.sadad.clicked"));
    }
};

$(function(){
    $("#creditCardPayment").on('shown.bs.modal', function () {

        if(!$(this).find('.gw-proxy-number').length) {

            PaymentSession.configure({
                fields: {
                    // ATTACH HOSTED FIELDS TO YOUR PAYMENT PAGE FOR A CREDIT CARD
                    card: {
                        number: "#card-number",
                        securityCode: "#security-code",
                        expiryMonth: "#expiry-month",
                        expiryYear: "#expiry-year"
                    }
                },
                //SPECIFY YOUR MITIGATION OPTION HERE
                frameEmbeddingMitigation: ["javascript"],
                callbacks: {
                    initialized: function(response) {
                        // HANDLE INITIALIZATION RESPONSE
                    },
                    formSessionUpdate: function(response) {
                        // HANDLE RESPONSE FOR UPDATE SESSION
                        if (response.status) {
                            if ("ok" == response.status) {
                                $("#payment-formError").hide();
                                var map = {sessionId: response.session.id, amount: $("#total").attr("total"), "currency": $("#total").attr("currency")};
                                if(SAGIA.payment.salesOrders){
                                    map["salesOrder1"] = SAGIA.payment.salesOrders[0];
                                    map["salesOrder2"] = SAGIA.payment.salesOrders[1];
                                    map["salesOrder1Amount"] = SAGIA.payment.salesOrders[2];
                                    map["salesOrder2Amount"] = SAGIA.payment.salesOrders[3];
                                }
                                $.ajax({
                                    type: 'POST',
                                    contentType: "application/json",
                                    url: ACC.config.encodedContextPath+'/payment/payLicense',
                                    data : JSON.stringify(map),
                                    success : function(data) {
                                        var jsonData = JSON.parse(data);
                                        if (jsonData.success == "true") {
                                            window.location.assign(ACC.config.encodedContextPath + "/payment/success");
                                        } else if(jsonData.success == "secure3DForm"){
                                            window.location.assign(ACC.config.encodedContextPath + "/payment/secure3DForm");
                                        } else if (jsonData.success == "false") {
                                            SAGIA.payment.displayPaymentError("payments.missingData");
                                        }
                                        else {
                                            SAGIA.payment.displayPaymentError(jsonData.error.cause + " : " + jsonData.error.explanation);
                                        }
                                    },
                                    error: function (xhr,textStatus,throwError){
                                        //Error block
                                        SAGIA.payment.displayPaymentError("payments.connectionError");
                                    }

                                });
                                //check if the security code was provided by the user
                                if (response.sourceOfFunds.provided.card.securityCode) {
                                    //console.log("Security code was provided.");
                                }

                                //check if the user entered a Mastercard credit card
                                if (response.sourceOfFunds.provided.card.scheme == 'MASTERCARD') {
                                    //console.log("The user entered a Mastercard credit card.")
                                }
                            } else if ("fields_in_error" == response.status)  {

                                //console.log("Session update failed with field errors.");
                                if (response.errors.cardNumber) {
                                    SAGIA.payment.displayPaymentError("payments.cardNumberInvalid");
                                }
                                if (response.errors.expiryYear) {
                                    SAGIA.payment.displayPaymentError("payments.yearError");
                                }
                                if (response.errors.expiryMonth) {
                                    SAGIA.payment.displayPaymentError("payments.monthError");

                                }
                                if (response.errors.securityCode) {
                                    SAGIA.payment.displayPaymentError("payments.securityCodeError");

                                }
                            } else if ("request_timeout" == response.status)  {
                                //console.log("Session update failed with request timeout: " + response.errors.message);
                                SAGIA.payment.displayPaymentError("payments.requestTimeout");

                            } else if ("system_error" == response.status)  {
                                //console.log("Session update failed with system error: " + response.errors.message);
                                SAGIA.payment.displayPaymentError("payments.sessionUpdateError");

                            }
                        } else {
                            //console.log("Session update failed: " + response);
                            SAGIA.payment.displayPaymentError("payments.sessionUpdateFailed");
                        }
                    }
                }
            });
        }

        PaymentSession.setFocus('card.number');

        PaymentSession.setFocusStyle(["card.number","card.securityCode"], {
            borderColor: '#a0ed98',
            borderWidth: '1px',
            boxShadow: '0 0 10px 0 rgba(0, 0, 0, 0.05), inset 0 1px 3px 0 rgba(241, 242, 242, 0.6)',
            height: '39px'
        });

    });
});






