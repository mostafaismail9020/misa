<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="cts" tagdir="/WEB-INF/tags/addons/customerticketingaddon/cts"%>

<c:set var="requestType" value="supporttickets"/>
<c:if test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or woagUserGroup eq 'WOAGUserGroup'}">
  <c:set var="requestType" value="opportunityrequests"/>
</c:if>

<div class="border-header header-account">
  <spring:theme code="text.account.${requestType}.updateTicket.heading" text="Request Customer Support"/>
</div>

<div class="header-box">
  <div class="row">
    <div class="col-md-3 item-wrapper">
      <div class="item-group">
        <span class="item-label">
          <spring:message code="text.account.supporttickets.createTicket.subject" text="Subject" />
        </span>
        <span class="item-value">
          ${fn:escapeXml(ticketData.subject)}
        </span>
      </div>
    </div>

    <div class="col-md-3 item-wrapper">
      <div class="item-group">
        <span class="item-label">
          <spring:theme code="text.account.supporttickets.dateUpdated" text="Date Updated" />
        </span>
        <span class="item-value">
          <fmt:formatDate value="${ticketData.lastModificationDate}" pattern="dd-MM-yy hh:mm a" />
        </span>
      </div>
    </div>

    <div class="col-md-3 item-wrapper">
      <div class="item-group">
        <span class="item-label">
          <spring:theme code="text.account.supporttickets.dateCreated" text="Date Created" />
        </span>
        <span class="item-value">
          <fmt:formatDate value="${ticketData.creationDate}" pattern="dd-MM-yy hh:mm a" />
        </span>
      </div>
    </div>

    <div class="col-md-3 item-wrapper">
      <div class="item-group">
        <span class="item-label">
          <spring:theme code="text.account.supporttickets.status" text="Status" />
        </span>
        <span class="item-value">
          <spring:message code="ticketstatus.${fn:toUpperCase(ticketData.status.id)}" />
        </span>
      </div>
    </div>

    <%--Ticket Categories--%>
      <c:if test="${not empty ticketData.ticketCategory }">
        <div class="col-md-3 item-wrapper">
          <div class="item-group">
            <span class="item-label">
              <spring:theme code="text.account.supporttickets.createTicket.ticketCategory" text="Category" />
            </span>
            <span class="item-value">
              <spring:theme
                code="text.account.supporttickets.createTicket.ticketCategory.${ticketData.ticketCategory}" />
            </span>
          </div>
        </div>
      </c:if>

      <%--Ticket Blog Infos--%>
        <c:if test="${not empty ticketData.blogInfo}">
          <div class="col-md-3 item-wrapper">
            <div class="item-group">
              <span class="item-label">
                <spring:theme code="text.account.supporttickets.createTicket.bloginfo.name" text="Event Name" />
              </span>
              <span class="item-value">
                ${fn:escapeXml(ticketData.blogInfo.title)}
              </span>
            </div>
          </div>

          <div class="col-md-3 item-wrapper">
            <div class="item-group">
              <span class="item-label">
                <spring:theme code="text.account.supporttickets.createTicket.bloginfo.path" text="Event Path" />
              </span>
              <span class="item-value">
                ${fn:escapeXml(ticketData.blogInfo.postUrl)}
              </span>
            </div>
          </div>
        </c:if>

        <%--Ticket Location--%>
          <c:if test="${not empty ticketData.location }">
            <div class="col-md-3 item-wrapper">
              <div class="item-group">
                <span class="item-label">
                  <spring:theme code="text.account.supporttickets.createTicket.location" text="Location" />
                </span>
                <span class="item-value">
                  ${fn:escapeXml(ticketData.location)}
                </span>
              </div>
            </div>
          </c:if>

          <%--Ticket sector--%>
            <c:if test="${not empty ticketData.sector}">
              <div class="col-md-3 item-wrapper">
                <div class="item-group">
                  <span class="item-label">
                    <spring:theme code="text.account.supporttickets.createTicket.sector" text="Sector" />
                  </span>
                  <span class="item-value">
                    ${fn:escapeXml(ticketData.sector)}
                  </span>
                </div>
              </div>
            </c:if>

            <%--Associated Objects--%>
              <c:if test="${not empty ticketData.associatedTo}">
                <div class="col-md-3 item-wrapper">
                  <div class="item-group">
                    <span class="item-label">
                      <spring:theme code="text.account.supporttickets.createTicket.associatedTo" text="Associated To" />
                    </span>
                    <span class="item-value">
                      ${fn:substringBefore(fn:toUpperCase(ticketData.associatedTo), ';')}
                    </span>
                  </div>
                </div>
              </c:if>

              <%--Ticket sector--%>
                <c:if test="${not empty ticketData.opportunityMessage}">
                  <div class="col-md-3 item-wrapper">
                    <div class="item-group">
                      <span class="item-label">
                        <spring:theme code="text.account.supporttickets.createTicket.opportunityMessage"
                          text="Message" />
                      </span>
                      <span class="item-value">
                        ${fn:escapeXml(ticketData.opportunityMessage)}
                      </span>
                    </div>
                  </div>
                </c:if>
  </div>
