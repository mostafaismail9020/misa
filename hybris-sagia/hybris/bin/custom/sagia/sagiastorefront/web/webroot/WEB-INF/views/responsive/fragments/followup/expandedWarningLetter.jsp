<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="js-hidden-element" style="display: none;">
    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
        <div class="contentModule">
            <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                    <div class="contentModule-headline">
                        <icon:info/>
                        <spring:theme code="text.account.followup.info"/>: ${selectedItem.srId}
                    </div>
                    <a href="warning-letters/create" type="submit" class="btn btn_slim js-create-warning-letter">
                        <spring:theme code="text.account.followup.create"/>
                    </a>
                    <div class="statusIndicator
                                    <c:if test="${selectedItem.srStCode == 'E0001'}">statusIndicator_process</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0002'}">statusIndicator_accepted</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0003'}">statusIndicator_rejected</c:if>
                                ">
                        <spring:theme code="text.account.followup.status"/>: <span>${selectedItem.srStDesc}</span>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt>
                                <spring:theme code="text.account.followup.requestedDaysNumber"/>
                            </dt>
                            <dd>${selectedItem.noOfDaysExtension}</dd>
                            <dt>
                                <spring:theme code="text.account.followup.extendedDate"/>
                            </dt>
                            <dd>${selectedItem.extWlDate.dateFormatted}</dd>
                        </dl>
                    </div>
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt>
                                <spring:theme code="text.account.followup.approvedDaysNumber"/>
                            </dt>
                            <dd>${selectedItem.noOfApprDaysExtn}</dd>
                            <dt>
                                <spring:theme code="text.account.followup.extendedEndDate"/>
                            </dt>
                            <dd>${selectedItem.extWlEndDate.dateFormatted}</dd>
                        </dl>
                    </div>
                </div>
            </div>

            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                <div class="contentModule-headline contentModule-headline_small">
                    <spring:theme code="text.account.followup.comments"/>
                </div>
                <div class="commentModule">
                    <div class="commentModule-window">
                        <ul class="messageList">
                            <c:forEach items="${selectedItem.followText}" var="comment">
                                <li class="messageList-item">
                                    <div class="messageList-img">
                                        <!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
                                        <span class="iconElement iconElement_expertProfile_white">
													<icon:expertProfile/>
												</span>
                                    </div>
                                    <div class="messageList-content">
                                        <h2 class="messageList-name">${comment.commentBy}</h2>
                                        <h3 class="messageList-date">${comment.commentDate.dateFormatted}</h3>
                                        <div class="messageList-message">
                                            <p>${comment.comments}</p>
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

    <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
        <div class="contentModule">
            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noPadding contentModule-section_noMargin">

                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                    <div class="contentModule-headline contentModule-headline_bordered">
                        <icon:documents/>
                        <spring:theme code="text.account.followup.supportDocuments"/>
                    </div>
                </div>

                <ul class="downloadList downloadList_maxHeight">
                    <c:forEach items="${selectedItem.contentHdr}" var="element">
                        <li class="downloadList-item">
                            <div class="downloadList-description">
                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                    ${element.fileName}
                            </div>
                            <div class="downloadList-actions">
                                    <%--<a href="#" class="link link_nowrap" data-attachments-object="a" data-attachments-file="b">--%>
                                <a href="${encodedContextPath}/attachment/pdf/${element.objectId}/${element.documentID}"
                                	  class="link link_nowrap"
                                   download="${element.fullFileName}">
                                    <span class="iconElement iconElement_cloud"><icon:download/></span>
                                    <spring:theme code="text.account.followup.download"/>
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
