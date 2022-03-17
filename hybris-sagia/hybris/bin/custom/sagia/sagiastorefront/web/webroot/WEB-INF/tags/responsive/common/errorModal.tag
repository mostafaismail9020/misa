<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<div class="modal fade" id="errorResponseModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"></div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onClick="closeErrorModal()">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:attention-error/>
				</div>
				<div class="modal-description">
					error description
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="d-none"></button>
				<button type="button" class="btn" data-dismiss="modal" onClick="closeErrorModal()"><spring:theme code="general.close"/></button>
			</div>		
		</div>
	</div>
</div>
