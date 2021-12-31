var SAGIA = SAGIA || {};
SAGIA.globalAlerts = {
	_autoload: [
		["init", '$(".closeAccAlert").length > 0']
	],

	init:function(){
		$(document).on('click','.closeAccAlert', function(e){
			e.preventDefault();
			var currentMsg = $(this).parent('.globalMessage');
			currentMsg.addClass('globalMessage_closeing').one("webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend", function() {
				$(this).addClass('hidden');
			});
		})
	}
};