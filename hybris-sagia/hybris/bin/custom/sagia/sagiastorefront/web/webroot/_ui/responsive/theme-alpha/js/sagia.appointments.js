var timePickerFrom;
$(function () {
	//var $modal = $('#appointmentDisclaimer');
    //$modal.modal('toggle');
    formOptionsJSON = formOptionsJSON.length === 0 ? {} : JSON.parse(formOptionsJSON);
    $(".service-selection .service-type").change(function () {
        var me = this;
        var serviceType = formOptionsJSON.serviceTypes.find(function (x) {
            return x.fieldKey === $(me).val();
        });
        renderAppointmentOptions($(this).parents('.service-selection').find('.ministry'), serviceType.ministries);
        renderAppointmentOptions($(this).parents('.service-selection').find('.service'), serviceType.services);
    });

    $(".service-selection .ministry").change(function () {
        var me = this;
        var serviceType = formOptionsJSON.serviceTypes.find(function (x) {
            return x.fieldKey === $(me).parents('.service-selection').find(".service-type").val();
        });
        var ministry = serviceType.ministries.find(function (x) {
            return x.fieldKey === $(me).val();
        });
        renderAppointmentOptions($(this).parents('.service-selection').find('.service'), ministry.services);
    });

    $(".service-selection .service").change(function () {
        if ($(this).val() != '0') {
            $('.btn-load-time-slots').removeAttr('disabled');
        }

    });
    $('.btn-load-time-slots').on('click', function () {
        loadAvailableTimeSlots();
    });

    var now = new Date();
    // var yesterday = new Date(now.setDate(now.getDate() - 1));
    var yesterday = new Date(now.setDate(now.getDate() - 1));

    $('.page-appointment-create #dateString').flatpickr({
    	minDate: new Date().fp_incr(1),
        maxDate: new Date().fp_incr(14),
        disable: [
            /*function (date) {
                return date < yesterday;
            },*/
            function (date) {
                // return true to disable
                return (date.getDay() === 5 || date.getDay() === 6);
            }
        ],
        dateFormat: ACC.formatUIDate ? ACC.formatUIDate : "M d, Y",
        onChange: function(selectedDates, dateStr, instance){
            // var date = selectedDates[0].getFullYear() + '-' + (selectedDates[0].getMonth() + 1) + '-' + selectedDates[0].getDate();
            var now = new Date();
            var date = selectedDates[0].getFullYear() + '-' + ('0' + (selectedDates[0].getMonth()+1)).slice(-2) + '-' + ('0' + selectedDates[0].getDate()).slice(-2);
            loadAvailableTimeSlots(date);
        }
    });
    // for the edit mode
    if(typeof appointmentId !== 'undefined' && appointmentId != ''){
        if(appointmentJson.ministry1 != "") $('#ministry1').removeAttr("disabled");
        if(appointmentJson.ministry2 != "") $('#ministry2').removeAttr("disabled");
        if(appointmentJson.ministry3 != "") $('#ministry3').removeAttr("disabled");

        if(appointmentJson.service1 != "") $('#service1').removeAttr("disabled");
        if(appointmentJson.service2 != "") $('#service2').removeAttr("disabled");
        if(appointmentJson.service3 != "") $('#service3').removeAttr("disabled");
        var flatpickr = document.querySelector("#dateString")._flatpickr;
        var formattedDate = flatpickr.formatDate(new Date(appointmentJson.dateData.millis), ACC.formatUIDate);
        $("#dateString").val(formattedDate);
        $('#timeStartString').val(('0' + appointmentJson.timeStart.hour).slice(-2) + ':' + ('0' + appointmentJson.timeStart.minute).slice(-2));
    }
    loadQR();

    var appointmentValidationParams = {
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        focusInvalid: true,
        ignore: [],
        errorElement: 'span',
        errorPlacement: function(error, element) {
            if (element.closest('.form-group').find('.help-block').length>0) {
                error.appendTo(element.closest('.form-group').find('.help-block'));
            } else if (element.hasClass('js-select2-oneColumn')){
                error.appendTo(element.closest('.formSelectBox').find('.help-block'));

            } else {
                error.appendTo(element.closest('.formInputBox').find('.help-block'));
            }
        },
        rules: {
            department: {
                required: true
            },
            branch: {
                required: true
            },
            serviceType1: {
                require_from_group: [1, ".servicetype-group"]
            },
            serviceType2: {
                require_from_group: [1, ".servicetype-group"]
            },
            serviceType3: {
                require_from_group: [1, ".servicetype-group"]
            },
            ministry1: {
                require_from_group: [1, ".ministry-group"]
            },
            ministry2: {
                require_from_group: [1, ".ministry-group"]
            },
            ministry3: {
                require_from_group: [1, ".ministry-group"]
            },
            service1: {
                require_from_group: [1, ".service-group"]
            },
            service2: {
                require_from_group: [1, ".service-group"]
            },
            service3: {
                require_from_group: [1, ".service-group"]
            },
            dateString: {
                required: true
            },
            timeStartString: {
                required: true
            },
            contactPerson: {
                required: true
            },
            notes: {
                required: true
            }
        },
        messages: {
            department: {
                required: getI18nText("validation.empty")
            },
            branch: {
                required: getI18nText("validation.empty")
            },
            serviceType1: {
                required: getI18nText("validation.service.group")
            },
            serviceType2: {
                required: getI18nText("validation.service.group")
            },
            serviceType3: {
                required: getI18nText("validation.service.group")
            },
            ministry1: {
                required: getI18nText("validation.service.group")
            },
            ministry2: {
                required: getI18nText("validation.service.group")
            },
            ministry3: {
                required: getI18nText("validation.service.group")
            },
            service1: {
                required: getI18nText("validation.service.group")
            },
            service2: {
                required: getI18nText("validation.service.group")
            },
            service3: {
                required: getI18nText("validation.service.group")
            },
            dateString: {
                required: getI18nText("validation.empty")
            },
            timeStartString: {
                required: getI18nText("validation.empty")
            },
            contactPerson: {
                required: getI18nText("validation.empty")
            },
            notes: {
                required: getI18nText("validation.empty")
            }
        },
        invalidHandler: function(event, validator) {
        }
    };

    var appointmentForm = $('#appointmentModel');
    appointmentForm.validate(appointmentValidationParams);
    appointmentForm.ajaxForm({
        beforeSubmit: function(arr, $form, options){
            var valid = $form.valid();
            if (!valid) {
                return false;
            }
            options.headers = {
                'Accept': 'application/json',
                'CSRFToken': ACC.config.CSRFToken
            };
        },
        success: function (data) {
            // var response = JSON.parse(data);
            // console.log(data);
            var $modal = $('#requestSubmittedApply');
            $modal.find(".js-description-text").empty();
            var messageText = getI18nText("submit.request.message")
            $modal.find(".js-description-text").text(messageText)
            /*if (data.userEmail) {
                var messageText = getI18nText("appointments.create.message").replace("{email}", data.userEmail);
                $modal.find(".js-description-text").text(messageText)
            }*/
            $modal.modal('toggle');
            // $('#nipSuccessModal #requestNumberPlaceholder').text(response.responseData);
            // $('#nipSuccessModal').modal('toggle');
        }
    });
});


