var SAGIA = SAGIA || {};
SAGIA.mainSections = {
    _autoload: [
        ["init", '$(".mainSection").length > 0']
    ],

    _enquireload: [
        ["screenSmMax" ]
    ],

    init: function() {
        $(".mainSection").addClass("mainSection_loaded");
    }
};