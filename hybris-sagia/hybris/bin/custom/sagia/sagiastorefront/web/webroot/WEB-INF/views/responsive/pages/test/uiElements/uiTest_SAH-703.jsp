<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>





<section class="mainSection mainSection_white mainSection_noPaddingTop js-dashboard">

    <div class="container">


        <div class="dashboardWidget js-dashboardWidget">
            <c:if test="${editable}">
                <dashboard:addAndRemoveComponent checkboxIndex="7"/>
            </c:if>
            <div class="dashboardWidget-headline dashboardWidget-headline_mobile js-dashboardWidget-headline">
                <spring:theme code="dashboard.salaryEmp.title"/>
                <div class="dashboardWidget-headline-icon">
                    <icon:salary-and-employment/>
                </div>
            </div>
                            
            <div class="dashboardWidget-body  ">
                <div class="dashboardWidgetCharts">
                    <div class="dashboardWidget-headline  dashboardWidget-headline_desktop">
                        <div class="dashboardWidget-headline-text">
                            <spring:theme code="dashboard.salaryEmp.title"/>
                        </div>
                        <div class="dashboardWidget-headline-icon">
                            <icon:salary-and-employment/>
                        </div>
                    </div>
                    <div class="row row-eq-height">
                        <div class="col-md-4">
                            
                            <div class="dashboardWidgetCharts-canvas">
                                <canvas id="js-dashboardWidgetCharts-chart" height="200" ></canvas>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="dashboardWidgetCharts-seperator">
                                <div class="dashboardWidgetCharts-info">
                                    <div class="dashboardWidgetCharts-info-graphic">
                                        <div class="dashboardWidgetCharts-info-graphic-saudi">9</div>
                                        <div class="dashboardWidgetCharts-info-graphic-sum">15</div>
                                        <div class="dashboardWidgetCharts-info-graphic-eypat">6</div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="dashboardWidgetCharts-info-text">
                                                Saudi
                                            </div>
                                        </div>
                                        <div class="col col-1">
                                        </div>
                                        <div class="col">
                                            <div class="dashboardWidgetCharts-info-text">
                                                Expat
                                            </div>
                                        </div>
                                    </div>
                                    <button class="btn btn_link">
                                        Show Gender Path
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="dashboardWidgetCharts-seperator">
                                <div class="dashboardWidgetCharts-sum">
                                    <div class="dashboardWidgetCharts-sum-title">
                                        <spring:theme code="dashboard.salaryEmp.overallAvg"/>
                                    </div>
                                    <div class="dashboardWidgetCharts-sum-value">
                                        90.000
                                    </div>
                                    <div class="dashboardWidgetCharts-sum-title">
                                        <spring:theme code="dashboard.salaryEmp.saudiAvg"/>
                                    </div>
                                    <div class="dashboardWidgetCharts-sum-value">
                                        120.000
                                    </div>
                                    <div class="dashboardWidgetCharts-sum-title">
                                       <spring:theme code="dashboard.salaryEmp.expatAvg"/>
                                    </div>
                                    <div class="dashboardWidgetCharts-sum-value">
                                        60.000
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>

        
    </div>
</section>