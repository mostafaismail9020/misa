<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
	
	<!-- Module description-->
	<div class="uiTest">
		<h1 class="uiTest-headline">searchInputBox</h1>
		<p class="uiTest-description"></p>
	</div>
	<!-- End of Module description-->
	<div class="row">
		<div class="col" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox">
				<input class="searchInputBox-input" type="text" placeholder="Search"/>
				<button class="btn btn_link searchInputBox-switchModeBtn" type="submit">Advanced</button>
			</div>
		</div>
		<div class="col" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox searchInputBox_slim">
				<input class="searchInputBox-input" type="text" placeholder="Search"/>
				<button class="btn btn_link searchInputBox-switchModeBtn" type="submit">Advanced</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox searchInputBox_inline">
				<input class="searchInputBox-input" type="text" placeholder="Search"/>
				<button class="btn btn_link searchInputBox-switchModeBtn" type="submit">Advanced</button>
			</div>
		</div>
		<div class="col-md-6" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox searchInputBox_inline searchInputBox_slim">
				<input class="searchInputBox-input" type="text" placeholder="Search"/>
				<button class="btn btn_link searchInputBox-switchModeBtn" type="submit">Advanced</button>
			</div>
		</div>
		<div class="col-md-6" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox searchInputBox_dark">
				<input class="searchInputBox-input" type="text" placeholder="Search" />
			</div>	
		</div>
		<div class="col-md-6" style="line-height: 0; padding-bottom: 3px; padding-top: 3px;">
			<div class="searchInputBox searchInputBox_limited">
				<input class="searchInputBox-input" type="text" placeholder="Search" />
			</div>	
		</div>		
	</div>
</div>