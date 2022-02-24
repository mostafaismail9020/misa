SAGIA.licenseAmendment.branch = SAGIA.licenseAmendment.branch || {};

$(document).ready(function () {

    if ($('body').hasClass('page-license-amend')) {

        $(".newBranchBtn").on('click', newBranch);

        $('.saveBranchBtn').on('click', saveBranch);

        $('.cancelBranchBtn').on('click', clearBranchForm);

        $('#branchesTableId').on('click', '.editBranchBtn', editBranch);

        $('#branchesTableId').on('click', '.viewBranchBtn', viewBranch);

        $('#branchesTableId').on('click', '.removeBranchBtn', removeBranch);

        $('#branchTypeId').on('change', removeBranchErrorsIfExists);

        $('#branchRegionId').on('change', removeBranchErrorsIfExists);

        $('#branchCityId').on('change', removeBranchErrorsIfExists);
    }
});

var setupBranchesTable = function() {

    var config = {
        "drawCallback": function () {
            $('#branchesTableId_length').remove();
            $('#branchesTableId_filter').addClass('searchInputBox searchInputBox_dark').css('margin-bottom', '15px');
            var searchBranchText = getI18nText("search.branch.placehoder");
            var $inputSearch = $('#branchesTableId_filter input[type="search"]').addClass('searchInputBox-input').attr('placeholder', searchBranchText);
            var $labelSearch = $inputSearch.closest("label");
            $labelSearch.replaceWith($inputSearch);

            var $pagination = $('#branchesTableId_paginate').addClass('paginationModule-wrapper').wrap('<div class="paginationModule"></div>');
            $pagination.find('span a').addClass('paginationModule-link').css('cursor', 'pointer').wrap('<div class="paginationModule-item"></div>');
            $pagination.find('span a.current').addClass('active');

            var $icon = $('#paginationElementId').find('svg').attr('height', '24').attr('width', '24');
            var $prev = '<button class="paginationModule-control paginationModule-control_left" disabled><img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive transform-180-degree"></button>'
            var $next = '<button class="paginationModule-control paginationModule-control_right"><img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive transform-180-degree"></button>'
            $pagination.find('a.previous').empty().html($prev).prop("disabled", true).addClass('paginationModule-control paginationModule-control_left');
            $pagination.find('a.next').empty().html($next).addClass('paginationModule-control paginationModule-control_right');
            // $pagination.find('a.previous').empty().html($icon.clone()).prop("disabled", true).addClass('paginationModule-control paginationModule-control_left');
            // $pagination.find('a.next').empty().html($icon.clone()).addClass('paginationModule-control paginationModule-control_right');
        }
    };

    if (SAGIA.locale === 'ar' && SAGIA.dataTablesArabic) {
        config.language = SAGIA.dataTablesArabic;
    }

    SAGIA.licenseAmendment.branch.dataTable = $('#branchesTableId').DataTable(config);
};

var newBranch = function () {
    clearBranchValidation();
    var $branchModal = $('#branchModalId');
    $branchModal.find('input, select').attr("disabled", false);
    $branchModal.find('#branchCountryId').attr("disabled", true);

    $branchModal.find('.cancelBranchBtn').show();
    $branchModal.find('.closeBranchBtn').hide();
    $branchModal.find('.saveBranchBtn').show();

    clearBranchForm();
};

var removeBranch = function () {
    SAGIA.licenseAmendment.dirtyAmendment = true;
    var $branchRow = $(this).closest('tr');
    $branchRow.remove();
    SAGIA.licenseAmendment.branch.dataTable.row($branchRow).remove().draw(false);

    var branchId = parseInt($branchRow.attr('id'));
    var branches = licenseAmendment.branches;
    for (var i = 0; i < branches.length; i++) {
        if (branchId == branches[i].srId) {
            branches[i].action = '03';
            break;
        } else if (branchId == branches[i].newItemId) {
            branches.splice(i, 1);
            break;
        }
    }
};

