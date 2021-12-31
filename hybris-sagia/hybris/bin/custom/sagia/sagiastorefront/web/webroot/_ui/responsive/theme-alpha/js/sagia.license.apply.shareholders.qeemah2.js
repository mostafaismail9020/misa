var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};

$(function() {
    SAGIA.license.apply.handleShareholdersQM2 = function() {
        var addShareholderQM2NoShareholderSection = $("#addShareholderQM2NoShareholderSection");
        var shareholderQM2TableSection = $("#shareholderQM2TableSection");
        if(SAGIA.license.apply.data.qeemah2Data.shareholders.length) {
            addShareholderQM2NoShareholderSection.hide();
            shareholderQM2TableSection.show();
            shareholderQM2TableSection.find(".addExistingButton").prop("disabled", false);
            shareholderQM2TableSection.find(".addNewButton").prop("disabled", false);
            $("#shareholdersNextButton").prop("disabled", false);

            var draft = SAGIA.license.apply.draft;
            if (draft && SAGIA.license.apply.draft.isPresent && !draft.handledForShareholders) {
                redrawShareholderQM2TableSection();
                draft.handledForShareholders = true;
            }

        } else {
            addShareholderQM2NoShareholderSection.show();
            shareholderQM2TableSection.find(".addExistingButton").prop("disabled", false);
            shareholderQM2TableSection.find(".addNewButton").prop("disabled", false);
            $("#shareholdersNextButton").prop("disabled", false);
            shareholderQM2TableSection.hide();
        }
        loadQeemah2Data();
    };

    var loadQeemah2Data = function() {
        $.ajax(ACC.config.encodedContextPath + controllerUrl + "/dropdownsQeemah2", {
            type: "GET",
            responseType: "application/json;charset=utf-8",
            contentType: "application/json;charset=utf-8",
            cache: false,
            success: function (data) {
                var jsonData = JSON.parse(data);

                var nationality = $("#shareholdersQM2 #nationality");
                var previousNationality = nationality.val();
                nationality.find("option").remove();
                nationality.append(new Option("", "", false, false));
                jsonData.countries.forEach(function (currentValue) {
                    nationality.append(new Option(currentValue.nationalityText, currentValue.nationality, false, false)); //retrieved via countries array
                });
                if(previousNationality) {
                    nationality.val(previousNationality).trigger("blur").trigger("change");
                } else {
                    nationality.val(null);
                }

                var legalStatus = $("#shareholdersQM2 #legalStatus");
                var previousLegalStatus = legalStatus.val();
                legalStatus.find("option").remove();
                legalStatus.append(new Option("", "", false, false));
                jsonData.legalStatusShareholder.forEach(function (currentValue) {
                    legalStatus.append(new Option(currentValue.legalStatusText, currentValue.legalStatus, false, false)); //retrieved via countries array
                });
                if(previousLegalStatus) {
                    legalStatus.val(previousLegalStatus).trigger("blur").trigger("change");
                } else {
                    legalStatus.val(null);
                }
            }
        });
    };

    $(document).on("change","#addShareholderQM2NewSection #legalStatus", function () {
        var sharesPercentage = $("#addShareholderQM2NewSection").find("#sharesPercentage");
        if($(this).find(":selected").val() === 'BRFC'){
            sharesPercentage.val(100);
            sharesPercentage.attr('disabled', 'disabled');
            sharesPercentage.removeClass("placeholder-shown"); //fix for ie11
        } else {
            sharesPercentage.val("");
            sharesPercentage.removeAttr('disabled');
        }
    });

    $(document).on("click", "#shareholdersQM2 .addNewButton", function (e) {
        $("#shareholdersQM2").find(".addNewButton").prop("disabled", true);
        $("#shareholdersNextButton").prop("disabled", true);
        $("#addShareholderQM2NewSection").show();
        if(!SAGIA.license.apply.data.hasShareholdersTypePerson) {
            $("#shareholdersQM2 #shareholderType #qm2PersonType").parent().hide();
            $("#shareholdersQM2 #shareholderType #qm2EntityType").trigger('click');
        } else {
            $("#shareholdersQM2 #shareholderType #qm2PersonType").parent().show();
        }
    });

    $(document).on("click", "#shareholdersQM2 #shareholderType input[type=radio]", function (e) {
        var shareholderType = $("#shareholdersQM2").find("#shareholderType input:checked").val();
        var legalStatus = $("#addShareholderQM2NewSection #legalStatus");

        if (shareholderType === "Person") {
            legalStatus.parent().hide();
        } else {
            legalStatus.parent().show();
        }
    });

    var removeErrorIfExists = function () {
        $('#shareholdersQM2 .form-group').each(function () {
            if ($(this).hasClass('has-error')) {
                $(this).removeClass('has-error');
            }

            if ($(this).find('.help-block:first').length) {
                $(this).find('.help-block:first').text('');
            }

            if ($(this).closest('.formSelectBox').length) {
                $(this).closest('.formSelectBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputBox').length) {
                $(this).closest('.formInputBox').find('.help-block').text('');
            }

            if ($(this).closest('.formInputFile').length) {
                $(this).closest('.formInputFile').find('.help-block').text('');
            }
        });
    };

    $(document).on("click","#addShareholderQM2NewSection .cancelButton", function (e) {
        $("#shareholdersQM2").find(".addNewButton").prop("disabled", false);
        $("#addShareholderQM2NewSection").hide();
        $("#shareholdersNextButton").prop("disabled", false);
        removeErrorIfExists();
        clearFormFields();
    });

    $(document).on("click", "#addShareholderQM2NewSection .addButton", function () {
        $("#shareholdersNextButton").prop("disabled", true);
        var addShareholderQM2NewSection = $("#addShareholderQM2NewSection");
        var addShareholderQM2NoShareholderSection = $("#addShareholderQM2NoShareholderSection");
        var shareholderQM2TableSection = $("#shareholderQM2TableSection");

        var id = addShareholderQM2NewSection.data("id");

        var shareholder = {
            id: new Date().getTime(),
            type: addShareholderQM2NewSection.find("#shareholderType input:checked").val(),
            name: addShareholderQM2NewSection.find("#name").val(),
            nationality: addShareholderQM2NewSection.find("#nationality").val(),
            nationalityText: addShareholderQM2NewSection.find("#nationality").find(":selected").text(),
            sharesPercentage: addShareholderQM2NewSection.find("#sharesPercentage").val()
        };

        if (shareholder.type === "Entity") {
            shareholder.legalStatus = addShareholderQM2NewSection.find("#legalStatus").val();
            shareholder.legalStatusText = addShareholderQM2NewSection.find("#legalStatus").find(":selected").text();
        }

        var map = {};
        map[0] = [];
        map[0].push(shareholder);

        var temp = { arr : SAGIA.license.apply.data.qeemah2Data };
        var qeemah2Data = $.extend(true, {}, temp);
        var shareholders = qeemah2Data.arr.shareholders;


        if (id) {
            getShareholderDataByIdFromArray(id, shareholders).sharesPercentage = 0;
        }

        map[1] = shareholders;


        var qeemah2InfoTemp = { arr : SAGIA.license.apply.data};
        var qeemah2InfoData = $.extend(true, {}, qeemah2InfoTemp);
        var shareholdersQm2Info = qeemah2InfoData.arr.qeemah2Data.shareholders;
        shareholdersQm2Info.push(shareholder);

        var promise = [];
        promise.push(validateLicenseData('#addShareholderQM2NewSection', 'new-shareholder-qeemah2-entity', qeemah2InfoData.arr, function () {}));
        promise.push(validateLicenseData('#addShareholderQM2NewSection', 'new-shareholder-percentage-QM2', map, function () {
                },
                function (xhr, status, error) {
                    var formErrors = xhr.responseJSON.formErrors;
                    $.each(formErrors, function (key, errorMessage) {
                        $("#addShareholderQM2NewSection" + ' [name="' + key + '"]').closest('.form-group').addClass('has-error');
                        $("#addShareholderQM2NewSection" + ' [name="' + key + '"]').closest('.form-group').siblings('.help-block').text(errorMessage);
                    });
                }
            ));

        $.when.apply($, promise).done(function (results) {
            if (!id) {
                SAGIA.license.apply.data.qeemah2Data.shareholders.push(shareholder);
            } else {
                shareholder = getShareholderDataById(id);
                shareholder.type = addShareholderQM2NewSection.find("#shareholderType input:checked").val();
                shareholder.name = addShareholderQM2NewSection.find("#name").val();
                shareholder.nationality = addShareholderQM2NewSection.find("#nationality").val();
                shareholder.nationalityText = addShareholderQM2NewSection.find("#nationality").find(":selected").text();
                shareholder.sharesPercentage = addShareholderQM2NewSection.find("#sharesPercentage").val();
            }

            clearFormFields();

            addShareholderQM2NewSection.hide();
            addShareholderQM2NoShareholderSection.hide();
            shareholderQM2TableSection.show();
            shareholderQM2TableSection.find(".addExistingButton").prop("disabled", false);
            shareholderQM2TableSection.find(".addNewButton").prop("disabled", false);
            $("#shareholdersNextButton").prop("disabled", false);
            redrawShareholderQM2TableSection();
        });
    });

    var clearFormFields = function() {
        var addShareholderQM2NewSection = $("#addShareholderQM2NewSection");
        addShareholderQM2NewSection.find("#shareholderType input").prop("checked", false);
        addShareholderQM2NewSection.find("#name").val("");
        addShareholderQM2NewSection.find("#nationality").val("").trigger("blur").trigger("change");
        addShareholderQM2NewSection.find("#sharesPercentage").val("");
        addShareholderQM2NewSection.find("#legalStatus").val("").trigger("blur").trigger("change");
        addShareholderQM2NewSection.data("id", null);
    };

    var getShareholderDataByIdFromArray = function(shareholderId, array) {
        for(var indexShareholder = 0; indexShareholder < array.length; indexShareholder++) {
            var shareholder = array[indexShareholder];
            if (shareholder.id === shareholderId) {
                return shareholder;
            }
        }
        return null;
    };

    var removeShareholder = function (shareholderId) {
        for (var index = 0; index < SAGIA.license.apply.data.qeemah2Data.shareholders.length; index++) {
            var existingShareholder = SAGIA.license.apply.data.qeemah2Data.shareholders[index];
            if (existingShareholder.id === shareholderId) {
                SAGIA.license.apply.data.qeemah2Data.shareholders.splice(index, 1);
                return;
            }
        }
    };
    var getShareholderDataById = function (shareholderId) {
        for (var index = 0; index < SAGIA.license.apply.data.qeemah2Data.shareholders.length; index++) {
            var shareholder = SAGIA.license.apply.data.qeemah2Data.shareholders[index];
            if (shareholder.id === shareholderId) {
                return shareholder;
            }
        }
        return null;
    };
    var redrawShareholderQM2TableSection = function () {
        for (var index = 0; index < SAGIA.license.apply.data.qeemah2Data.shareholders.length; index++) {
            var existingShareholder = SAGIA.license.apply.data.qeemah2Data.shareholders[index];
            var existingTable = $("#shareholderQM2TableSection table tbody tr[data-id='" + existingShareholder.id + "']");
            if (existingTable.length) {
                existingTable.empty().append("" +
                    "<td>" + existingShareholder.name + "</td>" +
                    "<td title='" + existingShareholder.name + "'>" + existingShareholder.type + "</td>" +
                    "<td>" + existingShareholder.sharesPercentage + "%</td>" +
                    "<td>" + existingShareholder.nationalityText + "</td>" +
                    "<td>" + (existingShareholder.legalStatusText ? existingShareholder.legalStatusText : "-") + "</td>" +
                    "<td class='tableModule-bodyItem-action'>" +
                    "   <button type='button' class='removeButton btn btn_link'><svg width=\"18\" height=\"18\" viewBox=\"0 0 18 18\" xmlns=\"http://www.w3.org/2000/svg\"><g stroke=\"#5CC83B\" fill=\"none\"><path d=\"M4 5h10l-1.017 12h-8.068z\"/><path d=\"M7 7v8\"/><path d=\"M9 7v8\"/><path d=\"M11 7v8\"/><path d=\"M4 3h10\"/><path d=\"M7 3c0-1.104.895-2 2-2 1.104 0 2 .896 2 2\"/></g></svg></button>" +
                    "   <button type='button' class='editButton btn btn_link'><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"18\" height=\"18\" viewBox=\"0 0 18 18\"><path fill=\"#5CC83B\" d=\"M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z\"/></svg></button>" +
                    "</td>");
            } else {
                $("#shareholderQM2TableSection table tbody").append("" +
                    "<tr data-id='" + existingShareholder.id + "' data-type='" + existingShareholder.type + "'>" +
                    "   <td title='" + existingShareholder.name + "'>" + existingShareholder.name + "</td>" +
                    "   <td>" + existingShareholder.type + "</td>" +
                    "   <td>" + existingShareholder.sharesPercentage + "%</td>" +
                    "   <td>" + existingShareholder.nationalityText + "</td>" +
                    "   <td>" + (existingShareholder.legalStatusText ? existingShareholder.legalStatusText : "-") + "</td>" +
                    "   <td class='tableModule-bodyItem-action'>" +
                    "       <button type='button' class='removeButton btn btn_link'><svg width=\"18\" height=\"18\" viewBox=\"0 0 18 18\" xmlns=\"http://www.w3.org/2000/svg\"><g stroke=\"#5CC83B\" fill=\"none\"><path d=\"M4 5h10l-1.017 12h-8.068z\"/><path d=\"M7 7v8\"/><path d=\"M9 7v8\"/><path d=\"M11 7v8\"/><path d=\"M4 3h10\"/><path d=\"M7 3c0-1.104.895-2 2-2 1.104 0 2 .896 2 2\"/></g></svg></button>" +
                    "       <button type='button' class='editButton btn btn_link'><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"18\" height=\"18\" viewBox=\"0 0 18 18\"><path fill=\"#5CC83B\" d=\"M15.434 14.934c0 .276-.224.5-.5.5h-14.934c-.276 0-.5-.224-.5-.5s.224-.5.5-.5h14.934c.276 0 .5.223.5.5zm-13.152-1.266c-.134-.133-.182-.33-.124-.51l1.485-4.567.007-.013.056-.098.048-.072.011-.016 7.577-7.58.003-.002c1.005-1.001 2.75-1 3.751.001.502.501.778 1.168.778 1.877 0 .71-.276 1.377-.778 1.878l-.004.003-7.574 7.575-.013.009-.075.05-.093.054-.014.008-4.53 1.521-.159.026c-.129 0-.257-.05-.352-.144zm10.175-12.448l1.115 1.116 1.116 1.116c.121-.233.186-.493.186-.763 0-.442-.173-.858-.485-1.17-.503-.503-1.316-.619-1.932-.299zm-7.632 7.525l2.339 2.339 6.87-6.872-1.17-1.17-1.169-1.17-6.87 6.873zm-1.408 3.777l2.824-.948-1.899-1.897-.925 2.845z\"/></svg></button>" +
                    "   </td>" +
                    "</tr>");
            }
        }
    };

    $(document).on("click", "#shareholderQM2TableSection .removeButton", function () {
        var trElement = $(this).parents("tr");
        removeShareholder(trElement.data("id"));
        trElement.remove();
        SAGIA.license.apply.handleShareholdersQM2();
    });
    $(document).on("click", "#shareholderQM2TableSection .editButton", function () {
        var addShareholderQM2NewSection = $("#addShareholderQM2NewSection");
        var legalStatus = $("#shareholdersQM2 #legalStatus");
        var shareholdersQM2 = $("#shareholdersQM2");
        var trElement = $(this).parents("tr");
        var id = trElement.data("id");
        var type = trElement.data("type");
        var shareholder = getShareholderDataById(id);
        addShareholderQM2NewSection.data("id", id);
        addShareholderQM2NewSection.data("type", type);
        addShareholderQM2NewSection.find("#shareholderType input[value='" + type + "']").prop("checked", true);
        addShareholderQM2NewSection.find("#name").val(shareholder.name);
        addShareholderQM2NewSection.find("#nationality").val(shareholder.nationality).trigger("blur").trigger("change");
        addShareholderQM2NewSection.find("#sharesPercentage").val(shareholder.sharesPercentage);
        if(type === "Entity") {
            legalStatus.parent().show();
            legalStatus.val(shareholder.legalStatus).trigger("blur").trigger("change");
        } else {
            legalStatus.val("").trigger("blur").trigger("change");
            legalStatus.parent().hide();
        }

        addShareholderQM2NewSection.show();
        shareholdersQM2.find(".addNewButton").prop("disabled", true);
    });
});
