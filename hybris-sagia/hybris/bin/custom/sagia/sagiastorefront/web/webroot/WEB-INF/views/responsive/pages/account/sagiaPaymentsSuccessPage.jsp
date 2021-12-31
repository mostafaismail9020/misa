<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

    <div id="paymentFeedbackModal" class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document" style="max-height: 711px">
		<c:choose>
			<c:when test="${billPaymentSuccess}">
				<div class="modal-content" id="billPaymentSuccess">
				     <div class="modal-header">
							<div class="modal-title"><spring:theme code="payments.request.submit"/></div>
						</div>
						<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" onclick="billPaymentLogOut(${isOutstandingFee})">
			                <icon:close/>
			            </button>
					<div class="modal-body">
						<input type="hidden" id="serviceId" value="${serviceId}" />
						<div class="modal-heroImage">
							<icon:modal02 />
						</div>
						<div class="modal-description">
			                <spring:theme code="payments.confirmation.thankYou"/>
							<spring:theme code="payment.success" />
							<br> <b><spring:theme code="payment.transaction" />
								 <c:out value="${transaction_id}"/>
								 </b>
						</div>
					</div>
		       </div>
			</c:when>
			<c:otherwise>
				<div class="modal-content">
				     <div class="modal-header">
							<div class="modal-title"><spring:theme code="payments.request.submit"/></div>
						</div>
					<div class="modal-body">
						<input type="hidden" id="serviceId" value="${serviceId}" />
						<div class="modal-heroImage">
							<icon:modal02 />
						</div>
						<div class="modal-description">
			                <spring:theme code="payments.confirmation.thankYou"/>
							<spring:theme code="payment.success" />
							<br> <b><spring:theme code="payment.transaction" />
								 <c:out value="${transaction_id}"/>
								 </b>
						</div>
					</div>
			            <div class="modal-secondaryContent modal-secondaryContent_wide">
			                <div class="modal-headline"><spring:theme code="payments.feedback.label"/></div>
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
			                                 <spring:theme code="text.feedback.modal.message"/>
			                            </label>
			                        </div>
			                    </div>
			                </div>
			                <div class="modal-footer">
			                    <button type="button" class="btn btn_slim" data-dismiss="modal" onclick="sendPaymentRatingFeedback()"><spring:theme code="payments.send.feedback"/></button>
			                </div>
			            </div>
			        </div>
			</c:otherwise>
		</c:choose>
    </div>
