<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="shareholderType" required="false" type="java.lang.String"%>

<div class="contentModule-section">
	<div class="contentModule-headline contentModule-headline_smallMargin w-50">
		<spring:theme code="license.apply.shareholder.delegate" />
	</div>
  <hr class="hr"/>
	<div class="formRadioBox-wrapper" id="delegateSectionQuestion">
	<div class="row"><div class="col-md-12 justify-content-center"><a class="btn btn_link js-tip delegate-entity" data-container="body" data-tip-id="delegateToolTip" data-tip-class="delegateToolTip" data-trigger="click" ><spring:theme code="text.account.profile.license.shareholders.tooltip.heading" /></a>
        <div class="tooltip_content" id="delegateToolTip" >
            <h2><span><spring:theme code="text.account.profile.license.shareholders.tooltip.heading" /></span></h2>
            <p style="margin: 10px">
					<spring:theme code="text.account.profile.license.shareholders.tooltip.body" />
             </p>
         </div></div></div>
		<div class="row">
			<div class="col-md-6">
				<span><spring:theme code="text.account.profile.license.shareholders.que.wantdelegate" /></span>
			</div>
			<div class="col-md-6">
				<div class="formRadioBox">
					<div class="form-group">
						<div class="form-item">
							<input type="radio" name="hasDelegateInfo" id="${shareholderType.concat('DelegateYES')}" class="form-control delegateYES" value="true"/>
							<label for="${shareholderType.concat('DelegateYES')}" class="btn-ctrl btn-outline btn_bold control-label " id="${shareholderType.concat('DelegateYESLable')}">
								<spring:theme code="text.account.profile.license.shareholders.delegate.yes" />
							</label>
						</div>
						<div class="form-item">
							<input type="radio" name="hasDelegateInfo" id="${shareholderType.concat('DelegateNO')}"
								value="false" class="form-control delegateNO" /> <label for="${shareholderType.concat('DelegateNO')}"
								id="${shareholderType.concat('DelegateNOLable')}" class="btn-ctrl btn-bg btn_bold control-label" ><spring:theme
									code="text.account.profile.license.shareholders.delegate.no" /></label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="delegateSection" style="display:none">
		<div class="formRadioBox-wrapper" id="showDelegateQuestion">
			<div class="row">
				<div class="col-md-6">
					<span><spring:theme
							code="text.account.profile.license.shareholders.isDelegate" /></span>
				</div>
				<div class="col-md-6">
					<div class="formRadioBox">
						<div class="form-group">
							<div class="form-item">
								<input type="radio" name="hasDelegate" id="hasDelegateYES"
									value="true" class="form-control" /> <label for="hasDelegateYES"
									id="hasDelegateYESLabel" class="control-label"><spring:theme
										code="text.account.profile.license.shareholders.hasDelegate.yes" /></label>
							</div>
							<div class="form-item">
								<input type="radio" name="hasDelegate" id="hasDelegateNO"
									value="false" class="form-control" /> <label for="hasDelegateNO"
									id="hasDelegateNOLabel" class="control-label"><spring:theme
										code="text.account.profile.license.shareholders.hasDelegate.no" /></label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="delegate">
				<div class="row" id="delegateDetails" style="display: none;">
					<div class="col-md-6">
						<div class="formSelectBox">
							<div class="form-group">
								<select id="idType" name="delegate.idType" class="js-select2-oneColumn form-control">
								</select>
								<label class="control-label control-label_mandatory" for="idType"><spring:theme
										code="license.apply.shareholder.idType" /></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="formInputBox">
							<div class="form-group">
								<input id="idNumber" name="delegate.idNumber" class="form-control"
									placeholder="." value="" type="text" /> <label
									class="control-label control-label_mandatory" for="idNumber"><spring:theme
										code="license.apply.shareholder.idNumber" /></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="formInputBox formInputBox_group ">
							<div class="form-group">
								<input id="delegateDateofBirth" name="delegate.dateofBirth"
									class="form-control " placeholder="." value=""
									type="text" /> <label
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
					<div class="col-md-6" id="nicVerifyBtnSection" >
						<a style="margin-top: 15px" class="btn" id="verifyDetailsShow" data-nic-verified="false"><spring:theme
								code="license.apply.shareholder.verify" /></a>
					</div>
				</div>
				<div id="verifyDelegateDetails" style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div style="margin-top: 20px;font-size: 18px;" class="contentModule-headline contentModule-headline_smallMargin">
							<spring:theme code="license.apply.shareholder.delegateDetails.title" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="formInputBox">
							<div class="form-group">
								<input id="delegateFirstNameArabic" name="delegate.firstNameArabic"
									class="form-control" placeholder="." value="" type="text" /> <label
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
								<input id="delegateLastNameArabic" name="delegate.lastNameArabic"
									class="form-control" placeholder="." value="" type="text" /> <label
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
								<input id="delegateFullNameEnglish" name="delegate.fullNameEnglish"
									class="form-control" placeholder="." value="" type="text" /> <label
									class="control-label control-label_mandatory"
									for="delegateFullNameEnglish"><spring:theme
										code="general.fullname.english" /></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="formSelectBox">
							<div class="form-group">
								<select id="delegateGender" name="delegate.gender" class="js-select2-oneColumn form-control">
								</select>
				                <label class="control-label control-label_mandatory" for="delegateGender"><spring:theme code="license.apply.shareholder.delegateDetails.gender"/></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="formInputBox formInputBox_group ">
							<div class="form-group">
								<input id="delegateIssueDate" name="delegate.issueDate"
									class="form-control" placeholder="." value=""
									type="text" />
									<label
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
						<div class="formInputBox formInputBox_group ">
							<div class="form-group">
								<input id="delegateExpiryDate" name="delegate.expiryDate"
									class="form-control " placeholder="." value=""
									type="text" /> <label
									class="control-label control-label_mandatory"
									for="delegateExpiryDate"><spring:theme
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
				                 <select id="delegateCountry" name="delegate.country" class="js-select2-oneColumn form-control"></select>
				                 <label class="control-label control-label_mandatory" for="delegateCountry"><spring:theme code="general.country"/></label>
				             </div>
				             <div class="help-block"></div>
						</div>
			       </div>
					<div class="col-md-6" id="delegateNationalityDiv">
						<div class="formSelectBox">
							<div class="form-group">
								<select id="delegateNationality" name="delegate.nationality" class="js-select2-oneColumn form-control"></select>
								<label class="control-label control-label_mandatory" for="delegateNationality"><spring:theme code="license.apply.shareholder.nationality" /></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6" id="postalCodeDiv"  style="display: none;">
			            <div class="formInputBox">
			                <div class="form-group">
			                    <input id="delegatePostalCode" name="delegate.postalCode" class="form-control" placeholder="." value="" type="text"/>
			                    <label class="control-label control-label_mandatory" for="delegatePostalCode"><spring:theme code="general.postalcode"/></label>
			                </div>
			                <div class="help-block"></div>
			            </div>
			        </div>
			        <div class="col-md-6" id="poBoxDiv"  style="display: none;">
			            <div class="formInputBox">
			                <div class="form-group">
			                    <input id="delegatePOBox" name="delegate.poBox" class="form-control" placeholder="." value="" type="text"/>
			                    <label class="control-label control-label_mandatory" for="delegatePOBox"><spring:theme code="general.pobox"/></label>
			                </div>
			                <div class="help-block"></div>
			            </div>
			        </div>
					<div class="col-md-6">
						<div class="formInputBox-split">
							<div class="formInputBox">
								<div class="form-group">
									<input id="delegateCountryCodeForTelephone" name="delegate.countryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="" />
									<label class="control-label control-label_mandatory" for="delegateCountryCodeForTelephone"><spring:theme code="general.country.code" /></label>
						</div>
						<div class="help-block"></div>
					</div>
					<div class="formInputBox formInputBox_big">
						<div class="form-group">
							<input id="delegateTelephone" name="delegate.telephone" class="form-control form-control_labeled" placeholder="." type="text" value="" />
							<label class="control-label control-label_mandatory" for="delegateTelephone"><spring:theme code="general.telephone" /></label>
								</div>
								<div class="help-block"></div>
							</div>
						</div>
					</div>
				   <div class="col-md-6">
			            <div class="formInputBox-split">
			                 <div class="formInputBox">
			                     <div class="form-group">
			                     	 <input id="delegateCountryCodeForMobile" name="delegate.countryCodeForMobile" class="form-control form-control_preNumber" placeholder="." type="text" value="" />
			                         <label class="control-label control-label_mandatory" for="delegateCountryCodeForMobile"><spring:theme code="general.country.code"/></label>
			                 	</div>
			             	<div class="help-block"></div>
			             </div>
			             	<div class="formInputBox formInputBox_big">
			                 	<div class="form-group">
			                     	<input id="delegateMobile" name="delegate.mobile" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
			                     	<label class="control-label control-label_mandatory" for="delegateMobile"><spring:theme code="general.mobilenumber"/></label>
			                 	</div>
			                 <div class="help-block"></div>
			             	</div>
			         	</div>
				    </div>
					<div class="col-md-6">
			           <div class="formInputBox">
			               <div class="form-group">
			                   <input id="delegateEmail" name="delegate.email" class="form-control" placeholder="." value="" type="text"/>
			                   <label class="control-label control-label_mandatory" for="delegateEmail"><spring:theme code="general.license.email"/></label>
			               </div>
			               <div class="help-block"></div>
			           </div>
			        </div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div style="margin-top: 20px;font-size: 18px;" class="contentModule-headline contentModule-headline_smallMargin">
							<spring:theme code="licence.apply.attachments" />
						</div>
					</div>
				</div>
				<div class="row">
			          <div class="col-md-6">
			              <div class="formInputFile">
			                  <div class="form-group">
			                      <input id="authorizationLetterFile" name="" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
			                      <input id="authorizationLetterFileName" name="delegate.authorizationLetterFile" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
			                      <label class="control-label control-label_mandatory" for="authorizationLetterName"><spring:theme code="license.apply.shareholder.authorizationLetter"/></label>
			                      <div class="form-icon form-icon_browse"><icon:upload/></div>
			                      <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
			                  </div>
			                  <div class="help-block"></div>
			              </div>
			          </div>
			          <div class="col-md-6" style="display: none;" id="idCopyFileDiv">
			              <div class="formInputFile">
			                  <div class="form-group">
			                      <input id="saudiIdCopy" name="" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
			                      <input id="idCopyFileName" name="delegate.saudiIdCopy" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
			                      <label class="control-label control-label_mandatory saudiIdCopy" for="idCopyFileName"><spring:theme code="license.apply.shareholder.idCopyFile"/> </label>
			                      <div class="form-icon form-icon_browse"><icon:upload/></div>
			                      <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
			                  </div>
			                  <div class="help-block"></div>
			              	</div>
			          	</div>
			    </div>
			</div>
		</div>
	</div>
</div>
