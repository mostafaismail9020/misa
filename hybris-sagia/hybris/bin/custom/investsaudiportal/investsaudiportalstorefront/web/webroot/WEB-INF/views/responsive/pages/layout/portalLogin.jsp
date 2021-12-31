<%@ page trimDirectiveWhitespaces="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="header"
	tagdir="/WEB-INF/tags/responsive/common/header"%>
<style>
.background {
	background-color: rgba(29, 36, 44, 0.12);
	background-position: bottom center;
	background-repeat: no-repeat;
	background-size: cover;
	overflow: auto;
	background-attachment: fixed;
	background-image:
		url("/investsaudistorefront/_ui/responsive/common/images/login-background.jpg");
	min-height: 100vh;
}

.myDiv {
	text-align: center;
}

.content {
	position: relative;
	left: 80px;
	background: rgb(0, 0, 0); /* Fallback color */
	background: rgba(0, 0, 0, 10); /* Black background with 0.5 opacity */
	color: #f1f1f1;
	width: 10%;
	padding: 10px;
	margin-right: 5%
}

html, body {
	height: 100%;
}

body {
	background-color: #252830;
	color: white;
	font-family: 'TheSans';
	position: relative;
	overflow: hidden;
}

@font-face {
	font-family: 'TheSans';
	src: url('landing/font/GEThameen-DemiBold.eot?81411578');
	src: url('landing/font/GEThameen-DemiBold.eot?81411578#iefix')
		format('embedded-opentype'),
		url('landing/font/GEThameen-DemiBold.woff?81411578') format('woff'),
		url('landing/font/GEThameen-DemiBold.ttf?81411578') format('truetype'),
		url('landing/font/GEThameen-DemiBold.svg?81411578#fontello')
		format('svg');
	font-weight: normal;
	font-style: normal;
}

.main-container {
	display: table;
	width: 100%;
	height: 100%;
	background-size: cover;
	position: relative;
}
/*Development Image*/
/*    .main-container {
        background: url(/wps/wcm/connect/individuals/132be055-3e10-40f4-80f1-9be9da7d95f0/BQ-2.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE.Z18_62LC1H82K8G710ABO8LPBL34O5-132be055-3e10-40f4-80f1-9be9da7d95f0-mvqHsE8) no-repeat center;        
    }*/
/*QA Image*/
/*    .main-container {
        background: url(/wps/wcm/connect/individuals/86b6ce4b-eead-41fa-a02e-9a108ed8f24e/BQ-2.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE.Z18_62LC1H82K8G710ABO8LPBL34O5-86b6ce4b-eead-41fa-a02e-9a108ed8f24e-mvr19JE) no-repeat center;        
    }*/
/*Production Image*/
.main-container {
	background:
		url(/wps/wcm/connect/individuals/78da68a0-8630-42bb-93ea-a1a255df9fb5/BQ-2.jpg?MOD=AJPERES&CACHEID=ROOTWORKSPACE.Z18_G11AH2G0L0Q000QCOAIADT26B5-78da68a0-8630-42bb-93ea-a1a255df9fb5-mvvcxAt)
		no-repeat center;
}

