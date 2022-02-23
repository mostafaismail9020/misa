<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<c:if test="${(not empty accConfMsgs) || (not empty accInfoMsgs) || (not empty accErrorMsgs)}">
	<div class="modal-backdrop fade show" id="globalModalBackDrop"></div>
    <div class="modal fade show" id="globalMessageModal"  tabindex="-1" role="dialog" aria-labelledby="globalMessage" aria-hidden="true" style="display:block">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <!-- Information (confirmation) messages -->
                <c:if test="${not empty accConfMsgs}">
                    <c:forEach items="${accConfMsgs}" var="msg">
                        <div class="modal-header">
                            <div class="modal-title"></div>
                            <button type="button" class="modal-close closeAccAlertModal" data-dismiss="modal" aria-label="Close">
								<icon:close/>
							</button>
                        </div>
                        <div class="modal-body">
							<div class="modal-heroImage">
								<icon:info/>
							</div>					
                            <span class="modal-description">	
                                <spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
                            </span>
                        </div>
						<div class="modal-footer"></div>
                    </c:forEach>
                </c:if>

                <!-- Warning messages  -->
                <c:if test="${not empty accInfoMsgs}">
                    <c:forEach items="${accInfoMsgs}" var="msg">
                        <div class="modal-header">
                            <div class="modal-title"></div>
                            <button type="button" class="modal-close closeAccAlertModal" data-dismiss="modal" aria-label="Close">
								<icon:close/>
							</button>
                        </div>
                        <div class="modal-body">
							<div class="modal-heroImage">
								<icon:warning/>
							</div>					
                            <span class="modal-description">	
                                <spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
                            </span>
                        </div>
						<div class="modal-footer"></div>
                    </c:forEach>
                </c:if>

                <!-- Error messages (includes spring validation messages) -->
                <c:if test="${not empty accErrorMsgs}">
                    <c:forEach items="${accErrorMsgs}" var="msg">
                        <div class="modal-header">
                            <div class="modal-title"></div>
                            <button type="button" class="modal-close closeAccAlertModal" data-dismiss="modal" aria-label="Close">
								<icon:close/>
							</button>
                        </div>
                        <div class="modal-body">                            
							<div class="modal-heroImage">
								<icon:warning/>
							</div>	
							<span class="modal-description">				
                                <spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
                            </span>
                        </div>
						<div class="modal-footer"></div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</c:if>