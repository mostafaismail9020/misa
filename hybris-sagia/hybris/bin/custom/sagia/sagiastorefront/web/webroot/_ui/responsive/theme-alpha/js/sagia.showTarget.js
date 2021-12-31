var SAGIA = SAGIA || {};
SAGIA.showTarget = {
	_autoload: [
		["init", '$(".js-showTarget").length > 0']
	],

	init:function(){
		$(document).on("click",".js-showTarget", function(e){
			e.preventDefault();
			
			var target = $(this).data('show-target');
			$('#' + target).toggleClass('hidden');
			
			var hideSelf = $(this).data('hideSelf');

			if (hideSelf) {
				$(this).addClass('hidden');
			}
		})
	}
};