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
<div class="contentModule-section" id="attachmentSection" style="display: none" ${not empty sagiaApplyEntityInfoForm.licenseType ? "data-existingentity" : ""}>
    <div class="contentModule-headline contentModule-headline_smallMargin" id="attachmentSectionTitle"><spring:theme code="licenseApplyEntityInformation.attachmentSection.attachment"/></div>
	<div id="entrepreneurAttachment" style="display: none">
    <div class="row">
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.boardResolutionFile ? "active" : ""}">
                <div class="form-group">
                    <input id="boardResolutionFile" name="customBoardResolutionFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="boardResolutionFileName" name="boardResolutionFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.boardResolutionFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="boardResolutionFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.boardResolution"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.letterOfSupportFile ? "active" : ""}">
                <div class="form-group">
                    <input id="letterOfSupportFile" name="customLetterOfSupportFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="letterOfSupportFileName" name="letterOfSupportFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.letterOfSupportFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="letterOfSupportFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.letterOfSupport"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.letterOfSupportFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
    </div>
    
    
   <div id="entityListedInStockMarketAttachment" style="display: none">
	    <div class="row">
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqStockMarketAttachment ? "active" : ""}">
	                <div class="form-group">
	                    <input id="entityListedInStockMarketFile" name="customEntityListedInStockMarketFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="entityListedInStockMarketFileName" name="entityListedInStockMarketFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqStockMarketAttachment.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="entityListedInStockMarketFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.listed.in.stock.market"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
    	</div>
    </div>
   <div id="entityAssetAttachment" style="display: none">
	    <div class="row">
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqEntityAssetAttachment ? "active" : ""}">
	                <div class="form-group">
	                    <input id="entityAssetFile" name="customEntityAssetFileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="entityAssetFileName" name="entityAssetFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqEntityAssetAttachment.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="entityAssetFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.asset.more.than.threshold"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
    	</div>
    </div>    
   <div id="entityRevenueAttachment" style="display: none">
	    <div class="row">
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqEntityRevenueAttachment ? "active" : ""}">
	                <div class="form-group">
	                    <input id="entityRevenueFile" name="customEntityRevenueFileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="entityRevenueFileName" name="entityRevenueFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqEntityRevenueAttachment.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="entityRevenueFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.revenue.more.than.threshold"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
    	</div>
    </div>    
  
	  <div id="entityBranchAttachment" style="display: none">
	    <div class="row">
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqCR1 ? "active" : ""}">
	                <div class="form-group">
	                    <input id="branchCR1File" name="customBranchCR1FileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="branchCR1FileName" name="branchCR1FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqCR1.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="branchCR1FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.branch.CR1"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
			<div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqCR2 ? "active" : ""}">
	                <div class="form-group">
	                    <input id="branchCR2File" name="customBranchCR2FileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="branchCR2FileName" name="branchCR2FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqCR2.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="branchCR2FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.branch.CR2"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqCR3 ? "active" : ""}">
	                <div class="form-group">
	                    <input id="branchCR3File" name="customBranchCR3FileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="branchCR3FileName" name="branchCR3FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqCR3.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="branchCR3FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.branch.CR3"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
	        <div class="col-md-6">
	            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.rhqCR4 ? "active" : ""}">
	                <div class="form-group">
	                    <input id="branchCR4File" name="customBranchCR4FileName" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
	                    <input id="branchCR4FileName" name="branchCR4FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.rhqCR4.fileName}" readonly tabindex="-1"/>
	                    <label class="control-label control-label_mandatory" for="branchCR4FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.entity.branch.CR4"/></label>
	                    <div class="form-icon form-icon_browse"><icon:upload/></div>
	                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
	<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
	                </div>
	                <div class="help-block"></div>
	            </div>
	        </div>
	    </div>
	  </div>
    
    
    <div id="branchAttachment" style="display: none">
                <div class="row">
                    <div class="col-md-6">
                        <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.mainBranchCR ? "active" : ""}">
                            <div class="form-group">
                                <input id="mainBranchCRFile" name="customMainBranchCRFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                                <input id="mainBranchCRFileName" name="mainBranchCRFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.mainBranchCR.fileName}" readonly tabindex="-1"/>
                                <label class="control-label control-label_mandatory" for="mainBranchCRFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.mainBranchCR"/></label>
                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
            <%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.otherBranchCR1 ? "active" : ""}">
                            <div class="form-group">
                                <input id="otherBranchCR1File" name="customOtherBranchCR1File" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                                <input id="otherBranchCR1FileName" name="otherBranchCR1FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.otherBranchCR1.fileName}" readonly tabindex="-1"/>
                                <label class="control-label control-label_mandatory" for="otherBranchCR1FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.otherBranchCR1"/></label>
                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
            <%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.letterOfSupportFile.code}</div>--%>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                            <div class="col-md-6">
                        <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.otherBranchCR2 ? "active" : ""}">
                            <div class="form-group">
                                <input id="otherBranchCR2File" name="customOtherBranchCR2File" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                                <input id="otherBranchCR2FileName" name="otherBranchCR2FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.otherBranchCR2.fileName}" readonly tabindex="-1"/>
                                <label class="control-label control-label_mandatory" for="otherBranchCR2FileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.otherBranchCR2"/></label>
                                <div class="form-icon form-icon_browse"><icon:upload/></div>
                                <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
            <%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.letterOfSupportFile.code}</div>--%>
                            </div>
                            <div class="help-block"></div>
                        </div>
                    </div>
                </div>
                </div>

    <div id="branchAttachmentNewRhq" style="display: none">
       <div class="row">
          <div class="col-md-6">
             <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.entityFinancialStatementFile ? "active" : ""}">
             <div class="form-group">
                <input id="entityFinancialStatementFile" name="customEntityFinancialStatementFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                <input id="entityFinancialStatementFileName" name="entityFinancialStatementFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.entityFinancialStatementFile.fileName}" readonly tabindex="-1"/>
                <label class="control-label control-label_mandatory" for="entityFinancialStatementFileName">
                   <spring:theme code="licenseApplyEntityInformation.attachmentSection.entityFinancialStatementFile"/>
                </label>
                <div class="form-icon form-icon_browse">
                   <icon:upload/>
                </div>
                <div class="form-icon form-icon_reset js-inputFile-reset">
                   <icon:cross/>
                </div>
             </div>
             <div class="help-block" id="entityFinancialStatementFile-helper"></div>
          </div>
       </div>
       <div class="col-md-6">
          <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.commercialRegMainEntryFile ? "active" : ""}">
          <div class="form-group">
             <input id="commercialRegMainEntryFile" name="customCommercialRegMainEntryFile" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
             <input id="commercialRegMainEntryFileName" name="commercialRegMainEntryFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.commercialRegMainEntryFile.fileName}" readonly tabindex="-1"/>
             <label class="control-label control-label_mandatory" for="commercialRegMainEntryFileName">
                <spring:theme code="licenseApplyEntityInformation.attachmentSection.commercialRegMainEntryFile"/>
             </label>
             <div class="form-icon form-icon_browse">
                <icon:upload/>
             </div>
             <div class="form-icon form-icon_reset js-inputFile-reset">
                <icon:cross/>
             </div>
          </div>
          <div class="help-block" id="commercialRegMainEntryFile-helper"></div>
       </div>
    </div>
    <div class="col-md-6">
       <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.commercialRegBranch1File ? "active" : ""}">
       <div class="form-group">
          <input id="commercialRegBranch1File" name="customCommercialRegBranch1File" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
          <input id="commercialRegBranch1FileName" name="commercialRegBranch1FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.commercialRegBranch1File.fileName}" readonly tabindex="-1"/>
          <label class="control-label control-label_mandatory" for="commercialRegBranch1FileName">
             <spring:theme code="licenseApplyEntityInformation.attachmentSection.commercialRegBranch1File"/>
          </label>
          <div class="form-icon form-icon_browse">
             <icon:upload/>
          </div>
          <div class="form-icon form-icon_reset js-inputFile-reset">
             <icon:cross/>
          </div>
       </div>
       <div class="help-block" id="commercialRegBranch1File-helper"></div>
    </div>
    </div>
        <div class="col-md-6">
           <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.commercialRegBranch2File ? "active" : ""}">
           <div class="form-group">
              <input id="commercialRegBranch2File" name="customCommercialRegBranch2File" class="form-control js-inputFile validate__file" type="file" accept="application/pdf" value="" data-maxsize="5"/>
              <input id="commercialRegBranch2FileName" name="commercialRegBranch2FileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.commercialRegBranch2File.fileName}" readonly tabindex="-1"/>
              <label class="control-label control-label_mandatory" for="commercialRegBranch2FileName">
                 <spring:theme code="licenseApplyEntityInformation.attachmentSection.commercialRegBranch2File"/>
              </label>
              <div class="form-icon form-icon_browse">
                 <icon:upload/>
              </div>
              <div class="form-icon form-icon_reset js-inputFile-reset">
                 <icon:cross/>
              </div>
           </div>
           <div class="help-block" id="commercialRegBranch2File-helper"></div>
        </div>

