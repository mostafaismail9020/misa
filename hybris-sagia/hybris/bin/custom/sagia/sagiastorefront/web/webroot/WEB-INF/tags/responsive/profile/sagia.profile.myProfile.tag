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
                <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap mb-0">
                    <div class="contentModule-headline profile-settings">
                     <!--  <span class="iconElement iconElement_accountSettings02"><icon:accountSettings02/></span>--><spring:theme code="profile.settings"/>
                    </div>
                    <div>
                        <button class="js-myAccount-edit btn_link btn_edit border-0">
                            <div class="js-myAccount-edit-text"><!--<spring:theme code="general.edit"/>-->
                                <span class="iconElement iconElement_edit02 pr-0"><icon:edit/></span>
                            </div>
                            <div class="js-myAccount-edit-text hidden">
                                <span class="iconElement iconElement_closeEdit" aria-hidden="true">&times;</span><spring:theme code="profile.exit.editMode"/>
                            </div>
                        </button>
                    </div>
                </div>
                <hr class="hr">
                <div class="myAccount-edit-toggle js-myAccount-edit-toggle">
                    <div class="myAccount-profilImage">
					<h4 id="sagia_profile_picture_title" class="sagia_profile_picture_title"></span><spring:theme code="profile.my.profile.picture"/></h4>
                        <div class="myAccount-profilImage-img"><div class="profilePicture js-profilePicture"></div></div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated border-0">
                                <dt><spring:theme code="profile.personal.title"/></dt>
                                <dd id="sagiaProfilePersonalFormTitle"></dd>
                            </dl>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <dl class="dlList dlList_separated border-0">
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
                            <dl class="dlList dlList_separated border-0">
                                <dt><spring:theme code="general.lastname"/></dt>
                                <dd id="sagiaProfilePersonalFormLastName"></dd>
                                <dt><spring:theme code="general.company"/></dt>
                                <dd id="sagiaProfilePersonalFormCompany"></dd>
                                <dt><spring:theme code="general.countrycode"/></dt>
                                <dd id="sagiaProfilePersonalFormMobileCountryCode"></dd>
                                <dt><spring:theme code="general.companyWebsite"/></dt>
                                <dd id="sagiaProfilePersonalFormCompanyWebsite"></dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="myAccount-profilImage">
                        <h4 id="sagia_companyLogo_picture_title"  class="sagia_profile_picture_title"></h4>
                        <div class="myAccount-profilImage-img"><div class="companyLogo js-companyLogo"></div></div>
                    </div></div>

                </div>

                <div class="myAccount-edit-toggle js-myAccount-edit-toggle" style="display: none;">
                    <div class="myAccount-profilImage">
                       <!-- <div class="myAccount-profilImage-img"><div class="profilePicture js-profilePicture" ></div></div>-->
                        <a href="#" class="myAccount-profilImage-change w-50 p-4 pt-4 pl-3 pr-3 mt-4" data-toggle="modal" data-target="#uploadFilePicture" data-action="update-profilePic"><spring:theme code="company.changeprofilepicture"/>
                        <span class="uploadFile text-center pr-2"><svg xmlns="http://www.w3.org/2000/svg" class="mt-3" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
  </g>
</svg>
</span>
                        </a>
                    </div>

                    <!--Modal: Use (data-toggle="modal" data-target="#uploadFilePicture") on link or button to call it-->
                    <div class="modal fade" id="uploadFilePicture" tabindex="-1" role="dialog"
                         aria-labelledby="uploadFilePicture" aria-hidden="true" >
                        <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent"
                             role="document">
                            <div class="modal-content">
                                <form:form action="dummy" id="modalPictureUploadForm" method="post" enctype="multipart/form-data" class="js-formInputFileBox">
                                    <div class="modal-header">
                                        <div class="modal-title"><spring:theme code="company.uploadyourdocument"/></div>
                                    </div>
                                    <div class="modal-body">
                                        <div class="formInputFileBox">
                                            <div class="form-group file-area">
                                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                                <input id="fileBoxModalPicture" class="form-control js-inputFile" type="file" name="file" accept="image/jpeg,image/png" placeholder="."/>
                                                <label class="control-label" for="fileBoxModalPicture">
                                                    <spring:theme code="company.choseapicture"/><!--<span class="formInputFileBox-dragndrop"> <spring:theme code ="company.dragpicture"/></span>-->.
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
                                        <input id="sagiaProfilePersonalFormSelectFirstName" name="firstName" class="form-control" type="text" value="" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectFirstName">
                                            <spring:theme code="profile.personal.firstName"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectLastName" name="lastName" class="form-control" type="text" value="" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectLastName">
                                            <spring:theme code="profile.personal.lastName"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6" style="display: none">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectEmail" name="email" class="form-control" type="text" value="" readonly="readonly" disabled="disabled" placeholder="."/>
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
                                        <input id="sagiaProfilePersonalFormSelectCompany" name="company" class="form-control" type="text" value="" placeholder="."/>
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
                                        <input id="sagiaProfilePersonalFormSelectMobileCountryCode" title="mobileCountryCode" name="mobileCountryCode" class="form-control" type="text" maxLength="4" value="" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectMobileCountryCode">
                                            <spring:theme code="profile.personal.mobileCountryCode"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectMobileNumber" oninput="limitChar(this)" title="mobileNumber" name="mobileNumber" class="form-control" type="number" value="" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectMobileNumber">
                                            <spring:theme code="profile.personal.mobileNumber"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formInputBox">
                                    <div class="form-group">
                                        <input id="sagiaProfilePersonalFormSelectCompanyWebsite" title="companyWebsite" name="companyWebsite" class="form-control" type="text" value="" placeholder="."/>
                                        <label class="control-label control-label_mandatory" for="sagiaProfilePersonalFormSelectCompanyWebsite">
                                            <spring:theme code="general.companyWebsite"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                             <div class="col-md-12">
                             <div class="myAccount-profilImage mt-0">
                       <!-- <div class="myAccount-profilImage-img"><div class="profilePicture js-profilePicture" ></div></div>-->
                        <a href="#" class="myAccount-profilImage-change w-50 p-4 pt-4 pl-3 pr-3 mt-4" data-toggle="modal" data-target="#uploadFilePicture" data-action="update-companyLogo"><spring:theme code="general.change.companylogo"/>
                        <span class="uploadFile text-center pr-2"><svg xmlns="http://www.w3.org/2000/svg" class="mt-3" width="29" height="29" viewBox="0 0 29 29">
                            <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
                                <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
                                <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
                                <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
                             </g>
                            </svg>
                        </span>
                        </a>
                    </div>
                    </div>

                        </div>

                        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection justify-content-end">
                            <ycommerce:testId code="personalDetails_cancelPersonalDetails_button">
                                <button id="sagiaProfilePersonalFormCancelUpdates" type="button" class="btn btn_bold btn-outline pt-2 pb-2 w-25 text-uppercase"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
                            </ycommerce:testId>
                            <ycommerce:testId code="personalDetails_savePersonalDetails_button">
                                <button id="sagiaProfilePersonalFormSaveUpdates" type="button" class="btn btn_bold btn-bg pt-2 pb-2 w-25 text-uppercase"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
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
