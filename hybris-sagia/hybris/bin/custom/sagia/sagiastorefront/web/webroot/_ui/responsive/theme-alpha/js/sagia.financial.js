/**
 * Makes an Ajax request to Hybris to retrieve a specific financial entity from CRM(by the id).
 * @param id - The id of the entity that is requested.
 * @param attachmentURL - The base URL for an attachment found in an financial entity.
 */
function changeFinanceHDR(event,id,attachmentURL) {
    var token = $('input[name="CSRFToken"]').attr('value');

    $.ajax(ACC.config.encodedContextPath + "/financial/changeFinanceHDR/" + id, {
        type: "GET",
        contentType: "application/json;charset=utf-8",
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        success: function (data) {
            var financeData = JSON.parse(data);

            var incomeStat = financeData.incomeStat;
            var balanceSheet = financeData.balanceSheet;
            var financeContents = financeData.financeContents;
            var changeEquity = financeData.changeEquity;

            bindObjectAttributes(incomeStat);
            bindObjectAttributes(balanceSheet);
            bindObjectAttributes(changeEquity);

            var attachmentsHTML = '';

            for (var attribute in financeContents) {
                var value = financeContents[attribute];
                attachmentsHTML +='<li class="downloadList-item"><div class="downloadList-description"><span class="iconElement iconElement_pdf"><icon:pdf/></span>';
                attachmentsHTML += value.filename + '</div><div class="downloadList-actions">';
                attachmentsHTML += "<a class='link link_nowrap' href='"+attachmentURL+financeData.srId+"/"+value.documentId+"'>";
                attachmentsHTML += '<span class="iconElement iconElement_cloud"><icon:download/></span> Download </a></div></li>';
            }
            $('#documents-container').html(attachmentsHTML);

            $('#history-list').find('.historyList-item_current').removeClass('historyList-item_current');
            $(event).parent().addClass('historyList-item_current');
        },
        error: function (e) {
        },
        done: function (e) {
        }
    });
}

/**
 * Displays the data within object on the page in order for it to be visualize.
 * The field attributes of the object are bind on the HTML elements having 'name' attribute named
 * the same as the field attribute of the object.
 * @param object - The object to be displayed
 */
function bindObjectAttributes(object) {
    for(var attribute in object) {
        var value = object[attribute];
        var span = $("[name='"+attribute+"']");

        if(span == undefined)
            continue;
        else if(value!= undefined && value != null){
            span.text(value);
        }
        else{
            span.text('-');
        }

    }
}

