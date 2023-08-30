<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/WEB-INF/tags/responsive/common/errorModal.tag" %>--%>


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

        <header:portalPageTitle/>
        
        <cms:pageSlot position="PortalPageTop" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

        <main>    
            <section class="page-link-and-title">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <cms:pageSlot position="ContactUsHomeLink" var="slotComponent">
					            <cms:component component="${slotComponent}"/>
					        </cms:pageSlot>
                            <h1 class="title">${cmsPage.title}</h1>                            
                            <cms:pageSlot position="ContactUsAbout" var="slotComponent">
				                <cms:component component="${slotComponent}"/>
				            </cms:pageSlot>
                        </div>
                    </div>
                </div>
            </section>
            <section id="contact-details" class="mb-0 pb-0">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="contact-details">
                                <div>
                                    <div class="text-center">
                                        <div class="icon">
                                            <div class="svg">
                                                <svg viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M16.3417 9.55279L15.2361 9C13.9083 8.3361 12.3235 8.74926 11.4753 9.90834C10.1966 9.49893 8.50058 7.80168 8.09156 6.52234C9.249 5.67466 9.66238 4.09207 8.99909 2.7621L8.44723 1.65836C7.93905 0.642007 6.90026 0 5.76395 0H5.00001C2.83691 0 1.40902 0.87544 0.770411 2.3596C0.353793 3.32785 0.332976 4.4614 0.535084 5.24707C2.0727 11.2244 6.77643 15.9279 12.7539 17.4652C13.5402 17.6674 14.6728 17.647 15.6409 17.2305C17.1249 16.5921 18 15.164 18 13V12.2361C18 11.0998 17.358 10.061 16.3417 9.55279ZM15.4472 11.3416C15.786 11.511 16 11.8573 16 12.2361V13C16 14.3891 15.5759 15.0813 14.8505 15.3933C14.3331 15.6159 13.6415 15.6284 13.252 15.5282C7.98051 14.1725 3.82805 10.0203 2.47202 4.74882C2.37204 4.36014 2.38475 3.66791 2.60756 3.1501C2.91986 2.42429 3.6119 2 5.00001 2H5.76394C6.14272 2 6.48898 2.214 6.65837 2.55279L7.2107 3.65744C7.45718 4.15167 7.25634 4.75214 6.76211 4.99862L6.55372 5.10255C6.21443 5.27176 6.00001 5.61829 6.00001 5.99744C6.00001 8.54946 9.44808 12 12 12C12.3788 12 12.725 11.786 12.8944 11.4472L13 11.2361C13.247 10.7421 13.8477 10.5419 14.3417 10.7889L15.4472 11.3416Z" fill="white"/>
                                                </svg>  
                                            </div>                                          
                                        </div>
                                        <div class="contact-type mt-2">Call us</div>
                                    </div>
                                    <div class="telephone-numbers">
								        <cms:pageSlot position="ContactUsTelephoneNumbers" var="slotComponent">
								            <cms:component component="${slotComponent}"/>
								        </cms:pageSlot>
                                    </div>
                                </div>
                                <div>
                                    <div class="text-center">
                                        <div class="icon">
                                            <div class="svg">
                                                <svg width="20" height="16" viewBox="0 0 20 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M2.8 0H17.2C18.7361 0 20 1.22881 20 2.75V2.97844C20.0003 2.99221 20.0003 3.00597 20 3.01972V13.25C20 14.7712 18.7361 16 17.2 16H2.8C1.26392 16 0 14.7712 0 13.25V2.99907V2.75C0 1.22881 1.26392 0 2.8 0ZM2 4.86845V13.25C2 13.6538 2.35608 14 2.8 14H17.2C17.6439 14 18 13.6538 18 13.25V4.86859L10.5548 9.83205C10.2189 10.056 9.78131 10.056 9.44541 9.83205L2 4.86845ZM17.9519 2.49693L10.0001 7.79815L2.0481 2.49681C2.15963 2.21156 2.45297 2 2.8 2H17.2C17.5471 2 17.8404 2.21161 17.9519 2.49693Z" fill="white"/>
                                                </svg> 
                                            </div>                                          
                                        </div>
                                        <div class="contact-type mt-2">Email us</div>
                                    </div>
                                    <div class="email-addresses">
                                        <cms:pageSlot position="ContactUsEmail" var="slotComponent">
								            <cms:component component="${slotComponent}"/>
								        </cms:pageSlot>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="locations">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">                
                            <h3 class="h3">Our Locations</h3>
                            <div class="locations">
                                <button class="sagia-accordion locations-accordion"><spring:theme code="portal.contact.us.local" text = "Local"/></button>
                                <div class="sagia-accordion-panel">
                                
                                    <cms:pageSlot position="ContactUsLocalLocations" var="slotComponent">
							            <cms:component component="${slotComponent}"/>
							        </cms:pageSlot>
 
                                </div>
                            </div>

                            <div class="locations">
                                <button class="sagia-accordion locations-accordion"><spring:theme code="portal.contact.us.international" text = "International"/></button>
                                <div class="sagia-accordion-panel">
                                    <cms:pageSlot position="ContactUsIntnlLocations" var="slotComponent">
							            <cms:component component="${slotComponent}"/>
							        </cms:pageSlot>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- <section class="mb-5 general-info">
                <cms:pageSlot position="PortalPageMap" var="slotComponent">
                    <cms:component component="${slotComponent}"/>
                </cms:pageSlot>
            </section>-->

            <cms:pageSlot position="PortalPageContact" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>

            <cms:pageSlot position="PortalPageOffices" var="slotComponent">
                <cms:component component="${slotComponent}"/>
            </cms:pageSlot>
            
        </main>

        <cms:pageSlot position="PortalPageBottom" var="slotComponent">
            <cms:component component="${slotComponent}"/>
        </cms:pageSlot>

    </jsp:body>
</template:portalpage>

<script>
// show and hide accordion component
var acc = document.getElementsByClassName("sagia-accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    /* toggle between adding and removing the "active" class,
    to highlight the button that controls the panel */
    this.classList.toggle("active");

    /* toggle between hiding and showing the active panel */
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}
</script>
