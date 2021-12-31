var element = document.getElementsByClassName('btn-contact');
var btnText = element[0] && element[0].textContent;

function onRegistrationSubmit() {
    element[0].disabled = true;
    element[0].textContent = site.messages().sending;
    $.ajax({
        url: ACC.config.contextPath + '/registration',
        async: true,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify({
            userEmail: $.trim($("#emailField").val()),
            webinarCode: $.trim($("#webinarCode").val())
        }),
        success: function (data) {
            console.log(data);
            if (data == "success") {

                $(".alert-success").removeClass("d-none");
                $("#corForm").addClass("d-none");
            }

            if (data == "duplicated") {
                $(".alert-warning").removeClass("d-none");
                $("#corForm").addClass("d-none");
            }

            if (data == "error") {
                $(".alert-danger").removeClass("d-none");
                $("#corForm").addClass("d-none");
            }

            element[0].disabled = false;
            element[0].textContent = btnText;
        }
    });
}

function validateRegist(event) {
    if (validateForm($(event).parents("div#corForm")) == true) {
        onRegistrationSubmit();
        return true;
    }
    return false;
}

function onRegistrationload() {
    element.onclick = validateRegist(element);
}