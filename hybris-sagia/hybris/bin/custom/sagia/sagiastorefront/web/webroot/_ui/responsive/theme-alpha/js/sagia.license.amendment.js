var licenseAmendment, listItems, newItemId = 1000;
var attachments;

SAGIA = SAGIA || {};
SAGIA.licenseAmendment = SAGIA.licenseAmendment || {};
SAGIA.licenseAmendment.dirtyAmendment = false;

$(document).ready(function () {

    //show submit window when closing T&C
    $("#termsAndConditionsResponseModal").on("hidden.bs.modal", function () {
        $('#docsmodalId').modal('show');
        $('.modal:visible').length && $(document.body).addClass('modal-open');
    });

    //hide submit modal when opening T&C
    $("#termsAndConditionsResponseModal").on("show.bs.modal", function () {
        $('#docsmodalId').modal('hide');
        $('.modal:visible').length && $(document.body).addClass('modal-open');
    });

    if ($('body').hasClass("page-license-amend")) {

        $(".js-expandContent").on('click', expandHistoryList);
        // First check redirect from dashboard
        if (typeof srId !== 'undefined' && srId) {
            $('#expandAmendmentHistoryBtnId').click();
            return;
        }

        $('.shareholderTemplate').hide();
        $('.branchTemplate').hide();
        $('.productTemplate').hide();
        $('.docTemplate').hide();
        $('#entityHistoryTabId').hide();

        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/checkAvailability", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function () {
                getCurrentLicenseData();
                getDropDownsData();
                setupBranchesTable();
                SAGIA.formElements.placeholderPolyfill();
            },
            error: function (data) {
                var message = data.responseJSON.sagiaExceptionResponse.message;
                $('#requestErrorDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                }).find('.modal-description').text(message);
                $('#errorResponseModal').remove();
            },
            async: false
        });

        $("#branchRegionId").change(updateCities);
        $(".validateLicenseBtn").on('click', validateLicense);

        // Tabs navigation
        $(document).on("click", "#nextTabEntityBtnId", function () {
            $('a[href="#accessibletabscontent0-1"]').click();
        });

        $(document).on("click", "#nextTabIsicBtnId", function () {
            $('a[href="#accessibletabscontent0-2"]').click();
        });
        $(document).on("click", "#nextTabShareholdersBtnId", function () {
            $('a[href="#accessibletabscontent0-3"]').click();
        });

        $(document).on("click", "#nextTabBranchesBtnId", function () {
            $('a[href="#accessibletabscontent0-4"]').click();
        });

        $(document).on("click", ".newProductBtn", function (e){
            e.preventDefault();
            var licenseType = $("#licenseType").val();
            if(!licenseType) {
                SAGIA.showError(getI18nText("license.amendment.create.products.validationNoEntity"));
                return;
            }
            if (licenseType!=="1" && licenseType!=="5") {
                SAGIA.showError(getI18nText("license.amendment.create.products.validation"));
            }
        });

        // Cancel amendment
        $(document).on("click", ".cancelAmendmentBtn", function () {
            var dirty = SAGIA.licenseAmendment.dirtyAmendment;
            if (!dirty) {
                var labour = licenseAmendment.entity.labour;
                var labourOld = licenseAmendment.entity.labourOld;
                dirty = $('#labourId').val() !== labour || labour !== labourOld;

                var capital = licenseAmendment.entity.capital;
                var capitalOld = licenseAmendment.entity.capitalOld;
                dirty = dirty || $('#capitalId').val() !== capital || capital !== capitalOld;

                var name = licenseAmendment.entity.name;
                var nameOld = licenseAmendment.entity.nameOld;
                dirty = dirty || $('#entityNameId').val() !== name || name !== nameOld;

                var legalStatus = licenseAmendment.entity.legalStatus;
                var legalStatusOld = licenseAmendment.entity.legalStatusOld;
                dirty = dirty || $('#legalStatusId option:selected').val() !== legalStatus || legalStatus !== legalStatusOld;
            }

            if (dirty) {
                $('#unsavedChangesModalId').modal({backdrop: "static", keyboard: false});
            } else {
                $('#expandAmendmentHistoryBtnId').click();
            }
        });

        $(document).on("click", "#dismissChangesBtnId, .showHistoryBtn", function () {
            $('#expandAmendmentHistoryBtnId').click();
        });

        $(document).on("click", "#submitChangesBtnId", function () {
            validateLicense();
        });

        var licenseServiceId = "ZSR5";
        $("#saveDraftBtnId").click(function () {
           
            licenseAmendment.entity.name = $('#entityNameId').val();
            licenseAmendment.entity.capital = $('#capitalId').val();
            licenseAmendment.entity.labour = $('#labourId').val();
            licenseAmendment.entity.legalStatus = $('#legalStatusId option:selected').val();

            var data = {};
            data.serviceId = licenseServiceId;
            data.json = JSON.stringify(licenseAmendment);
            var pathNameURL = window.location.pathname;
            if (window.location.href.indexOf("?") > 0) {
                var indexOfQuestion = window.location.href.indexOf("?");
                var parametersString = window.location.href.substr(indexOfQuestion);
                pathNameURL = pathNameURL + parametersString;
            }
            data.url = pathNameURL;
            $.ajax(ACC.config.encodedContextPath + "/draft/save-json", {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader('CSRFToken', $('input[name="CSRFToken"]').attr('value'));
                },
                method: 'POST',
                data: JSON.stringify(data),
                success: function () {
                    $('#loadDraftBtnId').show();
                },
                error: function (error) {
                }
            });
        });

        $("#loadDraftBtnId").click(function () {
            $.ajax(ACC.config.encodedContextPath + "/draft/json?serviceId=" + licenseServiceId, {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (draft) {
                    if (draft) {
                        SAGIA.licenseAmendment.branch.dataTable.destroy();
                        setLicenseData(JSON.parse(draft.data));
                        setupBranchesTable();
                    }
                }
            });
        });

        var $submitAmendmentBtn = $('#submitAmendmentBtnId');
        $("#termsAndConditionsId").click(function () {
            if ($(this).is(':checked')) {
                $submitAmendmentBtn.attr("disabled", false);
            } else {
                $submitAmendmentBtn.attr("disabled", true);
            }
        });

        $submitAmendmentBtn.click(function () {
            $(this).attr('disabled', true);
        });

        $(".cancelAmendmentDialogBtn").on('click', cancelLicense);
        $(".submitAmendmentBtn").on('click', submitLicense);
    }
});

