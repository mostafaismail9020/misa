<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="editable" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="dashboard" tagdir="/WEB-INF/tags/responsive/dashboard" %>
<%@ attribute name="shareholders" required="false" type="java.util.List"%>

<div style="${editable ? 'margin-left:-15px; margin-right: -15px;' : 'width:auto; '}height:auto;" class="row">
    <%--<div class="col-12 col-md-8">--%>
    <div class="col-12 col-md-12">
        <%-- start Ask our expert --%>
        <div class="dashboardWidget js-dashboardWidget dashboardWidget_noRadiusRight no-border"
                      data-eServiceTutorial-index="3"
                      data-eServiceTutorial-position='<spring:theme code="dashboard.tutorial.step3.position" />'
                      data-eServiceTutorial-offset='<spring:theme code="dashboard.tutorial.step3.offset" />'
                      data-eServiceTutorial-borderradius='<spring:theme code="dashboard.tutorial.step3.border.radius" />'
                      data-eServiceTutorial-borderradius-sm='[0,13,0,13]'>
            <c:if test="${editable}">
                <dashboard:addAndRemoveComponent checkboxIndex="4"/>
            </c:if>
            <div class="dashboardWidget-headline js-dashboardWidget-headline">
              
                    <spring:theme code="dashboard.support.title"/>
          
                <div class="dashboardWidget-headline-icon"><icon:ask-our-expert/></div>
            </div>
            <div class="dashboardWidget-body ">
                <div class="dashboardWidgetAskOurExpert">
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="dashboardWidgetAskOurExpert-headline"><spring:theme code="dashboard.support.helpQuestion"/></div>
                            <ul class="dashboardWidgetAskOurExpert-list">
                               <%-- <li>
                                    <a href="${encodedContextPath}/my-sagia/sagia-profile" class="dashboardWidgetAskOurExpert-link">
                                        <icon:account-settings/><spring:theme code="dashboard.support.accountSettings"/>
                                    </a>
                                </li>--%>
<!--
                                <li>
                                   <a href="" class="dashboardWidgetAskOurExpert-link">
                                        <icon:licensing/><spring:theme code="dashboard.support.licensing"/>
                                    </a>
                                </li>
-->
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#eServiceTour" class="dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                        <icon:first-steps/><spring:theme code="dashboard.support.firstSteps"/>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="js-realTimeOnlineSupportCall-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                        <icon:call/><spring:theme code="realTimeOnlineSupportCall.title"/>
                                    </a>
                                </li>

                                   <li>
                                       <a href="#" class="j-realTimeOnlineSupport-enquiry dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                           <icon:contactUs-file/><spring:theme code="realTimeOnlineSupport.enquiry"/>
                                       </a>
                                   </li>

                                   <%-- <li>
                                       <a href="#" class="js-realTimeOnlineSupportChatList-open dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                           <icon:chat/><spring:theme code="realTimeOnlineSupportChatList.title"/>
                                       </a>
                                   </li>  --%> 

                                   <li>
                                       <a href="#" class="js-realtimeOnlineSupportEmailUs dashboardWidgetAskOurExpert-link dashboardWidgetAskOurExpert-link_stroke">
                                           <icon:contactUs-message/><spring:theme code="realtimeOnlineSupportEmailUs.title"/>
                                       </a>
                                   </li>

                                   <!--
                                                                   <li>
                                                                       <a href="" class="dashboardWidgetAskOurExpert-link">
                                                                           <icon:investments/><spring:theme code="dashboard.support.investments"/>
                                                                       </a>
                                                                   </li>
                                   -->
