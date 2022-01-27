<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license"%>
<%@ taglib prefix="modals" tagdir="/WEB-INF/tags/responsive/common"%>
<%--<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>--%>
<%--<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>--%>

<script>
	var configuredFileSize = $
	{
		maxUploadSize
	};
</script>

<div class="mainSection mainSection">
	<div class="achievement_header">
			<img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
			<div class="container">
					<div class="banner-container aos-init aos-animate container" data-aos="fade-up">
							<h1 data-aos="fade-up">
								<spring:theme code="license.amendlicense" />
							</h1>
					</div>
					<div class="profile-icons float-right">
							<c:if test="${hasLicense or hasAwaitingPayment}">
									<div class="calendar">
											<a href="${encodedContextPath}/appointments" title="<spring:message code='appointments.appointmentoverview'/>">
													<span></span>
											</a>
									</div>
									<div class="calendar notification">
											<div class="count-notification">123</div>
											<a href="${encodedContextPath}/my-sagia/notifications">
													<span></span>
											</a>
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
		<div class="m-5">
			<div class="row w-100 renewal-services">
				<div class="col-md-3 col-12 px-0">
                    <a href="/service-search" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span>Back to All Services</a>
                </div>
                <div class="col-xl-3 col-12">
                    <button class="btn btn_leftIconLink btn_darkLink back_to_service serviceTab" data-expand-target="service-tab" onclick="expandServiceTab('${sagiaService.code}')">Show Service Tabs</button>
                </div>
			</div>
			<div class="row w-100 d-flex mt-4	">
				<div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
					<div class="col-xl-3 col-12">
						<button id="saveDraftBtnId" class="btn btn_round">
							<spring:theme code="general.savedraft" />
							<span class="iconElement iconElement_save"><icon:save /></span>
						</button>
					</div>
					<div class="col-xl-6 col-12 amend-btns-list">
						<button id="loadDraftBtnId"
							class="btn btn_round btn_slim js-load-draft"
							<c:if test="${!draftExists}">style="display: none"</c:if>>
							<spring:theme code="general.loaddraft" />
							<span class="iconElement iconElement_save"><icon:upload /></span>
						</button>
					</div>
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
			<div class="hidden "><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span> Show Service Overview</div>
			<div class=""><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span>Hide Service Overview</div>
		</button>
	</div>
	<div class="service-wrapper service-wrapper-info w-100" id="expand-03">
		<div class="serviceModule serviceModule_list mx-5 pt-4">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">
						<span class="serviceModule-headline"> Service overview </span>
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
		<div class="serviceModule serviceModule_list mx-5">
			<div class="serviceModule-section">
				<div class="serviceModule-content">
					<div class="serviceModule-description">
						<span class="serviceModule-headline"> Service document </span>
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
						<span class="serviceModule-headline"> Rules & Restriction </span>
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
						<span class="serviceModule-headline"> Financial FEE </span>
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
						<span class="serviceModule-headline"> Duration </span>
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

	<!-- <div class="mainSection mainSection_white mainSection_narrow">
		<div class="container d-flex service-history">
			
		</div>
	</div> -->
	<!-- <div class="mainSection mainSection_white mainSection_narrow mainSection_xsmallPaddingTop service-request service-wrapper service-wrapper-info mw-100 w-100 mt-5 mb-3"> -->
		<div class="mainSection mainSection_dark mainSection_pdt16 mt-5 mb-3 service-main">
		<div class="container">
			<div class="mainSection-linkActions mainSection-linkActions_right amend-service-link">
				<button class="btn_history btn_rightIconLink btn_bold btn_greenLink js-expandContent" data-expand-target="expand01">
					<div class="hidden"><span class=""><img src="${commonResourcePath}/images/dashboard-media/services/Show.png" alt="show"/></span><spring:theme code="legalConsultation.showServiceHistory"/></div>
					<div><span class="iconElement iconElement_closeBack image-pos"><img src="${commonResourcePath}/images/dashboard-media/services/Hide.png" alt="hide"/></span><spring:theme code="legalConsultation.hideServiceHistory"/></div>
				</button>
			</div>

			<div class="expandableContent expandableContent_upLg" id="expand01">
				<div class="expandableContent-aside">
					<div class="panelModule panelModule_halfRadius">
						<div class="contentModule">
							<div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
								<div class="contentModule-headline contentModule-headline-history">
									<span class="iconElement iconElement_history"><icon:history /></span>
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
									class="contentModule-actions contentModule-actions_spaceBetween">
									<button type="button"
										class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn full-width-responsive">
										<spring:theme code="general.cancel" />
									</button>
									<button id="nextTabEntityBtnId" type="button"
										class="btn btn-primary newAmendmentBtn full-width-responsive">
										<spring:theme code="general.proceed" />
									</button>
								</div>
							</div>

							<div id="entityHistoryTabId" class="contentModule">
								<div class="contentModule-section" style="font-size: 15px;">
									<div class="row">
										<div class="col-sm-4" style="text-align: right">
											<spring:theme code="general.capital" />
										</div>
										<div id="capitalNewId" class="col-sm-4"></div>
										<div id="capitalOldId" class="col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col-sm-4" style="text-align: right">
											<spring:theme code="license.labour" />
										</div>
										<div id="labourNewId" class="col-sm-4"></div>
										<div id="labourOldId" class="col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col-sm-4" style="text-align: right">
											<spring:theme code="license.entityname" />
										</div>
										<div id="entitynameNewId" class="col-sm-4"></div>
										<div id="entitynameOldId" class="col-sm-4"></div>
									</div>

									<div class="row">
										<div class="col-sm-4" style="text-align: right">
											<spring:theme code="license.legalstatus" />
										</div>
										<div id="legalstatusNewId" class="col-sm-4"></div>
										<div id="legalstatusOldId" class="col-sm-4"></div>
									</div>
								</div>
							</div>
						</div>

						<div class="panelTabs-head" id="">
							<!-- <icon:isic-activity /> -->
							<span class="panelTabs-label"><spring:theme
									code="license.isicactivity" /></span>
						</div>
						<div class="panelTabs-body">
							<p id="oldActivitiesId"></p>
							<license:licenseBusinessActivitiesSection />
							<div
								class="contentModule-actions contentModule-actions_spaceBetween">
								<button type="button"
									class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn full-width-responsive">
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
													<th><spring:theme
															code="text.account.profile.license.shareholders.nationality" /></th>
													<th><spring:theme
															code="text.account.profile.license.shareholders.legalStatus" /></th>
													<th id="shareholderBtnColumnId"></th>
												</tr>
											</thead>
											<tbody id="shareholdersId" class="tableModule-body">
												<tr class="shareholderTemplate">
													<td></td>
													<td class="type"></td>
													<td class="percentage"></td>
													<td></td>
													<td></td>
													<td class="tableModule-bodyItem-action">
														<button type="button"
															class="btn btn_link editShareholderBtn"
															data-toggle="modal"
															<%--                                                        data-target="#shareholderEditModalId" data-backdrop="static"--%>
															data-target="#shareholderModalId"
															data-backdrop="static" data-keyboard="false">
															<icon:edit />
														</button>
														<div class="deleteDropdown js-deleteDropdown">
															<button type="button"
																class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
																<icon:remove />
															</button>
															<div class="deleteDropdown-drop">
																<div class="deleteDropdown-text">
																	<spring:theme
																		code="text.account.profile.license.shareholders.deleteShareholder" />
																</div>
																<div class="deleteDropdown-actions">
																	<button type="button"
																		class="btn btn_outline btn_slim js-deleteDropdown-cancel">
																		<spring:theme code="general.cancel" />
																	</button>
																	<button type="button"
																		class="btn btn_slim removeShareholderBtn">
																		<spring:theme code="general.delete" />
																	</button>
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
									class="contentModule-actions contentModule-actions_spaceBetween">
									<button type="button"
										class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn full-width-responsive">
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
										<table>
											<tr class="branchTemplate">
												<td><strong></strong></td>
												<td></td>
												<td></td>
												<td></td>
												<td class="tableModule-bodyItem-action">
													<button type="button" class="btn btn_link editBranchBtn"
														data-toggle="modal" data-target="#branchModalId"
														data-backdrop="static" data-keyboard="false">
														<icon:edit />
													</button>
													<button type="button" class="btn btn_link viewBranchBtn"
														data-toggle="modal" data-target="#branchModalId"
														data-backdrop="static" data-keyboard="false">
														<icon:view />
													</button>
													<div class="deleteDropdown js-deleteDropdown">
														<button type="button"
															class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
															<icon:remove />
														</button>
														<div class="deleteDropdown-drop">
															<div class="deleteDropdown-text">
																<spring:theme
																	code="text.account.profile.license.branches.deletethebranch" />
															</div>
															<div class="deleteDropdown-actions">
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
												</td>
											</tr>
										</table>
										<table id="branchesTableId" class="tableModule-table">
											<thead class="tableModule-head">
												<tr>
													<th><spring:theme
															code="text.account.profile.license.branches.type" /> <span
														class="sort-icon"></span></th>
													<th><spring:theme
															code="text.account.profile.license.branches.name" /> <span
														class="sort-icon"></span></th>
													<th><spring:theme code="general.city" /> <span
														class="sort-icon"></span></th>
													<th><spring:theme
															code="text.account.profile.license.branches.number" /> <span
														class="sort-icon"></span></th>
													<th id="branchesBtnColumnId"></th>
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
									class="contentModule-actions contentModule-actions_spaceBetween">
									<button type="button"
										class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn full-width-responsive">
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
													<th id="productsBtnColumnId"></th>
												</tr>
											</thead>
											<tbody id="productsId" class="tableModule-body">
												<tr class="productTemplate">
													<td><strong></strong></td>
													<td></td>
													<td></td>
													<td></td>
													<td class="tableModule-bodyItem-action">
														<button type="button" class="btn btn_link editProductBtn"
															data-toggle="modal" data-target="#productModalId"
															data-backdrop="static" data-keyboard="false">
															<icon:edit />
														</button>
														<div class="deleteDropdown js-deleteDropdown">
															<button type="button"
																class="btn btn_link deleteDropdown-btn js-deleteDropdown-btn">
																<icon:remove />
															</button>
															<div class="deleteDropdown-drop">
																<div class="deleteDropdown-text">
																	<spring:theme code="products.deleteproduct" />
																</div>
																<div class="deleteDropdown-actions">
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
									class="contentModule-actions contentModule-actions_spaceBetween">
									<button type="button"
										class="btn btn-secondary cancelAmendmentBtn newAmendmentBtn full-width-responsive">
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

					<div class="contentModule-commentsSection" id="ammendComments"
						style="display: none;">
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
							class="btn btn-slim btn_outline cancelShareholderBtn"
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
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content panelModule panelModule_halfRadius">
					<div class="contentModule-headline">
						<spring:theme code="license.shareholder" />
					</div>
					<div id="shareholderNewExistingTypeId" class="row">
						<div class="col-md-8">
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

					<div id="shareholderPersonEntityTypeId" class="row">
						<div class="col-md-8">
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

					<div id="shareholderValidationSection">
						<div class="row" id="shareholderValidationDetails">
							<div id="shareholderIdTypeSection" class="col-md-6">
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
							<div id="shareholderIdNumberSection" class="col-md-6">
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
							<div id="shareholderDateofBirthSection" class="col-md-6">
								<div class="formInputBox formInputBox_group ">
									<div class="form-group">
										<input id="shareholderDateofBirth"
											name="shareholderValidation.shareholderDateofBirth"
											class="form-control " placeholder="." value="" type="text" />
										<label class="control-label control-label_mandatory"
											for="shareholderDateofBirth"><spring:theme
												code="license.apply.shareholder.dateOfBirth" /></label>
										<div class="formInputBox-append">
											<span class="formInputBox-text"><icon:calendar-gray /></span>
										</div>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="col-md-6" id="nicShareholderVerifyBtnSection">
								<a style="margin-top: 15px" class="btn"
									id="verifyShareholderDetailsShow" data-nic-verified="false"><spring:theme
										code="license.apply.shareholder.verify" /></a> <input
									type="checkbox" id="isShareholderNicVerified"
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
							<div class="contentModule-headline">
								<spring:theme code="license.basicinformation" />
							</div>

							<%--Entity shareholder--%>
							<div id="entityShareholderId">
								<div class="row" id="companyVerificationSection">
									<div class="col-md-6">
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
									<div class="col-md-12">
										<div class="contentModule-separator"></div>
									</div>
								</div>

								<div id="entityBasicInformation" class="row">
									<div class="col-md-6">
										<div class="formInputBox">
											<div class="form-group">
												<input id="shareholderNameId" name="shareholderName"
													class="form-control" placeholder="." value="" type="text">
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
													placeholder="." value="" type="text"> <label
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
											</div>
											<div class="help-block"></div>
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
											</div>
											<div class="help-block"></div>
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
											</div>
											<div class="help-block"></div>
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
							<div id="individualShareholderId" class="row">
								<div class="col-md-6">
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
								<div class="col-md-6">
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

								<div class="col-md-6">
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
								<div class="col-md-6">
									<div class="formInputBox formInputBox_group ">
										<div class="form-group">
											<input id="birthDateId" name="enquiryType"
												class="form-control js-form-control_date flatpickr-input"
												placeholder="." value="" readonly="readonly" type="text">
											<label class="control-label control-label_mandatory"
												for="birthDateId"> <spring:theme
													code="license.dateofbirth" />
											</label>
											<div class="formInputBox-append">
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
											<select id="shareholderGenderId" name="shareholderGender"
												class="js-select2-oneColumn form-control"></select> <label
												class="control-label control-label_mandatory"
												for="shareholderGenderId"><spring:theme
													code="license.gender" /></label>
										</div>
										<div class="help-block"></div>
									</div>
								</div>
								<div class="col-md-6">
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

								<div class="col-md-6">
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

								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-sm-6">
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
								<div class="col-md-6">
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

								<div class="contentModule-headline">
									<spring:theme code="license.inheritverification" />
								</div>

								<div class="row">
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

							<div class="contentModule-headline">
								<spring:theme code="license.contactinformation" />
							</div>

							<div id="shareholderAddressId" class="row">
								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-md-6">
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
								<div class="col-md-6">
									<div class="formInputBox formInputBox_group">
										<div class="form-group">
											<input id="shareholderWebsiteId" name="shareholderWebsite"
												class="form-control website" placeholder="." value=""
												type="text"> <label
												class="control-label control-label_mandatory" for="">
												<spring:theme code="license.website" />
											</label>
											<div class="formInputBox-append">
												<span class="formInputBox-text formInputBox-text_tip js-tip"
													style="position: relative; z-index: 1000;"
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
									<div class="contentModule-separator"></div>
									<div
										class="contentModule-headline contentModule-headline_smallMargin">
										<spring:theme code="license.apply.shareholder.delegate" />
										<a class="btn btn_link js-tip" style="padding-top: 10px;"
											data-container="body" data-tip-id="delegateToolTip"
											data-tip-class="delegateToolTip" data-trigger="click"><spring:theme
												code="text.account.profile.license.shareholders.tooltip.heading" /></a>
										<div class="tooltip_content" id="delegateToolTip">
											<h2>
												<span><spring:theme
														code="text.account.profile.license.shareholders.tooltip.heading" /></span>
											</h2>
											<p style="margin: 10px">
												<spring:theme
													code="text.account.profile.license.shareholders.tooltip.body" />
											</p>
										</div>
									</div>
									<div id="delegateSection">
										<div class="formRadioBox-wrapper" id="showDelegateQuestion"
											${shareholderType eq "Organization" ? 'style="display: none"' : ''}>
											<div class="row">
												<div class="col-md-6">
													<span><spring:theme
															code="text.account.profile.license.shareholders.isDelegate" /></span>
												</div>
												<div class="col-md-6">
													<div class="formRadioBox">
														<div class="form-group">
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateYES" value="true" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq true ? 'checked="checked"' : ''} />
																<label for="hasDelegateYES" id="hasDelegateYESLabel"
																	class="control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.yes" /></label>
															</div>
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateNO" value="false" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq false ? 'checked="checked"' : ''} />
																<label for="hasDelegateNO" id="hasDelegateNOLabel"
																	class="control-label"><spring:theme
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
												<div class="col-md-6">
													<span><spring:theme
															code="text.account.profile.license.shareholders.que.wantdelegate" /></span>
												</div>
												<div class="col-md-6">
													<div class="formRadioBox">
														<div class="form-group">
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateYES" value="true" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq true ? 'checked="checked"' : ''} />
																<label for="hasDelegateYES" id="hasDelegateYESLabel"
																	class="control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.no" /></label>
															</div>
															<div class="form-item">
																<input type="radio" name="delegateInfo.delegateYourself"
																	id="hasDelegateNO" value="false" class="form-control"
																	${not empty data.delegateInfo && data.delegateInfo.delegate != false && data.delegateInfo.delegateYourself eq false ? 'checked="checked"' : ''} />
																<label for="hasDelegateNO" id="hasDelegateNOLabel"
																	class="control-label"><spring:theme
																		code="text.account.profile.license.shareholders.hasDelegate.yes" /></label>
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
															<div class="formInputBox-append">
																<span class="formInputBox-text"><icon:calendar-gray /></span>
															</div>
														</div>
														<div class="help-block"></div>
													</div>
												</div>
												<div class="col-md-6" id="nicVerifyBtnSection">
													<a style="margin-top: 15px" class="btn"
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
												<div class="row">
													<div class="col-md-6">
														<div style="margin-top: 20px; font-size: 18px;"
															class="contentModule-headline contentModule-headline_smallMargin">
															<spring:theme
																code="license.apply.shareholder.delegateDetails.title" />
														</div>
													</div>
												</div>
												<div class="row">

													<div class="col-md-6">
														<div class="formInputBox">
															<div class="form-group">
																<input id="delegateFirstNameArabic"
																	name="delegate.firstNameArabic" class="form-control"
																	placeholder="." value="" type="text" /> <label
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
																	placeholder="." value="" type="text" /> <label
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
																	placeholder="." value="" type="text" /> <label
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
																<div class="formInputBox-append">
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
																<div class="formInputBox-append">
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
																<div class="formInputBox-append">
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


						<div
							class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
							<button type="button"
								class="btn btn-slim btn_outline cancelShareholderBtn"
								data-dismiss="modal">
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
					<div class="contentModule-headline">
						<spring:theme code="general.branch" />
					</div>

					<div class="contentModule-headline">
						<spring:theme code="license.branchdetails" />
					</div>

					<div class="row">
						<div class="col-md-6">
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
						<div class="col-md-6">
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

					<div class="contentModule-separator"></div>
					<div class="contentModule-headline">
						<spring:theme code="license.contactinformation" />
					</div>

					<div class="row">
						<div class="col-md-6">
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
						<div class="col-md-6">
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
						<div class="col-md-6">
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
						<div class="col-md-6">
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
						<div class="col-md-6">
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
						<div class="col-md-6">
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
						<div class="col-md-6">
							<div class="formInputBox formInputBox_group">
								<div class="form-group">
									<input id="branchWebsiteId" name="branchWebsite"
										class="form-control website" placeholder="." value=""
										type="text"> <label
										class="control-label control-label_mandatory"
										for="branchWebsiteId"> <spring:theme
											code="license.website" />
									</label>
									<div class="formInputBox-append">
										<span class="formInputBox-text formInputBox-text_tip js-tip"
											style="position: relative; z-index: 1000;"
											data-tip-title="Tooltip Information to be shown to the user."
											data-original-title="" title=""><icon:tipInfo /> </span>
									</div>
								</div>
								<div class="help-block"></div>
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
					<div class="contentModule-headline">
						<spring:theme code="general.edit" />
					</div>
					<div class="row">
						<div class="col-md-6">
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
						<div class="modal-title">
							<spring:theme code="license.apply.changes.unsaved" />
						</div>
						<button type="button" class="modal-close" data-dismiss="modal"
							aria-label="Close">
							<icon:close />
						</button>
					</div>
					<div class="modal-body">
						<div
							class="modal-description modal-description_largeMargin modal-description_smallText">
							<spring:theme code="license.apply.changes.notsubmitted" />
						</div>
					</div>
					<div class="modal-footer modal-footer_spaceBetween">
						<button id="dismissChangesBtnId" type="button"
							class="btn btn-warning btn_round btn_slim" data-dismiss="modal">
							<spring:theme code="general.dismiss.changes" />
						</button>
						<button id="submitChangesBtnId" type="button"
							class="btn btn_round btn_slim" data-dismiss="modal">
							<spring:theme code="general.submit.changes" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="docsmodalId" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content panelModule panelModule_halfRadius">
				<div class="contentModule">
					<form:form id="docsFormId">
						<div id="amendmentTypeId"
							class="contentModule-headline contentModule-headline_big contentModule-headline_bordered">
							<spring:theme code="license.amend.documents" />
						</div>

						<div
							class="contentModule-headline contentModule-headline_marginBottom">
							<spring:theme code="license.amend.amendment.types" />
						</div>
						<div id="amendmentTypesId" class="row"></div>

						<div id="regularAmendmentDocsId">
							<div class="contentModule-separator"></div>
							<div class="contentModule-headline">
								<spring:theme code="license.amend.supporting.documents" />
							</div>
							<div id="documentsId" class="row"></div>
						</div>
						<div class="col-sm-6 docTemplate">
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
						<%-- <div class="contentModule-separator"></div>
					<div id="simulatedPriceDivContent" class="contentModule-headline contentModule-headline_marginBottom"><spring:theme code="license.amend.totalPrice"/></div>
						<div id="simulatedPriceRow" class="row">
							<div class="col-sm-6">
							<ul class="dottedList dottedList_green dottedList_big">
							<li class="dottedList-item" id="simulatedPriceNetValue"></li></ul></div>
					</div> --%>
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
					<button type="button" class="btn btn_slim showHistoryBtn"
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
					<button type="button" class="btn btn_slim showHistoryBtn"
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
</script>
