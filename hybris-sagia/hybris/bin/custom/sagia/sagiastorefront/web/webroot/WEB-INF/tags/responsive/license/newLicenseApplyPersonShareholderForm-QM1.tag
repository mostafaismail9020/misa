<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license"%>

<c:set var="editCss">
    ${(saveStatus eq false || editOrError eq true) &&
        (sagiaApplyPersonShareholderForm.delegateInfo.delegate eq true ||
        sagiaApplyPersonShareholderForm.delegateInfo.delegateYourself eq false) ? "edit-initial" : ""}
</c:set>

<form:form action="/" id="personShareholderForm"
	modelAttribute="sagiaApplyPersonShareholderForm" method="POST"
	cssClass="${editCss}">
	<input type="hidden" name="code"
		value="${sagiaApplyPersonShareholderForm.code}">
	<div class="contentModule-section">
		<div class="contentModule-headline">
			<spring:theme code="licence.apply.shareholder.details" />
		</div>
<hr class="hr">
		<div class="contentModule-section row mt-5" id="shareholderIdTypeSection">


			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="shareholderIdType"
					labelKey="license.apply.shareholder.idType"
					path="shareHolderIdType"
					selectCSSClass="js-select2-oneColumn validate__delegate-mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.shareHolderIdType}" />
			</div>

			<div class="col-md-6" id="shareholderIdNumberSection">
				<div class="formInputBox">
					<div class="form-group">
						<input id="shareholderIdNumber" name="shareholder.idNumber"
							class="form-control" placeholder="."
							value="${sagiaApplyPersonShareholderForm.passportNumber}"
							type="text" /> <label
							class="control-label control-label_mandatory"
							for="shareholderIdNumber"><spring:theme
								code="license.apply.shareholder.idNumber" /></label>
					</div>
					<div class="help-block"></div>
				</div>
			</div>
			<div class="col-md-6" id="shareholderDateofBirthSection">
						<div class="formInputBox formInputBox_group ">
							<div class="form-group">
								<input id="shareholderDateofBirth" name="delegate.dateofBirth"
									class="form-control " placeholder="." value=""
									type="text" /> <label
									class="control-label control-label_mandatory"
									for="shareholderDateofBirth"><spring:theme
										code="license.apply.shareholder.dateOfBirth" /></label>
								<div class="formInputBox-append">
									<span class="formInputBox-text"><icon:calendar-gray /></span>
								</div>
							</div>
							<div class="help-block"></div>
						</div>
		     </div>


			<div class="col-md-6 mt-2" id="nicShareholderVerifyBtnSection">
				<a href="#" class="btn btn-bg btn-normal btn_bold btn-ctrl w-50 mt-4"
					id="verifyShareholderDetailsShow" data-nic-verified="false"><spring:theme
						code="license.apply.shareholder.verify" /></a>
			</div>


		</div>
		<div class="row" style="display: none" id="dataSection">
			<div class="col-md-6">
				<div id="shareholderTitle" class="formRadioBox">
					<div class="form-group">
						<div class="formRadioBox-label">
							<spring:theme code="general.license.title" />
						</div>
						<div class="form-item mt-2 pr-0 mb-4">
							<input id="personTitle" name="shareHolderTitle"
								class="form-control" type="radio" value="Mr."
								${sagiaApplyPersonShareholderForm.shareHolderTitle eq 'Mr.' ? 'checked="checked"' : ''} />
							<label for="personTitle" class="btn-ctrl btn_bold control-label"><spring:theme
									code="general.mr" /></label>
						</div>

						<div class="form-item mt-2 mb-4 pl-0">
							<input id="organizationTitle" name="shareHolderTitle"
								class="form-control" type="radio" value="Mrs."
								${sagiaApplyPersonShareholderForm.shareHolderTitle eq 'Mrs.' ? 'checked="checked"' : ''} />
							<label for="organizationTitle" class="btn-ctrl btn_bold control-label"><spring:theme
									code="general.mrs" /></label>
						</div>
					</div>
					<div class="help-block"></div>
				</div>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="academicTitle"
					labelKey="general.academic.title" path="academicTitle"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.academicTitle}"
					labelCSS="control-label_mandatory" />
				<%--                <div class="formSelectBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <select id="academicTitle" name="academicTitle" class="js-select2-oneColumn form-control"></select>--%>
				<%--                        <label class="control-label control-label_mandatory" for="academicTitle"><spring:theme code="general.academic.title"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="firstNameArabic"
					labelKey="general.firstname.arabic" path="firstNameArabic"
					labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__arabic-only" maxlength="40" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="firstNameArabic" name="firstNameArabic" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="firstNameArabic"><spring:theme code="general.firstname.arabic"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="lastNameArabic"
					labelKey="general.lastname.arabic" path="lastNameArabic"
					labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__arabic-only" maxlength="40" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="lastNameArabic" name="lastNameArabic" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="lastNameArabic"><spring:theme code="general.lastname.arabic"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="fullNameEnglish"
					labelKey="general.fullname.english" path="fullName"
					labelCSS="control-label_mandatory" maxlength="100"
					inputCSS="validate__mandatory validate__characters-only" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="fullNameEnglish" name="fullName" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="fullNameEnglish"><spring:theme code="general.fullname.english"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBoxCustom idKey="personSharesPercentage"
					labelKey="license.apply.review.shares.percentage"
					path="sharesPercentage" labelCSS="control-label_mandatory"
					inputBoxAppendKey="%" inputBoxCSS="formInputBox_group"
					inputCSS="validate__mandatory validate__float-numbers-only-sharespercentage" />
				<%--                <div class="formInputBox formInputBox_group">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="personSharesPercentage" name="sharesPercentage" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="personSharesPercentage"><spring:theme code="license.apply.review.shares.percentage"/></label>--%>
				<%--                        <div class="formInputBox-append">--%>
				<%--                            <span class="formInputBox-text">%</span>--%>
				<%--                        </div>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<c:set var="calendar">
					<icon:calendar-gray />
				</c:set>
				<formElement:formInputBoxCustom idKey="dateOfBirth"
					labelKey="general.dateofbirth" path="dateOfBirth"
					labelCSS="control-label_mandatory" inputBoxCode="${calendar}"
					inputBoxCSS="formInputBox_group"
					inputCSS="js-form-control_date validate__mandatory" />

			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="passportNumber"
					labelKey="license.apply.shareholder.idNumber" maxlength="60"
					path="passportNumber" labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__no-special-chars" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="passportNumber" name="passportNumber" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="passportNumber"><spring:theme code="general.passport.number"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBoxCustom idKey="passportIssueDate"
					labelKey="license.apply.shareholder.issueDate"
					path="passportIssueDate" labelCSS="control-label_mandatory"
					inputBoxCode="${calendar}" inputBoxCSS="formInputBox_group"
					inputCSS="js-form-control_date validate__mandatory" />

			</div>

			<div class="col-md-6">
				<formElement:formInputBoxCustom idKey="passportExpiryDate"
					labelKey="license.apply.shareholder.expiryDate"
					path="passportExpiryDate" labelCSS="control-label_mandatory"
					inputBoxCode="${calendar}" inputBoxCSS="formInputBox_group"
					inputCSS="js-form-control_date validate__mandatory" />
				<%--                <div class="formInputBox formInputBox_group ">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="passportExpiryDate" name="passportExpiryDate" class="form-control js-form-control_date" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="passportExpiryDate"><spring:theme code="general.passport.expirydate"/></label>--%>
				<%--                        <div class="formInputBox-append">--%>
				<%--                            <span class="formInputBox-text"><icon:calendar-gray/></span>--%>
				<%--                        </div>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="currentNationality"
					labelKey="general.nationality.current" path="currentNationality"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.currentNationality}" />
				<%--                <div class="formSelectBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <select id="currentNationality" name="currentNationality" class="js-select2-oneColumn form-control"></select>--%>
				<%--                        <label class="control-label control-label_mandatory" for="currentNationality"><spring:theme code="general.nationality.current"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="previousNationality"
					labelKey="general.nationality.previous" path="previousNationality"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.previousNationality}" />
				<%--                <div class="formSelectBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <select id="previousNationality" name="previousNationality" class="js-select2-oneColumn form-control"></select>--%>
				<%--                        <label class="control-label control-label_mandatory" for="previousNationality"><spring:theme code="general.nationality.previous"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="country"
					labelKey="general.country" path="country"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.country}" />
				<%--                <div class="formSelectBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <select id="country" name="country" class="js-select2-oneColumn form-control"></select>--%>
				<%--                        <label class="control-label control-label_mandatory" for="country"><spring:theme code="general.country"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="premiumResident"
					labelKey="license.premiumstatus" path="premiumResident"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.premiumResident}" />
				<%--                <div class="formSelectBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <select id="premiumResident" name="premiumResident" class="js-select2-oneColumn form-control"></select>--%>
				<%--                        <label class="control-label control-label_mandatory" for="premiumResident"><spring:theme code="license.premiumstatus"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formSelectBoxCustom idKey="professionalLicense"
					labelKey="license.professionalLicense" path="professionalLicense"
					selectCSSClass="js-select2-oneColumn validate__mandatory"
					labelCSS="control-label_mandatory"
					selectedDataValue="${sagiaApplyPersonShareholderForm.professionalLicense}" />
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="city" labelKey="general.city"
					path="city" maxlength="40" labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__no-special-chars-with-arabic" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="city" name="city" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="city"><spring:theme code="general.city"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="poBox" labelKey="general.pobox"
					path="poBox" labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__numbers-only" maxlength="5" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="poBox" name="poBox" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="poBox"><spring:theme code="general.pobox"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="postalCode"
					labelKey="general.postalcode" path="postalCode"
					labelCSS="control-label_mandatory"
					inputCSS="validate__mandatory validate__numbers-only" maxlength="5" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="postalCode" name="postalCode" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="postalCode"><spring:theme code="general.postalcode"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>


			<div class="col-md-6">
				<div class="formInputBox-split">
					<formElement:formInputBoxCustom idKey="countryCodeForTelephone"
						labelKey="general.country.code" path="countryCodeTelephone"
						labelCSS="control-label_mandatory"
						inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
						maxlength="5"
						dataValue="${sagiaApplyPersonShareholderForm.countryCodeTelephone}" />
					<%--                    <div class="formInputBox">--%>
					<%--                        <div class="form-group">--%>
					<%--                            <input id="countryCodeForTelephone" name="countryCodeTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
					<%--                            <label class="control-label control-label_mandatory" for="countryCodeForTelephone"><spring:theme code="general.country.code"/></label>--%>
					<%--                        </div>--%>
					<%--                        <div class="help-block"></div>--%>
					<%--                    </div>--%>
					<formElement:formInputBox idKey="telephone"
						labelKey="general.telephone" path="telephoneNumber"
						labelCSS="control-label_mandatory"
						inputCSS="form-control_labeled validate__mandatory validate__numbers-only"
						inputBoxCSS="formInputBox_big" maxlength="30" />
					<%--                    <div class="formInputBox formInputBox_big">--%>
					<%--                        <div class="form-group">--%>
					<%--                            <input id="telephone" name="telephoneNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
					<%--                            <label class="control-label control-label_mandatory" for="telephone"><spring:theme code="general.telephone"/></label>--%>
					<%--                        </div>--%>
					<%--                        <div class="help-block"></div>--%>
					<%--                    </div>--%>
				</div>
			</div>

			<div class="col-md-6">
				<div class="formInputBox-split">
					<formElement:formInputBoxCustom idKey="countryCodeForMobile"
						labelKey="general.country.code" path="countryCodeMobile"
						labelCSS="control-label_mandatory"
						inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
						maxlength="5"
						dataValue="${sagiaApplyPersonShareholderForm.countryCodeMobile}" />
					<%--                    <div class="formInputBox">--%>
					<%--                        <div class="form-group">--%>
					<%--                            <input id="countryCodeForMobile" name="countryCodeMobile" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>--%>
					<%--                            <label class="control-label control-label_mandatory" for="countryCodeForMobile"><spring:theme code="general.country.code"/></label>--%>
					<%--                        </div>--%>
					<%--                        <div class="help-block"></div>--%>
					<%--                    </div>--%>
					<formElement:formInputBox idKey="mobile"
						labelKey="general.mobilenumber" path="mobileNumber"
						labelCSS="control-label_mandatory"
						inputCSS="form-control_labeled validate__mandatory validate__numbers-only"
						inputBoxCSS="formInputBox_big" maxlength="30" />
					<%--                    <div class="formInputBox formInputBox_big">--%>
					<%--                        <div class="form-group">--%>
					<%--                            <input id="mobile" name="mobileNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>--%>
					<%--                            <label class="control-label control-label_mandatory" for="mobile"><spring:theme code="general.mobilenumber"/></label>--%>
					<%--                        </div>--%>
					<%--                        <div class="help-block"></div>--%>
					<%--                    </div>--%>
				</div>
			</div>

			<div class="col-md-6">
				<formElement:formInputBox idKey="email" labelKey="general.email"
					path="email" labelCSS="control-label_mandatory"
					inputCSS="validate__email" />
				<%--                <div class="formInputBox">--%>
				<%--                    <div class="form-group">--%>
				<%--                        <input id="email" name="email" class="form-control" placeholder="." value="" type="text"/>--%>
				<%--                        <label class="control-label control-label_mandatory" for="email"><spring:theme code="general.email"/></label>--%>
				<%--                    </div>--%>
				<%--                    <div class="help-block"></div>--%>
				<%--                </div>--%>
			</div>
			<div class="col-md-6" id="mofaNumberSection" style="display: none">
				<%-- <formElement:formInputBox idKey="mofaNumber"
					labelKey="license.apply.shareholder.mofaNumber" maxlength="12"
					path="mofaNumber" inputCSS="validate__numbers-only" />
					<div class="form-group cr-validation help-block" id="mofaNumber-error"></div> --%>
				<c:set var="mofaNumberTooltip">
                    <span class="formInputBox-text formInputBox-text_tip js-tip"
                          style="position: relative;z-index: 1000;"
                          data-tip-title="MOFA-Request/ Tracking Number"
                          data-original-title="<spring:theme code="license.apply.shareholder.mofaNumberTooltip"/>" title=""><icon:tipInfo/>
                    </span>
                </c:set>
                <formElement:formInputBoxCustom idKey="mofaNumber" labelKey="license.apply.shareholder.mofaNumber"
                                                path="mofaNumber" inputBoxCode="${mofaNumberTooltip}" inputBoxCSS="formInputBox_group"
                                                inputCSS="validate__numbers-only" maxlength="13"/>
                <!-- <div class="help-block" id="mofaNumber-error"></div> -->
                <input type="hidden" id="isMofaVerified" name="mofaNumberVerified" value="${sagiaApplyPersonShareholderForm.mofaNumberVerified}">
                <%-- <input type="hidden" id="isMofaVerifiedPer" name="mofaNumberVerified" value="${sagiaApplyPersonShareholderForm.mofaNumberVerified}"> --%>
			</div>



		</div>


		<!--  Attachment  -->
		<div class="contentModule-section" id="attachmentSection"
			style="display: none">
			<div
				class="contentModule-headline contentModule-headline_smallMargin mw0" id="personAttachmentTitle">
				<spring:theme code="general.attachments" />
			</div>
			<hr class="hr">
			<div class="row">
				<div class="col-md-6" id="passportFileAttachment">
					<div
						class="formInputFile ${not empty sagiaApplyPersonShareholderForm.passportIdCopy ? "active" : ""}">
						<c:set var="passportIdCopyErrors">
							<form:errors path="passportIdCopy" />
						</c:set>
						<div
							class="form-group ${not empty passportIdCopyErrors ? 'has-error' : ''}">
							<input id="passportFile" name="passportFile"
								class="form-control js-inputFile validate__file" type="file"
								data-maxsize="5" accept="application/pdf" value="" /> <input
								id="passportFileName" name="passportFileName"
								class="form-control" type="text" value=""
								placeholder="${sagiaApplyPersonShareholderForm.passportIdCopy.fileName}"
								readonly tabindex="-1" /> <label
								class="control-label control-label_mandatory"
								for="passportFileName"><spring:theme
									code="licence.apply.passport" /></label>
							<div class="form-icon form-icon_browse">
								<icon:upload />
							</div>
							<div class="form-icon form-icon_reset js-inputFile-reset">
								<icon:cross />
							</div>
						</div>
						<div class="help-block">${passportIdCopyErrors}</div>
					</div>
				</div>
				<%-- <div class="col-md-6" id="otherFileAttachment">
					<div
						class="formInputFile ${not empty sagiaApplyPersonShareholderForm.other ? "active" : ""}">
						<c:set var="otherErrors">
							<form:errors path="other" />
						</c:set>
						<div
							class="form-group ${not empty otherErrors ? 'has-error' : ''}">
							<input id="otherFile" name="otherFile"
								class="form-control js-inputFile validate__file" type="file"
								data-maxsize="5" accept="application/pdf" value="" /> <input
								id="otherFileName" name="otherFileName" class="form-control"
								type="text" value=""
								placeholder="${sagiaApplyPersonShareholderForm.other.fileName}"
								readonly tabindex="-1" /> <label
								class="control-label control-label_mandatory"
								for="otherFileName"><spring:theme
									code="licence.apply.other" /></label>
							<div class="form-icon form-icon_browse">
								<icon:upload />
							</div>
							<div class="form-icon form-icon_reset js-inputFile-reset">
								<icon:cross />
							</div>
						</div>
						<div class="help-block">${otherErrors}</div>
					</div>
				</div> --%>

				<div class="col-md-6" id="professionalLicenseFileAttachment"
					style="display: none">
					<div
						class="formInputFile ${not empty sagiaApplyPersonShareholderForm.professionalLicenseCertificate ? "active" : ""}">
						<c:set var="professionalLicenseErrors">
							<form:errors path="professionalLicenseCertificate" />
						</c:set>
						<div
							class="form-group ${not empty professionalLicenseErrors ? 'has-error' : ''}">
							<input id="professionalLicenseFile"
								name="professionalLicenseFile" class="form-control js-inputFile"
								type="file" data-maxsize="5" accept="application/pdf" value="" />
							<input id="professionalLicenseFileName"
								name="professionalLicenseFileName" class="form-control"
								type="text" value=""
								placeholder="${sagiaApplyPersonShareholderForm.professionalLicenseCertificate.fileName}"
								readonly tabindex="-1" /> <label
								class="control-label control-label_mandatory"
								for="professionalLicenseFileName"><spring:theme
									code="licence.apply.commercial.professionalLicense" /> </label>
							<div class="form-icon form-icon_browse">
								<icon:upload />
							</div>
							<div class="form-icon form-icon_reset js-inputFile-reset">
								<icon:cross />
							</div>
						</div>
						<div class="help-block">${professionalLicenseErrors}</div>
					</div>
				</div>

			</div>
		</div>

		<div id="delegateDivSection" style="display: none">
			<license:newLicenseApplyShareholders-delegate
				shareholderType="Person"
				personData="${sagiaApplyPersonShareholderForm}" />
		</div>

	</div>

	<div
		class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
		<button type="button" class="cancelButton btn btn-outline btn-normal btn_bold w-25">
			<spring:theme code="general.cancel" />
		</button>
		<button type="button" class="addButton btn btn-bg btn-normal btn_bold w-25">
			<spring:theme code="licence.apply.savenewshareholder" />
		</button>
	</div>
</form:form>


