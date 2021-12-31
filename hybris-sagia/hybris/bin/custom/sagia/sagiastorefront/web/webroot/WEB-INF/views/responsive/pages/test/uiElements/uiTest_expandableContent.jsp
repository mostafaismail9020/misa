<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<div class="container">
    
<!-- Module description-->
<div class="uiTest">
	<h1 class="uiTest-headline">expandableContent</h1>
	<p class="uiTest-description"></p>
</div>
<!-- End of Module description-->


 <button  class="btn btn js-expandContent" data-expand-target="expand01" type="button">expand</button>


<div class="expandableContent" id="expand01">
	<div class="expandableContent-aside">
		aside
	</div>            
	<div class="expandableContent-main">           
		<p> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Totam nihil adipisci et deleniti quos dolores voluptatum quas nesciunt, vel animi voluptate inventore sequi, rerum itaque ipsum quibusdam commodi quasi voluptatem.</p>
		<p> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Totam nihil adipisci et deleniti quos dolores voluptatum quas nesciunt, vel animi voluptate inventore sequi, rerum itaque ipsum quibusdam commodi quasi voluptatem.</p>
		<p> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Totam nihil adipisci et deleniti quos dolores voluptatum quas nesciunt, vel animi voluptate inventore sequi, rerum itaque ipsum quibusdam commodi quasi voluptatem.</p>
		<p> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Totam nihil adipisci et deleniti quos dolores voluptatum quas nesciunt, vel animi voluptate inventore sequi, rerum itaque ipsum quibusdam commodi quasi voluptatem.</p>
	</div>
</div>
</div>

