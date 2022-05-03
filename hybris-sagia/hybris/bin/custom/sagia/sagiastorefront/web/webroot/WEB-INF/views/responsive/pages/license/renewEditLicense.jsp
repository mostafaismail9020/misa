<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/textfieldconfiguratortemplateaddon/responsive/product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
    var autoRenewal = "${autoRenewal}";
    var autoRenewalClearance = "${autoRenewalClearance}";
</script>

<div class="mainSection mainSection bg-white">
	<div class="achievement_header">
			<img class="achievement_header_icon  page-header-image"  src="${commonResourcePath}/images/dashboard-media/Banner-icons/header-banner-image.png" alt='${imageIcon.altText}' title='${imageIcon.altText}'>
			<div class="container">
					<div class="banner-container aos-init aos-animate container" data-aos="fade-up">
							<h1 data-aos="fade-up">
								<spring:theme code="renewlicense.licenserenewal"/>
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
												<img src="${commonResourcePath}/images/dashboard-media/Profile-bar/message-in-active.svg" class="notification_b2b_img" alt="message"/>
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


<!-- <div class="mainSection mainSection_dark">
	<div class="container">
		<div class="mainSection-header">
			<h1 class="mainSection-headline mainSection-header-action" ><spring:theme code="renewlicense.licenserenewal"/></h1>
		</div>
	</div>
</div> -->

<div class="container mainSection mainSection_dark mainSection_noPadding">
	<div class="m-0 ml-custom-35">
		<div class="row w-100">
            <div class="col-md-6 col-12 px-0">
				<a href="${encodedContextPath}/service-search/FIRST" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack  " id="image-pos"><img src="${commonResourcePath}/images/dashboard-media/arrow-back.png" alt="back"/></span><spring:theme code="service.back.all"/></a>
			</div>
        </div>
		<div class="row w-100 d-none mt-4 lg-btn">
			<div class="mainSection-linkActions mainSection-linkActions_spaceBetween btn-drafts_list amend-service-link amend-btns-list">
				<!-- <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/></a> -->
				<button class="btn btn_round js-save-draft ml-0"
						data-target-form="renewLicenceId"
						data-service-id="${serviceId}">
					<spring:theme code="general.savedraft"/>
					<span class="iconElement iconElement_save"><icon:save/></span>
				</button>
				<button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
						data-target-form="renewLicenceId"
						data-service-id="${serviceId}">
					<spring:theme code="general.loaddraft"/><span class="iconElement iconElement_save"><icon:upload/></span>
				</button>
			</div>
		</div>
	</div>
</div>



