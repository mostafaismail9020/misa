<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons"%>


<div class="container">
	
	<!-- Module description-->
	<div class="uiTest">
		<h1 class="uiTest-headline">sagiaNavigation</h1>
		<p class="uiTest-description">some Elements within sagia Navigation</p>
	</div>
	<!-- End of Module description-->
	<div class="sagiaNavigation">
	<div class="row">
		<div class="col-md-6"><span style="padding: 12px 10px; display: block;">sagiaNavigation-btn</span></div>
		<div class="col-md-6">
			<div class="sagiaNavigation-right">
				<a class="sagiaNavigation-btn sagiaNavigation-msg">
					<icon:mail/>
				</a>
				<a class="sagiaNavigation-btn sagiaNavigation-btn_indicated sagiaNavigation-msg">
					<icon:mail/>
				</a>
				<a class="sagiaNavigation-btn sagiaNavigation-msg">
					<span id ="unreadNotificationSpan" class="notifyCount notifyCount_small">11</span>
					<icon:mail/>
				</a>
				<div class="sagiaNavigation-entry sagiaNavigation-entry-hasSub">
					<button class="sagiaNavigation-btn sagiaNavigation-msg js-sagiaNavigationToggle">
						<icon:mail/>
					</button>
					<div class="sagiaNavigation-subPane-shadow js-sagiaNavigationToggle"></div>
					<div class="sagiaNavigation-subPane sagiaNavigation-subPane_right sagiaNavigation-subPane_visible">
						<div class="sagiaNavigation-subPane-title sagiaNavigation-subPane-title_borderGreen">Most Recent</div>
						<ul class="notificationList notificationList_small notificationList_borderBottom notificationList_noMargin">
							<li class="notificationList-item">
								<a href="#" class="notificationList-link">
									<div class="notificationList-thumb">
										<span class="iconElement iconElement_expertProfile_green">
											<icon:expertProfile/>
										</span>
									</div>
									<div class="notificationList-note">
										<div class="notificationList-details">
											<div class="notificationList-name">Steve Rodriguez</div>
											<div class="notificationList-date">Tue</div>
										</div>
										<div class="notificationList-details">
											<div class="notificationList-title">Lorem ipsum dolor sit amet</div>
											<div class="notificationList-status"></div>
										</div>
									</div>
								</a>
							</li>
							<li class="notificationList-item notificationList-item_current">
								<a href="#" class="notificationList-link">
									<div class="notificationList-thumb">
										<span class="iconElement iconElement_expertProfile_green">
											<icon:expertProfile/>
										</span>
									</div>
									<div class="notificationList-note">
										<div class="notificationList-details">
											<div class="notificationList-name">Holly Bethford</div>
											<div class="notificationList-date">15th February</div>
										</div>
										<div class="notificationList-details">
											<div class="notificationList-title">Etiam rhoncus maecen</div>
											<div class="notificationList-status"></div>
										</div>
									</div>
								</a>
							</li>
							<li class="notificationList-item">
								<a href="#" class="notificationList-link">
									<div class="notificationList-thumb">
										<span class="iconElement">
											<icon:user_system/>
										</span>
									</div>
									<div class="notificationList-note">
										<div class="notificationList-details">
											<div class="notificationList-name">System notification</div>
											<div class="notificationList-date">2nd February</div>
										</div>
										<div class="notificationList-details">
											<div class="notificationList-title">Et ante tincidunt tempus donec</div>
											<div class="notificationList-status"></div>
										</div>
									</div>
								</a>
							</li>
							<li class="notificationList-item">
								<a href="#" class="notificationList-link">
									<div class="notificationList-thumb">
										<span class="iconElement iconElement_expertProfile_green">
											<icon:expertProfile/>
										</span>
									</div>
									<div class="notificationList-note">
										<div class="notificationList-details">
											<div class="notificationList-name">John Smith</div>
											<div class="notificationList-date">1st February</div>
										</div>
										<div class="notificationList-details">
											<div class="notificationList-title">Sed fringilla mauris sit amet nib</div>
											<div class="notificationList-status"></div>
										</div>
									</div>
								</a>
							</li>
							<li class="notificationList-item">
								<a href="#" class="notificationList-link">
									<div class="notificationList-thumb">
										<span class="iconElement">
											<icon:user_calendar/>
										</span>
									</div>
									<div class="notificationList-note">
										<div class="notificationList-details">
											<div class="notificationList-name">Appointment notification</div>
											<div class="notificationList-date">1st February</div>
										</div>
										<div class="notificationList-details">
											<div class="notificationList-title">Aenean vulputate eleifend tellus</div>
											<div class="notificationList-status"></div>
										</div>
									</div>
								</a>
							</li>
						</ul>
						<div class="sagiaNavigation-subPane-actions">
							<button class="btn btn_slim btn_round btn_outline">View all</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>