<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="canvasId" value="devicesUsedCanvas"/>

<h3><spring:theme code="text.customer360.devices.used" text="Devices Used"/></h3>
<div class="row overview-tab-charts">
    <div class="col-sm-12 devices-used-chart">
        <canvas id="${canvasId}" width="auto" height="200"></canvas>
        <script type="application/javascript">
            var labels = [];
            var values = [];
            <c:forEach items="${devicesUsedDatas}" var="device">
            ACC.sagiaasmaddon.buildArrayValues(labels, '${device.deviceName}');
            ACC.sagiaasmaddon.buildArrayValues(values, '${device.value}');
            </c:forEach>
            ACC.sagiaasmaddon.drawDoughnutChart("${canvasId}", labels, values);
        </script>
    </div>
</div>
