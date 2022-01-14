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

<!--  Attachment  -->
<div class="contentModule-section" id="attachmentSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin"><spring:theme code="licenseApplyEntityInformation.attachmentSection.attachment"/></div>
<hr class="hr">
    <div class="row">
        <div class="col-md-6">
            <div class="formInputFile">
                <div class="form-group">
                    <input id="boardResolutionFile" name="boardResolutionFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                    <input id="boardResolutionFileName" name="boardResolutionFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="boardResolutionFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.boardResolution"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputFile">
                <div class="form-group">
                    <input id="letterOfSupportFile" name="letterOfSupportFile" class="form-control js-inputFile" type="file" accept="application/pdf" value=""/>
                    <input id="letterOfSupportFileName" name="letterOfSupportFileName" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="letterOfSupportFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.letterOfSupport"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
</div>

