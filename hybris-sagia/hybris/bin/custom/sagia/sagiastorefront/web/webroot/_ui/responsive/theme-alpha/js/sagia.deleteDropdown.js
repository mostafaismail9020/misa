var SAGIA = SAGIA || {};
SAGIA.deleteDropdown = {
	_autoload: [
		["init", '$(".js-deleteDropdown").length > 0']
	],

	init:function(){
		$(document).on("click",".js-deleteDropdown .js-deleteDropdown-btn, .js-deleteDropdown .js-deleteDropdown-cancel", function(e){
			e.preventDefault();
			var $e = $(this).parents(".js-deleteDropdown");
			if($e.hasClass("active")){
				$e.removeClass("active");
			}else{
				$(".js-deleteDropdown").removeClass("active");
				$e.addClass("active");
			}
		})
	}
};