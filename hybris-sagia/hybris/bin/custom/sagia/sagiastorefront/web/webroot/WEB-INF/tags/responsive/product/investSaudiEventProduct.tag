<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<main>
	<section class="opportunity-article-section-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
				    <h1 class="page-title">${fn:escapeXml(productData.name)}</h1>
				</div>
			</div>
		</div>
    </section>

    <section class="opportunity-article-section-body">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-8 col-12">
    			    <div class="row">
	    				<p class="desc">${productData.description}</p>
	    				${productData.summary}
    				</div>
    				<div class="row">
		    		 <c:forEach items="${productData.eventDetailGrid}" var="eventDetailGrid">
		             	<h4 class="sub-title">${eventDetailGrid.value}</h4>&nbsp;
		             	${eventDetailGrid.key}&nbsp;&nbsp;&nbsp;&nbsp;
		             </c:forEach>
		             </div>
    			</div>
    			<div class="col-md-4 col-12">
	            	<img class="img-fluid" src="${productData.overviewPicture.url}">
	            </div>
    		</div>

            <h3><spring:theme code="portal.event.details.subjects"/></h3>
            <c:forEach items="${productData.subjects}" var="subjects">
            <div class="row">
            <h5 class="sub-title">${subjects.key}</h5>
            </div>
            <div class="row">
            ${subjects.value}
            </div>
            </c:forEach>

            <h3><spring:theme code="portal.event.details.speakers"/></h3>
            <c:forEach items="${productData.speakers}" var="speakers">
            <div class="row">
            	<h5 class="sub-title">${speakers.key}</h5>
            </div>
            <div class="row">
            ${speakers.value}
            </div>
            </c:forEach>

            <h3><spring:theme code="portal.event.details.sponsors"/></h3>
            <div class="row">
	            <c:forEach items="${productData.sponsersPartners}" var="sponsersAndPartner">
	            	<img alt="" src="${sponsersAndPartner.url}" height="50" width="50" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            </c:forEach>
            </div>
            
    	</div>
    </section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