function getCurrentLicenseData() {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/00", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            if (data) {
                setLicenseData(data);
                updateIsic(data);
            }
        },
        async: false
    });
}

function updateIsic(data) {
    SAGIA.license.businessActivities.setBusinessTypeSectionVisible(false);

    var licenseActivities = [];
    if (data.entity.activity) {
        var containsOnlyNumbers = /^\d+$/;
        var activities = data.entity.activity.split('|');
        if (activities) {
            activities.forEach(function(activity) {
                if(activity.length > 6 && containsOnlyNumbers.test(activity.substring(0,5)) && activity.indexOf(':') > -1){
                    var currentActivity = activity.split(':');
                    var currentActivityId = currentActivity[0];
                    var currentActivityDescription = currentActivity[1];
                    if (currentActivityId && /^([0-9]+)$/.test(currentActivityId)) {
                        licenseActivities.push({id: currentActivityId, description: currentActivityDescription});
                    }
                }

            })
        }
    }

    if (licenseActivities && licenseActivities.length) {
        SAGIA.license.businessActivities.setLicenseType(data.licenseType, data.licenseTypeText, licenseActivities);
        SAGIA.license.businessActivities.show(true);
    } else {
        if (data.entity.activity) { // user has old isic
            $('#oldActivitiesId').text(data.entity.activity);
        }
        SAGIA.license.businessActivities.setLicenseType(data.licenseType, data.licenseTypeText);
        SAGIA.license.businessActivities.show(false);
    }
}

function getDropDownsData() {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/listItems", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            if (data) {
                setupDropDowns(data);
            }
        }
    });
}

