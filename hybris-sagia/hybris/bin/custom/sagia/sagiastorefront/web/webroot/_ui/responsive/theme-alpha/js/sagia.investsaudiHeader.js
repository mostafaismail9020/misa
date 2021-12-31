var SAGIA = SAGIA || {};
SAGIA.investsaudiHeader = {
	_autoload: [
		["init", '$(".navbar-toggler").length > 0']
	],

	init:function(){
		$(document).on("click",".navbar-toggler", function(e){
			e.preventDefault();

			if($("#navbarResponsive").hasClass("collapse")){
				$("#navbarResponsive").removeClass("collapse")
			}else{
				$("#navbarResponsive").addClass("collapse")
			}
		})
	}
};