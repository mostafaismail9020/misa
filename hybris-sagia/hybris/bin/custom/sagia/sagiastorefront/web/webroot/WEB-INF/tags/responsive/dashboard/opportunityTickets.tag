<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<div class="col col-12" id="yourOpportunities">
                        <div class="dashboardWidget js-dashboardWidget">
                            <div class="dashboardWidget-headline js-dashboardWidget-headline">
                                <span>
<!--                                     <a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect" -->
<!--                                        style="text-decoration: inherit;color: inherit"> -->
                                    <spring:theme code="dashboard.myopportunity.section"/>
                                    </a>
                                </span>
                            </div>
                            <div class="dashboardWidget-body">
                                <div class="dashboardWidgetTickets">
                                    <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                               <th><spring:theme code="dashboard.myopportunity.ticketdate"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketnumber"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketsubject"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketmessage"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketstatus"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketdetails"/></th>
                                            </tr>
                                            </thead>
                                            <tbody class="tableModule-body" id="ticketsTable">
													<!-- <tr>
														<td>3000000</td>
														<td>General Enquiry</td>
													</tr>
													<tr>
														<td>3000001</td>
														<td>Interested in Business Opportunity</td>
													</tr>
													<tr>
														<td>3000002</td>
														<td>Legal Advisory</td>
													</tr>
													<tr>
														<td>3000003</td>
														<td>Interested in Investment in Saudi</td>
													</tr>
													<tr>
														<td>3000004</td>
														<td>Media Enquirie</td>
													</tr> -->
                                                    <c:forEach items="${userOpportunityTickets}" var="leadTickets">
		                                                    <tr>
		                                                    <td> <fmt:formatDate value="${leadTickets.creationtime}" pattern="dd MM yyyy"/></td>
		                                                        
																<td class="ticketNumber">${leadTickets.ticketID}</td>
																<td>${leadTickets.sectorCategoryName}</td>
																<td>${leadTickets.message}</td>
																<td>${leadTickets.state}</td>
																 <td>
            <a href="javascript:void(0)" class="link dashboardWidgetTickets-btn"><spring:theme code="dashboard.ticket.details"/></a>
            <div class="dashboardWidgetTickets-count">1</div>
        </td>
															</tr>
                                                    </c:forEach>
											</tbody>
                                        </table>
                                    </div>
                                    
                                    <div class="paginationModule paginationModule_loading">
                                       <%--  <c:if test="${!pageIsDashboard}">
                                            <div style="width: 150px; position: absolute">
                                                <select class="paginationPicker js-select2-oneColumn form-control"></select>
                                            </div>
                                        </c:if> --%>
                                        <div class="paginationModule-wrapper">
                                            <button class="paginationModule-control paginationModule-control_left" disabled>
                                                <icon:arrow_green_right/>
                                            </button>
                                            <div class="paginationModule-items">
                                                <%-- <div class="loadingModule">
                                                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                                                    <div class="loadingModule-msg">Loading content ...</div>
                                                </div> --%>
                                            </div>
                                            <button class="paginationModule-control paginationModule-control_right">
                                                <icon:arrow_green_right/>
                                            </button>
                                        </div>
<%--                                             <div class="tableModule-headline">
                                                <a href="" data-redirect="payments-overview" class="js-page-redirect">
                                                    <spring:theme code="dashboard.viewall" text="View all"/>
                                                </a>
                                            </div>
 --%>                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>