String.prototype.lpad = function(padString, length) {
    var str = this;
    while (str.length < length)
        str = padString + str;
    return str;
}


function loadQR(){
    if($('.page-appointment-details .appointmentDetails-qrCodeImagePlaceholder').length > 0){
        $('.page-appointment-details .appointmentDetails-qrCodeImagePlaceholder').qrcode({
            width: 200,
            height: 200,
            text: infoPath
            //text: appointmentDate + '|' + appointmentTime + '|' + appointmentDepartment + '|' + appointmentBranch
        });
    }

    if($('.page-appointment-info .appointmentDetails-qrCodeImagePlaceholder').length > 0){
        $('.page-appointment-info .appointmentDetails-qrCodeImagePlaceholder').qrcode({
            width: 200,
            height: 200,
            text: window.location.href
        });
    }
}

function renderAppointmentOptions(selectEl, items) {
    selectEl.find("option:not(:first)").remove();
    if (items.length > 0) {
        items.forEach(function (value) {
            selectEl.append($("<option></option>")
                .attr("value", value.fieldKey)
                .text(value.description));
        });
        selectEl.removeAttr('disabled');
    } else {
        selectEl.attr('disabled', true);
    }
}

function loadAvailableTimeSlots(selectedDate) {
    $.ajax(ACC.config.encodedContextPath + "/appointments/get-calendar-slots", {
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
        },
        cache: false,
        data: JSON.stringify(objectifyForm($('#appointmentModel').serializeArray())),
        success: function (data) {
            var timeSlots = [];
            data.forEach(function (currentValue) {
                if (currentValue.date[0] === new Date(selectedDate).getFullYear() &&
                    currentValue.date[1] === new Date(selectedDate).getMonth() + 1 &&
                    currentValue.date[2] === new Date(selectedDate).getDate() && currentValue.available) {
                    var minute = currentValue.dateTimeStart[1];
                    if(currentValue.dateTimeStart[1] === 0) {
                        minute = '00';
                    }
                    timeSlots.push(currentValue.dateTimeStart[0] + ':' + minute);
                }
            });
            _updateSlot($('.page-appointment-create #timeStartString'), timeSlots);
        }
    });
}

