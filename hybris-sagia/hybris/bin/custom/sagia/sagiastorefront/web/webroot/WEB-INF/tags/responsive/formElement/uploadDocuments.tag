<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/responsive/format" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" type="java.lang.String" %>

<div class="documentModule js-upload-files-list" data-files-name="${name}">
    <c:if test="${not empty title}">
        <div class="contentModule-headline contentModule-headline_smallMargin"><icon:documents/>${title}</div>
    </c:if>

    <ul class="downloadList downloadList_secondary js-confirmed-files"></ul>

    <div class="contentModule-actions contentModule-actions_centered">
        <button class="btn js-upload-files"><spring:theme code="general.add"/></button>
    </div>

    <div class="modal fade js-upload-list-modal" tabindex="-1" role="dialog" aria-labelledby="uploadListModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title"><spring:theme code="uploaddocuments.uploadyourfiles"/></div>
                </div>
                <div class="modal-body">
                    <div class="formInputFileBox js-form-input-file-box">
                        <div class="form-group">
                            <div class="form-icon form-icon_browse">
                                <icon:upload/>
                            </div>
                            <input class="form-control js-input-file" type="file" accept="image/jpeg,application/pdf" multiple name="" id="fileBoxModal${name}"/>
                            <label class="control-label js-file-label" for="fileBoxModal${name}"><spring:theme code="uploaddocuments.chooseafile"/> <span class="formInputFileBox-dragndrop"><spring:theme code="uploaddocuments.draghere"/></span></label>
                        </div>
                    </div>

                    <div class="modal-description modal-description_largeMargin">
                        <ul class="downloadList downloadList_secondary js-file-list"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn_slim btn_round js-close-modal-btn"><spring:theme code="general.cancel"/></button>
                    <button type="button" class="btn btn_slim btn_round js-confirm-modal-btn"><spring:theme code="general.confirm"/></button>
                </div>
            </div>
        </div>
    </div>

    <div style="display: none">
        <ul>
            <li class="downloadList-item js-append-file js-loaded-file">
                <div class="downloadList-description">
                    <span class="iconElement iconElement_pdf"><icon:document/></span>
                    <span class="js-file-name"><spring:theme code="general.name"/></span>
                    <input name="${name}" class="js-file-code-input" type="hidden"/>
                </div>
                <div class="downloadList-actions">
                    <a href="#" class="link link_nowrap js-remove-file" style="display:none;">
                        <span class="iconElement iconElement_cloud02"><icon:remove/></span>
                        <spring:theme code="general.remove"/>
                    </a>
                    <img class="js-loading-spinner" src="${commonResourcePath}/images/spinner.gif"/>
                </div>
            </li>
        </ul>
    </div>
</div>