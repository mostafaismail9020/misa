<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<div id="contactPersonQM1">
    <div class="contentModule-section">
        <div class="row">
            <div class="col-md-6">
                <div class="formRadioBox ml-4 pl-2" id="qm1Title">
                    <div class="form-group">
                        <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licenseApply.contactPerson.qm1.title"/></div>
                        <div class="form-item pt-2 pb-5">
                            <input id="mrTitle" name="title" class="form-control" type="radio" value="Mr"/>
                            <label for="mrTitle" class="btn-ctrl btn_bold control-label"><spring:theme code="licenseApply.contactPerson.qm1.mr"/></label>
                        </div>

                        <div class="form-item pt-2 pb-5">
                            <input id="mrsTitle" name="title" class="form-control" type="radio" value="Mrs"/>
                            <label for="mrsTitle" class="btn-ctrl btn_bold control-label"><spring:theme code="licenseApply.contactPerson.qm1.mrs"/></label>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1FirstName" name="firstName" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1FirstName"><spring:theme code="licenseApply.contactPerson.qm1.firstName"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1LastName" name="lastName" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1LastName"><spring:theme code="licenseApply.contactPerson.qm1.lastName"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="qm1Role" name="role" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="qm1Role"><spring:theme code="licenseApply.contactPerson.qm1.role"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="qm1Education" name="education" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="qm1Education"><spring:theme code="licenseApply.contactPerson.qm1.education"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <div class="form-group">
                        <input id="qm1DateOfBirth" name="dateOfBirth" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1DateOfBirth"><spring:theme code="licenseApply.contactPerson.qm1.dateOfBirth"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1PassportNumber" name="passportNumber" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1PassportNumber"><spring:theme code="licenseApply.contactPerson.qm1.passportNumber"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <div class="form-group">
                        <input id="qm1PassportIssueDate" name="passportIssueDate" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1PassportIssueDate"><spring:theme code="licenseApply.contactPerson.qm1.passportIssueDate"/></label>
                        <div class="formInputBox-append">
                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                        </div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox formInputBox_group ">
                    <div class="form-group">
                        <input id="qm1PassportExpiryDate" name="passportExpiryDate" class="form-control js-form-control_date" placeholder="." value="" type="text"/>
                        <label class="control-label control-label_mandatory" for="qm1PassportExpiryDate"><spring:theme code="licenseApply.contactPerson.qm1.passportExpiryDate"/></label>
                        <div class="formInputBox-append"><span class="formInputBox-text"><icon:calendar-gray/></span></div>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="contentModule-section">
        <div class="contentModule-headline"><spring:theme code="licenseApply.contactPerson.qm1.contactInformation"/></div>
       <hr class="hr">
        <div class="row">
            <div class="col-md-6">
                <div class="formSelectBox">
                    <div class="form-group">
                        <select id="qm1Country" name="country" class="js-select2-oneColumn form-control"></select>
                        <label class="control-label control-label_mandatory" for="qm1Country"><spring:theme code="licenseApply.contactPerson.qm1.country"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <%--<select id="qm1City" name="qm1City" class="js-select2-oneColumn form-control"></select>--%>
                        <input id="qm1City" name="city" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1City"><spring:theme code="licenseApply.contactPerson.qm1.city"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1Address" name="address" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1Address"><spring:theme code="licenseApply.contactPerson.qm1.address"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1POBox" name="poBox" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1POBox"><spring:theme code="licenseApply.contactPerson.qm1.poBox"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1PostalCode" name="postalCode" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1PostalCode"><spring:theme code="licenseApply.contactPerson.qm1.postalCode"/></label>
                        <div class="help-block"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox-split">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="qm1CountryCodeForTelephone" name="qm1CountryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                            <label class="control-label" for="qm1CountryCodeForTelephone"><spring:theme code="licenseApply.contactPerson.qm1.countryCode"/></label>
                        </div>
                    </div>
                    <div class="formInputBox formInputBox_big">
                        <div class="form-group">
                            <input id="qm1Telephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                            <label class="control-label control-label_mandatory" for="qm1Telephone"><spring:theme code="licenseApply.contactPerson.qm1.telephone"/></label>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox-split">
                    <div class="formInputBox">
                        <div class="form-group">
                            <input id="qm1CountryCodeForMobileNumber" name="qm1CountryCodeForMobileNumber" class="form-control form-control_preNumber" placeholder="." type="text" value="" maxlength="5"/>
                            <label class="control-label" for="qm1CountryCodeForMobileNumber"><spring:theme code="licenseApply.contactPerson.qm1.countryCode"/></label>
                        </div>
                    </div>
                    <div class="formInputBox formInputBox_big">
                        <div class="form-group">
                            <input id="qm1MobileNumber" name="mobileNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                            <label class="control-label control-label_mandatory" for="qm1MobileNumber"><spring:theme code="licenseApply.contactPerson.qm1.mobileNumber"/></label>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm1Email" name="email" class="form-control" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm1Email"><spring:theme code="licenseApply.contactPerson.qm1.email"/></label>
                        <div class="help-block" id="contactEmailValidation"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