<%--                                <li>
                                    <a href="${encodedContextPath}/my-sagia/license/bidding" class="dashboardWidgetAskOurExpert-link">
                                        <icon:bidding-certificates/><spring:theme code="dashboard.support.biddingCertificates"/>
                                    </a>
                                </li>--%>
                               <%-- <li>
                                   <a href="${encodedContextPath}/service-search" class="dashboardWidgetAskOurExpert-link">
                                        <icon:services/><spring:theme code="dashboard.support.services"/>
                                    </a>
                                </li>--%>
                            </ul>
                        </div>
                        <div id="askYourAccountManagerSection" class="col-lg-5 dashboardWidgetAskOurExpert-seperator" style="display: none">
                            <div class="dashboardWidgetAskOurExpert-headline">
                                <spring:theme code="dashboard.support.ask"/>
                            </div>

                            <ul class="dashboardWidgetAskOurExpert-list dashboardWidgetAskOurExpert-list_oneColumn">
                                <li><icon:call/><a href="#" id="scheduleCallButton"><spring:theme code="support.schedulecall"/></a></li>
                                <li><icon:chat/><a href="#" id="liveChatButton"><spring:theme code="support.livechat"/></a></li>
                                <li><icon:enquiry/><a href="#" id="makeAnEnquiry"><spring:theme code="support.makeenquiry"/></a></li>
                            </ul>

                            <div class="row">
                                <div class="col">
                                    <div class="dashboardWidgetAskOurExpert-manager-img img-without-border">
                                            <span class="iconElement iconElement_expertProfile_green iconElement-colorSecondary_grey" style="width: 100%; height: 100%">
                                                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="48px" height="48px" viewBox="0 0 48 48" enable-background="new 0 0 48 48" xml:space="preserve">
                                                    <path fill="#E6E6ED" d="M24,0C10.745,0,0,10.745,0,24c0,6.121,2.295,11.703,6.066,15.942C10.462,44.884,16.866,48,24,48c7.27,0,13.779-3.236,18.18-8.342C45.804,35.455,48,29.985,48,24C48,10.745,37.255,0,24,0z"/>
                                                    <path fill="#5CC83B" d="M24,48c7.27,0,13.779-3.236,18.18-8.342c-1.908-1.747-6.629-4.112-12.207-6.376v-3.601c0.334-0.238,0.687-0.584,1.023-1.065c0.779-1.11,1.261-2.673,1.347-4.752c0.878-0.492,1.427-1.685,1.538-2.997c0.112-1.333-0.273-2.511-1.121-3.135c0.094-0.211,0.172-0.399,0.25-0.594c0.676-1.711-0.472-3.202-0.88-4.832c-1.354-5.429-12.184-6.24-14.594-1.341c-1.276-0.099-1.614,0.905-2.066,1.823c-0.395,0.803-0.744,1.633-0.372,2.873c0.119,0.403,0.268,0.82,0.454,1.283c0.069,0.176,0.23,0.559,0.347,0.839c-0.915,0.542-1.333,1.726-1.23,3.076c0.1,1.312,0.648,2.511,1.53,3.005c0.087,2.079,0.568,3.642,1.348,4.752c0.279,0.41,0.625,0.771,1.023,1.067v3.6c-5.887,2.387-10.825,4.891-12.503,6.659C10.462,44.884,16.866,48,24,48z"/>
                                                    <path fill="#32465A" d="M33.98,16.944v-0.233c0-5.455-4.44-9.894-9.896-9.894c-5.455,0-9.894,4.44-9.894,9.894v0.225c-0.917,0.138-1.598,0.924-1.602,1.852v4.46c0,0.856,0.58,1.572,1.366,1.8v0.081c0.001,2.549,2.066,4.614,4.615,4.617h2.578c0.189,0.578,0.727,0.97,1.335,0.972h2.685c0.776,0,1.411-0.637,1.411-1.413v-0.15c0-0.778-0.635-1.412-1.411-1.412h-2.685c-0.624,0-1.15,0.408-1.335,0.971h-2.578c-1.978-0.003-3.579-1.606-3.581-3.584v-0.08c0.804-0.23,1.36-0.963,1.364-1.8v-4.461c0-0.593-0.283-1.116-0.715-1.461v-0.616c0-4.659,3.79-8.447,8.447-8.447c4.658,0,8.45,3.788,8.45,8.447v0.548c-0.479,0.343-0.798,0.899-0.798,1.529v4.46c0,1.036,0.849,1.881,1.882,1.881h0.002c1.036,0,1.881-0.845,1.881-1.88v-4.461C35.501,17.877,34.844,17.114,33.98,16.944"/>
                                                </svg>
                                            </span>
                                    </div>
                                    <div class="dashboardWidgetAskOurExpert-manager-name"></div>
                                </div>
                                <div class="col">
                                    <div class="dashboardWidgetAskOurExpert-manager-action">
                                        <button type="button" data-toggle="modal" data-target="#contactPersonDetails" class="btn btn_slim btn_outline">
                                            <spring:theme code="support.contacts.contactme"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<!--
                    <%--
                    <div class="dashboardWidgetAskOurExpert-footer">
                        <div class="dashboardWidgetAskOurExpert-footer-action">
                            <button class="btn" id="getInTouchButton"><spring:theme code="support.getintouch"/></button>
                        </div>
                        <div class="dashboardWidgetAskOurExpert-footer-title">
                            <spring:theme code="support.didnotfindanswermessage"/>
                        </div>
                        <div class="dashboardWidgetAskOurExpert-footer-desc">
                            <spring:theme code="support.contactusdirectly"/>
                        </div>
                    </div>
                    --%>
