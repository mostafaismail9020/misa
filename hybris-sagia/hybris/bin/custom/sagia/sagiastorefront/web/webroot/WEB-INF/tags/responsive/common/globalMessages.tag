<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<c:if test="${(not empty accConfMsgs) || (not empty accInfoMsgs) || (not empty accErrorMsgs)}">
	<div class="global-alerts container globalMessage-holder globalMessage-holder_marginTop">
		<%-- Information (confirmation) messages --%>
		<c:if test="${not empty accConfMsgs}">
			<c:forEach items="${accConfMsgs}" var="msg">
				<div class="globalMessage alert alert-info alert-dismissable getAccAlert">
					<button class="globalMessage-action_close close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button" aria-label="Close"><icon:close/></button>
					<span class="globalMessage-msg">
						<div class="globalMessage-icon globalMessage-icon_warning">
							<icon:info/>
						</div>					
						<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
					</span>
				</div>
			</c:forEach>
		</c:if>

		<%-- Warning messages --%>
		<c:if test="${not empty accInfoMsgs}">
			<c:forEach items="${accInfoMsgs}" var="msg">
				<div class="globalMessage alert alert-warning alert-dismissable getAccAlert">
					<button class="globalMessage-action_close close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button" aria-label="Close"><icon:close/></button>
					<span class="globalMessage-msg">
						<div class="globalMessage-icon globalMessage-icon_warning">
							<icon:warning/>
						</div>					
						<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
					</span>
				</div>
			</c:forEach>
		</c:if>

		<%-- Error messages (includes spring validation messages)--%>
		<c:if test="${not empty accErrorMsgs}">
			<c:forEach items="${accErrorMsgs}" var="msg">
				<div class="globalMessage alert alert-danger alert-dismissable getAccAlert">
					<button class="globalMessage-action_close close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button" aria-label="Close"><icon:close/></button>
					<span class="globalMessage-msg">
						<div class="globalMessage-icon">
							<icon:warning/>
						</div>					
						<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
					</span>
				</div>
			</c:forEach>
		</c:if>
	</div>
</c:if>