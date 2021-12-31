//updates the status of an item in the detail section based on the selected item from history list
function loadData(objectId, objectClass) {
    $("#classification-id").text(objectId);
    $("#classification-classText").text(objectClass);

    $('ul#classificationUpgradeList').on('click', 'li', function (e) {
        var objectID = $(e.currentTarget).find('.historyList-id').text();
        var lis = document.getElementById("classificationUpgradeList").getElementsByTagName("li");
        for (var index = 0; index < lis.length; ++index) {
            lis[index].classList.remove('historyList-item_current');
        }
        e.currentTarget.className += " historyList-item_current";
        $.ajax({
            type: 'GET',
            url: ACC.config.encodedContextPath + "/classifications/" + objectID,
            dataType: 'json',
            success: function (res) {
                var classProperty = res.classProperty;
                var appeal =  res.appeal;
                $('#classification-classText').text(classProperty);
                $('#classification-upgradeText').text(appeal);
            }
        });
    });
}

//save current classification upgrade to the localStorage
function setLocalStorage() {
    localStorage.setItem("classificationUpgradeCurrentCLASSLocalStorage", $("#classification-classText").text());
    window.location.href = ACC.config.encodedContextPath + "/my-sagia/license/classifications/new";
}

function showErrorModal() {
    var errorModal = $('#errorResponseModal');
    errorModal.find('.modal-description').text(getI18nText("validation.entityStatus.error"));
    errorModal.modal('show');
}

//function that search in history list after objectId
$(document).ready(function () {
    $("#classificationUpgradeSearchBox").on("keyup", function () {
        var valThis = $(this).val();
        $('#classificationUpgradeList > li').each(function () {
            var entry = $(this);
            (entry.find('.historyList-id').text().search(valThis) === -1) ? entry.hide() : entry.show();
        });
    });

    var upgradeStatus = $('#classificationUpgradeWarning').attr('upgrade-status');
    if (upgradeStatus === "In Process") {
        $('#classificationUpgradeWarning').modal('show');
    }

    $('.js-classification-upgrade').on("click",function(){
        var hasInvalidLicense = $('.js-classification-upgrade').attr('data-invalid-license');
        if (hasInvalidLicense === "true") {
            showErrorModal();
        } else {
            setLocalStorage();
        }
    })
});