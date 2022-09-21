<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<template:portalpage pageTitle="${pageTitle}">
	<jsp:body>
    	<header:portalPageTitle />
    	
 <!-- Banner  -->
 <cms:pageSlot position="PortalPageMain" var="feature">
  <cms:component component="${feature}" element="div" class=""/>
  </cms:pageSlot>
<!-- Banner -->   	
<!-- Banner  -->

<!-- Tableau Report Start -->
<c:if test="${language eq 'en'}">
	<div class='tableauPlaceholder' id='viz1661433488951' style='position: relative'><noscript><a href='#'><img alt='Real Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-RealSector-EN&#47;RealSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-RealSector-EN&#47;RealSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-RealSector-EN&#47;RealSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433488951');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1227px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433499629' style='position: relative'><noscript><a href='#'><img alt='Monetary Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-MonetarySector-EN&#47;MonetarySector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-MonetarySector-EN&#47;MonetarySector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-MonetarySector-EN&#47;MonetarySector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433499629');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1827px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433509080' style='position: relative'><noscript><a href='#'><img alt='Fiscal Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-FiscalSector-EN&#47;FiscalSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-FiscalSector-EN&#47;FiscalSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-FiscalSector-EN&#47;FiscalSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433509080');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1577px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433517562' style='position: relative'><noscript><a href='#'><img alt='External Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-ExternalSector-EN&#47;ExternalSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-ExternalSector-EN&#47;ExternalSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-ExternalSector-EN&#47;ExternalSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433517562');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1577px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433527519' style='position: relative'><noscript><a href='#'><img alt='Workforce ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Workforce-EN&#47;Workforce&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Workforce-EN&#47;Workforce' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Workforce-EN&#47;Workforce&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433527519');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1427px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433536897' style='position: relative'><noscript><a href='#'><img alt='Employment ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Employment-EN&#47;Employment&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Employment-EN&#47;Employment' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Employment-EN&#47;Employment&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433536897');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1027px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433545620' style='position: relative'><noscript><a href='#'><img alt='Unemployment ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Unemployment-EN&#47;Unemployment&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Unemployment-EN&#47;Unemployment' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Unemployment-EN&#47;Unemployment&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433545620');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1327px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<c:if test="${language eq 'ar'}">
	<div class='tableauPlaceholder' id='viz1661433249256' style='position: relative'><noscript><a href='#'><img alt='Real Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-RealSector-AR&#47;RealSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-RealSector-AR&#47;RealSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-RealSector-AR&#47;RealSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433249256');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1227px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433280137' style='position: relative'><noscript><a href='#'><img alt='Monetary Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-MonetarySector-AR&#47;MonetarySector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-MonetarySector-AR&#47;MonetarySector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-MonetarySector-AR&#47;MonetarySector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433280137');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1827px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433322012' style='position: relative'><noscript><a href='#'><img alt='Fiscal Sector ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-FiscalSector-AR&#47;FiscalSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-FiscalSector-AR&#47;FiscalSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-FiscalSector-AR&#47;FiscalSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433322012');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1577px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433340050' style='position: relative'><noscript><a href='#'><img alt='External Sector  ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-ExternalSector-AR&#47;ExternalSector&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-ExternalSector-AR&#47;ExternalSector' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-ExternalSector-AR&#47;ExternalSector&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433340050');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1577px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433354985' style='position: relative'><noscript><a href='#'><img alt='Workforce ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Workforce-AR&#47;Workforce&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Workforce-AR&#47;Workforce' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Workforce-AR&#47;Workforce&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433354985');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1427px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433387432' style='position: relative'><noscript><a href='#'><img alt='Employment ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Employment-AR&#47;Employment&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Employment-AR&#47;Employment' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Employment-AR&#47;Employment&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433387432');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1027px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
	<div class='tableauPlaceholder' id='viz1661433411559' style='position: relative'><noscript><a href='#'><img alt='Unemployment ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Unemployment-AR&#47;Unemployment&#47;1_rss.png' style='border: none' /></a></noscript><object class='tableauViz'  style='display:none;'><param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> <param name='embed_code_version' value='3' /> <param name='site_root' value='' /><param name='name' value='4-SaudiEconomicSectors-Unemployment-AR&#47;Unemployment' /><param name='tabs' value='no' /><param name='toolbar' value='yes' /><param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;4-&#47;4-SaudiEconomicSectors-Unemployment-AR&#47;Unemployment&#47;1.png' /> <param name='animate_transition' value='yes' /><param name='display_static_image' value='yes' /><param name='display_spinner' value='yes' /><param name='display_overlay' value='yes' /><param name='display_count' value='yes' /><param name='language' value='en-US' /></object></div>                <script type='text/javascript'>                    var divElement = document.getElementById('viz1661433411559');                    var vizElement = divElement.getElementsByTagName('object')[0];                    if ( divElement.offsetWidth > 800 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else if ( divElement.offsetWidth > 500 ) { vizElement.style.width='100%';vizElement.style.height=(divElement.offsetWidth*0.75)+'px';} else { vizElement.style.width='100%';vizElement.style.height='1127px';}                     var scriptElement = document.createElement('script');                    scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    vizElement.parentNode.insertBefore(scriptElement, vizElement);                </script>
