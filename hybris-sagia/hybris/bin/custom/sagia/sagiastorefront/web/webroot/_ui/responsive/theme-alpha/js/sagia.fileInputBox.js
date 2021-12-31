var SAGIA = SAGIA || {};
SAGIA.fileInputBox = {
	_autoload: [
		["init", '$(".js-formInputFileBox").length > 0']
	],

	init: function() {
		var isAdvancedUpload = function() {
			var div = document.createElement('div');
			return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
		}();

		var $form = $('.js-formInputFileBox');
		var droppedFiles = null;
		var defaultLabel = $('.js-inputFile').next('.control-label').html();
		
		if (isAdvancedUpload) {
			var rootElement = $form.find('.formInputFileBox');
			rootElement.addClass('hasAdvancedUpload');
			
			rootElement.on('drag dragstart dragend dragover dragenter dragleave drop', function (e) {
				e.preventDefault();
				e.stopPropagation();
			}).on('dragover dragenter', function () {
				$(this).addClass('isDragover');
			}).on('dragleave dragend drop', function () {
				$(this).removeClass('isDragover');
			}).on('drop', function (e) {
				droppedFiles = e.originalEvent.dataTransfer.files;

                var ext = droppedFiles[0].type.split('/').pop().toLowerCase();

			  var uploadedFileSize = (droppedFiles[0].size / 1048576).toFixed(2);
				//var rootElement = $(this).closest('.formInputFileBox');                           

				if($.inArray(ext, ['gif','png','jpg','jpeg']) === -1){

							   alert("Incorrect file format. (تنسيق ملف غير صحيح)");

							   $("#updateProfilePicBtn").attr("disabled", true);

							  e.target.value = "";

							  droppedFiles = "";

							  return;

				 }else if(uploadedFileSize > 10){

								alert("Please select a file less than 10MB. (يرجى تحديد ملف أقل من 10 ميغابايت)");

								$("#updateProfilePicBtn").attr("disabled", true);

									e.target.value = "";

								  droppedFiles = "";

									 return;

				 }else{

								droppedFiles = e.originalEvent.dataTransfer.files;                                                         

															updateFileName(droppedFiles, $(this).find('.control-label'));

								$("#updateProfilePicBtn").attr("disabled", false);

				 }

				  //droppedFiles = e.originalEvent.dataTransfer.files;                                                     

				  //updateFileName(droppedFiles, $(this).find('.control-label'));
			});
		}
		
		function updateFileName(input, element) {
            var inputLabel = element;
            input.length > 0 ? inputLabel.text(input[0].name) : inputLabel.html(defaultLabel);
        }

		//label name update for traditional input file
		$(document).on("change", ".js-inputFile", function(e){
			var fileInput = $(this),
				inputLabel = $(this).next('.control-label'),
				fileName = $(this).val().replace(/\\/g, '/').replace(/.*\//, ''),
				fileName = e.target.files[0].name,
				rootElement = $(this).closest('.formInputFileBox');
			
			fileName.length > 0 ? inputLabel.text(fileName) : inputLabel.html(defaultLabel);
		});
	}
};


