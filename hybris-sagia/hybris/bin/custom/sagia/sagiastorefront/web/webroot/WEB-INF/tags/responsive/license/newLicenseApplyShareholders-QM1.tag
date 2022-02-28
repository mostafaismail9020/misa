<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="license" tagdir="/WEB-INF/tags/responsive/license" %>

<c:set var="hasShareholder" value="${fn:length(shareHoldersDataList) > 0}"/>

<!-- QM1 -->
<div class="contentModule" id="shareholdersQM1" data-legalstatus="${entityInformationData.legalStatus}" data-licensetype="${entityInformationData.licenseType}">
    <div class="contentModule-section" id="addShareholderQM1NoShareholderSection" ${hasShareholder ? "style='display: none'" : ""}>
        <span class="iconElement iconElement_shareholderProfile text-center"><icon:shareholderProfile/></span>
        <p class="text-center"><spring:theme code="licence.apply.noshareholder"/></p>
        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <c:if test="${entityInformationData.licenseType ne 11}">
                <button type="button" class="addExistingButton btn btn_bold btn-outline btn-normal my-button my-button-handler" data-url="<c:url value="/my-sagia/license/existing-shareholder-form"/>">
                <span class="icon-bg add-icon my-icon" >
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                    <g id="add-new" transform="translate(-222.936 -1127.936)">
                        <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                        <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
                        <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                        <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                        </g>
                    </g>
                    </svg>
                </span>
                <span class="icon-bg plus-icon my-icon" >
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                    <g id="existing" transform="translate(-222.936 -1127.936)">
                    <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                    <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                    </g>
                </svg>
                </span>
                <spring:theme code="license.apply.review.existing.shareholder"/></button>
            </c:if>
           <button type="button" class="addNewButton btn btn_bold btn-outline btn-normal my-button my-button-handler">
                <span class="icon-bg add-icon my-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                        <g id="add-new" transform="translate(-222.936 -1127.936)">
                        <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                        <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
                            <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                            <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                        </g>
                        </g>
                    </svg>
              </span>              
              <span class="icon-bg plus-icon my-icon" >
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                    <g id="existing" transform="translate(-222.936 -1127.936)">
                        <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                        <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                    </g>
                </svg>
              </span>

            <spring:theme code="license.apply.review.new.shareholder"/>
        </button>
        </div>
    </div>

    <!--        This section should be shown if you click on Save shareholer                -->
    <div class="contentModule-section" id="shareholderQM1TableSection" ${hasShareholder ? "" : "style='display: none'"}>
        <div class="tableModule">
            <table class="tableModule-table">
                <thead class="tableModule-head">
                    <tr>
                        <th><spring:theme code="license.apply.review.name"/></th>
                        <th><spring:theme code="license.apply.review.type"/></th>
                        <th><spring:theme code="license.apply.percentage"/></th>
                        <th><spring:theme code="license.apply.review.nationality"/></th>
                        <th><spring:theme code="license.apply.review.legalstatus"/></th>
                        <th><spring:theme code="license.apply.review.delegate"/></th>
                        <th><spring:theme code="license.apply.review.delegateId"/></th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody class="tableModule-body">
                    <license:newLicenseApplyRegisteredShareholdersTable/>
                </tbody>
            </table>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
        	<c:if test="${entityInformationData.licenseType ne 11}">
           		 <button type="button" class="addExistingButton btn btn_bold btn-outline btn-normal my-button my-button-handler">
                    <span class="icon-bg add-icon my-icon" >
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                            <g id="add-new" transform="translate(-222.936 -1127.936)">
                                <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                                <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
                                    <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                                    <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                                </g>
                            </g>
                        </svg>
                    </span>
                    <span class="icon-bg plus-icon my-icon" >
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                            <g id="existing" transform="translate(-222.936 -1127.936)">
                                <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                                <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                            </g>
                        </svg>
                    </span>
                    <spring:theme code="license.apply.review.existing.shareholder"/>
                </button>
            </c:if>
            <button type="button" class="addNewButton btn btn_bold btn-outline btn-normal my-button my-button-handler">
                <span class="icon-bg add-icon my-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                        <g id="add-new" transform="translate(-222.936 -1127.936)">
                        <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                        <g id="Group_1067" data-name="Group 1067" transform="translate(-5.5)">
                            <path id="Path_1956" data-name="Path 1956" d="M0,12V0" transform="translate(244.436 1137.436)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                            <path id="Path_1957" data-name="Path 1957" d="M0,12V0" transform="translate(250.436 1143.436) rotate(90)" fill="#1ac4dc" stroke="#fff" stroke-linecap="round" stroke-width="2"></path>
                        </g>
                        </g>
                    </svg>
              </span>              
              <span class="icon-bg plus-icon my-icon" >
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                    <g id="existing" transform="translate(-222.936 -1127.936)">
                        <circle id="Ellipse_35" data-name="Ellipse 35" cx="16" cy="16" r="16" transform="translate(222.936 1127.936)" fill="#1ac4dc"></circle>
                        <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#fff" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                    </g>
                </svg>
              </span>
              <spring:theme code="license.apply.review.new.shareholder"/>
            </button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1ExistingSection" style="display: none">
        <div id="shareholderExistingGlobalMessage" class="globalMessage alert alert-warning getAccAlert" style="display: none;">
        <span class="globalMessage-msg">
            <div class="globalMessage-icon globalMessage-icon_warning">
                <icon:warning/>
            </div>
            <span class="text">
                <spring:theme code="license.apply.qeemah.determination.warning"/>
            </span>
        </span>
        </div>

        <div class="contentModule-headline"><spring:theme code="license.apply.review.existing.shareholder"/></div>
     <hr class="hr">
        <div class="existingShareholdersForm-wrapper mt-4 pt-2">
