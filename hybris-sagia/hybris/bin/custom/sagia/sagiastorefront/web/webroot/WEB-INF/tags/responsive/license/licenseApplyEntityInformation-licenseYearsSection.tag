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
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<div class="contentModule-section" id="licenseYearSection">
	<div class="contentModule-headline contentModule-headline_smallMargin">
		<spring:theme
			code="licenseApplyEntityInformation.licenseYearSection.title" />
	</div>
	<hr class="hr"/>
	<div class="row">
		<div class="col-md-6">
			<div class="formSelectBox">
				<div class="form-group">
					<select id="licenseYear" name="licenseYear"
						class="js-select2-oneColumn form-control">
					</select> <label class="control-label control-label_mandatory"
						for="licenseYear"><spring:theme
							code="license.apply.entity.licenseYear" /></label>
				</div>
				<div class="help-block"></div>
			</div>
		</div>
	</div>
</div>