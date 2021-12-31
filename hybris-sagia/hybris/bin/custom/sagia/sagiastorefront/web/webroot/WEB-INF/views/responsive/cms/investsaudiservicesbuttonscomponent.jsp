<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<section class="container mb-5 general-info mt-4">
	<div class="col-md-6 column" style="margin: 0 auto;">
		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-md-5 mx-2 my-2 col-12 mt-5">
				    <a style="padding: 0.6em;" rel="noopener" href="${investorLicenseApply.url}" target="_blank" class="new-reg text-center d-block">
				        ${investorLicenseApplyText}
				    </a>
                </div>
				<div class="col-md-5 mx-2 my-2 col-12 mt-5">
				    <a style="padding: 0.6em;" rel="noopener" href="${investorAccountLogin.url}" target="_blank" class="acc-login text-center d-block">
				        ${investorAccountLoginText}
                    </a>
                </div>
			</div>
		</div>
	</div>
</section>