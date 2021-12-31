<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>

<div class="mainSection mainSection_dark">
	<div class="container">
		<div class="mainSection-header">
			<h1 class="mainSection-headline">Notification Center</h1>
		</div>
	</div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
	<div class="container">
		<div class="mainSection-linkActions mainSection-linkActions_spaceBetween">
			<div class="row">
				<div class="searchInputBox searchInputBox_inline searchInputBox_inline_aside">
					<input class="searchInputBox-input" type="text" placeholder="Search"/>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="mainSection mainSection_dark mainSection_noPaddingTop">
	<div class="container contentModule">
		<hr class="contentModule-separator contentModule-separator_green" />
		<div class="expandableContent expanded">
			 <div class="expandableContent-aside">
				<div class="panelModule panelModule_halfRadius">
					<div class="contentModule">
						<div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
							<div class="contentModule-headline contentModule-headline_noMargin">
							Your messages</div>
							<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap contentModule-actions_right contentModule-actions_noMargin">
								<button class="btn btn_slim btn-secondary btn_link btn_link_text">Mark all as read</button>
							</div>
							<ul class="notificationList notificationList_smallMarginTop">
								<li class="notificationList-item notificationList-item_current">
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
								<li class="notificationList-item notificationList-item_unread">
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
								<li class="notificationList-item">
									<a href="#" class="notificationList-link">
										<div class="notificationList-thumb">
											<span class="iconElement iconElement_expertProfile_green">
												<icon:expertProfile/>
											</span>
										</div>
										<div class="notificationList-note">
											<div class="notificationList-details">
												<div class="notificationList-name">Lorem Ipsum</div>
												<div class="notificationList-date">23rd January</div>
											</div>
											<div class="notificationList-details">
												<div class="notificationList-title">Lorem ipsum dolor sit amet</div>
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
												<div class="notificationList-name">Lorem Ipsum</div>
												<div class="notificationList-date">19th January</div>
											</div>
											<div class="notificationList-details">
												<div class="notificationList-title">Lorem ipsum dolor sit amet</div>
												<div class="notificationList-status"></div>
											</div>
										</div>
									</a>
								</li> 
							</ul>
							<%--
							<div class="emptyIndicator">
								No Messages
							</div>
							--%>
						</div>
					</div>
				</div>
			</div>
			
			
			
			<div class="expandableContent-main">
				<div class="panelModule panelModule_halfRadius panelModule_smallMargin">
					<div class="contentModule">
						<div class="contentModule-section contentModule-section_noDivider contentModule-section_noMargin">
							<ul class="messageList">
								<li class="messageList-item">
									<div class="messageList-header">
										<div class="messageList-header-details">
											<div class="messageList-img messageList-img_large">
												<!-- img src="https://dummyimage.com/80x80/DDDDDD/fff" alt="" -->
												<span class="iconElement iconElement_expertProfile_green">
													<icon:expertProfile/>
												</span>
											</div>
											<div class="messageList-header-details-content">
												<div class="contentModule-headline contentModule-headline_noMargin">
													Steve Rodriguez
												</div>
												<div class="contentModule-subheadline contentModule-subheadline_small">
													Tuesday, 21 February, 2018
												</div>
											</div>
										</div>
										<div class="messageList-header-actions">
											<div>
												<button class="btn btn_outline btn_round btn_slim">Print <span class="iconElement iconElement_print"><icon:print/></span></button>
											</div>
										</div>
									</div>
									<div class="messageList-content messageList-content_standalone">
										<div class="messageList-message">
											<p>Lorem ipsum,</p>
											<p>dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.</p>
											<p>Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi.</p>
											<p>Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis.</p>
											<p>Regards,</p>
											<p>Steve</p>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>