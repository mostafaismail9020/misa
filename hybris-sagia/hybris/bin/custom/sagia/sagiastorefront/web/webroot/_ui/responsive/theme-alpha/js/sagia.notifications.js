var SAGIA = SAGIA || {};
SAGIA.notifications = SAGIA.notifications || {};

//if /notifications is requested, read the first entry in the table and expand it
$(function() {
    SAGIA.notifications.changeNotification = function(id) {
        var token = $('input[name="CSRFToken"]').attr('value');

        $.ajax(ACC.config.encodedContextPath + "/my-sagia/notifications/changeNotification/" + id, {
            type: "GET",
            contentType: "application/json;charset=utf-8",
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader('CSRFToken', token);
            },
            success: function (data) {
                var notificationData = JSON.parse(data);
                var oldValue = $('#unreadNotificationSpan').html();
                if(oldValue > 0 && notificationData.readDate) {
                    SAGIA.notifications.updateNotificationsCount(oldValue - 1);
                }

                var date = notificationData.createdOn.dateFormatted;
                $('#createdByDiv').text(notificationData.createdBy);
                $('#createdOnDiv').text(date);

                if(notificationData.notificationType === 'PY' && notificationData.paymentItems){
                    $('#notificationTextParagraph').html("");
                    $('#notificationTextParagraph').append('<p>'+notificationData.notificationText+'</p><br>');
                    for(key in notificationData.paymentItems){
                        $('#notificationTextParagraph').append('<p style="font-weight: 700">'+key+'</p>');
                        $('#notificationTextParagraph').append('<p>' + getI18nText("text.items") + '</p>');
                        for(item in notificationData.paymentItems[key]){
                            $('#notificationTextParagraph').append('<p style="margin-left:5em">'+notificationData.paymentItems[key][item].description+
                                " : "+notificationData.paymentItems[key][item].currency+
                                " "+notificationData.paymentItems[key][item].value+'</p>');
                        }
                    }
                }
                else{
                    $('#notificationTextParagraph').text(notificationData.notificationText);
                    $('.pay-not').addClass('hidden');
                }

                var payButton = $('#payButton').html();
                var printButtonHtml = $('#printButton').html();
                var participateButtonHtml = $('#participateButton').html();
                if (notificationData.notificationType === 'SY') {
                    $('.print-not-link').attr('href', ACC.config.encodedContextPath+ '/my-sagia/questionnaires/' + notificationData.transactionId);
                    $('.print-not-link').html(participateButtonHtml);
                    $('.print-not').removeClass('hidden');
                    return;
                }
                if (notificationData.notificationType === 'WL') {
                   $('.print-not-link').html(printButtonHtml);
                   $('.print-not').removeClass('hidden');
                   $('.print-not-link').attr('href', ACC.config.encodedContextPath+ '/my-sagia/notifications/print/' + notificationData.transactionId);
                } else if ((notificationData.notificationType === 'NO')) {
                    $('.print-not-link').html(printButtonHtml);
                    $('.print-not').removeClass('hidden');
                    $('.print-not-link').attr('href', ACC.config.encodedContextPath+ '/my-sagia/notifications/print');
                } else if(notificationData.notificationType === 'PY'){
                    $('.pay-link').on('click', function(){
                        SAGIA.payment.requestPayment(notificationData);
                    });
                    $('.print-not').addClass('hidden');
                    $('.pay-not').removeClass('hidden');
                }
                else {
                    $('.pay-not').addClass('hidden');
                    $('.print-not').addClass('hidden');
                }
            },
            error: function (e) {
            },
            done: function (e) {
            }
        });
    };

    SAGIA.notifications.bindObjectAttributes = function(object) {
        for (var attribute in object) {
            if(object.hasOwnProperty(attribute)) {
                var value = object[attribute];
                var span = $("[name='" + attribute + "']");

                if (span.length) {
                    if (value) {
                        span.text(value);
                    } else {
                        span.text('-');
                    }
                }
            }
        }
    };

    SAGIA.notifications.requestSingleNotification = function(listItem) {
        var transactionId = listItem.data('transactionId');
        var now = new Date();
        var formattedDate = ('0' + now.getDate()).slice(-2) + '/' + ('0' + (now.getMonth() + 1)).slice(-2) + '/' + now.getFullYear();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: ACC.config.encodedContextPath + "/my-sagia/notifications/" + transactionId,
            data: JSON.stringify({readDate: formattedDate}),
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Accept', "application/json");
            },
            success: function (data) {
                listItem.removeClass("notificationList-item_unread");
                listItem.parent().children().removeClass('select');
                listItem.addClass('select');
            }
        ,
            complete: function (data) {
                SAGIA.notifications.changeNotification(transactionId);
                var iconE = listItem.find('.iconElement');
                $('.notificationIconSpan').html(iconE.html());
            }
        });
    };

    $("#history-list li").click(function() {
        SAGIA.notifications.requestSingleNotification($(this));
    });

    var href = location.href;
    var lastSegment = href.match(/([^\/]*)\/*$/)[1];
    var $firstNotificationFromHistoryList = $('#history-list').find('li:first');
    if (lastSegment === 'notifications' && $firstNotificationFromHistoryList.length) {
        SAGIA.notifications.requestSingleNotification($firstNotificationFromHistoryList);
    }

    $('#readAllNotificationsButton').on("click", function (event) {
        var token = $('input[name="CSRFToken"]').attr('value');
        var today = new Date().toISOString().slice(0, 10);
        var readDate = '{"readDate": "' + today + '"}';
        $.ajax({
            type: 'PUT',
            url: ACC.config.encodedContextPath + "/my-sagia/notifications/read/all",
            data: readDate,
            cache: false,
            processData: false,
            contentType: "application/json; charset=utf-8",
            beforeSend: function (xhr) {
                xhr.setRequestHeader('CSRFToken', token);
                xhr.setRequestHeader('Accept', "application/json");
            },
            success: function (response) {
                SAGIA.notifications.updateNotificationsCount(0);
            },
            complete: function () {
                $("#history-list").find("li").each(function () {
                    $(this).removeClass("notificationList-item_unread");
                });
            },
            error: function (xhr, status, error) {
                var errorMessage = xhr.responseJSON.sagiaExceptionResponse.message;
                var errorResponseModal = $('#errorResponseModal');
                errorResponseModal.find('.modal-description').text(errorMessage);
                errorResponseModal.modal('show');
            }
        });
    });
});