// Displays a branch in the modal when click on the edit button frum the branches list
function showBranchInModal(e){
    var index = $(e).data('index');
    selectedBranch = getBranch(index);
    handleBranchChange(selectedBranch);
}

// Function that updates the fields of a branch in modal
function handleBranchChange(selectedBranch) {
    setMarker();

    $('#branchName').text(selectedBranch.name);

    $('.gov-docs-hasCr[value=' + selectedBranch.hasCr + ']').prop('checked', true);
    $('.gov-docs-crn').val(selectedBranch.commercialRegisterNumber);
    $('.gov-docs-momra[value=' + selectedBranch.hasMomra +']').prop('checked', true);
    $('.gov-docs-shopLicNo').val(selectedBranch.shopLicenseNumber);
    $('.gov-docs-amanah').val(selectedBranch.amanah).change();
    $('#gMapAddress').val(selectedBranch.gMapAddress);
    //$('.gov-docs-amanah').trigger('change').next('.select2-container').removeClass('select2Container_selected');

    if(selectedBranch.wassel != undefined) {
        $('.gov-docs-wassel[value=' + selectedBranch.wassel + ']').prop('checked', true);
    } else {
        $('.gov-docs-wassel').prop('checked', false);
    }

    handleCrnRadioChange();
    handleMomraRadioChange();
}

// Get a branch by the index from all branches that are stored in a variable globally.
function getBranch(index) {
    var branch = branchesJson.find(function (x) {
        return x.selectedIndex === index;
    });
    return branch;
}

// Check if all the branches have been updated. Used to validate before final submit.
function areBranchesUpdated(){
    var branch = branchesJson.find(function (x) {
        return x.updated === undefined || x.updated === false;
    });
    return branch === undefined;
}

// Saves a branch locally. Used on the 'Update' button inside the editing modal.
function saveBranch() {
    selectedBranch.hasCr = $('.gov-docs-hasCr:checked').val();
    selectedBranch.commercialRegisterNumber = $('.gov-docs-crn').val();
    selectedBranch.hasMomra = $('.gov-docs-momra:checked').val();
    selectedBranch.shopLicenseNumber = $('.gov-docs-shopLicNo').val();
    selectedBranch.amanah = $('.gov-docs-amanah').val();
    selectedBranch.wassel = $('.gov-docs-wassel:checked').val();
    selectedBranch.gMapAddress = $('#gMapAddress').val();

    if(validateBranch(selectedBranch)){
        $('[data-completed-index="' + selectedBranch.selectedIndex + '"]').removeClass('hidden');
        $('#branchEditModal').modal('toggle');
        selectedBranch.updated = true;
    } // validari
}

// Validation of a branch on the client. Used on the 'Update' button before saving the branch.
function validateBranch(branch){
    var valid = true;

    if(selectedBranch.hasCr == 'true'){
        if(!validateNonNull(branch.commercialRegisterNumber)){
            showFieldError('cr-group','govDocs-CRN-error',notNullError);
            valid = false;
        }
        else if(!validateNumber(branch.commercialRegisterNumber)){
            showFieldError('cr-group','govDocs-CRN-error',notNumberError);
            valid = false;
        }
        if(valid){
            hideFieldError('cr-group','govDocs-CRN-error');
        }
    }

    if(selectedBranch.hasMomra == 'true'){
        if(!validateNonNull(branch.shopLicenseNumber)){
            showFieldError('shopLicNo-group','govDocs-shopLicNo-error',notNullError);
            valid = false;
        }
        else if(!validateNumber(branch.shopLicenseNumber)){
            showFieldError('shopLicNo-group','govDocs-shopLicNo-error',notNumberError);
            valid = false;
        }
        else {
            hideFieldError('shopLicNo-group','govDocs-shopLicNo-error');
        }

        if(!validateNonNull(branch.amanah)){
            showFieldError('amanah-group','govDocs-amanah-error',notNullError);
            valid = false;
        }
        else {
            hideFieldError('amanah-group','govDocs-amanah-error');
        }
    }

    if(selectedBranch.wassel == "1"){
        if(!validateNonNull(selectedBranch.gMapAddress)){
            showFieldError('gMap-group','govDocs-gMap-error',gMapError);
            valid = false;
        }
        else{
            hideFieldError('gMap-group','govDocs-gMap-error');
        }
    }

    return valid;
}