</c:if>
<!-- Tableau Report End -->



<!-- <section class="eco-banner eco-banner-ecosector position-relative">
  <div class="eco-banner-container" data-aos="fade-up">
    <h1><spring:theme code="economic.saudieconomic.page.heading.name"/></h1>
  </div>
  <section class="eh-page-breadcrum-link">
    <div class="">
      <div class="eh-page-links">
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/dashboard">
            <spring:theme code="economic.highlights.dashboard.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/saInternationalIndices">
            <spring:theme code="economic.highlights.internationalindices.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/infraLogistics">
            <spring:theme code="economic.highlights.infralogistics.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link active">
          <a href="${encodedContextPath}/economicHighlights/saudiEconomicSectors">
            <spring:theme code="economic.highlights.economicsector.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
        <div class="eh-page-link">
          <a href="${encodedContextPath}/economicHighlights/investmentData">
            <spring:theme code="economic.highlights.investmentdata.page.link.name"/>
            <img class="img-fluid close-icon" src="${commonResourcePath}/images/right_arrow_blue.png" alt=""/>
          </a>
        </div>
      </div>
    </div>
  </section>
</section> -->

<!-- ----------Real Sector Dashboard---------------- -->
<section class="" id="">
  <div class="INL_BG_image">
  
<div class="container">
<div class="row">
  <div class="col-md-12">
    <div class="INL_bg_gold_header">
      <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.realsector.heading.name"/></h1>
    </div>
    <div class="text-center">
      <p class="INL_bg_gray_paraset ">${economicSector['RealSector'][0].label}</p>
    </div>
  </div> 
</div>
<div class="row">
 
  <!-- <div class="col-md-9 realSectorDiv" style="height: 380px;position: relative;top: 25%;display: table-cell;vertical-align: middle;transform: translatey(25%);">
    <div class="row posRelDiv"> -->
    <c:forEach items="${economicSector['RealSector']}" var="card">
        <div class="col-12 col-lg-3 mx-auto">
            <div class="INL_SD_Real_Sector">
              <h4 class="INL_SD_Real_Sector_header">${card.value}</h4>
              <p class="INL_SD_Real_Sector_paper">${card.displayName}</p>
            </div>
          </div>
    </c:forEach>
     <!-- <div class="col-md-3 mx-auto INL_Arrow_bg_log d-none d-md-block">
            
      </div>
      <div class="col-md-3 mx-auto d-block d-md-none mobArrowDiv">
            <img src="${commonResourcePath}/images/sector_arrow.png">
      </div> --> 
  <!--  </div> 
  </div>  -->
