var SAGIA = SAGIA || {};


SAGIA.realTimeOnlineSupport = {
    availabilityByDayOfWeek: [],
    countries :{},
    _autoload: [
        ["init", '$(".js-realTimeOnlineSupport-btn").length > 1']
    ],
    init: function () {
        $(document).on("click", ".js-realTimeOnlineSupport-btn", function (e) {
            e.preventDefault();
            if ($(".js-realTimeOnlineSupport").hasClass("active")) {
                $.ajax({
                    type: "GET",
                    url: ACC.config.encodedContextPath + "/realtime/availability",
                    success: function (response) {
                        response.isCallbackAvailable = true;
                        if (response.isLiveChatAvailable) {
                            $(".js-realTimeOnlineSupportChatList-open").parent().show();
                        } else {
                            $(".js-realTimeOnlineSupportChatList-open").parent().hide();
                        }

                        if (response.isCallbackAvailable) {
                            $(".js-realTimeOnlineSupportCall-open").parent().show();
                        } else {
                            $(".js-realTimeOnlineSupportCall-open").parent().hide();
                        }

                        var counter = 2 + (response.isLiveChatAvailable ? 1 : 0) + (response.isCallbackAvailable ? 1 : 0);
                        $(".realTimeOnlineSupport-col").css({
                            "max-width": (100 / counter) + "%",
                            "flex": "0 0 " + (100 / counter) + "%"
                        });
                        if ($(document).width() > 768) {
                            $(".realTimeOnlineSupport").css({
                                "width": 200 * counter + "px"
                            });
                        } else {
                            $(".realTimeOnlineSupport").css({
                                "width": "auto"
                            });
                        }

                        $(".js-realTimeOnlineSupport").toggleClass("active");
                    }
                });
            } else {
                $(".js-realTimeOnlineSupport").toggleClass("active");
            }
        });
        var loadCountries = function () {
            $.get(ACC.config.encodedContextPath + "/register/countries", function (data) {
                var jsonData = JSON.parse(data);
                SAGIA.realTimeOnlineSupport.countries = jsonData;
                var quickRealtimeCountry = $('#realtime\\.callback\\.country');
                quickRealtimeCountry.empty();
                quickRealtimeCountry.append(new Option('', '', false, false));
                jsonData.forEach(function (currentValue) {
                    quickRealtimeCountry.append(new Option(currentValue.name, currentValue.code, false, false));
                });
            });
        };
        setMobileCode(".countriesselect\\.realtime", "realtime.callback.mobileCountryCode", "#realTimeScheduleForm", ".js-apply-mobile-number");

        function setMobileCode(sourceElement, targetElement, form, validateElement) {
            $(sourceElement).on('change', function () {
                var code = $(this).val();
                var prefix = SAGIA.realTimeOnlineSupport.countries.filter(function (el) {
                    return el.code === code;
                });

                $(this).valid();

                if (prefix[0] != null) {
                    var countryCodeInput = document.getElementById(targetElement);
                    if (prefix[0].phoneprefix) {
                        countryCodeInput.value = prefix[0].phoneprefix;
                    } else {
                        countryCodeInput.value = '';
                    }
                    countryCodeInput.classList.remove('placeholder-shown');
                    var $form = $(form);
                    var nextHelpBlock = $(validateElement).next(".help-block");
                    nextHelpBlock.empty();
                    /* Trigger manual remote validation from jQuery Validation for mobileNumber */
                    $(validateElement).removeData("previousValue");
                    //$form.data('validator').element(validateElement);
                }
            });
        }


        $(document).on("click", "#scheduleCallButton,#liveChatButton,#makeAnEnquiry", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupport").toggleClass("active");
        });

        $(document).on("click", ".js-realTimeOnlineSupportCall-open", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportCall-page1").addClass('active').siblings().removeClass('active');
            $(".js-realTimeOnlineSupportCall").addClass('active');
            $(".js-realTimeOnlineSupport").removeClass('active');
        });

        $(document).on("click", ".js-realTimeOnlineSupportCall-close", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportCall").removeClass('active');
            $(".realTimeOnlineSupport-btn").addClass("active");
        });

        $(document).on("click", ".js-realTimeOnlineSupportCall-page2-open", function (e) {
            e.preventDefault();
            loadCountries();
            $(".js-realTimeOnlineSupportCall-page2").addClass('active').siblings().removeClass('active');
            var now = new Date();
            var today = new Date();
            today.setHours(0,0,0,0);
            var yesterday = new Date(now.setDate(now.getDate() - 1));
            if ($('#dateStart').length > 0) {
                $('#dateStart').flatpickr({
                    disable: [{
                        from: "1970-01-01",
                        to: yesterday.getFullYear() + '-' + ('0' + (yesterday.getMonth() + 1)).slice(-2) + '-' + ('0' + yesterday.getDate()).slice(-2)
                    }, function (date) {
                        // return true to disable
                        return (date.getDay() === 5 || date.getDay() === 6);
                    }],
                    onChange: function (selectedDates, dateStr, instance) {
                        var now = new Date();
                        var date = selectedDates[0].getFullYear() + '-' + ('0' + (selectedDates[0].getMonth() + 1)).slice(-2) + '-' + ('0' + selectedDates[0].getDate()).slice(-2);
                        var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
                        var dayName = days[selectedDates[0].getDay()];
                        var i;
                        for (i = 0; i < SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek.length; i++) {
                            var n = SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek[i].indexOf(dayName.concat("-"));
                            if (n >= 0) {
                                var availability = SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek[i].split('-', 2);
                                var timeSlot = availability[1];
                                
                                if(today.getTime() == selectedDates[0].getTime()) {
                                    var timeSlotTokens = timeSlot.split('#');
                                    var hourTokens = timeSlotTokens[0].split(':');
                                    if(now.getMinutes() > 30){
                                        now.setMinutes(0);
                                        now.setHours(now.getHours() + 1);
                                    } else {
                                        now.setMinutes(30);
                                    }
                                    hourTokens[0] = (now.getHours() + "").padStart(2, '0');
                                    hourTokens[1] = (now.getMinutes() + "").padStart(2, '0');
                                    timeSlotTokens[0] = hourTokens.join(':');
                                    timeSlot = timeSlotTokens.join('#');
                                    // console.log(timeSlot);
                                }
                                $('#timeStart').data("dates-enabled", timeSlot);
                                $('#timeStart').get(0)._resetTimeslots();
                            }
                        }
                    }
                });
            }
        });

        $('#formAvailableSlot').submit(function (e) {
            e.preventDefault();
            var $form = $("#formAvailableSlot");
            var fd = new FormData($(this)[0]);
            var token = $('input[name="CSRFToken"]').attr('value');
            $.ajax({
                type: 'POST',
                url: $form.attr('action'),
                data: fd,
                cache: false,
                processData: false,
                contentType: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('CSRFToken', token);
                    xhr.setRequestHeader('Accept', "application/json");
                },
                success: function (srId) {
                    $('div.js-timeslot-value').text($("#timeStart").val());
                    $('div.js-date-value').text($("#dateStart").val());
                    $(".js-realTimeOnlineSupportCall-page3").addClass('active').siblings().removeClass('active');
                },
                error: function (e) {
                    $('#errorResponseModal').find('.modal-description').text(getI18nText("realtime.callback.data.invalid"));
                    $('#errorResponseModal').modal('show');
                }
            });
        });

        $(document).on("click", ".js-realTimeOnlineSupportChatList-open", function (e) {
            e.preventDefault();
            var toggleValue = $.cookie("chatOpen");
            if (toggleValue === null) {
                toggleValue = "false";
            }
            if (toggleValue == "true") {
                toggleValue = "false";
                $.cookie('chatOpen', null, { path: ACC.config.encodedContextPath });
            } else {
                toggleValue = "true";
                $.cookie('chatOpen', toggleValue, { path: ACC.config.encodedContextPath });
            }
            $("#sap-ecf-client").toggle(toggleValue == 'true');
        });

        $(document).on("click", ".js-realTimeOnlineSupportChatList-close", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportChatList").removeClass('active');
        });

        $(document).on("click", ".js-realTimeOnlineSupportChatList-close", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportChatList").removeClass('active');
            $(".realTimeOnlineSupport-btn").addClass("active");
        });

        $(document).on("click", ".js-realTimeOnlineSupportChatList-openChat", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportChatList").removeClass('active');
            $(".js-realTimeOnlineSupportChat").addClass('active');
        });

        $(document).on("click", ".js-realTimeOnlineSupportChat-close", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportChat").removeClass('active');
            $(".js-realTimeOnlineSupportChatDone").addClass('active');
            $(".realTimeOnlineSupport-btn").addClass("active");
        });

        $(document).on("click", ".js-realTimeOnlineSupportChatDone-close", function (e) {
            e.preventDefault();
            $(".js-realTimeOnlineSupportChatDone").removeClass('active');
            $(".realTimeOnlineSupport-btn").addClass("active");
        });

        $(document).on("click", ".j-realTimeOnlineSupport-enquiry", function (e) {
            e.preventDefault();
            var tabs = $(".js-panelTabs");
            if (tabs.length) {
                try {
                    SAGIA.license.apply.tabs.showAccessibleTabSelector("#enquiriesTab");
                } catch (exception) {
                    document.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile#enquiriesTab";
                }
            } else {
                document.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile#enquiriesTab";
            }
        });

        $.ajax({
            type: "GET",
            url: ACC.config.encodedContextPath + "/realtime/connectivity",
            success: function (response) {
                $(".js-realtimeOnlineSupportEmailUs, #eServicesButton").attr("href", "mailto:" + response.realtimeEmailUs);
                $(".js-realtimeOnlineSupportEmailUs, #eServicesButton").attr("target", "_new");
                $(".js-realTimeOnlineSupportCallLocal")
                    .attr("href", "tel:" + response.realtimeCallLocal)
                    .text(response.realtimeCallLocal);
                $(".js-realTimeOnlineSupportCallNow").attr("href", "tel:" + response.realtimeCallLocal);
                $(".js-realTimeOnlineSupportCallInternational")
                    .attr("href", "tel:" + response.realtimeCallInternational)
                    .text(response.realtimeCallInternational);
                SAGIA.realTimeOnlineSupport.availabilityByDayOfWeek = response.availabilityByDayOfWeek;
            }
        });
        setTimeout(function () {
            var toggleValue = $.cookie("chatOpen");
            $("#sap-ecf-client").toggle(toggleValue == 'true');
        }, 500);
    }
};
