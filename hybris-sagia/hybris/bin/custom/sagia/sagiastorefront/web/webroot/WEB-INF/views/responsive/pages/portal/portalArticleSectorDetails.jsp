<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="template"
tagdir="/WEB-INF/tags/responsive/template" %> <%@ taglib prefix="cms"
uri="http://hybris.com/tld/cmstags" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="header"
tagdir="/WEB-INF/tags/responsive/common/header" %> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags" %>

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
      <cms:component component="${slotComponent}" />
    </cms:pageSlot>

    <main>
      <div class="Inc-sector-banner">
        <div class="Inc-sector-banner-container" data-aos="fade-up">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <h1 class="article-details-sector-page-general-title">
                  Sector Name
                </h1>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Article Details Page -->
      <section class="article-details-sector-page">
        <div class="container">
          <!-- Top -->
          <div class="row">
            <div class="col-md-9">
              <h1 class="article-details-sector-page-title">Overview</h1>
              <p>
                The Ministry of Investment of Saudi Arabia (MISA) has
                facilitated four more investment agreements on the sidelines of
                the Future Investment Initiative (FII6) across the education,
                entertainment and biotechnology sectors. The agreements signed
                showcase the government’s commitment to transforming quality of
                life in the Kingdom, coupled with investor recognition of the
                vast opportunities available.
              </p>

              <div
                class="article-details-sector-page-info-box-container mt-5 mb-5"
              >
                <div class="article-details-sector-page-info-box">
                  <div class="article-details-sector-page-info-box-item">
                    <h2>8,872,712</h2>
                    <p>Population (2020)</p>
                  </div>

                  <div class="article-details-sector-page-info-box-item">
                    <h2>380,000</h2>
                    <p>Area in sq.km</p>
                  </div>
                  <div class="article-details-sector-page-info-box-item">
                    <h2>21</h2>
                    <p>Governorates</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-3 article-details-sector-page-img">
              <img
                alt=""
                src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ"
              />
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <div class="value-proposition-title mt-3 mb-3">
                Value Proposition
              </div>
            </div>
            <div class="col-md-12">
              <div class="value-proposition-items">
                <div class="value-proposition-item">
                  <div class="chart-multiple"></div>
                  <div class="value-proposition-text">
                    Large and growing market demand for water
                  </div>
                </div>
                <div class="value-proposition-item">
                  <div class="building-home"></div>
                  <div class="value-proposition-text">
                    Global leader in desalination
                  </div>
                </div>
                <div class="value-proposition-item">
                  <div class="briefcase"></div>
                  <div class="value-proposition-text">
                    Strong commitment to develop infrastructure beyond
                    desalination
                  </div>
                </div>
              </div>
            </div>
          </div>


          <div class="row">
            <div class="col-md-12">
              <div class="energy-opportunities-title mt-3 mb-3">
                ENERGY OPPORTUNITIES
              </div>
            </div>
        
			<div class="energy-opportunities-items">
				<div class="energy-opportunities-item col-md-4">
					<div class="energy-opportunities-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="energy-opportunities-texts">
						<div class="energy-opportunities-header">
							Digital Twins For Tourism, Heritage And Events
						</div>
						<div class="energy-opportunities-content">
							Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.                                        </div>
						<div class="energy-opportunities-footer">
							Market CAGR 19-26 +3%
						</div>
					</div>
				</div>
				<div class="energy-opportunities-item col-md-4">
					<div class="energy-opportunities-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="energy-opportunities-texts">
						<div class="energy-opportunities-header">
							Digital Twins For Tourism, Heritage And Events
						</div>
						<div class="energy-opportunities-content">
							Amet minim mollit non deserunt ullamco est sit aliqua dolor o amet sint.
						</div>
						<div class="energy-opportunities-footer">
							Market CAGR 19-26 +3%
						</div>
					</div>
				</div>
				<div class="energy-opportunities-item col-md-4">
					<div class="energy-opportunities-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="energy-opportunities-texts">
						<div class="energy-opportunities-header">
							Digital Twins For Tourism, Heritage And Events
						</div>
						<div class="energy-opportunities-content">
							Amet minim mollit non deserunt ullamco est sit aliqua dolor o amet sint.
						</div>
						<div class="energy-opportunities-footer">
							Market CAGR 19-26 +3%
						</div>
					</div>
				</div>
			</div>
          </div>

          <div class="row">
            <div class="col-md-12 mt-3">
              <a
                class="view-opportunity-button"
                href="/sectors-opportunities/opportunities"
                ><spring:theme
                  code="portal.article.sector.details.view.opportunities"
                  text="View all opportunities"
              /></a>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12 mt-5 mb-5">
              <h1 class="core-components-title">Core Components</h1>
              <p class="core-components-content">
                A core component of the strategy is the Global Supply Chain
                Resilience Initiative – launched on Sunday. The initiative aims
                to enable global investors to create low-risk, low-cost and
                low-carbon supply chains leveraging the Kingdom’s natural
                resources, strong logistics infrastructure and untapped
                potential. In its launch phase, the initiative aims to attract
                more than US$10bn of industrial and service investments in
                global supply chains to the Kingdom.
              </p>
            </div>
          </div>

		<div class="row">
		
		
			<div class="col-md-4 mt-3">
				<div class="success-stories-video br-10">
					<iframe width="100%" src="https://www.youtube.com/embed/jP-z4m92UAE"
						title="TAP INTO THE UNTAPPED   Invest Saudi  20200722 1000 1  ICT" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						allowfullscreen></iframe>
				</div>
				<a class="contact-expert-button" href="/sectors-opportunities/opportunities">
					<spring:theme code="portal.article.sector.details.contact.expert" text="Contact Our Expert ->" />
				</a>
		
		
			</div>
			<div class="col-md-8 mt-3">
		
		
				<h1 class="success-stories-title">
					Success Stories from the Sector
				</h1>
				<p class="core-components-content">
					There are a lot of efforts going on to make it easier for people
					to come and invest in Saudi Arabia, , which we saw recently
					recognized in the Kingdom’s position in the World Bank’s Ease of
					Doing Business rankings.</p><span class="core-components-content-dark">-Stephen McCalion, COO, GE Saudi
					Arabia and Bahrain</span>
		
				<p class="core-components-content mt-1">Company Name : GE Saudi Arabia and Bahrain</p>
				<p class="core-components-content mt-1">Name : Stephen McCalion</p>
				<p class="core-components-content mt-1">Position : COO</p>
				<p class="core-components-content mt-1"> Sector name : Energy andWater</p>
		
				<div class="success-stories-title">
					<h4> Look at other stories </h4>
				</div>

				<div class="success-stories-container">
				
					
					<div class="boxes">

						<div class="box">
							<div class="icon left-icon">
								<svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" viewBox="0 0 21 21" fill="none">
									<path d="M16.0489 10.6935H5.53333M9.57778 15.5469L5.29641 11.2655C4.98052 10.9496 4.98052 10.4375 5.29641 10.1216L9.57778 5.84021" stroke="#D7DEE6" stroke-width="0.91" stroke-linecap="round"/>
									</svg>

							</div>
							
						</div>
						<div class="box">
							<div class="icon sectors-icon"></div>
							
						</div>
						<div class="box">
							<div class="icon sectors-icon"></div>
							
						</div>
						<div class="box">
							<div class="icon sectors-icon"></div>
							
						</div>

						<div class="box">
							<div class="icon next-icon"></div>
							
						</div>
						
					
					</div>
				</div>
			</div>
		
		</div>

	


		  <div class="row mt-5 mb-5">
            <div class="col-md-12">
              <div class="latest-news-title mt-3 mb-3">
				Latest news
              </div>
            </div>
        
			<div class="latest-news-items">
				<div class="latest-news-item col-md-4">
					<div class="latest-news-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="latest-news-texts">
						<div class="latest-news-header">
							DSaudi investment ministry signs four investment agreements that will enhance quality of life in the kingdom
						</div>
			
						<div class="latest-news-footer">
							27 Oct 2022
						</div>
					</div>
				</div>
				<div class="latest-news-item col-md-4">
					<div class="latest-news-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="latest-news-texts">
						<div class="latest-news-header">
							The Ministry of Investment signed 11 investment agreements with international companies on the sidelines of the Sixth Future Investment Initiative conference
						</div>
						
						<div class="latest-news-footer">
							24 Oct 2022
						</div>
					</div>
				</div>
				<div class="latest-news-item col-md-4">
					<div class="latest-news-item-img">
						<img alt="" src="/medias/industrial-manufacturing-banner.jpg?context=bWFzdGVyfHBvcnRhbC1tZWRpYXw2MDUxMXxpbWFnZS9qcGVnfHBvcnRhbC1tZWRpYS9oMDYvaDhlLzg4MTE3MTU2OTA1MjYuanBnfGY4MGUwYmU2MGM0MGRjYzZhZmY1ZDRiMDZjYmNhNzhlNGExNDgyZWVlYzQyYzg0ZjEyY2M1M2Y5ZjRjNWFmNmQ" />
					</div>
					<div class="latest-news-texts">
						<div class="latest-news-header">
							HRH Crown Prince Mohammed bin Salman bin Abdulaziz Launches GSCRI
						</div>
						
						<div class="latest-news-footer">
							24 Oct 2022
						</div>
					</div>
				</div>
			</div>
          </div>

		  <div class="row">
			<div class="col-md-12 wow fadeIn" data-wow-delay="0.3s">

			  <div class="col-md-12 videos-pagination-box">
					<ul class="pagination pg-darkgrey videos-pagination-bottom  mt-4" >
					 
						<li class="page-item prev prev-item">
							<a class="page-link waves-effect waves-light" href="/test">
								<
							</a>
						</li>
					
					<li class="page-item active">
						<a class="page-link waves-effect waves-light" href=/test> 1 of 2</a>
					</li>
					<li class="page-item next-item">
						<a class="page-link waves-effect waves-light arrow-left" href="${url}${param.page+1}">
							>
						</a>
					</li>              
			   </ul>
		   </div>
	   </div>
   </div>

        </div>
      </section>
      <!-- Article Details Page -->
    </main>
  </jsp:body>
</template:portalpage>
