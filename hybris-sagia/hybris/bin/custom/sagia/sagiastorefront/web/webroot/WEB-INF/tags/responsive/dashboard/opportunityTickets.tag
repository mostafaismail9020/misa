<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="Inc-title-header py-5">
   <h1 class="Inc-secdetil-enop-header text-uppercase text-center clr_gld">
       <c:if test="${language eq 'en'}">
           <span class="clr_gld">${category.name}</span>&nbsp;<spring:theme code="dashboard.myopportunity.section"/>
       </c:if>
       <c:if test="${language eq 'ar'}">
           <span class="clr_gld"> <spring:theme code="dashboard.myopportunity.section"/>&nbsp;${category.name}</span>
       </c:if>
   </h1>
</div>

<div class="col col-12 p-0" id="yourOpportunities">
	<div class="dashboardWidget  potential-opportunities js-dashboardWidget">
    	<div class="dashboardWidget-headline js-dashboardWidget-headline">
        	<span>
			<!--	<a href="" data-redirect="my-sagia/sagia-profile#enquiriesTab" class="js-page-redirect" -->
			<!--                                        style="text-decoration: inherit;color: inherit"> </a> -->
				<spring:theme code="dashboard.myopportunity.section"/>        
    		</span>
		</div>
		
		<div class="dashboardWidget-body">
		    <div class="dashboardWidgetTickets">
		        <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
		        <c:choose>
                  <c:when test="${fn:length(userOpportunityTickets)>0}">
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
                        	<c:forEach items="${userOpportunityTickets}" var="leadTickets">
                            	<tr>
                                  	<td><fmt:formatDate value="${leadTickets.creationtime}" pattern="dd-MM-yyyy"/></td>                                          
									<td class="ticketNumber">${leadTickets.ticketID}</td>
									<td>${leadTickets.sectorCategoryName} 
										<c:if test="${not empty leadTickets.opportunityName}">
											- ${leadTickets.opportunityName}
										</c:if>
									</td>
									<td>${leadTickets.message}</td>
									<td>${leadTickets.state}</td>
									<td><a href="/potentialOpportunity/${leadTickets.ticketID}" class="link dashboardWidgetTickets-btn"><spring:theme code="dashboard.ticket.details"/></a></td>
									<!-- <td>
										<a href="javascript:void(0)" class="link dashboardWidgetTickets-btn"><spring:theme code="dashboard.ticket.details"/></a>
										    <div class="dashboardWidgetTickets-count">1</div>
									</td>  -->
								</tr>
                           </c:forEach>
						</tbody>
                   </table>
                </c:when>
            <c:otherwise>
              <spring:theme code="dashboard.license.no.data.available"/>
            </c:otherwise>
          </c:choose>
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
					<%--   <div class="tableModule-headline">
                                                <a href="" data-redirect="payments-overview" class="js-page-redirect">
                                                    <spring:theme code="dashboard.viewall" text="View all"/>
                                                </a>
                                            </div>
 					--%>                                    
 				</div>
            </div>
        </div>
    </div>
</div>
