<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="the_kingdom_glance_component">
	
	
	<video class="int-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
      <source src="${fn:escapeXml(component.localizedRhqVideo.url)}" type="${fn:escapeXml(component.localizedRhqVideo.mime)}">
    </video>
        </div>
    
