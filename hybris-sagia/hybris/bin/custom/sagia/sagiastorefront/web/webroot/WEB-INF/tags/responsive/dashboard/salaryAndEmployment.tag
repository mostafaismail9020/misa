<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="editable" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>

<div class="dashboardWidget js-dashboardWidget">
    <c:if test="${editable}"><dashboard:addAndRemoveComponent checkboxIndex="2"/></c:if>
    <div class="dashboardWidget-headline dashboardWidget-headline_mobile js-dashboardWidget-headline">
        <spring:theme code="dashboard.salaryEmp.title"/>
        <div class="dashboardWidget-headline-icon"><icon:salary-and-employment/></div>
    </div>
                    
    <div class="dashboardWidget-body" id="salaryAndEmploymentSection">
        <div class="dashboardWidgetCharts">
            <div class="row row-eq-height hidden js-loadedContent">
                <div class="col-md-9">
                    <div class="dashboardWidget-headline  dashboardWidget-headline_desktop">
                        <div class="dashboardWidget-headline-text"><spring:theme code="dashboard.salaryEmp.title"/></div>
                        <div class="dashboardWidget-headline-icon"><icon:salary-and-employment/></div>
                    </div>
                    <div class="dashboardWidgetCharts-canvas">
                        <canvas id="js-dashboardWidgetCharts-chart"></canvas>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="dashboardWidgetCharts-seperator">
                        <div class="dashboardWidgetCharts-sum">
                            <div class="dashboardWidgetCharts-sum-title"><spring:theme code="dashboard.salaryEmp.overallAvg"/></div>
                            <div id="employmentOverallAverage" class="dashboardWidgetCharts-sum-value"></div>
                            <div class="dashboardWidgetCharts-sum-title"><spring:theme code="dashboard.salaryEmp.saudiAvg"/></div>
                            <div id="employmentSaudiAverage" class="dashboardWidgetCharts-sum-value"></div>
                            <div class="dashboardWidgetCharts-sum-title"><spring:theme code="dashboard.salaryEmp.expatAvg"/></div>
                            <div id="employmentExpatAverage" class="dashboardWidgetCharts-sum-value"></div>
                        </div>
                        <div class="dashboardWidgetCharts-info">
                            <div class="dashboardWidgetCharts-info-graphic">
                                <div id="employmentNoOfSaudiEmployees" class="dashboardWidgetCharts-info-graphic-saudi"></div>
                                <div id="employmentTotalNoOfEmployees" class="dashboardWidgetCharts-info-graphic-sum"></div>
                                <div id="employmentNoOfExpatEmployees" class="dashboardWidgetCharts-info-graphic-eypat"></div>
                            </div>

                            <div class="dashboardWidgetCharts-info-graphic hidden">

                                <div id="employmentNoOfMale" class="dashboardWidgetCharts-info-graphic-saudi"></div>
                                <div id="employmentTotalNoOfGender" class="dashboardWidgetCharts-info-graphic-sum"></div>
                                <div id="employmentNoOfFemales" class="dashboardWidgetCharts-info-graphic-eypat"></div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="dashboardWidgetCharts-info-text">
                                        <span class="dashboardWidgetCharts-toggleInfo">
                                            <spring:theme code="salaryandemployment.saudi"/>
                                        </span>
                                        <span class="dashboardWidgetCharts-toggleInfo hidden">
                                            <spring:theme code="salaryandemployment.male"/>
                                        </span>                                        
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="dashboardWidgetCharts-info-text">
                                        <span class="dashboardWidgetCharts-toggleInfo">
                                            <spring:theme code="salaryandemployment.expat"/>
                                        </span>
                                        <span class="dashboardWidgetCharts-toggleInfo hidden">
                                            <spring:theme code="salaryandemployment.female"/>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <button type="button" class="btn btn_link js-toggleChartInfo">
                                <span class="dashboardWidgetCharts-toggleInfo">
                                    <spring:theme code="salaryandemployment.showgenderpath"/>
                                </span>
                                <span class="dashboardWidgetCharts-toggleInfo hidden">
                                    <spring:theme code="salaryandemployment.shownationpath"/>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="js-preloadContainer">
                <div class="dashboardWidget-headline  dashboardWidget-headline_desktop">
                    <div class="dashboardWidget-headline-text"><spring:theme code="dashboard.salaryEmp.title"/></div>
                    <div class="dashboardWidget-headline-icon"><icon:salary-and-employment/></div>
                </div>
                <div class="loadingModule">
                    <div class="loadingModule-icon"><icon:loading-spinner /></div>
                    <div class="loadingModule-msg">Loading content ...</div>
                </div>                                 
            </div>
        </div>
    </div>
</div>
