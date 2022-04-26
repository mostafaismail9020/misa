<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">historyList</h1>
	<p class="uiTest-description">HistoryList (in expandable aside module)</p>
</div>
<!-- End of Module description-->

<ul class="notificationList">
	<li class="notificationList-item notificationList-item_current">
		<a href="#" class="notificationList-link">
			<div class="notificationList-thumb">
				<span class="iconElement iconElement_expertProfile_green">
					<icon:expertProfile/>
				</span>
			</div>
			<div class="notificationList-note">
				<div class="notificationList-details">
					<div class="notificationList-name">Name Sirname</div>
					<div class="notificationList-date">Day</div>
				</div>
				<div class="notificationList-details">
					<div class="notificationList-title">Some Message Title</div>
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
					<div class="notificationList-name">Name Sirname</div>
					<div class="notificationList-date">Day</div>
				</div>
				<div class="notificationList-details">
					<div class="notificationList-title">Some other Message Title or Subject</div>
					<div class="notificationList-status"></div>
				</div>
			</div>
		</a>
	</li>
	<li class="notificationList-item">
		<a href="#" class="notificationList-link">
			<div class="notificationList-thumb">
				<span class="iconElement">
					<img alt="" src="${commonResourcePath}/images/Survey-list-icon.png"/>
				</span>
			</div>
			<div class="notificationList-note">
				<div class="notificationList-details">
					<div class="notificationList-name">System notification</div>
					<div class="notificationList-date">Day</div>
				</div>
				<div class="notificationList-details">
					<div class="notificationList-title">Some System Notification Subject</div>
					<div class="notificationList-status"></div>
				</div>
			</div>
		</a>
	</li>
	<li class="notificationList-item">
		<a href="#" class="notificationList-link">
			<div class="notificationList-thumb">
				<span class="iconElement">
					<img alt="" src="${commonResourcePath}/images/Appointment-list-icon.png"/>
				</span>
			</div>
			<div class="notificationList-note">
				<div class="notificationList-details">
					<div class="notificationList-name">Appointment notification</div>
					<div class="notificationList-date">Day</div>
				</div>
				<div class="notificationList-details">
					<div class="notificationList-title">Some Appointment Notification Title</div>
					<div class="notificationList-status"></div>
				</div>
			</div>
		</a>
	</li>
</ul>
<br><br><br>
<ul class="notificationList notificationList_empty">
	<li class="notificationList-item notificationList-item_empty">
		No Notifications
	</li>
</ul>
</div>