var viewBranch = function() {
    clearBranchValidation();
    var $branchModal = $('#branchModalId');
    $branchModal.find('input, select').attr("disabled", true);

    $branchModal.find('.cancelBranchBtn').hide();
    $branchModal.find('.closeBranchBtn').show();
    $branchModal.find('.saveBranchBtn').hide();

    updateBranchForm($(this).closest('tr').attr('id'));
    SAGIA.formElements.placeholderPolyfill();
};

var editBranch = function () {
    clearBranchValidation();
    var $branchModal = $('#branchModalId');
    $branchModal.find('input, select').attr("disabled", false);
    $branchModal.find('#branchCountryId').attr("disabled", true);

    // if main branch disable type and name
    var branchSrId = $(this).closest('tr').attr('id');
    var selectedBranch = licenseAmendment.branches.find(function (branch) {
        return branch.srId == branchSrId || branch.newItemId == parseInt(branchSrId);
    });
    $branchModal.find('#branchTypeId').attr("disabled", selectedBranch.main);
    $branchModal.find('#branchNameId').attr("disabled", selectedBranch.main);
    
    $branchModal.find('.cancelBranchBtn').show();
    $branchModal.find('.closeBranchBtn').hide();
    $branchModal.find('.saveBranchBtn').show();

    updateBranchForm($(this).closest('tr').attr('id'));
    SAGIA.formElements.placeholderPolyfill();
};

function updateBranchForm(selectedBranchId) {
    var selectedBranch = licenseAmendment.branches.find(function (branch) {
        return branch.srId == selectedBranchId || branch.newItemId == parseInt(selectedBranchId);
    });

    var $branchType = $('#branchTypeId');
    $branchType.next().addClass("select2Container_selected");
    $branchType.val(selectedBranch.type).trigger('change');

    var regionId = selectedBranch.address.region;
    var $branchRegion = $('#branchRegionId');
    $branchRegion.next().addClass("select2Container_selected");
    $branchRegion.val(regionId).trigger('change');

    updateCities();
    var cityId = selectedBranch.address.city;
    var $branchCity = $('#branchCityId');
    $branchCity.next().addClass("select2Container_selected");
    $branchCity.val(cityId).trigger('change');

    $('#branchNameId').val(selectedBranch.name);
    $('#branchStreetId').val(selectedBranch.address.street);
    $('#branchNumberId').val(selectedBranch.address.number);
    $('#branchTelephoneId').val(selectedBranch.address.telephone);
    $('#branchEmailId').val(selectedBranch.address.email);
    $('#branchWebsiteId').val(selectedBranch.address.website);
    $('.saveBranchBtn').attr('id', selectedBranch.srId || selectedBranch.newItemId);
}

