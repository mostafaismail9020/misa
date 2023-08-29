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
					<h2 class="page-title">${fn:escapeXml(productData.articleTitle)}</h2>
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
    			<div class="col-md-5 col-12">
                    <iframe src="${productData.videoUrl}" width="360" height="240"></iframe>
    			</div>
    		</div>
    		<div class="row">
   		        <p class="desc">
   					${productData.summary}	
    			</p>
   			</div>
   			<c:forEach items="${productData.articleTiles}" var="tile">
    			<div class="row">
    			    <h2 class="page-title">${tile.value}</h2>
    			</div>
   			</c:forEach>
	    	<div>
   				<p class="desc">
   					${productData.articleThirdPara}
   				</p>
    		</div>

   		    <c:forEach items="${productData.subheadingWithMedia}" var="subheadingWithMedia">
   		    <div class="row">
    			<h4 class="page-title">${subheadingWithMedia.key}</h4>
    			</div>
    			<div class="row">
    			<div class="col-md-4 col-12">
    				<img class="img-fluid" src="${subheadingWithMedia.value.url}">
    			</div>
    			<div class="col-md-8 col-12">
    				${subheadingWithMedia.value.descriptionText}
    			</div>
    	    </div>
   			</c:forEach>
   			
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
   			
   			
   			<c:forEach items="${productData.subHeadings}" var="articleSubHeading">
    			<div class="row">
    			<h4 class="sub-title">${articleSubHeading.key}</h4>
    			</div>
    			<div class="row">
    			${articleSubHeading.value}	
    			</div>
   			</c:forEach>
   			
   			
   			<c:forEach items="${productData.articleSubDetails4Boxes}" var="articleSubDetails4Boxes">
    			<div class="row">
    			<h4 class="sub-title">${articleSubDetails4Boxes.key}</h4>
    			${articleSubDetails4Boxes.value}	
    			</div>
   			</c:forEach>
   			
   		    <div class="row">
    			${productData.articleSubDetails5}	
    		</div>
    	</div>
    </section>
</main>

<cms:pageSlot position="PortalPageBottom" var="slotComponent">
	<cms:component component="${slotComponent}"/>
</cms:pageSlot>
