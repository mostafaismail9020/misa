<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="hideFooterLinks" required="false" %>

<c:if test="${!hideFooterLinks}">
<cms:pageSlot position="PortalFooter" var="feature">
    <cms:component component="${feature}"/>
</cms:pageSlot>
</c:if>

<!--string applyNow = "https://investsaudi.sa/en/maintenance/";-->
<!--string loginNow = "https://investsaudi.sa/en/maintenance/";-->