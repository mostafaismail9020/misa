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


<div class="contentModule-section" id="rhqAttachmentSection">
    <div class="contentModule-headline contentModule-headline_smallMargin mw2" id="attachmentSectionTitle"><spring:theme code="licenseApplyEntityInformation.rhqattachmentSection.attachment"/></div>
    <hr class="hr">
   	<div id="currentMarketValueAttachment" style="display: none">
    	<div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.currentMarketValueFile ? "active" : ""}">
             <div class="form-group">
                <input id="currentMarketValueFile" name="customCurrentMarketValueFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="currentMarketValueFileName" name="currentMarketValueFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.currentMarketValueFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="currentMarketValueFileName">
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
   
   <div id="average3YearRevenueAttachment" style="display: none">
    	<div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.average3YearRevenueFile ? "active" : ""}">
             <div class="form-group">
                <input id="average3YearRevenueFile" name="customAverage3YearRevenueFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="average3YearRevenueFileName" name="average3YearRevenueFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.average3YearRevenueFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="average3YearRevenueFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.average3YearRevenueFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="average3YearRevenueFile-helper"></div>
          	</div>
			</div>
   		</div>
   </div>
   
   <div id="lastYearAssetAttachment" style="display: none">
    	<div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.lastYearAssetFile ? "active" : ""}">
             <div class="form-group">
                <input id="lastYearAssetFile" name="customLastYearAssetFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="lastYearAssetFileName" name="lastYearAssetFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.lastYearAssetFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="lastYearAssetFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.lastYearAssetFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="lastYearAssetFile-helper"></div>
          	</div>
			</div>
   		</div>
   </div>
   
   <div id="numberOfEmployeesAttachment" style="display: none">
    	<div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.numberOfEmployeesFile ? "active" : ""}">
             <div class="form-group">
                <input id="numberOfEmployeesFile" name="customNumberOfEmployeesFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="numberOfEmployeesFileName" name="numberOfEmployeesFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.numberOfEmployeesFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="numberOfEmployeesFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.numberOfEmployeesFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="numberOfEmployeesFile-helper"></div>
          	</div>
			</div>
   		</div>
   </div>
   
   <div id="companyRankedInFortuneAttachment" style="display: none">
    	<div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.companyRankedInFortuneFile ? "active" : ""}">
             <div class="form-group">
                <input id="companyRankedInFortuneFile" name="customCompanyRankedInFortuneFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="companyRankedInFortuneFileName" name="companyRankedInFortuneFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.companyRankedInFortuneFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="companyRankedInFortuneFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.companyRankedInFortuneFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="companyRankedInFortuneFile-helper"></div>
          	</div>
			</div>
   		</div>
   </div>
   
</div>