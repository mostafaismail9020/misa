function showStatusModal(statusCode){
    $(document).ready(function () {
        var text;
        if(statusCode == 'I')
            text = 'Your request is in process.Changes cannot be made';
        else if(statusCode == "V")
            text = 'Your request has been verified.';

        if(text != undefined && text != null){
            $('#status-text').text(text);
            $('#status-modal').modal('toggle');
        }

    });
}
