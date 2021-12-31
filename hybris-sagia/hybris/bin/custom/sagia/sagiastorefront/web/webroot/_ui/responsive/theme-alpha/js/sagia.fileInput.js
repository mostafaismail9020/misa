var SAGIA = SAGIA || {};
SAGIA.fileInput = {
    _autoload: [
        ["init", '$(".js-inputFile").length > 0']
    ],

    init: function() {
        var defaultPlaceholder = '';
        $(document).on("change", ".js-inputFile", function(e) {
            var fileInput = $(this),
                textInput = $(this).next('input:text'),
                fileName = $(this).val().replace(/\\/g, '/').replace(/.*\//, ''),
                rootElement = $(this).closest('.formInputFile');

			var fileSize = e.target.files[0].size;
			var uploadedFileSize = (fileSize / 1048576).toFixed(2);
            
			if (typeof(configuredFileSize) != 'undefined' && uploadedFileSize > configuredFileSize) // 2 mb for bytes.
			{
			  //$(this).parent().find(".uploadServiceFile").val("");
			  $(this).parent().addClass("has-error");
			  rootElement.find(".help-block").text("*File size must be under "+configuredFileSize+"MB");
			  //rootElement.removeClass('active'); 
			  e.target.value = "";
			  return;
			} else{
				 textInput.attr('placeholder', fileName);
				 $(this).parent().removeClass("has-error"); 
				 rootElement.find(".help-block").hide();
					if (fileInput.length > 0) {
						rootElement.addClass('active');
					} else {
						rootElement.removeClass('active');
					}
			}

            /*textInput.attr('placeholder', fileName);

            if (fileInput.length > 0) {
                rootElement.addClass('active');
            } else {
                rootElement.removeClass('active');
            }*/
        });
		
		$(document).on("change", ".dashboardBannerUpload", function(e) {

              var ext = $('#file').val().split('.').pop().toLowerCase();

              var fileSize = e.target.files[0].size;

            var uploadedFileSize = (fileSize / 1048576).toFixed(2);

              var rootElement = $(this).closest('.dashboardBannerUpload');             

              if($.inArray(ext, ['gif','png','jpg','jpeg']) === -1){

                alert("Incorrect file format. (تنسيق ملف غير صحيح)");

                    e.target.value = "";

                    return;

               }else if(uploadedFileSize > 10){

                                alert("Please select a file less than 10MB. (يرجى تحديد ملف أقل من 10 ميغابايت)");

                                e.target.value = "";

                                return;

               }else{

                              $("#bannerUploadForm").submit();

               }

             

        });

       

        $(document).on("change", "#fileBoxModalPicture", function(e) {

              var ext = $('#fileBoxModalPicture').val().split('.').pop().toLowerCase();

              var fileSize = e.target.files[0].size;

            var uploadedFileSize = (fileSize / 1048576).toFixed(2);

              var rootElement = $(this).closest('.formInputFileBox');             

              if($.inArray(ext, ['gif','png','jpg','jpeg']) === -1){

                             alert("Incorrect file format. (تنسيق ملف غير صحيح)");

                             $("#updateProfilePicBtn").attr("disabled", true);

                            e.target.value = "";

                            return;

               }else if(uploadedFileSize > 10){

                              alert("Please select a file less than 10MB. (يرجى تحديد ملف أقل من 10 ميغابايت)");

                              $("#updateProfilePicBtn").attr("disabled", true);

                             e.target.value = "";

                                 return;

               }else{

                              $("#updateProfilePicBtn").attr("disabled", false);

               }

             

        });

        $(document).on('click', '.js-inputFile-reset', function(){
            var rootElement = $(this).closest('.formInputFile');

            rootElement.find('input:file').val(null);
            rootElement.find('.js-mock-input').remove();
            rootElement.find('input:text').attr('placeholder', defaultPlaceholder);
            rootElement.removeClass('active');

            if ($(this).hasClass("js-change-after-reset")) {
                $(document).trigger('resetFormInput');
            }
        });
    }
};