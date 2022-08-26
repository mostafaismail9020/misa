investmentTermsVar =  {};

function loadInvestmentTermsData() {
            $.ajax({
                url: ACC.config.encodedContextPath + "/economic/investmentsTermsData",
                method: 'GET',
                ajaxHideLoadingIndicator: true,
                success: function (investmentTerms) {
                    setupTable();
                    var $investmentsTermsTable = $('#investmentsTermsId').empty();
                    if (investmentTerms && investmentTerms.sgiaIndicatorTerms) {
                        investmentTerms.sgiaIndicatorTerms.forEach(function (investmentTerm) {
                                var code = investmentTerm.code;
                                var name = investmentTerm.code;
                                var $investmentTermRow = {};
                                $investmentTermRow.attr("id", code).children().first().html(code).next().text(name).next().text(name);
                                setColorForDraftRow($investmentTermRow);
                                investmentTermsVar.dataTable.row.add($investmentTermRow).draw();
                            $investmentsTermsTable.append($investmentTermRow);
                        });
                    }
                },
                error: function() {
                    // Display error
                }
            });

    };

var setupTable = function() {

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
            $pagination.find('a.previous').empty().html($icon.clone()).prop("disabled", true).addClass('paginationModule-control paginationModule-control_left');
            $pagination.find('a.next').empty().html($icon.clone()).addClass('paginationModule-control paginationModule-control_right');
        }
    };

    if (SAGIA.locale === 'ar' && SAGIA.dataTablesArabic) {
        config.language = SAGIA.dataTablesArabic;
    }

    investmentTermsVar.dataTable = $('#investmentsTermsTableId').DataTable(config);
};


$(document).ready(function () {
    loadInvestmentTermsData();
});