</div>
</div>

    <div id="preApprovalNrAttachment" style="display: none">
    <div class="row">
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.financialStatementFile ? "active" : ""}">
                <div class="form-group">
                    <input id="financialStatementFile" name="customFinancialStatementFile" class="form-control js-inputFile validate__preApproval" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="financialStatementFileName" name="financialStatementFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.financialStatementFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="financialStatementFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.financialStatementFile"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.iqamaFile ? "active" : ""}">
                <div class="form-group">
                    <input id="iqamaFile" name="customIqamaFile" class="form-control js-inputFile validate__preApproval" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="iqamaFileName" name="iqamaFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.iqamaFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="iqamaFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.iqamaFile"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.letterOfSupportFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.crCertificateFile ? "active" : ""}">
                <div class="form-group">
                    <input id="crCertificateFile" name="customCrCertificateFile" class="form-control js-inputFile validate__preApproval" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="crCertificateFileName" name="crCertificateFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.crCertificateFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="crCertificateFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.crCertificateFile"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.gosiCertificateFile ? "active" : ""}">
                <div class="form-group">
                    <input id="gosiCertificateFile" name="customGosiCertificateFile" class="form-control js-inputFile validate__preApproval" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="gosiCertificateFileName" name="gosiCertificateFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.gosiCertificateFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="gosiCertificateFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.gosiCertificateFile"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.letterOfSupportFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="formInputFile ${not empty sagiaApplyEntityInfoForm.noObjectionCertificateFile ? "active" : ""}">
                <div class="form-group">
                    <input id="noObjectionCertificateFile" name="customNoObjectionCertificateFile" class="form-control js-inputFile validate__preApproval" type="file" accept="application/pdf" value="" data-maxsize="5"/>
                    <input id="noObjectionCertificateFileName" name="noObjectionCertificateFileName" class="form-control" type="text" value="" placeholder="${sagiaApplyEntityInfoForm.noObjectionCertificateFile.fileName}" readonly tabindex="-1"/>
                    <label class="control-label control-label_mandatory" for="noObjectionCertificateFileName"><spring:theme code="licenseApplyEntityInformation.attachmentSection.noObjectionCertificateFile"/></label>
                    <div class="form-icon form-icon_browse"><icon:upload/></div>
                    <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
<%--                    <div style="text-align: right;">${sagiaApplyEntityInfoForm.boardResolutionFile.code}</div>--%>
                </div>
                <div class="help-block"></div>
            </div>
        </div>
    </div>
    </div>
</div>

