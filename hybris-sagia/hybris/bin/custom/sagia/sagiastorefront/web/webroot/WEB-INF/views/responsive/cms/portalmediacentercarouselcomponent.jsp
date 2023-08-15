<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="News_press" id="News_press">
	<div class="rect">
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" id="carousel" data-interval="false">
        	<div class="carousel-inner">                                    
            	<c:forEach items="${mediaCenterComponents}" var="mediaCenter">
	            	<c:if test="${null != mediaCenter.mediaCenterTitle}">
	                	<div class="carousel-item">
	                    	<img class="d-block w-100" src="${fn:escapeXml(mediaCenter.mediaCenterDetailsImage.url)}" alt="${mediaCenter.mediaCenterTitle}" loading="lazy">
	                    	<div class="toplist">
	                      		<div class="container-fluid">
	                        		<div class="mask flex-center">
	                              		<div class="row  align-items-center  ">
	                                 		<div class="container">
			                                  	<div class="col-md-6 col-sm-12">
			                                     	<h2 class="News_Press_Releases">${mediaCenter.mediaCenterTypeName}</h2>
			                                  	</div>
	                                 		</div>
	                                 		<div class="col-md-5 col-sm-12 content-slider-part">
	                                    		<div class="News_press_bgwhite">
	                                    		   <c:url value="${mediaCenter.mediaCenterUrl}/${mediaCenter.mediaCenterCode}" var="mediaCenterUrl"/>
                                                   <a href="${mediaCenterUrl}">
		                                       		<div class="top_date position-absolute">
		                                       			<c:if test="${!mediaCenter.isVideoComponent}">
		                                                	<h4 class="date"><fmt:formatDate value="${mediaCenter.mediaCenterStartDate}" pattern="d" /></h4>
		                                          			<h6 class="date_name"><fmt:formatDate value="${mediaCenter.mediaCenterStartDate}" pattern="MMMM" /></h6>
		                                          		</c:if>
		                                          		<%--                                         
			                                          	<c:if test="${mediaCenter.isVideoComponent}">
			                                                <h4 class="date">09</h4>
			                                                <h6 class="date_name">AUG 2021</h6>
			                                          	</c:if>
			                                          	--%>
	                                       			</div>
	                                       			<div class="p-5 paddding_align">
			                                        	<div>
			                                        		<h3 class="highlight_title">${mediaCenter.mediaCenterTitle}</h3>
			                                            	<p class="para_title">${mediaCenter.mediaCenterShortInformation}</p>
				                                            <div class="container p-0 ">
				                                            	<c:choose>
				                                                	<c:when test="${mediaCenter.isVideoComponent}">
				                                                    	<div class="mt-0">
				                                                          	<%-- <h3 class="highlight_title">${mediaCenter.mediaCenterTitle}</h3>
				                                                          	<p class="para_title">${mediaCenter.mediaCenterShortInformation}</p> --%>	
				                                                          	<a href="#" class="glightbox play-btn trigger-videoModal" data-url="${fn:escapeXml(mediaCenter.embedURL)}"></a>
				                                                          	<input type="hidden" id="" value="${fn:escapeXml(mediaCenter.embedURL)}">
				                                                        </div>
				                                                 	</c:when>
	                                             				</c:choose>                                                
															</div>
				                                        </div>
													</div>
													</a>
												</div>
				     						</div>
			                            </div>
                        			</div>
                      			</div>
                        	</div>
                		</div>
                	</c:if>
            	</c:forEach>
			</div>
            <div class="arrow_set">
            	<ol class="carousel-indicators">
              		<c:forEach items="${mediaCenterComponents}" var="mediaCenterComp" varStatus="status">
              			<c:if test="${null != mediaCenterComp.mediaCenterTitle}">
               				<li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="<c:if test="${status.index} = 0">active</c:if>"></li>
               			</c:if>
              		</c:forEach>
               	</ol>
         		<a class="carousel-control-prev control_arrow" href="#carouselExampleIndicators" role="button" data-slide="prev">
	             	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
	             	<span class="sr-only">Previous</span>
	           	</a>
           		<a class="carousel-control-next control_arrow" href="#carouselExampleIndicators" role="button" data-slide="next">
             		<span class="carousel-control-next-icon" aria-hidden="true"></span>
             		<span class="sr-only">Next</span>
           		</a>
			</div>           
 		</div>
    </div>
</section>

<!-- Modal -->
<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="mediaModalTitle" aria-hidden="true">
	<button type="button" class="btn-dismiss close" data-dismiss="modal" aria-label="Close">
    	<span aria-hidden="true">x</span>
    </button>
  	<div class="modal-dialog modal-dialog-centered mediaModal_section" role="document">
     	<div class="modal-content">
        	<div class="modal-body">
               	<div class="video-player-container">
                	<div class="embed-responsive embed-responsive-16by9">
                    	<iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="100%" height="315" 
									src="${fn:escapeXml(mediaCenter.embedURL)}" frameborder="0" 
                           			allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen loading="lazy">
                       	</iframe>
                   	</div>
				</div>
        	</div>
     	</div>
  	</div>
</div>
<!-- Modal End -->
    