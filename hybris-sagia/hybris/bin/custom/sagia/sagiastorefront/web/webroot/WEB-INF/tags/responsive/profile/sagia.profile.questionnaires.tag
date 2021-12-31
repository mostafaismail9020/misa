<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags/responsive/survey"%>

<c:if test="${hasLicense}">
	<div class="panelTabs-head" id="questionnairesTab">
		<spring:theme code="general.questionnaires" />
		<span id="surveyCount" class="notifyCount"></span>
	</div>
	<div class="panelTabs-body" id="questionnairesTabData">
		<div class="panelModule panelModule_halfRadius">
			<survey:surveyList />
		</div>
	</div>
</c:if>