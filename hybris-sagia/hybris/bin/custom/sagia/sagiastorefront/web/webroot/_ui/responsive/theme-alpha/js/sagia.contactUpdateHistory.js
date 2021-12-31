$(function(){
    $(document).on("click",".js-attach-file-download",function(e){
        console.log("down");
        var element = $(this);
        var objectId = element.data('objectId');
        var documentId = element.data('documentId');
        window.open(ACC.config.encodedContextPath + "/my-sagia/attachment/" + objectId + "/" + documentId, '_blank');
    });
});

/**
 * Makes an Ajax request to Hybris to retrieve a specific financial entity from CRM(by the id).
 * @param id - The id of the entity that is requested.
 * @param attachmentURL - The base URL for an attachment found in an financial entity.
 */
function changeContactUpdateHistoryEntry(event,id) {
    var token = $('input[name="CSRFToken"]').attr('value');

    $.ajax(ACC.config.encodedContextPath + "/contacts/history/" + id, {
        type: "GET",
        contentType: "application/json;charset=utf-8",
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader('CSRFToken', token);
        },
        success: function (data) {
            $('#history-list').find('.historyList-item_current').removeClass('historyList-item_current');
            $(event).parent().addClass('historyList-item_current');

            var contactUpdateHistoryEntry = JSON.parse(data);
            var updatedContacts = contactUpdateHistoryEntry.updatedContacts;
            $('#contact-update-history-id').text(contactUpdateHistoryEntry.srId);

            var statusClass = '';
            if(contactUpdateHistoryEntry.status == 'E0001'){
                statusClass = 'statusIndicator statusIndicator_inprocess';
            }
            else if(contactUpdateHistoryEntry.status == 'E0014'){
                statusClass = 'statusIndicator statusIndicator_accepted';
            }
            else if(contactUpdateHistoryEntry.status == 'E0006'){
                statusClass = 'statusIndicator statusIndicator_rejected';
            }

            $('#currentStatus').attr('class',statusClass);
            $('#statusText').text(contactUpdateHistoryEntry.statusDescription);

            var attachmentsHTML = '';
            var attachmentIndex = 0;

            for (var attribute in updatedContacts) {
                var value = updatedContacts[attribute];

                attachmentsHTML +='<div class="contentModule-section">\n' +
                    '                                    <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">\n' +
                    '                                        <div class="contentModule-headline">';
                    if(value.contactType == 'GM'){
                        attachmentsHTML += '<span class="iconElement iconElement_generalManager">'+generalManagerIcon+'</span>'+getI18nText("profileCompany.generalManager.title");
                    }
                    else{
                        attachmentsHTML += '<span class="iconElement iconElement_generalManager">'+companyRepresentativeIcon+'</span>'+getI18nText("profileCompany.companyRepresentative.title");
                    }
                    attachmentsHTML +='</div>\n' +
                    '                                    </div>\n' +
                    '\n' +
                    '                                    <div class="js-myAccount-edit-toggle">\n' +
                    '                                        <div class="row">\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.firstname")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.firstName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.middlename")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.middleName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.lastname")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.lastName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.mobilenumber")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.mobileNumber)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.email")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.email)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt>'+getI18nText("general.nationalid")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.nationalId)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                        </div>\n';

                    var documentsAdded = false;
                    if(contactUpdateHistoryEntry.attachedDocuments[attachmentIndex] != undefined){
                        attachmentsHTML+= '                                        <div class="documentSection">\n' +
                            '                                            <div class="documentSection-headline">'+getI18nText("general.documents")+'</div>\n' +
                            '                                            <div class="row">\n' +
                            '                                                <div class="col-md-6">\n' +
                            '                                                    <div class="documentSection-item">\n' +
                            '                                                        <div class="documentSection-img">\n' +
                            '                                                            <span class="iconElement iconElement_pdf02">'+pdfIcon+'</span>\n' +
                            '                                                        </div>\n' +
                            '                                                        <div class="documentSection-name js-attach-file-download"\n' +
                            '                                                             data-object-id="'+contactUpdateHistoryEntry.attachedDocuments[attachmentIndex].objectId+'"\n' +
                            '                                                             data-document-id="'+contactUpdateHistoryEntry.attachedDocuments[attachmentIndex].documentID+'"\n' +
                            '                                                             style="cursor: pointer">'+getI18nText("general.idoriqama")+
                            '                                                        </div>\n' +
                            '                                                    </div>\n' +
                            '                                                </div>';
                        var documentsAdded = true;
                    }

                attachmentIndex++;
                if(value.contactType == 'GM' && contactUpdateHistoryEntry.attachedDocuments[attachmentIndex] != undefined){
                attachmentsHTML+=                        '<div class="documentSection-item">\n' +
                        '                                                        <div class="documentSection-img">\n' +
                        '                                                            <span class="iconElement iconElement_pdf02">'+pdfIcon+'</span>\n' +
                        '                                                        </div>\n' +
                        '                                                        <div class="documentSection-name js-attach-file-download"\n' +
                        '                                                             data-object-id="'+contactUpdateHistoryEntry.attachedDocuments[attachmentIndex].objectId+'"\n' +
                        '                                                             data-document-id="'+contactUpdateHistoryEntry.attachedDocuments[attachmentIndex].documentID+'"\n' +
                        '                                                             style="cursor: pointer">'+getI18nText("general.gosicertificate")+
                        '                                                        </div>\n' +
                        '                                                    </div>\n' +
                        '                                                </div>';
                    attachmentIndex++;
                }
                if(documentsAdded){
                    attachmentsHTML+='                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </div>\n' +
                        '                                    \n' +
                        '                                </div>';
                }
            }
            // Supporting documents
            attachmentsHTML += '<div class="contentModule">\n' +
                '                                    <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">\n' +
                '                                        <div class="contentModule-headline">\n' +
                '                                            <icon:documents/>\n'+getI18nText('general.supportingDocuments')+
                '                                        </div>\n' +
                '                                        <ul class="downloadList downloadList_maxHeight" id="documents-container">\n';

            for(index in contactUpdateHistoryEntry.attachedDocuments){
                var doc = contactUpdateHistoryEntry.attachedDocuments[index];

                attachmentsHTML +='                                      <li class="downloadList-item">\n' +
                    '                                                    <div class="downloadList-description">\n' +
                    '                                                        <span class="iconElement iconElement_pdf">'+pdfIcon+'</span>' + doc.fileName +
                    '                                                    </div>\n' +
                    '                                                    <div class="downloadList-actions">\n' +
                    '                                                        <a class="link link_nowrap" href=\''+ACC.config.encodedContextPath+'/attachment/pdf/'+doc.objectId+'/'+doc.documentID+'\' download>\n' +
                    '                                                            <span class="iconElement iconElement_cloud">'+downloadIcon+'</span>\n' +
                    '                                                            Download\n' +
                    '                                                        </a>\n' +
                    '                                                    </div>\n' +
                    '                                                </li>';
            }
            attachmentsHTML += '</ul></div></div>';

            $('#history-container').html(attachmentsHTML);

            if (contactUpdateHistoryEntry.comments != null && contactUpdateHistoryEntry.comments.length > 0) {
                var commentsTemplate = document.getElementById("contactUpdateComments-template");
                var commentsTemplateHtml = commentsTemplate.innerHTML;
                var commentsHtml = "";
                var hideCommentsSection = false;
                $('#contactUpdateComments').removeAttr('hidden');
                for (var key in contactUpdateHistoryEntry.comments) {
                    var dateFormatted = '';
                    var commentsBy = '';
                    var comments = '';

                    if(contactUpdateHistoryEntry.comments[key]["commentsBy"] != undefined && contactUpdateHistoryEntry.comments[key]["commentsBy"] != null){
                        commentsBy = contactUpdateHistoryEntry.comments[key]["commentsBy"];
                    }

                    if(contactUpdateHistoryEntry.comments[key].commentDate != undefined && contactUpdateHistoryEntry.comments[key].commentDate != null){
                        dateFormatted = contactUpdateHistoryEntry.comments[key].commentDate.dateFormatted;
                    }

                    if(contactUpdateHistoryEntry.comments[key]["comments"] != undefined && contactUpdateHistoryEntry.comments[key]["comments"] != null){
                        comments = contactUpdateHistoryEntry.comments[key]["comments"];
                    }

                    if(contactUpdateHistoryEntry.comments.length == 1 && commentsBy == '' && comments == ''){
                        hideCommentsSection = true;
                    }

                    commentsHtml += commentsTemplateHtml
                        .replace(
                            /{{commentsBy}}/g,
                            commentsBy)
                        .replace(
                            /{{dateFormatted}}/g,
                            dateFormatted)
                        .replace(
                            /{{comments}}/g,
                            comments
                        );
                }
                if(hideCommentsSection){
                    $('#contactUpdateComments').attr('hidden', 'hidden');
                } else{
                    $('#contactUpdateCommentsList').html(commentsHtml);
                }
            } else{
                $('#contactUpdateComments').attr('hidden', 'hidden');
            }

        },
        error: function (e) {
        },
        done: function (e) {
        }
    });
}