</div>
                                                        <div class="chart-bottom-link pb-4 pr-2">
                                                            <a href="https://www.stats.gov.sa/en/823" target="_blank" class="anchor-link"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.gastat.text"/></span></a>
                                                        </div>
</div> 
  </div>  
</section>
<!-- -----------Real Sector Dashboard--------------- -->

<!-- ----------Monetary Sector Dashboard---------------- -->
<section class="" id="monetarySectorDashboard">
  <div class="INL_BG_image"> 
<div class="container">
<div class="row">
  <div class="col-md-12">
    <div class="INL_bg_gold_header">
      <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.monetarysector.heading.name"/></h1>
    </div> 
  </div> 
</div>
<div class="row pt-5">
    <c:forEach items="${economicSector['MonetarySector']}" var="card" varStatus="loop">
    <div class="col-12 col-lg-3 mx-auto">
        <div class="INL_SD_Real_Sector">
          <h4 class="INL_SD_Real_Sector_header">${card.value}</h4>
          <p class="INL_SUB_Sector">${card.label}</p>
          <h5 class="INL_SD_Real_Sector_header_green p-2">${card.displayName}</h5>
          <p class="INL_SD_Real_Sector_paper2">${card.year}</p>
        </div>
    </div>
    </c:forEach>
  </div>  
</div>
<div class="chart-bottom-link pb-4 pr-2 d-flex justify-content-end">
    <a target="_blank" href="https://www.sama.gov.sa/en-US/EconomicReports/Pages/MonthlyStatistics.aspx" class="anchor-link pr-3"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.sama.text"/></span></a>
    <a target="_blank" href="https://www.stats.gov.sa/en/394" class="anchor-link"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.gastat.text"/></span></a>
</div>
</div>  
  </div>  
</section>
<!-- -----------Monetary Sector Dashboard--------------- -->

<!-- ----------Fiscal Sector Dashboard---------------- -->
<section class="" id="">
  <div class="INL_BG_image"> 
<div class="container">
<div class="row">
  <div class="col-md-12">
    <div class="INL_bg_gold_header">
      <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.fiscalsector.heading.name"/></h1>
    </div> 
  </div> 
</div>
<div class="row pt-5 pb-5">
        <c:forEach items="${economicSector['FiscalSector']}" var="card" varStatus="loop">
            <div class="col-12 col-lg-3 mx-auto">
                <div class="INL_SD_Real_Sector">
                  <h4 class="INL_SD_Real_Sector_header">${card.value}</h4>
                  <p class="INL_SUB_Sector">${card.label}&nbsp;</p>
                  <h5 class="INL_SD_Real_Sector_header_green p-2">${card.displayName}</h5>
                  <p class="INL_SD_Real_Sector_paper2">${card.year}</p>
                </div>
              </div>
        </c:forEach>
</div>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="chart-bottom-link pb-4">
                                                                    <a target="_blank" href="https://www.mof.gov.sa/en/financialreport/Pages/default.aspx" class="anchor-link"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.mof.text"/></span></a>
                                                                </div>
                                                            </div>
                                                        </div>
<div class="row pt-5 ">
  <div class="col-md-12">
    <div class="INL_bg_gold_header">
      <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.externalsector.heading.name"/></h1>
    </div> 
  </div> 
</div>
<div class="row pt-5 pb-5">
        <c:forEach items="${economicSector['ExternalSector']}" var="card" varStatus="loop">
            <div class="col-12 col-lg-3 mx-auto">
                <div class="INL_SD_Real_Sector">
                  <h4 class="INL_SD_Real_Sector_header">${card.value}</h4>
                  <p class="INL_SUB_Sector">${card.label}</p>
                  <h5 class="INL_SD_Real_Sector_header_green p-2">${card.displayName}</h5>
                  <p class="INL_SD_Real_Sector_paper2">${card.year}</p>
                </div>
              </div>
        </c:forEach>
