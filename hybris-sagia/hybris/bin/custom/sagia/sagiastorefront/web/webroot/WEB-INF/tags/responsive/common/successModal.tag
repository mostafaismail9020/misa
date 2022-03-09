<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<div class="modal fade" id="successResponseModal"  tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"></div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:success/>
				</div>
				<div class="modal-description">
					error description
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="general.close"/></button>
			</div>		
		</div>
	</div>
</div>
