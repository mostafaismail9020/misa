<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade show" id="requestSubmittedComment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document" style="max-height: 711px">
        <div class="modal-content">
            <div class="modal-header">
                <div id="requestTitleId" class="modal-title"><spring:theme code="general.requestsubmitted"/></div>
                <div id="chatTitleId" class="modal-title" style="display:none;"><spring:theme code="general.chatFeedbackTitle"/></div>
            </div>
            <div class="submit-sucess-modal">
                <div class="modal-body">
                    <div class="modal-heroImage">
                        <icon:modal02 />
                    </div>
                    <div id="requestMessageId" class="modal-description">
                        <spring:theme code="specialservices.wewillreviewmessage"/>
                    </div>
                    <div id="chatMessageId" class="modal-description" style="display:none;">
                        <spring:theme code="general.chatFeedbackMessage"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="feedbackDashboardButtonId" type="button" class="btn btn_slim back_to_service" data-dismiss="modal"><spring:theme code="feedback.modal.goto.dashboard"/></button>
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
                        <button id="sendFeedbackButton" type="button" class="btn btn_slim back_to_service" data-dismiss="modal" onclick="sendFeedback()"><spring:theme code="feedback.modal.send.feedback"/></button>
                    </div>
                <input type="hidden" name="csrfToken" value="${_csrf.token}" />
                </div>
            </div>
        </div>
    </div>
</div>

<script>
     window.addEventListener('load', function () {
        $(document).on('click',"#feedbackDashboardButtonId",function(){
            window.location ='${encodedContextPath}/dashboard';
        })    
    })
</script>