var _updateSlot = function (picker, data) {
    var _dataString = false;
    if (typeof(data) === "string" && data.indexOf(":") >= 0) {
        _dataString = data;
    } else if (data instanceof Array) {
        _dataString = data.join(";");
    }
    if (!_dataString) {
        return;
    }
    picker.data('dates-enabled', _dataString);
    picker.get(0)._resetTimeslots();
};

function pushIntoCalendar(itemDate, displayName, type) {
    if (!appointmentMatrix[itemDate.date.year]) {
        appointmentMatrix[itemDate.date.year] = {};
    }
    // month is zero indexed????
    if (!appointmentMatrix[itemDate.date.year][itemDate.date.month - 1]) {
        appointmentMatrix[itemDate.date.year][itemDate.date.month - 1] = {};
    }
    if (!appointmentMatrix[itemDate.date.year][itemDate.date.month - 1][itemDate.date.day]) {
        appointmentMatrix[itemDate.date.year][itemDate.date.month - 1][itemDate.date.day] = [];
    }
    appointmentMatrix[itemDate.date.year][itemDate.date.month - 1][itemDate.date.day].push({
        displayname: displayName,
        type: type
    });
}
var calendar = null;
if (!SAGIA.appointmentCalendar) {
    var today = new Date();
    var eventTypes = {
        "gov": {
            cls: "js-event-gov",
            name: "Gov Appointments"
        },
        "app": {
            cls: "js-event-app",
            name: "Appointments"
        },
        "sys": {
            cls: "js-event-sys",
            name: "System Notifications"
        }
    };
    var _getEventMatrix = function (container) {
        var _matrix = {};
        var _types = {};
        var valid = false;
        $("#" + container.getAttribute('id') + " .appointmentList-entry").each(function (i, c) {
            var _date = $(c).children('[data-date]').first().attr('data-date'),
                _time = $(c).children('[data-time]').first().attr('data-time'),
                _duration = $(c).children('[data-duration]').first().attr('data-duration'),
                _type = $(c).children('[data-type]').first().attr('data-type'),
                _displayname = $(c).children('[data-displayname]').first().attr('data-displayname'),
                _location = $(c).children('[data-location]').first().attr('data-location'),
                _state = $(c).children('[data-state]').first().attr('data-state');
            if (_date) {
                _date = _date.split('-');
                if (_date.length != 3 || !_displayname) {
                    return false;
                }
                var _obj = {displayname: _displayname};
                if (!_matrix[_date[0]]) {
                    _matrix[_date[0]] = {};
                }
                _date[1] = parseInt(_date[1]);
                if (!_matrix[_date[0]][_date[1]]) {
                    _matrix[_date[0]][_date[1]] = {};
                }
                if (!_matrix[_date[0]][_date[1]][_date[2]]) {
                    _matrix[_date[0]][_date[1]][_date[2]] = [];
                }
                if (_time && _time.length > 0) {
                    _obj.time = _time;
                }
                if (_type && _type.length > 0) {
                    if (_type.indexOf('\\') > 0) {
                        _type = _type.substring(0, _type.indexOf('\\'));
                    }
                    var _key = _type.replace(' ', '').toLowerCase().substring(0, 3);
                    var _cls = "js-event-" + _key;
                    if (!_types[_key]) {
                        _types[_key] = {cls: _cls, name: _type};
                    }
                    _obj.type = _key;
                    valid = true;
                }
                if (_location.length > 2) {
                    _obj.location = _location;
                }
                if (_state.length > 2) {
                    _obj.state = _state;
                }
                if (_time.length > 2) {
                    _obj.at = _time;
                }
                if (_duration.length > 2) {
                    _obj.duration = _duration;
                }
                _matrix[_date[0]][_date[1]][_date[2]].push(_obj);
            }
        });
        if (valid) {
            return {matrix: _matrix, types: _types};
        }
        return false;
    };
    var appointmentMatrix = {};
    try {
        if (appointmentsJson) {
            appointmentsJson.forEach(function (item) {
                if (item.date) {
                    var serviceType = "sys";
                    switch(item.serviceType1){
                        case "GOVTSERV": serviceType = "gov"; break;
                        case "SAGIASER": serviceType = "sag"; break;
                    }
                    pushIntoCalendar(item.date, item.service1Description, serviceType);
                }
            });

            /*if (notificationsJson) {
                notificationsJson.forEach(function (notification) {
                    if (notification.expiryDate && notification.expiryDate.date) {
                        pushIntoCalendar(notification.expiryDate, notification.notificationText, "sys");
                    }
                });
            }*/

            $(document).ready(function () {
                if (document.getElementById('appointmentCalendar')) {
                    if (document.getElementById('appointmentList')) {
                        var mat = _getEventMatrix(document.getElementById('appointmentList'));
                        if (mat && mat.matrix) {
                            matrix = mat.matrix;
                        }
                        if (mat && mat.types) {
                            eventTypes = mat.types;
                        }
                    }
                    //calendarLabelsJson is a JSON object with all calendar labels
                    var viewsLabels = calendarLabelsJson.views;
                    var viewsVocabLabels = calendarLabelsJson.viewsVocab; //day, week, month
                    var daysVocabShortLabels = calendarLabelsJson.daysVocabShort; //Sun, Mon, Tue..
                    var daysVocabLabels = calendarLabelsJson.daysVocab;//Sunday, Monday, Tuesday..
                    var buttonsLabels = calendarLabelsJson.buttons;//previous,next..
                    var buttonsActionsLabels = calendarLabelsJson.buttonsActions;//gonext, gonow..

                    calendar = new JSCalendar(document.getElementById('appointmentCalendar'), {
                        titleCropSize: 30,
                        eventBackground: "rgb(193, 155, 113)",
                        eventTypes: eventTypes,
                        views: [viewsLabels.week, viewsLabels.month],
                        viewsVocab: {day: viewsVocabLabels.Day, week: viewsVocabLabels.Week, month: viewsVocabLabels.Month},
                        daysVocabShort: [ daysVocabShortLabels.Sun, 
                        					 daysVocabShortLabels.Mon,
                        					 daysVocabShortLabels.Tue,
                        					 daysVocabShortLabels.Wed, 
                        					 daysVocabShortLabels.Thu,
                        					 daysVocabShortLabels.Fri,
                        					 daysVocabShortLabels.Sat ],
                        buttons: [buttonsLabels.previous, buttonsLabels.title, buttonsLabels.today, buttonsLabels.next],
                        width: "full",
                        buttonsActions: {
                            previous: {action: buttonsActionsLabels.goBack, parent: "center"},
                            title: {type: buttonsActionsLabels.title, parent: "center"},
                            next: {action: buttonsActionsLabels.goNext, parent: "center"},
                            today: buttonsActionsLabels.goNow
                        }
                    });
                    calendar.on("rendered", function(){
                        $.each(notificationsJson, function( index, visit ) {
                            var expiryDateData = visit.expiryDate.date;
                            // check if there is a visit around this month (previous and next)
                            if(calendar.state.year == expiryDateData.year
                                && calendar.state.month >= expiryDateData.month - 2
                                && calendar.state.month <= expiryDateData.month){
                                $('.calendar-legend .js-event-hasvisit').remove();
                                var expiryDate = new Date(expiryDateData.year, expiryDateData.month - 1, expiryDateData.day);
                                var monday = getFirstMondayOfWeek(expiryDateData.year, expiryDate.getWeekNumber());
                                if(expiryDate.getDay() == 0){
                                    monday = (new Date(monday)).addDays(7).getTime();
                                }
                                var sundayDate = new Date(monday);
                                sundayDate = sundayDate.addDays(6);
                                var sunday = sundayDate.getTime();
                                var hasVisitLegend = false;
                                if(calendar.state.view === 'month'){
                                    $('.calendar-table td').each(function(index){
                                        if($(this).data('at') >= monday && $(this).data('at') <= sunday){
                                            hasVisitLegend = true;
                                            $(this).addClass('has-visit');
                                        }
                                    });
                                }
                                if(calendar.state.view === 'week'){
                                    $('.calendar-table td div.col-week-day-container').each(function(index){
                                        if($(this).data('at') >= monday && $(this).data('at') <= sunday){
                                            hasVisitLegend = true;
                                            $(this).addClass('has-visit');
                                            $(this).find('.cal-week-day-no-event-col').text(getI18nText("general.supportingvisit"));
                                        }
                                    });
                                }
                                if(hasVisitLegend){
                                    $('.calendar-legend').append('<li class="js-event-hasvisit">Sagia Supporting Visit</li>');
                                }
                            }
                        });
                    });
                    calendar.init();
                    calendar.setMatrix(appointmentMatrix).render();
                }
            });
        }
    } catch (e) {
    }
}

Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

Date.prototype.getWeekNumber = function(){
    var d = new Date(Date.UTC(this.getFullYear(), this.getMonth(), this.getDate()));
    var dayNum = d.getUTCDay() || 7;
    d.setUTCDate(d.getUTCDate() + 4 - dayNum);
    var yearStart = new Date(Date.UTC(d.getUTCFullYear(),0,1));
    return Math.ceil((((d - yearStart) / 86400000) + 1)/7)
};

function getFirstMondayOfWeek(year, weekNo) {
    var firstMonday = new Date(year, 0, 4, 0, 0, 0, 0);
    while (firstMonday.getDay() != 0) {
        firstMonday.setDate(firstMonday.getDate() - 1); // - THIS IS SUNDAY
    }
    if (1 <= weekNo && weekNo <= 52)
        return firstMonday.setDate(firstMonday.getDate() + 7 * (weekNo - 1));

    firstMonday.setDate(firstMonday.getDate() + 7 * (weekNo - 1));
    if (weekNo == 53 && firstMonday.getDate() >= 22 && firstMonday.getDate() <= 28)
        return firstMonday;
    return null;
}

var objectifyForm = function(formArray) { //serialize data function
    var returnArray = {};
    for (var i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}