var expandHistoryList = function () {
    if (!$('#expand01').hasClass('expanded')) {
        var $historyList = $('#history-list').empty();
        $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/history", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                if (data.length) {
                    var flatpickr = document.querySelector("#birthDateId")._flatpickr;
                    data.forEach(function (amendHeader) {
                        var statusClass = 'historyList-status_process';
                        if (amendHeader.status.indexOf("Rejected") !== -1) {
                            statusClass = 'historyList-status_rejected';
                        } else if (amendHeader.status.indexOf("Completed") !== -1) {
                            statusClass = 'historyList-status_accepted';
                        }
                        var formattedDate = '-';
                        if (amendHeader.createdDate) {
                            formattedDate = flatpickr.formatDate(new Date(amendHeader.createdDate.millis), ACC.formatUIDate);
                        }
                        $historyList.append('<li id="' + amendHeader.id + '" class="historyList-item">' +
                            '<a href="#" class="historyList-link" onclick="showHistoryDetails(' + amendHeader.id + ')">' +
                            '<div class="historyList-id">' + amendHeader.id + '</div>' +
                            '<div class="historyList-info">' +
                            '<div class="historyList-date">' + formattedDate + '</div>' +
                            '<div class="historyList-status ' + statusClass + '" >' + amendHeader.status + '</div>' +
                            '</div></a></li>');
                    });

                    var id = data[0].id;

                    // First check redirect from dashboard
                    if (typeof srId !== 'undefined' && srId) {
                        var elementFromDashboard = data.find(function(element) {
                            return element.id === srId;
                        });

                        if (elementFromDashboard) {
                            id = elementFromDashboard.id;
                        }
                    }

                    showHistoryDetails(id);

                } else {
                    hideNewAmendmentElements();
                    $('.expandableContent-main').hide();
                    $historyList.append('<li class="historyList-item historyList-item_empty">' + getI18nText('license.amendment.history.empty') + '</li>');
                }
            }
        });
    } else {
        window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/amend";
    }
};

function showHistoryDetails(amendHeaderId) {
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/" + amendHeaderId, {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (data) {
            if (data) {
                setLicenseData(data, true);
                hideNewAmendmentElements();
                $('#entityHistoryTabId').show();
                $('#capitalNewId').text(data.entity.capital);
                $('#capitalOldId').text(data.entity.capitalOld);
                $('#labourNewId').text(data.entity.labour);
                $('#labourOldId').text(data.entity.labourOld);
                $('#entitynameNewId').text(data.entity.name);
                $('#entitynameOldId').text(data.entity.nameOld);
                $('#legalstatusNewId').text(data.entity.legalStatus);
                $('#legalstatusOldId').text(data.entity.legalStatusOld);

                var $businessActivitiesTable = $('#businessActivitiesTable');
                $businessActivitiesTable.find('#supportDocumentsThId, td > .formInputFile').hide();
                $businessActivitiesTable.find('#actionsThId, td.tableModule-bodyItem-action').hide();
                $businessActivitiesTable.find('.contentModule-actions').hide();
                
                $('#history-list .historyList-item_current').removeClass('historyList-item_current');
                $('#' + amendHeaderId).addClass('historyList-item_current');

                var $currentItem = $('#' + amendHeaderId).addClass('historyList-item_current');
                if (data.texts && data.texts.length && data.texts[0].comment) {
                    $('#ammendComments').show();
                    $('#commentMessage').text(data.texts[0].comment);
                } else {
                    $('#ammendComments').hide();
                }
                var noBusinessActivitiesSelected = $("#noBusinessActivitiesSelected");
                noBusinessActivitiesSelected.hide();

                var $historyList = $('#history-list');
                $historyList.scrollTop(
                    $currentItem.offset().top - $historyList.offset().top + $historyList.scrollTop()
                );
            }
        }
    });
}

function hideNewAmendmentElements() {
    $('.newAmendmentBtn, #saveDraftBtnId, #loadDraftBtnId').hide();
    $('#entityAmendTabId').hide();
}

