<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
	<!--
	<script>
    document.querySelector("html").style.cssText = 'background:#000!important';
    document.querySelector("body").style.cssText += 'opacity:0!important;display:block!important;';
    window['_sfw_host'] ='https://www.sessionforward.com/assets/js/';
    window['_sfw_script'] = 'sf_ab.min.js?v=1.0.4';
    window['_sfw_key'] = 'a2dc6f16-8c9e-4dfe-a3a4-66b765ee29c8';
    (function(s,e,ss,i,o,n){
    if(s.console && s.console.log) { s.console.log(i);};
    o=e.createElement(ss);o.async=1;o.src=_sfw_host+_sfw_script;
    n=e.getElementsByTagName(ss)[0];n.parentNode.insertBefore(o,n);
    })(window,document,'script','SessionForward Loaded.');
    </script>
	-->

    	<header:portalPageTitle />

		<cms:pageSlot position="PortalPageTop" var="slotComponent">
	    	<cms:component component="${slotComponent}"/>
	    </cms:pageSlot>

		<main>
	        <!-- Breadcurms -->
	     	<section id="page-breadcrums" class="page-breadcrums">
	         	<div class="container">
	             	<div class="row">
	                 	<nav aria-label="breadcrumb">
	                 		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	                 	</nav>
	             	</div>
	         	</div>
	     	</section>
	   
		   <cms:pageSlot position="PortalPageMain" var="slotComponent">
		        <cms:component component="${slotComponent}"/>
		    </cms:pageSlot>	  
	  	</main>

  		<section class="sitemap-section" dir="ltr">
		    <div class="fluid-container w-100">
				<div class="row">
		        	<div class="col-12 sitemap-content">
		          	<!--  <span class="sitemap-section-heading">Site Map </span>-->
		          		<table class="table">
				            <tbody>
				              	<c:forEach items="${investSaudiSiteMapData}" var="investSaudiSiteMapData">
				                	<tr>
				                  		<th class="sitemap-content-level1">
				                  			<a href="/${language}${investSaudiSiteMapData.siteMapLevel1.siteMapLevel2Url}">${investSaudiSiteMapData.siteMapLevel1.siteMapLevel2Name}</a>
				                  		</th>
				                  	</tr>                  
				                    <c:forEach items="${investSaudiSiteMapData.siteMapLevel2}" var="siteMapLevel2Data">
				                      	<tr class="sitemap-content-level2">
				                        	<td class="no-border-bottom"></td>
				                        	<td>
				                          		<a href="/${language}${siteMapLevel2Data.siteMapLevel2Url}">${siteMapLevel2Data.siteMapLevel2Name}</a>
				                        	</td>
				                      	</tr>				                      
				                      	<c:forEach items="${siteMapLevel2Data.siteMapLevel3Links}" var="siteMapLevel3Links">
				                        	<tr class="sitemap-content-level2">
					                          	<td></td>
					                          	<td></td>
					                          	<td>
					                            	<a href="/${language}${siteMapLevel3Links.siteMapLevel3Url}">${siteMapLevel3Links.siteMapLevel3Name}</a>
					                          	</td>
					                        </tr>                        
					                        <c:forEach items="${siteMapLevel3Links.siteMapLevel4Links}" var="siteMapLevel4Links">
					                        	<tr class="sitemap-content-level2">
					                            	<td></td>
					                                <td></td>
					                                <td></td>
					                                <td>
					                                	<a href="/${language}${siteMapLevel4Links.siteMapLevel4Url}">${siteMapLevel4Links.siteMapLevel4Name}</a>
					                                </td>
					                              </tr>                                       
					                    	</c:forEach>
					               		</c:forEach>
                    				</c:forEach>
              					</c:forEach>
            				</tbody>
          				</table>        
        			</div>
      			</div>
    		</div>
  		</section>
     	     
		<cms:pageSlot position="PortalPageBottom" var="slotComponent">
        	<cms:component component="${slotComponent}"/>
    	</cms:pageSlot>

	</jsp:body>
</template:portalpage>
