var Draft = function () {

    var pageId = window.location.pathname;

    function saveDraft() {

        $(".js-save-draft").click(
            function () {
                var formId = $(this).data("targetForm");
                var form = document.getElementById(formId);
                var data = toFormObject(form);
                var serviceId = $(this).data("serviceId");

                if (serviceId) {
                    data.append("id", serviceId);
                } else {
                    data.append("id", formId);
                }

                var pathNameURL = window.location.pathname;
                if (window.location.href.indexOf("?") > 0) {
                    var indexOfQuestion = window.location.href.indexOf("?");
                    var parametersString = window.location.href.substr(indexOfQuestion);
                    pathNameURL = pathNameURL + parametersString;
                }
                data.append("url", pathNameURL);

                $.ajax({
                    url: ACC.config.encodedContextPath + '/draft/save',
                    data: data,
                    enctype: 'multipart/form-data',
                    cache: false,
                    contentType: false,
                    processData: false,
                    method: 'POST'
                }).done(function(response) {
                    $('.js-load-draft').show();
                    $(".js-remove-draft").show();
                }).fail(function(error){})
            }
        )
    }

    return {
        init: function () {
            saveDraft();
        }
    }
}();

function toFormObject( form ) {
    var parameters = [];
    var data = new FormData();

    var elements = form.querySelectorAll( "input, select, textarea" );
    for (var i = 0; i < elements.length; ++i) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if(name && value) {
            var domElement = $("[name='" + name + "']");
            var type = domElement.attr('type') || domElement.prev().prop('nodeName');

            if (type === 'file') {
                if (element.files && element.files[0]) {
                    var attachmentName = domElement.data('attachmentName');
                    data.append('attachments', attachmentName);
                    var inputName = domElement.attr('name');
                    data.append('attachmentsNames', inputName);
                    var file = element.files[0];
                    data.append("files", file)
                }
            } else if (type === 'checkbox') {
                if (element.checked) {
                    parameters.push({
                        name: domElement.attr('name'),
                        type: "checkbox",
                        value: domElement.val()
                    });
                }
            } else if (type === 'radio') {
                if (domElement.is(':checked')) {
                    parameters.push({
                        name: domElement.attr('name'),
                        type: "radio",
                        value: domElement.val()
                    });
                }
            } else if (domElement.hasClass('js-mock-input')) {
                var fileId = domElement.val();
                data.append('draftFiles', fileId);
            } else if (domElement.hasClass('js-file-code-input')) {
                parameters.push({
                    name: name,
                    type: type,
                    value: value,
                    fileName: domElement.attr('file-name')
                });
            } else {
                parameters.push({
                    name: name,
                    type: type,
                    value: value
                });
            }
        }
    }

    data.append("paramsCount", parameters.length);
    parameters.forEach(function(parameter, index) {
        data.append("parameters[" + index + "][name]", parameter.name);
        data.append("parameters[" + index + "][type]", parameter.type ? parameter.type : "");
        data.append("parameters[" + index + "][value]", parameter.value ? parameter.value : "");

        if (parameter.fileName) {
            data.append("parameters[" + index + "][fileName]", parameter.fileName);
        }
    });

    return data;
}


jQuery(document).ready(function () {
    Draft.init();
});

