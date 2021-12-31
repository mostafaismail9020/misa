var SAGIA = SAGIA || {};
SAGIA.license = SAGIA.license || {};
SAGIA.license.apply = SAGIA.license.apply || {};

SAGIA.license.apply.onChangeTabToShareholdersTab = function() {
    $(document).scrollTop(0);
    var shareholdersQM1 = $("#shareholdersQM1");
    var shareholdersQM2 = $("#shareholdersQM2");
    shareholdersQM1.hide();
    shareholdersQM2.hide();
    $('#shareholdersTabData .globalMessage').hide();
    if(SAGIA.license.apply.data.qeemah === 1) {
        shareholdersQM1.show();
        SAGIA.license.apply.handleShareholdersQM1();
    } else if(SAGIA.license.apply.data.qeemah === 2) {
        shareholdersQM2.show();
        SAGIA.license.apply.handleShareholdersQM2();
    } else {
        $('#shareholdersTabData .globalMessage').show();
    }
};

$(document).on("click", "#shareholdersNextButton", function () {
    if (SAGIA.license.apply.data.qeemah === 2) {
        validateLicenseData('#addShareholderQM2NewSection', 'shareholders-total-percentage-QM2', SAGIA.license.apply.data.qeemah2Data.shareholders,
            function () {
                SAGIA.license.apply.tabs.showAccessibleTabSelector("#contactPersonTab");
                SAGIA.license.apply.onChangeTabToContactPersonTab();
            },
            function (xhr, status, error) {
                var formErrors = xhr.responseJSON.formErrors;
                $.each(formErrors, function (key, errorMessage) {
                    SAGIA.showError(errorMessage);
                });
            });
    } else {
        validateLicenseData('#addShareholderQM1NewSection', 'shareholders-total-percentage-QM1', SAGIA.license.apply.data.qeemah1Data,
            function () {
                SAGIA.license.apply.tabs.showAccessibleTabSelector("#contactPersonTab");
                SAGIA.license.apply.onChangeTabToContactPersonTab();
            },
            function (xhr, status, error) {
                var formErrors = xhr.responseJSON.formErrors;
                $.each(formErrors, function (key, errorMessage) {
                    SAGIA.showError(errorMessage);
                });
            });
    }
});

$(document).on("click", "#shareholdersBackButton", function () {
    SAGIA.license.apply.tabs.showAccessibleTabSelector("#entityInformationTab");
    SAGIA.license.apply.onChangeTabToEntityInformation();
});

$(document).on("click", "#shareholdersTab", function () {
    SAGIA.license.apply.onChangeTabToShareholdersTab();
});

