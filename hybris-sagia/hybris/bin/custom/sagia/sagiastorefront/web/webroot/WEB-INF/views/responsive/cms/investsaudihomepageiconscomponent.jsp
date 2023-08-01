<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${component.visible}">
	<section id="homepage-icons" class="homepageIcons">
	    <div class="container">
			<div class="row justify-content-center">
				 <c:forEach var="homepageIcon" items="${component.icons}" varStatus="status">
					<div class="col-md-1 col-3">
						<div class="row">
							<div class="col-md-12 text-center">
								<img src="${homepageIcon.url}" class="rounded-circle" alt='${homepageIcon.altText}' title='${homepageIcon.altText}' style="" loading="lazy" width="50" height="50">
							</div>
							<div class="col-md-12">
								<p class="text-center">test</p>
							</div>
						</div>
					</div>
	            </c:forEach>
			</div>
    	</div>
	</section>
</c:if>
