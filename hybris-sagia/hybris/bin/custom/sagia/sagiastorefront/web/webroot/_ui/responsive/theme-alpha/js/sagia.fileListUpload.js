$(function () {
    var isAdvancedUpload = function() {
        var div = document.createElement('div');
        return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
    }();

    $(".js-upload-files-list").each(function() {
        /*var newCodes = [];
        var removeCodes = [];*/

            var $uploadList = $(this);
            var $uploadModal = $uploadList.find(".js-upload-list-modal");
            var droppedFiles = null;
            var $uploadFiles = $uploadList.find(".js-upload-files");
            var $confirmedFiles = $uploadList.find(".js-confirmed-files");

        if (isAdvancedUpload) {
            var rootElement = $uploadList.find('.js-form-input-file-box');
            rootElement.addClass('hasAdvancedUpload');

            rootElement.on('drag dragstart dragend dragover dragenter dragleave drop', function (e) {
                e.preventDefault();
                e.stopPropagation();
            }).on('dragover dragenter', function () {
                $(this).addClass('isDragover');
            }).on('dragleave dragend drop', function () {
                $(this).removeClass('isDragover');
            }).on('drop', function (e) {
                droppedFiles = e.originalEvent.dataTransfer.files;

                var keys = Object.keys(droppedFiles);
                keys.forEach(function(key) {
                    var file = droppedFiles[key];
                    var data = new FormData();
                    data.append('CSRFToken', ACC.config.CSRFToken);
                    data.append("file", file);
                    uploadFile(data, file.name);
                });
            });
        }

        //label name update for traditional input file
        $uploadList.find('.js-input-file').on("change", function(e) {
            var $input = $(this);

            var files = e.target.files;
            var keys = Object.keys(files);
            keys.forEach(function(key) {
                var file = files[key];
                var data = new FormData();
                data.append("file", file);
                uploadFile(data, file.name);
            });
            e.target.value = "";
        });

        function uploadFile(data, fileName) {
            var code = getRequestCode();
            data.append("code", code);

            $.ajax({
                url: ACC.config.encodedContextPath + '/file/send-data',
                data: data,
                enctype: 'multipart/form-data',
                cache: false,
                contentType: false,
                processData: false,
                method: 'POST',
                ajaxHideLoadingIndicator: true,
                beforeSend: function () {
                    var template = $uploadList.find(".js-append-file").clone();
                    template.attr("data-code", code);
                    template.removeClass("js-append-file");

                        template.find(".js-remove-file").click(
                            function (event)         {
                                event.preventDefault();

                                var $element = $(this).closest(".js-loaded-file");

                                $element.remove();
                            }
                        );

                        template.find(".js-file-name").text(fileName);
                        template.find(".js-file-code-input").attr('file-name', fileName);
                        $uploadList.find(".js-file-list").append(template);
                    }
                }).done(function (response) {

                    var uploadedFileTemplate = $uploadList.find(".js-loaded-file[data-code= " + response.code + "]");
                    uploadedFileTemplate.attr("data-file-code", response.fileCode);
                    uploadedFileTemplate.find(".js-remove-file").show();
                    uploadedFileTemplate.find(".js-loading-spinner").hide();
                    uploadedFileTemplate.find(".js-file-code-input").val(response.fileCode);
                })
                    .fail(function (error) {
                    });
            }

        /*Open Modal Button*/
        $uploadFiles.click(function(event) {
            event.preventDefault();

            $uploadModal.find(".js-file-list").empty();
            /*newCodes = [];
            removeCodes = [];*/
            $uploadModal.modal('show');
        });

        /*Close Modal Button*/
        $uploadModal.find(".js-close-modal-btn").click(function() {
            $uploadModal.modal('hide');
        });

            /*Confirm Button*/
            $uploadModal.find(".js-confirm-modal-btn").click(
                function() {
                    var uploadedFiles = $uploadModal.find(".js-file-list .js-loaded-file");
                    $confirmedFiles.append(uploadedFiles);
                    initRemoveEvent();
                    $uploadModal.modal('hide');
                }
            );

            $uploadList.on('addFile', function(event, data, cleanFileList) {
                if (cleanFileList) {
                    $confirmedFiles.empty();
                }

                data.forEach(function(file) {
                    var template = $uploadList.find(".js-append-file").clone();
                    template.removeClass("js-append-file");
                    template.attr("data-file-code", file.value);
                    template.find(".js-remove-file").show();
                    template.find(".js-loading-spinner").hide();
                    template.find(".js-file-code-input").val(file.value);
                    template.find(".js-file-name").text(file.fileName);
                    $confirmedFiles.append(template);
                    initRemoveEvent();
                });
            });

            function initRemoveEvent() {
                $confirmedFiles.find(".js-remove-file").unbind('click');
                $confirmedFiles.find(".js-remove-file").click(
                    function(event) {
                        event.preventDefault();

                        var $element = $(this).closest(".js-loaded-file");
                        var code = $element.attr("data-file-code");
                        var data = {
                            code: code
                        };
                        $element.remove();
                        $.ajax({
                            url: ACC.config.encodedContextPath + "/file/remove-data",
                            data: JSON.stringify(data),
                            contentType: 'application/json',
                            method: 'DELETE'
                        });
                    }
                );
            }
        }
    );

    function getRequestCode() {
        return Math.random().toString(36).substr(2, 5);
    }
});