function transformEmptyOrNull(value){
    if(value == undefined || value == null || value.length ==0)
        return '-';

    return value;
}

var downloadIcon = '<svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="18px" height="18px" viewBox="0 0 18 18" enable-background="new 0 0 18 18" xml:space="preserve">\n' +
    '    <path fill="#5CC83B" d="M3.769,12.936c-0.154,0-1.459-0.028-2.381-0.936C0.799,11.421,0.5,10.627,0.5,9.64\n' +
    '        c0-1.043,0.45-2.001,1.234-2.629c0.568-0.455,1.26-0.683,1.964-0.664C4.094,3.589,6.333,1.5,9,1.5c1.962,0,3.78,1.164,4.713,2.99\n' +
    '        c0.922,0.045,1.791,0.423,2.479,1.084c0.832,0.8,1.31,1.942,1.31,3.134c0,2.946-2.33,4.05-3.562,4.222l-1.128,0.005\n' +
    '        c-0.276,0-0.5-0.224-0.5-0.5s0.224-0.5,0.5-0.5h1.059c0.039-0.011,2.631-0.425,2.631-3.227c0-0.921-0.365-1.801-1.003-2.414\n' +
    '        c-0.572-0.551-1.312-0.835-2.082-0.807c-0.204,0.013-0.394-0.11-0.477-0.297C12.218,3.556,10.672,2.5,9,2.5\n' +
    '        c-2.305,0-4.219,1.947-4.356,4.432c-0.008,0.146-0.08,0.282-0.197,0.371C4.33,7.392,4.178,7.425,4.036,7.392\n' +
    '        C3.443,7.259,2.847,7.401,2.36,7.791C1.813,8.228,1.5,8.902,1.5,9.64c0,0.706,0.196,1.259,0.584,1.643\n' +
    '        c0.66,0.656,1.657,0.646,1.693,0.653h1.392c0.276,0,0.5,0.224,0.5,0.5s-0.224,0.5-0.5,0.5H3.783\n' +
    '        C3.781,12.936,3.776,12.936,3.769,12.936z M9.849,16.609V8.957c0-0.276-0.224-0.5-0.5-0.5s-0.5,0.224-0.5,0.5v7.652\n' +
    '        c0,0.276,0.224,0.5,0.5,0.5S9.849,16.886,9.849,16.609z M9.701,16.963l2.088-2.086c0.195-0.195,0.195-0.512,0-0.707\n' +
    '        s-0.512-0.195-0.707,0l-1.734,1.732L7.615,14.17c-0.195-0.195-0.512-0.195-0.707,0s-0.195,0.512,0,0.707l2.087,2.086\n' +
    '        c0.098,0.098,0.226,0.146,0.354,0.146S9.604,17.061,9.701,16.963z"></path>\n' +
    '</svg>';