</div>
                                                        <div class="chart-bottom-link pb-4 pr-2 d-flex justify-content-end">
                                                            <a target="_blank" href="https://www.stats.gov.sa/en/325" class="anchor-link pr-3"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.gastat.text"/></span></a>
                                                            <a target="_blank" href="https://www.sama.gov.sa/en-US/EconomicReports/Pages/MonthlyStatistics.aspx" class="anchor-link"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.sama.text"/></span></a>

                                                        </div>
</div>  
  </div>  
</section>
<!-- -----------Fiscal Sector Dashboard--------------- -->

<!-- ----------Workforce Dashboard---------------- -->
<section class="" id=""> 
<div class="container">
<div class="row">
  <div class="col-md-12">
    <div class="INL_bg_gold_header">
      <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.workforce.section.heading.name"/></h1>
    </div> 
    <p class="INL_Workforce_para text-center w-100 pt-5"><spring:theme code="economic.saudieconomic.population.subheading.name"/></p>
  </div>  
</div>
<div class="row pt-5 pb-2">
  <div class="col-md-3"> 
    <div class="form-inline">
      <div class="form-group">
      <label class="INL_Select_year"><spring:theme code="economic.saudieconomic.selectyear.dropdown.text"/></label>
      <form:form id="SecrorChart" method="post" action="/saudiEconomicSectors">
        <!-- <input type="month" id="year-picker-html" value="2010-10"> -->
        <input type="text" class="yearpicker form-control" value="" />
      </form:form>
        <!-- <select class="form-control mx-sm-3 INL_Select_number">
          <option>2020</option>
        </select> -->
      </div>  
    </div>  
  </div>  
</div>
<div id="sector-json-data" style="display: none;">
  <c:if test="${not empty saudiPopulationJson}">
    <div id="saudiPopulationJson">
      ${saudiPopulationJson}
    </div>
  </c:if>
  <c:if test="${not empty graduatesByDegreeJson}">
    <div id="graduatesByDegreeJson">
      ${graduatesByDegreeJson}
    </div>
  </c:if>
  <c:if test="${not empty populationByRegionJson}">
    <div id="populationByRegionJson">
      ${populationByRegionJson}
    </div>
  </c:if>
   
  <c:if test="${not empty labourPrivateSectorJson}">
    <div id="labourPrivateSectorJson">
      ${labourPrivateSectorJson}
    </div>
  </c:if>
  <c:if test="${not empty saudiEmploymentByRegionJson}">
    <div id="saudiEmploymentByRegionJson">
      ${saudiEmploymentByRegionJson}
    </div>
  </c:if>
  <c:if test="${not empty nonSaudiEmploymentByRegionJson}">
    <div id="nonSaudiEmploymentByRegionJson">
      ${nonSaudiEmploymentByRegionJson}
    </div>
  </c:if>
  <c:if test="${not empty saudiEmploymentJson}">
    <div id="saudiEmploymentJson">
      ${saudiEmploymentJson}
    </div>
  </c:if>
  <c:if test="${not empty nonSaudiEmploymentJson}">
    <div id="nonSaudiEmploymentJson">
      ${nonSaudiEmploymentJson}
    </div>
  </c:if>
  <c:if test="${not empty avgMonthlyWagesJson}">
    <div id="avgMonthlyWagesJson">
      ${avgMonthlyWagesJson}
    </div>
  </c:if>
  <c:if test="${not empty growthrateWagesJson}">
    <div id="growthrateWagesJson">
      ${growthrateWagesJson}
    </div>
  </c:if>
  <c:if test="${not empty saudiUnemploymentJson}">
    <div id="saudiUnemploymentJson">
      ${saudiUnemploymentJson}
    </div>
  </c:if>
  <c:if test="${not empty nonSaudiUnemploymentJson}">
    <div id="nonSaudiUnemploymentJson">
      ${nonSaudiUnemploymentJson}
    </div>
  </c:if>
  <c:if test="${not empty overallUnemploymentJson}">
    <div id="overallUnemploymentJson">
      ${overallUnemploymentJson}
    </div>
  </c:if>
