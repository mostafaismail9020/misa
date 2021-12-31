<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="News_press" id="News_press">
    <div class="rect">
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" id="carousel" data-interval="false">                        						
        	<div class="carousel-inner">
        	
           		<c:url value="/mediaCenter/news" var="newsUrl"/>                            
                <c:forEach items="${portalNews}" var="news">
                	<div class="carousel-item">
                        <img class="d-block w-100" src="${fn:escapeXml(news.newsDetailsImage .url)}" alt="${news.newsTitle}">
                        <div class="toplist">
                          	<div class="container-fluid">
                            	<div class="mask flex-center">
                                  	<div class="row  align-items-center d-block ">
                                    	<div class="container">
                                      		<div class="col-md-6 col-sm-12">
                                         		<h2 class="News_Press_Releases"><spring:theme code="portal.media.press.releases" text="NEWS & PRESS RELEASES" /></h2>
                                      		</div>
                                     	</div>
                                     	<div class="col-md-5 col-sm-12 content-slider-part">
                                        	<div class="News_press_bgwhite">
                                           		<div class="top_date position-absolute">
	                                            	<h4 class="date"><fmt:formatDate value="${news.newsDate}" pattern="d" /></h4>
	                                              	<h6 class="date_name"><fmt:formatDate value="${news.newsDate}" pattern="MMMM" /></h6>
                                           		</div>
                                                <div class="p-5 paddding_align">
                                                   	<div>
                                                      	<h3 class="highlight_title">${news.newsTitle}</h3>
                                                      	<p class="para_title">${news.newsShortInformation} </p>
                                                      	<div class="container p-0 ">
                                                         	<div class="row ">
                                                            	<div class="col pull-left p-0">
	                                                             	<a href="${newsUrl}/${news.uid}">
	                                                                  	<div class="know_more"><spring:theme code="portal.media.know.more" text="Know more"/>&nbsp;
	                                                                     	<svg xmlns="http://www.w3.org/2000/svg" width="15.804" height="12.058" viewBox="0 0 15.804 12.058">
	                                                                        	<path id="Path" d="M12.058,6.045l-.87.87L6.651,2.393V15.8H5.408V2.393L.87,6.915,0,6.045,6.029,0Z" transform="translate(15.804) rotate(90)" fill="#00a6be"/>
	                                                                     	</svg>
	                                                                  	</div>
	                                                              	</a>
                                                            	</div>
                                                         	</div>
                                                      	</div>
                                                   	</div>
												</div>
											</div>
										</div>
									</div>
                             	</div>
                           	</div>
                     	</div>
					</div>
         		</c:forEach>
         	</div>
            <div class="arrow_set">
               	<ol class="carousel-indicators">
                  	<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                  	<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                  	<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
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
