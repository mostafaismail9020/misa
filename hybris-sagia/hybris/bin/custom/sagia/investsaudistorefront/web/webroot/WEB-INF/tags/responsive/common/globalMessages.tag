<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<c:if test="${(not empty accConfMsgs) || (not empty accInfoMsgs) || (not empty accErrorMsgs)}">
	<div class="global-alerts">
		
		<%-- Information (confirmation) messages --%>
		<c:if test="${not empty accConfMsgs}">
			<c:forEach items="${accConfMsgs}" var="msg">
				<div class="alert alert-info alert-dismissable getAccAlert">
					<button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
						<img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
					</button>
					<div class="alert-wrapper">
						<img src="/investsaudistorefront/_ui/responsive/common/images/primary.png">
						<span><spring:theme code="${msg.code}" arguments="${msg.attributes}"/></span>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<%-- Warning messages --%>
		<c:if test="${not empty accInfoMsgs}">
			<c:forEach items="${accInfoMsgs}" var="msg">
				<div class="alert alert-warning alert-dismissable getAccAlert">
					<button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
						<img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
					</button>
					<div class="alert-wrapper">
						<img src="/investsaudistorefront/_ui/responsive/common/images/warning.png">
						<span><spring:theme code="${msg.code}" arguments="${msg.attributes}"/></span>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<%-- Error messages (includes spring validation messages)--%>
		<c:if test="${not empty accErrorMsgs}">
			<c:forEach items="${accErrorMsgs}" var="msg">
				<div class="alert alert-danger alert-dismissable getAccAlert">
					<button class="close closeAccAlert" aria-hidden="true" data-dismiss="alert" type="button">
						<img src="/investsaudistorefront/_ui/responsive/common/images/close-icon.png">
					</button>
					<div class="alert-wrapper">
						<img src="/investsaudistorefront/_ui/responsive/common/images/danger.png">
						<span><spring:theme code="${msg.code}" arguments="${msg.attributes}"/></span>
					</div>
				</div>
			</c:forEach>
		</c:if>

	</div>
</c:if>