<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license"%>
<%@ taglib prefix="modals" tagdir="/WEB-INF/tags/responsive/common"%>
<%--<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>--%>
<%--<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>--%>

<script>
	var configuredFileSize = ${maxUploadSize};
</script>

<div class="mainSection bg-white">
	<div class="achievement_header">
		<img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
		<div class="container">
			<div class="banner-container aos-init aos-animate container" data-aos="fade-up">
				<h1 data-aos="fade-up"><spring:theme code="license.amendlicense" /></h1>
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


<div class="container">
	<div class="mainSection mainSection_white mainSection_narrow mainSection_noPadding">
		<div class="container">
			<!-- <div class="mainSection-header">
				<h1 class="mainSection-headline">
					<spring:theme code="license.amendlicense" />
				</h1>
			</div> -->
			<div class="row service-time">
				<c:if test="${not empty processingTime}">
					<div class="serviceTime">
						<div class="serviceTime-label">
							<spring:theme code="average.service.time" />
						</div>
						<div class="serviceTime-detail">
							<c:choose>
								<c:when
									test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
									<span class="serviceTime-highlight">${processingTime.days}</span>
									<spring:theme code="average.processingTime.days" />
									<span class="serviceTime-highlight">${processingTime.hours}</span>
									<spring:theme code="average.processingTime.hours" />
								</c:when>
								<c:when
									test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
									<span class="serviceTime-highlight">${processingTime.minutes}</span>
									<spring:theme code="average.processingTime.minutes" />
									<span class="serviceTime-highlight">${processingTime.seconds}</span>
									<spring:theme code="average.processingTime.seconds" />
								</c:when>
							</c:choose>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<div class="container mainSection mainSection_white mainSection_narrow mainSection_noPadding">
		<div class="m-0 ml-custom-35">
			<div class="row w-100 renewal-services">
				<div class="col-md-6 col-12">
                    <a href="${encodedContextPath}/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
                </div>
				<c:if test="${fn:length(sagiaService.tabs) > 0}">
					<div class="col-xl-3 col-12 ml-1">
						<button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')"><spring:theme code="service.tabs.show"/></button>
					</div>
				</c:if>
			</div>
			<div class="row w-100 d-none mt-4	">
				<div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
					<div class="col-xl-12 col-12 amend-btns-list">
						<button id="saveDraftBtnId" class="btn btn_round btn_slim mr-0 mr-md-2">
							<spring:theme code="general.savedraft" />
							<span class="iconElement iconElement_save"><icon:save /></span>
						</button>
					<!-- </div>
					<div class="col-xl-6 col-12 amend-btns-list"> -->
						<button id="loadDraftBtnId"
							class="btn btn_round btn_slim js-load-draft mr-0 mr-md-2"
							<c:if test="${!draftExists}">style="display: none"</c:if>>
							<spring:theme code="general.loaddraft" />
							<span class="iconElement iconElement_save"><icon:upload /></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container mainSection mainSection_dark mainSection_pdt16 service-main">
		<div class="">
			<div class="expandableContent" id="service-tab">
				
			</div>
		</div>
	</div>
	
<c:if test="${not empty sagiaService.description || not empty sagiaService.serviceDocuments || not empty sagiaService.rulesRestrictions || not empty sagiaService.serviceFinancialFees || not empty sagiaService.serviceDuration}">  
	<div class="container ml-0 ml-md-4">
		<button class="btn_history btn_rightIconLink btn_bold btn_greenLink btn_show_hide_service" data-expand-target="expand-03">
			<div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> <spring:theme code="service.overview.show"/></div>
			<div class=""><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="service.overview.hide"/></div>
		</button>
	</div>
	<div class="service-wrapper service-wrapper-info mb-5 mx-0 mx-md-5 expanded" id="expand-03">
		<div class="serviceModule serviceModule_list mx-5 pt-4">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">					
						<c:if test="${not empty sagiaService.description}">	
							<span class="serviceModule-headline"> <spring:theme code="sagia.services.service.overview"/> </span>													
							<div class="serviceModule-detail serviceList-description"><div class="w-100"><p>${sagiaService.description}</p></div></div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%--  
		<div class="serviceModule serviceModule_list mx-5">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">
						<span class="serviceModule-headline"> <spring:theme code="sagia.services.service.document"/> </span>
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
		<div class="serviceModule serviceModule_list mx-5">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">
						<span class="serviceModule-headline"> <spring:theme code="sagia.services.rules.restrictions"/> </span>
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
		<div class="serviceModule serviceModule_list mx-5">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">
						<span class="serviceModule-headline"> <spring:theme code="sagia.services.financial.fees"/> </span>
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
		<div class="serviceModule serviceModule_list mx-5 pb-4">
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
		--%>
	</div>
