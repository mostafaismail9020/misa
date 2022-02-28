function CreatePagination(element, itemsPerPage) {
    this.element = element;
    var self = this;
    
    this.itemsParent = $(this.element).find('.paginationModule-items');
    this.itemsCount = $(this.element).find('.paginationModule-items').children().length;
    this.items = $(this.element).find('.paginationModule-item');
    this.leftControl = $(this.element).find('.paginationModule-control_left');
    this.rightControl = $(this.element).find('.paginationModule-control_right');
    this.currentIndex = 0;
    this.itemsPerPage = itemsPerPage ? itemsPerPage : 5;

    this._firstNumbers = function() {
        //clearClass
        self.itemsParent.removeClass('paginationModule-items_lastNumbers');
        self.itemsParent.removeClass('paginationModule-items_middleNumbers');

        //setClass
        self.itemsParent.addClass('paginationModule-items_firstNumbers');

        self.items.css('display', 'inline-block');
        self.items.slice(self.itemsPerPage - 1, self.itemsCount - 1).css('display', 'none');
    };

    this._lastNumbers = function() {
        //clearClasses
        self.itemsParent.removeClass('paginationModule-items_firstNumbers');
        self.itemsParent.removeClass('paginationModule-items_middleNumbers');

        //setClass
        self.itemsParent.addClass('paginationModule-items_lastNumbers');

        self.items.css('display', 'inline-block');
        self.items.slice(1, -(self.itemsPerPage - 1)).css('display', 'none');
    };

    this._middleNumbers = function() {
        //clearClasses
        self.itemsParent.removeClass('paginationModule-items_firstNumbers');
        self.itemsParent.removeClass('paginationModule-items_lastNumbers');

        //setClass
        self.itemsParent.addClass('paginationModule-items_middleNumbers'); 

        self.items.css('display', 'inline-block');
        self.items.slice(1, self.currentIndex - 1).css('display', 'none');
        self.items.slice(self.currentIndex + 2, self.itemsCount - 1).css('display', 'none');
    };

    //initial setup
    if(self.itemsCount > self.itemsPerPage) {
        this._firstNumbers();
    }
    
    //click on numbers
    $(this.element).find('.paginationModule-link').on('click', function (e) {
        e.preventDefault();
        
        //get current index
        self.currentIndex = $(this).parent().index();
        
        self.items.children().removeClass('active');
        self.items.eq(self.currentIndex).children().addClass('active');
        
        if(self.currentIndex > 0 && self.currentIndex < self.itemsCount - 1) {
            self.leftControl.removeAttr('disabled');
            self.rightControl.removeAttr('disabled');
        } else if (self.currentIndex === 0) {
            self.leftControl.attr('disabled','true');
            self.rightControl.removeAttr('disabled');
        } else if (self.currentIndex === self.itemsCount - 1) {
            self.rightControl.attr('disabled','true');
            self.leftControl.removeAttr('disabled');
        }

        if(self.currentIndex < (self.itemsPerPage - 2) && self.itemsCount > self.itemsPerPage) {
            //show firstNumbers
            self._firstNumbers();
        } else if(self.currentIndex > self.itemsCount - (self.itemsPerPage - 1) && self.itemsCount > self.itemsPerPage) {
            //show lastNumbers 
            self._lastNumbers();
        } else if(self.itemsCount > self.itemsPerPage) {
            //show middleNumbers 
            self._middleNumbers();
        }
    });

    //click on right arrow
    $(this.element).find('.paginationModule-control_right').on('click', function () {
        if(self.currentIndex >= 0 && self.currentIndex < self.itemsCount - 1) {
            self.items.children().removeClass('active');
            self.currentIndex++;
            self.items.eq(self.currentIndex).children().addClass('active').trigger('click');

            //set states for control arrows
            self.leftControl.removeAttr('disabled');
            if(self.currentIndex === self.itemsCount - 1) {
                self.rightControl.attr('disabled','true');
            }
            
            //set display of numbers
            if(self.currentIndex < (self.itemsPerPage - 2) && self.itemsCount > self.itemsPerPage) {
                //show firstNumbers
                self._firstNumbers();
            } else if(self.currentIndex > self.itemsCount - (self.itemsPerPage - 1) && self.itemsCount > self.itemsPerPage) {
                //show lastNumbers 
                self._lastNumbers();
            } else if(self.itemsCount > self.itemsPerPage) {
                //show middleNumbers 
                self._middleNumbers();
            }
        }
    });

    //click on left arrow
    $(this.element).find('.paginationModule-control_left').on('click', function () {
        if(self.currentIndex >= 0 && self.currentIndex <= self.itemsCount - 1) {
            self.items.children().removeClass('active');
            self.currentIndex--;
            self.items.eq(self.currentIndex).children().addClass('active').trigger('click');

            //set states for control arrows
            self.rightControl.removeAttr('disabled');
            if(self.currentIndex === 0) {
                self.leftControl.attr('disabled','true');
            }            

            if(self.currentIndex < (self.itemsPerPage - 2) && self.itemsCount > self.itemsPerPage) {
                //show firstNumbers
                self._firstNumbers();
            } else if(self.currentIndex > self.itemsCount - (self.itemsPerPage - 1) && self.itemsCount > self.itemsPerPage) {
                //show lastNumbers 
                self._lastNumbers();
            } else if(self.itemsCount > self.itemsPerPage) {
                //show middleNumbers 
                self._middleNumbers();
            }
        }
    });
}

