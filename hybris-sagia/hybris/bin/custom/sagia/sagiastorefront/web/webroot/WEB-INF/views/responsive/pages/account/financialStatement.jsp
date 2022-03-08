<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="${themeResourcePath}/js/sagia.financial.js"></script>
<c:url var="attachmentURL" value="/attachment/pdf/"></c:url>

<div class="mainSection mainSection bg-white">
    <div class="achievement_header">
        <img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
        <div class="container">
            <div class="banner-container aos-init aos-animate container" data-aos="fade-up">
                <h1 data-aos="fade-up">
                    <spring:theme code="fiancial.statement"/>
                </h1>
            </div>
            <div class="profile-icons float-right">
                <c:if test="${hasLicense or hasAwaitingPayment}">
                    <div class="calendar">
                        <a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
                            <span></span>
                        </a>
                    </div>
                    <div class="calendar notification p-0 sagiaNavigation-entry sagiaNavigation-entry-hasSub">
                        <c:if test="${hasLicense or hasAwaitingPayment}">
                            <button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle btnNotifications m-0 p-0" title="<spring:message code='account.notifications.yourMessages'/>">
                                <span id="unreadNotificationSpan" class="notifyCount notifyCount_small"></span>
                                <img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img"/>
                            </button>
                        </c:if>
                        <div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
                        <div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible d-my-message-popup my-msg-popup notification_b2b_content">
                            <div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen"><spring:message code="header.mostRecent.text"/></div>
                            <ul id="popupNotificationHistoryList" class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin"></ul>
                            <div class="sagiaNavigation-subPane-actions">
                                <a class="btn btn_slim btn_round btn_outline"  href="${encodedContextPath}/my-sagia/notifications"><spring:message code="header.viewAll.text"/></a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="profile">
                    <a href="${encodedContextPath}/my-sagia/sagia-profile" title="<spring:theme code='company.myprofile'/>">
                        <span></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header row service-time">
            <!-- <h1 class="mainSection-headline"><spring:theme code="fiancial.statement"/></h1> -->
            <c:if test="${not empty processingTime}">
                <div class="serviceTime">
                    <div class="serviceTime-label"><spring:theme code="average.service.time" /></div>
                    <div class="serviceTime-detail">
                        <c:choose>
                            <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                <span class="serviceTime-highlight">${processingTime.days}</span>
                                <spring:theme code="average.processingTime.days"/>
                                <span class="serviceTime-highlight">${processingTime.hours}</span>
                                <spring:theme code="average.processingTime.hours"/>
                            </c:when>
                            <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                <spring:theme code="average.processingTime.minutes"/>
                                <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                <spring:theme code="average.processingTime.seconds"/>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>


<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="row w-100">
                <div class="col-md-3 col-12 px-0">
                    <a href="${encodedContextPath}/service-search/SAGIA SERVICES" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
                
                <c:if test="${fn:length(sagiaService.tabs) > 0}">
                    <div class="col-xl-3 col-12">
                        <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16 service-main">
    <div class="container">
        <div class="expandableContent" id="service-tab">
            
        </div>
    </div>
</div>
<div class="container">
    <button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
        <div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
        <div class=""><span class="iconElement iconElement_closeBack " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
    </button>
