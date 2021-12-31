var element = document.getElementsByClassName('btn-contact');
var btnText = element[0] && element[0].textContent;
function onContactSubmit() {
    element[0].disabled = true;
    element[0].textContent = site.messages().sending;
    $.ajax({
        url: ACC.config.contextPath + '/contactus',
        async: true,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify({
            name: $.trim($("#crName").val()),
            email: $.trim($("#crEmail").val()),
            mobile: $.trim(($(".ddl-countryCode").val() ? $(".ddl-countryCode").val() : "+966") + $("#crMobile").val()),
            company: $.trim($("#crCompany").val()),
            jobTitle: $.trim($("#crJobTitle").val()),
            message: $.trim($("#crMessage").val()),
            contactSubject: $.trim($("#crSubjectID").val()),
            categoryCode: $("#hfSectorID").length > 0 ? $.trim($("#hfSectorID").val()) : 0,
            opportunity: $("#hfOpportunity").length > 0 ? $.trim($("#hfOpportunity").val()) : 0
        }),
        success: function (data) {
            console.log(data);
            if (data == "mir-robot") {
                $("label.lbError").removeClass("d-none").html("<em><span>" + site.messages().mirRobot + "</span></em>");
            }
            if (data == "success") {
                if (($("#hfSectorID").length > 0 && $("#hfSectorID").val() != "0" && $("#hfSectorID").val() != 0) || $("#hfOpportunityID").length > 0) {
                    dataLayer.push({
                        'event': 'fire_event',
                        'category': 'Contact by Industry',
                        'action': $("#hfPageTitle").val(),
                        'label': 'Successful  Submit'
                    });
                }
                else {
                    dataLayer.push({
                        'event': 'fire_event',
                        'category': 'Contact Us Form',
                        'action': 'Successful Submit'
                    });
                }
                $(".formSuccess").removeClass("d-none");
                $("#corForm").addClass("d-none");
            }

            if (data.indexOf('error') >= 0 || data.indexOf('Error') >= 0) {
                $("label.lbError").removeClass("d-none").html("<em><span>" + site.messages().formSubmissionFailed + "</span></em>");
            }

            element[0].disabled = false;
            element[0].textContent = btnText;
        }
    });
}
function validateContact(event) {
    if (validateForm($(event).parents("div#corForm")) == true) {
        onContactSubmit();
        return true;
    }
    return false;
}
function onContactload() {
    element.onclick = validateContact(element);
}
function updateSubjectId(e) {
    document.getElementById("crSubjectID").value = e.currentTarget.value;
    document.getElementById(e.currentTarget.id).classList.remove("required", "error");
    document.getElementById(e.currentTarget.id).parentElement.classList.remove("error");
}