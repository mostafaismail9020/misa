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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="hasLicense" required="false" type="java.lang.Boolean" %>

<c:set var="pageIsDashboard" value="${fn:containsIgnoreCase(requestScope['javax.servlet.forward.request_uri'], 'dashboard')}"/>

<c:if test="${hasLicense}">
    <div class="panelTabs-head" id="enquiriesTab"><spring:theme code="company.enquirescomplaints"/>
        <span id="complaintsCount" class="notifyCount"></span>
    </div>
    <div class="panelTabs-body" id="enquiriesTabData">
        <div class="panelModule panelModule_halfRadius">
            <div class="contentModule">
                <div class="contentModule-section">
                    <div class="contentModule-headline mw0">
                      <!-- <span class="iconElement iconElement_enquiry3"><icon:enquiry3/></span>--><spring:theme code="profile.enquiry.new"/>
                    </div>
                    <hr class="hr"/>
                    <div id="complaintInProgress" style="display: none">${complaintInProgress}</div>
                    <form:form id="createComplantForm" class="pt-4" action="${encodedContextPath}/complaints/create" enctype="multipart/form-data" method="post" modelAttribute="complaintFormData">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <spring:bind path="details.EnquiryType">
                                        <div class="form-group <c:if test="${status.error}">has-error</c:if>">
                                            <form:select class="js-select2-oneColumn" id="enquiryList" path="details.EnquiryType" mandatory = "true">
                                                <form:options items="${complaintFormData.enquiryTypes}" itemValue="catID" itemLabel="catDesc" htmlEscape="true" />
                                            </form:select>
                                            <span class="help-block"><form:errors path="details.EnquiryType"/></span>
                                            <label class="control-label control-label_mandatory"><spring:theme code="profile.enquiry.type"/></label>
                                        </div>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox">
                                    <spring:bind path="details.Category1">
                                        <div class="form-group <c:if test="${status.error}">has-error</c:if>">
                                            <form:select class="js-select2-oneColumn" id="categoriesOneList" path="details.Category1" mandatory = "true">
                                                <form:options itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                            </form:select>
                                            <span class="help-block"><form:errors path="details.Category1"/></span>
                                            <label class="control-label control-label_mandatory"><spring:theme code="general.category1"/></label>
                                        </div>
                                    </spring:bind>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="formSelectBox pb-3">
                                    <spring:bind path="details.Category2">
                                        <div class="form-group <c:if test="${status.error}">has-error</c:if>">
                                            <form:select class="js-select2-oneColumn" id="categoriesTwoList" path="details.Category2" mandatory = "true">
                                                <form:options itemValue="catID" itemLabel="catDesc" htmlEscape="true"/>
                                            </form:select>
                                            <span class="help-block"><form:errors path="details.Category2"/></span>
                                            <label class="control-label control-label_mandatory"><spring:theme code="general.category2"/></label>
                                        </div>
                                    </spring:bind>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="formSelectBox pb-3">
                                    <div class="form-group">
                                        <form:select class="js-select2-oneColumn" path="details.Branch" mandatory = "true">
                                             <!-- population is making in sagia.profile.js - reason for which I cut itemValue="description" itemLabel="description" -->
                                            <form:options items="${complaintFormData.branches}" htmlEscape="true"/>
                                        </form:select>
                                        <label class="control-label"><spring:theme code="general.branch"/></label>
                                    </div>
                                </div>
                            </div>

                            <div class="hiddenAttachmentsSection"></div>
                        </div>

                        <formElement:formInputBox idKey="details.Subject" labelKey="profile.enquiry.subject" path="details.Subject" inputCSS="text" labelCSS="control-label_mandatory" mandatory="true"/>
                        <formElement:formTextArea idKey="details.TextMsg" labelKey="profile.enquiry.message" path="details.TextMsg" labelCSS="control-label_mandatory"  areaCSS="form-control" mandatory="true"/>
                        <div class="row enquiryFiles pt-5 mt-4 pb-4 mb-5">
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile mb-5">
                                    <div class="form-group">
                                        <input id="file0" name="files[0]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text05" name="text05" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                        <label class="control-label"><spring:theme code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>

                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file1" name="files[1]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text02" name="text02" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                        <label class="control-label"><spring:theme code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><!--<icon:upload/>--><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file2" name="files[2]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text03" name="text03" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                        <label class="control-label"><spring:theme code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <!-- todo: formInputFile tag needs to be added -->
                                <div class="formInputFile">
                                    <div class="form-group">
                                        <input id="file3" name="files[3]" class="form-control js-inputFile" type="file" accept="image/jpeg,application/pdf" value=""/>
                                        <input id="text04" name="text04" class="form-control" type="text" value="" placeholder="" readonly tabindex="-1"/>
                                        <label class="control-label"><spring:theme code="general.labelforfile"/></label>
                                        <div class="form-icon form-icon_browse"><svg xmlns="http://www.w3.org/2000/svg" class="mt-4" width="29" height="29" viewBox="0 0 29 29">
  <g id="Icon_feather-upload" data-name="Icon feather-upload" transform="translate(-3.5 -3.5)">
    <path id="Path_2191" data-name="Path 2191" d="M31.5,22.5v6a3,3,0,0,1-3,3H7.5a3,3,0,0,1-3-3v-6" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2192" data-name="Path 2192" d="M25.5,12,18,4.5,10.5,12" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
    <path id="Path_2193" data-name="Path 2193" d="M18,4.5v18" fill="none" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"></path>
  </g>