var validateLicense = function () {
    var validator = entityValidator();
    if (!validator.form()) {
        $('a[href="#accessibletabscontent0-0"]').click();
        return;
    }

    licenseAmendment.entity.name = $('#entityNameId').val();
    licenseAmendment.entity.capital = $('#capitalId').val();
    licenseAmendment.entity.labour = $('#labourId').val();
    licenseAmendment.entity.legalStatus = $('#legalStatusId option:selected').val();

    // validate percentage sum
    var sum = 0;
    licenseAmendment.shareholders.forEach(function(shareholder) {
        if(shareholder.percentage != null && shareholder.action != null && shareholder.action !== '03') {
                sum = Math.round((sum + Number(shareholder.percentage)) * 1e12) / 1e12 ;
            }
    });

    if (sum !== 100) {
        $('#licenseAmendmentValidationDialogId').modal({
            backdrop: "static",
            keyboard: false
        }).find('.modal-description').empty().text(getI18nText('validation.sharePerc.sum'));
        return;
    }
    
    licenseAmendment.entity.activity = licenseAmendment.entity.activity || '';
    var currentActivitiesIds = licenseAmendment.entity.activity.match(/\d+/g) || [];
    var selectedActivities = SAGIA.license.businessActivities.selectedActivities;
    var selectedActivitiesIds = [];
    selectedActivities.forEach(function(activity) {
        selectedActivitiesIds.push(activity.activityId);
    });

    var equalActivities = currentActivitiesIds.length === selectedActivitiesIds.length
        && currentActivitiesIds.sort().every(function(value, index) { return value == selectedActivitiesIds.sort()[index]});

    if (!equalActivities) {
        licenseAmendment.businessActivities = [];
        selectedActivities.forEach(function (activity) {
            licenseAmendment.businessActivities.push({
                id: activity.activityId,
                description: activity.description
            });
        });
    }

    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend/amendmentTypes", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(licenseAmendment),
        method: "POST",
        success: function (data) {
            if (data) {
                licenseAmendment = data;
                if (licenseAmendment.noChanges) {
                    $('#licenseAmendmentNoChangesDialogId').modal({
                        backdrop: "static",
                        keyboard: false
                    });
                }else if (licenseAmendment.errors.length) {
                    var $modalDescription = $('#licenseAmendmentValidationDialogId').modal({
                        backdrop: "static",
                        keyboard: false
                    }).find('.modal-description');
                    $modalDescription.empty();
                    licenseAmendment.errors.forEach(function (error) {
                        $modalDescription.append('<p>' + error + '</p>');
                    });
                } else {
                    var $docsModal = $('#docsmodalId');

                    $('#submitAmendmentBtnId').attr('disabled', true);
                    $('#termsAndConditionsId')[0].checked = false;

                    var $amendmentTypeDiv = $docsModal.find('#amendmentTypeId');
                    var $regularAmendmentDocs = $docsModal.find('#regularAmendmentDocsId');
                    if (licenseAmendment.instantAmendment) {
                        $amendmentTypeDiv.text(getI18nText('license.amendment.type.instant'));
                        $regularAmendmentDocs.hide();
                    } else {
                        $amendmentTypeDiv.text(getI18nText('license.amendment.type.regular'));
                        $regularAmendmentDocs.show();
                    }

                    var $amendmentTypes = $docsModal.find('#amendmentTypesId');
                    var $docTemplate = $docsModal.find('.docTemplate').clone();
                    var $documents = $docsModal.find('#documentsId');
                    SAGIA.licenseAmendment.generatedDocuments = [];
                    data.amendmentTypesView.forEach(function (amendmentType) {
                        $amendmentTypes.append('<div class="col-sm-12"><ul class="dottedList dottedList_green dottedList_big">' +
                            '<li class="dottedList-item">' + amendmentType.name + '</li>' +
                            '</ul></div>');
                        if (!data.instantAmendment) {
                            attachments.forEach(function (attachment) {
                                if (attachment.longDescription.indexOf(amendmentType.code) !== -1 && $docsModal.find('#' + attachment.dockeyId).length === 0) {
                                    var $documentRow = $docTemplate.clone();
                                    $documentRow.find('#fileId').attr('id', attachment.dockeyId).attr('name', attachment.dockeyId);
                                    $documentRow.find('.control-label').text(attachment.name).attr('for', attachment.dockeyId);
                                    SAGIA.licenseAmendment.generatedDocuments.push(attachment.dockeyId);
                                    $documentRow.show();
                                    $documents.append($documentRow);
                                }
                            });
                        }

                        var $simulatedPrice = getSimulatedPrices(data.simulatedPrices);
                        $("#simulatedPriceDivContent").css("display","block");
                        if(typeof $simulatedPrice.netValue !== 'undefined') {
                            $("#simulatedPriceRow").css("display","block");
                            $('#simulatedPriceNetValue').text($simulatedPrice.netValue  + " " + ($simulatedPrice.currency || ""));
                        }
                    });
                    $docsModal.modal({
                        backdrop: "static",
                        keyboard: false
                    });
                }
            }
        }
    });
};

function getSimulatedPrices(simulatedPrices) {
	if(simulatedPrices instanceof Array) {
		return simulatedPrices[0] || {};
	}
	return {};
}

