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


<div class="contentModule-section" id="rhqAttachmentSection" style="display: none">
    <div class="contentModule-headline contentModule-headline_smallMargin mw2" id="attachmentSectionTitle"><spring:theme code="licenseApplyEntityInformation.rhqattachmentSection.attachment"/></div>
    <hr class="hr">
    <div class="row">
    	<div id="currentMarketValueAttachment" style="display: none">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.currentMarketValueFile ? "active" : ""}">
             <div class="form-group">
                <input id="currentMarketValueFile" name="customCurrentMarketValueFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="rhqCurrentMarketValueFileName" name="rhqCurrentMarketValueFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.currentMarketValueFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="rhqCurrentMarketValueFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.currentMarketValueFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="currentMarketValueFile-helper"></div>
          	</div>
			</div>
   		</div>
   </div>
</div>