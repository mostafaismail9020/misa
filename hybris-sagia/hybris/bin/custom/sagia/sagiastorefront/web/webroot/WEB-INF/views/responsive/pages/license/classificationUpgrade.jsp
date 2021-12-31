<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<%-- TODO: SAH-552 --%>
<c:set var = "inProcessStatus" value="In Process"/>
<div class="mainSection mainSection_dark">
    <div class="container">
       <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="classificationupgrade.classificationupgrade"/></h1>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_noPaddingTop mainSection_pdb12">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_right">
                <div>
                    <%--<c:if test="${latestClassificationUpgrade.STATUS ne inProcessStatus}">--%>
                        <%--<button class="btn btn_slim js-classification-upgrade"--%>
                                <%--data-invalid-license="${hasInvalidLicense}" >--%>
                            <%--<spring:theme code="general.upgrade" />--%>
                        <%--</button>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${not (latestClassificationUpgrade.STATUS ne inProcessStatus)}">--%>
                        <%--<button class="btn btn_slim js-classification-upgrade"--%>
                                <%--data-invalid-license="${hasInvalidLicense}" disabled="disabled">--%>
                            <%--<spring:theme code="general.upgrade" />--%>
                        <%--</button>--%>
                    <%--</c:if>--%>
                </div>
        </div>
    </div>
</div>								


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a>
            <c:if test="${fn:length(classificationUpgrade_list) > 1}">
                <button class="btn btn_rightIconLink btn_bold btn_greenLink js-expandContent" id="historyList" data-expand-target="expand02">
                    <div class="hidden"><spring:theme code="text.account.followup.showServiceHistory"/><span>&#x27f6;</span></div>
                    <div><spring:theme code="text.account.followup.hideServiceHistory"/><span>&times;</span></div>
                </button>
            </c:if>
        </div>
    </div>
</div>

<c:set var = "classificationUpgradeCurrentObjectID" scope = "session"/>
<c:set var = "classificationUpgradeCurrentCLASS" scope = "session"/>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <div class="expandableContent expanded" id="expand02">
            <c:if test="${fn:length(classificationUpgrade_list) > 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline">
                                    <span class="iconElement iconElement_history"><icon:history/></span><spring:theme code="text.account.followup.history"/>
                                </div>
                                <div class="searchInputBox searchInputBox_slim">
                                    <input id="classificationUpgradeSearchBox" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="classificationUpgradeList" class="historyList">
                                    <c:forEach items="${classificationUpgrade_list}" var="classificationUpgrade" varStatus="upgradeStatus">
                                        <c:if test = "${empty classificationUpgradeCurrentObjectID}">
                                            <c:set var = "classificationUpgradeCurrentObjectID" value = "${classificationUpgrade.OBJECT_ID}"/>
                                            <c:set var = "classificationUpgradeCurrentCLASS" value = "${classificationUpgrade.CLASS}"/>
                                        </c:if>
                                        <li class="historyList-item <c:if test="${upgradeStatus.index == 0}">historyList-item_current</c:if>"
                                            style="cursor: pointer;" data-expand-target="expand02" onclick="loadData('${classificationUpgrade.OBJECT_ID}', '${classificationUpgrade.CLASS}')">
                                            <a class="historyList-link">
                                                <div class="historyList-id">${classificationUpgrade.OBJECT_ID}</div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date">${classificationUpgrade.SAGIADATE.dateFormatted}</div>
                                                    <div class="historyList-status historyList-status_process">${classificationUpgrade.STATUS}</div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="expandableContent-main">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
							<div
								class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
								<div class="contentModule-headline">
									<span class="iconElement iconElement_info"><icon:info /></span>
									<span id="classification-id">${classificationUpgradeCurrentObjectID}</span>
									<spring:theme code="general.info" />
								</div>
<!--
								<div>
									<button class="btn btn_slim js-classification-upgrade"
										data-invalid-license="${hasInvalidLicense}" >
										<spring:theme code="general.upgrade" />
									</button>
								</div>
-->
							</div>
							<c:choose>
								<c:when test="${empty latestClassificationUpgrade}">
									<div
										class="classificationUpgrade-status classificationUpgrade-status_margin">
										<spring:theme
											code="classificationupgrade.currentclassificationstatus" />
										<span id="classification-classText"
											class="classificationUpgrade-status-value">${currentClassification}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div
										class="classificationUpgrade-status classificationUpgrade-status_margin">
										<spring:theme
											code="classificationupgrade.currentclassificationstatus" />
										<span id="classification-classText"
											class="classificationUpgrade-status-value">${latestClassificationUpgrade.CLASS}</span>
									</div>
									<div
										class="classificationUpgrade-status classificationUpgrade-upgrade_margin">
										<spring:theme code="classificationupgrade.upgradeTo" />
										<span id="classification-upgradeText"
											class="classificationUpgrade-status-value">${latestClassificationUpgrade.APPEAL}</span>
									</div>
								</c:otherwise>
							</c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${not (latestClassificationUpgrade.STATUS ne inProcessStatus)}">
    <div class="modal fade" id="classificationUpgradeWarning" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true" upgrade-status="${latestClassificationUpgrade.STATUS}">
        <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"><spring:theme code="classificationupgrade.upgrade.progress.title"/></div>
                </div>
                <div class="modal-body">
                    <div class="modal-description">
                        <spring:theme code="classificationupgrade.upgrade.progress.content"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn_slim showHistoryBtn" data-dismiss="modal"><spring:theme
                            code="general.close"/></button>
                </div>
            </div>
        </div>
    </div>
</c:if>


