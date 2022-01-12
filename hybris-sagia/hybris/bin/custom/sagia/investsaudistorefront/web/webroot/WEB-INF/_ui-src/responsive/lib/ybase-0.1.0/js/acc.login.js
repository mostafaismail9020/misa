ACC.login = {

    _autoload: [
        "disableLoginButtonWhenClicked"
    ],

    disableLoginButtonWhenClicked: function () {
        $('#loginForm').on('submit', function () {
            $(this).find('button[type=submit]').prop("disabled", true);
        })
    }
};



$('.register-account-partner-screen .login-btn-cancel').on('click',function(){
    window.location="/investsaudistorefront/investsaudi/en/login";
})


$(document).on('focus blur change keyup', '.register-user-details', function (e) {
	var $currEl = $(this);
    $currEl.parent().addClass('focus-on-change');
    
	if (e.type === "keyup") {
		$(this).removeClass('required');
	}    
	if ($($currEl)[0].value !== undefined && $($currEl)[0].value.trim() !== "") {
		$currEl.parents('.focus-on-change').children('label').addClass('focused');
	}
	else if (e.type === "focusin") {
		$currEl.parents('.focus-on-change').children('label').addClass('focused');
	}
	else {
		$currEl.parents('.focus-on-change').children('label').toggleClass('focused');
	}

    
}).trigger('blur');



$(".register-account-partner-screen .login-btn-next").on('click',function(){
    $(".register-account-partner-screen2").removeClass('next-hide');
    $(".register-account-partner-screen").addClass('next-hide');

    var src = $(".function-userGroup .login-role-selection-box.active img.img-function-inactive").attr('src');
    $(".function-selected").attr('src',src)
})

$(".function-userGroup .login-role-selection-box").on('click',function(){
    $(".function-userGroup .login-role-selection-box.active").removeClass("active"); 
    $(this).addClass("active");

    $('.function-selected').attr('src','');

    if($(this).hasClass('function-BDUserGroup active')){
		$('.img-function-active').removeClass('d-none');
		$('.img-function-inactive').addClass('d-none');    
        
        $(".function-MarCommUserGroup .img-function-active").addClass('d-none');
        $(".function-MarCommUserGroup .img-function-inactive").removeClass('d-none');

        $(".item_container.group1").removeClass('next-hide');
        $(".item_container.group2").addClass('next-hide');
        $("register-function-group").val("1");
	}
	if($(this).hasClass('function-MarCommUserGroup active')){
		$('.img-function-active').removeClass('d-none');
		$('.img-function-inactive').addClass('d-none');

        $(".function-BDUserGroup .img-function-active").addClass('d-none');
        $(".function-BDUserGroup .img-function-inactive").removeClass('d-none');

        $(".item_container.group1").addClass('next-hide');
        $(".item_container.group2").removeClass('next-hide');
        $("register-function-group").val("2");
	}
    var src = $(".function-userGroup .login-role-selection-box.active img.img-function-inactive").attr('src')
    $('.function-selected').attr('src',src)
})

$(".register-investor-screen2-btn-back").on('click',function(){
    $(".register-account-partner-screen2").addClass('next-hide');
    $(".register-account-partner-screen").removeClass('next-hide');

    $(".function-selected").attr('src','')
})

$(".register-investor-screen2-btn-next").on('click',function(){
    $(".register-account-partner-screen2").addClass('next-hide');
    $(".register-account-partner-screen3").removeClass('next-hide');

    if($("#register-function-group").val() === "1"){
        $(".register-BDUserGroup").removeClass('next-hide')
    }
    if($("#register-function-group").val() === "2"){
        $(".register-MarCommUserGroup").removeClass('next-hide')
    }
})
