SAGIA.financialSurvey.subsidiary  = SAGIA.financialSurvey.subsidiary || {};

// subsidiaries subsidiary
$(document).ready(function () {

    if ($('body').hasClass('page-financial-survey')) {

        $(".newSubsidiaryBtn").on('click', newSubsidiary);

        $('.saveSubsidiaryBtn').on('click', saveSubsidiary);

        $('.cancelSubsidiaryBtn').on('click', clearSubsidiaryForm);

        $('#subsidiariesTableId').on('click', '.editSubsidiaryBtn', editSubsidiary);

        $('#subsidiariesTableId').on('click', '.viewSubsidiaryBtn', viewSubsidiary);

        $('#subsidiariesTableId').on('click', '.removeSubsidiaryBtn', removeSubsidiary);

        $('#subsidiaryTypeId').on('change', removeSubsidiaryErrorsIfExists);

        $('#subsidiaryRegionId').on('change', removeSubsidiaryErrorsIfExists);

        $('#subsidiaryCityId').on('change', removeSubsidiaryErrorsIfExists);
    }
});

var setupSubsidiariesTable = function() {

    var config = {
        "drawCallback": function () {
            $('#subsidiariesTableId_length').remove();
            $('#subsidiariesTableId_filter').addClass('searchInputBox searchInputBox_dark').css('margin-bottom', '15px');
            var searchSubsidiaryText = getI18nText("search.subsidiary.placehoder");
            var $inputSearch = $('#subsidiariesTableId_filter input[type="search"]').addClass('searchInputBox-input').attr('placeholder', searchSubsidiaryText);
            var $labelSearch = $inputSearch.closest("label");
            $labelSearch.replaceWith($inputSearch);

            var $pagination = $('#subsidiariesTableId_paginate').addClass('paginationModule-wrapper').wrap('<div class="paginationModule"></div>');
            $pagination.find('span a').addClass('paginationModule-link').css('cursor', 'pointer').wrap('<div class="paginationModule-item"></div>');
            $pagination.find('span a.current').addClass('active');

            var $icon = $('#paginationElementId').find('svg').attr('height', '24').attr('width', '24');
            $pagination.find('a.previous').empty().html($icon.clone()).prop("disabled", true).addClass('paginationModule-control paginationModule-control_left');
            $pagination.find('a.next').empty().html($icon.clone()).addClass('paginationModule-control paginationModule-control_right');
        }
    };

    if (SAGIA.locale === 'ar' && SAGIA.dataTablesArabic) {
        config.language = SAGIA.dataTablesArabic;
    }

    SAGIA.financialSurvey.subsidiary.dataTable = $('#subsidiariesTableId').DataTable(config);
};

var newSubsidiary = function () {
    clearSubsidiaryValidation();
    var $subsidiaryModal = $('#subsidiaryModalId');
    $subsidiaryModal.find('input, select').attr("disabled", false);
    $subsidiaryModal.find('#subsidiaryCountryId').attr("disabled", true);

    $subsidiaryModal.find('.cancelSubsidiaryBtn').show();
    $subsidiaryModal.find('.closeSubsidiaryBtn').hide();
    $subsidiaryModal.find('.saveSubsidiaryBtn').show();

    clearSubsidiaryForm();
};

var removeSubsidiary = function () {
    SAGIA.financialSurvey.dirtyAmendment = true;
    var $subsidiaryRow = $(this).closest('tr');
    $subsidiaryRow.remove();
    SAGIA.financialSurvey.subsidiary.dataTable.row($subsidiaryRow).remove().draw(false);

    var subsidiaryId = parseInt($subsidiaryRow.attr('id'));
    var subsidiaries = financialSurvey.subsidiaries;
    for (var i = 0; i < subsidiaries.length; i++) {
        if (subsidiaryId == subsidiaries[i].srId) {
            subsidiaries[i].action = '03';
            break;
        } else if (subsidiaryId == subsidiaries[i].newItemId) {
            subsidiaries.splice(i, 1);
            break;
        }
    }
};

var viewSubsidiary = function() {
    clearSubsidiaryValidation();
    var $subsidiaryModal = $('#subsidiaryModalId');
    $subsidiaryModal.find('input, select').attr("disabled", true);

    $subsidiaryModal.find('.cancelSubsidiaryBtn').hide();
    $subsidiaryModal.find('.closeSubsidiaryBtn').show();
    $subsidiaryModal.find('.saveSubsidiaryBtn').hide();

    updateSubsidiaryForm($(this).closest('tr').attr('id'));
    SAGIA.formElements.placeholderPolyfill();
};

var editSubsidiary = function () {
    clearSubsidiaryValidation();
    var $subsidiaryModal = $('#subsidiaryModalId');
    $subsidiaryModal.find('input, select').attr("disabled", false);
    $subsidiaryModal.find('#subsidiaryCountryId').attr("disabled", true);

    // if main subsidiary disable type and name
    var subsidiarySrId = $(this).closest('tr').attr('id');
    var selectedSubsidiary = financialSurvey.subsidiaries.find(function (subsidiary) {
        return subsidiary.srId == subsidiarySrId || subsidiary.newItemId == parseInt(subsidiarySrId);
    });
    $subsidiaryModal.find('#subsidiaryTypeId').attr("disabled", selectedSubsidiary.main);
    $subsidiaryModal.find('#subsidiaryNameId').attr("disabled", selectedSubsidiary.main);

    $subsidiaryModal.find('.cancelSubsidiaryBtn').show();
    $subsidiaryModal.find('.closeSubsidiaryBtn').hide();
    $subsidiaryModal.find('.saveSubsidiaryBtn').show();

    updateSubsidiaryForm($(this).closest('tr').attr('id'));
    SAGIA.formElements.placeholderPolyfill();
};

