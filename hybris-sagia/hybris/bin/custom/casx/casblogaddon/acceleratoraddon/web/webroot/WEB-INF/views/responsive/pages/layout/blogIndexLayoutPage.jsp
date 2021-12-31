<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<template:page pageTitle="${pageTitle}">
	<style type="text/css">
		.containerBlog img {
			width: 100%;
		}
	</style>
	<div id="contactUs">
		<%-- <common:contactUs /> --%>
	</div>
	<div class="container">
		<div class="containerBlog">
			<div>
				<cms:pageSlot position="Section1Head" var="feature">
					<cms:component component="${feature}" />
				</cms:pageSlot>
			</div>
			<div class="row why-blog-top">
				<div class="col-md-8 text-align-right">
					<cms:pageSlot position="Section2A" var="feature" element="div">
						<cms:component component="${feature}" />
					</cms:pageSlot>
					<cms:pageSlot position="Section3" var="feature" element="div">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div>
				<div class="col-md-4 text-align-left">
					<cms:pageSlot position="Section2B" var="feature" element="div">
						<cms:component component="${feature}" />
					</cms:pageSlot>
				</div>
			</div>
		</div>
	</div>
</template:page>