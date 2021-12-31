$(document).on("click", "#newMessageBtn", function (event) {
    var srId = $(this).data('current-srid');
    var token = $('input[name="CSRFToken"]').attr('value')
    var inputMessage = $('#inputNewMessage').val();

    if ($.trim(inputMessage) == '') {
        $("#inputNewMessage").closest('.form-group').addClass('has-error');
        return;
    }
    $.ajax({
        type: "POST",
        url: ACC.config.encodedContextPath + "/complaints/" + srId + "/update",
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
            if ($("#inputNewMessage").closest('.form-group').hasClass('has-error')) {
                $("#inputNewMessage").closest('.form-group').removeClass('has-error')
            }
            var complaintMessages = data.compAndEnqHdrToTextNav;
            var complaintMessagesDivContent = $('#messagesList');
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
                        /{{commentDate}}/g, complaintMessages[key]["commentDate"].dateFormatted)
                    .replace(
                        /{{messageComments}}/g,
                        complaintMessages[key]["comments"]);
            }
            document
                .getElementById("messagesList").innerHTML = listHtml;
            document.getElementById("inputNewMessage").value = '';
        })
        .fail(function (xhr, status, error) {
            if ($("#inputNewMessage").closest('.form-group').hasClass('has-error')) {
                $("#inputNewMessage").closest('.form-group').removeClass('has-error')
            }
            var jsonResponse = xhr.responseJSON;
            if (typeof jsonResponse !== 'undefined' && jsonResponse !== null) {
                var errorMessage = jsonResponse.sagiaExceptionResponse.message;
                $('#errorUpdateLabel').text(errorMessage);
            }
            $('#errorUpdateLabel').show();
        })
});

if ($('#complaintInProgress').text().length > 1) {
    $('#errorResponseModal').find('.modal-description').text($('#complaintInProgress').text());
    $('#errorResponseModal').modal('show');
    e.preventDefault();
}

$('#attachmentList').on('click', 'div', function (e) {
    var objectId = $(this).data('objectId');
    var documentId = $(this).data('documentId');
    window.open(ACC.config.encodedContextPath + "/attachment/pdf/" + objectId + "/" + documentId, '_blank');
});
