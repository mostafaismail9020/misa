var SAGIA = SAGIA || {};
SAGIA.expandTables = {
	_autoload: [
		["init", '$(".js-tableToggle").length > 0']
	],

	init:function(){
		$(document).on("click",".js-tableToggle", function(e){
			e.preventDefault();
			$(this).closest('.tableModule-body').next('.tableModule-body_collapsed').toggleClass('hidden');
		})
	}
};