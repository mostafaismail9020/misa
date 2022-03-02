<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<style>
   .page-dashboard  .ticketModule-top .ticketModule-details p span {
        font-weight: 600;
        color: #bf9b2e;
    }
    .page-dashboard  .ticketModule-top .ticketModule-details p { 
        color: #707070;
    }
    .page-dashboard  .modal-body .downloadList .downloadList-description {
        font-size: 16px;
        font-weight: 600;
        color: #707070;
    }
    .dashboardUser-image-add{
        cursor: pointer;
    }
</style>
<div class="modal-content modal-ticket-wrapper">
    <div class="modal-header">
        <div class="modal-title view-title modal-ticket-heading w-100" id="exampleModalLabel">
            <!-- <div class="contentModule-headline w-50"> 
                <spring:theme code="general.ticketNumber" /> : ${expandedComplaintFormData.srID}
            </div>
            <hr class="hr"> -->
            <div class="contentModule contentModule-wrap">
                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
                    <span class="contentModule-headline"><spring:theme code="general.ticketNumber" /> : ${expandedComplaintFormData.srID}</span>
                    <div class="contentModule-headline-border"></div>
                </div>
            </div>
        </div>
        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close" style="margin-top: -20px;" >
            <icon:close/>
        </button>
    </div>
    <div class="modal-body">
        <div class="ticketModule">
            <div class="ticketModule-top"> 
                <div class="ticketModule-details">
                    <div class="row">
                        <div class="col-md-6">
                            <p><span class="ticketModule-label"><spring:theme code="general.enquiry.type"/></span><br> ${expandedComplaintFormData.compAndEnqHdrToDetailNav.enquiryType}</p>
                        </div>
                        <div class="col-md-6">
                            <p><span class="ticketModule-label"><spring:theme code="general.topic"/></span><br> ${expandedComplaintFormData.compAndEnqHdrToDetailNav.category1}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <p><span class="ticketModule-label"><spring:theme code="general.subtopic"/></span><br> ${expandedComplaintFormData.compAndEnqHdrToDetailNav.category2}</p>
                        </div>
                        <div class="col-md-6">
                            <p><span class="ticketModule-label"><spring:theme code="general.branch"/></span><br> ${expandedComplaintFormData.compAndEnqHdrToDetailNav.branch}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <p><span class="ticketModule-label"><spring:theme code="general.support.documents"/></span></p>
                         
                            <ul class="downloadList downloadList_secondary">
                                <c:forEach items="${uploadedAttachments}" var="attachment">
                                    <li class="downloadList-item downloadList-item_noPadding">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span> ${attachment.filename}
                                        </div>
                                        <div class="downloadList-actions">
                                            <a href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentID}" download="${attachment.fullFileName}"  class="link link_nowrap ">
                                                <span class="iconElement iconElement_cloud02 "><icon:download/></span>
                                                <spring:theme code="general.download"/>
                                            </a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <p id="errorUpdateLabel " style="color:red; display:none "><b><spring:theme code="general.message.could.not.be.sent "/></b></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <ul id="messagesList" class="messageList">
                                <c:forEach items="${messages}" var="message">
                                    <li class="messageList-item">
                                        <div class="messageList-content">
                                            <h2 class="messageList-name">${message.commentBy}</h2>
                                            <h3 class="messageList-date">${message.commentDate.dateFormatted}</h3>
                                            <div class="messageList-message">
                                                <p>${message.comments}</p>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            
                            <div class="formTextArea">
                                <div class="form-group">
                                    <textarea id="inputNewMessage" class="form-control form-control_slim " placeholder=". " style="height: 183px;"></textarea>
                                    <label class="control-label " for="inputNewMessage ">
                                        <spring:theme code="general.comments"/>
                                    </label>
                                </div>
                            </div>
                            <div class="ticketModule-actions text-center">
                                <button class="btn btn-bg btn_bold " id="newMessageBtn" data-current-srid="${expandedComplaintFormData.srID} " <c:if test="${expandedComplaintFormData.srStCode=='E0010' } "> disabled </c:if>><spring:theme code="general.send.message"/>
                                </button>
                            </div>

                            <input type="hidden " name="csrfToken"  class="w-50 mh-50 pt-3 pb-3 " value="${_csrf.token} " style="visibility: hidden;"/>

                        </div> 
                    </div>
                </div>
               <!--<div class="row">
                    <div class="col-md-6">
                        <div class="ticketModule-details">
                             
                           
                            <p><span class="ticketModule-label"><spring:theme code="general.support.documents"/></span></p>

                            <ul class="downloadList downloadList_secondary">
                                <c:forEach items="${uploadedAttachments}" var="attachment">
                                    <li class="downloadList-item downloadList-item_noPadding">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span> ${attachment.filename}
                                        </div>
                                        <div class="downloadList-actions">
                                            <a href="${encodedContextPath}/attachment/pdf/${attachment.objectId}/${attachment.documentID}" download="${attachment.fullFileName}" "
                                                class="link link_nowrap ">
                                                <span class="iconElement iconElement_cloud02 "><icon:download/></span>
                                                <spring:theme code="general.download "/>
                                            </a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <p id="errorUpdateLabel " style="color:red; display:none "><b><spring:theme code="general.message.could.not.be.sent "/></b></p>
                        </div>
                     </div>

                    <div class="col-md-6 ">
                        <ul id="messagesList " class="messageList ">
                            <c:forEach items="${messages} " var="message ">
                                <li class="messageList-item ">
                                    <div class="messageList-content ">
                                        <h2 class="messageList-name ">${message.commentBy}</h2>
                                        <h3 class="messageList-date ">${message.commentDate.dateFormatted}</h3>
                                        <div class="messageList-message ">
                                            <p>${message.comments}</p>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="formTextArea ">
                            <div class="form-group ">
                                <textarea id="inputNewMessage " class="form-control form-control_slim " placeholder=". "></textarea>
                                <label class="control-label " for="inputNewMessage ">
                                    <spring:theme code="general.comments"/>
                                </label>
                            </div>
                        </div>
                        <div class="ticketModule-actions ">
                            <button class="btn btn-bg btn_bold " id="newMessageBtn " data-current-srid="${expandedComplaintFormData.srID} " <c:if test="${expandedComplaintFormData.srStCode=='E0010' } "> disabled </c:if>><spring:theme code="general.send.message"/>
                            </button>
                        </div>
                    </div>
                    <input type="hidden " name="csrfToken "  class="w-50 mh-50 pt-3 pb-3 " value="${_csrf.token} "/>
                </div>-->
            </div>
        </div>
    </div>
</div>

<script id="messages-template " type="text/template ">
    <li class="messageList-item ">
        <div class="messageList-content ">
            <h2 class="messageList-name ">{{commentBy}}</h2>
            <h3 class="messageList-date ">{{commentDate}}</h3>
            <div class="messageList-message ">
                <p>{{messageComments}}</p>
            </div>
        </div>
    </li>
</script>