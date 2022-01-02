<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<spring:htmlEscape defaultHtmlEscape="true" />


<c:set var="requestType" value="supporttickets"/>
<c:if test="${bdUserGroup eq 'BDUserGroup' or nipcUserGroup eq 'NIPCUserGroup' or nipcUserGroup eq 'WOBDUserGroup'}">
  <c:set var="requestType" value="opportunityrequests"/>
</c:if>

<c:if test="${!isWOAUGMember}">
  <div class="border-header">
    <div class="header-account ${empty supportTickets ? '':'no-border'}">
      <spring:theme code="text.account.${requestType}"  />
    </div>
    <div class="account-section-header-add pull-right">
      <a href="add-support-ticket" class="btn btn-sagia btn-sagia-green btn-block add-address" id="add-support-ticket-btn">
        <spring:theme code="text.account.${requestType}.requestSupport" text="Opportunity Requests" />
      </a>
    </div>
  </div>
</c:if>
<c:set var="searchUrl" value="/my-account/support-tickets?sort=${searchPageData.pagination.sort}"/>

<c:if test="${isNipcMember}">
  <c:set var="searchUrl" value="/my-account/support-tickets?sort=${searchPageData.pagination.sort}&oppType=${oppType}&sec=${sec}"/>
</c:if>

<c:if test="${empty searchPageData.results}">
	<div class="account-section-content col-md-6 col-md-push-3 content-empty">
    <spring:theme code="text.account.${requestType}.noSupporttickets" text="You have no Opportunity requests" />
	</div>
</c:if>

<div class="clearfix visible-md-block visible-lg-block"></div>

<div class="customer-ticketing account-overview-table">
	<c:if test="${not empty searchPageData.results}">

  <nav:pagination top="false" msgKey="text.account.supportTickets.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}"  numberPagesShown="${numberPagesShown}"/>

    <table class="responsive-table">
      <thead>
        <tr class="table-header-gold hidden-xs">
          <th><spring:theme code="text.account.${requestType}.ticketId" text="Ticket ID" /></th>
          <th><spring:theme code="text.account.supporttickets.subject" text="Subject" /></th>
          <th><spring:theme code="text.account.supporttickets.dateCreated" text="Date Created" /></th>
          <th><spring:theme code="text.account.supporttickets.dateUpdated" text="Date Updated" /></th>
          <th class="supportTicketsTableState"><spring:theme code="text.account.supporttickets.status" text="Status" /></th>
          <c:if test="${isNipcMember or isWOAUGMember}">
            <th><spring:theme code="text.account.supporttickets.sector" text="Sector" /></th>
            <th><spring:theme code="text.account.supporttickets.unit" text="Entity" /></th>
          </c:if>
        </tr>
      </thead>

      <tbody>
        <c:forEach items="${searchPageData.results}" var="supportTicket">
          <c:url value="/my-account/support-ticket/${supportTicket.id}" var="myAccountsupportTicketDetailsUrl" />
          <tr class="responsive-table-item bg-white border-bottom table-body-font">
            <td class="hidden-sm hidden-md hidden-lg">
              <spring:theme code="text.account.supporttickets.ticketId" text="Ticket ID" />
            </td>
            <td>
              <a href="${myAccountsupportTicketDetailsUrl}" class="table-url-color"><c:out value="${supportTicket.id}" /></a>
            </td>

            <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.subject" text="Subject" /></td>
            <td class="break-word"><a href="${myAccountsupportTicketDetailsUrl}" class="table-subject"><c:out value="${supportTicket.subject}" /></a></td>

            <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.dateCreated" text="Date Created" /></td>
            <td><fmt:formatDate value="${supportTicket.creationDate}" pattern="dd-MM-yy hh:mm a" /></td>

            <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.dateUpdated" text="Date Updated" /></td>
            <td><fmt:formatDate value="${supportTicket.lastModificationDate}" pattern="dd-MM-yy hh:mm a" /></td>

            <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.status" text="Status" /></td>
            <td><spring:message code="ticketstatus.${fn:toUpperCase(supportTicket.status.id)}"/></td>

            <c:if test="${isNipcMember or isWOAUGMember}">
              <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.sector" text="Sector" /></td>
              <td><c:out value="${supportTicket.sector}" /></td>

              <td class="hidden-sm hidden-md hidden-lg"><spring:theme code="text.account.supporttickets.unit" text="Entity" /></td>
              <td><c:out value="${supportTicket.unit}" /></td>
            </c:if>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <!-- <nav:pagination top="false" msgKey="text.account.supportTickets.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}"  numberPagesShown="${numberPagesShown}"/> -->

  </c:if>
</div>