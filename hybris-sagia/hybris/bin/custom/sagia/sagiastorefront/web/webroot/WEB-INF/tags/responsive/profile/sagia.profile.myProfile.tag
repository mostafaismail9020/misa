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

<div class="panelTabs-head" id="myProfileTab"><spring:theme code="company.myprofile"/></div>
<div class="panelTabs-body" id="myProfileTabData">
    <div class="panelModule panelModule_halfRadius">
        <div class="contentModule">
            <div class="contentModule-section">
                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                    <div class="contentModule-headline">
                        <span class="iconElement iconElement_accountSettings02"><icon:accountSettings02/></span><spring:theme code="profile.settings"/>
                    </div>
                    <div>
                        <button class="js-myAccount-edit btn btn_link btn_edit">
                            <div class="js-myAccount-edit-text"><spring:theme code="general.edit"/>
                                <span class="iconElement iconElement_edit02"><icon:edit/></span>
                            </div>
                            <div class="js-myAccount-edit-text hidden">
                                <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span><spring:theme code="profile.exit.editMode"/>
                            </div>
                        </button>
                    </div>
                </div>

                <div class="myAccount-edit-toggle js-myAccount-edit-toggle">
                    <div class="myAccount-profilImage">
                        <div class="myAccount-profilImage-img"><div class="profilePicture js-profilePicture"></div></div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="profile.personal.title"/></dt>
                                <dd id="sagiaProfilePersonalFormTitle"></dd>
                            </dl>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="general.firstname"/></dt>
                                <dd id="sagiaProfilePersonalFormFirstName"></dd>
                                <dt><spring:theme code="general.country"/></dt>
                                <dd id="sagiaProfilePersonalFormCountryName"></dd>
                                <dt><spring:theme code="general.sector"/></dt>
                                <dd id="sagiaProfilePersonalFormSectorName"></dd>
                                <dt><spring:theme code="general.mobilenumber"/></dt>
                                <dd id="sagiaProfilePersonalFormMobileNumber"></dd>
                            </dl>
                        </div>
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated">
                                <dt><spring:theme code="general.lastname"/></dt>
                                <dd id="sagiaProfilePersonalFormLastName"></dd>
                                <dt><spring:theme code="general.company"/></dt>
                                <dd id="sagiaProfilePersonalFormCompany"></dd>
                                <dt><spring:theme code="general.countrycode"/></dt>
                                <dd id="sagiaProfilePersonalFormMobileCountryCode"></dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <div class="myAccount-edit-toggle js-myAccount-edit-toggle" style="display: none;">
                    <div class="myAccount-profilImage">
                        <div class="myAccount-profilImage-img"><div class="profilePicture js-profilePicture" ></div></div>
                        <a href="#" class="myAccount-profilImage-change" data-toggle="modal" data-target="#uploadFilePicture"><spring:theme code="company.changeprofilepicture"/></a>
                    </div>

                    <!--Modal: Use (data-toggle="modal" data-target="#uploadFilePicture") on link or button to call it-->
                    <div class="modal fade" id="uploadFilePicture" tabindex="-1" role="dialog"
                         aria-labelledby="uploadFilePicture" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent"
                             role="document">
                            <div class="modal-content">
                                <form:form action="update-profilePic" method="post" enctype="multipart/form-data" class="js-formInputFileBox">
                                    <div class="modal-header">
                                        <div class="modal-title"><spring:theme code="company.uploadyourdocument"/></div>
                                    </div>
                                    <div class="modal-body">
                                        <div class="formInputFileBox">
                                            <div class="form-group">
                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                <input id="fileBoxModalPicture" class="form-control js-inputFile" type="file" name="file" accept="image/jpeg" value=""/>
                                                <label class="control-label" for="fileBoxModalPicture">
                                                    <spring:theme code="company.choseapicture"/><span class="formInputFileBox-dragndrop"> <spring:theme code ="company.dragpicture"/></span>.
                                                </label>
                                            </div>
                                            <div class="formInputFileBox-uploading"><spring:theme code="company.uploading"/></div>
                                            <div class="formInputFileBox-success"><spring:theme code="company.done"/></div>
                                            <div class="formInputFileBox-error"><spring:theme code="company.error"/><span></span>.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn" id="updateProfilePicBtn"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>

                    <form id="sagiaProfilePersonalForm">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="sagiaProfilePersonalFormSelectTitle" name="title" class="js-select2-oneColumn form-control">
                                            <option></option>
                                        </select>
                                        <label class="control-label" for="sagiaProfilePersonalFormSelectTitle"><spring:theme code="profile.personal.title"/></label>
                                        <div class="help-block"><span id="sagiaProfilePersonalFormSelectTitle-error"></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectFirstName" name="firstName" class="form-control" type="text" value=""/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectFirstName">
                                            <spring:theme code="profile.personal.firstName"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectLastName" name="lastName" class="form-control" type="text" value=""/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectLastName">
                                            <spring:theme code="profile.personal.lastName"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6" style="display: none">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectEmail" name="email" class="form-control" type="text" value="" readonly="readonly" disabled="disabled"/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectEmail">
                                            <spring:theme code="profile.personal.email"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="sagiaProfilePersonalFormSelectCountry" name="country" class="js-select2-oneColumn js-select2-searchBegining js-select2-sortAlphabetically form-control">
                                            <option></option>
                                        </select>
                                        <label class="control-label" for="sagiaProfilePersonalFormSelectCountry"><spring:theme code="profile.personal.country"/></label>
                                        <div class="help-block"><span id="sagiaProfilePersonalFormSelectCountry-error"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectCompany" name="company" class="form-control" type="text" value=""/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectCompany">
                                            <spring:theme code="profile.personal.company"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="sagiaProfilePersonalFormSelectSector" name="country" class="js-select2-oneColumn form-control" disabled="disabled">
                                            <option></option>
                                        </select>
                                        <label class="control-label" for="sagiaProfilePersonalFormSelectSector"><spring:theme code="profile.personal.sector"/></label>
                                        <div class="help-block"><span id="sagiaProfilePersonalFormSelectSector-error"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectMobileCountryCode" title="mobileCountryCode" name="mobileCountryCode" class="form-control" type="text" maxLength="4" value=""/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectMobileCountryCode">
                                            <spring:theme code="profile.personal.mobileCountryCode"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectMobileNumber" title="mobileNumber" name="mobileNumber" class="form-control" type="number" value=""/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectMobileNumber">
                                            <spring:theme code="profile.personal.mobileNumber"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                <button id="sagiaProfilePersonalFormCancelUpdates" type="button" class="btn btn-secondary"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
                            </ycommerce:testId>
                            <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                <button id="sagiaProfilePersonalFormSaveUpdates" type="button" class="btn"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
                            </ycommerce:testId>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var tutorialJson = [
        {
            selector: "#myProfileTabData .contentModule-headline",
            index: 0,
            position: "bottom",
            offset: 10,
            borderRadius: "[0,0,0,0]",
            borderRadiusSm: "[0,13,0,13]",
            title: "Lorem ipsum 11",
            description: "Dolor sit amet 11"
        }, {
            selector: "#accessibletabsnavigation0-1",
            index: 0,
            position: "bottom",
            offset: 10,
            borderRadius: "[0,0,0,0]",
            borderRadiusSm: "[0,13,0,13]",
            title: "Lorem ipsum 22",
            description: "Dolor sit amet 22"
        }
    ];
</script>
