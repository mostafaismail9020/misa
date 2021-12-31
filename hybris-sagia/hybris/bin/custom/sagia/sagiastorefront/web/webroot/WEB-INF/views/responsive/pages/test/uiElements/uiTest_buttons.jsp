<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">Buttons</h1>
	<p class="uiTest-description">Collection of buttons</p>
</div>
<!-- End of Module description-->

</div>

<div class="container">
	Normal
	<div class="row">
		<div class="col-xs-12 col-md-2"  style="line-height: 36px;">
			default<br>
			secondary<br>
			warning<br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn">Lorem ipsum</button><br><br>
			<button class="btn btn-secondary">Lorem ipsum</button><br><br>
			<button class="btn btn-warning">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_outline">Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_outline">Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_outline">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_round">Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_round">Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_round">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_link">Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_link">Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_link">Lorem ipsum</button><br>
		</div>
	</div>

	<br><br>
	Normal link
	<div class="row">
		<div class="col-xs-12 col-md-2"  style="line-height: 36px;">
			default<br>
			secondary<br>
			warning<br>
		</div>
		<div class="col-6 col-md-2">
			<a href="" class="btn">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-secondary">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-warning">Lorem ipsum</a><br>
		</div>
		<div class="col-6 col-md-2">
			<a href=""  class="btn btn_outline">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-secondary btn_outline">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-warning btn_outline">Lorem ipsum</a><br>
		</div>
		<div class="col-6 col-md-2">
			<a href=""  class="btn btn_round">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-secondary btn_round">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-warning btn_round">Lorem ipsum</a><br>
		</div>
		<div class="col-6 col-md-2">
			<a href=""  class="btn btn_link">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-secondary btn_link">Lorem ipsum</a><br><br>
			<a href=""  class="btn btn-warning btn_link">Lorem ipsum</a><br>
		</div>
	</div>
	<br><br>
	Disabled
	<div class="row">
		<div class="col-xs-12 col-md-2" style="line-height: 36px;">
			default<br>
			secondary<br>
			warning<br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-warning" disabled>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_outline" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_outline" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_outline" disabled>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_round" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_round" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_round" disabled>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_link" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_link" disabled>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_link" disabled>Lorem ipsum</button><br>
		</div>
	</div>
	<br><br>
	Hover
	<div class="row">
		<div class="col-xs-12 col-md-2"  style="line-height: 36px;">
			default<br>
			secondary<br>
			warning<br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-warning" hover>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_outline" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_outline" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_outline" hover>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_round" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_round" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_round" hover>Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_link" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-secondary btn_link" hover>Lorem ipsum</button><br><br>
			<button class="btn btn-warning btn_link" hover>Lorem ipsum</button><br>
		</div>
	</div>
	<br><br>
	Slim
	<div class="row">
		<div class="col-xs-12 col-md-2"  style="line-height: 36px;">
			default<br>
			secondary<br>
			warning<br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_slim">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-secondary">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-warning">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_slim btn_outline">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-secondary btn_outline">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-warning btn_outline">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_slim btn_round">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-secondary btn_round">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-warning btn_round">Lorem ipsum</button><br>
		</div>
		<div class="col-6 col-md-2">
			<button class="btn btn_slim btn_link">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-secondary btn_link">Lorem ipsum</button><br><br>
			<button class="btn btn_slim btn-warning btn_link">Lorem ipsum</button><br>
		</div>
	</div>

	<br><br>


</div>	