function validateNonNull(value) {
    if(!value || 0 === value.length)
        return false;

    return true;
}

function validateNumber(value){
    return !isNaN(parseFloat(value)) && isFinite(value);
}

function handleMomraRadioChange() {
    var hasMomra = ($('.gov-docs-momra:checked').val() == 'true')

    var disabled = false;

    if (!hasMomra) {
        disabled = true;
        $('.gov-docs-shopLicNo').val('');
        hideFieldError('shopLicNo-group','govDocs-shopLicNo-error');
        hideFieldError('amanah-group','govDocs-amanah-error');
        $('.gov-docs-amanah').val(null).trigger('change').next('.select2-container').removeClass('select2Container_selected');
    }

    $('.gov-docs-shopLicNo').prop('disabled', disabled);
    $('.gov-docs-amanah').prop('disabled', disabled);
}

function handleCrnRadioChange() {
    var crn = ($('.gov-docs-hasCr:checked').val() == 'true')

    if(crn) {
        $('#mapContainer').addClass('hidden');
        $('.gov-docs-crn').prop('disabled', false);;
        $('.gov-docs-wassel[value="0"]').prop('disabled', false)
        $('.gov-docs-wassel[value="0"]').prop('checked', true);
    }
    else {
        $('#mapContainer').removeClass('hidden');
        $('.gov-docs-crn').prop('disabled', true);
        $('.gov-docs-crn').val('');
        $('.gov-docs-wassel[value="0"]').prop('disabled', true)
        $('.gov-docs-wassel[value="1"]').prop('checked', true);
        hideFieldError('cr-group','govDocs-CRN-error');
    }
}

function handleWasselRadioChange() {
    var wassel = $('.gov-docs-wassel:checked').val();

    if(wassel == '0'){
        $('#mapContainer').addClass('hidden');
    }
    else if(wassel == '1'){
        $('#mapContainer').removeClass('hidden');
    }
    else if(wassel == '2'){
        $('#mapContainer').addClass('hidden');
    }
}

// Ajax request to the server to update the server. Happens only if all the branches have been updated.
function sendBranches() {
    mainBranchJson.branches = branchesJson;
    
    if(!areBranchesUpdated()){
        $('#error-group').removeClass("hidden");
    }
    else{
        $.ajax(ACC.config.encodedContextPath + "/governmentDocuments/updateBranches", {
            type:"POST",
            contentType:"application/json;charset=utf-8",
            cache:false,
            data: JSON.stringify(mainBranchJson),
            success : function(response) {
            		var newEntityCreatedId = response;
                $('#service-id').text(newEntityCreatedId);
                showAndSendFeedback(newEntityCreatedId);
               // $('#branchesUpdated').modal('toggle');
            },
            error : function(e) {
                $('#update-error').text(JSON.parse(e.responseText));
                $('#branchesUpdatedError').modal('toggle');
            },
            done : function(e) {
            }
        });
    }
}

function showFieldError(groupMarkup,errorMarkup,errorValue){
    $('#'+groupMarkup+'').addClass("has-error");
    $('#'+errorMarkup+'').text(errorValue);
}

function hideFieldError(groupMarkup,errorMarkup){
    $('#'+groupMarkup+'').removeClass("has-error");
    $('#'+errorMarkup+'').text('');
}

function showAndSendFeedback(serviceId) {
	$('#serviceId').val(serviceId);
	$('#requestSubmittedComment').modal();
	$('#requestSubmittedComment').on('hide.bs.modal', function() {
		window.location.href = ACC.config.encodedContextPath + '/governmentDocuments';
	});
}
