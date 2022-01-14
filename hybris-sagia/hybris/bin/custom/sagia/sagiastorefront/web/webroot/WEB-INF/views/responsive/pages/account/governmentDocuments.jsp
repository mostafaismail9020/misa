<%@ page trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
                <%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
                    <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
                        <%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
                            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                                <%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
                                    <%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

                                        <div class="mainSection mainSection_dark">
                                            <div class="container">
                                                <div class="mainSection-header">
                                                    <h1 class="mainSection-headline">
                                                        <spring:theme code="general.governmentdocuments" />
                                                    </h1>
                                                    <c:if test="${not empty processingTime}">
                                                        <div class="serviceTime">
                                                            <div class="serviceTime-label">
                                                                <spring:theme code="average.service.time" />
                                                            </div>
                                                            <div class="serviceTime-detail">
                                                                <c:choose>
                                                                    <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                                                        <span class="serviceTime-highlight">${processingTime.days}</span>
                                                                        <spring:theme code="average.processingTime.days" />
                                                                        <span class="serviceTime-highlight">${processingTime.hours}</span>
                                                                        <spring:theme code="average.processingTime.hours" />
                                                                    </c:when>
                                                                    <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                                                        <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                                                        <spring:theme code="average.processingTime.minutes" />
                                                                        <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                                                        <spring:theme code="average.processingTime.seconds" />
                                                                    </c:when>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mainSection mainSection_dark mainSection_noPadding">
                                            <div class="container">
                                                <a href="${encodedContextPath}/my-sagia/sagia-profile" class="btn btn_leftIconLink btn_darkLink"><span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="govDocs.backToAccountOverview" /></a>
                                            </div>
                                        </div>
                                        <div class="mainSection mainSection_dark mainSection_pdt16">
                                            <div class="container">
                                                <form:form method="post" modelAttribute="branchData" action="${encodedContextPath}/governmentDocuments/branches">

                                                    <div class="panelModule panelModule_halfRadius">
                                                        <c:if test="${branchData.statusCode == 'V' || branchData.statusCode == 'I' }">
                                                            <c:set var="disabledFields" value="true" />
                                                            <c:set var="disabledTnC" value="disabled" />
                                                        </c:if>
                                                        <c:if test="${not empty registerFormError}">
                                                            <div class="formError">
                                                                <icon:messageError/>
                                                                <spring:theme code="${registerFormError}" arguments="" />
                                                            </div>
                                                        </c:if>
                                                        <div class="contentModule">

                                                            <div class="contentModule-section">
                                                                <div class="contentModule-headline">
                                                                    <!-- <span class="iconElement iconElement_register02"><icon:register02/></span>-->
                                                                    <spring:theme code="general.governmentdocuments.commercialregister" />
                                                                </div>
                                                                <hr class="hr" />
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <formElement:formInputBox idKey="govDocs.CRN" labelKey="govDocs.CRN" path="commercialRegisterNumber" inputCSS="form-control" mandatory="true" labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <formElement:formInputBox idKey="govDocs.gosiNo" labelKey="govDocs.gosiNo" path="gosiNumber" inputCSS="form-control" mandatory="true" labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <formElement:formInputBox idKey="govDocs.zakatNo" labelKey="govDocs.zakatNo" path="zakatNumber" inputCSS="form-control" mandatory="true" labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <formElement:formInputBox idKey="govDocs.molNo" labelKey="govDocs.molNo" path="molNumber" inputCSS="form-control" mandatory="true" labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                                                                    </div>
                                                                </div>
                                                            </div>


                                                            <div class="contentModule-section">
                                                                <div class="contentModule-headline">
                                                                    <!--<span class="iconElement iconElement_momra"><icon:momra/></span>-->
                                                                    <spring:theme code="govDocs.Momra" />
                                                                </div>
                                                                <hr class="hr" />
                                                                <div class="row">
                                                                    <div class="col">
                                                                        <div class="formRadioButton">
                                                                            <div class="form-group">

                                                                                <div class="form-item">
                                                                                    <form:radiobutton path="hasMomra" value="true" class="form-control gov-docs-momra" onchange="handleMomraRadioChange()" checked="checked" disabled="${disabledFields}"></form:radiobutton>
                                                                                    <label for="hasMomra1" class="control-label">
                            <span></span>
                            <spring:theme code="govDocs.momraY"/>
                          </label>
                                                                                </div>

                                                                                <div class="form-item">
                                                                                    <form:radiobutton path="hasMomra" value="false" class="form-control gov-docs-momra" onchange="handleMomraRadioChange()" disabled="${disabledFields}"></form:radiobutton>
                                                                                    <label for="hasMomra2" class="control-label">
                            <span></span>
                            <spring:theme code="govDocs.momraN"/>
                          </label>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <formElement:formInputBox idKey="govDocs.shopLicNo" labelKey="govDocs.shopLicNo" path="shopLicenseNumber" inputCSS="form-control gov-docs-shopLicNo" mandatory="true" labelCSS="control-label_mandatory" disabled="${disabledFields}" />
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <formElement:formSelectBox idKey="govDocs.amanah" labelKey="govDocs.amanah" selectCSSClass="form-control gov-docs-amanah" path="amanah" mandatory="true" skipBlank="false" itemLabel="name" itemValue="key" skipBlankMessageKey="form.select.empty" labelCSS="control-label_mandatory"
                                                                            items="${amanahs}" disabled="${disabledFields}" />
                                                                    </div>
                                                                </div>
                                                            </div>


                                                            <div class="contentModule-section">
                                                                <div class="contentModule-headline">
                                                                    <!--  <span class="iconElement iconElement_enquiry"><icon:enquiry2/></span>-->
                                                                    <spring:theme code="general.governmentdocuments.comments" />
                                                                </div>
                                                                <hr class="hr" />
                                                                <div class="row">
                                                                    <div class="col">
                                                                        <formElement:formTextArea areaCSS="form-control" idKey="govDocs.comments" labelKey="govDocs.comments" path="comments" mandatory="false" disabled="${disabledFields}" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </fieldset>
                                                    </div>

                                                    <div class="hidden">
                                                        <formElement:formInputBox idKey="" labelKey="" path="statusCode" inputCSS="hidden" />
                                                    </div>


                                                    <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding p-0">
                                                        <button type="reset" class="btn btn-outline btn_bold btn-normal">
              Cancel
            </button>
                                                        <div class="formCheckBox formCheckBox_belowPanel">
                                                            <div class="form-group">
                                                                <formElement:termsAndConditionsCheckbox event="GOVERNMENT_DOCUMENTS" id="termsAndConditions" path="termsAndConditionsChecked" containerCssClass="terms-and-condition" disabled="${disabledTnC}" />
                                                            </div>
                                                        </div>
                                                        <ycommerce:testId code="nextButton">
                                                            <button type="submit" class="btn btn-bg btn_bold btn-normal"><spring:theme code="govDocs.nextButton" />
              </button>
                                                        </ycommerce:testId>
                                                    </div>

                                                </form:form>

                                            </div>
                                        </div>


                                        <div class="modal fade" id="wassel-check" tabindex="-1" role="dialog" aria-labelledby="requestSubmittedApply" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <div class="modal-title"></div>
                                                        <button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
          <icon:close/>
        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="modal-heroImage">
                                                            <icon:modal02/>
                                                        </div>
                                                        <div class="modal-description">
                                                            <c:out value="${wasselCheckMessage}"></c:out>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button id="infoModalClose" type="button" class="btn btn_slim" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>