</div>
<div class="row pb-2">
  <div class="col-12 col-lg-6"> 
    <div class="INL_SD_Real_Sector p-4">
      <div class="row"> 
      
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.overallpopulation.charttitle"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
        
      </div>   
      <div class="row p-4">
        <div class="col-md-4 saudi-sector-map">
          <div id="population">

          </div>
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/Overall_Saudi_Population_map.png"/>
        </div>
        <div class="col-md-8">
          <div id="renderoverallSaudiPopulationChartDiv" class="w-100 text-center" dir="ltr"></div>
          <div id="ChartDivError" class="w-100 text-center" style="display: none;">
            No Data Available
          </div>
        </div>
      </div>   
    </div>  
  </div> 
  <div class="col-12 col-lg-6"> 
    <div class="INL_SD_Real_Sector p-4">
      <div class="row">  
      
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.graduatesbydegree.charttitle"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
        
      </div>  
      <div class="row p-4">
        <div class="col-12"> 
          <div id="rendergraduatesByDegreeChartDiv" class="w-100 text-center" dir="ltr"></div>
          <div id="ChartDivError" class="w-100 text-center" style="display: none;">
            No Data Available
          </div>
        </div>
      </div>    
    </div>  
  </div>  
</div>
<div class="row pb-4">
  <div class="INL_SD_Real_Sector w-100">
       
    <div class="col-12">
      <div class="d-flex justify-content-center">
        <div class="position-absolute text-center">
          <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.populationbyregion.charttitle"/></h4>
        </div>
        <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
          <span class="INL_Select_download">Download</span>
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
        </div> -->
      </div>
       
    </div> 	 
    <div class="col-12"> 
      <div class="row p-4">
        <div class="col-12  pt-3"> 
          <div id="renderpopulationByRegionDiv" class="w-100 text-center" dir="ltr"></div>
          <div id="ChartDivError" class="w-100 text-center" style="display: none;">
            No Data Available
          </div>
        </div>
      </div> 
      
      <!-- <p class="pull-right text-right INL_margin_n35px">
        <span class="INL_Select_download pull-right text-right">Download</span>
        <img src="${commonResourcePath}/images/pdf.png" class="img-fluid pull-right text-right"> 
        <img src="${commonResourcePath}/images/pdf.png" class="img-fluid pull-right text-right"> 
      </p> -->
    </div> 
  </div>  
</div>  
<div class="col-md-12 mt-5 pt-5">
  <div class="INL_bg_gold_header">
    <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.employment.section.heading.name"/></h1>
  </div>  
</div> 
<div class="row pt-5 pb-2">
  <div class="col-12 col-lg-6"> 
    <div class="INL_SD_Real_Sector p-4 INL_Height_setle334">
      <div class="row"> 
        
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.avgmonthlywages.text"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
      </div>  
      <div class="pt-2 text-center">
        <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/Avg_monthly_wages_icon.png" class="img-fluid"> 
        <h4 class="text-center INL_EEMP_number_set">3,440</h4>
        <p class="text-center INL_EEMP_number_para"><spring:theme code="economic.saudieconomic.avywagesprivatesector.text"/></p>
      </div>
    </div>  
  </div> 
  <div class="col-12 col-lg-6"> 
    <div class="INL_SD_Real_Sector p-4 INL_Height_setle334">
      <div class="row"> 
        
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.laborprivatesector"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
        
      </div> 
      <div class="row"> 
        <div class="col-12"> 
          <div id="renderlabourPrivateSectorChartDiv" class="w-100 text-center" dir="ltr"></div>
          <div id="ChartDivError" class="w-100 text-center" style="display: none;">
            No Data Available
          </div>
        </div>
      </div>
    </div>  
  </div>  
