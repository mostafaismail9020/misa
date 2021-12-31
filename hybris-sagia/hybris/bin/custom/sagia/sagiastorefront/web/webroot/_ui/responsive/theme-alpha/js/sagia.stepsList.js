var SAGIA = SAGIA || {};
SAGIA.stepsList = {
	_autoload: [
		["init", '$(".js-stepsList").length > 0']
	],

	init: function (){
		$(".js-stepsList").each(function(){
			var nextAutoValid = true; //sets item onto valid by default
			var me = $(this);
			var key = me.attr('data-keyClass') ? me.attr('data-keyClass') : "",
				keyMe = (key ? key + "_" : ""),
				keyCh = (key ? key + "-" : "") + "item";
			var validatable = me.hasClass(keyMe + "validatable");
			var collapsible = me.hasClass(keyMe + "collapsible");
			var options = {
				item : keyCh,
				active : keyCh + "_active",
				valid : keyCh + "_valid",
				invalid : keyCh + "_invalid",
				collapsed : keyCh + "_collapsed",
				disabled : keyCh + "_disabled"
			};
			me.find('.js-stepsList-toggle').on('click', function(){
				var elem = $(this).parents("." + options.item);
				if(elem.hasClass(options.disabled))return;
				if(elem.hasClass(options.active)){
					elem.toggleClass(options.collapsed);
				}else{
					me.find("." + options.active).removeClass(options.active);
					elem.addClass(options.active).removeClass(options.collapsed);
				}
			});
			me.find('.js-stepsList-next,.js-stepsList-prev').on('click', function(){
				var elem = $(this).parents("." + options.item),
					target,
					valid = false;
				if($(this).hasClass('js-stepsList-prev')){
					target = elem.prevAll('.' + options.item).not('.' + options.disabled).first();
				}else{
					target = elem.nextAll('.' + options.item).not('.' + options.disabled).first();
					if(nextAutoValid)valid = nextAutoValid;
				}
				if(target){
					me.find("." + options.active).removeClass(options.active);
					target.addClass(options.active).removeClass(options.collapsed);
					if(validatable && valid){
						elem.addClass(options.valid);
					}
				}
			});
		});
	}
}