-->
                </div>
            </div>
        </div>
        <%-- end Ask our expert --%>
    </div>

    <div class="col-12 col-md-4">
        <div class="row">
            <div class="col-12 col-md-6" style="display: none">
                <%-- start Shareholding Information --%>
                <div class="dashboardWidget dashboardWidget_noRadiusLeft">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="dashboard.shareholding.title"/>
                        <div class="dashboardWidget-headline-icon"></div>
                    </div>
                    <div class="dashboardWidget-body">
                        <div class="dashboardWidgetPie">
                            <div class="row">
                                <div class="col-6">
                                    <span id="supportShareholder1Name"></span>
                                    <div class="dashboardWidgetPie-chart js-dashboardWidgetPie-chart">
                                        <div class="dashboardWidgetPie-chart-text">
                                            <span id="supportShareholder1SharesPercentage"></span>%
                                        </div>
                                        <canvas id="dashboardWidgetPie-chart" width="100" height="100"></canvas>
                                    </div>
                                    <span id="supportShareholder1Nationality"></span>
                                </div>
                                <div class="col-6">
                                    <span id="supportShareholder2Name"></span>
                                    <div class="dashboardWidgetPie-chart js-dashboardWidgetPie-chart">
                                        <div class="dashboardWidgetPie-chart-text">
                                            <span id="supportShareholder2SharesPercentage"></span>%
                                        </div>
                                        <canvas id="dashboardWidgetPie-chart2" width="100" height="100"></canvas>
                                    </div>
                                    <span id="supportShareholder2Nationality"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--<div class="row">
            <div class="col-12 col-md-12">
                <div class="dashboardWidget dashboardWidget_bg dashboardWidget_noRadiusLeft">
                    <div class="dashboardWidget-headline js-dashboardWidget-headline">
                        <spring:theme code="dashboard.newsevents"/><div class="dashboardWidget-headline-icon"></div>
                    </div>
                    <div class="dashboardWidget-body ">
                        <div class="dashboardWidgetNews">
                            <div class="dashboardWidgetNews-item">
                                <div class="dashboardWidgetNews-text">
                                    <spring:theme code="dashboard.newsevents.news"/>
                                </div>
                                <div class="dashboardWidgetNews-action">
                                    <a class="btn" target="_new" href="https://investsaudi.sa/${currentLanguage.isocode}/news"><spring:theme code="dashboard.readnews"/></a>
                                </div>
                            </div>
                            <div class="dashboardWidgetNews-item">
                                <div class="dashboardWidgetNews-text">
                                    <spring:theme code="dashboard.newsevents.events"/>
                                </div>
                                <div class="dashboardWidgetNews-action">
                                    <a class="btn" target="_new" href="https://investsaudi.sa/${currentLanguage.isocode}/sectors-opportunities/opportunities/"><spring:theme code="dashboard.showevents"/></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>
    </div>
</div>





<div class="modal fade" id="contactPersonDetails"  tabindex="-1" role="dialog" aria-labelledby="contactPersonDetails" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-sm modal-dialog-centeredContent" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="modal-title">
                    <spring:theme code="support.contacts.askAccountManager"/>
                </div>
				<button type="button" class="modal-close" data-dismiss="modal" aria-label="Close">
					<icon:close/>
				</button>
			</div>
			<div class="modal-body">
				<div class="modal-heroImage modal-heroImage_80 text-center">
					<icon:contact-person/>
				</div>
				<div class="row">
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt><spring:theme code="general.telephone"/></dt>
                            <dd><a class="js-contacts-phone"></a></dd>
                        </dl>          
                    </div>
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt><spring:theme code="general.mobilenumber"/></dt>
                            <dd><a class="js-contacts-mobile-number"></a></dd>
                        </dl>          
                    </div>
                    <div class="col-md-6">
                        <dl class="dlList dlList_separated">
                            <dt><spring:theme code="general.email"/></dt>
                            <dd><a class="js-contacts-email"></a></dd>
                        </dl>          
                    </div>                    	                    				    
				</div>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>