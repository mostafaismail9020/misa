<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="emailSendedModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"><spring:theme code="general.emailSended.title"/></div>
            </div>
            <div class="modal-body">
                <div class="modal-heroImage">
                    <icon:modal02/>
                </div>
            </div>
            <div class="modal-description js-message">
                <spring:theme code="general.email.sent"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn_outline js-close-btn" data-dismiss="modal"><spring:theme code="general.close"/></button>
            </div>
        </div>
    </div>
</div>