<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">statusIndicator</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->
	<div class="contentModule contentModule-wrap">
		<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
			<span class="contentModule-headline"><icon:info/> headline</span>
				<button type="submit" class="btn btn_slim">
					Replace
				</button>
			<div class="statusIndicator statusIndicator_rejected">
				Status: <span>Rejected</span>
			</div>
			<div class="contentModule-headline-border"></div>
		</div>
	</div>
	<br><br><br>
	<div class="contentModule contentModule-wrap">
		<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
			<span class="contentModule-headline"><icon:info/> headline</span>
				<button type="submit" class="btn btn_slim">
					Replace
				</button>
			<div class="statusIndicator statusIndicator_accepted">
				Status: <span>Accepted</span>
			</div>
			<div class="contentModule-headline-border"></div>
		</div>
	</div>
	<br><br><br>
	<div class="contentModule contentModule-wrap">
		<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
			<span class="contentModule-headline"><icon:info/> headline</span>
				<button type="submit" class="btn btn_slim">
					Replace
				</button>
			<div class="statusIndicator statusIndicator_process">
				Status: <span>In Process</span>
			</div>
			<div class="contentModule-headline-border"></div>
		</div>
	</div>
 </div>