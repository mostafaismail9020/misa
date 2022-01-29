ACC.common = {
	currentCurrency: $("main").data('currencyIsoCode') || "USD",
	processingMessage: $("<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif'/>"),


	blockFormAndShowProcessingMessage: function (submitButton)
	{
		var form = submitButton.parents('form:first');
		form.block({ message: ACC.common.processingMessage });
	},

	refreshScreenReaderBuffer: function ()
	{
		// changes a value in a hidden form field in order
		// to trigger a buffer update in a screen reader
		$('#accesibility_refreshScreenReaderBufferField').attr('value', new Date().getTime());
	},

	checkAuthenticationStatusBeforeAction: function (actionCallback)
	{
		$.ajax({
			url: ACC.config.authenticationStatusUrl,
			statusCode: {
				401: function () {
					location.href = ACC.config.loginUrl;
				}
			},
			success: function (data) {
				if (data == "authenticated") {
					actionCallback();
				}
			}
		});
	}
};

// I guess it's simpler method to add CSRF but ok
/*function initCSRF() {
    var header= "CSRFToken";
    var token = ACC.config.CSRFToken;

    if (token && header) {
        jQuery(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }
}
initCSRF();*/

/* Extend jquery with a postJSON method */
jQuery.extend({
	postJSON: function (url, data, callback)
	{
		return jQuery.post(url, data, callback, "json");
	},
    postJsonData: function(url, data) {
		return $.ajax({
            type:    'POST',
            url:     url,
			contentType: 'application/json',
			accept: 'application/json',
            data:    function(){
                if (jQuery.type(data) == 'string') {
                    return data;
                }
                else {
                    return JSON.stringify(data);
                }
            }(),
            headers: { 'Content-Type': 'application/json',
                       'Accept': 'application/json'
                      }
        })
	}
});



// add a CSRF request token to POST ajax request if its not available
$.ajaxPrefilter(function (options, originalOptions, jqXHR)
{
	// Modify options, control originalOptions, store jqXHR, etc
	if (options.type === "post" || options.type === "POST")
	{
		var noData = (typeof options.data === "undefined");
		if (noData)
		{
			options.data = "CSRFToken=" + ACC.config.CSRFToken;
		}
		else
		{
			var patt1 = /application\/json/i;
			if (options.data instanceof window.FormData)
			{
				options.data.append("CSRFToken", ACC.config.CSRFToken);
			}
			// if its a json post, then append CSRF to the header. 
			else if (patt1.test(options.contentType))
			{
				jqXHR.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
			}
			//else if (options.data.indexOf("CSRFToken") === -1)
			//{
			//	options.data = options.data + "&" + "CSRFToken=" + ACC.config.CSRFToken;
			//}
		}
		
	}
});


function applyNewTnC(e, onAcceptAction) {
	e.preventDefault();
	var link = $('#applyNewLicenseAfterTnC');
	if(link && link.data("skipPopup"))
	{
		$('#applyNewLicenseAfterTnC')[0].click();
	}
	else
	{
		var termsAndConditionsModal = $('#termsAndConditionsResponseApplyModal');
		termsAndConditionsModal.find("#acceptBtn").data("onAcceptAction",onAcceptAction);
		var url = $("#applyNewLicenseTermsUrl").val() + " .content";
		if (url) {
			termsAndConditionsModal.find('.modal-description').load(url,
					function() {
						termsAndConditionsModal.modal('show');
					});
		} else {
			termsAndConditionsModal.modal('show');
		}		
	}
}
function acceptNewLinceseTCAction(){
    var cookiesTnC = $.cookie('AcceptTermsAndCondition');

    if (cookiesTnC == null) {
    	$.cookie("AcceptTermsAndCondition", true, { expires : 1 ,path: '/'});
    }
    else {
    	$.cookie("AcceptTermsAndCondition", null, { path: '/' });
    	$.cookie("AcceptTermsAndCondition", true, { expires : 1 ,path: '/'});
    }
    
    var onAcceptAction =  $("#acceptBtn").data("onAcceptAction");
    		
    if(onAcceptAction && onAcceptAction==="Close"){
    	//For review tab: Close the POPUP and trigger submit event again
    	$('#termsAndConditionsResponseApplyModal').modal('toggle');
    	$("#reviewSubmitButton").click();
    }
    else{
   	 	//For Dashboard: just close the popup on Apply
   	 	$('#termsAndConditionsResponseApplyModal').modal('toggle');
    	$('#applyNewLicenseAfterTnC')[0].click();
    	$("#spinnerMainDiv").removeClass("hidden");
    }
}



$(document).on('focus blur change keyup', '.form-control', function (e) { 
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