</svg></div>
                                        <div class="form-icon form-icon_reset js-inputFile-reset"><icon:cross/></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="contentModule-actions contentModule-actions_centered contentModule-actions_insideSection">
                            <button class="btn btn_bold text-uppercase btn-bg w-25 pt-2 pb-2" type="submit" value="SUBMIT"><spring:theme code="profile.enquiry.create"/></button>
                        </div>
                    </form:form>
                </div>

                <div class="contentModule-section" id="ticketsSection">

                        <div class="contentModule-headline mw0">
                           <!-- <span class="iconElement iconElement_your-tickets"><icon:your-tickets/></span>-->
                            <spring:theme code="profile.yourTickets"/>
                        </div>
                        <div class="serviceTime" style="display: none">
                            <div class="serviceTime-label"><spring:theme code="average.service.time"/></div>
                            <div class="serviceTime-detail">
                                <c:choose>
                                    <c:when test="${(processingTime.days > 0)  ||  (processingTime.hours > 0)}">
                                        <span class="serviceTime-highlight">${processingTime.days}</span>
                                        <spring:theme code="average.processingTime.days"/>
                                        <span class="serviceTime-highlight">${processingTime.hours}</span>
                                        <spring:theme code="average.processingTime.hours"/>
                                    </c:when>
                                    <c:when test="${(processingTime.minutes > 0)  ||  (processingTime.seconds > 0)}">
                                        <span class="serviceTime-highlight">${processingTime.minutes}</span>
                                        <spring:theme code="average.processingTime.minutes"/>
                                        <span class="serviceTime-highlight">${processingTime.seconds}</span>
                                        <spring:theme code="average.processingTime.seconds"/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>

                     <hr class="hr"/>
                    <div class="contentModule-filter p-2 mb-4">
                        <select id="profileTicketSort" title="profileTicketSort" class="js-select2-oneColumn form-control" onchange="sortProfileTickets()">
                            <option value="status_asc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                            <option value="status_desc"><spring:theme code="sagia.sort.status"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                            <option value="number_asc" data-sort="asc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                            <option value="number_desc" data-sort="desc"><spring:theme code="sagia.sort.ticketNumber"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                            <option value="date_asc" data-sort="asc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.asc"/></option>
                            <option value="date_desc" data-sort="desc"><spring:theme code="sagia.sort.date"/>&nbsp;<spring:theme code="sagia.sort.desc"/></option>
                        </select>
                    </div>

                    <div class="tableModule">
                        <table class="tableModule-table" id="profileTicketsTable">
                            <thead class="tableModule-head">
                            <tr>
                                <th><spring:theme code="general.lastUpdate"/></th>
                                <th><spring:theme code="general.ticketNumber"/></th>
                                <%--<th><spring:theme code="general.enquiry.type"/></th>--%>
                                <th><spring:theme code="general.status"/></th>
                                <th><spring:theme code="general.options"/></th>
                            </tr>
                            </thead>
                            <tbody class="tableModule-body" id="ticketsTable"></tbody>
                        </table>
                    </div>
                    <div class="paginationModule paginationModule_loading">
                        <c:if test="${!pageIsDashboard}">
                            <div class="dashboardWidget-filter">
                                <diV>
                                    <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                </div>
                            </div>
                        </c:if>
                        <div class="paginationModule-wrapper">
                            <button class="paginationModule-control paginationModule-control_left" disabled><!--<icon:arrow_green_right/>--><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30.822" viewBox="0 0 30 30.822">
  <g id="Icon_feather-arrow-left" data-name="Icon feather-arrow-left" transform="translate(1 1.411)" opacity="0.4">
    <path id="Path_55" data-name="Path 55" d="M35.5,18H7.5" transform="translate(-7.5 -4)" fill="none" stroke="#8c8b8b" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
    <path id="Path_56" data-name="Path 56" d="M23.534,35.5,7.5,21.5l16.034-14" transform="translate(-7.5 -7.5)" fill="none" stroke="#8c8b8b" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
  </g>
</svg>
</button>
                            <div class="paginationModule-items">
                                <div class="loadingModule">
                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                    <div class="loadingModule-msg">Loading content ...</div>
                                </div>
                            </div>
                            <button class="paginationModule-control paginationModule-control_right"><!--<icon:arrow_green_right/>-->
                           <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30.822" viewBox="0 0 30 30.822">
  <g id="Icon_feather-arrow-left" data-name="Icon feather-arrow-left" transform="translate(1 1.411)">
    <path id="Path_55" data-name="Path 55" d="M7.5,18h28" transform="translate(-7.5 -4)" fill="none" stroke="#0489a3" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
    <path id="Path_56" data-name="Path 56" d="M7.5,35.5l16.034-14L7.5,7.5" transform="translate(4.466 -7.5)" fill="none" stroke="#0489a3" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"/>
  </g>
</svg>


                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="enquiryDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document"></div>
    </div>

    <table class="profileTicketsTemplateWrapper" style="display: none;">
        <tr>
            <td class="lastUpdate"></td>
            <td class="ticketNumber"></td>
            <%--<td class="enquiryType"></td>--%>
            <td class="status"></td>
            <td class="details">
                <a href="#" class="link" data-toggle="modal" data-complaint-id="" data-target="#enquiryDetail"><spring:theme code="profile.details.label"/></a>
                <span class="notifyCount"></span>
            </td>
        </tr>
    </table>
</c:if>