</div>
<div class="row pb-4">
  <div class="INL_SD_Real_Sector w-100"> 
    <div class="col-12">
      <div class="d-flex justify-content-center">
        <div class="position-absolute text-center">
          <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.employmentprivatesector.heading.text"/></h4>
        </div>
        <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
          <span class="INL_Select_download">Download</span>
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
        </div> -->
      </div>
      
      <!-- <p class="pull-right text-right INL_margin_n35px">
        <span class="INL_Select_download pull-right text-right">Download</span>
        <img src="${commonResourcePath}/images/pdf.png" class="img-fluid pull-right text-right"> 
        <img src="${commonResourcePath}/images/pdf.png" class="img-fluid pull-right text-right"> 
      </p> -->
    </div>  
    
    <div class="col-12 pt-3"> 
      <div id="renderemploymentByRegionChartDiv" class="w-100 text-center" dir="ltr"></div>
      <div id="ChartDivError" class="w-100 text-center" style="display: none;" dir="ltr">
        No Data Available
      </div>
    </div> 
  </div>  
</div>
<div class="col-md-12 mt-5 pt-5">
  <div class="INL_bg_gold_header">
    <h1 class="INL_logistics_header text-center w-100"><spring:theme code="economic.saudieconomic.unemployment.section.heading"/></h1>
  </div>  
</div> 
<div class="row pt-5 pb-2">
  <div class="col-md-6"> 
    <div class=" p-4">
      <div class="row"> 
        
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.unemployment.section.heading"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
        
      </div>  
      <div class="row pt-2 text-center"> 
        <div class="col-6 pull-left">
          <h5 class="INL_UEMP_header INL_unemp_hei50"><spring:theme code="economic.saudieconomic.overallunemployment.text"/></h5>
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/Overall_Unemployment_icon.png" class="img-fluid"> 
          <h6 class="INL_UEMP_header_number_gold pt-3">7.4%</h6>
        </div>
        <div class="col-6 pull-right"> 
          <h5 class="INL_UEMP_header INL_unemp_hei50"><spring:theme code="economic.saudieconomic.saudiunemployment.text"/></h5>
          <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/Saudi_Unemployment_icon.png" class="img-fluid">
          <h6 class="INL_UEMP_header_number_green pt-3">12.6%</h6> 
        </div>

      </div>
    </div>  
  </div> 
  <div class="col-md-6"> 
    <div class=" p-4">
      <div class="row"> 
      
        <div class="d-flex w-100 pl-4 pr-4">
          <div class="pull-left  text-left">
            <h4 class="INL_Select_overall"><spring:theme code="economic.saudieconomic.unemploymentquarterlydata.text"/></h4>
          </div>
          <!-- <div class="pull-right text-right INL_Select_icons" style="margin-left: auto;">
            <span class="INL_Select_download">Download</span>
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/PDF_icon1.png" class="img-fluid"> 
            <img src="${commonResourcePath}/images/Infrastructures_and_Logistics/sector/xls_icon1.png" class="img-fluid"> 
          </div> -->
        </div>
        
      </div> 
      <div class="row pt-3"> 
        <div class="col-12 pt-3"> 
          <div id="rendersaudiUnemploymentChartDiv" class="w-100 text-center" dir="ltr"></div>
          <div id="ChartDivError" class="w-100 text-center" style="display: none;" dir="ltr">
            No Data Available
          </div>
        </div> 
      </div>  
    </div>  
  </div>  
</div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="chart-bottom-link pb-4 d-flex justify-content-end">
                                                                <a href="https://www.stats.gov.sa/ar/814" target="_blank" class="anchor-link"><spring:theme code="economic.saudieconomic.source.text"/><span><spring:theme code="economic.saudieconomic.gastat.text"/></span></a>
</div>  
                                                        </div>
                                                    </div>
                                                </div>
</section>
<!-- -----------Workforce Dashboard--------------- -->
  

</jsp:body>
</template:portalpage>