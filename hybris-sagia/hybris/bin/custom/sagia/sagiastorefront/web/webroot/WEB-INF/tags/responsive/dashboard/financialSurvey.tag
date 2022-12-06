<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="sector" required="false" type="com.sap.ibso.eservices.facades.data.SagiaSectorData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <div class="">
     <div class="Inc-title-header  py-3 py-sm-5 mt-3 mt-sm-0">
		   <h1 class="Inc-secdetil-enop-header text-uppercase text-center clr_gld">
			  <spring:theme code="dashboard.yourfinancialsurveys"/>
		   </h1>
      </div>
      <div class="col col-12 p-0" id="yourFinancialSurveys">
        <div class="dashboardWidget dash-fdi-survey js-dashboardWidget pt-3">
		 <div class="dashboardWidget-body">
		    <div class="dashboardWidgetTickets">
		      <div class="tableModule tableModule_slim dashboardWidgetTickets-table overflow-x-hidden">
				<div class="dashboardWidget-body">
                  <div class="dashboardWidgetTickets">
                    <div class="tableModule tableModule_slim dashboardWidgetTickets-table">
						<table class="tableModule-table">
							<thead class="tableModule-head">
							<tr>
								<th><spring:theme code="dashboard.financialsurveys.quarter"/></th>
								<th><spring:theme code="dashboard.financialsurveys.lastupdate"/></th>
								<th><spring:theme code="dashboard.financialsurveys.status"/></th>
								<%--<th><spring:theme code="dashboard.financialsurveys.connectWithUs"/></th>--%>
								<th><spring:theme code="dashboard.financialsurveys.completesurvey"/></th>
							</tr>
							</thead>
							<tbody class="tableModule-body" id="financialSurveysTable"></tbody>
						</table>
                    </div>
                   </div>
                 </div>
			  </div>
 			</div>
        </div>
        <div class="firstBlock-contact d-flex row mt-5 mb-3">
		<%--<div class="firstBlock-contact-local line-after col-md-6">
                                    <div>
                                        <img  alt="" src="${commonResourcePath}/images/Contact-us/local.png"/>
                                    </div>
                                    <div>
                                        <span class="firstBlock-contact-local-label">
                                            <spring:theme code="financial.survey.connectwithus.phone"/>
                                        </span>
                                    </div>
                                    <div>
                                        <span class="firstBlock-contact-local-number local-connect-number">
                                            <spring:theme code="financial.survey.connectwithus.phone.number"/>
                                        </span>
                                    </div>
                                </div>--%>
        <div class="firstBlock-contact-local col-md-3">
                                    <div>
                                        <img  alt="" src="${commonResourcePath}/images/Contact-us/email.png"/>
                                    </div>
                                    <div class="d-none d-md-block">
                                        <span class="firstBlock-contact-local-label">
                                           <spring:theme code="financial.survey.connectwithus.email"/>
                                        </span>
                                    </div>
                                    <div>
                                        <span class="firstBlock-contact-local-number emailBlock d-email-id">
                                            <spring:theme code="financial.survey.connectwithus.email.number"/>
                                        </span>
                                    </div>
                                </div>
        </div>
	 </div>
	</div>
  </div>
</div>
		<table class="financialSurveyTemplateWrapper" style="display:none;">
			<tr>
				<td id="quarter-id" class="quarter"></td>
				<td class="lastUpdate"></td>
				<td><div class="dashboardWidgetTickets-status-open"></div></td>
				<%--<td>
					<a href="javascript:void(0)" class="link dashboardWidgetFinancialSurvey-btn"><spring:theme code="dashboard.financialsurveys.completesurvey"/></a>
				</td>--%>
				<%--<td>
					<a href="javascript:void(0)" class="link dashboardWidgetFinancialSurveys-btn"><spring:theme code="dashboard.financial.survey.connectWithUs"/></a>
				</td>--%>
				<td>
					<div class="dashboardWidgetFinancialSurvey-btn">
						<a href="" class="" style=""></a>
					</div>
				</td>

			</tr>
		</table>
