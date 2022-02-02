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
                existingTable.empty().append('' +
                    "<td>" + existingShareholder.name + "</td>" +
                    "<td title='" + existingShareholder.name + "'>" + existingShareholder.type + "</td>" +
                    "<td>" + existingShareholder.sharesPercentage + "%</td>" +
                    "<td>" + existingShareholder.nationalityText + "</td>" +
                    "<td>" + (existingShareholder.legalStatusText ? existingShareholder.legalStatusText : "-") + "</td>" +
                    "<td class='tableModule-bodyItem-action'>" +
                    '   <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                    '   <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
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
                    '       <button type="button" class="removeButton btn btn_link"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="32px" height="32px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve"><path fill="#00A6BE" d="M12.983,17.5H4.916c-0.261,0-0.479-0.201-0.499-0.462l-0.916-12C3.491,4.899,3.539,4.762,3.633,4.66S3.861,4.5,4,4.5h10c0.14,0,0.273,0.059,0.368,0.162s0.142,0.241,0.13,0.38l-1.017,12C13.46,17.301,13.243,17.5,12.983,17.5zM5.379,16.5h7.146l0.932-11H4.54L5.379,16.5z M7.5,15V7c0-0.276-0.224-0.5-0.5-0.5S6.5,6.724,6.5,7v8c0,0.276,0.224,0.5,0.5,0.5S7.5,15.276,7.5,15z M9.5,15V7c0-0.276-0.224-0.5-0.5-0.5S8.5,6.724,8.5,7v8c0,0.276,0.224,0.5,0.5,0.5S9.5,15.276,9.5,15z M11.5,15V7c0-0.276-0.224-0.5-0.5-0.5S10.5,6.724,10.5,7v8c0,0.276,0.224,0.5,0.5,0.5S11.5,15.276,11.5,15z M14.5,3c0-0.276-0.224-0.5-0.5-0.5H4C3.724,2.5,3.5,2.724,3.5,3S3.724,3.5,4,3.5h10C14.276,3.5,14.5,3.276,14.5,3z M11.5,3c0-1.378-1.121-2.5-2.5-2.5C7.622,0.5,6.5,1.622,6.5,3c0,0.276,0.224,0.5,0.5,0.5S7.5,3.276,7.5,3c0-0.827,0.673-1.5,1.5-1.5s1.5,0.673,1.5,1.5c0,0.276,0.224,0.5,0.5,0.5S11.5,3.276,11.5,3z"></path></svg></button>' +
                    '       <button type="button" class="editButton btn btn_link"><svg xmlns="http://www.w3.org/2000/svg" width="23.085" height="26.089" viewBox="0 0 23.085 26.089"><g id="edite-icon" transform="translate(-709.429 -1490.767)"><g id="Group_1112" data-name="Group 1112" transform="translate(710.436 1491.776)"><g id="Group_1111" data-name="Group 1111"><path id="Path_1961" data-name="Path 1961" d="M730.078,1492.859a3.615,3.615,0,0,1,.6,4.8l-12.7,15.059a5.089,5.089,0,0,1-1.052.9,25.644,25.644,0,0,1-5.127,2.107s-.667.264-.972-.037l-.166-.14s-.321-.221-.187-.988c0,0,.823-5.062,1.821-6.245l13.236-15.631S727.524,1490.529,730.078,1492.859Z" transform="translate(-710.436 -1491.776)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path><line id="Line_114" data-name="Line 114" x2="4.635" y2="3.912" transform="translate(14.288 2.344)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line><line id="Line_115" data-name="Line 115" x2="4.216" y2="3.556" transform="translate(2.943 16.527)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g><line id="Line_116" data-name="Line 116" x2="20.158" transform="translate(0.718 24.055)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></line></g></g></svg></button>' +
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
