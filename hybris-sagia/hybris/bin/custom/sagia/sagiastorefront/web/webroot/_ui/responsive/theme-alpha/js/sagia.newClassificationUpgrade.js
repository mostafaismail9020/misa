   //access the classification upgrade saved in localStorage
    onload = function () {
    		document.getElementById('current-classificationUpgrade-status').innerHTML = localStorage.getItem("classificationUpgradeCurrentCLASSLocalStorage");
    };

    //Used to validate before final submit. Check if an appeal has been selected and if all the files have been uploaded
    function validateFormClassificationUpgrade(e) {
        var file1 = $("#file01").val();
        var file2 = $("#file02").val();
        var file3 = $("#file03").val();
        var file4 = $("#file04").val();
        var file5 = $("#file05").val();
        var file6 = $("#file06").val();
        var allFilesToBeUploaded = new Array();
        allFilesToBeUploaded.push(file1);
        allFilesToBeUploaded.push(file2);
        allFilesToBeUploaded.push(file3);
        allFilesToBeUploaded.push(file4);
        allFilesToBeUploaded.push(file5);
        allFilesToBeUploaded.push(file6);

        if ($('#classificationUpgradeAppealList').val() === '') {
         $('#errorResponseModal').find('.modal-description').text(getI18nText("validation.selectAppeal"));
         $('#errorResponseModal').modal('show');
            e.preventDefault();

            return false;
        }

        var isEmpty = function(inputFile) {
            return inputFile === '';
        };

        if (allFilesToBeUploaded.some(isEmpty)) {
          $('#errorResponseModal').find('.modal-description').text(getI18nText("general.pleaseuploadfiles"));
          $('#errorResponseModal').modal('show');
            return false;
        }
    }