var SAGIA = SAGIA || {};
SAGIA.tabComponent = {
	_autoload: [
		["init", '$(".js-tabComponent").length > 0']
	],

	init:function() {
		$(document).on("click",".js-tabComponent a", function(e){
			// Anchors not working because of this
			// e.preventDefault();

			$(this).parent()
				.addClass("active")
				.siblings()
				.removeClass("active");

			var target = $(this).attr("href").substr(1);
			$(this).parents(".js-tabComponent").find(".js-tabComponent-body[data-tab='"+target+"']")
				.addClass("active")
				.siblings()
				.removeClass("active");
		})		
	}
};
