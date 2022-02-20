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
                <div class="scrollWrapper popup-body">
                    <div class="scrollWrapper-inner pt-0">
                    <h4 class="title-top pb-3"><spring:message code="apply.for.new.license.terms.conditions"/></h4>
                        <div class="modal-heroImage">
                            <icon:attention-error/>
                        </div>
                        <div class="modal-description"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer tc-footer">
                <a href = "/en/licenseTermsAndConditions/download/sagiaCMSParagraphMediaComponent" target="_blank" download class="btn btn-ctrl btn_slim text-uppercase font-weight-bold">
	                <spring:message code="apply.for.new.license.terms.conditions.download"/>
                    <svg xmlns="http://www.w3.org/2000/svg" width="21.615" height="18.827" viewBox="0 0 21.615 18.827">
	  					<path id="download" d="M9.119,0H12.5A.952.952,0,0,1,13.51.883V7.06h3.7c.751,0,1.127.791.6,1.254l-6.421,5.6a.906.906,0,0,1-1.153,0L3.8,8.314c-.532-.463-.156-1.254.6-1.254H8.106V.883A.952.952,0,0,1,9.119,0Zm12.5,13.826v4.118a.952.952,0,0,1-1.013.883H1.013A.952.952,0,0,1,0,17.945V13.826a.952.952,0,0,1,1.013-.883H7.206l2.069,1.8a2.4,2.4,0,0,0,3.065,0l2.069-1.8H20.6A.952.952,0,0,1,21.615,13.826ZM16.38,17.062a.852.852,0,1,0-.844.735A.8.8,0,0,0,16.38,17.062Zm2.7,0a.852.852,0,1,0-.844.735A.8.8,0,0,0,19.082,17.062Z" fill="#00a6be"/>
					</svg>
					
				</a>
                <button type="button" class="btn btn-normal btn_bold ml-auto d-block" data-dismiss="modal"><spring:message code="general.close"/></button>
                <button type='button' class='btn btn-normal btn-bg btn_bold' id='acceptBtn' onclick='acceptNewLinceseTCAction();'>
                	<spring:message code="text.consent.button.accept"/>
                </button>
            </div>
        </div>
    </div>
</div>