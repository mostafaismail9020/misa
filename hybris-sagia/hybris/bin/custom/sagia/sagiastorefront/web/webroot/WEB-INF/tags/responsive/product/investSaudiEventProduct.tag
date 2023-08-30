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
    			<div class="col-md-7 col-12">
    				<h3 class="sub-title">${productData.description}</h3>
    			</div>
    		</div>
    		<div class="row">
   		        <p class="desc">
   				${productData.summary}
    			</p>
   			</div>

            <c:forEach items="${productData.paraWithMedia}" var="paraWithMedia">
            <div class="row">
            <div class="col-md-8 col-12">
            ${paraWithMedia.value.descriptionText}
            </div>
            <div class="col-md-4 col-12">
            <img class="img-fluid" src="${paraWithMedia.value.url}">
            </div>
            </div>
            </c:forEach>

            <c:forEach items="${productData.eventDetailGrid}" var="eventDetailGrid">
                        <div class="row">
                        <h4 class="sub-title">${eventDetailGrid.key}</h4>
                        </div>
                        <div class="row">
                        ${eventDetailGrid.value}
                        </div>
                        </c:forEach>



            <spring:theme code="portal.event.details.subjects"/>
            <c:forEach items="${productData.subjects}" var="subjects">
            <div class="row">
            <h2 class="sub-title">${subjects.key}</h4>
            </div>
            <div class="row">
            ${subjects.value}
            </div>
            </c:forEach>

            <spring:theme code="portal.event.details.speakers"/>
            <c:forEach items="${productData.speakers}" var="speakers">
            <div class="row">
            <h2 class="sub-title">${speakers.key}</h4>
            </div>
            <div class="row">
            ${speakers.value}
            </div>
            </c:forEach>



            <spring:theme code="portal.event.details.sponsors"/>



    	</div>
    </section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
