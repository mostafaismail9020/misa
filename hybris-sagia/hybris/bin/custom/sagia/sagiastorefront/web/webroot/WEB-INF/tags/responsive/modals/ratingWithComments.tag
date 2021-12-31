<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade show" id="requestSubmittedComment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document" style="max-height: 711px">
        <div class="modal-content">
            <div class="modal-header">
                <div id="requestTitleId" class="modal-title"><spring:theme code="general.requestsubmitted"/></div>
                <div id="chatTitleId" class="modal-title" style="display:none;"><spring:theme code="general.chatFeedbackTitle"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <svg xmlns="http://www.w3.org/2000/svg" width="61" height="71" viewBox="0 0 61 71"><g fill="none" fill-rule="evenodd"><path d="M26.664 53.893L16.422 70.72l-4.23-11.484L0 61.277l8.416-14.073a4.013 4.013 0 0 0 3.45 1.428l3.545-.314 1.042 3.248c.748 2.331 3.443 3.437 5.647 2.316l3.169-1.612 1.395 1.623zm26.214-6.809l7.835 13.972-12.192-2.039-4.23 11.484-9.529-16.869 1.17-1.36 3.165 1.61c2.203 1.12 4.898.016 5.647-2.314l1.045-3.25 3.543.314a4.013 4.013 0 0 0 3.546-1.548z" fill="#1C242C"></path><path fill="#5CC83B" d="M52.399 30.39l5.201-4.205-5.201-4.203 3.144-5.818-6.464-1.953.61-6.543-6.742.597-2.016-6.273-5.994 3.052L30.601 0l-4.338 5.044-5.997-3.052-2.011 6.273-6.746-.597.612 6.543-6.462 1.953 3.144 5.818L3.6 26.185l5.203 4.206-3.144 5.815 6.462 1.955-.612 6.541 6.746-.596 2.011 6.27 5.997-3.05 4.338 5.047 4.336-5.047 5.994 3.05 2.016-6.27 6.742.596-.61-6.54 6.464-1.956z"></path><path stroke="#FFF" stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M42.5 18.74L28.13 34.62l-8.33-7.496"></path></g></svg></div>
                <div id="requestMessageId" class="modal-description">
                    <spring:theme code="specialservices.wewillreviewmessage"/>
                </div>
                <div id="chatMessageId" class="modal-description" style="display:none;">
                    <spring:theme code="general.chatFeedbackMessage"/>
                </div>
            </div>
            <div class="modal-footer">
                <button id="feedbackDashboardButtonId" type="button" class="btn btn_slim" data-dismiss="modal" onclick="window.location.href='${encodedContextPath}/dashboard'"><spring:theme code="feedback.modal.goto.dashboard"/></button>
            </div>
            <div class="modal-secondaryContent modal-secondaryContent_wide">
                <div class="modal-headline"><spring:theme code="feedback.modal.experience"/></div>
                <div class="ratingModule review">
                    <div class="ratingModule-star review active"><icon:rating-star/></div>
                    <div class="ratingModule-star review"><icon:rating-star/></div>
                    <div class="ratingModule-star review"><icon:rating-star/></div>
                    <div class="ratingModule-star review"><icon:rating-star/></div>
                    <div class="ratingModule-star review"><icon:rating-star/></div>
                </div>
                <div class="modal-body">
                    <div class="formTextArea formTextArea_slim" hidden>
                        <div class="form-group">
                            <textarea id="extensionReason" name="extensionReason" class="form-control" placeholder="."></textarea>
                            <label class="control-label" for="">
                                <spring:theme code="general.improve.here"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="sendFeedbackButton" type="button" class="btn btn_slim" data-dismiss="modal" onclick="sendFeedback()"><spring:theme code="feedback.modal.send.feedback"/></button>
                </div>
               <input type="hidden" name="csrfToken" value="${_csrf.token}" />
            </div>
        </div>
    </div>
</div>
