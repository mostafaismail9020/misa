<%@ taglib prefix="icon" tagdir="/WEB-INF/tags/responsive/icons" %>
<div class="container">
	<!-- Module description-->
	<div class="uiTest">
		<h1 class="uiTest-headline">appointmentDetails</h1>
		<p class="uiTest-description">mainly the properties which occor on the side including small modifications.</p>
	</div>
	<!-- End of Module description-->
	<div class="row">


		<div class="panelModule panelModule_halfRadius appointmentDetails">
			<div class="contentModule contentModule-wrap">
				<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
						<span class="contentModule-headline"><icon:calendarProfile/>Personal Appointment</span>
						<div class="appointmentDetails-properties">
							<div class="appointmentDetails-property appointmentList-location">
								<button class="appointmentList-locationBtn"><icon:location-pin/></button>
								<span class="appointmentList-label">Moscow, Russia</span>
							</div>
							<div class="appointmentDetails-property_delimiter"></div>
							<div class="appointmentDetails-property appointmentList-status">
								<span class="appointmentList-status-value appointmentList-icon_after appointmentList-icon_status appointmentList-icon_status_completed">Completed</span>
							</div>
							<div class="appointmentDetails-property appointmentList-date">
								<span class="appointmentList-date-month">Jan</span>
								<span class="appointmentList-date-day">07</span>
							</div>
						</div>
						<div class="contentModule-headline-border"></div>
			  </div>
				<div class="">
					<div class="contentModule contentModule-wrap">
						<div class="contentModule-actions contentModule-actions_spaceBetween contentModule-actions_wrap w-100">
							<span class="contentModule-headline">Services</span>
							<div class="contentModule-headline-border"></div>
						</div>
					</div>
				</div>
				<div class="contentModule-section">
					<div class="row w-100">
						<div class="col-sm-3">
							<dl class="dlList dlList_separated">
								<dt>Service</dt>
								<dd>Government-Service</dd>
							</dl>
						</div>
						<div class="col-sm-3">
							<dl class="dlList dlList_separated dlList_lineThrough">
								<dt>Type</dt>
								<dd>Government</dd>
							</dl>
						</div>
						<div class="col-sm-3">
							<dl class="dlList dlList_separated dlList_lineThrough">
								<dt>Ministry</dt>
								<dd>Chamber of Commerce</dd>
							</dl>
						</div>
						<div class="col-sm-3">
							<dl class="dlList dlList_separated dlList_lineThrough">
								<dt>Service Step</dt>
								<dd>Stamp original Document</dd>
							</dl>
						</div>
					</div>
				</div>

				<div class="contentModule-actions contentModule-actions_centered contentModule-actions_noMargin">
					<button class="btn btn_link back_to_service">
						<icon:download/> Download .ics file
					</button>
				</div>
			</div>
		</div>
	</div>
</div>