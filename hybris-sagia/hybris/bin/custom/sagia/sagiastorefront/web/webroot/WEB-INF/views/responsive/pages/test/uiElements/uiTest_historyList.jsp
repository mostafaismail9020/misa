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

<ul class="historyList">
	<li class="historyList-item historyList-item_current">
		<a href="#" class="historyList-link">
			<div class="historyList-id">500009991</div>
			<div class="historyList-info">
				<div class="historyList-date">06 Nov 2017</div>
				<div class="historyList-status historyList-status_process">In Process</div>
			</div>
		</a>
	</li>
	<li class="historyList-item">
		<a href="" class="historyList-link">
			<div class="historyList-id">200009991</div>
			<div class="historyList-info">
				<div class="historyList-date">02 Nov 2017</div>
				<div class="historyList-status historyList-status_accepted">Accepted</div>
			</div>
		</a>
	</li>
	<li class="historyList-item">
		<a href="" class="historyList-link">
			<div class="historyList-id">100009991</div>
			<div class="historyList-info">
				<div class="historyList-date">01 Nov 2017</div>
				<div class="historyList-status historyList-status_rejected">Rejected</div>
			</div>
		</a>
	</li>
	<li class="historyList-item">
		<a href="" class="historyList-link">
			<div class="historyList-id">100009991</div>
			<div class="historyList-info">
				<div class="historyList-date">01 Nov 2017</div>
				<div class="historyList-status historyList-status_rejected">Rejected</div>
			</div>
		</a>
	</li>	
</ul>
<br><br><br>
<ul class="historyList historyList_empty">
	<li class="historyList-item historyList-item_empty">
		No History List
	</li>
</ul>

</div>