<div class="mainSection mainSection_dark mainSection_pdt16 mt-3">
	<div class="container">
		<c:choose>
			<c:when test='${reapply}'><c:set value="${encodedContextPath}/my-sagia/license/renew/reapply/${reapplyId}" var="actionUrl"></c:set></c:when>
			<c:otherwise><c:set value="${encodedContextPath}/my-sagia/license/renew/edit" var="actionUrl"></c:set></c:otherwise>
		</c:choose>

		<form:form id="renewLicenceId"  method="post" action="${actionUrl}" modelAttribute="licenseRenewalForm" enctype="multipart/form-data">
			<div class="panelModule panelModule_halfRadius mt-3">
				<div class="contentModule contentModule-wrap">
					<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
						<span class="contentModule-headline"><spring:theme code="renewlicense.wasseladdress"/></span>
						<div class="contentModule-headline-border"></div>
					</div>
				</div>
				<div class="contentModule-section contentModule-section_paddingSide">
					<div class="row">
						<div class="col-md-6">
							<div class="formInputBox-split">
								<div class="formInputBox formInputBox_big">
									<div class="form-group">
										<form:input path="street" cssClass="form-control form-control_preNumber" placeholder="."/>
										<label class="control-label control-label_mandatory" for="street">
											<spring:theme code="license.street"/>
										</label>
									</div>
									<div class="help-block"></div>
								</div>
								<div class="formInputBox">
									<div class="form-group">
										<form:input path="houseNo" cssClass="form-control form-control_preNumber" placeholder="."/>
										<label class="control-label control-label_mandatory" for="houseNo">
											<spring:theme code="license.number"/>.
										</label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="formInputBox">
								<div class="form-group">
									<form:input path="city" cssClass="form-control form-control_preNumber" placeholder="."/>
									<label class="control-label control-label_mandatory" for="city"><spring:theme code="general.city"/></label>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="formInputBox">
								<div class="form-group">
									<form:input path="additNo" cssClass="form-control form-control_preNumber" placeholder="."/>
									<label class="control-label control-label_mandatory" for="additNo"><spring:theme code="license.additionalnumber"/></label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="formSelectBox">
								<div class="form-group">
									<form:select path="country" cssClass="js-select2-oneColumn">
										<option></option>
										<form:options items="${countries}" itemValue="${not empty itemValue ? itemValue :'code'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}" htmlEscape="true"/>
									</form:select>
									<label class="control-label control-label_mandatory" for="country"><spring:theme code="general.country"/></label>
								</div>
								<div class="help-block"></div>
							</div>
							<div class="formInputBox">
								<div class="form-group">
									<form:input path="zipCode" cssClass="form-control form-control_preNumber" placeholder="."/>
									<label class="control-label control-label_mandatory" for="zipCode"><spring:theme code="address.postalcode"/></label>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="formInputBox">
								<div class="form-group">
									<form:input path="buildingNo" cssClass="form-control form-control_preNumber" placeholder="."/>
									<label class="control-label control-label_mandatory" for="buildingNo"><spring:theme code="address.buildingno"/></label>
									<div class="help-block"></div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<%-- <div class="contentModule-section contentModule-section_paddingSide">
					<span class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="general.images"/></span>
					<div class="documentModule js-upload-files-list" data-files-name="img">


						<ul class="downloadList downloadList_secondary js-confirmed-files">
							<c:forEach items="${resubmittedImages}" var="image" varStatus="count">
								<li class="downloadList-item js-loaded-file" data-file-code="${image.documentID}">
									<div class="downloadList-description">
										<span class="iconElement iconElement_pdf"><icon:document></icon:document></span>
										<span class="js-file-name">image-${count.index+1}</span>
										<input name="img" class="js-file-code-inpjs-remove-fileut" type="hidden" file-name="${image.documentID}" value="${image.documentID}">
									</div>
									<div class="downloadList-actions">
										<a class="link link_nowrap" onclick="removeImage(this)">
												<span class="iconElement iconElement_cloud02"><icon:remove></icon:remove></span>
											Remove
										</a>
									</div>
								</li>
							</c:forEach>
						</ul>

						<div class="contentModule-actions contentModule-actions_centered">
							<button class="btn js-upload-files"><spring:theme code="general.add"/></button>
						</div>

						<div class="modal fade js-upload-list-modal" tabindex="-1" role="dialog" aria-labelledby="uploadListModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<div class="modal-title"><spring:theme code="uploaddocuments.uploadyourfiles"/></div>
									</div>
									<div class="modal-body">
										<div class="formInputFileBox js-form-input-file-box">
											<div class="form-group">
												<div class="form-icon form-icon_browse">
													<icon:upload/>
												</div>
												<input class="form-control js-input-file" type="file" accept="image/jpg" multiple name="" id="fileBoxModalimg"/>
												<label class="control-label js-file-label" for="fileBoxModalimg"><spring:theme code="uploaddocuments.chooseafile"/> <span class="formInputFileBox-dragndrop"><spring:theme code="uploaddocuments.draghere"/></span></label>
											</div>
										</div>

										<div class="modal-description modal-description_largeMargin">
											<ul class="downloadList downloadList_secondary js-file-list"></ul>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary btn_slim btn_round js-close-modal-btn"><spring:theme code="general.cancel"/></button>
										<button type="button" class="btn btn_slim btn_round js-confirm-modal-btn"><spring:theme code="general.confirm"/></button>
									</div>
								</div>
							</div>
						</div>

						<div style="display: none">
							<ul>
								<li class="downloadList-item js-append-file js-loaded-file">
									<div class="downloadList-description">
										<span class="iconElement iconElement_pdf"><icon:document/></span>
										<span class="js-file-name"><spring:theme code="general.name"/></span>
										<input name="img" class="js-file-code-input" type="hidden"/>
									</div>
									<div class="downloadList-actions">
										<a href="#" class="link link_nowrap js-remove-file" style="display:none;">
											<span class="iconElement iconElement_cloud02"><icon:remove/></span>
											<spring:theme code="general.remove"/>
										</a>
										<img class="js-loading-spinner" src="${commonResourcePath}/images/spinner.gif" alt="loading"/>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div> --%>

				<div class="contentModule-section contentModule-section_paddingSide">
					<!-- <span class="contentModule-headline contentModule-headline_smallMargin "><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></span> -->
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline"><spring:theme code="licenseApplyEntityInformation.licenseYearSection.title"/></span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
					<div class="documentModule contentModule-headline-border js-upload-files-list border-top-0" data-files-name="img">
							<div class="formSelectBox">
								<div class="form-group">
									<form:select path="duration" cssClass="js-select2-oneColumn" onChange="updateExpiryDate(this, event)">
										<option></option>
										<form:options items="${durations}" itemValue="${not empty itemValue ? itemValue :'code'}" itemLabel="${not empty itemLabel ? itemLabel :'name'}"  htmlEscape="true"/>
									</form:select>
									<label class="control-label control-label_mandatory" for="duration"><spring:theme code="license.apply.entity.licenseYear"/></label>
								</div>
								<div class="help-block"></div>

									<span class="renewMessage" id="expDateTag" style="display: none"></span>
									<input class="renewMessage" id="expDate" style="display: none" value="${licExDateData.date}"/>
									<input class="renewMessage" id="dateUIPattern" style="display: none" value="${licExDateData.dateUIPattern}"/>
									<br/>
									<span class="renewMessage" > <spring:theme code="license.apply.licenseYear.warning"/> </span>
									<br/><br/>
									<span> <spring:theme code="license.apply.licenseYear.note"/> </span>
									<br/>
									<span> <spring:theme code="license.apply.licenseYear.example"/> </span>
							</div>
					</div>
				</div>
				<div class="contentModule-section contentModule-section_paddingSide">
					<!-- <span class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="text.account.followup.supportDocuments"/></span> -->
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline"><spring:theme code="text.account.followup.supportDocuments"/></span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
					<div class="row contentModule-headline-border">
						<div class="attachment-division-wrap">
							<c:choose>
								<c:when test="${reapply}">
									<c:forEach items="${resubmittedDocuments}" var="attachment" varStatus="counter">
										<c:set var="noExtensionFileName" value="${fn:replace(attachment.fullFileName,'.pdf' ,'')}"/>
										<div class="formInputFile active">
											<div class="form-group">
												<input id="fileId_${counter.index}" name="multipartFile[${counter.index}]" class="form-control js-inputFile" type="file" accept="application/pdf,image/jpeg" value="">
												<input id="fileTextId_${counter.index}" name="fileText[${counter.index}]" class="form-control" type="text" value="" placeholder="${attachment.fullFileName}" readonly tabindex="-1">
												<label class="control-label control-label_mandatory" for="fileTextId_${counter.index}">${noExtensionFileName}</label>
												<div class="form-icon form-icon_browse">
													<icon:upload/>
												</div>
												<div class="form-icon form-icon_reset js-inputFile-reset">
													<icon:cross/>
												</div>
												<div class="help-block"></div>
												<input type="hidden" name="dockeyID[${counter.index}]" value="${attachment.keyID}" />
												<input name="draftFiles[${counter.index}]" type="hidden" class="js-mock-input">
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${attachments}" var="attachment" varStatus="counter">
										<div class="formInputFile">
											<div class="form-group">
												<input class="form-control js-inputFile" type="file" accept="application/pdf,image/jpeg" value="" name="multipartFile[${counter.index}]">
												<input id="labelPlaceholder1" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
												<label class="control-label control-label_mandatory" for="labelPlaceholder1">${attachment.description}</label>
												<div class="form-icon form-icon_browse">
													<icon:upload/>
												</div>
												<div class="form-icon form-icon_reset js-inputFile-reset">
													<icon:cross/>
												</div>
												<div class="help-block"></div>
												<input type="hidden" name="dockeyID[${counter.index}]" value="${attachment.dockeyID}" />
												<input name="draftFiles[${counter.index}]" type="hidden" class="js-mock-input">
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-condition-spl-notes"><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
				</div>
				<!-- </div> -->
			</div>

			<div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions action_res_view-renew">
				<c:url var="back" value="/my-sagia/license/renew"/>
				<div class="formCheckBox formCheckBox_belowPanel w-100">
					<div class="form-group">
						<formElement:termsAndConditionsCheckbox event="LICENSE_SERVICES" id="termsAndConditions" path="termsAndConditionsChecked"/>
					</div>
				</div>
				<a class="btn btn_slim btn_outline" href="${back}"><spring:theme code="general.cancel"/></a>
				<input type="submit" class="btn" value="<spring:theme code="general.submit"/>" id="submit"/>
			</div>
			<input type="hidden" id="serviceId"/>
		</form:form>
	</div>
