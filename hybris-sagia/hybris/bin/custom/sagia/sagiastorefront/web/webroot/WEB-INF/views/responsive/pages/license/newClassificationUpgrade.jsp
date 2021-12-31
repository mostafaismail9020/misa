<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>
<%@ include file="/WEB-INF/tags/responsive/common/termsAndConditionsModal.tag" %>

<%-- TODO: SAH-552 --%>
<%--@elvariable id="encodedContextPath" type="java.lang.String"--%>

<div class="mainSection mainSection_dark">
    <div class="container">
        <div class="mainSection-header">
            <h1 class="mainSection-headline"><spring:theme code="classificationupgrade.classificationupgrade"/></h1>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
    <div class="container">
        <div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
            <a href="${encodedContextPath}/dashboard" class="btn btn_leftIconLink btn_darkLink">
                <span class="iconElement iconElement_closeBack"><icon:close/></span><spring:theme code="general.backtodashboard"/>
            </a>
        </div>
    </div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
    <div class="container">
        <form:form action="${encodedContextPath}/classifications/create"
                   enctype="multipart/form-data" method="post"
                   id="formClassificationUpgrade"
                   modelAttribute="classificationUpgradeFormData">
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider">
                        <div class="contentModule-actions contentModule-actions_spaceBetween">
                            <div class="contentModule-headline">
                                <icon:info/><spring:theme code="general.info"/>
                            </div>
                        </div>
                        <div class="classificationUpgrade-status classificationUpgrade-status_margin">
                            <spring:theme code="classificationupgrade.currentclassificationstatus"/>
                            <span id="current-classificationUpgrade-status" class="classificationUpgrade-status-value">${currentClassification}</span>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="formSelectBox">
                                    <div class="form-group">
                                        <select id="classificationUpgradeAppealList" name="appeal" class="js-select2-oneColumn form-control">
                                            <c:forEach items="${classifications}" var="classification">
                                                <option></option>
                                                <%--<option value="${classification.code}">${classification.name}</option>--%>
                                                <%--SAH-2226: for appeal, use the same value as displayed for POST to CRM--%>
                                                <option value="${classification.name}">${classification.name}</option>
                                            </c:forEach>
                                        </select>
                                        <label class="control-label" for="classificationUpgradeAppealList"><spring:theme code="classificationupgrade.upgrade"/></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panelModule panelModule_halfRadius panelModule_smallMargin">
                <div class="contentModule">
                    <div class="contentModule-section contentModule-section_noDivider">
                        <div class="contentModule-actions contentModule-actions_spaceBetween">
                            <div class="contentModule-headline">
                                <icon:documents/><spring:theme code="text.account.followup.supportDocuments"/>
                            </div>
                        </div>

                        <div class="classificationUpgrade-documents">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file01" name="files[0]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text01" name="text01" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory">
                                                <spring:theme code="classificationupgrade.applicationletterauthenticated"/>
                                            </label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file02" name="files[1]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text02" name="text02" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory">
                                                <spring:theme code="classificationupgrade.printoutfromsaudicustoms"/>
                                            </label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file03" name="files[2]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text03" name="text03" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory"><spring:theme code="classificationupgrade.gocicertificate"/></label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file04" name="files[3]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text04" name="text04" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory"><spring:theme code="classificationupgrade.currentclassificationletter"/></label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file05" name="files[4]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text05" name="text05" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory"><spring:theme code="classificationupgrade.nitaqatcertificate"/></label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="formInputFile formInputFile_btnBegin formInputFile_group">
                                        <div class="form-group">
                                            <input id="file06" name="files[5]" class="form-control js-inputFile" value="" type="file" accept="image/jpeg,application/pdf">
                                            <input id="text06" name="text06" class="form-control" value="" placeholder="" readonly="" tabindex="-1" type="text">
                                            <label class="control-label control-label_mandatory"><spring:theme code="classificationupgrade.patentregistered"/></label>
                                            <div class="form-icon form-icon_browse">
                                                <icon:upload/>
                                            </div>
                                            <div class="form-icon form-icon_reset js-inputFile-reset">
                                                <icon:cross/>
                                            </div>
                                            <div class="formInputFile-append">
                                                <span class="formInputFile-text formInputFile-text_tip js-tip"
                                                      style="position: relative;z-index: 1000;"
                                                      data-tip-title="Tooltip Information to be shown to the user."
                                                      data-original-title="" title=""><icon:tipInfo/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mainSection-linkActions mainSection-linkActions_spaceBetween mainSection-linkActions_hasPadding">
                <button type="button" class="btn btn-secondary" onclick="window.location.href='${encodedContextPath}/my-sagia/license/classifications'">
                    <spring:theme code="general.cancel"/>
                </button>
                <button type="submit" value="Submit request" class="btn btn-primary" onclick="return validateFormClassificationUpgrade(event)" <%--data-toggle="modal" data-target="#requestSubmitted"--%> >
                    <spring:theme code="general.submit"/>
                </button>
            </div>
        </form:form>
        <div class="modal fade" id="requestSubmitted" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-xs modal-dialog-centeredContent" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="modal-title"><spring:theme code="general.requestsubmitted"/></div>
                    </div>
                    <div class="modal-body">
                        <div class="modal-heroImage">
                            <icon:modal02/>
                        </div>
                        <div class="modal-description">
                            <spring:theme code="specialservices.wewillreviewmessage"/>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn_slim" data-dismiss="modal" onclick=""><spring:theme code="general.gotomydashboard"/></button>
                    </div>
                    <div class="modal-secondaryContent">
                        <div class="modal-headline"><spring:theme code="general.howwasyourexperience"/>?</div>
                        <div class="ratingModule">
                            <div class="ratingModule-star active"><icon:rating-star/></div>
                            <div class="ratingModule-star active"><icon:rating-star/></div>
                            <div class="ratingModule-star active"><icon:rating-star/></div>
                            <div class="ratingModule-star active"><icon:rating-star/></div>
                            <div class="ratingModule-star"><icon:rating-star-empty/></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