var saveBranch = function () {
    var validator = branchValidator();
    if(!validator.form()) {
        return;
    }

    SAGIA.licenseAmendment.dirtyAmendment = true;
    var saveBranchBtn = $('.saveBranchBtn');
    var branchSrId = saveBranchBtn.attr('id');
    saveBranchBtn.removeAttr('id');

    var $type = $('#branchTypeId option:selected');
    var type = $type.val();
    var typeDescription = $type.text();

    var name = $('#branchNameId').val();
    var street = $('#branchStreetId').val();
    var number = $('#branchNumberId').val();
    var telephone = $('#branchTelephoneId').val();
    var email = $('#branchEmailId').val();
    var website = $('#branchWebsiteId').val();

    var $region = $('#branchRegionId option:selected');
    var region = $region.val();
    var regionDesc = $region.text();

    var $city = $('#branchCityId option:selected');
    var city = $city.val();
    var cityDescription = $city.text();

    var branchRow;
    if (branchSrId) { // edit branch
        branchRow = $('#' + branchSrId);
        branchRow.children().first().html(typeDescription)
            .next().text(name).next().text(cityDescription);

        var rowData = SAGIA.licenseAmendment.branch.dataTable.row(branchRow).data();
        rowData[0] = typeDescription;
        rowData[1] = name;
        rowData[2] = cityDescription;
        SAGIA.licenseAmendment.branch.dataTable.row(branchRow).data(rowData).invalidate();

        var branchIndex = licenseAmendment.branches.findIndex(function (branch) {
            return branch.srId === branchSrId || branch.newItemId === parseInt(branchSrId);
        });
        var branch = licenseAmendment.branches[branchIndex];
        if (branch.main) {
            branch.action = '02';
            setColorForModifiedRow(branchRow);
        }
        branch.name = name;
        branch.type = type;
        branch.typeDescription = typeDescription;
        branch.address.street = street;
        branch.address.number = number;
        branch.address.telephone = telephone;
        branch.address.email = email;
        branch.address.website = website;
        branch.address.region = region;
        branch.address.regionDescription = regionDesc;
        branch.address.city = city;
        branch.address.cityDescription = cityDescription;
    } else { // new branch
        branchRow = $('.branchTemplate').first().clone();
        branchRow.show();
        branchRow.attr("id", newItemId).children().first().html(typeDescription)
            .next().text(name).next().text(cityDescription);
        setColorForNewRow(branchRow);

        branchRow.find('.viewBranchBtn').hide();
        // branchRow.find('.removeBranchBtn').on('click', removeBranch);
        // branchRow.find('.editBranchBtn').on('click', editBranch);
        $('#branchesId').append(branchRow);
        SAGIA.licenseAmendment.branch.dataTable.row.add(branchRow).draw();

        licenseAmendment.branches.push({
            action: '01',
            name: name,
            type: type,
            typeDescription: typeDescription,
            main: false,
            address: {
                street: street,
                number: number,
                telephone: telephone,
                email: email,
                website: website,
                region: region,
                regionDescription: regionDesc,
                city: city,
                cityDescription: cityDescription
            },
            newItemId: newItemId++
        });
    }

    $("#branchModalId").modal('hide');
    $("#branchModalId").modal('toggle');
    var elmnt = document.getElementById("branchesTableId_wrapper");
    elmnt.scrollIntoView();

    clearBranchForm();
};

var clearBranchForm = function() {
    var $branchType = $('#branchTypeId');
    $branchType.val(null).trigger('change');
    $branchType.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $branchRegion = $('#branchRegionId');
    $branchRegion.val(null).trigger('change');
    $branchRegion.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    var $branchCity = $('#branchCityId');
    $branchCity.val(null).trigger('change');
    $branchCity.next().removeClass("select2Container_selected").removeClass("select2Container_selected_onLoad");

    $('#branchNameId').val('');
    $('#branchStreetId').val('');
    $('#branchNumberId').val('');
    $('#branchTelephoneId').val('');
    $('#branchEmailId').val('');
    $('#branchWebsiteId').val('');
    $('.saveBranchBtn').removeAttr('id');
};

var removeBranchErrorsIfExists = function(){
    if ($(this).closest('.form-group').hasClass('has-error')) {
        $(this).closest('.form-group').removeClass('has-error');
    }

    if ($(this).closest('.formSelectBox').length>0){
        $(this).closest('.formSelectBox').find('.help-block').text('');
    }
};

$("#branchTelephoneId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});
$("#branchNumberId").keypress(function (e) {
    //if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       //display error message
       $("#errmsg").html("Digits Only").show().fadeOut("slow");
              return false;
   }
});

$("#branchEmailId").focusout(function() {
   	var emailValue = $('#branchEmailId').val();

   	if (!validateBranchEmail(emailValue)) {
   	 	$("#branchEmailValidation").text(getI18nText("validation.basicinformation.email"));
   	 	$(".saveBranchBtn").attr("disabled", true);
   	}
   	else {
   		$(".saveBranchBtn").attr("disabled", false);	
   	}
});

$("#branchEmailId").focusin(function() {
	$("#branchEmailValidation").text("");
});

function validateBranchEmail(emailValue) {
	 var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 return filter.test(emailValue);
}