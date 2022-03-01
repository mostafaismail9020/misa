<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal fade" id="termsAndConditionsResponseModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title"></div>
                <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
                    <icon:close/>
                </button>
            </div>
            <div class="modal-body">
                <div class="scrollWrapper">
                    <div class="scrollWrapper-inner">
                        <div class="modal-heroImage">
                            <icon:attention-error/>
                        </div>
                        <div class="modal-description"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="d-none"></button>
                <button type="button" class="btn" data-dismiss="modal"><spring:theme code="general.close" /></button>
            </div>
        </div>
    </div>
</div>
