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

                attachmentsHTML+= '    <div class="contentModule contentModule-wrap"><div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100"> \n' ;
                if(value.contactType == 'GM'){
                    attachmentsHTML += '<span class="contentModule-headline">'+getI18nText("profileCompany.generalManager.title")+'</span>';
                }
                else{
                    attachmentsHTML += '<span class="contentModule-headline">'+getI18nText("profileCompany.generalManager.title")+'</span>';
                }
                attachmentsHTML +='              <div class="contentModule-headline-border"></div> </div> </div> \n' +

                    '\n' +
                    '                                    <div class="js-myAccount-edit-toggle">\n' +
                    '                                        <div class="row">\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.firstname")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.firstName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.middlename")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.middleName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.lastname")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.lastName)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.mobilenumber")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.mobileNumber)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.email")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.email)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                            <div class="col-md-6">\n' +
                    '                                                <dl class="dlList dlList_separated">\n' +
                    '                                                    <dt class="headline-golden">'+getI18nText("general.nationalid")+'</dt>\n' +
                    '                                                    <dd>'+transformEmptyOrNull(value.nationalId)+'</dd>\n' +
                    '                                                </dl>\n' +
                    '                                            </div>\n' +
                    '                                        </div>\n';

                    var documentsAdded = false;
                    
                
                    if(contactUpdateHistoryEntry.attachedDocuments[attachmentIndex] != undefined){
                        attachmentsHTML+= '                                        <div class="contentModule contentModule-wrap"><div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100"> \n' +
                            '                                            <span class="contentModule-headline">'+getI18nText("general.documents")+'</span> <div class="contentModule-headline-border"></div> </div> </div> \n' +
                            '                                            <div class="row ml-2">\n' +
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
            attachmentsHTML += '<div class="contentModule contentModule-wrap"><div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">\n' +
                '                                        <span class="contentModule-headline">\n' +
                '                                            <icon:documents/>\n'+getI18nText('general.supportingDocuments')+
                '                                        </span> <div class="contentModule-headline-border"></div> </div> </div>\n' +
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

var downloadIcon = '<svg xmlns="http://www.w3.org/2000/svg" width="23.265" height="24" viewBox="0 0 23.265 24">'
                        +'<g id="Download" transform="translate(-465.44 -1494.127)">'
                        +'<path id="Path_1894" data-name="Path 1894" d="M487.935,1509.231a.769.769,0,0,0-.77.77v10.064H466.98V1510a.77.77,0,0,0-1.54,0v11.6H488.7V1510A.769.769,0,0,0,487.935,1509.231Z" transform="translate(0 -3.477)" fill="#00a6be"/>'
                        +'<path id="Path_1895" data-name="Path 1895" d="M473.908,1504.419l4.042,4.043V1494.9a.77.77,0,0,1,1.54,0v13.562l4.039-4.04a.77.77,0,1,1,1.088,1.089l-5.9,5.9-5.9-5.9a.77.77,0,0,1,1.089-1.089Z" transform="translate(-1.647 0)" fill="#00a6be"/>'
                        +'</g>'
                    +'</svg>';

var generalManagerIcon = '<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23"><g fill="none" fill-rule="evenodd"><path fill="#5CC83B" d="M14.579 3.208a2.845 2.845 0 1 1-5.69-.003 2.845 2.845 0 0 1 5.69.003z"/><path fill="#5CC83B" d="M16.285 10.603H7.182a4.553 4.553 0 0 1 9.103 0z"/><g fill="#32465A"><path d="M6.05 17.252a2.051 2.051 0 1 1-4.102 0 2.051 2.051 0 0 1 4.102 0z"/><path d="M7.635 22.938H.364a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.636 3.635zm13.799-5.686a2.051 2.051 0 1 1-4.103 0 2.051 2.051 0 0 1 4.103 0z"/><path d="M23.018 22.938h-7.27a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.635 3.635z"/><path d="M21.434 17.252a2.051 2.051 0 1 1-4.103 0 2.051 2.051 0 0 1 4.103 0z"/><path d="M23.018 22.938h-7.27a3.634 3.634 0 0 1 3.635-3.635 3.634 3.634 0 0 1 3.635 3.635zm-9.234-5.686a2.05 2.05 0 1 1-4.102 0c0-1.135.918-2.05 2.05-2.05 1.135 0 2.052.915 2.052 2.05z"/><path d="M15.369 22.938h-7.27a3.633 3.633 0 0 1 3.634-3.635 3.634 3.634 0 0 1 3.636 3.635z"/></g><path stroke="#32465A" stroke-linecap="round" stroke-linejoin="round" d="M4 14v-2h16v2m-8-2v2"/></g></svg>';
var pdfIcon = '<svg id="PDF-Docments" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="59.572" height="78.222" viewBox="0 0 59.572 78.222">'
                +'<defs>'
                +'<clipPath id="clip-path">'
                +'  <rect id="Rectangle_1212" data-name="Rectangle 1212" width="59.572" height="78.222" fill="none" stroke="#00a6be" stroke-width="2"/>'
                +'</clipPath>'
                +'</defs>'
                +'<g id="Group_1696" data-name="Group 1696" clip-path="url(#clip-path)">'
                +'<path id="Path_2326" data-name="Path 2326" d="M59.65,16.758V74.667a3.371,3.371,0,0,1-3.089,3.6H3.589A3.371,3.371,0,0,1,.5,74.667V4.127A3.371,3.371,0,0,1,3.589.531H44.222" transform="translate(-0.296 -0.279)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'
                +'<path id="Path_2327" data-name="Path 2327" d="M123.109,16.855,108.186,1.128V17.008Z" transform="translate(-64.06 -0.592)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'
                +'<line id="Line_552" data-name="Line 552" x2="59.15" transform="translate(0.204 54.875)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'
                +'<path id="Path_2328" data-name="Path 2328" d="M52.169,31.25s-2.985-5.777,0-8.08a3.489,3.489,0,0,1,2.273,4.323s-5.4,23.17-11.333,29.17c0,0-2.747,1.53-3.788-.53a2.97,2.97,0,0,1,1.705-3.273s16.349-9.893,26.52-8.964c0,0,2.155,1.511.282,3.313,0,0-1.977.429-5.363-1.312a6.206,6.206,0,0,1-.621-.313C60.991,45.019,58.532,43.632,52.169,31.25Z" transform="translate(-23.258 -12.171)" fill="none" stroke="#00a6be" stroke-miterlimit="10" stroke-width="2"/>'
                +'<path id="Path_2329" data-name="Path 2329" d="M33.02,124.42a20.365,20.365,0,0,1,3.537-.289,5.675,5.675,0,0,1,4,1.246,4.129,4.129,0,0,1,1.25,3.137,4.545,4.545,0,0,1-1.1,3.2,5.752,5.752,0,0,1-4.363,1.646,6.033,6.033,0,0,1-1.483-.133v6.006H33.02Zm1.843,7.229a5.9,5.9,0,0,0,1.525.156c2.224,0,3.579-1.135,3.579-3.2,0-1.98-1.334-2.936-3.367-2.936a7.368,7.368,0,0,0-1.737.155Z" transform="translate(-19.552 -65.204)" fill="#00a6be"/>'
                +'<path id="Path_2330" data-name="Path 2330" d="M60.643,124.442a25.216,25.216,0,0,1,3.918-.311c2.647,0,4.532.645,5.782,1.868a7.087,7.087,0,0,1,2.012,5.383,8.159,8.159,0,0,1-2.054,5.828c-1.335,1.4-3.537,2.158-6.311,2.158a28.366,28.366,0,0,1-3.347-.178ZM62.486,137.7a11.111,11.111,0,0,0,1.864.111c3.94,0,6.078-2.313,6.078-6.362.021-3.537-1.885-5.783-5.782-5.783a9.994,9.994,0,0,0-2.16.2Z" transform="translate(-35.909 -65.204)" fill="#00a6be"/>'
                +'<path id="Path_2331" data-name="Path 2331" d="M95.224,124.365h7.688v1.624H97.067v4.983h5.4v1.6h-5.4v6.785H95.224Z" transform="translate(-56.385 -65.327)" fill="#00a6be"/>'
                +'</g>'
                +'</svg>';
var companyRepresentativeIcon = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="#32465A" d="M20.5 17.271v5.272a.445.445 0 0 1-.432.457h-16.636a.445.445 0 0 1-.432-.457v-5.272c0-.833 2.714-2.371 6.1-3.822v-1.767a2.126 2.126 0 0 1-.476-.525c-.362-.544-.586-1.312-.627-2.332-.41-.243-.664-.831-.71-1.476-.048-.663.146-1.245.572-1.51l-.162-.412a7.828 7.828 0 0 1-.212-.63c-.172-.61-.206-1.098-.023-1.492.21-.45.638-.643 1.233-.595 1.12-2.404 6.725-2.273 7.355.393.19.8.075 1.578-.238 2.419a8.931 8.931 0 0 1-.115.292c.392.306.572.884.52 1.538-.052.645-.306 1.23-.715 1.473-.04 1.02-.264 1.788-.626 2.332a2.126 2.126 0 0 1-.476.525v1.767c3.385 1.45 6.099 2.99 6.099 3.822z"/></svg>';