.main-container-child {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

.main-container-child:before {
	content: '';
	display: block;
	/* position: absolute; */
	left: 0;
	right: 0;
	bottom: 0;
	top: 0;
	background-color: rgba(0, 0, 0, 0.2);
}

.main-header {
	position: relative;
}

.main-header h1 {
	color: #fff;
	font-size: 56px;
	font-family: 'TheSans', TheSans;
	font-weight: normal;
	text-transform: uppercase;
	-webkit-text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
	-moz-text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.main-header p {
	color: #fff;
	font-size: 18px;
	font-family: 'TheSans', TheSans;
	font-weight: normal;
}

.flex-parent {
	display: flex;
	justify-content: center;
	flex-direction: row-reverse;
}

.figure-wrapper {
	display: inline-block;
	position: relative;
	visibility: hidden;
	-webkit-animation-delay: 0.2s;
	-moz-animation-delay: 0.2s;
	animation-delay: 0.2s;
	margin: 2%;
}

.figure-wrapper-indi {
	padding: 0;
}

.figure-wrapper-indi a, .figure-wrapper-busi a {
	-webkit-transition: background 0.4s ease-out;
	-moz-transition: background 0.4s ease-out;
	transition: background 0.4s ease-out;
}

.figure-wrapper-indi a:hover, .figure-wrapper-busi a:hover {
	text-decoration: none;
}

.figure-wrapper-busi {
	padding: 0;
}

.figure-wrapper-busi a:hover {
	text-decoration: none !important;
}

.figure-wrapper a {
	display: table;
	width: 100%;
	height: 100%;
	position: relative;
}

.figure-wrapper a>span {
	display: table-cell;
	height: 100%;
	vertical-align: middle;
	text-align: center;
	color: #fff;
	cursor: pointer;
}

.figure-wrapper a img {
	width: 200px;
	margin: 0px 33px;
	-webkit-transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
	-moz-transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
	transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
}

.figure-wrapper a b {
	display: block;
	font-size: 22px;
	font-family: 'TheSans', TheSans;
	font-weight: normal;
	margin: 30px 0 0;
	-webkit-transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
	-moz-transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
	transition: all 0.2s cubic-bezier(.25, .8, .25, 1);
	-webkit-text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
	-moz-text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.figure-wrapper a b.ar {
	font-size: 22px;
}

.figure-wrapper a b+b {
	margin-top: 0;
}

.figure-wrapper a strong {
	display: inline-block;
	border-radius: 6px;
	overflow: hidden;
	background-color: rgba(0, 113, 77, 0.3);
	-webkit-box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px
		rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px
		rgba(0, 0, 0, 0.15);
	box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px
		rgba(0, 0, 0, 0.15);
	-webkit-transition: all 0.6s cubic-bezier(.25, .8, .25, 1);
	-moz-transition: all 0.6s cubic-bezier(.25, .8, .25, 1);
	transition: all 0.6s cubic-bezier(.25, .8, .25, 1);
}

.blue {
/* 	display: inline-block; */
/* 	border-radius: 6px; */
/* 	overflow: hidden; */
	background-color: rgba(0, 151, 169, 0.9);
/* 	-webkit-box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px */
/* 		rgba(0, 0, 0, 0.15); */
/* 	-moz-box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px */
/* 		rgba(0, 0, 0, 0.15); */
/* 	box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.15), 0 15px 12px */
/* 		rgba(0, 0, 0, 0.15); */
/* 	-webkit-transition: all 0.6s cubic-bezier(.25, .8, .25, 1); */
/* 	-moz-transition: all 0.6s cubic-bezier(.25, .8, .25, 1); */
/* 	transition: all 0.6s cubic-bezier(.25, .8, .25, 1); */
}

.no-touch_screen .figure-wrapper a:hover strong {
	-webkit-box-shadow: 0 0 48px rgba(0, 0, 0, 0.15), 0 30px 24px
		rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 0 48px rgba(0, 0, 0, 0.15), 0 30px 24px
		rgba(0, 0, 0, 0.15);
	box-shadow: 0 0 48px rgba(0, 0, 0, 0.15), 0 30px 24px
		rgba(0, 0, 0, 0.15);
}

.figure-wrapper .txt-header>div {
	padding: 9px 0;
	text-align-last: center;
	font-family: "TheSans";
}

.figure-wrapper .txt-header p {
	margin: 0;
	font-size: 20px;
	line-height: 1.2;
	font-family:  	MyriadHebrew-Regular;
	font-weight: normal;
}

.figure-wrapper .txt-header p.ar {
	font-family: 'TheSans', TheSans;
}

.figure-wrapper-busi .txt-header>div {
	background: rgba(0, 151, 169, 0.9);
	font-family: 'MyriadPro', MyriadPro;
}

.figure-wrapper-indi .txt-header>div {
	background: rgba(0, 113, 77, 0.9);
}

.no-touch_screen .figure-wrapper-busi a:hover strong {
	background-color: rgba(192,192,192,0.3);
	font-family: 'MyriadPro', MyriadPro;
}

.no-touch_screen .figure-wrapper-busi a strong {
	background-color: rgba(0, 151, 169, 0.3);
	font-family: 'MyriadPro', MyriadPro;
}

.no-touch_screen .figure-wrapper-indi a:hover strong {
	background-color: rgba(192,192,192,0.3);
	font-family: 'MyriadPro', MyriadPro;
}



.flip-container {
	perspective: 1000px;
}

.no-touch_screen .flip-container:hover .flipper, .no-touch_screen .flip-container.hover .flipper
	{
	-webkit-transform: rotateY(180deg);
	-moz-transform: rotateY(180deg);
	transform: rotateY(180deg);
}

.flip-container, .front, .back {
	width: 266px;
	height: 200px;
}

.flipper {
	-webkit-transition: 0.6s;
	-moz-transition: 0.6s;
	transition: 0.6s;
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	transform-style: preserve-3d;
	position: relative;
}

.front, .back {
	-webkit-perspective: 0;
	-webkit-backface-visibility: hidden;
	-webkit-transform: translate3d(0, 0, 0);
	visibility: visible;
	backface-visibility: hidden;
	position: absolute;
	top: 0;
	left: 0;
}

.front {
	z-index: 2;
	-webkit-transform: rotateY(0deg);
	-moz-transform: rotateY(0deg);
	transform: rotateY(0deg);
	background: rgba(255, 255, 255, 0);
}

.front2 {
	z-index: 2;
	-webkit-transform: rotateY(0deg);
	-moz-transform: rotateY(0deg);
	transform: rotateY(0deg);
	background: rgba(255, 255, 255, 0);
}

.back {
	-webkit-transform: rotateY(180deg);
	-moz-transform: rotateY(180deg);
	transform: rotateY(180deg);
	background-color: rgba(255, 255, 255, 0);
}

.misa-white-img {
	display: block;
	position: absolute;
	right: 30px;
	top: 30px;
	background: url(landing/img/absher-white.png) no-repeat center;
	width: 175px;
	height: 120px;
	background-size: contain;
	z-index: 10;
	background-position: right center;
}

@media screen and (max-width: 991px) {
	.figure-wrapper {
		height: 50%;
	}
	.flip-container, .front, .back {
		width: 206px !important;
		height: 150px !important;
	}
	.figure-wrapper a img {
		width: 150px;
	}
	.figure-wrapper a b {
		margin-top: 10px;
	}
	.misa-white-img {
		right: 12px;
		top: 12px;
		width: 132px;
		height: 90px;
	}
}

@media screen and (max-width: 768px) {
	body {
		overflow-y: initial;
	}
	.flip-container, .front, .back {
		width: 136px !important;
		height: 100px !important;
	}
	.figure-wrapper a img {
		width: 100px;
		margin: 0 18px;
	}
	.figure-wrapper a b {
		font-size: 18px !important;
	}
	.misa-white-img {
		right: 6px;
		top: 6px;
		width: 87px;
		height: 60px;
	}
}

@media screen and (max-width: 480px) {
	.main-header {
		display: none;
	}
	.figure-wrapper {
		display: block;
		margin: 0;
		padding: 20px 0;
	}
	.figure-wrapper1 {
		display: block;
		margin: 0;
	}
	.flip-container, .front, .back {
		width: 136px !important;
		height: 100px !important;
	}
	.figure-wrapper a img {
		width: 100px;
		margin: 0 18px;
	}
	.txt-wrapper {
		background: rgba(0, 0, 0, 0.2);
		padding: 9px;
		margin: 12px 0 0;
	}
	.flex-parent, .flex-wrapper {
		display: block;
		height: 100%;
	}
	.misa-white-img {
		display: none;
	}
}
</style>





<template:portalpage pageTitle="${pageTitle}">

	<jsp:body>
<div class=background> 
   
<div class="no-touch_screen">
<div class="myDiv">
     
  </div>
   <div class="misa-white-img wow fadeIn"
					style="visibility: visible; animation-name: fadeIn;"></div>
    <div class="container-fluid main-container wow fadeIn"
					style="padding: 0px; visibility: visible; animation-name: fadeIn;">
      
        <div class="main-container-child">
<!--             <div class="wow fadeIn main-header" data-wow-delay="0.8s">
                <h1>Absher Platform</h1>
            </div> -->
            
    <div class="figure-wrapper1"
							style="visibility: visible; animation-name: fadeInLeft;">
          <a href="https://eservices.sagia.gov.sa/en"> <img
								src="/investsaudistorefront/_ui/responsive/common/images/logo-en.svg"
								alt="" width="250" height="250" /></a>  
    </div> 
         
            <div class="flex-wrapper">
                <div class="flex-parent">
                    <div	class="wow fadeInRight figure-wrapper figure-wrapper-indi"	style="visibility: visible; animation-name: fadeInRight;">
                        <a	href="https://eservices.sagia.gov.sa/investsaudistorefront/investsaudi/en/login">
                            <span>
                                    <strong>
                                        <div class="flip-container">
                                            <div class="flipper">
                                            <div class="front" >
                                                    <img src="/investsaudistorefront/_ui/responsive/common/images/individual-hover.png"	class="img-hover">            
                                                </div>
                                                <div class="back">
                                                    <img src="/investsaudistorefront/_ui/responsive/common/images/IS_White.png"	class="img-normal">            
                                                </div>
                                            </div>
                                        </div>
                                        <div class="txt-header" style="text-align: centre;">
                                            <div>
                                                <p class="ar">الشركاء</p>
                                                <p>Partners</p>
                                            </div>                             
                                        </div>
                                    </strong>       
<!--                                    <div class="txt-wrapper wow fadeIn" style="visibility: visible; animation-name: fadeIn;">
                                        <b class="ar">خدمات إلكترونية متكاملة</b>
                                        <b>Integrated eServices</b>
                                    </div> -->  

                            </span>
                        </a>
                    </div>
                    <div	class="wow fadeInLeft figure-wrapper figure-wrapper-busi" style="visibility: visible; animation-name: fadeInLeft;">
                        <a	href="https://eservices.sagia.gov.sa/sagiastorefront/sagia/en/login">
                            <span>
                                    <strong>
                                        <div class="flip-container">
                                            <div class="flipper">
                                                <div class="front">
                                                    <img src="/investsaudistorefront/_ui/responsive/common/images/IS_White.png"	class="img-normal">            
                                                </div>
                                                <div class="back">
                                                    <img src="/investsaudistorefront/_ui/responsive/common/images/business-hover.png" class="img-hover">            
                                                </div>
                                            </div>
                                        </div>
                                        <div class="txt-header">
                                            <div>
                                                <p class="ar">المستثمرين</p>
                                                <p>Investors</p>
                                            </div>
                                        </div>
                                    </strong>
<!--                                     <div class="txt-wrapper wow fadeIn" style="visibility: visible; animation-name: fadeIn;">
                                        <b class="ar">خدمات مؤسستك بين يديك</b>
                                        <b>Establishments eServices</b>
                                    </div>  --> 

                            </span>
                        </a>                
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./Absher-أبشر_files/jquery.min.js.download"></script>
    <script src="./Absher-أبشر_files/bootstrap.min.js.download"></script>
    <script src="./Absher-أبشر_files/wow.min.js.download"></script>
    <script>
					$(document)
							.ready(
									function() {
										//Check Touch Devices
										if (is_touch_screen = 'ontouchstart' in document.documentElement) {
											$('body').addClass('touch_screen');
										} else {
											$('body').addClass(
													'no-touch_screen');
										}
										//Initialize WOW.js
										new WOW().init();
									});
				</script>
</div>

</div>
    </jsp:body>
</template:portalpage>

