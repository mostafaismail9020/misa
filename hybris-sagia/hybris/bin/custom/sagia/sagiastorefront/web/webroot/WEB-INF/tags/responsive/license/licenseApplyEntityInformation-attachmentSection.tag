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
                    <div class="form-icon form-icon_browse"><!--<icon:upload/>--> <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 36 36">
  <g id="round-clouse" transform="translate(-0.454)">
    <circle id="Ellipse_45" data-name="Ellipse 45" cx="18" cy="18" r="18" transform="translate(0.454)" fill="#fff"/>
    <g id="Group_981" data-name="Group 981" transform="translate(13.454 13)">
      <line id="Line_90" data-name="Line 90" x2="10.018" y2="10.018" fill="none" stroke="#707070" stroke-width="1"/>
      <line id="Line_91" data-name="Line 91" x1="10.018" y2="10.018" fill="none" stroke="#707070" stroke-width="1"/>
    </g>
  </g>
</svg>
</div>
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
                    <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 36 36">
  <g id="round-clouse" transform="translate(-0.454)">
    <circle id="Ellipse_45" data-name="Ellipse 45" cx="18" cy="18" r="18" transform="translate(0.454)" fill="#fff"/>
    <g id="Group_981" data-name="Group 981" transform="translate(13.454 13)">
      <line id="Line_90" data-name="Line 90" x2="10.018" y2="10.018" fill="none" stroke="#707070" stroke-width="1"/>
      <line id="Line_91" data-name="Line 91" x1="10.018" y2="10.018" fill="none" stroke="#707070" stroke-width="1"/>
    </g>
  </g>
</svg>
</div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
</div>