var cancelLicense = function () {
    $('#amendmentTypesId').empty();
    $('#documentsId').empty();
    $('#docsmodalId').modal('hide');
};

var submitLicense = function () {
    licenseAmendment.attachments = [];

    var generatedDocuments = SAGIA.licenseAmendment.generatedDocuments;
    if (generatedDocuments && generatedDocuments.length && !documentsValidator(generatedDocuments).form()) {
        return;
    }

    var $attachments = $('#documentsId :file');
    var attachmentsNotLoaded = $attachments.length;
    $attachments.each(function (index, file) {
        var attachment = {};
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            attachment.content = removeBase64prefix(event.target._TYPE, event.target.result);
            attachment.name = event.target._NAME;
            attachment.dockeyId = event.target._DOCKEYID;
            licenseAmendment.attachments.push(attachment);
            attachmentsNotLoaded--;
        };
        fileReader._NAME = $('#documentsId label[for=' + $(file).attr('id') + ']').text();
        fileReader._DOCKEYID = $(file).attr('id');
        fileReader._TYPE = file.files[0].type;
        fileReader.readAsDataURL(file.files[0]);
    });


    var timerId = setInterval(function () {
        if (attachmentsNotLoaded === 0) {
            submitLicenseWithAttachments();
            clearInterval(timerId);
        }
    }, 1000);
};

