var SAGIA = SAGIA || {};

SAGIA.myAccount = {
	_autoload: [
		["init", '$(".js-myAccount-edit").length > 0']
	],
    toggleContent: function(clickedElement){
        $(clickedElement).closest(".contentModule").find(".js-myAccount-edit-toggle").each(function() {
            if($(this).is(":visible")) {
                $(this).hide();
            } else {
                $(this).show();
            }
        });
        SAGIA.formElements.placeholderPolyfill();
        
    },
    toggleEditMode : function(clickedElement) {
        $(clickedElement).children('.js-myAccount-edit-text').each(function() {
            if($(this).is(":visible")) {
                $(this).addClass('hidden').hide();
            } else {
                $(this).removeClass('hidden').show();
            }
        });
        SAGIA.myAccount.toggleContent(clickedElement);
    },
	init: function() {
		$(document).on("click",".js-myAccount-edit", function(e) {
			e.preventDefault();
			SAGIA.myAccount.toggleEditMode(this);
		});
	}
};