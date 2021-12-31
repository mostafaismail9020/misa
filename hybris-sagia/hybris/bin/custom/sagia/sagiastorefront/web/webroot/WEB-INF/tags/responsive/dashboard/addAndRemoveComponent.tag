<%@ attribute name="checkboxIndex" type="java.lang.String"%>
<%@ attribute name="checkboxName" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ attribute name="switchEnabled" type="java.lang.Boolean" %>
<c:set var="switchEnabledVar" value="${empty switchEnabled ? true : switchEnabled}"/>

<div class="dashboardWidget-setting">
	<div class="formCheckBox formCheckBox_switch dashboardWidget-setting-switch">
		<input id="settingsCheckbox${checkboxIndex}" name="${checkboxName}" class="form-control" placeholder="." type="checkbox" value="entity name" ${switchEnabledVar ? "checked" : ""}>
		<label class="control-label " for="settingsCheckbox${checkboxIndex}"><span></span></label>
	</div>
</div>
