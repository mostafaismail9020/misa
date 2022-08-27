function loadInvestmentTermsData() {
    $.ajax({
        url: ACC.config.encodedContextPath + "/economic/investmentsTermsData",
        method: 'GET',
        ajaxHideLoadingIndicator: true,
        success: function (investmentTerms) {
            var $investmentsTermsTable = $('#investmentsTermsId').empty();
            if (investmentTerms && investmentTerms.sgiaIndicatorTerms) {
                investmentTerms.sgiaIndicatorTerms.forEach(function (investmentTerm) {
                    var code = investmentTerm.code;
                    $investmentsTermsTable.append('<tr id="'+code+'"><td>'+code+'</td><td>'+investmentTerm.dataPoint+'</td><td>'+investmentTerm.description+'</td><td>'+investmentTerm.calculation+'</td><td>'+investmentTerm.measurement+'</td><td>'+investmentTerm.source+'</td></tr>');
                });
            }
            setupTable();
        },
        error: function() {
            // Display error
        }
    });

};

var setupTable = function() {

    var config = {
        "drawCallback": function () {
            $('#investmentsTermsTableId_length').remove();
            $('#investmentsTermsTableId_filter').addClass('searchInputBox searchInputBox_dark').css('margin-bottom', '15px');
            var searchBranchText = getI18nText("search.branch.placehoder");
            var $inputSearch = $('#investmentsTermsTableId_filter input[type="search"]').addClass('searchInputBox-input').attr('placeholder', searchBranchText);
            var $labelSearch = $inputSearch.closest("label");
            $labelSearch.replaceWith($inputSearch);

            var $pagination = $('#investmentsTermsTableId_paginate');
            $pagination.find('a.previous').empty().html('<img src="/_ui/responsive/common/images/arrow-right.png" class="img-responsive transform-180-degree">').addClass('paginationModule-control paginationModule-control_left');
            $pagination.find('a.next').empty().html('<img src="/_ui/responsive/common/images/Icon-feather-arrow-left.png" class="img-responsive transform-180-degree">').addClass('paginationModule-control paginationModule-control_right');
        },
        //"lengthMenu": [ 10, 25, 50, 75, 100 ]
    };

    if (SAGIA.locale === 'ar' && SAGIA.dataTablesArabic) {
        config.language = SAGIA.dataTablesArabic;
    }

    $('#investmentsTermsTableId').dataTable(config);
};


$(document).ready(function () {
    loadInvestmentTermsData();
});