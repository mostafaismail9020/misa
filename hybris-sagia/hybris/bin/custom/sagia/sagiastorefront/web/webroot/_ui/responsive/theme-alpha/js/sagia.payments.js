var SAGIA = SAGIA || {};
SAGIA.payments = {
    _autoload: [],

    _enquireload: [
        ["screenSmMax", "match", "unmatch"]
    ],

    loadDashboardData: function() {
            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/payments/10000",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (payments) {
                    if (payments && payments.paymentData && payments.PaymentsPagesNumber) {
                        var paymentsTableHtml = '';
                        for (var index in payments.paymentData) {
                            if (payments.paymentData.hasOwnProperty(index)) {
                                var payment = payments.paymentData[index];
                                var billId = payment.serviceId;
                                var billDesc = payment.name;
                                var currency = payment.currency;
                                var amount = payment.amount;
                                paymentsTableHtml +=
                                    '<tr>' +
                                    '   <td>' +
                                    '       <div class="dashboardWidgetPayments-date dashboardWidgetPayments-date_advanced">' +
                                    '           <div>' + payment.sagiaPaymentDate.dateFormatted + '</div>' +
                                    '       </div>' + '</td>'
                                    + '<td>'+
                                    '       <div class="dashboardWidgetPayments-title dashboardWidgetPayments-title_advanced">' +
                                    '           <a href="' + ACC.config.encodedContextPath + '/payment/details/' + payment.serviceId + '">' + payment.name + '</a>' +
                                    '       </div>' +
                                    '       <div class="dashboardWidgetPayments-eid dashboardWidgetPayments-eid_advanced">EID 600048</div>' +
                                    '    </td>' +
                                    '    <td>';
                                if (payment.statusIcon.indexOf('ERROR') !== -1) {
                                    paymentsTableHtml += '<span class="payment-text_error">';
                                } else if (payment.statusIcon.indexOf('PENDING') !== -1) {
                                    paymentsTableHtml += '<span class="payment-text_pending">';
                                } else if (payment.statusIcon.indexOf('DONE') !== -1) {
                                    paymentsTableHtml += '<span class="payment-text_done">';
                                }
                                if (payment.status == 'E0003' && payment.hybrisStatusDescription == 'Paid') {
                                	paymentsTableHtml += payment.hybrisStatusDescription + '</span>';
                                }else{
                                	paymentsTableHtml += payment.statusDescription + '</span>';
                                }
                                paymentsTableHtml +=
                                    '       <div class="dashboardWidgetPayments-status-icon">';
                                if (payment.statusIcon.indexOf('ERROR') !== -1) {
                                    paymentsTableHtml += '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 18"><g fill="none"><path fill="#D0021B" d="M9.864 9.157l2.303 2.303a.5.5 0 0 1-.707.707l-2.303-2.303-2.303 2.303a.5.5 0 0 1-.708-.707l2.304-2.303-2.304-2.303a.5.5 0 1 1 .708-.708l2.303 2.304 2.303-2.304a.5.5 0 0 1 .707.708l-2.303 2.303z"/><path stroke="#D0021B" stroke-linecap="round" stroke-linejoin="round" d="M17 9a8 8 0 1 1-16.001-.001 8 8 0 0 1 16.001.001z"/></g></svg>';
                                } else if (payment.statusIcon.indexOf('PENDING') !== -1) {
                                    paymentsTableHtml += '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">' +
                                        '<path fill="#CFB264" d="M7.999,15.611c-4.197,0-7.611-3.415-7.611-7.612c0-4.196,3.414-7.61,7.61-7.61\tc4.198,0,7.613,3.414,7.613,7.611C15.611,12.197,12.196,15.611,7.999,15.611z M8,1.389c-1.767,0-3.427,0.688-4.676,1.936C2.076,4.573,1.388,6.233,1.388,7.999c0,3.646,2.966,6.612,6.611,6.612c3.646,0,6.612-2.966,6.612-6.611C14.611,4.355,11.646,1.389,8,1.389z M8,6.223c0.981,0,1.777,0.795,1.777,1.777S8.981,9.777,8,9.777S6.223,8.981,6.223,8S7.019,6.223,8,6.223z"/>' +
                                        '</svg>';
                                } else if (payment.statusIcon.indexOf('DONE') !== -1) {
                                    paymentsTableHtml += '<svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><g stroke="#5CC83B" stroke-linecap="round" stroke-linejoin="round" fill="none"><path d="M11.564 5.686l-5.187 4.842-1.73-1.729"/><path d="M15.111 8c0 3.928-3.185 7.111-7.112 7.111s-7.111-3.185-7.111-7.112c0-3.928 3.184-7.111 7.112-7.11 3.928 0 7.111 3.183 7.111 7.111z"/></g></svg>';
                                }
                                paymentsTableHtml += '' +
                                    '       </div>' +
                                    '    </td>' +
                                    '    <td>' +
                                    '       <span class="dashboardWidgetPayments-amount">' + (payment.amount ? payment.amount.toLocaleString('en-US', {maximumFractionDigits: 2}) : 0)+ "&nbsp;" + payment.currency  +"</span>" +
                                    '    </td>' +
                                    '    <td><div class="dashboardWidgetPayments-pay">';
                                if (payment.status == 'E0003' && payment.hybrisStatusDescription != 'Paid') {
                                	paymentsTableHtml +='<a onclick="SAGIA.payment.requestCreditBillPayment('+billId+',\''+billDesc+'\','+amount+',\''+payment.currency+'\')" class="print-not-link btn btn_outline btn_round btn_slim" style="float: left;">' + getI18nText("payment.pay") +'</a>';
                                }
                                paymentsTableHtml +='</div></td></tr>';
                            }
                        }
                        $("#paymentsTable").empty().append(paymentsTableHtml);
                        var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment active">1</a></div>';
                        for (var i = 2; i <= payments.PaymentsPagesNumber; i++) {
                            paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment">' + i + '</a></div>';
                        }
                        $(".dashboardWidgetPayments .paginationModule-items").empty().append(paginationHtml);
                        new CreatePagination($(".dashboardWidgetPayments .paginationModule"));
                        $(".dashboardWidgetPayments .paginationPicker").append("<option selected='selected' value='" + payments.paymentsItemsPerPage + "'>" + getI18nText("dashboard.servicesRequest.viewAll") + "</option>")


                        if(payments.showItemsPerPage != null && payments.showItemsPerPage.length > 0){
                            var items = payments.showItemsPerPage.split(",");
                            for(i=0;i<items.length;i++){
                                $(".dashboardWidgetPayments .paginationPicker").append("<option value='" +  items[i] + "'>"+ items[i] +"</option>");
                            }
                        }
                    }
                    $('.dashboardWidgetPayments .loadingModule').addClass('loadingModule_loaded');
                    $('.dashboardWidgetPayments .paginationModule').removeClass('paginationModule_loading');
                },
                error: function() {
                    paymentsTable.empty();
                    $(".dashboardWidgetPayments .paginationModule-items").empty();
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.payments"));
                    errorModal.modal('show');
                    $('.dashboardWidgetPayments .loadingModule .loadingModule-icon').hide();
                    $('.dashboardWidgetPayments .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>Could not payments</span>");
                }
            });

    }
};

$(function () {
    SAGIA.payments.loadDashboardData();
});