</div>
<div class="container service-wrapper service-wrapper-info mb-5 expanded" id="expand-03">
	<div class="serviceModule serviceModule_list mx-1 mx-md-5 pt-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="sagia.services.service.overview"/></span>
					<c:choose>
						<c:when test="${empty sagiaService.description}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.description}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-1 mx-md-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="sagia.services.service.document"/></span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceDocuments}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDocuments}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-1 mx-md-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="sagia.services.rules.restrictions"/></span>
					<c:choose>
						<c:when test="${empty sagiaService.rulesRestrictions}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.rulesRestrictions}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-1 mx-md-5">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"><spring:theme code="sagia.services.financial.fees"/></span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceFinancialFees}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceFinancialFees}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="serviceModule serviceModule_list mx-1 mx-md-5 pb-4">
		<div class="serviceModule-section">
			<div class="serviceModule-content">
				<div class="serviceModule-description">
					<span class="serviceModule-headline"> <spring:theme code="sagia.services.duration"/> </span>
					<c:choose>
						<c:when test="${empty sagiaService.serviceDuration}">
							<div class="serviceModule-detail serviceList-description"><div class="w-75"><p>N/A</p></div></div><br>
						</c:when>
						<c:otherwise>
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.serviceDuration}</p></div></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="mainSection mainSection_dark mainSection_xsmallPaddingTop">
    <div class="container">
        <c:if test="${fn:length(financialEntities) gt 1}">
            <button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand02">
                <div class=""><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
                <div class="hidden"><span class="iconElement iconElement_closeBack" id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
            </button>
        </c:if>
        <div class="expandableContent financial-statement-details" id="expand02">
            <c:if test="${fn:length(financialEntities) gt 1}">
                <div class="expandableContent-aside">
                    <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline contentModule-headline-history">
                                    <!-- <span class="iconElement iconElement_history"><icon:history/></span> -->
                                    History</div>
                                <div class="searchInputBox searchInputBox_slim searchInputBox_spaceTop">
                                    <input onkeyup="filterHistory(this)" class="searchInputBox-input" type="text" placeholder="<spring:theme code='storeFinder.search'/>"/>
                                </div>
                                <ul id="history-list" class="historyList">
                                    <c:forEach items="${financialEntities}" var="financial" varStatus="financialStatus">
                                        <li class="historyList-item <c:choose>
                                                <c:when test="${fromServiceRequestOverview}">
                                                    <c:if test="${financial.srId == first.srId}">historyList-item_current</c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${financialStatus.index == 0}">historyList-item_current</c:if>
                                                </c:otherwise>
                                            </c:choose>">
                                            <a onclick="changeFinanceHDR(this,${financial.srId},'${attachmentURL}')" class="historyList-link">
                                                <div class="historyList-id"><c:out value="${financial.year}"/></div>
                                                <div class="historyList-info">
                                                    <div class="historyList-date"><c:out value="${financial.crDateData.dateFormatted}"/></div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <%--
                                <div class="emptyIndicator">
                                    No History List
                                </div>
                                --%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>


            <div class="expandableContent-main">
                <div class="js-panelTabs panelTabs panelTabs_withHistory panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_right financialStatement financial-statement-wrapper">
                    <div class="panelTabs-head">
                        <!-- <icon:financialStatement-income/> -->
                        <span class="panelTabs-label"><spring:theme code="fiancial.incomeStatement.title"/></span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_noRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                    <!-- <div class="contentModule-headline headline-text">
                                        <icon:generalManager/>
                                        <spring:theme code="fiancial.data"/>
                                    </div> -->
                                    <!-- <span class="contentModule-headline headline-text headline-background"><spring:theme code="fiancial.data"/></span> -->
                                    <span class="headline-background"><spring:theme code="fiancial.data"/></span>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated dlList_marginBottom">
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.revenue"/></dt>
                                            <dd><span name="revenue"><c:out value="${first.incomeStat.revenue}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.grossIncome"/></dt>
                                            <dd><span name="grossIncome"><c:out value="${first.incomeStat.grossIncome}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.incomeMainOperations"/></dt>
                                            <dd><span name="mainOperatingIncome"><c:out value="${first.incomeStat.mainOperatingIncome}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.netIncomeBeforeTax"/></dt>
                                            <dd><span name="incomeBeforeZakat"><c:out value="${first.incomeStat.incomeBeforeZakat}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.tax"/></dt>
                                            <dd><span name="taxAmount"><c:out value="${first.incomeStat.taxAmount}"/></span>&nbsp;${currency}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated dlList_lineThrough">
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.costOfSales"/></dt>
                                            <dd><span name="salesCost"><c:out value="${first.incomeStat.salesCost}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.totalOperatingExpenses"/></dt>
                                            <dd><span name="totalOperatingCost"><c:out value="${first.incomeStat.totalOperatingCost}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.totalOtherIncomes"/></dt>
                                            <dd><span name="totalOtherIncome"><c:out value="${first.incomeStat.totalOtherIncome}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.zakat"/></dt>
                                            <dd><span name="zakatAmount"><c:out value="${first.incomeStat.zakatAmount}"/></span>&nbsp;${currency}</dd>
                                            <dt class="headline-golden"><spring:theme code="fiancial.incomeStatement.netIncome"/></dt>
                                            <dd><span name="netIncome"><c:out value="${first.incomeStat.netIncome}"/></span>&nbsp;${currency}</dd>
                                        </dl>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <dl class="dlList dlList_separated">
                                            <dt class="headline-text-dark">
                                                <spring:theme code="text.account.profile.license.contact"/>
                                            </dt>
                                        </dl>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="panelTabs-head">
                        <!-- <icon:financialStatement-balance/> -->
                        <span class="panelTabs-label"><spring:theme code="fiancial.balanceSheet.title"/></span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_noRadius">

                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">

                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                    <!-- <div class="contentModule-headline headline-text">
                                        <icon:generalManager/>
                                        <spring:theme code="fiancial.data"/>
                                    </div> -->
                                    <span class="headline-background"><spring:theme code="fiancial.data"/></span>
                                </div>


                                <div class="row">
                                    <div class="col-md-6">

                                        <div class="contentModule-subheadline headline-text ml-2">
                                            <spring:theme code="fiancial.balanceSheet.assets"/>
                                        </div>
                                        <dl class="dlList dlList_separated dlList_marginBottom">
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.totalCurrentAssets"/></dt>
                                            <dd><span name="currentAssets"><c:out value="${first.balanceSheet.currentAssets}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.totalNonCurrentAssets"/></dt>
                                            <dd><span name="nonCurrentAssets"><c:out value="${first.balanceSheet.nonCurrentAssets}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.otherAssets"/></dt>
                                            <dd><span name="otherAssets"><c:out value="${first.balanceSheet.otherAssets}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><strong><spring:theme code="fiancial.balanceSheet.totalAssets"/></strong></dt>
                                            <dd><strong><span name="totalAssets"><c:out value="${first.balanceSheet.totalAssets}"/></span>&nbsp;${currency}</strong></dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.tax"/></dt>
                                            <dd><span name="taxAmount"><c:out value="${first.incomeStat.taxAmount}"/></span>&nbsp;${currency}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="contentModule-subheadline headline-text">
                                            <spring:theme code="fiancial.balanceSheet.liabilities"/>
                                        </div>
                                        <dl class="dlList dlList_separated dlList_lineThrough">
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.totalCurrentLiabilities"/></dt>
                                            <dd><span name="currentLiabilities"><c:out value="${first.balanceSheet.currentLiabilities}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.totalNonCurrentLiabilities"/></dt>
                                            <dd><span name="nonCurrentLiabilities"><c:out value="${first.balanceSheet.nonCurrentLiabilities}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.otherLiabilities"/></dt>
                                            <dd><span name="otherLiabilities"><c:out value="${first.balanceSheet.otherLiabilities}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><strong><spring:theme code="fiancial.balanceSheet.totalLiabilities"/></strong></dt>
                                            <dd><strong><span name="totalLiabilities"><c:out value="${first.balanceSheet.totalLiabilities}"/></span>&nbsp;${currency}</strong></dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.netIncome"/></dt>
                                            <dd><span name="netIncome"><c:out value="${first.incomeStat.netIncome}"/></span>&nbsp;${currency}</dd>
                                        </dl>
                                    </div>
                                </div>

                                <div class="contentModule-subheadline headline-text ml-2">
                                    <spring:theme code="fiancial.balanceSheet.additionalData"/>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated dlList_marginBottom">
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.capital"/></dt>
                                            <dd><span name="capital"><c:out value="${first.balanceSheet.capital}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.retainedEarnings"/></dt>
                                            <dd><span name="retainedEarnings"><c:out value="${first.balanceSheet.retainedEarnings}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.totalShareholdersEquity"/></dt>
                                            <dd><span name="shareholdersEquity"><c:out value="${first.balanceSheet.shareholdersEquity}"/></span>&nbsp;${currency}</dd>
                                        </dl>
                                    </div>
                                    <div class="col-md-6">
                                        <dl class="dlList dlList_separated dlList_lineThrough">
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.partnersDrawingAccounts"/></dt>
                                            <dd><span name="partnersDrawAccs"><c:out value="${first.balanceSheet.partnersDrawAccs}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden"><spring:theme code="fiancial.balanceSheet.others"/></dt>
                                            <dd><span name="others"><c:out value="${first.balanceSheet.others}"/></span>&nbsp;${currency}</dd>
                                            <dt  class="headline-golden" class="dlList-empty">&nbsp;</dt>
                                            <dd class="dlList-empty">&nbsp;</dd>
                                        </dl>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>



                    <div class="panelTabs-head">
                        <!-- <icon:financialStatement-equity/> -->
                        <span class="panelTabs-label"><spring:theme code="fiancial.equityChange.title"/></span>
                    </div>
                    <div class="panelTabs-body panelModule panelModule_noRadius">


                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">

                                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap headline-background-wrapper">
                                    <!-- <div class="contentModule-headline headline-text">
                                        <icon:documents/>
                                        <spring:theme code="fiancial.data"/>
                                    </div> -->
                                    <span class="headline-background"><spring:theme code="fiancial.data"/></span>
                                </div>

                                <div class="tableModule">
                                    <table class="tableModule-table">
                                        <thead class="tableModule-head">
                                        <tr>
                                            <th><spring:theme code="fiancial.equityChange.items"/></th>
                                            <th><spring:theme code="fiancial.equityChange.retainedEarnings"/></th>
                                            <th><spring:theme code="fiancial.equityChange.total"/></th>
                                        </tr>
                                        </thead>
                                        <tbody class="tableModule-body">
                                        <tr>
                                            <td><spring:theme code="fiancial.equityChange.earningsBefore"/></td>
                                            <td><span name="retainedEarningsBf"><c:out value="${first.changeEquity.retainedEarningsBf}"/></span>&nbsp;${currency}</td>
                                            <td><span name="totalBalanceBf"><c:out value="${first.changeEquity.totalBalanceBf}"/></span>&nbsp;${currency}</td>
                                        </tr>
                                        <tr>
                                            <td><spring:theme code="fiancial.equityChange.earningsAfter"/></td>
                                            <td><span name="retainedEarningsAf"><c:out value="${first.changeEquity.retainedEarningsAf}"/></span>&nbsp;${currency}</td>
                                            <td><span name="totalBalanceAf"><c:out value="${first.changeEquity.totalBalanceAf}"/></span>&nbsp;${currency}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>


                    <c:if test="${fn:length(financialEntities) gt 0}">
                        <div class="panelModule panelModule_halfRadius">
                        <div class="contentModule">
                            <div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
                                <div class="contentModule-headline headline-text">
                                    <!-- <icon:documents/> -->
                                    <spring:theme code="fiancial.incomeStatement.supportingDocuments"/>
                                </div>
                                <ul class="downloadList downloadList_maxHeight" id="documents-container">
                                    <c:forEach items="${first.financeContents}" var="financeContent">
                                        <c:url value="/attachment/pdf/${first.srId}/${financeContent.documentId}" var="firstAttachment"></c:url>
                                        <li class="downloadList-item">
                                            <div class="downloadList-description">
                                                <span class="iconElement iconElement_pdf"><icon:pdf/></span>
                                                <c:out value="${financeContent.filename}"></c:out>
                                            </div>
                                            <div class="downloadList-actions">
                                                <a class="link link_nowrap" href='${firstAttachment}' download>
                                                    <span class="iconElement iconElement_cloud"><icon:download/></span>
                                                    Download
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </div>
                </div>
                    </c:if>

        </div>
    </div>
</div>

</div>
