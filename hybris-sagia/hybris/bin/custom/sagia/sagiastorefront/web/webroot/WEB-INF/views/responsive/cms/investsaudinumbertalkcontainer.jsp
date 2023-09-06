<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section id="when-number-talk" class="when-number-talk-container">
	<div class="container">
		<div class="row reasons">
			<div class="col-lg-12 pt-4 pt-lg-0 content mx-auto">
					<h2 class="misa-text-title">${fn:escapeXml(component.title)}</h2>
				</div>
			<div class="col-lg-12">
				<div class="row number-talk-items-container">
					<c:forEach var="currentComponent" items="${components}" varStatus="status">
						<c:set var="-**" value="${(status.index + 1) * 150}" />
						<div class="col-md-2 col-sm-6 col-6">
							<cms:component component="${currentComponent}" element="div" />
						</div>
					</c:forEach>
				</div>
				<div class="row explore-keys-btn justify-content-center justify-content-md-between">
					<a href="/aboutKindgom" class="btn btn-primary-fill btn-video misa-btn-special">
						<spring:theme code="portal.read.more"/>
					</a>
				</div>
			</div>
		</div>
	</div>
</section>