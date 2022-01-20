$(document).on("click", "#financialSurveyNewMessageBtn", function (event) {
   // var srId = $(this).data('current-srid');
    var srId = "Q4_2021" ;
    var token = $('input[name="CSRFToken"]').attr('value')
    var inputMessage = $('#financialSurveyInputNewMessage').val();

    if ($.trim(inputMessage) == '') {
        $("#financialSurveyInputNewMessage").closest('.form-group').addClass('has-error');
        return;
    }
    $.ajax({
        type: "POST",
        url: ACC.config.encodedContextPath + "/my-sagia/financial-survey/complete/" + srId + "/update",
        data: JSON.stringify({
            TextMsg: inputMessage
        }),
        displayErrorMessageInModal: false,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        }
    })
        .done(function (data) {
            if ($("#financialSurveyInputNewMessage").closest('.form-group').hasClass('has-error')) {
                $("#financialSurveyInputNewMessage").closest('.form-group').removeClass('has-error')
            }
            var complaintMessages = data.messages;
            var complaintMessagesDivContent = $('#financialSurveyMessagesList');
            complaintMessagesDivContent.empty();
            var template = document
                .getElementById("messages-template");
            var templateHtml = template.innerHTML;
            var listHtml = "";
            for (var key in complaintMessages) {
                listHtml += templateHtml
                    .replace(
                        /{{commentBy}}/g,
                        complaintMessages[key]["commentBy"])
                    .replace(
                        /{{commentDate}}/g, complaintMessages[key]["commentDate"])
                    .replace(
                        /{{content}}/g,
                        complaintMessages[key]["content"]);
            }
            document
                .getElementById("financialSurveyMessagesList").innerHTML = listHtml;
            document.getElementById("financialSurveyInputNewMessage").value = '';
        })
        .fail(function (xhr, status, error) {
            if ($("#financialSurveyInputNewMessage").closest('.form-group').hasClass('has-error')) {
                $("#financialSurveyInputNewMessage").closest('.form-group').removeClass('has-error')
            }
            var jsonResponse = xhr.responseJSON;
            if (typeof jsonResponse !== 'undefined' && jsonResponse !== null) {
                var errorMessage = jsonResponse.sagiaExceptionResponse.message;
                $('#financialSurveyErrorUpdateLabel').text(errorMessage);
            }
            $('#financialSurveyErrorUpdateLabel').show();
        })
});

if ($('#complaintInProgress').text().length > 1) {
    $('#errorResponseModal').find('.modal-description').text($('#complaintInProgress').text());
    $('#errorResponseModal').modal('show');
    e.preventDefault();
}
