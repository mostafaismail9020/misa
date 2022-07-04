var element = document.getElementsByClassName('btn-contact');
var btnText = element[0] && element[0].textContent;

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const partner= urlParams.get('partner')
if(partner==='MCIT'){
    document.getElementById('partnerSectors').value = 'information-technology';
     $("#partnerSectors").prop("disabled", true);
}

function onContactSubmit() {
    //element[0].disabled = true;
    //element[0].textContent = site.messages().sending;
    $.ajax({
        url: ACC.config.contextPath + '/contactus',
        async: true,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers : {"g-recaptcha-response": grecaptcha.getResponse()},
        data: JSON.stringify({
            name: $.trim($("#crName").val()),
            email: $.trim($("#crEmail").val()),
 			mobile: $.trim($("#crMobile").val()),
            countryCode: $.trim(($(".ddl-countryCode").val() ? $(".ddl-countryCode").val() : "+966")),
            company: $.trim($("#crCompany").val()),
            jobTitle: $.trim($("#crJobTitle").val()),
            message: $.trim($("#crMessage").val()),
            contactSubject: $.trim($("#crSubjectID").val()),
            sectorCode: $.trim($("#partnerSectors").val()),
            categoryCode: $("#hfSectorID").length > 0 ? $.trim($("#hfSectorID").val()) : 0,
            opportunity: $("#hfOpportunity").length > 0 ? $.trim($("#hfOpportunity").val()) : 0,
            productCode: $("#hfProductCode").length > 0 ? $.trim($("#hfProductCode").val()) : 0
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

            if (data.indexOf('success_') >= 0)
            {
                dataLayer.push({
                    'event': 'fire_event',
                    'category': 'Contact Us Form',
                    'action': 'Successful Submit'
                });

                $(".formSuccess").removeClass("d-none");
                $("#corForm").addClass("d-none");

                var parts = data.split("_");
                if (parts.length == 2)
                {
                    var ticketNumber = parts[1];
                    $(".formTicketCreationConfirmation").removeClass("d-none");
                    $(".formTicketNumber").html(ticketNumber);
                }
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
       onContactSubmit();
}
function onContactload() {
    element.onclick = validateContact(element);
}
function updateSubjectId(e) {
    document.getElementById("crSubjectID").value = e.currentTarget.value;
    document.getElementById(e.currentTarget.id).classList.remove("required", "error");
    document.getElementById(e.currentTarget.id).parentElement.classList.remove("error");
}