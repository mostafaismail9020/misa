function sendPaymentRatingFeedback() {
    	var numberOfStars = 0,
        feedbackText = $('#extensionReason').val(),
        serviceId = $('#serviceId').val();
    	$('.ratingModule.review > div').each(function () {
        numberOfStars++;
        if ($(this).hasClass('selected')) {
            return false;
        }
    });

    $.ajax({
        url: ACC.config.encodedContextPath + "/sendFeedback",
        type: 'POST',
        data: {"numberOfStars": numberOfStars, "feedbackText": feedbackText, "serviceId": serviceId},
        beforeSend: function(xhr) {
            xhr.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
        },
        success : function(response) {
            window.location.href = ACC.config.encodedContextPath;
         },
         error : function(xhr, status, error) {
           $('#errorResponseModal').find('.modal-description').text(getI18nText("general.error"));
           $('#errorResponseModal').modal('show');
         }
    });
}

function billPaymentLogOut(isOutstandingFee){
	var url ;
	if(isOutstandingFee){
		url = ACC.config.encodedContextPath + '/logout'
	}else{
		url = ACC.config.encodedContextPath + '/payments-overview'
	}
	
    window.location.replace(url);
}

    $('#paymentFeedbackModal').on('hide.bs.modal', function () {
        window.location.href = ACC.config.encodedContextPath;
    });

(function () {

    $('.ratingModule-star.review').mouseenter(function () {
        $(".ratingModule.review>div.selected").removeClass("selected");
        $(this).addClass('selected');

        var flag = true,
            index = 0;
        $('.ratingModule.review > div').each(function () {
            if (flag) {
                if ($(this).hasClass('selected')) {
                    flag = false;
                }
                $(this).html('<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="24px" height="24px" viewBox="0.062 0.062 24 24" enable-background="new 0.062 0.062 24 24" xml:space="preserve">' +
                    '<path fill="#7ACC61" d="M23.423,10.986c0.468-0.46,0.633-1.136,0.431-1.764C23.658,8.6,23.124,8.144,22.479,8.05' +
                    'l-5.75-0.845c-0.246-0.036-0.458-0.191-0.567-0.415l-2.57-5.267c-0.282-0.587-0.877-0.96-1.528-0.959' +
                    'c-0.651,0-1.237,0.368-1.526,0.96L7.964,6.79c-0.108,0.223-0.32,0.378-0.566,0.415L1.646,8.05C1.001,8.144,0.467,8.6,0.271,9.222' +
                    'c-0.203,0.622-0.036,1.305,0.431,1.764l4.161,4.099c0.179,0.175,0.259,0.427,0.218,0.673l-0.983,5.789' +
                    'c-0.11,0.648,0.15,1.295,0.678,1.682c0.528,0.391,1.216,0.441,1.794,0.131l5.144-2.732c0.219-0.115,0.481-0.115,0.7,0l5.144,2.732' +
                    'c0.573,0.307,1.271,0.256,1.794-0.131c0.527-0.387,0.789-1.039,0.677-1.682l-0.981-5.789c-0.042-0.246,0.039-0.498,0.217-0.673' +
                    'L23.423,10.986L23.423,10.986z"/>' +
                    '</svg>');
                index++;
            } else {
                $(this).html('<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="24px" height="24px" viewBox="0 0 24 24" enable-background="new 0 0 24 24" xml:space="preserve">' +
                    '<path fill="#7B838C" d="M5.588,22.997c-0.36,0-0.706-0.115-1-0.332c-0.528-0.389-0.788-1.034-0.677-1.684l0.982-5.787c0.042-0.247-0.04-0.498-0.217-0.673l-4.162-4.1c-0.467-0.46-0.633-1.136-0.431-1.764C0.287,8.03,0.813,7.581,1.459,7.486L7.21,6.641c0.246-0.036,0.457-0.191,0.566-0.415l2.572-5.267C10.638,0.367,11.223,0,11.876,0c0.652,0,1.252,0.378,1.53,0.963l2.569,5.262c0.108,0.225,0.319,0.379,0.565,0.416l5.751,0.845c0.644,0.093,1.185,0.555,1.378,1.177c0.199,0.624,0.034,1.299-0.434,1.759l-4.16,4.1c-0.178,0.176-0.259,0.427-0.217,0.671l0.981,5.79c0.114,0.637-0.152,1.299-0.679,1.684c-0.29,0.215-0.637,0.328-1,0.328l0,0c-0.275,0-0.549-0.068-0.791-0.197l-5.146-2.733c-0.213-0.113-0.484-0.113-0.7,0.001l-5.144,2.732C6.137,22.928,5.862,22.997,5.588,22.997z M11.88,1.08c-0.245,0-0.453,0.132-0.561,0.353L8.748,6.699c-0.266,0.544-0.781,0.922-1.38,1.01L1.617,8.554c-0.24,0.035-0.428,0.198-0.504,0.435c-0.077,0.24-0.017,0.488,0.161,0.664l4.162,4.1c0.428,0.423,0.624,1.029,0.523,1.623l-0.982,5.787c-0.042,0.249,0.052,0.485,0.253,0.634c0.192,0.141,0.438,0.159,0.646,0.048l5.144-2.733c0.527-0.279,1.188-0.279,1.713,0l5.145,2.733c0.205,0.107,0.461,0.087,0.646-0.048c0.197-0.145,0.296-0.391,0.254-0.629l-0.982-5.793c-0.102-0.591,0.094-1.197,0.523-1.622l4.16-4.1c0.178-0.175,0.238-0.424,0.161-0.665c-0.071-0.231-0.269-0.399-0.503-0.433l-5.753-0.846c-0.601-0.089-1.116-0.467-1.38-1.012l-2.57-5.264C12.331,1.217,12.114,1.08,11.88,1.08z"/>' +
                    '</svg>');

            }
        });
        if (index > 3) {
            $('.formTextArea.formTextArea_slim').attr('hidden', 'hidden');
        } else {
            $('.formTextArea.formTextArea_slim').removeAttr('hidden');
        }
    });

})();