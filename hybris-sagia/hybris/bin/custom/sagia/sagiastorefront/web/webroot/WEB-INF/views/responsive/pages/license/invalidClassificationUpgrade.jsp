<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="account" tagdir="/WEB-INF/tags/responsive/user"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="mainSection mainSection_dark">
	<div class="container">
		<div class="mainSection-header">
			<h1 class="mainSection-headline">
				<spring:theme code="classificationupgrade.classificationupgrade" />
			</h1>
		</div>
	</div>
</div>

<div class="mainSection mainSection_dark mainSection_noPadding">
	<div class="container">
		<div
			class="mainSection-linkActions mainSection-linkActions_spaceBetween">
			<a href="${encodedContextPath}/dashboard"
				class="btn btn_leftIconLink btn_darkLink"><span
				class="iconElement iconElement_closeBack"><icon:close /></span> <spring:theme
					code="general.backtodashboard" /></a>
		</div>
	</div>
</div>

<div class="mainSection mainSection_dark mainSection_pdt16">
	<div class="container">
		<div class="expandableContent expanded" id="expand02">
			<div class="expandableContent-main">
				<div
					class="panelModule panelModule_halfRadius panelModule_smallMargin">
					<div class="contentModule">
						<div class="contentModule-section">
							<div
								class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_hasStatusIndicator">
								<div class="contentModule-headline">
									<span class="iconElement iconElement_info"><icon:info /></span>
									<spring:theme code="classificationupgrade.upgrade.invalid" />
								</div>
							 <div style="display: none" class="classificationUpgrade-status classificationUpgrade-status_margin">
                            		<spring:theme code="classificationupgrade.currentclassificationstatus"/>
                            		<span id="current-classificationUpgrade-status" class="classificationUpgrade-status-value">${currentClassification}</span>
                        		</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>