<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%-- <section class="figures feature fo-container">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="titleArea">
                    <h2 class="feature_totitle">${component.carouselTitle}</span></h2>
             	</div>
                <div class="explore-all">
                    <div class="title">
                        <a href="${portal.cmsLinkUrl(component.exploreAllUrl)}" class="get-explore-btn rounded-pill"><spring:theme code="portal.exploreall.button.text"/>&nbsp;                   
                    	   <img src="${commonResourcePath}/images/explore-all-img.svg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw1NzF8aW1hZ2Uvc3ZnK3htbHxwb3J0YWwtbWVkaWEvaDYwL2hhOS84ODExMDczOTYyMDE0LnN2Z3w0ZTMyZDdlOGYwMWExMzU0YmM2Nzk0ZTZiZjhhMDRhMmMwZjA0NTZiZGU2YTMzMTBhMGYxMDU4MTBkMDZmYTM3" class="img-responsive"></a>
                		</a>
                    </div>
                </div>
       		</div>
            <div class="col-lg-12 pb-4">
                <div>
                    <nav class="laptop_viewonly feature-tab">
                        <div class="nav nav-tabs nav-fill border-0" id="nav-tab" role="tablist">
                            <a class=" nav-link active pl-0" id="top-featured" data-toggle="tab" href="#top-featured" 
                            		role="tab" aria-controls="top-featured" aria-selected="true"><spring:theme code="portal.top.featured.tab.name"/></a>
                            <a class=" nav-link mrg-lft mr-5 ml-5" id="newly-released" data-toggle="tab" href="#newly-released" 
                            		role="tab" aria-controls="newly-released" aria-selected="false"><spring:theme code="portal.newly.released.tab.name"/></a>
                            <!-- <a class=" nav-link mrg-lft mr-5 ml-5" id="last_viewed2" data-toggle="tab" href="#last_viewed" 
                            		role="tab" aria-controls="last_viewed" aria-selected="false">Last Viewed</a> -->
                        </div>
                    </nav>
                    <!-- <nav class="mobile_viewonly">
                        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            <a class=" nav-link active pl-0" id="Mtop-featured1" data-toggle="tab" href="#Mtop-featured" 
                            		role="tab" aria-controls="top-featured" aria-selected="true">Top Featured</a>
                            <a class=" nav-link mrg-lft mr-4 ml-4" id="Mnewly-released1" data-toggle="tab" href="#Mnewly-released" 
                            		role="tab" aria-controls="newly-released" aria-selected="false">Newly Released</a>
                        </div>
                    </nav> -->
                </div>
            </div>
            <div class="col-lg-12 p-0">
                <div class="tab-content feature-tab-content" id="pills-tabContent">
                    <c:forEach var="currentComponent" items="${featuredProducts}" varStatus="status">
                        <div class="tab-pane fade show <c:if test="${currentComponent.defaultCarousel}">active</c:if>" 
                        		id="${currentComponent.carouselId}" role="tabpanel" aria-labelledby="${currentComponent.carouselId}">
                            <div class="flex-container text-left">
                                <c:forEach var="product" items="${currentComponent.getProducts()}">
                                	<c:set var="superCategory" value="${product.supercategories[0]}"/>
                                	<c:set var="productUrl" value="/sectors-opportunities/${superCategory.code}/${product.code}"/>
                                    <!-- <div class="flex-slide one" style="background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0)), url(${commonResourcePath}/images/Featured_Opportunities/Destination-Management-Company.png);background-size: cover;background-position: center center;"> -->
                                    <div class="flex-slide one" style="background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0)), url(${superCategory.others[0].url});background-size: cover;background-position: center center;">
                                         <div class="flex-title">
                                            <div class="card-title">
                                            	<img class="img-fluid" src="${superCategory.picture.url}" alt=""/>
                                                <h5>${product.name}</h5>
                                                <div class="text-left flex-knowmore-btn">
                                                    <span class="more"><a href="${encodedContextPath}${productUrl}"><spring:theme code="portal.opportunity.know.more.button"/>&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right pt-1" viewBox="0 0 16 16">
                                                        <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z" /></a>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>                                       
                                    </div>
								</c:forEach>
                             </div>
                         </div>
                     </c:forEach>
                 </div>
             </div>
             <!-- <div class="col-lg-12 p-0 mt-4">
                 <div class="pull-right p-0 feature_righttop laptop_viewonly">
                     <ul class="nav nav-pills mb-3 nav-tabs pagination pg-blue justify-content-end border-0 mn_10" id="pills-tab" role="tablist">
                         <li class="nav-item  nav_margin page-item">
                             <a class="active page-link border-0" id="pills-profile-tab12" data-toggle="pill" href="#feature-home1" 
                             		role="tab" aria-controls="feature-profile1" aria-selected="false">
                                 <span id="left" onclick="myFunctionleftside()">
                         			<img src="${commonResourcePath}/images/arrow-right.png" class="img-responsive" id="firstimg">
                     			</span>
                             </a>
                         </li>
                         <li class="page-item"><a class="page-link border-0" id="numberss">01-02</a></li>
                         <li class="nav-item nav_margin page-item">
                             <a class="page-link border-0" id="pills-home-tab12" data-toggle="pill" href="#feature-profile1" 
                             		role="tab" aria-controls="feature-home1" aria-selected="true">
                                 <span id="right" onclick="myFunctionrightside()"><img src="${commonResourcePath}/images/arrow-left.png" 
                                 		class="img-responsive"></span>
                             </a>
                         </li>
                     </ul>
                 </div>
                 <div class="p-0 feature_righttop mobile_viewonly">
                     <ul class="nav nav-pills mt-4 nav-tabs pagination pg-blue justify-content-end border-0 mn_10" id="pills-tab" role="tablist">
                         <li class="nav-item  nav_margin page-item">
                             <a class="active page-link border-0" id="pills-profile-tab12" data-toggle="pill" href="#feature-home1" 
                             		role="tab" aria-controls="feature-profile1" aria-selected="false">
                                 <span id="left" onclick="myFunctionleftside()">
                         			<img src="img/Icon feather-arrow-left.png" class="img-responsive" id="firstimg">
                     			</span>
                             </a>
                         </li>
                         <li class="page-item"><a class="page-link border-0" id="numberss">01-02</a></li>
                         <li class="nav-item nav_margin page-item">
                             <a class="page-link border-0" id="pills-home-tab12" data-toggle="pill" href="#feature-profile1" 
                             		role="tab" aria-controls="feature-home1" aria-selected="true">
                                 <span id="right" onclick="myFunctionrightside()"><img src="img/arrow.png" class="img-responsive"></span>
                             </a>
                         </li>
                     </ul>
                 </div>
             </div> -->
        </div>
    </div>
</section> --%>
                               