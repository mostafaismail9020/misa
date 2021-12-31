$( document ).ready(function() {
        $('#XSemptyNotificationsModal').find('.modal-description').text(getI18nText("general.nonewnotifications"));
        $('#XSemptyNotificationsModal').modal('show');
    });
    $('#XSemptyNotificationsModal').on('hide.bs.modal', function() {
        window.location.href = ACC.config.encodedContextPath;
})