function updateSubsidiaryForm(selectedSubsidiaryId) {
    var selectedSubsidiary = financialSurvey.subsidiaries.find(function (subsidiary) {
        return subsidiary.srId == selectedSubsidiaryId || subsidiary.newItemId == parseInt(selectedSubsidiaryId);
    });

    $('#subsidiaryNameId').val(selectedSubsidiary.subsidiaryName);
    $('#registrationNameId').val(selectedSubsidiary.registrationName);
    $('#unifiedNoId').val(selectedSubsidiary.unifiedNo);
    $('#contributionId').val(selectedSubsidiary.contribution);
    $('.saveSubsidiaryBtn').attr('id', selectedSubsidiary.srId || selectedSubsidiary.newItemId);
}

var saveSubsidiary = function () {
    var validator = subsidiaryValidator();
    if(!validator.form()) {
        return;
    }

    SAGIA.financialSurvey.dirtyAmendment = true;
    var saveSubsidiaryBtn = $('.saveSubsidiaryBtn');
    var subsidiarySrId = saveSubsidiaryBtn.attr('id');
    saveSubsidiaryBtn.removeAttr('id');

    var subsidiaryName = $('#subsidiaryNameId').val();
    var registrationName = $('#registrationNameId').val();
    var unifiedNo = $('#unifiedNoId').val();
    var contribution = $('#contributionId').val();

    var subsidiaryRow;
    if (subsidiarySrId) { // edit subsidiary
        subsidiaryRow = $('#' + subsidiarySrId);
        subsidiaryRow.children().first().html(subsidiaryName)
            .next().text(name).next().text(registrationName);

        var rowData = SAGIA.financialSurvey.subsidiary.dataTable.row(subsidiaryRow).data();
        rowData[0] = subsidiaryName;
        rowData[1] = registrationName;
        rowData[2] = unifiedNo;
        SAGIA.financialSurvey.subsidiary.dataTable.row(subsidiaryRow).data(rowData).invalidate();

        var subsidiaryIndex = financialSurvey.subsidiaries.findIndex(function (subsidiary) {
            return subsidiary.srId === subsidiarySrId || subsidiary.newItemId === parseInt(subsidiarySrId);
        });
        var subsidiary = financialSurvey.subsidiaries[subsidiaryIndex];
        if (subsidiary.main) {
            subsidiary.action = '02';
            setColorForModifiedRow(subsidiaryRow);
        }
        subsidiary.subsidiaryName = subsidiaryName;
        subsidiary.registrationName = registrationName;
        subsidiary.unifiedNo = unifiedNo;
        subsidiary.contribution = contribution;
    } else { // new subsidiary
        subsidiaryRow = $('.subsidiaryTemplate').first().clone();
        subsidiaryRow.show();
        subsidiaryRow.attr("id", newItemId).children().first().html(subsidiaryName)
            .next().text(registrationName).next().text(unifiedNo);
        setColorForNewRow(subsidiaryRow);

        subsidiaryRow.find('.viewSubsidiaryBtn').hide();

        $('#subsidiariesId').append(subsidiaryRow);
        SAGIA.financialSurvey.subsidiary.dataTable.row.add(subsidiaryRow).draw();
        financialSurvey.subsidiaries = [];
        financialSurvey.subsidiaries.push({
            action: '01',
            subsidiaryName: subsidiaryName,
            registrationName: registrationName,
            unifiedNo: unifiedNo,
            contribution: contribution,
            newItemId: newItemId++
        });
    }

    $("#subsidiaryModalId").modal('hide');
    clearSubsidiaryForm();
};

var clearSubsidiaryForm = function() {
    $('#subsidiaryNameId').val('');
    $('#registrationNameId').val('');
    $('#unifiedNoId').val('');
    $('#contributionId').val('');
    $('.saveSubsidiaryBtn').removeAttr('id');
};

var removeSubsidiaryErrorsIfExists = function(){
    if ($(this).closest('.form-group').hasClass('has-error')) {
        $(this).closest('.form-group').removeClass('has-error');
    }

    if ($(this).closest('.formSelectBox').length>0){
        $(this).closest('.formSelectBox').find('.help-block').text('');
    }
};

$("#subsidiaryTelephoneId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#subsidiaryNumberId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});

$("#subsidiaryEmailId").focusout(function() {
   	var emailValue = $('#subsidiaryEmailId').val();

   	if (!validateSubsidiaryEmail(emailValue)) {
   	 	$("#subsidiaryEmailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$(".saveSubsidiaryBtn").attr("disabled", true);
   	}
   	else {
   		$(".saveSubsidiaryBtn").attr("disabled", false);
   	}
});

$("#subsidiaryEmailId").focusin(function() {
	$("#subsidiaryEmailValidation").text("");
});

function validateSubsidiaryEmail(emailValue) {
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 return filter.test(emailValue);
}
