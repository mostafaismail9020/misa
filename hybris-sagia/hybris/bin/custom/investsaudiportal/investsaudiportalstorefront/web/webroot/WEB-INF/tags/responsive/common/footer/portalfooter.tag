<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<cms:pageSlot position="PortalFooter" var="feature">
    <cms:component component="${feature}"/>
</cms:pageSlot>

<!--string applyNow = "https://investsaudi.sa/en/maintenance/";-->
<!--string loginNow = "https://investsaudi.sa/en/maintenance/";-->