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

<div id="contactPersonQM1">
    <div class="contentModule-section">
    
        <div class="row">
            <div class="col-md-6">
            
               <formElement:formSelectBoxCustom idKey="qm1Contacts"
                              labelKey="licenseApply.contactPerson.qm1.contact" 
                              path="contacts" labelCSS="control-label control-label_mandatory"
                              selectCSSClass="js-select2-oneColumn form-control"
                              selectedDataValue="${contactPersonsData.contacts}"
                              items="${contacts}" itemValue="contact" 
                              itemLabel="contactText" mandatory="true"/>     
            </div>
            
            
            
            
            
            
         </div>
         
          <div class="row contentModule-section" id="delegateDetails" >
					
					<div class="col-md-6">
					<input id="savedContactIdType" type="hidden" value="${contactPersonsData.idType}">
				        <formElement:formSelectBoxCustom idKey="idType"
					    labelKey="license.apply.shareholder.idType"
				    	path="idType"
					        selectCSSClass="js-select2-oneColumn validate__delegate-mandatory"
					      labelCSS="control-label_mandatory"
					      selectedDataValue="${contactPersonsData.idType}" />
			         </div>
			
					<div class="col-md-6" id="idNumberection">
						<div class="formInputBox">
							<div class="form-group">
								<input id="idNumber" name="delegate.idNumber" class="form-control"
									placeholder="." value="${contactPersonsData.passportNumber}" type="text" /> <label
									class="control-label" for="idNumber"><spring:theme
										code="license.apply.shareholder.idNumber" /></label>
							</div>
							<div class="help-block"></div>
						</div>
					</div>
					<div class="col-md-6" id="delegateDateofBirthSection">
						<div class="formInputBox formInputBox_group ">
							<div class="form-group">
								<input id="delegateDateofBirth" name="delegate.dateofBirth"
									class="form-control " placeholder="." value="${contactPersonsData.dateOfBirth}"
									type="text" /> <label
									class="control-label"
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
				
	<div id="contactDetails" >		    
        <div class="row">
            <div class="col-md-6">
                <div class="formRadioBox" id="qm1Title">
                    <div class="form-group">
                        <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licenseApply.contactPerson.qm1.title"/></div>
                        <div class="form-item">
                            <input id="mrTitle" name="title" class="form-control" type="radio" value="Mr" ${not empty contactPersonsData.title && contactPersonsData.title == 'Mr' ? 'checked' : ''}/>
                            <label for="mrTitle" class="control-label"><spring:theme code="licenseApply.contactPerson.qm1.mr"/></label>
                        </div>

                        <div class="form-item">
                            <input id="mrsTitle" name="title" class="form-control" type="radio" value="Mrs" ${not empty contactPersonsData.title && contactPersonsData.title == 'Mrs' ? 'checked' : ''}/>
                            <label for="mrsTitle" class="control-label"><spring:theme code="licenseApply.contactPerson.qm1.mrs"/></label>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
              <formElement:formInputBoxCustom idKey="qm1FirstName" maxlength="40"
                                            labelKey="licenseApply.contactPerson.qm1.firstName" path="firstName"
                                            labelCSS="control-label control-label_mandatory"
                                            inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" />
            </div>

            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1LastName" maxlength="40"
                                    labelKey="licenseApply.contactPerson.qm1.lastName" path="lastName"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" />
            </div>

            <div class="col-md-6">
               <formElement:formSelectBoxCustom idKey="qm1Role"
                              labelKey="licenseApply.contactPerson.qm1.role"
                              path="role" labelCSS="control-label control-label_mandatory"
                              selectCSSClass="js-select2-oneColumn form-control"
                              selectedDataValue="${contactPersonsData.role}"
                              items="${roles}" itemValue="role" 
                              itemLabel="roleText" mandatory="true"/>
            </div>

            <div class="col-md-6">
               <formElement:formSelectBoxCustom idKey="qm1Education"
                              labelKey="licenseApply.contactPerson.qm1.education"
                              path="education" labelCSS="control-label control-label_mandatory"
                              selectCSSClass="js-select2-oneColumn form-control"
                              selectedDataValue="${contactPersonsData.education}"
                              items="${educations}" itemValue="educationText"
                              itemLabel="educationText" mandatory="true"/>
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <c:set var="dateOfBirthErrors">
                        <form:errors path="dateOfBirth"/>
                    </c:set>
                    <div class="form-group ${not empty dateOfBirthErrors ? 'has-error' : ''}">
                        <input id="qm1DateOfBirth" name="dateOfBirth" class="form-control js-form-control_date" placeholder="." value="${contactPersonsData.dateOfBirth}" data-date="${contactPersonsData.dateOfBirth}" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1DateOfBirth"><spring:theme code="licenseApply.contactPerson.qm1.dateOfBirth"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                        </div>
                    </div>
                    <div class="help-block">${dateOfBirthErrors}</div>
                </div>
            </div>

            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1PassportNumber" maxlength="60"  
                                    labelKey="license.apply.shareholder.idNumber" path="passportNumber"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__no-special-chars" mandatory="true" />
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <c:set var="passportIssueDateErrors">
                        <form:errors path="passportIssueDate"/>
                    </c:set>
                    <div class="form-group ${not empty passportIssueDateErrors ? 'has-error' : ''}">                                                
                        <input id="qm1PassportIssueDate" name="passportIssueDate" class="form-control js-form-control_date" placeholder="." value="${contactPersonsData.passportIssueDate}" data-date="${contactPersonsData.passportIssueDate}" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1PassportIssueDate"><spring:theme code="license.apply.shareholder.issueDate"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                        </div>
                    </div>
                    <div class="help-block">${passportIssueDateErrors}</div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <c:set var="passportExpiryDateErrors">
                        <form:errors path="passportExpiryDate"/>
                    </c:set>
                    <div class="form-group ${not empty passportExpiryDateErrors ? 'has-error' : ''}">
                        <input id="qm1PassportExpiryDate" name="passportExpiryDate" class="form-control js-form-control_date" placeholder="." value="${contactPersonsData.passportExpiryDate}" data-date="${contactPersonsData.passportExpiryDate}" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1PassportExpiryDate"><spring:theme code="license.apply.shareholder.expiryDate"/></label>
                        <div class="formInputBox-append"><span class="formInputBox-text"><icon:calendar-gray/></span></div>
                    </div>
                    <div class="help-block">${passportExpiryDateErrors}</div>
                </div>
            </div>
        </div>
        
        </div> 
    </div>

    <div id="contactSection" class="contentModule-section">
        <div class="licensecontactperson_bottomboarder">
            <div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.qm1.contactInformation"/></div>
        </div>
		<div class="row">
            <div class="col-md-6">
            
               <formElement:formSelectBoxCustom idKey="qm1Country"
                              labelKey="licenseApply.contactPerson.qm1.country"
                              path="country" labelCSS="control-label control-label_mandatory"
                              selectCSSClass="js-select2-oneColumn form-control"
                              selectedDataValue="${contactPersonsData.country}"/>
                <%-- <div class="formSelectBox">
                    <div class="form-group">
                        <select id="qm1Country" name="country" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="qm1Country"><spring:theme code="licenseApply.contactPerson.qm1.country"/></label>
                    </div>
                    <div class="help-block"></div>
                </div> --%>
            </div>

            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1City" maxlength="40"
                                    labelKey="licenseApply.contactPerson.qm1.city" path="city" 
                                    labelCSS="control-label control-label_mandatory" 
                                    inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" />
            </div>
            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1Address"
                                    labelKey="licenseApply.contactPerson.qm1.address" path="address"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__no-special-chars-with-arabic" mandatory="true" maxlength="60" />
            </div>

            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1POBox"
                                    labelKey="licenseApply.contactPerson.qm1.poBox" path="poBox"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__numbers-only" mandatory="true" maxlength="5" />
            </div>

            <div class="col-md-6">
                 <formElement:formInputBoxCustom idKey="qm1PostalCode"
                                    labelKey="licenseApply.contactPerson.qm1.postalCode" path="postalCode"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__numbers-only" mandatory="true" maxlength="5" />
            </div>

            <div class="col-md-6">
                <div class="formInputBox-split">

                        <formElement:formInputBoxCustom idKey="qm1CountryCodeForTelephone" labelKey="general.country.code"
                                                  path="countryCodeTelephone" labelCSS="control-label_mandatory"
                                                  inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
                                                  maxlength="5" dataValue="${contactPersonsData.countryCodeTelephone}"/>

                            <%--
                            <div class="formInputBox">
                            <div class="form-group">
                            <input id="qm1CountryCodeForTelephone" name="countryCodeTelephone" inputCSS="validate__numbers-only"
                                   class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>

                            <label class="control-label" for="qm1CountryCodeForTelephone"><spring:theme code="licenseApply.contactPerson.qm1.countryCode"/></label>
                            </div></div>--%>

                    
                 	<formElement:formInputBoxCustom idKey="qm1Telephone" inputBoxCSS="formInputBox_big"
                                    labelKey="licenseApply.contactPerson.qm1.telephone" path="telephoneNumber"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__numbers-only" mandatory="true" maxlength="30" />
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox-split">

                <%--
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="qm1CountryCodeForMobileNumber" name="countryCodeMobile" inputCSS="validate__numbers-only" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                            <label class="control-label" for="qm1CountryCodeForMobileNumber"><spring:theme code="licenseApply.contactPerson.qm1.countryCode"/></label>
                        </div>
                    </div>
                --%>

                    <formElement:formInputBoxCustom idKey="qm1CountryCodeForMobileNumber" labelKey="general.country.code"
                                              path="countryCodeMobile" labelCSS="control-label_mandatory"
                                              inputCSS="form-control_preNumber validate__mandatory validate__numbers-only"
                                              maxlength="5" dataValue="${contactPersonsData.countryCodeMobile}"/>

                 	<formElement:formInputBoxCustom idKey="qm1MobileNumber" inputBoxCSS="formInputBox_big"
                                    labelKey="licenseApply.contactPerson.qm1.mobileNumber" path="mobileNumber"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__numbers-only" mandatory="true" maxlength="30" />
                </div>
            </div>
            
            <div class="col-md-6">
            
                 	
                 <formElement:formInputBoxCustom idKey="qm1Email"
                                    labelKey="licenseApply.contactPerson.qm1.email" path="email"
                                    labelCSS="control-label control-label_mandatory"
                                    inputCSS="text validate__email" mandatory="true" />
            </div>
        </div>
    </div>
</div>
