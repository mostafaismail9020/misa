(function () {

    if (Boolean($('.js-email-sended-message').val())) {

        var messageTemplate = getI18nText('messages.email.send');
        messageTemplate = messageTemplate.replace(/{email}/, 'name');
        showSendEmailPopup(messageTemplate);
    }
})();

function showSendEmailPopup(text) {
    var $emailSendModal = $("#emailSendedModal");
    $emailSendModal.find(".js-message").text(text);
    $emailSendModal.find(".js-close-btn").click(function() {
        $emailSendModal.modal('hide');
    });
    $emailSendModal.modal('show');
}