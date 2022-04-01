var SAGIA = SAGIA || {};
SAGIA.dashboardWithLicense = {
    _autoload: [
        "editDashboardComponents",
        "repositionDashboardComponents",
        "initMobile"
    ],

    bar: function (dataArray) {
        document.getElementById("js-dashboardWidgetCharts-chart") !== null &&
        new Chart(document.getElementById("js-dashboardWidgetCharts-chart"), {
            type: 'bar',
            options: {
                maintainAspectRatio: false,
                legend: {
                    display: false
                },
                tooltips: {
                    enabled: false
                },
                scales: {
                    xAxes: [{
                        gridLines: {
                            display: false,
                            drawBorder: false
                        }
                    }],
                    yAxes: [{
                        gridLines: {
                            drawBorder: false,
                            color: "#c2c9d1",
                            lineWidth: 0.6
                        },
                        ticks: {
                            beginAtZero: true
                        },
                        scaleLabel: {
                            display: true,
                            labelString: 'Salary (SAR)'
                        }
                    }]
                }
            },
            data: {
                labels: ['Saudi-Average', 'Expat Average'],
                datasets: [{
                    label: 'My First dataset',
                    data: dataArray,
                    fill: false,
                    lineTension: 0,
                    backgroundColor: ["#7acc61", "#e6e6ed"],
                    hoverBackgroundColor: ["#7acc61", "#e6e6ed"]
                }]
            }
        });
    },
    initMobile: function () {
        document.addEventListener("touchstart", touchHandler, true);
        document.addEventListener("touchmove", touchHandler, true);
        document.addEventListener("touchend", touchHandler, true);
        document.addEventListener("touchcancel", touchHandler, true);
    },
    repositionDashboardComponents: function () {
        window.variableMappingIndexes = {
            "myLicense": "settingsCheckbox1",
            "salaryAndEmployment": "settingsCheckbox2",
            "servicesRequest": "settingsCheckbox3",
            "payments": "settingsCheckbox5",
            "yourTickets": "settingsCheckbox6",
            "support": "settingsCheckbox4",
            "savedDrafts": "settingsCheckbox7"
        };

        window.variableSectionsMap = {};
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: ACC.config.encodedContextPath + "/dashboard/sections",
            success: function (data) {
                $.each(data, function (key) {
                    window.variableSectionsMap[key] = [];
                    window.variableSectionsMap[key][0] = Number(data[key][0]);
                    window.variableSectionsMap[key][1] = JSON.parse(data[key][1]);
                });

                var li = $("li.js-component");
                for (var i = 0; i < li.length; i++) {
                    var $elem = li[i].firstElementChild;
                    var $elemId = $elem.id;
                    if ($elemId === "") {
                        $elem = li[i].firstElementChild.firstElementChild;
                        $elemId = $elem.id;
                    }
                    if (window.variableSectionsMap[$elemId] && window.variableSectionsMap[$elemId] !== null) {
                        var $newPos = window.variableSectionsMap[$elemId][0];
                        var $visible = window.variableSectionsMap[$elemId][1];

                        if ($visible) {
                            li.show();
                        }
                        if ($newPos !== i && $visible === true) {
                            if (window.variableEditable) {
                                if ($elemId in window.variableMappingIndexes) {
                                    document.getElementById(window.variableMappingIndexes[$elemId]).checked = true;
                                }
                            }
                            li[$newPos].appendChild(li[i].firstElementChild);
                        } else if ($newPos !== i && $visible === false) {
                            if (window.variableEditable) {
                                if ($elemId in window.variableMappingIndexes) {
                                    document.getElementById(window.variableMappingIndexes[$elemId]).checked = false;
                                }
                                li[$newPos].appendChild(li[i].firstElementChild);
                            } else {
                                $(li[i].firstElementChild).hide();
                            }
                        } else if ($newPos === i && $visible === false) {
                            if (window.variableEditable) {
                                if ($elemId in window.variableMappingIndexes) {
                                    document.getElementById(window.variableMappingIndexes[$elemId]).checked = false;
                                }
                            } else {
                                $(li[i].firstElementChild).hide();
                            }
                        }
                    }
                }

                SAGIA.dashboardWithLicense.loadDashboardData();
                if(window.matchMedia("(max-width:640px)").matches){
                    var spinner = '<div id="sp1" class="inline-custom-spinner text-center">'
                    spinner += '<svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 72 72"><g transform="translate(-0.1)"><path d="M42.1,6a6,6,0,1,1-6-6A6.018,6.018,0,0,1,42.1,6Z" fill="#00a6be" opacity="0.8"></path><path d="M61.5,19A6.01,6.01,0,0,1,53,10.5a6.1,6.1,0,0,1,8.5,0A5.917,5.917,0,0,1,61.5,19Z" fill="#00a6be" opacity="0.9"></path><path d="M66.1,42a6,6,0,1,1,6-6A6.018,6.018,0,0,1,66.1,42Z" fill="#00a6be"></path><path d="M53,61.5a6.01,6.01,0,1,1,8.5,0A6.1,6.1,0,0,1,53,61.5Z" fill="#00a6be" opacity="0.3"></path><path d="M30,66a6,6,0,1,1,6,6A6.018,6.018,0,0,1,30,66Z" fill="#00a6be" opacity="0.4"></path><path d="M10.6,53a6.01,6.01,0,1,1,0,8.5A6.1,6.1,0,0,1,10.6,53Z" fill="#00a6be" opacity="0.5"></path><path d="M6.1,30a6,6,0,1,1-6,6,5.954,5.954,0,0,1,6-6Z" fill="#00a6be" opacity="0.6"></path><path d="M19.1,10.5A6.01,6.01,0,0,1,10.6,19a6.01,6.01,0,0,1,8.5-8.5Z" fill="#00a6be" opacity="0.7"></path></g></svg>'
                    spinner +='</div>'
                    $("#accordionDashboard").empty().append(spinner);
                }

                $( document ).ajaxStop(function() {
                    var element_id = $("body").hasClass("page-dashboard") ? "#tabsDasboard" :"#tabs";

                    var concat = '';
                    obj_tabs = $( element_id + " li" ).toArray();
                    obj_cont = $( ".dashboard-tabs .tab-content .tab-pane" ).toArray();
                    jQuery.each( obj_tabs, function( n, val )
                    {
                        concat += '<div id="' + n + '" class="panel panel-default">';
                        concat += '<div class="panel-heading  dashboardWidget-headline js-dashboardWidget-headline-icon text-upercase" role="tab" id="heading' + n + '">';
                        concat += '<h5 class="panel-title"><a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse' + n + '" aria-expanded="false" aria-controls="collapse' + n + '">' + val.innerText + '</a><h5>';
                        concat += '</div>';
                        concat += '<div id="collapse' + n + '" class="panel-collapse collapse heading' + n + '" role="tabpanel" aria-labelledby="heading' + n + '">';
                        concat += '<div class="panel-body">' + obj_cont[n].innerHTML + '</div>';
                        concat += '</div>';
                        concat += '</div>';
                    });
                    $("#accordionDashboard").empty().append(concat);
                    $("#accordionDashboard").find('.panel-collapse:first').addClass("in");
                    $("#accordionDashboard").find('.panel-title a').attr("aria-expanded","true");
                    $("#accordionDashboard").find('.panel-title a').removeClass("collapsed");
                    $(element_id).not('.services-container-tabcontent').hide();
                    $(".tab-content").not('.services-container-tabcontent').hide();

                    if(window.matchMedia("(max-width:640px)").matches){
                        $(element_id).hide();
                        $("#accordionDashboard").show();
                        $(".tab-content").not('.services-container-tabcontent').hide();
                    }
                    else{
                        $(element_id).show();
                        $("#accordionDashboard").hide();
                        $(".tab-content").not('.services-container-tabcontent').show();
                    }
                })
            },
            error: function (e) {
            }
        });
    },

    editDashboardComponents: function () {
        var map = {};
        var panelList = $('#draggableComponentsList');

        panelList.sortable({
            handle: '.drag',
            update: function () {
                $('.drag', panelList).each(function (index, elem) {
                    var $listItem = $(elem);
                    var $listId = $listItem.attr('id');

                    // Persist the new indices.
                    map[$listId] = [];
                    map[$listId][0] = index;

                    var $checkboxId = window.variableMappingIndexes[$listId];
                    var $checkboxElem = document.getElementById($checkboxId);
                    if ($checkboxElem !== null) {
                        map[$listId][1] = $checkboxElem.checked;
                    } else {
                        map[$listId][1] = true;
                    }
                });

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: ACC.config.encodedContextPath + "/dashboard-edit",
                    data: JSON.stringify(map),
                    success: function () {
                        window.variableSectionsMap = map;
                    },
                    error: function (e) {
                    }
                });
            }
        });

        $(document).on("change", ".dashboardWidget-setting-switch .form-control", function () {
            map = window.variableSectionsMap;
            if ($(this).is(':checked')) {
                $(this).closest('.dashboardWidgetListComponent').removeClass('dashboardWidgetListComponent_invisible');
            } else {
                $(this).closest('.dashboardWidgetListComponent').addClass('dashboardWidgetListComponent_invisible');
            }

            var elem = $(this).closest('div[class^="drag col"]');
            var $divItem = $(elem);
            var $divId = $divItem.attr('id');

            map[$divId][1] = $(this).is(':checked');

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: ACC.config.encodedContextPath + "/dashboard-edit",
                data: JSON.stringify(map),
                success: function () {
                    window.variableSectionsMap = map;
                },
                error: function (e) {
                }
            });
        });
    },
    displayLicenseRenewalModal: function(title, description, period){
        var confirmationModal = $('#confirmationModal');
        confirmationModal.find('.modal-title').text(title);
        confirmationModal.find('.modal-description').text(description);
        confirmationModal.modal('show');

        confirmationModal.find('.yesButton').on('click', function () {
            confirmationModal.modal('hide');
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/instant/" + period, {
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
                },
                cache: false,
                data: {},
                success: function (data) {
                    if (!data.valid) {
                        var errorModal = $('#errorResponseModal');
                        errorModal.find('.modal-description').text(getI18nText("text.account.renewlicense.error"));
                        errorModal.modal('show');
                    } else {
                        // var infoModal = $('#infoResponseModal');
                        // infoModal.find('.modal-description').text(getI18nText("text.account.renewlicense.success"));
                        // infoModal.modal('show');
                        $('#serviceId').val(data.entityCreatedId);
                        var infoModal = $('#requestSubmittedComment');
                        infoModal.find('.modal-description').text(getI18nText("special.services.wewillreviewmessage"));
                        infoModal.modal('show');
                        infoModal.on('hide.bs.modal', function () {
                            window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/renew";
                        });
                    }
                }
            });
        });

        confirmationModal.find('.noButton').on('click', function () {
            $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/renew/removePopupALR", {
                type: "GET",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
            });
        });
    },
    loadDashboardData: function () {
        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/licenseAndEmployee",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (licenseAndEmployee) {
                if (licenseAndEmployee) {
                    if(licenseAndEmployee.renewalIndicators) {
                         if(licenseAndEmployee.renewalIndicators.enforcePopup) {
                             SAGIA.dashboardWithLicense.displayLicenseRenewalModal(
                                 getI18nText("licence.renew.popup.title"),
                                 getI18nText("licence.renew.popup.description"),'A');
                         } else if(licenseAndEmployee.renewalIndicators.enforceRenewalForClearancePopup){
                             SAGIA.dashboardWithLicense.displayLicenseRenewalModal(
                                 getI18nText("licence.renew.clearance.popup.title"),
                                 getI18nText("licence.renew.clearence.popup.description"),'R');
                         }
                    }

                    if (licenseAndEmployee.license && licenseAndEmployee.license.profileCompanyData) {
                        $("#licenseEntityId").text(licenseAndEmployee.license.profileCompanyData.entityId);
                        $("#licenseCapital").text(licenseAndEmployee.license.profileCompanyData.capitalFormatted);
                        $("#licenseClassLevel").text(licenseAndEmployee.license.profileCompanyData.classLevel);
                        $("#licenseEntityStatus").text(licenseAndEmployee.license.profileCompanyData.entityStatus);
                        $("#licenseExpiryDate").text(licenseAndEmployee.license.expiryDate.dateFormatted);
                        $("#licenseDuration").text(licenseAndEmployee.license.duration);
                        $("#licenseType").text(licenseAndEmployee.license.type);
                        $("#licenseIsicSection").text(licenseAndEmployee.license.type);
                        $("#licenseYearlyFees").text(licenseAndEmployee.license.profileCompanyData.yearlyFeesFormatted);
                    }

                    if (licenseAndEmployee.license && licenseAndEmployee.license.branches) {
                        var branchesTable = $("#branchesTable").empty();
                        if(licenseAndEmployee.license.branches.length > 5)
                        $(".d-license-branch").show();
                        else
                        $(".d-license-branch").hide();
                        for (var indexBranches in licenseAndEmployee.license.branches) {
                            if (licenseAndEmployee.license.branches.hasOwnProperty(indexBranches)) {
                                var branch = licenseAndEmployee.license.branches[indexBranches];
                                var branchTemplate = $(".branchTemplateWrapper").find("tbody").clone();
                                branchTemplate.find(".branchType").html(branch.type);
                                branchTemplate.find(".branchName").html(branch.name);
                                branchTemplate.find(".branchCity").html(branch.city);
                                branchesTable.append(branchTemplate.html());
                            }
                        }
                    }
                    var branchesSection = $("#branchesPagesSection").empty();
                    branchesSection.append('<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link branch active">1</a></div>');
                    if (licenseAndEmployee.BranchesPagesNumber) {
                        for (var i = 2; i <= licenseAndEmployee.BranchesPagesNumber; i++) {
                            branchesSection.append('<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link branch">' + i + '</a></div>');
                        }
                    }

                    if (licenseAndEmployee.license && licenseAndEmployee.license.shareholdersData) {
                        var shareholdersTable = $("#shareholdersTable").empty();
                        for (var indexShareholders in licenseAndEmployee.license.shareholdersData) {
                            if (licenseAndEmployee.license.shareholdersData.hasOwnProperty(indexShareholders)) {
                                var shareholder = licenseAndEmployee.license.shareholdersData[indexShareholders];
                                var templateShareholder = $(".shareholderTemplateWrapper").find("tbody").clone();
                                templateShareholder.find(".shareholderName").html(shareholder.name);
                                var shareholderType = getI18nText("text.account.profile.license.shareholders.type." + shareholder.type);
                                templateShareholder.find(".shareholderType").html(shareholderType ? shareholderType : shareholder.type);
                                templateShareholder.find(".shareholderPercentage").html(shareholder.sharesPercentage);
                                templateShareholder.find(".shareholderNationality").html(shareholder.nationality);
                                templateShareholder.find(".shareholderLegalStatus").html(shareholder.legalStatus);
                                if (shareholder.sharesPercentage > 0) {
                                    shareholdersTable.append(templateShareholder.html());
                                }
                            }
                        }

                        var shareholder1 = licenseAndEmployee.license.shareholdersData[0];
                        if (shareholder1) {
                            $("#supportShareholder1Name").text(shareholder1.name);
                            $("#supportShareholder1SharesPercentage").text(shareholder1.sharesPercentage);
                            $("#supportShareholder1Nationality").text(shareholder1.nationality);
                        }
                        if (licenseAndEmployee.license.shareholdersData.length > 1) {
                            var shareholder2 = licenseAndEmployee.license.shareholdersData[1];
                            if (shareholder2) {
                                $("#supportShareholder2Name").text(shareholder2.name);
                                $("#supportShareholder2SharesPercentage").text(shareholder2.sharesPercentage);
                                $("#supportShareholder2Nationality").text(shareholder2.nationality);
                            }
                        }
                    }

                    if (licenseAndEmployee.license && licenseAndEmployee.license.contactPerson) {
                        var contactPersonTable = $("#contactPersonTable").empty();
                        for (var indexContactPerson in licenseAndEmployee.license.contactPerson) {
                            if (licenseAndEmployee.license.contactPerson.hasOwnProperty(indexContactPerson)) {
                                var contactPerson = licenseAndEmployee.license.contactPerson[indexContactPerson];
                                var templateContactPerson = $(".contactPersonTemplateWrapper").find("tbody").clone();
                                templateContactPerson.find(".contactPersonName").html(contactPerson.firstName + ' ' + contactPerson.lastName);
                                templateContactPerson.find(".contactPersonMobileNumber").html(contactPerson.mobileNumber);
                                templateContactPerson.find(".contactPersonEmail").html(contactPerson.email);
                                templateContactPerson.find(".contactPersonNationalId").html(contactPerson.nationalId);
                                contactPersonTable.append(templateContactPerson.html());
                            }
                        }
                    }

                    if (licenseAndEmployee.license && licenseAndEmployee.license.generalManager) {
                        var generalManager = licenseAndEmployee.license.generalManager;
                        $("#generalManagerName").text(generalManager.firstName + ' ' + generalManager.lastName);
                        $("#generalManagerMobileNumber").text(generalManager.mobileNumber);
                        $("#generalManagerEmail").text(generalManager.email);
                    }

                    if (licenseAndEmployee.employment) {
                        $("#employmentOverallAverage").text(licenseAndEmployee.employment.overallAverage);
                        $("#employmentSaudiAverage").text(licenseAndEmployee.employment.saudiAverage);
                        $("#employmentExpatAverage").text(licenseAndEmployee.employment.expatAverage);
                        $("#employmentNoOfSaudiEmployees").text(licenseAndEmployee.employment.noOfSaudiEmployees);
                        $("#employmentTotalNoOfEmployees").text(licenseAndEmployee.employment.totalNoOfEmployees);
                        $("#employmentNoOfExpatEmployees").text(licenseAndEmployee.employment.noOfExpatEmployees);
                        $("#employmentNoOfMale").text(licenseAndEmployee.employment.noOfMale);
                        $("#employmentTotalNoOfGender").text(licenseAndEmployee.employment.totalNoGender);
                        $("#employmentNoOfFemales").text(licenseAndEmployee.employment.noOfFemale);
                        $("#salaryAndEmploymentSection .js-loadedContent").removeClass('hidden');
                        SAGIA.dashboardWithLicense.bar([licenseAndEmployee.employment.saudiAverage, licenseAndEmployee.employment.expatAverage]);
                        $("#salaryAndEmploymentSection .js-preloadContainer").remove();
                    }
                }
            },
            error: function() {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.licence.employee.data"));
                errorModal.modal('show');
                $('#salaryAndEmploymentSection .loadingModule .loadingModule-icon').hide();
                $('#salaryAndEmploymentSection .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.salary.employment.data')+"</span>");
            }
        });

        var paymentsSection = $("#payments");
        if (paymentsSection.closest('.js-component').css('display') !== 'none') {
            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/payments",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (payments) {
                    if (payments && payments.paymentData && payments.PaymentsPagesNumber) {
                        var paymentsTable = $("#paymentsTable");
                        var isAwaitingPayment = false;
                        paymentsTable.empty();
                        for (var index in payments.paymentData) {
                            if (payments.paymentData.hasOwnProperty(index)) {
                                var payment = payments.paymentData[index];
                                var template = $(".paymentTemplateWrapper").find("tbody").clone();
                                template.find(".paymentDate").html(payment.sagiaPaymentDate.dateFormatted);
                                template.find(".dashboardWidgetPayments-title").find("a").attr("href", ACC.config.encodedContextPath + "/payment/details/" + payment.serviceId);
                                template.find(".paymentName").html(payment.name);
                                template.find(".dashboardWidgetPayments-eid").html(payment.serviceId);
                                if (payment.status == 'E0003' && payment.hybrisStatusDescription == 'Paid') {
                                	template.find(".statusDescription").html(payment.hybrisStatusDescription);
                                }else{
                                	template.find(".statusDescription").html(payment.statusDescription);
                                }
                                //template.find(".statusDescription").html(payment.statusDescription);
                                template.find(".dashboardWidgetPayments-status-icon").html(SAGIA.statusUpdate.initServiceRequest(payment.statusDescriptionKey));
                                template.find(".dashboardWidgetPayments-amount").html(payment.amount ? payment.amount.toLocaleString('en-US', {maximumFractionDigits: 2}) : 0);
                                template.find(".dashboardWidgetPayments-currency").html(payment.currency);
                                if (payment.status == 'E0003' && payment.hybrisStatusDescription != 'Paid') {
                                	template.find(".dashboardWidgetPayments-pay").find("a").text(getI18nText("payment.pay"));
                                	template.find(".dashboardWidgetPayments-pay").find("a").attr("class","print-not-link");
                                	template.find(".dashboardWidgetPayments-pay").find("a").attr("style","float: left;");
                                	template.find(".dashboardWidgetPayments-pay").find("a").attr("onclick", "SAGIA.payment.requestCreditBillPayment("+payment.serviceId+",'"+payment.name+"',"+payment.amount+",'"+payment.currency+"')");
                                	isAwaitingPayment = true;
                                }
                                paymentsTable.append(template.html());
                            }
                        }
                        if (isAwaitingPayment) {
                            $("#awaitingPaymentDiv").css("display", "block");
                        }else {
                        	$("#awaitingPaymentDiv").css("display", "none");
                        }

                        var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment active">1</a></div>';
                        for (var i = 2; i <= payments.PaymentsPagesNumber; i++) {
                            paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link payment">' + i + '</a></div>';
                        }
                        $(".dashboardWidgetPayments .paginationModule-items").empty().append(paginationHtml);
                        new CreatePagination($(".dashboardWidgetPayments .paginationModule"), payments.paymentsItemsPerPage);
                        // $(".dashboardWidgetPayments .paginationPicker").append("<option selected='selected' value='" + payments.paymentsItemsPerPage + "'>" + payments.paymentsItemsPerPage + "</option>")
                        // $(".dashboardWidgetPayments .paginationPicker").append("<option value='10000'>View All</option>");
                    }
                    $('.dashboardWidgetPayments .loadingModule').addClass('loadingModule_loaded');
                    $('.dashboardWidgetPayments .paginationModule').removeClass('paginationModule_loading');
                },
                error: function () {
                    //paymentsTable.empty();
                    //$(".dashboardWidgetPayments .paginationModule-items").empty();
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.payments"));
                    errorModal.modal('show');
                    $('.dashboardWidgetPayments .loadingModule .loadingModule-icon').hide();
                    $('.dashboardWidgetPayments .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.payments')+"</span>");
                }
            });
        }

        var ticketsTable = $("#ticketsTable");
        var ticketsSection = $("#yourTickets");
        if(ticketsSection.closest('.js-component').css('display') !== 'none') {
        //if(ticketsTable.is(":visible")) {
            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/tickets",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (tickets) {
                    if (tickets && tickets.tickets && tickets.TicketsPagesNumber) {
                        for (var index in tickets.tickets) {
                            if (tickets.tickets.hasOwnProperty(index)) {
                                var ticket = tickets.tickets[index];
                                var template = $(".ticketTemplateWrapper").find("tbody").clone();
                                template.find(".lastUpdate").html(ticket.lastUpdateData.dateFormatted);
                                template.find(".ticketNumber").html(ticket.ticketNumber);
                                template.find(".dashboardWidgetTickets-status-open").html(ticket.status);
                                template.find(".dashboardWidgetTickets-status-open").addClass(function () {
                                    switch (ticket.statusKey) {
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
                                template.find(".dashboardWidgetTickets-btn").attr("data-complaint-Id", ticket.ticketNumber);
                                ticketsTable.append(template.html());
                            }
                        }
                        var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket active">1</a></div>';
                        for (var i = 2; i <= tickets.TicketsPagesNumber; i++) {
                            paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link ticket">' + i + '</a></div>';
                        }
                        $(".dashboardWidgetTickets .paginationModule-items").empty().append(paginationHtml);
                        new CreatePagination($(".dashboardWidgetTickets .paginationModule"), tickets.ticketsItemsPerPage);
                        // $(".dashboardWidgetTickets .paginationPicker").append("<option selected='selected' value='" + tickets.ticketsItemsPerPage + "'>" + tickets.ticketsItemsPerPage + "</option>")
                        // $(".dashboardWidgetTickets .paginationPicker").append("<option value='10000'>View All</option>");
                    }
                    $('.dashboardWidgetTickets .loadingModule').addClass('loadingModule_loaded');
                    $('.dashboardWidgetTickets .paginationModule').removeClass('paginationModule_loading');
                },
                error: function () {
                    //$(".dashboardWidgetTickets .paginationModule-items").empty();
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').html(getI18nText("general.couldnot.load.tickets"));
                    errorModal.modal('show');
                    $('.dashboardWidgetTickets .loadingModule .loadingModule-icon').hide();
                    $('.dashboardWidgetTickets .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.tickets')+"</span>");
                }
            });
        }


        /*var financialSurveysTable = $("#financialSurveysTable");
        var financialSurveysSection = $("#yourFinancialSurveys");
        if(financialSurveysSection.closest('.js-component').css('display') !== 'none') {
            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/financialSurveys",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (financialSurveys) {
                    if (financialSurveys && financialSurveys.surveys ) {
                        for (var index in financialSurveys.surveys) {
                            if (financialSurveys.surveys.hasOwnProperty(index)) {
                                var survey = financialSurveys.surveys[index];
                                var template = $(".financialSurveyTemplateWrapper").find("tbody").clone();
                                //template.find(".lastUpdate").html(ticket.lastUpdateData.dateFormatted);
                                template.find(".quarter").html(survey.quarter);
                                template.find(".lastUpdate").html(survey.lastUpdate);
                                template.find(".dashboardWidgetTickets-status-open").html(getI18nText(survey.status));
                                template.find(".dashboardWidgetTickets-status-open").addClass(function () {
                                    switch (survey.status) {
                                        case 'PROCESSED':
                                            return 'dashboardWidgetTickets-status_code01';
                                        case 'OPEN':
                                        case 'IN_PROGRESS':
                                        case 'SUBMITTED':
                                            return 'dashboardWidgetTickets-status_code02';
                                        case 'WAITING_FOR_CUSTOMER_ACTION':
                                        default:
                                            return 'dashboardWidgetTickets-status_code03';
                                    }
                                });

                                if (survey.status == 'OPEN' || survey.status == 'WAITING_FOR_CUSTOMER_ACTION' || survey.status == 'IN_PROGRESS') {
                                    //template.find(".dashboardWidgetFinancialSurvey-btn").find("a").text(getI18nText("financial.survey.button.fill.survey"));
                                    template.find(".dashboardWidgetFinancialSurvey-btn").find("a").text(getI18nText("financial.survey.button.fill.survey"));
                                    template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr("class","btn btn_slim draftContinueBtn");
                                    // template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr("style","float: left;");
                                    template.find(".dashboardWidgetFinancialSurvey-btn").find("a").attr('href', ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/display/" + survey.quarterCode);
                                }
                                template.find(".dashboardWidgetFinancialSurveys-btn").attr("data-complaint-Id", survey.quarterCode);
                                financialSurveysTable.append(template.html());
                            }
                        }
                    }
                },
                error: function () {
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').html(getI18nText("general.couldnot.load.tickets"));
                    errorModal.modal('show');
                    $('.dashboardWidgetTickets .loadingModule .loadingModule-icon').hide();
                    $('.dashboardWidgetTickets .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.tickets')+"</span>");
                }
            });
        }*/

        var serviceRequestsTable = $("#serviceRequestsTable");
        var secriceRequestSection = $("#servicesRequest");
        if(secriceRequestSection.closest('.js-component').css('display') !== 'none') {

            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/serviceRequests",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (serviceRequests) {
                    if (serviceRequests && serviceRequests.serviceRequestsData && serviceRequests.serviceRequestPagesNumber) {
                        for (var index in serviceRequests.serviceRequestsData) {
                            if (serviceRequests.serviceRequestsData.hasOwnProperty(index)) {
                                var serviceRequest = serviceRequests.serviceRequestsData[index];
                                var template = $(".serviceTemplateWrapper").find("tbody").clone();

                                if(serviceRequest.urlPath){
                                    template.find("#serviceRequestLink").attr('href',ACC.config.encodedContextPath + '/'+serviceRequest.urlPath);
                                }
                                else{
                                    $('<div class="tableModule-headline" style="cursor: not-allowed;"></div>').insertAfter(template.find('#serviceRequestLink'));
                                    template.find("#serviceRequestLink").remove();
                                }

                                if(!isEmpty(serviceRequest.categoryLevelFour)){
                                    template.find(".tableModule-headline").html(serviceRequest.categoryLevelFour);
                                    template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelThree);
                                    template.find("#description1").html(serviceRequest.categoryLevelTwo);
                                    template.find("#description2").html(serviceRequest.categoryLevelOne);
                                    template.find("#description3").html(serviceRequest.serviceType);

                                }
                                else if(!isEmpty(serviceRequest.categoryLevelThree)) {
                                    template.find(".tableModule-headline").html(serviceRequest.categoryLevelThree);
                                    template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelTwo);
                                    template.find("#description1").html(serviceRequest.categoryLevelOne);
                                    template.find("#description2").html(serviceRequest.serviceType);
                                }
                                else if(!isEmpty(serviceRequest.categoryLevelTwo)) {
                                    template.find(".tableModule-headline").html(serviceRequest.categoryLevelTwo);
                                    template.find(".tableModule-subHeadline").html(serviceRequest.categoryLevelOne);
                                    template.find("#description1").html(serviceRequest.serviceType);
                                }
                                else if(!isEmpty(serviceRequest.categoryLevelOne)) {
                                    template.find(".tableModule-headline").html(serviceRequest.categoryLevelOne);
                                    template.find("#description1").html(serviceRequest.serviceType);
                                }
                                else if(!isEmpty(serviceRequest.serviceType)){
                                    template.find(".tableModule-headline").html(serviceRequest.serviceType);
                                }

                                template.find(".requestNumber").html(serviceRequest.requestNumber);
                                template.find(".requestDate").html(serviceRequest.requestDate.dateFormatted);
                                template.find(".serviceStatus").html(serviceRequest.statusDescription);
                                template.find(".dashboardWidgetRequests-status-icon").html(SAGIA.statusUpdate.initServiceRequest(serviceRequest.status));
                                if(serviceRequest.status==="completed") {
                                    template.find("#printLink").attr('href', ACC.config.encodedContextPath + "/dashboard/serviceRequests/print/" + serviceRequest.requestNumber);
                                }
                                else{
                                    template.find("#printLink").html('');
                                }
                                serviceRequestsTable.append(template.html());
                            }
                        }

                        var paginationHtml = '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link service active">1</a></div>';
                        for (var i = 2; i <= serviceRequests.serviceRequestPagesNumber; i++) {
                            paginationHtml += '<div class="paginationModule-item"><a href="javascript:void(0);" class="paginationModule-link service">' + i + '</a></div>';
                        }
                        $(".dashboardWidgetRequests .paginationModule-items").empty().append(paginationHtml);
                        new CreatePagination($(".dashboardWidgetRequests .paginationModule"), serviceRequests.serviceRequestsItemsPerPage);
                        // $(".dashboardWidgetRequests .paginationPicker").append("<option selected='selected' value='" + serviceRequests.serviceRequestsItemsPerPage + "'>" + serviceRequests.serviceRequestsItemsPerPage + "</option>")
                        // $(".dashboardWidgetRequests .paginationPicker").append("<option value='10000'>View All</option>");
                    }
                    $('.dashboardWidgetRequests .loadingModule').addClass('loadingModule_loaded');
                    $('.dashboardWidgetRequests .paginationModule').removeClass('paginationModule_loading');
                },
                error: function () {
                    //serviceRequestsTable.empty();
                    //$(".dashboardWidgetRequests .paginationModule-items").empty();
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').html(getI18nText("general.couldnot.load.service.requests"));
                    errorModal.modal('show');
                    $('.dashboardWidgetRequests .loadingModule .loadingModule-icon').hide();
                    $('.dashboardWidgetRequests .loadingModule .loadingModule-msg').html("<svg width=\"68\" height=\"60\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 68 60\"><g transform=\"translate(2 2)\" fill=\"none\" fill-rule=\"evenodd\"><path stroke=\"#5CC83B\" stroke-width=\"4\" stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M63.173 55.36H.8l10.077-17.845h.003L31.987.134l21.109 37.381h.002z\"></path><path d=\"M32 17c-1.104 0-2 .902-2 2.018v18.146c0 1.116.896 2.018 2 2.018s2-.902 2-2.018V19.018A2.009 2.009 0 0 0 32 17z\" fill=\"#32465A\" fill-rule=\"nonzero\"></path><circle fill=\"#32465A\" cx=\"32\" cy=\"46\" r=\"2\"></circle></g></svg>&nbsp;<span>"+getI18nText('general.couldnot.load.service.requests')+"</span>");
                }
            });
        }

        var draftsTable = $("#draftsTable");
        if (draftsTable.closest('.js-component').css('display') !== 'none') {
            $.ajax({
                url: ACC.config.encodedContextPath + "/dashboard/drafts",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (draftInfo) {
                    if (draftInfo) {
                        for (var index in draftInfo) {
                            if (draftInfo.hasOwnProperty(index)) {
                                var draft = draftInfo[index];
                                var template = $(".draftTemplateWrapper").find("tbody").clone();
                                template.find(".js-category-1").html(draft.category1);
                                if (draft.category2)
                                    template.find(".js-category-2").html(draft.category2);
                                if (draft.category3)
                                    template.find(".js-category-3").html(draft.category3);
                                if (draft.category4)
                                    template.find(".js-category-4").html(draft.category4);

                                template.find(".draftTemporaryNumber").html(draft.temporaryNumber);
                                template.find(".draftDate").html(draft.creationDate.dateFormatted);
                                if (draft.url)
                                    template.find(".draftContinueBtn").attr("href", draft.url);

                                draftsTable.append(template.html());
                            }
                        }
                    }
                },
                error: function () {
                    draftsTable.empty();
                    var errorModal = $('#errorResponseModal');
                    errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.payments"));
                    errorModal.modal('show');
                }
            });
        }

        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/banners",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (bannerData) {
                var pictureSet = $(".dashboardWidget-pictureSet");
                if (bannerData.extraLargeBannerUrl) {
                    pictureSet.append('<source srcset="' + bannerData.extraLargeBannerUrl + '" media="(min-width: 1140px)">');
                }
                if (bannerData.largeBannerUrl) {
                    pictureSet.append('<source srcset="' + bannerData.largeBannerUrl + '" media="(min-width: 992px)">');
                }
                if (bannerData.mediumBannerUrl) {
                    pictureSet.append('<source srcset="' + bannerData.mediumBannerUrl + '" media="(min-width: 768px)">');
                }
                if (bannerData.smallBannerUrl) {
                    pictureSet.append('<img src="' + bannerData.smallBannerUrl + '" alt="" data-object-fit="" class="">');
                }
                //call picturefill to support sourceset for ie11
                picturefill();
            }
        });

        $.ajax({
            url: ACC.config.encodedContextPath + "/dashboard/accountManager",
            method: 'GET',
            ajaxHideLoadingIndicator: true,
            success: function (accountManager) {
                if (accountManager.general) {
                    var askYourAccountManagerSection = $("#askYourAccountManagerSection");
                    var askYourExpertList = $("#askYourAccountManagerSection > .dashboardWidgetAskOurExpert-list");

                    askYourExpertList.hide();
                    askYourAccountManagerSection.show();

                    askYourAccountManagerSection.find(".dashboardWidgetAskOurExpert-manager-name").text(
                        accountManager.title + ' ' + accountManager.firstName + ' ' + accountManager.lastName);

                    var contactsModal = $("#contactPersonDetails");
                    if (accountManager.phoneNumber) {
                        var phoneLink =  contactsModal.find(".js-contacts-phone");
                        phoneLink.text(accountManager.phoneNumber);
                        phoneLink.attr("href", "tel:" + accountManager.phoneNumber);

                    }
                    if (accountManager.mobileNumber) {
                        var mobileNumberLink =  contactsModal.find(".js-contacts-mobile-number");
                        mobileNumberLink.text(accountManager.mobileNumber);
                        mobileNumberLink.attr("href", "tel:" + accountManager.mobileNumber);
                    }
                    if (accountManager.email) {
                        var emailLink =  contactsModal.find(".js-contacts-email");
                        emailLink.text(accountManager.email);
                        emailLink.attr("href", "mailto:" + accountManager.email);
                    }
                }
            },
            error: function () {
                var errorModal = $('#errorResponseModal');
                errorModal.find('.modal-description').text(getI18nText("general.couldnot.load.account.manager.data"));
                errorModal.modal('show');
            }
        });

    }
};

function touchHandler(event) {
    var touch = event.changedTouches[0];

    var simulatedEvent = document.createEvent("MouseEvent");
    simulatedEvent.initMouseEvent({
            touchstart: "mousedown",
            touchmove: "mousemove",
            touchend: "mouseup"
        }[event.type], true, true, window, 1,
        touch.screenX, touch.screenY,
        touch.clientX, touch.clientY, false,
        false, false, false, 0, null);

    touch.target.dispatchEvent(simulatedEvent);
}

function dismissDashboardTutorial() {
    $.ajax({
        url: ACC.config.encodedContextPath + "/dashboard/dismiss-tutorial",
        method: 'GET',
        ajaxHideLoadingIndicator: false,
        success: function (data) {
        },
        error: function(data) {
            var errorModal = $('#errorResponseModal');
            errorModal.find('.modal-description').text(getI18nText('general.dismiss.tutorial'));
            errorModal.modal('show');
        }
    });
}

function awaitingPayment(){
	var url = ACC.config.encodedContextPath + '/payments-overview'
    window.location.replace(url);
}
