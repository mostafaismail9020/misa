<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var navItems = $('.admin-menu li > a');
		var navListItems = $('.admin-menu li');
		var allWells = $('.admin-content');
		var allWellsExceptFirst = $('.admin-content:not(:first)');
		allWellsExceptFirst.hide();
		navItems.click(function(e) {
			e.preventDefault();
			navListItems.removeClass('active');
			$(this).closest('li').addClass('active');

			allWells.hide();
			var target = $(this).attr('data-target-id');
			$('#' + target).show();
		});
	});
</script>

<template:page pageTitle="${pageTitle}">
<div class="container pl-0">
  <div class="account-section-header no-border" style="padding-bottom: 10px;">
    <h1 class="heading text-left mt-0">
      <spring:theme code="text.contact.contact.bsu" text="Contact BSU" />
    </h1>
  </div>

    <div class="row">
      <div class="col-md-3 col-sm-5 pl-0">
        <div id="sidebar">
          <ul class="nav nav-pills nav-stacked admin-menu">
				  
			<li class="active">
				<a data-target-id="contact-us"> Contact Us</a>
			</li>
            <c:forEach items="${contacts.departments}" var="department" varStatus="status">
              <!-- only the first element in the set is visible: -->
              <c:if test="${status.first}">
                <li class=" ">
                  <a data-target-id="${department.code}">${department.name}</a>
                </li>
              </c:if>
              <c:if test="${!status.first}">
                <li>
                  <a data-target-id="${department.code}">${department.name}</a>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </div>
      </div> 
	  
		<div class="col-md-9 col-sm-7 col-xs-12 admin-content" id="contact-us">
			<div class="email-call" style="margin-top: -50px;">
            	<h2>CALL US</h2>
                <h3 class="text-center">
					We look forward to your enquiries and will be pleased to assist you.<br />
					We are available for you 24 hrs, 7 days a week.
				</h3>
				<div class="pt-4 pb-5 width60ncenter">
					<div class="row">
					<div class="col-md-6">
						<div class="contact-num text-center">
						<img src="${commonResourcePath}/images/local.png" alt="local number"
							class="img-fluid" />
							<div class="contact-text">
								<h3 class="cntct-number">
									<spring:theme code="portal.contact.us.local.number" text="Local: 8002449990" />
								</h3>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="contact-num text-center">
						<img src="${commonResourcePath}/images/International.png"
							alt="local number" class="img-fluid" />
							<div class="contact-text"> 
								<h3 class="cntct-number">
								<spring:theme code="portal.contact.us.international.number"
									text="International: 00966112035777" />
								</h3>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
			<!--<div class="seperator"></div>-->
			<div class="email-call">
			<h2>
			EMAIL US
			</h2>
			<h3 class="text-center email_h3 contact_line_height">                          
				You can also mail us and will be pleased to assist you.<br> We are available for you 24 hrs, 7 days a week.
			</h3>
			<div class="text-center">
				<div class="row">
				<div class="col-md-12">
					<div class="contact-num">
					<!-- <img src="${commonResourcePath}/images/Contact-us/local.png" alt="local number" class="img-fluid"/> -->
					<div class="contact-text">
						<h3 class="cntct-number mt-2">
						<spring:theme code="portal.contact.us.mail.id"
							text="InvestorCare@misa.gov.sa" />
						</h3>
					</div>
					</div>
				</div>
				</div>
			</div>
			</div>
		</div>
      <c:forEach items="${contacts.departments}" var="department">
        <div class="col-md-9 col-sm-7 col-xs-12 admin-content" id="${department.code}">
          <h1 class="header-account mt-0">${department.name}</h1>
          <div class="container-fluid pt-5">
            <div class="row">
              <c:forEach items="${department.contacts}" var="document">
                <div class="col-md-6" >
                  <div class="brach-location">
                    <p class="font-bold">${document.name}</p>
                    <ul class="list-unstyled m-0">
                      <li class="b-position">${document.posistion}</li>
                      <li class="b-phone"><em>Telephone:</em>${document.telephone}</li>
                      <li class="b-phone-int"><em>Mobile:</em>${document.mobile}</li>
                      <li class="b-email"><em>Email:</em>${document.email}</li>
                    </ul>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </c:forEach>

    </div>
</div>

	<storepickup:pickupStorePopup />
</template:page>

