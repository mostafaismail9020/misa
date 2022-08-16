<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
               
<div class="the_kingdom_culture_component">
	
	<c:if test="${not empty videoLink}">
              <div class="video-player-container">
    <div class="embed-responsive embed-responsive-16by9">
        <iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="30%" height="215" src="${fn:escapeXml(videoLink.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy"></iframe>
    </div>
</div>
</c:if>
           
		</div>
		
	