<%--            <license:newLicenseApplyExistingShareholderForm-QM1/>--%>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button type="button" class="cancelButton btn btn-normal btn_bold btn-outline w-25"><spring:theme code="general.cancel"/></button>
            <button id="validateAddShareholder" type="button" class="addButton btn btn-normal btn-bg btn-bg btn_bold w-25"><spring:theme code="licence.apply.validate.add"/></button>
        </div>
    </div>

    <div class="contentModule-section" id="addShareholderQM1NewSection" style="display: none">
        <div class="contentModule-headline"><spring:theme code="license.apply.review.new.shareholder"/></div>
    <hr class="hr">
        <div class="row">
            <div class="col-md-12">
                <div class="formRadioBox pt-3" id="shareholderType">
                    <div class="form-group justify-content-center">
                        <div class="formRadioBox-label control-label_mandatory"><spring:theme code="licence.apply.shareholder.type"/></div>
                        <div class="form-item">
                            <input id="personType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Person"/>
                            <label for="personType" class="btn-normal btn_bold person-type control-label ">
                            <span class="icon-bg icon-ele">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                                    <g id="Person-inactive" transform="translate(-222.936 -1127.936)">
                                        <g id="Ellipse_35" data-name="Ellipse 35" transform="translate(222.936 1127.936)" fill="none" stroke="#00a6be" stroke-width="1">
                                        <circle cx="16" cy="16" r="16" stroke="none"></circle>
                                        <circle cx="16" cy="16" r="15.5" fill="none"></circle>
                                        </g>
                                        <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                                    </g>
                                </svg>
                                </span>
                        <span class="label-name label-name-inactive">    <spring:theme code="license.apply.shareholder.person"/></span>

                            </label>
                        </div>

                        <div class="form-item">
                            <input id="organizationType" name="ShareholderRadioBox01" class="form-control" type="radio" value="Organization"/>
                            <label for="organizationType" class="btn-normal btn_bold control-label organization-type ">
                            <span class="icon-bg icon-ele">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
                                    <g id="Person-inactive" transform="translate(-222.936 -1127.936)">
                                        <g id="Ellipse_35" data-name="Ellipse 35" transform="translate(222.936 1127.936)" fill="none" stroke="#00a6be" stroke-width="1">
                                        <circle cx="16" cy="16" r="16" stroke="none"></circle>
                                        <circle cx="16" cy="16" r="15.5" fill="none"></circle>
                                        </g>
                                        <path id="Path_1887" data-name="Path 1887" d="M221.668,850.239l3.6,3a.125.125,0,0,0,.174-.013l7.727-8.753" transform="translate(11.755 294.682)" fill="none" stroke="#00a6be" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2"></path>
                                    </g>
                                </svg>
                            </span>
                           <span class="label-name label-name-inactive"> <spring:theme code="general.organization"/></span>

                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
            <button class="cancelButton btn btn-normal btn_bold btn-outline w-25"><spring:theme code="general.cancel"/></button>
        </div>
    </div>

     <div id="shareholderNewGlobalMessage" class="globalMessage alert alert-warning getAccAlert" style="display: none;">
                <span class="globalMessage-msg">
                    <div class="globalMessage-icon globalMessage-icon_warning">
                        <icon:warning/>
                    </div>
                    <spring:theme code="validation.review.form"/>
                    <%--<span class="text"></span>--%>
                </span>
     </div>


    <!-- section for type Person -->
    <div id="addShareholderQM1NewPersonSection" style="display: none">

    </div>

    <!-- section for type organization -->
    <div id="addShareholderQM1NewOrganizationSection" style="display: none">

	</div>
</div>