</div>

<!-- Save Modal -->
<!--Modal: Use (data-toggle="modal" data-target="#requestSubmitted") on link or button to call it-->
<div class="modal fade" id="requestSubmitted" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title"><spring:theme code="general.requestsubmitted"/></div>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage">
					<icon:modal02/>
				</div>
				<div class="modal-description">
					<spring:theme code="specialservices.wewillreviewmessage"/>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn_slim" data-dismiss="modal"><spring:theme code="general.gotomydashboard"/></button>
			</div>
			<div class="modal-secondaryContent">
				<div class="modal-headline"><spring:theme code="general.howwasyourexperience"/></div>
				<div class="ratingModule">
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star active"><icon:rating-star/></div>
					<div class="ratingModule-star"><icon:rating-star-empty/></div>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="imageUploadTemplateWrapper" style="display: none;">
	<div class="col-md-6">
		<div class="formInputFile">
			<div class="form-group">
				<input class="form-control js-inputFile" type="file" accept="image/jpeg" value="">
				<input class="form-control" type="text" value="" placeholder="" readonly tabindex="-1">
				<label class="control-label"><spring:theme code="general.picture"/></label>
				<div class="form-icon form-icon_browse">
					<icon:upload/>
				</div>
				<div class="form-icon form-icon_reset js-inputFile-reset">
					<icon:cross/>
				</div>
			</div>
		</div>
	</div>
</div>



