<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="modal-content">
    <div class="modal-header">
        <div class="modal-title" id="exampleModalLabel"><spring:theme code="general.ticketNumber"/>: ${legalConsultation_detail.srId}</div>
        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
            <icon:close/>
        </button>
    </div>
    <div class="modal-body">
        <div class="ticketModule">
            <div class="ticketModule-top">
                <div class="row">
                    <div class="col-md-4">

                        <div class="ticketModule-details">
                            <p><span
                                    class="ticketModule-label"><spring:theme code="general.type"/></span> ${legalConsultation_detail.legalInquiry.legEnqDesc}
                            </p>
                            <p><span
                                    class="ticketModule-label"><spring:theme code="general.status"/></span> ${legalConsultation_detail.srStDesc}
                            </p>
                            <p><span class="ticketModule-label"><spring:theme code="general.support.documents"/></span></p>

                            <ul class="downloadList downloadList_secondary">
                                <c:forEach items="${legalConsultation_detail.contentHDRSet}" var="attachment">
                                    <li class="downloadList-item downloadList-item_noPadding">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                ${attachment.filename}
                                        </div>
                                        <div class="downloadList-actions">
                                            <a href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentId}"
                                               class="link link_nowrap">
                                                <span class="iconElement iconElement_cloud02"><icon:download/></span>
                                                <spring:theme code="general.download"/>
                                            </a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>


                        </div>

                    </div>

                    <div class="col-md-8">
                        <ul id="messagesList" class="messageList">
                            <c:forEach items="${legalConsultation_detail.getTextSet}" var="message">
                                <li class="messageList-item">
                                    <div class="messageList-content">
                                        <h2 class="messageList-name">${message.commentsBy}</h2>
                                        <h3 class="messageList-date">${message.commentDate.dateFormatted}</h3>
                                        <div class="messageList-message">
                                            <p>${message.tdline}</p>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>