</div>
<c:if test="${(woagUserGroup eq 'WOAGUserGroup') && (ticketData.status.id eq 'WOAGPENDINGAPPROVAL')}">
  <form:form method="get" modelAttribute="supportTicketForm" enctype="multipart/form-data"
    id="opportunitySupportTicketForm" action="#">
    <form:hidden path="id" value="${ticketData.id}" />
    <div class="modal-actions row" style="margin-top: 10px; margin-bottom: 10px; display: flex">
      <div class="col-xs-8 col-sm-8">
        <formElement:formTextArea idKey="opportunityMessage" labelKey="text.account.supporttickets.createTicket.message"
          path="opportunityMessage" areaCSS="form-control js-add-message opportunity-status-message"
          labelCSS="control-label" />
      </div>
      <div class="col-xs-4 col-sm-4 " style="display: flex; align-items: center;">
        <div class="col-sm-6">
          <ycommerce:testId code="supportTicket_update_button">
            <button class="btn btn-sagia btn-sagia-green btn-block" id="launch-approve-confirm" type="submit">
              <spring:theme code="text.account.supporttickets.approve.submit" text="Submit" />
            </button>
          </ycommerce:testId>
        </div>
        <div class="col-sm-6">
          <ycommerce:testId code="supportTicket_update_button">
            <button class="btn btn-sagia btn-sagia-red btn-block" id="launch-reject-confirm" type="submit">
              <spring:theme code="text.account.supporttickets.reject.submit" text="Submit" />
            </button>
          </ycommerce:testId>
        </div>
      </div>
    </div>
  </form:form>
</c:if>
<div class="container Opportunity_Request_Detail">
    <div class="row customer-ticketing-body">
    <div class="col-md-12">
        <%-- <div class="account-section-header account-section-header-secondary">
        <spring:theme code="text.account.supporttickets.messages" text="Messages" />
        <cms:component uid="AddMessageButtonComponent" evaluateRestriction="true" />
        </div> --%>

    <div class="cts-msg-history">
        <div style="display:none" id="ct-overlay-title">
        <div class="headline">
            <span class="headline-text">
            <spring:theme code="text.account.supporttickets.updateTicket.addMessage" text="Add Message" />
            </span>
        </div>
        </div>

        <spring:hasBindErrors name="supportTicketForm">
        <div class="updateSupportTicketValidationErrors"></div>
        </spring:hasBindErrors>

        <div style="display: none">
        <span id="supporttickets-tryLater">
            <spring:theme code="text.account.supporttickets.tryLater" />
        </span>
        <span id="attachment-file-max-size-exceeded-error-message">
            <spring:theme code="text.account.supporttickets.fileMaxSizeExceeded" />
        </span>
        <span id="file-too-large-message">
            <spring:theme code="text.account.supporttickets.file.is.large.than" arguments="${maxUploadSizeMB}" />
        </span>
        <span id="opportunity-approve-message">
            <spring:theme code="text.account.opportunity.approve.confirmation.message" arguments="${ticketData.id}" />
        </span>
        <span id="opportunity-reject-message">
            <spring:theme code="text.account.opportunity.reject.confirmation.message" arguments="${ticketData.id}" />
        </span>
        </div>
        <common:globalMessagesTemplates />

        <div style="display:none;" id="ct-add-new-msg">
        <div id="customer-ticketing-alerts"></div>
        <div id="global-alerts" class="global-alerts"></div>
        <c:if test="${woagUserGroup ne 'WOAGUserGroup'}">
            <form:form method="post" modelAttribute="supportTicketForm" enctype="multipart/form-data">

            <form:hidden path="subject" value="${ticketData.subject}" />
            <input id="currentTicketStatus" type="hidden" value="${ticketData.status.id}">
            <form:hidden path="id" value="${ticketData.id}" />
            <%-- <formElement:formTextArea idKey="createTicket-message"
                labelKey="text.account.supporttickets.createTicket.message" path="message"
                areaCSS="form-control js-add-message" labelCSS="control-label" /> --%>
            <formElement:formTextArea idKey="message" labelKey="text.account.supporttickets.createTicket.message"
                path="message" areaCSS="form-control js-add-message" labelCSS="control-label" />

            <div class="has-error">
                <div id="NotEmpty-supportTicketForm-message" class="help-block" style="display: none;"></div>
                <div id="Size-supportTicketForm-message" class="help-block" style="display: none;"></div>
            </div>
            <div class="form-group">
                <form:hidden path="status" value="${ticketData.status.id}" />
            </div>

            <div style="display:block;" class="cs_file_upload form-group file-upload js-file-upload">
                <label class="control-label file-upload__label" for="files">
                <spring:theme code="text.account.supporttickets.createTicket.selectFile" text="Select a file" />
                </label>
                <div class="file-upload__wrapper btn btn-sagia btn-small">
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

            <div class="modal-actions row">
                <div class="col-xs-12 col-sm-6 col-sm-push-6">
                <ycommerce:testId code="supportTicket_update_button">
                    <button class="btn btn-sagia btn-sagia-green btn-block" id="updateTicket" type="submit">
                    <spring:theme code="text.account.supporttickets.createTicket.submit" text="Submit" />
                    </button>
                </ycommerce:testId>
                </div>
                <div class="col-xs-12 col-sm-6 col-sm-pull-6">
                <a class="btn btn-sagia btn-block">
                    <spring:theme code="text.account.supporttickets.cancel" text="Cancel" />
                </a>
                </div>
            </div>
            </form:form>
        </c:if>
        </div>

        <c:choose>
        <c:when test="${not empty ticketData.messageHistory}">
            <div class="form-group">
            <label class="control-label">
                <spring:message code="text.account.supporttickets.createTicket.message.history" />
            </label>
            <p class="form-control-static">${ticketData.messageHistory}</p>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${woagUserGroup eq 'WOAGUserGroup'}">
                <cts:ticketEvents ticketData="${ticketData}" isWoapUser="true" />
            </c:when>
            <c:otherwise>
                <cts:ticketEvents ticketData="${ticketData}" isWoapUser="false" />
            </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </div>
    </div>
</div>
</div>
<common:confirmationModal/>