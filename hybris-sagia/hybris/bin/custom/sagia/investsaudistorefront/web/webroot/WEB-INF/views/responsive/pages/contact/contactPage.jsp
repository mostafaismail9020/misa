<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>
<%@ taglib prefix="storepickup"
	tagdir="/WEB-INF/tags/responsive/storepickup"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
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


<div class="account-section-header no-border" style="padding-bottom: 20px;padding-left: 10px">
		<h1 class="heading text-left mt-4 my-0"><spring:theme code="text.contact.contact.bsu" text="Contact BSU" /></h1>
			
</div>

	<div class="row">
		<div class="col-md-3 col-sm-5">
			<div id="sidebar">

				<ul class="nav nav-pills nav-stacked admin-menu">

					<c:forEach items="${contacts.departments}" var="department"
						varStatus="status">

						<!-- only the first element in the set is visible: -->
						<c:if test="${status.first}">
							<li class="active"><a style="color: #272a35"
								data-target-id="${department.code}"><i
									style="color: #272a35" class="fa fa-list-alt fa-fw"></i>${department.name}</a></li>
						</c:if>
						<c:if test="${!status.first}">
							<li><a style="color: #272a35"
								data-target-id="${department.code}"><i
									style="color: #272a35" class="fa fa-list-alt fa-fw"></i>${department.name}</a></li>
						</c:if>
					</c:forEach>

				</ul>


			</div>
		</div>

		<c:forEach items="${contacts.departments}" var="department">

			<div class="col-md-9 col-sm-7 col-xs-12 admin-content" id="${department.code}">
				<div class="container-fluid">
					<div class="row">
						<c:forEach items="${department.contacts}" var="document">
							<div class="col-lg-4 col-md-6 col-sm-12 d-flex alignment-stretch" >
								<div class="brach-location">
									<p class="font-bold">${document.name}</p>
									<ul class="list-unstyled m-0">

										<li class="b-position" style="min-height: 40px;">${document.posistion}</li>
										<li class="b-phone"><em>Telephone:</em>${document.telephone}</li>
										<li class="b-phone"><em>Mobile:</em>${document.mobile}</li>
										<li class="b-email" style="min-height: 40px;"><em>Email:</em>${document.email}</li>
									</ul>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>





	</div>

	<storepickup:pickupStorePopup />
</template:page>

