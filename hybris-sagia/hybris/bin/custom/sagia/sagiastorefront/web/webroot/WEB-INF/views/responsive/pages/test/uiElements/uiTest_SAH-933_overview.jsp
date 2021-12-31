<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline">Opening Closed Entity</h1>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span>Back to Dashboard</a>
            <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
                <div class="hidden"><spring:theme code="text.account.followup.showServiceHistory"/><span>&#x27f6;</span></div>
                <div><spring:theme code="text.account.followup.hideServiceHistory"/><span class="iconElement iconElement_closeBack"><icon:close/></span></div>
            </button>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <div class="expandableContent expanded" id="expand01">
            <div class="expandableContent-aside">
                <div class="panelModule panelModule_halfRadius">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                            <div class="contentModule-headline">
                                <span class="iconElement iconElement_history"><icon:history/></span>
                                <spring:theme code="text.account.followup.history"/>
                            </div>
                            <div class="searchInputBox searchInputBox_slim">
                                <input class="searchInputBox-input" type="text" placeholder="Search"/>
                            </div>
                               <ul class="historyList">
                                <%-- <c:if test="${fn:}"" --%>
                                    <li class="historyList-item historyList-item_current">
                                            <a href="#" class="historyList-link">
                                                <div class="historyList-id">500009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">06 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_process">In Process</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">200009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">02 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_accepted">Accepted</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">100009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">01 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_rejected">Rejected</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="historyList-item">
                                            <a href="" class="historyList-link">
                                                <div class="historyList-id">100009991</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">01 Nov 2017</div>
                                                    <div class="historyList-status historyList-status_rejected">Rejected</div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                            <%--
                            <c:if test="${fn:}">
                                <div class="emptyIndicator">
                                    <spring:theme code="text.account.followup.noHistory"/>
                                </div>
                            </c:if>
                            --%>
                        </div>
                    </div>
                </div>
            </div>


            <div class="expandableContent-main js-warning-letters">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section contentModule-section_noDivider contentModule-section_slimDivider">
                            <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
                                <div class="contentModule-headline">
                                    <icon:info/>
                                    Title
                                    <%--
                                    <spring:theme code="text.account.followup.info"/>: ${selectedItem.srId}
                                    --%>
                                </div>
                                <a href="warning-letters/create" type="submit" class="btn btn_slim">
                                    <spring:theme code="text.account.followup.create"/>
                                </a>
                                <%--
                                <div class="statusIndicator
                                    <c:if test="${selectedItem.srStCode == 'E0001'}">statusIndicator_process</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0002'}">statusIndicator_accepted</c:if>
                                    <c:if test="${selectedItem.srStCode == 'E0003'}">statusIndicator_rejected</c:if>
                                ">
                                    <spring:theme code="text.account.followup.status"/>: <span>${selectedItem.srStDesc}</span>
                                </div>
                                --%>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <%--
                                        <dt>
                                        </dt>
                                        <dd></dd>
                                        <dt>
                                        </dt>
                                        <dd></dd>
                                        --%>
                                    </dl>
                                </div>
                                <div class="col-md-6">
                                    <dl class="dlList dlList_separated">
                                        <%--
                                        <dt>
                                        </dt>
                                        <dd></dd>
                                        <dt>
                                        </dt>
                                        <dd></dd>
                                        --%>
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
                                                    <%--
                                                    <h2 class="messageList-name">Name</h2>
                                                    <h3 class="messageList-date">01.01.2018</h3>
                                                    <div class="messageList-message">
                                                        <p>Commenttext</p>
                                                    </div>
                                                    --%>
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
                                <%--
                                <c:forEach items="${}" var="element">
                                --%>
                                    <li class="downloadList-item">
                                        <div class="downloadList-description">
                                            <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                            Filename.ext
                                                <%-- ${element.filename} --%>
                                        </div>
                                        <div class="downloadList-actions">
                                                <%--<a href="#" class="link link_nowrap" data-attachments-object="a" data-attachments-file="b">--%>
                                            <%--
                                            <a href="#" class="link link_nowrap js-download-attachments"
                                               data-attachments-object="${element.objectId}"
                                               data-attachments-file="${element.documentID}">
                                                <span class="iconElement iconElement_cloud"><icon:download/></span>
                                                <spring:theme code=""/>
                                            </a>
                                            --%>
                                        </div>
                                    </li>
                                <%--
                                </c:forEach>
                                --%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>