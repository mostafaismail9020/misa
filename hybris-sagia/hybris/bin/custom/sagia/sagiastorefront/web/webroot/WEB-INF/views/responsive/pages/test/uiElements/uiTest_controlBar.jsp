<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">controlBar</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->

<div class="row">
	<div class="col-md-6">
		<div class="controlBar">
			<button class="controlBar-action active">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
			</button>
			<button class="controlBar-action">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
			</button>
		</div>
	</div>
	
	<div class="col-md-6">
		<div class="controlBar">
			<button class="controlBar-action">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
			</button>
			<button class="controlBar-action active">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
			</button>
		</div>
	</div>	
	
	<div class="col-md-6">
		<div class="controlBar">
			<a href="#" class="controlBar-action active">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
			</a>
			<a href="#" class="controlBar-action">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
			</a>
		</div>
	</div>
	
	<div class="col-md-6">
		<div class="controlBar">
			<a href="#" class="controlBar-action">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:book-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:book-grey/></span>
			</a>
			<a href="#" class="controlBar-action active">
				<span class="iconElement iconElement_controlBar iconElement_controlBar_active"><icon:head-green/></span>
				<span class="iconElement iconElement_controlBar"><icon:head-grey/></span>
			</a>
		</div>
	</div>	
</div>



</div>

