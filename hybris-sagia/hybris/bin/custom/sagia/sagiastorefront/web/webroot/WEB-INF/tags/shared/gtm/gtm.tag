<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:if test="${not empty gtmContainerId}">
	<script>
		window.dataLayer = window.dataLayer || [];
		<c:if test="${not empty user}">
			<c:if test="${user.customerId ne 'anonymous'}">
				window.dataLayer.push({'user_id':'${user.customerId}','email':'${user.email}'});
		  	</c:if>
		</c:if>
  	</script>

	<!-- Google Tag Manager --> 
	
	<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start': 
	new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0], 
	j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src= 
	'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f); 
	})(window,document,'script','dataLayer','${gtmContainerId}');</script> 
	
	<!-- End Google Tag Manager --> 
</c:if>
