<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">eService Tutorial</h1>
	<p class="uiTest-description">Container for steps inside the page tour. The description and title for each step should be placed inside the list with the class eServiceTutorial.<br>
	Steps can be then called by data attributes on the elements that should be highlighted. Available options are: <br><br>
	data-eServiceTutorial-index="0" (mandatory, corresponding with the list element inside ul.eServiceTutorial)<br>
	data-eServiceTutorial-position="top" (mandatory, choose between top, right, bottom and left)<br>
	data-eServiceTutorial-offset="20" (optional, padding offset for highlight element)<br>
	data-eServiceTutorial-borderradius="[0,20,0,20]" (optional, array for border-radius)
	</p>
</div>
<!-- End of Module description-->
<a class="btn btn_slim" data-toggle="modal" data-target="#eServiceTour">Start Tour</a>
<ul class="eServiceTutorial js-eServiceTour">
	<li class="eServiceTutorial-item" id="eServiceTutorial-item-0">
		<div class="eServiceTutorial-panel">
			<div class="eServiceTutorial-panelInner">
				<div class="eServiceTutorial-header">
					<div class="eServiceTutorial-headline">Headline 1</div>
					<button class="eServiceTutorial-close js-eServiceTour-close">
						<icon:close/>
					</button>
				</div>
				<div class="eServiceTutorial-body">
					<div class="eServiceTutorial-description">
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Maxime veniam, aut, quod necessitatibus laborum totam sed iste iusto cumque non ducimus, iure nihil mollitia numquam ratione porro? Hic, libero, nemo?
					</div>
					<div class="eServiceTutorial-actions">
						<a class="btn btn_slim js-eServiceTour-next">Next</a>
					</div>
				</div>
			</div>
		</div>
	</li>
	<li class="eServiceTutorial-item" id="eServiceTutorial-item-1">
		<div class="eServiceTutorial-panel">
			<div class="eServiceTutorial-panelInner">
				<div class="eServiceTutorial-header">
					<div class="eServiceTutorial-headline">Headline 2</div>
					<button class="eServiceTutorial-close js-eServiceTour-close">
						<icon:close/>
					</button>
				</div>
				<div class="eServiceTutorial-body">
					<div class="eServiceTutorial-description">
						Quod necessitatibus laborum totam sed iste iusto cumque non ducimus, iure nihil mollitia numquam ratione porro? Hic, libero, nemo?
					</div>
					<div class="eServiceTutorial-actions">
						<a class="btn btn_slim js-eServiceTour-next">Next</a>
					</div>
				</div>
			</div>
		</div>
	</li>
	<li class="eServiceTutorial-item" id="eServiceTutorial-item-2">
		<div class="eServiceTutorial-panel">
			<div class="eServiceTutorial-panelInner">
				<div class="eServiceTutorial-header">
					<div class="eServiceTutorial-headline">Headline 3</div>
					<button class="eServiceTutorial-close js-eServiceTour-close">
						<icon:close/>
					</button>
				</div>
				<div class="eServiceTutorial-body">
					<div class="eServiceTutorial-description">
						Quod necessitatibus laborum totam sed iste iusto cumque non ducimus, iure nihil mollitia numquam ratione porro? Hic, libero, nemo?
					</div>
					<div class="eServiceTutorial-actions">
						<a class="btn btn_slim js-eServiceTour-next">Next</a>
					</div>
				</div>
			</div>
		</div>
	</li>
	<li class="eServiceTutorial-item" id="eServiceTutorial-item-3">
		<div class="eServiceTutorial-panel">
			<div class="eServiceTutorial-panelInner">
				<div class="eServiceTutorial-header">
					<div class="eServiceTutorial-headline">Headline 4</div>
					<button class="eServiceTutorial-close js-eServiceTour-close">
						<icon:close/>
					</button>
				</div>
				<div class="eServiceTutorial-body">
					<div class="eServiceTutorial-description">
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Maxime veniam, aut, quod necessitatibus laborum totam sed iste iusto cumque non ducimus, iure nihil mollitia numquam ratione porro? Hic, libero, nemo?
					</div>
				</div>
			</div>
		</div>
	</li>
</ul>

<div class="eServiceTutorial-test">
	<div class="eServiceTutorial-test-item"><span data-eServiceTutorial-index="0" data-eServiceTutorial-position="top" data-eServiceTutorial-offset="20" data-eServiceTutorial-borderradius="[0,20,0,20]">top</span></div>
	<div class="eServiceTutorial-test-item"><span data-eServiceTutorial-index="1" data-eServiceTutorial-position="right">right</span></div>
	<div class="eServiceTutorial-test-item"><span data-eServiceTutorial-index="2" data-eServiceTutorial-position="bottom">bottom</span></div>
	<div class="eServiceTutorial-test-item"><span data-eServiceTutorial-index="3" data-eServiceTutorial-position="left">left</span></div>
</div>



</div>
