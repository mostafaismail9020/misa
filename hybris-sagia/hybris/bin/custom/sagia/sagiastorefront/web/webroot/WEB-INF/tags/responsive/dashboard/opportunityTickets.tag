<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="col col-12 p-0" id="yourOpportunities">
                        <div class="js-dashboardWidget">
                            <h1 class="dashboard-headline js-dashboardWidget-headline text-center mt-5 mb-3">
                                    <spring:theme code="dashboard.myopportunity.section"/>
                            </h1>
                            <div class="card dashboardWidget-body mb-5">
                                <div class="dashboardWidgetTickets">
                                    <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
                                        <table class="tableModule-table">
                                            <thead class="tableModule-head">
                                            <tr>
                                                <th><spring:theme code="dashboard.myopportunity.ticketnumber"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketsubject"/></th>
                                                <th><spring:theme code="dashboard.myopportunity.ticketstatus"/></th>
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
																<td>${leadTickets.ticketID}</td>
																<td>${leadTickets.headline}</td>
																<td>${leadTickets.state}</td>
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