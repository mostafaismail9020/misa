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

<div class="contentModule-section" id="contactPersonQM2">
    <div class="row">
        <div class="col-md-6">
            <div class="formRadioBox" id="qm2Title">
                <div class="form-group">
                    <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licenseApply.contactPerson.qm2.title"/></div>
                    <div class="form-item">
                        <input id="qm2MrTitle" name="gender" class="form-control" type="radio" value="Mr"/>
                        <label for="qm2MrTitle" class="control-label"><spring:theme code="licenseApply.contactPerson.qm2.mr"/></label>
                    </div>

                    <div class="form-item">
                        <input id="qm2MrsTitle" name="gender" class="form-control" type="radio" value="Mrs"/>
                        <label for="qm2MrsTitle" class="control-label"><spring:theme code="licenseApply.contactPerson.qm2.mrs"/></label>
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
                    <input id="qm2FirstName" name="firstName" class="form-control" placeholder="." type="text" value=""/>
                    <label class="control-label control-label_mandatory" for="qm2FirstName"><spring:theme code="licenseApply.contactPerson.qm2.firstName"/></label>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="qm2LastName" name="lastName" class="form-control" placeholder="." type="text" value=""/>
                    <label class="control-label control-label_mandatory" for="qm2LastName"><spring:theme code="licenseApply.contactPerson.qm2.lastName"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="qm2Nationality" name="nationality" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="qm2Nationality"><spring:theme code="licenseApply.contactPerson.qm2.nationality"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="qm2Country" name="country" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="qm2Country"><spring:theme code="licenseApply.contactPerson.qm2.country"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formSelectBox">
                <div class="form-group">
                    <select id="qm2Role" name="role" class="js-select2-oneColumn form-control"></select>
                    <label class="control-label control-label_mandatory" for="qm2Role"><spring:theme code="licenseApply.contactPerson.qm2.role"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox">
                <div class="form-group">
                    <input id="qm2Email" name="email" class="form-control" placeholder="." type="text" value=""/>
                    <label class="control-label control-label_mandatory" for="qm2Email"><spring:theme code="licenseApply.contactPerson.qm2.email"/></label>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm2CountryCodeForTelephone" name="qm2CountryCodeForTelephone" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>
                        <label class="control-label" for="qm2CountryCodeForTelephone"><spring:theme code="licenseApply.contactPerson.qm2.countryCode"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
                <div class="formInputBox formInputBox_big">
                    <div class="form-group">
                        <input id="qm2Telephone" name="telephone" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm2Telephone"><spring:theme code="licenseApply.contactPerson.qm2.telephone"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputBox-split">
                <div class="formInputBox">
                    <div class="form-group">
                        <input id="qm2CountryCodeForMobileNumber" name="qm2CountryCodeForMobileNumber" class="form-control form-control_preNumber" placeholder="." type="text" value=""/>
                        <label class="control-label" for="qm2CountryCodeForMobileNumber"><spring:theme code="licenseApply.contactPerson.qm2.countryCode"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
                <div class="formInputBox formInputBox_big">
                    <div class="form-group">
                        <input id="qm2MobileNumber" name="mobileNumber" class="form-control form-control_labeled" placeholder="." type="text" value=""/>
                        <label class="control-label control-label_mandatory" for="qm2MobileNumber"><spring:theme code="licenseApply.contactPerson.qm2.mobileNumber"/></label>
                    </div>
                    <div class="help-block"></div>
                </div>
            </div>
        </div>
    </div>
</div>
