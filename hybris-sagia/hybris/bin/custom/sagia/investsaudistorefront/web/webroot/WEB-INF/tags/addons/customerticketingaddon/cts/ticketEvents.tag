<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="ticketData" required="true"
			  type="de.hybris.platform.customerticketingfacades.data.TicketData"%>
<%@ attribute name="isWoapUser" required="false"
			  type="java.lang.Boolean"%>


<c:if test="${ empty isWoapUser }">
	<c:set var="isWoapUser" value="false"/>
</c:if>


<div class="row">
	<c:forEach items="${ticketData.ticketEvents}" var="ticketEventData" varStatus="loop">
		<c:set var="msgCreationDate" scope="page">
			<fmt:formatDate value="${ticketEventData.startDateTime}" pattern="dd-MM-yy hh:mm a" />
		</c:set>

		<c:set var="cssLine2" scope="page" value="well well-sm well-quaternary" />

		<c:set var="cssLine3" scope="page" value="col-sm-5 col-md-6 well well-lg bg-secondary " />

		<c:choose>
			<c:when test="${ticketEventData.addedByAgent}">
				<c:set var="cssLine1" scope="page" value="cts-msg-history-item cts-msg-history-item-agent col-md-7 col-md-offset-5 col-sm-9 col-sm-offset-3 ct-msg-visible" />
			</c:when>
			<c:otherwise>
				<c:set var="cssLine1" scope="page" value="cts-msg-history-item cts-msg-history-item-customer col-md-7 col-sm-9 ct-msg-visible" />
			</c:otherwise>
		</c:choose>

		<%-- <div class="${cssLine1}">
			<div class="cts-msg-history-item-info">
				<b>
				<c:choose>
					<c:when test="${ticketEventData.addedByAgent}"><spring:theme code="text.account.supporttickets.history.addedBy.customerService" text="Customer Support"/></c:when>
					<c:otherwise>${fn:escapeXml(ticketEventData.author)}</c:otherwise>
				</c:choose>
				</b>
				<spring:theme code="text.account.supporttickets.updateTicket.on" text="on" />&nbsp;${msgCreationDate}
				<c:choose>
					<c:when test="${!empty ticketEventData.modifiedFields}">
						<c:forEach items="${ticketEventData.modifiedFields }" var="modifiedField">
							<c:set var="statusMessage" scope="page"							value="ticketstatus.${fn:toUpperCase(modifiedField.value.get(1).id)}" />
							<c:choose>
								<c:when test="${fn:toLowerCase(modifiedField.value.get(1).id).equals('closed')}">
									<c:set var="cssLine2" scope="page" value="well well-sm well-closed" />
								</c:when>
								<c:when	test="${fn:toLowerCase(modifiedField.value.get(0).id).equals('closed') && fn:toLowerCase(modifiedField.value.get(1).id).equals('inprocess')}">
									<c:set var="cssLine2" scope="page" value="well well-sm well-reopen" />
									<c:set var="statusMessage" scope="page"	value="ticketstatus.REOPEN" />
								</c:when>
								<c:otherwise>
									<c:set var="cssLine2" scope="page" value="well well-sm well-quaternary" />
								</c:otherwise>
							</c:choose>
							<span class="cts-msg-history-item-modified-field">${fn:escapeXml(modifiedField.key)}: <span class="cts-msg-history-item-modified-field--status cts-msg-history-item-modified-field--status-${fn:toLowerCase(modifiedField.value.get(1).id)}"><spring:message code="${statusMessage}" /></span></span>
						</c:forEach>
					</c:when>
				</c:choose>
			</div> --%>
            <div class="${cssLine2}">
                <div class="row">
                    <c:choose>
                        <c:when test="${not empty ticketData.answers}">
                            <div class="col-md-12">
                                <div class="account-section-content">
                                    <c:forEach items="${ticketData.answers}" var="answers">
                                        <div class="col-md-6">
                                            <div class="form-group" style="margin-bottom: 30px;">
                                                    <%-- <label class="control-label label-color">Q:</label>
                                                    <label class="control-label label-color">${answers.question} </label><br>
                                                    <label class="control-label label-color">A:</label>
                                                    <label class="control-label label-color" style="text-transform: lowercase;">${answers.answer} </label> --%>
                                                <div style="word-wrap: break-word; font-size: 16px;">
                                                        <%-- <spring:theme code="Q: " text="Q: " /> --%>
                                                    <spring:theme code="${answers.question}" text="${answers.question}" /><br>
                                                        <%-- <spring:theme code="A: " text="A: " />
                                                        <spring:theme code="${answers.answer}" text="${answers.answer}" /> --%>
                                                    <textarea name="${answers.question}" class="ticket-questions text" readonly>${answers.answer}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${not empty ticketEventData.text }">
                                <div class="cts-msg-history-item-msg">${fn:escapeXml(ticketEventData.text)}</div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty ticketEventData.attachments}">
                        <ul class="cts-attach-list" style="border-top: 0px solid #e5e5e5;">
                            <c:forEach items="${ticketEventData.attachments}" var="attachment">
                                <li class="cts-attach-file">
                                    <a href="../../../../${attachment.URL}" target="_new" download="${fn:escapeXml(attachment.filename)}"><span class="glyphicon glyphicon-paperclip"></span>${fn:escapeXml(attachment.filename)}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </div>
	</c:forEach>
</div>