var generalManagerIcon = '<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23"><g fill="none" fill-rule="evenodd"><path fill="#5CC83B" d="M14.579 3.208a2.845 2.845 0 1 1-5.69-.003 2.845 2.845 0 0 1 5.69.003z"/><path fill="#5CC83B" d="M16.285 10.603H7.182a4.553 4.553 0 0 1 9.103 0z"/><g fill="#32465A"><path d="M6.05 17.252a2.051 2.051 0 1 1-4.102 0 2.051 2.051 0 0 1 4.102 0z"/><path d="M7.635 22.938H.364a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.636 3.635zm13.799-5.686a2.051 2.051 0 1 1-4.103 0 2.051 2.051 0 0 1 4.103 0z"/><path d="M23.018 22.938h-7.27a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.635 3.635z"/><path d="M21.434 17.252a2.051 2.051 0 1 1-4.103 0 2.051 2.051 0 0 1 4.103 0z"/><path d="M23.018 22.938h-7.27a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.635 3.635zm-9.234-5.686a2.05 2.05 0 1 1-4.102 0c0-1.135.918-2.05 2.05-2.05 1.135 0 2.052.915 2.052 2.05z"/><path d="M15.369 22.938h-7.27a3.633 3.633 0 0 1 3.634-3.635 3.634 3.634 0 0 1 3.636 3.635z"/></g><path stroke="#32465A" stroke-linecap="round" stroke-linejoin="round" d="M4 14v-2h16v2m-8-2v2"/></g></svg>';
var pdfIcon = '<svg width="21" height="26" xmlns="http://www.w3.org/2000/svg"><g fill-rule="nonzero" fill="none"><path d="M6.533 15.579a.876.876 0 0 1-.453-.136c-.454-.362-.544-.724-.499-.996.09-.724.998-1.494 2.72-2.309.68-1.494 1.315-3.35 1.723-4.89-.453-.996-.907-2.263-.59-2.988a.855.855 0 0 1 .5-.543c.09-.045.362-.09.453-.09.226 0 .408.271.59.452.135.181.452.544-.182 3.079.59 1.268 1.496 2.535 2.312 3.44.59-.09 1.088-.18 1.541-.18.726 0 1.134.18 1.315.498.136.271.09.588-.181.996-.272.362-.59.543-.998.543-.544 0-1.178-.362-1.904-1.041-1.27.271-2.765.77-3.99 1.267-.362.815-.725 1.45-1.087 1.947-.454.68-.862.951-1.27.951zm1.224-2.309c-.952.543-1.36.996-1.405 1.222 0 .046 0 .136.181.317.09 0 .499-.18 1.224-1.539zm6.166-1.992c.363.272.453.407.68.407.09 0 .408 0 .544-.18.045-.091.09-.137.09-.182-.045-.045-.135-.09-.543-.09-.182-.046-.454 0-.771.045zm-3.4-2.988c-.318 1.132-.771 2.309-1.224 3.44.952-.362 1.994-.678 2.947-.905-.59-.77-1.18-1.63-1.723-2.535zM10.25 4.44c-.046 0-.59.815.045 1.45.453-.952 0-1.45-.045-1.45z" fill="#5CC83B"/><path d="M19.998 5.89L14.739.638c-.09-.09-.181-.135-.272-.135H1.32c-.363 0-.68.316-.68.86v24.041c0 .135.271.452.634.452h18.18c.363 0 .635-.317.635-.452V6.388c.045-.317 0-.407-.091-.498zM1.546 18.16V1.408H14.15v5.07h5.077V18.16H1.547z" fill="#5CC83B"/><path d="M5.581 24.498h-.725v-4.573H6.17c.182 0 .409.046.59.091s.363.136.499.272c.136.135.272.271.362.452.09.181.136.363.136.589 0 .226-.045.453-.136.634-.09.18-.181.362-.317.453-.136.09-.317.271-.544.316-.227.046-.408.091-.635.091h-.544v1.675zm0-3.984v1.811h.68c.09 0 .182 0 .272-.045.09-.046.182-.09.227-.136a.947.947 0 0 0 .181-.272c.046-.09.046-.271.046-.453 0-.09 0-.18-.046-.271 0-.09-.045-.181-.136-.272a.464.464 0 0 0-.272-.226c-.136-.045-.272-.09-.453-.09l-.499-.046zM12.29 22.099c0 .362-.044.679-.135.95-.09.272-.181.498-.317.68a1.73 1.73 0 0 1-.409.407c-.136.09-.317.181-.453.226-.136.046-.272.09-.408.09H8.573v-4.527h1.36c.363 0 .726.046.998.182.272.135.544.271.725.498.182.226.318.452.454.679.136.271.18.543.18.815zm-2.175 1.856c.498 0 .861-.181 1.088-.498.226-.317.317-.77.317-1.404 0-.18 0-.362-.045-.543a1.079 1.079 0 0 0-.272-.498 1.63 1.63 0 0 0-.544-.362c-.227-.09-.499-.136-.862-.136h-.453v3.44h.77zM14.15 20.514v1.449h1.904v.498H14.15v2.037h-.771v-4.573h2.856v.544H14.15z" fill="#FFF"/></g></svg>';
var companyRepresentativeIcon = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="#32465A" d="M20.5 17.271v5.272a.445.445 0 0 1-.432.457h-16.636a.445.445 0 0 1-.432-.457v-5.272c0-.833 2.714-2.371 6.1-3.822v-1.767a2.126 2.126 0 0 1-.476-.525c-.362-.544-.586-1.312-.627-2.332-.41-.243-.664-.831-.71-1.476-.048-.663.146-1.245.572-1.51l-.162-.412a7.828 7.828 0 0 1-.212-.63c-.172-.61-.206-1.098-.023-1.492.21-.45.638-.643 1.233-.595 1.12-2.404 6.725-2.273 7.355.393.19.8.075 1.578-.238 2.419a8.931 8.931 0 0 1-.115.292c.392.306.572.884.52 1.538-.052.645-.306 1.23-.715 1.473-.04 1.02-.264 1.788-.626 2.332a2.126 2.126 0 0 1-.476.525v1.767c3.385 1.45 6.099 2.99 6.099 3.822z"/></svg>';


