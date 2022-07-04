<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
            <icon:close/>
        </button>
    </div>
    <div class="modal-body">
        <div class="ticketModule">
            <div class="ticketModule-top">
                <div class="row">
                    <div class="col-12">
                        <div class="ticketModule-details">
                            <p id="financialSurveyErrorUpdateLabel" style="color:red; display:none"><b><spring:theme code="general.message.could.not.be.sent"/></b></p>
                        </div>
                    </div>

                    <div class="col-12">
                        <ul id="financialSurveyMessagesList" class="messageList">
                            <c:forEach items="${messages}" var="message">
                                <li class="messageList-item">
                                    <div class="messageList-content">
                                        <h2 class="messageList-name">${message.commentBy}</h2>
                                        <h3 class="messageList-date">${message.commentDate}</h3>
                                        <div class="messageList-message">
                                            <p>${message.content}</p>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="formTextArea">
                            <div class="form-group">
                                <textarea id="financialSurveyInputNewMessage" class="form-control form-control_slim" placeholder="."></textarea>
                                <label class="control-label" for="financialSurveyInputNewMessage">
                                    <spring:theme code="general.comments"/>
                                </label>
                            </div>
                        </div>
                        <div class="ticketModule-actions text-center">
                            <button class="btn" id="financialSurveyNewMessageBtn" data-current-srid="${expandedComplaintFormData.srID}" <c:if test="${expandedComplaintFormData.srStCode == 'E0010'}"> disabled </c:if>><spring:theme code="general.send.message"/>
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="csrfToken" value="${_csrf.token}"/>
                </div>
            </div>
        </div>
    </div>
</div>

<script id="messages-template" type="text/template">
    <li class="messageList-item">
        <div class="messageList-content">
            <h2 class="messageList-name">{{commentBy}}</h2>
            <h3 class="messageList-date">{{commentDate}}</h3>
            <div class="messageList-message">
                <p>{{content}}</p>
            </div>
        </div>
    </li>
</script>