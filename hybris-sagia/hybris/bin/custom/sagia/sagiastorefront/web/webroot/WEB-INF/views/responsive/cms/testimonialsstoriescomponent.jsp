<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="success_story_component container-fluid success-main-content pl-0">
	<div class="row sucess_section  pb-5">
		<div class="col-md-5 p-md-0 sucess_img_bg aos-init aos-animate" data-aos="fade-up" data-aos-delay="0">
			<div class="quote-wrapper kingdom-quote-wrapper">
				<div class="sucess_img ">
					<c:choose>
				<c:when test="${not empty videoLink}">
				<div class="video-player-container">
						<div class="embed-responsive embed-responsive-16by9">
							<iframe class="video-fluid z-depth-1 vplayer embed-responsive-item" width="30%" height="215" src="${fn:escapeXml(videoLink.url)}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
						</div>
					</div>
				</c:when>
				<c:otherwise>
				    <c:if test="${not empty poster}">
						<img class="img-fluid" src="${poster.url}" alt='${poster.altText}' title='${poster.altText}' style="">
					</c:if>
				</c:otherwise>
				</c:choose>
				</div>
			</div>

		</div>
		<div class="col-md-7" data-aos="fade-up" data-aos-delay="600">
			<div class="sucess_content">

				<div class="sucess_content_itenlogo position-relative">
					<c:if test="${not empty companyLogo}">
						<img class=" js-responsive-image achievement_header_icon d-block " src="${companyLogo.url}" alt='${companyLogo.altText}' title='${companyLogo.altText}' style="">
					</c:if>
					<!-- <img class=" js-responsive-image achievement_header_icon  pr-5 mr-5 d-block " src="/_ui/responsive/common/images/rhq/Baker-Huges-Icon.png" alt="" title=""> -->
				</div>
				<c:if test="${not empty description}">
					<p class="text-left  w-100  mt-5">${description}</p>
				</c:if> 
				<!-- <p class="text-left  w-100  mt-5">
					"Baker Hughes has been an integral part of Saudi Arabia for more than 80 years, dating back to the Prosperity Well, Dammam #7, the first well drilled in the Kingdom in 1938. As an energy technology company, we are committed to supporting Saudi Vision 2030, with a strong focus on driving the energy transition, building a strong energy ecosystem, and supporting localization initiatives in support of Saudi Arabia’s journey towards becoming a global energy hub serving the world. Additionally, expanding Saudi Arabia as one of our regional headquarters will further strengthen our efforts to take energy forward along with our customers and partners in the region – making it safer, cleaner, and more efficient for people and the planet"

				</p> -->
				<c:if test="${not empty companyDetails}">
					<p class="font-weight-normal"> <strong>${companyDetails} </strong></p>
				</c:if>
				<!-- <p class="font-weight-normal"> <strong>Lorenzo Simonelli, Baker Hughes Chairman &amp; CEO   </strong></p> -->
			  
			</div>

		</div>
	</div>
</div>

<!-- <div class="testimonial_stories_component">
	
	<c:if test="${not empty poster}">
		<img class="img-fluid w-40" src="${poster.url}" alt='${poster.altText}' title='${poster.altText}' style="">
	</c:if>
	
	<c:if test="${not empty accordion}">
		<img class="img-fluid w-40" src="${accordion.url}" alt='${accordion.altText}' title='${accordion.altText}' style="">
	</c:if>
	
	<c:if test="${not empty companyLogo}">
		<img class="img-fluid w-40" src="${companyLogo.url}" alt='${companyLogo.altText}' title='${companyLogo.altText}' style="">
	</c:if>
	
	
	
	<c:if test="${not empty description}">
             <span >${description}</span>
         </c:if> 
          <c:if test="${not empty companyDetails}">
                <h5>${companyDetails}</h5>
            </c:if>
      
		</div>
		 -->
	

