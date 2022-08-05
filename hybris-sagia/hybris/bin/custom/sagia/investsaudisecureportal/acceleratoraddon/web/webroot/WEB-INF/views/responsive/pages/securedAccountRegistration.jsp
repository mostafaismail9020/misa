<%@ page trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sptemplate" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/sptemplate"%>
<%@ taglib prefix="spuser" tagdir="/WEB-INF/tags/addons/investsaudisecureportal/responsive/spuser"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<sptemplate:page pageTitle="${pageTitle}">
    <jsp:body>

        <div class="row container-fluid">
            <div class="col-md-6">
                <div class="item_container group1">
                    <cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="side-content-slot cms_disp-img_slot register-page-left-content">
                        <div class="login-logo text-left">
                            <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                                <cms:component component="${logo}"/>
                            </cms:pageSlot>
                        </div>
                        <cms:component component="${feature}"/>
                    </cms:pageSlot>
                </div>
                <div class="item_container group2 next-hide">
                    <cms:pageSlot position="LeftContentSlot02" var="feature" element="div" class="side-content-slot cms_disp-img_slot">
                        <div class="login-logo text-left">
                            <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                                <cms:component component="${logo}"/>
                            </cms:pageSlot>
                        </div>
                        <cms:component component="${feature}"/>
                    </cms:pageSlot>
                </div>
            </div>
            <div class="col-md-6 mb-5">
                <div class="row col-lg-12 col-xl-12 col-12 register-account-partner-screen r-sn ">
                    <div class="login-right-wrapper">
                        <div class="login-register-text">REGISTER YOUR ACCOUNT</div>
                        <div class="register-role-selection">
                            <img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid" />
                        </div>
                        <div class="register-progress">
                            <span class="circle register-progress-selection">1</span>
                            <hr />
                            <span class="circle">2</span>
                            <hr />
                            <span class="circle">3</span>
                        </div>
                        <div class="login-register-role">SELECT YOUR ROLE</div>
                        <div class="login-role-selection">
                            <div class="login-role-selection-box role-investor">
                                    <img src="${commonResourcePath}/images/Investor-icon.png" alt="Investor" class="img-fluid img-ivestor-inactive" />
                                    <img src="${commonResourcePath}/images/Investor-icons.png" alt="Investor" class="img-fluid img-ivestor-active d-none" />
                                    <p class="role-text">Investor</p>
                            </div>
                            <div class="login-role-selection-box role-partner active">
                                <img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid img-partner-inactive d-none" />
                                <img src="${commonResourcePath}/images/Partner-icon.png" alt="Investor" class="img-fluid img-partner-active" />
                                <p class="role-text">Partner</p>
                            </div>
                        </div>

                        <div class="login-buttons">
                            <div class="col-md-6 col-sm-6 col-12">
                                <a class="login-btn login-btn-cancel">CANCEL</a>
                            </div>
                            <div class="col-md-6 col-sm-6 col-12">
                                <a class="login-btn login-btn-next active" >NEXT &nbsp;
                                    <svg xmlns="http://www.w3.org/2000/svg" width="15.835" height="10.561" viewBox="0 0 15.835 10.561">
                                        <path id="Icon_ionic-ios-arrow-round-forward" data-name="Icon ionic-ios-arrow-round-forward"
                                            d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
                                            transform="translate(-7.875 -11.252)" fill="#fff">
                                        </path>
                                    </svg>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row col-lg-12 col-xl-12 col-12 register-account-partner-screen2 r-sn next-hide">
                    <div class="login-right-wrapper">
                        <div class="login-register-text">REGISTER YOUR ACCOUNT</div>
                        <div class="register-role-selection">
                            <img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid" />
                            <img src="" class="img-fluid function-selected" alt=""/>
                        </div>
                        <div class="register-progress">
                            <span class="circle progress-completion"><div></div></span>
                            <hr />
                            <span class="circle register-progress-selection">2</span>
                            <hr />
                            <span class="circle">3</span>
                        </div>
                        <div class="login-register-role">CHOOSE YOUR FUNCTION</div>
                        <div class="login-role-selection function-userGroup">
                            <div class="login-role-selection-box function-BDUserGroup active">
                                    <img src="${commonResourcePath}/images/Business Development-blue.png" alt="Investor" class="img-fluid img-function-inactive d-none" />
                                    <img src="${commonResourcePath}/images/Business Development-white.png" alt="Investor" class="img-fluid img-function-active" />
                                    <p class="role-text"><spring:theme code="investsaudi.registration.lob.BDUserGroup.english"/><br><spring:theme code="investsaudi.registration.lob.BDUserGroup.arabic"/></p>
                            </div>
                            <div class="login-role-selection-box function-MarCommUserGroup ">
                                <img src="${commonResourcePath}/images/Marketing & Communication-blue.png" alt="Partner" class="img-fluid img-function-inactive" />
                                <img src="${commonResourcePath}/images/Marketing & Communication-white.png" alt="Investor" class="img-fluid img-function-active d-none" />
                                <p class="role-text">Marketing & Communication Representative</p>
                            </div>
                        </div>
                        <div class="login-buttons">
                            <div class="col-md-6 col-sm-6 col-12">
                                <a class="login-btn login-btn-cancel register-investor-screen2-btn-back">BACK</a>
                            </div>
                            <div class="col-md-6 col-sm-6 col-12">
                                <a class="login-btn login-btn-next register-investor-screen2-btn-next active">NEXT&nbsp;
                                    <svg xmlns="http://www.w3.org/2000/svg" width="15.835"
                                        height="10.561" viewBox="0 0 15.835 10.561"
                                        class="">
                                        <path id="Icon_ionic-ios-arrow-round-forward"
                                            data-name="Icon ionic-ios-arrow-round-forward"
                                            d="M17.973,11.454a.719.719,0,0,0-.005,1.012l3.344,3.35H8.585a.715.715,0,0,0,0,1.43H21.306L17.962,20.6a.724.724,0,0,0,.005,1.012.712.712,0,0,0,1.007-.006l4.532-4.565h0a.8.8,0,0,0,.149-.226.682.682,0,0,0,.055-.275.717.717,0,0,0-.2-.5L18.974,11.47A.7.7,0,0,0,17.973,11.454Z"
                                            transform="translate(-7.875 -11.252)" fill="#fff">
                                        </path>
                                    </svg>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" id="register-function-group" value="1"/>
                <div class="row col-lg-12 col-xl-12 col-12 register-account-partner-screen3 r-sn next-hide">
                    <div class="login-right-wrapper">
                        <div class="login-register-text">REGISTER YOUR ACCOUNT</div>
							<div class="register-role-selection">
								<img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid" />
                                <img src="" class="img-fluid function-selected" alt=""/>
							</div>
							<div class="register-progress">
								<span class="circle progress-completion register-progress-selection"><div></div></span>
								<hr />
								<span class="circle progress-completion register-progress-selection"><div></div></span>
								<hr />
								<span class="circle register-progress-selection">3</span>
							</div>
							<div class="login-register-role">ENTER YOUR DETAILS</div>
                            <div class="register__section register-BDUserGroup next-hide">
                                <c:url value="/register" var="submitAction" />
                                <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="BDUserGroup" />
                            </div>
                            <div class="register__section register-MarCommUserGroup next-hide">
                                <c:url value="/register" var="submitAction" />
                                <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="MarCommUserGroup"  />
                            </div>
                    </div>
                </div>


                <div class="row col-lg-12 col-xl-12 col-12 register-account-partner-screen4 r-sn next-hide">
                    <div class="login-right-wrapper">
                        <div class="login-register-text">REGISTER YOUR ACCOUNT</div>
							<div class="register-role-selection">
								<img src="${commonResourcePath}/images/Partner.png" alt="Partner" class="img-fluid" />
                                <img src="" class="img-fluid function-selected" alt=""/>
							</div>
							<div class="login-register-role">ACCOUNT REGISTRATION SUCCESSFUL</div>
                            <div class="row register-user-info success-circle">
								<span class=""><img src="${commonResourcePath}/images/completeed.png" alt="REGISTRATION SUCCESSFUL" /></span>
								<div class="col-md-12 register-form register-success-msg" dir="ltr">
									<span>Thank you for registering with us. Your registration is Under review. One of our export will be in touchwith you as soon as possible.</span>
								</div>
							</div>
                    </div>
                </div>

            </div>
        </div>
        <%--
        <div class="tab-controls">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#BDRegistration__tab" aria-controls="home" role="tab" data-toggle="tab">
                        <span>NDFC Partners</span>
                    </a>
                </li>
                <li role="presentation">
                    <a href="#MarcommRegistration__tab" aria-controls="profile" role="tab" data-toggle="tab">
                        <span>Marketing & Communication Representative</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="tab-content bootstrap-tab">
            <div class="register__container tab-pane fade in active" id="BDRegistration__tab">
                <div class="row" data-role="content">
                    <div class="col-md-4">
                        <div class="item_container">
                            <cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="side-content-slot cms_disp-img_slot">
                                <cms:component component="${feature}"/>
                            </cms:pageSlot>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="register__section">
                            <c:url value="/register" var="submitAction" />
                            <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="BDUserGroup" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="register__container tab-pane fade" id="MarcommRegistration__tab">
                <div class="row" data-role="content">
                    <div class="col-md-4">
                        <div class="item_container">
                            <cms:pageSlot position="LeftContentSlot02" var="feature" element="div" class="side-content-slot cms_disp-img_slot">
                                <cms:component component="${feature}"/>
                            </cms:pageSlot>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="register__section">
                            <c:url value="/register" var="submitAction" />
                            <spuser:register actionNameKey="register.submit" action="${submitAction}" userGroup="MarCommUserGroup"  />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        --%>
        <%@ include file="/WEB-INF/tags/addons/investsaudisecureportal/responsive/common/termsAndConditionsModal.tag" %>
    </jsp:body>
</sptemplate:page>
