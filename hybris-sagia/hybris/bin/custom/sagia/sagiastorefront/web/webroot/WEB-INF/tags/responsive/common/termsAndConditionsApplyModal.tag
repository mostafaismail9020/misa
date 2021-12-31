<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="applyNewLicenseTermsUrl" value="/cms/sagia-cms-TandC-applyNewLicense"/>
<input type="hidden" value="${applyNewLicenseTermsUrl}" id="applyNewLicenseTermsUrl"/>
<div class="modal fade" id="termsAndConditionsResponseApplyModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
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
                <button type="button" class="btn btn_slim" data-dismiss="modal"><spring:message code="general.close"/></button>
                <button type='button' class='btn btn_slim' id='acceptBtn' onclick='acceptNewLinceseTCAction();'><spring:message code="text.consent.button.accept"/></button>
            </div>
        </div>
    </div>
</div>