</c:if>

	<!-- <div class="mainSection mainSection_white mainSection_narrow">
		<div class="container d-flex service-history">			
		</div>
	</div> -->
	<!-- <div class="mainSection mainSection_white mainSection_narrow mainSection_xsmallPaddingTop service-request service-wrapper service-wrapper-info mw-100 w-100 mt-5 mb-3"> -->
	<div class="container mainSection mainSection_dark mainSection_pdt16 mb-3 ml-0 ml-md-4 service-main">
		<div class="">
			<div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
				<button id="expandAmendmentHistoryBtnId" class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent service-history-link" data-expand-target="expand01">
					<div class="" ><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
					<div class="hidden"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
				</button>
			</div>

			<div class="expandableContent expandableContent_upLg" id="expand01">
				<div class="expandableContent-aside">
					<div class="panelModule panelModule_halfRadius">
						<div class="contentModule">
							<div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
								<div class="contentModule-headline contentModule-headline-history">
									<!-- <span class="iconElement iconElement_history"><icon:history /></span> -->
									<spring:theme code="text.account.followup.history" />
								</div>
								<div class="searchInputBox searchInputBox_slim">
									<input onkeyup="filterHistory(this)" id="convertSearchBox"
										class="searchInputBox-input search-history-services" type="text"
										placeholder="<spring:theme code='storeFinder.search'/>" />
								</div>
								<ul id="history-list" class="historyList"></ul>
							</div>
						</div>
					</div>
				</div>
				<div class="expandableContent-main amend-ddl-list service-wrapper-info">
					<div class="js-panelTabs panelTabs panelTabs_iconsAndLabel panelTabs_separated panelTabs_tip_none  panelTabs_noPanelInBody panelTabs_white panelTabs_whiteNavigation panelTabs_noPaddingTop">
						<div class="panelTabs-head" id="tab1">
							<!-- <icon:registerinTab /> -->
							<span class="panelTabs-label"><spring:theme
									code="license.entity" /></span>
						</div>
						<div class="panelTabs-body">
							<div id="entityAmendTabId" class="contentModule">
								<form:form id="entityFormId">
									<div class="contentModule-section">
										<div class="row">
											<div class="col-sm-6">
												<div class="formInputBox">
													<div class="formInputBox_group">
														<div class="form-group">
															<input id="capitalId" name="capital" class="form-control"
																placeholder="." value="" type="text" maxlength="20">
															<label class="control-label" for=""> <spring:theme
																	code="general.capital" />
															</label>
															<div class="formInputBox-append">
																<span class="formInputBox-text"><spring:theme
																		code="general.sar" /></span>
															</div>
														</div>
													</div>
													<div class="help-block"></div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="formInputBox">
													<div class="form-group">
														<input id="labourId" name="labour" class="form-control"
															placeholder="." value="" type="text" maxlength="20">
														<label class="control-label" for=""> <spring:theme
																code="license.labour" />
														</label>
														<div class="help-block"></div>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="formInputBox">
													<div class="form-group">
														<input id="entityNameId" name="entityName"
															class="form-control" placeholder="." value="" type="text"
															<%--style="text-align: right;"--%>
															dir="rtl"
															maxlength="80"> <label class="control-label"
															for=""> <spring:theme code="license.entityname" />
														</label>
														<div class="help-block"></div>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="formSelectBox">
													<div class="form-group">
														<select id="legalStatusId" name="legalStatusId"
															class="js-select2-oneColumn form-control">
															<option></option>
															<option value="ESTB" selected><spring:theme
																	code="license.estb" /></option>
															<option value="LLC"><spring:theme
																	code="license.llc" /></option>
															<option value="ILLC"><spring:theme
																	code="license.individualllc" /></option>
														</select> <label class="control-label" for="legalStatusId"><spring:theme
																code="license.legalstatus" /></label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form:form>
								<div
									class="contentModule-actions contentModule-actions_spaceBetween contentModule-mob-btns">
									<button type="button"
										class="btn btn-outline cancelAmendmentBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.cancel" />
									</button>
									<button id="nextTabEntityBtnId" type="button"
										class="btn btn-primary newAmendmentBtn full-width-responsive">
										<spring:theme code="general.proceed" />
									</button>
								</div>
							</div>

							<div id="entityHistoryTabId" class="contentModule">
								<div class="contentModule-section service-content-history">
									<div class="row">
										<div class="col col-sm-4">
											<spring:theme code="general.capital" />
										</div>
										<div id="capitalNewId" class="col col-sm-4"></div>
										<div id="capitalOldId" class="col col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col col-sm-4">
											<spring:theme code="license.labour" />
										</div>
										<div id="labourNewId" class="col col-sm-4"></div>
										<div id="labourOldId" class="col col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col col-sm-4">
											<spring:theme code="license.entityname" />
										</div>
										<div id="entitynameNewId" class="col col-sm-4"></div>
										<div id="entitynameOldId" class="col col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col col-sm-4">
											<spring:theme code="license.legalstatus" />
										</div>
										<div id="legalstatusNewId" class="col col-sm-4"></div>
										<div id="legalstatusOldId" class="col col-sm-4"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="panelTabs-head" id="">
							<!-- <icon:isic-activity /> -->
							<span class="panelTabs-label"><spring:theme
									code="license.isicactivity" /></span>
						</div>
						<div class="panelTabs-body" id="service-business-activities">
							<p id="oldActivitiesId"></p>
							<license:licenseBusinessActivitiesSection />
							<div
								class="contentModule-actions contentModule-actions_spaceBetween contentModule-mob-btns">
								<button type="button"
									class="btn btn-outline cancelAmendmentBtn newAmendmentBtn full-width-responsive">
									<spring:theme code="general.cancel" />
								</button>
								<button id="nextTabIsicBtnId" type="button"
									class="btn btn-primary newAmendmentBtn full-width-responsive">
									<spring:theme code="general.proceed" />
								</button>
							</div>
						</div>

						<div class="panelTabs-head" id="tab2">
							<!-- <icon:shareholder /> -->
							<span class="panelTabs-label"><spring:theme
									code="license.apply.shareholders" /></span>
						</div>
						<div class="panelTabs-body">
							<div class="contentModule">
								<div class="contentModule-section">
									<div class="tableModule tableModule_noOverflow">
										<table class="tableModule-table">
											<thead class="tableModule-head">
												<tr>
													<th><spring:theme code="general.name" /></th>
													<th><spring:theme code="license.type" /></th>
													<th><spring:theme code="license.percentage" /></th>
													<th><spring:theme code="text.account.profile.license.shareholders.nationality" /></th>
													<th><spring:theme code="text.account.profile.license.shareholders.legalStatus" /></th>
													<th id="shareholderBtnColumnId"><spring:theme code="general.edit" /></th>
													<th id="shareholderBtnColumnId"><spring:theme code="general.delete" /></th>

													<!-- <th id="shareholderBtnColumnId"></th> -->
												</tr>
											</thead>
											<tbody id="shareholdersId" class="tableModule-body service-share-holder-table">
												<tr class="shareholderTemplate">
													<td></td>
													<td class="type"></td>
													<td class="percentage"></td>
													<td></td>
													<td></td>
													<td class="tableModule-bodyItem-action">
														<button type="button service_action_btn"
															class="btn btn_link editShareholderBtn"
															data-toggle="modal"
															<%-- data-target="#shareholderEditModalId" data-backdrop="static"--%>
															data-target="#shareholderModalId"
															data-backdrop="static" data-keyboard="false">
															<icon:edit />
														</button>
														
														
													</td>
													<td>
														<div class="deleteDropdown js-deleteDropdown">
															<button type="button service_action_btn"
																class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
																<icon:remove />
															</button>
															<div class="deleteDropdown-drop modal">
																<div class="modal-dialog modal-md modal-dialog-centered" role="document">
																	<div class="modal-content">
																	  <div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLiveLabel"><spring:theme code="text.account.profile.license.shareholders.deleteShareholder" /></h5>
																	  </div>
																	  <div class="deleteDropdown-actions d-flex justify-content-center align-items-center my-4">
																		<button type="button" class="btn btn_primary btn_outline js-deleteDropdown-cancel">
																			<spring:theme code="general.cancel" />
																		</button>
																		<button type="button" class="btn btn_slim removeShareholderBtn">
																			<spring:theme code="general.delete" />
																		</button>
																	</div>
																	</div>
																  </div>
															</div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div
										class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
										<button type="button" class="btn btn-primary newShareholderBtn"
											data-toggle="modal" data-target="#shareholderModalId"
											data-backdrop="static" data-keyboard="false">
											<spring:theme
												code="text.account.profile.license.shareholders.newShareholder" />
										</button>
									</div>
								</div>

								<div
									class="contentModule-actions contentModule-actions_spaceBetween contentModule-mob-btns">
									<button type="button"
										class="btn btn-outline cancelAmendmentBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.cancel" />
									</button>
									<button id="nextTabShareholdersBtnId" type="button"
										class="btn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.proceed" />
									</button>
								</div>
							</div>
						</div>
						<div class="panelTabs-head" id="tab3">
							<!-- <icon:branch /> -->
							<span class="panelTabs-label"><spring:theme
									code="general.branch" /></span>
						</div>
						<div class="panelTabs-body">
							<div class="contentModule">
								<div class="contentModule-section">
									<div class="tableModule">
										<table class="service-share-holder-table">
											<tr class="branchTemplate">
												<td><strong></strong></td>
												<td></td>
												<td></td>
												<td></td>
												<td class="tableModule-bodyItem-action">
													<button type="button" class="btn btn_link editBranchBtn service_action_btn" id="btn_editBranchBtn"
														data-toggle="modal" data-target="#branchModalId"
														data-backdrop="static" data-keyboard="false">
														<icon:edit />
													</button>
													<button type="button" class="btn btn_link viewBranchBtn service_action_btn"
														data-toggle="modal" data-target="#branchModalId"
														data-backdrop="static" data-keyboard="false">
														<icon:view />
													</button>
													</td>
													<td>
													<div class="deleteDropdown js-deleteDropdown">
														<button type="button" class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn service_action_btn">
															<icon:remove />
														</button>
														<div class="deleteDropdown-drop modal">
															<div class="modal-dialog modal-md modal-dialog-centered" role="document">
																<div class="modal-content">
																  <div class="modal-header">
																	<h5 class="modal-title" id="exampleModalLiveLabel"><spring:theme code="text.account.profile.license.branches.deletethebranch" /> </h5>
																  </div>
																  <div class="deleteDropdown-actions d-flex justify-content-center align-items-center my-4">
																	<button type="button"
																		class="btn btn_outline btn_slim js-deleteDropdown-cancel">
																		<spring:theme code="general.cancel" />
																	</button>
																	<button type="button"
																		class="btn btn_slim removeBranchBtn">
																		<spring:theme code="general.delete" />
																	</button>
																</div>
																</div>
															  </div>
														</div>
													</div>
												</td>
											</tr>
										</table>
										<table id="branchesTableId" class="tableModule-table">
											<thead class="tableModule-head">
												<tr>
													<th><spring:theme code="text.account.profile.license.branches.type" /> <span class="sort-icon"></span></th>
													<th><spring:theme code="text.account.profile.license.branches.name" /> <span class="sort-icon"></span></th>
													<th><spring:theme code="general.city" /> <span class="sort-icon"></span></th>
													<th><spring:theme code="text.account.profile.license.branches.number" /> <span class="sort-icon"></span></th>
													<th id="branchesBtnColumnId"><spring:theme code="general.edit" /> </th>
													<th id="branchesBtnColumnId"><spring:theme code="general.delete" /> </th>
													<!-- <th id="branchesBtnColumnId"></th> -->
												</tr>
											</thead>
											<tbody id="branchesId" class="tableModule-body"></tbody>
										</table>
									</div>
									<div
										class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
										<button type="button" class="btn btn-primary newBranchBtn"
											data-toggle="modal" data-target="#branchModalId"
											data-backdrop="static" data-keyboard="false">
											<spring:theme
												code="text.account.profile.license.branches.newbranch" />
										</button>
									</div>
								</div>
								<div
									class="contentModule-actions contentModule-actions_spaceBetween contentModule-mob-btns">
									<button type="button"
										class="btn btn-outline cancelAmendmentBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.cancel" />
									</button>
									<button id="nextTabBranchesBtnId" type="button"
										class="btn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.proceed" />
									</button>
								</div>
							</div>
						</div>

						<div class="panelTabs-head" id="tab4">
							<!-- <icon:productinTab /> -->
							<span class="panelTabs-label"><spring:theme
									code="products.products" /></span>
						</div>
						<div class="panelTabs-body">
							<div class="contentModule">
								<div class="contentModule-section">
									<div class="tableModule">
										<table class="tableModule-table">
											<thead class="tableModule-head">
												<tr>
													<th><spring:theme code="products.productid" /></th>
													<th><spring:theme code="products.productdescription" /></th>
													<th><spring:theme code="products.qty" /></th>
													<th><spring:theme code="products.unit" /></th>
													<th id="productsBtnColumnId"><spring:theme code="general.edit" /></th>
													<th id="productsBtnColumnId"><spring:theme code="general.delete" /></th>
													<!-- <th id="productsBtnColumnId"></th> -->
												</tr>
											</thead>
											<tbody id="productsId" class="tableModule-body service-share-holder-table">
												<tr class="productTemplate">
													<td><strong></strong></td>
													<td></td>
													<td></td>
													<td></td>
													<td class="tableModule-bodyItem-action">
														<button type="button" class="btn btn_link editProductBtn service_action_btn"
															data-toggle="modal" data-target="#productModalId"
															data-backdrop="static" data-keyboard="false">
															<icon:edit />
														</button>
														</td>
														<td>
														<div class="deleteDropdown js-deleteDropdown">
															<button type="button"class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn service_action_btn">
																<icon:remove />
															</button>
															<div class="deleteDropdown-drop modal">
																<div class="modal-dialog modal-md modal-dialog-centered" role="document">
																	<div class="modal-content">
																	  <div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLiveLabel"><spring:theme code="products.deleteproduct" /> </h5>
																	  </div>
																	  <div class="deleteDropdown-actions d-flex justify-content-center align-items-center my-4">
																			<button type="button"
																				class="btn btn_outline btn_slim js-deleteDropdown-cancel">
																				<spring:theme code="general.cancel" />
																			</button>
																			<button type="button"
																				class="btn btn_slim removeProductBtn">
																				<spring:theme code="general.delete" />
																			</button>
																	</div>
																	</div>
																  </div>
															</div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div
										class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
										<button type="button" class="btn btn-primary newProductBtn">
											<spring:theme code="license.newproduct" />
										</button>
										<input id="licenseType" type="hidden" />
									</div>
								</div>
								<div
									class="contentModule-actions contentModule-actions_spaceBetween contentModule-mob-btns">
									<button type="button"
										class="btn btn-outline cancelAmendmentBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.cancel" />
									</button>
									<button type="button"
										class="btn validateLicenseBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.submit" />
									</button>
								</div>
							</div>

						</div>
					</div>

					<div class="contentModule-commentsSection" id="ammendComments" style="display: none;">
						<div class="contentModule-headline contentModule-headline_small ">
							<spring:theme code="text.account.followup.comments" />
						</div>
						<div class="commentModule">
							<div class="commentModule-window">
								<ul id="messagesListUL" class="messageList">
									<li class="messageList-item">
										<div class="messageList-img">
											<span class="iconElement iconElement_expertProfile_white">
												<icon:expertProfile />
											</span>
										</div>
										<div class="messageList-content">
											<div class="messageList-message">
												<p id="commentMessage"></p>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="shareholderEditModalId" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content panelModule panelModule_halfRadius">
				<div class="contentModule-headline">
					<spring:theme code="license.shareholder" />
				</div>
				<form class="editShareholder">
					<div class="row">
						<div class="col-xs-12">
							<div class="formInputBox">
								<div class="form-group">
									<input type="text" id="shareholderPercentage"
										name="shareholderPercentage" class="form-control"> <label
										for="shareholderPercentage"
										class="control-label control-label_mandatory"><spring:theme
											code="license.apply.review.shares.percentage" /></label>
								</div>
							</div>
						</div>
					</div>

					<div
						class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
						<button type="button"
							class="btn btn-sector-outline mx-auto cancelShareholderBtn"
							data-dismiss="modal">
							<spring:theme code="general.cancel" />
						</button>
						<button type="button" class="btn btn-slim saveEditShareholderBtn">
							<spring:theme code="general.save" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="shareholderModalId" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<form id="shareholderFormId">
			<div class="modal-dialog modal-dialog-centered modal-header text-center" role="document">
				<div class="modal-content panelModule panelModule_halfRadius">
					<div class="modal-title">
						<spring:theme code="license.shareholder" />
					</div>
					<div class="border-top-line">
						<div id="shareholderNewExistingTypeId" class="row amend-shareholder-wrapper">
							<div class="col-md-12">
								<div class="formRadioBox">
									<div class="form-group">
										<div class="form-item">
											<input id="newShareholderId"
												name="shareholderNewExistingRadioBox" class="form-control"
												value="true" type="radio" checked> <label
												for="newShareholderId" class="control-label"> <spring:theme
													code="license.newshareholder" />
											</label>
										</div>
										<div class="form-item">
											<input id="existingShareholderId"
												name="shareholderNewExistingRadioBox" class="form-control"
												value="false" type="radio"> <label
												for="existingShareholderId" class="control-label"> <spring:theme
													code="license.existingshareholder" />
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

				

						<div id="shareholderPersonEntityTypeId" class="row amend-shareholder-wrapper">
							<div class="col-md-12">
								<div class="formRadioBox">
									<div class="form-group">
										<div class="form-item">
											<input id="personId" name="shareholderPersonEntityRadioBox"
												class="form-control" value="true" type="radio" checked>
											<label for="personId" class="control-label"> <spring:theme
													code="general.person" />
											</label>
										</div>
										<div class="form-item">
											<input id="entityId" name="shareholderPersonEntityRadioBox"
												class="form-control" value="false" type="radio"> <label
												for="entityId" class="control-label"> <spring:theme
													code="license.entity" />
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="bpNumberGroupId" class="row">
							<div class="col-md-8">
								<div class="formInputBox">
									<div class="form-group">
										<input id="bpNumberId" class="form-control" placeholder="."
											value="" type="text" name="bpNumber"> <label
											class="control-label control-label_mandatory" for=""> <spring:theme
												code="license.bpnumber" />
										</label>
										<div class="help-block"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="contentModule-separator"></div>

					</div>

					<div id="shareholderValidationSection">
						<div class="row" id="shareholderValidationDetails">
							<div id="shareholderIdTypeSection" class="col-md-12 col-lg-6">
								<div class="formSelectBox">
									<div class="form-group">
										<select id="shareholderIdType"
											name="shareholderValidation.shareholderIdType"
											class="js-select2-oneColumn form-control">
										</select> <label class="control-label control-label_mandatory"
											for="shareholderIdType"><spring:theme
												code="license.apply.shareholder.idType" /></label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div id="shareholderIdNumberSection" class="col-md-12 col-lg-6">
								<div class="formInputBox">
									<div class="form-group">
										<input id="shareholderIdNumber"
											name="shareholderValidation.shareholderIdNumber"
											class="form-control" placeholder="." value="" type="text" />
										<label class="control-label control-label_mandatory"
											for="shareholderIdNumber"><spring:theme
												code="license.apply.shareholder.idNumber" /></label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div id="shareholderDateofBirthSection" class="col-md-12 col-lg-6">
								<div class="formInputBox formInputBox_group ">
									<div class="form-group">
										<input id="shareholderDateofBirth"
											name="shareholderValidation.shareholderDateofBirth"
											class="form-control " placeholder="." value="" type="text" />
										<label class="control-label control-label_mandatory" for="shareholderDateofBirth"><spring:theme code="license.apply.shareholder.dateOfBirth" /></label>
										<div class="formInputBox-append" id="calendar-icon-pos"><span class="formInputBox-text"><icon:calendar-gray /></span></div>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="col-md-6" id="nicShareholderVerifyBtnSection">
								<a style="margin-top: 22px" class="btn btn_slim btn-bg btn_bold btn-normal w-auto"
									id="verifyShareholderDetailsShow" data-nic-verified="false"><spring:theme code="license.apply.shareholder.verify" /></a> 
									<input type="checkbox" id="isShareholderNicVerified"
									name="delegateInfo.shareholderNicVerified" value="true"
									class="hidden"
									${data.delegateInfo.shareholderNicVerified ? "checked=checked" : 0}>
								<div class="inputShareholderValidationError"
									style="display: none; color: #ff4c4a; line-height: 1.2; margin-top: 10px;">
									<spring:theme
										code="validation.licenseApply.shareholder.invalidInputValidation"
										text="Please click on 'Input Validation' to save Shareholder" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="contentModule-separator"></div>
							</div>
						</div>

						<div id="contentNewShareholderForm">
							<!-- <div class="contentModule-headline"><spring:theme code="license.basicinformation" /></div> -->
							

							<div class="border-top-line">

							<%--Entity shareholder--%>
							<div id="entityShareholderId">
								<div class="row" id="companyVerificationSection">
									<div class="contentModule contentModule-wrap col-md-12">
										<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
											<span class="contentModule-headline"><spring:theme code="license.basicinformation" /></span>
											<div class="contentModule-headline-border"></div>
										</div>
									</div>
									<div class="col-md-6 mt-3">
										<div class="formInputBox">
											<div class="form-group">
												<select id="companyCountry" name="companyCountry"
													class="js-select2-oneColumn form-control"></select> <label
													class="control-label control-label_mandatory"
													for="companyCountry"><spring:theme
														code="general.country" /></label>
											</div>
										</div>
									</div>

									<div class="col-md-6"></div>

									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="inputCRNumber" name="professionalLicenseCr"
													disabled="disabled"
													class="form-control validate__numbers-only" placeholder="."
													type="text"
													value="${not empty sagiaApplyOrganizationShareholderForm.professionalLicenseCr ? sagiaApplyOrganizationShareholderForm.professionalLicenseCr : ''}">
												<label class="control-label" for="professionalLicenseCr"><spring:theme
														code="licenseApplyEntityInformation.licenseInformationSection.crnumber" /></label>
												<div class="help-block" id="inputCRNumber-error"></div>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<button id="load-investor" type="button"
													class="btn load-investor" disabled="disabled">
													<spring:theme
														code="licenseApplyEntityInformation.licenseInformationSection.validatecr" />
												</button>
												<input type="hidden" id="professionalLicenseCrVerified"
													name="professionalLicenseCrVerified"
													value="${sagiaApplyOrganizationShareholderForm.professionalLicenseCrVerified}" />
												<%-- <input type="checkbox" id="professionalLicenseCrVerified" name="professionalLicenseCrVerified" value="true" class="hidden" ${sagiaApplyEntityInfoForm.professionalLicenseCrVerified ? "checked=checked" : 0}> --%>
											</div>
										</div>
									</div>
								</div>

								<div id="entityBasicInformation" class="row">
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderNameId" name="shareholderName"
													class="form-control" placeholder="." value="" type="text"
													dir="rtl" lang="ar" pattern="[\
													\u0620-\u063F\u0641-\u064A\u066E-\u066F\u0671-\u06D3\u06D5\
													\u06E5-\u06E6\u06EE-\u06EF\u06FA-\u06FC\u06FF\u0750-\u077F\
													\u08A0\u08A2-\u08AC\uFB50-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\
													\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\s]+">
												<label class="control-label control-label_mandatory"
													for="shareholderNameId"> <spring:theme
														code="license.nameinarabic" />
												</label>
												<div class="help-block"></div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderNameEnglishId"
													name="shareholderNameEnglish" class="form-control"
													placeholder="." value="" type="text"
													dir="ltr" lang="en" pattern="[a-zA-Z\s]+"> <label
													class="control-label control-label_mandatory"
													for="shareholderNameEnglishId"> <spring:theme
														code="license.nameinenglish" />
												</label>
												<div class="help-block"></div>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="formSelectBox">
											<div class="form-group">
												<select id="shareholderSectorId" name="shareholderSector"
													class="js-select2-oneColumn form-control"></select> <label
													class="control-label control-label_mandatory"
													for="shareholderSectorId"><spring:theme
														code="license.sector" /></label>
												<div class="help-block"></div>
											</div>
											
										</div>
									</div>
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderSubsectorId"
													name="shareholderSubsector" class="form-control"
													placeholder="." value="" type="text" maxlength="20"> <label
													class="control-label control-label_mandatory" for="">
													<spring:theme code="license.subsector" />
												</label>
												<div class="help-block"></div>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="formSelectBox">
											<div class="form-group">
												<select id="shareholderMultinationalCompanyId"
													name="shareholderMultinationalCompany"
													class="js-select2-oneColumn form-control"></select> <label
													class="control-label control-label_mandatory"
													for="shareholderMultinationalCompanyId"><spring:theme
														code="license.multinational" /></label>
												<div class="help-block"></div>
											</div>
											
										</div>
									</div>
									<div class="col-md-6">
										<div class="formSelectBox">
											<div class="form-group">
												<select id="shareholderLegalStatusId"
													name="shareholderLegalStatus"
													class="js-select2-oneColumn form-control"></select> <label
													class="control-label control-label_mandatory"
													for="shareholderLegalStatusId"><spring:theme
														code="license.legalstatus" /></label>
												<div class="help-block"></div>
											</div>
											
										</div>
									</div>
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderCapitalId" name="shareholderCapital"
													class="form-control" placeholder="." value="" type="text">
												<label class="control-label control-label_mandatory" for="">
													<spring:theme code="license.capital" />
												</label>
												<div class="help-block"></div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderLabourSizeId"
													name="shareholderLabourSize" class="form-control"
													placeholder="." value="" type="text"> <label
													class="control-label control-label_mandatory"
													for="shareholderLabourSizeId"> <spring:theme
														code="license.laboursize" />
												</label>
												<div class="help-block"></div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<%--Individual shareholder--%>
							<div id="individualShareholderId" class="row mt-3">
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderFirstNameId" name="shareholderFirstName"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory"
												for="shareholderFirstNameId"> <spring:theme
													code="license.firstname" />
											</label>
											<div class="help-block"></div>
										</div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderLastNameId" name="shareholderLastName"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory"
												for="shareholderLastNameId"> <spring:theme
													code="license.lastname" />
											</label>
											<div class="help-block"></div>
										</div>
									</div>
								</div>

								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderAcademicTitleId"
												name="shareholderAcademicTitle"
												class="js-select2-oneColumn form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderAcademicTitleId"><spring:theme
													code="license.academictitle" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox formInputBox_group ">
										<div class="form-group">
											<input id="birthDateId" name="enquiryType"
												class="form-control js-form-control_date flatpickr-input"
												placeholder="." value="" readonly="readonly" type="text">
											<label class="control-label control-label_mandatory"
												for="birthDateId"> <spring:theme
													code="license.dateofbirth" />
											</label>
											<div class="formInputBox-append" id="calendar-icon-pos">
												<span class="formInputBox-text"> <icon:calendar-gray />
												</span>
											</div>
										</div>
										<div class="help-block"></div>
									</div>
								</div>

								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderGenderId" name="shareholderGender"
												class="js-select2-oneColumn form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderGenderId"><spring:theme
													code="license.gender" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderMaritalStatusId"
												name="shareholderMaritalStatus"
												class="js-select2-oneColumn form-control">
											</select> <label class="control-label control-label_mandatory"
												for="shareholderMaritalStatusId"><spring:theme
													code="license.maritalstatus" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>

								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderPremiumResidentId"
												name="shareholderPremiumResident"
												class="js-select2-oneColumn form-control">
											</select> <label class="control-label control-label_mandatory"
												for="shareholderPremiumResidentId"><spring:theme
													code="license.premiumstatus" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>

								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderNationalityCurrentId"
												name="shareholderNationalityCurrent"
												class="js-select2-search form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderNationalityCurrentId"><spring:theme
													code="license.currentnationality" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderNationalityPreviousId"
												name="shareholderNationalityPrevious"
												class="js-select2-search form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderNationalityPreviousId"><spring:theme
													code="license.previousnationality" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
							</div>

							<div id="entityBasicInformation2" class="row">
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderPercentageId"
												name="shareholderPercentage" class="form-control"
												placeholder="." value="" type="text"> <label
												class="control-label control-label_mandatory"
												for="shareholderPercentageId"> <spring:theme
													code="license.sharespercentage" />
											</label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formCheckBox">
										<div class="form-group">
											<div class="form-item">
												<input id="shareholderInheritedPropertyId"
													name="shareholderInheritedProperty" class="form-control"
													placeholder="." type="checkbox" value=""> <label
													class="control-label" for="shareholderInheritedPropertyId">
													<span> <icon:check />
												</span> <spring:theme code="license.apply.inherited.property" />
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>


							<div id="inheritsection"  style="display: none">

								<div class="contentModule-separator"></div>

								<!-- <div class="contentModule-headline"><spring:theme code="license.inheritverification" /></div> -->
								<div class="contentModule contentModule-wrap">
									<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
										<span class="contentModule-headline"><spring:theme code="license.inheritverification"/></span>
										<div class="contentModule-headline-border"></div>
									</div>
								</div>

								<div class="row mt-3">
									<div class="col-md-4">
										<div class="formInputBox">
											<div class="form-group">
												<input id="deceasedId" name="deceasedId"
													class="form-control verify-moj" placeholder="." value="" type="text" maxlength="15">
												<label class="control-label" for="">
													<spring:theme code="license.inherit.deceasedId" />
												</label>
											</div>
											<div class="help-block"></div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="formInputBox">
											<div class="form-group">
												<input id="deedNumber" name="deedNumber"
													class="form-control verify-moj" placeholder="." value="" type="text" maxlength="15">
												<label class="control-label" for="">
													<spring:theme code="license.inherit.deedNo" />
												</label>
											</div>
											<div class="help-block"></div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="formInputBox">
											<div class="form-group">
												<button id="verifyInherit" type="button" class="btn" disabled><spring:theme code="license.inherit.verify"/></button>
												<input type="hidden" id="isMojVerified" name="isMojVerified" />
											</div>
										</div>
									</div>
									<div class="col-md-6" id="deceasedNameSection" style="display: none">
										<div class="formInputBox">
											<div class="form-group">
												<input id="deceasedName" name="deceasedName"
													class="form-control" placeholder="." value="" type="text" disabled>
												<label class="control-label" for="">
													<spring:theme code="license.inherit.deceasedName" />
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="contentModule-separator"></div>
						</div>

							<!-- <div class="contentModule-headline"><spring:theme code="license.contactinformation" /></div> -->
							

							<div class="border-top-line">
							<div id="shareholderAddressId" class="row">
								<div class="contentModule contentModule-wrap col-md-12">
									<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
										<span class="contentModule-headline"><spring:theme code="license.contactinformation"/></span>
										<div class="contentModule-headline-border"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formSelectBox">
										<div class="form-group">
											<select id="shareholderCountryId" name="shareholderCountry"
												class="js-select2-search form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderCountryId"><spring:theme
													code="license.country" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox-split">
										<div class="formInputBox formInputBox_big">
											<div class="form-group">
												<input id="shareholderStreetId" name="shareholderStreet"
													class="form-control form-control_labeled" placeholder="."
													value="" type="text"> <label
													class="control-label control-label_mandatory" for="">
													<spring:theme code="license.street" />
												</label>
											</div>
											<div class="help-block"></div>
										</div>
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderNumberId" name="shareholderNumber"
													class="form-control form-control_preNumber" placeholder="."
													value="" type="text"> <label
													class="control-label control-label_mandatory" for="">
													<spring:theme code="license.number" />
												</label>
											</div>
											<div class="help-block"></div>
										</div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderCityId" name="shareholderCity"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory" for="">
												<spring:theme code="license.city" />
											</label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderZipCodeId" name="shareholderZipCode"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory" for="">
												<spring:theme code="license.zipcode" />
											</label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderTelephoneId" name="shareholderTelephone"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory" for="">
												<spring:theme code="license.telephoneno" />
											</label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox">
										<div class="form-group">
											<input id="shareholderEmailId" name="shareholderEmail"
												class="form-control" placeholder="." value="" type="text">
											<label class="control-label control-label_mandatory" for="">
												<spring:theme code="license.email" />
											</label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-12 col-lg-6">
									<div class="formInputBox formInputBox_group">
										<div class="form-group">
											<input id="shareholderWebsiteId" name="shareholderWebsite"
												class="form-control website" placeholder="." value=""
												type="text"> <label
												class="control-label control-label_mandatory" for="">
												<spring:theme code="license.website" />
											</label>
											<div class="formInputBox-append d-none">
												<span class="formInputBox-text formInputBox-text_tip js-tip tool-tip-info"
													data-tip-title="Tooltip Information to be shown to the user."
													data-original-title="" title=""> <icon:tipInfo />
												</span>
											</div>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
							</div>

							<div id="delegateDivSection" style="display: none">
								<div class="contentModule-section">
									<!-- <div class="contentModule-separator"></div> -->
									<!-- <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator"> -->
										<!-- <span class="contentModule-headline contentModule-headline_small "> <spring:theme code="license.apply.shareholder.delegate" /></span> -->
										<div class="contentModule contentModule-wrap">
											<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
												<span class="contentModule-headline"><spring:theme code="license.apply.shareholder.delegate"/></span>
												<div class="contentModule-headline-border"></div>
											</div>
										</div>

										<div class="w-100 delegate-info-popover">
											<a class="btn btn_link js-tip btn-tooltip" style="padding-top: 10px;" data-container="body" data-tip-id="delegateToolTip" data-tip-class="delegateToolTip" data-trigger="click">
												<spring:theme code="text.account.profile.license.shareholders.tooltip.heading" />
												<svg version="1" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18"><path fill="#999ca4" d="M7.567 6.081c.407-.433.965-.649 1.674-.649.657 0 1.182.186 1.577.556s.592.844.592 1.42c0 .349-.07.632-.214.849-.144.218-.437.537-.88.958-.323.305-.533.564-.63.776-.098.213-.146.526-.146.94h-.879c0-.471.056-.85.167-1.138s.361-.618.748-.99l.402-.39c.122-.111.219-.227.295-.35.136-.213.205-.436.205-.666 0-.323-.099-.603-.295-.84s-.522-.355-.975-.355c-.561 0-.949.204-1.165.612-.121.228-.189.555-.207.983h-.878c0-.711.203-1.283.609-1.716zm1.074 5.67h.982v1.027h-.982v-1.027z" enable-background="new"></path><path fill="#999ca4" d="M9 17.389c-4.625 0-8.389-3.763-8.389-8.389 0-4.625 3.764-8.389 8.389-8.389 4.626 0 8.389 3.764 8.389 8.389 0 4.626-3.763 8.389-8.389 8.389zm0-16c-4.197 0-7.611 3.414-7.611 7.611 0 4.196 3.414 7.611 7.611 7.611 4.196 0 7.611-3.415 7.611-7.611 0-4.197-3.415-7.611-7.611-7.611z"></path></svg>
											</a>
											<div class="tooltip_content service_tooltip_content" id="delegateToolTip">
												<h2>
													<span><spring:theme
															code="text.account.profile.license.shareholders.tooltip.heading" /></span>
												</h2>
												<p >
													<spring:theme code="text.account.profile.license.shareholders.tooltip.body" />
												</p>
											</div>
										</div>
									</div>

									<div id="delegateSection">
										<div class="formRadioBox-wrapper my-0 py-0" id="showDelegateQuestion"
											${shareholderType eq "Organization" ? 'style="display: none"' : ''}>
											<div class="row">
												<div class="col-md-12 col-lg-7 mb-4">
													<span><spring:theme
															code="text.account.profile.license.shareholders.isDelegate" /></span>
												</div>
												<div class="col-md-12 col-lg-5 deligate-info-wrapper">
													<div class="formRadioBox deligate-info-form">
														<div class="form-group">
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateYES" value="true" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq true ? 'checked="checked"' : ''} />
																<label for="hasDelegateYES" id="hasDelegateYESLabel"
																	class="btn btn-ctrl btn_bold btn-bg control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.yes" /></label>
															</div>
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateNO" value="false" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq false ? 'checked="checked"' : 'checked'} />
																<label for="hasDelegateNO" id="hasDelegateNOLabel"
																	class="btn btn-ctrl btn_bold  control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.no" /></label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="formRadioBox-wrapper" id="showDelegateQuestionOrganization"
											${shareholderType eq "Organization" ? 'style="display: none"' : ''}>
											<div class="row">
												<div class="col-md-12 col-lg-6">
													<span><spring:theme
															code="text.account.profile.license.shareholders.que.wantdelegate" /></span>
												</div>
												<div class="col-md-12 col-lg-6">
													<div class="formRadioBox deligate-info-form">
														<div class="form-group">
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateNO" value="false" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq false ? 'checked="checked"' : ''} />
																<label for="hasDelegateNO" id="hasDelegateNOLabel"
																	class="btn btn-ctrl btn_bold control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.yes" /></label>
															</div>
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateYES" value="true" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq true ? 'checked="checked"' : 'checked'} />
																<label for="hasDelegateYES" id="hasDelegateYESLabel"
																	class="btn btn-ctrl btn_bold btn-bg control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.no" /></label>
															</div>
															
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="delegate">
											<div class="row" id="delegateDetails"
												${shareholderType eq "Organization" ? '' : 'style="display: none;"'}>
												<div class="col-md-6">
													<%--                                                <formElement:formSelectBoxCustom idKey="idType" labelKey="license.apply.shareholder.idType"--%>
													<%--                                                                                 path="delegateInfo.delegateIdentityType"--%>
													<%--                                                                                 selectCSSClass="js-select2-oneColumn validate__delegate-mandatory" labelCSS="control-label_mandatory"--%>
													<%--                                                                                 selectedDataValue="${data.delegateInfo.delegateIdentityType}" />--%>
													<div class="formSelectBox">
														<div class="form-group">
															<select id="idType"
																name="delegateInfo.delegateIdentityType"
																class="js-select2-oneColumn form-control">
															</select> <label class="control-label control-label_mandatory"
																for="idType"><spring:theme
																	code="license.apply.shareholder.idType" /></label>
														</div>
														<div class="help-block"></div>
													</div>
												</div>
												<div class="col-md-6">
													<%--                                                <formElement:formInputBoxCustom path="delegateInfo.delegateIdentityNumber" labelKey="license.apply.shareholder.idNumber"--%>
													<%--                                                                                idKey="idNumber" labelCSS="control-label_mandatory" inputCSS="validate__delegate-mandatory"/>--%>
													<div class="formInputBox">
														<div class="form-group">
															<input id="idNumber" name="delegateIdentityNumberName"
																class="form-control" placeholder="." value="" type="text" />
															<label class="control-label control-label_mandatory"
																for="idNumber"><spring:theme
																	code="license.apply.shareholder.idNumber" /></label>
														</div>
														<div class="help-block"></div>
													</div>
												</div>
												<div id="delegateDateofBirthSection" class="col-md-6">
													<c:set var="calendar">
														<icon:calendar-gray />
													</c:set>
													<div class="formInputBox formInputBox_group ">
														<div class="form-group">
															<input id="delegateDateofBirth"
																name="delegateDateOfBirthName" class="form-control "
																placeholder="." value="" type="text" /> <label
																class="control-label control-label_mandatory"
																for="delegateDateofBirth"><spring:theme
																	code="license.apply.shareholder.dateOfBirth" /></label>
															<div class="formInputBox-append" id="calendar-icon-pos">
																<span class="formInputBox-text"><icon:calendar-gray /></span>
															</div>
														</div>
														<div class="help-block"></div>
													</div>
												</div>
												<div class="col-md-6" id="nicVerifyBtnSection">
													<a style="margin-top: 15px" class="btn "
														id="verifyDetailsShow" data-nic-verified="false"><spring:theme
															code="license.apply.shareholder.verify" /></a> <input
														type="checkbox" id="isNicVerified"
														name="delegateInfo.nicVerified" value="true" class="hidden"
														${data.delegateInfo.nicVerified ? "checked=checked" : 0}>
													<div class="inputValidationError"
														style="display: none; color: #ff4c4a; line-height: 1.2; margin-top: 10px;">
														<spring:theme
															code="validation.licenseApply.shareholder.invalidInputValidation"
															text="Please click on 'Input Validation' to save Shareholder" />
													</div>
												</div>
											</div>
											<div id="verifyDelegateDetails" style="display: none;">
												<div class="contentModule contentModule-wrap">
													<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
														<span class="contentModule-headline"><spring:theme
															code="license.apply.shareholder.delegateDetails.title" /></span>
														<div class="contentModule-headline-border"></div>
													</div>
												</div>
												<!-- <div class="row">
													<div class="col-md-6">
														<div style="margin-top: 20px; font-size: 18px;"
															class="contentModule-headline contentModule-headline_smallMargin">
															<spring:theme
																code="license.apply.shareholder.delegateDetails.title" />
														</div>
													</div>
												</div> -->
												<div class="row">

													<div class="col-md-6">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegateFirstNameArabic"
																	name="delegate.firstNameArabic" class="form-control"
																	placeholder="." value="" type="text" 
																	dir="rtl" lang="ar" pattern="[\
																	\u0620-\u063F\u0641-\u064A\u066E-\u066F\u0671-\u06D3\u06D5\
																	\u06E5-\u06E6\u06EE-\u06EF\u06FA-\u06FC\u06FF\u0750-\u077F\
																	\u08A0\u08A2-\u08AC\uFB50-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\
																	\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\s]+"/> <label
																	class="control-label control-label_mandatory"
																	for="delegateFirstNameArabic"><spring:theme
																		code="general.firstname.arabic" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>

													<div class="col-md-6">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegateLastNameArabic"
																	name="delegate.lastNameArabic" class="form-control"
																	placeholder="." value="" type="text" 
																	dir="rtl" lang="ar" pattern="[\
																	\u0620-\u063F\u0641-\u064A\u066E-\u066F\u0671-\u06D3\u06D5\
																	\u06E5-\u06E6\u06EE-\u06EF\u06FA-\u06FC\u06FF\u0750-\u077F\
																	\u08A0\u08A2-\u08AC\uFB50-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\
																	\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\s]+"/> <label
																	class="control-label control-label_mandatory"
																	for="delegateLastNameArabic"><spring:theme
																		code="general.lastname.arabic" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>

													<div class="col-md-6">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegateFullNameEnglish"
																	name="delegate.fullNameEnglish" class="form-control"
																	placeholder="." value="" type="text" 
																	dir="ltr" lang="en" pattern="[a-zA-Z\s]+"/> <label
																	class="control-label control-label_mandatory"
																	for="delegateFullNameEnglish"><spring:theme
																		code="general.fullname.english" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>


													<div class="col-md-6">
														<div class="formInputBox formInputBox_group ">
															<div class="form-group">
																<input id="delegateBirthDateId" name="enquiryType"
																	class="form-control js-form-control_date flatpickr-input"
																	placeholder="." value="" readonly="readonly" type="text">
																<label class="control-label control-label_mandatory"
																	for="delegateBirthDateId"> <spring:theme
																		code="license.dateofbirth" />
																</label>
																<div class="formInputBox-append" id="calendar-icon-pos">
																	<span class="formInputBox-text"> <icon:calendar-gray />
																	</span>
																</div>
															</div>
															<div class="help-block"></div>
														</div>
													</div>

													<div class="col-md-6">
														<div class="formSelectBox">
															<div class="form-group">
																<select id="delegateGenderId" name="delegateGender"
																	class="js-select2-oneColumn form-control"></select> <label
																	class="control-label control-label_mandatory"
																	for="delegateGenderId"><spring:theme
																		code="license.gender" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>

													<div class="col-md-6">
														<c:set var="calendar">
															<icon:calendar-gray />
														</c:set>
														<div class="formInputBox formInputBox_group ">
															<div class="form-group">
																<input id="delegateIssueDate" name="delegateIssueDate"
																	class="form-control " placeholder="." value=""
																	type="text" /> <label
																	class="control-label control-label_mandatory"
																	for="delegateIssueDate"><spring:theme
																		code="license.apply.shareholder.issueDate" /></label>
																<div class="formInputBox-append" id="calendar-icon-pos">
																	<span class="formInputBox-text"><icon:calendar-gray /></span>
																</div>
															</div>
															<div class="help-block"></div>
														</div>
													</div>

													<div class="col-md-6">
														<c:set var="calendar">
															<icon:calendar-gray />
														</c:set>
														<div class="formInputBox formInputBox_group ">
															<div class="form-group">
																<input id="delegateExpiryDate" name="delegateExpiryDate"
																	class="form-control " placeholder="." value=""
																	type="text" /> <label
																	class="control-label control-label_mandatory"
																	for="delegateIssueDate"><spring:theme
																		code="license.apply.shareholder.expiryDate" /></label>
																<div class="formInputBox-append" id="calendar-icon-pos">
																	<span class="formInputBox-text"><icon:calendar-gray /></span>
																</div>
															</div>
															<div class="help-block"></div>
														</div>
													</div>


													<div class="col-md-6" id="delegateCountryDiv">

														<div class="formSelectBox">
															<div class="form-group">
																<select id="delegateCountry" name="delegate.country"
																	class="js-select2-oneColumn form-control"></select> <label
																	class="control-label control-label_mandatory"
																	for="delegateCountry"><spring:theme
																		code="general.country" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>
													<div class="col-md-6" id="delegateNationalityDiv">

														<div class="formSelectBox">
															<div class="form-group">
																<select id="delegateNationality"
																	name="delegateInfo.nationality"
																	class="js-select2-oneColumn form-control"></select> <label
																	class="control-label control-label_mandatory"
																	for="delegateNationality"><spring:theme
																		code="license.apply.shareholder.nationality" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>
													<div class="col-md-6" id="postalCodeDiv"
														style="display: none;">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegatePostalCode" name="delegate.postalCode"
																	class="form-control" placeholder="." value=""
																	type="text" /> <label
																	class="control-label control-label_mandatory"
																	for="delegatePostalCode"><spring:theme
																		code="general.postalcode" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>
													<div class="col-md-6" id="poBoxDiv" style="display: none;">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegatePOBox" name="delegate.poBox"
																	class="form-control" placeholder="." value=""
																	type="text" /> <label
																	class="control-label control-label_mandatory"
																	for="delegatePOBox"><spring:theme
																		code="general.pobox" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="formInputBox-split">
															<div class="formInputBox">
																<div class="form-group">
																	<input id="delegateCountryCodeForTelephone"
																		name="delegateCountryCodeForTelephoneName"
																		class="form-control form-control_preNumber"
																		placeholder="." type="text" value="" /> <label
																		class="control-label control-label_mandatory"
																		for="delegateCountryCodeForTelephone"><spring:theme
																			code="general.country.code" /></label>
																</div>
																<div class="help-block"></div>
															</div>
															<div class="formInputBox formInputBox_big">
																<div class="form-group">
																	<input id="delegateTelephone"
																		name="delegateTelephoneName"
																		class="form-control form-control_labeled"
																		placeholder="." type="text" value="" /> <label
																		class="control-label control-label_mandatory"
																		for="delegateTelephone"><spring:theme
																			code="general.telephone" /></label>
																</div>
																<div class="help-block"></div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="formInputBox-split">
															<div class="formInputBox">
																<div class="form-group">
																	<input id="delegateCountryCodeForMobile"
																		name="delegateCountryCodeForMobileName"
																		class="form-control form-control_preNumber"
																		placeholder="." type="text" value="" /> <label
																		class="control-label control-label_mandatory"
																		for="delegateCountryCodeForMobile"><spring:theme
																			code="general.country.code" /></label>
																</div>
																<div class="help-block"></div>
															</div>
															<div class="formInputBox formInputBox_big">
																<div class="form-group">
																	<input id="delegateMobile" name="delegateMobileName"
																		class="form-control form-control_labeled"
																		placeholder="." type="text" value="" /> <label
																		class="control-label control-label_mandatory"
																		for="delegateMobile"><spring:theme
																			code="general.mobilenumber" /></label>
																</div>
																<div class="help-block"></div>
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegateEmail" name="delegateEmailName"
																	class="form-control" placeholder="." value=""
																	type="text" /> <label
																	class="control-label control-label_mandatory"
																	for="delegateEmail"><spring:theme
																		code="general.license.email" /></label>
															</div>
															<div class="help-block"></div>
														</div>
													</div>



												</div>


											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>


						<div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection mt-5">
							<button type="button" class="btn btn-sector-outline btn_outline cancelShareholderBtn" data-dismiss="modal">
								<spring:theme code="general.cancel" />
							</button>
							<button type="button" class="btn btn-slim saveShareholderBtn">
								<spring:theme code="general.save" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div class="modal fade" id="branchModalId" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content panelModule panelModule_halfRadius">
				<form id="branchFormId">
					<!-- <div class="contentModule-headline">
						<spring:theme code="general.branch" />
					</div> -->

					<!-- <div class="contentModule-headline">
						<spring:theme code="license.branchdetails" />
					</div> -->
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline"><spring:theme code="license.branchdetails" /></span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
					<div class="border-top-line">
					<div class="row">
						<div class="col-md-12 col-lg-6">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="branchTypeId" name="branchType"
										class="js-select2-oneColumn form-control">
									</select> <label class="control-label control-label_mandatory"
										for="branchTypeId"><spring:theme
											code="license.branchtype" /></label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formInputBox">
								<div class="form-group">
									<input id="branchNameId" name="branchName" class="form-control"
										placeholder="." value="" type="text"> <label
										class="control-label control-label_mandatory" for=""> <spring:theme
											code="license.branchname" />
									</label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
					</div>
					</div>

					<div class="contentModule-separator"></div>
					<!-- <div class="contentModule-headline">
						<spring:theme code="license.contactinformation" />
					</div> -->
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline"><spring:theme code="license.contactinformation" /></span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
					<div class="border-top-line">
					<div class="row">
						<div class="col-md-12 col-lg-6">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="branchCountryId" name="branchCountry"
										class="js-select2-oneColumn form-control" disabled>
										<option value="SA" selected>Saudi Arabia</option>
									</select> <label class="control-label control-label_mandatory"
										for="branchCountryId"><spring:theme
											code="license.country" /></label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formInputBox-split">
								<div class="formInputBox formInputBox_big">
									<div class="form-group">
										<input id="branchStreetId" name="branchStreet"
											class="form-control form-control_labeled" placeholder="."
											value="" type="text"> <label
											class="control-label control-label_mandatory"
											for="branchStreetId"> <spring:theme
												code="license.street" />
										</label>
									</div>
									<div class="help-block"></div>
								</div>
								<div class="formInputBox">
									<div class="form-group">
										<input id="branchNumberId" name="branchNumber"
											class="form-control form-control_preNumber" placeholder="."
											value="" type="text"> <label
											class="control-label control-label_mandatory"
											for="branchNumberId"> <spring:theme
												code="general.no.number" />
										</label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="branchRegionId" name="branchRegion"
										class="js-select2-search form-control"></select> <label
										class="control-label control-label_mandatory"
										for="branchRegionId"><spring:theme
											code="license.region" /></label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="branchCityId" name="branchCity"
										class="js-select2-search form-control"></select> <label
										class="control-label control-label_mandatory"
										for="branchCityId"><spring:theme code="license.city" /></label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formInputBox">
								<div class="form-group">
									<input id="branchTelephoneId" name="branchTelephone"
										class="form-control" placeholder="." value="" type="text">
									<label class="control-label control-label_mandatory"
										for="branchTelephoneId"> <spring:theme
											code="license.telephoneno" />
									</label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formInputBox">
								<div class="form-group">
									<input id="branchEmailId" name="branchEmail"
										class="form-control" placeholder="." value="" type="text">
									<label class="control-label control-label_mandatory"
										for="branchEmailId"> <spring:theme code="license.email" />
									</label>
									<div class="help-block" id="branchEmailValidation"></div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-6">
							<div class="formInputBox formInputBox_group">
								<div class="form-group">
									<input id="branchWebsiteId" name="branchWebsite"
										class="form-control website" placeholder="." value=""
										type="text"> <label
										class="control-label control-label_mandatory"
										for="branchWebsiteId"> <spring:theme
											code="license.website" />
									</label>
									<div class="formInputBox-append d-none">
										<span class="formInputBox-text formInputBox-text_tip js-tip tool-tip-info"
											data-tip-title="Tooltip Information to be shown to the user."
											data-original-title="" title=""><icon:tipInfo /> </span>
									</div>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
					</div>
					</div>

					<div
						class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
						<button type="button"
							class="btn btn-slim btn_outline cancelBranchBtn"
							data-dismiss="modal">
							<spring:theme code="general.cancel" />
						</button>
						<button type="button" class="btn btn-slim saveBranchBtn">
							<spring:theme code="general.save" />
						</button>
						<button type="button" class="btn btn-slim closeBranchBtn"
							data-dismiss="modal">
							<spring:theme code="general.close" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="productModalId" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content panelModule panelModule_halfRadius">
				<form id="productFormId">
					<!-- <div class="contentModule-headline">
						<spring:theme code="general.edit" />
					</div> -->
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline"><spring:theme code="general.edit" /></span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="productId" name="productId"
										class="js-select2-oneColumn form-control js-product-list"
										data-search-placeholder="<spring:theme code='license.apply.product.id.description'/>">
									</select> <label class="control-label control-label_mandatory"
										for="productId"><spring:theme
											code="license.apply.product.id.description" /></label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
					</div>

					<div class="formTextArea">
						<div class="form-group">
							<textarea id="productDescriptionId" name="productDescription"
								class="form-control form-control_slim" placeholder="."
								maxlength="40"></textarea>
							<label class="control-label control-label_mandatory"
								for="productDescriptionId"> <spring:theme
									code="license.apply.product.description" />
							</label>
							<div class="help-block"></div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="formInputBox">
								<div class="form-group">
									<input id="productQuantityId" name="productQuantity"
										class="form-control" placeholder="." value="" type="text">
									<label class="control-label control-label_mandatory"
										for="productQuantityId"> <spring:theme
											code="license.apply.quantity" />
									</label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="formSelectBox">
								<div class="form-group">
									<select id="productUnitId" name="productUnit"
										class="js-select2-oneColumn form-control"></select> <label
										class="control-label control-label_mandatory"
										for="productUnitId"> <spring:theme
											code="license.apply.review.product.unit" />
									</label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
					</div>

					<div
						class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
						<button type="button"
							class="btn btn-slim btn_outline cancelProductBtn"
							data-dismiss="modal">
							<spring:theme code="general.cancel" />
						</button>
						<button type="button" class="btn btn-slim saveProductBtn">
							<spring:theme code="general.save" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="unsavedChangesModalId" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
			role="document">
			<div class="modal-content">
				<form action="" class="js-formInputFileBox">
					<div class="modal-header modal-header_smallPDB">
						<button type="button" class="modal-close" data-dismiss="modal"
							aria-label="Close">
							<icon:close />
						</button>
						<div class="modal-title">
							<spring:theme code="license.apply.changes.unsaved" />
						</div>						
					</div>
					<div class="modal-body">
						<div
							class="modal-description modal-description_largeMargin modal-description_smallText">
							<spring:theme code="license.apply.changes.notsubmitted" />
						</div>
					</div>
					<div class="modal-footer modal-footer_spaceBetween px-0">
						<button id="dismissChangesBtnId" type="button"
							class="btn btn-warning btn_round btn_slim ml-3" data-dismiss="modal">
							<spring:theme code="general.dismiss.changes" />
						</button>
						<button id="submitChangesBtnId" type="button"
							class="btn btn_round mr-3" data-dismiss="modal">
							<spring:theme code="general.submit.changes" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="docsmodalId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content panelModule panelModule_halfRadius">
					<form:form id="docsFormId">
						<div class="modal-title">
							<div id="amendmentTypeId" class="">
								<span class="contentModule-headline"><spring:theme code="license.amend.documents" /></span>
								<!-- <div class="contentModule-headline-border"></div> -->
							</div>
						</div>

						<div class="contentModule contentModule-wrap">
							<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
								<span class="contentModule-headline"><spring:theme code="license.amend.amendment.types" /></span>
								<div class="contentModule-headline-border"></div>
							</div>
						</div>							

						<div id="amendmentTypesId" class="row"></div>

						<div id="regularAmendmentDocsId">
							<div class="contentModule contentModule-wrap">
								<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
									<span class="contentModule-headline"><spring:theme code="license.amend.supporting.documents" /></span>
									<div class="contentModule-headline-border"></div>
								</div>
							</div>
							<div id="documentsId" class="row"></div>
						</div>
						<div class="col-sm-12 docTemplate">
							<div class="formInputFile">
								<div class="form-group">
									<input id="fileId" name="file" class="form-control js-inputFile"
										type="file" accept="image/jpeg,application/pdf" value="">
									<input id="text08" name="text08" class="form-control"
										type="text" value="" placeholder="" readonly tabindex="-1">
									<label class="control-label" for=""></label>
									<div class="form-icon form-icon_browse">
										<icon:upload />
									</div>
									<div class="form-icon form-icon_reset js-inputFile-reset">
										<icon:cross />
									</div>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="acceptTerms acceptTerms-no-margin">
							<div class="row">
								<div class="col-md-12">
									<div class="formCheckBox">
										<div class="form-group">
											<div class="form-item">
												<c:url var="termsUrl"
													value="/cms/sagia-cms-TandC-licenseServices" />
												<input id="termsAndConditionsId" name="checkbox01name"
													class="form-control" placeholder="." type="checkbox"
													value="entity name"> <label class="control-label"
													for="termsAndConditionsId"> <span><icon:check /></span>
													<spring:theme code="register.termsConditions"
														arguments="${termsUrl}" />
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div
							class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
							<button type="button"
								class="btn btn-slim btn_outline cancelAmendmentDialogBtn">
								<spring:theme code="general.cancel" />
							</button>
							<button id="submitAmendmentBtnId" type="button"
								class="btn btn-slim submitAmendmentBtn">
								<spring:theme code="general.submit" />
							</button>
						</div>
					</form:form>
			</div>
		</div>
	</div>
	
	

	<div class="modal fade" id="requestSubmittedDialogId" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">
						<spring:theme code="license.licenseamendment" />
					</div>
				</div>
				<div class="modal-body">
					<div class="modal-description">
						<spring:theme code="license.amendment.submitted" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_slim showHistoryBtn w-100"
						data-dismiss="modal">
						<spring:theme code="license.gotoamendmenthistory" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="requestErrorDialogId" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">
						<spring:theme code="license.licenseamendment" />
					</div>
				</div>
				<div class="modal-body">
					<div class="modal-description">
						<spring:theme code="license.errorsavingamendment" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_slim showHistoryBtn w-100"
						data-dismiss="modal">
						<spring:theme code="license.gotoamendmenthistory" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="licenseAmendmentValidationDialogId"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">
						<spring:theme code="license.errorvalidatingamendment" />
					</div>
				</div>
				<div class="modal-body">
					<div class="modal-description">
						<spring:theme code="license.errorsavingamendment" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_slim" data-dismiss="modal">
						<spring:theme code="general.close" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="licenseAmendmentNoChangesDialogId"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title">
						<spring:theme code="license.amendment.noChanges" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_slim" data-dismiss="modal">
						<spring:theme code="general.close" />
					</button>
				</div>
			</div>
		</div>
	</div>

	<div id="paginationElementId" style="display: none">
		<icon:arrow_green_right />
	</div>

	<modals:termsAndConditionsModal />
	<modals:errorModal />

</div>

<script>
	var controllerUrl = '${controllerUrl}';
	var srId = '${srId}';

function closeErrorModal() {
  if ($("body").hasClass("modal-open")) {
	$('body').removeClass('modal-open');
	$('body').addClass('modal-scroll');
  }
}

</script>

