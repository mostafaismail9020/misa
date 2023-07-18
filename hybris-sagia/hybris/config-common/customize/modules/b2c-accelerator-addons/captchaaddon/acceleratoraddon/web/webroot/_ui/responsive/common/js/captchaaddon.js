ACC.captcha = {
	bindAll: function ()
	{
		this.renderWidget();
	},

	renderWidget: function ()
	{
		$.ajax({
			url: ACC.config.encodedContextPath + "/register/captcha/widget/recaptcha",
			type: 'GET',
			cache: false,
			success: function (html)
			{
				if ($(html) != [])
				{
					$(html).appendTo('.js-recaptcha-captchaaddon');
					$.getScript('https://www.google.com/recaptcha/api.js?hl=' + document.documentElement.lang, function ()
					{
						$('#g-recaptcha_incorrect').hide();
						
					});
				}
			}
		});
	}
};

$(document).ready(function ()
{
	if ($('#contact-us-page-contact-us-form').html() != null || $('#sagiaRegisterFormQuickRegistration').html() != null || $('#updateEmailForm').html() != null || $('#corForm').html() != null || $('#sagiaAuthenticationForm').html() != null || $('#sagiaAuthenticateCodeForm').html() != null || $('#sagiaVerificationForm').html() != null || $('#mizaContactForm').html() != null || $('#strategicContactForm').html() != null|| $('#investorVisaContactForm').html() != null)

	{
		ACC.captcha.bindAll();
	}
});