$('.paginationModule').each(function(index, element) {
    new CreatePagination(element);
});

$(document).on("change", ".dashboardWidgetPayments .paginationModule .paginationPicker", function() {
    var pagesNumber = 1;
    var totalItems = $(".dashboardWidgetPayments .tableModule-body tr").length;
    var itemsPerPage = $(this).val();
    $('.paginationModule-link.payment').removeClass("active");
    $('.paginationModule-link.payment:first').addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/payments/page/" + pagesNumber + "/" + itemsPerPage,
        cache : false,
        success : function(response) {
            var paymentsTable = $("#paymentsTable");
            var isAwaitingPayment = false;
            paymentsTable.empty();
            var paymentsList = JSON.parse(response);
            for (var key in paymentsList) {
                if(paymentsList.hasOwnProperty(key)) {
                    var template = $(".paymentTemplateWrapper").find("tbody").clone();
                    template.find(".paymentDate").html(paymentsList[key].sagiaPaymentDate.dateFormatted);
                    template.find(".dashboardWidgetPayments-title").find("a").attr("href", ACC.config.encodedContextPath + "/payment/details/" + paymentsList[key].serviceId);
                    template.find(".paymentName").html(paymentsList[key].name);
                    template.find(".dashboardWidgetPayments-eid").html(paymentsList[key].serviceId); //property needs to be set here
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription == 'Paid') {
                    	template.find(".statusDescription").html(paymentsList[key].hybrisStatusDescription);
                    }else{
                    	template.find(".statusDescription").html(paymentsList[key].statusDescription);
                    }
//                    var $icon = $(".statusIcons ." + paymentsList[key].statusIcon).html();
//                    template.find(".dashboardWidgetPayments-status-icon").html($icon);
                    template.find(".dashboardWidgetPayments-status-icon").html(SAGIA.statusUpdate.initServiceRequest(paymentsList[key].statusDescriptionKey));
                    template.find(".dashboardWidgetPayments-amount").html(paymentsList[key].amount ? paymentsList[key].amount.toLocaleString('en-US', {maximumFractionDigits: 2}) : 0);
                    template.find(".dashboardWidgetPayments-currency").html(paymentsList[key].currency);
                    //template.find(".dashboardWidgetPayments-pay").find("a").attr("onclick", "SAGIA.payment.requestCreditBillPayment("+paymentsList[key].serviceId+",'"+paymentsList[key].name+"',"+paymentsList[key].amount+",'"+paymentsList[key].currency+"')");
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription != 'Paid') {
                    	template.find(".dashboardWidgetPayments-pay").find("a").text(getI18nText("payment.pay"));
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("class","print-not-link payment-link");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("style","float: left;");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("onclick", "SAGIA.payment.requestCreditBillPayment("+paymentsList[key].serviceId+",'"+paymentsList[key].name+"',"+paymentsList[key].amount+",'"+paymentsList[key].currency+"')");
                    	isAwaitingPayment = true;
                    }
                    paymentsTable.append(template.html());
                }
            }
            var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment active">1</a></div>';
            var pagesNumbers = totalItems / itemsPerPage;
            for (var i = 2; i <= pagesNumbers; i++) {
                paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment">' + i + '</a></div>';
            }
            $(".dashboardWidgetPayments .paginationModule-items").empty().append(paginationHtml);
            new CreatePagination($(".dashboardWidgetPayments .paginationModule"));
            
            if (isAwaitingPayment) {
                $("#awaitingPaymentDiv").css("display", "block");
            }else{
            	$("#awaitingPaymentDiv").css("display", "none");
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
$(document).on('click', '.paginationModule-link.payment', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.payment').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/payments/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var paymentsTable = $("#paymentsTable");
            var isAwaitingPayment = false;
            paymentsTable.empty();
            var paymentsList = JSON.parse(response);
            for (var key in paymentsList) {
                if(paymentsList.hasOwnProperty(key)) {
                    var template = $(".paymentTemplateWrapper").find("tbody").clone();
                    template.find(".paymentDate").html(paymentsList[key].sagiaPaymentDate.dateFormatted);
                    template.find(".dashboardWidgetPayments-title").find("a").attr("href", ACC.config.encodedContextPath + "/payment/details/" + paymentsList[key].serviceId);
                    template.find(".paymentName").html(paymentsList[key].name);
                    template.find(".dashboardWidgetPayments-eid").html(paymentsList[key].serviceId); //property needs to be set here
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription == 'Paid') {
                    	template.find(".statusDescription").html(paymentsList[key].hybrisStatusDescription);
                    }else{
                    	template.find(".statusDescription").html(paymentsList[key].statusDescription);
                    }
                    //template.find(".statusDescription").html(paymentsList[key].statusDescription);
//                    var $icon = $(".statusIcons ." + paymentsList[key].statusIcon).html();
//                    template.find(".dashboardWidgetPayments-status-icon").html($icon);
                    template.find(".dashboardWidgetPayments-status-icon").html(SAGIA.statusUpdate.initServiceRequest(paymentsList[key].statusDescriptionKey));
                    template.find(".dashboardWidgetPayments-amount").html(paymentsList[key].amount ? paymentsList[key].amount.toLocaleString('en-US', {maximumFractionDigits: 2}) : 0);
                    template.find(".dashboardWidgetPayments-currency").html(paymentsList[key].currency);
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription != 'Paid') {
                    	template.find(".dashboardWidgetPayments-pay").find("a").text(getI18nText("payment.pay"));
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("class","print-not-link  payment-link");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("style","float: left;");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("onclick", "SAGIA.payment.requestCreditBillPayment("+paymentsList[key].serviceId+",'"+paymentsList[key].name+"',"+paymentsList[key].amount+",'"+paymentsList[key].currency+"')");
                    	isAwaitingPayment = true;
                    }
                    paymentsTable.append(template.html());
                }
            }
            if (isAwaitingPayment) {
                $("#awaitingPaymentDiv").css("display", "block");
            }else{
            	$("#awaitingPaymentDiv").css("display", "none");
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

function sortPayments() {
    var paymentSort = $("#paymentSort");
    var sortParameter = paymentSort.val().split("_")[0];
    var order = paymentSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.payment');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    //$(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/payments/sort/" + sortParameter + "/order/" + order,
        cache : false,
        success : function(response) {
            var paymentsTable = $("#paymentsTable");
            var isAwaitingPayment = false;
            paymentsTable.empty();
            var paymentsList = JSON.parse(response);
            for (var key in paymentsList) {
                if(paymentsList.hasOwnProperty(key)) {
                    var template = $(".paymentTemplateWrapper").find("tbody").clone();
                    template.find(".paymentDate").html(paymentsList[key].sagiaPaymentDate.dateFormatted);
                    template.find(".dashboardWidgetPayments-title").find("a").attr("href", ACC.config.encodedContextPath + "/payment/details/" + paymentsList[key].serviceId);
                    template.find(".paymentName").html(paymentsList[key].name);
                    template.find(".dashboardWidgetPayments-eid").html(paymentsList[key].serviceId); //property needs to be set here
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription == 'Paid') {
                    	template.find(".statusDescription").html(paymentsList[key].hybrisStatusDescription);
                    }else{
                    	template.find(".statusDescription").html(paymentsList[key].statusDescription);
                    }
                    //template.find(".statusDescription").html(paymentsList[key].statusDescription);
                    template.find(".dashboardWidgetPayments-status-icon").html(SAGIA.statusUpdate.initServiceRequest(paymentsList[key].statusDescription));
                    template.find(".dashboardWidgetPayments-amount").html(paymentsList[key].amount ? paymentsList[key].amount.toLocaleString('en-US', {maximumFractionDigits: 2}) : 0);
                    template.find(".dashboardWidgetPayments-currency").html(paymentsList[key].currency);
                    if (paymentsList[key].status == 'E0003' && paymentsList[key].hybrisStatusDescription != 'Paid') {
                    	template.find(".dashboardWidgetPayments-pay").find("a").text(getI18nText("payment.pay"));
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("class","print-not-link payment-link");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("style","float: left;");
                    	template.find(".dashboardWidgetPayments-pay").find("a").attr("onclick", "SAGIA.payment.requestCreditBillPayment("+paymentsList[key].serviceId+",'"+paymentsList[key].name+"',"+paymentsList[key].amount+",'"+paymentsList[key].currency+"')");
                    	isAwaitingPayment = true;
                    }
                    paymentsTable.append(template.html());
                }
            }
            
            if (isAwaitingPayment) {
                $("#awaitingPaymentDiv").css("display", "block");
            }else{
            	$("#awaitingPaymentDiv").css("display", "none");
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

$(document).on("change", "#ticketsSection .paginationModule .paginationPicker", function() {
    var pagesNumber = 1;
    var totalItems = $(".dashboardWidgetTickets .tableModule-body tr").length;
    var itemsPerPage = $(this).val();
    $('.paginationModule-link.ticket').removeClass("active");
    $('.paginationModule-link.ticket:first').addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/tickets/page/" + pagesNumber + "/" + itemsPerPage,
        cache : false,
        success : function(response) {
            var ticketsTable = $("#ticketsTable");
            ticketsTable.empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".profileTicketsTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".dashboardWidgetTickets-status-open").html(ticketsList[key].status);
                    template.find(".dashboardWidgetTickets-status-open").addClass(function() {
                        switch (ticketsList[key].statusKey) {
                            case 'Resolved':
                                return 'dashboardWidgetTickets-status_code01';
                            case 'Open':
                            case 'In Process':
                                return 'dashboardWidgetTickets-status_code02';
                            case 'Closed':
                            default:
                                return 'dashboardWidgetTickets-status_code03';
                        }
                    });
                    template.find(".dashboardWidgetTickets-btn").attr("data-complaint-Id", ticketsList[key].ticketNumber);
                    ticketsTable.append(template.html());
                }
            }
            var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket active">1</a></div>';
            var pagesNumbers = totalItems / itemsPerPage;
            for (var i = 2; i <= pagesNumbers; i++) {
                paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket">' + i + '</a></div>';
            }
            $("#ticketsSection .paginationModule-items").empty().append(paginationHtml);
            new CreatePagination($(".dashboardWidgetTickets .paginationModule"));
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
$(document).on('click', '.paginationModule-link.ticket', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.ticket').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/tickets/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var ticketsTable = $("#ticketsTable");
            ticketsTable.empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".ticketTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".dashboardWidgetTickets-status-open").html(ticketsList[key].status);
                    template.find(".dashboardWidgetTickets-status-open").addClass(function() {
                        switch (ticketsList[key].statusKey) {
                            case 'Resolved':
                                return 'dashboardWidgetTickets-status_code01';
                            case 'Open':
                            case 'In Process':
                                return 'dashboardWidgetTickets-status_code02';
                            case 'Closed':
                            default:
                                return 'dashboardWidgetTickets-status_code03';
                        }
                    });
                    template.find(".dashboardWidgetTickets-btn").attr("data-complaint-Id", ticketsList[key].ticketNumber);
                    ticketsTable.append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
$(document).on('click', '.paginationModule-link.ticket', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.ticket').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/tickets/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var ticketsTable = $("#ticketsTable");
            ticketsTable.empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".ticketTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".dashboardWidgetTickets-status-open").html(ticketsList[key].status);
                    template.find(".dashboardWidgetTickets-status-open").addClass(function() {
                        switch (ticketsList[key].statusKey) {
                            case 'Resolved':
                                return 'dashboardWidgetTickets-status_code01';
                            case 'Open':
                            case 'In Process':
                                return 'dashboardWidgetTickets-status_code02';
                            case 'Closed':
                            default: 
                                return 'dashboardWidgetTickets-status_code03';
                        }
                    });
                    template.find(".dashboardWidgetTickets-btn").attr("data-complaint-Id", ticketsList[key].ticketNumber);
                    ticketsTable.append(template.html());
                }
            }  
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

$(document).on('click', '.paginationModule-link.profile-ticket', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.profile-ticket').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : "/pagination/tickets/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var profileTicketsTable = $("#profileTicketsTable");
            profileTicketsTable.find("tbody").empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".profileTicketsTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".enquiryType").html(ticketsList[key].enquiryType);
                    template.find(".status").html(ticketsList[key].status);
                    template.find(".details").find("a").attr("data-complaint-id", ticketsList[key].ticketNumber);
                    profileTicketsTable.find("tbody").append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

$(document).on('click', '.paginationModule-link.profile-legalConsultation', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.profile-legalConsultation').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : "/pagination/legalConsultations/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var profileLegalConsultationsTable = $("#profileLegalConsultationsTable");
            profileLegalConsultationsTable.find("tbody").empty();
            var legalConsultations = JSON.parse(response);
            for (var key in legalConsultations) {
                if(legalConsultations.hasOwnProperty(key)) {
                    var template = $(".profileLegalConsultationsTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(legalConsultations[key].srCrDate.dateFormatted);
                    template.find(".id").html(legalConsultations[key].srId);
                    template.find(".status").html(legalConsultations[key].srStDesc);
                    template.find(".details").find("a").attr("data-legalconsultation-id", legalConsultations[key].srId);
                    profileLegalConsultationsTable.find("tbody").append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

function sortLegalConsultations() {
    var legalConsultationsSort = $("#legalConsultationsSort");
    var sortParameter = legalConsultationsSort.val().split("_")[0];
    var order = legalConsultationsSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.profile-legalConsultation');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    //$(this).addClass("active");
    $.ajax({
        type : "GET",
        url : "/pagination/legalConsultations/sort/"+sortParameter+"/order/"+order,
        cache : false,
        success : function(response) {
            var profileLegalConsultationsTable = $("#profileLegalConsultationsTable");
            profileLegalConsultationsTable.find("tbody").empty();
            var legalConsultations = JSON.parse(response);
            for (var key in legalConsultations) {
                if(legalConsultations.hasOwnProperty(key)) {
                    var template = $(".profileLegalConsultationsTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(legalConsultations[key].srCrDate.dateFormatted);
                    template.find(".id").html(legalConsultations[key].srId);
                    template.find(".status").html(legalConsultations[key].srStDesc);
                    template.find(".details").find("a").attr("data-legalConsultation-id", legalConsultations[key].srId);
                    profileLegalConsultationsTable.find("tbody").append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

function sortProfileTickets() {
    var profileTicketSort = $("#profileTicketSort");
    var sortParameter = profileTicketSort.val().split("_")[0];
    var order = profileTicketSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.profile-ticket');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    //$(this).addClass("active");
    $.ajax({
        type : "GET",
        url : "/pagination/tickets/sort/" + sortParameter + "/order/" + order,
        cache : false,
        success : function(response) {
            var profileTicketsTable = $("#profileTicketsTable");
            profileTicketsTable.find("tbody").empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".profileTicketsTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".enquiryType").html(ticketsList[key].enquiryType);
                    template.find(".status").html(ticketsList[key].status);
                    template.find(".details").find("a").attr("data-complaint-id", ticketsList[key].ticketNumber);
                    profileTicketsTable.find("tbody").append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

function sortTickets() {
    var ticketSort = $("#ticketSort");
    var sortParameter = ticketSort.val().split("_")[0];
    var order = ticketSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.ticket');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    //$(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/tickets/sort/" + sortParameter + "/order/" + order,
        cache : false,
        success : function(response) {
            var ticketsTable = $("#ticketsTable");
            ticketsTable.empty();
            var ticketsList = JSON.parse(response);
            for (var key in ticketsList) {
                if(ticketsList.hasOwnProperty(key)) {
                    var template = $(".ticketTemplateWrapper").find("tbody").clone();
                    template.find(".lastUpdate").html(ticketsList[key].lastUpdateData.dateFormatted);
                    template.find(".ticketNumber").html(ticketsList[key].ticketNumber);
                    template.find(".dashboardWidgetTickets-status-open").html(ticketsList[key].status);
                    template.find(".dashboardWidgetTickets-status-open").addClass(function() {
                        switch (ticketsList[key].status) {
                            case 'Resolved':
                                return 'dashboardWidgetTickets-status_code01';
                            case 'Open':
                            case 'In Process':
                                return 'dashboardWidgetTickets-status_code02';
                            case 'Closed':
                            default: 
                                return 'dashboardWidgetTickets-status_code03';
                        }
                    });
                    template.find(".dashboardWidgetTickets-btn").attr("data-complaint-Id", ticketsList[key].ticketNumber);
                    ticketsTable.append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

$(document).on("change", ".dashboardWidgetRequests .paginationModule .paginationPicker", function() {
    var pagesNumber = 1;
    var totalItems = $(".dashboardWidgetRequests .tableModule-body tr").length;
    var itemsPerPage = $(this).val();
    $('.paginationModule-link.service').removeClass("active");
    $('.paginationModule-link.service:first').addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/services/page/" + pagesNumber + "/" + itemsPerPage,
        cache : false,
        success : function(response) {
            var serviceRequestsTable = $("#serviceRequestsTable");
            serviceRequestsTable.empty();
            var servicesList = JSON.parse(response);

            for (var key in servicesList) {
                if(servicesList.hasOwnProperty(key)) {
                    var template = $(".serviceTemplateWrapper").find("tbody").clone();

                    if(servicesList[key].urlPath){
                        template.find("#serviceRequestLink").attr('href',ACC.config.encodedContextPath + '/'+servicesList[key].urlPath);
                    }
                    else{
                        $('<div class="tableModule-headline" style="cursor: not-allowed;"></div>').insertAfter(template.find('#serviceRequestLink'));
                        template.find("#serviceRequestLink").remove();
                    }

                    if(!isEmpty(servicesList[key].categoryLevelFour)){
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelFour);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelThree);
                        template.find("#description1").html(servicesList[key].categoryLevelTwo);
                        template.find("#description2").html(servicesList[key].categoryLevelOne);
                        template.find("#description3").html(servicesList[key].serviceType);

                    }
                    else if(!isEmpty(servicesList[key].categoryLevelThree)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelThree);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelTwo);
                        template.find("#description1").html(servicesList[key].categoryLevelOne);
                        template.find("#description2").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelTwo)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelTwo);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelOne)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].serviceType)){
                        template.find(".tableModule-headline").html(servicesList[key].serviceType);
                    }

                    template.find(".requestNumber").html(servicesList[key].requestNumber);
                    template.find(".requestDate").html(servicesList[key].requestDate.dateFormatted);
                    template.find(".serviceStatus").html(servicesList[key].statusDescription);
                    template.find(".dashboardWidgetRequests-status-icon").html(SAGIA.statusUpdate.initServiceRequest(servicesList[key].status));
                    if(servicesList[key].status==="completed") {
                        template.find("#printLink").attr('href', ACC.config.encodedContextPath + "/dashboard/serviceRequests/print/" + servicesList[key].requestNumber);
                    }
                    else{
                        template.find("#printLink").html('');
                    }
                    serviceRequestsTable.append(template.html());
                }
            }
            var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket active">1</a></div>';
            var pagesNumbers = totalItems / itemsPerPage;
            for (var i = 2; i <= pagesNumbers; i++) {
                paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket">' + i + '</a></div>';
            }
            $(".dashboardWidgetRequests .paginationModule-items").empty().append(paginationHtml);
            new CreatePagination($(".dashboardWidgetRequests .paginationModule"));
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
$(document).on('click', '.paginationModule-link.service', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.service').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/services/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var serviceRequestsTable = $("#serviceRequestsTable");
            serviceRequestsTable.empty();
            var servicesList = JSON.parse(response);

            for (var key in servicesList) {
                if(servicesList.hasOwnProperty(key)) {
                    var template = $(".serviceTemplateWrapper").find("tbody").clone();

                    if(servicesList[key].urlPath){
                        template.find("#serviceRequestLink").attr('href',ACC.config.encodedContextPath + '/'+servicesList[key].urlPath);
                    }
                    else{
                        $('<div class="tableModule-headline" style="cursor: not-allowed;"></div>').insertAfter(template.find('#serviceRequestLink'));
                        template.find("#serviceRequestLink").remove();
                    }

                    if(!isEmpty(servicesList[key].categoryLevelFour)){
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelFour);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelThree);
                        template.find("#description1").html(servicesList[key].categoryLevelTwo);
                        template.find("#description2").html(servicesList[key].categoryLevelOne);
                        template.find("#description3").html(servicesList[key].serviceType);

                    }
                    else if(!isEmpty(servicesList[key].categoryLevelThree)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelThree);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelTwo);
                        template.find("#description1").html(servicesList[key].categoryLevelOne);
                        template.find("#description2").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelTwo)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelTwo);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelOne)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].serviceType)){
                        template.find(".tableModule-headline").html(servicesList[key].serviceType);
                    }

                    template.find(".requestNumber").html(servicesList[key].requestNumber);
                    template.find(".requestDate").html(servicesList[key].requestDate.dateFormatted);
                    template.find(".serviceStatus").html(servicesList[key].statusDescription);
                    template.find(".dashboardWidgetRequests-status-icon").html(SAGIA.statusUpdate.initServiceRequest(servicesList[key].status));
                    if(servicesList[key].status==="completed") {
                        template.find("#printLink").attr('href', ACC.config.encodedContextPath + "/dashboard/serviceRequests/print/" + servicesList[key].requestNumber);
                    }
                    else{
                        template.find("#printLink").html('');
                    }
                    serviceRequestsTable.append(template.html());                    
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

function isEmpty(value){
    return value == undefined || value == null || value === '';
}

function sortServices() {
    var serviceSort = $("#serviceSort");
    var sortParameter = serviceSort.val().split("_")[0];
    var order = serviceSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.service');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/services/sort/" + sortParameter + "/order/" + order,
        cache : false,
        success : function(response) {
            var serviceRequestsTable = $("#serviceRequestsTable");
            serviceRequestsTable.empty();
            var servicesList = JSON.parse(response);
            for (var key in servicesList) {
                if(servicesList.hasOwnProperty(key)) {
                    var template = $(".serviceTemplateWrapper").find("tbody").clone();

                    if(!isEmpty(servicesList[key].categoryLevelFour)){
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelFour);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelThree);
                        template.find("#description1").html(servicesList[key].categoryLevelTwo);
                        template.find("#description2").html(servicesList[key].categoryLevelOne);
                        template.find("#description3").html(servicesList[key].serviceType);

                    }
                    else if(!isEmpty(servicesList[key].categoryLevelThree)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelThree);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelTwo);
                        template.find("#description1").html(servicesList[key].categoryLevelOne);
                        template.find("#description2").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelTwo)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelTwo);
                        template.find(".tableModule-subHeadline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].categoryLevelOne)) {
                        template.find(".tableModule-headline").html(servicesList[key].categoryLevelOne);
                        template.find("#description1").html(servicesList[key].serviceType);
                    }
                    else if(!isEmpty(servicesList[key].serviceType)){
                        template.find(".tableModule-headline").html(servicesList[key].serviceType);
                    }

                    template.find(".requestNumber").html(servicesList[key].requestNumber);
                    template.find(".requestDate").html(servicesList[key].requestDate.dateFormatted);
                    template.find(".serviceStatus").html(servicesList[key].statusDescription);
                    template.find(".dashboardWidgetRequests-status-icon").html(SAGIA.statusUpdate.initServiceRequest(servicesList[key].status));
                    if(servicesList[key].status==="completed") {
                        template.find("#printLink").attr('href', ACC.config.encodedContextPath + "/dashboard/serviceRequests/print/" + servicesList[key].requestNumber);
                    }
                    else{
                        template.find("#printLink").html('');
                    }
                    serviceRequestsTable.append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

// Appointments
$(document).on('click', '.paginationModule-link.appointment', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.appointment').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/appointments/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var appointmentList = $("#appointmentList").find("tbody");
            appointmentList.empty();
            var locale = ACC.config.encodedContextPath.slice(-2);
            var appointmentsList = JSON.parse(response);
            for (var key in appointmentsList) {
                if (appointmentsList.hasOwnProperty(key)) {
                    var template = $(".appointmentTemplateWrapper").find("tbody").clone();
                    if (appointmentsList[key].hasOwnProperty('date')) {
                        var date = new Date(appointmentsList[key].date.date.year, appointmentsList[key].date.date.month, appointmentsList[key].date.date.day);
                        template.find(".appointmentList-date").attr("data-date", appointmentsList[key].date.date.year + "-" + appointmentsList[key].date.date.month + "-" + appointmentsList[key].date.date.day);
                        template.find(".appointmentList-date-month").html(date.toLocaleString(locale, {month: "short"}));
                        template.find(".appointmentList-date-day").html(appointmentsList[key].date.date.day);
                        template.find(".appointmentList-date-time").html(function(){
                            var hour = appointmentsList[key].timeStart.hour.toString().length < 2 ? '0' + appointmentsList[key].timeStart.hour : appointmentsList[key].timeStart.hour;
                        
                            var minute = appointmentsList[key].timeStart.minute.toString().length < 2 ? '0' + appointmentsList[key].timeStart.minute : appointmentsList[key].timeStart.minute;
                        
                            return (hour + ':' + minute);                            
                        });
                    }
                    template.find(".displayName").attr("data-displayname", appointmentsList[key].service1Description);
                    template.find(".appointmentList-title").html(appointmentsList[key].service1Description);
                    template.find(".appointmentList-subTitle").html(appointmentsList[key].branchDescription);
                    template.find(".appointmentList-label.branch").html(appointmentsList[key].branchDescription);
                    template.find(".appointmentList-status").attr("data-type", appointmentsList[key].serviceType1Description);
                    template.find(".appointmentList-status").attr("data-state", appointmentsList[key].statusDescription);
                    template.find(".appointmentList-status-value").find("span").html(appointmentsList[key].statusDescription);
                    if(appointmentsList[key].status == 'E0001'){
                        /*viewLink.removeAttr("href");
                        viewLink.removeAttr("class");
                        viewLink.removeAttr("svg");
                    	template.find(".appointmentList-view").find("span").attr("style", "color: red;").html("Pending For Approval");*/
                    	template.find(".appointmentList-view").find("a").replaceWith("<span style='color: red;'>"+getI18nText("appointment.pending.approval")+"</span>");
                    	
                    }else if(appointmentsList[key].status == 'E0012'){
                    	var viewLink = template.find(".appointmentList-view").find("a");
                        viewLink.attr("href", viewLink.attr("href") + appointmentsList[key].appointmentID);
                        template.find(".appointmentList-status-value.appointmentList-icon_after")
                            .addClass("appointmentList-icon_status appointmentList-icon_status_" + appointmentsList[key].statusDescription.toLowerCase());
                    }
                    else{
                    	template.find(".appointmentList-view").find("a").replaceWith("<span style='color: green;'>"+appointmentsList[key].statusDescription.toLowerCase()+"</span>");
                    }
                    
                    appointmentList.append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});

function sortAppointments() {
    var appointmentSort = $("#appointmentSort");
    var sortParameter = appointmentSort.val().split("_")[0];
    var order = appointmentSort.val().split("_")[1];
    var paginationModule = $('.paginationModule-link.appointment');
    paginationModule.removeClass("active");
    paginationModule.first().addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/appointments/sort/" + sortParameter + "/order/" + order,
        cache : false,
        success : function(response) {
            var appointmentList = $("#appointmentList");
            appointmentList.empty();
            var locale = ACC.config.encodedContextPath.slice(-2);
            var appointmentsList = JSON.parse(response);
            for (var key in appointmentsList) {
                if(appointmentsList.hasOwnProperty(key)) {
                    var template = $(".appointmentTemplateWrapper").clone();

                    if (appointmentsList[key].hasOwnProperty('date')) {
                        var date = new Date(appointmentsList[key].date.date.year, appointmentsList[key].date.date.month, appointmentsList[key].date.date.day);
                        template.find(".appointmentList-date").attr("data-date", appointmentsList[key].date.date.year + "-" + appointmentsList[key].date.date.month + "-" + appointmentsList[key].date.date.day);
                        template.find(".appointmentList-date-month").html(date.toLocaleString(locale, {month: "short"}));
                        template.find(".appointmentList-date-day").html(appointmentsList[key].date.date.day);
                    }
                    template.find(".displayName").attr("data-displayname", appointmentsList[key].service1Description);
                    template.find(".appointmentList-title").html(appointmentsList[key].service1Description);
                    template.find(".appointmentList-subTitle").html(appointmentsList[key].branchDescription);
                    template.find(".appointmentList-label.branch").html(appointmentsList[key].branchDescription);
                    template.find(".appointmentList-status").attr("data-type", appointmentsList[key].serviceType1Description);
                    template.find(".appointmentList-status").attr("data-state", appointmentsList[key].statusDescription);
                    template.find(".appointmentList-status-value").find("span").html(appointmentsList[key].statusDescription);
                    if(appointmentsList[key].status == 'E0001'){
                    	template.find(".appointmentList-view").find("a").replaceWith("<span style='color: red;'>"+getI18nText("appointment.pending.approval")+"</span>");
                    }else if(appointmentsList[key].status == 'E0012'){
                    	var viewLink = template.find(".appointmentList-view").find("a");
                        viewLink.attr("href", viewLink.attr("href") + appointmentsList[key].appointmentID);
                        template.find(".appointmentList-status-value.appointmentList-icon_after")
                            .addClass("appointmentList-icon_status appointmentList-icon_status_" + appointmentsList[key].statusDescription.toLowerCase());
                    }
                    else{
                    	template.find(".appointmentList-view").find("a").replaceWith("<span style='color: green;'>"+appointmentsList[key].statusDescription.toLowerCase()+"</span>");
                    }
                    
                    appointmentList.append(template.html())
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
}

$(document).on('click', '.paginationModule-link.branch', function () {
    var pagesNumber = $(this).html();
    $('.paginationModule-link.branch').removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type : "GET",
        url : ACC.config.encodedContextPath + "/pagination/branches/page/" + pagesNumber,
        cache : false,
        success : function(response) {
            var branchesTable = $("#branchesTable");
            branchesTable.empty();
            var branchesList = JSON.parse(response);
            for (var key in branchesList) {
                if(branchesList.hasOwnProperty(key)) {
                    var template = $(".branchTemplateWrapper").find("tbody").clone();
                    template.find(".branchType").html(branchesList[key].type);
                    template.find(".branchName").html(branchesList[key].name);
                    template.find(".branchCity").html(branchesList[key].city);
                    branchesTable.append(template.html());
                }
            }
        },
        error : function() {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText("general.errorwhilerequest"));
            errorModal.modal('show');
        }
    });
});
