ACC.termsandconditions = {

	bindTermsAndConditionsLink: function() {
		/*$(document).on("click",".termsAndConditionsLink",function(e) {
			e.preventDefault();
			$.colorbox({
				maxWidth:"100%",
				maxHeight:"80%",
				width:"870px",
				scrolling:true,
				href: $(this).attr("href"),
				close:'<span class="glyphicon glyphicon-remove"></span>',
				title:'<div class="headline"><span class="headline-text">Terms and Conditions</span></div>',
				onComplete: function() {
					ACC.common.refreshScreenReaderBuffer();
				},
				onClosed: function() {
					ACC.common.refreshScreenReaderBuffer();
				}
			});
		});*/

		$(document).on("click", ".termsAndConditionsLink", function(event) {
			event.preventDefault();
			ACC.termsandconditions.showTermsAndConditions($(this).attr("href") + " .content");
		});
	},


	showTermsAndConditions: function(message) {
		var termsAndConditionsModal = $('#termsAndConditionsResponseModal');
		if(message) {
			termsAndConditionsModal.find('.modal-description').load(message, function() {
				termsAndConditionsModal.modal('show');
			});
		} else {
			termsAndConditionsModal.modal('show');
		}
	},

	handleRegisterChkTermsConditionsChange: function() {
		$("#registerChkTermsConditions").change( function(e) {
			e.preventDefault();
			var form = $(this).parents('form:first');
			var btnSubmit = form.find(':submit');

			if( $(this).is(':checked') )
			{
				btnSubmit.prop('disabled',false);
			}
			else
			{
				btnSubmit.prop('disabled',true);
			}
		});
	}
}

$(function(){
	with(ACC.termsandconditions) {
		bindTermsAndConditionsLink();
		handleRegisterChkTermsConditionsChange();
		$("#registerChkTermsConditions").removeAttr("disabled");
		$('[name="consentForm.consentGiven"]').removeAttr("disabled");
	}
});