function submitLicenseWithAttachments() {
    var token = $('input[name="CSRFToken"]').attr('value');
    $.ajax(ACC.config.encodedContextPath + "/my-sagia/license/amend", {
        method: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        data: JSON.stringify(licenseAmendment),
        success: function () {
            $('#docsmodalId').modal('hide');
            if (licenseAmendment.errors.length) {
                var $modalDescription = $('#licenseAmendmentValidationDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                }).find('.modal-description');
                $modalDescription.empty();
                licenseAmendment.errors.forEach(function (error) {
                    $modalDescription.append('<p>' + error + '</p>');
                });
            } else {
                $('#requestSubmittedDialogId').modal({
                    backdrop: "static",
                    keyboard: false
                });
            }
        },
        error: function () {
            $('#docsmodalId').modal('hide');
            $('#requestErrorDialogId').modal({
                backdrop: "static",
                keyboard: false
            }).find('.globalMessage-msg').text(getI18nText("license.amendment.error"));
        }
    });
}

function setLicenseData(data, history) {
    licenseAmendment = data;
    $("#licenseType").val(licenseAmendment.licenseType);
    allowCreateNewProduct(licenseAmendment.licenseType);

    // Entity
    var entity = licenseAmendment.entity;
    $('#capitalId').val(entity.capital);
    $('#labourId').val(entity.labour);
    $('#entityNameId').val(entity.name);
    updateDropDown('#legalStatusId', entity.legalStatus);
    if (entity.legalStatus !== 'ESTB') {
        $('#legalStatusId').attr('disabled', true);
    }

    var DELETE_ACTION = '03';
    var $shareholderRowTemplate = $('.shareholderTemplate').first().clone(true);
    var $shareholdersTable = $('#shareholdersId').empty().append($shareholderRowTemplate.clone(true).hide());
    if (history) {
        $('#shareholderBtnColumnId').hide();
        $shareholderRowTemplate.find('.tableModule-bodyItem-action').hide();
        $('.newShareholderBtn').hide();
    }
    licenseAmendment.shareholders.forEach(function (shareholder) {
        if (shareholder.action !== DELETE_ACTION) {
            var name = shareholder.firstName + ' ' + shareholder.secondName;
            var type = shareholder.bpType === '1' ? getI18nText("general.individual") : getI18nText("general.entity");
            var percentage = 0;
            if(shareholder.percentage != null) {
                percentage = (shareholder.percentage.length > 5 ? shareholder.percentage.substring(0, 5) : shareholder.percentage) + '%';
            }
            var nationality = shareholder.nationalityCurrentDescription || '-';
            var legalStatus = shareholder.legalStatus || '-';

            var $shareholderRow = $shareholderRowTemplate.clone(true).show();
            $shareholderRow.attr("id", shareholder.srId ? shareholder.srId : shareholder.newItemId).children().first().text(name).next().text(type)
                .next().text(percentage).next().text(nationality).next().text(legalStatus);
            setColorForDraftRow($shareholderRow, shareholder.action);
            $shareholdersTable.append($shareholderRow);
        }
    });

    var $branchRowTemplate = $('.branchTemplate').first().clone(true);
    var $branchesTable = $('#branchesId').empty();//.append($branchRowTemplate.clone(true).hide());
    if (history) {
        $('#branchBtnColumnId').hide();
        $branchRowTemplate.find('.tableModule-bodyItem-action').hide();
        $('.newBranchBtn').hide();
    }
    licenseAmendment.branches.forEach(function (branch) {
        if (branch.action !== DELETE_ACTION) {
            var typeDescription = branch.typeDescription;
            var name = branch.name;
            var city = branch.address.cityDescription;
            var addrNumber = branch.addrNumber;
            var $branchRow = $branchRowTemplate.clone(true).show();
            $branchRow.attr("id", branch.srId).children().first().html(typeDescription).next().text(name).next().text(city).next().text(addrNumber);
            setColorForDraftRow($branchRow, branch.action);
            if (!history) {
                if (branch.main) { // Main branch, can edit, can't be removed
                    $branchRow.find('.viewBranchBtn').hide();
                    $branchRow.find('.editBranchBtn').show();
                    $branchRow.find('.deleteDropdown').hide();
                } else { // Can be removed
                    $branchRow.find('.viewBranchBtn').show();
                    $branchRow.find('.editBranchBtn').hide();
                    $branchRow.find('.deleteDropdown').show();
                }
            }

            $branchesTable.append($branchRow);
        }
    });

    var $productRowTemplate = $('.productTemplate').first().clone(true);
    var $productsTable = $('#productsId').empty().append($productRowTemplate.clone(true).hide());
    if (history) {
        $('#productsBtnColumnId').hide();
        $productRowTemplate.find('.tableModule-bodyItem-action').hide();
        $('.newProductBtn').hide();
    }
    licenseAmendment.products.forEach(function (product) {
        if (product.action !== DELETE_ACTION) {
            var id = product.id;
            var description = product.description;
            var quantity = product.quantity;
            var unit = product.unit;

            var $productRow = $productRowTemplate.clone(true).show();
            $productRow.attr("id", product.srId).children().first().html(id).next().text(description).next().text(quantity).next().text(unit);
            setColorForDraftRow($productRow, product.action);
            $productsTable.append($productRow);
        }
    });

    adjustNewItemIdVariable();
}

function setupDropDowns(data) {
    attachments = data.attachments;
    listItems = data;

    var $branchTypeSelect = $('#branchTypeId').append(new Option('', '', false, false));
    data.branchTypes.forEach(function (branchType) {
        $branchTypeSelect.append(new Option(branchType.name, branchType.id, false, false));
    });

    var $branchRegionSelect = $('#branchRegionId').append(new Option('', '', false, false));
    data.regions.forEach(function (region) {
        $branchRegionSelect.append(new Option(region.name, region.id, false, false));
    });

    var $academicTitleSelect = $('#shareholderAcademicTitleId').append(new Option('', '', false, false));
    var $delegateAcademicTitleSelect = $('#delegateAcademicTitleId').append(new Option('', '', false, false));
    data.academicTitle.forEach(function (title) {
        $academicTitleSelect.append(new Option(title.name, title.id, false, false));
        $delegateAcademicTitleSelect.append(new Option(title.name, title.id, false, false));
    });

    var $genderSelect = $('#shareholderGenderId').append(new Option('', '', false, false));
    var $delegateGenderSelect = $('#delegateGenderId').append(new Option('', '', false, false));
    data.gender.forEach(function (gender) {
        $genderSelect.append(new Option(gender.name, gender.id, false, false));
        $delegateGenderSelect.append(new Option(gender.name, gender.id, false, false));
    });

    var $statusSelect = $('#shareholderMaritalStatusId').append(new Option('', '', false, false));
    var $delegateStatusSelect = $('#delegateMaritalStatusId').append(new Option('', '', false, false));
    data.status.forEach(function (status) {
        $statusSelect.append(new Option(status.name, status.id, false, false));
        $delegateStatusSelect.append(new Option(status.name, status.id, false, false));
    });
    
    var $premiumResidentSelect = $('#shareholderPremiumResidentId').append(new Option('', '', false, false));
    var $delegatePremiumResidentSelect = $('#delegatePremiumResidentId').append(new Option('', '', false, false));
    data.premiumResident.forEach(function (premiumResident) {
        $premiumResidentSelect.append(new Option(premiumResident.name, premiumResident.id, false, false));
        $delegatePremiumResidentSelect.append(new Option(premiumResident.name, premiumResident.id, false, false));
    });

    var $currentNationalitySelect = $('#shareholderNationalityCurrentId').append(new Option('', '', false, false));
    var $previousNationalitySelect = $('#shareholderNationalityPreviousId').append(new Option('', '', false, false));
    var $shareholderCountrySelect = $('#shareholderCountryId').append(new Option('', '', false, false));
    var delegateNationalitySelect = $('#delegateNationality').append(new Option('', '', false, false));
    var $delegateCountrySelect = $('#delegateCountry').append(new Option('', '', false, false));
    var $companyCountrySelect = $('#companyCountry').append(new Option('', '', false, false));
    data.countries.forEach(function (country) {
        $currentNationalitySelect.append(new Option(country.name, country.id, false, false));
        $previousNationalitySelect.append(new Option(country.name, country.id, false, false));
        $shareholderCountrySelect.append(new Option(country.name, country.id, false, false));
        delegateNationalitySelect.append(new Option(country.name, country.id, false, false));
        $delegateCountrySelect.append(new Option(country.name, country.id, false, false));
        $companyCountrySelect.append(new Option(country.name, country.id, false, false));
    });

    var $sectorSelect = $('#shareholderSectorId').append(new Option('', '', false, false));
    data.sectors.forEach(function (sector) {
        $sectorSelect.append(new Option(sector.name, sector.id, false, false));
    });

    var $legalStatusSelect = $('#shareholderLegalStatusId').append(new Option('', '', false, false));
    data.legalStatus.forEach(function (legalStatus) {
        $legalStatusSelect.append(new Option(legalStatus.name, legalStatus.id, false, false));
    });

    var $multinationalCompanySelect = $('#shareholderMultinationalCompanyId').append(new Option('', '', false, false));
    data.multinationalCompany.forEach(function (multinationalCompany) {
        $multinationalCompanySelect.append(new Option(multinationalCompany.name, multinationalCompany.id, false, false));
    });

    var $unitSelect = $('#productUnitId').append(new Option('', '', false, false));
    data.unit.forEach(function (unit) {
        $unitSelect.append(new Option(unit.name, unit.id, false, false));
    });

    var selectedRegionId = data.regions[0].id;
    var $branchCitySelect = $('#branchCityId').append(new Option('', '', false, false));
    data.cities.forEach(function (city) {
        if (city.parentId === selectedRegionId) {
            $branchCitySelect.append(new Option(city.name, city.id, false, false));
        }
    });
}

var updateCities = function () {
    var selectedRegionId = $('#branchRegionId option:selected').val();

    var $branchCitySelect = $('#branchCityId').empty();
    $branchCitySelect.append(new Option('', '', false, false));
    listItems.cities.forEach(function (city) {
        if (city.parentId === selectedRegionId) {
            $branchCitySelect.append(new Option(city.name, city.id, false, false));
        }
    });
};


var allowCreateNewProduct = function (licenseType){
    if(licenseType === "1" ||  licenseType === "5") {
        var buttonElem =  $(".newProductBtn");
        buttonElem.attr("data-toggle", "modal");
        buttonElem.attr("data-target", "#productModalId");
        buttonElem.attr("data-backdrop", "static");
        buttonElem.attr("data-keyboard", "false");
    }
};

var adjustNewItemIdVariable = function () {
    // var verifiedExistingNewItemId = false;
    // while (verifiedExistingNewItemId === false) {
    //     if ($('.shareholderTemplate[id='+newItemId+']').length > 0) {
    //         newItemId++;
    //     } else {
    //         verifiedExistingNewItemId = true;
    //     }
    // }

    newItemId = parseInt($('.shareholderTemplate:last-child').attr('id')) + 1;
}

$(document).on('click', '#showDelegateQuestionOrganization .formRadioBox .form-item label', function(event) {
    $("#showDelegateQuestionOrganization .formRadioBox .form-item label").removeClass('btn-bg');
          $(this).toggleClass('btn-bg');  
         
});
$(document).on('click', '#showDelegateQuestion .formRadioBox .form-item label', function(event) {
    $("#showDelegateQuestion .formRadioBox .form-item label").removeClass('btn-bg');
          $(this).toggleClass('btn-bg');  
         
});











