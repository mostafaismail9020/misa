<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<script>
    var configuredFileSize = ${maxUploadSize};
</script>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="realEstate.management"/></h1>
            
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <div class="d-flex row renewal-services w-100">
                <div class="col-md-3">
                    <a href="${request.contextPath}/real-estate" class="btn btn_leftIconLink btn_darkLink back_to_service"><span class="iconElement iconElement_closeBack"><icon:close/></span>
                        <spring:theme code="text.specialservices.backToServiceDetails"/>
                    </a>
                </div>
                <div class="col-md-9 d-flex btn-drafts_list amend-service-link">
                    <button class="btn btn_round btn_slim js-save-draft" data-target-form="createRealEstateForm"
                            data-service-id="${serviceId}">
                        <spring:theme code="general.savedraft"/><span class="iconElement iconElement_save"><icon:save/></span>
                    </button>
                    <button class="btn btn_round btn_slim js-load-draft" <c:if test="${!draftExists}">style="display: none"</c:if>
                            data-target-form="createRealEstateForm"
                            data-service-id="${serviceId}">
                        <spring:theme code="general.loaddraft"/><span class="iconElement iconElement_save"><icon:upload/></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <c:url value="/real-estate/save" var="createRealEstateUrl"/>
        <form:form id="createRealEstateForm" method="post" modelAttribute="realEstate" action="${createRealEstateUrl}" enctype="multipart/form-data">
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section">
                        <div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap">
                            <div class="contentModule-headline headline-text">
                                <icon:info/>
                                <spring:theme code="text.headertext.info"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select path="requestType" cssClass="js-select2-oneColumn request-type" disabled="${empty allowBuy or allowBuy eq false}">
                                        <c:if test="${allowBuy eq true}"><option></option></c:if>
                                            <form:options items="${requestType}"/>
                                        </form:select>
                                        <label class="control-label control-label_mandatory" for="requestType"><spring:theme code="general.requesttype"/></label>
                                    </div>
                                    <c:if test="${fn:length(requestType) eq 1}">
                                        <input type="hidden" name="requestType" value="${requestType.get('Sell')}"/>
                                    </c:if>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <form:select path="purchaseType" cssClass="js-select2-oneColumn realEstate-type" disabled="${allowBuy eq true}">
                                            <option></option>
                                            <form:options items="${purchaseType}"/>
                                        </form:select>
                                        <label class="control-label control-label_mandatory" for="purchaseType"><spring:theme code="realEstateDetails.realEstatetype"/></label>
                                    </div>
                                    <div class="help-block"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="realEstateAddInputs">
                        	<div class="col-md-3">
								<div class="formSelectBox">
									<div class="form-group">
                                        <form:select path="identityType" cssClass="js-select2-oneColumn identity-type" id="identityType">
                                            <option></option>
                                            <form:options items="${identityType}"/>
                                        </form:select>
                                        <label class="control-label control-label_mandatory" for="identityType"><spring:theme code="realEstate.identitytype"/></label>
                                    </div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="formInputBox">
									<div class="form-group">
										<form:input path="identityNumber" id="identityNumber" name="identityNumber" class="form-control"
											placeholder="." value="" type="text" disabled="true"/> <label
											class="control-label control-label_mandatory" for="identityNumber"><spring:theme
												code="realEstate.identityNumber" /></label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="col-md-3" id="deedNumberDiv">
								<div class="formInputBox">
									<div class="form-group">
										<form:input path="deedNumber" id="deedNumber" name="deedNumber" class="form-control"
											placeholder="." value="" type="text" disabled="true"/> <label
											class="control-label control-label_mandatory" for="deedNumber"><spring:theme
												code="realEstate.deedNumber" /></label>
									</div>
									<div class="help-block"></div>
								</div>
							</div>
							<div class="col-md-3">
								<%-- <div class="col-6 col-md-2" id = "align" style="padding:15px">
									<button type="submit" class="btn btn_round" name="smsverify" id="mobileBtn"><spring:theme code="text.verification.verify.mobile"/></button>
								</div> --%>
								<div class="formInputBox">
									<div class="form-group">
										<button type="submit" class="btn btn_round" name="getDeeds" id="getDeeds">
											<spring:theme code="realEstate.getDeedList"/>
										</button>
									</div>
								</div>
							</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="realEstateAddInputDetails" hidden="true">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                        	<div class="contentModule-headline headline-text">
                                <icon:info/>
                                <spring:theme code="realEstate.headertext.propinfo"/> 
                            </div>
                            <div class="tableModule tableModule_striped" >
					            <table class="tableModule-table" id="deedTable">
					                <thead class="tableModule-head">
					                    <tr>
					                    	<th><spring:theme code="realEstate.selectDeed"/></th>
					                        <th class="tableModule-headItem"><spring:theme code="realEstate.deedNumber"/></th>
					                        <th><spring:theme code="realEstate.deedDate"/></th>
					                        <th><spring:theme code="realEstate.region"/></th>
					                        <th><spring:theme code="realEstate.city"/></th>
					                    </tr>
					                </thead>
					                <tbody class="tableModule-body">
					                </tbody>
					            </table>
					        </div>
                            <div class="row">
                            </div>
                        </div>
                   	</div>
                </div>
             </div>

            <div id="realEstateDetails" hidden="true">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-headline headline-text">
                                <icon:documents/>
                                <div id="realEstateTypeTitle" style="display:inline-block;"></div>
                                <span class="iconElement iconElement_headlineTooltip js-tip" data-tip-title="Lorem Ipsum" data-original-title="" title=""><icon:tipInfo/></span>
                            </div>
							<!-- <input type="hidden" name="isMojVerified" id="isMojVerified" /> -->
							<form:hidden id="isMojVerified" path="mojVerified"/>
                            <div class="row">
                                <div class="col-md-6">
                                    <div id="plotNo1">
                                        <div class="form-group" style="position: relative;">
                                            <div class="formInputClass">
                                                <formElement:formInputBox inputCSS="form-control"
                                                                          idKey="realEstate.plotNo"
                                                                          labelKey="realEstate.plotNo"
                                                                          labelCSS="control-label_mandatory"
                                                                          path="plotNo"/>
                                            </div>
                                            <div class="formInputBox-append iconClass">
                                                <span class="formInputBox-text formInputBox-text_tip js-tip"
                                                      style="display:inline;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label id="addPlotNo" class="btn btn_link btn_inForm js-addPlot">
                                        <span>&#43;</span><spring:theme code="realestate.addplotno"/>
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div id="plotNo2" hidden>
                                        <div class="form-group">
                                            <formElement:formInputBox inputCSS="form-control" idKey="realEstate.plotNo" labelCSS="control-label_mandatory" labelKey="realEstate.plotNo" path="plotNo2"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div id="plotNo3" hidden>
                                        <div class="form-group">
                                            <formElement:formInputBox inputCSS="form-control" idKey="realEstate.plotNo" labelKey="realEstate.plotNo" path="plotNo3"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div id="plotNo4" hidden>
                                        <div class="form-group">
                                            <formElement:formInputBox inputCSS="form-control" idKey="realEstate.plotNo" labelKey="realEstate.plotNo" path="plotNo4"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div id="plotNo5" hidden>
                                        <div class="form-group">
                                            <formElement:formInputBox inputCSS="form-control" idKey="realEstate.plotNo" labelKey="realEstate.plotNo" path="plotNo5"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6" id="plotArea">
                                    <formElement:formInputBox idKey="realEstate.plotArea" labelKey="realEstate.plotArea" labelCSS="control-label_mandatory" path="plotArea"/>
                                </div>

                                <div class="col-md-6" id="deedDate" hidden="false">
                                    <div class="form-group">
                                        <div class="formInputClass">
                                            <formElement:formInputBox inputCSS="form-control js-form-control_date"
                                                                      idKey="realEstate.deedDate"
                                                                      labelKey="realEstate.deedDate"
                                                                      labelCSS="control-label_mandatory"
                                                                      path="purchaseDate"/>
                                        </div>
                                        <div class="formInputBox-append iconClass">
                                            <span class="formInputBox-text"><icon:calendar-gray/></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6" id="mojDeedDate" hidden="true">
	                                <div class="formInputBox">
										<div class="form-group">
											<form:input path="mojDeedDate" name="mojDeedDate" class="form-control"
												placeholder="." value="" type="text" /> 
												<label class="control-label" for="purchaseDate" idKey="realEstate.deedDate" labelKey="realEstate.deedDate"><spring:theme code="realEstate.deedDate"/></label>
										</div>
										<div class="help-block"></div>
									</div>
                                </div>

                                <div class="col-md-6" id="deedNo">
                                    <formElement:formInputBox idKey="realEstate.deedNo" labelKey="realEstate.deedNo" labelCSS="control-label_mandatory" path="deedNo" maxlength="50"/>
                                </div>

                                <div class="col-md-6" id="price">
                                    <div class="form-group">
                                        <div class="formInputClass">
                                            <formElement:formInputBox inputCSS="form-control" idKey="realEstate.price" labelCSS="control-label_mandatory" labelKey="realEstate.price" path="price"/>
                                        </div>
                                        <div class="formInputBox-append iconClass iconClass-currency">
                                            <span class="formInputBox-text"><spring:theme code="general.sar"/></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6" id="projectValue">
                                    <formElement:formInputBox idKey="realEstate.projectValue" labelKey="realEstate.projectValue" path="projectValue"/>
                                </div>

                                <div class="col-md-6" id="region" hidden="false">
                                    <input type="hidden" name="regionName" id="regionName"/>
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <form:select id="regionForm" path="region" cssClass="js-select2-oneColumn form-control">
                                                <form:options items="${regionCities}"
                                                              itemValue="${not empty itemValue ? itemValue :'RegionCode'}"
                                                              itemLabel="${not empty itemLabel ? itemLabel :'RegionDesc'}"
                                                              htmlEscape="true"/>
                                            </form:select>
                                            <label class="control-label" for="region" idKey="realEstate.region" labelKey="realEstate.region"><spring:theme code="text.account.profile.basic.region"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6" id="mojRegion" hidden="true">
	                                <div class="formInputBox">
										<div class="form-group">
											<form:input path="mojRegion" name="mojRegion" class="form-control"
												placeholder="." value="" type="text" /> 
												<label class="control-label" for="region" idKey="realEstate.region" labelKey="realEstate.region"><spring:theme code="text.account.profile.basic.region"/></label>
										</div>
										<div class="help-block"></div>
									</div>
                                </div>

                                <div class="col-md-6" id="housingTypeBox">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="housingType" name="housingType" class="js-select2-oneColumn form-control">
                                                <option></option>
                                                <option value="1"><spring:theme code="realestate.apartment"/></option>
                                                <option value="2"><spring:theme code="realestate.villa"/></option>
                                                <option value="3"><spring:theme code="realestate.land"/></option>
                                            </select>
                                            <label class="control-label" for="housingType"><spring:theme code="realestate.housingtype"/></label>
                                        </div>
                                    </div>
                                </div>

                                <form:hidden id="selectedCity" path="city"/>
                                <input type="hidden" name="cityName" id="cityName"/>
                                <div class="col-md-6" id="city" hidden="false">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <form:select name="cityList" path="" id="selectCity" cssClass="js-select2-oneColumn form-control">
                                            </form:select>
                                            <label class="control-label" for="" idKey="" labelKey=""><spring:theme code="text.account.profile.basic.city"/></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6" id="mojCity" hidden="true">
	                                <div class="formInputBox">
										<div class="form-group">
											<form:input path="mojCity" name="mojCity" class="form-control"
												placeholder="." value="" type="text" /> 
												<label class="control-label" for="" idKey="" labelKey=""><spring:theme code="text.account.profile.basic.city"/></label>
										</div>
										<div class="help-block"></div>
									</div>
                                </div>

                                <div class="col-md-6" id="district">
                                    <formElement:formInputBox idKey="realEstate.district" labelKey="realEstate.district" labelCSS="control-label_mandatory" path="district"/>
                                </div>

                                <div class="col-md-6" id="blockNo">
                                    <formElement:formInputBox idKey="realEstate.blockNo" labelKey="realEstate.blockNo" labelCSS="control-label_mandatory" path="blockNo"/>
                                </div>

                                <div class="col-md-6" id="unitNo">
                                    <formElement:formInputBox idKey="realEstate.unitNo" labelKey="realEstate.unitNo" labelCSS="control-label_mandatory" path="unitNo"/>
                                </div>

                                <div class="col-md-6" id="outsideMakkahBox">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="outsideMakkah" name="outsideMakkah" class="js-select2-oneColumn form-control">
                                                <option></option>
                                                <option value="0"><spring:theme code="general.yes"/></option>
                                                <option value="1"><spring:theme code="general.no"/></option>
                                            </select>
                                            <label class="control-label" for="outsideMakkah"><spring:theme code="realEstate.outsideMakkah"/></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6" id="thirtyMoreBox">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <select id="thirtyMore" name="thirtyMore" class="js-select2-oneColumn form-control">
                                                <option></option>
                                                <option value="0"><spring:theme code="general.no"/></option>
                                                <option value="1"><spring:theme code="general.yes"/></option>
                                            </select>
                                            <label class="control-label" for="thirtyMore"><spring:theme code="realEstate.thirtyMore"/></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formSelectBox">
                                        <div class="form-group">
                                            <form:select path="approvedIndustrial" cssClass="js-select2-oneColumn request-type">
                                                <option value="true"><spring:theme code="general.yes"/></option>
                                                <option value="false"><spring:theme code="general.no"/></option>
                                            </form:select>
                                            <label class="control-label" for="approvedIndustrial"><spring:theme code="realEstateDetails.approvedIndustrial"/></label>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6" id="mojIssuerCourtName">
	                                <div class="formInputBox">
										<div class="form-group">
											<form:input path="mojIssuerCourtName" name="mojIssuerCourtName" class="form-control"
												placeholder="." value="" type="text" /> 
												<label class="control-label" for="" idKey="" labelKey=""><spring:theme code="text.account.profile.basic.mojIssuerCourtName"/></label>
										</div>
										<div class="help-block"></div>
									</div>
                                </div>

                                <div class="col-md-6" id="remarks">
                                    <formElement:formInputBox idKey="realEstate.additionalDetails" labelKey="realEstate.additionalDetails" labelCSS="control-label_mandatory" path="remarks"/>
                                </div>
                            </div>
                            <div><spring:theme code="realEstate.type.note" /></div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="realEstateDocuments" hidden="true">
                <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                    <div class="contentModule">
                        <div class="contentModule-section">
                            <div class="contentModule-headline headline-text">
                                <icon:documents/>
                                <spring:theme code="text.account.followup.supportDocuments"/>
                                <span class="iconElement iconElement_headlineTooltip js-tip" data-tip-title="Lorem Ipsum" data-original-title="" title=""><icon:tipInfo/></span>
                            </div>

                            <div id="realEstateupload_placeholder" class="js-inputFile">
                                <c:if test="${not empty realEstate.documentsToUpload}">
                                    <c:forEach items="${realEstate.documentsToUpload}" var="document" varStatus="count">
                                        <div class="col-md-6">
                                            <div class="formInputFile <c:if test="${not empty document.resubmittedFileName}">active</c:if>">
                                                <div class="form-group <c:if test="${hasErrors && empty document.fileText}">has-error</c:if>">
                                                    <input id="fileId_${count.index}" name="files[${count.index}]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                                    <input id="fileTextId_${count.index}" name="fileText[${count.index}]" class="form-control uploadServiceFile" value="" placeholder="<c:if test="${not empty document.resubmittedFileName}">${document.resubmittedFileName}</c:if>" readonly="" tabindex="-1" type="text">
                                                    <label class="control-label control-label_mandatory">
                                                        <c:choose>
                                                            <c:when test="${empty document.fileText}">${document.attachmentName}</c:when>
                                                            <c:otherwise>${document.fileText}</c:otherwise>
                                                        </c:choose>
                                                    </label>
                                                    <input id="dockey_${count.index}" name="dockeyID[${count.index}]" type="hidden" value="dockeyID">
                                                    <div class="form-icon form-icon_browse">
                                                        <icon:upload/>
                                                    </div>
                                                    <div class="form-icon form-icon_reset js-inputFile-reset">
                                                        <icon:cross/>
                                                    </div>
                                                    <div class="help-block"></div>
                                                    <c:if test="${hasErrors && empty document.fileText}">
                                                        <div class="help-block">
                                                            <span id="create.realEstate.uploadError">
                                                                <spring:theme code="create.realEstate.uploadError" text="You must also upload a file in here."/>
                                                            </span>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
							<div><spring:theme code="sagia.upload.file.size.note" arguments="${maxUploadSize}"/></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_flexend mainSection-linkActions_hasPadding px-4 contentModule-actions">
                <div class="formCheckBox formCheckBox_belowPanel w-100">
                    <div class="form-group">
                        <div class="form-group">
                            <formElement:termsAndConditionsCheckbox event="REAL_ESTATE" id="termsAndConditions" path="termsAndConditionsChecked"/>
                        </div>
                    </div>
                </div>
                <button type="button" class="btn btn_leftIconLink btn_outline js-cancel-create-realEstate">
                    <spring:theme code="general.cancel"/>
                </button>
                
                <button type="submit" class="btn js-submit-create-realEstate" >
                    <spring:theme code="general.submit"/>
                </button>
            </div>
            <input type="hidden" name="csrfToken" value="${_csrf.token}" />
            <input type="hidden" id="serviceId"/>
        </form:form>
    </div>
</div>

<script>
    var serviceCategoryTypeCode = "${serviceCategoryTypeCode}";
</script>
