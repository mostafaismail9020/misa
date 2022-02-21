<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<spring:htmlEscape defaultHtmlEscape="true" />
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/addons/customerticketingaddon/responsive/account"%>


<c:set var="requestType" value="supporttickets"/>
<c:if test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or bdUserGroup eq 'WOBDUserGroup'}">
<c:set var="requestType" value="opportunityrequests"/>
</c:if>

<c:if test="${not empty supportTicketForm}">
  <div id="global-alerts" class="global-alerts"></div>
  <div class="border-header header-account">
    <spring:theme code="text.account.${requestType}" />
  </div>
<c:if test="${bdUserGroup eq 'BDUserGroup' or bdUserGroup eq 'WOBDUserGroup'}">

  </c:if>
  <div class="row">
    <div class="container-lg col-md-12">
      <div class="account-section-content">
        <div class="account-section-form">
          <div id="customer-ticketing-alerts"></div>
          <form:form method="post" modelAttribute="supportTicketForm" enctype="multipart/form-data">
            <c:choose>
              <c:when test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or bdUserGroup eq 'WOBDUserGroup'}">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label control-label_mandatory label-color" for="text.account.supporttickets.createTicket.ticketCategory">
                        <spring:theme code="text.account.supporttickets.createTicket.ticketCategory" text="Category" />
                      </label>
                      <form:select path="ticketCategory" cssClass="form-control">
                        <c:choose>
                          <c:when
                            test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or bdUserGroup eq 'WOBDUserGroup'}">
                            <c:forEach var="category" items="${categories}">
                              <c:if test="${category eq 'OPPORTUNITYSUBMISSION'}">
                                <form:option value="${category}">
                                  <spring:message
                                    code="text.account.supporttickets.createTicket.ticketCategory.${category}" />
                                </form:option>
                              </c:if>
                            </c:forEach>
                          </c:when>
                          <c:otherwise>
                            <c:forEach var="category" items="${categories}">
                              <c:if test="${category ne 'OPPORTUNITYSUBMISSION'}">
                                <form:option value="${category}">
                                  <spring:message
                                    code="text.account.supporttickets.createTicket.ticketCategory.${category}" />
                                </form:option>
                              </c:if>
                            </c:forEach>
                          </c:otherwise>
                        </c:choose>
                      </form:select>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <formElement:formSelectBox idKey="createTicket-question4"
                        labelKey="text.account.supporttickets.bdu.question4" path="question4"
                        labelCSS="label-ticket-tight control-label_mandatory" mandatory="true"
                        skipBlank="false" skipBlankMessageKey="user.selectUserLocation"
                        items="${regions}" itemValue="name"
                        selectCSSClass="form-control js-secureportal-userEntity" />
                      <div id="NotEmpty-BDSupportTicketForm-question4" class="help-block"
                        style="display: none;">
                        <span id="subject-errors"></span>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label control-label_mandatory label-color"
                        for="text.account.supporttickets.bdu.question2">
                        <spring:theme code="text.account.supporttickets.bdu.question2"
                          text="Sector" />
                      </label>
                      <form:select path="question20" id="parentSector" cssClass="form-control"
                        idKey="createTicket-question2"
                        labelKey="text.account.supporttickets.bdu.question2"
                        labelCSS="label-ticket control-label_mandatory"
                        selectCSSClass="ticket-questions text-area" mandatory="true">
                        <c:forEach var="sector" items="${sectors}">
                          <form:option value="${sector.id}">${sector.name}</form:option>
                        </c:forEach>
                      </form:select>


                      <div id="NotEmpty-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;">
                        <span id="subject-errors"></span>
                      </div>
                      <div id="Size-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label control-label_mandatory label-color"
                        for="text.account.supporttickets.bdu.question20">
                        <spring:theme code="text.account.supporttickets.bdu.question20"
                          text="Subsector" />
                      </label>
                      <form:select path="question2" id="sector" cssClass="form-control"
                        idKey="createTicket-question2"
                        labelKey="text.account.supporttickets.bdu.question20"
                        labelCSS="label-ticket control-label_mandatory"
                        selectCSSClass="ticket-questions text-area" mandatory="true">
                      </form:select>
                      <div id="NotEmpty-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;">
                        <span id="subject-errors"></span>
                      </div>
                      <div id="Size-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <formElement:formInputBox idKey="createTicket-subject"
                      labelKey="text.account.supporttickets.bdu.question1" path="subject"
                      labelCSS="label-ticket-tight control-label_mandatory" inputCSS="text"
                      mandatory="true" />
                    <div id="NotEmpty-BDSupportTicketForm-subject" class="help-block"
                      style="display: none;">
                      <span id="subject-errors"></span>
                    </div>
                    <div id="Size-BDSupportTicketForm-subject" class="help-block"
                      style="display: none;"></div>
                  </div>
                </div>

                <div class="ticket-questions-area">
                  <account:supportTicketQuestions />
                </div>
                <input type="hidden" name="questions" id="questions">
              </c:when>
              <c:otherwise>
                <c:if test="${not empty categories}">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label control-label_mandatory label-color"
                          for="text.account.supporttickets.createTicket.ticketCategory">
                          <spring:theme code="text.account.supporttickets.createTicket.ticketCategory" text="Category" />
                        </label>
                        <form:select path="ticketCategory" cssClass="form-control">
                          <c:choose>
                            <c:when
                              test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or bdUserGroup eq 'WOBDUserGroup'}">
                              <c:forEach var="category" items="${categories}">
                                <c:if test="${category eq 'OPPORTUNITYSUBMISSION'}">
                                  <form:option value="${category}">
                                    <spring:message code="text.account.supporttickets.createTicket.ticketCategory.${category}" />
                                  </form:option>
                                </c:if>
                              </c:forEach>
                            </c:when>
                            <c:otherwise>
                              <c:forEach var="category" items="${categories}">
                                <c:if test="${category ne 'OPPORTUNITYSUBMISSION'}">
                                  <form:option value="${category}">
                                    <spring:message
                                      code="text.account.supporttickets.createTicket.ticketCategory.${category}" />
                                  </form:option>
                                </c:if>
                              </c:forEach>
                            </c:otherwise>
                          </c:choose>
                        </form:select>
                      </div>
                    </div>
                  </div>
                </c:if>

                <div class="row">
                  <div class="col-md-6">
                    <formElement:formInputBox idKey="createTicket-subject"
                      labelKey="text.account.supporttickets.bdu.subject" path="subject"
                      labelCSS="label-ticket control-label_mandatory" inputCSS="text"
                      mandatory="true" />
                    <div id="NotEmpty-BDSupportTicketForm-subject" class="help-block"
                      style="display: none;">
                      <span id="subject-errors"></span>
                    </div>
                    <div id="Size-BDSupportTicketForm-subject" class="help-block"
                      style="display: none;"></div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group" style="display: none">
                      <label class="control-label control-label_mandatory label-color"
                        for="text.account.supporttickets.bdu.question2">
                        <spring:theme code="text.account.supporttickets.bdu.question2"
                          text="Sector" />
                      </label>
                      <form:select path="question2" id="sector" cssClass="form-control"
                        idKey="createTicket-question2"
                        labelKey="text.account.supporttickets.bdu.question2"
                        labelCSS="label-ticket control-label_mandatory"
                        selectCSSClass="ticket-questions text-area" mandatory="true">
                        <c:forEach var="sector" items="${sectors}">
                          <form:option value="${sector.id}">${sector.name}</form:option>
                        </c:forEach>
                      </form:select>

                      <div id="NotEmpty-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;">
                        <span id="subject-errors"></span>
                      </div>
                      <div id="Size-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group" style="display: none">
                      <label class="control-label control-label_mandatory label-color" for="text.account.supporttickets.bdu.question2">
                        <spring:theme code="text.account.supporttickets.bdu.question2" text="Sector" />
                      </label>
                      <form:select path="question2" id="sector" cssClass="form-control"
                        idKey="createTicket-question2"
                        labelKey="text.account.supporttickets.bdu.question2"
                        labelCSS="label-ticket control-label_mandatory"
                        selectCSSClass="ticket-questions text-area" mandatory="true">
                        <c:forEach var="sector" items="${sectors}">
                          <form:option value="${sector.id}">${sector.name}</form:option>
                        </c:forEach>
                      </form:select>

                      <div id="NotEmpty-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;">
                        <span id="subject-errors"></span>
                      </div>
                      <div id="Size-BDSupportTicketForm-question2" class="help-block"
                        style="display: none;"></div>
                    </div>
                  </div>
                </div>

                <div class="ticket-questions-area">
                  <account:supportTicketQuestions />
                </div>
                <input type="hidden" name="questions" id="questions">
              </c:otherwise>
            </c:choose>

            <div class="row">
              <div class="col-md-12 col-xl-12">
                <form:hidden path="message" />
              <%--<formElement:formTextArea idKey="createTicket-message"
                labelKey="text.account.supporttickets.createTicket.message" path="message" mandatory="true" areaCSS="form-control"
                labelCSS="control-label" />--%>
              <div id="NotEmpty-BDSupportTicketForm-message" class="help-block" style="display: none;"></div>
            <div id="Size-BDSupportTicketForm-message" class="help-block" style="display: none;"></div>
            <div class="form-group file-upload js-file-upload">
              <label class="control-label file-upload__label" for="files">
                <spring:theme code="text.account.supporttickets.createTicket.selectFile" text="Select a file" />
              </label>
              <div class="file-upload__wrapper btn file-upload-button">
                <span>
                  <spring:theme code="text.account.supporttickets.createTicket.chooseFile" text="Choose file" />
                </span>
                <input type="file" name="files" id="attachmentFiles" multiple size="60"
                  class="file-upload__input js-file-upload__input" data-max-upload-size="${maxUploadSize}" />
              </div>
              <span class="file-upload__file-name js-file-upload__file-name">
                <spring:theme code="text.account.supporttickets.createTicket.noFileChosen" />
              </span>
            </div>

      <%--Associated Objects--%>
      <%-- <c:if test="${not empty associatedObjects}"> --%>
      <%-- <label class="control-label" for="text.account.supporttickets.createTicket.associatedTo.option1">
      <spring:theme code="text.account.supporttickets.createTicket.associatedTo" text="Associated To" /></label> --%>

      <%-- <form:select path="associatedTo" cssClass="form-control"> --%>
      <%-- <option>
        <spring:theme
          code="text.account.supporttickets.createTicket.associatedTo.option1"
          text="Please select"></spring:theme>
        </option> --%>
      <%-- <c:forEach var="associatedMap" items="${associatedObjects}"> --%>
      <%-- <c:forEach var="associatedItem" items="${associatedMap.value}"> --%>
      <%-- <form:option value="${associatedMap.key}=${associatedItem.code}"> --%>
      <%-- <c:choose> --%>
      <%-- <c:when test="${'SavedCart' eq associatedItem.type }">
        <spring:message code="text.account.supporttickets.createTicket.${associatedItem.type}" />
        : ${associatedItem.code};
        <spring:message
          code="text.account.supporttickets.createTicket.updated" />:
        <fmt:formatDate pattern="dd/MM/yy"
          value="${associatedItem.modifiedtime}" />
        </c:when> --%>
        <%-- <c:otherwise>
          <spring:message code="text.account.supporttickets.createTicket.${associatedMap.key}" />
          : ${associatedItem.code};
          <spring:message
            code="text.account.supporttickets.createTicket.updated" />:
          <fmt:formatDate pattern="dd/MM/yy"
            value="${associatedItem.modifiedtime}" />
          </c:otherwise> --%>
        <%-- </c:choose> --%>
        <%-- </form:option> --%>
        <%-- </c:forEach> --%>
        <%-- </c:forEach> --%>
        <%-- </form:select> --%>
        <%-- </c:if> --%>

        <div>
          <spring:theme code="text.account.supporttickets.createTicket.note" />
        </div>

        <div id="customer-ticketing-buttons"
          class="form-actions">
          <div class="accountActions">
            <div class="d-flex space-around align-center flex-wrap">
              <div class="col-md-6 accountButtons">
                <a href="support-tickets" class="btn btn-sagia btn-block"><!-- width-350 -->
                  <spring:theme code="text.account.supporttickets.createTicket.back" text="Cancel" />
                </a>
              </div>
              <div class="col-md-6 accountButtons">
                <ycommerce:testId
                  code="supportTicket_create_button">
                  <button class="btn btn-sagia btn-sagia-green btn-block" type="button" id="addTicket"><!-- width-350 -->
                    <spring:theme code="text.account.supporttickets.createTicket.submit" text="Submit" />
                  </button>
                </ycommerce:testId>
              </div>
            </div>
          </div>
        </div>

              </div>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>

  <div style="display: none">
    <span id="supporttickets-tryLater">
      <spring:theme code="text.account.supporttickets.tryLater" />
    </span>
    <span id="attachment-file-max-size-exceeded-error-message">
      <spring:theme code="text.account.supporttickets.fileMaxSizeExceeded" />
    </span>
    <span id="file-too-large-message">
      <spring:theme code="text.account.supporttickets.file.is.large.than"
        arguments="${maxUploadSizeMB}" />
    </span>
  </div>
  <common:globalMessagesTemplates />
</c:if>