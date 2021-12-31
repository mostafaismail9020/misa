<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="legalConsultationCreate.title"/> </h1>
            <div>
                <button class="btn btn_round btn_slim js-save-draft"
                        data-target-form="${serviceId}"
                        data-service-id="${serviceId}"><spring:theme code="general.savedraft"/><span
                        class="iconElement iconElement_save"><icon:save/></span></button>
                <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                        data-target-form="${serviceId}"
                        data-service-id="${serviceId}">
                    <spring:theme code="general.loaddraft"/><span
                        class="iconElement iconElement_save"><icon:upload/></span></button>
            </div>
        </div>
    </div>
</div>
<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink">
            <span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="legalConsultationCreate.backToAccountOverview.text"/>
        </a>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <form:form id="${serviceId}" action="${encodedContextPath}/legalconsultations/create" enctype="multipart/form-data" method="post" modelAttribute="legalConsultationFormData" class="js-legalconsultation-create">
            <div class="panelModule panelModule_halfRadius">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <span class="iconElement iconElement_enquiry3"><icon:info/></span><spring:theme code="legalConsultationCreate.reqInformations.text"/>
                        </div>

                        <div class="formSelectBox">
                            <div class="form-group <c:if test="${status.error}">has-error</c:if>">
                                <form:select class="js-select2-oneColumn" id="legalInquiryTypeId" path="legEnqTitle">
                                    <form:option value=""/>
                                    <form:options items="${legalInquiryTypes}" itemValue="id" itemLabel="description" htmlEscape="true"/>
                                </form:select>
                                <label class="control-label control-label_mandatory" for=""><spring:theme code="profile.enquiry.type"/></label>
                                <span class="help-block"><form:errors path="legEnqTitle"/></span>
                            </div>
                        </div>

                        <div class="formTextArea">
                            <div class="form-group <c:if test="${status.error}">has-error</c:if>">
                                <form:textarea id="" class="form-control form-control_slim" placeholder="." path="textMsg"/>
                                <form:label class="control-label control-label_mandatory" for="" path="textMsg">Message</form:label>
                                <span class="help-block"><form:errors path="textMsg"/></span>
                            </div>
                        </div>

                        <form:hidden id="typeDescriptionId" path="legEnqDesc" />
                    </div>
                    <div class="contentModule-section">
                        <div class="contentModule-headline">
                            <icon:documents/>Attachments
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file0" data-id="0" name="files[0]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text05" name="text05" class="form-control" type="text" value="" placeholder="" readonly="" tabindex="-1"/>
                                        <label class="control-label " for="">Label for file </label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
										<div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file1" data-id="1" name="files[1]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text02" name="text02" class="form-control" type="text" value="" placeholder="" readonly="" tabindex="-1"/>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
										<div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file2" data-id="2" name="files[2]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text03" name="text03" class="form-control" type="text" value="" placeholder="" readonly="" tabindex="-1"/>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
										<div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file3" data-id="3" name="files[3]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text04" name="text04" class="form-control" type="text" value="" placeholder="" readonly="" tabindex="-1"/>
                                        <label class="control-label " for="">Label for file</label>
                                        <div class="form-icon form-icon_browse"><icon:upload/></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
										<div class="help-block"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
						 <div><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                    </div>
                </div>
            </div>
            <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                <button type="reset" class="btn btn-secondary" onclick="window.location.href='${encodedContextPath}">
                    <spring:theme code="general.cancel"/>
                </button>
                <div class="formCheckBox formCheckBox_belowPanel">
                    <formElement:termsAndConditionsCheckbox event="LEGAL_CONSULTATION" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition"/>
                </div>
                <button type="submit" value="Submit request" class="btn js-submit-legalconsultation"><spring:theme code="general.submit"/></button>
            </div>
        </form:form>
    </div>
</div>

<common:errorModal />
<input type="hidden" id="legalConsultationErrorMessage" value="${errorMessage}"/>
