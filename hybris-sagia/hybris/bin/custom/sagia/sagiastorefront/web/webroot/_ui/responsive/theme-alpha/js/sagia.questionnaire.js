(function () {


    $(".js-survey .js-required").on("blur change", function validateFields(){
        var element = $(this);
        if (element.is(':checkbox') && !element.prop('checked')) {
            element.closest(".form-group").addClass("has-error");
        } else if (!element.val()) {
            element.closest(".form-group").addClass("has-error");
        }
        else {
            element.closest(".form-group").removeClass("has-error");
        }

    });


    $(".js-survey").submit(function(event){
        event.preventDefault();
        var hasErrors = false;
        $(".js-required").each(function () {
            var element = $(this);
            if (!element.val()) {
                element.closest(".form-group").addClass("has-error");
                hasErrors = true;
            }

            if (element.is(':checkbox') && !element.prop('checked')) {
                element.closest(".form-group").addClass("has-error");
                hasErrors = true;
            }
        });

        if (hasErrors){
            $("#surveyResultValidateModal").modal('show');
            return;
        }

        var $form = $(this);
        var survey = $form.data("survey");
        var surveyVersion = $form.data("surveyVersion");
        var transactionId = $form.data("transactionId");
        var application = $form.data("application");
        var isFetchedFromNotificationService = $form.data("isfetchedfromnotificationservice");

        var data = {
            surveyId: survey,
            applicationId: application,
            transactionId: transactionId,
            surveyVersion: surveyVersion,
            isFetchedFromNotificationService: isFetchedFromNotificationService,
            answers: []
        };

        var $surveyForm = $(".js-survey");
        $surveyForm.find("input[type=text], input[type=number], textarea").each(
            function() {
                var $input = $(this);
                var quest = $input.data("question");
                var answer = $input.data("answer");
                var answType = $input.data("question-type");
                var value = $input.val();

                if (value) {
                    data.answers.push({
                        Surveyid: survey,
                        QuestID: quest,
                        AnswerID: answer,
                        Txtlg: value,
                        AnswType: answType
                    })
                }
            }
        );

        $surveyForm.find("input[type=radio]:checked, input[type=checkbox]:checked").each(
            function() {
                var $input = $(this);
                var quest = $input.data("question");
                var answer = $input.data("answer");
                var control = $input.data("control");
                var value = $input.val();
                var answType = $input.data("question-type");

                if (value) {
                    data.answers.push({
                        Surveyid: survey,
                        QuestID: quest,
                        AnswerID: answer,
                        Txtlg: control,
                        AnswType: answType
                    })
                }
            }
        );

        $.postJsonData(ACC.config.encodedContextPath + "/my-sagia/questionnaires/send-survey-data", data)
            .done(function(response){
                $('#errorResponseModal').find('.modal-description').text(getI18nText("general.request.submitted"));
                $('#errorResponseModal').modal('show');
                $('#errorResponseModal').on('hide.bs.modal', function () {
                    window.location.href = window.location.href = ACC.config.encodedContextPath + "/my-sagia/sagia-profile#questionnairesTab";
                });
            })
            .fail(function(error) {
                 $('#errorResponseModal').find('.modal-description').text(error.responseJSON.sagiaExceptionResponse.message);
                 $('#errorResponseModal').modal('show');
            })
    })
})();